/**
 * <copyright>
 * </copyright>
 *
 * $Id: UnitType.java,v 1.8 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Unit Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getUnitType()
 * @model
 * @generated
 */
public final class UnitType extends AbstractEnumerator {
	/**
	 * The '<em><b>K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #K_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int K = 0;

	/**
	 * The '<em><b>M</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #M_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int M = 1;

	/**
	 * The '<em><b>G</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #G_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int G = 2;

	/**
	 * The '<em><b>K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #K
	 * @generated
	 * @ordered
	 */
	public static final UnitType K_LITERAL = new UnitType(K, "K", "K"); //$NON-NLS-1$

	/**
	 * The '<em><b>M</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>M</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #M
	 * @generated
	 * @ordered
	 */
	public static final UnitType M_LITERAL = new UnitType(M, "M", "M"); //$NON-NLS-1$

	/**
	 * The '<em><b>G</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>G</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #G
	 * @generated
	 * @ordered
	 */
	public static final UnitType G_LITERAL = new UnitType(G, "G", "G"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Unit Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final UnitType[] VALUES_ARRAY =
		new UnitType[] {
			K_LITERAL,
			M_LITERAL,
			G_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Unit Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Unit Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnitType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnitType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Unit Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnitType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnitType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Unit Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnitType get(int value) {
		switch (value) {
			case K: return K_LITERAL;
			case M: return M_LITERAL;
			case G: return G_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private UnitType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //UnitType
