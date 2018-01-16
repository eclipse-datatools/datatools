/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;


/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseASABaseParameterValidator extends SybaseParameterValidator
{

    public SybaseASABaseParameterValidator()
    {
        // TODO Auto-generated constructor stub
    }

    
    protected boolean validateAttribute(EObject object, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        SybaseASABaseParameter param = (SybaseASABaseParameter) object;
        int paramType = param.getParmType().getValue();
        if (paramType == ParameterType.SQLCODE || paramType == ParameterType.SQLSTATE || paramType == ParameterType.RESULT)
        {
            return true;
        }
            
        return super.validateAttribute(object, featureId, diagnostics, sharedParams);
    }
    
    
    protected boolean validateReference(EObject object, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        SybaseASABaseParameter param = (SybaseASABaseParameter) object;
        int paramType = param.getParmType().getValue();
        if (paramType == ParameterType.SQLCODE || paramType == ParameterType.SQLSTATE || paramType == ParameterType.RESULT)
        {
            return true;
        }
        //TODO validate the feasible return datatypes
        return super.validateReference(object, featureId, diagnostics, sharedParams);
    }
}
