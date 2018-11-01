/**
 *************************************************************************
 * Copyright (c) 2009, 2010 Actuate Corporation.
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
 * $Id: SortKeyImpl.java,v 1.4 2010/03/17 00:34:13 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.NullOrderingType;
import org.eclipse.datatools.connectivity.oda.design.SortDirectionType;
import org.eclipse.datatools.connectivity.oda.design.SortKey;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sort Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#getColumnIdentifier <em>Column Identifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#getColumnPosition <em>Column Position</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#getSortDirection <em>Sort Direction</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#getNullValueOrdering <em>Null Value Ordering</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.SortKeyImpl#isOptional <em>Optional</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class SortKeyImpl extends EObjectImpl implements SortKey
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getColumnIdentifier() <em>Column Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnIdentifier()
     * @generated
     * @ordered
     * @since 3.3.2
     */
    protected DataElementIdentifier m_columnIdentifier;

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
     * @deprecated  since 3.3.2; replaced by m_columnIdentifier
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
     * @deprecated  since 3.3.2; replaced by m_columnIdentifier
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
     * The default value of the '{@link #getNullValueOrdering() <em>Null Value Ordering</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullValueOrdering()
     * @generated
     * @ordered
     */
    protected static final NullOrderingType NULL_VALUE_ORDERING_EDEFAULT = NullOrderingType.UNKNOWN;

    /**
     * The cached value of the '{@link #getNullValueOrdering() <em>Null Value Ordering</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullValueOrdering()
     * @generated
     * @ordered
     */
    protected NullOrderingType m_nullValueOrdering = NULL_VALUE_ORDERING_EDEFAULT;

    /**
     * This is true if the Null Value Ordering attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_nullValueOrderingESet;

    /**
     * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected static final boolean OPTIONAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected boolean m_optional = OPTIONAL_EDEFAULT;

    /**
     * This is true if the Optional attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_optionalESet;

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
    public DataElementIdentifier getColumnIdentifier()
    {
        return m_columnIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetColumnIdentifier(
            DataElementIdentifier newColumnIdentifier, NotificationChain msgs )
    {
        DataElementIdentifier oldColumnIdentifier = m_columnIdentifier;
        m_columnIdentifier = newColumnIdentifier;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.SORT_KEY__COLUMN_IDENTIFIER,
                    oldColumnIdentifier, newColumnIdentifier );
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
    public void setColumnIdentifier( DataElementIdentifier newColumnIdentifier )
    {
        if( newColumnIdentifier != m_columnIdentifier )
        {
            NotificationChain msgs = null;
            if( m_columnIdentifier != null )
                msgs = ((InternalEObject) m_columnIdentifier).eInverseRemove(
                        this, EOPPOSITE_FEATURE_BASE
                                - DesignPackage.SORT_KEY__COLUMN_IDENTIFIER,
                        null, msgs );
            if( newColumnIdentifier != null )
                msgs = ((InternalEObject) newColumnIdentifier).eInverseAdd(
                        this, EOPPOSITE_FEATURE_BASE
                                - DesignPackage.SORT_KEY__COLUMN_IDENTIFIER,
                        null, msgs );
            msgs = basicSetColumnIdentifier( newColumnIdentifier, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.SORT_KEY__COLUMN_IDENTIFIER,
                    newColumnIdentifier, newColumnIdentifier ) );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnName()
     * @generated NOT
     */
    public String getColumnName()
    {
        // the name attribute should now be stored in the associated identifier;
        // for backward compatibility of previously persisted object,
        // use the one in deprecated member variable, if exists
        String elementName = getColumnNameGen();
        if( elementName != COLUMN_NAME_EDEFAULT )
            return elementName;
        return getColumnNameInIdentifier();
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getColumnNameGen()
    {
        return m_columnName;
    }

    /**
     * Returns the column name stored in the associated identifier.
     * @generated NOT
     */
    protected String getColumnNameInIdentifier()
    {
        DataElementIdentifier identifier = getColumnIdentifier();
        if( identifier == null )
            return COLUMN_NAME_EDEFAULT;

        return identifier.getName();
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
        // the name attribute should now be stored in the associated identifier;
        // clear any existing value in the deprecated member variable
        if( getColumnNameGen() != COLUMN_NAME_EDEFAULT )
            setColumnNameGen( COLUMN_NAME_EDEFAULT );
        setColumnNameInIdentifier( newColumnName );
    }
    
    /**
     * Set the column name in the associated identifier.
     * @generated NOT
     */
    protected void setColumnNameInIdentifier( String newName )
    {
        DataElementIdentifier identifier = getColumnIdentifier();
        if( identifier == null )
        {
            identifier = DesignFactory.eINSTANCE.createDataElementIdentifier();
            setColumnIdentifier( identifier );
        }

        identifier.setName( newName );
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnPosition()
     * @generated NOT
     */
    public int getColumnPosition()
    {
        // the position attribute should now be stored in the associated identifier;
        // for backward compatibility of previously persisted object,
        // use the one in deprecated member variable, if exists
        if( isSetColumnPosition() )
            return getColumnPositionGen();
        return getColumnPositionInIdentifier();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected int getColumnPositionGen()
    {
        return m_columnPosition;
    }
    
    /**
     * Returns the column position stored in the associated identifier.
     * @generated NOT
     */
    protected int getColumnPositionInIdentifier()
    {
        DataElementIdentifier identifier = getColumnIdentifier();
        if( identifier == null )
            return COLUMN_POSITION_EDEFAULT;

        return identifier.getPosition();
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
        // the position attribute should now be stored in the associated identifier;
        // clear any existing value in the deprecated member variable
        if( isSetColumnPosition() )
            unsetColumnPosition();
        setColumnPositionInIdentifier( newColumnPosition );
    }
    
    /**
     * Sets the column position in the associated identifier.
     * @param newPosition
     * @generated NOT
     */
    protected void setColumnPositionInIdentifier( int newPosition )
    {
        DataElementIdentifier identifier = getColumnIdentifier();
        if( identifier == null )
        {
            identifier = DesignFactory.eINSTANCE.createDataElementIdentifier();
            setColumnIdentifier( identifier );
        }

        identifier.setPosition( newPosition );
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
    public NullOrderingType getNullValueOrdering()
    {
        return m_nullValueOrdering;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNullValueOrdering( NullOrderingType newNullValueOrdering )
    {
        NullOrderingType oldNullValueOrdering = m_nullValueOrdering;
        m_nullValueOrdering = newNullValueOrdering == null ? NULL_VALUE_ORDERING_EDEFAULT
                : newNullValueOrdering;
        boolean oldNullValueOrderingESet = m_nullValueOrderingESet;
        m_nullValueOrderingESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.SORT_KEY__NULL_VALUE_ORDERING,
                    oldNullValueOrdering, m_nullValueOrdering,
                    !oldNullValueOrderingESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetNullValueOrdering()
    {
        NullOrderingType oldNullValueOrdering = m_nullValueOrdering;
        boolean oldNullValueOrderingESet = m_nullValueOrderingESet;
        m_nullValueOrdering = NULL_VALUE_ORDERING_EDEFAULT;
        m_nullValueOrderingESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.SORT_KEY__NULL_VALUE_ORDERING,
                    oldNullValueOrdering, NULL_VALUE_ORDERING_EDEFAULT,
                    oldNullValueOrderingESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetNullValueOrdering()
    {
        return m_nullValueOrderingESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isOptional()
    {
        return m_optional;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOptional( boolean newOptional )
    {
        boolean oldOptional = m_optional;
        m_optional = newOptional;
        boolean oldOptionalESet = m_optionalESet;
        m_optionalESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.SORT_KEY__OPTIONAL, oldOptional, m_optional,
                    !oldOptionalESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetOptional()
    {
        boolean oldOptional = m_optional;
        boolean oldOptionalESet = m_optionalESet;
        m_optional = OPTIONAL_EDEFAULT;
        m_optionalESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.SORT_KEY__OPTIONAL, oldOptional,
                    OPTIONAL_EDEFAULT, oldOptionalESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetOptional()
    {
        return m_optionalESet;
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
        case DesignPackage.SORT_KEY__COLUMN_IDENTIFIER:
            return basicSetColumnIdentifier( null, msgs );
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
        case DesignPackage.SORT_KEY__COLUMN_IDENTIFIER:
            return getColumnIdentifier();
        case DesignPackage.SORT_KEY__COLUMN_NAME:
            return getColumnName();
        case DesignPackage.SORT_KEY__COLUMN_POSITION:
            return new Integer( getColumnPosition() );
        case DesignPackage.SORT_KEY__SORT_DIRECTION:
            return getSortDirection();
        case DesignPackage.SORT_KEY__NULL_VALUE_ORDERING:
            return getNullValueOrdering();
        case DesignPackage.SORT_KEY__OPTIONAL:
            return isOptional() ? Boolean.TRUE : Boolean.FALSE;
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
        case DesignPackage.SORT_KEY__COLUMN_IDENTIFIER:
            setColumnIdentifier( (DataElementIdentifier) newValue );
            return;
        case DesignPackage.SORT_KEY__COLUMN_NAME:
            setColumnName( (String) newValue );
            return;
        case DesignPackage.SORT_KEY__COLUMN_POSITION:
            setColumnPosition( ((Integer) newValue).intValue() );
            return;
        case DesignPackage.SORT_KEY__SORT_DIRECTION:
            setSortDirection( (SortDirectionType) newValue );
            return;
        case DesignPackage.SORT_KEY__NULL_VALUE_ORDERING:
            setNullValueOrdering( (NullOrderingType) newValue );
            return;
        case DesignPackage.SORT_KEY__OPTIONAL:
            setOptional( ((Boolean) newValue).booleanValue() );
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
        case DesignPackage.SORT_KEY__COLUMN_IDENTIFIER:
            setColumnIdentifier( (DataElementIdentifier) null );
            return;
        case DesignPackage.SORT_KEY__COLUMN_NAME:
            setColumnName( COLUMN_NAME_EDEFAULT );
            return;
        case DesignPackage.SORT_KEY__COLUMN_POSITION:
            unsetColumnPosition();
            return;
        case DesignPackage.SORT_KEY__SORT_DIRECTION:
            unsetSortDirection();
            return;
        case DesignPackage.SORT_KEY__NULL_VALUE_ORDERING:
            unsetNullValueOrdering();
            return;
        case DesignPackage.SORT_KEY__OPTIONAL:
            unsetOptional();
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
        case DesignPackage.SORT_KEY__COLUMN_IDENTIFIER:
            return m_columnIdentifier != null;
        case DesignPackage.SORT_KEY__COLUMN_NAME:
            return COLUMN_NAME_EDEFAULT == null ? m_columnName != null
                    : !COLUMN_NAME_EDEFAULT.equals( m_columnName );
        case DesignPackage.SORT_KEY__COLUMN_POSITION:
            return isSetColumnPosition();
        case DesignPackage.SORT_KEY__SORT_DIRECTION:
            return isSetSortDirection();
        case DesignPackage.SORT_KEY__NULL_VALUE_ORDERING:
            return isSetNullValueOrdering();
        case DesignPackage.SORT_KEY__OPTIONAL:
            return isSetOptional();
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
        result.append( ", nullValueOrdering: " ); //$NON-NLS-1$
        if( m_nullValueOrderingESet )
            result.append( m_nullValueOrdering );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", optional: " ); //$NON-NLS-1$
        if( m_optionalESet )
            result.append( m_optional );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //SortKeyImpl
