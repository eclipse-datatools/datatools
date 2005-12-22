/**
 * <copyright>
 * </copyright>
 *
 * $Id: DataAccess.java,v 1.1 2005/08/02 22:56:30 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.sql.routines;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Data Access</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 5WD-02-Foundation-2002-12
 * 4.27 SQL-invoked routines
 * 
 * An external routine either does not possibly contain SQL or possibly contains SQL. Only an external routine that possibly contains SQL may execute SQL-statements during its invocation.
 * 
 * An SQL-invoked routine may or may not possibly read SQL-data. Only an SQL-invoked routine that possibly reads SQL-data may read SQL-data during its invocation.
 * 
 * An SQL-invoked routine may or may not possibly modify SQL-data. Only an SQL-invoked routine that possibly modifies SQL-data may modify SQL-data during its invocation.
 * 
 * 11.50 <SQL-invoked routine>
 * <SQL-data access indication> ::= NO SQL | CONTAINS SQL | READS SQL DATA | MODIFIES SQL DATA
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getDataAccess()
 * @model
 * @generated
 */
public final class DataAccess extends AbstractEnumerator {
	/**
	 * The '<em><b>NO SQL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_SQL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_SQL = 0;

	/**
	 * The '<em><b>CONTAINS SQL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTAINS_SQL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONTAINS_SQL = 1;

	/**
	 * The '<em><b>READS SQL DATA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #READS_SQL_DATA_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int READS_SQL_DATA = 2;

	/**
	 * The '<em><b>MODIFIES SQL DATA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MODIFIES_SQL_DATA_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MODIFIES_SQL_DATA = 3;

	/**
	 * The '<em><b>NO SQL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO SQL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_SQL
	 * @generated
	 * @ordered
	 */
	public static final DataAccess NO_SQL_LITERAL = new DataAccess(NO_SQL, "NO_SQL"); //$NON-NLS-1$

	/**
	 * The '<em><b>CONTAINS SQL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONTAINS SQL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTAINS_SQL
	 * @generated
	 * @ordered
	 */
	public static final DataAccess CONTAINS_SQL_LITERAL = new DataAccess(CONTAINS_SQL, "CONTAINS_SQL"); //$NON-NLS-1$

	/**
	 * The '<em><b>READS SQL DATA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READS SQL DATA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READS_SQL_DATA
	 * @generated
	 * @ordered
	 */
	public static final DataAccess READS_SQL_DATA_LITERAL = new DataAccess(READS_SQL_DATA, "READS_SQL_DATA"); //$NON-NLS-1$

	/**
	 * The '<em><b>MODIFIES SQL DATA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MODIFIES SQL DATA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MODIFIES_SQL_DATA
	 * @generated
	 * @ordered
	 */
	public static final DataAccess MODIFIES_SQL_DATA_LITERAL = new DataAccess(MODIFIES_SQL_DATA, "MODIFIES_SQL_DATA"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Data Access</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DataAccess[] VALUES_ARRAY =
		new DataAccess[] {
			NO_SQL_LITERAL,
			CONTAINS_SQL_LITERAL,
			READS_SQL_DATA_LITERAL,
			MODIFIES_SQL_DATA_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Data Access</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Data Access</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DataAccess get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DataAccess result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Data Access</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DataAccess get(int value) {
		switch (value) {
			case NO_SQL: return NO_SQL_LITERAL;
			case CONTAINS_SQL: return CONTAINS_SQL_LITERAL;
			case READS_SQL_DATA: return READS_SQL_DATA_LITERAL;
			case MODIFIES_SQL_DATA: return MODIFIES_SQL_DATA_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DataAccess(int value, String name) {
		super(value, name);
	}

} //DataAccess
