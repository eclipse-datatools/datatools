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
 * $Id: ValueFormatHints.java,v 1.2 2006/03/09 05:09:18 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Hints on how to format a value.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getDisplaySize <em>Display Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getDisplayFormat <em>Display Format</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getTextFormatType <em>Text Format Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getTextWrapType <em>Text Wrap Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getValueFormatHints()
 * @model extendedMetaData="name='ValueFormatHints' kind='elementOnly'"
 * @generated
 */
public interface ValueFormatHints extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Display Size</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Default display length of the data value.  A value of -1 means unknown length.  The default value, if not specified, is -1.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Size</em>' attribute.
     * @see #isSetDisplaySize()
     * @see #unsetDisplaySize()
     * @see #setDisplaySize(int)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getValueFormatHints_DisplaySize()
     * @model default="-1" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='element' name='displaySize' namespace='##targetNamespace'"
     * @generated
     */
    int getDisplaySize();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getDisplaySize <em>Display Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Size</em>' attribute.
     * @see #isSetDisplaySize()
     * @see #unsetDisplaySize()
     * @see #getDisplaySize()
     * @generated
     */
    void setDisplaySize( int value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getDisplaySize <em>Display Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDisplaySize()
     * @see #getDisplaySize()
     * @see #setDisplaySize(int)
     * @generated
     */
    void unsetDisplaySize();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getDisplaySize <em>Display Size</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Display Size</em>' attribute is set.
     * @see #unsetDisplaySize()
     * @see #getDisplaySize()
     * @see #setDisplaySize(int)
     * @generated
     */
    boolean isSetDisplaySize();

    /**
     * Returns the value of the '<em><b>Display Format</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The preferred display format of the data value.  It contains a data format mask, e.g. #,###.00, ShortDate, etc.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Format</em>' attribute.
     * @see #setDisplayFormat(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getValueFormatHints_DisplayFormat()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='displayFormat' namespace='##targetNamespace'"
     * @generated
     */
    String getDisplayFormat();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getDisplayFormat <em>Display Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Format</em>' attribute.
     * @see #getDisplayFormat()
     * @generated
     */
    void setDisplayFormat( String value );

    /**
     * Returns the value of the '<em><b>Text Format Type</b></em>' attribute.
     * The default value is <code>"Plain"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.TextFormatType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The type of text content of the data value, i.e. plain text, HTML or RTF.  It provides a hint on the type of text control to use for displaying the data value, e.g. a Dynamic Text Control.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Text Format Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.TextFormatType
     * @see #isSetTextFormatType()
     * @see #unsetTextFormatType()
     * @see #setTextFormatType(TextFormatType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getValueFormatHints_TextFormatType()
     * @model default="Plain" unique="false" unsettable="true"
     *        extendedMetaData="kind='element' name='textFormatType' namespace='##targetNamespace'"
     * @generated
     */
    TextFormatType getTextFormatType();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getTextFormatType <em>Text Format Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Format Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.TextFormatType
     * @see #isSetTextFormatType()
     * @see #unsetTextFormatType()
     * @see #getTextFormatType()
     * @generated
     */
    void setTextFormatType( TextFormatType value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getTextFormatType <em>Text Format Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTextFormatType()
     * @see #getTextFormatType()
     * @see #setTextFormatType(TextFormatType)
     * @generated
     */
    void unsetTextFormatType();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getTextFormatType <em>Text Format Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Text Format Type</em>' attribute is set.
     * @see #unsetTextFormatType()
     * @see #getTextFormatType()
     * @see #setTextFormatType(TextFormatType)
     * @generated
     */
    boolean isSetTextFormatType();

    /**
     * Returns the value of the '<em><b>Horizontal Alignment</b></em>' attribute.
     * The default value is <code>"Automatic"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.HorizontalAlignment}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Horizontal display alignment of the data value.  See valid enum values.  "Automatic" means that it is up to an ODA host designer to determine the alignment based on its default rules.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Horizontal Alignment</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.HorizontalAlignment
     * @see #isSetHorizontalAlignment()
     * @see #unsetHorizontalAlignment()
     * @see #setHorizontalAlignment(HorizontalAlignment)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getValueFormatHints_HorizontalAlignment()
     * @model default="Automatic" unique="false" unsettable="true"
     *        extendedMetaData="kind='element' name='horizontalAlignment' namespace='##targetNamespace'"
     * @generated
     */
    HorizontalAlignment getHorizontalAlignment();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getHorizontalAlignment <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Horizontal Alignment</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.HorizontalAlignment
     * @see #isSetHorizontalAlignment()
     * @see #unsetHorizontalAlignment()
     * @see #getHorizontalAlignment()
     * @generated
     */
    void setHorizontalAlignment( HorizontalAlignment value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getHorizontalAlignment <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetHorizontalAlignment()
     * @see #getHorizontalAlignment()
     * @see #setHorizontalAlignment(HorizontalAlignment)
     * @generated
     */
    void unsetHorizontalAlignment();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getHorizontalAlignment <em>Horizontal Alignment</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Horizontal Alignment</em>' attribute is set.
     * @see #unsetHorizontalAlignment()
     * @see #getHorizontalAlignment()
     * @see #setHorizontalAlignment(HorizontalAlignment)
     * @generated
     */
    boolean isSetHorizontalAlignment();

    /**
     * Returns the value of the '<em><b>Text Wrap Type</b></em>' attribute.
     * The default value is <code>"None"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.TextWrapType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A hint on the type of text wrapping on the data value.  It could be in a single line (None), or word-wrapped (Word).
     * <!-- end-model-doc -->
     * @return the value of the '<em>Text Wrap Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.TextWrapType
     * @see #isSetTextWrapType()
     * @see #unsetTextWrapType()
     * @see #setTextWrapType(TextWrapType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getValueFormatHints_TextWrapType()
     * @model default="None" unique="false" unsettable="true"
     *        extendedMetaData="kind='element' name='textWrapType' namespace='##targetNamespace'"
     * @generated
     */
    TextWrapType getTextWrapType();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getTextWrapType <em>Text Wrap Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Wrap Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.TextWrapType
     * @see #isSetTextWrapType()
     * @see #unsetTextWrapType()
     * @see #getTextWrapType()
     * @generated
     */
    void setTextWrapType( TextWrapType value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getTextWrapType <em>Text Wrap Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTextWrapType()
     * @see #getTextWrapType()
     * @see #setTextWrapType(TextWrapType)
     * @generated
     */
    void unsetTextWrapType();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints#getTextWrapType <em>Text Wrap Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Text Wrap Type</em>' attribute is set.
     * @see #unsetTextWrapType()
     * @see #getTextWrapType()
     * @see #setTextWrapType(TextWrapType)
     * @generated
     */
    boolean isSetTextWrapType();

} // ValueFormatHints
