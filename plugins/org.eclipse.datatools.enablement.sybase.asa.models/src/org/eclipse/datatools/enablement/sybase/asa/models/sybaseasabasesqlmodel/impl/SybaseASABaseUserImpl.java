/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseUserImpl.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUser;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.UserImpl;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserImpl#getSqlContainer <em>Sql Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseUserImpl extends UserImpl implements SybaseASABaseUser
{
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
    protected SybaseASABaseUserImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass()
    {
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_USER;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SQLObject getSqlContainer()
    {
		if (sqlContainer != null && sqlContainer.eIsProxy()) {
			InternalEObject oldSqlContainer = (InternalEObject)sqlContainer;
			sqlContainer = (SQLObject)eResolveProxy(oldSqlContainer);
			if (sqlContainer != oldSqlContainer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER__SQL_CONTAINER, oldSqlContainer, sqlContainer));
			}
		}
		return sqlContainer;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SQLObject basicGetSqlContainer()
    {
		return sqlContainer;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSqlContainer(SQLObject newSqlContainer)
    {
		SQLObject oldSqlContainer = sqlContainer;
		sqlContainer = newSqlContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER__SQL_CONTAINER, oldSqlContainer, sqlContainer));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER__SQL_CONTAINER:
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
    public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER__SQL_CONTAINER:
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
    public void eUnset(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER__SQL_CONTAINER:
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
    public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER__SQL_CONTAINER:
				return sqlContainer != null;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)
    {
		if (baseClass == SybaseAuthorizationIdentifier.class) {
			switch (derivedFeatureID) {
				case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER__SQL_CONTAINER: return SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)
    {
		if (baseClass == SybaseAuthorizationIdentifier.class) {
			switch (baseFeatureID) {
				case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER: return SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER__SQL_CONTAINER;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //SybaseASABaseUserImpl
