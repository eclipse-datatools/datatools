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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;


/**
 * This class is the meta information of a a procedure.
 * @author Yang Liu
 */
public class ProcedureDescriptor
{
    String _procedureSchema;
    String _procedureName;
    List   _parameters;

    /**
     * Prevents it from created from other package.
     */
    private ProcedureDescriptor()
    {
    }

    public static ProcedureDescriptor getProcedureDescriptor(DatabaseIdentifier databaseIdentifier, Connection connection, String catalog, String schema,
        String procedure) throws SQLException
    {
        ProcedureDescriptor pd = new ProcedureDescriptor();
        pd._procedureSchema = null;
        pd._procedureName = null;

        DatabaseMetaData md = null;
        ResultSet rs = null;

        try
        {
            md = connection.getMetaData();
            rs = md.getProcedureColumns(catalog, schema, procedure, "%");
            pd._parameters = new ArrayList();
            while (rs.next())
            {
                if (pd._procedureSchema == null)
                pd._procedureSchema = rs.getString(2);
                if (pd._procedureName == null)
                pd._procedureName = rs.getString(3);
                String name = rs.getString(4);
                short parmType = rs.getShort(5);	// in/out, etc.
                short sqlDataType = rs.getShort(6);
                String dataTypeName = rs.getString(7);
                int precision = rs.getInt(8);
                //int length = rs.getInt(9);
                short scale = rs.getShort(10);
                //short radix = rs.getShort(11);
                short nullable = rs.getShort(12);
                String comment = rs.getString(13);
                switch (sqlDataType)
                {
                    case Types.NUMERIC:
                    case Types.DECIMAL:
                        dataTypeName = dataTypeName + "(" + precision + "," + scale + ")";
                        break;
                    case Types.VARBINARY:
                    case Types.BINARY:
                    case Types.CHAR:
                    case Types.FLOAT:
                    case Types.VARCHAR:
                        dataTypeName = dataTypeName + "(" + precision + ")";
                        break;
                }

                ParameterDescriptor p = new ParameterDescriptor(databaseIdentifier, name, parmType, sqlDataType,precision,scale, dataTypeName, nullable,
                    comment);
                pd._parameters.add(p);

            }
            return pd;
        }
        finally
        {
            if (rs != null)
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
            }
            finally
            {
                rs = null;
            }
        }
    }
}
