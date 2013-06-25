package com.amar.wiget.jms.demo1;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.TransportListener;

@SuppressWarnings( "unused" )
public class TestActiveReceiveMQ
{
	public static String url2 = "tcp://192.168.8.185:61616";

	public static String url3 = "failover:(tcp://192.168.8.185:61616,tcp://192.168.8.33:61617)?initialReconnectDelay=1000&timeout=10000";

	public static String url4 = "discovery:(multicast://default)?initialReconnectDelay=1000&maxReconnectAttempts=3&maxReconnectDelay=30000";

	public static String url = "discovery:(multicast://default)?initialReconnectDelay=1000&maxReconnectAttempts=3&reconnectDelay=1000&maxReconnectDelay=30000";

	public static void main( String [] args ) throws JMSException , InterruptedException
	{
		new TestActiveReceiveMQ().topicCilent();
	}

	private AtomicBoolean isReConnecting = new AtomicBoolean( false );

	public void queueCilent()
	{
		try
		{
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( url );
			Connection connection = connectionFactory.createConnection();

			( ( ActiveMQConnection ) connection ).addTransportListener( new TransportListener()
			{
				public void onCommand( Object cmd )
				{
				}

				public void onException( IOException exp )
				{
					System.out.println( "in exp" );
				}

				public void transportInterupted()
				{
					System.out.println( "in transportInterupted" );
					try
					{
						if ( isReConnecting.get() != true )
						{
							isReConnecting.set( true );
							Thread.sleep( 5000 );
							queueCilent();
							isReConnecting.set( false );
						}
						else
						{
							System.out.println( "跳过.." );
						}
					}
					catch ( InterruptedException e )
					{
						e.printStackTrace();
					}
				}

				public void transportResumed()
				{
					System.out.println( "in transportResumed" );
				}
			} );

			final Session session = connection.createSession( true , Session.CLIENT_ACKNOWLEDGE );
			Queue queue = session.createQueue( "jmsQueueTest" );

			MessageConsumer consumer = session.createConsumer( queue );

			consumer.setMessageListener( new MessageListener()
			{
				public void onMessage( Message msg )
				{
					try
					{
						ObjectMessage message = ( ObjectMessage ) msg;
						// MapMessage message = ( MapMessage ) msg;
						System.out.println( "receive message:" + message.getStringProperty( "date" ) + "," + message.getStringProperty( "number" ) );
						session.commit();
					}
					catch ( JMSException e )
					{
						e.printStackTrace();
					}
				}
			} );
			connection.start();
		}
		catch ( JMSException e )
		{
			e.printStackTrace();
			try
			{
				System.out.println( "sleep 30 second" );
				Thread.sleep( 3 );
				System.out.println( "start new one" );
				// queueCilent() ;
			}
			catch ( InterruptedException e1 )
			{
				e1.printStackTrace();
				System.out.println( "再次出现异常" );
			}
		}
		System.out.println( "开始监听。。。" );
	}

	public void topicCilent() throws JMSException , InterruptedException
	{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( url2 );
		Connection connection = connectionFactory.createConnection();

		final Session session = connection.createSession( true , Session.CLIENT_ACKNOWLEDGE );
		Topic topic = session.createTopic( "alarm-topic" );
		MessageConsumer consumer = session.createConsumer( topic );
		consumer.setMessageListener( new MessageListener()
		{
			public void onMessage( Message msg )
			{
				try
				{
					ObjectMessage message = ( ObjectMessage ) msg;
					// MapMessage message = ( MapMessage ) msg;
					System.out.println( "receive message:" + message.getStringProperty( "recid" ) + "," + message.getStringProperty( "type" ) );
					session.commit();
					Thread.sleep( 1000 );
				}
				catch ( JMSException e )
				{
					e.printStackTrace();
				}
				catch ( InterruptedException e )
				{
					e.printStackTrace();
				}
			}
		} );
		connection.start();

	}
}
