/**
 * <copyright>
 * </copyright>
 *
 * $Id: IntegrityControlOption.java,v 1.1 2005/08/02 22:56:26 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.sql.datatypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Integrity Control Option</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntegrityControlOption()
 * @model
 * @generated
 */
public final class IntegrityControlOption extends AbstractEnumerator {
	/**
	 * The '<em><b>ALL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALL = 0;

	/**
	 * The '<em><b>SELECTIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SELECTIVE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SELECTIVE = 1;

	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE = 2;

	/**
	 * The '<em><b>ALL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALL
	 * @generated
	 * @ordered
	 */
	public static final IntegrityControlOption ALL_LITERAL = new IntegrityControlOption(ALL, "ALL"); //$NON-NLS-1$

	/**
	 * The '<em><b>SELECTIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SELECTIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SELECTIVE
	 * @generated
	 * @ordered
	 */
	public static final IntegrityControlOption SELECTIVE_LITERAL = new IntegrityControlOption(SELECTIVE, "SELECTIVE"); //$NON-NLS-1$

	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
	public static final IntegrityControlOption NONE_LITERAL = new IntegrityControlOption(NONE, "NONE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Integrity Control Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final IntegrityControlOption[] VALUES_ARRAY =
		new IntegrityControlOption[] {
			ALL_LITERAL,
			SELECTIVE_LITERAL,
			NONE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Integrity Control Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Integrity Control Option</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntegrityControlOption get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IntegrityControlOption result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Integrity Control Option</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntegrityControlOption get(int value) {
		switch (value) {
			case ALL: return ALL_LITERAL;
			case SELECTIVE: return SELECTIVE_LITERAL;
			case NONE: return NONE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private IntegrityControlOption(int value, String name) {
		super(value, name);
	}

} //IntegrityControlOption
