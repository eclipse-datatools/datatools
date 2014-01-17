package org.eclipse.datatools.enablement.ibm.db2.model;


import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Database Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * [...]
 * SQL statements are executed by a database manager. One of the functions of the database manager is to transform the specification of a result table into a sequence of internal operations that optimize data retrieval.
 * 
 * [...]
 * The database manager maintains a set of tables and views containing information about objects in the database. These tables and views are collectively known as the catalog. The catalog tables and catalog views contain information about objects such as tables, views, indexes, packages, and constraints.
 * 
 * [...]
 * An application process involves the execution of one or more programs, and is the unit to which the database manager allocates resources and locks.
 * 
 * [...]
 * The locking facilities of the database managers are similar but not identical. One of the common properties is that each of the database managers can acquire locks in order to prevent uncommitted changes made by one application process from being perceived by any other. The database manager will release all locks it has acquired on behalf of an application process when that process ends, but an application process itself can also explicitly request that locks be released sooner.
 * 
 * [...]
 * Like the locking facilities, the recovery facilities of the database managers are similar but not identical. One common property is that each of the database managers provides a means of backing out uncommitted changes made by an application process.
 * 
 * [...]
 * The database manager can back out all changes made in a unit of work or only selected changes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getDatabases <em>Databases</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getDb2Process <em>Db2 Process</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getServer <em>Server</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getCluster <em>Cluster</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2DatabaseManager()
 * @model
 * @generated
 */
public interface DB2DatabaseManager extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Databases</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Database}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Databases</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Databases</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2DatabaseManager_Databases()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Database"
	 * @generated
	 */
	EList getDatabases();

	/**
	 * Returns the value of the '<em><b>Db2 Process</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Db2 Process</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Db2 Process</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2DatabaseManager_Db2Process()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess"
	 * @generated
	 */
	EList getDb2Process();

	/**
	 * Returns the value of the '<em><b>Server</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWAdminServer}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWAdminServer#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2DatabaseManager_Server()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWAdminServer#getInstances
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWAdminServer" opposite="instances"
	 * @generated
	 */
	EList getServer();

	/**
	 * Returns the value of the '<em><b>Cluster</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cluster</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cluster</em>' reference.
	 * @see #setCluster(DB2Cluster)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2DatabaseManager_Cluster()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster#getInstance
	 * @model opposite="instance"
	 * @generated
	 */
	DB2Cluster getCluster();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getCluster <em>Cluster</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cluster</em>' reference.
	 * @see #getCluster()
	 * @generated
	 */
	void setCluster(DB2Cluster value);

} // DB2DatabaseManager
