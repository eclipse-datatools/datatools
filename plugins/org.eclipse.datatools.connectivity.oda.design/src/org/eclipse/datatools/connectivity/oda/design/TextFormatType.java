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
 * A representation of the literals of the enumeration '<em><b>Text Format Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The type of text content of the data column values, i.e. plain text, HTML or RTF.  It provides a hint on the type of text control to use for displaying the data value, e.g. a Text Control vs. Dynamic Text Control.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getTextFormatType()
 * @model
 * @generated
 */
public final class TextFormatType extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Plain</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PLAIN_LITERAL
     * @model name="Plain"
     * @generated
     * @ordered
     */
    public static final int PLAIN = 0;

    /**
     * The '<em><b>HTML</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HTML_LITERAL
     * @model 
     * @generated
     * @ordered
     */
    public static final int HTML = 1;

    /**
     * The '<em><b>RTF</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RTF_LITERAL
     * @model 
     * @generated
     * @ordered
     */
    public static final int RTF = 2;

    /**
     * The '<em><b>Plain</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PLAIN
     * @generated
     * @ordered
     */
    public static final TextFormatType PLAIN_LITERAL = new TextFormatType(PLAIN, "Plain"); //$NON-NLS-1$

    /**
     * The '<em><b>HTML</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HTML
     * @generated
     * @ordered
     */
    public static final TextFormatType HTML_LITERAL = new TextFormatType(HTML, "HTML"); //$NON-NLS-1$

    /**
     * The '<em><b>RTF</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RTF
     * @generated
     * @ordered
     */
    public static final TextFormatType RTF_LITERAL = new TextFormatType(RTF, "RTF"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Text Format Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final TextFormatType[] VALUES_ARRAY =
        new TextFormatType[]
        {
            PLAIN_LITERAL,
            HTML_LITERAL,
            RTF_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Text Format Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Text Format Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TextFormatType get( String name )
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            TextFormatType result = VALUES_ARRAY[i];
            if (result.toString().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Text Format Type</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TextFormatType get( int value )
    {
        switch (value)
        {
            case PLAIN: return PLAIN_LITERAL;
            case HTML: return HTML_LITERAL;
            case RTF: return RTF_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private TextFormatType( int value, String name )
    {
        super( value, name );
    }

} //TextFormatType
