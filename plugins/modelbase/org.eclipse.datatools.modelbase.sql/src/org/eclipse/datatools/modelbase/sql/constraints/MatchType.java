/**
 * <copyright>
 * </copyright>
 *
 * $Id: MatchType.java,v 1.3 2006/09/07 00:19:49 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.constraints;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Match Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * See KeyRelationship description.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getMatchType()
 * @model
 * @generated
 */
public final class MatchType extends AbstractEnumerator {
	/**
	 * The '<em><b>MATCH SIMPLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MATCH_SIMPLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MATCH_SIMPLE = 0;

	/**
	 * The '<em><b>MATCH FULL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MATCH_FULL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MATCH_FULL = 1;

	/**
	 * The '<em><b>MATCH PARTIAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MATCH_PARTIAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MATCH_PARTIAL = 2;

	/**
	 * The '<em><b>MATCH SIMPLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MATCH SIMPLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MATCH_SIMPLE
	 * @generated
	 * @ordered
	 */
	public static final MatchType MATCH_SIMPLE_LITERAL = new MatchType(MATCH_SIMPLE, "MATCH_SIMPLE", "MATCH_SIMPLE"); //$NON-NLS-1$

	/**
	 * The '<em><b>MATCH FULL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MATCH FULL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MATCH_FULL
	 * @generated
	 * @ordered
	 */
	public static final MatchType MATCH_FULL_LITERAL = new MatchType(MATCH_FULL, "MATCH_FULL", "MATCH_FULL"); //$NON-NLS-1$

	/**
	 * The '<em><b>MATCH PARTIAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MATCH PARTIAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MATCH_PARTIAL
	 * @generated
	 * @ordered
	 */
	public static final MatchType MATCH_PARTIAL_LITERAL = new MatchType(MATCH_PARTIAL, "MATCH_PARTIAL", "MATCH_PARTIAL"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Match Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MatchType[] VALUES_ARRAY =
		new MatchType[] {
			MATCH_SIMPLE_LITERAL,
			MATCH_FULL_LITERAL,
			MATCH_PARTIAL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Match Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Match Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MatchType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MatchType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Match Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MatchType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MatchType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Match Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MatchType get(int value) {
		switch (value) {
			case MATCH_SIMPLE: return MATCH_SIMPLE_LITERAL;
			case MATCH_FULL: return MATCH_FULL_LITERAL;
			case MATCH_PARTIAL: return MATCH_PARTIAL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MatchType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //MatchType
