/**
 * @Description:TODO
 * @author:YC
 * @time:2016年12月28日 下午1:55:41
 */
package com.bfec.dsdemo.service.impl.no_trans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfec.dsdemo.dao.TestBeanMapper;
import com.bfec.dsdemo.dynamicds.annotation.DataSource;
import com.bfec.dsdemo.dynamicds.support.DynamicDataSourceHolder;
import com.bfec.dsdemo.model.TestBean;
import com.bfec.dsdemo.service.TestBeanService;

/**
 * @Description:待完善处：修改操作与读操作分离后，本类无修改数据方法，不支持事务
 * @author:YC
 * @time:2016年12月28日 下午1:55:41
 */
@Service("testBeanService")
@DataSource(value = "dataSource3")
public class TestBeanServiceImpl implements TestBeanService {
	
	@Autowired
	private TestBeanMapper testBeanMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return testBeanMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TestBean record) {
		return testBeanMapper.insert(record);
	}

	@Override
	public int insertSelective(TestBean record) {
		return testBeanMapper.insertSelective(record);
	}

	@Override
	public TestBean selectByPrimaryKey(Integer id) {
		return testBeanMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TestBean record) {
		return testBeanMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TestBean record) {
		return testBeanMapper.updateByPrimaryKey(record);
	}

	/* ******方法在事务监控中，不能切换数据源
	 * @see com.bfec.dsdemo.service.TestBeanService#reportAll()
	 */
	@Override
	public List<TestBean> reportAll() {
		List<TestBean> list = new ArrayList<TestBean>();
		DynamicDataSourceHolder.clearDataSource();
		DynamicDataSourceHolder.setDataSource("dataSource1");
		list.addAll(testBeanMapper.queryAll());
		DynamicDataSourceHolder.clearDataSource();
		DynamicDataSourceHolder.setDataSource("dataSource3");
		list.addAll(testBeanMapper.queryAll());
		DynamicDataSourceHolder.clearDataSource();
		DynamicDataSourceHolder.setDataSource("dataSource2");
		list.addAll(testBeanMapper.queryAll());
		return list;
	}

}
