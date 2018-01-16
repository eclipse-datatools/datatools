/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseGroupImpl.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseGroup;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.UserImpl;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseGroupImpl#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseGroupImpl#getSqlContainer <em>Sql Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseGroupImpl extends UserImpl implements SybaseASABaseGroup 
{
    /**
	 * The cached value of the '{@link #getUser() <em>User</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected EList user;

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
	protected SybaseASABaseGroupImpl()
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
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_GROUP;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getUser()
    {
		if (user == null) {
			user = new EObjectWithInverseResolvingEList.ManyInverse(User.class, this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER, SQLAccessControlPackage.USER__GROUP);
		}
		return user;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__SQL_CONTAINER, oldSqlContainer, sqlContainer));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__SQL_CONTAINER, oldSqlContainer, sqlContainer));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER:
				return ((InternalEList)getUser()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER:
				return ((InternalEList)getUser()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER:
				return getUser();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__SQL_CONTAINER:
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER:
				getUser().clear();
				getUser().addAll((Collection)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__SQL_CONTAINER:
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER:
				getUser().clear();
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__SQL_CONTAINER:
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER:
				return user != null && !user.isEmpty();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__SQL_CONTAINER:
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
		if (baseClass == Group.class) {
			switch (derivedFeatureID) {
				case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER: return SQLAccessControlPackage.GROUP__USER;
				default: return -1;
			}
		}
		if (baseClass == SybaseAuthorizationIdentifier.class) {
			switch (derivedFeatureID) {
				case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__SQL_CONTAINER: return SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER;
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
		if (baseClass == Group.class) {
			switch (baseFeatureID) {
				case SQLAccessControlPackage.GROUP__USER: return SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__USER;
				default: return -1;
			}
		}
		if (baseClass == SybaseAuthorizationIdentifier.class) {
			switch (baseFeatureID) {
				case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER: return SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP__SQL_CONTAINER;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //SybaseASABaseGroupImpl