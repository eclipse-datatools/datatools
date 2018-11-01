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
 * $Id: InputParameterUIHints.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
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
    String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Group Prompt Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * For grouping multiple top-level input parameters under the same UI group prompt.  Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Group Prompt Display Name</em>' attribute.
     * @see #setGroupPromptDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputParameterUIHints_GroupPromptDisplayName()
     * @see #getGroupPromptDisplayNameKey()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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
     * @see #setGroupPromptDisplayNameKey(String)
     * @generated
     */
    void setGroupPromptDisplayName( String value );

    /**
     * Returns the resource key of the '<em><b>Group Prompt Display Name</b></em>' attribute.
     * @return the resource key of the '<em>Group Prompt Display Name</em>' attribute.
     * @see #setGroupPromptDisplayNameKey
     * @see #getGroupPromptDisplayName()
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    String getGroupPromptDisplayNameKey();
    
    /**
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints#getGroupPromptDisplayName <em>Group Prompt Display Name</em>}' attribute.
     * @param value the new resource key of the '<em>Group Prompt Display Name</em>' attribute;
     *              may be null to reset
     * @see #getGroupPromptDisplayNameKey
     * @see #setGroupPromptDisplayName(String)
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    void setGroupPromptDisplayNameKey( String value );

} // InputParameterUIHints
