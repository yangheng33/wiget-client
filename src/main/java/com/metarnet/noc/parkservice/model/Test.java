package com.metarnet.noc.parkservice.model;

import java.util.Date;


//create table test2(  a int,b numeric(5,1), c char(10),d datetime );
//insert into test2 values( 1,3455.3, '你好啊',getdate());
public class Test
{
	private int a;
	private float b;
	private String c;
	private Date d;
	
	public int getA()
	{
		return a;
	}
	public void setA( int a )
	{
		this.a = a;
	}
	public float getB()
	{
		return b;
	}
	public void setB( float b )
	{
		this.b = b;
	}
	public String getC()
	{
		return c;
	}
	public void setC( String c )
	{
		this.c = c;
	}
	public Date getD()
	{
		return d;
	}
	public void setD( Date d )
	{
		this.d = d;
	}
	
}
