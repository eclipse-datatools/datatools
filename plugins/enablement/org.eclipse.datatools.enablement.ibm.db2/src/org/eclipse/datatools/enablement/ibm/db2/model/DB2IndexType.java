/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2IndexType.java,v 1.8 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>DB2 Index Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2IndexType()
 * @model
 * @generated
 */
public final class DB2IndexType extends AbstractEnumerator {
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
	 * The '<em><b>BLOCK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCK_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BLOCK = 1;

	/**
	 * The '<em><b>DIMENSION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIMENSION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIMENSION = 2;

	/**
	 * The '<em><b>XMLPATH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XMLPATH_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int XMLPATH = 3;

	/**
	 * The '<em><b>XMLREGION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XMLREGION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int XMLREGION = 4;

	/**
	 * The '<em><b>XMLCOLUMN LOGICAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XMLCOLUMN_LOGICAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int XMLCOLUMN_LOGICAL = 5;

	/**
	 * The '<em><b>XMLCOLUMN PHYSICAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XMLCOLUMN_PHYSICAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int XMLCOLUMN_PHYSICAL = 6;

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
	public static final DB2IndexType REGULAR_LITERAL = new DB2IndexType(REGULAR, "REGULAR", "REGULAR"); //$NON-NLS-1$

	/**
	 * The '<em><b>BLOCK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BLOCK</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BLOCK
	 * @generated
	 * @ordered
	 */
	public static final DB2IndexType BLOCK_LITERAL = new DB2IndexType(BLOCK, "BLOCK", "BLOCK"); //$NON-NLS-1$

	/**
	 * The '<em><b>DIMENSION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIMENSION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIMENSION
	 * @generated
	 * @ordered
	 */
	public static final DB2IndexType DIMENSION_LITERAL = new DB2IndexType(DIMENSION, "DIMENSION", "DIMENSION"); //$NON-NLS-1$

	/**
	 * The '<em><b>XMLPATH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XMLPATH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XMLPATH
	 * @generated
	 * @ordered
	 */
	public static final DB2IndexType XMLPATH_LITERAL = new DB2IndexType(XMLPATH, "XMLPATH", "XMLPATH"); //$NON-NLS-1$

	/**
	 * The '<em><b>XMLREGION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XMLREGION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XMLREGION
	 * @generated
	 * @ordered
	 */
	public static final DB2IndexType XMLREGION_LITERAL = new DB2IndexType(XMLREGION, "XMLREGION", "XMLREGION"); //$NON-NLS-1$

	/**
	 * The '<em><b>XMLCOLUMN LOGICAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XMLCOLUMN LOGICAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XMLCOLUMN_LOGICAL
	 * @generated
	 * @ordered
	 */
	public static final DB2IndexType XMLCOLUMN_LOGICAL_LITERAL = new DB2IndexType(XMLCOLUMN_LOGICAL, "XMLCOLUMN_LOGICAL", "XMLCOLUMN_LOGICAL"); //$NON-NLS-1$

	/**
	 * The '<em><b>XMLCOLUMN PHYSICAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XMLCOLUMN PHYSICAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XMLCOLUMN_PHYSICAL
	 * @generated
	 * @ordered
	 */
	public static final DB2IndexType XMLCOLUMN_PHYSICAL_LITERAL = new DB2IndexType(XMLCOLUMN_PHYSICAL, "XMLCOLUMN_PHYSICAL", "XMLCOLUMN_PHYSICAL"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>DB2 Index Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DB2IndexType[] VALUES_ARRAY =
		new DB2IndexType[] {
			REGULAR_LITERAL,
			BLOCK_LITERAL,
			DIMENSION_LITERAL,
			XMLPATH_LITERAL,
			XMLREGION_LITERAL,
			XMLCOLUMN_LOGICAL_LITERAL,
			XMLCOLUMN_PHYSICAL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>DB2 Index Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>DB2 Index Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2IndexType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2IndexType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2 Index Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2IndexType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DB2IndexType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DB2 Index Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2IndexType get(int value) {
		switch (value) {
			case REGULAR: return REGULAR_LITERAL;
			case BLOCK: return BLOCK_LITERAL;
			case DIMENSION: return DIMENSION_LITERAL;
			case XMLPATH: return XMLPATH_LITERAL;
			case XMLREGION: return XMLREGION_LITERAL;
			case XMLCOLUMN_LOGICAL: return XMLCOLUMN_LOGICAL_LITERAL;
			case XMLCOLUMN_PHYSICAL: return XMLCOLUMN_PHYSICAL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DB2IndexType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DB2IndexType
