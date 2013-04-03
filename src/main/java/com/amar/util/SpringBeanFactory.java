package com.amar.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanFactory implements ApplicationContextAware
{
	private static ApplicationContext applicationContext;

	public void setApplicationContext( ApplicationContext a )
	{
		applicationContext = a;
	}

	@SuppressWarnings( "unchecked" )
	public static < T > T getBean( String beanName )
	{
		return ( T ) applicationContext.getBean( beanName );
	}
}
