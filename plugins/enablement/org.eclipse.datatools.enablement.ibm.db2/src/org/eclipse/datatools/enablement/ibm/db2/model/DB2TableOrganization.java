/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>DB2 Table Organization</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2TableOrganization()
 * @model
 * @generated
 */
public final class DB2TableOrganization extends AbstractEnumerator {
	/**
	 * The '<em><b>UNSPECIFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSPECIFIED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSPECIFIED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNSPECIFIED = 0;

	/**
	 * The '<em><b>ROW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ROW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROW_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ROW = 1;

	/**
	 * The '<em><b>COLUMN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COLUMN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COLUMN_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COLUMN = 2;

	/**
	 * The '<em><b>UNSPECIFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSPECIFIED
	 * @generated
	 * @ordered
	 */
	public static final DB2TableOrganization UNSPECIFIED_LITERAL = new DB2TableOrganization(UNSPECIFIED, "UNSPECIFIED", "UNSPECIFIED"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ROW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROW
	 * @generated
	 * @ordered
	 */
	public static final DB2TableOrganization ROW_LITERAL = new DB2TableOrganization(ROW, "ROW", "ROW"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>COLUMN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COLUMN
	 * @generated
	 * @ordered
	 */
	public static final DB2TableOrganization COLUMN_LITERAL = new DB2TableOrganization(COLUMN, "COLUMN", "COLUMN"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>DB2 Table Organization</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DB2TableOrganization[] VALUES_ARRAY =
		new DB2TableOrganization[] {
			UNSPECIFIED_LITERAL,
			ROW_LITERAL,
			COLUMN_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>DB2 Table Organization</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>DB2 Table Organization</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2TableOrganization get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2TableOrganization result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2 Table Organization</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2TableOrganization getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2TableOrganization result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2 Table Organization</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2TableOrganization get(int value) {
		switch (value) {
			case UNSPECIFIED: return UNSPECIFIED_LITERAL;
			case ROW: return ROW_LITERAL;
			case COLUMN: return COLUMN_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DB2TableOrganization(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DB2TableOrganization
