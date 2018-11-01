/*
 *************************************************************************
 * Copyright (c) 2006, 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
                                    Map<Object, Object> context )
    {        
        // use extended validator method for selected ODA Design classes
        if( isDataSetQueryObject( eObject ) )
        {
            return validate_DataSetQuery_MultiplicityConforms( eObject, eFeature, 
                    diagnostics, context );
        }
        else if( isDataSetDesignObject( eObject ) )
        {
            return validate_DataSetDesign_MultiplicityConforms( eObject, eFeature, 
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
        return ( eObject.eClass() == DesignPackage.Literals.DATA_SET_QUERY );
    }
    
    /**
     * Determines whether the specific EObject is a DataSetDesign type.
     */
    private boolean isDataSetDesignObject( EObject eObject )
    {
        return ( eObject.eClass() == DesignPackage.Literals.DATA_SET_DESIGN );
    }
   
    /**
     * Specialized validator for the DataSetQuery EObject.
     */
    protected boolean validate_DataSetQuery_MultiplicityConforms( EObject eObject, 
                                    EStructuralFeature eFeature, 
                                    DiagnosticChain diagnostics, 
                                    Map<Object, Object> context )
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
                    newRequiredFeatureDiagnostic( Diagnostic.WARNING, eObject, eFeature, context ));
            }
            return true;    // ok to continue with validation
        }
        
        // use default validator implementation
        return super.validate_MultiplicityConforms( eObject, eFeature, 
                                        diagnostics, context );
    }
    
    /**
     * Specialized validator for the DataSetDesign EObject.
     */
    protected boolean validate_DataSetDesign_MultiplicityConforms( EObject eObject, 
                                    EStructuralFeature eFeature, 
                                    DiagnosticChain diagnostics, 
                                    Map<Object, Object> context )
    {
        // validating the DataSourceDesign attribute in a DataSetDesign object
        if( isDataSetDesignObject( eObject ) && 
            eFeature.getFeatureID() == DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN )
        {
            if( eObject.eIsSet( eFeature ) )
                return true;    // is valid; optional attribute is set
            
            // a DataSourceDesign is optional only when its containing DataSetDesign 
            // is contained by a DynamicValuesQuery
            if( eObject.eContainer().eClass() == DesignPackage.Literals.DYNAMIC_VALUES_QUERY )
                return true;

            // required attribute is not set
            if( diagnostics != null )
            {
                diagnostics.add(
                    newRequiredFeatureDiagnostic( Diagnostic.ERROR, eObject, eFeature, context ));
            }
            return false;   
        }
        
        // use default validator implementation for other attributes/features
        return super.validate_MultiplicityConforms( eObject, eFeature, 
                                        diagnostics, context );        
    }
    
    private BasicDiagnostic newRequiredFeatureDiagnostic( int severity, 
                EObject eObject, EStructuralFeature eFeature, Map<Object, Object> context)
    {
        return new BasicDiagnostic( severity,
                    DIAGNOSTIC_SOURCE,
                    EOBJECT__EVERY_MULTIPCITY_CONFORMS,
                    getEcoreResourceLocator().getString(
                        "_UI_RequiredFeatureMustBeSet_diagnostic", //$NON-NLS-1$
                        new Object[]{ getFeatureLabel( eFeature, context), 
                                        getObjectLabel( eObject, context )} ),
                    new Object[] { eObject, eFeature } );        
    }
    
}
