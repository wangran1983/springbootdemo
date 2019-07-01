package com.donbala.mapper;

import com.donbala.model.CmsMenu;

import java.util.List;

public interface CmsMenuMapper {
    int deleteByPrimaryKey(String menuid);

    int insert(CmsMenu record);

    int insertSelective(CmsMenu record);

    CmsMenu selectByPrimaryKey(String menuid);

    int updateByPrimaryKeySelective(CmsMenu record);

    int updateByPrimaryKey(CmsMenu record);

    List<CmsMenu> selectByUsercode(String usercode);

}