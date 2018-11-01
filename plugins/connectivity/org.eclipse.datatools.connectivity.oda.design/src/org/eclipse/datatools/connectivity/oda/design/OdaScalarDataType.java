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
 * $Id: OdaScalarDataType.java,v 1.3 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Oda Scalar Data Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * ODA scalar data types.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaScalarDataType()
 * @model extendedMetaData="name='OdaScalarDataType'"
 * @generated
 */
public enum OdaScalarDataType implements Enumerator {
    /**
     * The '<em><b>Date</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DATE
     * @generated
     * @ordered
     */
    DATE_LITERAL(0, "Date", "Date"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Double</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOUBLE
     * @generated
     * @ordered
     */
    DOUBLE_LITERAL(1, "Double", "Double"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Integer</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTEGER
     * @generated
     * @ordered
     */
    INTEGER_LITERAL(2, "Integer", "Integer"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>String</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRING
     * @generated
     * @ordered
     */
    STRING_LITERAL(3, "String", "String"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Time</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TIME
     * @generated
     * @ordered
     */
    TIME_LITERAL(4, "Time", "Time"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Timestamp</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TIMESTAMP
     * @generated
     * @ordered
     */
    TIMESTAMP_LITERAL(5, "Timestamp", "Timestamp"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Decimal</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DECIMAL
     * @generated
     * @ordered
     */
    DECIMAL_LITERAL(6, "Decimal", "Decimal"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Blob</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BLOB
     * @generated
     * @ordered
     */
    BLOB_LITERAL(7, "Blob", "Blob"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Clob</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CLOB
     * @generated
     * @ordered
     */
    CLOB_LITERAL(8, "Clob", "Clob"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Boolean</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BOOLEAN
     * @generated
     * @ordered
     */
    BOOLEAN_LITERAL(9, "Boolean", "Boolean"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Java Object</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #JAVA_OBJECT
     * @generated
     * @ordered
     */
    JAVA_OBJECT_LITERAL(10, "JavaObject", "JavaObject"); //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Date</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DATE_LITERAL
     * @model name="Date"
     * @generated
     * @ordered
     */
    public static final int DATE = 0;

    /**
     * The '<em><b>Double</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOUBLE_LITERAL
     * @model name="Double"
     * @generated
     * @ordered
     */
    public static final int DOUBLE = 1;

    /**
     * The '<em><b>Integer</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTEGER_LITERAL
     * @model name="Integer"
     * @generated
     * @ordered
     */
    public static final int INTEGER = 2;

    /**
     * The '<em><b>String</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRING_LITERAL
     * @model name="String"
     * @generated
     * @ordered
     */
    public static final int STRING = 3;

    /**
     * The '<em><b>Time</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TIME_LITERAL
     * @model name="Time"
     * @generated
     * @ordered
     */
    public static final int TIME = 4;

    /**
     * The '<em><b>Timestamp</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TIMESTAMP_LITERAL
     * @model name="Timestamp"
     * @generated
     * @ordered
     */
    public static final int TIMESTAMP = 5;

    /**
     * The '<em><b>Decimal</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DECIMAL_LITERAL
     * @model name="Decimal"
     * @generated
     * @ordered
     */
    public static final int DECIMAL = 6;

    /**
     * The '<em><b>Blob</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BLOB_LITERAL
     * @model name="Blob"
     * @generated
     * @ordered
     */
    public static final int BLOB = 7;

    /**
     * The '<em><b>Clob</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CLOB_LITERAL
     * @model name="Clob"
     * @generated
     * @ordered
     */
    public static final int CLOB = 8;

    /**
     * The '<em><b>Boolean</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Boolean</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BOOLEAN_LITERAL
     * @model name="Boolean"
     * @generated
     * @ordered
     */
    public static final int BOOLEAN = 9;

    /**
     * The '<em><b>Java Object</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Java Object</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #JAVA_OBJECT_LITERAL
     * @model name="JavaObject"
     * @generated
     * @ordered
     */
    public static final int JAVA_OBJECT = 10;

    /**
     * An array of all the '<em><b>Oda Scalar Data Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final OdaScalarDataType[] VALUES_ARRAY = new OdaScalarDataType[]
    { DATE_LITERAL, DOUBLE_LITERAL, INTEGER_LITERAL, STRING_LITERAL,
            TIME_LITERAL, TIMESTAMP_LITERAL, DECIMAL_LITERAL, BLOB_LITERAL,
            CLOB_LITERAL, BOOLEAN_LITERAL, JAVA_OBJECT_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Oda Scalar Data Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<OdaScalarDataType> VALUES = Collections
            .unmodifiableList( Arrays.asList( VALUES_ARRAY ) );

    /**
     * Returns the '<em><b>Oda Scalar Data Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OdaScalarDataType get( String literal )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            OdaScalarDataType result = VALUES_ARRAY[i];
            if( result.toString().equals( literal ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Oda Scalar Data Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OdaScalarDataType getByName( String name )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            OdaScalarDataType result = VALUES_ARRAY[i];
            if( result.getName().equals( name ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Oda Scalar Data Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OdaScalarDataType get( int value )
    {
        switch( value )
        {
        case DATE:
            return DATE_LITERAL;
        case DOUBLE:
            return DOUBLE_LITERAL;
        case INTEGER:
            return INTEGER_LITERAL;
        case STRING:
            return STRING_LITERAL;
        case TIME:
            return TIME_LITERAL;
        case TIMESTAMP:
            return TIMESTAMP_LITERAL;
        case DECIMAL:
            return DECIMAL_LITERAL;
        case BLOB:
            return BLOB_LITERAL;
        case CLOB:
            return CLOB_LITERAL;
        case BOOLEAN:
            return BOOLEAN_LITERAL;
        case JAVA_OBJECT:
            return JAVA_OBJECT_LITERAL;
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
    private OdaScalarDataType( int value, String name, String literal )
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
