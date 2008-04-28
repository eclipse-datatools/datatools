/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage
 * @generated
 */
public interface SybasesqlmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SybasesqlmodelFactory eINSTANCE = org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sybase Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase Parameter</em>'.
	 * @generated
	 */
	SybaseParameter createSybaseParameter();

	/**
	 * Returns a new object of class '<em>Sybase Base Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase Base Table</em>'.
	 * @generated
	 */
	SybaseBaseTable createSybaseBaseTable();

	/**
	 * Returns a new object of class '<em>Sybase View Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase View Table</em>'.
	 * @generated
	 */
	SybaseViewTable createSybaseViewTable();

	/**
	 * Returns a new object of class '<em>Sybase Authorization Identifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase Authorization Identifier</em>'.
	 * @generated
	 */
	SybaseAuthorizationIdentifier createSybaseAuthorizationIdentifier();

	/**
	 * Returns a new object of class '<em>Sybase Index Member</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase Index Member</em>'.
	 * @generated
	 */
	SybaseIndexMember createSybaseIndexMember();

	/**
	 * Returns a new object of class '<em>Sybase Privilege</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase Privilege</em>'.
	 * @generated
	 */
	SybasePrivilege createSybasePrivilege();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SybasesqlmodelPackage getSybasesqlmodelPackage();

} //SybasesqlmodelFactory
