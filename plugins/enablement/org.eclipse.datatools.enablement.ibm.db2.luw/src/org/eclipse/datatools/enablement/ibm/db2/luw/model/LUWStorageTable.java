/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWStorageTable.java,v 1.11 2008/01/29 00:04:56 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Storage Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isValueCompression <em>Value Compression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isRowCompression <em>Row Compression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isRowCompressionEmpty <em>Row Compression Empty</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getCompressionMode <em>Compression Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getPartitionKey <em>Partition Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getIndexDataTableSpace <em>Index Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getLOBDataTableSpace <em>LOB Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getRegularDataTableSpace <em>Regular Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitions <em>Data Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitionKey <em>Data Partition Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface LUWStorageTable extends EObject {
	/**
	 * Returns the value of the '<em><b>Value Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Compression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Compression</em>' attribute.
	 * @see #setValueCompression(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_ValueCompression()
	 * @model
	 * @generated
	 */
	boolean isValueCompression();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isValueCompression <em>Value Compression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Compression</em>' attribute.
	 * @see #isValueCompression()
	 * @generated
	 */
	void setValueCompression(boolean value);

	/**
	 * Returns the value of the '<em><b>Row Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Compression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Compression</em>' attribute.
	 * @see #setRowCompression(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_RowCompression()
	 * @model
	 * @generated
	 */
	boolean isRowCompression();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isRowCompression <em>Row Compression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Compression</em>' attribute.
	 * @see #isRowCompression()
	 * @generated
	 */
	void setRowCompression(boolean value);

	/**
	 * Returns the value of the '<em><b>Row Compression Empty</b></em>' attribute.
	 * The default value is <code>"True"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Compression Empty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Compression Empty</em>' attribute.
	 * @see #setRowCompressionEmpty(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_RowCompressionEmpty()
	 * @model default="True"
	 * @generated
	 */
	boolean isRowCompressionEmpty();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isRowCompressionEmpty <em>Row Compression Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Compression Empty</em>' attribute.
	 * @see #isRowCompressionEmpty()
	 * @generated
	 */
	void setRowCompressionEmpty(boolean value);

	/**
	 * Returns the value of the '<em><b>Compression Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compression Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compression Mode</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode
	 * @see #setCompressionMode(LUWStorageTableCompressionMode)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_CompressionMode()
	 * @model
	 * @generated
	 */
	LUWStorageTableCompressionMode getCompressionMode();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getCompressionMode <em>Compression Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compression Mode</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode
	 * @see #getCompressionMode()
	 * @generated
	 */
	void setCompressionMode(LUWStorageTableCompressionMode value);

	/**
	 * Returns the value of the '<em><b>Partition Key</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Key</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Key</em>' containment reference.
	 * @see #setPartitionKey(LUWPartitionKey)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_PartitionKey()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTable
	 * @model opposite="table" containment="true"
	 * @generated
	 */
	LUWPartitionKey getPartitionKey();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getPartitionKey <em>Partition Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition Key</em>' containment reference.
	 * @see #getPartitionKey()
	 * @generated
	 */
	void setPartitionKey(LUWPartitionKey value);

	/**
	 * Returns the value of the '<em><b>Index Data Table Space</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataTables <em>Index Data Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Data Table Space</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Data Table Space</em>' reference.
	 * @see #setIndexDataTableSpace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_IndexDataTableSpace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataTables
	 * @model opposite="indexDataTables"
	 * @generated
	 */
	LUWTableSpace getIndexDataTableSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getIndexDataTableSpace <em>Index Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Data Table Space</em>' reference.
	 * @see #getIndexDataTableSpace()
	 * @generated
	 */
	void setIndexDataTableSpace(LUWTableSpace value);

	/**
	 * Returns the value of the '<em><b>LOB Data Table Space</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataTables <em>LOB Data Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LOB Data Table Space</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LOB Data Table Space</em>' reference.
	 * @see #setLOBDataTableSpace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_LOBDataTableSpace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataTables
	 * @model opposite="LOBDataTables"
	 * @generated
	 */
	LUWTableSpace getLOBDataTableSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getLOBDataTableSpace <em>LOB Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LOB Data Table Space</em>' reference.
	 * @see #getLOBDataTableSpace()
	 * @generated
	 */
	void setLOBDataTableSpace(LUWTableSpace value);

	/**
	 * Returns the value of the '<em><b>Regular Data Table Space</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataTables <em>Regular Data Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regular Data Table Space</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regular Data Table Space</em>' reference.
	 * @see #setRegularDataTableSpace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_RegularDataTableSpace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataTables
	 * @model opposite="regularDataTables" required="true"
	 * @generated
	 */
	LUWTableSpace getRegularDataTableSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getRegularDataTableSpace <em>Regular Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Regular Data Table Space</em>' reference.
	 * @see #getRegularDataTableSpace()
	 * @generated
	 */
	void setRegularDataTableSpace(LUWTableSpace value);

	/**
	 * Returns the value of the '<em><b>Data Partitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Partitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Partitions</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_DataPartitions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getTable
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition" opposite="table" containment="true"
	 * @generated
	 */
	EList getDataPartitions();

	/**
	 * Returns the value of the '<em><b>Data Partition Key</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Partition Key</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Partition Key</em>' containment reference.
	 * @see #setDataPartitionKey(LUWDataPartitionKey)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWStorageTable_DataPartitionKey()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getTable
	 * @model opposite="table" containment="true"
	 * @generated
	 */
	LUWDataPartitionKey getDataPartitionKey();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitionKey <em>Data Partition Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Partition Key</em>' containment reference.
	 * @see #getDataPartitionKey()
	 * @generated
	 */
	void setDataPartitionKey(LUWDataPartitionKey value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
	 * @generated
	 */
	List getTableSpaces();

} // LUWStorageTable
