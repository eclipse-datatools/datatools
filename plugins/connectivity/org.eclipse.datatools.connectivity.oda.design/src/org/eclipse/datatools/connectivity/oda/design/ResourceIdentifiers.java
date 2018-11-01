/**
 *************************************************************************
 * Copyright (c) 2008, 2009 Actuate Corporation.
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
 * $Id: ResourceIdentifiers.java,v 1.1 2008/07/23 04:12:27 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.net.URI;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Identifiers</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Resource identifiers of an ODA host application or custom ODA designer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers#getApplResourceBaseURIString <em>Appl Resource Base URI String</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers#getDesignResourceBaseURIString <em>Design Resource Base URI String</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResourceIdentifiers()
 * @model extendedMetaData="name='ResourceIdentifiers' kind='elementOnly'"
 * @generated
 * @since DTP 1.7
 */
public interface ResourceIdentifiers extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2008, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Appl Resource Base URI String</b></em>' attribute in URI form.
     * @return  the base URI of general purpose resources of an ODA consumer application; may be null
     * @see #getApplResourceBaseURIString()
     * @generated NOT
     */
    URI getApplResourceBaseURI();

    /**
     * Sets the value of the '<em><b>Appl Resource Base URI String</b></em>' attribute in URI form.
     * @param baseURI the base URI of general purpose resources of an ODA consumer application
     * @see #setApplResourceBaseURIString()
     * @generated NOT
     */
    void setApplResourceBaseURI( URI baseURI );

    /**
     * Returns the value of the '<em><b>Appl Resource Base URI String</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The base URI of general purpose resources of an ODA consumer application in string form.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Appl Resource Base URI String</em>' attribute.
     * @see #getApplResourceBaseURI()
     * @see #setApplResourceBaseURIString(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResourceIdentifiers_ApplResourceBaseURIString()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='applResourceBaseURIString' namespace='##targetNamespace'"
     * @generated
     */
    String getApplResourceBaseURIString();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers#getApplResourceBaseURIString <em>Appl Resource Base URI String</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Appl Resource Base URI String</em>' attribute.
     * @see #setApplResourceBaseURI(URI)
     * @see #getApplResourceBaseURIString()
     * @generated
     */
    void setApplResourceBaseURIString( String value );

    /**
     * Returns the value of the '<em><b>Design Resource Base URI String</b></em>' attribute in URI form.
     * @return the base URI of design resources of an ODA consumer application; may be null
     * @see #getApplResourceBaseURIString()
     * @generated NOT
     */
    URI getDesignResourceBaseURI();

    /**
     * Sets the value of the '<em><b>Design Resource Base URI String</b></em>' attribute in URI form.
     * @param baseURI the base URI of design resources of an ODA consumer application
     * @see #setDesignResourceBaseURIString()
     * @generated NOT
     */
    void setDesignResourceBaseURI( URI baseURI );

    /**
     * Returns the value of the '<em><b>Design Resource Base URI String</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The base URI of design resources of an ODA consumer application in string form.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Design Resource Base URI String</em>' attribute.
     * @see #getDesignResourceBaseURI()
     * @see #setDesignResourceBaseURIString(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResourceIdentifiers_DesignResourceBaseURIString()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='designResourceBaseURIString' namespace='##targetNamespace'"
     * @generated
     */
    String getDesignResourceBaseURIString();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers#getDesignResourceBaseURIString <em>Design Resource Base URI String</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Design Resource Base URI String</em>' attribute.
     * @see #setDesignResourceBaseURI(URI)
     * @see #getDesignResourceBaseURIString()
     * @generated
     */
    void setDesignResourceBaseURIString( String value );

} // ResourceIdentifiers