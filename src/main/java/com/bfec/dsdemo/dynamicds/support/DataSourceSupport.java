/**
 * @Description:AOP�����ע��com.bfec.dsdemo.dynamicds.annotation.DataSource
 * ������Դ���Ƹ�������ԴDynamicDataSourceHolder.setDataSource(..)
 * @author:YC
 * @time:2017��1��4�� ����5:22:36
 */
package com.bfec.dsdemo.dynamicds.support;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.bfec.dsdemo.dynamicds.annotation.DataSource;

/**
 * @Description:@Description:AOP�����ע��com.bfec.dsdemo.dynamicds.annotation.DataSource 
 * ������Դ���Ƹ�������ԴDynamicDataSourceHolder.setDataSource(..)
 * @author:YC
 * @time:2017��1��4�� ����5:22:36
 */
public class DataSourceSupport {

	/**
	 * @Description:����Ŀ�귽������ȡ��@DataSourceָ��������Դ��ʶ�����õ��̴߳洢���Ա��л�����Դ
	 * @param point
	 * @throws Exception
	 *             return:void
	 * @exception:
	 * @author: YC
	 * @time:2017��1��4�� ����5:24:33
	 */
	public void interceptMethod(JoinPoint point) throws Exception {
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		// Ĭ��ʹ��Ŀ�����͵�ע�⣬���û����ʹ����ʵ�ֽӿڵ�ע����
		for (Class<?> cls : target.getInterfaces()) {
			resetDataSource(cls, signature.getMethod());
		}
		resetDataSource(target, signature.getMethod());
	}

	/**
	 * @Description:��ȡĿ����󷽷�ע�����ע���е�����Դ��ʶ
	 * @param cls
	 * @param method
	 *            return:void
	 * @exception:
	 * @author: YC
	 * @time:2017��1��4�� ����5:27:37
	 */
	private void resetDataSource(Class<?> cls, Method method) {
		try {
			Class<?>[] types = method.getParameterTypes();
			// Ĭ��ʹ����ע��
			if (cls.isAnnotationPresent(DataSource.class)) {
				DataSource source = cls.getAnnotation(DataSource.class);
				DynamicDataSourceHolder.setDataSource(source.value());
				System.out.println("class source.value()=" + source.value());
			}
			// ����ע����Ը�����ע��
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
