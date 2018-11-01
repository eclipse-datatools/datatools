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
 * $Id: DataElementUIHints.java,v 1.3 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * UI hints for a data element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementUIHints()
 * @model extendedMetaData="name='DataElementUIHints' kind='elementOnly'"
 * @generated
 */
public interface DataElementUIHints extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Business name of the data element.  Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementUIHints_DisplayName()
     * @see #getDisplayNameKey()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='displayName' namespace='##targetNamespace'"
     * @generated
     */
    String getDisplayName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDisplayName <em>Display Name</em>}' attribute.
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
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDisplayName <em>Display Name</em>}' attribute.
     * @param value  the new resource key of the '<em>Display Name</em>' attribute;
     *              may be null to reset
     * @see #getDisplayNameKey()
     * @see #setDisplayName(String)
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    void setDisplayNameKey( String value );

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * More extensive description of the data element.  Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementUIHints_Description()
     * @see #getDescriptionKey()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @see #setDescriptionKey(String)
     * @generated
     */
    void setDescription( String value );

    /**
     * Returns the resource key of the '<em><b>Description</b></em>' attribute.
     * @return the resource key of the '<em>Description</em>' attribute.
     * @see #setDescriptionKey(String)
     * @see #getDescription()
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    String getDescriptionKey();
    
    /**
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDescription <em>Description</em>}' attribute.
     * @param value the new resource key of the '<em>Description</em>' attribute;
     *              may be null to reset
     * @see #getDescriptionKey()
     * @see #setDescription(String)
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    void setDescriptionKey( String value );

} // DataElementUIHints
