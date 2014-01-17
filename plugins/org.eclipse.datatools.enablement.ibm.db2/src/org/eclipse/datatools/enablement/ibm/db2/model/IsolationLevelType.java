/**
 * <copyright>
 * </copyright>
 *
 * $Id: IsolationLevelType.java,v 1.9 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Isolation Level Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Isolation level
 * 
 * The isolation level used during the execution of SQL statement determines the degree to which the application process is isolated from concurrently executing application processes. Thus, when application process P executes an SQL statement, the isolation level determines:
 *  - The degree to which rows retrieved by P are available to other concurrently executing application processes.
 *  - The degree to which database changes made by concurrently executing application processes can affect P.
 * 
 * The isolation level can be explicitly specified on a DELETE, INSERT, SELECT INTO, UPDATE, or select-statement. If the isolation level is not explicitly specified, the isolation level used when the SQL statement is executed  is the default isolation level.
 * 
 * Each product provides a product-specific means of explicitly specifying a default isolation level:
 *  - For static SQL statements, the default isolation level is the isolation level specified when the containing package, procedure, function, or trigger was created.
 *  - For dynamic SQL statements, the default isolation level is isolation level specified for the application process.
 * 
 * Products support these isolation levels by automatically locking the appropriate data. Depending on the type of lock, this limits or prevents access to the data by concurrent application processes. Each database manager supports at least two types of locks:
 *  - Share: Limits concurrent application processes to read-only operations on the data.
 *  - Exclusive Prevents concurrent application processes from accessing the data in any way except for application processes with an isolation level of uncommitted read, which can read but not modify the data. (See
 * " Uncommitted read" on page 18.)
 * 
 * The isolation levels are:
 * 
 * Repeatable read
 * The Repeatable Read (RR) isolation level ensures that:
 *  - Any row read during a unit of work is not changed by other application processes until the unit of work is complete.
 *  - Any row changed by another application process cannot be read until it is committed by that application process.
 * In addition to any exclusive locks, an application process running at level RR acquires at least share locks on all the rows it reads. Furthermore, the locking is performed so that the application process is completely isolated from the effects of concurrent application processes. In the SQL 1999 Core standard, Repeatable Read is called Serializable.
 * 
 * Read stability
 * Like level RR, the Read Stability (RS) isolation level ensures that:
 *  - Any row read during a unit of work is not changed by other application processes until the unit of work is complete.
 *  - Any row changed by another application process cannot be read until it is committed by that application process.
 * Unlike RR, RS does not completely isolate the application process from the effects of concurrent application processes. At level RS, application processes that issue the same query more than once in the same unit of work might see additional rows. These additional rows are called phantom rows.
 * 
 * Cursor stability
 * Like levels RR and RS, the Cursor Stability (CS) isolation level ensures that any row changed by another application process cannot be read until it is committed by that application process. Unlike RR and RS, level CS only ensures that the current row of every updatable cursor is not changed by other application processes. Thus, the rows read during a unit of work can be changed by other application processes. In addition to any exclusive locks, an application process running at level CS has at least a share lock for the current row of every one of its open cursors. In the SQL 1999 Core standard, Cursor Stability is called Read Committed.
 * 
 * Uncommitted read
 * For a SELECT INTO, FETCH with a read-only cursor, subquery, or subselect used in an INSERT statement, the Uncommitted Read (UR) isolation level allows:
 *  - Any row read during the unit of work to be changed by other application processes.
 *  - Any row changed by another application process to be read even if the change has not been committed by that application process.
 * For other operations, the rules of level CS apply. In DB2 UDB for z/OS and OS/390, UR is escalated to CS for a subquery used in a DELETE or UPDATE statement, or for a subselect used in an INSERT statement. In the SQL 1999 Core standard, Uncommitted Read is called Read Uncommitted.
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getIsolationLevelType()
 * @model
 * @generated
 */
public final class IsolationLevelType extends AbstractEnumerator {
	/**
	 * The '<em><b>REPEATABLE READ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPEATABLE_READ_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REPEATABLE_READ = 0;

	/**
	 * The '<em><b>READ STABILITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #READ_STABILITY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int READ_STABILITY = 1;

	/**
	 * The '<em><b>CURSOR STABILITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURSOR_STABILITY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CURSOR_STABILITY = 2;

	/**
	 * The '<em><b>UNCOMMITTED READ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNCOMMITTED_READ_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNCOMMITTED_READ = 3;

	/**
	 * The '<em><b>REPEATABLE READ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REPEATABLE READ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REPEATABLE_READ
	 * @generated
	 * @ordered
	 */
	public static final IsolationLevelType REPEATABLE_READ_LITERAL = new IsolationLevelType(REPEATABLE_READ, "REPEATABLE_READ", "REPEATABLE_READ"); //$NON-NLS-1$

	/**
	 * The '<em><b>READ STABILITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READ STABILITY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READ_STABILITY
	 * @generated
	 * @ordered
	 */
	public static final IsolationLevelType READ_STABILITY_LITERAL = new IsolationLevelType(READ_STABILITY, "READ_STABILITY", "READ_STABILITY"); //$NON-NLS-1$

	/**
	 * The '<em><b>CURSOR STABILITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CURSOR STABILITY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURSOR_STABILITY
	 * @generated
	 * @ordered
	 */
	public static final IsolationLevelType CURSOR_STABILITY_LITERAL = new IsolationLevelType(CURSOR_STABILITY, "CURSOR_STABILITY", "CURSOR_STABILITY"); //$NON-NLS-1$

	/**
	 * The '<em><b>UNCOMMITTED READ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNCOMMITTED READ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNCOMMITTED_READ
	 * @generated
	 * @ordered
	 */
	public static final IsolationLevelType UNCOMMITTED_READ_LITERAL = new IsolationLevelType(UNCOMMITTED_READ, "UNCOMMITTED_READ", "UNCOMMITTED_READ"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Isolation Level Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final IsolationLevelType[] VALUES_ARRAY =
		new IsolationLevelType[] {
			REPEATABLE_READ_LITERAL,
			READ_STABILITY_LITERAL,
			CURSOR_STABILITY_LITERAL,
			UNCOMMITTED_READ_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Isolation Level Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Isolation Level Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IsolationLevelType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IsolationLevelType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Isolation Level Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IsolationLevelType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IsolationLevelType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Isolation Level Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IsolationLevelType get(int value) {
		switch (value) {
			case REPEATABLE_READ: return REPEATABLE_READ_LITERAL;
			case READ_STABILITY: return READ_STABILITY_LITERAL;
			case CURSOR_STABILITY: return CURSOR_STABILITY_LITERAL;
			case UNCOMMITTED_READ: return UNCOMMITTED_READ_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private IsolationLevelType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //IsolationLevelType
