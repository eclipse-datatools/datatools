
/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.HashMap;

import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;

/**
 *	This class is used to make transformation between data type display name and 
 *  data type in org.eclipse.datatools.enablement.oda.xml.impl.DataTypes.
 */
final class DataTypeUtil
{
	//
	private static HashMap displayNameDataTypeMapping = new HashMap();
	private static HashMap dataTypeDisplayNameMapping = new HashMap();
	
    static
    {
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.dateTime"),new Integer( DataTypes.TIMESTAMP ) );
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.decimal"),new Integer( DataTypes.BIGDECIMAL ) );
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.float"),new Integer( DataTypes.DOUBLE ) );
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.integer"), new Integer( DataTypes.INT ));
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.date"), new Integer( DataTypes.DATE )  );
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.time"), new Integer( DataTypes.TIME ) );
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.string"), new Integer( DataTypes.STRING ) );
    
    	dataTypeDisplayNameMapping.put( new Integer( DataTypes.TIMESTAMP ), Messages.getString("datatypes.dateTime") );
    	dataTypeDisplayNameMapping.put( new Integer( DataTypes.BIGDECIMAL ), Messages.getString("datatypes.decimal"));
    	dataTypeDisplayNameMapping.put( new Integer( DataTypes.DOUBLE ), Messages.getString("datatypes.float"));
    	dataTypeDisplayNameMapping.put( new Integer( DataTypes.INT ),Messages.getString("datatypes.integer"));
    	dataTypeDisplayNameMapping.put( new Integer( DataTypes.DATE ),Messages.getString("datatypes.date"));
    	dataTypeDisplayNameMapping.put( new Integer( DataTypes.TIME ), Messages.getString("datatypes.time"));
    	dataTypeDisplayNameMapping.put( new Integer( DataTypes.STRING ), Messages.getString("datatypes.string"));
    }
    
    /**
     * Get data type's display name.
     * 
     * @param type
     * @return
     */
    public static String getDataTypeDisplayName( int type )
    {
    	Object o = dataTypeDisplayNameMapping.get( new Integer(type) );
    	if( o != null )
    		return o.toString( );
    	else
    		return Messages.getString("datatypes.string");
    }
    
    /**
     * Get data type from its display name.
     * @param displayName
     * @return
     */
    public static Integer getDataType( String displayName )
    {
    	Object o = displayNameDataTypeMapping.get( displayName );
    	if( o != null )
    		return (Integer)o;
    	else
    		return new Integer(DataTypes.STRING);
    }
    
}
