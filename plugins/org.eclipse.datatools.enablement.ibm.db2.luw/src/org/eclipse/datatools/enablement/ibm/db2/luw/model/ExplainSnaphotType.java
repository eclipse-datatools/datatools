/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExplainSnaphotType.java,v 1.1 2009/02/23 22:15:32 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Explain Snaphot Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getExplainSnaphotType()
 * @model
 * @generated
 */
public final class ExplainSnaphotType extends AbstractEnumerator {
	/**
	 * The '<em><b>ALL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALL = 0;

	/**
	 * The '<em><b>NO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO = 1;

	/**
	 * The '<em><b>REOPT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REOPT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REOPT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REOPT = 2;

	/**
	 * The '<em><b>YES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #YES_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int YES = 3;

	/**
	 * The '<em><b>ALL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL
	 * @generated
	 * @ordered
	 */
	public static final ExplainSnaphotType ALL_LITERAL = new ExplainSnaphotType(ALL, "ALL", "ALL"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO
	 * @generated
	 * @ordered
	 */
	public static final ExplainSnaphotType NO_LITERAL = new ExplainSnaphotType(NO, "NO", "NO"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>REOPT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REOPT
	 * @generated
	 * @ordered
	 */
	public static final ExplainSnaphotType REOPT_LITERAL = new ExplainSnaphotType(REOPT, "REOPT", "REOPT"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>YES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #YES
	 * @generated
	 * @ordered
	 */
	public static final ExplainSnaphotType YES_LITERAL = new ExplainSnaphotType(YES, "YES", "YES"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Explain Snaphot Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ExplainSnaphotType[] VALUES_ARRAY =
		new ExplainSnaphotType[] {
			ALL_LITERAL,
			NO_LITERAL,
			REOPT_LITERAL,
			YES_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Explain Snaphot Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Explain Snaphot Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExplainSnaphotType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ExplainSnaphotType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Explain Snaphot Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExplainSnaphotType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ExplainSnaphotType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Explain Snaphot Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExplainSnaphotType get(int value) {
		switch (value) {
			case ALL: return ALL_LITERAL;
			case NO: return NO_LITERAL;
			case REOPT: return REOPT_LITERAL;
			case YES: return YES_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ExplainSnaphotType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ExplainSnaphotType
