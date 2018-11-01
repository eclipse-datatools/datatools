/*******************************************************************************
 * Copyright ©  2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;


/**
 * This interface defines the set of properties which determine a SQL dialect.
 */
public interface ISQLDialectInfo  {

    /**
     * Gets the database product name for this ISQLDialectInfo.
     * 
     * @return  the database product name
     */
    public String getProduct();
      
    /**
     * Sets the database product name for this ISQLDialectInfo.
     * 
     */
    public void setProduct(String product);
      
    /**
     * Gets the database version for this ISQLDialectInfo.
     * 
     * @return  the database version
     */
    public String getVersion();
      
    /**
     * Sets the database version for this ISQLDialectInfo.
     * 
     */
    public void setVersion(String version);
      
    /**
     * Gets the database omitSchema for this ISQLDialectInfo.
     * If the schema name was omitted when the SQL was constructed, this is the schema name 
     * that was omitted. If the schema name was not omitted, it is null.
     * @return  the omitSchema
     */
    public String getOmitSchema();
      
    /**
     * Sets the omitSchema for this ISQLDialectInfo.
     * If the schema name was omitted when the SQL was constructed, this is the schema name 
     * that was omitted. If the schema name was not omitted, it is null.
     */
    public void setOmitSchema(String omitSchema);
      

	/**
	 * Encodes the given <code>ISQLDialectInfo</code> object for persistence.
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDialectInfo#decode(String)
	 * @return encoded String
	 */
	public String encode();

}
