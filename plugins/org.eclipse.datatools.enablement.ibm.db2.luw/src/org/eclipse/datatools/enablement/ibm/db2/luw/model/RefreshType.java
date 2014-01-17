/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefreshType.java,v 1.8 2008/02/05 02:01:24 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Refresh Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRefreshType()
 * @model
 * @generated
 */
public final class RefreshType extends AbstractEnumerator {
	/**
	 * The '<em><b>DEFERRED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEFERRED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DEFERRED = 0;

	/**
	 * The '<em><b>IMMEDIATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMMEDIATE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int IMMEDIATE = 1;

	/**
	 * The '<em><b>DEFERRED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DEFERRED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DEFERRED
	 * @generated
	 * @ordered
	 */
	public static final RefreshType DEFERRED_LITERAL = new RefreshType(DEFERRED, "DEFERRED", "DEFERRED"); //$NON-NLS-1$

	/**
	 * The '<em><b>IMMEDIATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IMMEDIATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IMMEDIATE
	 * @generated
	 * @ordered
	 */
	public static final RefreshType IMMEDIATE_LITERAL = new RefreshType(IMMEDIATE, "IMMEDIATE", "IMMEDIATE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Refresh Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RefreshType[] VALUES_ARRAY =
		new RefreshType[] {
			DEFERRED_LITERAL,
			IMMEDIATE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Refresh Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Refresh Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RefreshType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RefreshType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Refresh Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RefreshType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RefreshType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Refresh Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RefreshType get(int value) {
		switch (value) {
			case DEFERRED: return DEFERRED_LITERAL;
			case IMMEDIATE: return IMMEDIATE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private RefreshType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //RefreshType
