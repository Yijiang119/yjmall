package com.yijiang.mall.service.api;

import com.yijiang.mall.entity.Menu;

import java.util.List;

/**
 * @InterfaceName MenuService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/22 18:55
 * @Version 1.0
 */
public interface MenuService {
    List<Menu> getAll();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void removeMenu(Integer id);
}
