/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASERoleImpl.java,v 1.8 2007/07/06 08:40:20 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleImpl;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERoleImpl#getSqlContainer <em>Sql Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASERoleImpl extends RoleImpl implements SybaseASERole 
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
	protected SybaseASERoleImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_ROLE;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__SQL_CONTAINER, oldSqlContainer, sqlContainer));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__SQL_CONTAINER, oldSqlContainer, sqlContainer));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List getReceivedPrivileges(String catalogName) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__SQL_CONTAINER:
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__SQL_CONTAINER:
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__SQL_CONTAINER:
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__SQL_CONTAINER:
                return sqlContainer != null;
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
                case SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__SQL_CONTAINER: return SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER;
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
                case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER: return SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__SQL_CONTAINER;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //SybaseASERoleImpl
