package com.amar.wiget.rmi.demo1;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.amar.common.AppInterface;

@Service( "rmiDemo1Proxy" )
public class Demo1Proxy implements AppInterface
{
	private static Log log = LogFactory.getLog(Demo1Proxy.class);
	
	@Resource( name = "rmiDemo1_client" )
	private HelloRmiService helloRmiService;
	
	@Override
	public void start()
	{
		log.info( helloRmiService.getAdd( 1 , 3 ) );
	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public long getId()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId( long id )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName( String name )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String show()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
