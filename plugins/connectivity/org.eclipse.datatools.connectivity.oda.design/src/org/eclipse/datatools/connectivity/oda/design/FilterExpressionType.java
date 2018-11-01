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
 * $Id: FilterExpressionType.java,v 1.1 2009/10/23 20:17:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter Expression Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Identifies a type of custom filter expression contributed by an extension of the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.FilterExpressionType#getDeclaringExtensionId <em>Declaring Extension Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.FilterExpressionType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getFilterExpressionType()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='FilterExpressionType' kind='elementOnly'"
 * @generated
 */
public interface FilterExpressionType extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Declaring Extension Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The id of the ODA dynamicResultSet extension that declares this type of filter expression.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Declaring Extension Id</em>' attribute.
     * @see #setDeclaringExtensionId(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getFilterExpressionType_DeclaringExtensionId()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='declaringExtensionId' namespace='##targetNamespace'"
     * @generated
     */
    String getDeclaringExtensionId();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.FilterExpressionType#getDeclaringExtensionId <em>Declaring Extension Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Declaring Extension Id</em>' attribute.
     * @see #getDeclaringExtensionId()
     * @generated
     */
    void setDeclaringExtensionId( String value );

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The id that uniquely identifies  a custom expression type within an ODA dynamicResultSet extension.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getFilterExpressionType_Id()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='id' namespace='##targetNamespace'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.FilterExpressionType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId( String value );

} // FilterExpressionType
