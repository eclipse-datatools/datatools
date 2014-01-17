/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XMLSchemaDecomposition.java,v 1.4 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>DB2XML Schema Decomposition</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The states can either be Yes, No or Inoperative and thus need to be represented by an Enumeration instead of a boolean. Represented in the database:
 * Y=enabled
 * N=not enabled
 * X=inoperative
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDecomposition()
 * @model
 * @generated
 */
public final class DB2XMLSchemaDecomposition extends AbstractEnumerator {
	/**
	 * The '<em><b>ENABLED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENABLED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ENABLED = 0;

	/**
	 * The '<em><b>NOT ENABLED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_ENABLED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_ENABLED = 1;

	/**
	 * The '<em><b>INOPERATIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INOPERATIVE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INOPERATIVE = 2;

	/**
	 * The '<em><b>ENABLED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ENABLED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENABLED
	 * @generated
	 * @ordered
	 */
	public static final DB2XMLSchemaDecomposition ENABLED_LITERAL = new DB2XMLSchemaDecomposition(ENABLED, "ENABLED", "ENABLED"); //$NON-NLS-1$

	/**
	 * The '<em><b>NOT ENABLED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT ENABLED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_ENABLED
	 * @generated
	 * @ordered
	 */
	public static final DB2XMLSchemaDecomposition NOT_ENABLED_LITERAL = new DB2XMLSchemaDecomposition(NOT_ENABLED, "NOT_ENABLED", "NOT_ENABLED"); //$NON-NLS-1$

	/**
	 * The '<em><b>INOPERATIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INOPERATIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INOPERATIVE
	 * @generated
	 * @ordered
	 */
	public static final DB2XMLSchemaDecomposition INOPERATIVE_LITERAL = new DB2XMLSchemaDecomposition(INOPERATIVE, "INOPERATIVE", "INOPERATIVE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>DB2XML Schema Decomposition</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DB2XMLSchemaDecomposition[] VALUES_ARRAY =
		new DB2XMLSchemaDecomposition[] {
			ENABLED_LITERAL,
			NOT_ENABLED_LITERAL,
			INOPERATIVE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>DB2XML Schema Decomposition</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>DB2XML Schema Decomposition</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2XMLSchemaDecomposition get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2XMLSchemaDecomposition result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2XML Schema Decomposition</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2XMLSchemaDecomposition getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2XMLSchemaDecomposition result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2XML Schema Decomposition</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2XMLSchemaDecomposition get(int value) {
		switch (value) {
			case ENABLED: return ENABLED_LITERAL;
			case NOT_ENABLED: return NOT_ENABLED_LITERAL;
			case INOPERATIVE: return INOPERATIVE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DB2XMLSchemaDecomposition(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DB2XMLSchemaDecomposition
