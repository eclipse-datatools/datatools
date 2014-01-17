/**
 * <copyright>
 * </copyright>
 *
 * $Id: WrapperType.java,v 1.8 2008/02/05 02:01:24 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Wrapper Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getWrapperType()
 * @model
 * @generated
 */
public final class WrapperType extends AbstractEnumerator {
	/**
	 * The '<em><b>RELATIONAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELATIONAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RELATIONAL = 0;

	/**
	 * The '<em><b>NON RELATIONAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NON_RELATIONAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NON_RELATIONAL = 1;

	/**
	 * The '<em><b>RELATIONAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RELATIONAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RELATIONAL
	 * @generated
	 * @ordered
	 */
	public static final WrapperType RELATIONAL_LITERAL = new WrapperType(RELATIONAL, "RELATIONAL", "RELATIONAL"); //$NON-NLS-1$

	/**
	 * The '<em><b>NON RELATIONAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NON RELATIONAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NON_RELATIONAL
	 * @generated
	 * @ordered
	 */
	public static final WrapperType NON_RELATIONAL_LITERAL = new WrapperType(NON_RELATIONAL, "NON_RELATIONAL", "NON_RELATIONAL"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Wrapper Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WrapperType[] VALUES_ARRAY =
		new WrapperType[] {
			RELATIONAL_LITERAL,
			NON_RELATIONAL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Wrapper Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Wrapper Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WrapperType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WrapperType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Wrapper Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WrapperType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WrapperType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Wrapper Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WrapperType get(int value) {
		switch (value) {
			case RELATIONAL: return RELATIONAL_LITERAL;
			case NON_RELATIONAL: return NON_RELATIONAL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private WrapperType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //WrapperType
