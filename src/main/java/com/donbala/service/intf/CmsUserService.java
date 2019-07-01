package com.donbala.service.intf;

import com.donbala.model.CmsMenu;
import com.donbala.model.CmsUser;

import java.util.List;
import java.util.Map;

public interface CmsUserService {

     CmsUser getUserByUsercode(Map<String,String> usermap);

     void saveLoginTrace(String usercode,String logintype);

     List<CmsMenu> getUserMenu(String usercode);
}
