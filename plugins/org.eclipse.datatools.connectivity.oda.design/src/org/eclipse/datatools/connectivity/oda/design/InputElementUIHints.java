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
 * $Id: InputElementUIHints.java,v 1.1 2005/12/29 04:17:55 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * UI hints for a data element defined with the input mode.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementUIHints#getPromptStyle <em>Prompt Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementUIHints()
 * @model extendedMetaData="name='InputElementUIHints' kind='elementOnly'"
 * @generated
 */
public interface InputElementUIHints extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Prompt Style</b></em>' attribute.
     * The default value is <code>""</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Defaults to TextField if no static and dynamic value choices are defined; otherwise, defaults to SelectableList.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Prompt Style</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle
     * @see #isSetPromptStyle()
     * @see #unsetPromptStyle()
     * @see #setPromptStyle(InputPromptControlStyle)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementUIHints_PromptStyle()
     * @model default="" unique="false" unsettable="true"
     *        extendedMetaData="kind='element' name='promptStyle' namespace='##targetNamespace'"
     * @generated
     */
    InputPromptControlStyle getPromptStyle();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementUIHints#getPromptStyle <em>Prompt Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Prompt Style</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle
     * @see #isSetPromptStyle()
     * @see #unsetPromptStyle()
     * @see #getPromptStyle()
     * @generated
     */
    void setPromptStyle( InputPromptControlStyle value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementUIHints#getPromptStyle <em>Prompt Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetPromptStyle()
     * @see #getPromptStyle()
     * @see #setPromptStyle(InputPromptControlStyle)
     * @generated
     */
    void unsetPromptStyle();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementUIHints#getPromptStyle <em>Prompt Style</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Prompt Style</em>' attribute is set.
     * @see #unsetPromptStyle()
     * @see #getPromptStyle()
     * @see #setPromptStyle(InputPromptControlStyle)
     * @generated
     */
    boolean isSetPromptStyle();

} // InputElementUIHints
