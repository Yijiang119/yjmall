package com.yijiang.mall.service.impl;

import com.yijiang.mall.entity.Menu;
import com.yijiang.mall.entity.MenuExample;
import com.yijiang.mall.mapper.MenuMapper;
import com.yijiang.mall.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/22 18:55
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        // 由于 pid 没有传入，一定要使用有选择的更新，保证“pid”字段不会被置空
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
