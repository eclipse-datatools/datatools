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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Input Prompt Control Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The style of UI control for prompting user to input a value.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputPromptControlStyle()
 * @model
 * @generated
 */
public final class InputPromptControlStyle extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

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
     * The '<em><b>Text Field</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TEXT_FIELD
     * @generated
     * @ordered
     */
    public static final InputPromptControlStyle TEXT_FIELD_LITERAL = new InputPromptControlStyle(TEXT_FIELD, "TextField"); //$NON-NLS-1$

    /**
     * The '<em><b>Selectable List</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SELECTABLE_LIST
     * @generated
     * @ordered
     */
    public static final InputPromptControlStyle SELECTABLE_LIST_LITERAL = new InputPromptControlStyle(SELECTABLE_LIST, "SelectableList"); //$NON-NLS-1$

    /**
     * The '<em><b>Selectable List With Text Field</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SELECTABLE_LIST_WITH_TEXT_FIELD
     * @generated
     * @ordered
     */
    public static final InputPromptControlStyle SELECTABLE_LIST_WITH_TEXT_FIELD_LITERAL = new InputPromptControlStyle(SELECTABLE_LIST_WITH_TEXT_FIELD, "SelectableListWithTextField"); //$NON-NLS-1$

    /**
     * The '<em><b>Check Box</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CHECK_BOX
     * @generated
     * @ordered
     */
    public static final InputPromptControlStyle CHECK_BOX_LITERAL = new InputPromptControlStyle(CHECK_BOX, "CheckBox"); //$NON-NLS-1$

    /**
     * The '<em><b>Radio Button</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RADIO_BUTTON
     * @generated
     * @ordered
     */
    public static final InputPromptControlStyle RADIO_BUTTON_LITERAL = new InputPromptControlStyle(RADIO_BUTTON, "RadioButton"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Input Prompt Control Style</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final InputPromptControlStyle[] VALUES_ARRAY =
        new InputPromptControlStyle[]
        {
            TEXT_FIELD_LITERAL,
            SELECTABLE_LIST_LITERAL,
            SELECTABLE_LIST_WITH_TEXT_FIELD_LITERAL,
            CHECK_BOX_LITERAL,
            RADIO_BUTTON_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Input Prompt Control Style</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Input Prompt Control Style</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static InputPromptControlStyle get( String name )
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            InputPromptControlStyle result = VALUES_ARRAY[i];
            if (result.toString().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Input Prompt Control Style</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static InputPromptControlStyle get( int value )
    {
        switch (value)
        {
            case TEXT_FIELD: return TEXT_FIELD_LITERAL;
            case SELECTABLE_LIST: return SELECTABLE_LIST_LITERAL;
            case SELECTABLE_LIST_WITH_TEXT_FIELD: return SELECTABLE_LIST_WITH_TEXT_FIELD_LITERAL;
            case CHECK_BOX: return CHECK_BOX_LITERAL;
            case RADIO_BUTTON: return RADIO_BUTTON_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private InputPromptControlStyle( int value, String name )
    {
        super( value, name );
    }

} //InputPromptControlStyle
