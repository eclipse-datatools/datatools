/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
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
 * $Id: InputPromptControlStyle.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Input Prompt Control Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The style of UI control for prompting user to input a value.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputPromptControlStyle()
 * @model extendedMetaData="name='InputPromptControlStyle'"
 * @generated
 */
public enum InputPromptControlStyle implements Enumerator {
    /**
     * The '<em><b>Text Field</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TEXT_FIELD
     * @generated
     * @ordered
     */
    TEXT_FIELD_LITERAL(0, "TextField", "TextField"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Selectable List</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SELECTABLE_LIST
     * @generated
     * @ordered
     */
    SELECTABLE_LIST_LITERAL(1, "SelectableList", "SelectableList"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Selectable List With Text Field</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SELECTABLE_LIST_WITH_TEXT_FIELD
     * @generated
     * @ordered
     */
    SELECTABLE_LIST_WITH_TEXT_FIELD_LITERAL(2, "SelectableListWithTextField", //$NON-NLS-1$
            "SelectableListWithTextField"), //$NON-NLS-1$
    /**
     * The '<em><b>Check Box</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CHECK_BOX
     * @generated
     * @ordered
     */
    CHECK_BOX_LITERAL(3, "CheckBox", "CheckBox"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Radio Button</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RADIO_BUTTON
     * @generated
     * @ordered
     */
    RADIO_BUTTON_LITERAL(4, "RadioButton", "RadioButton"); //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Text Field</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TEXT_FIELD_LITERAL
     * @model name="TextField"
     * @generated
     * @ordered
     */
    public static final int TEXT_FIELD = 0;

    /**
     * The '<em><b>Selectable List</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SELECTABLE_LIST_LITERAL
     * @model name="SelectableList"
     * @generated
     * @ordered
     */
    public static final int SELECTABLE_LIST = 1;

    /**
     * The '<em><b>Selectable List With Text Field</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SELECTABLE_LIST_WITH_TEXT_FIELD_LITERAL
     * @model name="SelectableListWithTextField"
     * @generated
     * @ordered
     */
    public static final int SELECTABLE_LIST_WITH_TEXT_FIELD = 2;

    /**
     * The '<em><b>Check Box</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CHECK_BOX_LITERAL
     * @model name="CheckBox"
     * @generated
     * @ordered
     */
    public static final int CHECK_BOX = 3;

    /**
     * The '<em><b>Radio Button</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RADIO_BUTTON_LITERAL
     * @model name="RadioButton"
     * @generated
     * @ordered
     */
    public static final int RADIO_BUTTON = 4;

    /**
     * An array of all the '<em><b>Input Prompt Control Style</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final InputPromptControlStyle[] VALUES_ARRAY = new InputPromptControlStyle[]
    { TEXT_FIELD_LITERAL, SELECTABLE_LIST_LITERAL,
            SELECTABLE_LIST_WITH_TEXT_FIELD_LITERAL, CHECK_BOX_LITERAL,
            RADIO_BUTTON_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Input Prompt Control Style</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<InputPromptControlStyle> VALUES = Collections
            .unmodifiableList( Arrays.asList( VALUES_ARRAY ) );

    /**
     * Returns the '<em><b>Input Prompt Control Style</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static InputPromptControlStyle get( String literal )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            InputPromptControlStyle result = VALUES_ARRAY[i];
            if( result.toString().equals( literal ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Input Prompt Control Style</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static InputPromptControlStyle getByName( String name )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            InputPromptControlStyle result = VALUES_ARRAY[i];
            if( result.getName().equals( name ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Input Prompt Control Style</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static InputPromptControlStyle get( int value )
    {
        switch( value )
        {
        case TEXT_FIELD:
            return TEXT_FIELD_LITERAL;
        case SELECTABLE_LIST:
            return SELECTABLE_LIST_LITERAL;
        case SELECTABLE_LIST_WITH_TEXT_FIELD:
            return SELECTABLE_LIST_WITH_TEXT_FIELD_LITERAL;
        case CHECK_BOX:
            return CHECK_BOX_LITERAL;
        case RADIO_BUTTON:
            return RADIO_BUTTON_LITERAL;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private InputPromptControlStyle( int value, String name, String literal )
    {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue()
    {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName()
    {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral()
    {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        return literal;
    }
}
