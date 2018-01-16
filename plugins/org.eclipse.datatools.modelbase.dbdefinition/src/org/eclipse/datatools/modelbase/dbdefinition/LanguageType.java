/**
 * <copyright>
 * </copyright>
 *
 * $Id: LanguageType.java,v 1.3 2006/10/11 20:34:55 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Language Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getLanguageType()
 * @model
 * @generated
 */
public final class LanguageType extends AbstractEnumerator {
	/**
	 * The '<em><b>SQL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SQL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SQL = 0;

	/**
	 * The '<em><b>JAVA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int JAVA = 1;

	/**
	 * The '<em><b>C</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #C_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int C = 2;

	/**
	 * The '<em><b>OLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OLE = 3;

	/**
	 * The '<em><b>ASSEMBLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASSEMBLY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ASSEMBLY = 4;

	/**
	 * The '<em><b>COBOL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COBOL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COBOL = 5;

	/**
	 * The '<em><b>PLI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLI_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PLI = 6;

	/**
	 * The '<em><b>CPLUSPLUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CPLUSPLUS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CPLUSPLUS = 7;

	/**
	 * The '<em><b>CL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CL = 8;

	/**
	 * The '<em><b>COBOLLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COBOLLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COBOLLE = 9;

	/**
	 * The '<em><b>FORTRAN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORTRAN_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FORTRAN = 10;

	/**
	 * The '<em><b>REXX</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REXX_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REXX = 11;

	/**
	 * The '<em><b>RPG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RPG_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RPG = 12;

	/**
	 * The '<em><b>RPGLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RPGLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RPGLE = 13;

	/**
	 * The '<em><b>PLSQL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PLSQL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PLSQL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PLSQL = 14;

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
	public static final LanguageType SQL_LITERAL = new LanguageType(SQL, "SQL", "SQL"); //$NON-NLS-1$

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
	public static final LanguageType JAVA_LITERAL = new LanguageType(JAVA, "JAVA", "JAVA"); //$NON-NLS-1$

	/**
	 * The '<em><b>C</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>C</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #C
	 * @generated
	 * @ordered
	 */
	public static final LanguageType C_LITERAL = new LanguageType(C, "C", "C"); //$NON-NLS-1$

	/**
	 * The '<em><b>OLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OLE
	 * @generated
	 * @ordered
	 */
	public static final LanguageType OLE_LITERAL = new LanguageType(OLE, "OLE", "OLE"); //$NON-NLS-1$

	/**
	 * The '<em><b>ASSEMBLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ASSEMBLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ASSEMBLY
	 * @generated
	 * @ordered
	 */
	public static final LanguageType ASSEMBLY_LITERAL = new LanguageType(ASSEMBLY, "ASSEMBLY", "ASSEMBLY"); //$NON-NLS-1$

	/**
	 * The '<em><b>COBOL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COBOL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COBOL
	 * @generated
	 * @ordered
	 */
	public static final LanguageType COBOL_LITERAL = new LanguageType(COBOL, "COBOL", "COBOL"); //$NON-NLS-1$

	/**
	 * The '<em><b>PLI</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PLI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PLI
	 * @generated
	 * @ordered
	 */
	public static final LanguageType PLI_LITERAL = new LanguageType(PLI, "PLI", "PLI"); //$NON-NLS-1$

	/**
	 * The '<em><b>CPLUSPLUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CPLUSPLUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CPLUSPLUS
	 * @generated
	 * @ordered
	 */
	public static final LanguageType CPLUSPLUS_LITERAL = new LanguageType(CPLUSPLUS, "CPLUSPLUS", "CPLUSPLUS"); //$NON-NLS-1$

	/**
	 * The '<em><b>CL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CL
	 * @generated
	 * @ordered
	 */
	public static final LanguageType CL_LITERAL = new LanguageType(CL, "CL", "CL"); //$NON-NLS-1$

	/**
	 * The '<em><b>COBOLLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COBOLLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COBOLLE
	 * @generated
	 * @ordered
	 */
	public static final LanguageType COBOLLE_LITERAL = new LanguageType(COBOLLE, "COBOLLE", "COBOLLE"); //$NON-NLS-1$

	/**
	 * The '<em><b>FORTRAN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FORTRAN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FORTRAN
	 * @generated
	 * @ordered
	 */
	public static final LanguageType FORTRAN_LITERAL = new LanguageType(FORTRAN, "FORTRAN", "FORTRAN"); //$NON-NLS-1$

	/**
	 * The '<em><b>REXX</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REXX</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REXX
	 * @generated
	 * @ordered
	 */
	public static final LanguageType REXX_LITERAL = new LanguageType(REXX, "REXX", "REXX"); //$NON-NLS-1$

	/**
	 * The '<em><b>RPG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RPG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RPG
	 * @generated
	 * @ordered
	 */
	public static final LanguageType RPG_LITERAL = new LanguageType(RPG, "RPG", "RPG"); //$NON-NLS-1$

	/**
	 * The '<em><b>RPGLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RPGLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RPGLE
	 * @generated
	 * @ordered
	 */
	public static final LanguageType RPGLE_LITERAL = new LanguageType(RPGLE, "RPGLE", "RPGLE"); //$NON-NLS-1$

	/**
	 * The '<em><b>PLSQL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLSQL
	 * @generated
	 * @ordered
	 */
	public static final LanguageType PLSQL_LITERAL = new LanguageType(PLSQL, "PLSQL", "PLSQL"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Language Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LanguageType[] VALUES_ARRAY =
		new LanguageType[] {
			SQL_LITERAL,
			JAVA_LITERAL,
			C_LITERAL,
			OLE_LITERAL,
			ASSEMBLY_LITERAL,
			COBOL_LITERAL,
			PLI_LITERAL,
			CPLUSPLUS_LITERAL,
			CL_LITERAL,
			COBOLLE_LITERAL,
			FORTRAN_LITERAL,
			REXX_LITERAL,
			RPG_LITERAL,
			RPGLE_LITERAL,
			PLSQL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Language Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Language Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LanguageType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LanguageType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Language Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LanguageType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LanguageType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Language Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LanguageType get(int value) {
		switch (value) {
			case SQL: return SQL_LITERAL;
			case JAVA: return JAVA_LITERAL;
			case C: return C_LITERAL;
			case OLE: return OLE_LITERAL;
			case ASSEMBLY: return ASSEMBLY_LITERAL;
			case COBOL: return COBOL_LITERAL;
			case PLI: return PLI_LITERAL;
			case CPLUSPLUS: return CPLUSPLUS_LITERAL;
			case CL: return CL_LITERAL;
			case COBOLLE: return COBOLLE_LITERAL;
			case FORTRAN: return FORTRAN_LITERAL;
			case REXX: return REXX_LITERAL;
			case RPG: return RPG_LITERAL;
			case RPGLE: return RPGLE_LITERAL;
			case PLSQL: return PLSQL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LanguageType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LanguageType
