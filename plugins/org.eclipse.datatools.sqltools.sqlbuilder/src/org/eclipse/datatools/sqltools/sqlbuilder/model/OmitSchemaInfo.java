/*******************************************************************************
 * Copyright � 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.util.Observable;

import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.preferences.SQLBuilderPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This class defines the set of properties which determine whether the
 * current schema name is omitted from SQL generated by the SQL Query Builder.
 */
public class OmitSchemaInfo extends Observable implements IOmitSchemaInfo{

	protected boolean _omitCurrentSchema = false;
	protected boolean _useAUIDAsCurrentSchema = true;
	protected String _currentSchema = "";
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#getOmitCurrentSchema()}
	 */
	public boolean getOmitCurrentSchema() {
		return _omitCurrentSchema;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#setOmitCurrentSchema(boolean)}
	 */
    public void setOmitCurrentSchema(boolean omitCurrentSchema)
    {
    	if (omitCurrentSchema != _omitCurrentSchema){
        	_omitCurrentSchema = omitCurrentSchema;
        	setChanged();
    	}
    }
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#getUseAUIDAsCurrentSchema()}
	 */
	public boolean getUseAUIDAsCurrentSchema() {
		return _useAUIDAsCurrentSchema;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#setUseAUIDAsCurrentSchema(boolean)}
	 */
    public void setUseAUIDAsCurrentSchema(boolean useAUIDAsCurrentSchema)
    {
    	if (useAUIDAsCurrentSchema != _useAUIDAsCurrentSchema){
    		_useAUIDAsCurrentSchema = useAUIDAsCurrentSchema;
    		setChanged();
    	}
    }
	
	/**
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#getCurrentSchema()
	 */
	public String getCurrentSchema() {
		return _currentSchema;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#setCurrentSchema(String)}
	 */
    public void setCurrentSchema(String currentSchema)
    {
    	if (!_currentSchema.equals(currentSchema)){
    		_currentSchema = (currentSchema == null ? "" : currentSchema);
    		setChanged();
    	}
    }
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#encode()}
	 * @see decode()
	 */
	public String encode() {
		StringBuffer code = new StringBuffer("");
		code.append(Boolean.toString(_omitCurrentSchema)).append(":");
		code.append(Boolean.toString(_useAUIDAsCurrentSchema)).append(":");
		code.append(_currentSchema == null? "":_currentSchema);
		return code.toString();
	}

	/**
	 * Decodes a <code>OmitSchemaInfo</code> from an encoded String.
	 * @see encode()
	 * @param code encoded <code>OmitSchemaInfo</code> object.
	 * @return <code>OmitSchemaInfo</code> object
	 */
	public static OmitSchemaInfo decode(String code)
	{
		OmitSchemaInfo omitSchemaInfo = new OmitSchemaInfo();
		
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{code});
        }

        if (code == null || !code.matches(".*:.*:.*"))
		{
			SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Cannot decode <" + code + ">");
			omitSchemaInfo.initFromPreferences();
		}
        else {
        	
        }
		int i = 0;
		int j = code.indexOf(':');
		String sOmitCurrentSchema = code.substring(i, j);
		omitSchemaInfo._omitCurrentSchema = 
			Boolean.valueOf(sOmitCurrentSchema).booleanValue();
		
		i = j + 1;
		j = code.indexOf(':', i);
		String sUseAUIDAsCurrentSchema = code.substring(i, j);
		omitSchemaInfo._useAUIDAsCurrentSchema = 
			Boolean.valueOf(sUseAUIDAsCurrentSchema).booleanValue();
		
		i = j + 1;
		omitSchemaInfo._currentSchema = code.substring(i);

	    return (OmitSchemaInfo) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(omitSchemaInfo);
	}
	
	/**
	 * Function to be called when this <code>OmitSchemaInfo</code> has changed.
	 * Overrides {@link java.util.Observable#setChanged}
	 * 
	 */
	public void setChanged(){
		super.setChanged();
    	this.notifyObservers(null);
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#equals(IOmitSchemaInfo)}
	 */
	public boolean equals(IOmitSchemaInfo iOmitSchemaInfo){
		boolean equals = true;
    	if (iOmitSchemaInfo.getOmitCurrentSchema() != _omitCurrentSchema){
    		equals = false;
    	}
    	else if (iOmitSchemaInfo.getUseAUIDAsCurrentSchema() != _useAUIDAsCurrentSchema){
    		equals = false;
    	}
    	else if (_currentSchema == null){
    		if (iOmitSchemaInfo.getCurrentSchema() != null && iOmitSchemaInfo.getCurrentSchema().length() > 0){
    			equals = false;
    		}
    	}
    	else if (_currentSchema != null && !_currentSchema.equals(iOmitSchemaInfo.getCurrentSchema())){
    		equals = false;
    	}
    	
    	return equals;
	}
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#copy(IOmitSchemaInfo)}
	 */
	public void copy(IOmitSchemaInfo iOmitSchemaInfo){
    	
    	// Reset values of this
    	_omitCurrentSchema = iOmitSchemaInfo.getOmitCurrentSchema();
    	_useAUIDAsCurrentSchema = iOmitSchemaInfo.getUseAUIDAsCurrentSchema();
    	if (iOmitSchemaInfo.getCurrentSchema() == null){
    		_currentSchema = "";
    	}
    	else {
    		_currentSchema = iOmitSchemaInfo.getCurrentSchema();
    	}
    	
	}
	
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo#initFromPreferences()}
	 */
	public void initFromPreferences(){
		IPreferenceStore store = getPreferenceStore();
		_omitCurrentSchema = store.getBoolean(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_IN_SQL);
		_useAUIDAsCurrentSchema = store.getBoolean(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_USE_AUID);
		_currentSchema = store.getString(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_CURRENT_SCHEMA);
	}
	
    /*
     * Returns preference store that belongs to the our plugin.
     */
    protected IPreferenceStore getPreferenceStore()
    {
        return SQLBuilderPlugin.getPlugin().getPreferenceStore();
    }
	
}
