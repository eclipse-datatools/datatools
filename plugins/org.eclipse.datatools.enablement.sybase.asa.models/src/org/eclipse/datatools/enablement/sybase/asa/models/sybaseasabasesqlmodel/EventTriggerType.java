/**
 * <copyright>
 * </copyright>
 *
 * $Id: EventTriggerType.java,v 1.3 2007/02/08 01:41:34 linsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Event Trigger Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getEventTriggerType()
 * @model
 * @generated
 */
public final class EventTriggerType extends AbstractEnumerator {
	/**
	 * The '<em><b>MANUALLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MANUALLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MANUALLY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MANUALLY = 0;

	/**
	 * The '<em><b>SCHEDULED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SCHEDULED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SCHEDULED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SCHEDULED = 1;

	/**
	 * The '<em><b>CONDITIONAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONDITIONAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONDITIONAL = 2;

	/**
	 * The '<em><b>MANUALLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MANUALLY
	 * @generated
	 * @ordered
	 */
	public static final EventTriggerType MANUALLY_LITERAL = new EventTriggerType(MANUALLY, "MANUALLY", "MANUALLY");

	/**
	 * The '<em><b>SCHEDULED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCHEDULED
	 * @generated
	 * @ordered
	 */
	public static final EventTriggerType SCHEDULED_LITERAL = new EventTriggerType(SCHEDULED, "SCHEDULED", "SCHEDULED");

	/**
	 * The '<em><b>CONDITIONAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL
	 * @generated
	 * @ordered
	 */
	public static final EventTriggerType CONDITIONAL_LITERAL = new EventTriggerType(CONDITIONAL, "CONDITIONAL", "CONDITIONAL");

	/**
	 * An array of all the '<em><b>Event Trigger Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EventTriggerType[] VALUES_ARRAY =
		new EventTriggerType[] {
			MANUALLY_LITERAL,
			SCHEDULED_LITERAL,
			CONDITIONAL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Event Trigger Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Event Trigger Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventTriggerType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EventTriggerType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Event Trigger Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventTriggerType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EventTriggerType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Event Trigger Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventTriggerType get(int value) {
		switch (value) {
			case MANUALLY: return MANUALLY_LITERAL;
			case SCHEDULED: return SCHEDULED_LITERAL;
			case CONDITIONAL: return CONDITIONAL_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EventTriggerType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //EventTriggerType
