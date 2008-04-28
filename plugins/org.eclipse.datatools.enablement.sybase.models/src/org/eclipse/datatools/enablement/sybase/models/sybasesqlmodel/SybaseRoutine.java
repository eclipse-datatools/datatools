/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import org.eclipse.datatools.modelbase.sql.routines.Routine;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase Routine</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseRoutine()
 * @model abstract="true"
 * @generated
 */
public interface SybaseRoutine extends Routine, SybaseAuthorizedObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Parses the source code to get parameter default values. Not intended for use by clients.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void parseParameterDefaultValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isSystem();

} // SybaseRoutine
