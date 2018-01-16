/**
 * <copyright>
 * </copyright>
 *
 * $Id: SystemDefinedDefaultType.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>System Defined Default Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSystemDefinedDefaultType()
 * @model
 * @generated
 */
public final class SystemDefinedDefaultType extends AbstractEnumerator {
    /**
	 * The '<em><b>AUTOINCREMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AUTOINCREMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AUTOINCREMENT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AUTOINCREMENT = 0;

    /**
	 * The '<em><b>CURRENT DATABASE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT DATABASE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_DATABASE_LITERAL
	 * @model literal="CURRENT DATABASE"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_DATABASE = 1;

    /**
	 * The '<em><b>CURRENT REMOTE USER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT REMOTE USER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_REMOTE_USER_LITERAL
	 * @model literal="CURRENT REMOTE USER"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_REMOTE_USER = 2;

    /**
	 * The '<em><b>CURRENT UTC TIMESTAMP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT UTC TIMESTAMP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_UTC_TIMESTAMP_LITERAL
	 * @model literal="CURRENT UTC TIMESTAMP"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_UTC_TIMESTAMP = 3;

    /**
	 * The '<em><b>GLOBAL AUTOINCREMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GLOBAL AUTOINCREMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GLOBAL_AUTOINCREMENT_LITERAL
	 * @model literal="GLOBAL AUTOINCREMENT"
	 * @generated
	 * @ordered
	 */
	public static final int GLOBAL_AUTOINCREMENT = 4;

    /**
	 * The '<em><b>NULL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NULL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NULL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NULL = 5;

    /**
	 * The '<em><b>TIMESTAMP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TIMESTAMP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMESTAMP_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TIMESTAMP = 6;

    /**
	 * The '<em><b>UTC TIMESTAMP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UTC TIMESTAMP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UTC_TIMESTAMP_LITERAL
	 * @model literal="UTC TIMESTAMP"
	 * @generated
	 * @ordered
	 */
	public static final int UTC_TIMESTAMP = 7;

    /**
	 * The '<em><b>LAST USER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LAST USER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LAST_USER_LITERAL
	 * @model literal="LAST USER"
	 * @generated
	 * @ordered
	 */
	public static final int LAST_USER = 8;

    /**
	 * The '<em><b>CURRENT DATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT DATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_DATE_LITERAL
	 * @model literal="CURRENT DATE"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_DATE = 9;

    /**
	 * The '<em><b>CURRENT TIME</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT TIME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_TIME_LITERAL
	 * @model literal="CURRENT TIME"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_TIME = 10;

    /**
	 * The '<em><b>CURRENT TIMESTAMP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT TIMESTAMP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_TIMESTAMP_LITERAL
	 * @model literal="CURRENT TIMESTAMP"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_TIMESTAMP = 11;

    /**
	 * The '<em><b>CURRENT USER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT USER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_USER_LITERAL
	 * @model literal="CURRENT USER"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_USER = 12;

    /**
	 * The '<em><b>CURRENT PUBLISHER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT PUBLISHER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_PUBLISHER_LITERAL
	 * @model literal="CURRENT PUBLISHER"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_PUBLISHER = 13;

    /**
	 * The '<em><b>USER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USER = 14;

    /**
	 * The '<em><b>AUTOINCREMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTOINCREMENT
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType AUTOINCREMENT_LITERAL = new SystemDefinedDefaultType(AUTOINCREMENT, "AUTOINCREMENT", "AUTOINCREMENT");

    /**
	 * The '<em><b>CURRENT DATABASE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_DATABASE
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType CURRENT_DATABASE_LITERAL = new SystemDefinedDefaultType(CURRENT_DATABASE, "CURRENT_DATABASE", "CURRENT DATABASE");

    /**
	 * The '<em><b>CURRENT REMOTE USER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_REMOTE_USER
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType CURRENT_REMOTE_USER_LITERAL = new SystemDefinedDefaultType(CURRENT_REMOTE_USER, "CURRENT_REMOTE_USER", "CURRENT REMOTE USER");

    /**
	 * The '<em><b>CURRENT UTC TIMESTAMP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_UTC_TIMESTAMP
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType CURRENT_UTC_TIMESTAMP_LITERAL = new SystemDefinedDefaultType(CURRENT_UTC_TIMESTAMP, "CURRENT_UTC_TIMESTAMP", "CURRENT UTC TIMESTAMP");

    /**
	 * The '<em><b>GLOBAL AUTOINCREMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GLOBAL_AUTOINCREMENT
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType GLOBAL_AUTOINCREMENT_LITERAL = new SystemDefinedDefaultType(GLOBAL_AUTOINCREMENT, "GLOBAL_AUTOINCREMENT", "GLOBAL AUTOINCREMENT");

    /**
	 * The '<em><b>NULL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NULL
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType NULL_LITERAL = new SystemDefinedDefaultType(NULL, "NULL", "NULL");

    /**
	 * The '<em><b>TIMESTAMP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMESTAMP
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType TIMESTAMP_LITERAL = new SystemDefinedDefaultType(TIMESTAMP, "TIMESTAMP", "TIMESTAMP");

    /**
	 * The '<em><b>UTC TIMESTAMP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UTC_TIMESTAMP
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType UTC_TIMESTAMP_LITERAL = new SystemDefinedDefaultType(UTC_TIMESTAMP, "UTC_TIMESTAMP", "UTC TIMESTAMP");

    /**
	 * The '<em><b>LAST USER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LAST_USER
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType LAST_USER_LITERAL = new SystemDefinedDefaultType(LAST_USER, "LAST_USER", "LAST USER");

    /**
	 * The '<em><b>CURRENT DATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_DATE
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType CURRENT_DATE_LITERAL = new SystemDefinedDefaultType(CURRENT_DATE, "CURRENT_DATE", "CURRENT DATE");

    /**
	 * The '<em><b>CURRENT TIME</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_TIME
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType CURRENT_TIME_LITERAL = new SystemDefinedDefaultType(CURRENT_TIME, "CURRENT_TIME", "CURRENT TIME");

    /**
	 * The '<em><b>CURRENT TIMESTAMP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_TIMESTAMP
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType CURRENT_TIMESTAMP_LITERAL = new SystemDefinedDefaultType(CURRENT_TIMESTAMP, "CURRENT_TIMESTAMP", "CURRENT TIMESTAMP");

    /**
	 * The '<em><b>CURRENT USER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_USER
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType CURRENT_USER_LITERAL = new SystemDefinedDefaultType(CURRENT_USER, "CURRENT_USER", "CURRENT USER");

    /**
	 * The '<em><b>CURRENT PUBLISHER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_PUBLISHER
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType CURRENT_PUBLISHER_LITERAL = new SystemDefinedDefaultType(CURRENT_PUBLISHER, "CURRENT_PUBLISHER", "CURRENT PUBLISHER");

    /**
	 * The '<em><b>USER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USER
	 * @generated
	 * @ordered
	 */
	public static final SystemDefinedDefaultType USER_LITERAL = new SystemDefinedDefaultType(USER, "USER", "USER");

