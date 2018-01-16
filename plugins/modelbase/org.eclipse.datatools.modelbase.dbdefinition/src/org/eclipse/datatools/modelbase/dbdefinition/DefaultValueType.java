/**
 * <copyright>
 * </copyright>
 *
 * $Id: DefaultValueType.java,v 1.1 2005/08/02 22:56:24 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Default Value Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDefaultValueType()
 * @model
 * @generated
 */
public final class DefaultValueType extends AbstractEnumerator {
	/**
	 * The '<em><b>CURRENT DATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_DATE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_DATE = 0;

	/**
	 * The '<em><b>CURRENT ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_ID_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_ID = 1;

	/**
	 * The '<em><b>CURRENT PATH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_PATH_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_PATH = 2;

	/**
	 * The '<em><b>CURRENT TIME STAMP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_TIME_STAMP_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_TIME_STAMP = 3;

	/**
	 * The '<em><b>CURRENT TIME</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_TIME_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_TIME = 4;

	/**
	 * The '<em><b>CURRENT USER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_USER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_USER = 5;

	/**
	 * The '<em><b>EXPRESSION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPRESSION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION = 6;

	/**
	 * The '<em><b>LITERAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LITERAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL = 7;

	/**
	 * The '<em><b>NO DEFAULT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_DEFAULT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_DEFAULT = 8;

	/**
	 * The '<em><b>NULL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NULL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NULL = 9;

	/**
	 * The '<em><b>CURRENT DATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT DATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_DATE
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType CURRENT_DATE_LITERAL = new DefaultValueType(CURRENT_DATE, "CURRENT_DATE"); //$NON-NLS-1$

	/**
	 * The '<em><b>CURRENT ID</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT ID</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_ID
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType CURRENT_ID_LITERAL = new DefaultValueType(CURRENT_ID, "CURRENT_ID"); //$NON-NLS-1$

	/**
	 * The '<em><b>CURRENT PATH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT PATH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_PATH
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType CURRENT_PATH_LITERAL = new DefaultValueType(CURRENT_PATH, "CURRENT_PATH"); //$NON-NLS-1$

	/**
	 * The '<em><b>CURRENT TIME STAMP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT TIME STAMP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_TIME_STAMP
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType CURRENT_TIME_STAMP_LITERAL = new DefaultValueType(CURRENT_TIME_STAMP, "CURRENT_TIME_STAMP"); //$NON-NLS-1$

	/**
	 * The '<em><b>CURRENT TIME</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT TIME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_TIME
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType CURRENT_TIME_LITERAL = new DefaultValueType(CURRENT_TIME, "CURRENT_TIME"); //$NON-NLS-1$

	/**
	 * The '<em><b>CURRENT USER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURRENT USER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT_USER
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType CURRENT_USER_LITERAL = new DefaultValueType(CURRENT_USER, "CURRENT_USER"); //$NON-NLS-1$

	/**
	 * The '<em><b>EXPRESSION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EXPRESSION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXPRESSION
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType EXPRESSION_LITERAL = new DefaultValueType(EXPRESSION, "EXPRESSION"); //$NON-NLS-1$

	/**
	 * The '<em><b>LITERAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LITERAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LITERAL
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType LITERAL_LITERAL = new DefaultValueType(LITERAL, "LITERAL"); //$NON-NLS-1$

	/**
	 * The '<em><b>NO DEFAULT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO DEFAULT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_DEFAULT
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType NO_DEFAULT_LITERAL = new DefaultValueType(NO_DEFAULT, "NO_DEFAULT"); //$NON-NLS-1$

	/**
	 * The '<em><b>NULL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NULL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NULL
	 * @generated
	 * @ordered
	 */
	public static final DefaultValueType NULL_LITERAL = new DefaultValueType(NULL, "NULL"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Default Value Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DefaultValueType[] VALUES_ARRAY =
		new DefaultValueType[] {
			CURRENT_DATE_LITERAL,
			CURRENT_ID_LITERAL,
			CURRENT_PATH_LITERAL,
			CURRENT_TIME_STAMP_LITERAL,
			CURRENT_TIME_LITERAL,
			CURRENT_USER_LITERAL,
			EXPRESSION_LITERAL,
			LITERAL_LITERAL,
			NO_DEFAULT_LITERAL,
			NULL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Default Value Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Default Value Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DefaultValueType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DefaultValueType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Default Value Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DefaultValueType get(int value) {
		switch (value) {
			case CURRENT_DATE: return CURRENT_DATE_LITERAL;
			case CURRENT_ID: return CURRENT_ID_LITERAL;
			case CURRENT_PATH: return CURRENT_PATH_LITERAL;
			case CURRENT_TIME_STAMP: return CURRENT_TIME_STAMP_LITERAL;
			case CURRENT_TIME: return CURRENT_TIME_LITERAL;
			case CURRENT_USER: return CURRENT_USER_LITERAL;
			case EXPRESSION: return EXPRESSION_LITERAL;
			case LITERAL: return LITERAL_LITERAL;
			case NO_DEFAULT: return NO_DEFAULT_LITERAL;
			case NULL: return NULL_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DefaultValueType(int value, String name) {
		super(value, name);
	}

} //DefaultValueType
