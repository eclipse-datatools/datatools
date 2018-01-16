/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Temporary Storage Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Introduced to support the created global temporary table needs in DB2 LUW.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getPartitionKey <em>Partition Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getUserTemporaryTableSpace <em>User Temporary Table Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTemporaryStorageTable()
 * @model
 * @generated
 */
public interface LUWTemporaryStorageTable extends EObject {
	/**
	 * Returns the value of the '<em><b>Partition Key</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTemporaryStorageTable <em>Temporary Storage Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Key</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Key</em>' containment reference.
	 * @see #setPartitionKey(LUWPartitionKey)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTemporaryStorageTable_PartitionKey()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTemporaryStorageTable
	 * @model opposite="temporaryStorageTable" containment="true"
	 * @generated
	 */
	LUWPartitionKey getPartitionKey();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getPartitionKey <em>Partition Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition Key</em>' containment reference.
	 * @see #getPartitionKey()
	 * @generated
	 */
	void setPartitionKey(LUWPartitionKey value);

	/**
	 * Returns the value of the '<em><b>User Temporary Table Space</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTemporaryStorageTables <em>Temporary Storage Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Temporary Table Space</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Temporary Table Space</em>' reference.
	 * @see #setUserTemporaryTableSpace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTemporaryStorageTable_UserTemporaryTableSpace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTemporaryStorageTables
	 * @model opposite="temporaryStorageTables"
	 * @generated
	 */
	LUWTableSpace getUserTemporaryTableSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getUserTemporaryTableSpace <em>User Temporary Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Temporary Table Space</em>' reference.
	 * @see #getUserTemporaryTableSpace()
	 * @generated
	 */
	void setUserTemporaryTableSpace(LUWTableSpace value);

} // LUWTemporaryStorageTable
