/**
 * @Description:����Beanҵ���ӿ�
 * @author:YC
 * @time:2016��12��28�� ����1:53:01
 */
package com.bfec.dsdemo.service;

import java.util.List;

import com.bfec.dsdemo.model.TestBean;

/**
 * @Description:����Beanҵ���ӿ�
 * @author:YC
 * @time:2016��12��28�� ����1:53:01
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
