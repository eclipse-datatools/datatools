/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWDataPartitionKey.java,v 1.4 2008/01/29 00:04:56 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Partition Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionMethod <em>Partition Method</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionExpressions <em>Partition Expressions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartitionKey()
 * @model
 * @generated
 */
public interface LUWDataPartitionKey extends EObject {
	/**
	 * Returns the value of the '<em><b>Partition Method</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Method</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod
	 * @see #setPartitionMethod(DataPartitionMethod)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartitionKey_PartitionMethod()
	 * @model
	 * @generated
	 */
	DataPartitionMethod getPartitionMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionMethod <em>Partition Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition Method</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod
	 * @see #getPartitionMethod()
	 * @generated
	 */
	void setPartitionMethod(DataPartitionMethod value);

	/**
	 * Returns the value of the '<em><b>Partition Expressions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Expressions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Expressions</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartitionKey_PartitionExpressions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getKey
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression" opposite="key" containment="true" required="true"
	 * @generated
	 */
	EList getPartitionExpressions();

	/**
	 * Returns the value of the '<em><b>Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitionKey <em>Data Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' container reference.
	 * @see #setTable(LUWStorageTable)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartitionKey_Table()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitionKey
	 * @model opposite="dataPartitionKey" required="true"
	 * @generated
	 */
	LUWStorageTable getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getTable <em>Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' container reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(LUWStorageTable value);

} // LUWDataPartitionKey
