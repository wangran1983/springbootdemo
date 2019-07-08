package com.donbala.util;

import com.donbala.model.CmsMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @CLassName: TreeNodeUtil
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/4-13:51
 * @Description: todo
 **/
public class TreeNodeUtil {

    /**
    *@methodname:
    *@description: 将菜单转为一个树
    *@param:
    *@return:
    *@date: 2019/7/4 13:56
    *@author: wangran
    */
    public   List<TreeNode>  getNodeTree(List<CmsMenu> list) {

            List<TreeNode> menuTreeNodeList = new ArrayList<>();

            for (CmsMenu menu : list) {
                TreeNode treeNode = new TreeNode();
                List<TreeNode> nodes = new ArrayList<>();
                if (menu.getParentmenuid().equals("0000")) {
                    treeNode.setNodeid(menu.getMenuid());
                    treeNode.setParentid(menu.getParentmenuid());
                    treeNode.setText(menu.getMenuname());
                    nodes = getChildren(menu.getMenuid(),list);
                    if(!nodes.isEmpty())
                    {
                        treeNode.setNodes(nodes);
                    }
                    menuTreeNodeList.add(treeNode);
                }
            }

        return  menuTreeNodeList;
    }

    private List<TreeNode> getChildren(String menuid,List<CmsMenu> list) {

        List<TreeNode> nodes = new ArrayList<>();
        for (CmsMenu menu : list) {
            TreeNode treeNode = new TreeNode();
            if (menu.getParentmenuid().equals(menuid)) {
                treeNode.setNodeid(menu.getMenuid());
                treeNode.setParentid(menu.getParentmenuid());
                treeNode.setText(menu.getMenuname());
                nodes.add(treeNode);
            }
        }

        return  nodes;
    }
}
