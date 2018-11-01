/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DesignXMLProcessor.java,v 1.2 2007/11/17 05:30:20 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.util;

import java.util.Map;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
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
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

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
    @Override
    protected Map<String, Resource.Factory> getRegistrations()
    {
        if( registrations == null )
        {
            super.getRegistrations();
            registrations.put( XML_EXTENSION, new DesignResourceFactoryImpl() );
            registrations.put( STAR_EXTENSION, new DesignResourceFactoryImpl() );
        }
        return registrations;
    }

    /**
     * Creates and returns a new resource for saving or loading an ODA Design object.
     * @param uri   the URI of the resource to create
     * @return      a new resource
     * @since DTP 1.6
     * @generated NOT
     */
    public Resource createResource( URI uri )
    {
        ResourceSet resourceSet = createResourceSet();
        // Register the Design package to ensure it is available during loading.
        resourceSet.getPackageRegistry().put( DesignPackage.eNS_URI,
                DesignPackage.eINSTANCE );

        XMLResource resource = (XMLResource) resourceSet.createResource( uri );

        // Use the OPTION_SCHEMA_LOCATION_IMPLEMENTATION option to avoid pre-registration 
        // of the generated packages 
        resource.getDefaultSaveOptions()
                .put( XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION,
                        Boolean.TRUE );
        return resource;
    }

} //DesignXMLProcessor
