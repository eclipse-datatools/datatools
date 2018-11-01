/**
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
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
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Custom data specific to any participant of an ODA design session, i.e. an ODA host or custom designer, as identified by its providerId.  Its value is used only by its provider, but may be displayed in a string format by a non-provider component. This may be used as one of the StaticValues defined to be an input parameter's default values.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.CustomData#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.CustomData#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.CustomData#getDisplayValue <em>Display Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCustomData()
 * @model extendedMetaData="name='CustomData' kind='elementOnly'"
 * @generated
 * @since 3.3.1 (DTP 1.8.1)
 */
public interface CustomData extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Provider Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The unique identifier or namespace of the provider of this instance.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Provider Id</em>' attribute.
     * @see #setProviderId(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCustomData_ProviderId()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='providerId' namespace='##targetNamespace'"
     * @generated
     */
    String getProviderId();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.CustomData#getProviderId <em>Provider Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Provider Id</em>' attribute.
     * @see #getProviderId()
     * @generated
     */
    void setProviderId( String value );

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Custom data value that is normally used only by its provider.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(Object)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCustomData_Value()
     * @model dataType="org.eclipse.emf.ecore.xml.type.AnySimpleType" required="true"
     *        extendedMetaData="kind='element' name='value' namespace='##targetNamespace'"
     * @generated
     */
    Object getValue();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.CustomData#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue( Object value );

    /**
     * Returns the value of the '<em><b>Display Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Display Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The String representation of the data value, which can be displayed by a non provider.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Value</em>' attribute.
     * @see #setDisplayValue(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCustomData_DisplayValue()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='displayValue' namespace='##targetNamespace'"
     * @generated
     */
    String getDisplayValue();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.CustomData#getDisplayValue <em>Display Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Value</em>' attribute.
     * @see #getDisplayValue()
     * @generated
     */
    void setDisplayValue( String value );

} // CustomData
