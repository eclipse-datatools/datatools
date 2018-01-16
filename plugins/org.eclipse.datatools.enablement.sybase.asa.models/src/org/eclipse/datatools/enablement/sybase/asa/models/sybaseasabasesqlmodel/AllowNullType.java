/**
 * <copyright>
 * </copyright>
 *
 * $Id: AllowNullType.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Allow Null Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getAllowNullType()
 * @model
 * @generated
 */
public final class AllowNullType extends AbstractEnumerator {
    /**
	 * The '<em><b>Nullable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Nullable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NULLABLE_LITERAL
	 * @model name="Nullable"
	 * @generated
	 * @ordered
	 */
	public static final int NULLABLE = 0;

    /**
	 * The '<em><b>Not Nullable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Not Nullable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_NULLABLE_LITERAL
	 * @model name="Not_Nullable"
	 * @generated
	 * @ordered
	 */
	public static final int NOT_NULLABLE = 1;

    /**
	 * The '<em><b>Database Default</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Database Default</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DATABASE_DEFAULT_LITERAL
	 * @model name="Database_Default"
	 * @generated
	 * @ordered
	 */
	public static final int DATABASE_DEFAULT = 2;

    /**
	 * The '<em><b>Nullable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NULLABLE
	 * @generated
	 * @ordered
	 */
	public static final AllowNullType NULLABLE_LITERAL = new AllowNullType(NULLABLE, "Nullable", "Nullable");

    /**
	 * The '<em><b>Not Nullable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_NULLABLE
	 * @generated
	 * @ordered
	 */
	public static final AllowNullType NOT_NULLABLE_LITERAL = new AllowNullType(NOT_NULLABLE, "Not_Nullable", "Not_Nullable");

    /**
	 * The '<em><b>Database Default</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DATABASE_DEFAULT
	 * @generated
	 * @ordered
	 */
	public static final AllowNullType DATABASE_DEFAULT_LITERAL = new AllowNullType(DATABASE_DEFAULT, "Database_Default", "Database_Default");

    /**
	 * An array of all the '<em><b>Allow Null Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AllowNullType[] VALUES_ARRAY =
        new AllowNullType[] {
			NULLABLE_LITERAL,
			NOT_NULLABLE_LITERAL,
			DATABASE_DEFAULT_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Allow Null Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Allow Null Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AllowNullType get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AllowNullType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Allow Null Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AllowNullType getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AllowNullType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Allow Null Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AllowNullType get(int value)
    {
		switch (value) {
			case NULLABLE: return NULLABLE_LITERAL;
			case NOT_NULLABLE: return NOT_NULLABLE_LITERAL;
			case DATABASE_DEFAULT: return DATABASE_DEFAULT_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AllowNullType(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //AllowNullType
