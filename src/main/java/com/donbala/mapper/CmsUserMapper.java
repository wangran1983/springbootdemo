package com.donbala.mapper;

import com.donbala.model.CmsUser;

import java.util.Map;

public interface CmsUserMapper {
    int deleteByPrimaryKey(String usercode);

    int insert(CmsUser record);

    int insertSelective(CmsUser record);

    CmsUser selectByPrimaryKey(String usercode);

    int updateByPrimaryKeySelective(CmsUser record);

    int updateByPrimaryKey(CmsUser record);

    CmsUser selectByUsercodeAndPsw(Map<String,String> user);

}