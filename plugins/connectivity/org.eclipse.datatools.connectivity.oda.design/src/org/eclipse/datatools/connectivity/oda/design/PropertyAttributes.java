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
 * $Id: PropertyAttributes.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The attributes of a property.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#getElementAttributes <em>Element Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#isDerivedMetaData <em>Derived Meta Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getPropertyAttributes()
 * @model extendedMetaData="name='PropertyAttributes' kind='elementOnly'"
 * @generated
 */
public interface PropertyAttributes extends EObject
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
     * Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getPropertyAttributes_DisplayName()
     * @see #getDisplayNameKey()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='displayName' namespace='##targetNamespace'"
     * @generated
     */
    String getDisplayName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#getDisplayName <em>Display Name</em>}' attribute.
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
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#getDisplayName <em>Display Name</em>}' attribute.
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
     * Returns the value of the '<em><b>Element Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Element Attributes</em>' containment reference.
     * @see #setElementAttributes(InputElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getPropertyAttributes_ElementAttributes()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='elementAttributes' namespace='##targetNamespace'"
     * @generated
     */
    InputElementAttributes getElementAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#getElementAttributes <em>Element Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Element Attributes</em>' containment reference.
     * @see #getElementAttributes()
     * @generated
     */
    void setElementAttributes( InputElementAttributes value );

    /**
     * Returns the value of the '<em><b>Derived Meta Data</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * If the property metadata can be derived, i.e. can be obtained by an ODA driver in each design session, an ODA host designer is not required to include the derived metadata in the next design session request.  An ODA designer may ignore such metadata in a Request.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Derived Meta Data</em>' attribute.
     * @see #isSetDerivedMetaData()
     * @see #unsetDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getPropertyAttributes_DerivedMetaData()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='derivedMetaData' namespace='##targetNamespace'"
     * @generated
     */
    boolean isDerivedMetaData();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#isDerivedMetaData <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Derived Meta Data</em>' attribute.
     * @see #isSetDerivedMetaData()
     * @see #unsetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @generated
     */
    void setDerivedMetaData( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#isDerivedMetaData <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @generated
     */
    void unsetDerivedMetaData();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#isDerivedMetaData <em>Derived Meta Data</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Derived Meta Data</em>' attribute is set.
     * @see #unsetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @generated
     */
    boolean isSetDerivedMetaData();

} // PropertyAttributes
