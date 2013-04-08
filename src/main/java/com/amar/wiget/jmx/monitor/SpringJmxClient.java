package com.amar.wiget.jmx.monitor;

import java.util.*;

import javax.management.*;
import javax.management.remote.*;

public class SpringJmxClient implements NotificationListener
{
	public void handleNotification( Notification notification , Object handback )
	{
		System.out.println( "Notification: " + notification.getMessage() );
	}

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public static void main( String [] args ) throws Exception
	{
		SpringJmxClient listener = new SpringJmxClient();

		JMXServiceURL serviceURL = new JMXServiceURL( "service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmxrmi" );
		Map environment = null;
		JMXConnector connector = JMXConnectorFactory.connect( serviceURL , environment );
		MBeanServerConnection mBeanConnection = connector.getMBeanServerConnection();

		ObjectName exampleServiceName = ObjectName.getInstance( "annojmx:myjao=AnnotationObject" );
		mBeanConnection.addNotificationListener( exampleServiceName , listener , null , null );

		Object lock = new Object();
		synchronized ( lock )
		{
			lock.wait();
		}
		// mBeanConnection.invoke( exampleServiceName , "startService" , null , null );
		// mBeanConnection.setAttribute( exampleServiceName , new Attribute( "propertyValue" , "new value" ) );
		// System.out.println( mBeanConnection.getAttribute( exampleServiceName , "propertyValue" ) );
		// mBeanConnection.invoke( exampleServiceName , "stopService" , null , null );
	}
}
