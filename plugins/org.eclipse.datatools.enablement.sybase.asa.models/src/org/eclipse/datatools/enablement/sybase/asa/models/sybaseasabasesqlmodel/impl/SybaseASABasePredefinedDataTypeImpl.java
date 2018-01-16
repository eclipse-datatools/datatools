/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABasePredefinedDataTypeImpl.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.impl.PredefinedDataTypeImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Predefined Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePredefinedDataTypeImpl#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABasePredefinedDataTypeImpl extends PredefinedDataTypeImpl implements SybaseASABasePredefinedDataType 
{
    /**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected SybaseASABaseDatabase database;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABasePredefinedDataTypeImpl()
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
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASABaseDatabase getDatabase()
    {
		if (database != null && database.eIsProxy()) {
			InternalEObject oldDatabase = (InternalEObject)database;
			database = (SybaseASABaseDatabase)eResolveProxy(oldDatabase);
			if (database != oldDatabase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE, oldDatabase, database));
			}
		}
		return database;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASABaseDatabase basicGetDatabase()
    {
		return database;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatabase(SybaseASABaseDatabase newDatabase, NotificationChain msgs)
    {
		SybaseASABaseDatabase oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE, oldDatabase, newDatabase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(SybaseASABaseDatabase newDatabase)
    {
		if (newDatabase != database) {
			NotificationChain msgs = null;
			if (database != null)
				msgs = ((InternalEObject)database).eInverseRemove(this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES, SybaseASABaseDatabase.class, msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES, SybaseASABaseDatabase.class, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE, newDatabase, newDatabase));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE:
				if (database != null)
					msgs = ((InternalEObject)database).eInverseRemove(this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES, SybaseASABaseDatabase.class, msgs);
				return basicSetDatabase((SybaseASABaseDatabase)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE:
				return basicSetDatabase(null, msgs);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE:
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
	public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE:
				setDatabase((SybaseASABaseDatabase)newValue);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE:
				setDatabase((SybaseASABaseDatabase)null);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE:
				return database != null;
		}
		return super.eIsSet(featureID);
	}

} //SybaseASABasePredefinedDataTypeImpl