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
 * $Id: AxisType.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Axis Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Axis type of a result set column.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisType()
 * @model extendedMetaData="name='AxisType'"
 * @generated
 */
public enum AxisType implements Enumerator {
    /**
     * The '<em><b>Dimension Member</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIMENSION_MEMBER
     * @generated
     * @ordered
     */
    DIMENSION_MEMBER_LITERAL(0, "DimensionMember", "DimensionMember"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Dimension Attribute</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIMENSION_ATTRIBUTE
     * @generated
     * @ordered
     */
    DIMENSION_ATTRIBUTE_LITERAL(1, "DimensionAttribute", "DimensionAttribute"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Measure</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MEASURE
     * @generated
     * @ordered
     */
    MEASURE_LITERAL(2, "Measure", "Measure"); //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

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
     * An array of all the '<em><b>Axis Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final AxisType[] VALUES_ARRAY = new AxisType[]
    { DIMENSION_MEMBER_LITERAL, DIMENSION_ATTRIBUTE_LITERAL, MEASURE_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Axis Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<AxisType> VALUES = Collections
            .unmodifiableList( Arrays.asList( VALUES_ARRAY ) );

    /**
     * Returns the '<em><b>Axis Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static AxisType get( String literal )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            AxisType result = VALUES_ARRAY[i];
            if( result.toString().equals( literal ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Axis Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static AxisType getByName( String name )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            AxisType result = VALUES_ARRAY[i];
            if( result.getName().equals( name ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Axis Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static AxisType get( int value )
    {
        switch( value )
        {
        case DIMENSION_MEMBER:
            return DIMENSION_MEMBER_LITERAL;
        case DIMENSION_ATTRIBUTE:
            return DIMENSION_ATTRIBUTE_LITERAL;
        case MEASURE:
            return MEASURE_LITERAL;
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
    private AxisType( int value, String name, String literal )
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
