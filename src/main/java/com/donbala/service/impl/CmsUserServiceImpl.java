package com.donbala.service.impl;

import ch.qos.logback.classic.Logger;
import com.donbala.mapper.CmsLogintraceMapper;
import com.donbala.mapper.CmsMenuMapper;
import com.donbala.mapper.CmsUserMapper;
import com.donbala.mapper.CmsUserroleMapper;
import com.donbala.model.CmsLogintrace;
import com.donbala.model.CmsMenu;
import com.donbala.model.CmsUser;
import com.donbala.model.CmsUserrole;
import com.donbala.service.intf.CmsUserServiceIntf;
import com.donbala.util.DateUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CmsUserServiceImpl implements CmsUserServiceIntf {

    public final static Logger log = (Logger) LoggerFactory.getLogger(CmsUserServiceImpl.class);

    @Autowired
    private CmsUserMapper cmsUserMapper;
    @Autowired
    private CmsLogintraceMapper cmsLogintraceMapper;
    @Autowired
    private CmsMenuMapper cmsMenuMapper;
    @Autowired
    private CmsUserroleMapper cmsUserroleMapper;


    @Override
    public CmsUser getUserByUsercode(Map<String,String> usermap) {
       CmsUser cmsUser = cmsUserMapper.selectByUsercodeAndPsw(usermap);
       return cmsUser;
    }

    

    /**
     *@methodname: saveLoginTrace
     *@description: todo
     *@param: [usercode, logintype]
     *@return: void
     *@date: 2019/7/2 12:42
     *@author: wangran
     */
    @Override
    public void saveLoginTrace(String usercode,String logintype) {
        CmsLogintrace cmsLogintrace = new CmsLogintrace();

        cmsLogintrace.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        cmsLogintrace.setUsercode(usercode);
        cmsLogintrace.setOperatedate(DateUtil.getSysDate());
        cmsLogintrace.setOperatetype(logintype);
        cmsLogintrace.setMakedate(DateUtil.getSysDate());
        cmsLogintrace.setMakeuser(usercode);
        cmsLogintrace.setModifydate(DateUtil.getSysDate());
        cmsLogintrace.setModifyuser(usercode);

        cmsLogintraceMapper.insert(cmsLogintrace);
    }




    /**
    *@description: 获取用户的菜单
    */
    @Override
    public List<CmsMenu> getUserMenu(String usercode) {
        List<CmsMenu> menus = cmsMenuMapper.selectByUsercode(usercode);
        return  menus;
    }

    /**
     *@description: 查询所有的菜单，做菜单树
     */
    @Override
    public List<CmsMenu> getAllMenu() {
        List<CmsMenu> menus = cmsMenuMapper.selectAllMenu();
        return  menus;
    }


    /**
    *@description: 根据页面的查询条件查询客户列表
    */
    @Override
    public List<CmsUser> queryUsers(CmsUser cmsUser) {
        List<CmsUser> userList = cmsUserMapper.selectUsers(cmsUser);
        return userList;
    }

    /**
    *@description: 根据用户id删除用户，包括用户的角色
    */
    @Override
    @Transactional
    public void deleteUser(String usercode) {
        cmsUserMapper.deleteByPrimaryKey(usercode);
        cmsUserroleMapper.deleteByUsercode(usercode);
    }

    @Override
    public CmsUser queryUserbyUsercode(String usercode) {
        CmsUser cmsUser = cmsUserMapper.selectByPrimaryKey(usercode);
        List<CmsUserrole> cmsUserroleList = cmsUserroleMapper.queryRoleByUsercode(usercode);
        cmsUser.setCmsUserroles(cmsUserroleList);
        return cmsUser;
    }


    @Override
    @Transactional
    public void editUser(CmsUser cmsUser) {
        cmsUserMapper.deleteByPrimaryKey(cmsUser.getUsercode());
        cmsUserroleMapper.deleteByUsercode(cmsUser.getUsercode());

        saveUser(cmsUser);
    }

    @Transactional
    @Override
    public void saveUser(CmsUser cmsUser) {
        cmsUserMapper.insert(cmsUser);
        cmsUserroleMapper.insertList(cmsUser.getCmsUserroles());
    }

    @Override
    @Transactional
    public void setPassword(CmsUser cmsUser) {
        cmsUserMapper.updateByPrimaryKeySelective(cmsUser);
    }
}
