/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLReturningType.java,v 1.4 2007/02/08 17:04:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>XML Returning Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLReturningType()
 * @model
 * @generated
 */
public final class XMLReturningType extends AbstractEnumerator {
	/**
     * The '<em><b>RETURNING CONTENT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RETURNING_CONTENT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int RETURNING_CONTENT = 0;

	/**
     * The '<em><b>RETURNING SEQUENCE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RETURNING_SEQUENCE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int RETURNING_SEQUENCE = 1;

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
     * The '<em><b>RETURNING CONTENT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>RETURNING CONTENT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RETURNING_CONTENT
     * @generated
     * @ordered
     */
    public static final XMLReturningType RETURNING_CONTENT_LITERAL = new XMLReturningType(RETURNING_CONTENT, "RETURNING_CONTENT", "RETURNING_CONTENT");

	/**
     * The '<em><b>RETURNING SEQUENCE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>RETURNING SEQUENCE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RETURNING_SEQUENCE
     * @generated
     * @ordered
     */
    public static final XMLReturningType RETURNING_SEQUENCE_LITERAL = new XMLReturningType(RETURNING_SEQUENCE, "RETURNING_SEQUENCE", "RETURNING_SEQUENCE");

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
    public static final XMLReturningType NONE_LITERAL = new XMLReturningType(NONE, "NONE", "NONE");

	/**
     * An array of all the '<em><b>XML Returning Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final XMLReturningType[] VALUES_ARRAY =
		new XMLReturningType[] {
            RETURNING_CONTENT_LITERAL,
            RETURNING_SEQUENCE_LITERAL,
            NONE_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>XML Returning Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>XML Returning Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLReturningType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLReturningType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Returning Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static XMLReturningType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLReturningType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Returning Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLReturningType get(int value) {
        switch (value) {
            case RETURNING_CONTENT: return RETURNING_CONTENT_LITERAL;
            case RETURNING_SEQUENCE: return RETURNING_SEQUENCE_LITERAL;
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
	private XMLReturningType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //XMLReturningType
