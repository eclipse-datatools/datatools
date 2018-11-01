/*******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * The object of column Mapping, it contains the properties of ColumnName,
 * XPath, and Column Type, this object is added into column mapping table.
 */

public class ColumnMappingElement
{
	//column name
	String columnName;
	//xpath expression
	String xpath;
	//column type
	String type;

	String getColumnName( )
	{
		return columnName;
	}

	void setColumnName( String columnName )
	{
		this.columnName = columnName;
	}

	String getXPath( )
	{
		return xpath;
	}

	void setXPath( String xpath )
	{
		this.xpath = xpath;
	}

	String getType( )
	{
		return type;
	}

	void setType( String type )
	{
		this.type = type;
	}
	
	String getTypeStandardString( )
	{
		Object type = DataTypeUtil.getDataType( this.type );
		if ( type != null )
		try
		{
			return DataTypes.getTypeString( Integer.valueOf( type.toString( ) )
					.intValue( ) );
		}
		catch ( NumberFormatException e )
		{
			return null;
		}
		catch ( OdaException e )
		{
			return null;
		}
		else
			return null;
	}
}
