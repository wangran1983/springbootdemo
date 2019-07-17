package com.donbala.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.model.CmsUser;
import com.donbala.model.CmsUserrole;
import com.donbala.service.intf.CmsUserServiceIntf;
import com.donbala.util.DateUtil;
import com.donbala.util.MessageNotice;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @CLassName: UserController
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/10-14:08
 * @Description: todo
 **/
@Controller
public class UserController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CmsUserServiceIntf cmsUserService;


    @GetMapping("/controller/queryusers")
    @ResponseBody
    public List<CmsUser> queryUsers(CmsUser cmsUser) {
        List<CmsUser> cmsUsers = cmsUserService.queryUsers(cmsUser);
        return cmsUsers;
    }

    @PostMapping("/controller/saveuser")
    @ResponseBody
    public MessageNotice saveUser(@RequestBody CmsUser cmsUser, HttpSession session) {
        MessageNotice messageNotice = new MessageNotice();
        String sysdate = DateUtil.getSysDate();
        String makeuser = ((CmsUser) session.getAttribute("user")).getUsercode();
        String password = DigestUtils.md5DigestAsHex(cmsUser.getPassword().getBytes());
        cmsUser.setPassword(password);
        cmsUser.setMakedate(sysdate);
        cmsUser.setMakeuser(makeuser);
        cmsUser.setModifydate(sysdate);
        cmsUser.setModifyuser(makeuser);

        List<CmsUserrole> cmsUserroleList = cmsUser.getCmsUserroles();
        initUserroles(sysdate, makeuser, cmsUserroleList);

        List<CmsUser> cmsUserList = cmsUserService.queryUsers(cmsUser);
        if (cmsUserList.size() > 0) {
            messageNotice.setFlag("0");
            messageNotice.setMessage("该用户已经存在！");
            return messageNotice;
        }else{
            cmsUserService.saveUser(cmsUser);
            messageNotice.setFlag("1");
        }

        return messageNotice;
    }



    @PostMapping("/controller/deleteuser")
    @ResponseBody
    public MessageNotice deleteUser(String usercode) {

        MessageNotice messageNotice = new MessageNotice();

        try {
            cmsUserService.deleteUser(usercode);
            messageNotice.setFlag("1");
            messageNotice.setMessage("删除成功");
        } catch (Exception e) {
            messageNotice.setFlag("0");
            messageNotice.setMessage("删除失败");
            e.printStackTrace();
        }

        return messageNotice;
    }

    @GetMapping("/controller/getuserdetail")
    @ResponseBody
    public CmsUser getUserDetail(String usercode) {
        CmsUser cmsUser = cmsUserService.queryUserbyUsercode(usercode);
        return cmsUser;
    }

    @PostMapping("/controller/edituser")
    @ResponseBody
    public MessageNotice editUser(@RequestBody CmsUser cmsUser, HttpSession session) {
        MessageNotice messageNotice = new MessageNotice();
        String sysDate = DateUtil.getSysDate();
        String modifyuser = ((CmsUser) session.getAttribute("user")).getUsercode();

        CmsUser cmsUseredit = cmsUserService.queryUserbyUsercode(cmsUser.getUsercode());

        cmsUseredit.setName(cmsUser.getName());
        cmsUseredit.setMobile(cmsUser.getMobile());
        cmsUseredit.setEmail(cmsUser.getEmail());
        cmsUseredit.setModifydate(sysDate);
        cmsUseredit.setModifyuser(modifyuser);

        List<CmsUserrole> cmsUserroleList = cmsUser.getCmsUserroles();
        initUserroles(sysDate, modifyuser, cmsUserroleList);

        cmsUserService.editUser(cmsUser);
        messageNotice.setFlag("1");
        messageNotice.setMessage("修改成功");

        return messageNotice;
    }

    /**
    *@description: 初始化用户的角色列表
    */
    private void initUserroles(String sysDate, String modifyuser, List<CmsUserrole> cmsUserroleList) {
        for (CmsUserrole cmsUserrole : cmsUserroleList) {
            cmsUserrole.setMakedate(sysDate);
            cmsUserrole.setMakeuser(modifyuser);
            cmsUserrole.setModifydate(sysDate);
            cmsUserrole.setModifyuser(modifyuser);
        }
    }

}
