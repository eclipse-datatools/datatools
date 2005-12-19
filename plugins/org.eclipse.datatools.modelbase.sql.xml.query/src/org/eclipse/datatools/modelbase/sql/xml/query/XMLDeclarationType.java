/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLDeclarationType.java,v 1.1 2005/12/16 13:16:52 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>XML Declaration Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage#getXMLDeclarationType()
 * @model
 * @generated
 */
public final class XMLDeclarationType extends AbstractEnumerator {
	/**
	 * The '<em><b>EXCLUDING XMLDECLARATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #EXCLUDING_XMLDECLARATION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int EXCLUDING_XMLDECLARATION = 0;

	/**
	 * The '<em><b>INCLUDING XMLDECLARATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #INCLUDING_XMLDECLARATION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int INCLUDING_XMLDECLARATION = 1;

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
	 * The '<em><b>EXCLUDING XMLDECLARATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EXCLUDING XMLDECLARATION</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #EXCLUDING_XMLDECLARATION
	 * @generated
	 * @ordered
	 */
    public static final XMLDeclarationType EXCLUDING_XMLDECLARATION_LITERAL = new XMLDeclarationType(EXCLUDING_XMLDECLARATION, "EXCLUDING_XMLDECLARATION");

	/**
	 * The '<em><b>INCLUDING XMLDECLARATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>INCLUDING XMLDECLARATION</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #INCLUDING_XMLDECLARATION
	 * @generated
	 * @ordered
	 */
    public static final XMLDeclarationType INCLUDING_XMLDECLARATION_LITERAL = new XMLDeclarationType(INCLUDING_XMLDECLARATION, "INCLUDING_XMLDECLARATION");

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
    public static final XMLDeclarationType NONE_LITERAL = new XMLDeclarationType(NONE, "NONE");

	/**
	 * An array of all the '<em><b>XML Declaration Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final XMLDeclarationType[] VALUES_ARRAY =
		new XMLDeclarationType[] {
			EXCLUDING_XMLDECLARATION_LITERAL,
			INCLUDING_XMLDECLARATION_LITERAL,
			NONE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>XML Declaration Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>XML Declaration Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static XMLDeclarationType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			XMLDeclarationType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>XML Declaration Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static XMLDeclarationType get(int value) {
		switch (value) {
			case EXCLUDING_XMLDECLARATION: return EXCLUDING_XMLDECLARATION_LITERAL;
			case INCLUDING_XMLDECLARATION: return INCLUDING_XMLDECLARATION_LITERAL;
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
	private XMLDeclarationType(int value, String name) {
		super(value, name);
	}

} //XMLDeclarationType
