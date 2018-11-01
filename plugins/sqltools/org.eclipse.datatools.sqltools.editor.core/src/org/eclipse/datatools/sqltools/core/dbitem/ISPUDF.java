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
