package com.tyrival.entity.base;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/11/1
 * @Version: V1.0
 */
public class TreeNode extends Base {

    private String parentId;

    private List<TreeNode> children;

    /**
     * 将节点列表转换为菜单树，可能不止一棵
     * @param nodeList
     * @return
     */
    public static <T> List<T> formatTree(List<T> nodeList) {
        if (!(nodeList.get(0) instanceof TreeNode)) {
            return null;
        }
        Map<String, TreeNode> map = new HashMap<>();
        List<TreeNode> rootList = new ArrayList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode node = (TreeNode) nodeList.get(i);
            map.put(node.getId(), node);
        }
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode node = (TreeNode) nodeList.get(i);
            String parentId = node.getParentId();
            if (StringUtils.isEmpty(parentId)) {
                rootList.add(node);
            } else {
                TreeNode parent = map.get(parentId);
                parent.addChild(node);
            }
        }
        return (List<T>) rootList;
    }

    /**
     * 添加子节点
     * @param node
     */
    private void addChild(TreeNode node) {
        this.children.add(node);
    }

    /**
     * 根据id移除子节点
     * @param id
     */
    private void removeChild(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        for (int i = 0; i < this.children.size(); i++) {
            if (id.equals(this.children.get(i).getId())) {
                this.children.remove(i);
                return;
            }
        }
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
