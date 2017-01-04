/**
 * @Description:动态数据源实现spring 类方法
 * AbstractRoutingDataSource.determineCurrentLookupKey以更新数据源bean
 * @author:YC
 * @time:2017年1月4日 下午5:29:46
 */
package com.bfec.dsdemo.dynamicds.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description:动态数据源实现spring 类方法
 * AbstractRoutingDataSource.determineCurrentLookupKey以更新数据源bean
 * @author:YC
 * @time:2017年1月4日 下午5:29:46
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// 获取数据源标识
		return DynamicDataSourceHolder.getDataSource();
	}
}
