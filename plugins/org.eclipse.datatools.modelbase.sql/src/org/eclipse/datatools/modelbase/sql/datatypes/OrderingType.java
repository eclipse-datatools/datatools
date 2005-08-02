/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderingType.java,v 1.4 2005/06/15 18:15:25 ledunnel Exp $
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
	public static final OrderingType EQUALS_LITERAL = new OrderingType(EQUALS, "EQUALS"); //$NON-NLS-1$

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
	public static final OrderingType FULL_LITERAL = new OrderingType(FULL, "FULL"); //$NON-NLS-1$

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
	 * Returns the '<em><b>Ordering Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OrderingType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Ordering Type</b></em>' literal with the specified value.
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
	private OrderingType(int value, String name) {
		super(value, name);
	}

} //OrderingType
