/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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
 * $Id: StaticValues.java,v 1.1 2009/02/12 02:50:20 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Values</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of static values in any data type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.StaticValues#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getStaticValues()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='StaticValues' kind='elementOnly'"
 * @generated
 */
public interface StaticValues extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Values</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Object}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Values</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Values</em>' attribute list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getStaticValues_Values()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnySimpleType" required="true"
     *        extendedMetaData="kind='element' name='values' namespace='##targetNamespace'"
     * @generated
     */
    EList<Object> getValues();
    
    /**
     * Indicates whether this collection of static values is empty.
     * @return  true if this has an empty collection of static values; false otherwise
     * @generated NOT
     */
    boolean isEmpty();

    /**
     * Gets the number of static values in this collection.
     * @return  number of static values
     * @generated NOT
     */
    int count();
    
    /**
     * Appends the specified value to this ordered collection of static values.
     * It is the responsibility of the caller to ensure compatible type of value object
     * is added to the collection.
     * @param aValue    a value to add; may be null
     * @generated NOT
     */
    void add( Object aValue );
    
    /**
     * Removes all of the static values from this collection. 
     * This will be empty after this call returns. 
     * @generated NOT
     */
    void clear();

} // StaticValues
