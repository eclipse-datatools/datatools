/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
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
 * $Id: DataSetDesignImpl.java,v 1.13 2010/02/17 02:20:38 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DataSetQuery;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ResultSets;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Set Design</b></em>'.
 * @extends IAdaptable
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getOdaExtensionDataSetId <em>Oda Extension Data Set Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getDataSourceDesign <em>Data Source Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getPublicProperties <em>Public Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getPrivateProperties <em>Private Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getResultSets <em>Result Sets</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getPrimaryResultSetName <em>Primary Result Set Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetDesignImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataSetDesignImpl extends EObjectImpl implements DataSetDesign,
        IAdaptable
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

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
     * The default value of the '{@link #getOdaExtensionDataSetId() <em>Oda Extension Data Set Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOdaExtensionDataSetId()
     * @generated
     * @ordered
     */
    protected static final String ODA_EXTENSION_DATA_SET_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOdaExtensionDataSetId() <em>Oda Extension Data Set Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOdaExtensionDataSetId()
     * @generated
     * @ordered
     */
    protected String m_odaExtensionDataSetId = ODA_EXTENSION_DATA_SET_ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getDataSourceDesign() <em>Data Source Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataSourceDesign()
     * @generated
     * @ordered
     */
    protected DataSourceDesign m_dataSourceDesign;

    /**
     * The cached value of the '{@link #getQuery() <em>Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQuery()
     * @generated
     * @ordered
     */
    protected DataSetQuery m_query;

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
    protected Properties m_publicProperties;

    /**
     * The cached value of the '{@link #getPrivateProperties() <em>Private Properties</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrivateProperties()
     * @generated
     * @ordered
     */
    protected Properties m_privateProperties;

    /**
     * The cached value of the '{@link #getResultSets() <em>Result Sets</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResultSets()
     * @generated
     * @ordered
     */
    protected ResultSets m_resultSets;

    /**
     * The default value of the '{@link #getPrimaryResultSetName() <em>Primary Result Set Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrimaryResultSetName()
     * @generated
     * @ordered
     */
    protected static final String PRIMARY_RESULT_SET_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPrimaryResultSetName() <em>Primary Result Set Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrimaryResultSetName()
     * @generated
     * @ordered
     */
    protected String m_primaryResultSetName = PRIMARY_RESULT_SET_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected DataSetParameters m_parameters;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataSetDesignImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return DesignPackage.Literals.DATA_SET_DESIGN;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     * @generated NOT
     */
    @SuppressWarnings("unchecked")
    public Object getAdapter( Class adapter )
    {
        if( adapter.isAssignableFrom( this.getClass() ) )
            return EcoreUtil.copy( this );
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getOdaExtensionDataSourceId()
     * @generated NOT
     */
    public String getOdaExtensionDataSourceId()
    {
        DataSourceDesign dataSource = getDataSourceDesign();
        return (dataSource == null) ? null : dataSource
                .getOdaExtensionDataSourceId();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getQueryText()
     * @generated NOT
     */
    public String getQueryText()
    {
        DataSetQuery query = getQuery();
        if( query == null )
            return null;
        return query.getQueryText();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#setQueryText(java.lang.String)
     * @generated NOT
     */
    public void setQueryText( String queryText )
    {
        DataSetQuery query = getQuery();
        if( query == null )
        {
            query = DesignFactory.eINSTANCE.createDataSetQuery();
            setQuery( query );
        }
        query.setQueryText( queryText );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getPrimaryResultSet()
     * @generated NOT
     */
    public ResultSetDefinition getPrimaryResultSet()
    {
        if( getResultSets() == null
                || getResultSets().getResultSetDefinitions().size() == 0 )
            return null;

        String primaryName = getPrimaryResultSetName();
        if( primaryName == null || primaryName.length() == 0 )
        {
            // takes the first one in collection
            return (ResultSetDefinition) getResultSets()
                    .getResultSetDefinitions().get( 0 );
        }

        // try search by name
        return getResultSets().findResultSetDefinition( primaryName );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#setPrimaryResultSet(org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition)
     * @generated NOT
     */
    public void setPrimaryResultSet( ResultSetDefinition resultSetDefn )
    {
        if( resultSetDefn == null )
            return; // nothing to set

        if( getResultSets() == null )
            setResultSets( DesignFactory.eINSTANCE.createResultSets() );
        if( getResultSets().getResultSetDefinitions().isEmpty() )
            getResultSets().addResultSetDefinition( 0, resultSetDefn );
        else
            getResultSets().getResultSetDefinitions().set( 0, resultSetDefn );

        if( resultSetDefn.getName() != null ) // has name
            setPrimaryResultSetName( resultSetDefn.getName() );
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
                    DesignPackage.DATA_SET_DESIGN__NAME, oldName, m_name ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOdaExtensionDataSetId()
    {
        return m_odaExtensionDataSetId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOdaExtensionDataSetId( String newOdaExtensionDataSetId )
    {
        String oldOdaExtensionDataSetId = m_odaExtensionDataSetId;
        m_odaExtensionDataSetId = newOdaExtensionDataSetId;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__ODA_EXTENSION_DATA_SET_ID,
                    oldOdaExtensionDataSetId, m_odaExtensionDataSetId ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataSourceDesign getDataSourceDesign()
    {
        return m_dataSourceDesign;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataSourceDesign(
            DataSourceDesign newDataSourceDesign, NotificationChain msgs )
    {
        DataSourceDesign oldDataSourceDesign = m_dataSourceDesign;
        m_dataSourceDesign = newDataSourceDesign;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN,
                    oldDataSourceDesign, newDataSourceDesign );
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
    public void setDataSourceDesign( DataSourceDesign newDataSourceDesign )
    {
        if( newDataSourceDesign != m_dataSourceDesign )
        {
            NotificationChain msgs = null;
            if( m_dataSourceDesign != null )
                msgs = ((InternalEObject) m_dataSourceDesign)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN,
                                null, msgs );
            if( newDataSourceDesign != null )
                msgs = ((InternalEObject) newDataSourceDesign)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN,
                                null, msgs );
            msgs = basicSetDataSourceDesign( newDataSourceDesign, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN,
                    newDataSourceDesign, newDataSourceDesign ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataSetQuery getQuery()
    {
        return m_query;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQuery( DataSetQuery newQuery,
            NotificationChain msgs )
    {
        DataSetQuery oldQuery = m_query;
        m_query = newQuery;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET, DesignPackage.DATA_SET_DESIGN__QUERY,
                    oldQuery, newQuery );
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
    public void setQuery( DataSetQuery newQuery )
    {
        if( newQuery != m_query )
        {
            NotificationChain msgs = null;
            if( m_query != null )
                msgs = ((InternalEObject) m_query).eInverseRemove( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.DATA_SET_DESIGN__QUERY, null,
                        msgs );
            if( newQuery != null )
                msgs = ((InternalEObject) newQuery).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.DATA_SET_DESIGN__QUERY, null,
                        msgs );
            msgs = basicSetQuery( newQuery, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__QUERY, newQuery, newQuery ) );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getDisplayName()
     * @generated NOT
     */
    public String getDisplayName()
    {
        return DesignUtil.getDefaultResourceString( getDisplayNameGen() );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getDisplayNameGen()
    {
        return m_displayName;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#setDisplayName(java.lang.String)
     * @generated NOT
     */
    public void setDisplayName( String newDisplayName )
    {
        String newAttrValue = DesignUtil.addDefaultToResourceAttribute( newDisplayName, getDisplayNameGen() );
        setDisplayNameGen( newAttrValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setDisplayNameGen( String newDisplayName )
    {
        String oldDisplayName = m_displayName;
        m_displayName = newDisplayName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__DISPLAY_NAME,
                    oldDisplayName, m_displayName ) );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getDisplayNameKey()
     * @generated NOT
     */
    public String getDisplayNameKey()
    {
        return DesignUtil.getResourceKey( getDisplayNameGen() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign#setDisplayNameKey(java.lang.String)
     * @generated NOT
     */
    public void setDisplayNameKey( String newDisplayNameKey )
    {
        String newAttrValue = DesignUtil.addKeyToResourceAttribute( newDisplayNameKey, getDisplayNameGen() );
        setDisplayNameGen( newAttrValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Properties getPublicProperties()
    {
        return m_publicProperties;
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
                    DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES,
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
                                        - DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES,
                                null, msgs );
            if( newPublicProperties != null )
                msgs = ((InternalEObject) newPublicProperties)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES,
                                null, msgs );
            msgs = basicSetPublicProperties( newPublicProperties, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES,
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
                    DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES,
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
                                        - DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES,
                                null, msgs );
            if( newPrivateProperties != null )
                msgs = ((InternalEObject) newPrivateProperties)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES,
                                null, msgs );
            msgs = basicSetPrivateProperties( newPrivateProperties, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES,
                    newPrivateProperties, newPrivateProperties ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultSets getResultSets()
    {
        return m_resultSets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResultSets( ResultSets newResultSets,
            NotificationChain msgs )
    {
        ResultSets oldResultSets = m_resultSets;
        m_resultSets = newResultSets;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__RESULT_SETS, oldResultSets,
                    newResultSets );
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
    public void setResultSets( ResultSets newResultSets )
    {
        if( newResultSets != m_resultSets )
        {
            NotificationChain msgs = null;
            if( m_resultSets != null )
                msgs = ((InternalEObject) m_resultSets).eInverseRemove( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.DATA_SET_DESIGN__RESULT_SETS,
                        null, msgs );
            if( newResultSets != null )
                msgs = ((InternalEObject) newResultSets).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.DATA_SET_DESIGN__RESULT_SETS,
                        null, msgs );
            msgs = basicSetResultSets( newResultSets, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__RESULT_SETS, newResultSets,
                    newResultSets ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPrimaryResultSetName()
    {
        return m_primaryResultSetName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrimaryResultSetName( String newPrimaryResultSetName )
    {
        String oldPrimaryResultSetName = m_primaryResultSetName;
        m_primaryResultSetName = newPrimaryResultSetName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__PRIMARY_RESULT_SET_NAME,
                    oldPrimaryResultSetName, m_primaryResultSetName ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataSetParameters getParameters()
    {
        return m_parameters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParameters(
            DataSetParameters newParameters, NotificationChain msgs )
    {
        DataSetParameters oldParameters = m_parameters;
        m_parameters = newParameters;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__PARAMETERS, oldParameters,
                    newParameters );
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
    public void setParameters( DataSetParameters newParameters )
    {
        if( newParameters != m_parameters )
        {
            NotificationChain msgs = null;
            if( m_parameters != null )
                msgs = ((InternalEObject) m_parameters).eInverseRemove( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.DATA_SET_DESIGN__PARAMETERS,
                        null, msgs );
            if( newParameters != null )
                msgs = ((InternalEObject) newParameters).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.DATA_SET_DESIGN__PARAMETERS,
                        null, msgs );
            msgs = basicSetParameters( newParameters, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_SET_DESIGN__PARAMETERS, newParameters,
                    newParameters ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
            int featureID, NotificationChain msgs )
    {
        switch( featureID )
        {
        case DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN:
            return basicSetDataSourceDesign( null, msgs );
        case DesignPackage.DATA_SET_DESIGN__QUERY:
            return basicSetQuery( null, msgs );
        case DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES:
            return basicSetPublicProperties( null, msgs );
        case DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES:
            return basicSetPrivateProperties( null, msgs );
        case DesignPackage.DATA_SET_DESIGN__RESULT_SETS:
            return basicSetResultSets( null, msgs );
        case DesignPackage.DATA_SET_DESIGN__PARAMETERS:
            return basicSetParameters( null, msgs );
        }
        return super.eInverseRemove( otherEnd, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType )
    {
        switch( featureID )
        {
        case DesignPackage.DATA_SET_DESIGN__NAME:
            return getName();
        case DesignPackage.DATA_SET_DESIGN__ODA_EXTENSION_DATA_SET_ID:
            return getOdaExtensionDataSetId();
        case DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN:
            return getDataSourceDesign();
        case DesignPackage.DATA_SET_DESIGN__QUERY:
            return getQuery();
        case DesignPackage.DATA_SET_DESIGN__DISPLAY_NAME:
            return getDisplayNameGen();
        case DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES:
            return getPublicProperties();
        case DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES:
            return getPrivateProperties();
        case DesignPackage.DATA_SET_DESIGN__RESULT_SETS:
            return getResultSets();
        case DesignPackage.DATA_SET_DESIGN__PRIMARY_RESULT_SET_NAME:
            return getPrimaryResultSetName();
        case DesignPackage.DATA_SET_DESIGN__PARAMETERS:
            return getParameters();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public void eSet( int featureID, Object newValue )
    {
        switch( featureID )
        {
        case DesignPackage.DATA_SET_DESIGN__NAME:
            setName( (String) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__ODA_EXTENSION_DATA_SET_ID:
            setOdaExtensionDataSetId( (String) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN:
            setDataSourceDesign( (DataSourceDesign) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__QUERY:
            setQuery( (DataSetQuery) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__DISPLAY_NAME:
            setDisplayNameGen( (String) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES:
            setPublicProperties( (Properties) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES:
            setPrivateProperties( (Properties) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__RESULT_SETS:
            setResultSets( (ResultSets) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__PRIMARY_RESULT_SET_NAME:
            setPrimaryResultSetName( (String) newValue );
            return;
        case DesignPackage.DATA_SET_DESIGN__PARAMETERS:
            setParameters( (DataSetParameters) newValue );
            return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public void eUnset( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.DATA_SET_DESIGN__NAME:
            setName( NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_SET_DESIGN__ODA_EXTENSION_DATA_SET_ID:
            setOdaExtensionDataSetId( ODA_EXTENSION_DATA_SET_ID_EDEFAULT );
            return;
        case DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN:
            setDataSourceDesign( (DataSourceDesign) null );
            return;
        case DesignPackage.DATA_SET_DESIGN__QUERY:
            setQuery( (DataSetQuery) null );
            return;
        case DesignPackage.DATA_SET_DESIGN__DISPLAY_NAME:
            setDisplayNameGen( DISPLAY_NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES:
            setPublicProperties( (Properties) null );
            return;
        case DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES:
            setPrivateProperties( (Properties) null );
            return;
        case DesignPackage.DATA_SET_DESIGN__RESULT_SETS:
            setResultSets( (ResultSets) null );
            return;
        case DesignPackage.DATA_SET_DESIGN__PRIMARY_RESULT_SET_NAME:
            setPrimaryResultSetName( PRIMARY_RESULT_SET_NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_SET_DESIGN__PARAMETERS:
            setParameters( (DataSetParameters) null );
            return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.DATA_SET_DESIGN__NAME:
            return NAME_EDEFAULT == null ? m_name != null : !NAME_EDEFAULT
                    .equals( m_name );
        case DesignPackage.DATA_SET_DESIGN__ODA_EXTENSION_DATA_SET_ID:
            return ODA_EXTENSION_DATA_SET_ID_EDEFAULT == null ? m_odaExtensionDataSetId != null
                    : !ODA_EXTENSION_DATA_SET_ID_EDEFAULT
                            .equals( m_odaExtensionDataSetId );
        case DesignPackage.DATA_SET_DESIGN__DATA_SOURCE_DESIGN:
            return m_dataSourceDesign != null;
        case DesignPackage.DATA_SET_DESIGN__QUERY:
            return m_query != null;
        case DesignPackage.DATA_SET_DESIGN__DISPLAY_NAME:
            return DISPLAY_NAME_EDEFAULT == null ? m_displayName != null
                    : !DISPLAY_NAME_EDEFAULT.equals( m_displayName );
        case DesignPackage.DATA_SET_DESIGN__PUBLIC_PROPERTIES:
            return m_publicProperties != null;
        case DesignPackage.DATA_SET_DESIGN__PRIVATE_PROPERTIES:
            return m_privateProperties != null;
        case DesignPackage.DATA_SET_DESIGN__RESULT_SETS:
            return m_resultSets != null;
        case DesignPackage.DATA_SET_DESIGN__PRIMARY_RESULT_SET_NAME:
            return PRIMARY_RESULT_SET_NAME_EDEFAULT == null ? m_primaryResultSetName != null
                    : !PRIMARY_RESULT_SET_NAME_EDEFAULT
                            .equals( m_primaryResultSetName );
        case DesignPackage.DATA_SET_DESIGN__PARAMETERS:
            return m_parameters != null;
        }
        return super.eIsSet( featureID );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if( eIsProxy() )
            return super.toString();

        StringBuffer result = new StringBuffer( super.toString() );
        result.append( " (name: " ); //$NON-NLS-1$
        result.append( m_name );
        result.append( ", odaExtensionDataSetId: " ); //$NON-NLS-1$
        result.append( m_odaExtensionDataSetId );
        result.append( ", displayName: " ); //$NON-NLS-1$
        result.append( m_displayName );
        result.append( ", primaryResultSetName: " ); //$NON-NLS-1$
        result.append( m_primaryResultSetName );
        result.append( ')' );
        return result.toString();
    }

} //DataSetDesignImpl
