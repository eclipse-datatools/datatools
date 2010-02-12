/**
 * <copyright>
 * </copyright>
 *
 * $Id: ParentDeleteDRIRuleType.java,v 1.3 2006/10/11 20:34:55 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Parent Delete DRI Rule Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getParentDeleteDRIRuleType()
 * @model
 * @generated
 */
public final class ParentDeleteDRIRuleType extends AbstractEnumerator {
	/**
	 * The '<em><b>NO ACTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_ACTION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_ACTION = 0;

	/**
	 * The '<em><b>RESTRICT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESTRICT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESTRICT = 1;

	/**
	 * The '<em><b>CASCADE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CASCADE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CASCADE = 2;

	/**
	 * The '<em><b>SET NULL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SET_NULL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SET_NULL = 3;

	/**
	 * The '<em><b>SET DEFAULT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SET_DEFAULT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SET_DEFAULT = 4;

	/**
	 * The '<em><b>NO ACTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO ACTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_ACTION
	 * @generated
	 * @ordered
	 */
	public static final ParentDeleteDRIRuleType NO_ACTION_LITERAL = new ParentDeleteDRIRuleType(NO_ACTION, "NO_ACTION", "NO_ACTION"); //$NON-NLS-1$

	/**
	 * The '<em><b>RESTRICT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESTRICT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESTRICT
	 * @generated
	 * @ordered
	 */
	public static final ParentDeleteDRIRuleType RESTRICT_LITERAL = new ParentDeleteDRIRuleType(RESTRICT, "RESTRICT", "RESTRICT"); //$NON-NLS-1$

	/**
	 * The '<em><b>CASCADE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CASCADE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CASCADE
	 * @generated
	 * @ordered
	 */
	public static final ParentDeleteDRIRuleType CASCADE_LITERAL = new ParentDeleteDRIRuleType(CASCADE, "CASCADE", "CASCADE"); //$NON-NLS-1$

	/**
	 * The '<em><b>SET NULL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SET NULL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SET_NULL
	 * @generated
	 * @ordered
	 */
	public static final ParentDeleteDRIRuleType SET_NULL_LITERAL = new ParentDeleteDRIRuleType(SET_NULL, "SET_NULL", "SET_NULL"); //$NON-NLS-1$

	/**
	 * The '<em><b>SET DEFAULT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SET DEFAULT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SET_DEFAULT
	 * @generated
	 * @ordered
	 */
	public static final ParentDeleteDRIRuleType SET_DEFAULT_LITERAL = new ParentDeleteDRIRuleType(SET_DEFAULT, "SET_DEFAULT", "SET_DEFAULT"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Parent Delete DRI Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ParentDeleteDRIRuleType[] VALUES_ARRAY =
		new ParentDeleteDRIRuleType[] {
			NO_ACTION_LITERAL,
			RESTRICT_LITERAL,
			CASCADE_LITERAL,
			SET_NULL_LITERAL,
			SET_DEFAULT_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Parent Delete DRI Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Parent Delete DRI Rule Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParentDeleteDRIRuleType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParentDeleteDRIRuleType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Parent Delete DRI Rule Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParentDeleteDRIRuleType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParentDeleteDRIRuleType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Parent Delete DRI Rule Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParentDeleteDRIRuleType get(int value) {
		switch (value) {
			case NO_ACTION: return NO_ACTION_LITERAL;
			case RESTRICT: return RESTRICT_LITERAL;
			case CASCADE: return CASCADE_LITERAL;
			case SET_NULL: return SET_NULL_LITERAL;
			case SET_DEFAULT: return SET_DEFAULT_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ParentDeleteDRIRuleType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ParentDeleteDRIRuleType
