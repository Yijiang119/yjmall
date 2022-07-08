package com.yijiang.mall.service.api;

import com.yijiang.mall.entity.vo.DetailProjectVO;
import com.yijiang.mall.entity.vo.PortalTypeVO;
import com.yijiang.mall.entity.vo.ProjectVO;

import java.util.List;

/**
 * @ClassName ProjectService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/29 17:49
 * @Version 1.0
 */
public interface ProjectService {
    void saveProject(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeVOList();

    DetailProjectVO getDetailProjectVO(Integer projectId);
}
