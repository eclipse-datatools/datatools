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
 * A representation of the literals of the enumeration '<em><b>Storage Table Compression Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTableCompressionMode()
 * @model
 * @generated
 */
public final class LUWStorageTableCompressionMode extends AbstractEnumerator {
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
	 * The '<em><b>ADAPTIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ADAPTIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ADAPTIVE_LITERAL
	 * @model literal="Adaptive"
	 * @generated
	 * @ordered
	 */
	public static final int ADAPTIVE = 1;

	/**
	 * The '<em><b>STATIC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STATIC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATIC_LITERAL
	 * @model literal="Static"
	 * @generated
	 * @ordered
	 */
	public static final int STATIC = 2;

	/**
	 * The '<em><b>NO SELECTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_SELECTION
	 * @generated
	 * @ordered
	 */
	public static final LUWStorageTableCompressionMode NO_SELECTION_LITERAL = new LUWStorageTableCompressionMode(NO_SELECTION, "NO_SELECTION", "NO_SELECTION"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ADAPTIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ADAPTIVE
	 * @generated
	 * @ordered
	 */
	public static final LUWStorageTableCompressionMode ADAPTIVE_LITERAL = new LUWStorageTableCompressionMode(ADAPTIVE, "ADAPTIVE", "Adaptive"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>STATIC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATIC
	 * @generated
	 * @ordered
	 */
	public static final LUWStorageTableCompressionMode STATIC_LITERAL = new LUWStorageTableCompressionMode(STATIC, "STATIC", "Static"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Storage Table Compression Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LUWStorageTableCompressionMode[] VALUES_ARRAY =
		new LUWStorageTableCompressionMode[] {
			NO_SELECTION_LITERAL,
			ADAPTIVE_LITERAL,
			STATIC_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Storage Table Compression Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Storage Table Compression Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWStorageTableCompressionMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWStorageTableCompressionMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Storage Table Compression Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWStorageTableCompressionMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWStorageTableCompressionMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Storage Table Compression Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWStorageTableCompressionMode get(int value) {
		switch (value) {
			case NO_SELECTION: return NO_SELECTION_LITERAL;
			case ADAPTIVE: return ADAPTIVE_LITERAL;
			case STATIC: return STATIC_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LUWStorageTableCompressionMode(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LUWStorageTableCompressionMode
