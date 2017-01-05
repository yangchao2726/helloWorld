package com.bfec.dsdemo.dao;

import java.util.List;

import com.bfec.dsdemo.model.TestBean;


public interface TestBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestBean record);

    int insertSelective(TestBean record);

    TestBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestBean record);

    int updateByPrimaryKey(TestBean record);
    
    List<TestBean> queryAll();
}