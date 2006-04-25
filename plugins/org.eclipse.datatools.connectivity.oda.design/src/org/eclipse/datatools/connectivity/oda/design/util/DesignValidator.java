/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.util;

import java.util.Map;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * Extended validator of ODA Design EMF Objects.
 */
class DesignValidator extends EObjectValidator
{
    DesignValidator()
    {
        super();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.util.EObjectValidator#validate_MultiplicityConforms(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
     */
    protected boolean validate_MultiplicityConforms( EObject eObject, 
                                    EStructuralFeature eFeature, 
                                    DiagnosticChain diagnostics, 
                                    Map context )
    {        
        // use extended validator method for selected ODA Design classes
        if( isDataSetQueryObject( eObject ) )
        {
            return validate_DataSetQuery_MultiplicityConforms( eObject, eFeature, 
                    diagnostics, context );
        }
        
        // use default validator
        return super.validate_MultiplicityConforms( eObject, eFeature, 
                                                    diagnostics, context );
    }

    /**
     * Determines whether the specific EObject is a DataSetQuery type.
     */
    private boolean isDataSetQueryObject( EObject eObject )
    {
        return ( eObject.eClass() == DesignPackage.eINSTANCE.getDataSetQuery() );
    }
    
    /**
     * Specialized validator for the DataSetQuery EObject.
     */
    protected boolean validate_DataSetQuery_MultiplicityConforms( EObject eObject, 
                                    EStructuralFeature eFeature, 
                                    DiagnosticChain diagnostics, 
                                    Map context )
    {
        if( isDataSetQueryObject( eObject ) &&
            eFeature.isRequired() )
        {
            if( eObject.eIsSet( eFeature ) )
                return true;    // is valid; required attribute is set

            // if the required attribute is not set, add a warning diagnostic
            if( diagnostics != null )
            {
                diagnostics.add(
                    new BasicDiagnostic( Diagnostic.WARNING,
                            DIAGNOSTIC_SOURCE,
                            EOBJECT__EVERY_MULTIPCITY_CONFORMS,
                            getEcoreResourceLocator().getString(
                                "_UI_RequiredFeatureMustBeSet_diagnostic",
                                new Object[] { getFeatureLabel( eFeature, context), 
                                                getObjectLabel( eObject, context ) } ),
                            new Object[] { eObject, eFeature } ));
            }
            return true;    // ok to continue with validation
        }
        
        // use default validator implementation
        return super.validate_MultiplicityConforms( eObject, eFeature, 
                                        diagnostics, context );
    }
    
}
