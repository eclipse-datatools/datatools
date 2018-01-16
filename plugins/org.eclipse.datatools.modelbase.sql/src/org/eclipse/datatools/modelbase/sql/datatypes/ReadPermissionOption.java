/**
 * <copyright>
 * </copyright>
 *
 * $Id: ReadPermissionOption.java,v 1.3 2006/09/07 00:19:48 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.datatypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Read Permission Option</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getReadPermissionOption()
 * @model
 * @generated
 */
public final class ReadPermissionOption extends AbstractEnumerator {
	/**
	 * The '<em><b>FS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FS = 0;

	/**
	 * The '<em><b>DB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DB = 1;

	/**
	 * The '<em><b>FS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FS
	 * @generated
	 * @ordered
	 */
	public static final ReadPermissionOption FS_LITERAL = new ReadPermissionOption(FS, "FS", "FS"); //$NON-NLS-1$

	/**
	 * The '<em><b>DB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DB
	 * @generated
	 * @ordered
	 */
	public static final ReadPermissionOption DB_LITERAL = new ReadPermissionOption(DB, "DB", "DB"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Read Permission Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReadPermissionOption[] VALUES_ARRAY =
		new ReadPermissionOption[] {
			FS_LITERAL,
			DB_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Read Permission Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Read Permission Option</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReadPermissionOption get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReadPermissionOption result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Read Permission Option</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReadPermissionOption getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReadPermissionOption result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Read Permission Option</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReadPermissionOption get(int value) {
		switch (value) {
			case FS: return FS_LITERAL;
			case DB: return DB_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ReadPermissionOption(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ReadPermissionOption
