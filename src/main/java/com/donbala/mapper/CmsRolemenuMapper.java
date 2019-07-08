package com.donbala.mapper;

import com.donbala.model.CmsRolemenu;
import com.donbala.model.CmsRolemenuKey;

import java.util.List;

public interface CmsRolemenuMapper {
    int deleteByPrimaryKey(CmsRolemenuKey key);

    int insert(CmsRolemenu record);

    int insertSelective(CmsRolemenu record);

    int deleteByRoleid(String roleid);

    CmsRolemenu selectByPrimaryKey(CmsRolemenuKey key);

    List<CmsRolemenu> selectByRoleid(String roleid);

    int updateByPrimaryKeySelective(CmsRolemenu record);

    int updateByPrimaryKey(CmsRolemenu record);
}