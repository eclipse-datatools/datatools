/**
 * <copyright>
 * </copyright>
 *
 * $Id: IncrementType.java,v 1.1 2005/08/02 22:56:27 ledunnel Exp $
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
	public static final IncrementType ASC_LITERAL = new IncrementType(ASC, "ASC"); //$NON-NLS-1$

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
	public static final IncrementType DESC_LITERAL = new IncrementType(DESC, "DESC"); //$NON-NLS-1$

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
		};

	/**
	 * A public read-only list of all the '<em><b>Increment Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Increment Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IncrementType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IncrementType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Increment Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IncrementType get(int value) {
		switch (value) {
			case ASC: return ASC_LITERAL;
			case DESC: return DESC_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private IncrementType(int value, String name) {
		super(value, name);
	}

} //IncrementType
