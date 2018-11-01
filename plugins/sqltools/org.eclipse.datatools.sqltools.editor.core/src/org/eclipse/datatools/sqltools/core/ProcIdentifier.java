/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core;

import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;

/**
 * This interface is used to identify a database object.
 * 
 * @author Yang Liu
 * @author Hui Cao
 */
public interface ProcIdentifier
{
    public static final int TYPE_SP = 0;
    public static final int TYPE_UDF = 1;
    public static final int TYPE_EVENT = 2;
    public static final int TYPE_TRIGGER = 3;
    public static final int TYPE_SQL = 4; //represents all the other types

    public static final String PROP_OWNER = "OWNER"; //$NON-NLS-1$
    public static final String PROP_NAME = "NAME"; //$NON-NLS-1$
    public static final String PROP_NUMBER = "NUMBER";	// for stored procedure's group number //$NON-NLS-1$
    public static final String PROP_TABLENAME = "TABLENAME";	// for trigger's owner table //$NON-NLS-1$
    public static final String PROP_TABLEOWNERNAME = "TABLEOWNERNAME";	// for trigger's subject table's owner//$NON-NLS-1$

    // the following two property is for in the future we may remove databaseidentifer,
    // and put info also as dynamic property.
    public static final String PROP_PROFILE = "PROFILE"; //$NON-NLS-1$
    public static final String PROP_DBNAME = "DBNAME"; //$NON-NLS-1$
    public static final String PROP_TYPE = "TYPE"; //$NON-NLS-1$

    /**
     * Gets the database object's name.
     */
    public String getProcName();

    /**
     * Gets the database object's type, as defined in this interface.
     */
    public int getType();

    /**
     * Gets owner name of the database object. Only valid for those database object with
     * an owner (or creator).
     * 
     * @return null if this database object don't support owner.
     */
    public String getOwnerName();

    /**
     * Gets owner name of the subject table when the procedural object type is trigger .
     * @since 1.5
     * @return null if the procedural object type is not trigger. otherwise, it may equals to getOwnerName().
     */
    public String getTableOwnerName();
    
    /**
     * 
     * @return the database identifier
     */
    public DatabaseIdentifier getDatabaseIdentifier();

    /**
     * 
     * @return the database name
     */
    public String getDatabaseName();

    /**
     * 
     * @return the connection profile name
     */
    public String getProfileName();

    /**
     * Encoded this as a string. Can be decoded using ProcIdentifierImpl.decode()
     */
    public String encode();

    /**
     * This method is only valid when this stored procedure supports group. Otherwise will return 0
     */
    public int getNumber();

    /**
     * This is method is only valid when this proc is trigger.
     */
    public String getTableName();

    /**
     * Gets a short display string for the rountine object.
     */
    public String getDisplayString();

    /**
     * Gets a long display string for the rountine object.
     */
    public String getLongDisplayString();

    /**
     * Gets a executable SQL string for the rountine object.
     */
    public String getCallableString();

    /**
     * 
     * Gets a executable SQL string for the rountine object without group number.
     */
    public String getCallableStringWithoutGroupNumber();

    /**
     * Gets a executable SQL string for the rountine object.
     * @param quoted_id whether "quoted identifier" is on
     */
    public String getCallableString(boolean quoted_id);

    /**
     * Gets a executable SQL string for the rountine object without group number.
     * @param quoted_id whether "quoted identifier" is on
     */
    public String getCallableStringWithoutGroupNumber(boolean quoted_id);

	/**
	 * Returns whether the given object equals to this ProcIdentifier object. 
	 * If the profile names are different, the comparison will base on the
	 * server url.
	 * @param obj
	 * @return
	 */
	public boolean equalsByServer(Object obj);
}
