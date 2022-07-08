package com.yijiang.mall.handler;

import com.yijiang.mall.api.MySQLRemoteService;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.vo.PortalTypeVO;
import com.yijiang.mall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName PotalHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 17:46
 * @Version 1.0
 */
@Controller
public class PortalHandler {

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/")
    public String showPortalPage(ModelMap modelMap) {
        // 调用MySQLRemoteService提供的方法查询首页要显示的数据
        ResultEntity<List<PortalTypeVO>> resultEntity = mySQLRemoteService.getPortalTypeProjectDataRemote();
        // 如果操作成功，将得到的list加入请求域
        if (ResultEntity.SUCCESS.equals(resultEntity.getOperationResult())){
            List<PortalTypeVO> portalTypeVOList = resultEntity.getQueryData();
            modelMap.addAttribute(MallConstant.ATTR_NAME_PORTAL_TYPE_LIST,portalTypeVOList);
        }
        return "portal";
    }
}

