/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderingType.java,v 1.3 2006/09/07 00:19:48 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.datatypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Ordering Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.7 User-defined types
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getOrderingType()
 * @model
 * @generated
 */
public final class OrderingType extends AbstractEnumerator {
	/**
	 * The '<em><b>EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUALS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EQUALS = 0;

	/**
	 * The '<em><b>FULL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FULL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FULL = 1;

	/**
	 * The '<em><b>EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EQUALS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EQUALS
	 * @generated
	 * @ordered
	 */
	public static final OrderingType EQUALS_LITERAL = new OrderingType(EQUALS, "EQUALS", "EQUALS"); //$NON-NLS-1$

	/**
	 * The '<em><b>FULL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FULL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FULL
	 * @generated
	 * @ordered
	 */
	public static final OrderingType FULL_LITERAL = new OrderingType(FULL, "FULL", "FULL"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Ordering Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final OrderingType[] VALUES_ARRAY =
		new OrderingType[] {
			EQUALS_LITERAL,
			FULL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Ordering Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Ordering Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OrderingType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Ordering Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OrderingType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Ordering Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingType get(int value) {
		switch (value) {
			case EQUALS: return EQUALS_LITERAL;
			case FULL: return FULL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private OrderingType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //OrderingType
