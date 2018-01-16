/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWDataPartition.java,v 1.7 2007/10/12 23:05:35 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Partition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#isLowInclusive <em>Low Inclusive</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#isHighInclusive <em>High Inclusive</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getLOBDataTableSpace <em>LOB Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getRegularDataTableSpace <em>Regular Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getPartitionElements <em>Partition Elements</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getIndexDataTableSpace <em>Index Data Table Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition()
 * @model
 * @generated
 */
public interface LUWDataPartition extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Low Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Low Inclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Low Inclusive</em>' attribute.
	 * @see #setLowInclusive(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition_LowInclusive()
	 * @model
	 * @generated
	 */
	boolean isLowInclusive();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#isLowInclusive <em>Low Inclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Low Inclusive</em>' attribute.
	 * @see #isLowInclusive()
	 * @generated
	 */
	void setLowInclusive(boolean value);

	/**
	 * Returns the value of the '<em><b>High Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>High Inclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>High Inclusive</em>' attribute.
	 * @see #setHighInclusive(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition_HighInclusive()
	 * @model
	 * @generated
	 */
	boolean isHighInclusive();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#isHighInclusive <em>High Inclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>High Inclusive</em>' attribute.
	 * @see #isHighInclusive()
	 * @generated
	 */
	void setHighInclusive(boolean value);

	/**
	 * Returns the value of the '<em><b>LOB Data Table Space</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataPartition <em>LOB Data Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LOB Data Table Space</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LOB Data Table Space</em>' reference.
	 * @see #setLOBDataTableSpace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition_LOBDataTableSpace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataPartition
	 * @model opposite="LOBDataPartition"
	 * @generated
	 */
	LUWTableSpace getLOBDataTableSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getLOBDataTableSpace <em>LOB Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LOB Data Table Space</em>' reference.
	 * @see #getLOBDataTableSpace()
	 * @generated
	 */
	void setLOBDataTableSpace(LUWTableSpace value);

	/**
	 * Returns the value of the '<em><b>Regular Data Table Space</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataPartition <em>Regular Data Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regular Data Table Space</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regular Data Table Space</em>' reference.
	 * @see #setRegularDataTableSpace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition_RegularDataTableSpace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataPartition
	 * @model opposite="regularDataPartition"
	 * @generated
	 */
	LUWTableSpace getRegularDataTableSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getRegularDataTableSpace <em>Regular Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Regular Data Table Space</em>' reference.
	 * @see #getRegularDataTableSpace()
	 * @generated
	 */
	void setRegularDataTableSpace(LUWTableSpace value);

	/**
	 * Returns the value of the '<em><b>Partition Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getPartition <em>Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Elements</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition_PartitionElements()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getPartition
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement" opposite="partition" required="true"
	 * @generated
	 */
	EList getPartitionElements();

	/**
	 * Returns the value of the '<em><b>Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitions <em>Data Partitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' container reference.
	 * @see #setTable(LUWStorageTable)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition_Table()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitions
	 * @model opposite="dataPartitions" required="true"
	 * @generated
	 */
	LUWStorageTable getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getTable <em>Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' container reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(LUWStorageTable value);

	/**
	 * Returns the value of the '<em><b>Index Data Table Space</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataPartition <em>Index Data Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Data Table Space</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Data Table Space</em>' reference.
	 * @see #setIndexDataTableSpace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDataPartition_IndexDataTableSpace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataPartition
	 * @model opposite="indexDataPartition"
	 * @generated
	 */
	LUWTableSpace getIndexDataTableSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getIndexDataTableSpace <em>Index Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Data Table Space</em>' reference.
	 * @see #getIndexDataTableSpace()
	 * @generated
	 */
	void setIndexDataTableSpace(LUWTableSpace value);

} // LUWDataPartition
