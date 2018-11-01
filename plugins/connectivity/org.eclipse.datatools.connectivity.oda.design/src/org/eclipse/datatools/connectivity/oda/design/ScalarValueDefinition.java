/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
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
 * $Id: ScalarValueDefinition.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines a scalar value with a brief description.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#getDisplayName <em>Display Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getScalarValueDefinition()
 * @model extendedMetaData="name='ScalarValueDefinition' kind='elementOnly'"
 * @generated
 */
public interface ScalarValueDefinition extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getScalarValueDefinition_Value()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='value' namespace='##targetNamespace'"
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue( String value );

    /**
     * Returns the value of the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A localized name that describes the value.  Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getScalarValueDefinition_DisplayName()
     * @see #getDisplayNameKey()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='displayName' namespace='##targetNamespace'"
     * @generated
     */
    String getDisplayName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#getDisplayName <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Name</em>' attribute.
     * @see #getDisplayName()
     * @see #setDisplayNameKey(String)
     * @generated
     */
    void setDisplayName( String value );
    
    /**
     * Returns the resource key of the '<em><b>Display Name</b></em>' attribute.
     * @return  the resource key of the '<em>Display Name</em>' attribute; may be null if none is available
     * @see #setDisplayNameKey(String)
     * @see #getDisplayName()
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    String getDisplayNameKey();
    
    /**
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#getDisplayName <em>Display Name</em>}' attribute.
     * @param value  the new resource key of the '<em>Display Name</em>' attribute;
     *              may be null to reset
     * @see #getDisplayNameKey()
     * @see #setDisplayName(String)
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    void setDisplayNameKey( String value );

} // ScalarValueDefinition
