package com.donbala.service.impl;

import ch.qos.logback.classic.Logger;
import com.donbala.mapper.CmsLogintraceMapper;
import com.donbala.mapper.CmsMenuMapper;
import com.donbala.mapper.CmsUserMapper;
import com.donbala.model.CmsLogintrace;
import com.donbala.model.CmsMenu;
import com.donbala.model.CmsUser;
import com.donbala.service.intf.CmsUserService;
import com.donbala.util.DateUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CmsUserServiceImpl implements CmsUserService {

    public final static Logger log = (Logger) LoggerFactory.getLogger(CmsUserServiceImpl.class);

    @Autowired
    private CmsUserMapper cmsUserMapper;
    @Autowired
    private CmsLogintraceMapper cmsLogintraceMapper;
    @Autowired
    private CmsMenuMapper cmsMenuMapper;

    @Override
    public CmsUser getUserByUsercode(Map<String,String> usermap) {
        System.out.println("-----------121312123121312-------------------------");
        log.info("jinru service");
       CmsUser cmsUser = new CmsUser();
       cmsUser = cmsUserMapper.selectByUsercodeAndPsw(usermap);

       return cmsUser;
    }

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

    @Override
    public List<CmsMenu> getUserMenu(String usercode) {
        List<CmsMenu> menus = new ArrayList<>();

        menus = cmsMenuMapper.selectByUsercode(usercode);

        return  menus;
    }

}
