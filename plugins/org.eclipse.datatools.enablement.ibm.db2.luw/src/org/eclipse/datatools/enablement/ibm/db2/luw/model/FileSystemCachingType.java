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
 * A representation of the literals of the enumeration '<em><b>File System Caching Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFileSystemCachingType()
 * @model
 * @generated
 */
public final class FileSystemCachingType extends AbstractEnumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE = 0;

	/**
	 * The '<em><b>FILE CACHING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FILE CACHING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FILE_CACHING_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FILE_CACHING = 1;

	/**
	 * The '<em><b>NO FILE CACHING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO FILE CACHING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_FILE_CACHING_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_FILE_CACHING = 2;

	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
	public static final FileSystemCachingType NONE_LITERAL = new FileSystemCachingType(NONE, "NONE", "NONE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>FILE CACHING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FILE_CACHING
	 * @generated
	 * @ordered
	 */
	public static final FileSystemCachingType FILE_CACHING_LITERAL = new FileSystemCachingType(FILE_CACHING, "FILE_CACHING", "FILE_CACHING"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NO FILE CACHING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_FILE_CACHING
	 * @generated
	 * @ordered
	 */
	public static final FileSystemCachingType NO_FILE_CACHING_LITERAL = new FileSystemCachingType(NO_FILE_CACHING, "NO_FILE_CACHING", "NO_FILE_CACHING"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>File System Caching Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final FileSystemCachingType[] VALUES_ARRAY =
		new FileSystemCachingType[] {
			NONE_LITERAL,
			FILE_CACHING_LITERAL,
			NO_FILE_CACHING_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>File System Caching Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>File System Caching Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FileSystemCachingType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FileSystemCachingType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>File System Caching Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FileSystemCachingType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FileSystemCachingType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>File System Caching Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FileSystemCachingType get(int value) {
		switch (value) {
			case NONE: return NONE_LITERAL;
			case FILE_CACHING: return FILE_CACHING_LITERAL;
			case NO_FILE_CACHING: return NO_FILE_CACHING_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private FileSystemCachingType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //FileSystemCachingType
