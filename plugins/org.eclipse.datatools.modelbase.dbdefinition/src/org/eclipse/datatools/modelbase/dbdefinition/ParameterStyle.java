/**
 * <copyright>
 * </copyright>
 *
 * $Id: ParameterStyle.java,v 1.3 2006/10/11 20:34:54 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Parameter Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getParameterStyle()
 * @model
 * @generated
 */
public final class ParameterStyle extends AbstractEnumerator {
	/**
	 * The '<em><b>DB2SQL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DB2SQL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DB2SQL = 0;

	/**
	 * The '<em><b>GENERAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GENERAL = 1;

	/**
	 * The '<em><b>GENERAL WITH NULLS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERAL_WITH_NULLS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GENERAL_WITH_NULLS = 2;

	/**
	 * The '<em><b>DB2GENRL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DB2GENRL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DB2GENRL = 3;

	/**
	 * The '<em><b>DB2DARI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DB2DARI_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DB2DARI = 4;

	/**
	 * The '<em><b>JAVA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int JAVA = 5;

	/**
	 * The '<em><b>SQL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SQL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SQL = 6;

	/**
	 * The '<em><b>DB2SQL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DB2SQL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DB2SQL
	 * @generated
	 * @ordered
	 */
	public static final ParameterStyle DB2SQL_LITERAL = new ParameterStyle(DB2SQL, "DB2SQL", "DB2SQL"); //$NON-NLS-1$

	/**
	 * The '<em><b>GENERAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GENERAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERAL
	 * @generated
	 * @ordered
	 */
	public static final ParameterStyle GENERAL_LITERAL = new ParameterStyle(GENERAL, "GENERAL", "GENERAL"); //$NON-NLS-1$

	/**
	 * The '<em><b>GENERAL WITH NULLS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GENERAL WITH NULLS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERAL_WITH_NULLS
	 * @generated
	 * @ordered
	 */
	public static final ParameterStyle GENERAL_WITH_NULLS_LITERAL = new ParameterStyle(GENERAL_WITH_NULLS, "GENERAL_WITH_NULLS", "GENERAL_WITH_NULLS"); //$NON-NLS-1$

	/**
	 * The '<em><b>DB2GENRL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DB2GENRL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DB2GENRL
	 * @generated
	 * @ordered
	 */
	public static final ParameterStyle DB2GENRL_LITERAL = new ParameterStyle(DB2GENRL, "DB2GENRL", "DB2GENRL"); //$NON-NLS-1$

	/**
	 * The '<em><b>DB2DARI</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DB2DARI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DB2DARI
	 * @generated
	 * @ordered
	 */
	public static final ParameterStyle DB2DARI_LITERAL = new ParameterStyle(DB2DARI, "DB2DARI", "DB2DARI"); //$NON-NLS-1$

	/**
	 * The '<em><b>JAVA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>JAVA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA
	 * @generated
	 * @ordered
	 */
	public static final ParameterStyle JAVA_LITERAL = new ParameterStyle(JAVA, "JAVA", "JAVA"); //$NON-NLS-1$

	/**
	 * The '<em><b>SQL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SQL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SQL
	 * @generated
	 * @ordered
	 */
	public static final ParameterStyle SQL_LITERAL = new ParameterStyle(SQL, "SQL", "SQL"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Parameter Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ParameterStyle[] VALUES_ARRAY =
		new ParameterStyle[] {
			DB2SQL_LITERAL,
			GENERAL_LITERAL,
			GENERAL_WITH_NULLS_LITERAL,
			DB2GENRL_LITERAL,
			DB2DARI_LITERAL,
			JAVA_LITERAL,
			SQL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Parameter Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Parameter Style</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterStyle get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParameterStyle result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Parameter Style</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterStyle getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParameterStyle result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Parameter Style</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterStyle get(int value) {
		switch (value) {
			case DB2SQL: return DB2SQL_LITERAL;
			case GENERAL: return GENERAL_LITERAL;
			case GENERAL_WITH_NULLS: return GENERAL_WITH_NULLS_LITERAL;
			case DB2GENRL: return DB2GENRL_LITERAL;
			case DB2DARI: return DB2DARI_LITERAL;
			case JAVA: return JAVA_LITERAL;
			case SQL: return SQL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ParameterStyle(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ParameterStyle
