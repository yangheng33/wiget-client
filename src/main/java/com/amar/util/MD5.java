package com.amar.util;

import java.security.MessageDigest;

public class MD5 {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
		{
		n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static void main( String [] args )
	{
		String s1 = "123456";
		String s2 = "e10adc3949ba59abbe56e057f20f883e";
		try
		{
			System.out.println( md5Encode(s1) );
			System.out.println( s2.length() );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用传入的编码方式加密
	 * @param origin
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static String encode(String origin, String encode) throws Exception {
		String resultString = null;
		resultString = new String(origin);
		MessageDigest md = MessageDigest.getInstance(encode);
		resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		return resultString;
	}
	
	/**
	 * MD5加密
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String md5Encode(String origin) throws Exception {
		return encode(origin, "MD5");
	}
	
	/**
	 * 使用默认的方式（MD5）加密
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String encode(String origin) throws Exception {
		return md5Encode(origin);
	}
	
	/**
	 * Sha加密
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String shaEncode(String origin) throws Exception {
		return encode(origin, "SHA");
	}
	
	/**
	 * Sha256加密
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String sha256Encode(String origin) throws Exception {
		return encode(origin, "SHA-256");
	}
	
	/**
	 * Sha512加密
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String sha512Encode(String origin) throws Exception {
		return encode(origin, "SHA-512");
	}
}
