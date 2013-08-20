package com.amar.wiget.webservice.demo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.metarnet.noc.parkservice.model.Test;
import com.metarnet.noc.parkservice.services.ITestService;


public class SoapClient2
{

	public static void main( String [] args ) throws ParseException
	{
		JaxWsProxyFactoryBean soapFactoryBean = new JaxWsProxyFactoryBean();
		
		soapFactoryBean.setAddress( "http://127.0.0.1:8080/parkservice/s/testService");
		soapFactoryBean.setServiceClass( ITestService.class );
		Object o = soapFactoryBean.create();
		ITestService testService = ( ITestService ) o;
		
		Test test = new Test();
		List<Test> list= testService.getTest( test );
		System.out.println( list.size() );
		
		//ContextLoaderListener sr;
	}
	
	

}
