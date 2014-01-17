/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Transaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Unit of work
 * 
 * Like the locking facilities, the recovery facilities of the database managers are similar but not identical. One common property is that each of the database managers provides a means of backing out uncommitted changes made by an application process. This might be necessary in the event of a failure on the part of an application process, or in a deadlock situation. An application process itself, however, can explicitly request that its database changes be backed out. This operation is called rollback.
 * 
 * A unit of work (also called a transaction, logical unit of work, or unit of recovery) is a recoverable sequence of operations within an application process. At any time, an application process has at most a single unit of work, but the life of an application process may involve many units of work as a result of commit or rollback operations.
 * 
 * Note: In addition to relational databases, the environment in which an SQL program executes may also include other types of recoverable resources. If this is the case, the scope and acceptability of the SQL COMMIT and ROLLBACK statements depend on the environment.
 * 
 * A unit of work is started when the first SQL statement in the an application process or the first SQL statement after a commit or rollback is executed. A unit of work is ended by a commit operation, a rollback operation, or the end of an application process. A commit or rollback operation affects only the database changes made within the unit of work it ends. While these changes remain uncommitted, other application processes are unable to perceive them and they can be backed out. 4 Once committed, these database changes are accessible by other application processes and can no longer be backed out by a rollback.
 * 
 * The start and end of a unit of work define points of consistency within an application process. For example, a banking transaction might involve the transfer of funds from one account to another. Such a transaction would require that these funds be subtracted from the first account, and added to the second. Following the subtraction step, the data is inconsistent. Only after the funds have been added to the second account is consistency reestablished. When both steps are complete, the commit operation can be used to end the unit of work, thereby making the changes available to other application processes.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Transaction()
 * @model
 * @generated
 */
public interface DB2Transaction extends SQLObject {
} // DB2Transaction
