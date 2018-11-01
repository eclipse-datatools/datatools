/**************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 **************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.input;

import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;

/**
 * This class defines the usage options of ISQLBuilderEditorInput.
 * An option may be specified to ignore a specific input info even if exists.
 */

public class SQLBuilderEditorInputUsageOptions implements ISQLBuilderEditorInputUsageOptions
{
	protected final static String KEY_USE_WINDOW_STATE = "useWindowState"; //$NON-NLS-1$
	
	/*
	 * Default value for _useWindowState is true
	 */
	private boolean _useWindowState = true;
	
	
	/**
	 * No parameter constructor for SQLBuilderEditorInputUsageOptions
	 */
	public SQLBuilderEditorInputUsageOptions(){
	}
	
	/**
	 * Constructor for SQLBuilderEditorInputUsageOptions with parameter for useWindowState
	 */
	public SQLBuilderEditorInputUsageOptions(boolean useWindowState){
		_useWindowState = useWindowState;
	}
	
    /*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.input.ISQLBuilderEditorInputUsageOptions#useWindowState()
	 */
	public boolean useWindowState() {
		return _useWindowState;
	}
    
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.input.ISQLBuilderEditorInputUsageOptions#setUseWindowState(boolean)
	 */
    public void setUseWindowState( boolean windowState ) {
        _useWindowState = windowState;
    }
    
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.input.ISQLBuilderEditorInputUsageOptions#encode()}
	 * @see decode()
	 */
	public String encode() {
		StringBuffer code = new StringBuffer(""); //$NON-NLS-1$
		code.append(KEY_USE_WINDOW_STATE).append("="); //$NON-NLS-1$
		code.append(Boolean.toString(_useWindowState));
		return code.toString();
	}

	/**
	 * Decodes a <code>SQLBuilderEditorInputUsageOptions</code> from an encoded String.
	 * @see encode()
	 * @param code encoded <code>SQLBuilderEditorInputUsageOptions</code> object.
	 * @return <code>SQLBuilderEditorInputUsageOptions</code> object
	 */
	public static SQLBuilderEditorInputUsageOptions decode(String code)
	{
		SQLBuilderEditorInputUsageOptions inputUsageOptions = new SQLBuilderEditorInputUsageOptions();
		
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{code});
        }

        if (code == null || !code.matches(".*=.*"))
		{
			SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Cannot decode SQLBuilderEditorInputUsageOptions <" + code + ">");
		}
        else {
    		int i = 0;
    		int j = code.indexOf('=');
    		String sKey = code.substring(i, j);
    		if (KEY_USE_WINDOW_STATE.equals(sKey)){
        		i = j + 1;
        		String sUseWindowState = code.substring(i);
        		if (sUseWindowState != null && sUseWindowState.length() > 0){
        			inputUsageOptions._useWindowState = 
        				Boolean.valueOf(sUseWindowState).booleanValue();
        		}
    		}
        }

	    return (SQLBuilderEditorInputUsageOptions) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(inputUsageOptions);
	}
    
}
