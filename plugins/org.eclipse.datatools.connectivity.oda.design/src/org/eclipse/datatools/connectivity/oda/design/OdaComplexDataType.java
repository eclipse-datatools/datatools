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
 * A representation of the literals of the enumeration '<em><b>Oda Complex Data Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * ODA complex data types.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaComplexDataType()
 * @model
 * @generated
 */
public final class OdaComplexDataType extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

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
     * The '<em><b>Structure</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRUCTURE
     * @generated
     * @ordered
     */
    public static final OdaComplexDataType STRUCTURE_LITERAL = new OdaComplexDataType(STRUCTURE, "Structure"); //$NON-NLS-1$

    /**
     * The '<em><b>Table</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TABLE
     * @generated
     * @ordered
     */
    public static final OdaComplexDataType TABLE_LITERAL = new OdaComplexDataType(TABLE, "Table"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Oda Complex Data Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final OdaComplexDataType[] VALUES_ARRAY =
        new OdaComplexDataType[]
        {
            STRUCTURE_LITERAL,
            TABLE_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Oda Complex Data Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Oda Complex Data Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OdaComplexDataType get( String name )
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            OdaComplexDataType result = VALUES_ARRAY[i];
            if (result.toString().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Oda Complex Data Type</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OdaComplexDataType get( int value )
    {
        switch (value)
        {
            case STRUCTURE: return STRUCTURE_LITERAL;
            case TABLE: return TABLE_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private OdaComplexDataType( int value, String name )
    {
        super( value, name );
    }

} //OdaComplexDataType
