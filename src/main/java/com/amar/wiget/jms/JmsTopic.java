package com.amar.wiget.jms;

import org.springframework.jms.core.JmsTemplate;

import com.amar.util.SpringBeanFactory;

public class JmsTopic
{

	@SuppressWarnings( "unused" )
	public void test()
	{
		JmsTemplate jmsTemplate = SpringBeanFactory.getBean( "jmsTemplate" );
		
	}
}
