/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEDatabaseImpl.java,v 1.11 2008/02/19 04:30:40 renj Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl#getDataTypes <em>Data Types</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl#isEncryptionKeyApplicable <em>Encryption Key Applicable</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl#getCaches <em>Caches</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl#getWebServices <em>Web Services</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl#isWebserviceApplicable <em>Webservice Applicable</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl#getSdsServer <em>Sds Server</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl#getTempDBName <em>Temp DB Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEDatabaseImpl extends DatabaseImpl implements SybaseASEDatabase 
{
	
	public static final String VERSION_15              = "15";
	
	/**
	 * The cached value of the '{@link #getDataTypes() <em>Data Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTypes()
	 * @generated
	 * @ordered
	 */
	protected EList dataTypes;

	/**
	 * The default value of the '{@link #isEncryptionKeyApplicable() <em>Encryption Key Applicable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEncryptionKeyApplicable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENCRYPTION_KEY_APPLICABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEncryptionKeyApplicable() <em>Encryption Key Applicable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEncryptionKeyApplicable()
	 * @generated
	 * @ordered
	 */
	protected boolean encryptionKeyApplicable = ENCRYPTION_KEY_APPLICABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRoles() <em>Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoles()
	 * @generated
	 * @ordered
	 */
	protected EList roles;

	/**
	 * The cached value of the '{@link #getCaches() <em>Caches</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCaches()
	 * @generated
	 * @ordered
	 */
	protected EList caches;

	/**
	 * The cached value of the '{@link #getWebServices() <em>Web Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebServices()
	 * @generated
	 * @ordered
	 */
	protected EList webServices;

	/**
	 * The default value of the '{@link #isWebserviceApplicable() <em>Webservice Applicable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWebserviceApplicable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WEBSERVICE_APPLICABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWebserviceApplicable() <em>Webservice Applicable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWebserviceApplicable()
	 * @generated
	 * @ordered
	 */
	protected boolean webserviceApplicable = WEBSERVICE_APPLICABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSdsServer() <em>Sds Server</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSdsServer()
	 * @generated
	 * @ordered
	 */
    protected EList sdsServer;

    /**
	 * The default value of the '{@link #getTempDBName() <em>Temp DB Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempDBName()
	 * @generated
	 * @ordered
	 */
	protected static final String TEMP_DB_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTempDBName() <em>Temp DB Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTempDBName()
	 * @generated
	 * @ordered
	 */
	protected String tempDBName = TEMP_DB_NAME_EDEFAULT;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASEDatabaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_DATABASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDataTypes() {
		if (dataTypes == null) {
			dataTypes = new EObjectWithInverseResolvingEList(SybaseASEPredefinedDataType.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES, SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE);
		}
		return dataTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEncryptionKeyApplicable() {
		return encryptionKeyApplicable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncryptionKeyApplicable(boolean newEncryptionKeyApplicable) {
		boolean oldEncryptionKeyApplicable = encryptionKeyApplicable;
		encryptionKeyApplicable = newEncryptionKeyApplicable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE, oldEncryptionKeyApplicable, encryptionKeyApplicable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRoles() {
		if (roles == null) {
			roles = new EObjectResolvingEList(SybaseASERole.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ROLES);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCaches() {
		if (caches == null) {
			caches = new EObjectContainmentWithInverseEList(SybaseASECache.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES, SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE);
		}
		return caches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getWebServices() {
		if (webServices == null) {
			webServices = new EObjectContainmentWithInverseEList(SybaseASEWebService.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE);
		}
		return webServices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWebserviceApplicable() {
		return webserviceApplicable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWebserviceApplicable(boolean newWebserviceApplicable) {
		boolean oldWebserviceApplicable = webserviceApplicable;
		webserviceApplicable = newWebserviceApplicable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE, oldWebserviceApplicable, webserviceApplicable));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getSdsServer()
    {
		if (sdsServer == null) {
			sdsServer = new EDataTypeUniqueEList(String.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__SDS_SERVER);
		}
		return sdsServer;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTempDBName() {
		return tempDBName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTempDBName(String newTempDBName) {
		String oldTempDBName = tempDBName;
		tempDBName = newTempDBName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__TEMP_DB_NAME, oldTempDBName, tempDBName));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isComputedColApplicable() {
		return getVersion().compareTo(VERSION_15) >= 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isPartitionsApplicable() {
		return getVersion().compareTo(VERSION_15) >= 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isFunctionalBasedIndexMemApplicable() {
		return getVersion().compareTo(VERSION_15) >= 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES:
				return ((InternalEList)getDataTypes()).basicAdd(otherEnd, msgs);
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES:
				return ((InternalEList)getCaches()).basicAdd(otherEnd, msgs);
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES:
				return ((InternalEList)getWebServices()).basicAdd(otherEnd, msgs);
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
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES:
				return ((InternalEList)getDataTypes()).basicRemove(otherEnd, msgs);
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES:
				return ((InternalEList)getCaches()).basicRemove(otherEnd, msgs);
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES:
				return ((InternalEList)getWebServices()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES:
				return getDataTypes();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE:
				return isEncryptionKeyApplicable() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ROLES:
				return getRoles();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES:
				return getCaches();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES:
				return getWebServices();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE:
				return isWebserviceApplicable() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__SDS_SERVER:
				return getSdsServer();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__TEMP_DB_NAME:
				return getTempDBName();
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
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES:
				getDataTypes().clear();
				getDataTypes().addAll((Collection)newValue);
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE:
				setEncryptionKeyApplicable(((Boolean)newValue).booleanValue());
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection)newValue);
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES:
				getCaches().clear();
				getCaches().addAll((Collection)newValue);
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES:
				getWebServices().clear();
				getWebServices().addAll((Collection)newValue);
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE:
				setWebserviceApplicable(((Boolean)newValue).booleanValue());
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__SDS_SERVER:
				getSdsServer().clear();
				getSdsServer().addAll((Collection)newValue);
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__TEMP_DB_NAME:
				setTempDBName((String)newValue);
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
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES:
				getDataTypes().clear();
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE:
				setEncryptionKeyApplicable(ENCRYPTION_KEY_APPLICABLE_EDEFAULT);
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ROLES:
				getRoles().clear();
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES:
				getCaches().clear();
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES:
				getWebServices().clear();
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE:
				setWebserviceApplicable(WEBSERVICE_APPLICABLE_EDEFAULT);
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__SDS_SERVER:
				getSdsServer().clear();
				return;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__TEMP_DB_NAME:
				setTempDBName(TEMP_DB_NAME_EDEFAULT);
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
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES:
				return dataTypes != null && !dataTypes.isEmpty();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE:
				return encryptionKeyApplicable != ENCRYPTION_KEY_APPLICABLE_EDEFAULT;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ROLES:
				return roles != null && !roles.isEmpty();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES:
				return caches != null && !caches.isEmpty();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES:
				return webServices != null && !webServices.isEmpty();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE:
				return webserviceApplicable != WEBSERVICE_APPLICABLE_EDEFAULT;
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__SDS_SERVER:
				return sdsServer != null && !sdsServer.isEmpty();
			case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__TEMP_DB_NAME:
				return TEMP_DB_NAME_EDEFAULT == null ? tempDBName != null : !TEMP_DB_NAME_EDEFAULT.equals(tempDBName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (encryptionKeyApplicable: "); //$NON-NLS-1$
		result.append(encryptionKeyApplicable);
		result.append(", webserviceApplicable: "); //$NON-NLS-1$
		result.append(webserviceApplicable);
		result.append(", sdsServer: "); //$NON-NLS-1$
		result.append(sdsServer);
		result.append(", tempDBName: "); //$NON-NLS-1$
		result.append(tempDBName);
		result.append(')');
		return result.toString();
	}

} //SybaseASEDatabaseImpl
