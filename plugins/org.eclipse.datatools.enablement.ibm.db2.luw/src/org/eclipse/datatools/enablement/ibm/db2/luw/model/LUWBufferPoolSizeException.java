/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWBufferPoolSizeException.java,v 1.1 2009/02/27 23:12:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Buffer Pool Size Exception</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getBufferPool <em>Buffer Pool</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getPartitions <em>Partitions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPoolSizeException()
 * @model
 * @generated
 */
public interface LUWBufferPoolSizeException extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPoolSizeException_Size()
	 * @model
	 * @generated
	 */
	int getSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(int value);

	/**
	 * Returns the value of the '<em><b>Buffer Pool</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSizeException <em>Size Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Buffer Pool</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buffer Pool</em>' container reference.
	 * @see #setBufferPool(LUWBufferPool)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPoolSizeException_BufferPool()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSizeException
	 * @model opposite="sizeException" required="true"
	 * @generated
	 */
	LUWBufferPool getBufferPool();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getBufferPool <em>Buffer Pool</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Buffer Pool</em>' container reference.
	 * @see #getBufferPool()
	 * @generated
	 */
	void setBufferPool(LUWBufferPool value);

	/**
	 * Returns the value of the '<em><b>Partitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getSizeException <em>Size Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partitions</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPoolSizeException_Partitions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getSizeException
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition" opposite="sizeException"
	 * @generated
	 */
	EList getPartitions();

} // LUWBufferPoolSizeException
