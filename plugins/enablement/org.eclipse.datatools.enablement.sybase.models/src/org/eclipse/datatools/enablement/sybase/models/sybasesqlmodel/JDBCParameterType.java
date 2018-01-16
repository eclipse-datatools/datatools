/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>JDBC Parameter Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * This class maps to JDBC DatabaseMetadata.procedureColumnXXX types.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getJDBCParameterType()
 * @model
 * @generated
 */
public final class JDBCParameterType extends AbstractEnumerator {
	/**
	 * The '<em><b>Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_LITERAL
	 * @model name="Unknown"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN = 0;

	/**
	 * The '<em><b>In</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>In</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IN_LITERAL
	 * @model name="In"
	 * @generated
	 * @ordered
	 */
	public static final int IN = 1;

	/**
	 * The '<em><b>In Out</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>In Out</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IN_OUT_LITERAL
	 * @model name="InOut"
	 * @generated
	 * @ordered
	 */
	public static final int IN_OUT = 2;

	/**
	 * The '<em><b>Result</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Result</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESULT_LITERAL
	 * @model name="Result"
	 * @generated
	 * @ordered
	 */
	public static final int RESULT = 3;

	/**
	 * The '<em><b>Out</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Out</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OUT_LITERAL
	 * @model name="Out"
	 * @generated
	 * @ordered
	 */
	public static final int OUT = 4;

	/**
	 * The '<em><b>Return</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Return</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RETURN_LITERAL
	 * @model name="Return"
	 * @generated
	 * @ordered
	 */
	public static final int RETURN = 5;

	/**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @generated
	 * @ordered
	 */
	public static final JDBCParameterType UNKNOWN_LITERAL = new JDBCParameterType(UNKNOWN, "Unknown", "Unknown");

	/**
	 * The '<em><b>In</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IN
	 * @generated
	 * @ordered
	 */
	public static final JDBCParameterType IN_LITERAL = new JDBCParameterType(IN, "In", "In");

	/**
	 * The '<em><b>In Out</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IN_OUT
	 * @generated
	 * @ordered
	 */
	public static final JDBCParameterType IN_OUT_LITERAL = new JDBCParameterType(IN_OUT, "InOut", "InOut");

	/**
	 * The '<em><b>Result</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESULT
	 * @generated
	 * @ordered
	 */
	public static final JDBCParameterType RESULT_LITERAL = new JDBCParameterType(RESULT, "Result", "Result");

	/**
	 * The '<em><b>Out</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OUT
	 * @generated
	 * @ordered
	 */
	public static final JDBCParameterType OUT_LITERAL = new JDBCParameterType(OUT, "Out", "Out");

	/**
	 * The '<em><b>Return</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RETURN
	 * @generated
	 * @ordered
	 */
	public static final JDBCParameterType RETURN_LITERAL = new JDBCParameterType(RETURN, "Return", "Return");

	/**
	 * An array of all the '<em><b>JDBC Parameter Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final JDBCParameterType[] VALUES_ARRAY =
		new JDBCParameterType[] {
			UNKNOWN_LITERAL,
			IN_LITERAL,
			IN_OUT_LITERAL,
			RESULT_LITERAL,
			OUT_LITERAL,
			RETURN_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>JDBC Parameter Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>JDBC Parameter Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JDBCParameterType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JDBCParameterType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>JDBC Parameter Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JDBCParameterType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JDBCParameterType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>JDBC Parameter Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JDBCParameterType get(int value) {
		switch (value) {
			case UNKNOWN: return UNKNOWN_LITERAL;
			case IN: return IN_LITERAL;
			case IN_OUT: return IN_OUT_LITERAL;
			case RESULT: return RESULT_LITERAL;
			case OUT: return OUT_LITERAL;
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
	private JDBCParameterType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //JDBCParameterType
