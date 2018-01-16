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
 * A representation of the literals of the enumeration '<em><b>Index Compress Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndexCompressType()
 * @model
 * @generated
 */
public final class LUWIndexCompressType extends AbstractEnumerator {
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
	 * The '<em><b>NO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO = 1;

	/**
	 * The '<em><b>YES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #YES_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int YES = 2;

	/**
	 * The '<em><b>NO SELECTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_SELECTION
	 * @generated
	 * @ordered
	 */
	public static final LUWIndexCompressType NO_SELECTION_LITERAL = new LUWIndexCompressType(NO_SELECTION, "NO_SELECTION", "NO_SELECTION"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO
	 * @generated
	 * @ordered
	 */
	public static final LUWIndexCompressType NO_LITERAL = new LUWIndexCompressType(NO, "NO", "NO"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>YES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #YES
	 * @generated
	 * @ordered
	 */
	public static final LUWIndexCompressType YES_LITERAL = new LUWIndexCompressType(YES, "YES", "YES"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Index Compress Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LUWIndexCompressType[] VALUES_ARRAY =
		new LUWIndexCompressType[] {
			NO_SELECTION_LITERAL,
			NO_LITERAL,
			YES_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Index Compress Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Index Compress Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWIndexCompressType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWIndexCompressType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Index Compress Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWIndexCompressType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWIndexCompressType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Index Compress Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWIndexCompressType get(int value) {
		switch (value) {
			case NO_SELECTION: return NO_SELECTION_LITERAL;
			case NO: return NO_LITERAL;
			case YES: return YES_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LUWIndexCompressType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LUWIndexCompressType
