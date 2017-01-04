/**
 * @Description:��̬����Դ��ʶ����
 * @author:YC
 * @time:2017��1��4�� ����5:32:06
 */
package com.bfec.dsdemo.dynamicds.support;

/**
 * @Description:��̬����Դ��ʶ����
 * @author:YC
 * @time:2017��1��4�� ����5:32:06
 */
public class DynamicDataSourceHolder {

	/**
	 * @Description: THREAD_DATA_SOURCE : ����Դ��ʶ�������̱߳����У�������̲߳�������Դʱ�������
	 */
	private static ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<String>();

	public DynamicDataSourceHolder() {
		
	}

	/**
	 * @Description:ȡ�õ�ǰ����Դ
	 * @return return:String
	 * @exception:
	 * @author: YC
	 * @time:2017��1��4�� ����5:37:20
	 */
	public static String getDataSource() {
		return THREAD_DATA_SOURCE.get();
	}

	/**
	 * @Description:��������Դ
	 * @param dataSource
	 *            return:void
	 * @exception:
	 * @author: YC
	 * @time:2017��1��4�� ����5:37:40
	 */
	public static void setDataSource(String dataSource) {
		THREAD_DATA_SOURCE.set(dataSource);
	}

	/**
	 * @Description:�������Դ return:void
	 * @exception:
	 * @author: YC
	 * @time:2017��1��4�� ����5:37:52
	 */
	public static void clearDataSource() {
		THREAD_DATA_SOURCE.remove();
	}
}
