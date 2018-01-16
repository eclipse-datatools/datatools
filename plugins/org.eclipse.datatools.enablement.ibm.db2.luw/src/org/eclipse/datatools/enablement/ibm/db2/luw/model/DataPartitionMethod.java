/**
 * <copyright>
 * </copyright>
 *
 * $Id: DataPartitionMethod.java,v 1.3 2008/02/05 02:01:24 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Data Partition Method</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getDataPartitionMethod()
 * @model
 * @generated
 */
public final class DataPartitionMethod extends AbstractEnumerator {
	/**
	 * The '<em><b>RANGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RANGE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RANGE = 0;

	/**
	 * The '<em><b>RANGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RANGE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RANGE
	 * @generated
	 * @ordered
	 */
	public static final DataPartitionMethod RANGE_LITERAL = new DataPartitionMethod(RANGE, "RANGE", "RANGE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Data Partition Method</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DataPartitionMethod[] VALUES_ARRAY =
		new DataPartitionMethod[] {
			RANGE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Data Partition Method</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Data Partition Method</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DataPartitionMethod get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DataPartitionMethod result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Data Partition Method</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DataPartitionMethod getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DataPartitionMethod result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Data Partition Method</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DataPartitionMethod get(int value) {
		switch (value) {
			case RANGE: return RANGE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DataPartitionMethod(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DataPartitionMethod
