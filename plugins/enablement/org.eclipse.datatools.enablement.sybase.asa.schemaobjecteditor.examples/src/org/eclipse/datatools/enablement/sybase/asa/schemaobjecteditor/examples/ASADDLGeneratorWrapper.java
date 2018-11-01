/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.ISybaseDeltaDdlGenerator;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.DDLGeneratorWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;

public class ASADDLGeneratorWrapper extends DDLGeneratorWrapper
{
    public ASADDLGeneratorWrapper(DatabaseIdentifier identifier)
    {
        super(identifier);

        if (_ddlGen instanceof SybaseDdlGenerator)
        {
            ((SybaseDdlGenerator) _ddlGen).setParameter(_databaseIdentifier);
        }

        if (_deltaDdlGen instanceof SybaseDeltaDdlGeneration)
        {
            ((SybaseDeltaDdlGeneration) _deltaDdlGen).setParameter(_databaseIdentifier);
        }
    }

    public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, IProgressMonitor monitor)
    {
        _quotedIdentifier = SQLDevToolsUtil.isQuotedIdentifierOn(_databaseIdentifier);
        if (_deltaDdlGen == null)
        {
            return new String[]
            {};
        }
        else if (_deltaDdlGen instanceof ISybaseDeltaDdlGenerator)
        {
            ISybaseDeltaDdlGenerator sybDeltaDdlGen = (ISybaseDeltaDdlGenerator) _deltaDdlGen;
            EngineeringOption[] engOption = ((ISybaseDdlGenerator) _ddlGen).createGenerationOptions();
            engOption[SybaseDdlGenerator.GENERATE_QUOTED_IDENTIFIER_GEN_OPTION_INDEX].setBoolean(_quotedIdentifier);
            engOption[SybaseDdlGenerator.GENERATE_FULLY_QUALIFIED_NAME_GEN_OPTION_INDEX].setBoolean(_fullQualified);
            engOption[SybaseDdlGenerator.GENERATE_SETUSER_GEN_OPTION_INDEX].setBoolean(_setUser);

            return sybDeltaDdlGen.generateDeltaDDL(rootObject, changeDescription, engOption, monitor);
        }
        else
        {
            return _deltaDdlGen.generateDeltaDDL(rootObject, changeDescription, monitor);
        }
    }

    public SybaseDdlScript getDeltaDDLScript(EObject rootObject, ChangeDescription changeSummary,
            IProgressMonitor monitor, boolean fullQualified)
    {
        /**
         * Re-read the quoted_identifier option in case the user change it
         */
        _quotedIdentifier = SQLDevToolsUtil.isQuotedIdentifierOn(_databaseIdentifier);
        EngineeringOption[] options = null;
        if (_ddlGen instanceof SybaseDdlGenerator)
        {
            options = ((SybaseDdlGenerator) _ddlGen).createGenerationOptions();
            options[SybaseDdlGenerator.GENERATE_QUOTED_IDENTIFIER_GEN_OPTION_INDEX].setBoolean(_quotedIdentifier);
            options[SybaseDdlGenerator.GENERATE_FULLY_QUALIFIED_NAME_GEN_OPTION_INDEX].setBoolean(fullQualified);
            options[SybaseDdlGenerator.GENERATE_SETUSER_GEN_OPTION_INDEX].setBoolean(_setUser);
        }
        if (_deltaDdlGen instanceof SybaseDeltaDdlGeneration)
        {
            return ((SybaseDeltaDdlGeneration) _deltaDdlGen).generateDeltaDDLReturnScript(rootObject, changeSummary,
                    options, monitor);
        }

        return null;
    }
}
