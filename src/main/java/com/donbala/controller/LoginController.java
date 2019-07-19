package com.donbala.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.model.CmsMenu;
import com.donbala.model.CmsUser;
import com.donbala.service.intf.CmsUserServiceIntf;
import com.donbala.util.MessageNotice;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CmsUserServiceIntf cmsUserServiceIntf;


    /**
    *@description: 用户权限验证，成功后，则写入登录轨迹，返回用户所有菜单，失败则返回登录界面
    */
    @PostMapping("/userlogin")
    @ResponseBody
    public MessageNotice login(String usercode, String password, ModelAndView mv, HttpSession session) {

        MessageNotice messageNotice = new MessageNotice();

        System.out.println("1111111111111111111");
        Map<String, String> usermap = new HashMap<>();
        CmsUser cmsUser = new CmsUser();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        usermap.put("usercode",usercode);
        usermap.put("password",password);
        cmsUser = cmsUserServiceIntf.getUserByUsercode(usermap);

        //login failed
        if (cmsUser == null) {
            mv.setViewName("redirect:/");
            messageNotice.setFlag("0|");
            messageNotice.setMessage("用户名或密码错误");
        }
        else {
            cmsUserServiceIntf.saveLoginTrace(usercode,"1");

            List<CmsMenu> menus = cmsUserServiceIntf.getUserMenu(usercode);
            session.setAttribute("user",cmsUser);
            session.setAttribute("menus",menus);
            messageNotice.setFlag("1");
        }
        return messageNotice;
    }

    /**
    *@description: 登出操作
    *@param: [mv, session]
    *@return: org.springframework.web.servlet.ModelAndView
    *@date: 2019/6/26 18:17
    *@author: wangran
    */
    @GetMapping("/userlogout")
    public ModelAndView logout(ModelAndView mv, HttpSession session){

        CmsUser cmsUser = (CmsUser) session.getAttribute("user");
        if (session == null || cmsUser == null) {
            mv.setViewName("redirect:/");
        }
        else if(session!=null && cmsUser!=null){
            session.invalidate();
            cmsUserServiceIntf.saveLoginTrace(cmsUser.getUsercode(),"0");
            mv.setViewName("redirect:/");
        }
        return mv;
    }

}
