package com.amar.wiget.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MessageTopicReceiver implements MessageListener
{

	@Override
	public void onMessage( Message message )
	{
		if ( message instanceof TextMessage )
		{
			try
			{
				System.out.println( "Receive msg:" + ( ( TextMessage ) message ).getText() );
			}
			catch ( JMSException e )
			{
				throw new RuntimeException( e );
			}
		}
		else
		{
			//throw new IllegalArgumentException( "Message must be of type TextMessage" );
		}
	}
}
