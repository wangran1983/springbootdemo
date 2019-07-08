package com.donbala.service.intf;

import com.donbala.model.CmsRole;
import com.donbala.model.CmsRolemenu;

import java.util.List;

/**
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/2-11:56
 * @Description: 角色管理服务接口类
 **/
public interface CmsRoleServiceIntf {

    List<CmsRole> getRoleList(CmsRole cmsRole);

    CmsRole getRoleByid(String roleid);

    List<CmsRolemenu> getRoleMenu(String roleid);

    void deleteRolemenuByroleid(String roleid);

    void saveRole(CmsRole cmsRole);
}
