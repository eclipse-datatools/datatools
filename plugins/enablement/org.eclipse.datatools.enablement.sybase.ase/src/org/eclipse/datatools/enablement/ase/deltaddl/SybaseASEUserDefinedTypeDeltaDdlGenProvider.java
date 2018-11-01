/*******************************************************************************
 * Copyright (c) 2007-8 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

public class SybaseASEUserDefinedTypeDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements
        IDeltaDdlGenProvider, ISybaseASEDdlConstants
{
    /**
     * Processes the alter statement on UserDefinedType.
     * <p>
     * 
     * @param element
     * @param modifyRecordMap
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @param script
     * @param monitor
     * 
     * @author renj
     */
    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        List list = (List) modifyRecordMap.get(element);
        if (list != null && list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list
                        .get(i);
                // guarantee the rename statement should be the first script
                if (cr.feature == EcorePackage.eINSTANCE.getENamedElement_Name())
                {
                    list.remove(i);
                    list.add(0, cr);
                }
            }
        }
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                StringBuffer sb = new StringBuffer(128);
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list
                        .get(i);

                int crFeatureID = cr.feature.getFeatureID();
                if (crFeatureID == SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT)
                {
                    SybaseASEUserDefinedType UDT = (SybaseASEUserDefinedType) element;
                    if (cr.oldValue != null)
                    {
                        sb.append(EXEC).append(SPACE).append(SP_UNBINDEFAULT).append(SPACE).append(
                                getName(UDT.getName(),quoteIdentifiers));
                        script.addAlterOtherStatements(sb.toString());
                    }
                    if (cr.newValue != null)
                    {
                        sb.delete(0, sb.length());
                        sb.append(EXEC).append(SPACE).append(SP_BINDEFAULT).append(SPACE).append(
                                getName((SybaseASEDefault) cr.newValue, quoteIdentifiers, true)).append(COMMA).append(
                                        getName(UDT.getName(),quoteIdentifiers));
                        for (int j = 0; j < list.size(); j++)
                        {
                            SybaseDeltaDdlGeneration.FeatureChangeRecord cr2 = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list
                                    .get(j);
                            if (cr2.feature.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY)
                            {
                                sb.append(COMMA).append(FUTUREONLY);
                                continue;
                            }
                        }
                        script.addAlterOtherStatements(sb.toString());
                    }
                }

                if (crFeatureID == SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE)
                {
                    SybaseASEUserDefinedType UDT = (SybaseASEUserDefinedType) element;
                    if (cr.oldValue != null)
                    {
                        sb.append(EXEC).append(SPACE).append(SP_UNBINDRULE).append(SPACE).append(
                                getName(UDT.getName(), quoteIdentifiers)).append(COMMA).append(NULL)
                                .append(COMMA).append(SINGLE_QUOTE).append(ALL).append(SINGLE_QUOTE);
                        script.addAlterOtherStatements(sb.toString());
                    }
                    if (cr.newValue != null)
                    {
                        sb.delete(0, sb.length());
                        sb.append(EXEC).append(SPACE).append(SP_BINDRULE).append(SPACE).append(
                                getName((SybaseASERule) cr.newValue, quoteIdentifiers, true)).append(COMMA).append(
                                        getName(UDT.getName(), quoteIdentifiers));
                        for (int j = 0; j < list.size(); j++)
                        {
                            SybaseDeltaDdlGeneration.FeatureChangeRecord cr2 = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list
                                    .get(j);
                            if (cr2.feature.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY)
                            {
                                sb.append(COMMA).append(FUTUREONLY);
                                continue;
                            }
                        }
                        script.addAlterOtherStatements(sb.toString());
                    }
                }

                if (crFeatureID == EcorePackage.EATTRIBUTE__NAME)
                {
                    sb.append(EXEC).append(SPACE).append(SP_RENAME).append(SPACE);
                    if (quoteIdentifiers)
                    {
                        sb.append(getName((String) cr.oldValue, quoteIdentifiers)).append(COMMA).append(SPACE).append(
                                getName((String) cr.newValue, quoteIdentifiers));
                    }
                    else
                    {
                        sb.append(SQLUtil.quote((String) cr.oldValue, SINGLE_QUOTE)).append(COMMA).append(SPACE)
                                .append(SQLUtil.quote((String) cr.newValue, SINGLE_QUOTE));
                    }
                    script.addAlterOtherStatements(sb.toString());
                }
            }
        }
    }

    /**
     * Gets the modification result.
     * <p>
     * 
     * @param e
     * @param feature
     * @param oldValue
     * @param newValue
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @param script
     * 
     * @author renj
     */
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
    }

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        // TODO Auto-generated method stub

    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        // TODO Auto-generated method stub

    }

    protected String getName(String name, boolean quoteIdentifiers)
    {
        String Name = name;

        if (quoteIdentifiers)
        {
            Name = SQLDevToolsUtil.quoteWhenNecessary(name, (DatabaseIdentifier)getParameter());
        }
        else
        {
            Name = SQLUtil.quote(name, SINGLE_QUOTE);
        }

        return Name;
    }

    protected String getName(SQLObject eobject, boolean quoteIdentifiers, boolean qualifyNames)
    {
        if (eobject instanceof SybaseASEDefault || eobject instanceof SybaseASERule)
        {
            String name = eobject.getName();
            String schemaName = "";
            if (eobject instanceof SybaseASEDefault)
            {
                schemaName = ((SybaseASEDefault) eobject).getSchema().getName();
            }
            if (eobject instanceof SybaseASERule)
            {
                schemaName = ((SybaseASERule) eobject).getSchema().getName();
            }

            if (quoteIdentifiers)
            {
                name = this.getDoubleQuotedString(name);
                schemaName = this.getDoubleQuotedString(schemaName);
            }
            if (qualifyNames)
            {
                name = schemaName + DOT + name;
            }

            name = SINGLE_QUOTE + name + SINGLE_QUOTE;

            return name;
        }

        return "";
    }
}
