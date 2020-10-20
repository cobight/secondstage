package com.aaa.sbm.entity;

import java.util.List;

/**
 * fileName:TreeNode
 * description:
 * author:zz
 * createTime:2020/10/15 11:30
 * version:1.0.0
 */
public class TreeNode {

    private int id;
    private String label;
    private int parentId;
    private String icon;
    private String menuUrl;
    //子菜单集合
    private List<TreeNode> children;

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", parentId=" + parentId +
                ", icon='" + icon + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", children=" + children +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
