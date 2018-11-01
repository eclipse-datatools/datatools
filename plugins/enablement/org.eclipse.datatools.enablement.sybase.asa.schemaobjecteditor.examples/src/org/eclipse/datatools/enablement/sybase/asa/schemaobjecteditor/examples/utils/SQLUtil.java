/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Types;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLDataType;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;
import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.IDatatype;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.osgi.util.NLS;

/**
 * Utility class for database/SQL related stuff.
 * 
 * @author Yang Liu
 */
public class SQLUtil
{
    public static final int TYPE_MUTIL_RECORDS                  = 0;
    public static final int TYPE_XML_RESULT                     = 1;

    public static final int NO_QUOTES                           = 0;
    public static final int MATCHING_SINGLE_QUOTES              = 1;
    public static final int MATCHING_DOUBLE_QUOTES              = 2;
    public static final int NO_MATCHING_QUOTES                  = 3;
    private static String   _sql;
    private static String[] _allCurrencySymbols;

    private static Pattern  ID_PATTERN                          = Pattern
                                                                        .compile("((\\Q[\\E([^\"]|(\"\"))+\\Q]\\E|[^\\s\"\\Q.\\E]+|\"([^\"]|(\"\"))+\")\\Q.\\E?)");
    private static Pattern  STRING_PATTERN                      = Pattern.compile("(([^\\s']+)|('([^']|(''))+'))");
    public static int       MAX_NAME_LENGTH_FOR_MESSAGE_DIALOGS = 30;

    private static ILog     _log                                = ExamplePlugin.getDefault().getLog();

    /**
     * "objstr" is a string representing a database object. Possibly in following formats: <ll> <li>objname</li> <li>
     * ownername.objname</li> <li>databasename.ownername.objname</li> <li>databasename..objname</li> </ll>
     * 
     * This method will try to figure it out, and return a string array. with the first element be the last segment in
     * "objstr".
     * 
     * In case of invalid objstr, will return null
     * 
     * NOTE: it is allowed to have whitespace in objname/ownername/databasename, in that case, the name is quoted in
     * "'". See SQL grammer for detail.
     * 
     * @param objstr a string identifying a database object.
     * @return string array
     */
    public static String[] parseDatabaseObject(String objstr)
    {
        ArrayList ids = new ArrayList();
        Matcher m = ID_PATTERN.matcher(objstr);
        while (m.find())
        {
            ids.add(0, SQLUtil.unquote(m.group(2)));
        }
        return (String[]) ids.toArray(new String[ids.size()]);
    }

    /**
     * Return whether objstr and proc represent the same database object. If db name or owner name is omitted int
     * objstr, defDB or defOwner will be used.
     * 
     * @param objstr
     * @param proc
     * @return
     */
    public static boolean equals(int parserType, int procType, String objstr, String defDB, String defOwner,
            ProcIdentifier proc, boolean caseSensitive)
    {
        if (!isSameType(parserType, procType))
        {
            return false;
        }
        String[] tokens = parseDatabaseObject(objstr);
        int count = tokens.length;

        if (count == 0 || count > 3)
        {
            return false;
        }

        // user can't use owner name for event definition, so don't check it
        if (parserType == SQLParserConstants.TYPE_SQL_ALTER_EVENT
                || parserType == SQLParserConstants.TYPE_SQL_CREATE_EVENT)
        {
            defOwner = proc.getOwnerName();
        }

        String db = count == 3 ? tokens[2] : defDB;
        String owner = count >= 2 ? tokens[1] : proc.getOwnerName();
        String objname = count >= 1 ? tokens[0] : objstr;

        return equalsIgnoreQuote(proc.getDatabaseIdentifier().getDBname(), db, caseSensitive)
                && equalsIgnoreQuote(proc.getOwnerName(), owner, caseSensitive)
                && equalsIgnoreQuote(proc.getProcName(), objname, caseSensitive);
    }

    /**
     * Returns whether the 2 Strings are equal by ignoring the surrounding quotes
     * 
     * @param s1
     * @param s2
     * @param caseSensitive whether to consider case
     * @return
     */
    public static boolean equalsIgnoreQuote(String s1, String s2, boolean caseSensitive)
    {
        if (caseSensitive)
        {
            boolean e = s1.equals(s2);
            if (!e)
            {
                s1 = unquote(s1);
                s2 = unquote(s2);
                e = s1.equals(s2);
            }
            return e;
        }
        else
        {
            boolean e = s1.equalsIgnoreCase(s2);
            if (!e)
            {
                if (s1.equals(unquote(s2)) || s2.equals(unquote(s1)))
                {
                    return true;
                }
                s1 = unquote(s1);
                s2 = unquote(s2);
                e = s1.equalsIgnoreCase(s2);
            }
            return e;
        }
    }

    /**
     * Return whether the object type represented by parserType and that by procType are of the same type
     * 
     * @param parserType type defined in SQLParserContants
     * @param procType type defined in ProcIdentifier
     * @return
     */
    public static boolean isSameType(int parserType, int procType)
    {
        boolean same = (parserType == SQLParserConstants.TYPE_SQL_ALTER_PROCEDURE || parserType == SQLParserConstants.TYPE_SQL_CREATE_PROCEDURE)
                && (procType == ProcIdentifier.TYPE_SP);
        same = same
                || (parserType == SQLParserConstants.TYPE_SQL_ALTER_FUNCTION || parserType == SQLParserConstants.TYPE_SQL_CREATE_FUNCTION)
                && (procType == ProcIdentifier.TYPE_UDF);
        same = same
                || (parserType == SQLParserConstants.TYPE_SQL_ALTER_EVENT || parserType == SQLParserConstants.TYPE_SQL_CREATE_EVENT)
                && (procType == ProcIdentifier.TYPE_EVENT);
        same = same
                || (parserType == SQLParserConstants.TYPE_SQL_ALTER_TRIGGER || parserType == SQLParserConstants.TYPE_SQL_CREATE_TRIGGER)
                && (procType == ProcIdentifier.TYPE_TRIGGER);
        return same;
    }

