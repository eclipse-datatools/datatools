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
 * A representation of the model object '<em><b>DB2 Application Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Application processes, concurrency, and recovery
 * 
 * All SQL programs execute as part of an application process. An application process involves the execution of one or more programs, and is the unit to which the database manager allocates resources and locks. Different application processes may involve the execution of different programs, or different executions of the same program. The means of starting and ending an application process are dependent on the environment.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess#getIsolationLevel <em>Isolation Level</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess#getUnitOfWork <em>Unit Of Work</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ApplicationProcess()
 * @model
 * @generated
 */
public interface DB2ApplicationProcess extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Isolation Level</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isolation Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isolation Level</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType
	 * @see #setIsolationLevel(IsolationLevelType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ApplicationProcess_IsolationLevel()
	 * @model
	 * @generated
	 */
	IsolationLevelType getIsolationLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess#getIsolationLevel <em>Isolation Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isolation Level</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType
	 * @see #getIsolationLevel()
	 * @generated
	 */
	void setIsolationLevel(IsolationLevelType value);

	/**
	 * Returns the value of the '<em><b>Unit Of Work</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit Of Work</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit Of Work</em>' containment reference.
	 * @see #setUnitOfWork(DB2Transaction)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ApplicationProcess_UnitOfWork()
	 * @model containment="true"
	 * @generated
	 */
	DB2Transaction getUnitOfWork();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess#getUnitOfWork <em>Unit Of Work</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit Of Work</em>' containment reference.
	 * @see #getUnitOfWork()
	 * @generated
	 */
	void setUnitOfWork(DB2Transaction value);

} // DB2ApplicationProcess
