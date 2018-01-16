/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableType.java,v 1.3 2007/02/08 01:41:08 linsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Table Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getTableType()
 * @model
 * @generated
 */
public final class TableType extends AbstractEnumerator {
	/**
	 * The '<em><b>USERTABLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USERTABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USERTABLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USERTABLE = 1;

	/**
	 * The '<em><b>SHARABLETEMPTABLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SHARABLETEMPTABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHARABLETEMPTABLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SHARABLETEMPTABLE = 2;

	/**
	 * The '<em><b>NONESHARABLETEMPTABLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONESHARABLETEMPTABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONESHARABLETEMPTABLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONESHARABLETEMPTABLE = 3;

	/**
	 * The '<em><b>PROXYTABLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROXYTABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROXYTABLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROXYTABLE = 4;

	/**
	 * The '<em><b>SYSTEMTABLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYSTEMTABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYSTEMTABLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYSTEMTABLE = 5;

	/**
	 * The '<em><b>WEBSERVICEASTABLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WEBSERVICEASTABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEBSERVICEASTABLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WEBSERVICEASTABLE = 6;

	/**
	 * The '<em><b>USERTABLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USERTABLE
	 * @generated
	 * @ordered
	 */
	public static final TableType USERTABLE_LITERAL = new TableType(USERTABLE, "USERTABLE", "USERTABLE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SHARABLETEMPTABLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHARABLETEMPTABLE
	 * @generated
	 * @ordered
	 */
	public static final TableType SHARABLETEMPTABLE_LITERAL = new TableType(SHARABLETEMPTABLE, "SHARABLETEMPTABLE", "SHARABLETEMPTABLE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NONESHARABLETEMPTABLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONESHARABLETEMPTABLE
	 * @generated
	 * @ordered
	 */
	public static final TableType NONESHARABLETEMPTABLE_LITERAL = new TableType(NONESHARABLETEMPTABLE, "NONESHARABLETEMPTABLE", "NONESHARABLETEMPTABLE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>PROXYTABLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROXYTABLE
	 * @generated
	 * @ordered
	 */
	public static final TableType PROXYTABLE_LITERAL = new TableType(PROXYTABLE, "PROXYTABLE", "PROXYTABLE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SYSTEMTABLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYSTEMTABLE
	 * @generated
	 * @ordered
	 */
	public static final TableType SYSTEMTABLE_LITERAL = new TableType(SYSTEMTABLE, "SYSTEMTABLE", "SYSTEMTABLE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>WEBSERVICEASTABLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEBSERVICEASTABLE
	 * @generated
	 * @ordered
	 */
	public static final TableType WEBSERVICEASTABLE_LITERAL = new TableType(WEBSERVICEASTABLE, "WEBSERVICEASTABLE", "WEBSERVICEASTABLE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Table Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TableType[] VALUES_ARRAY =
		new TableType[] {
			USERTABLE_LITERAL,
			SHARABLETEMPTABLE_LITERAL,
			NONESHARABLETEMPTABLE_LITERAL,
			PROXYTABLE_LITERAL,
			SYSTEMTABLE_LITERAL,
			WEBSERVICEASTABLE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Table Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Table Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TableType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TableType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Table Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TableType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TableType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Table Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TableType get(int value) {
		switch (value) {
			case USERTABLE: return USERTABLE_LITERAL;
			case SHARABLETEMPTABLE: return SHARABLETEMPTABLE_LITERAL;
			case NONESHARABLETEMPTABLE: return NONESHARABLETEMPTABLE_LITERAL;
			case PROXYTABLE: return PROXYTABLE_LITERAL;
			case SYSTEMTABLE: return SYSTEMTABLE_LITERAL;
			case WEBSERVICEASTABLE: return WEBSERVICEASTABLE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TableType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //TableType
