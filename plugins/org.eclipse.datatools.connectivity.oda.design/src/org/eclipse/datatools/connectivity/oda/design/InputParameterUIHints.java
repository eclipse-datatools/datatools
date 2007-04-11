/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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
 * $Id: InputParameterUIHints.java,v 1.1 2005/12/29 04:17:55 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * UI hints for a top-level parameter.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints#getGroupPromptDisplayName <em>Group Prompt Display Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputParameterUIHints()
 * @model extendedMetaData="name='InputParameterUIHints' kind='elementOnly'"
 * @generated
 */
public interface InputParameterUIHints extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Group Prompt Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * For grouping multiple top-level input parameters under the same UI group prompt.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Group Prompt Display Name</em>' attribute.
     * @see #setGroupPromptDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputParameterUIHints_GroupPromptDisplayName()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='groupPromptDisplayName' namespace='##targetNamespace'"
     * @generated
     */
    String getGroupPromptDisplayName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints#getGroupPromptDisplayName <em>Group Prompt Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Group Prompt Display Name</em>' attribute.
     * @see #getGroupPromptDisplayName()
     * @generated
     */
    void setGroupPromptDisplayName( String value );

} // InputParameterUIHints
