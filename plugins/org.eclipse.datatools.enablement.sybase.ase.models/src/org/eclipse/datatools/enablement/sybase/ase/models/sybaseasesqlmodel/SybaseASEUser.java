/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEUser.java,v 1.7 2007/07/06 08:40:17 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser#getLoginName <em>Login Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUser()
 * @model
 * @generated
 */
public interface SybaseASEUser extends User, SybaseAuthorizationIdentifier {
	/**
     * Returns the value of the '<em><b>Login Name</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Login Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Login Name</em>' attribute.
     * @see #setLoginName(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUser_LoginName()
     * @model default=""
     * @generated
     */
    String getLoginName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser#getLoginName <em>Login Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Login Name</em>' attribute.
     * @see #getLoginName()
     * @generated
     */
    void setLoginName(String value);

} // SybaseASEUser