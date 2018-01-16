/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Temporary Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents the Created Global Temporary Table introduced in DB2 LUW v9.7
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable#getLogOption <em>Log Option</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTemporaryTable()
 * @model
 * @generated
 */
public interface LUWTemporaryTable extends TemporaryTable, LUWTemporaryStorageTable {
	/**
	 * Returns the value of the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' reference.
	 * @see #setTable(Table)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTemporaryTable_Table()
	 * @model
	 * @generated
	 */
	Table getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable#getTable <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(Table value);

	/**
	 * Returns the value of the '<em><b>Log Option</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Log Option</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Log Option</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption
	 * @see #setLogOption(LUWTemporaryTableLoggingOption)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTemporaryTable_LogOption()
	 * @model
	 * @generated
	 */
	LUWTemporaryTableLoggingOption getLogOption();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable#getLogOption <em>Log Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Log Option</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption
	 * @see #getLogOption()
	 * @generated
	 */
	void setLogOption(LUWTemporaryTableLoggingOption value);

} // LUWTemporaryTable
