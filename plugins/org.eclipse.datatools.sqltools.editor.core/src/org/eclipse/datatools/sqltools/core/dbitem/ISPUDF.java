/*
 * Confidential Property of Sybase, Inc.
 * (c) Copyright Sybase, Inc. 2004-2006.
 * All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.dbitem;

import java.sql.SQLException;
import java.util.Map;


/**
 * Represents a SP (Stored procedure) or UDF.
 * @author Yang Liu
 */
public interface ISPUDF
{
    /**
     * Gets parameter descriptor of this SP or UDF.
     */
    public ParameterDescriptor[] getParameterDescriptor() throws SQLException;

    /**
     * Gets parameter default values of this SP or UDF.
     */
    public Map getParameterDefalutValues(String sql) throws SQLException;

}
