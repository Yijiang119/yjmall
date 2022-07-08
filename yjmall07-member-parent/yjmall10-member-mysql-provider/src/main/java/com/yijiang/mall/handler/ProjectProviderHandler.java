package com.yijiang.mall.handler;

import com.yijiang.mall.entity.vo.DetailProjectVO;
import com.yijiang.mall.entity.vo.PortalTypeVO;
import com.yijiang.mall.entity.vo.ProjectVO;
import com.yijiang.mall.service.api.ProjectService;
import com.yijiang.mall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ProjectProviderHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/29 17:49
 * @Version 1.0
 */
@RestController
public class ProjectProviderHandler {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/save/project/vo/remote")
    public ResultEntity<String> saveProjectVORemote(
            @RequestBody ProjectVO projectVO,
            @RequestParam("memberId") Integer memberId) {
        try {
            // 调用“本地”Service 执行保存
            projectService.saveProject(projectVO, memberId);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/portal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote(){
        try {
            List<PortalTypeVO> portalTypeVOList = projectService.getPortalTypeVOList();
            return ResultEntity.successWithData(portalTypeVOList);
        } catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/detail/project/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId){
        return ResultEntity.successWithData(projectService.getDetailProjectVO(projectId));
    }

}
