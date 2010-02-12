/**
 * <copyright>
 * </copyright>
 *
 * $Id: CheckOption.java,v 1.3 2006/10/11 20:34:55 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Check Option</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getCheckOption()
 * @model
 * @generated
 */
public final class CheckOption extends AbstractEnumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE = 0;

	/**
	 * The '<em><b>CASCADE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CASCADE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CASCADE = 1;

	/**
	 * The '<em><b>LOCAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOCAL = 2;

	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
	public static final CheckOption NONE_LITERAL = new CheckOption(NONE, "NONE", "NONE"); //$NON-NLS-1$

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
	public static final CheckOption CASCADE_LITERAL = new CheckOption(CASCADE, "CASCADE", "CASCADE"); //$NON-NLS-1$

	/**
	 * The '<em><b>LOCAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL
	 * @generated
	 * @ordered
	 */
	public static final CheckOption LOCAL_LITERAL = new CheckOption(LOCAL, "LOCAL", "LOCAL"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Check Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CheckOption[] VALUES_ARRAY =
		new CheckOption[] {
			NONE_LITERAL,
			CASCADE_LITERAL,
			LOCAL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Check Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Check Option</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CheckOption get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CheckOption result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Check Option</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CheckOption getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CheckOption result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Check Option</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CheckOption get(int value) {
		switch (value) {
			case NONE: return NONE_LITERAL;
			case CASCADE: return CASCADE_LITERAL;
			case LOCAL: return LOCAL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CheckOption(int value, String name, String literal) {
		super(value, name, literal);
	}

} //CheckOption
