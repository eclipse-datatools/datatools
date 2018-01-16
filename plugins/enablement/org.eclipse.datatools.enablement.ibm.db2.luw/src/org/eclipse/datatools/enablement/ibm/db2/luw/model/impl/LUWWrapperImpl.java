/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wrapper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#getLibrary <em>Library</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#isFenced <em>Fenced</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#getWrapperType <em>Wrapper Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#getDiscoveredLibraries <em>Discovered Libraries</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#getServers <em>Servers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#getLUWDatabase <em>LUW Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl#getOptions <em>Options</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LUWWrapperImpl extends SQLObjectImpl implements LUWWrapper {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLibrary() <em>Library</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibrary()
	 * @generated
	 * @ordered
	 */
	protected static final String LIBRARY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLibrary() <em>Library</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibrary()
	 * @generated
	 * @ordered
	 */
	protected String library = LIBRARY_EDEFAULT;

	/**
	 * The default value of the '{@link #isFenced() <em>Fenced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFenced()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FENCED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isFenced() <em>Fenced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFenced()
	 * @generated
	 * @ordered
	 */
	protected boolean fenced = FENCED_EDEFAULT;

	/**
	 * The default value of the '{@link #getWrapperType() <em>Wrapper Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWrapperType()
	 * @generated
	 * @ordered
	 */
	protected static final WrapperType WRAPPER_TYPE_EDEFAULT = WrapperType.RELATIONAL_LITERAL;

	/**
	 * The cached value of the '{@link #getWrapperType() <em>Wrapper Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWrapperType()
	 * @generated
	 * @ordered
	 */
	protected WrapperType wrapperType = WRAPPER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataSource() <em>Data Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataSource()
	 * @generated
	 * @ordered
	 */
	protected static final LUWFederatedDataSource DATA_SOURCE_EDEFAULT = LUWFederatedDataSource.DB2_LITERAL;

	/**
	 * The cached value of the '{@link #getDataSource() <em>Data Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataSource()
	 * @generated
	 * @ordered
	 */
	protected LUWFederatedDataSource dataSource = DATA_SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDiscoveredLibraries() <em>Discovered Libraries</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiscoveredLibraries()
	 * @generated
	 * @ordered
	 */
	protected EList discoveredLibraries;

	/**
	 * The cached value of the '{@link #getServers() <em>Servers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServers()
	 * @generated
	 * @ordered
	 */
	protected EList servers;

	/**
	 * The cached value of the '{@link #getLUWDatabase() <em>LUW Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLUWDatabase()
	 * @generated
	 * @ordered
	 */
	protected LUWDatabase luwDatabase;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected EList options;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWWrapperImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_WRAPPER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_WRAPPER__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLibrary() {
		return library;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLibrary(String newLibrary) {
		String oldLibrary = library;
		library = newLibrary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_WRAPPER__LIBRARY, oldLibrary, library));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFenced() {
		return fenced;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFenced(boolean newFenced) {
		boolean oldFenced = fenced;
		fenced = newFenced;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_WRAPPER__FENCED, oldFenced, fenced));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WrapperType getWrapperType() {
		return wrapperType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWrapperType(WrapperType newWrapperType) {
		WrapperType oldWrapperType = wrapperType;
		wrapperType = newWrapperType == null ? WRAPPER_TYPE_EDEFAULT : newWrapperType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_WRAPPER__WRAPPER_TYPE, oldWrapperType, wrapperType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWFederatedDataSource getDataSource() {
		return dataSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataSource(LUWFederatedDataSource newDataSource) {
		LUWFederatedDataSource oldDataSource = dataSource;
		dataSource = newDataSource == null ? DATA_SOURCE_EDEFAULT : newDataSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_WRAPPER__DATA_SOURCE, oldDataSource, dataSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDiscoveredLibraries() {
		if (discoveredLibraries == null) {
			discoveredLibraries = new EDataTypeUniqueEList(String.class, this, LUWPackage.LUW_WRAPPER__DISCOVERED_LIBRARIES);
		}
		return discoveredLibraries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getServers() {
		if (servers == null) {
			servers = new EObjectWithInverseResolvingEList(LUWServer.class, this, LUWPackage.LUW_WRAPPER__SERVERS, LUWPackage.LUW_SERVER__WRAPPER);
		}
		return servers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase getLUWDatabase() {
		if (luwDatabase != null && luwDatabase.eIsProxy()) {
			InternalEObject oldLUWDatabase = (InternalEObject)luwDatabase;
			luwDatabase = (LUWDatabase)eResolveProxy(oldLUWDatabase);
			if (luwDatabase != oldLUWDatabase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_WRAPPER__LUW_DATABASE, oldLUWDatabase, luwDatabase));
			}
		}
		return luwDatabase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase basicGetLUWDatabase() {
		return luwDatabase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLUWDatabase(LUWDatabase newLUWDatabase, NotificationChain msgs) {
		LUWDatabase oldLUWDatabase = luwDatabase;
		luwDatabase = newLUWDatabase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_WRAPPER__LUW_DATABASE, oldLUWDatabase, newLUWDatabase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLUWDatabase(LUWDatabase newLUWDatabase) {
		if (newLUWDatabase != luwDatabase) {
			NotificationChain msgs = null;
			if (luwDatabase != null)
				msgs = ((InternalEObject)luwDatabase).eInverseRemove(this, LUWPackage.LUW_DATABASE__WRAPPERS, LUWDatabase.class, msgs);
			if (newLUWDatabase != null)
				msgs = ((InternalEObject)newLUWDatabase).eInverseAdd(this, LUWPackage.LUW_DATABASE__WRAPPERS, LUWDatabase.class, msgs);
			msgs = basicSetLUWDatabase(newLUWDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_WRAPPER__LUW_DATABASE, newLUWDatabase, newLUWDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList(LUWOption.class, this, LUWPackage.LUW_WRAPPER__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_WRAPPER__SERVERS:
				return ((InternalEList)getServers()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_WRAPPER__LUW_DATABASE:
				if (luwDatabase != null)
					msgs = ((InternalEObject)luwDatabase).eInverseRemove(this, LUWPackage.LUW_DATABASE__WRAPPERS, LUWDatabase.class, msgs);
				return basicSetLUWDatabase((LUWDatabase)otherEnd, msgs);
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
			case LUWPackage.LUW_WRAPPER__SERVERS:
				return ((InternalEList)getServers()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_WRAPPER__LUW_DATABASE:
				return basicSetLUWDatabase(null, msgs);
			case LUWPackage.LUW_WRAPPER__OPTIONS:
				return ((InternalEList)getOptions()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_WRAPPER__VERSION:
				return getVersion();
			case LUWPackage.LUW_WRAPPER__LIBRARY:
				return getLibrary();
			case LUWPackage.LUW_WRAPPER__FENCED:
				return isFenced() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_WRAPPER__WRAPPER_TYPE:
				return getWrapperType();
			case LUWPackage.LUW_WRAPPER__DATA_SOURCE:
				return getDataSource();
			case LUWPackage.LUW_WRAPPER__DISCOVERED_LIBRARIES:
				return getDiscoveredLibraries();
			case LUWPackage.LUW_WRAPPER__SERVERS:
				return getServers();
			case LUWPackage.LUW_WRAPPER__LUW_DATABASE:
				if (resolve) return getLUWDatabase();
				return basicGetLUWDatabase();
			case LUWPackage.LUW_WRAPPER__OPTIONS:
				return getOptions();
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
			case LUWPackage.LUW_WRAPPER__VERSION:
				setVersion((String)newValue);
				return;
			case LUWPackage.LUW_WRAPPER__LIBRARY:
				setLibrary((String)newValue);
				return;
			case LUWPackage.LUW_WRAPPER__FENCED:
				setFenced(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_WRAPPER__WRAPPER_TYPE:
				setWrapperType((WrapperType)newValue);
				return;
			case LUWPackage.LUW_WRAPPER__DATA_SOURCE:
				setDataSource((LUWFederatedDataSource)newValue);
				return;
			case LUWPackage.LUW_WRAPPER__DISCOVERED_LIBRARIES:
				getDiscoveredLibraries().clear();
				getDiscoveredLibraries().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_WRAPPER__SERVERS:
				getServers().clear();
				getServers().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_WRAPPER__LUW_DATABASE:
				setLUWDatabase((LUWDatabase)newValue);
				return;
			case LUWPackage.LUW_WRAPPER__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection)newValue);
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
			case LUWPackage.LUW_WRAPPER__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case LUWPackage.LUW_WRAPPER__LIBRARY:
				setLibrary(LIBRARY_EDEFAULT);
				return;
			case LUWPackage.LUW_WRAPPER__FENCED:
				setFenced(FENCED_EDEFAULT);
				return;
			case LUWPackage.LUW_WRAPPER__WRAPPER_TYPE:
				setWrapperType(WRAPPER_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_WRAPPER__DATA_SOURCE:
				setDataSource(DATA_SOURCE_EDEFAULT);
				return;
			case LUWPackage.LUW_WRAPPER__DISCOVERED_LIBRARIES:
				getDiscoveredLibraries().clear();
				return;
			case LUWPackage.LUW_WRAPPER__SERVERS:
				getServers().clear();
				return;
			case LUWPackage.LUW_WRAPPER__LUW_DATABASE:
				setLUWDatabase((LUWDatabase)null);
				return;
			case LUWPackage.LUW_WRAPPER__OPTIONS:
				getOptions().clear();
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
			case LUWPackage.LUW_WRAPPER__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case LUWPackage.LUW_WRAPPER__LIBRARY:
				return LIBRARY_EDEFAULT == null ? library != null : !LIBRARY_EDEFAULT.equals(library);
			case LUWPackage.LUW_WRAPPER__FENCED:
				return fenced != FENCED_EDEFAULT;
			case LUWPackage.LUW_WRAPPER__WRAPPER_TYPE:
				return wrapperType != WRAPPER_TYPE_EDEFAULT;
			case LUWPackage.LUW_WRAPPER__DATA_SOURCE:
				return dataSource != DATA_SOURCE_EDEFAULT;
			case LUWPackage.LUW_WRAPPER__DISCOVERED_LIBRARIES:
				return discoveredLibraries != null && !discoveredLibraries.isEmpty();
			case LUWPackage.LUW_WRAPPER__SERVERS:
				return servers != null && !servers.isEmpty();
			case LUWPackage.LUW_WRAPPER__LUW_DATABASE:
				return luwDatabase != null;
			case LUWPackage.LUW_WRAPPER__OPTIONS:
				return options != null && !options.isEmpty();
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
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", library: "); //$NON-NLS-1$
		result.append(library);
		result.append(", fenced: "); //$NON-NLS-1$
		result.append(fenced);
		result.append(", wrapperType: "); //$NON-NLS-1$
		result.append(wrapperType);
		result.append(", dataSource: "); //$NON-NLS-1$
		result.append(dataSource);
		result.append(", DiscoveredLibraries: "); //$NON-NLS-1$
		result.append(discoveredLibraries);
		result.append(')');
		return result.toString();
	}

} //LUWWrapperImpl
