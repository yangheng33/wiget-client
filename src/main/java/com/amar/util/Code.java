package com.amar.util;

import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @description
 * @date 2012-2-14 下午03:09:04
 * @author Amar.Yang
 * @mail yanghs@sun-ground.com
 */
@SuppressWarnings( "restriction" )
public class Code
{
	private final static String [] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString( byte [] b )
	{
		StringBuffer resultSb = new StringBuffer();
		for( int i = 0 ; i < b.length ; i ++ )
		{
			resultSb.append( byteToHexString( b[ i ] ) );
		}
		return resultSb.toString();
	}

	private static String byteToHexString( byte b )
	{
		int n = b;
		if ( n < 0 ) n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[ d1 ] + hexDigits[ d2 ];
	}

	/**
	 * @param s
	 * @return
	 * @return String
	 * @description 将 s 进行 BASE64 编码
	 */
	@Deprecated
	public static String getBASE64( String s )
	{
		if ( s == null ) return null;
		return ( new BASE64Encoder() ).encode( s.getBytes() );
	}

	/**
	 * @param s
	 * @return
	 * @return String
	 * @description 将 BASE64 编码的字符串 s 进行解码
	 */
	@Deprecated
	public static String getFromBASE64( String s )
	{
		if ( s == null ) return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try
		{
			byte [] b = decoder.decodeBuffer( s );
			return new String( b );
		}
		catch ( Exception e )
		{
			return null;
		}
	}

	/**
	 * 使用传入的编码方式加密
	 * 
	 * @param origin
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static String encode( String origin , String encode ) throws Exception
	{
		String resultString = null;
		resultString = new String( origin );
		MessageDigest md = MessageDigest.getInstance( encode );
		resultString = byteArrayToHexString( md.digest( resultString.getBytes() ) );
		return resultString;
	}

	/**
	 * MD5加密
	 * 
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String md5Encode( String origin ) throws Exception
	{
		return encode( origin , "MD5" );
	}

	/**
	 * 使用默认的方式（MD5）加密
	 * 
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String encode( String origin ) throws Exception
	{
		return md5Encode( origin );
	}

	/**
	 * Sha加密
	 * 
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String shaEncode( String origin ) throws Exception
	{
		return encode( origin , "SHA" );
	}

	/**
	 * Sha-1加密
	 * 
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String sha1Encode( String origin ) throws Exception
	{
		return encode( origin , "SHA-1" );
	}

	/**
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String urlEncode( String origin ) throws Exception
	{
		return java.net.URLEncoder.encode( origin , "UTF-8" );
	}

	/**
	 * Sha256加密
	 * 
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String sha256Encode( String origin ) throws Exception
	{
		return encode( origin , "SHA-256" );
	}

	/**
	 * Sha512加密
	 * 
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public static String sha512Encode( String origin ) throws Exception
	{
		return encode( origin , "SHA-512" );
	}
}
