package com.donbala.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.model.CmsMenu;
import com.donbala.model.CmsUser;
import com.donbala.service.intf.CmsUserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CmsUserService cmsUserService;


    /**
    *@description: 用户权限验证，成功后，则写入登录轨迹，返回用户所有菜单，失败则返回登录界面
    *@param: [usercode, password, mv, session, modelMap]
    *@return: org.springframework.web.servlet.ModelAndView
    *@date: 2019/6/26 18:16
    *@author: wangran
    */
    @PostMapping("/userlogin")
    public ModelAndView login(String usercode, String password, ModelAndView mv, HttpSession session) {

        Map<String, String> usermap = new HashMap<>();
        CmsUser cmsUser = new CmsUser();

        usermap.put("usercode",usercode);
        usermap.put("password",password);
        cmsUser = cmsUserService.getUserByUsercode(usermap);

        //login failed
        if (cmsUser == null) {

            System.out.println("没有此用户");
            mv.setViewName("redirect:/");

            return  mv;
        }
        else {//login success

            cmsUserService.saveLoginTrace(usercode,"1");

            List<CmsMenu> menus = cmsUserService.getUserMenu(usercode);
            session.setAttribute("user",cmsUser);

            for (CmsMenu menu : menus) {
                System.out.println(menu.getMenuname());
            }

            session.setAttribute("menus",menus);
            mv.setViewName("redirect:/main/welcome.jsp");
        }

        return mv;
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

        log.info(cmsUser.getEmail());

        if(session!=null && cmsUser!=null)
        {
            session.invalidate();
            cmsUserService.saveLoginTrace(cmsUser.getUsercode(),"0");
        }

        mv.setViewName("redirect:/");

        return mv;
    }

}