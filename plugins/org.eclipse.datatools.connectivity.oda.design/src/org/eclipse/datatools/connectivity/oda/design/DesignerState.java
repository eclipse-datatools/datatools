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
 * $Id: DesignerState.java,v 1.1 2005/12/29 04:17:55 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Private state of an ODA designer upon exit of a design session.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignerState#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignerState#getStateContent <em>State Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignerState()
 * @model 
 * @generated
 */
public interface DesignerState extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Version of the designer state's data format.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignerState_Version()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignerState#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion( String value );

    /**
     * Returns the value of the '<em><b>State Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Designer's private state information; its content is a blackbox to an ODA host.
     * <!-- end-model-doc -->
     * @return the value of the '<em>State Content</em>' containment reference.
     * @see #setStateContent(DesignerStateContent)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignerState_StateContent()
     * @model containment="true" resolveProxies="false" required="true"
     * @generated
     */
    DesignerStateContent getStateContent();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignerState#getStateContent <em>State Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>State Content</em>' containment reference.
     * @see #getStateContent()
     * @generated
     */
    void setStateContent( DesignerStateContent value );

    /**
     * Sets the value of the <em>State Content As String</em> attribute
     * in a new <em>State Content</em> containment reference.
     * @param value the String content value of the new '<em>State Content</em>' containment reference.
     * @see #setStateContent(DesignerStateContent)
     * @generated NOT
     */
    void setNewStateContentAsString( String value );

    /**
     * Sets the value of the <em>State Content As Blob</em> attribute
     * in a new <em>State Content</em> containment reference.
     * @param value the Blob content value of the new '<em>State Content</em>' containment reference.
     * @see #setStateContent(DesignerStateContent)
     * @generated NOT
     */
    void setNewStateContentAsBlob( byte[] value );

} // DesignerState
