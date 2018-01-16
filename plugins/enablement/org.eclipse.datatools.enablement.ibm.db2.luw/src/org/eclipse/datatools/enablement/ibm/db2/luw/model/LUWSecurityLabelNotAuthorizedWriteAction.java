/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Security Label Not Authorized Write Action</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityLabelNotAuthorizedWriteAction()
 * @model
 * @generated
 */
public final class LUWSecurityLabelNotAuthorizedWriteAction extends AbstractEnumerator {
	/**
	 * The '<em><b>OVERRIDE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OVERRIDE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OVERRIDE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OVERRIDE = 0;

	/**
	 * The '<em><b>RESTRICT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESTRICT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESTRICT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESTRICT = 1;

	/**
	 * The '<em><b>OVERRIDE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OVERRIDE
	 * @generated
	 * @ordered
	 */
	public static final LUWSecurityLabelNotAuthorizedWriteAction OVERRIDE_LITERAL = new LUWSecurityLabelNotAuthorizedWriteAction(OVERRIDE, "OVERRIDE", "OVERRIDE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>RESTRICT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESTRICT
	 * @generated
	 * @ordered
	 */
	public static final LUWSecurityLabelNotAuthorizedWriteAction RESTRICT_LITERAL = new LUWSecurityLabelNotAuthorizedWriteAction(RESTRICT, "RESTRICT", "RESTRICT"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Security Label Not Authorized Write Action</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LUWSecurityLabelNotAuthorizedWriteAction[] VALUES_ARRAY =
		new LUWSecurityLabelNotAuthorizedWriteAction[] {
			OVERRIDE_LITERAL,
			RESTRICT_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Security Label Not Authorized Write Action</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Security Label Not Authorized Write Action</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWSecurityLabelNotAuthorizedWriteAction get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWSecurityLabelNotAuthorizedWriteAction result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Security Label Not Authorized Write Action</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWSecurityLabelNotAuthorizedWriteAction getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWSecurityLabelNotAuthorizedWriteAction result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Security Label Not Authorized Write Action</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWSecurityLabelNotAuthorizedWriteAction get(int value) {
		switch (value) {
			case OVERRIDE: return OVERRIDE_LITERAL;
			case RESTRICT: return RESTRICT_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LUWSecurityLabelNotAuthorizedWriteAction(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LUWSecurityLabelNotAuthorizedWriteAction
