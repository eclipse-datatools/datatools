/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLContentType.java,v 1.4 2007/02/08 17:04:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>XML Content Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLContentType()
 * @model
 * @generated
 */
public final class XMLContentType extends AbstractEnumerator {
	/**
     * The '<em><b>CONTENT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CONTENT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int CONTENT = 0;

	/**
     * The '<em><b>DOCUMENT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOCUMENT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int DOCUMENT = 1;

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
     * The '<em><b>CONTENT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CONTENT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CONTENT
     * @generated
     * @ordered
     */
    public static final XMLContentType CONTENT_LITERAL = new XMLContentType(CONTENT, "CONTENT", "CONTENT");

	/**
     * The '<em><b>DOCUMENT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DOCUMENT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DOCUMENT
     * @generated
     * @ordered
     */
    public static final XMLContentType DOCUMENT_LITERAL = new XMLContentType(DOCUMENT, "DOCUMENT", "DOCUMENT");

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
    public static final XMLContentType NONE_LITERAL = new XMLContentType(NONE, "NONE", "NONE");

	/**
     * An array of all the '<em><b>XML Content Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final XMLContentType[] VALUES_ARRAY =
		new XMLContentType[] {
            CONTENT_LITERAL,
            DOCUMENT_LITERAL,
            NONE_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>XML Content Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>XML Content Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLContentType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLContentType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Content Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static XMLContentType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLContentType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Content Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLContentType get(int value) {
        switch (value) {
            case CONTENT: return CONTENT_LITERAL;
            case DOCUMENT: return DOCUMENT_LITERAL;
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
	private XMLContentType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //XMLContentType
