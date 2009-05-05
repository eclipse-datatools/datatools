/**
 * <copyright>
 * </copyright>
 *
 * $Id: EventLocationType.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Event Location Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getEventLocationType()
 * @model
 * @generated
 */
public final class EventLocationType extends AbstractEnumerator {
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
	 * The '<em><b>REMOTE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REMOTE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMOTE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REMOTE = 1;

				/**
	 * The '<em><b>CONSOLIDATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONSOLIDATED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONSOLIDATED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONSOLIDATED = 2;

    /**
	 * The '<em><b>ALL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL
	 * @generated
	 * @ordered
	 */
	public static final EventLocationType ALL_LITERAL = new EventLocationType(ALL, "ALL", "ALL");

				/**
	 * The '<em><b>REMOTE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMOTE
	 * @generated
	 * @ordered
	 */
	public static final EventLocationType REMOTE_LITERAL = new EventLocationType(REMOTE, "REMOTE", "REMOTE");

				/**
	 * The '<em><b>CONSOLIDATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONSOLIDATED
	 * @generated
	 * @ordered
	 */
	public static final EventLocationType CONSOLIDATED_LITERAL = new EventLocationType(CONSOLIDATED, "CONSOLIDATED", "CONSOLIDATED");

    /**
	 * An array of all the '<em><b>Event Location Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EventLocationType[] VALUES_ARRAY =
        new EventLocationType[] {
			ALL_LITERAL,
			REMOTE_LITERAL,
			CONSOLIDATED_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Event Location Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Event Location Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventLocationType get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EventLocationType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Event Location Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventLocationType getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EventLocationType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Event Location Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventLocationType get(int value)
    {
		switch (value) {
			case ALL: return ALL_LITERAL;
			case REMOTE: return REMOTE_LITERAL;
			case CONSOLIDATED: return CONSOLIDATED_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EventLocationType(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //EventLocationType
