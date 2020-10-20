package com.aaa.sbm.service;

import com.aaa.sbm.constants.ReturnStatus;
import com.aaa.sbm.dao.PowerDao;
import com.aaa.sbm.entity.TreeNode;
import com.aaa.sbm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * fileName:PowerServiceImpl
 * description:
 * author:zz
 * createTime:2020/10/15 11:38
 * version:1.0.0
 */
@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerDao powerDao;

    @Override
    public Result treeData() {
        //获取所有菜单数据
        List<TreeNode> treeNodeList = powerDao.listAll();
        List<TreeNode> tempTreeNodes = new ArrayList<TreeNode>();
        //判断集合是否为空
        if(treeNodeList!=null&&treeNodeList.size()>0){
           //加载一级菜单
            for (TreeNode treeNode : treeNodeList) {
                if(treeNode.getParentId()==0){
                    tempTreeNodes.add(treeNode);
                    //查找的孩子
                    bindChildren(treeNode,treeNodeList);
                }
            }
        }
        return new Result< List<TreeNode>>(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),tempTreeNodes);
    }


    /**
     * 递归找孩子
     * @param currTreeNode  当前节点
     * @param treeNodeList   所有节点
     */
    private void  bindChildren(TreeNode currTreeNode,List<TreeNode> treeNodeList){
        //循环查找孩子
        for (TreeNode node : treeNodeList) {
            if(node.getParentId()==currTreeNode.getId()){ //循环节点的父ID等于当前节点ID，说明循环节点是当前节点的孩子
                //获取原有节点
                List<TreeNode> children = currTreeNode.getChildren();
                //判断是否为空,说明循环节点是当前节点的第一孩子
                if(children==null){
                    children = new ArrayList<>();
                }
                //把循环节点加入孩子集合
                children.add(node);
                //与当前节点绑定
                 currTreeNode.setChildren(children);
                 //继续为循环节点找孩子
                 bindChildren(node,treeNodeList);
                //出错
                //treeNodeList.remove(node);
            }
        }
    }
}
