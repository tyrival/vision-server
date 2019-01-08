package com.tyrival.system.menu.service.impl;

import com.tyrival.entity.base.TreeNode;
import com.tyrival.entity.system.menu.Menu;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.system.menu.dao.MenuDAO;
import com.tyrival.system.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Override
    public BaseDAO<Menu> getDAO() {
        return menuDAO;
    }

    @Override
    public Menu getTreeById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        List<Menu> list = this.list();
        List<Menu> treeList = TreeNode.formatTree(list);
        for (int i = 0; i < treeList.size(); i++) {
            if (id.equals(treeList.get(i).getId())) {
                return treeList.get(i);
            }
        }
        return null;
    }
}
