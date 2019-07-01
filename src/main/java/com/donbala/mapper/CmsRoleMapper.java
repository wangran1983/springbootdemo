package com.donbala.mapper;

import com.donbala.model.CmsRole;

public interface CmsRoleMapper {
    int deleteByPrimaryKey(String roleid);

    int insert(CmsRole record);

    int insertSelective(CmsRole record);

    CmsRole selectByPrimaryKey(String roleid);

    int updateByPrimaryKeySelective(CmsRole record);

    int updateByPrimaryKey(CmsRole record);
}