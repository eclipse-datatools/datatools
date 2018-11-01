/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: Properties.java,v 1.7 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of properties.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.Properties#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getProperties()
 * @model extendedMetaData="name='Properties' kind='elementOnly'"
 * @generated
 */
public interface Properties extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Finds and returns the Property with the
     * given property name in the 
     * '<em><b>Properties</b></em>' containment reference list.
     * @param propName	property name
     * @return  the Property found with the given property name, or
     *          null if no property is found with given name
     * @generated NOT
     */
    Property findProperty( String propName );

    /**
     * Returns the value of the named Property in the
     * '<em><b>Properties</b></em>' containment reference list.
     * @param propName  property name
     * @return  the value of the given named property; 
     *          may be null if no property is found with given name
     * @generated NOT
     */
    String getProperty( String propName );

    /**
     * Sets the value in the Property with given name in the
     * '<em><b>Properties</b></em>' containment reference list.
     * Adds a new NameValuePair if none exists with the given 
     * property name.
     * @param propName	property name
     * @param propValue	property value
     * @generated NOT
     */
    void setProperty( String propName, String propValue );

    /**
     * Removes the Property with given name in the
     * '<em><b>Properties</b></em>' containment reference list.
     * @param propName
     * @generated NOT
     */
    void unsetProperty( String propName );

    /**
     * Indicates whether this collection of properties is empty.
     * @return  true if this collection has no entries; false otherwise.
     * @generated NOT
     */
    boolean isEmpty();

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.Property}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getProperties_Properties()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='properties' namespace='##targetNamespace'"
     * @generated
     */
    EList<Property> getProperties();

} // Properties
