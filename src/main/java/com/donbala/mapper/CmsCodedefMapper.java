package com.donbala.mapper;

import com.donbala.model.CmsCodedef;
import com.donbala.model.CmsCodedefKey;

public interface CmsCodedefMapper {
    int deleteByPrimaryKey(CmsCodedefKey key);

    int insert(CmsCodedef record);

    int insertSelective(CmsCodedef record);

    CmsCodedef selectByPrimaryKey(CmsCodedefKey key);

    int updateByPrimaryKeySelective(CmsCodedef record);

    int updateByPrimaryKey(CmsCodedef record);
}