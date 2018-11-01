/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

/**
 * Sybase Adaptive Server Anywhere table validator.
 * 
 * @author Idull
 */
public class SybaseASATableValidator extends SybaseASABaseTableValidator
{
    protected boolean validateReference(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;
        SybaseASATable table = (SybaseASATable) eObject;
        isValid &= super.validateReference(eObject, featureId, diagnostics, sharedParams);

        /**
         * Validates PCT option.
         */
        if (featureId == SybaseasasqlmodelPackage.SYBASE_ASA_TABLE__PCTFREE)
        {
            if (table.getPctfree() < -1 || table.getPctfree() > 100)
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.INFO, Integer
                    .toString(SybaseasasqlmodelPackage.SYBASE_ASA_TABLE__NAME), DATA_VALUE__VALUE_IN_RANGE,
                    NLS.bind(Messages.SybaseASATableValidator_invalid_pct_option, table.getName()), new Object[]
                {
                    SybaseasasqlmodelPackage.eINSTANCE.getSybaseASATable()
                }
                );
                diagnostics.add(d);
            }
        }
        return isValid;
    }
}
