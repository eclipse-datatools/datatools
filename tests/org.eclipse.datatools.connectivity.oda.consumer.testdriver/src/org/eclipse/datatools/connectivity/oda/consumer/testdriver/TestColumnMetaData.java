/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.testdriver;

import org.eclipse.datatools.connectivity.oda.OdaException;

public class TestColumnMetaData 
{
	private int m_displayLength = 0;
	private String m_label = null;
	private String m_name = null;
	private int m_type = 0;
	private String m_typeName = null;
	private int m_precision = 0;
	private int m_scale = 0;
	private int m_isNullable;
	
	TestColumnMetaData( int displayLength,
			String label,
			String name,
			int type,
			String typeName,
			int precision,
			int scale,
			int isNullable )
	{
		m_displayLength = displayLength;
		m_label = label;
		m_name = name;
		m_type = type;
		m_typeName = typeName;
		m_precision = precision;
		m_scale = scale;
		m_isNullable = isNullable;
	}
	
	int getDisplayLength()
	{
		return m_displayLength;
	}

	String getLabel()
	{
		return m_label;
	}

	String getName()
	{
		return m_name;
	}

	int getType()
	{
		return m_type;
	}

	String getTypeName() 
	{
		return m_typeName;
	}

	int getPrecision() 
	{
		return m_precision;
	}

	public int getScale() 
	{
		return m_scale;
	}

	public int isNullable() throws OdaException 
	{
		return m_isNullable;
	}	
}


