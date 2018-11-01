/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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
 * $Id: ResultSetCriteriaImpl.java,v 1.1 2009/03/13 05:19:46 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.FilterExpression;
import org.eclipse.datatools.connectivity.oda.design.ResultSetCriteria;
import org.eclipse.datatools.connectivity.oda.design.SortKey;
import org.eclipse.datatools.connectivity.oda.design.SortSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Set Criteria</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSetCriteriaImpl#getFilterSpecification <em>Filter Specification</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSetCriteriaImpl#getRowOrdering <em>Row Ordering</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class ResultSetCriteriaImpl extends EObjectImpl implements
        ResultSetCriteria
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getFilterSpecification() <em>Filter Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFilterSpecification()
     * @generated
     * @ordered
     */
    protected FilterExpression m_filterSpecification;

    /**
     * The cached value of the '{@link #getRowOrdering() <em>Row Ordering</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRowOrdering()
     * @generated
     * @ordered
     */
    protected SortSpecification m_rowOrdering;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResultSetCriteriaImpl()
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
        return DesignPackage.Literals.RESULT_SET_CRITERIA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterExpression getFilterSpecification()
    {
        return m_filterSpecification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFilterSpecification(
            FilterExpression newFilterSpecification, NotificationChain msgs )
    {
        FilterExpression oldFilterSpecification = m_filterSpecification;
        m_filterSpecification = newFilterSpecification;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION,
                    oldFilterSpecification, newFilterSpecification );
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
    public void setFilterSpecification( FilterExpression newFilterSpecification )
    {
        if( newFilterSpecification != m_filterSpecification )
        {
            NotificationChain msgs = null;
            if( m_filterSpecification != null )
                msgs = ((InternalEObject) m_filterSpecification)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION,
                                null, msgs );
            if( newFilterSpecification != null )
                msgs = ((InternalEObject) newFilterSpecification)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION,
                                null, msgs );
            msgs = basicSetFilterSpecification( newFilterSpecification, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION,
                    newFilterSpecification, newFilterSpecification ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SortSpecification getRowOrdering()
    {
        return m_rowOrdering;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRowOrdering(
            SortSpecification newRowOrdering, NotificationChain msgs )
    {
        SortSpecification oldRowOrdering = m_rowOrdering;
        m_rowOrdering = newRowOrdering;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING,
                    oldRowOrdering, newRowOrdering );
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
    public void setRowOrdering( SortSpecification newRowOrdering )
    {
        if( newRowOrdering != m_rowOrdering )
        {
            NotificationChain msgs = null;
            if( m_rowOrdering != null )
                msgs = ((InternalEObject) m_rowOrdering)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING,
                                null, msgs );
            if( newRowOrdering != null )
                msgs = ((InternalEObject) newRowOrdering)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING,
                                null, msgs );
            msgs = basicSetRowOrdering( newRowOrdering, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING,
                    newRowOrdering, newRowOrdering ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSetCriteria#addRowSortKey(org.eclipse.datatools.connectivity.oda.design.SortKey)
     * @generated NOT
     */
    public void addRowSortKey( SortKey sortKey )
    {
        if( getRowOrdering() == null )
            setRowOrdering( DesignFactory.eINSTANCE.createSortSpecification() );
        getRowOrdering().getSortKeys().add( sortKey );
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
        case DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION:
            return basicSetFilterSpecification( null, msgs );
        case DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING:
            return basicSetRowOrdering( null, msgs );
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
        case DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION:
            return getFilterSpecification();
        case DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING:
            return getRowOrdering();
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
        case DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION:
            setFilterSpecification( (FilterExpression) newValue );
            return;
        case DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING:
            setRowOrdering( (SortSpecification) newValue );
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
        case DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION:
            setFilterSpecification( (FilterExpression) null );
            return;
        case DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING:
            setRowOrdering( (SortSpecification) null );
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
        case DesignPackage.RESULT_SET_CRITERIA__FILTER_SPECIFICATION:
            return m_filterSpecification != null;
        case DesignPackage.RESULT_SET_CRITERIA__ROW_ORDERING:
            return m_rowOrdering != null;
        }
        return super.eIsSet( featureID );
    }

} //ResultSetCriteriaImpl
