/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableSpaceType.java,v 1.3 2006/10/11 20:34:55 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Table Space Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceType()
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
	 * The '<em><b>LOB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOB = 1;

	/**
	 * The '<em><b>SYSTEM TEMPORARY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_TEMPORARY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYSTEM_TEMPORARY = 2;

	/**
	 * The '<em><b>USER TEMPORARY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USER_TEMPORARY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USER_TEMPORARY = 3;

	/**
	 * The '<em><b>PERMANENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PERMANENT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PERMANENT = 4;

	/**
	 * The '<em><b>TEMPORARY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMPORARY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TEMPORARY = 5;

	/**
	 * The '<em><b>LONG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LONG_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LONG = 6;

	/**
	 * The '<em><b>LARGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LARGE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LARGE = 7;

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
	 * The '<em><b>LOB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOB
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType LOB_LITERAL = new TableSpaceType(LOB, "LOB", "LOB"); //$NON-NLS-1$

	/**
	 * The '<em><b>SYSTEM TEMPORARY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYSTEM TEMPORARY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_TEMPORARY
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType SYSTEM_TEMPORARY_LITERAL = new TableSpaceType(SYSTEM_TEMPORARY, "SYSTEM_TEMPORARY", "SYSTEM_TEMPORARY"); //$NON-NLS-1$

	/**
	 * The '<em><b>USER TEMPORARY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USER TEMPORARY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USER_TEMPORARY
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType USER_TEMPORARY_LITERAL = new TableSpaceType(USER_TEMPORARY, "USER_TEMPORARY", "USER_TEMPORARY"); //$NON-NLS-1$

	/**
	 * The '<em><b>PERMANENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PERMANENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PERMANENT
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType PERMANENT_LITERAL = new TableSpaceType(PERMANENT, "PERMANENT", "PERMANENT"); //$NON-NLS-1$

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
	public static final TableSpaceType TEMPORARY_LITERAL = new TableSpaceType(TEMPORARY, "TEMPORARY", "TEMPORARY"); //$NON-NLS-1$

	/**
	 * The '<em><b>LONG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LONG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LONG
	 * @generated
	 * @ordered
	 */
	public static final TableSpaceType LONG_LITERAL = new TableSpaceType(LONG, "LONG", "LONG"); //$NON-NLS-1$

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
	 * An array of all the '<em><b>Table Space Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TableSpaceType[] VALUES_ARRAY =
		new TableSpaceType[] {
			REGULAR_LITERAL,
			LOB_LITERAL,
			SYSTEM_TEMPORARY_LITERAL,
			USER_TEMPORARY_LITERAL,
			PERMANENT_LITERAL,
			TEMPORARY_LITERAL,
			LONG_LITERAL,
			LARGE_LITERAL,
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
			case LOB: return LOB_LITERAL;
			case SYSTEM_TEMPORARY: return SYSTEM_TEMPORARY_LITERAL;
			case USER_TEMPORARY: return USER_TEMPORARY_LITERAL;
			case PERMANENT: return PERMANENT_LITERAL;
			case TEMPORARY: return TEMPORARY_LITERAL;
			case LONG: return LONG_LITERAL;
			case LARGE: return LARGE_LITERAL;
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
