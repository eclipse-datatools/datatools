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
 * A representation of the literals of the enumeration '<em><b>Index Page Split Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndexPageSplitType()
 * @model
 * @generated
 */
public final class LUWIndexPageSplitType extends AbstractEnumerator {
	/**
	 * The '<em><b>NO SELECTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO SELECTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_SELECTION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_SELECTION = 0;

	/**
	 * The '<em><b>SYMMETRIC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYMMETRIC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYMMETRIC_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYMMETRIC = 1;

	/**
	 * The '<em><b>HIGH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HIGH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HIGH_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HIGH = 2;

	/**
	 * The '<em><b>LOW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOW_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOW = 3;

	/**
	 * The '<em><b>NO SELECTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_SELECTION
	 * @generated
	 * @ordered
	 */
	public static final LUWIndexPageSplitType NO_SELECTION_LITERAL = new LUWIndexPageSplitType(NO_SELECTION, "NO_SELECTION", "NO_SELECTION"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SYMMETRIC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYMMETRIC
	 * @generated
	 * @ordered
	 */
	public static final LUWIndexPageSplitType SYMMETRIC_LITERAL = new LUWIndexPageSplitType(SYMMETRIC, "SYMMETRIC", "SYMMETRIC"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>HIGH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HIGH
	 * @generated
	 * @ordered
	 */
	public static final LUWIndexPageSplitType HIGH_LITERAL = new LUWIndexPageSplitType(HIGH, "HIGH", "HIGH"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>LOW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOW
	 * @generated
	 * @ordered
	 */
	public static final LUWIndexPageSplitType LOW_LITERAL = new LUWIndexPageSplitType(LOW, "LOW", "LOW"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Index Page Split Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LUWIndexPageSplitType[] VALUES_ARRAY =
		new LUWIndexPageSplitType[] {
			NO_SELECTION_LITERAL,
			SYMMETRIC_LITERAL,
			HIGH_LITERAL,
			LOW_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Index Page Split Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Index Page Split Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWIndexPageSplitType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWIndexPageSplitType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Index Page Split Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWIndexPageSplitType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWIndexPageSplitType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Index Page Split Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWIndexPageSplitType get(int value) {
		switch (value) {
			case NO_SELECTION: return NO_SELECTION_LITERAL;
			case SYMMETRIC: return SYMMETRIC_LITERAL;
			case HIGH: return HIGH_LITERAL;
			case LOW: return LOW_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LUWIndexPageSplitType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LUWIndexPageSplitType
