/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
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
 * $Id: HorizontalAlignment.java,v 1.3 2009/04/24 03:20:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Horizontal Alignment</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Horizontal display alignment of the data value.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getHorizontalAlignment()
 * @model extendedMetaData="name='HorizontalAlignment'"
 * @generated
 */
public enum HorizontalAlignment implements Enumerator {
    /**
     * The '<em><b>Automatic</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #AUTOMATIC
     * @generated
     * @ordered
     */
    AUTOMATIC_LITERAL(0, "Automatic", "Automatic"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Left</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LEFT
     * @generated
     * @ordered
     */
    LEFT_LITERAL(1, "Left", "Left"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Center</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CENTER
     * @generated
     * @ordered
     */
    CENTER_LITERAL(2, "Center", "Center"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Right</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RIGHT
     * @generated
     * @ordered
     */
    RIGHT_LITERAL(3, "Right", "Right"), /**
                                         * The '<em><b>Left And Right</b></em>' literal object.
                                         * <!-- begin-user-doc -->
                                         * <!-- end-user-doc -->
                                         * @see #LEFT_AND_RIGHT
                                         * @generated
                                         * @ordered
                                         */
    LEFT_AND_RIGHT_LITERAL(4, "LeftAndRight", "Left and Right"); //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Automatic</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #AUTOMATIC_LITERAL
     * @model name="Automatic"
     * @generated
     * @ordered
     */
    public static final int AUTOMATIC = 0;

    /**
     * The '<em><b>Left</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LEFT_LITERAL
     * @model name="Left"
     * @generated
     * @ordered
     */
    public static final int LEFT = 1;

    /**
     * The '<em><b>Center</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CENTER_LITERAL
     * @model name="Center"
     * @generated
     * @ordered
     */
    public static final int CENTER = 2;

    /**
     * The '<em><b>Right</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RIGHT_LITERAL
     * @model name="Right"
     * @generated
     * @ordered
     */
    public static final int RIGHT = 3;

    /**
     * The '<em><b>Left And Right</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Left And Right</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LEFT_AND_RIGHT_LITERAL
     * @model name="LeftAndRight" literal="Left and Right"
     * @generated
     * @ordered
     * @since 3.3.1 (DTP 1.8.1)
     */
    public static final int LEFT_AND_RIGHT = 4;

    /**
     * An array of all the '<em><b>Horizontal Alignment</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final HorizontalAlignment[] VALUES_ARRAY = new HorizontalAlignment[]
    { AUTOMATIC_LITERAL, LEFT_LITERAL, CENTER_LITERAL, RIGHT_LITERAL,
            LEFT_AND_RIGHT_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Horizontal Alignment</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<HorizontalAlignment> VALUES = Collections
            .unmodifiableList( Arrays.asList( VALUES_ARRAY ) );

    /**
     * Returns the '<em><b>Horizontal Alignment</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static HorizontalAlignment get( String literal )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            HorizontalAlignment result = VALUES_ARRAY[i];
            if( result.toString().equals( literal ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Horizontal Alignment</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static HorizontalAlignment getByName( String name )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            HorizontalAlignment result = VALUES_ARRAY[i];
            if( result.getName().equals( name ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Horizontal Alignment</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static HorizontalAlignment get( int value )
    {
        switch( value )
        {
        case AUTOMATIC:
            return AUTOMATIC_LITERAL;
        case LEFT:
            return LEFT_LITERAL;
        case CENTER:
            return CENTER_LITERAL;
        case RIGHT:
            return RIGHT_LITERAL;
        case LEFT_AND_RIGHT:
            return LEFT_AND_RIGHT_LITERAL;
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
    private HorizontalAlignment( int value, String name, String literal )
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
