/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Tables (Chapter 1. Concepts 3)
 * 
 * Tables are logical structures maintained by the database manager. Tables are made up of columns and rows. There is no inherent order of the rows within a table. At the intersection of every column and row is a specific data item called a value. A column is a set of values of the same type. A row is a sequence of values such that the nth value is a value of the nth column of the table.
 * 
 * A base table is created with the CREATE TABLE statement and is used to hold persistent user data. A result table is a set of rows that the database manager selects or selects or generates, directly or indirectly, from one or more base tables. For more information about creating tables, see CREATE TABLE on page 379.
 * 
 * A declared temporary table is created with a DECLARE GLOBAL TEMPORARY TABLE statement and is used to hold temporary data on behalf of a single application. This table is dropped implicitly when the application disconnects from the database.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getDataCapture <em>Data Capture</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#isActivateRowAccessControl <em>Activate Row Access Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#isActivateColumnAccessControl <em>Activate Column Access Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getOrganizeBy <em>Organize By</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPackages <em>Packages</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPeriods <em>Periods</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getHistoryTable <em>History Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getTemporalTable <em>Temporal Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getMasks <em>Masks</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table()
 * @model
 * @generated
 */
public interface DB2Table extends PersistentTable {
	/**
	 * Returns the value of the '<em><b>Data Capture</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Capture</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Capture</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType
	 * @see #setDataCapture(DataCaptureType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_DataCapture()
	 * @model
	 * @generated
	 */
	DataCaptureType getDataCapture();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getDataCapture <em>Data Capture</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Capture</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType
	 * @see #getDataCapture()
	 * @generated
	 */
	void setDataCapture(DataCaptureType value);

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
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_ActivateRowAccessControl()
	 * @model default="False"
	 * @generated
	 */
	boolean isActivateRowAccessControl();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#isActivateRowAccessControl <em>Activate Row Access Control</em>}' attribute.
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
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_ActivateColumnAccessControl()
	 * @model default="False"
	 * @generated
	 */
	boolean isActivateColumnAccessControl();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#isActivateColumnAccessControl <em>Activate Column Access Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activate Column Access Control</em>' attribute.
	 * @see #isActivateColumnAccessControl()
	 * @generated
	 */
	void setActivateColumnAccessControl(boolean value);

	/**
	 * Returns the value of the '<em><b>Organize By</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Organize By</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Organize By</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization
	 * @see #setOrganizeBy(DB2TableOrganization)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_OrganizeBy()
	 * @model
	 * @generated
	 */
	DB2TableOrganization getOrganizeBy();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getOrganizeBy <em>Organize By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Organize By</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization
	 * @see #getOrganizeBy()
	 * @generated
	 */
	void setOrganizeBy(DB2TableOrganization value);

	/**
	 * Returns the value of the '<em><b>Packages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Packages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Packages</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_Packages()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Package"
	 * @generated
	 */
	EList getPackages();

	/**
	 * Returns the value of the '<em><b>Periods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Periods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Periods</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_Periods()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getTable
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Period" opposite="table" containment="true"
	 * @generated
	 */
	EList getPeriods();

	/**
	 * Returns the value of the '<em><b>History Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getTemporalTable <em>Temporal Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>History Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>History Table</em>' reference.
	 * @see #setHistoryTable(DB2Table)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_HistoryTable()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getTemporalTable
	 * @model opposite="temporalTable"
	 * @generated
	 */
	DB2Table getHistoryTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getHistoryTable <em>History Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>History Table</em>' reference.
	 * @see #getHistoryTable()
	 * @generated
	 */
	void setHistoryTable(DB2Table value);

	/**
	 * Returns the value of the '<em><b>Temporal Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getHistoryTable <em>History Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Temporal Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temporal Table</em>' reference.
	 * @see #setTemporalTable(DB2Table)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_TemporalTable()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getHistoryTable
	 * @model opposite="historyTable"
	 * @generated
	 */
	DB2Table getTemporalTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getTemporalTable <em>Temporal Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temporal Table</em>' reference.
	 * @see #getTemporalTable()
	 * @generated
	 */
	void setTemporalTable(DB2Table value);

	/**
	 * Returns the value of the '<em><b>Masks</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectTable <em>Subject Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Masks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Masks</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_Masks()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectTable
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask" opposite="subjectTable"
	 * @generated
	 */
	EList getMasks();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSubjectTable <em>Subject Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Table_Permissions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSubjectTable
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission" opposite="subjectTable"
	 * @generated
	 */
	EList getPermissions();

} // DB2Table
