/**
 * <copyright>
 * </copyright>
 *
 * $Id: LinkControlOption.java,v 1.3 2006/09/07 00:19:48 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.datatypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Link Control Option</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getLinkControlOption()
 * @model
 * @generated
 */
public final class LinkControlOption extends AbstractEnumerator {
	/**
	 * The '<em><b>FILE LINK CONTROL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FILE_LINK_CONTROL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FILE_LINK_CONTROL = 0;

	/**
	 * The '<em><b>NO FILE LINK CONTROL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_FILE_LINK_CONTROL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_FILE_LINK_CONTROL = 1;

	/**
	 * The '<em><b>FILE LINK CONTROL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FILE LINK CONTROL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FILE_LINK_CONTROL
	 * @generated
	 * @ordered
	 */
	public static final LinkControlOption FILE_LINK_CONTROL_LITERAL = new LinkControlOption(FILE_LINK_CONTROL, "FILE_LINK_CONTROL", "FILE_LINK_CONTROL"); //$NON-NLS-1$

	/**
	 * The '<em><b>NO FILE LINK CONTROL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO FILE LINK CONTROL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_FILE_LINK_CONTROL
	 * @generated
	 * @ordered
	 */
	public static final LinkControlOption NO_FILE_LINK_CONTROL_LITERAL = new LinkControlOption(NO_FILE_LINK_CONTROL, "NO_FILE_LINK_CONTROL", "NO_FILE_LINK_CONTROL"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Link Control Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LinkControlOption[] VALUES_ARRAY =
		new LinkControlOption[] {
			FILE_LINK_CONTROL_LITERAL,
			NO_FILE_LINK_CONTROL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Link Control Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Link Control Option</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LinkControlOption get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LinkControlOption result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Link Control Option</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LinkControlOption getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LinkControlOption result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Link Control Option</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LinkControlOption get(int value) {
		switch (value) {
			case FILE_LINK_CONTROL: return FILE_LINK_CONTROL_LITERAL;
			case NO_FILE_LINK_CONTROL: return NO_FILE_LINK_CONTROL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LinkControlOption(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LinkControlOption
