/**
 * <copyright>
 * </copyright>
 *
 * $Id: IntervalQualifierType.java,v 1.4 2006/09/07 00:19:48 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.datatypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Interval Qualifier Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.6.2 Intervals
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalQualifierType()
 * @model
 * @generated
 */
public final class IntervalQualifierType extends AbstractEnumerator {
	/**
	 * The '<em><b>YEAR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #YEAR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int YEAR = 0;

	/**
	 * The '<em><b>MONTH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MONTH_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MONTH = 1;

	/**
	 * The '<em><b>DAY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DAY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DAY = 2;

	/**
	 * The '<em><b>HOUR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HOUR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HOUR = 3;

	/**
	 * The '<em><b>MINUTE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUTE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MINUTE = 4;

	/**
	 * The '<em><b>SECOND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECOND_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SECOND = 5;

	/**
	 * The '<em><b>FRACTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FRACTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FRACTION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FRACTION = 6;

	/**
	 * The '<em><b>YEAR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YEAR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #YEAR
	 * @generated
	 * @ordered
	 */
	public static final IntervalQualifierType YEAR_LITERAL = new IntervalQualifierType(YEAR, "YEAR", "YEAR"); //$NON-NLS-1$

	/**
	 * The '<em><b>MONTH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MONTH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MONTH
	 * @generated
	 * @ordered
	 */
	public static final IntervalQualifierType MONTH_LITERAL = new IntervalQualifierType(MONTH, "MONTH", "MONTH"); //$NON-NLS-1$

	/**
	 * The '<em><b>DAY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DAY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DAY
	 * @generated
	 * @ordered
	 */
	public static final IntervalQualifierType DAY_LITERAL = new IntervalQualifierType(DAY, "DAY", "DAY"); //$NON-NLS-1$

	/**
	 * The '<em><b>HOUR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HOUR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HOUR
	 * @generated
	 * @ordered
	 */
	public static final IntervalQualifierType HOUR_LITERAL = new IntervalQualifierType(HOUR, "HOUR", "HOUR"); //$NON-NLS-1$

	/**
	 * The '<em><b>MINUTE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MINUTE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MINUTE
	 * @generated
	 * @ordered
	 */
	public static final IntervalQualifierType MINUTE_LITERAL = new IntervalQualifierType(MINUTE, "MINUTE", "MINUTE"); //$NON-NLS-1$

	/**
	 * The '<em><b>SECOND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SECOND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SECOND
	 * @generated
	 * @ordered
	 */
	public static final IntervalQualifierType SECOND_LITERAL = new IntervalQualifierType(SECOND, "SECOND", "SECOND"); //$NON-NLS-1$

	/**
	 * The '<em><b>FRACTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FRACTION
	 * @generated
	 * @ordered
	 */
	public static final IntervalQualifierType FRACTION_LITERAL = new IntervalQualifierType(FRACTION, "FRACTION", "FRACTION"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Interval Qualifier Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final IntervalQualifierType[] VALUES_ARRAY =
		new IntervalQualifierType[] {
			YEAR_LITERAL,
			MONTH_LITERAL,
			DAY_LITERAL,
			HOUR_LITERAL,
			MINUTE_LITERAL,
			SECOND_LITERAL,
			FRACTION_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Interval Qualifier Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Interval Qualifier Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntervalQualifierType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IntervalQualifierType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Interval Qualifier Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntervalQualifierType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IntervalQualifierType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Interval Qualifier Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntervalQualifierType get(int value) {
		switch (value) {
			case YEAR: return YEAR_LITERAL;
			case MONTH: return MONTH_LITERAL;
			case DAY: return DAY_LITERAL;
			case HOUR: return HOUR_LITERAL;
			case MINUTE: return MINUTE_LITERAL;
			case SECOND: return SECOND_LITERAL;
			case FRACTION: return FRACTION_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private IntervalQualifierType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //IntervalQualifierType
