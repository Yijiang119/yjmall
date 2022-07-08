package com.yijiang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.Project;
import com.yijiang.mall.exception.LoginAcctAlreadyInUseForUpdateException;
import com.yijiang.mall.mapper.ProjectMapper;
import com.yijiang.mall.service.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProjectServiceImpl
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/31 21:19
 * @Version 1.0
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public PageInfo<Project> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.执行查询
        List<Project> ProjectList = projectMapper.selectProjectListByKeyword(keyword);
        for (Project project : ProjectList) {
            setStatusDescriptionByStatus(project);
        }
        // 3.封装为 PageInfo 对象返回
        return new PageInfo<>(ProjectList);
    }

    @Override
    public void update(Project project) {
        try {
            projectMapper.updateByPrimaryKeySelective(project);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseForUpdateException(MallConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            throw e;
        }
    }

    @Override
    public void agreeProject(Integer projectId) {
        Project project = projectMapper.selectByPrimaryKey(projectId);
        project.setStatus(1);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    private void setStatusDescriptionByStatus(Project project) {
        Integer status = project.getStatus();
        if (status < 0 || status > 3) {
            throw new RuntimeException("换购项目状态异常");
        }
        switch (status){
            case 0 :
                project.setStatusDescription("即将开始");
                break;
            case 1 :
                project.setStatusDescription("换购中");
                break;
            case 2 :
                project.setStatusDescription("换购失败");
                break;
            case 3 :
                project.setStatusDescription("换购成功");
                break;
        }
    }

}
