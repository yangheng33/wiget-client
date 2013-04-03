package com.amar.common;

import org.apache.log4j.Logger;

public abstract class BaseApp implements AppInterface
{
	protected final Logger log = Logger.getLogger( this.getClass() );

	private long id;

	private String name;

	@Override
	public String show()
	{
		return this.toString();
	}

	@Override
	public long getId()
	{
		return id;
	}

	@Override
	public void setId( long id )
	{
		this.id = id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName( String name )
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append( "[id=" ).append( id ).append( ", name=" ).append( name ).append( "]" );
		return builder.toString();
	}

}
