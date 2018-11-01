
/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
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
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.dateTime"),Integer.valueOf( DataTypes.TIMESTAMP ) );     //$NON-NLS-1$
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.decimal"),Integer.valueOf( DataTypes.BIGDECIMAL ) );     //$NON-NLS-1$
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.float"),Integer.valueOf( DataTypes.DOUBLE ) );           //$NON-NLS-1$
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.integer"), Integer.valueOf( DataTypes.INT ));            //$NON-NLS-1$
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.date"), Integer.valueOf( DataTypes.DATE )  );            //$NON-NLS-1$
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.time"), Integer.valueOf( DataTypes.TIME ) );             //$NON-NLS-1$
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.string"), Integer.valueOf( DataTypes.STRING ) );         //$NON-NLS-1$
    	displayNameDataTypeMapping.put( Messages.getString("datatypes.boolean"), Integer.valueOf( DataTypes.BOOLEAN ) );       //$NON-NLS-1$
    	
    	dataTypeDisplayNameMapping.put( Integer.valueOf( DataTypes.TIMESTAMP ), Messages.getString("datatypes.dateTime") );    //$NON-NLS-1$
    	dataTypeDisplayNameMapping.put( Integer.valueOf( DataTypes.BIGDECIMAL ), Messages.getString("datatypes.decimal"));     //$NON-NLS-1$
    	dataTypeDisplayNameMapping.put( Integer.valueOf( DataTypes.DOUBLE ), Messages.getString("datatypes.float"));           //$NON-NLS-1$
    	dataTypeDisplayNameMapping.put( Integer.valueOf( DataTypes.INT ),Messages.getString("datatypes.integer"));             //$NON-NLS-1$
    	dataTypeDisplayNameMapping.put( Integer.valueOf( DataTypes.DATE ),Messages.getString("datatypes.date"));               //$NON-NLS-1$
    	dataTypeDisplayNameMapping.put( Integer.valueOf( DataTypes.TIME ), Messages.getString("datatypes.time"));              //$NON-NLS-1$
    	dataTypeDisplayNameMapping.put( Integer.valueOf( DataTypes.STRING ), Messages.getString("datatypes.string"));          //$NON-NLS-1$
    	dataTypeDisplayNameMapping.put( Integer.valueOf( DataTypes.BOOLEAN ), Messages.getString("datatypes.boolean"));        //$NON-NLS-1$
    }
    
    /**
     * Get data type's display name.
     * 
     * @param type
     * @return
     */
    public static String getDataTypeDisplayName( int type )
    {
    	Object o = dataTypeDisplayNameMapping.get( Integer.valueOf(type) );
    	if( o != null )
    		return o.toString( );
    	else
    		return Messages.getString("datatypes.string");   //$NON-NLS-1$
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
    		return Integer.valueOf(DataTypes.STRING);
    }
    
}
