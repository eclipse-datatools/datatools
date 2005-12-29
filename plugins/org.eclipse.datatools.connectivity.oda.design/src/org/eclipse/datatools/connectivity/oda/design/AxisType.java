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
 * A representation of the literals of the enumeration '<em><b>Axis Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Axis type of a result set column.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisType()
 * @model
 * @generated
 */
public final class AxisType extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Dimension Member</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIMENSION_MEMBER_LITERAL
     * @model name="DimensionMember"
     * @generated
     * @ordered
     */
    public static final int DIMENSION_MEMBER = 0;

    /**
     * The '<em><b>Dimension Attribute</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIMENSION_ATTRIBUTE_LITERAL
     * @model name="DimensionAttribute"
     * @generated
     * @ordered
     */
    public static final int DIMENSION_ATTRIBUTE = 1;

    /**
     * The '<em><b>Measure</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MEASURE_LITERAL
     * @model name="Measure"
     * @generated
     * @ordered
     */
    public static final int MEASURE = 2;

    /**
     * The '<em><b>Dimension Member</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIMENSION_MEMBER
     * @generated
     * @ordered
     */
    public static final AxisType DIMENSION_MEMBER_LITERAL = new AxisType(DIMENSION_MEMBER, "DimensionMember"); //$NON-NLS-1$

    /**
     * The '<em><b>Dimension Attribute</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIMENSION_ATTRIBUTE
     * @generated
     * @ordered
     */
    public static final AxisType DIMENSION_ATTRIBUTE_LITERAL = new AxisType(DIMENSION_ATTRIBUTE, "DimensionAttribute"); //$NON-NLS-1$

    /**
     * The '<em><b>Measure</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MEASURE
     * @generated
     * @ordered
     */
    public static final AxisType MEASURE_LITERAL = new AxisType(MEASURE, "Measure"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Axis Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final AxisType[] VALUES_ARRAY =
        new AxisType[]
        {
            DIMENSION_MEMBER_LITERAL,
            DIMENSION_ATTRIBUTE_LITERAL,
            MEASURE_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Axis Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Axis Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static AxisType get( String name )
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            AxisType result = VALUES_ARRAY[i];
            if (result.toString().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Axis Type</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static AxisType get( int value )
    {
        switch (value)
        {
            case DIMENSION_MEMBER: return DIMENSION_MEMBER_LITERAL;
            case DIMENSION_ATTRIBUTE: return DIMENSION_ATTRIBUTE_LITERAL;
            case MEASURE: return MEASURE_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private AxisType( int value, String name )
    {
        super( value, name );
    }

} //AxisType
