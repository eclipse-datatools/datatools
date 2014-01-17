/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionMethod.java,v 1.9 2008/02/05 02:01:24 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Partition Method</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getPartitionMethod()
 * @model
 * @generated
 */
public final class PartitionMethod extends AbstractEnumerator {
	/**
	 * The '<em><b>HASHING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HASHING_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HASHING = 0;

	/**
	 * The '<em><b>TABLE REPLICATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TABLE_REPLICATED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TABLE_REPLICATED = 1;

	/**
	 * The '<em><b>HASHING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HASHING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HASHING
	 * @generated
	 * @ordered
	 */
	public static final PartitionMethod HASHING_LITERAL = new PartitionMethod(HASHING, "HASHING", "HASHING"); //$NON-NLS-1$

	/**
	 * The '<em><b>TABLE REPLICATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TABLE REPLICATED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TABLE_REPLICATED
	 * @generated
	 * @ordered
	 */
	public static final PartitionMethod TABLE_REPLICATED_LITERAL = new PartitionMethod(TABLE_REPLICATED, "TABLE_REPLICATED", "TABLE_REPLICATED"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Partition Method</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PartitionMethod[] VALUES_ARRAY =
		new PartitionMethod[] {
			HASHING_LITERAL,
			TABLE_REPLICATED_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Partition Method</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Partition Method</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PartitionMethod get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PartitionMethod result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Partition Method</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PartitionMethod getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PartitionMethod result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Partition Method</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PartitionMethod get(int value) {
		switch (value) {
			case HASHING: return HASHING_LITERAL;
			case TABLE_REPLICATED: return TABLE_REPLICATED_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PartitionMethod(int value, String name, String literal) {
		super(value, name, literal);
	}

} //PartitionMethod
