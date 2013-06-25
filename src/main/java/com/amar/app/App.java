package com.amar.app;

import java.util.List;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amar.common.AppInterface;
import com.amar.model.Project;
import com.amar.util.SpringBeanFactory;
import com.amar.dao.*;

public class App implements BundleActivator
{
	private final static Logger log = Logger.getLogger( App.class );

	public static ClassPathXmlApplicationContext cpac;

	private static BundleContext context;

	static BundleContext getContext()
	{
		return context;
	}

	public void start( BundleContext bundleContext ) throws Exception
	{
		App.context = bundleContext;
		App app = new App();
		app.loadXML();
		doTest( bundleContext );
	}

	public void stop( BundleContext bundleContext ) throws Exception
	{
		cpac.destroy();
		App.context = null;
	}

	public void doTest( BundleContext ctx ) throws Exception
	{
		ServiceReference ref = ctx.getServiceReference( ProjectDAO.class.getName() );

		List<Project> list = ( ( ProjectDAO ) ctx.getService( ref ) ).findProject( new Project() );

		log.info( "list.size=" + list.size() );
	}

	public static void main( String [] args )
	{
		App app = new App();
		app.loadXML();
		AppInterface main = SpringBeanFactory.getBean( "rmiDemo1Proxy" );
		main.start();
	}

	public void loadXML()
	{
		cpac = new ClassPathXmlApplicationContext( "config/amar-*.xml" );
	}

}
