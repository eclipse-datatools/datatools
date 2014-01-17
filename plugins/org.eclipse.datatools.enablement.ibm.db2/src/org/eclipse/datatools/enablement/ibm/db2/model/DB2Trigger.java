/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;


import org.eclipse.datatools.modelbase.sql.tables.Trigger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Triggers (Chapter 1. Concepts 7)
 * 
 * A trigger defines a set of actions that are executed automatically whenever a delete, insert, or update operation occurs on a specified base table. When such an SQL operation is executed, the trigger is said to be activated. Triggers can be used along with referential constraints and check constraints to enforce data integrity rules. Triggers are more powerful than constraints because they can also be used to cause updates to other tables, automatically generate or transform values for inserted or updated rows, or invoke functions that perform operations both inside and outside of DB2. For example, instead of preventing an update to a column if the new value exceeds a certain amount, a trigger can substitute a valid value and send a notice to an administrator about the invalid update.
 * 
 * Triggers are a useful mechanism to define and enforce transitional business rules that involve different states of the data (for example, salary cannot be increased by more than 10 percent). Such a limit requires comparing the value of a salary before and after an increase. For rules that do not involve more than one state of the data, consider using referential and check constraints. Triggers also move the application logic that is required to enforce business rules into the database, which can result in faster application development and easier maintenance because the business rule is no longer repeated in several applications, but one version is centralized to the trigger. With the logic in the database, for example, the previously mentioned limit on increases to the salary column of a table, DB2 checks the validity of the changes that any application makes to the salary column. In addition, the application programs do not need to be changed when the logic changes.
 * 
 * There are a number of criteria that are defined when creating a trigger which are used to determine when a trigger should be activated.
 *  - The subject table defines the base table for which the trigger is defined.
 *  - The trigger event defines a specific SQL operation that modifies the subject table. The operation could be delete, insert, or update.
 *  - The trigger activation time defines whether the trigger should be activated before or after the trigger event is performed on the subject table. The statement that causes a trigger to be activated will include a set of affected rows. These are the rows of the subject table that are being deleted, inserted or updated. The trigger granularity defines whether the actions of the trigger will be performed once for the statement or once for each of the rows in the set of affected rows. The trigger action consists of an optional search condition and a set of SQL statements that are executed whenever the trigger is activated. The SQL statements are only executed if no search condition is specified or the specified search condition evaluates to true.
 * 
 * The triggered action may refer to the values in the set of affected rows. This is supported through the use of transition variables. Transition variables use the names of the columns in the subject table qualified by a specified name that identifies whether the reference is to the old value (prior to the update) or the new value (after the update). The new value can also be changed using the SET transition-variable statement in before update or insert triggers. Another means of referring to the values in the set of affected rows is using transition tables.
 * 
 * Transition tables also use the names of the columns of the subject table but have a name specified that allows the complete set of affected rows to be treated as a table. Transition tables can only be used in after triggers. Separate transition tables can be defined for old and new values. Multiple triggers can be specified for a combination of table, event, or activation time. The order in which the triggers are activated is the same as the order in which they were created. Thus, the most recently created trigger will be the last trigger activated. The activation of a trigger may cause trigger cascading. This is the result of the activation of one trigger that executes SQL statements that cause the activation of other triggers or even the same trigger again. The triggered actions may also cause updates as a result of the original modification, which may result in the activation of additional triggers. With trigger cascading, a significant chain of triggers may be activated causing significant change to the database as a result of a single delete, insert or update statement.
 * 
 * The actions performed in the trigger are considered to be part of the operation that caused the trigger to be executed.
 *  - The database manager ensures that the operation and the triggers executed as a result of that operation either all complete or are backed out. Operations that occurred prior to the triggering operation are not affected.
 *  - The database manager effectively checks all constraints (except for a constraint with a RESTRICT delete rule) after the operation and the associated triggers have been executed.
 * 
 * Triggers (Appendix E. SQLSTATE values -- common return codes)
 * 
 * SQLSTATE 51037 - The operation is not allowed because a trigger has been marked inoperative.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger#isOperative <em>Operative</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger#isSecured <em>Secured</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Trigger()
 * @model
 * @generated
 */
public interface DB2Trigger extends Trigger, DB2AccessPlan {
	/**
	 * Returns the value of the '<em><b>Operative</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operative</em>' attribute.
	 * @see #setOperative(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Trigger_Operative()
	 * @model default="true"
	 * @generated
	 */
	boolean isOperative();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger#isOperative <em>Operative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operative</em>' attribute.
	 * @see #isOperative()
	 * @generated
	 */
	void setOperative(boolean value);

	/**
	 * Returns the value of the '<em><b>Secured</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secured</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secured</em>' attribute.
	 * @see #setSecured(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Trigger_Secured()
	 * @model default="False"
	 * @generated
	 */
	boolean isSecured();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger#isSecured <em>Secured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secured</em>' attribute.
	 * @see #isSecured()
	 * @generated
	 */
	void setSecured(boolean value);

} // DB2Trigger
