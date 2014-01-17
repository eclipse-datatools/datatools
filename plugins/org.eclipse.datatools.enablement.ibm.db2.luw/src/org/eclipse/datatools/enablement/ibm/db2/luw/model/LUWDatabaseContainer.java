/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.datatools.enablement.ibm.db2.model.UnitType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * 
 * Table spaces and other storage structures
 * 
 * Storage structures contain database objects. The basic storage structure is the table space; it contains tables, indexes, large objects, and data defined with a LONG data type.
 * 
 * There are two types of table spaces:
 *  - Database managed space (DMS): a table space that is managed by the database manager.
 *  - System managed space (SMS): a table space that is managed by the operating system.
 * 
 * All table spaces consist of containers. A container describes where objects are stored. A subdirectory in a file system is an example of a container.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getContainerType <em>Container Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSizeInPages <em>Size In Pages</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSizeUnits <em>Size Units</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getTableSpace <em>Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getPartitions <em>Partitions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabaseContainer()
 * @model
 * @generated
 */
public interface LUWDatabaseContainer extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Container Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType
	 * @see #setContainerType(LUWContainerType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabaseContainer_ContainerType()
	 * @model
	 * @generated
	 */
	LUWContainerType getContainerType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getContainerType <em>Container Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType
	 * @see #getContainerType()
	 * @generated
	 */
	void setContainerType(LUWContainerType value);

	/**
	 * Returns the value of the '<em><b>Size In Pages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size In Pages</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size In Pages</em>' attribute.
	 * @see #setSizeInPages(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabaseContainer_SizeInPages()
	 * @model
	 * @generated
	 */
	int getSizeInPages();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSizeInPages <em>Size In Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size In Pages</em>' attribute.
	 * @see #getSizeInPages()
	 * @generated
	 */
	void setSizeInPages(int value);

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
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabaseContainer_Size()
	 * @model
	 * @generated
	 */
	int getSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(int value);

	/**
	 * Returns the value of the '<em><b>Size Units</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.UnitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size Units</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size Units</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see #setSizeUnits(UnitType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabaseContainer_SizeUnits()
	 * @model
	 * @generated
	 */
	UnitType getSizeUnits();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSizeUnits <em>Size Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size Units</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see #getSizeUnits()
	 * @generated
	 */
	void setSizeUnits(UnitType value);

	/**
	 * Returns the value of the '<em><b>Table Space</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Space</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Space</em>' container reference.
	 * @see #setTableSpace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabaseContainer_TableSpace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getContainers
	 * @model opposite="containers" required="true"
	 * @generated
	 */
	LUWTableSpace getTableSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getTableSpace <em>Table Space</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Space</em>' container reference.
	 * @see #getTableSpace()
	 * @generated
	 */
	void setTableSpace(LUWTableSpace value);

	/**
	 * Returns the value of the '<em><b>Partitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partitions</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabaseContainer_Partitions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getContainers
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition" opposite="containers"
	 * @generated
	 */
	EList getPartitions();

} // LUWDatabaseContainer
