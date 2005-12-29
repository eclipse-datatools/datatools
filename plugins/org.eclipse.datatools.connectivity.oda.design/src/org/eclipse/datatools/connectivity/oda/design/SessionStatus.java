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
 * A representation of the literals of the enumeration '<em><b>Session Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Indicates to ODA host designer on how to proceed after an ODA design session exits.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSessionStatus()
 * @model
 * @generated
 */
public final class SessionStatus extends AbstractEnumerator
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

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
     * The '<em><b>Ok</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OK
     * @generated
     * @ordered
     */
    public static final SessionStatus OK_LITERAL = new SessionStatus(OK, "Ok"); //$NON-NLS-1$

    /**
     * The '<em><b>User Cancelled</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #USER_CANCELLED
     * @generated
     * @ordered
     */
    public static final SessionStatus USER_CANCELLED_LITERAL = new SessionStatus(USER_CANCELLED, "UserCancelled"); //$NON-NLS-1$

    /**
     * The '<em><b>Login Failed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOGIN_FAILED
     * @generated
     * @ordered
     */
    public static final SessionStatus LOGIN_FAILED_LITERAL = new SessionStatus(LOGIN_FAILED, "LoginFailed"); //$NON-NLS-1$

    /**
     * The '<em><b>Error</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ERROR
     * @generated
     * @ordered
     */
    public static final SessionStatus ERROR_LITERAL = new SessionStatus(ERROR, "Error"); //$NON-NLS-1$

    /**
     * An array of all the '<em><b>Session Status</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final SessionStatus[] VALUES_ARRAY =
        new SessionStatus[]
        {
            OK_LITERAL,
            USER_CANCELLED_LITERAL,
            LOGIN_FAILED_LITERAL,
            ERROR_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Session Status</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Session Status</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SessionStatus get( String name )
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            SessionStatus result = VALUES_ARRAY[i];
            if (result.toString().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Session Status</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SessionStatus get( int value )
    {
        switch (value)
        {
            case OK: return OK_LITERAL;
            case USER_CANCELLED: return USER_CANCELLED_LITERAL;
            case LOGIN_FAILED: return LOGIN_FAILED_LITERAL;
            case ERROR: return ERROR_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private SessionStatus( int value, String name )
    {
        super( value, name );
    }

} //SessionStatus
