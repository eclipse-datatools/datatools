/**
 * <copyright>
 * </copyright>
 *
 * $Id: CoercibilityType.java,v 1.4 2005/06/15 18:15:25 ledunnel Exp $
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
 * 4.2.1 Comparison of character strings
 * 
 * Two character strings are comparable if and only if either they have the same character set or there exists at least one collation that is applicable to both their respective character sets.
 * 
 * A collation is defined by ISO/IEC 14651 as "a process by which two strings are determined to be in exactly one of the relationships of less than, greater than, or equal to one another". Each collation known in an SQLenvironment is applicable to one or more character sets, and for each character set, one or more collations are applicable to it, one of which is associated with it as its character set collation.
 * 
 * Anything that has a declared type can, if that type is a character string type, be associated with a collation applicable to its character set; this is known as a declared type collation. Every declared type that is a character string type has a collation derivation, this being either none, implicit, or explicit. The collation derivation of a declared type with a declared type collation that is explicitly or implicitly specified by a <data type> is implicit. If the collation derivation of a declared type that has a declared type collation is not implicit, then it is explicit. The collation derivation of an expression of character string type that has no declared type collation is none.
 * 
 * An operation that explicitly or implicitly involves character string comparison is a character comparison operation. At least one of the operands of a character comparison operation shall have a declared type collation. There may be an SQL-session collation for some or all of the character sets known to the SQL-implementation (see Subclause 4.37, "SQL-sessions").
 * 
 * The collation used for a particular character comparison is specified by Subclause 9.13, "Collation determination". The comparison of two character string expressions depends on the collation used for the comparison (see Subclause 9.13, "Collation determination"). When values of unequal length are compared, if the collation for the comparison has the NO PAD characteristic and the shorter value is equal to some prefix of the longer value, then the shorter value is considered less than the longer value. If the collation for the comparison has the PAD SPACE characteristic, for the purposes of the comparison, the shorter value is effectively extended to the length of the longer by concatenation of <space> s on the right. For every character set, there is at least one collation.
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
	public static final CoercibilityType IMPLICIT_LITERAL = new CoercibilityType(IMPLICIT, "IMPLICIT"); //$NON-NLS-1$

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
	public static final CoercibilityType EXPLICIT_LITERAL = new CoercibilityType(EXPLICIT, "EXPLICIT"); //$NON-NLS-1$

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
	public static final CoercibilityType COERCIBILE_LITERAL = new CoercibilityType(COERCIBILE, "COERCIBILE"); //$NON-NLS-1$

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
	public static final CoercibilityType NO_COLLATION_LITERAL = new CoercibilityType(NO_COLLATION, "NO_COLLATION"); //$NON-NLS-1$

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
	 * Returns the '<em><b>Coercibility Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CoercibilityType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CoercibilityType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Coercibility Type</b></em>' literal with the specified value.
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
	private CoercibilityType(int value, String name) {
		super(value, name);
	}

} //CoercibilityType
