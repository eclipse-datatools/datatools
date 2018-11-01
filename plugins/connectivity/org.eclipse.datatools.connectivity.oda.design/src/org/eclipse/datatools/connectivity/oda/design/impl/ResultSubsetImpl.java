/**
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
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
 * $Id: ResultSubsetImpl.java,v 1.1 2010/10/15 05:41:37 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier;
import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ResultSubset;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Subset</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSubsetImpl#getDataSetDesign <em>Data Set Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSubsetImpl#getResultSetName <em>Result Set Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSubsetImpl#getColumnIdentifiers <em>Column Identifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultSubsetImpl extends EObjectImpl implements ResultSubset
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDataSetDesign() <em>Data Set Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataSetDesign()
     * @generated
     * @ordered
     */
    protected DataSetDesign m_dataSetDesign;

    /**
     * The default value of the '{@link #getResultSetName() <em>Result Set Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResultSetName()
     * @generated
     * @ordered
     */
    protected static final String RESULT_SET_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getResultSetName() <em>Result Set Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResultSetName()
     * @generated
     * @ordered
     */
    protected String m_resultSetName = RESULT_SET_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getColumnIdentifiers() <em>Column Identifiers</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnIdentifiers()
     * @generated
     * @ordered
     */
    protected DataElementIdentifiers m_columnIdentifiers;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResultSubsetImpl()
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
        return DesignPackage.Literals.RESULT_SUBSET;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataSetDesign getDataSetDesign()
    {
        return m_dataSetDesign;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataSetDesign(
            DataSetDesign newDataSetDesign, NotificationChain msgs )
    {
        DataSetDesign oldDataSetDesign = m_dataSetDesign;
        m_dataSetDesign = newDataSetDesign;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN,
                    oldDataSetDesign, newDataSetDesign );
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
    public void setDataSetDesign( DataSetDesign newDataSetDesign )
    {
        if( newDataSetDesign != m_dataSetDesign )
        {
            NotificationChain msgs = null;
            if( m_dataSetDesign != null )
                msgs = ((InternalEObject) m_dataSetDesign).eInverseRemove(
                        this, EOPPOSITE_FEATURE_BASE
                                - DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN,
                        null, msgs );
            if( newDataSetDesign != null )
                msgs = ((InternalEObject) newDataSetDesign).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN,
                        null, msgs );
            msgs = basicSetDataSetDesign( newDataSetDesign, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN,
                    newDataSetDesign, newDataSetDesign ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getResultSetName()
    {
        return m_resultSetName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResultSetName( String newResultSetName )
    {
        String oldResultSetName = m_resultSetName;
        m_resultSetName = newResultSetName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.RESULT_SUBSET__RESULT_SET_NAME,
                    oldResultSetName, m_resultSetName ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataElementIdentifiers getColumnIdentifiers()
    {
        return m_columnIdentifiers;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetColumnIdentifiers(
            DataElementIdentifiers newColumnIdentifiers, NotificationChain msgs )
    {
        DataElementIdentifiers oldColumnIdentifiers = m_columnIdentifiers;
        m_columnIdentifiers = newColumnIdentifiers;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS,
                    oldColumnIdentifiers, newColumnIdentifiers );
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
    public void setColumnIdentifiers(
            DataElementIdentifiers newColumnIdentifiers )
    {
        if( newColumnIdentifiers != m_columnIdentifiers )
        {
            NotificationChain msgs = null;
            if( m_columnIdentifiers != null )
                msgs = ((InternalEObject) m_columnIdentifiers)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS,
                                null, msgs );
            if( newColumnIdentifiers != null )
                msgs = ((InternalEObject) newColumnIdentifiers)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS,
                                null, msgs );
            msgs = basicSetColumnIdentifiers( newColumnIdentifiers, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS,
                    newColumnIdentifiers, newColumnIdentifiers ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSubset#addColumnIdentifier(java.lang.String)
     * @generated NOT
     */
    public void addColumnIdentifier( String columnName )
    {
        addColumnIdentifier( columnName, 0 );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSubset#addColumnIdentifier(java.lang.String, int)
     * @generated NOT
     */
    public void addColumnIdentifier( String columnName, int columnPosition )
    {
        DataElementIdentifier columnIdentifier = DesignFactory.eINSTANCE.createDataElementIdentifier();
        columnIdentifier.setName( columnName );
        if( columnPosition > 0 )
            columnIdentifier.setPosition( columnPosition );
        addColumnIdentifier( columnIdentifier );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSubset#addColumnIdentifier(org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier)
     * @generated NOT
     */
    public void addColumnIdentifier( DataElementIdentifier columnIdentifier )
    {
        if( columnIdentifier == null )
            return; // nothing to add
        
        DataElementIdentifiers columns = getColumnIdentifiers();
        if( columns == null )
        {
            columns = DesignFactory.eINSTANCE.createDataElementIdentifiers();
            setColumnIdentifiers( columns );
        }
        columns.getIdentifiers().add( columnIdentifier );
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
        case DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN:
            return basicSetDataSetDesign( null, msgs );
        case DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS:
            return basicSetColumnIdentifiers( null, msgs );
        }
        return super.eInverseRemove( otherEnd, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType )
    {
        switch( featureID )
        {
        case DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN:
            return getDataSetDesign();
        case DesignPackage.RESULT_SUBSET__RESULT_SET_NAME:
            return getResultSetName();
        case DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS:
            return getColumnIdentifiers();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet( int featureID, Object newValue )
    {
        switch( featureID )
        {
        case DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN:
            setDataSetDesign( (DataSetDesign) newValue );
            return;
        case DesignPackage.RESULT_SUBSET__RESULT_SET_NAME:
            setResultSetName( (String) newValue );
            return;
        case DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS:
            setColumnIdentifiers( (DataElementIdentifiers) newValue );
            return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN:
            setDataSetDesign( (DataSetDesign) null );
            return;
        case DesignPackage.RESULT_SUBSET__RESULT_SET_NAME:
            setResultSetName( RESULT_SET_NAME_EDEFAULT );
            return;
        case DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS:
            setColumnIdentifiers( (DataElementIdentifiers) null );
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
        case DesignPackage.RESULT_SUBSET__DATA_SET_DESIGN:
            return m_dataSetDesign != null;
        case DesignPackage.RESULT_SUBSET__RESULT_SET_NAME:
            return RESULT_SET_NAME_EDEFAULT == null ? m_resultSetName != null
                    : !RESULT_SET_NAME_EDEFAULT.equals( m_resultSetName );
        case DesignPackage.RESULT_SUBSET__COLUMN_IDENTIFIERS:
            return m_columnIdentifiers != null;
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
        result.append( " (resultSetName: " ); //$NON-NLS-1$
        result.append( m_resultSetName );
        result.append( ')' );
        return result.toString();
    }

} //ResultSubsetImpl
