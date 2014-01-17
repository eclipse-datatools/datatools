/**
 * <copyright>
 * </copyright>
 *
 * $Id: CursorBlockType.java,v 1.1 2009/02/23 22:15:32 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Cursor Block Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getCursorBlockType()
 * @model
 * @generated
 */
public final class CursorBlockType extends AbstractEnumerator {
	/**
	 * The '<em><b>BLOCK UNAMBIGUOUS CURSORS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BLOCK UNAMBIGUOUS CURSORS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BLOCK_UNAMBIGUOUS_CURSORS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BLOCK_UNAMBIGUOUS_CURSORS = 0;

	/**
	 * The '<em><b>BLOCK ALL CURSORS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BLOCK ALL CURSORS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BLOCK_ALL_CURSORS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BLOCK_ALL_CURSORS = 1;

	/**
	 * The '<em><b>NO BLOCKING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO BLOCKING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_BLOCKING_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_BLOCKING = 2;

	/**
	 * The '<em><b>BLOCK UNAMBIGUOUS CURSORS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCK_UNAMBIGUOUS_CURSORS
	 * @generated
	 * @ordered
	 */
	public static final CursorBlockType BLOCK_UNAMBIGUOUS_CURSORS_LITERAL = new CursorBlockType(BLOCK_UNAMBIGUOUS_CURSORS, "BLOCK_UNAMBIGUOUS_CURSORS", "BLOCK_UNAMBIGUOUS_CURSORS"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>BLOCK ALL CURSORS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCK_ALL_CURSORS
	 * @generated
	 * @ordered
	 */
	public static final CursorBlockType BLOCK_ALL_CURSORS_LITERAL = new CursorBlockType(BLOCK_ALL_CURSORS, "BLOCK_ALL_CURSORS", "BLOCK_ALL_CURSORS"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NO BLOCKING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_BLOCKING
	 * @generated
	 * @ordered
	 */
	public static final CursorBlockType NO_BLOCKING_LITERAL = new CursorBlockType(NO_BLOCKING, "NO_BLOCKING", "NO_BLOCKING"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Cursor Block Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CursorBlockType[] VALUES_ARRAY =
		new CursorBlockType[] {
			BLOCK_UNAMBIGUOUS_CURSORS_LITERAL,
			BLOCK_ALL_CURSORS_LITERAL,
			NO_BLOCKING_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Cursor Block Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Cursor Block Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CursorBlockType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CursorBlockType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Cursor Block Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CursorBlockType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CursorBlockType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Cursor Block Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CursorBlockType get(int value) {
		switch (value) {
			case BLOCK_UNAMBIGUOUS_CURSORS: return BLOCK_UNAMBIGUOUS_CURSORS_LITERAL;
			case BLOCK_ALL_CURSORS: return BLOCK_ALL_CURSORS_LITERAL;
			case NO_BLOCKING: return NO_BLOCKING_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CursorBlockType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //CursorBlockType
