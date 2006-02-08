/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: Property.java,v 1.3 2006/02/07 05:52:28 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The property of a data design component.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.Property#getNameValue <em>Name Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.Property#getDesignAttributes <em>Design Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getProperty()
 * @model extendedMetaData="name='Property' kind='elementOnly'"
 * @generated
 */
public interface Property extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the name of the '<em><b>Name Value</b></em>' containment reference.
     * @return  the property name, or null if none is specified
     * @see #getNameValue()
     * @generated NOT
     */
    String getName();

    /**
     * Returns the value of the '<em><b>Name Value</b></em>' containment reference.
     * @return  the property value, or null if none is specified
     * @see #getNameValue()
     * @generated NOT
     */
    String getValue();

    /**
     * Returns the value of the '<em><b>Name Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Property name and corresponding value if available.  The property value must be a string.  The value may be selected from one of the value choices defined for a property.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name Value</em>' containment reference.
     * @see #setNameValue(NameValuePair)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getProperty_NameValue()
     * @model containment="true" resolveProxies="false" required="true"
     extendedMetaData="kind='element' name='nameValue' namespace='##targetNamespace'"
     * @generated
     */
    NameValuePair getNameValue();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.Property#getNameValue <em>Name Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name Value</em>' containment reference.
     * @see #getNameValue()
     * @generated
     */
    void setNameValue( NameValuePair value );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.Property#getNameValue <em>Name Value</em>}' containment reference
     * with the pair values.
     * @param name
     * @param value
     * @see #setNameValue(NameValuePair)
     * @generated NOT
     */
    void setNameValue( String name, String value );

    /**
     * Returns the value of the '<em><b>Design Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Property attributes defined for this *instance* of DataAccessDesign.  This set of attributes overrides all those pre-defined by an ODA extension in its plugin.xml.  An ODA host designer should apply those pre-defined attributes by default to this instance, only if none is specified here.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Design Attributes</em>' containment reference.
     * @see #setDesignAttributes(PropertyAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getProperty_DesignAttributes()
     * @model containment="true" resolveProxies="false"
     extendedMetaData="kind='element' name='designAttributes' namespace='##targetNamespace'"
     * @generated
     */
    PropertyAttributes getDesignAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.Property#getDesignAttributes <em>Design Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Design Attributes</em>' containment reference.
     * @see #getDesignAttributes()
     * @generated
     */
    void setDesignAttributes( PropertyAttributes value );

} // Property
