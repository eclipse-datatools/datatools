/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEUserImpl.java,v 1.8 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

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
 * An implementation of the model object '<em><b>Sybase ASE User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserImpl#getSqlContainer <em>Sql Container</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserImpl#getLoginName <em>Login Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEUserImpl extends UserImpl implements SybaseASEUser
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
     * The default value of the '{@link #getLoginName() <em>Login Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoginName()
     * @generated
     * @ordered
     */
    protected static final String LOGIN_NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getLoginName() <em>Login Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoginName()
     * @generated
     * @ordered
     */
    protected String loginName = LOGIN_NAME_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SybaseASEUserImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_USER;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SQLObject getSqlContainer() {
        if (sqlContainer != null && sqlContainer.eIsProxy())
        {
            InternalEObject oldSqlContainer = (InternalEObject)sqlContainer;
            sqlContainer = (SQLObject)eResolveProxy(oldSqlContainer);
            if (sqlContainer != oldSqlContainer)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_USER__SQL_CONTAINER, oldSqlContainer, sqlContainer));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_USER__SQL_CONTAINER, oldSqlContainer, sqlContainer));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLoginName() {
        return loginName;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLoginName(String newLoginName) {
        String oldLoginName = loginName;
        loginName = newLoginName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_USER__LOGIN_NAME, oldLoginName, loginName));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER__SQL_CONTAINER:
                if (resolve) return getSqlContainer();
                return basicGetSqlContainer();
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER__LOGIN_NAME:
                return getLoginName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER__SQL_CONTAINER:
                setSqlContainer((SQLObject)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER__LOGIN_NAME:
                setLoginName((String)newValue);
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER__SQL_CONTAINER:
                setSqlContainer((SQLObject)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER__LOGIN_NAME:
                setLoginName(LOGIN_NAME_EDEFAULT);
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER__SQL_CONTAINER:
                return sqlContainer != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER__LOGIN_NAME:
                return LOGIN_NAME_EDEFAULT == null ? loginName != null : !LOGIN_NAME_EDEFAULT.equals(loginName);
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == SybaseAuthorizationIdentifier.class)
        {
            switch (derivedFeatureID)
            {
                case SybaseasesqlmodelPackage.SYBASE_ASE_USER__SQL_CONTAINER: return SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER;
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
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
        if (baseClass == SybaseAuthorizationIdentifier.class)
        {
            switch (baseFeatureID)
            {
                case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER: return SybaseasesqlmodelPackage.SYBASE_ASE_USER__SQL_CONTAINER;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (loginName: "); //$NON-NLS-1$
        result.append(loginName);
        result.append(')');
        return result.toString();
    }

} //SybaseASEUserImpl