/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;


/**
 * Validator for ASA Base UDT
 * 
 * @author Hao-Yue
 */
public class SybaseASABaseUserDefinedTypeValidator extends DefaultSQLModelValidator
{
    protected boolean validateAttribute(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        SybaseASABaseUserDefinedType udt = (SybaseASABaseUserDefinedType) eObject;
        boolean valid = true;
        valid &= super.validateAttribute(eObject, featureId, diagnostics, sharedParams);
        if (featureId == SybaseasabasesqlmodelPackageImpl.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NAME)
        {
            DatabaseIdentifier databaseIdentifier = (DatabaseIdentifier) sharedParams
                    .get(ValidatorConstants.DATABASE_IDENTIFIER);
            IIdentifierValidator identifierValidator = SQLToolsFacade.getConfiguration(databaseIdentifier, null).getSQLService().getIdentifierValidator();

            if (udt.getName() == null || udt.getName().trim().equals("")) //$NON-NLS-1$
            {
                valid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.INFO, Integer
                        .toString(SybaseasabasesqlmodelPackageImpl.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NAME),
                        EMPTY_IDENTIFIER, Messages.Udt_No_Name_Present, new Object[]
                        {
                            SybaseasabasesqlmodelPackageImpl.eINSTANCE.getSybaseASABaseUserDefinedType()
                        });
                diagnostics.add(d);
            }
            else if (databaseIdentifier != null)
            {
                ValidatorMessage msg = identifierValidator.isValid(SQLUtil.quoteWhenNecessary(udt.getName(),
                        databaseIdentifier), IIdentifierValidator.IDENTIFIER_TYPE_UNKNOW, databaseIdentifier);
                if (msg != null && msg.getType() != ValidatorMessage.NO_ERROR)
                {
                    // check name's validity
                    valid = false;
                    Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                            .toString(SybaseasabasesqlmodelPackageImpl.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NAME),
                            INVALID_IDENTIFIER, NLS.bind(Messages.Udt_for_udt, udt.getName()) + msg.getMessage(), new Object[] //$NON-NLS-1$
                            {
                                SybaseasabasesqlmodelPackageImpl.eINSTANCE.getSybaseASABaseUserDefinedType()
                            });
                    diagnostics.add(d);
                }
                else
                {
                    if (_currentItemContext.get(ValidatorConstants.VALIDATE_DUPLICATE_NAME_VIA_DB) != null)
                    {
                        ConnectionService connServ = SQLToolsFacade.getConnectionService((databaseIdentifier));
                        Connection conn = null;
                        PreparedStatement newSt = null;
                        ResultSet rs = null;
                        try
                        {
                            conn = connServ.createConnection(databaseIdentifier, true);

                            StringBuffer sql = new StringBuffer("");
                            sql.append("select * from sysusertype where type_name = ?");

                            newSt = conn.prepareStatement(sql.toString());
                            newSt.setString(1, udt.getName());
                            rs = newSt.executeQuery();
                            if (rs.next())
                            {
                                valid = false;
                                Diagnostic d = new BasicDiagnostic(
                                        Diagnostic.ERROR,
                                        Integer
                                                .toString(SybaseasabasesqlmodelPackageImpl.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NAME),
                                        0, NLS.bind(Messages.Udt_Duplicate_Name_Error, udt.getName()), new Object[]
                                        {});
                                diagnostics.add(d);
                            }

                        }
                        catch (Exception e)
                        {
                            valid = false;
                            Diagnostic d = new BasicDiagnostic(
                                    Diagnostic.ERROR,
                                    Integer
                                            .toString(SybaseasabasesqlmodelPackageImpl.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NAME),
                                    0, Messages.Database_Error, new Object[]
                                    {});
                            diagnostics.add(d);
                        }
                        finally
                        {
                            try
                            {
                                if (rs != null)
                                {
                                    rs.close();
                                }
                                if (newSt != null)
                                {
                                    newSt.close();
                                }
                            }
                            catch (Exception e)
                            {
                            	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, e.getMessage()));
                            }

                            try
                            {
                                connServ.closeConnection(conn,
                                        SQLToolsFacade.getConnectionId(databaseIdentifier, conn), databaseIdentifier);
                            }
                            catch (Exception e)
                            {
                            	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, e.getMessage()));
                            }
                        }
                    }
                }
            }
        }

        if (featureId == SybaseasabasesqlmodelPackageImpl.SYBASE_ASA_BASE_USER_DEFINED_TYPE__CONSTRAINT)
        {
            if (_currentItemContext.get(ValidatorConstants.VALIDATE_DEFAULT_VALUE_VIA_DB) != null)
            {
                EList constraints = udt.getConstraint();
                if (constraints != null && constraints.size() > 0)
                {
                    CheckConstraint checkConstraint = (CheckConstraint) constraints.get(0);
                    String constraint = checkConstraint.getSearchCondition().getSQL().trim().toLowerCase();
                    // check constraint's validity
                    if (!constraint.equals("") && (!constraint.startsWith("check(") || !constraint.endsWith(")")))
                    {
                        valid = false;
                        Diagnostic d = new BasicDiagnostic(
                                Diagnostic.ERROR,
                                Integer
                                        .toString(SybaseasabasesqlmodelPackageImpl.SYBASE_ASA_BASE_USER_DEFINED_TYPE__CONSTRAINT),
                                0, Messages.Udt_Constraint_Definition_Error, new Object[]
                                {});
                        diagnostics.add(d);
                    }
                    else if (!constraint.equals(""))
                    {
                        if (constraint.substring("check(".length(), constraint.lastIndexOf(")")).trim().equals(""))
                        {
                            valid = false;
                            Diagnostic d = new BasicDiagnostic(
                                    Diagnostic.INFO,
                                    Integer
                                            .toString(SybaseasabasesqlmodelPackageImpl.SYBASE_ASA_BASE_USER_DEFINED_TYPE__CONSTRAINT),
                                    EMPTY_IDENTIFIER, Messages.Udt_Constraint_Definition_Info, new Object[]
                                    {
                                        SybaseasabasesqlmodelPackageImpl.eINSTANCE.getSybaseASABaseUserDefinedType()
                                    });
                            diagnostics.add(d);
                        }
                    }
                }
            }
        }

        return valid;
    }
}
