/**
 *************************************************************************
 * Copyright (c) 2005, 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *
 *************************************************************************
 *
 * $Id: DataSourceDesignImpl.java,v 1.16 2010/03/13 02:00:28 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.io.File;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Source Design</b></em>'.
 * @extends IAdaptable
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getOdaExtensionId <em>Oda Extension Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getEffectiveOdaExtensionId <em>Effective Oda Extension Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getOdaExtensionDataSourceId <em>Oda Extension Data Source Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getPublicProperties <em>Public Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getPrivateProperties <em>Private Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getLinkedProfileName <em>Linked Profile Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getLinkedProfileStoreFilePath <em>Linked Profile Store File Path</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getHostResourceIdentifiers <em>Host Resource Identifiers</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getResourceFile <em>Resource File</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataSourceDesignImpl extends EObjectImpl implements DataSourceDesign, IAdaptable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/*
	 * @generated NOT
	 */
	private static final String RESOURCE_FILE_SUFFIX = ".properties"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getOdaExtensionId() <em>Oda Extension Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOdaExtensionId()
	 * @generated
	 * @ordered
	 */
	protected static final String ODA_EXTENSION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOdaExtensionId() <em>Oda Extension Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOdaExtensionId()
	 * @generated
	 * @ordered
	 */
	protected String odaExtensionId = ODA_EXTENSION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getEffectiveOdaExtensionId() <em>Effective Oda Extension Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEffectiveOdaExtensionId()
	 * @generated
	 * @ordered
	 */
	protected static final String EFFECTIVE_ODA_EXTENSION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEffectiveOdaExtensionId() <em>Effective Oda Extension Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEffectiveOdaExtensionId()
	 * @generated
	 * @ordered
	 */
	protected String effectiveOdaExtensionId = EFFECTIVE_ODA_EXTENSION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getOdaExtensionDataSourceId() <em>Oda Extension Data Source Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOdaExtensionDataSourceId()
	 * @generated
	 * @ordered
	 */
	protected static final String ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOdaExtensionDataSourceId() <em>Oda Extension Data Source Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOdaExtensionDataSourceId()
	 * @generated
	 * @ordered
	 */
	protected String odaExtensionDataSourceId = ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPublicProperties() <em>Public Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublicProperties()
	 * @generated
	 * @ordered
	 */
	protected Properties publicProperties;

	/**
	 * The cached value of the '{@link #getPrivateProperties() <em>Private Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivateProperties()
	 * @generated
	 * @ordered
	 */
	protected Properties privateProperties;

	/**
	 * The default value of the '{@link #getLinkedProfileName() <em>Linked Profile Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedProfileName()
	 * @generated
	 * @ordered
	 */
	protected static final String LINKED_PROFILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLinkedProfileName() <em>Linked Profile Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedProfileName()
	 * @generated
	 * @ordered
	 */
	protected String linkedProfileName = LINKED_PROFILE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLinkedProfileStoreFilePath() <em>Linked Profile Store File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedProfileStoreFilePath()
	 * @generated
	 * @ordered
	 */
	protected static final String LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLinkedProfileStoreFilePath() <em>Linked Profile Store File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedProfileStoreFilePath()
	 * @generated
	 * @ordered
	 */
	protected String linkedProfileStoreFilePath = LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHostResourceIdentifiers() <em>Host Resource Identifiers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostResourceIdentifiers()
	 * @generated
	 * @ordered
	 */
	protected ResourceIdentifiers hostResourceIdentifiers;

	/**
	 * The default value of the '{@link #getResourceFile() <em>Resource File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceFile()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceFile() <em>Resource File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceFile()
	 * @generated
	 * @ordered
	 */
	protected String resourceFile = RESOURCE_FILE_EDEFAULT;

	/**
	 * property name for storing linked profile instance's name
	 * TODO - share common constants defined by core ODA plugin which introduces new plugin dependency
	 * @generated NOT
	 */
	private static final String CONN_PROFILE_NAME_PROP = "OdaConnProfileName"; //$NON-NLS-1$

	/**
	 * property name for storing linked profile store file path
	 * TODO - share common constants defined by core ODA plugin
	 * @generated NOT
	 */
	private static final String CONN_PROFILE_STORE_FILE_PATH_PROP = "OdaConnProfileStorePath"; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataSourceDesignImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.DATA_SOURCE_DESIGN;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 * @generated NOT
	 */
	@Override
	public Object getAdapter(Class adapter) {
		// TODO - supports adapter to ConnectionProfile class
		if (adapter.isAssignableFrom(this.getClass())) {
			return EcoreUtil.copy(this);
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SOURCE_DESIGN__NAME, oldName,
					name));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOdaExtensionId() {
		return odaExtensionId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOdaExtensionId(String newOdaExtensionId) {
		String oldOdaExtensionId = odaExtensionId;
		odaExtensionId = newOdaExtensionId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID,
					oldOdaExtensionId, odaExtensionId));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getEffectiveOdaExtensionId()
	 * @generated NOT
	 */
	@Override
	public String getEffectiveOdaExtensionId() {
		String assignedValue = getEffectiveOdaExtensionIdGen();
		if (assignedValue != null) {
			return assignedValue;
		}

		// null, default to be the same as the ODA extension id
		return getOdaExtensionId();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String getEffectiveOdaExtensionIdGen() {
		return effectiveOdaExtensionId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEffectiveOdaExtensionId(String newEffectiveOdaExtensionId) {
		String oldEffectiveOdaExtensionId = effectiveOdaExtensionId;
		effectiveOdaExtensionId = newEffectiveOdaExtensionId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.DATA_SOURCE_DESIGN__EFFECTIVE_ODA_EXTENSION_ID, oldEffectiveOdaExtensionId,
					effectiveOdaExtensionId));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getOdaExtensionDataSourceId()
	 * @generated NOT
	 */
	@Override
	public String getOdaExtensionDataSourceId() {
		String assignedValue = getOdaExtensionDataSourceIdGen();
		if (assignedValue != null) {
			return assignedValue;
		}

		// null, default to be the same as the ODA extension id
		return getOdaExtensionId();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String getOdaExtensionDataSourceIdGen() {
		return odaExtensionDataSourceId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOdaExtensionDataSourceId(String newOdaExtensionDataSourceId) {
		String oldOdaExtensionDataSourceId = odaExtensionDataSourceId;
		odaExtensionDataSourceId = newOdaExtensionDataSourceId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID, oldOdaExtensionDataSourceId,
					odaExtensionDataSourceId));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getDisplayName()
	 * @generated NOT
	 */
	@Override
	public String getDisplayName() {
		return DesignUtil.getDefaultResourceString(getDisplayNameGen());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String getDisplayNameGen() {
		return displayName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#setDisplayName(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		String newAttrValue = DesignUtil.addDefaultToResourceAttribute(newDisplayName, getDisplayNameGen());
		setDisplayNameGen(newAttrValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setDisplayNameGen(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME,
					oldDisplayName, displayName));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getDisplayNameKey()
	 * @generated NOT
	 */
	@Override
	public String getDisplayNameKey() {
		return DesignUtil.getResourceKey(getDisplayNameGen());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#setDisplayNameKey(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setDisplayNameKey(String newDisplayNameKey) {
		String newAttrValue = DesignUtil.addKeyToResourceAttribute(newDisplayNameKey, getDisplayNameGen());
		setDisplayNameGen(newAttrValue);
	}

	/**
	 * Returns a collection of public properties.
	 * @generated NOT
	 */
	@Override
	public Properties getPublicProperties() {
		// a linked profile attribute should now be stored as a public property;
		// for backward compatibility of previously persisted object,
		// migrate the values from deprecated member variables, if exists
		String profileName = getLinkedProfileNameGen();
		if (profileName != null) {
			setLinkedProfileName(profileName); // move to public property
		}

		String filePath = getLinkedProfileStoreFilePathGen();
		if (filePath != null) {
			setLinkedProfileStoreFilePath(filePath); // move to public property
		}

		return getPublicPropertiesGen();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Properties getPublicPropertiesGen() {
		return publicProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPublicProperties(Properties newPublicProperties, NotificationChain msgs) {
		Properties oldPublicProperties = publicProperties;
		publicProperties = newPublicProperties;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES, oldPublicProperties, newPublicProperties);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPublicProperties(Properties newPublicProperties) {
		if (newPublicProperties != publicProperties) {
			NotificationChain msgs = null;
			if (publicProperties != null) {
				msgs = ((InternalEObject) publicProperties).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES, null, msgs);
			}
			if (newPublicProperties != null) {
				msgs = ((InternalEObject) newPublicProperties).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES, null, msgs);
			}
			msgs = basicSetPublicProperties(newPublicProperties, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES,
					newPublicProperties, newPublicProperties));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Properties getPrivateProperties() {
		return privateProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrivateProperties(Properties newPrivateProperties, NotificationChain msgs) {
		Properties oldPrivateProperties = privateProperties;
		privateProperties = newPrivateProperties;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES, oldPrivateProperties, newPrivateProperties);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrivateProperties(Properties newPrivateProperties) {
		if (newPrivateProperties != privateProperties) {
			NotificationChain msgs = null;
			if (privateProperties != null) {
				msgs = ((InternalEObject) privateProperties).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES, null, msgs);
			}
			if (newPrivateProperties != null) {
				msgs = ((InternalEObject) newPrivateProperties).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES, null, msgs);
			}
			msgs = basicSetPrivateProperties(newPrivateProperties, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES,
					newPrivateProperties, newPrivateProperties));
		}
	}

	/**
	 * Returns the name of an associated profile instance.
	 * May be null if no profile instance is linked to the data source design.
	 * @generated NOT
	 */
	@Override
	public String getLinkedProfileName() {
		// a linked profile attribute should now be stored as a property;
		// for backward compatibility of previously persisted object,
		// use the one in deprecated member variable, if exists
		String profileName = getLinkedProfileNameGen();
		if (profileName != null) {
			return profileName;
		}
		return getLinkedProfileNameInProperties();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String getLinkedProfileNameGen() {
		return linkedProfileName;
	}

	/**
	 * Returns the name of an associated profile instance, as specified
	 * in the public properties collection.
	 * @generated NOT
	 */
	protected String getLinkedProfileNameInProperties() {
		Properties props = getPublicPropertiesGen();
		if (props == null) {
			return null;
		}

		return props.getProperty(CONN_PROFILE_NAME_PROP);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#setLinkedProfileName(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setLinkedProfileName(String newLinkedProfileName) {
		// a linked profile attribute should now be stored as a property;
		// clear any value in the deprecated member variable
		setLinkedProfileNameGen(null);
		setLinkedProfileNameAsProperty(newLinkedProfileName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setLinkedProfileNameGen(String newLinkedProfileName) {
		String oldLinkedProfileName = linkedProfileName;
		linkedProfileName = newLinkedProfileName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME,
					oldLinkedProfileName, linkedProfileName));
		}
	}

	/**
	 * Assigns the specified profile instance name as a data source public property.
	 * Overrides existing property value, if exists.
	 * @param newLinkedProfileName
	 * @generated NOT
	 */
	protected void setLinkedProfileNameAsProperty(String newLinkedProfileName) {
		Properties publicProps = getPublicPropertiesGen();
		if (publicProps == null) {
			publicProps = DesignFactory.eINSTANCE.createProperties();
			basicSetPublicProperties(publicProps, null);
		}

		// override existing property value, if exists
		publicProps.setProperty(CONN_PROFILE_NAME_PROP, newLinkedProfileName);
	}

	/**
	 * Returns the file path of an associated profile store.
	 * May be null if no user-defined profile store is specified the data source design.
	 * @generated NOT
	 */
	@Override
	public String getLinkedProfileStoreFilePath() {
		// a linked profile attribute should now be stored as a property;
		// for backward compatibility of previously persisted object,
		// use the one in deprecated member variable, if exists
		String filePath = getLinkedProfileStoreFilePathGen();
		if (filePath != null) {
			return filePath;
		}
		return getLinkedProfileStoreFilePathInProperties();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String getLinkedProfileStoreFilePathGen() {
		return linkedProfileStoreFilePath;
	}

	/**
	 * Returns the file path of an associated profile store, as specified
	 * in the public properties collection.
	 * @generated NOT
	 */
	protected String getLinkedProfileStoreFilePathInProperties() {
		Properties props = getPublicPropertiesGen();
		if (props == null) {
			return null;
		}

		return props.getProperty(CONN_PROFILE_STORE_FILE_PATH_PROP);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#setLinkedProfileStoreFilePath(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setLinkedProfileStoreFilePath(String newLinkedProfileStoreFilePath) {
		// a linked profile attribute should now be stored as a property;
		// clear any value in the deprecated member variable
		setLinkedProfileStoreFilePathGen(null);
		setLinkedProfileStoreFilePathAsProperty(newLinkedProfileStoreFilePath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setLinkedProfileStoreFilePathGen(String newLinkedProfileStoreFilePath) {
		String oldLinkedProfileStoreFilePath = linkedProfileStoreFilePath;
		linkedProfileStoreFilePath = newLinkedProfileStoreFilePath;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH, oldLinkedProfileStoreFilePath,
					linkedProfileStoreFilePath));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceIdentifiers getHostResourceIdentifiers() {
		return hostResourceIdentifiers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHostResourceIdentifiers(ResourceIdentifiers newHostResourceIdentifiers,
			NotificationChain msgs) {
		ResourceIdentifiers oldHostResourceIdentifiers = hostResourceIdentifiers;
		hostResourceIdentifiers = newHostResourceIdentifiers;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS, oldHostResourceIdentifiers,
					newHostResourceIdentifiers);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHostResourceIdentifiers(ResourceIdentifiers newHostResourceIdentifiers) {
		if (newHostResourceIdentifiers != hostResourceIdentifiers) {
			NotificationChain msgs = null;
			if (hostResourceIdentifiers != null) {
				msgs = ((InternalEObject) hostResourceIdentifiers).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS, null,
						msgs);
			}
			if (newHostResourceIdentifiers != null) {
				msgs = ((InternalEObject) newHostResourceIdentifiers).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS, null,
						msgs);
			}
			msgs = basicSetHostResourceIdentifiers(newHostResourceIdentifiers, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS, newHostResourceIdentifiers,
					newHostResourceIdentifiers));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getResourceFile() {
		return resourceFile;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#setResourceFile(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setResourceFile(String newResourceFile) {
		if (newResourceFile != null) {
			if (!newResourceFile.endsWith(RESOURCE_FILE_SUFFIX)) {
				throw new IllegalArgumentException(
						NLS.bind(Messages.design_invalidResourceFileName, newResourceFile));
			}
		}
		setResourceFileGen(newResourceFile);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setResourceFileGen(String newResourceFile) {
		String oldResourceFile = resourceFile;
		resourceFile = newResourceFile;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SOURCE_DESIGN__RESOURCE_FILE,
					oldResourceFile, resourceFile));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
			return basicSetPublicProperties(null, msgs);
		case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
			return basicSetPrivateProperties(null, msgs);
		case DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS:
			return basicSetHostResourceIdentifiers(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.DATA_SOURCE_DESIGN__NAME:
			return getName();
		case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID:
			return getOdaExtensionId();
		case DesignPackage.DATA_SOURCE_DESIGN__EFFECTIVE_ODA_EXTENSION_ID:
			return getEffectiveOdaExtensionId();
		case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID:
			return getOdaExtensionDataSourceId();
		case DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME:
			return getDisplayNameGen();
		case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
			return getPublicProperties();
		case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
			return getPrivateProperties();
		case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME:
			return getLinkedProfileName();
		case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH:
			return getLinkedProfileStoreFilePath();
		case DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS:
			return getHostResourceIdentifiers();
		case DesignPackage.DATA_SOURCE_DESIGN__RESOURCE_FILE:
			return getResourceFile();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DesignPackage.DATA_SOURCE_DESIGN__NAME:
			setName((String) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID:
			setOdaExtensionId((String) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__EFFECTIVE_ODA_EXTENSION_ID:
			setEffectiveOdaExtensionId((String) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID:
			setOdaExtensionDataSourceId((String) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME:
			setDisplayNameGen((String) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
			setPublicProperties((Properties) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
			setPrivateProperties((Properties) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME:
			setLinkedProfileName((String) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH:
			setLinkedProfileStoreFilePath((String) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS:
			setHostResourceIdentifiers((ResourceIdentifiers) newValue);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__RESOURCE_FILE:
			setResourceFile((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case DesignPackage.DATA_SOURCE_DESIGN__NAME:
			setName(NAME_EDEFAULT);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID:
			setOdaExtensionId(ODA_EXTENSION_ID_EDEFAULT);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__EFFECTIVE_ODA_EXTENSION_ID:
			setEffectiveOdaExtensionId(EFFECTIVE_ODA_EXTENSION_ID_EDEFAULT);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID:
			setOdaExtensionDataSourceId(ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME:
			setDisplayNameGen(DISPLAY_NAME_EDEFAULT);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
			setPublicProperties((Properties) null);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
			setPrivateProperties((Properties) null);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME:
			setLinkedProfileName(LINKED_PROFILE_NAME_EDEFAULT);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH:
			setLinkedProfileStoreFilePath(LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS:
			setHostResourceIdentifiers((ResourceIdentifiers) null);
			return;
		case DesignPackage.DATA_SOURCE_DESIGN__RESOURCE_FILE:
			setResourceFile(RESOURCE_FILE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case DesignPackage.DATA_SOURCE_DESIGN__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID:
			return ODA_EXTENSION_ID_EDEFAULT == null ? odaExtensionId != null
					: !ODA_EXTENSION_ID_EDEFAULT.equals(odaExtensionId);
		case DesignPackage.DATA_SOURCE_DESIGN__EFFECTIVE_ODA_EXTENSION_ID:
			return EFFECTIVE_ODA_EXTENSION_ID_EDEFAULT == null ? effectiveOdaExtensionId != null
					: !EFFECTIVE_ODA_EXTENSION_ID_EDEFAULT.equals(effectiveOdaExtensionId);
		case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID:
			return ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT == null ? odaExtensionDataSourceId != null
					: !ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT.equals(odaExtensionDataSourceId);
		case DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME:
			return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
		case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
			return publicProperties != null;
		case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
			return privateProperties != null;
		case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME:
			return LINKED_PROFILE_NAME_EDEFAULT == null ? linkedProfileName != null
					: !LINKED_PROFILE_NAME_EDEFAULT.equals(linkedProfileName);
		case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH:
			return LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT == null ? linkedProfileStoreFilePath != null
					: !LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT.equals(linkedProfileStoreFilePath);
		case DesignPackage.DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS:
			return hostResourceIdentifiers != null;
		case DesignPackage.DATA_SOURCE_DESIGN__RESOURCE_FILE:
			return RESOURCE_FILE_EDEFAULT == null ? resourceFile != null : !RESOURCE_FILE_EDEFAULT.equals(resourceFile);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * Assigns the specified profile store's file path as a data source public property.
	 * Overrides existing property value, if exists.
	 * @param newLinkedProfileStoreFilePath
	 * @generated NOT
	 */
	protected void setLinkedProfileStoreFilePathAsProperty(String newLinkedProfileStoreFilePath) {
		Properties publicProps = getPublicPropertiesGen();
		if (publicProps == null) {
			publicProps = DesignFactory.eINSTANCE.createProperties();
			basicSetPublicProperties(publicProps, null);
		}

		// override existing property value, if exists
		publicProps.setProperty(CONN_PROFILE_STORE_FILE_PATH_PROP, newLinkedProfileStoreFilePath);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getLinkedProfileStoreFile()
	 */
	@Override
	public File getLinkedProfileStoreFile() {
		String storeFilePath = getLinkedProfileStoreFilePath();
		return DesignUtil.convertPathToResourceFile(storeFilePath, getHostResourceIdentifiers());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#setLinkedProfileStoreFile(java.io.File)
	 */
	@Override
	public void setLinkedProfileStoreFile(File storageFile) {
		String filePath = DesignUtil.convertFileToPath(storageFile);
		setLinkedProfileStoreFilePath(filePath);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#hasLinkToProfile()
	 */
	@Override
	public boolean hasLinkToProfile() {
		String profileName = getLinkedProfileName();
		return (profileName != null && profileName.trim().length() > 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", odaExtensionId: "); //$NON-NLS-1$
		result.append(odaExtensionId);
		result.append(", effectiveOdaExtensionId: "); //$NON-NLS-1$
		result.append(effectiveOdaExtensionId);
		result.append(", odaExtensionDataSourceId: "); //$NON-NLS-1$
		result.append(odaExtensionDataSourceId);
		result.append(", displayName: "); //$NON-NLS-1$
		result.append(displayName);
		result.append(", linkedProfileName: "); //$NON-NLS-1$
		result.append(linkedProfileName);
		result.append(", linkedProfileStoreFilePath: "); //$NON-NLS-1$
		result.append(linkedProfileStoreFilePath);
		result.append(", resourceFile: "); //$NON-NLS-1$
		result.append(resourceFile);
		result.append(')');
		return result.toString();
	}

} //DataSourceDesignImpl
