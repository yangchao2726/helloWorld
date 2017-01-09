/**
 * @Description:TODO
 * @author:YC
 * @time:2017年1月9日 下午5:44:51
 */
package com.bfec.dsdemo.utils;

/**
 * @Description:TODO
 * @author:YC
 * @time:2017年1月9日 下午5:44:51
 */
public class StringUtils {

	/**
	 * @Description: 去掉空白字符
	 * @param str
	 * @return return:String
	 * @exception:
	 * @author: YC
	 * @time:2017年1月9日 下午5:45:15
	 */
	public static String trimAllWhitespace(String str) {
		if (str != null) {
			int len = str.length();
			if (len > 0) {
				char[] dest = new char[len];
				int destPos = 0;
				for (int i = 0; i < len; ++i) {
					char c = str.charAt(i);
					if (!Character.isWhitespace(c)) {
						dest[destPos++] = c;
					}
				}
				return new String(dest, 0, destPos);
			}
		}
		return str;
	}
}
