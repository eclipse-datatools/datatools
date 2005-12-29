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
 * A representation of the literals of the enumeration '<em><b>Parameter Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Indicates whether a parameter is of input and/or output mode.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterMode()
 * @model
 * @generated
 */
public final class ParameterMode extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>In</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IN_LITERAL
     * @model name="In"
     * @generated
     * @ordered
     */
    public static final int IN = 0;

    /**
     * The '<em><b>Out</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OUT_LITERAL
     * @model name="Out"
     * @generated
     * @ordered
     */
    public static final int OUT = 1;

    /**
     * The '<em><b>In Out</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IN_OUT_LITERAL
     * @model name="InOut"
     * @generated
     * @ordered
     */
    public static final int IN_OUT = 2;

    /**
     * The '<em><b>In</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IN
     * @generated
     * @ordered
     */
    public static final ParameterMode IN_LITERAL = new ParameterMode(IN, "In"); //$NON-NLS-1$

    /**
     * The '<em><b>Out</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OUT
     * @generated
     * @ordered
     */
    public static final ParameterMode OUT_LITERAL = new ParameterMode(OUT, "Out"); //$NON-NLS-1$

    /**
     * The '<em><b>In Out</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IN_OUT
     * @generated
     * @ordered
     */
    public static final ParameterMode IN_OUT_LITERAL = new ParameterMode(IN_OUT, "InOut"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Parameter Mode</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ParameterMode[] VALUES_ARRAY =
        new ParameterMode[]
        {
            IN_LITERAL,
            OUT_LITERAL,
            IN_OUT_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Parameter Mode</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Parameter Mode</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ParameterMode get( String name )
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            ParameterMode result = VALUES_ARRAY[i];
            if (result.toString().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Parameter Mode</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ParameterMode get( int value )
    {
        switch (value)
        {
            case IN: return IN_LITERAL;
            case OUT: return OUT_LITERAL;
            case IN_OUT: return IN_OUT_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private ParameterMode( int value, String name )
    {
        super( value, name );
    }

} //ParameterMode
