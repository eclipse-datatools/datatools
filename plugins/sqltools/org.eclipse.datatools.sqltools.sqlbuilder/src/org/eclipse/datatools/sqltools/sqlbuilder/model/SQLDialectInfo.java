/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.model;

import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;

/**
 * This class defines the set of properties defines the set of properties which
 * determine a SQL dialect.
 */
public class SQLDialectInfo implements ISQLDialectInfo{

	protected String _product = null;
	protected String _version = null;
	protected String _omitSchema = null;
	
	/**
	 * Constructor for SQLDialectInfo
	 */
	public SQLDialectInfo(String product, String version, String omitSchema){
		_product = product;
		_version = version;
		_omitSchema = omitSchema;
	}
	
	/**
	 * Constructor for SQLDialectInfo
	 */
	public SQLDialectInfo(){
		this(null, null, null);
	}
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo#getProduct()}
	 */
	public String getProduct() {
		return _product;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo#setProduct(String)}
	 */
    public void setProduct(String product)
    {
    	_product = product;
    }
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo#getVersion()}
	 */
	public String getVersion() {
		return _version;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo#setVersion(String)}
	 */
    public void setVersion(String version)
    {
    	_version = version;
    }
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo#getOmitSchema()}
	 */
	public String getOmitSchema() {
		return _omitSchema;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo#setOmitSchema(String)}
	 */
    public void setOmitSchema(String omitSchema)
    {
    	_omitSchema = omitSchema;
    }
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo#encode()}
	 * @see decode()
	 */
	public String encode() {
		StringBuffer code = new StringBuffer("");
		if (_product != null){
			code.append(_product);
		}
		code.append(":");
		if (_version != null){
			code.append(_version);
		}
		
		code.append(":");
		if (_omitSchema != null){
			code.append(_omitSchema);
		}
		
		return code.toString();
	}

	/**
	 * Decodes a <code>SQLDialectInfo</code> from an encoded String.
	 * @see encode()
	 * @param code encoded <code>SQLDialectInfo</code> object.
	 * @return <code>SQLDialectInfo</code> object
	 */
	public static SQLDialectInfo decode(String code)
	{
		SQLDialectInfo sqlDialectInfo = new SQLDialectInfo();
		

        if (code == null || !code.matches(".*:.*:.*"))
		{
			SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Cannot decode SQLDialectInfo <" + code + ">");
		}
        else {
    		int i = 0;
    		int j = code.indexOf(':');
    		if (j > i){
    			String sProduct = code.substring(i, j);
    			sqlDialectInfo._product = sProduct;
    		}
    		
 
    		i = j + 1;
    		j = code.indexOf(':', i);
    		if (j > i){
    			String sVersion = code.substring(i, j);
    			sqlDialectInfo._version = sVersion;
    		}
    		
    		i = j + 1;
    		if (i < code.length()){
    			String sOmitSchema = code.substring(i);
    			sqlDialectInfo._omitSchema = sOmitSchema;
    		}
        }
	    return sqlDialectInfo;
	}
}
