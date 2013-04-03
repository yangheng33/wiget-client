/* 
 * @(#)Util.java v1.0 2011-7-19
 * 
 * Copyright 2011 Beijing SUN GROUND interactive entertainment Co.,Ltd. All rights reserved.
 */
package com.amar.util;


/**
 * <p>
 * 描述
 * </p>
 * 
 * @author Administrator
 * @version 1.0 2011-7-19 Administrator
 */
public class Base64 {
 
 
	/** 
     * 编码 
     * @param bstr 
     * @return String 
     */  
	public static String encode(byte[] bstr) {
		return  org.apache.commons.codec.binary.Base64.encodeBase64String(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str) {		 
		return org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes());
	}
	public static byte[] decode(String str, String charset) throws Exception{		 
		return org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes(charset));
	}	
	public static String encodeUrl(byte[] bstr) {
		return  org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(bstr);
	}
 
	public static byte[] decodeUrl(String str) {		 
		str = str.replaceAll("-", "+");
		str = str.replaceAll("_", "/");
		return org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes());
	}
	 
}
