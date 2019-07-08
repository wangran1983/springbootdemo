package com.donbala.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.model.CmsMenu;
import com.donbala.service.intf.CmsUserServiceIntf;
import com.donbala.util.TreeNode;
import com.donbala.util.TreeNodeUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @CLassName: MenuController
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/4-13:27
 * @Description: todo
 **/
@Controller
public class MenuController {

    public final static Logger log = (Logger)LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private CmsUserServiceIntf cmsUserServiceIntf;

    @GetMapping("/controller/menutree")
    @ResponseBody
    public List<TreeNode>  getMenuTree() {

        TreeNodeUtil treeNodeUtil = new TreeNodeUtil();

        List<CmsMenu> menulist = cmsUserServiceIntf.getAllMenu();

        List<TreeNode> treeNodes = treeNodeUtil.getNodeTree(menulist);

        return treeNodes;
    }

}
