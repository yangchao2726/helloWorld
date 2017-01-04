/**
 * @Description:动态数据源标识更新
 * @author:YC
 * @time:2017年1月4日 下午5:32:06
 */
package com.bfec.dsdemo.dynamicds.support;

/**
 * @Description:动态数据源标识更新
 * @author:YC
 * @time:2017年1月4日 下午5:32:06
 */
public class DynamicDataSourceHolder {

	/**
	 * @Description: THREAD_DATA_SOURCE : 数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
	 */
	private static ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<String>();

	public DynamicDataSourceHolder() {
		
	}

	/**
	 * @Description:取得当前数据源
	 * @return return:String
	 * @exception:
	 * @author: YC
	 * @time:2017年1月4日 下午5:37:20
	 */
	public static String getDataSource() {
		return THREAD_DATA_SOURCE.get();
	}

	/**
	 * @Description:设置数据源
	 * @param dataSource
	 *            return:void
	 * @exception:
	 * @author: YC
	 * @time:2017年1月4日 下午5:37:40
	 */
	public static void setDataSource(String dataSource) {
		THREAD_DATA_SOURCE.set(dataSource);
	}

	/**
	 * @Description:清楚数据源 return:void
	 * @exception:
	 * @author: YC
	 * @time:2017年1月4日 下午5:37:52
	 */
	public static void clearDataSource() {
		THREAD_DATA_SOURCE.remove();
	}
}
