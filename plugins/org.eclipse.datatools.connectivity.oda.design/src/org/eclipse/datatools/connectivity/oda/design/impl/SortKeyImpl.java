/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.SortDirectionType;
import org.eclipse.datatools.connectivity.oda.design.SortKey;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * <p>
 * <strong>EXPERIMENTAL</strong>.
 * </p>
 * An implementation of the model object '<em><b>Sort Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#getColumnPosition <em>Column Position</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#getSortDirection <em>Sort Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.2 (DTP 1.7)
 */
public class SortKeyImpl extends EObjectImpl implements SortKey
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * @generated NOT
     */
    protected static final String EMPTY_STR = ""; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnName()
     * @generated
     * @ordered
     */
    protected static final String COLUMN_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnName()
     * @generated
     * @ordered
     */
    protected String m_columnName = COLUMN_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getColumnPosition() <em>Column Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnPosition()
     * @generated
     * @ordered
     */
    protected static final int COLUMN_POSITION_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getColumnPosition() <em>Column Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnPosition()
     * @generated
     * @ordered
     */
    protected int m_columnPosition = COLUMN_POSITION_EDEFAULT;

    /**
     * This is true if the Column Position attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_columnPositionESet;

    /**
     * The default value of the '{@link #getSortDirection() <em>Sort Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSortDirection()
     * @generated
     * @ordered
     */
    protected static final SortDirectionType SORT_DIRECTION_EDEFAULT = SortDirectionType.ASCENDING;

    /**
     * The cached value of the '{@link #getSortDirection() <em>Sort Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSortDirection()
     * @generated
     * @ordered
     */
    protected SortDirectionType m_sortDirection = SORT_DIRECTION_EDEFAULT;

    /**
     * This is true if the Sort Direction attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_sortDirectionESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SortKeyImpl()
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
        return DesignPackage.Literals.SORT_KEY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getColumnName()
    {
        return m_columnName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setColumnNameGen( String newColumnName )
    {
        String oldColumnName = m_columnName;
        m_columnName = newColumnName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.SORT_KEY__COLUMN_NAME, oldColumnName,
                    m_columnName ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.SortKey#setColumnName(java.lang.String)
     * @generated NOT
     */
    public void setColumnName( String newColumnName )
    {
        if( newColumnName == null )
            newColumnName = EMPTY_STR;  // cannot be null per design definition
        setColumnNameGen( newColumnName );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getColumnPosition()
    {
        return m_columnPosition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setColumnPositionGen( int newColumnPosition )
    {
        int oldColumnPosition = m_columnPosition;
        m_columnPosition = newColumnPosition;
        boolean oldColumnPositionESet = m_columnPositionESet;
        m_columnPositionESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.SORT_KEY__COLUMN_POSITION, oldColumnPosition,
                    m_columnPosition, !oldColumnPositionESet ) );
    }

    /* non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.SortKey#setColumnPosition(int)
     * @generated NOT
     */
    public void setColumnPosition( int newColumnPosition )
    {
        setColumnPositionGen( newColumnPosition );

        /* If a column can only be identified by position, 
         * its name may be empty.
         * Set required name field to empty by default.
         */
        if( getColumnName() == null ) // not yet set
            setColumnName( EMPTY_STR );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetColumnPosition()
    {
        int oldColumnPosition = m_columnPosition;
        boolean oldColumnPositionESet = m_columnPositionESet;
        m_columnPosition = COLUMN_POSITION_EDEFAULT;
        m_columnPositionESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.SORT_KEY__COLUMN_POSITION, oldColumnPosition,
                    COLUMN_POSITION_EDEFAULT, oldColumnPositionESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetColumnPosition()
    {
        return m_columnPositionESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SortDirectionType getSortDirection()
    {
        return m_sortDirection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSortDirection( SortDirectionType newSortDirection )
    {
        SortDirectionType oldSortDirection = m_sortDirection;
        m_sortDirection = newSortDirection == null ? SORT_DIRECTION_EDEFAULT
                : newSortDirection;
        boolean oldSortDirectionESet = m_sortDirectionESet;
        m_sortDirectionESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.SORT_KEY__SORT_DIRECTION, oldSortDirection,
                    m_sortDirection, !oldSortDirectionESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetSortDirection()
    {
        SortDirectionType oldSortDirection = m_sortDirection;
        boolean oldSortDirectionESet = m_sortDirectionESet;
        m_sortDirection = SORT_DIRECTION_EDEFAULT;
        m_sortDirectionESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.SORT_KEY__SORT_DIRECTION, oldSortDirection,
                    SORT_DIRECTION_EDEFAULT, oldSortDirectionESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetSortDirection()
    {
        return m_sortDirectionESet;
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
        case DesignPackage.SORT_KEY__COLUMN_NAME:
            return getColumnName();
        case DesignPackage.SORT_KEY__COLUMN_POSITION:
            return new Integer( getColumnPosition() );
        case DesignPackage.SORT_KEY__SORT_DIRECTION:
            return getSortDirection();
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
        case DesignPackage.SORT_KEY__COLUMN_NAME:
            setColumnName( (String) newValue );
            return;
        case DesignPackage.SORT_KEY__COLUMN_POSITION:
            setColumnPosition( ((Integer) newValue).intValue() );
            return;
        case DesignPackage.SORT_KEY__SORT_DIRECTION:
            setSortDirection( (SortDirectionType) newValue );
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
        case DesignPackage.SORT_KEY__COLUMN_NAME:
            setColumnName( COLUMN_NAME_EDEFAULT );
            return;
        case DesignPackage.SORT_KEY__COLUMN_POSITION:
            unsetColumnPosition();
            return;
        case DesignPackage.SORT_KEY__SORT_DIRECTION:
            unsetSortDirection();
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
        case DesignPackage.SORT_KEY__COLUMN_NAME:
            return COLUMN_NAME_EDEFAULT == null ? m_columnName != null
                    : !COLUMN_NAME_EDEFAULT.equals( m_columnName );
        case DesignPackage.SORT_KEY__COLUMN_POSITION:
            return isSetColumnPosition();
        case DesignPackage.SORT_KEY__SORT_DIRECTION:
            return isSetSortDirection();
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
        result.append( " (columnName: " ); //$NON-NLS-1$
        result.append( m_columnName );
        result.append( ", columnPosition: " ); //$NON-NLS-1$
        if( m_columnPositionESet )
            result.append( m_columnPosition );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", sortDirection: " ); //$NON-NLS-1$
        if( m_sortDirectionESet )
            result.append( m_sortDirection );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //SortKeyImpl
