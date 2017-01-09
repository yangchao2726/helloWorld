/**
 * @Description:测试Bean业务层接口
 * @author:YC
 * @time:2016年12月28日 下午1:53:01
 */
package com.bfec.dsdemo.service;

import java.util.List;

import com.bfec.dsdemo.model.TestBean;

/**
 * @Description:测试Bean业务层接口
 * @author:YC
 * @time:2016年12月28日 下午1:53:01
 */
public interface TestBeanService {

	int deleteByPrimaryKey(Integer id);

    int insert(TestBean record);

    int insertSelective(TestBean record);

    TestBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestBean record);

    int updateByPrimaryKey(TestBean record);
    
    List<TestBean> reportAll();
}
