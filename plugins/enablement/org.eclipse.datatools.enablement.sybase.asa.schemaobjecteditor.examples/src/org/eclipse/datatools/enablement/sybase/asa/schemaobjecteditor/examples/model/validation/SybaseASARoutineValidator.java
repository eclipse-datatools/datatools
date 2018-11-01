/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.impl.RoutineResultTableImpl;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;


/**
 * Validator for Sybase ASE Procedure
 * 
 * @author Hui Cao
 */
public class SybaseASARoutineValidator extends DefaultSQLModelValidator
{
    protected boolean validateAttribute(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;

        SybaseRoutine proc = (SybaseRoutine) eObject;

        /**
         * Validates the name.<br>
         * Check if the name is null or empty, if not, check if it's a valid identifier, if it's valid, check whether
         * it's duplicated in the database if the <code>VALIDATE_DUPLICATE_NAME_VIA_DB</code> parameter is specified.
         * 
         */
        if (featureId == SQLSchemaPackage.SQL_OBJECT__NAME)
        {
            DatabaseIdentifier databaseIdentifier = (DatabaseIdentifier) sharedParams
                    .get(ValidatorConstants.DATABASE_IDENTIFIER);
            SQLDevToolsConfiguration conf = SQLToolsFacade.getConfiguration(databaseIdentifier, null);
            IIdentifierValidator identifierValidator = conf.getSQLService().getIdentifierValidator();

            if (proc.getName() == null || proc.getName().trim().equals("")) //$NON-NLS-1$
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                        .toString(SQLSchemaPackage.SQL_OBJECT__NAME), EMPTY_IDENTIFIER,
                        Messages.SybaseRoutineValidator_No_name_present, new Object[]
                        {
                            proc
                        });
                diagnostics.add(d);
            }
            else if (databaseIdentifier != null)
            {
                String quotedName = SQLUtil.quoteWhenNecessary(proc.getName(), databaseIdentifier);
                ValidatorMessage msg = identifierValidator.isValid(quotedName, IIdentifierValidator.IDENTIFIER_TYPE_SP,
                        databaseIdentifier);
                if (msg != null && msg.getType() != ValidatorMessage.NO_ERROR)
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(getDiagnosticCode(msg.getType()), Integer
                            .toString(SQLSchemaPackage.SQL_OBJECT__NAME), INVALID_IDENTIFIER, NLS.bind(
                            Messages.SybaseRoutineValidator_for_routine, proc.getName())
                            + msg.getMessage(), new Object[]
                            {
                                proc
                            });
                    diagnostics.add(d);
                }
                else
                {
                    if (_currentItemContext.get(ValidatorConstants.VALIDATE_DUPLICATE_NAME_VIA_DB) != null)
                    {

                        Statement newSt = null;
                        Connection conn = null;
                        ResultSet rs = null;
                        ConnectionService connServ = SQLToolsFacade.getConnectionService((databaseIdentifier));
                        try
                        {
                            conn = connServ.createConnection(databaseIdentifier, true);

                            StringBuffer sql = new StringBuffer("");

                            sql.append("select * from sysprocedure where proc_name = ").append(
                                    SQLUtil.quote(proc.getName(), "'")).append(
                                    " and creator = (select user_id from sysuserperm where user_name = ").append(
                                    SQLUtil.quote(proc.getSchema().getName(), "'")).append(")");

                            newSt = conn.createStatement();
                            rs = newSt.executeQuery(sql.toString());
                            if (rs.next())
                            {
                                isValid = false;
                                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                                        .toString(SQLSchemaPackage.SQL_OBJECT__NAME), DUPLICATE_IDENTIFIER,
                                        NLS.bind(Messages.Routine_Duplicate_Name_Error, proc.getName()), new Object[]
                                        {
                                            proc
                                        });
                                diagnostics.add(d);
                            }

                        }
                        catch (Exception e)
                        {
                        	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, Messages.Database_Error));
                        }
                        finally
                        {
                            try
                            {
                                rs.close();
                            }
                            catch (Exception e)
                            {
                                // don't need to handle
                            }

                            try
                            {
                                newSt.close();
                            }
                            catch (Exception e)
                            {
                                // don't need to handle
                            }

                            try
                            {
                                connServ.closeConnection(conn,
                                        SQLToolsFacade.getConnectionId(databaseIdentifier, conn), databaseIdentifier);
                            }
                            catch (Exception e)
                            {
                                // don't need to handle
                            }
                        }

                    }
                }
            }
        }
        return isValid;
    }

    protected boolean validateReference(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;

        Routine proc = (Routine) eObject;

        if (featureId == SQLRoutinesPackage.ROUTINE__PARAMETERS)
        {
            if (proc.getParameters().size() == 0)
            {
                return true;
            }

            if (_depth > 0)
            {
                /**
                 * If depth is greater than 0,should perform validity check for each parameter.
                 */
                SQLModelValidator paramValidator = new SybaseASABaseParameterValidator();
                if (paramValidator != null && _containmentItem != null)
                {
                    Iterator iter = proc.getParameters().iterator();
                    while (iter.hasNext())
                    {
                        SybaseParameter param = (SybaseParameter) iter.next();
                        // Since the list of Parameter contain multiplicate Parameter, VARIABLE, RESULT etc, Please
                        // refer
                        // org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType,
                        // and there is a validator for Result parameter. So don't validate Result parameter at here.
                        if (param instanceof SybaseASABaseParameter)
                        {
                            SybaseASABaseParameter asaParam = (SybaseASABaseParameter) param;
                            if (asaParam.getParmType() != null
                                    && asaParam.getParmType().getValue() == ParameterType.RESULT)
                            {
                                continue;
                            }
                        }
                        _containmentItem.getContext().put(SQLModelValidationDelegate.VALIDATIOR_KEY, paramValidator);
                        isValid &= SQLModelValidationDelegate.getInstance().validate(param, diagnostics,
                                _containmentItem.getContext(), sharedParams);
                    }
                }
            }

            // check duplicate name
            List list = proc.getParameters();
            String[] names = new String[list.size()];
            int i = 0;
            for (Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                SybaseParameter param = (SybaseParameter) iterator.next();
                // Since the list of Parameter contain multiplicate Parameter, VARIABLE, RESULT etc, Please refer
                // org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType,
                // and there is a validator for Result parameter. So don't validate Result parameter at here.
                if (param instanceof SybaseASABaseParameter)
                {
                    SybaseASABaseParameter asaParam = (SybaseASABaseParameter) param;
                    if (asaParam.getParmType() != null && asaParam.getParmType().getValue() == ParameterType.RESULT)
                    {
                        continue;
                    }
                }
                names[i++] = param.getName();
            }

            Diagnostic diagnostic = checkDuplicateName(names, String.valueOf(SQLRoutinesPackage.ROUTINE__PARAMETERS),
                    Messages.Duplicate_Parameter_Name_Error, new Object[]
                    {
                        proc
                    });
            if (diagnostic != null)
            {
                isValid = false;
                diagnostics.add(diagnostic);
            }
        }
        else if (featureId == SQLRoutinesPackage.PROCEDURE__RESULT_SET && proc instanceof Procedure)
        {
            Procedure procedure = (Procedure) proc;
            if (procedure.getResultSet().size() == 0)
            {
                return true;
            }

//            if (_depth > 0)
//            {
//                SQLModelValidator validator = new SybaseASAProcedureResultValidator();
//                if (validator != null && _containmentItem != null)
//                {
//                    List resultSet = procedure.getResultSet();
//
//                    for (Iterator iterator = resultSet.iterator(); iterator.hasNext();)
//                    {
//                        EObject eobj = (EObject) iterator.next();
//                        _containmentItem.getContext().put(SQLModelValidationDelegate.VALIDATIOR_KEY, validator);
//                        isValid &= SQLModelValidationDelegate.getInstance().validate(eobj, diagnostics,
//                                _containmentItem.getContext(), sharedParams);
//                    }
//                }
//            }

            List resultSet = procedure.getResultSet();

            for (Iterator iterator = resultSet.iterator(); iterator.hasNext();)
            {
                RoutineResultTableImpl resultTable = (RoutineResultTableImpl) iterator.next();
                List list = resultTable.getColumns();
                String[] names = new String[list.size()];
                int i = 0;
                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                    ColumnImpl column = (ColumnImpl) iter.next();
                    names[i++] = column.getName();
                }
                Diagnostic diagnostic = checkDuplicateName(names, String
                        .valueOf(SQLRoutinesPackage.PROCEDURE__RESULT_SET), Messages.Duplicate_Result_Name_Error,
                        new Object[]
                        {
                            resultTable
                        });
                if (diagnostic != null)
                {
                    isValid &= false;
                    diagnostics.add(diagnostic);
                }
            }

        }
        return isValid;
    }

    /**
     * Check if there is duplicated name in the Array of names
     * 
     * @param names all named need to be checked
     * @param diagnosticSource the source of Diagnostic
     * @param diagnosticMessages the message of Diagnostic. The duplicated name will be appended on the message.
     * @param diagnosticDatas the datas of Diagnostic
     * @return null if there is no duplicated name
     */
    public static Diagnostic checkDuplicateName(String[] names, String diagnosticSource, String diagnosticMessages,
            Object[] diagnosticDatas)
    {
        Diagnostic diagnostic = null;
        String duplicateName = "";
        for (int i = 0; i < names.length; i++)
        {
            boolean isDuplicate = false;
            String cname = names[i];
            for (int j = i + 1; j < names.length; j++)
            {
                if (j == names.length)
                {
                    break;
                }

                if (cname != null && names[j] != null
                        && !cname.trim().equals("") && cname.toLowerCase().equals(names[j].toLowerCase())) //$NON-NLS-1$
                {
                    duplicateName = cname;
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate)
            {

                diagnostic = new BasicDiagnostic(Diagnostic.ERROR, diagnosticSource, DUPLICATE_IDENTIFIER,
                        diagnosticMessages + duplicateName, diagnosticDatas);
                break;
            }
        }
        return diagnostic;
    }
}
