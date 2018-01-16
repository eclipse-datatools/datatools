/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEPredefinedDataTypeImpl.java,v 1.7 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.PredefinedDataTypeImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Predefined Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPredefinedDataTypeImpl#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEPredefinedDataTypeImpl extends PredefinedDataTypeImpl implements SybaseASEPredefinedDataType 
{
	/**
     * The cached value of the '{@link #getDatabase() <em>Database</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDatabase()
     * @generated
     * @ordered
     */
	protected SybaseASEDatabase database;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEPredefinedDataTypeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_PREDEFINED_DATA_TYPE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEDatabase getDatabase() {
        if (database != null && database.eIsProxy())
        {
            InternalEObject oldDatabase = (InternalEObject)database;
            database = (SybaseASEDatabase)eResolveProxy(oldDatabase);
            if (database != oldDatabase)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE, oldDatabase, database));
            }
        }
        return database;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEDatabase basicGetDatabase() {
        return database;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDatabase(SybaseASEDatabase newDatabase, NotificationChain msgs) {
        SybaseASEDatabase oldDatabase = database;
        database = newDatabase;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE, oldDatabase, newDatabase);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDatabase(SybaseASEDatabase newDatabase) {
        if (newDatabase != database)
        {
            NotificationChain msgs = null;
            if (database != null)
                msgs = ((InternalEObject)database).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES, SybaseASEDatabase.class, msgs);
            if (newDatabase != null)
                msgs = ((InternalEObject)newDatabase).eInverseAdd(this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES, SybaseASEDatabase.class, msgs);
            msgs = basicSetDatabase(newDatabase, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE, newDatabase, newDatabase));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE:
                if (database != null)
                    msgs = ((InternalEObject)database).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES, SybaseASEDatabase.class, msgs);
                return basicSetDatabase((SybaseASEDatabase)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE:
                return basicSetDatabase(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE:
                if (resolve) return getDatabase();
                return basicGetDatabase();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE:
                setDatabase((SybaseASEDatabase)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE:
                setDatabase((SybaseASEDatabase)null);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE:
                return database != null;
        }
        return super.eIsSet(featureID);
    }

} //SybaseASEPredefinedDataTypeImpl
