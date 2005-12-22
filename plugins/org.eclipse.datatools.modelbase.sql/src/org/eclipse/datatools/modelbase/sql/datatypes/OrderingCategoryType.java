/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderingCategoryType.java,v 1.1 2005/08/02 22:56:27 ledunnel Exp $
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
 * 4.7 User-defined types
 * 
 * A user-defined type is a schema object, identified by a <user-defined type name>.
 * 
 * A user-defined type is described by a user-defined type descriptor. A user-defined type descriptor contains:
 *  - The name of the user-defined type (<user-defined type name> ). This is the type designator of that type, used in type precedence lists (see Subclause 9.5, "Type precedence list determination").
 *  - An indication of whether the user-defined type is a structured type or a distinct type.
 *  - The ordering form for the user-defined type (EQUALS , FULL , or NONE ).
 *  - The ordering category for the user-defined type (RELATIVE , MAP , or STATE ).
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
	public static final OrderingCategoryType RELATIVE_LITERAL = new OrderingCategoryType(RELATIVE, "RELATIVE"); //$NON-NLS-1$

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
	public static final OrderingCategoryType MAP_LITERAL = new OrderingCategoryType(MAP, "MAP"); //$NON-NLS-1$

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
	public static final OrderingCategoryType STATE_LITERAL = new OrderingCategoryType(STATE, "STATE"); //$NON-NLS-1$

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
	 * Returns the '<em><b>Ordering Category Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingCategoryType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OrderingCategoryType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Ordering Category Type</b></em>' literal with the specified value.
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
	private OrderingCategoryType(int value, String name) {
		super(value, name);
	}

} //OrderingCategoryType
