/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design.util;

import java.util.Map;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DesignXMLProcessor extends XMLProcessor
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignXMLProcessor()
    {
        super( (EPackage.Registry.INSTANCE) );
        DesignPackage.eINSTANCE.eClass();
    }

    /**
     * Register for "*" and "xml" file extensions the DesignResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Map getRegistrations()
    {
        if( registrations == null )
        {
            super.getRegistrations();
            registrations.put( XML_EXTENSION, new DesignResourceFactoryImpl() );
            registrations.put( STAR_EXTENSION, new DesignResourceFactoryImpl() );
        }
        return registrations;
    }

} //DesignXMLProcessor
