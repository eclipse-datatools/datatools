/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Security Label Component Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityLabelComponentType()
 * @model
 * @generated
 */
public final class LUWSecurityLabelComponentType extends AbstractEnumerator {
	/**
	 * The '<em><b>SET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SET_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SET = 0;

	/**
	 * The '<em><b>ARRAY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ARRAY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ARRAY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY = 1;

	/**
	 * The '<em><b>TREE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TREE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TREE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TREE = 2;

	/**
	 * The '<em><b>SET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SET
	 * @generated
	 * @ordered
	 */
	public static final LUWSecurityLabelComponentType SET_LITERAL = new LUWSecurityLabelComponentType(SET, "SET", "SET"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ARRAY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ARRAY
	 * @generated
	 * @ordered
	 */
	public static final LUWSecurityLabelComponentType ARRAY_LITERAL = new LUWSecurityLabelComponentType(ARRAY, "ARRAY", "ARRAY"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>TREE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TREE
	 * @generated
	 * @ordered
	 */
	public static final LUWSecurityLabelComponentType TREE_LITERAL = new LUWSecurityLabelComponentType(TREE, "TREE", "TREE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Security Label Component Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LUWSecurityLabelComponentType[] VALUES_ARRAY =
		new LUWSecurityLabelComponentType[] {
			SET_LITERAL,
			ARRAY_LITERAL,
			TREE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Security Label Component Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Security Label Component Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWSecurityLabelComponentType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWSecurityLabelComponentType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Security Label Component Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWSecurityLabelComponentType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWSecurityLabelComponentType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Security Label Component Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWSecurityLabelComponentType get(int value) {
		switch (value) {
			case SET: return SET_LITERAL;
			case ARRAY: return ARRAY_LITERAL;
			case TREE: return TREE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LUWSecurityLabelComponentType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LUWSecurityLabelComponentType
