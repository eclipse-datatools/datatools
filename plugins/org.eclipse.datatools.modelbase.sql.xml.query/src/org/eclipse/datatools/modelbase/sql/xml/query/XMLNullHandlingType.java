/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNullHandlingType.java,v 1.4 2007/02/08 17:04:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>XML Null Handling Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNullHandlingType()
 * @model
 * @generated
 */
public final class XMLNullHandlingType extends AbstractEnumerator {
	/**
     * The '<em><b>ABSENT ON NULL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ABSENT_ON_NULL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int ABSENT_ON_NULL = 0;

	/**
     * The '<em><b>EMPTY ON NULL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EMPTY_ON_NULL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int EMPTY_ON_NULL = 1;

	/**
     * The '<em><b>NIL ON NO CONTENT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NIL_ON_NO_CONTENT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NIL_ON_NO_CONTENT = 2;

	/**
     * The '<em><b>NIL ON NULL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NIL_ON_NULL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NIL_ON_NULL = 3;

	/**
     * The '<em><b>NULL ON NULL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NULL_ON_NULL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NULL_ON_NULL = 4;

	/**
     * The '<em><b>NONE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NONE = 5;

	/**
     * The '<em><b>ABSENT ON NULL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ABSENT ON NULL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ABSENT_ON_NULL
     * @generated
     * @ordered
     */
    public static final XMLNullHandlingType ABSENT_ON_NULL_LITERAL = new XMLNullHandlingType(ABSENT_ON_NULL, "ABSENT_ON_NULL", "ABSENT_ON_NULL");

	/**
     * The '<em><b>EMPTY ON NULL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EMPTY ON NULL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EMPTY_ON_NULL
     * @generated
     * @ordered
     */
    public static final XMLNullHandlingType EMPTY_ON_NULL_LITERAL = new XMLNullHandlingType(EMPTY_ON_NULL, "EMPTY_ON_NULL", "EMPTY_ON_NULL");

	/**
     * The '<em><b>NIL ON NO CONTENT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NIL ON NO CONTENT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NIL_ON_NO_CONTENT
     * @generated
     * @ordered
     */
    public static final XMLNullHandlingType NIL_ON_NO_CONTENT_LITERAL = new XMLNullHandlingType(NIL_ON_NO_CONTENT, "NIL_ON_NO_CONTENT", "NIL_ON_NO_CONTENT");

	/**
     * The '<em><b>NIL ON NULL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NIL ON NULL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NIL_ON_NULL
     * @generated
     * @ordered
     */
    public static final XMLNullHandlingType NIL_ON_NULL_LITERAL = new XMLNullHandlingType(NIL_ON_NULL, "NIL_ON_NULL", "NIL_ON_NULL");

	/**
     * The '<em><b>NULL ON NULL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NULL ON NULL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NULL_ON_NULL
     * @generated
     * @ordered
     */
    public static final XMLNullHandlingType NULL_ON_NULL_LITERAL = new XMLNullHandlingType(NULL_ON_NULL, "NULL_ON_NULL", "NULL_ON_NULL");

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
    public static final XMLNullHandlingType NONE_LITERAL = new XMLNullHandlingType(NONE, "NONE", "NONE");

	/**
     * An array of all the '<em><b>XML Null Handling Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final XMLNullHandlingType[] VALUES_ARRAY =
		new XMLNullHandlingType[] {
            ABSENT_ON_NULL_LITERAL,
            EMPTY_ON_NULL_LITERAL,
            NIL_ON_NO_CONTENT_LITERAL,
            NIL_ON_NULL_LITERAL,
            NULL_ON_NULL_LITERAL,
            NONE_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>XML Null Handling Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>XML Null Handling Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLNullHandlingType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLNullHandlingType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Null Handling Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static XMLNullHandlingType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLNullHandlingType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Null Handling Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLNullHandlingType get(int value) {
        switch (value) {
            case ABSENT_ON_NULL: return ABSENT_ON_NULL_LITERAL;
            case EMPTY_ON_NULL: return EMPTY_ON_NULL_LITERAL;
            case NIL_ON_NO_CONTENT: return NIL_ON_NO_CONTENT_LITERAL;
            case NIL_ON_NULL: return NIL_ON_NULL_LITERAL;
            case NULL_ON_NULL: return NULL_ON_NULL_LITERAL;
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
	private XMLNullHandlingType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //XMLNullHandlingType
