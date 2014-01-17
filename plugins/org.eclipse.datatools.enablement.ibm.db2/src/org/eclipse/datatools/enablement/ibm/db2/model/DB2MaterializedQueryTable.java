/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.tables.DerivedTable;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Materialized Query Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getRefresh <em>Refresh</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isOptimizeQuery <em>Optimize Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getMaintainedBy <em>Maintained By</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isActivateRowAccessControl <em>Activate Row Access Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isActivateColumnAccessControl <em>Activate Column Access Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getMasks <em>Masks</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2MaterializedQueryTable()
 * @model abstract="true"
 * @generated
 */
public interface DB2MaterializedQueryTable extends DerivedTable {

	/**
	 * Returns the value of the '<em><b>Optimize Query</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optimize Query</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optimize Query</em>' attribute.
	 * @see #setOptimizeQuery(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2MaterializedQueryTable_OptimizeQuery()
	 * @model default="true"
	 * @generated
	 */
	boolean isOptimizeQuery();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isOptimizeQuery <em>Optimize Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optimize Query</em>' attribute.
	 * @see #isOptimizeQuery()
	 * @generated
	 */
	void setOptimizeQuery(boolean value);

	/**
	 * Returns the value of the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activate Row Access Control</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activate Row Access Control</em>' attribute.
	 * @see #setActivateRowAccessControl(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2MaterializedQueryTable_ActivateRowAccessControl()
	 * @model default="False"
	 * @generated
	 */
	boolean isActivateRowAccessControl();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isActivateRowAccessControl <em>Activate Row Access Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activate Row Access Control</em>' attribute.
	 * @see #isActivateRowAccessControl()
	 * @generated
	 */
	void setActivateRowAccessControl(boolean value);

	/**
	 * Returns the value of the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activate Column Access Control</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activate Column Access Control</em>' attribute.
	 * @see #setActivateColumnAccessControl(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2MaterializedQueryTable_ActivateColumnAccessControl()
	 * @model default="False"
	 * @generated
	 */
	boolean isActivateColumnAccessControl();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isActivateColumnAccessControl <em>Activate Column Access Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activate Column Access Control</em>' attribute.
	 * @see #isActivateColumnAccessControl()
	 * @generated
	 */
	void setActivateColumnAccessControl(boolean value);

	/**
	 * Returns the value of the '<em><b>Masks</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectMQT <em>Subject MQT</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Masks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Masks</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2MaterializedQueryTable_Masks()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectMQT
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask" opposite="subjectMQT"
	 * @generated
	 */
	EList getMasks();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSubjectMQT <em>Subject MQT</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2MaterializedQueryTable_Permissions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSubjectMQT
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission" opposite="subjectMQT"
	 * @generated
	 */
	EList getPermissions();

} // DB2MaterializedQueryTable
