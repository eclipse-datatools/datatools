/**
 * <copyright>
 * </copyright>
 *
 * $Id: RelationalRemoteDataSet.java,v 1.7 2007/10/12 23:05:35 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Remote Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRelationalRemoteDataSet()
 * @model
 * @generated
 */
public interface RelationalRemoteDataSet extends SQLObject, RemoteDataSet {
	/**
	 * Returns the value of the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' reference.
	 * @see #setTable(BaseTable)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRelationalRemoteDataSet_Table()
	 * @model
	 * @generated
	 */
	BaseTable getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet#getTable <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(BaseTable value);

} // RemoteTable
