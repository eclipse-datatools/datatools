/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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
 * $Id: DesignerStateContent.java,v 1.1 2005/12/29 04:17:56 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An ODA Designer's private state information; its content is a blackbox to an ODA host.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignerStateContent#getStateContentAsString <em>State Content As String</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignerStateContent#getStateContentAsBlob <em>State Content As Blob</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignerStateContent()
 * @model extendedMetaData="name='DesignerStateContent' kind='elementOnly'"
 * @generated
 */
public interface DesignerStateContent extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>State Content As String</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * State content in string format.
     * <!-- end-model-doc -->
     * @return the value of the '<em>State Content As String</em>' attribute.
     * @see #setStateContentAsString(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignerStateContent_StateContentAsString()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='stateContentAsString' namespace='##targetNamespace'"
     * @generated
     */
    String getStateContentAsString();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignerStateContent#getStateContentAsString <em>State Content As String</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>State Content As String</em>' attribute.
     * @see #getStateContentAsString()
     * @generated
     */
    void setStateContentAsString( String value );

    /**
     * Returns the value of the '<em><b>State Content As Blob</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * State content in binary format.
     * <!-- end-model-doc -->
     * @return the value of the '<em>State Content As Blob</em>' attribute.
     * @see #setStateContentAsBlob(byte[])
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignerStateContent_StateContentAsBlob()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Base64Binary"
     *        extendedMetaData="kind='element' name='stateContentAsBlob' namespace='##targetNamespace'"
     * @generated
     */
    byte[] getStateContentAsBlob();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignerStateContent#getStateContentAsBlob <em>State Content As Blob</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>State Content As Blob</em>' attribute.
     * @see #getStateContentAsBlob()
     * @generated
     */
    void setStateContentAsBlob( byte[] value );

} // DesignerStateContent
