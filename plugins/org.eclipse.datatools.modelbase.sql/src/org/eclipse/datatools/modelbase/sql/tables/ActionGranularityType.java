/**
 * <copyright>
 * </copyright>
 *
 * $Id: ActionGranularityType.java,v 1.4 2005/06/15 18:15:25 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.sql.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Action Granularity Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The literals of this enumeration are used to indicate whether the actionStatement of a Trigger is to be executed once for each affected row, in the case of a row-level trigger, or once for the whole triggering INSERT , DELETE , MERGE , or UPDATE
 * statement, in the case of a statement-level trigger.
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getActionGranularityType()
 * @model
 * @generated
 */
public final class ActionGranularityType extends AbstractEnumerator {
	/**
	 * The '<em><b>STATEMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATEMENT_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int STATEMENT = 0;

	/**
	 * The '<em><b>ROW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROW_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int ROW = 1;

	/**
	 * The '<em><b>STATEMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STATEMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATEMENT
	 * @generated
	 * @ordered
	 */
	public static final ActionGranularityType STATEMENT_LITERAL = new ActionGranularityType(STATEMENT, "STATEMENT"); //$NON-NLS-1$

	/**
	 * The '<em><b>ROW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ROW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROW
	 * @generated
	 * @ordered
	 */
	public static final ActionGranularityType ROW_LITERAL = new ActionGranularityType(ROW, "ROW"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Action Granularity Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ActionGranularityType[] VALUES_ARRAY =
		new ActionGranularityType[] {
			STATEMENT_LITERAL,
			ROW_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Action Granularity Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Action Granularity Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionGranularityType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ActionGranularityType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Action Granularity Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionGranularityType get(int value) {
		switch (value) {
			case STATEMENT: return STATEMENT_LITERAL;
			case ROW: return ROW_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ActionGranularityType(int value, String name) {
		super(value, name);
	}

} //ActionGranularityType
