/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLWhitespaceHandlingType.java,v 1.4 2007/02/08 17:04:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>XML Whitespace Handling Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLWhitespaceHandlingType()
 * @model
 * @generated
 */
public final class XMLWhitespaceHandlingType extends AbstractEnumerator {
	/**
     * The '<em><b>PRESERE WHITESPACE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PRESERE_WHITESPACE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int PRESERE_WHITESPACE = 0;

	/**
     * The '<em><b>STRIP WHITESPACE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRIP_WHITESPACE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int STRIP_WHITESPACE = 1;

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
     * The '<em><b>PRESERE WHITESPACE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PRESERE WHITESPACE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PRESERE_WHITESPACE
     * @generated
     * @ordered
     */
    public static final XMLWhitespaceHandlingType PRESERE_WHITESPACE_LITERAL = new XMLWhitespaceHandlingType(PRESERE_WHITESPACE, "PRESERE_WHITESPACE", "PRESERE_WHITESPACE");

	/**
     * The '<em><b>STRIP WHITESPACE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>STRIP WHITESPACE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #STRIP_WHITESPACE
     * @generated
     * @ordered
     */
    public static final XMLWhitespaceHandlingType STRIP_WHITESPACE_LITERAL = new XMLWhitespaceHandlingType(STRIP_WHITESPACE, "STRIP_WHITESPACE", "STRIP_WHITESPACE");

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
    public static final XMLWhitespaceHandlingType NONE_LITERAL = new XMLWhitespaceHandlingType(NONE, "NONE", "NONE");

	/**
     * An array of all the '<em><b>XML Whitespace Handling Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final XMLWhitespaceHandlingType[] VALUES_ARRAY =
		new XMLWhitespaceHandlingType[] {
            PRESERE_WHITESPACE_LITERAL,
            STRIP_WHITESPACE_LITERAL,
            NONE_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>XML Whitespace Handling Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>XML Whitespace Handling Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLWhitespaceHandlingType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLWhitespaceHandlingType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Whitespace Handling Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static XMLWhitespaceHandlingType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLWhitespaceHandlingType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Whitespace Handling Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLWhitespaceHandlingType get(int value) {
        switch (value) {
            case PRESERE_WHITESPACE: return PRESERE_WHITESPACE_LITERAL;
            case STRIP_WHITESPACE: return STRIP_WHITESPACE_LITERAL;
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
	private XMLWhitespaceHandlingType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //XMLWhitespaceHandlingType
