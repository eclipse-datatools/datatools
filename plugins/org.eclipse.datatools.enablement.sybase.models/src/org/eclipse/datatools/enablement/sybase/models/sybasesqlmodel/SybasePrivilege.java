/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase Privilege</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege#isRevoked <em>Revoked</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybasePrivilege()
 * @model
 * @generated
 */
public interface SybasePrivilege extends Privilege {
	/**
	 * Returns the value of the '<em><b>Revoked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Revoked</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Revoked</em>' attribute.
	 * @see #setRevoked(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybasePrivilege_Revoked()
	 * @model
	 * @generated
	 */
	boolean isRevoked();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege#isRevoked <em>Revoked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Revoked</em>' attribute.
	 * @see #isRevoked()
	 * @generated
	 */
	void setRevoked(boolean value);

} // SybasePrivilege
