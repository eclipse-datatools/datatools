/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLPassingType.java,v 1.4 2007/02/08 17:04:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>XML Passing Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Represents the possible values for the XML passing mechanism clause.  A value of NONE indicates that the passing mechanism clause should be omitted.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLPassingType()
 * @model
 * @generated
 */
public final class XMLPassingType extends AbstractEnumerator {
	/**
     * The '<em><b>BY REF</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BY REF</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BY_REF_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int BY_REF = 0;

	/**
     * The '<em><b>BY VALUE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BY VALUE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BY_VALUE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int BY_VALUE = 1;

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
     * The '<em><b>BY REF</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BY_REF
     * @generated
     * @ordered
     */
    public static final XMLPassingType BY_REF_LITERAL = new XMLPassingType(BY_REF, "BY_REF", "BY_REF");

	/**
     * The '<em><b>BY VALUE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BY_VALUE
     * @generated
     * @ordered
     */
    public static final XMLPassingType BY_VALUE_LITERAL = new XMLPassingType(BY_VALUE, "BY_VALUE", "BY_VALUE");

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
    public static final XMLPassingType NONE_LITERAL = new XMLPassingType(NONE, "NONE", "NONE");

	/**
     * An array of all the '<em><b>XML Passing Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final XMLPassingType[] VALUES_ARRAY =
		new XMLPassingType[] {
            BY_REF_LITERAL,
            BY_VALUE_LITERAL,
            NONE_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>XML Passing Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>XML Passing Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLPassingType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLPassingType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Passing Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static XMLPassingType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            XMLPassingType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>XML Passing Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static XMLPassingType get(int value) {
        switch (value) {
            case BY_REF: return BY_REF_LITERAL;
            case BY_VALUE: return BY_VALUE_LITERAL;
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
	private XMLPassingType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //XMLPassingType
