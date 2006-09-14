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

import java.sql.DatabaseMetaData;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.routineeditor.ProcEditorInput;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.ITextEditor;

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

    /**
	 * Closes all the editors which are editing the procedural object identified
	 * by the given ProcIdentifier. We will need to close all those editors for
	 * the same procedural objects that are opened by different profiles. For example
	 * a stored proc "myProc" can be opened by profile1 & profile2, we should
	 * close both the editor window when user drops "myProc".
	 * During the close process, the editor won't be saved.
	 * 
	 * @param databaseIdentifier
	 * @param dbObjectName
	 * @param dbObjectType
	 */
    public static void closeEditor(ProcIdentifier proc)
    {
        IEditorReference[] ht = SQLEditorPlugin.getActiveWorkbenchPage().getEditorReferences();
        
        if (ht == null || ht.length == 0)
        {
            return;
        }
        for (int i = 0; i < ht.length; i++) {
        	IEditorReference ref = ht[i];
        	IEditorInput input = null;
			try {
				input = ref.getEditorInput();
			} catch (PartInitException e) {
				SQLEditorPlugin.getDefault().log(e);
			}
            if (input instanceof ProcEditorInput)
            {
            	ProcEditorInput sqlEditorInput = (ProcEditorInput) input;
                if (sqlEditorInput.getProcIdentifier().equalsByServer(proc))
                {
                	ITextEditor editor = (ITextEditor)ref.getEditor(false);
                	if (editor != null)
                	{
                		editor.close(false);
                	}
                }
            }
        }
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
