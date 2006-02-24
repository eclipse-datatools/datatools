/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;

/**
 * @author Hui Cao
 * 
 */
public class RoutineUtil {

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

    public static String constructCALLSPUDFString(ProcIdentifier proc, List values, ParameterDescriptor[] pds, boolean quoted_id)
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

                if (name.equals(procName))
                {
                    continue;
                }
                if (j != 0)
                {
                    buffer.append(",?"); //$NON-NLS-1$
                }
                else
                {
                    buffer.append("?");
                }
                j++;
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
     * @param proc can be null
     * @param list must be valid value list
     * @param pds
     * @param quoted_id
     * @return
     */

    public static String constructDetailCALLSPUDFString(ProcIdentifier proc, List values, ParameterDescriptor[] pds, boolean quoted_id)
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

                if (pds[i].getParmType() == ParameterMode.IN
                || pds[i].getParmType() == ParameterMode.INOUT)
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
                if (pds[i].getParmType() == ParameterMode.OUT)
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
     * @param proc can be null
     * @param list must be valid value list
     * @param pds
     * @param quoted_id
     * @return
     */
    public static String constructSPUDFString(ProcIdentifier proc, List values, ParameterDescriptor[] pds, boolean quoted_id)
    {
        StringBuffer buffer = new StringBuffer(20);
        int type = proc == null ? ProcIdentifier.TYPE_SP : proc.getType();
		SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByProfileName(proc.getDatabaseIdentifier().getProfileName());
        buffer.append(config.getExecutionService().getCallableStatementPrefix(proc.getType()));

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
                sb.append(SQLUtil.quote(proc.getProcName(), '"'));
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
                sb.append("\""+name+"\"").append("=").append("\'"+value+"\'").append(",");
            }

            sb.deleteCharAt(sb.length() - 1); // remove the last ","
            sb.append(")"); //$NON-NLS-1$
        }
        return sb.toString();
    }


}
