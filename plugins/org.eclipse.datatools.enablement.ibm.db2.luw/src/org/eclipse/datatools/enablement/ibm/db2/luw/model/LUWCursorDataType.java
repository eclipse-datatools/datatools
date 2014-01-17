/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWCursorDataType.java,v 1.1 2009/02/27 18:42:01 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cursor Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType#getRowType <em>Row Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWCursorDataType()
 * @model
 * @generated
 */
public interface LUWCursorDataType extends UserDefinedType, SQLObject {
	/**
	 * Returns the value of the '<em><b>Row Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Type</em>' reference.
	 * @see #setRowType(LUWRowDataType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWCursorDataType_RowType()
	 * @model
	 * @generated
	 */
	LUWRowDataType getRowType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType#getRowType <em>Row Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Type</em>' reference.
	 * @see #getRowType()
	 * @generated
	 */
	void setRowType(LUWRowDataType value);

} // LUWCursorDataType
