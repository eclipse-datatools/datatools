/**
 * <copyright>
 * </copyright>
 *
 * $Id: PercentFreeTerminology.java,v 1.1 2005/08/02 22:56:24 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Percent Free Terminology</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPercentFreeTerminology()
 * @model
 * @generated
 */
public final class PercentFreeTerminology extends AbstractEnumerator {
	/**
	 * The '<em><b>PERCENT FREE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PERCENT_FREE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PERCENT_FREE = 0;

	/**
	 * The '<em><b>FILL FACTOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FILL_FACTOR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FILL_FACTOR = 1;

	/**
	 * The '<em><b>THRESHOLD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #THRESHOLD_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int THRESHOLD = 2;

	/**
	 * The '<em><b>PERCENT FREE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PERCENT FREE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PERCENT_FREE
	 * @generated
	 * @ordered
	 */
	public static final PercentFreeTerminology PERCENT_FREE_LITERAL = new PercentFreeTerminology(PERCENT_FREE, "PERCENT_FREE"); //$NON-NLS-1$

	/**
	 * The '<em><b>FILL FACTOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FILL FACTOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FILL_FACTOR
	 * @generated
	 * @ordered
	 */
	public static final PercentFreeTerminology FILL_FACTOR_LITERAL = new PercentFreeTerminology(FILL_FACTOR, "FILL_FACTOR"); //$NON-NLS-1$

	/**
	 * The '<em><b>THRESHOLD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>THRESHOLD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #THRESHOLD
	 * @generated
	 * @ordered
	 */
	public static final PercentFreeTerminology THRESHOLD_LITERAL = new PercentFreeTerminology(THRESHOLD, "THRESHOLD"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Percent Free Terminology</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PercentFreeTerminology[] VALUES_ARRAY =
		new PercentFreeTerminology[] {
			PERCENT_FREE_LITERAL,
			FILL_FACTOR_LITERAL,
			THRESHOLD_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Percent Free Terminology</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Percent Free Terminology</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PercentFreeTerminology get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PercentFreeTerminology result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Percent Free Terminology</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PercentFreeTerminology get(int value) {
		switch (value) {
			case PERCENT_FREE: return PERCENT_FREE_LITERAL;
			case FILL_FACTOR: return FILL_FACTOR_LITERAL;
			case THRESHOLD: return THRESHOLD_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PercentFreeTerminology(int value, String name) {
		super(value, name);
	}

} //PercentFreeTerminology
