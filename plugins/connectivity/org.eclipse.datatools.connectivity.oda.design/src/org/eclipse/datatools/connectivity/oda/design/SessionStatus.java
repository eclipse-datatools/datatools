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
 * $Id: SessionStatus.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Session Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Indicates to ODA host designer on how to proceed after an ODA design session exits.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSessionStatus()
 * @model extendedMetaData="name='SessionStatus'"
 * @generated
 */
public enum SessionStatus implements Enumerator {
    /**
     * The '<em><b>Ok</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OK
     * @generated
     * @ordered
     */
    OK_LITERAL(0, "Ok", "Ok"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>User Cancelled</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #USER_CANCELLED
     * @generated
     * @ordered
     */
    USER_CANCELLED_LITERAL(1, "UserCancelled", "UserCancelled"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Login Failed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOGIN_FAILED
     * @generated
     * @ordered
     */
    LOGIN_FAILED_LITERAL(2, "LoginFailed", "LoginFailed"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Error</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ERROR
     * @generated
     * @ordered
     */
    ERROR_LITERAL(3, "Error", "Error"); //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The '<em><b>Ok</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OK_LITERAL
     * @model name="Ok"
     * @generated
     * @ordered
     */
    public static final int OK = 0;

    /**
     * The '<em><b>User Cancelled</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #USER_CANCELLED_LITERAL
     * @model name="UserCancelled"
     * @generated
     * @ordered
     */
    public static final int USER_CANCELLED = 1;

    /**
     * The '<em><b>Login Failed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOGIN_FAILED_LITERAL
     * @model name="LoginFailed"
     * @generated
     * @ordered
     */
    public static final int LOGIN_FAILED = 2;

    /**
     * The '<em><b>Error</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ERROR_LITERAL
     * @model name="Error"
     * @generated
     * @ordered
     */
    public static final int ERROR = 3;

    /**
     * An array of all the '<em><b>Session Status</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final SessionStatus[] VALUES_ARRAY = new SessionStatus[]
    { OK_LITERAL, USER_CANCELLED_LITERAL, LOGIN_FAILED_LITERAL, ERROR_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Session Status</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<SessionStatus> VALUES = Collections
            .unmodifiableList( Arrays.asList( VALUES_ARRAY ) );

    /**
     * Returns the '<em><b>Session Status</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SessionStatus get( String literal )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            SessionStatus result = VALUES_ARRAY[i];
            if( result.toString().equals( literal ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Session Status</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SessionStatus getByName( String name )
    {
        for( int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            SessionStatus result = VALUES_ARRAY[i];
            if( result.getName().equals( name ) )
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Session Status</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SessionStatus get( int value )
    {
        switch( value )
        {
        case OK:
            return OK_LITERAL;
        case USER_CANCELLED:
            return USER_CANCELLED_LITERAL;
        case LOGIN_FAILED:
            return LOGIN_FAILED_LITERAL;
        case ERROR:
            return ERROR_LITERAL;
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
    private SessionStatus( int value, String name, String literal )
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
