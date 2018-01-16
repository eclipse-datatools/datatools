/**
 * <copyright>
 * </copyright>
 *
 * $Id: OriginType.java,v 1.4 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Origin Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getOriginType()
 * @model
 * @generated
 */
public final class OriginType extends AbstractEnumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE = 0;

	/**
	 * The '<em><b>TEMPLATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMPLATE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TEMPLATE = 1;

	/**
	 * The '<em><b>SOURCE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SOURCE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SOURCE = 2;

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
	public static final OriginType NONE_LITERAL = new OriginType(NONE, "NONE", "NONE"); //$NON-NLS-1$

	/**
	 * The '<em><b>TEMPLATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TEMPLATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEMPLATE
	 * @generated
	 * @ordered
	 */
	public static final OriginType TEMPLATE_LITERAL = new OriginType(TEMPLATE, "TEMPLATE", "TEMPLATE"); //$NON-NLS-1$

	/**
	 * The '<em><b>SOURCE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SOURCE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SOURCE
	 * @generated
	 * @ordered
	 */
	public static final OriginType SOURCE_LITERAL = new OriginType(SOURCE, "SOURCE", "SOURCE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Origin Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final OriginType[] VALUES_ARRAY =
		new OriginType[] {
			NONE_LITERAL,
			TEMPLATE_LITERAL,
			SOURCE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Origin Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Origin Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OriginType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OriginType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Origin Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OriginType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OriginType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Origin Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OriginType get(int value) {
		switch (value) {
			case NONE: return NONE_LITERAL;
			case TEMPLATE: return TEMPLATE_LITERAL;
			case SOURCE: return SOURCE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private OriginType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //OriginType
