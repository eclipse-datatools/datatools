/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableSpaceType.java,v 1.7 2008/02/05 02:01:24 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Table Space Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getTableSpaceType()
 * @model
 * @generated
 */
public final class TableSpaceType extends AbstractEnumerator {
	/**
	 * The '<em><b>REGULAR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REGULAR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REGULAR = 0;

	/**
	 * The '<em><b>LARGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LARGE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LARGE = 1;

	/**
	 * The '<em><b>SYSTEM TEMP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_TEMP_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYSTEM_TEMP = 2;

	/**
	 * The '<em><b>USER TEMP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USER_TEMP_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USER_TEMP = 3;

	/**
	 * The '<em><b>REGULAR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REGULAR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REGULAR
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType REGULAR_LITERAL = new TableSpaceType(REGULAR, "REGULAR", "REGULAR"); //$NON-NLS-1$

	/**
	 * The '<em><b>LARGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LARGE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LARGE
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType LARGE_LITERAL = new TableSpaceType(LARGE, "LARGE", "LARGE"); //$NON-NLS-1$

	/**
	 * The '<em><b>SYSTEM TEMP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYSTEM TEMP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_TEMP
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType SYSTEM_TEMP_LITERAL = new TableSpaceType(SYSTEM_TEMP, "SYSTEM_TEMP", "SYSTEM_TEMP"); //$NON-NLS-1$

	/**
	 * The '<em><b>USER TEMP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USER TEMP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USER_TEMP
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType USER_TEMP_LITERAL = new TableSpaceType(USER_TEMP, "USER_TEMP", "USER_TEMP"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Table Space Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TableSpaceType[] VALUES_ARRAY =
		new TableSpaceType[] {
			REGULAR_LITERAL,
			LARGE_LITERAL,
			SYSTEM_TEMP_LITERAL,
			USER_TEMP_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Table Space Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Table Space Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TableSpaceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TableSpaceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Table Space Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TableSpaceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TableSpaceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Table Space Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TableSpaceType get(int value) {
		switch (value) {
			case REGULAR: return REGULAR_LITERAL;
			case LARGE: return LARGE_LITERAL;
			case SYSTEM_TEMP: return SYSTEM_TEMP_LITERAL;
			case USER_TEMP: return USER_TEMP_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TableSpaceType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //TableSpaceType
