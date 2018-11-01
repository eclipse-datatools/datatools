/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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
 * $Id: ExpressionVariableType.java,v 1.1 2009/03/03 07:42:08 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Expression Variable Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The type of a filter expression variable.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionVariableType()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='ExpressionVariableType'"
 * @generated
 */
public enum ExpressionVariableType implements Enumerator {
    /**
     * The '<em><b>Result Set Column</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RESULT_SET_COLUMN_VALUE
     * @generated
     * @ordered
     */
    RESULT_SET_COLUMN(0, "ResultSetColumn", "ResultSetColumn"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Instance Of</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INSTANCE_OF_VALUE
     * @generated
     * @ordered
     */
    INSTANCE_OF(1, "InstanceOf", "InstanceOf"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Query Expression</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #QUERY_EXPRESSION_VALUE
     * @generated
     * @ordered
     */
    QUERY_EXPRESSION(2, "QueryExpression", "QueryExpression"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Result Set Column</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Result Set Column</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RESULT_SET_COLUMN
     * @model name="ResultSetColumn"
     * @generated
     * @ordered
     */
    public static final int RESULT_SET_COLUMN_VALUE = 0;

    /**
     * The '<em><b>Instance Of</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Instance Of</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INSTANCE_OF
     * @model name="InstanceOf"
     * @generated
     * @ordered
     */
    public static final int INSTANCE_OF_VALUE = 1;

    /**
     * The '<em><b>Query Expression</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Query Expression</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #QUERY_EXPRESSION
     * @model name="QueryExpression"
     * @generated
     * @ordered
     */
    public static final int QUERY_EXPRESSION_VALUE = 2;

    /**
     * An array of all the '<em><b>Expression Variable Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ExpressionVariableType[] VALUES_ARRAY = new ExpressionVariableType[]
    { RESULT_SET_COLUMN, INSTANCE_OF, QUERY_EXPRESSION, };

    /**
     * A public read-only list of all the '<em><b>Expression Variable Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<ExpressionVariableType> VALUES = Collections
            .unmodifiableList( Arrays.asList( VALUES_ARRAY ) );

    /**
     * Returns the '<em><b>Expression Variable Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ExpressionVariableType get( String literal )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            ExpressionVariableType result = VALUES_ARRAY[i];
            if( result.toString().equals( literal ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Expression Variable Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ExpressionVariableType getByName( String name )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            ExpressionVariableType result = VALUES_ARRAY[i];
            if( result.getName().equals( name ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Expression Variable Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ExpressionVariableType get( int value )
    {
        switch( value )
        {
        case RESULT_SET_COLUMN_VALUE:
            return RESULT_SET_COLUMN;
        case INSTANCE_OF_VALUE:
            return INSTANCE_OF;
        case QUERY_EXPRESSION_VALUE:
            return QUERY_EXPRESSION;
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
    private ExpressionVariableType( int value, String name, String literal )
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

} //ExpressionVariableType