    /**
     * @param con
     * @param type
     * @param runstring
     * @return @throws SQLException
     */
    public static ParameterDescriptor[] getParameterDescriptors(DatabaseIdentifier databaseIdentifier,
            Connection connection, int type, ProcIdentifier proc) throws SQLException
    {
        List parameters = new ArrayList();

        // first try to get parameters from model
        SQLObject sqlObj = ModelUtil.findProceduralObject(proc);
        if (sqlObj instanceof SybaseRoutine)
        {
            SybaseRoutine routine = ((SybaseRoutine) sqlObj);
            Database db = ModelUtil.getDatabase(routine.getSchema());
            List schemas = ModelUtil.getSchemas(db, databaseIdentifier.getDBname());
            DatabaseDefinition dbdef = ModelUtil.getDatabaseDefinition(sqlObj);

            // Invoke getParameters method, guarantee to load the parameters info
            routine.getParameters();

            routine.parseParameterDefaultValues();

            // FIX 472169: Add the return column descriptor
            ParameterDescriptor returnColumn = new ParameterDescriptor(databaseIdentifier, "RETURN_VALUE",
                    DatabaseMetaData.procedureColumnReturn, Types.INTEGER, 0, (short) 0, "INT", (short) 0,
                    "procedureColumnReturn");
            parameters.add(returnColumn);

            for (Iterator it = routine.getParameters().iterator(); it.hasNext();)
            {
                SybaseParameter param = (SybaseParameter) it.next();

                DataTypeProvider provider = SQLToolsFacade.getConfiguration(databaseIdentifier,
                        new DatabaseVendorDefinitionId(dbdef.getProduct(), dbdef.getVersion())).getSQLDataService()
                        .getDataTypeProvider();
                String typeName = provider.getDataTypeString(param.getDataType(), false);
                int paramType = param.getJDBCParameterType().getValue();
                int precision = 0;
                short scale = 0;
                int jdbcType = 0;
                // predefined data type
                DataType dataType = param.getContainedType();
                if (dataType != null)
                {
                    PredefinedDataTypeDefinition typeDefinition = dbdef.getPredefinedDataTypeDefinition(dataType
                            .getName());
                    jdbcType = typeDefinition.getJdbcEnumType();
                    if (typeDefinition.isLengthSupported())
                    {
                        EStructuralFeature feature = dataType.eClass().getEStructuralFeature("length"); //$NON-NLS-1$
                        Object l = dataType.eGet(feature);
                        if (l instanceof Integer)
                        {
                            precision = ((Integer) l).intValue();
                        }
                    }
                    else if (typeDefinition.isPrecisionSupported())
                    {
                        EStructuralFeature feature = dataType.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
                        Object l = dataType.eGet(feature);
                        if (l instanceof Integer)
                        {
                            precision = ((Integer) l).intValue();
                        }
                    }

                    if (typeDefinition.isScaleSupported())
                    {
                        EStructuralFeature feature = dataType.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
                        Object l = dataType.eGet(feature);
                        if (l instanceof Integer)
                        {
                            scale = (short) ((Integer) l).intValue();
                        }
                    }
                }
                else
                {
                    UserDefinedType udt = param.getReferencedType();
                    if (udt != null && udt instanceof DistinctUserDefinedType)
                    {
                        PredefinedDataType predefinedRepresentation = ((DistinctUserDefinedType) udt)
                                .getPredefinedRepresentation();
                        PredefinedDataTypeDefinition typeDefinition = dbdef
                                .getPredefinedDataTypeDefinition(predefinedRepresentation.getName());
                        jdbcType = typeDefinition.getJdbcEnumType();
                    }
                }
                String dft = ((SybaseParameter) param).getDefaultValue();
                // FIXME for backward compatible
                if (dft != null)
                {
                    dft = unquote(dft);
                }
                ParameterDescriptor p = new ParameterDescriptor(databaseIdentifier, param.getName(), paramType,
                        jdbcType, precision, scale, typeName, (short) (param.isNullable() ? 1 : 0), param
                                .getDescription());
                p.setDefaultValue(dft);
                parameters.add(p);
            }
            return (ParameterDescriptor[]) parameters.toArray(new ParameterDescriptor[0]);
        }

        DatabaseMetaData md = null;
        ResultSet rs = null;

        try
        {
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
                // set the sqlDataType of double definitely because JDBC return wrong type.
                // This only work for ASA/ASIQ since JDBC drive work well with ASE double precision data type
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

                ParameterDescriptor p = new ParameterDescriptor(databaseIdentifier, name, parmType, sqlDataType,
                        precision, scale, dataTypeName, nullable, comment);
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

    /**
     * parse the event handler body and return all the parameters of "event_parameter" function(ASA)
     * 
     * @param profileName
     * @param eventName
     * @return @throws SQLException
     * @throws NoSuchProfileException
     */
    public static String[] getEventParameters(ProcIdentifier proc) throws SQLException, NoSuchProfileException
    {
        String[] ps = new String[0];
        if (proc == null || proc.getProcName() == null || proc.getProcName().trim().length() == 0)
        {
            return ps;
        }

        String eventName = proc.getProcName();

        // remove the owner name
        if (eventName.indexOf('.') >= 0)
        {
            eventName = eventName.substring(eventName.lastIndexOf('.') + 1);
        }
        String text = null;

        IControlConnection controlconnection = EditorCorePlugin.getControlConnectionManager()
                .getOrCreateControlConnection(proc.getDatabaseIdentifier());
        text = controlconnection.getProcSource(proc);
        if (text == null)
        {
            return ps;
        }
        SQLParser parser = SQLToolsFacade.getSQLParser(proc.getDatabaseIdentifier().getProfileName(), "");
        ParsingResult result = parser.parse(text, new ParserParameters(false));
        ps = result.getEntries(SQLParserConstants.EVENT_PARAMETERS);
        if (ps != null)
        {
            for (int i = 0; i < ps.length; i++)
            {
                // in ASA, strings are enclosed by '' while identifiers ""
                if (ps[i] != null && ps[i].length() > 1 && ps[i].charAt(0) == '\''
                        && ps[i].charAt(ps[i].length() - 1) == '\'')
                {
                    ps[i] = ps[i].substring(1, ps[i].length() - 1);
                }

            }
            return ps;
        }

        return ps;
    }

    // public static String constructCallableString(int type, String objectName, String[] values, ParameterDescriptor[]
    // pds)
    // {
    // if (type == DmpConstants.TYPE_SP || type == DmpConstants.TYPE_UDF)
    // {
    // return constructSPUDFString(type, objectName, values, pds);
    // }
    // else if (type == DmpConstants.TYPE_EVENT)
    // {
    // return constructTriggerEventString(type, objectName, values, pds);
    // }
    // return "";
    // }

    /**
     * @return "exec ", "call ", "TRIGGER EVENT " or "" based on type
     */
    public static String getCallablePrefix(int type)
    {
        String prefix = "";
        switch (type)
        {
            case ProcIdentifier.TYPE_SP:
                prefix = "exec ";
                break;
            case ProcIdentifier.TYPE_UDF:
                prefix = "select ";
                break;
            case ProcIdentifier.TYPE_EVENT:
                prefix = "TRIGGER EVENT ";
                break;
        }
        return prefix;
    }

    /**
     * @return "exec ", "call ", "TRIGGER EVENT " or "" based on type
     */
    public static String getCallableStatementPrefix(int type)
    {
        String prefix = "";
        switch (type)
        {
            case ProcIdentifier.TYPE_SP:
                prefix = "call ";
                break;
            case ProcIdentifier.TYPE_UDF:
                prefix = "select ";
                break;
            case ProcIdentifier.TYPE_EVENT:
                prefix = "TRIGGER EVENT ";
                break;
        }
        return prefix;
    }

    /**
     * @param proc can be null
     * @param list must be valid value list
     * @param pds
     * @param quoted_id
     * @return
     */

    public static String constructDetailCALLSPUDFString(ProcIdentifier proc, List values, ParameterDescriptor[] pds,
            boolean quoted_id)
    {
        StringBuffer buffer = new StringBuffer(20);
        int type = proc == null ? ProcIdentifier.TYPE_SP : proc.getType();
        buffer.append(getCallableStatementPrefix(type));
        String LINESEPARATOR = System.getProperty("line.separator");

        String procName = null;
        if (proc != null)
        {
            buffer.append(proc.getCallableString(quoted_id));
            procName = proc.getProcName();
        }
        if (pds != null && pds.length > 0)
        {
            if (type == ProcIdentifier.TYPE_UDF || type == ProcIdentifier.TYPE_SP)
            {

                buffer.append("("); //$NON-NLS-1$
            }
            int j = 0;
            int k = 0;
            for (int i = 0; i < pds.length; i++)
            {
                String name = pds[i].getName();
                String prefix = "";

                if (pds[i].getParmType() == DatabaseMetaData.procedureColumnIn
                        || pds[i].getParmType() == DatabaseMetaData.procedureColumnInOut)
                {
                    String value = null;
                    if (values != null && values.size() >= (k + 1))
                    {
                        value = (String) values.get(k++);
                    }
                    if (name.equals(procName))
                    {
                        continue;
                    }
                    if (value != null && pds[i].isStringType())
                    {
                        value = quote(value, "'");
                    }
                    if (j != 0)
                    {
                        prefix = ",";
                    }
                    else
                    {
                        prefix = "";
                    }
                    buffer
                            .append(prefix
                                    + LINESEPARATOR
                                    + "     " + pds[i].getName() + "=" + value + " " + pds[i].getTypeName() + " " + pds[i].getParamTypeAsString()); //$NON-NLS-1$

                    j++;
                }
                if (pds[i].getParmType() == DatabaseMetaData.procedureColumnOut
                        || pds[i].getParmType() == DatabaseMetaData.procedureColumnUnknown)
                {
                    if (name.equals(procName))
                    {
                        continue;
                    }

                    String paramType = "OUT"; // pds[i].getParamTypeAsString();//FIXME it get "UNKNOWN",should be "OUT"

                    if (j != 0)
                    {
                        prefix = ",";
                    }
                    else
                    {
                        prefix = "";
                    }
                    buffer.append(prefix + LINESEPARATOR
                            + "     " + pds[i].getName() + " " + pds[i].getTypeName() + " " + paramType); //$NON-NLS-1$

                    j++;
                }

            }
            if (type == ProcIdentifier.TYPE_UDF || type == ProcIdentifier.TYPE_SP)
            {
                buffer.append(")"); //$NON-NLS-1$
            }
        }
        return buffer.toString();
    }

    /**
     * @param proc can be null
     * @param list must be valid value list
     * @param pds
     * @param quoted_id
     * @return
     */
    public static String constructSPUDFString(ProcIdentifier proc, List values, ParameterDescriptor[] pds,
            boolean quoted_id)
    {
        StringBuffer buffer = new StringBuffer(20);
        int type = proc == null ? ProcIdentifier.TYPE_SP : proc.getType();
        buffer.append(getCallablePrefix(type));

        if (proc != null)
            buffer.append(proc.getCallableString(quoted_id));
        buffer.append(" ");
        if (type == ProcIdentifier.TYPE_UDF)
        {
            buffer.append("("); //$NON-NLS-1$
        }
        if (pds != null && pds.length > 0)
        {

            for (int i = 0; i < pds.length; i++)
            {
                if (i != 0)
                {
                    buffer.append(", "); //$NON-NLS-1$
                }
                if (values == null || values.size() <= i || values.get(i) == null)
                {
                    buffer.append("null"); //$NON-NLS-1$
                }
                else if (pds[i].isStringType() && SQLUtil.findQuotes(values.get(i).toString()) == SQLUtil.NO_QUOTES)
                {
                    buffer.append(quote((String) values.get(i), '\''));
                }
                else
                {
                    buffer.append(values.get(i));
                }
            }
        }
        if (type == ProcIdentifier.TYPE_UDF)
        {
            buffer.append(")"); //$NON-NLS-1$
        }
        return buffer.toString();
    }

    public static String quote(String in, char quoteChar)
    {
        StringBuffer buffer = new StringBuffer(in.length() + 8);
        buffer.append(quoteChar);
        int len = in.length();
        for (int i = 0; i < len; i++)
        {
            char c = in.charAt(i);
            if (c == quoteChar)
                buffer.append(c);
            buffer.append(c);
        }

        buffer.append(quoteChar);
        return buffer.toString();
    }

    /**
     * @param proc can be null
     * @param quoted_id
     * @param triggerParams
     * @return
     */
    public static String constructTriggerEventString(ProcIdentifier proc, Map values, boolean quoted_id)
    {
        // For events we should not supply the dbname or owner name
        StringBuffer sb = new StringBuffer("TRIGGER EVENT "); //$NON-NLS-1$
        if (proc != null)
        {
            sb.append(quoteWhenNecessary(proc.getProcName(), proc.getDatabaseIdentifier()));
        }
        if (values != null && values.size() > 0)
        {
            sb.append("("); //$NON-NLS-1$
            for (Iterator iter = values.keySet().iterator(); iter.hasNext();)
            {
                String name = (String) iter.next();
                String value = (String) values.get(name);
                sb.append("\"" + name + "\"").append("=").append("\'" + value + "\'").append(",");
            }

            sb.deleteCharAt(sb.length() - 1); // remove the last ","
            sb.append(")"); //$NON-NLS-1$
        }
        return sb.toString();
    }

    /**
     * test whether a SQLException is warning only.
     * 
     * @param sqlexception
     * @return
     */
    public static boolean justWarnings(SQLException sqlexception)
    {
        do
        {
            if (!(sqlexception instanceof SQLWarning))
            {
                return false;
            }
            sqlexception = sqlexception.getNextException();
        }
        while (sqlexception != null);
        return true;
    }

    public static void saveResultToStream(ObjectOutputStream oos, Object result) throws IOException
    {
        if (oos != null)
        {
            oos.writeObject(result);
            oos.flush();
        }
    }

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
                if (value != null && value.equalsIgnoreCase("null"))
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

    public static int convert2SQLType(String typeName)
    {
        if (typeName == null)
        {
            return 0;
        }
        String name = typeName.toUpperCase();

        if (name.equals("BIGINT") || name.equals("UNSIGNED BIGINT"))
        {
            return Types.BIGINT;
        }
        else if (name.equals("BINARY") || name.equals("UNIQUEIDENTIFIER"))
        {
            return Types.BINARY;
        }
        else if (name.equals("BIT"))
        {
            return Types.BIT;
        }
        else if (name.equals("BLOB"))
        {
            return Types.BLOB;
        }
        else if (name.equals("BOOLEAN") || name.equals("BOOL"))
        {
            return Types.BOOLEAN;
        }
        else if (name.equals("CHAR") || name.equals("CHARACTER") || name.equals("NCHAR")
                || name.equals("NATIONAL CHARACTER") || name.equals("NATIONAL CHAR") || name.equals("NCHAR")
                || name.equals("SYSNAME") || name.equals("UNICHAR") || name.equals("UNIQUEIDENTIFIERSTR"))
        {
            return Types.CHAR;
        }
        else if (name.equals("CLOB"))
        {
            return Types.CLOB;
        }
        else if (name.equals("DATALINK"))
        {
            return Types.DATALINK;
        }
        else if (name.equals("DATE"))
        {
            return Types.DATE;
        }
        else if (name.equals("DECIMAL") || name.equals("DEC") || name.equals("MONEY") || name.equals("SMALLMONEY"))
        {
            return Types.DECIMAL;
        }
        else if (name.equals("DISTINCT"))
        {
            return Types.DISTINCT;
        }
        else if (name.equals("DOUBLE") || name.equals("DOUBLE PRECISION"))
        {
            return Types.DOUBLE;
        }
        else if (name.equals("FLOAT"))
        {
            return Types.FLOAT;
        }
        else if (name.equals("INTEGER") || name.equals("INT") || name.equals("UNSIGNED INTEGER")
                || name.equals("UNSIGNED INT"))
        {
            return Types.INTEGER;
        }
        else if (name.equals("JAVA_OBJECT"))
        {
            return Types.JAVA_OBJECT;
        }
        else if (name.equals("LONGVARBINARY") || name.equals("IMAGE") || name.equals("XML"))
        {
            return Types.LONGVARBINARY;
        }
        else if (name.equals("LONGVARCHAR") || name.equals("LONG VARCHAR") || name.equals("TEXT"))
        {
            return Types.LONGVARCHAR;
        }
        else if (name.equals("NULL"))
        {
            return Types.NULL;
        }
        else if (name.equals("NUMERIC"))
        {
            return Types.NUMERIC;
        }
        else if (name.equals("OTHER"))
        {
            return Types.OTHER;
        }
        else if (name.equals("REAL"))
        {
            return Types.REAL;
        }
        else if (name.equals("REF"))
        {
            return Types.REF;
        }
        else if (name.equals("SMALLINT"))
        {
            return Types.SMALLINT;
        }
        else if (name.equals("STRUCT"))
        {
            return Types.STRUCT;
        }
        else if (name.equals("TIME"))
        {
            return Types.TIME;
        }
        else if (name.equals("TIMESTAMP") || name.equals("DATETIME") || name.equals("SMALLDATETIME"))
        {
            return Types.TIMESTAMP;
        }
        else if (name.equals("TINYINT"))
        {
            return Types.TINYINT;
        }
        else if (name.equals("VARBINARY") || name.equals("UNITEXT"))
        {
            return Types.VARBINARY;
        }
        else if (name.equals("VARCHAR") || name.equals("NVARCHAR") || name.equals("UNIVARCHAR")
                || (name.indexOf("CHAR") > 0 && name.indexOf("VARYING") > 0))
        {
            return Types.VARCHAR;
        }
        else
        {
            return 0;
        }
    }

    public static int getDefaultPrecision(int sqlType)
    {
        switch (sqlType)
        {

            case Types.BIGINT:
                return 10;
            case Types.BINARY:
                return 1;
            case Types.BIT:
                return 1;
            case Types.BLOB:
                return 10;
            case Types.BOOLEAN:
                return 1;
            case Types.CHAR:
                return 1;
            case Types.DATE:
                return 10;
            case Types.DECIMAL:
                return 30;
            case Types.DOUBLE:
                return 15;
            case Types.FLOAT:
                return 10;
            case Types.INTEGER:
                return 10;
            case Types.NUMERIC:
                return 10;
            case Types.OTHER:
                return 10;
            case Types.REAL:
                return 7;
            case Types.SMALLINT:
                return 5;
            case Types.TIME:
                return 10;
            case Types.TIMESTAMP:
                return 23;
            case Types.TINYINT:
                return 3;
            case Types.VARCHAR:
                return 1;
            case Types.REF:
            case Types.STRUCT:
            case Types.VARBINARY:
            case Types.CLOB:
            case Types.DATALINK:
            case Types.DISTINCT:
            case Types.JAVA_OBJECT:
            case Types.LONGVARBINARY:
            case Types.LONGVARCHAR:
            case Types.NULL:
            default:
                return Integer.MAX_VALUE;
        }
    }

    public static int getDefaultScale(int sqlType)
    {
        switch (sqlType)
        {
            case Types.TIME:
            case Types.TIMESTAMP:
                return 3;
            case Types.DECIMAL:
            case Types.DOUBLE:
            case Types.FLOAT:
            case Types.NUMERIC:
            case Types.REAL:
            case Types.OTHER:
            case Types.INTEGER:
            case Types.SMALLINT:
            case Types.TINYINT:
            case Types.VARCHAR:
            case Types.BIGINT:
            case Types.BINARY:
            case Types.BIT:
            case Types.BLOB:
            case Types.BOOLEAN:
            case Types.CHAR:
            case Types.DATE:
            case Types.REF:
            case Types.STRUCT:
            case Types.VARBINARY:
            case Types.CLOB:
            case Types.DATALINK:
            case Types.DISTINCT:
            case Types.JAVA_OBJECT:
            case Types.LONGVARBINARY:
            case Types.LONGVARCHAR:
            case Types.NULL:
            default:
                return 0;
        }
    }

    private static String escape(String s)
    {
        StringBuffer stringbuffer = new StringBuffer(s.length() + 16);
        int j = s.length();
        for (int i = 0; i < j; i++)
        {
            char c = s.charAt(i);
            if (c == '\'')
            {
                stringbuffer.append("\'\'"); //$NON-NLS-1$
            }
            else
            {
                stringbuffer.append(c);
            }
        }

        return stringbuffer.toString();
    }

    public static int getSQLType(ProcIdentifier procId)
    {
        int procType = procId.getType();
        int sqlType = SQLParserConstants.TYPE_SQL_ROOT;
        switch (procType)
        {
            case ProcIdentifier.TYPE_SP:
                sqlType = SQLParserConstants.TYPE_SQL_CREATE_PROCEDURE;
                break;
            case ProcIdentifier.TYPE_UDF:
                sqlType = SQLParserConstants.TYPE_SQL_CREATE_FUNCTION;
                break;
            case ProcIdentifier.TYPE_EVENT:
                sqlType = SQLParserConstants.TYPE_SQL_CREATE_EVENT;
                break;
            case ProcIdentifier.TYPE_TRIGGER:
                sqlType = SQLParserConstants.TYPE_SQL_CREATE_TRIGGER;
                break;
        }
        return sqlType;
    }

    public static int getSQLType(SQLEditor sqlEditor)
    {
        /** The default sqlType is root */
        int sqlType = SQLParserConstants.TYPE_SQL_ROOT;
        if (sqlEditor.getEditorInput() instanceof ProcEditorInput)
        {
            int procType = ((ProcEditorInput) sqlEditor.getEditorInput()).getProcIdentifier().getType();
            switch (procType)
            {
                case ProcIdentifier.TYPE_SP:
                    sqlType = SQLParserConstants.TYPE_SQL_CREATE_PROCEDURE;
                    break;
                case ProcIdentifier.TYPE_UDF:
                    sqlType = SQLParserConstants.TYPE_SQL_CREATE_FUNCTION;
                    break;
                case ProcIdentifier.TYPE_EVENT:
                    sqlType = SQLParserConstants.TYPE_SQL_CREATE_EVENT;
                    break;
                case ProcIdentifier.TYPE_TRIGGER:
                    sqlType = SQLParserConstants.TYPE_SQL_CREATE_TRIGGER;
                    break;
            }
        }
        return sqlType;
    }

    /**
     * This method is to convert the String(quoted or unquoted) to quoted String. If the original string is incorrectly
     * quoted, Exception will be thrown.
     * 
     * @param pd
     * @param inputeValue
     * @return String
     */
    public static String getQuotedString(String dataType, String inputValue, DatabaseIdentifier databaseIdentifier)
            throws Exception
    {
        if (isStringType(dataType))
        {
            int quoteType = findQuotes(inputValue);
            if (quoteType == SQLUtil.MATCHING_DOUBLE_QUOTES || quoteType == SQLUtil.MATCHING_SINGLE_QUOTES)
            {
                return inputValue;
            }
            // if no quotes found,add two single quotes
            else if (quoteType == SQLUtil.NO_QUOTES)
            {
                return "'" + inputValue + "'";
            }
            // when the original string is incorrectly quoted, throw exception
            else if (quoteType == SQLUtil.NO_MATCHING_QUOTES)
            {
                if (databaseIdentifier == null)
                {
                    throw new SQLException(NLS.bind(Messages.SQLUtil_error_invalid_quotes, (new Object[]
                    {
                        dataType, inputValue
                    })));
                }
                Statement stmt = null;
                ResultSet rs = null;
                Connection cn = null;
                try
                {
                    cn = getConnection(databaseIdentifier);
                    StringBuffer queryWithSingleQuote = new StringBuffer();
                    queryWithSingleQuote.append("select convert(").append(dataType).append(",'").append(inputValue)
                            .append("')");

                    StringBuffer queryWithDoubleQuote = new StringBuffer();
                    queryWithDoubleQuote.append("select convert(").append(dataType).append(",\"").append(inputValue)
                            .append("\")");

                    stmt = cn.createStatement();
                    String sql = queryWithSingleQuote.toString();
                    rs = stmt.executeQuery(sql);

                    sql = queryWithDoubleQuote.toString();
                    rs = stmt.executeQuery(sql);
                }
                finally
                {
                    if (rs != null)
                    {
                        try
                        {
                            rs.close();
                        }
                        catch (Exception e)
                        {
                            // ignore
                            _log.log(new Status(IStatus.WARNING, ExamplePlugin.PLUGIN_ID,
                                    Messages.common_ignoreException));
                        }
                    }

                    if (stmt != null)
                    {
                        try
                        {
                            stmt.close();
                        }
                        catch (Exception e)
                        {
                            // ignore
                            _log.log(new Status(IStatus.WARNING, ExamplePlugin.PLUGIN_ID,
                                    Messages.common_ignoreException));
                        }
                    }

                }
            }
        }
        return inputValue;
    }

    public static int findQuotes(String content)
    {
        // void NullPointerException
        if (content == null)
        {
            return SQLUtil.NO_QUOTES;
        }
        else if (content.indexOf('\'') < 0 && content.indexOf('\"') < 0)
        {
            return SQLUtil.NO_QUOTES;
        }
        else
        {
            if (content.indexOf("'") == 0 && content.lastIndexOf("'") == content.length() - 1)
            {
                return SQLUtil.MATCHING_SINGLE_QUOTES;
            }
            else if (content.indexOf("\"") == 0 && content.lastIndexOf("\"") == content.length() - 1)
            {
                return SQLUtil.MATCHING_DOUBLE_QUOTES;
            }
            else
            {
                return SQLUtil.NO_MATCHING_QUOTES;
            }
        }
    }

    /**
     * Quotes the string when it contains space or single quote and is not properly quoted.
     * 
     * @param objstr
     * @return
     */
    public static String quoteStringWhenNecessary(String objstr)
    {
        String r = null;
        Matcher m = STRING_PATTERN.matcher(objstr);
        while (m.find())
        {
            r = m.group();
            break;
        }
        if (r != null && r.equals(objstr))
        {
            return r;
        }
        return quote(objstr, "'");

    }

    /**
     * This is a convenience method of
     * <code>quoteWhenNecessary(String content, DatabaseIdentifier _databaseIdentifier, String
     * quote)</code>
     * 
     * @param content original identifier
     * @return
     */
    public static String quoteWhenNecessary(String content, DatabaseIdentifier databaseIdentifier)
    {
        return quoteWhenNecessary(content, databaseIdentifier, "\"");
    }

    /**
     * Checks the validity and quoted identifier setting and surrounds content with proper quotation mark if necessary.
     * 
     * @param id Identifier
     * @param databaseIdentifier database Identfier
     * @param quote quotes, maybe single quote or double quote
     * @param IdentifierType identfierType,see IIdentfierValidator API
     * @return quoted String
     */
    public static String quoteWhenNecessary(String id, DatabaseIdentifier databaseIdentifier, String quote,
            int identiferType)
    {
        if (id == null || id.equals("") || databaseIdentifier == null)
        {
            return id;
        }
        SQLDevToolsConfiguration factory = SQLToolsFacade.getConfigurationByProfileName(databaseIdentifier
                .getProfileName());
        IIdentifierValidator validator = factory.getSQLService().getIdentifierValidator();
        if (validator != null)
        {
            ValidatorMessage msg = validator.isValid(id, identiferType, databaseIdentifier);
            if (msg == null || !ValidatorMessage.hasError(msg, ValidatorMessage.ERROR))
            {
                return id;
            }
        }

        boolean quoted_id = false;
        // initiate connection with quoted_identifier option
        IDatabaseSetting databaseConfiguration = factory.getDatabaseSetting(databaseIdentifier);
        if (databaseConfiguration != null)
        {
            try
            {
                quoted_id = ((Boolean) databaseConfiguration
                        .getConnectionConfigProperty(IDatabaseSetting.C_QUOTED_IDENTIFIER)).booleanValue();
            }
            catch (Exception e)
            {
                // can't get setting, assume it's false
            }
        }

        if (quoted_id)
        {
            return quote(id, quote);
        }
        return id;

    }

    /**
     * Checks the validity and quoted identifier setting and surrounds content with proper quotation mark if necessary.
     * 
     * @param id original identifier
     * @return
     */
    public static String quoteWhenNecessary(String id, DatabaseIdentifier databaseIdentifier, String quote)
    {
        return quoteWhenNecessary(id, databaseIdentifier, quote, IIdentifierValidator.IDENTIFIER_TYPE_UNKNOW);
    }

    /**
     * Checks the validity of the unquoted identifier and unquotes content if necessary.
     * 
     * @param id original identifier
     * @return
     */
    public static String unquoteWhenNecessary(String id, DatabaseIdentifier databaseIdentifier)
    {
        if (id == null || id.equals("") || databaseIdentifier == null)
        {
            return id;
        }
        String newId = unquote(id);
        SQLDevToolsConfiguration factory = SQLToolsFacade.getConfigurationByProfileName(databaseIdentifier
                .getProfileName());
        IIdentifierValidator validator = factory.getSQLService().getIdentifierValidator();
        if (validator != null)
        {
            ValidatorMessage msg = validator.isValid(newId, IIdentifierValidator.IDENTIFIER_TYPE_UNKNOW,
                    databaseIdentifier);
            if (msg == null || !ValidatorMessage.hasError(msg, ValidatorMessage.ERROR))
            {
                return newId;
            }
        }
        return id;

    }

    /**
     * surround content with quoteMark and double every quoteMark inside content
     * 
     * @param content
     * @param quoteMark
     * @return
     */
    public static String quote(String content, String quoteMark)
    {
        return quoteMark + content.replaceAll(quoteMark, quoteMark + quoteMark) + quoteMark;
    }

    /**
     * remove the surrounding quotation mark and restore 2 successive quotation marks to 1
     * 
     * @param quoted
     * @return
     */
    public static String unquote(String quoted)
    {
        String content = quoted;
        if (quoted.indexOf("'") == 0 && quoted.lastIndexOf("'") == quoted.length() - 1 && quoted.length() > 1)
        {
            content = quoted.substring(1, quoted.length() - 1).replaceAll("''", "'");
        }
        else if (quoted.indexOf("\"") == 0 && quoted.lastIndexOf("\"") == quoted.length() - 1 && quoted.length() > 1)
        {
            content = quoted.substring(1, quoted.length() - 1).replaceAll("\"\"", "\"");
        }
        else if (quoted.indexOf("[") == 0 && quoted.lastIndexOf("]") == quoted.length() - 1 && quoted.length() > 1)
        {
            content = quoted.substring(1, quoted.length() - 1);
        }
        return content;
    }

    public static char getPeerCharacter(char character)
    {
        switch (character)
        {
            case '(':
                return ')';

            case ')':
                return '(';

            case '"':
                return character;

            case '\'':
                return character;

            default:
                throw new IllegalArgumentException();
        }
    }

    public static char getEscapeCharacter(char character)
    {
        switch (character)
        {
            case '"':
            case '\'':
                return '\\';
            default:
                return 0;
        }
    }

    /**
     * given the name and UDT list,return the IDatatype object
     * 
     * @param typeName
     * @param dTypes
     * @return
     */
    public static IDatatype getDatatypeByName(String typeName, DBObject[] dTypes)
    {
        if (dTypes == null)
        {
            return null;
        }
        int count = dTypes.length;
        for (int i = 0; i < count; i++)
        {
            if (typeName.equals(dTypes[i].getName()))
            {
                return (IDatatype) dTypes[i];
            }
        }
        return null;
    }

    public static boolean isBinaryType(IDatatype datatype)
    {
        if (datatype.isUDT())
        {
            return isBinaryType(datatype.getBaseType().toString());
        }
        else
        {
            return isBinaryType(datatype.toString());
        }

    }

    public static boolean isBinaryType(String datatype)
    {
        String strType = datatype;
        int position = strType.indexOf('(');
        if (position > 0)
        {
            strType = strType.substring(0, position);
        }
        int _sqlDataType = convert2SQLType(strType);
        return _sqlDataType == Types.BINARY || _sqlDataType == Types.LONGVARBINARY || _sqlDataType == Types.VARBINARY;
    }

    public static boolean isNumericType(String datatype)
    {
        int position = datatype.indexOf('(');
        if (position > 0)
        {
            datatype = datatype.substring(0, position);
        }
        int _sqlDataType = convert2SQLType(datatype);
        return _sqlDataType == Types.BIGINT || _sqlDataType == Types.DECIMAL || _sqlDataType == Types.DOUBLE
                || _sqlDataType == Types.FLOAT || _sqlDataType == Types.INTEGER || _sqlDataType == Types.NUMERIC
                || _sqlDataType == Types.REAL || _sqlDataType == Types.SMALLINT || _sqlDataType == Types.TINYINT;
    }

    public static boolean isNumericType(int datatype)
    {
        return datatype == Types.BIGINT || datatype == Types.DECIMAL || datatype == Types.DOUBLE
                || datatype == Types.FLOAT || datatype == Types.INTEGER || datatype == Types.NUMERIC
                || datatype == Types.REAL || datatype == Types.SMALLINT || datatype == Types.TINYINT;
    }

    public static boolean isNumericType(IDatatype datatype)
    {
        if (datatype.isUDT())
        {
            return isNumericType(datatype.getBaseType().toString());
        }
        else
        {
            return isNumericType(datatype.toString());
        }
    }

    public static boolean isStringType(String datatype)
    {
        String strType = datatype;
        int position = strType.indexOf('(');
        if (position > 0)
        {
            strType = strType.substring(0, position);
        }
        int _sqlDataType = convert2SQLType(strType);
        return isStringType(_sqlDataType);
    }

    public static boolean isStringType(IDatatype datatype)
    {
        if (datatype.isUDT())
        {
            return isStringType(datatype.getBaseType().toString());
        }
        else
        {
            return isStringType(datatype.toString());
        }
    }

    public static boolean isStringType(int sqlType)
    {
        return sqlType == Types.CHAR || sqlType == Types.VARCHAR || sqlType == Types.LONGVARCHAR
                || sqlType == Types.DATE || sqlType == Types.TIME || sqlType == Types.TIMESTAMP;
    }

    /**
     * add definition of assigned params to the orginal SQL string, add the referenced params to list "refWithoutDef".
     * 
     * @param sql
     * @param databaseIdentifier
     * @return
     */
    public static String addDefOfAss(String sql, DatabaseIdentifier databaseIdentifier, Map varDecs,
            final ArrayList refWithoutDef)
    {
        String newSql = sql;
        // parse
        SQLParser p = SQLToolsFacade.getSQLParser(databaseIdentifier.getProfileName(), "");
        if (newSql == null)
        {
            return null;
        }

        // simply return the original sql when parser is null
        if (p == null)
        {
            return sql;
        }
        IDocument doc = new Document(newSql);
        ParsingResult result = p.parse(newSql, new ParserParameters(false));
        IASTStart root = result.getRootNode();
        root.setDocument(doc);
        String[] varRefs = result.getEntries(SQLParserConstants.VARIABLE_REFERENCES);
        String[] varAsss = result.getEntries(SQLParserConstants.VARIABLE_ASSIGNMENTS);
        String[] varDefs = result.getEntries(SQLParserConstants.VARIABLE_DECLARATIONS);
        // Collection of IASTSQLParam

        // ArrayList of IASTSQLParam
        ArrayList assWithoutDef = new ArrayList();

        if (varRefs != null && varRefs.length > 0 && varDecs != null)
        {
            outer: for (int i = 0; i < varRefs.length; i++)
            {
                // must has declaration
                if (varDecs.containsKey(varRefs[i]))
                {
                    // we only care about declarations before the selected text
                    for (int j = 0; j < varDefs.length; j++)
                    {
                        if (varDefs[j].equals(varRefs[i]))
                        {
                            continue outer;
                        }
                    }
                    refWithoutDef.add(varDecs.get(varRefs[i]));
                }
            }
        }

        if (varAsss != null && varAsss.length > 0 && varDecs != null)
        {
            outer: for (int i = 0; i < varAsss.length; i++)
            {
                // must has declaration
                if (varDecs.containsKey(varAsss[i]))
                {
                    // we only care about declarations before the selected text
                    for (int j = 0; j < varDefs.length; j++)
                    {
                        if (varDefs[j].equals(varAsss[i]))
                        {
                            continue outer;
                        }
                    }
                    IASTSQLParam param = (IASTSQLParam) varDecs.get(varAsss[i]);
                    if (!refWithoutDef.contains(param))
                    {
                        assWithoutDef.add(param);
                    }
                }
            }
        }

        if (assWithoutDef.size() > 0)
        {
            // for variables that are assigned but not declared in the selected text, just add declare
            for (int i = 0; i < assWithoutDef.size(); i++)
            {
                IASTSQLParam param = (IASTSQLParam) assWithoutDef.get(i);
                IASTSQLDataType datatype = param.getTypeObject();
                newSql = "declare " + param.getName() + " " + ((SimpleNode) datatype).getSQLText()
                        + System.getProperty("line.separator") + newSql;
            }
        }
        return newSql;
    }

    /**
     * add definitions statements of referenced or assigned variables/params to the original sql statement
     * 
     * @param sql
     * @param databaseIdentifier
     * @param varDecs
     * @return
     */
    public static String addDefOfAssAndRef(String sql, DatabaseIdentifier databaseIdentifier, Map varDecs)
    {
        ArrayList refWithoutDef = new ArrayList();
        String newSql = addDefOfAss(sql, databaseIdentifier, varDecs, refWithoutDef);
        if (refWithoutDef.size() > 0)
        {
            // for variables that are referenced but not declared in the selected text, just add declare
            for (int i = 0; i < refWithoutDef.size(); i++)
            {
                IASTSQLParam param = (IASTSQLParam) refWithoutDef.get(i);
                IASTSQLDataType datatype = param.getTypeObject();
                newSql = "declare " + param.getName() + " " + ((SimpleNode) datatype).getSQLText()
                        + System.getProperty("line.separator") + newSql;
            }
        }
        return newSql;
    }

    /**
     * Returns the reusable connection from the control connection. Since this connection is shared by other components,
     * do not close it.
     * 
     * @param databaseIdentifier
     * @return
     */
    public static Connection getConnection(DatabaseIdentifier databaseIdentifier)
    {
        try
        {
            IControlConnection c = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(
                    databaseIdentifier);
            return c.getReusableConnection();
        }
        catch (Exception e)
        {
            _log.log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID,
                    Messages.ObjectViewerUtil_exception_getconnection));
        }
        return null;
    }

