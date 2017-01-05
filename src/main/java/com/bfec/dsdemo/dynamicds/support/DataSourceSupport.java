/**
 * @Description:AOP类根据注解com.bfec.dsdemo.dynamicds.annotation.DataSource
 * 的数据源名称更新数据源DynamicDataSourceHolder.setDataSource(..)
 * @author:YC
 * @time:2017年1月4日 下午5:22:36
 */
package com.bfec.dsdemo.dynamicds.support;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.bfec.dsdemo.dynamicds.annotation.DataSource;

/**
 * @Description:@Description:AOP类根据注解com.bfec.dsdemo.dynamicds.annotation.DataSource 
 * 的数据源名称更新数据源DynamicDataSourceHolder.setDataSource(..)
 * @author:YC
 * @time:2017年1月4日 下午5:22:36
 */
public class DataSourceSupport {

	/**
	 * @Description:拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
	 * @param point
	 * @throws Exception
	 *             return:void
	 * @exception:
	 * @author: YC
	 * @time:2017年1月4日 下午5:24:33
	 */
	public void interceptMethod(JoinPoint point) throws Exception {
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		// 默认使用目标类型的注解，如果没有则使用其实现接口的注解类
		for (Class<?> cls : target.getInterfaces()) {
			resetDataSource(cls, signature.getMethod());
		}
		resetDataSource(target, signature.getMethod());
	}

	/**
	 * @Description:提取目标对象方法注解和类注解中的数据源标识
	 * @param cls
	 * @param method
	 *            return:void
	 * @exception:
	 * @author: YC
	 * @time:2017年1月4日 下午5:27:37
	 */
	private void resetDataSource(Class<?> cls, Method method) {
		try {
			Class<?>[] types = method.getParameterTypes();
			// 默认使用类注解
			if (cls.isAnnotationPresent(DataSource.class)) {
				DataSource source = cls.getAnnotation(DataSource.class);
				DynamicDataSourceHolder.setDataSource(source.value());
				System.out.println("class source.value()=" + source.value());
			}
			// 方法注解可以覆盖类注解
			Method m = cls.getMethod(method.getName(), types);
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
				DataSource source = m.getAnnotation(DataSource.class);
				System.out.println("method source.value()=" + source.value());
				DynamicDataSourceHolder.setDataSource(source.value());
			}
		} catch (Exception e) {
			System.out.println(cls + ":" + e.getMessage());
		}
	}
}
