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
package org.eclipse.datatools.sqltools.routineeditor.util;

import java.sql.DatabaseMetaData;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;

/**
 * @author Hui Cao
 * 
 */
public class RoutineUtil {

    /**
     * Constructs a sql string which can be used in a CallableStatement to invoke the Routine.
     * @param proc can be null
     * @param list must be valid value list
     * @param pds
     * @param quoted_id
     * @return
     */

    public static String constructCallableSPUDFString(ProcIdentifier proc, List values, ParameterDescriptor[] pds, boolean quoted_id)
    {
        StringBuffer buffer = new StringBuffer(20);
        buffer.append("{?=");
        int type = proc == null ? ProcIdentifier.TYPE_SP : proc.getType();
		SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByProfileName(proc.getDatabaseIdentifier().getProfileName());
        buffer.append(config.getExecutionService().getCallableStatementPrefix(proc.getType()));
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
            for (int i = 0; i < pds.length; i++)
            {
                String name = pds[i].getName();

                if (pds[i].getParmType() == DatabaseMetaData.procedureColumnIn
						|| pds[i].getParmType() == DatabaseMetaData.procedureColumnInOut
						|| pds[i].getParmType() == DatabaseMetaData.procedureColumnOut
						|| pds[i].getParmType() == DatabaseMetaData.procedureColumnUnknown) {
					if (name.equals(procName)) {
						continue;
					}
					if (j != 0) {
						buffer.append(",?"); //$NON-NLS-1$
					} else {
						buffer.append("?");
					}
					j++;
				}
            }
            if (type == ProcIdentifier.TYPE_UDF || type == ProcIdentifier.TYPE_SP)
            {
                buffer.append(")"); //$NON-NLS-1$
            }
        }
        else
        {
        	buffer.append("()");
        }
        buffer.append("}");
        return buffer.toString();
    }

    /**
	 * Constructs a sql string which can be used to show the detailed
	 * information about a CallableStatement to execute the given Routine. This
	 * is for display purpose only.
	 * 
	 * @param proc
	 *            can be null
	 * @param list
	 *            must be valid value list
	 * @param pds
	 * @param quoted_id
	 * @return
	 */

    public static String constructCallableSPUDFDisplayString(ProcIdentifier proc, List values, ParameterDescriptor[] pds, boolean quoted_id)
    {
        StringBuffer buffer = new StringBuffer(20);
        int type = proc == null ? ProcIdentifier.TYPE_SP : proc.getType();
		SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByProfileName(proc.getDatabaseIdentifier().getProfileName());
        buffer.append(config.getExecutionService().getCallableStatementPrefix(proc.getType()));
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

                //TODO MO
//                if (pds[i].getParmType() == ParameterMode.IN
//                		|| pds[i].getParmType() == ParameterMode.INOUT)
                if (pds[i].getParmType() == DatabaseMetaData.procedureColumnIn
                        || pds[i].getParmType() == DatabaseMetaData.procedureColumnInOut)
                {
                    String value =null;
                    if (values!=null && values.size()>=(k+1))
                    {
                        value = (String) values.get(k++); 
                    }
                    if (name.equals(procName))
                    {
                        continue;
                    }
                    if (value!= null && pds[i].isStringType() && SQLUtil.findQuotes(value) == SQLUtil.NO_QUOTES)
                    {
                        value = SQLUtil.quote(value,'\'');
                    }
                    if(j != 0)
                    {
                        prefix = ",";
                    }
                    else
                    {
                        prefix = "";
                    }
                    buffer
                        .append(prefix + LINESEPARATOR + "     " + pds[i].getName() + "=" + value + " " + pds[i].getTypeName() + " " + pds[i].getParamTypeAsString()); //$NON-NLS-1$

                    j++;
                }
                //TODO MO
//                if (pds[i].getParmType() == ParameterMode.OUT)
                if (pds[i].getParmType() == DatabaseMetaData.procedureColumnOut
                        || pds[i].getParmType() == DatabaseMetaData.procedureColumnUnknown)
                {
                    if (name.equals(procName))
                    {
                        continue;
                    }

                    String paramType = "OUT"; //pds[i].getParamTypeAsString();//FIXME it get "UNKNOWN",should be "OUT"

                    if(j != 0)
                    {
                        prefix = ",";
                    }
                    else
                    {
                        prefix = "";
                    }
                    buffer
                        .append(prefix + LINESEPARATOR + "     " + pds[i].getName() + " " + pds[i].getTypeName() + " " + paramType); //$NON-NLS-1$

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
     * Constructs a sql string to directly invoke the Routine.
     * @param proc can be null
     * @param list must be valid value list
     * @param pds
     * @param quoted_id
     * @return
     */
    public static String constructSPUDFDirectInvocationString(ProcIdentifier proc, List values, ParameterDescriptor[] pds, boolean quoted_id)
    {
        StringBuffer buffer = new StringBuffer(20);
        int type = proc == null ? ProcIdentifier.TYPE_SP : proc.getType();
		SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByProfileName(proc.getDatabaseIdentifier().getProfileName());
        buffer.append(config.getExecutionService().getDirectInvocationPrefix(proc.getType()));

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
                    buffer.append(SQLUtil.quote((String) values.get(i), '\''));
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
    /**
     * Constructs a sql string to directly invoke the Event object.
     * @param proc can be null
     * @param quoted_id
     * @param triggerParams
     * @return
     */
    public static String constructTriggerEventString(ProcIdentifier proc, Map values, boolean quoted_id)
    {
        //For events we should not supply the dbname or owner name
        StringBuffer sb = new StringBuffer("TRIGGER EVENT "); //$NON-NLS-1$
        if (proc != null)
        {
            if (quoted_id == true)
            {
                String quotedName = SQLDevToolsUtil.quoteWhenNecessary(proc.getProcName(), proc.getDatabaseIdentifier(), "\"", IIdentifierValidator.IDENTIFIER_TYPE_EVENT);
                sb.append(quotedName);
            }
            else
            {
                sb.append(proc.getProcName());
            }
        }
        if (values != null && values.size() > 0)
        {
            sb.append("("); //$NON-NLS-1$
            for (Iterator iter = values.keySet().iterator(); iter.hasNext();)
            {
                String name = (String) iter.next();
                String value =(String) values.get(name);
                String quotedName = SQLDevToolsUtil.quoteWhenNecessary(name, proc.getDatabaseIdentifier(), "\"", IIdentifierValidator.IDENTIFIER_TYPE_PARAMETER);
                sb.append(quotedName).append("=").append(SQLUtil.quote(value, '\'')).append(",");
            }

            sb.deleteCharAt(sb.length() - 1); // remove the last ","
            sb.append(")"); //$NON-NLS-1$
        }
        return sb.toString();
    }

    /**
     * Return whether objstr and proc represent the same database object.
     * If db name or owner name is omitted int objstr, defDB or defOwner will be used.
     * @param objstr
     * @param proc
     * @return
     */
    public static boolean equals(int parserType, int procType, String objstr, String defDB, String defOwner, ProcIdentifier proc, boolean caseSensitive)
    {
        if (!isSameType(parserType, procType))
        {
            return false;
        }
        String[] tokens = SQLUtil.parseDatabaseObject(objstr);
        int count = tokens.length;

        if (count == 0 || count > 3)
        {
            return false;
        }

        //user can't use owner name for event definition, so don't check it
        if (parserType == SQLParserConstants.TYPE_SQL_ALTER_EVENT || parserType == SQLParserConstants.TYPE_SQL_CREATE_EVENT)
        {
            defOwner = proc.getOwnerName();
        }

        String db = count == 3? tokens[2]:defDB;
        String owner = count >= 2? tokens[1]:proc.getOwnerName();
        String objname = count >= 1? tokens[0]:objstr;

        return SQLUtil.equalsIgnoreQuote(proc.getDatabaseIdentifier().getDBname(), db, caseSensitive)
            && SQLUtil.equalsIgnoreQuote(proc.getOwnerName(), owner, caseSensitive) && SQLUtil.equalsIgnoreQuote(proc.getProcName(), objname, caseSensitive);
    }


    /**
     * Return whether the object type represented by parserType and that by procType are of the same type 
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

    
}
