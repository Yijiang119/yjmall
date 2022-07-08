package com.yijiang.mall.service.api;

import com.github.pagehelper.PageInfo;
import com.yijiang.mall.entity.Project;

/**
 * @InterfaceName ProjectService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/31 21:19
 * @Version 1.0
 */
public interface ProjectService {

    PageInfo<Project> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    void update(Project project);

    void agreeProject(Integer projectId);
}
