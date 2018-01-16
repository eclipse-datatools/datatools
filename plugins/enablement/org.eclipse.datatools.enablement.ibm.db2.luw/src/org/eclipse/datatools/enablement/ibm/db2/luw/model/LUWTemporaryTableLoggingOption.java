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
 * A representation of the literals of the enumeration '<em><b>Temporary Table Logging Option</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * There are three options:
 * NOT_LOGGED_DELETE_ROWS - Not logged On Rollback Delete Rows
 * NOT_LOGGED_PRESERVE_ROWS - Not Logged On Rollback PreserveRows
 * LOGGED - Logged
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTemporaryTableLoggingOption()
 * @model
 * @generated
 */
public final class LUWTemporaryTableLoggingOption extends AbstractEnumerator {
	/**
	 * The '<em><b>NOT LOGGED DELETE ROWS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT LOGGED DELETE ROWS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_LOGGED_DELETE_ROWS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_LOGGED_DELETE_ROWS = 0;

	/**
	 * The '<em><b>NOT LOGGED PRESERVE ROWS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT LOGGED PRESERVE ROWS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_LOGGED_PRESERVE_ROWS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_LOGGED_PRESERVE_ROWS = 1;

	/**
	 * The '<em><b>LOGGED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOGGED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOGGED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOGGED = 2;

	/**
	 * The '<em><b>NOT LOGGED DELETE ROWS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_LOGGED_DELETE_ROWS
	 * @generated
	 * @ordered
	 */
	public static final LUWTemporaryTableLoggingOption NOT_LOGGED_DELETE_ROWS_LITERAL = new LUWTemporaryTableLoggingOption(NOT_LOGGED_DELETE_ROWS, "NOT_LOGGED_DELETE_ROWS", "NOT_LOGGED_DELETE_ROWS"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NOT LOGGED PRESERVE ROWS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_LOGGED_PRESERVE_ROWS
	 * @generated
	 * @ordered
	 */
	public static final LUWTemporaryTableLoggingOption NOT_LOGGED_PRESERVE_ROWS_LITERAL = new LUWTemporaryTableLoggingOption(NOT_LOGGED_PRESERVE_ROWS, "NOT_LOGGED_PRESERVE_ROWS", "NOT_LOGGED_PRESERVE_ROWS"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>LOGGED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGGED
	 * @generated
	 * @ordered
	 */
	public static final LUWTemporaryTableLoggingOption LOGGED_LITERAL = new LUWTemporaryTableLoggingOption(LOGGED, "LOGGED", "LOGGED"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Temporary Table Logging Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LUWTemporaryTableLoggingOption[] VALUES_ARRAY =
		new LUWTemporaryTableLoggingOption[] {
			NOT_LOGGED_DELETE_ROWS_LITERAL,
			NOT_LOGGED_PRESERVE_ROWS_LITERAL,
			LOGGED_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Temporary Table Logging Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Temporary Table Logging Option</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWTemporaryTableLoggingOption get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWTemporaryTableLoggingOption result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Temporary Table Logging Option</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWTemporaryTableLoggingOption getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWTemporaryTableLoggingOption result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Temporary Table Logging Option</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWTemporaryTableLoggingOption get(int value) {
		switch (value) {
			case NOT_LOGGED_DELETE_ROWS: return NOT_LOGGED_DELETE_ROWS_LITERAL;
			case NOT_LOGGED_PRESERVE_ROWS: return NOT_LOGGED_PRESERVE_ROWS_LITERAL;
			case LOGGED: return LOGGED_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LUWTemporaryTableLoggingOption(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LUWTemporaryTableLoggingOption
