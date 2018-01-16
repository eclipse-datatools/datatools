/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenerateType.java,v 1.5 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Generate Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getGenerateType()
 * @model
 * @generated
 */
public final class GenerateType extends AbstractEnumerator {
	/**
	 * The '<em><b>ALWAYS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALWAYS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALWAYS = 0;

	/**
	 * The '<em><b>BY DEFAULT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BY_DEFAULT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BY_DEFAULT = 1;

	/**
	 * The '<em><b>ALWAYS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALWAYS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALWAYS
	 * @generated
	 * @ordered
	 */
	public static final GenerateType ALWAYS_LITERAL = new GenerateType(ALWAYS, "ALWAYS", "ALWAYS"); //$NON-NLS-1$

	/**
	 * The '<em><b>BY DEFAULT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BY DEFAULT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BY_DEFAULT
	 * @generated
	 * @ordered
	 */
	public static final GenerateType BY_DEFAULT_LITERAL = new GenerateType(BY_DEFAULT, "BY_DEFAULT", "BY_DEFAULT"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Generate Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final GenerateType[] VALUES_ARRAY =
		new GenerateType[] {
			ALWAYS_LITERAL,
			BY_DEFAULT_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Generate Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Generate Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenerateType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GenerateType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Generate Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenerateType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GenerateType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Generate Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenerateType get(int value) {
		switch (value) {
			case ALWAYS: return ALWAYS_LITERAL;
			case BY_DEFAULT: return BY_DEFAULT_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private GenerateType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //GenerateType
