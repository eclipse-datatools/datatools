/**
 * <copyright>
 * </copyright>
 *
 * $Id: IntervalUnitType.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Interval Unit Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getIntervalUnitType()
 * @model
 * @generated
 */
public final class IntervalUnitType extends AbstractEnumerator {
    /**
	 * The '<em><b>HOURS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HOURS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HOURS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HOURS = 0;

    /**
	 * The '<em><b>MINUTES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MINUTES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MINUTES_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MINUTES = 1;

    /**
	 * The '<em><b>SECONDS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SECONDS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SECONDS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SECONDS = 2;

    /**
	 * The '<em><b>HOURS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HOURS
	 * @generated
	 * @ordered
	 */
	public static final IntervalUnitType HOURS_LITERAL = new IntervalUnitType(HOURS, "HOURS", "HOURS");

    /**
	 * The '<em><b>MINUTES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUTES
	 * @generated
	 * @ordered
	 */
	public static final IntervalUnitType MINUTES_LITERAL = new IntervalUnitType(MINUTES, "MINUTES", "MINUTES");

    /**
	 * The '<em><b>SECONDS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECONDS
	 * @generated
	 * @ordered
	 */
	public static final IntervalUnitType SECONDS_LITERAL = new IntervalUnitType(SECONDS, "SECONDS", "SECONDS");

    /**
	 * An array of all the '<em><b>Interval Unit Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final IntervalUnitType[] VALUES_ARRAY =
        new IntervalUnitType[] {
			HOURS_LITERAL,
			MINUTES_LITERAL,
			SECONDS_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Interval Unit Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Interval Unit Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntervalUnitType get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IntervalUnitType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Interval Unit Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntervalUnitType getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IntervalUnitType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Interval Unit Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntervalUnitType get(int value)
    {
		switch (value) {
			case HOURS: return HOURS_LITERAL;
			case MINUTES: return MINUTES_LITERAL;
			case SECONDS: return SECONDS_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private IntervalUnitType(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //IntervalUnitType
