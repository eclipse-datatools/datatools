/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.tables.BaseTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase Base Table</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseBaseTable()
 * @model
 * @generated
 */
public interface SybaseBaseTable extends BaseTable, SybaseAuthorizedObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isSystem();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
	 * @generated
	 */
	List getCheckConstraints();

} // SybaseBaseTable
