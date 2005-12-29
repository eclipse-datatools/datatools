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
 * A representation of the literals of the enumeration '<em><b>Text Wrap Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * A hint on the type of text wrapping on the data column values.  It could be in a single line (None), or word-wrapped (Word).
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getTextWrapType()
 * @model
 * @generated
 */
public final class TextWrapType extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>None</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE_LITERAL
     * @model name="None"
     * @generated
     * @ordered
     */
    public static final int NONE = 0;

    /**
     * The '<em><b>Word</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #WORD_LITERAL
     * @model name="Word"
     * @generated
     * @ordered
     */
    public static final int WORD = 1;

    /**
     * The '<em><b>None</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE
     * @generated
     * @ordered
     */
    public static final TextWrapType NONE_LITERAL = new TextWrapType(NONE, "None"); //$NON-NLS-1$

    /**
     * The '<em><b>Word</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #WORD
     * @generated
     * @ordered
     */
    public static final TextWrapType WORD_LITERAL = new TextWrapType(WORD, "Word"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Text Wrap Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final TextWrapType[] VALUES_ARRAY =
        new TextWrapType[]
        {
            NONE_LITERAL,
            WORD_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Text Wrap Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Text Wrap Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TextWrapType get( String name )
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            TextWrapType result = VALUES_ARRAY[i];
            if (result.toString().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Text Wrap Type</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TextWrapType get( int value )
    {
        switch (value)
        {
            case NONE: return NONE_LITERAL;
            case WORD: return WORD_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private TextWrapType( int value, String name )
    {
        super( value, name );
    }

} //TextWrapType
