/**
 * <copyright>
 * </copyright>
 *
 * $Id: IncrementType.java,v 1.3 2006/09/07 00:19:49 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.constraints;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Increment Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIncrementType()
 * @model
 * @generated
 */
public final class IncrementType extends AbstractEnumerator {
	/**
	 * The '<em><b>ASC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASC_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ASC = 0;

	/**
	 * The '<em><b>DESC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DESC_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DESC = 1;

	/**
	 * The '<em><b>RANDOM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RANDOM</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RANDOM_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RANDOM = 2;

	/**
	 * The '<em><b>ASC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ASC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ASC
	 * @generated
	 * @ordered
	 */
	public static final IncrementType ASC_LITERAL = new IncrementType(ASC, "ASC", "ASC"); //$NON-NLS-1$

	/**
	 * The '<em><b>DESC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DESC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DESC
	 * @generated
	 * @ordered
	 */
	public static final IncrementType DESC_LITERAL = new IncrementType(DESC, "DESC", "DESC"); //$NON-NLS-1$

	/**
	 * The '<em><b>RANDOM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RANDOM
	 * @generated
	 * @ordered
	 */
	public static final IncrementType RANDOM_LITERAL = new IncrementType(RANDOM, "RANDOM", "RANDOM"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Increment Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final IncrementType[] VALUES_ARRAY =
		new IncrementType[] {
			ASC_LITERAL,
			DESC_LITERAL,
			RANDOM_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Increment Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Increment Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IncrementType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IncrementType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Increment Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IncrementType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IncrementType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Increment Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IncrementType get(int value) {
		switch (value) {
			case ASC: return ASC_LITERAL;
			case DESC: return DESC_LITERAL;
			case RANDOM: return RANDOM_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private IncrementType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //IncrementType
