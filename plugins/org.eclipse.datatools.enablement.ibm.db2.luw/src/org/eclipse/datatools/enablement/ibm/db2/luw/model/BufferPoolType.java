/**
 * <copyright>
 * </copyright>
 *
 * $Id: BufferPoolType.java,v 1.7 2008/02/05 02:01:24 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Buffer Pool Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getBufferPoolType()
 * @model
 * @generated
 */
public final class BufferPoolType extends AbstractEnumerator {
	/**
	 * The '<em><b>IMMEDIATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMMEDIATE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int IMMEDIATE = 0;

	/**
	 * The '<em><b>DEFERRED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEFERRED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DEFERRED = 1;

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
	public static final BufferPoolType IMMEDIATE_LITERAL = new BufferPoolType(IMMEDIATE, "IMMEDIATE", "IMMEDIATE"); //$NON-NLS-1$

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
	public static final BufferPoolType DEFERRED_LITERAL = new BufferPoolType(DEFERRED, "DEFERRED", "DEFERRED"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Buffer Pool Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BufferPoolType[] VALUES_ARRAY =
		new BufferPoolType[] {
			IMMEDIATE_LITERAL,
			DEFERRED_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Buffer Pool Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Buffer Pool Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BufferPoolType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BufferPoolType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Buffer Pool Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BufferPoolType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BufferPoolType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Buffer Pool Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BufferPoolType get(int value) {
		switch (value) {
			case IMMEDIATE: return IMMEDIATE_LITERAL;
			case DEFERRED: return DEFERRED_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private BufferPoolType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //BufferPoolType
