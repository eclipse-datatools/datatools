/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DataSourceDesignImpl.java,v 1.8 2006/05/23 02:04:33 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Source Design</b></em>'.
 * @extends IAdaptable
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getOdaExtensionId <em>Oda Extension Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getOdaExtensionDataSourceId <em>Oda Extension Data Source Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getPublicProperties <em>Public Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getPrivateProperties <em>Private Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getLinkedProfileName <em>Linked Profile Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSourceDesignImpl#getLinkedProfileStoreFilePath <em>Linked Profile Store File Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataSourceDesignImpl extends EObjectImpl 
    implements DataSourceDesign, IAdaptable
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

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
    protected String m_name = NAME_EDEFAULT;

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
    protected String m_odaExtensionId = ODA_EXTENSION_ID_EDEFAULT;

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
    protected String m_odaExtensionDataSourceId = ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT;

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
    protected String m_displayName = DISPLAY_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getPublicProperties() <em>Public Properties</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPublicProperties()
     * @generated
     * @ordered
     */
    protected Properties m_publicProperties = null;

    /**
     * The cached value of the '{@link #getPrivateProperties() <em>Private Properties</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrivateProperties()
     * @generated
     * @ordered
     */
    protected Properties m_privateProperties = null;

    /**
     * The default value of the '{@link #getLinkedProfileName() <em>Linked Profile Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLinkedProfileName()
     * @generated
     * @ordered
     */
    protected static final String LINKED_PROFILE_NAME_EDEFAULT = null; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getLinkedProfileName() <em>Linked Profile Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLinkedProfileName()
     * @generated
     * @ordered
     */
    protected String m_linkedProfileName = LINKED_PROFILE_NAME_EDEFAULT;

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
    protected String m_linkedProfileStoreFilePath = LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT;

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
    protected DataSourceDesignImpl()
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
        return DesignPackage.eINSTANCE.getDataSourceDesign();
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     * @generated NOT
     */
    public Object getAdapter( Class adapter )
    {
        // TODO - supports adapter to ConnectionProfile class
        if( adapter.isAssignableFrom( this.getClass() ) )
            return EcoreUtil.copy( this );
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName( String newName )
    {
        String oldName = m_name;
        m_name = newName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__NAME, oldName, m_name ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOdaExtensionId()
    {
        return m_odaExtensionId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOdaExtensionId( String newOdaExtensionId )
    {
        String oldOdaExtensionId = m_odaExtensionId;
        m_odaExtensionId = newOdaExtensionId;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID,
                    oldOdaExtensionId, m_odaExtensionId ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getOdaExtensionDataSourceId()
     */
    public String getOdaExtensionDataSourceId()
    {
        String assignedValue = getOdaExtensionDataSourceIdGen();
        if( assignedValue != null )
            return assignedValue;

        // null, default to be the same as the ODA extension id 
        return getOdaExtensionId();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOdaExtensionDataSourceIdGen()
    {
        return m_odaExtensionDataSourceId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOdaExtensionDataSourceId( String newOdaExtensionDataSourceId )
    {
        String oldOdaExtensionDataSourceId = m_odaExtensionDataSourceId;
        m_odaExtensionDataSourceId = newOdaExtensionDataSourceId;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID,
                    oldOdaExtensionDataSourceId, m_odaExtensionDataSourceId ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDisplayName()
    {
        return m_displayName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisplayName( String newDisplayName )
    {
        String oldDisplayName = m_displayName;
        m_displayName = newDisplayName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME,
                    oldDisplayName, m_displayName ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Properties getPublicPropertiesGen()
    {
        return m_publicProperties;
    }
    
    /**
     * Returns a collection of public properties.
     * @generated NOT
     */
    public Properties getPublicProperties()
    {
        Properties publicProps = getPublicPropertiesGen();
        
        // add linked profile info to properties, if exists
        String profileName = getLinkedProfileNameGen();
        if( profileName != null )   // linked profile instance is specified
        {
            // override existing property value, if exists
            publicProps.setProperty( CONN_PROFILE_NAME_PROP, profileName );
            
            String filePath = this.getLinkedProfileStoreFilePathGen();
            if( filePath != null )  // optional attribute
                publicProps.setProperty( CONN_PROFILE_STORE_FILE_PATH_PROP, filePath );
        }           
        
        return publicProps;
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPublicProperties(
            Properties newPublicProperties, NotificationChain msgs )
    {
        Properties oldPublicProperties = m_publicProperties;
        m_publicProperties = newPublicProperties;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES,
                    oldPublicProperties, newPublicProperties );
            if( msgs == null )
                msgs = notification;
            else
                msgs.add( notification );
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPublicProperties( Properties newPublicProperties )
    {
        if( newPublicProperties != m_publicProperties )
        {
            NotificationChain msgs = null;
            if( m_publicProperties != null )
                msgs = ((InternalEObject) m_publicProperties)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES,
                                null, msgs );
            if( newPublicProperties != null )
                msgs = ((InternalEObject) newPublicProperties)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES,
                                null, msgs );
            msgs = basicSetPublicProperties( newPublicProperties, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES,
                    newPublicProperties, newPublicProperties ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Properties getPrivateProperties()
    {
        return m_privateProperties;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPrivateProperties(
            Properties newPrivateProperties, NotificationChain msgs )
    {
        Properties oldPrivateProperties = m_privateProperties;
        m_privateProperties = newPrivateProperties;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES,
                    oldPrivateProperties, newPrivateProperties );
            if( msgs == null )
                msgs = notification;
            else
                msgs.add( notification );
        }
        return msgs;
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrivateProperties( Properties newPrivateProperties )
    {
        if( newPrivateProperties != m_privateProperties )
        {
            NotificationChain msgs = null;
            if( m_privateProperties != null )
                msgs = ((InternalEObject) m_privateProperties)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES,
                                null, msgs );
            if( newPrivateProperties != null )
                msgs = ((InternalEObject) newPrivateProperties)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES,
                                null, msgs );
            msgs = basicSetPrivateProperties( newPrivateProperties, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES,
                    newPrivateProperties, newPrivateProperties ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getLinkedProfileNameGen()
    {
        return m_linkedProfileName;
    }

    /**
     * Returns the name of an associated profile instance.
     * May be null if no profile instance is linked to the data source design.
     * @generated NOT
     */
    public String getLinkedProfileName()
    {
        // use the one assigned by setter, if exists
        String profileName = getLinkedProfileNameGen();
        if( profileName != null )
            return profileName;
        return getLinkedProfileNameInProperties();
    }

    /**
     * Returns the name of an associated profile instance, as specified
     * in the public properties collection.
     * @generated NOT
     */
    protected String getLinkedProfileNameInProperties()
    {
        Properties props = getPublicPropertiesGen();
        if( props == null )
            return null;
        
        return props.getProperty( CONN_PROFILE_NAME_PROP );             
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLinkedProfileName( String newLinkedProfileName )
    {
        String oldLinkedProfileName = m_linkedProfileName;
        m_linkedProfileName = newLinkedProfileName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME,
                    oldLinkedProfileName, m_linkedProfileName ) );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getLinkedProfileStoreFilePathGen()
    {
        return m_linkedProfileStoreFilePath;
    }

    /**
     * Returns the file path of an associated profile store.
     * May be null if no user-defined profile store is specified the data source design.
     * @generated NOT
     */
    public String getLinkedProfileStoreFilePath()
    {
        // use the one assigned by setter, if exists
        String filePath = getLinkedProfileStoreFilePathGen();          
        if( filePath != null )
            return filePath;
        return getLinkedProfileStoreFilePathInProperties();      
    }

    /**
     * Returns the file path of an associated profile store, as specified
     * in the public properties collection.
     * @generated NOT
     */
    protected String getLinkedProfileStoreFilePathInProperties()
    {
        Properties props = getPublicPropertiesGen();
        if( props == null )
            return null;
        
        return props.getProperty( CONN_PROFILE_STORE_FILE_PATH_PROP );             
    }
   
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLinkedProfileStoreFilePath(
            String newLinkedProfileStoreFilePath )
    {
        String oldLinkedProfileStoreFilePath = m_linkedProfileStoreFilePath;
        m_linkedProfileStoreFilePath = newLinkedProfileStoreFilePath;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH,
                    oldLinkedProfileStoreFilePath, m_linkedProfileStoreFilePath ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getLinkedProfileStoreFile()
     */
    public File getLinkedProfileStoreFile()
    {
        String storeFilePath = getLinkedProfileStoreFilePath();
        if( storeFilePath == null || storeFilePath.length() == 0 )
            return null;

        return new File( storeFilePath );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#setLinkedProfileStoreFile(java.io.File)
     */
    public void setLinkedProfileStoreFile( File storageFile )
    {
        if( storageFile == null )
        {
            setLinkedProfileStoreFilePath( null );
            return; // done
        }

        String filePath = null;
        try
        {
            filePath = FileLocator.toFileURL( storageFile.toURI().toURL() )
                    .getPath();
        }
        catch( MalformedURLException e )
        {
            // ignore invalid file
        }
        catch( IOException e )
        {
            // ignore invalid file
        }

        setLinkedProfileStoreFilePath( filePath );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#hasLinkToProfile()
     */
    public boolean hasLinkToProfile()
    {
        String profileName = getLinkedProfileName();
        return ( profileName != null && 
                 profileName.length() > 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
            int featureID, Class baseClass, NotificationChain msgs )
    {
        if( featureID >= 0 )
        {
            switch( eDerivedStructuralFeatureID( featureID, baseClass ) )
            {
            case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
                return basicSetPublicProperties( null, msgs );
            case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
                return basicSetPrivateProperties( null, msgs );
            default:
                return eDynamicInverseRemove( otherEnd, featureID, baseClass,
                        msgs );
            }
        }
        return eBasicSetContainer( null, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet( EStructuralFeature eFeature, boolean resolve )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.DATA_SOURCE_DESIGN__NAME:
            return getName();
        case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID:
            return getOdaExtensionId();
        case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID:
            return getOdaExtensionDataSourceId();
        case DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME:
            return getDisplayName();
        case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
            return getPublicProperties();
        case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
            return getPrivateProperties();
        case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME:
            return getLinkedProfileName();
        case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH:
            return getLinkedProfileStoreFilePath();
        }
        return eDynamicGet( eFeature, resolve );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet( EStructuralFeature eFeature, Object newValue )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.DATA_SOURCE_DESIGN__NAME:
            setName( (String) newValue );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID:
            setOdaExtensionId( (String) newValue );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID:
            setOdaExtensionDataSourceId( (String) newValue );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME:
            setDisplayName( (String) newValue );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
            setPublicProperties( (Properties) newValue );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
            setPrivateProperties( (Properties) newValue );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME:
            setLinkedProfileName( (String) newValue );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH:
            setLinkedProfileStoreFilePath( (String) newValue );
            return;
        }
        eDynamicSet( eFeature, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset( EStructuralFeature eFeature )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.DATA_SOURCE_DESIGN__NAME:
            setName( NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID:
            setOdaExtensionId( ODA_EXTENSION_ID_EDEFAULT );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID:
            setOdaExtensionDataSourceId( ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME:
            setDisplayName( DISPLAY_NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
            setPublicProperties( (Properties) null );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
            setPrivateProperties( (Properties) null );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME:
            setLinkedProfileName( LINKED_PROFILE_NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH:
            setLinkedProfileStoreFilePath( LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT );
            return;
        }
        eDynamicUnset( eFeature );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet( EStructuralFeature eFeature )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.DATA_SOURCE_DESIGN__NAME:
            return NAME_EDEFAULT == null ? m_name != null : !NAME_EDEFAULT
                    .equals( m_name );
        case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_ID:
            return ODA_EXTENSION_ID_EDEFAULT == null ? m_odaExtensionId != null
                    : !ODA_EXTENSION_ID_EDEFAULT.equals( m_odaExtensionId );
        case DesignPackage.DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID:
            return ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT == null ? m_odaExtensionDataSourceId != null
                    : !ODA_EXTENSION_DATA_SOURCE_ID_EDEFAULT
                            .equals( m_odaExtensionDataSourceId );
        case DesignPackage.DATA_SOURCE_DESIGN__DISPLAY_NAME:
            return DISPLAY_NAME_EDEFAULT == null ? m_displayName != null
                    : !DISPLAY_NAME_EDEFAULT.equals( m_displayName );
        case DesignPackage.DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES:
            return m_publicProperties != null;
        case DesignPackage.DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES:
            return m_privateProperties != null;
        case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME:
            return LINKED_PROFILE_NAME_EDEFAULT == null ? m_linkedProfileName != null
                    : !LINKED_PROFILE_NAME_EDEFAULT
                            .equals( m_linkedProfileName );
        case DesignPackage.DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH:
            return LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT == null ? m_linkedProfileStoreFilePath != null
                    : !LINKED_PROFILE_STORE_FILE_PATH_EDEFAULT
                            .equals( m_linkedProfileStoreFilePath );
        }
        return eDynamicIsSet( eFeature );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString()
    {
        if( eIsProxy() )
            return super.toString();

        StringBuffer result = new StringBuffer( super.toString() );
        result.append( " (name: " ); //$NON-NLS-1$
        result.append( m_name );
        result.append( ", odaExtensionId: " ); //$NON-NLS-1$
        result.append( m_odaExtensionId );
        result.append( ", odaExtensionDataSourceId: " ); //$NON-NLS-1$
        result.append( m_odaExtensionDataSourceId );
        result.append( ", displayName: " ); //$NON-NLS-1$
        result.append( m_displayName );
        result.append( ", linkedProfileName: " ); //$NON-NLS-1$
        result.append( m_linkedProfileName );
        result.append( ", linkedProfileStoreFilePath: " ); //$NON-NLS-1$
        result.append( m_linkedProfileStoreFilePath );
        result.append( ')' );
        return result.toString();
    }

} //DataSourceDesignImpl
