/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASERole.java,v 1.7 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Role</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASERole()
 * @model
 * @generated
 */
public interface SybaseASERole extends Role, SybaseAuthorizationIdentifier {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Get privileges of role for specified catalog
     * <!-- end-model-doc -->
     * @model dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false" catalogNameRequired="true"
     * @generated
     */
    List getReceivedPrivileges(String catalogName);

} // SybaseASERole
