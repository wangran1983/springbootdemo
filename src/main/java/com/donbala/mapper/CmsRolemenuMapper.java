package com.donbala.mapper;

import com.donbala.model.CmsRolemenu;
import com.donbala.model.CmsRolemenuKey;

public interface CmsRolemenuMapper {
    int deleteByPrimaryKey(CmsRolemenuKey key);

    int insert(CmsRolemenu record);

    int insertSelective(CmsRolemenu record);

    CmsRolemenu selectByPrimaryKey(CmsRolemenuKey key);

    int updateByPrimaryKeySelective(CmsRolemenu record);

    int updateByPrimaryKey(CmsRolemenu record);
}