    /**
	 * An array of all the '<em><b>System Defined Default Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SystemDefinedDefaultType[] VALUES_ARRAY =
        new SystemDefinedDefaultType[] {
			AUTOINCREMENT_LITERAL,
			CURRENT_DATABASE_LITERAL,
			CURRENT_REMOTE_USER_LITERAL,
			CURRENT_UTC_TIMESTAMP_LITERAL,
			GLOBAL_AUTOINCREMENT_LITERAL,
			NULL_LITERAL,
			TIMESTAMP_LITERAL,
			UTC_TIMESTAMP_LITERAL,
			LAST_USER_LITERAL,
			CURRENT_DATE_LITERAL,
			CURRENT_TIME_LITERAL,
			CURRENT_TIMESTAMP_LITERAL,
			CURRENT_USER_LITERAL,
			CURRENT_PUBLISHER_LITERAL,
			USER_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>System Defined Default Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>System Defined Default Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SystemDefinedDefaultType get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SystemDefinedDefaultType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>System Defined Default Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SystemDefinedDefaultType getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SystemDefinedDefaultType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>System Defined Default Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SystemDefinedDefaultType get(int value)
    {
		switch (value) {
			case AUTOINCREMENT: return AUTOINCREMENT_LITERAL;
			case CURRENT_DATABASE: return CURRENT_DATABASE_LITERAL;
			case CURRENT_REMOTE_USER: return CURRENT_REMOTE_USER_LITERAL;
			case CURRENT_UTC_TIMESTAMP: return CURRENT_UTC_TIMESTAMP_LITERAL;
			case GLOBAL_AUTOINCREMENT: return GLOBAL_AUTOINCREMENT_LITERAL;
			case NULL: return NULL_LITERAL;
			case TIMESTAMP: return TIMESTAMP_LITERAL;
			case UTC_TIMESTAMP: return UTC_TIMESTAMP_LITERAL;
			case LAST_USER: return LAST_USER_LITERAL;
			case CURRENT_DATE: return CURRENT_DATE_LITERAL;
			case CURRENT_TIME: return CURRENT_TIME_LITERAL;
			case CURRENT_TIMESTAMP: return CURRENT_TIMESTAMP_LITERAL;
			case CURRENT_USER: return CURRENT_USER_LITERAL;
			case CURRENT_PUBLISHER: return CURRENT_PUBLISHER_LITERAL;
			case USER: return USER_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SystemDefinedDefaultType(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //SystemDefinedDefaultType
