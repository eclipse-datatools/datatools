/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase View Table</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseViewTable()
 * @model
 * @generated
 */
public interface SybaseViewTable extends ViewTable, SybaseAuthorizedObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isSystem();

} // SybaseViewTable
