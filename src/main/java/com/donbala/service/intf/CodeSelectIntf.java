package com.donbala.service.intf;

import com.donbala.model.CodeAndName;

import java.util.List;

/**
 * @CLassName: CodeSelectIntf
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/12-14:14
 * @Description: todo
 **/
public interface CodeSelectIntf {
    List<CodeAndName> codeSelect(String codetype);
}
