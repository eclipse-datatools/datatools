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
 * A representation of the literals of the enumeration '<em><b>Element Nullability</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Indicates whether a data element's value can be null.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getElementNullability()
 * @model
 * @generated
 */
public final class ElementNullability extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Unknown</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNKNOWN_LITERAL
     * @model name="Unknown"
     * @generated
     * @ordered
     */
    public static final int UNKNOWN = 0;

    /**
     * The '<em><b>Nullable</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NULLABLE_LITERAL
     * @model name="Nullable"
     * @generated
     * @ordered
     */
    public static final int NULLABLE = 1;

    /**
     * The '<em><b>Not Nullable</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_NULLABLE_LITERAL
     * @model name="NotNullable"
     * @generated
     * @ordered
     */
    public static final int NOT_NULLABLE = 2;

    /**
     * The '<em><b>Unknown</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNKNOWN
     * @generated
     * @ordered
     */
    public static final ElementNullability UNKNOWN_LITERAL = new ElementNullability(UNKNOWN, "Unknown"); //$NON-NLS-1$

    /**
     * The '<em><b>Nullable</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NULLABLE
     * @generated
     * @ordered
     */
    public static final ElementNullability NULLABLE_LITERAL = new ElementNullability(NULLABLE, "Nullable"); //$NON-NLS-1$

    /**
     * The '<em><b>Not Nullable</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_NULLABLE
     * @generated
     * @ordered
     */
    public static final ElementNullability NOT_NULLABLE_LITERAL = new ElementNullability(NOT_NULLABLE, "NotNullable"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Element Nullability</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ElementNullability[] VALUES_ARRAY =
        new ElementNullability[]
        {
            UNKNOWN_LITERAL,
            NULLABLE_LITERAL,
            NOT_NULLABLE_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Element Nullability</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Element Nullability</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ElementNullability get( String name )
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            ElementNullability result = VALUES_ARRAY[i];
            if (result.toString().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Element Nullability</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ElementNullability get( int value )
    {
        switch (value)
        {
            case UNKNOWN: return UNKNOWN_LITERAL;
            case NULLABLE: return NULLABLE_LITERAL;
            case NOT_NULLABLE: return NOT_NULLABLE_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private ElementNullability( int value, String name )
    {
        super( value, name );
    }

} //ElementNullability
