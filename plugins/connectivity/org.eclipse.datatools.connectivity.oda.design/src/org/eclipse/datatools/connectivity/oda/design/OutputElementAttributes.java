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
 * $Id: OutputElementAttributes.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
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
 * @model extendedMetaData="name='OutputElementAttributes' kind='elementOnly'"
 * @generated
 */
public interface OutputElementAttributes extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A free-format string used to identify the data element in a heading, such as a column header.  Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOutputElementAttributes_Label()
     * @see #getLabelKey
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='label' namespace='##targetNamespace'"
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @see #setLabelKey(String)
     * @generated
     */
    void setLabel( String value );

    /**
     * Returns the resource key of the '<em><b>Label</b></em>' attribute.
     * @return the resource key of the '<em>Label</em>' attribute.
     * @see #setLabelKey(String)
     * @see #getLabel()
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    String getLabelKey();
    
    /**
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getLabel <em>Label</em>}' attribute.
     * @param value the new resource key of the '<em>Label</em>' attribute;
     *              may be null to reset
     * @see #getLabelKey()
     * @see #setLabel(String)
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    void setLabelKey( String value );

    /**
     * Returns the value of the '<em><b>Formatting Hints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Formatting Hints</em>' containment reference.
     * @see #setFormattingHints(ValueFormatHints)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOutputElementAttributes_FormattingHints()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='formattingHints' namespace='##targetNamespace'"
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
     * Provides additional information to the user about this data element, such as in a balloon help.  Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Help Text</em>' attribute.
     * @see #setHelpText(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOutputElementAttributes_HelpText()
     * @see #getHelpTextKey()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='helpText' namespace='##targetNamespace'"
     * @generated
     */
    String getHelpText();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getHelpText <em>Help Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Help Text</em>' attribute.
     * @see #getHelpText()
     * @see #setHelpTextKey(String)
     * @generated
     */
    void setHelpText( String value );

    /**
     * Returns the resource key of the '<em><b>Help Text</b></em>' attribute.
     * @return the resource key of the '<em>Help Text</em>' attribute.
     * @see #setHelpTextKey(String)
     * @see #getHelpText()
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    String getHelpTextKey();
    
    /**
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getHelpText <em>Help Text</em>}' attribute.
     * @param value the new resource key of the '<em>Help Text</em>' attribute;
     *              may be null to reset
     * @see #getHelpTextKey()
     * @see #setHelpText(String)
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    void setHelpTextKey( String value );

} // OutputElementAttributes
