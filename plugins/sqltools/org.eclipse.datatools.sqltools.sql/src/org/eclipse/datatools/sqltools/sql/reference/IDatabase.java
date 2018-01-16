/**
 * Created on March 17, 2005
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.reference;

/**
 * @author Li Huang
 *
 */
public interface IDatabase
{
    /** stores identifiers in upper case */
    public final static int STORES_UPPERCASE = 301;
    /** stores identifiers in lower case */
    public final static int STORES_LOWERCASE = 302;
    /** stores identifiers in mixed case */
    public final static int STORES_MIXEDCASE = 303;

    public String getDatabaseType();


}
