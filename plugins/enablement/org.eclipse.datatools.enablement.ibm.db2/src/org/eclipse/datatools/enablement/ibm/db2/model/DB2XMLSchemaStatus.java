/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XMLSchemaStatus.java,v 1.4 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>DB2XML Schema Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * From LUW v9.1 LI2076 in the SQL reference changes section, XSROBJECT catalog table, STATUS colum, the following indicates the registration status. 
 * C=COMPLETE
 * I=INCOMPLETE
 * R=REPLACE
 * T=TEMPORARY
 * 
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaStatus()
 * @model
 * @generated
 */
public final class DB2XMLSchemaStatus extends AbstractEnumerator {
	/**
	 * The '<em><b>COMPLETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPLETE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE = 0;

	/**
	 * The '<em><b>INCOMPLETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCOMPLETE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INCOMPLETE = 1;

	/**
	 * The '<em><b>REPLACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPLACE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REPLACE = 2;

	/**
	 * The '<em><b>TEMPORARY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMPORARY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TEMPORARY = 3;

	/**
	 * The '<em><b>COMPLETE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPLETE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPLETE
	 * @generated
	 * @ordered
	 */
	public static final DB2XMLSchemaStatus COMPLETE_LITERAL = new DB2XMLSchemaStatus(COMPLETE, "COMPLETE", "COMPLETE"); //$NON-NLS-1$

	/**
	 * The '<em><b>INCOMPLETE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INCOMPLETE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INCOMPLETE
	 * @generated
	 * @ordered
	 */
	public static final DB2XMLSchemaStatus INCOMPLETE_LITERAL = new DB2XMLSchemaStatus(INCOMPLETE, "INCOMPLETE", "INCOMPLETE"); //$NON-NLS-1$

	/**
	 * The '<em><b>REPLACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REPLACE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REPLACE
	 * @generated
	 * @ordered
	 */
	public static final DB2XMLSchemaStatus REPLACE_LITERAL = new DB2XMLSchemaStatus(REPLACE, "REPLACE", "REPLACE"); //$NON-NLS-1$

	/**
	 * The '<em><b>TEMPORARY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TEMPORARY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEMPORARY
	 * @generated
	 * @ordered
	 */
	public static final DB2XMLSchemaStatus TEMPORARY_LITERAL = new DB2XMLSchemaStatus(TEMPORARY, "TEMPORARY", "TEMPORARY"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>DB2XML Schema Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DB2XMLSchemaStatus[] VALUES_ARRAY =
		new DB2XMLSchemaStatus[] {
			COMPLETE_LITERAL,
			INCOMPLETE_LITERAL,
			REPLACE_LITERAL,
			TEMPORARY_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>DB2XML Schema Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>DB2XML Schema Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2XMLSchemaStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2XMLSchemaStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2XML Schema Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2XMLSchemaStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2XMLSchemaStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2XML Schema Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2XMLSchemaStatus get(int value) {
		switch (value) {
			case COMPLETE: return COMPLETE_LITERAL;
			case INCOMPLETE: return INCOMPLETE_LITERAL;
			case REPLACE: return REPLACE_LITERAL;
			case TEMPORARY: return TEMPORARY_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DB2XMLSchemaStatus(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DB2XMLSchemaStatus
