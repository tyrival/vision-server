package com.tyrival.system.authority.service.impl;

import com.tyrival.entity.base.TreeNode;
import com.tyrival.entity.system.authority.MenuAuthority;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.entity.system.authority.MenuAuthorityVo;
import com.tyrival.entity.system.user.User;
import com.tyrival.system.authority.dao.MenuAuthorityDAO;
import com.tyrival.system.authority.service.MenuAuthorityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuAuthorityServiceImpl extends BaseServiceImpl<MenuAuthority> implements MenuAuthorityService {

    @Autowired
    private MenuAuthorityDAO menuAuthorityDAO;

    @Override
    public BaseDAO<MenuAuthority> getDAO() {
        return menuAuthorityDAO;
    }

    @Override
    public List<MenuAuthorityVo> getTreeByUser(User user) {
        List<MenuAuthorityVo> list = this.menuAuthorityDAO.listByUser(user);
        List<MenuAuthorityVo> treeList = TreeNode.formatTree(list);
        return treeList;
    }

    @Override
    public MenuAuthorityVo getTreeByIdAndUser(User user, String id) {
        List<MenuAuthorityVo> treeList = this.getTreeByUser(user);
        for (int i = 0; i < treeList.size(); i++) {
            MenuAuthorityVo tree = treeList.get(i);
            if (tree != null && !StringUtils.isNotEmpty(tree.getId()) && tree.getId().equals(id)) {
                return tree;
            }
        }
        return null;
    }
}
