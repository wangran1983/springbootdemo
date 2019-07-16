package com.donbala.mapper;

import com.donbala.model.CmsUserrole;
import com.donbala.model.CmsUserroleKey;

import java.util.List;

public interface CmsUserroleMapper {
    int deleteByPrimaryKey(CmsUserroleKey key);

    int deleteByUsercode(String usercode);

    int insert(CmsUserrole record);

    int insertSelective(CmsUserrole record);

    CmsUserrole selectByPrimaryKey(CmsUserroleKey key);

    int updateByPrimaryKeySelective(CmsUserrole record);

    int updateByPrimaryKey(CmsUserrole record);

    List<CmsUserrole> queryRoleByUsercode(String usercode);
}