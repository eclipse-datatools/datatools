/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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
 * $Id: HorizontalAlignment.java,v 1.1 2005/12/29 04:17:56 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Horizontal Alignment</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Horizontal display alignment of the data value.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getHorizontalAlignment()
 * @model
 * @generated
 */
public final class HorizontalAlignment extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

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
     * The '<em><b>Automatic</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #AUTOMATIC
     * @generated
     * @ordered
     */
    public static final HorizontalAlignment AUTOMATIC_LITERAL = new HorizontalAlignment(
            AUTOMATIC, "Automatic", "Automatic" ); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Left</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LEFT
     * @generated
     * @ordered
     */
    public static final HorizontalAlignment LEFT_LITERAL = new HorizontalAlignment(
            LEFT, "Left", "Left" ); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Center</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CENTER
     * @generated
     * @ordered
     */
    public static final HorizontalAlignment CENTER_LITERAL = new HorizontalAlignment(
            CENTER, "Center", "Center" ); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Right</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RIGHT
     * @generated
     * @ordered
     */
    public static final HorizontalAlignment RIGHT_LITERAL = new HorizontalAlignment(
            RIGHT, "Right", "Right" ); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * An array of all the '<em><b>Horizontal Alignment</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final HorizontalAlignment[] VALUES_ARRAY = new HorizontalAlignment[]
    { AUTOMATIC_LITERAL, LEFT_LITERAL, CENTER_LITERAL, RIGHT_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Horizontal Alignment</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList( Arrays
            .asList( VALUES_ARRAY ) );

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
        }
        return null;
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private HorizontalAlignment( int value, String name, String literal )
    {
        super( value, name, literal );
    }

} //HorizontalAlignment
