package com.taxi.admin.common;

import org.apache.commons.codec.binary.Base64;

/**
 * 解码 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2011-9-29 上午10:36:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: zhangrui@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class DecodeUtil {

	public static String decode64(String encode) {
		byte[] byteOfEncode = encode.getBytes();
		byte[] byteOfDecode = Base64.decodeBase64(byteOfEncode);// 调用decodeBase64方法
		String decode = new String(byteOfDecode);
		return unescape(decode);// 调用unescape（String src）方法
	}

	private static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

}