    /**
     * Get Control Connection from profile name
     * 
     * @param profile
     * @return
     */
    public static IControlConnection getControlConnection(DatabaseIdentifier databaseIdentifier)
    {
        try
        {
            return EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(databaseIdentifier);
        }
        catch (SQLException e)
        {
            return null;
        }
        catch (NoSuchProfileException e)
        {
            return null;
        }
    }

    /**
     * return the all currencySymbols
     * 
     * @return
     */
    public synchronized static String[] getAvaiableCurrencySymbols()
    {
        if (_allCurrencySymbols == null)
        {
            Locale[] locals = Locale.getAvailableLocales();
            ArrayList list = new ArrayList();
            for (int i = 0; i < locals.length; i++)
            {
                list.add(NumberFormat.getInstance(locals[i]).getCurrency().getSymbol(locals[i]));
            }
            _allCurrencySymbols = (String[]) list.toArray(new String[list.size()]);
            return _allCurrencySymbols;
        }
        else
        {
            return _allCurrencySymbols;
        }
    }

    /**
     * This method chops out a given String to a string with lenthg MAX_NAME_LENGTH_FOR_MESSAGE_DIALOGS, The usage is
     * when an identifier is too long as much as 255 characters the message dialog cannot accomodate it
     * 
     * @param name
     * @return
     */

