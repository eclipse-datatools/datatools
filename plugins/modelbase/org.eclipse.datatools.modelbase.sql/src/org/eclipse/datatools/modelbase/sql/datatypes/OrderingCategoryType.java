/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderingCategoryType.java,v 1.3 2006/09/07 00:19:48 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.datatypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Ordering Category Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.7 User-defined types
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getOrderingCategoryType()
 * @model
 * @generated
 */
public final class OrderingCategoryType extends AbstractEnumerator {
	/**
	 * The '<em><b>RELATIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELATIVE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RELATIVE = 0;

	/**
	 * The '<em><b>MAP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MAP_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MAP = 1;

	/**
	 * The '<em><b>STATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STATE = 2;

	/**
	 * The '<em><b>RELATIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RELATIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RELATIVE
	 * @generated
	 * @ordered
	 */
	public static final OrderingCategoryType RELATIVE_LITERAL = new OrderingCategoryType(RELATIVE, "RELATIVE", "RELATIVE"); //$NON-NLS-1$

	/**
	 * The '<em><b>MAP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MAP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MAP
	 * @generated
	 * @ordered
	 */
	public static final OrderingCategoryType MAP_LITERAL = new OrderingCategoryType(MAP, "MAP", "MAP"); //$NON-NLS-1$

	/**
	 * The '<em><b>STATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATE
	 * @generated
	 * @ordered
	 */
	public static final OrderingCategoryType STATE_LITERAL = new OrderingCategoryType(STATE, "STATE", "STATE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Ordering Category Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final OrderingCategoryType[] VALUES_ARRAY =
		new OrderingCategoryType[] {
			RELATIVE_LITERAL,
			MAP_LITERAL,
			STATE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Ordering Category Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Ordering Category Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingCategoryType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OrderingCategoryType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Ordering Category Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingCategoryType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OrderingCategoryType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Ordering Category Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingCategoryType get(int value) {
		switch (value) {
			case RELATIVE: return RELATIVE_LITERAL;
			case MAP: return MAP_LITERAL;
			case STATE: return STATE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private OrderingCategoryType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //OrderingCategoryType
