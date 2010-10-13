/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseAuthorizationIdentifierImpl.java,v 1.1 2008/04/28 17:10:56 bfitzpatrick Exp $
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase Authorization Identifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseAuthorizationIdentifierImpl#getSqlContainer <em>Sql Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseAuthorizationIdentifierImpl extends AuthorizationIdentifierImpl implements SybaseAuthorizationIdentifier {
	/**
	 * The cached value of the '{@link #getSqlContainer() <em>Sql Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlContainer()
	 * @generated
	 * @ordered
	 */
	protected SQLObject sqlContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseAuthorizationIdentifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SybasesqlmodelPackage.Literals.SYBASE_AUTHORIZATION_IDENTIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLObject getSqlContainer() {
		if (sqlContainer != null && sqlContainer.eIsProxy()) {
			InternalEObject oldSqlContainer = (InternalEObject)sqlContainer;
			sqlContainer = (SQLObject)eResolveProxy(oldSqlContainer);
			if (sqlContainer != oldSqlContainer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER, oldSqlContainer, sqlContainer));
			}
		}
		return sqlContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLObject basicGetSqlContainer() {
		return sqlContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlContainer(SQLObject newSqlContainer) {
		SQLObject oldSqlContainer = sqlContainer;
		sqlContainer = newSqlContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER, oldSqlContainer, sqlContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER:
				if (resolve) return getSqlContainer();
				return basicGetSqlContainer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER:
				setSqlContainer((SQLObject)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER:
				setSqlContainer((SQLObject)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER:
				return sqlContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //SybaseAuthorizationIdentifierImpl
