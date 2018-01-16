/**
 * <copyright>
 * </copyright>
 *
 * $Id: ManagementType.java,v 1.9 2008/02/05 02:01:24 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Management Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getManagementType()
 * @model
 * @generated
 */
public final class ManagementType extends AbstractEnumerator {
	/**
	 * The '<em><b>SYSTEM MANAGED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_MANAGED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYSTEM_MANAGED = 0;

	/**
	 * The '<em><b>DATABASE MANAGED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DATABASE_MANAGED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DATABASE_MANAGED = 1;

	/**
	 * The '<em><b>AUTOMATIC STORAGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTOMATIC_STORAGE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATIC_STORAGE = 2;

	/**
	 * The '<em><b>SYSTEM MANAGED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYSTEM MANAGED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_MANAGED
	 * @generated
	 * @ordered
	 */
	public static final ManagementType SYSTEM_MANAGED_LITERAL = new ManagementType(SYSTEM_MANAGED, "SYSTEM_MANAGED", "SYSTEM_MANAGED"); //$NON-NLS-1$

	/**
	 * The '<em><b>DATABASE MANAGED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DATABASE MANAGED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DATABASE_MANAGED
	 * @generated
	 * @ordered
	 */
	public static final ManagementType DATABASE_MANAGED_LITERAL = new ManagementType(DATABASE_MANAGED, "DATABASE_MANAGED", "DATABASE_MANAGED"); //$NON-NLS-1$

	/**
	 * The '<em><b>AUTOMATIC STORAGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AUTOMATIC STORAGE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AUTOMATIC_STORAGE
	 * @generated
	 * @ordered
	 */
	public static final ManagementType AUTOMATIC_STORAGE_LITERAL = new ManagementType(AUTOMATIC_STORAGE, "AUTOMATIC_STORAGE", "AUTOMATIC_STORAGE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Management Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ManagementType[] VALUES_ARRAY =
		new ManagementType[] {
			SYSTEM_MANAGED_LITERAL,
			DATABASE_MANAGED_LITERAL,
			AUTOMATIC_STORAGE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Management Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Management Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ManagementType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ManagementType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Management Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ManagementType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ManagementType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Management Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ManagementType get(int value) {
		switch (value) {
			case SYSTEM_MANAGED: return SYSTEM_MANAGED_LITERAL;
			case DATABASE_MANAGED: return DATABASE_MANAGED_LITERAL;
			case AUTOMATIC_STORAGE: return AUTOMATIC_STORAGE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ManagementType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ManagementType
