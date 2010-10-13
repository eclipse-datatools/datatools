/**
 * <copyright>
 * </copyright>
 *
 * $Id: AccessRuleType.java,v 1.1 2008/03/27 07:41:13 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Access Rule Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getAccessRuleType()
 * @model
 * @generated
 */
public final class AccessRuleType extends AbstractEnumerator {
	/**
	 * The '<em><b>DEF</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DEF</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #DEF_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int DEF = 0;

	/**
	 * The '<em><b>AND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AND_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AND = 1;

	/**
	 * The '<em><b>OR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OR = 2;

    /**
	 * The '<em><b>DEF</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #DEF
	 * @generated
	 * @ordered
	 */
    public static final AccessRuleType DEF_LITERAL = new AccessRuleType(DEF, "DEF", "DEF"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>AND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AND
	 * @generated
	 * @ordered
	 */
public static final AccessRuleType AND_LITERAL = new AccessRuleType(AND, "AND", "AND"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>OR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OR
	 * @generated
	 * @ordered
	 */
	public static final AccessRuleType OR_LITERAL = new AccessRuleType(OR, "OR", "OR"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
	 * An array of all the '<em><b>Access Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AccessRuleType[] VALUES_ARRAY =
		new AccessRuleType[] {
			DEF_LITERAL,
			AND_LITERAL,
			OR_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Access Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Access Rule Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AccessRuleType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AccessRuleType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Access Rule Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AccessRuleType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AccessRuleType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Access Rule Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AccessRuleType get(int value) {
		switch (value) {
			case DEF: return DEF_LITERAL;
			case AND: return AND_LITERAL;
			case OR: return OR_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AccessRuleType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //AccessRuleType
