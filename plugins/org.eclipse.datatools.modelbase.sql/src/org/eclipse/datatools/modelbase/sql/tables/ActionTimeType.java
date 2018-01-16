/**
 * <copyright>
 * </copyright>
 *
 * $Id: ActionTimeType.java,v 1.3 2006/09/07 00:19:49 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Action Time Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.38 Triggers
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getActionTimeType()
 * @model
 * @generated
 */
public final class ActionTimeType extends AbstractEnumerator {
	/**
	 * The '<em><b>AFTER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AFTER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AFTER = 0;

	/**
	 * The '<em><b>BEFORE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BEFORE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BEFORE = 1;

	/**
	 * The '<em><b>INSTEADOF</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSTEADOF_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INSTEADOF = 2;

	/**
	 * The '<em><b>AFTER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AFTER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AFTER
	 * @generated
	 * @ordered
	 */
	public static final ActionTimeType AFTER_LITERAL = new ActionTimeType(AFTER, "AFTER", "AFTER"); //$NON-NLS-1$

	/**
	 * The '<em><b>BEFORE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BEFORE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BEFORE
	 * @generated
	 * @ordered
	 */
	public static final ActionTimeType BEFORE_LITERAL = new ActionTimeType(BEFORE, "BEFORE", "BEFORE"); //$NON-NLS-1$

	/**
	 * The '<em><b>INSTEADOF</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INSTEADOF</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INSTEADOF
	 * @generated
	 * @ordered
	 */
	public static final ActionTimeType INSTEADOF_LITERAL = new ActionTimeType(INSTEADOF, "INSTEADOF", "INSTEADOF"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Action Time Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ActionTimeType[] VALUES_ARRAY =
		new ActionTimeType[] {
			AFTER_LITERAL,
			BEFORE_LITERAL,
			INSTEADOF_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Action Time Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Action Time Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionTimeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ActionTimeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Action Time Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionTimeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ActionTimeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Action Time Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionTimeType get(int value) {
		switch (value) {
			case AFTER: return AFTER_LITERAL;
			case BEFORE: return BEFORE_LITERAL;
			case INSTEADOF: return INSTEADOF_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ActionTimeType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ActionTimeType
