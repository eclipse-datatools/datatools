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
 * A representation of the literals of the enumeration '<em><b>Average Table Size Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getAverageTableSizeType()
 * @model
 * @generated
 */
public final class AverageTableSizeType extends AbstractEnumerator {
	/**
	 * The '<em><b>LESS THAN 50MB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LESS THAN 50MB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_50MB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LESS_THAN_50MB = 0;

	/**
	 * The '<em><b>BETWEEN 50MB AND 500MB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BETWEEN 50MB AND 500MB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BETWEEN_50MB_AND_500MB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BETWEEN_50MB_AND_500MB = 1;

	/**
	 * The '<em><b>BETWEEN 500MB AND 5GB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BETWEEN 500MB AND 5GB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BETWEEN_500MB_AND_5GB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BETWEEN_500MB_AND_5GB = 2;

	/**
	 * The '<em><b>GREATER THAN 5GB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GREATER THAN 5GB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN_5GB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_THAN_5GB = 3;

	/**
	 * The '<em><b>LESS THAN 50MB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_50MB
	 * @generated
	 * @ordered
	 */
	public static final AverageTableSizeType LESS_THAN_50MB_LITERAL = new AverageTableSizeType(LESS_THAN_50MB, "LESS_THAN_50MB", "LESS_THAN_50MB"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>BETWEEN 50MB AND 500MB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BETWEEN_50MB_AND_500MB
	 * @generated
	 * @ordered
	 */
	public static final AverageTableSizeType BETWEEN_50MB_AND_500MB_LITERAL = new AverageTableSizeType(BETWEEN_50MB_AND_500MB, "BETWEEN_50MB_AND_500MB", "BETWEEN_50MB_AND_500MB"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>BETWEEN 500MB AND 5GB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BETWEEN_500MB_AND_5GB
	 * @generated
	 * @ordered
	 */
	public static final AverageTableSizeType BETWEEN_500MB_AND_5GB_LITERAL = new AverageTableSizeType(BETWEEN_500MB_AND_5GB, "BETWEEN_500MB_AND_5GB", "BETWEEN_500MB_AND_5GB"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>GREATER THAN 5GB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN_5GB
	 * @generated
	 * @ordered
	 */
	public static final AverageTableSizeType GREATER_THAN_5GB_LITERAL = new AverageTableSizeType(GREATER_THAN_5GB, "GREATER_THAN_5GB", "GREATER_THAN_5GB"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Average Table Size Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AverageTableSizeType[] VALUES_ARRAY =
		new AverageTableSizeType[] {
			LESS_THAN_50MB_LITERAL,
			BETWEEN_50MB_AND_500MB_LITERAL,
			BETWEEN_500MB_AND_5GB_LITERAL,
			GREATER_THAN_5GB_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Average Table Size Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Average Table Size Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AverageTableSizeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AverageTableSizeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Average Table Size Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AverageTableSizeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AverageTableSizeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Average Table Size Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AverageTableSizeType get(int value) {
		switch (value) {
			case LESS_THAN_50MB: return LESS_THAN_50MB_LITERAL;
			case BETWEEN_50MB_AND_500MB: return BETWEEN_50MB_AND_500MB_LITERAL;
			case BETWEEN_500MB_AND_5GB: return BETWEEN_500MB_AND_5GB_LITERAL;
			case GREATER_THAN_5GB: return GREATER_THAN_5GB_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AverageTableSizeType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //AverageTableSizeType
