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
 * $Id: InputParameterAttributes.java,v 1.2 2006/02/12 06:45:56 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The attributes of a top-level input parameter, which can be either scalar or complex type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes#getElementAttributes <em>Element Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes#getUiHints <em>Ui Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputParameterAttributes()
 * @model extendedMetaData="name='InputParameterAttributes' kind='elementOnly'"
 * @generated
 */
public interface InputParameterAttributes extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Element Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Element Attributes</em>' containment reference.
     * @see #setElementAttributes(InputElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputParameterAttributes_ElementAttributes()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='elementAttributes' namespace='##targetNamespace'"
     * @generated
     */
    InputElementAttributes getElementAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes#getElementAttributes <em>Element Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Element Attributes</em>' containment reference.
     * @see #getElementAttributes()
     * @generated
     */
    void setElementAttributes( InputElementAttributes value );

    /**
     * Returns the value of the '<em><b>Ui Hints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ui Hints</em>' containment reference.
     * @see #setUiHints(InputParameterUIHints)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputParameterAttributes_UiHints()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='uiHints' namespace='##targetNamespace'"
     * @generated
     */
    InputParameterUIHints getUiHints();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes#getUiHints <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ui Hints</em>' containment reference.
     * @see #getUiHints()
     * @generated
     */
    void setUiHints( InputParameterUIHints value );

    /**
     * Sets the value of the <em>Group Prompt Display Name</em> attribute
     * in the element's UI hints.
     * @param value the new value of the '<em>Group Prompt Display Name</em>' attribute.
     * @see #setUiHints(InputParameterUIHints)
     * @generated NOT
     */
    void setUiGroupPromptDisplayName( String value );

} // InputParameterAttributes
