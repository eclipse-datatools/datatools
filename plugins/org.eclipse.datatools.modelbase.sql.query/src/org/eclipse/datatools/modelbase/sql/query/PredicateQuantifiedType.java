/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateQuantifiedType.java,v 1.2 2005/12/19 20:56:36 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>SQL Predicate Quantified Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedType()
 * @model
 * @generated
 */
public final class PredicateQuantifiedType extends AbstractEnumerator {
	/**
	 * The '<em><b>SOME</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #SOME_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int SOME = 0;

	/**
	 * The '<em><b>ANY</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #ANY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int ANY = 1;

	/**
	 * The '<em><b>ALL</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #ALL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int ALL = 2;

	/**
	 * The '<em><b>SOME</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SOME</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #SOME
	 * @generated
	 * @ordered
	 */
    public static final PredicateQuantifiedType SOME_LITERAL = new PredicateQuantifiedType(SOME, "SOME");

	/**
	 * The '<em><b>ANY</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ANY</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #ANY
	 * @generated
	 * @ordered
	 */
    public static final PredicateQuantifiedType ANY_LITERAL = new PredicateQuantifiedType(ANY, "ANY");

	/**
	 * The '<em><b>ALL</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ALL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #ALL
	 * @generated
	 * @ordered
	 */
    public static final PredicateQuantifiedType ALL_LITERAL = new PredicateQuantifiedType(ALL, "ALL");

	/**
	 * An array of all the '<em><b>Predicate Quantified Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final PredicateQuantifiedType[] VALUES_ARRAY =
		new PredicateQuantifiedType[] {
			SOME_LITERAL,
			ANY_LITERAL,
			ALL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Predicate Quantified Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Predicate Quantified Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static PredicateQuantifiedType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PredicateQuantifiedType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Predicate Quantified Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static PredicateQuantifiedType get(int value) {
		switch (value) {
			case SOME: return SOME_LITERAL;
			case ANY: return ANY_LITERAL;
			case ALL: return ALL_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PredicateQuantifiedType(int value, String name) {
		super(value, name);
	}

} //SQLPredicateQuantifiedType
