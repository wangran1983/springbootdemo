package com.donbala.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.model.CmsRole;
import com.donbala.model.CmsRolemenu;
import com.donbala.model.CmsUser;
import com.donbala.service.intf.CmsRoleServiceIntf;
import com.donbala.util.DateUtil;
import com.donbala.util.MessageNotice;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/2-11:55
 * @Description: todo
 **/
@Controller
public class RoleController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private CmsRoleServiceIntf cmsRoleService ;


    /**
    *@methodname: getRoleList
    *@description: 查询角色的列表
    *@param: []
    *@return: java.util.List<com.donbala.model.CmsRole>
    *@date: 2019/7/2 13:03
    *@author: wangran
    */
    @GetMapping("/controller/getrolelist")
    @ResponseBody
    public List<CmsRole> getRoleList(CmsRole cmsRole) {
        List<CmsRole> rolelist = cmsRoleService.getRoleList(cmsRole);
        return rolelist;
    }


    /**
    *@methodname: saveRole
    *@description: 保存角色信息和菜单信息
    *@param: [cmsRole]
    *@return: com.donbala.util.MessageNotice
    *@date: 2019/7/5 16:29
    *@author: wangran
    */
    @PostMapping("/controller/saverole")
    @ResponseBody
    public MessageNotice saveRole(@RequestBody CmsRole cmsRole, HttpSession session) {

        MessageNotice messageNotice = new MessageNotice();

        String operatetype = cmsRole.getOperatetype();

        String sysdate = DateUtil.getSysDate();
        String usercode = ((CmsUser) session.getAttribute("user")).getUsercode();


        CmsRole role= cmsRoleService.getRoleByid(cmsRole.getRoleid());

        if(operatetype.equals("insert"))
        {
            cmsRole.setMakedate(sysdate);
            cmsRole.setMakeuser(usercode);
            cmsRole.setModifydate(sysdate);
            cmsRole.setModifyuser(usercode);

            if (role != null) {
                messageNotice.setFlag("0");
                messageNotice.setMessage("该用户编码已经存在！");
                return messageNotice;
            }

            saveRoleOperate(cmsRole, messageNotice);

        } else if (operatetype.equals("update")) {
            cmsRole.setMakedate(role.getMakedate());
            cmsRole.setMakeuser(role.getMakeuser());
            cmsRole.setModifydate(sysdate);
            cmsRole.setModifyuser(usercode);

            saveRoleOperate(cmsRole, messageNotice);
        }

        return messageNotice;
    }

    /**
    *@methodname:
    *@description:  保存客户信息返回结果函数 insert and update公用
    *@param:
    *@return:
    *@date: 2019/7/8 16:55
    *@author: wangran
    */
    private void saveRoleOperate(CmsRole cmsRole, MessageNotice messageNotice) {
        try {
            cmsRoleService.saveRole(cmsRole);
            messageNotice.setFlag("1");
            messageNotice.setMessage("保存成功");

        } catch (Exception ex) {
            messageNotice.setFlag("0");
            messageNotice.setMessage("保存失败");
            ex.printStackTrace();
        }
    }


    /**
     *@methodname: getRoleDetail
     *@description: 查询角色明细
     *@param: [cmsRole]
     *@return: com.donbala.model.CmsRole
     *@date: 2019/7/8 9:59
     *@author: wangran
     */
    @GetMapping("/controller/getroledetail")
    @ResponseBody
    public CmsRole getRoleDetail(CmsRole cmsRole) {

        System.out.println(cmsRole.getRoleid());

        CmsRole tCmsrRole = new CmsRole();

        List<CmsRole> rolelist = cmsRoleService.getRoleList(cmsRole);
        tCmsrRole = rolelist.get(0);

        List<CmsRolemenu> cmsRolemenuList = cmsRoleService.getRoleMenu(cmsRole.getRoleid());

        List<String> rolemenus = new ArrayList<>();

        for (CmsRolemenu cmsRolemenu : cmsRolemenuList) {
            rolemenus.add(cmsRolemenu.getMenuid());
        }

        tCmsrRole.setMenus(rolemenus);

        return tCmsrRole;
    }


    @PostMapping("/controller/deleterole")
    @ResponseBody
    /**
    *@methodname: deleteRole
    *@description: 删除角色及下面的菜单
    *@param: [cmsRole]
    *@return: com.donbala.util.MessageNotice
    *@date: 2019/7/8 10:13
    *@author: wangran
    */
    public MessageNotice deleteRole(CmsRole cmsRole) {

        MessageNotice messageNotice = new MessageNotice();
        try {
            cmsRoleService.deleteRolemenuByroleid(cmsRole.getRoleid());
            messageNotice.setMessage("删除成功");
            messageNotice.setFlag("1");
        } catch (Exception ex) {
            messageNotice.setFlag("0");
            messageNotice.setMessage("删除失败");
        }

        return messageNotice;
    }

}
