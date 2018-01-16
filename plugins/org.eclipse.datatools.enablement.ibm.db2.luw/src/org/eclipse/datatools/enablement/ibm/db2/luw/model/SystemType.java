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
 * A representation of the literals of the enumeration '<em><b>System Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getSystemType()
 * @model
 * @generated
 */
public final class SystemType extends AbstractEnumerator {
	/**
	 * The '<em><b>SATA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SATA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SATA_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SATA = 0;

	/**
	 * The '<em><b>SAS 10K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SAS 10K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SAS_10K_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SAS_10K = 1;

	/**
	 * The '<em><b>SAS 15K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SAS 15K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SAS_15K_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SAS_15K = 2;

	/**
	 * The '<em><b>SSD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SSD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SSD_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SSD = 3;

	/**
	 * The '<em><b>SATA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SATA
	 * @generated
	 * @ordered
	 */
	public static final SystemType SATA_LITERAL = new SystemType(SATA, "SATA", "SATA"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SAS 10K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SAS_10K
	 * @generated
	 * @ordered
	 */
	public static final SystemType SAS_10K_LITERAL = new SystemType(SAS_10K, "SAS_10K", "SAS_10K"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SAS 15K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SAS_15K
	 * @generated
	 * @ordered
	 */
	public static final SystemType SAS_15K_LITERAL = new SystemType(SAS_15K, "SAS_15K", "SAS_15K"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SSD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SSD
	 * @generated
	 * @ordered
	 */
	public static final SystemType SSD_LITERAL = new SystemType(SSD, "SSD", "SSD"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>System Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SystemType[] VALUES_ARRAY =
		new SystemType[] {
			SATA_LITERAL,
			SAS_10K_LITERAL,
			SAS_15K_LITERAL,
			SSD_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>System Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>System Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SystemType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SystemType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>System Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SystemType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SystemType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>System Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SystemType get(int value) {
		switch (value) {
			case SATA: return SATA_LITERAL;
			case SAS_10K: return SAS_10K_LITERAL;
			case SAS_15K: return SAS_15K_LITERAL;
			case SSD: return SSD_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SystemType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //SystemType
