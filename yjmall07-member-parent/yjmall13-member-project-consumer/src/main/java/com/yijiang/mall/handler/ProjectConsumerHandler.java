package com.yijiang.mall.handler;

import com.yijiang.mall.api.MySQLRemoteService;
import com.yijiang.mall.config.OSSProperties;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.vo.*;
import com.yijiang.mall.util.MallUtil;
import com.yijiang.mall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProjectConsumerHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/29 18:36
 * @Version 1.0
 */
@Controller
public class ProjectConsumerHandler {

    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/create/confirm.html")
    public String saveConfirm(MemberConfirmInfoVO memberConfirmInfoVO, HttpSession session, ModelMap modelMap){
        // 从session域取出ProjectVO对象
        ProjectVO projectVO = (ProjectVO)session.getAttribute(MallConstant.ATTR_NAME_TEMPLE_PROJECT);

        // 判断ProjectVO是否回null
        if (projectVO == null){
            // 这里不多做处理了，就直接抛出异常
            throw new RuntimeException(MallConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
        }

        // ProjectVO正常，开始向其中存放MemberConfirmInfo
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        // 从session域中读取当前登录的用户
        MemberLoginVO loginMember = (MemberLoginVO)session.getAttribute(MallConstant.ATTR_NAME_LOGIN_MEMBER);
        Integer memberId = loginMember.getId();

        // 调用远程方法保存ProjectVO对象和当前登录的用户的id
        ResultEntity<String> saveResultEntity = mySQLRemoteService.saveProjectVORemote(projectVO, memberId);

        String result = saveResultEntity.getOperationResult();

        if (ResultEntity.FAILED.equals(result)){
            // 保存出错，返回确认的界面，并且携带错误的消息
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE, saveResultEntity.getOperationMessage());
            return "project-confirm";
        }

        // 保存正常完成，删除session中临时存放的ProjectVO
        session.removeAttribute(MallConstant.ATTR_NAME_TEMPLE_PROJECT);

        // 进入成功页面
        return "redirect:http://localhost/project/create/success.html";
    }

