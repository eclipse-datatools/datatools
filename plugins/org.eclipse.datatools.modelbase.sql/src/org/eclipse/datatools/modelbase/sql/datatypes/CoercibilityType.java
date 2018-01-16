/**
 * <copyright>
 * </copyright>
 *
 * $Id: CoercibilityType.java,v 1.3 2006/09/07 00:19:48 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.datatypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Coercibility Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.2.1 Comparison of character strings
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCoercibilityType()
 * @model
 * @generated
 */
public final class CoercibilityType extends AbstractEnumerator {
	/**
	 * The '<em><b>IMPLICIT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPLICIT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int IMPLICIT = 0;

	/**
	 * The '<em><b>EXPLICIT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPLICIT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EXPLICIT = 1;

	/**
	 * The '<em><b>COERCIBILE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COERCIBILE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COERCIBILE = 2;

	/**
	 * The '<em><b>NO COLLATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_COLLATION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_COLLATION = 3;

	/**
	 * The '<em><b>IMPLICIT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IMPLICIT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IMPLICIT
	 * @generated
	 * @ordered
	 */
	public static final CoercibilityType IMPLICIT_LITERAL = new CoercibilityType(IMPLICIT, "IMPLICIT", "IMPLICIT"); //$NON-NLS-1$

	/**
	 * The '<em><b>EXPLICIT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EXPLICIT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXPLICIT
	 * @generated
	 * @ordered
	 */
	public static final CoercibilityType EXPLICIT_LITERAL = new CoercibilityType(EXPLICIT, "EXPLICIT", "EXPLICIT"); //$NON-NLS-1$

	/**
	 * The '<em><b>COERCIBILE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COERCIBILE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COERCIBILE
	 * @generated
	 * @ordered
	 */
	public static final CoercibilityType COERCIBILE_LITERAL = new CoercibilityType(COERCIBILE, "COERCIBILE", "COERCIBILE"); //$NON-NLS-1$

	/**
	 * The '<em><b>NO COLLATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO COLLATION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_COLLATION
	 * @generated
	 * @ordered
	 */
	public static final CoercibilityType NO_COLLATION_LITERAL = new CoercibilityType(NO_COLLATION, "NO_COLLATION", "NO_COLLATION"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Coercibility Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CoercibilityType[] VALUES_ARRAY =
		new CoercibilityType[] {
			IMPLICIT_LITERAL,
			EXPLICIT_LITERAL,
			COERCIBILE_LITERAL,
			NO_COLLATION_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Coercibility Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Coercibility Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CoercibilityType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CoercibilityType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Coercibility Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CoercibilityType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CoercibilityType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Coercibility Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CoercibilityType get(int value) {
		switch (value) {
			case IMPLICIT: return IMPLICIT_LITERAL;
			case EXPLICIT: return EXPLICIT_LITERAL;
			case COERCIBILE: return COERCIBILE_LITERAL;
			case NO_COLLATION: return NO_COLLATION_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CoercibilityType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //CoercibilityType
