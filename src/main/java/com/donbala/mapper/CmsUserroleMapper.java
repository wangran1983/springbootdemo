package com.donbala.mapper;

import com.donbala.model.CmsUserrole;

import java.util.List;

public interface CmsUserroleMapper {

    int deleteByUsercode(String usercode);

    int insert(CmsUserrole record);

    int insertSelective(CmsUserrole record);


    int updateByPrimaryKeySelective(CmsUserrole record);

    int updateByPrimaryKey(CmsUserrole record);

    int insertList(List<CmsUserrole> cmsUserroles);

    List<CmsUserrole> queryRoleByUsercode(String usercode);
}