    // 回报页面上传图片时触发的ajax请求对应的handler方法
    @ResponseBody
    @RequestMapping("/create/upload/return/picture.json")
    public ResultEntity<String> uploadReturnPicture(@RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {
        // 判断是否是有效上传
        boolean pictureIsEmpty = returnPicture.isEmpty();
        if (pictureIsEmpty){
            // 如果上传文件为空
            ResultEntity.failed(MallConstant.MESSAGE_RETURN_PIC_EMPTY);
        }

        // 进行上传到OSS服务器的操作
        ResultEntity<String> returnPictureResultEntity = MallUtil.uploadFileToOss(ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                returnPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                returnPicture.getOriginalFilename());
        return returnPictureResultEntity;
    }





    // 回报页面保存回报信息的ajax请求对应的方法
    @ResponseBody
    @RequestMapping("/create/save/return.json")
    public ResultEntity<String> saveReturn(ReturnVO returnVO, HttpSession session) {
        try {
            // 从session域取出ProjectVO对象
            ProjectVO projectVO = (ProjectVO)session.getAttribute(MallConstant.ATTR_NAME_TEMPLE_PROJECT);

            // 判断ProjectVO是否回null
            if (projectVO == null){
                return ResultEntity.failed(MallConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
            }

            // ProjectVO不为null
            // 取出projectVO中的returnVOList
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();

            // 判断取出的list是否为空或长度为0
            if (returnVOList == null || returnVOList.size() == 0){
                // 初始化returnVOList
                returnVOList = new ArrayList<>();
                // 存入projectVO
                projectVO.setReturnVOList(returnVOList);
            }
            // 向returnVOList中存放当前接收的returnVO
            returnVOList.add(returnVO);

            // 重新将ProjectVO存入session域
            session.setAttribute(MallConstant.ATTR_NAME_TEMPLE_PROJECT,projectVO);

            // 全部操作正常完成，返回成功的ResultEntity
            return ResultEntity.successWithoutData();

        } catch (Exception e){
            e.printStackTrace();
            // 出现异常返回failed，带上异常信息
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/create/project/information")
    public String saveProjectBasicInfo(
            // 接收除了上传图片之外的其他普通数据
            ProjectVO projectVO,
            // 接收上传的头图
            MultipartFile headerPicture,
            // 接收上传的详情图片
            List<MultipartFile> detailPictureList,
            // 用来将收集了一部分数据的 ProjectVO 对象存入 Session 域
            HttpSession session,
            // 用来在当前操作失败后返回上一个表单页面时携带提示消息
            ModelMap modelMap
    ) throws IOException {
        // 一、完成头图上传
        // 1.获取当前 headerPicture 对象是否为空
        boolean headerPictureIsEmpty = headerPicture.isEmpty();
        if(headerPictureIsEmpty) {
            // 2.如果没有上传头图则返回到表单页面并显示错误消息
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE,
                    MallConstant.MESSAGE_HEADER_PIC_EMPTY);
            return "project-launch";
        }
        // 3.如果用户确实上传了有内容的文件，则执行上传
        ResultEntity<String> uploadHeaderPicResultEntity = MallUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                headerPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                headerPicture.getOriginalFilename());
        String result = uploadHeaderPicResultEntity.getOperationResult();
        // 4.判断头图是否上传成功
        if(ResultEntity.SUCCESS.equals(result)) {
            // 5.如果成功则从返回的数据中获取图片访问路径
            String headerPicturePath = uploadHeaderPicResultEntity.getQueryData();
            // 6.存入 ProjectVO 对象中
            projectVO.setHeaderPicturePath(headerPicturePath);
        } else {
            // 7.如果上传失败则返回到表单页面并显示错误消息
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE,
                    MallConstant.MESSAGE_HEADER_PIC_UPLOAD_FAILED);
            return "project-launch";
        }
        // 二、上传详情图片
        // 1.创建一个用来存放详情图片路径的集合
        List<String> detailPicturePathList = new ArrayList<String>();
        // 2.检查 detailPictureList 是否有效
        if(detailPictureList == null || detailPictureList.size() == 0) {
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE,
                    MallConstant.MESSAGE_DETAIL_PIC_EMPTY);
            return "project-launch";
        }
        // 3.遍历 detailPictureList 集合
        for (MultipartFile detailPicture : detailPictureList) {
            // 4.当前 detailPicture 是否为空
            if(detailPicture.isEmpty()) {
                // 5.检测到详情图片中单个文件为空也是回去显示错误消息
                modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE,
                        MallConstant.MESSAGE_DETAIL_PIC_EMPTY);
                return "project-launch";
            }
            // 6.执行上传
            ResultEntity<String> detailUploadResultEntity = MallUtil.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    detailPicture.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    detailPicture.getOriginalFilename());
            // 7.检查上传结果
            String detailUploadResult = detailUploadResultEntity.getOperationResult();
            if(ResultEntity.SUCCESS.equals(detailUploadResult)) {
                String detailPicturePath = detailUploadResultEntity.getQueryData();
                // 8.收集刚刚上传的图片的访问路径
                detailPicturePathList.add(detailPicturePath);
            } else {
                // 9.如果上传失败则返回到表单页面并显示错误消息
                modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE,
                        MallConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILED);
                return "project-launch";
            }
        }
        // 10.将存放了详情图片访问路径的集合存入 ProjectVO 中
        projectVO.setDetailPicturePathList(detailPicturePathList);
        // 三、后续操作
        // 1.将 ProjectVO 对象存入 Session 域
        session.setAttribute(MallConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);
        // 2.以完整的访问路径前往下一个收集回报信息的页面
        return "redirect:http://localhost/project/return/info/page.html";
    }

    @RequestMapping("/show/project/detail/{projectId}")
    public String getDetailProject(@PathVariable("projectId") Integer projectId, ModelMap modelMap){
        ResultEntity<DetailProjectVO> resultEntity = mySQLRemoteService.getDetailProjectVORemote(projectId);
        if (ResultEntity.SUCCESS.equals(resultEntity.getOperationResult())){
            DetailProjectVO detailProjectVO = resultEntity.getQueryData();
            modelMap.addAttribute(MallConstant.ATTR_NAME_DETAIL_PROJECT,detailProjectVO);
        }
        return "project-show-detail";
    }

}
