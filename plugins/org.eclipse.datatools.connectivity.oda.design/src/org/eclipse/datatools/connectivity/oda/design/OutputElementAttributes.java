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
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Common attributes for a data element defined with the output mode.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getFormattingHints <em>Formatting Hints</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getHelpText <em>Help Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOutputElementAttributes()
 * @model 
 * @generated
 */
public interface OutputElementAttributes extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A free-format string used to identify the data element in a heading, such as a column header.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOutputElementAttributes_Label()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel( String value );

    /**
     * Returns the value of the '<em><b>Formatting Hints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Formatting Hints</em>' containment reference.
     * @see #setFormattingHints(ValueFormatHints)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOutputElementAttributes_FormattingHints()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    ValueFormatHints getFormattingHints();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getFormattingHints <em>Formatting Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Formatting Hints</em>' containment reference.
     * @see #getFormattingHints()
     * @generated
     */
    void setFormattingHints( ValueFormatHints value );

    /**
     * Returns the value of the '<em><b>Help Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Provides additional information to the user about this data element, such as in a balloon help.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Help Text</em>' attribute.
     * @see #setHelpText(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOutputElementAttributes_HelpText()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getHelpText();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getHelpText <em>Help Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Help Text</em>' attribute.
     * @see #getHelpText()
     * @generated
     */
    void setHelpText( String value );

} // OutputElementAttributes
