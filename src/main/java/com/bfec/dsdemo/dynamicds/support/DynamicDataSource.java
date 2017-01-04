/**
 * @Description:��̬����Դʵ��spring �෽��
 * AbstractRoutingDataSource.determineCurrentLookupKey�Ը�������Դbean
 * @author:YC
 * @time:2017��1��4�� ����5:29:46
 */
package com.bfec.dsdemo.dynamicds.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description:��̬����Դʵ��spring �෽��
 * AbstractRoutingDataSource.determineCurrentLookupKey�Ը�������Դbean
 * @author:YC
 * @time:2017��1��4�� ����5:29:46
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// ��ȡ����Դ��ʶ
		return DynamicDataSourceHolder.getDataSource();
	}
}
