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
package org.eclipse.datatools.sqltools.sql.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;

/**
 * 
 * @author Hui Cao
 * 
 */
public class ParameterUtil {

    /**
     * Parse the given sp definition and returns the parameter name/default value pairs.
     * 
     * @param profileName used to determine the db type
     * @param sp the sp definition
     * @return
     */
    public static Map getSPParamDefaultValues(DatabaseIdentifier databaseIdentifier, String sp)
    {
        HashMap map = new HashMap();
        HashMap params = getSPParams(databaseIdentifier, sp);
        for (Iterator iter = params.keySet().iterator(); iter.hasNext();)
        {
            String name = (String) iter.next();
            IASTSQLParam param = (IASTSQLParam) params.get(name);
            if (param.getDefaultValue() != null)
            {
                String value = param.getDefaultValue();
                if (value !=null && value.equalsIgnoreCase("null"))
                {
                    continue;
                }
                map.put(name, value);
            }
        }
        return map;
    }

    public static Map getSPParamTypeNameMapFromParser(DatabaseIdentifier databaseIdentifier, String sp)
    {
        HashMap map = new HashMap();
        HashMap params = getSPParams(databaseIdentifier, sp);
        for (Iterator iter = params.keySet().iterator(); iter.hasNext();)
        {
            String name = (String) iter.next();
            IASTSQLParam param = (IASTSQLParam) params.get(name);
            String type = param.getType();
            map.put(name, type);
        }
        return map;
    }
    /**
     * Parse the given sp definition and returns the parameter name/IASTSQLParam pairs.
     * 
     * @param databaseIdentifier used to determine the db type
     * @param sp the sp definition
     * @return
     */
    public static HashMap getSPParams(DatabaseIdentifier databaseIdentifier, String sp)
    {
        HashMap map = new HashMap();
        SQLParser parser = SQLToolsFacade.getSQLParser(databaseIdentifier.getProfileName(), "");
        IDocument doc = new Document(sp);
        ParsingResult result = parser.parse(sp, new ParserParameters(false));
        HashMap params = result.getParameters(doc, doc.getLength() - 1);
        return params;
    }

	/**
	 * Returns the ParameterDescriptors of a procedural object. Although we can get ParameterMode from catalog loader,
	 * its limited parameter type ( compared with JDBC ) and lack of nullablity and default value support force us to use this JDBC approach.
	 * @param con
	 * @param type
	 * @param runstring
	 * @return @throws SQLException
	 */
	public static ParameterDescriptor[] getParameterDescriptors(DatabaseIdentifier databaseIdentifier, Connection connection, int type,
	    ProcIdentifier proc) throws SQLException
	{
	    DatabaseMetaData md = null;
	    ResultSet rs = null;
	
	    try
	    {
	        List parameters = new ArrayList();
	        md = connection.getMetaData();
	        String dbname = proc.getDatabaseIdentifier().getDBname();
	
	        // set dbname to be null to mean it's default database. this is to make the system to be more
	        // error tolerant, even if profile don't tell us the db, we can still work.
	        if (dbname != null && dbname.length() == 0)
	        {
	            dbname = null;
	        }
	        rs = md.getProcedureColumns(dbname, proc.getOwnerName(), proc.getProcName(), "%");
	
	        while (rs.next())
	        {
	            String name = rs.getString(4);
	            short parmType = rs.getShort(5); // in/out, etc.
	
	            short sqlDataType = rs.getShort(6);
	            String dataTypeName = rs.getString(7);
	            //set the sqlDataType of double definitely because JDBC return wrong type.
	            //This only work for ASA/ASIQ since JDBC drive work well with ASE double precision data type
	            if (dataTypeName.equalsIgnoreCase("double") && sqlDataType == Types.NULL)
	            {
	                sqlDataType = Types.DOUBLE;
	            }
	
	            int precision = rs.getInt(8);
	            int length = rs.getInt(9);
	            short scale = rs.getShort(10);
	            short radix = rs.getShort(11);
	            short nullable = rs.getShort(12);
	            String comment = rs.getString(13);
	            switch (sqlDataType)
	            {
	                case Types.NUMERIC:
	                case Types.DECIMAL:
	                    if ("money".equalsIgnoreCase(dataTypeName) || "smallmoney".equalsIgnoreCase(dataTypeName))
	                    {
	                        break;
	                    }
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
	
	            ParameterDescriptor p = new ParameterDescriptor(databaseIdentifier, name, parmType, sqlDataType, precision, scale,
	                dataTypeName, nullable, comment);
	            parameters.add(p);
	
	        }
	        return (ParameterDescriptor[]) parameters.toArray(new ParameterDescriptor[0]);
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
