/**
 * @Description:����Դ����ע����
 * @author:YC
 * @time:2017��1��4�� ����5:18:46
 */
package com.bfec.dsdemo.dynamicds.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:����Դ����ע����
 * @author:YC
 * @time:2017��1��4�� ����5:18:46
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
	String value();
}
