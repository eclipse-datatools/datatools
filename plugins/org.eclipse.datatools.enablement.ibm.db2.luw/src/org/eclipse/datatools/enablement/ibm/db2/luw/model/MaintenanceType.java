/**
 * <copyright>
 * </copyright>
 *
 * $Id: MaintenanceType.java,v 1.7 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Maintenance Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getMaintenanceType()
 * @model
 * @generated
 */
public final class MaintenanceType extends AbstractEnumerator {
	/**
	 * The '<em><b>SYSTEM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYSTEM = 0;

	/**
	 * The '<em><b>USER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USER = 1;

	/**
	 * The '<em><b>SYSTEM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYSTEM</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYSTEM
	 * @generated
	 * @ordered
	 */
	public static final MaintenanceType SYSTEM_LITERAL = new MaintenanceType(SYSTEM, "SYSTEM", "SYSTEM"); //$NON-NLS-1$

	/**
	 * The '<em><b>USER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USER
	 * @generated
	 * @ordered
	 */
	public static final MaintenanceType USER_LITERAL = new MaintenanceType(USER, "USER", "USER"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Maintenance Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MaintenanceType[] VALUES_ARRAY =
		new MaintenanceType[] {
			SYSTEM_LITERAL,
			USER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Maintenance Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Maintenance Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MaintenanceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MaintenanceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Maintenance Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MaintenanceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MaintenanceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Maintenance Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MaintenanceType get(int value) {
		switch (value) {
			case SYSTEM: return SYSTEM_LITERAL;
			case USER: return USER_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MaintenanceType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //MaintenanceType
