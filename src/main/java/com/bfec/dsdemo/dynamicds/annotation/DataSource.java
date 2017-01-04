/**
 * @Description:数据源名称注解类
 * @author:YC
 * @time:2017年1月4日 下午5:18:46
 */
package com.bfec.dsdemo.dynamicds.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:数据源名称注解类
 * @author:YC
 * @time:2017年1月4日 下午5:18:46
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
	String value();
}
