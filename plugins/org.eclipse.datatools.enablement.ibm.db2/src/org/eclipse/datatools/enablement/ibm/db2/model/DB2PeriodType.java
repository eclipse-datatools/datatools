/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>DB2 Period Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2PeriodType()
 * @model
 * @generated
 */
public final class DB2PeriodType extends AbstractEnumerator {
	/**
	 * The '<em><b>SYSTEM TIME</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYSTEM TIME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_TIME_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYSTEM_TIME = 0;

	/**
	 * The '<em><b>BUSINESS TIME</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BUSINESS TIME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BUSINESS_TIME_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BUSINESS_TIME = 1;

	/**
	 * The '<em><b>SYSTEM TIME</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_TIME
	 * @generated
	 * @ordered
	 */
	public static final DB2PeriodType SYSTEM_TIME_LITERAL = new DB2PeriodType(SYSTEM_TIME, "SYSTEM_TIME", "SYSTEM_TIME"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>BUSINESS TIME</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BUSINESS_TIME
	 * @generated
	 * @ordered
	 */
	public static final DB2PeriodType BUSINESS_TIME_LITERAL = new DB2PeriodType(BUSINESS_TIME, "BUSINESS_TIME", "BUSINESS_TIME"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>DB2 Period Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DB2PeriodType[] VALUES_ARRAY =
		new DB2PeriodType[] {
			SYSTEM_TIME_LITERAL,
			BUSINESS_TIME_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>DB2 Period Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>DB2 Period Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2PeriodType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2PeriodType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2 Period Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2PeriodType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2PeriodType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2 Period Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2PeriodType get(int value) {
		switch (value) {
			case SYSTEM_TIME: return SYSTEM_TIME_LITERAL;
			case BUSINESS_TIME: return BUSINESS_TIME_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DB2PeriodType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DB2PeriodType
