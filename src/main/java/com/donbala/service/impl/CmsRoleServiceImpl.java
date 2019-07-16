package com.donbala.service.impl;

import ch.qos.logback.classic.Logger;
import com.donbala.mapper.CmsRoleMapper;
import com.donbala.mapper.CmsRolemenuMapper;
import com.donbala.model.CmsRole;
import com.donbala.model.CmsRolemenu;
import com.donbala.service.intf.CmsRoleServiceIntf;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/2-11:58
 * @Description: 角色管理服务实现类
 **/
@Service
public class CmsRoleServiceImpl implements CmsRoleServiceIntf {

    public final static Logger log = (Logger) LoggerFactory.getLogger(CmsRoleServiceImpl.class);

    @Autowired
    private CmsRoleMapper cmsRoleMapper;
    @Autowired
    private CmsRolemenuMapper cmsRolemenuMapper;

 
    @Override
    /**
    *@methodname: getRoleList
    *@description: 根据输入的条件查询role的列表
    *@param: []
    *@return: java.util.List<com.donbala.model.CmsRole>
    *@date: 2019/7/2 12:43
    *@author: wangran
    */
    public List<CmsRole> getRoleList(CmsRole cmsRole) {
        List<CmsRole> rolelist = cmsRoleMapper.selectAll(cmsRole);
        return rolelist;
    }

    @Override
    public List<CmsRolemenu> getRoleMenu(String roleid) {
        List<CmsRolemenu> cmsRolemenuList = cmsRolemenuMapper.selectByRoleid(roleid);
        return cmsRolemenuList;
    }

    @Override
    @Transactional
    public void deleteRolemenuByroleid(String roleid) {
        cmsRoleMapper.deleteByPrimaryKey(roleid);
        cmsRolemenuMapper.deleteByRoleid(roleid);
    }


    @Override
    @Transactional
    public void saveRole(CmsRole cmsRole) {
        List<CmsRolemenu> cmsRolemenuList = new ArrayList<>();
        String makedate = cmsRole.getMakedate();
        String modifydate = cmsRole.getModifydate();
        String roleid = cmsRole.getRoleid();
        String usercode = cmsRole.getMakeuser();

        deleteRolemenuByroleid(roleid);

        for (String menu : cmsRole.getMenus()) {
            CmsRolemenu cmsRolemenu = new CmsRolemenu();
            cmsRolemenu.setRoleid(roleid);
            cmsRolemenu.setMenuid(menu);
            cmsRolemenu.setMakedate(makedate);
            cmsRolemenu.setMakeuser(usercode);
            cmsRolemenu.setModifydate(modifydate);
            cmsRolemenu.setModifyuser(usercode);
            cmsRolemenuMapper.insert(cmsRolemenu);
        }

        cmsRoleMapper.insert(cmsRole);
    }


    @Override
    public CmsRole getRoleByid(String roleid) {
        CmsRole cmsRole = cmsRoleMapper.selectByPrimaryKey(roleid);
        return cmsRole;
    }
}