    public static String stripNameForMessages(String name)
    {
        String tempName = name;
        if (tempName != null && tempName.length() > MAX_NAME_LENGTH_FOR_MESSAGE_DIALOGS)
        {
            tempName = tempName.substring(0, MAX_NAME_LENGTH_FOR_MESSAGE_DIALOGS) + "...";
        }
        return tempName;
    }

    /**
     * Returns an instance of <code>SQLException</code> containing detailed error information
     * 
     * @param throwable
     * @return
     */
    public static SQLException retrieveDetailException(Throwable throwable)
    {
        String lineSep = System.getProperty("line.separator");
        if (throwable != null)
        {
            StringBuffer msg = new StringBuffer("");
            if (throwable.getLocalizedMessage() != null)
            {
                msg.append(throwable.getLocalizedMessage());
            }
            else
            {
                msg.append(throwable.getMessage());
            }
            if (throwable instanceof SQLException)
            {
                SQLException sqlEx = (SQLException) throwable;
                while (sqlEx.getNextException() != null)
                {
                    sqlEx = sqlEx.getNextException();
                    if (sqlEx.getLocalizedMessage() != null)
                    {
                        msg.append("").append(lineSep).append(sqlEx.getLocalizedMessage());
                    }
                    else
                    {
                        msg.append("").append(lineSep).append(sqlEx.getMessage());
                    }
                }
            }
            return new SQLException(msg.toString());
        }
        return null;
    }

}
