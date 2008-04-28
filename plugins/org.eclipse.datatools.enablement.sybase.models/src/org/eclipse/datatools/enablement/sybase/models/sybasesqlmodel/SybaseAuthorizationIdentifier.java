/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase Authorization Identifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier#getSqlContainer <em>Sql Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseAuthorizationIdentifier()
 * @model
 * @generated
 */
public interface SybaseAuthorizationIdentifier extends AuthorizationIdentifier {
	/**
	 * Returns the value of the '<em><b>Sql Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sql Container</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sql Container</em>' reference.
	 * @see #setSqlContainer(SQLObject)
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseAuthorizationIdentifier_SqlContainer()
	 * @model required="true"
	 * @generated
	 */
	SQLObject getSqlContainer();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier#getSqlContainer <em>Sql Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql Container</em>' reference.
	 * @see #getSqlContainer()
	 * @generated
	 */
	void setSqlContainer(SQLObject value);

} // SybaseAuthorizationIdentifier
