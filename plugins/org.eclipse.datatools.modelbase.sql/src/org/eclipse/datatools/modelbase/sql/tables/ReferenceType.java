/**
 * <copyright>
 * </copyright>
 *
 * $Id: ReferenceType.java,v 1.3 2006/09/07 00:19:49 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Reference Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getReferenceType()
 * @model
 * @generated
 */
public final class ReferenceType extends AbstractEnumerator {
	/**
	 * The '<em><b>SYSTEM GENERATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_GENERATED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYSTEM_GENERATED = 0;

	/**
	 * The '<em><b>USER GENERATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USER_GENERATED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USER_GENERATED = 1;

	/**
	 * The '<em><b>DERIVED SELF REF</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DERIVED_SELF_REF_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DERIVED_SELF_REF = 2;

	/**
	 * The '<em><b>SYSTEM GENERATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYSTEM GENERATED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_GENERATED
	 * @generated
	 * @ordered
	 */
	public static final ReferenceType SYSTEM_GENERATED_LITERAL = new ReferenceType(SYSTEM_GENERATED, "SYSTEM_GENERATED", "SYSTEM_GENERATED"); //$NON-NLS-1$

	/**
	 * The '<em><b>USER GENERATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USER GENERATED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USER_GENERATED
	 * @generated
	 * @ordered
	 */
	public static final ReferenceType USER_GENERATED_LITERAL = new ReferenceType(USER_GENERATED, "USER_GENERATED", "USER_GENERATED"); //$NON-NLS-1$

	/**
	 * The '<em><b>DERIVED SELF REF</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DERIVED SELF REF</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DERIVED_SELF_REF
	 * @generated
	 * @ordered
	 */
	public static final ReferenceType DERIVED_SELF_REF_LITERAL = new ReferenceType(DERIVED_SELF_REF, "DERIVED_SELF_REF", "DERIVED_SELF_REF"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Reference Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReferenceType[] VALUES_ARRAY =
		new ReferenceType[] {
			SYSTEM_GENERATED_LITERAL,
			USER_GENERATED_LITERAL,
			DERIVED_SELF_REF_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Reference Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Reference Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferenceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReferenceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reference Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferenceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReferenceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reference Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferenceType get(int value) {
		switch (value) {
			case SYSTEM_GENERATED: return SYSTEM_GENERATED_LITERAL;
			case USER_GENERATED: return USER_GENERATED_LITERAL;
			case DERIVED_SELF_REF: return DERIVED_SELF_REF_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ReferenceType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ReferenceType
