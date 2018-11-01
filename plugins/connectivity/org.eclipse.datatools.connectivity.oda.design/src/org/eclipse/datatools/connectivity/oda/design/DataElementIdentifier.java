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
 * A representation of the model object '<em><b>Data Element Identifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Identifies a data element by name and/or position.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier#getPosition <em>Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementIdentifier()
 * @model extendedMetaData="name='DataElementIdentifier' kind='elementOnly'"
 * @generated
 * @since 3.3.2
 */
public interface DataElementIdentifier extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A name that uniquely identifies a data element.  If a data element can only be identified by position, this name may be empty.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementIdentifier_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName( String value );

    /**
     * Returns the value of the '<em><b>Position</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The 1-based index position (left-to-right order) within a collection of data elements.  The position is defined per the underlying data source, and is not necessarily sequential.  For example, a query may access 3 columns in a result set, but the data access design may choose to expose only column 2.  In this case, only one column element with postion 2 is defined in the result set definition.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Position</em>' attribute.
     * @see #isSetPosition()
     * @see #unsetPosition()
     * @see #setPosition(int)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementIdentifier_Position()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.UnsignedShort"
     *        extendedMetaData="kind='element' name='position' namespace='##targetNamespace'"
     * @generated
     */
    int getPosition();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier#getPosition <em>Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Position</em>' attribute.
     * @see #isSetPosition()
     * @see #unsetPosition()
     * @see #getPosition()
     * @generated
     */
    void setPosition( int value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier#getPosition <em>Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetPosition()
     * @see #getPosition()
     * @see #setPosition(int)
     * @generated
     */
    void unsetPosition();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier#getPosition <em>Position</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Position</em>' attribute is set.
     * @see #unsetPosition()
     * @see #getPosition()
     * @see #setPosition(int)
     * @generated
     */
    boolean isSetPosition();

} // DataElementIdentifier
