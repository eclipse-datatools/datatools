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
 * $Id: OdaComplexDataType.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Oda Complex Data Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * ODA complex data types.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaComplexDataType()
 * @model extendedMetaData="name='OdaComplexDataType'"
 * @generated
 */
public enum OdaComplexDataType implements Enumerator {
    /**
     * The '<em><b>Structure</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRUCTURE
     * @generated
     * @ordered
     */
    STRUCTURE_LITERAL(0, "Structure", "Structure"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Table</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TABLE
     * @generated
     * @ordered
     */
    TABLE_LITERAL(1, "Table", "Table"); //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Structure</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRUCTURE_LITERAL
     * @model name="Structure"
     * @generated
     * @ordered
     */
    public static final int STRUCTURE = 0;

    /**
     * The '<em><b>Table</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TABLE_LITERAL
     * @model name="Table"
     * @generated
     * @ordered
     */
    public static final int TABLE = 1;

    /**
     * An array of all the '<em><b>Oda Complex Data Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final OdaComplexDataType[] VALUES_ARRAY = new OdaComplexDataType[]
    { STRUCTURE_LITERAL, TABLE_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Oda Complex Data Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<OdaComplexDataType> VALUES = Collections
            .unmodifiableList( Arrays.asList( VALUES_ARRAY ) );

    /**
     * Returns the '<em><b>Oda Complex Data Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OdaComplexDataType get( String literal )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            OdaComplexDataType result = VALUES_ARRAY[i];
            if( result.toString().equals( literal ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Oda Complex Data Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OdaComplexDataType getByName( String name )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            OdaComplexDataType result = VALUES_ARRAY[i];
            if( result.getName().equals( name ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Oda Complex Data Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OdaComplexDataType get( int value )
    {
        switch( value )
        {
        case STRUCTURE:
            return STRUCTURE_LITERAL;
        case TABLE:
            return TABLE_LITERAL;
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
    private OdaComplexDataType( int value, String name, String literal )
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
