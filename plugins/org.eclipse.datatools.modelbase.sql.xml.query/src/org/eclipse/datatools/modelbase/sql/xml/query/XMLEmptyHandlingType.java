/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLEmptyHandlingType.java,v 1.4 2007/02/08 17:04:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>XML Empty Handling Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLEmptyHandlingType()
 * @model
 * @generated
 */
public final class XMLEmptyHandlingType extends AbstractEnumerator {
	/**
     * The '<em><b>EMPTY ON EMPTY</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EMPTY_ON_EMPTY_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int EMPTY_ON_EMPTY = 0;

	/**
     * The '<em><b>NULL ON EMPTY</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NULL_ON_EMPTY_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NULL_ON_EMPTY = 1;

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
     * The '<em><b>EMPTY ON EMPTY</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EMPTY ON EMPTY</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EMPTY_ON_EMPTY
     * @generated
     * @ordered
     */
    public static final XMLEmptyHandlingType EMPTY_ON_EMPTY_LITERAL = new XMLEmptyHandlingType(EMPTY_ON_EMPTY, "EMPTY_ON_EMPTY", "EMPTY_ON_EMPTY");

	/**
     * The '<em><b>NULL ON EMPTY</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NULL ON EMPTY</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NULL_ON_EMPTY
     * @generated
     * @ordered
     */
    public static final XMLEmptyHandlingType NULL_ON_EMPTY_LITERAL = new XMLEmptyHandlingType(NULL_ON_EMPTY, "NULL_ON_EMPTY", "NULL_ON_EMPTY");

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
    public static final XMLEmptyHandlingType NONE_LITERAL = new XMLEmptyHandlingType(NONE, "NONE", "NONE");

	/**
     * An array of all the '<em><b>XML Empty Handling Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final XMLEmptyHandlingType[] VALUES_ARRAY =
		new XMLEmptyHandlingType[] {
            EMPTY_ON_EMPTY_LITERAL,
            NULL_ON_EMPTY_LITERAL,
            NONE_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>XML Empty Handling Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>XML Empty Handling Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLEmptyHandlingType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLEmptyHandlingType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Empty Handling Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static XMLEmptyHandlingType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLEmptyHandlingType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Empty Handling Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLEmptyHandlingType get(int value) {
        switch (value) {
            case EMPTY_ON_EMPTY: return EMPTY_ON_EMPTY_LITERAL;
            case NULL_ON_EMPTY: return NULL_ON_EMPTY_LITERAL;
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
	private XMLEmptyHandlingType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //XMLEmptyHandlingType
