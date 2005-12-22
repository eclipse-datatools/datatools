/**
 * <copyright>
 * </copyright>
 *
 * $Id: CheckType.java,v 1.1 2005/08/02 22:56:30 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.sql.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Check Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 4.14 Tables
 * 
 * A table is a collection of rows having one or more columns. [...]
 * 
 * A view descriptor describes a view. In addition to the components of a derived table descriptor, a view descriptor includes:
 *  - The name of the view.
 *  - An indication of whether the view has the CHECK OPTION ; if so, whether it is to be applied as CASCADED or LOCAL.
 *  - The original <query expression> of the view.
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getCheckType()
 * @model
 * @generated
 */
public final class CheckType extends AbstractEnumerator {
	/**
	 * The '<em><b>CASCADED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CASCADED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CASCADED = 0;

	/**
	 * The '<em><b>LOCAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOCAL = 1;

	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE = 2;

	/**
	 * The '<em><b>CASCADED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CASCADED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CASCADED
	 * @generated
	 * @ordered
	 */
	public static final CheckType CASCADED_LITERAL = new CheckType(CASCADED, "CASCADED"); //$NON-NLS-1$

	/**
	 * The '<em><b>LOCAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL
	 * @generated
	 * @ordered
	 */
	public static final CheckType LOCAL_LITERAL = new CheckType(LOCAL, "LOCAL"); //$NON-NLS-1$

	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
	public static final CheckType NONE_LITERAL = new CheckType(NONE, "NONE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Check Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CheckType[] VALUES_ARRAY =
		new CheckType[] {
			CASCADED_LITERAL,
			LOCAL_LITERAL,
			NONE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Check Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Check Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CheckType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CheckType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Check Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CheckType get(int value) {
		switch (value) {
			case CASCADED: return CASCADED_LITERAL;
			case LOCAL: return LOCAL_LITERAL;
			case NONE: return NONE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CheckType(int value, String name) {
		super(value, name);
	}

} //CheckType
