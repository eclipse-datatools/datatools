/**
 * <copyright>
 * </copyright>
 *
 * $Id: ParameterType.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Parameter Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getParameterType()
 * @model
 * @generated
 */
public final class ParameterType extends AbstractEnumerator {
    /**
	 * The '<em><b>VARIABLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>VARIABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VARIABLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE = 0;

    /**
	 * The '<em><b>RESULT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESULT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESULT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESULT = 1;

    /**
	 * The '<em><b>SQLSTATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SQLSTATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SQLSTATE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SQLSTATE = 2;

    /**
	 * The '<em><b>SQLCODE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SQLCODE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SQLCODE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SQLCODE = 3;

    /**
	 * The '<em><b>RETURN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RETURN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RETURN_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RETURN = 4;

    /**
	 * The '<em><b>VARIABLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VARIABLE
	 * @generated
	 * @ordered
	 */
	public static final ParameterType VARIABLE_LITERAL = new ParameterType(VARIABLE, "VARIABLE", "VARIABLE");

    /**
	 * The '<em><b>RESULT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESULT
	 * @generated
	 * @ordered
	 */
	public static final ParameterType RESULT_LITERAL = new ParameterType(RESULT, "RESULT", "RESULT");

    /**
	 * The '<em><b>SQLSTATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SQLSTATE
	 * @generated
	 * @ordered
	 */
	public static final ParameterType SQLSTATE_LITERAL = new ParameterType(SQLSTATE, "SQLSTATE", "SQLSTATE");

    /**
	 * The '<em><b>SQLCODE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SQLCODE
	 * @generated
	 * @ordered
	 */
	public static final ParameterType SQLCODE_LITERAL = new ParameterType(SQLCODE, "SQLCODE", "SQLCODE");

    /**
	 * The '<em><b>RETURN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RETURN
	 * @generated
	 * @ordered
	 */
	public static final ParameterType RETURN_LITERAL = new ParameterType(RETURN, "RETURN", "RETURN");

    /**
	 * An array of all the '<em><b>Parameter Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ParameterType[] VALUES_ARRAY =
        new ParameterType[] {
			VARIABLE_LITERAL,
			RESULT_LITERAL,
			SQLSTATE_LITERAL,
			SQLCODE_LITERAL,
			RETURN_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Parameter Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Parameter Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterType get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParameterType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Parameter Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterType getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParameterType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Parameter Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterType get(int value)
    {
		switch (value) {
			case VARIABLE: return VARIABLE_LITERAL;
			case RESULT: return RESULT_LITERAL;
			case SQLSTATE: return SQLSTATE_LITERAL;
			case SQLCODE: return SQLCODE_LITERAL;
			case RETURN: return RETURN_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ParameterType(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //ParameterType
