/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DynamicValuesQueryImpl.java,v 1.3 2009/04/24 03:20:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Values Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DynamicValuesQueryImpl#getDataSetDesign <em>Data Set Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DynamicValuesQueryImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DynamicValuesQueryImpl#getValueColumnIdentifier <em>Value Column Identifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DynamicValuesQueryImpl#getValueColumn <em>Value Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DynamicValuesQueryImpl#getDisplayNameColumn <em>Display Name Column</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicValuesQueryImpl extends EObjectImpl implements
        DynamicValuesQuery
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

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
     * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    protected static final boolean ENABLED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    protected boolean m_enabled = ENABLED_EDEFAULT;

    /**
     * This is true if the Enabled attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_enabledESet;

    /**
     * The cached value of the '{@link #getValueColumnIdentifier() <em>Value Column Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueColumnIdentifier()
     * @generated
     * @ordered
     * @since 3.3.2
     */
    protected DataElementIdentifier m_valueColumnIdentifier;

    /**
     * The default value of the '{@link #getValueColumn() <em>Value Column</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueColumn()
     * @generated
     * @ordered
     */
    protected static final String VALUE_COLUMN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValueColumn() <em>Value Column</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueColumn()
     * @generated
     * @ordered
     * @deprecated  since 3.3.2; replaced by m_valueColumnIdentifier
     */
    protected String m_valueColumn = VALUE_COLUMN_EDEFAULT;

    /**
     * The default value of the '{@link #getDisplayNameColumn() <em>Display Name Column</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayNameColumn()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_NAME_COLUMN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayNameColumn() <em>Display Name Column</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayNameColumn()
     * @generated
     * @ordered
     */
    protected String m_displayNameColumn = DISPLAY_NAME_COLUMN_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DynamicValuesQueryImpl()
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
        return DesignPackage.Literals.DYNAMIC_VALUES_QUERY;
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
                    DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN,
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
                msgs = ((InternalEObject) m_dataSetDesign)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN,
                                null, msgs );
            if( newDataSetDesign != null )
                msgs = ((InternalEObject) newDataSetDesign)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN,
                                null, msgs );
            msgs = basicSetDataSetDesign( newDataSetDesign, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN,
                    newDataSetDesign, newDataSetDesign ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isEnabled()
    {
        return m_enabled;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEnabled( boolean newEnabled )
    {
        boolean oldEnabled = m_enabled;
        m_enabled = newEnabled;
        boolean oldEnabledESet = m_enabledESet;
        m_enabledESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DYNAMIC_VALUES_QUERY__ENABLED, oldEnabled,
                    m_enabled, !oldEnabledESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetEnabled()
    {
        boolean oldEnabled = m_enabled;
        boolean oldEnabledESet = m_enabledESet;
        m_enabled = ENABLED_EDEFAULT;
        m_enabledESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.DYNAMIC_VALUES_QUERY__ENABLED, oldEnabled,
                    ENABLED_EDEFAULT, oldEnabledESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetEnabled()
    {
        return m_enabledESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataElementIdentifier getValueColumnIdentifier()
    {
        return m_valueColumnIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValueColumnIdentifier(
            DataElementIdentifier newValueColumnIdentifier,
            NotificationChain msgs )
    {
        DataElementIdentifier oldValueColumnIdentifier = m_valueColumnIdentifier;
        m_valueColumnIdentifier = newValueColumnIdentifier;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER,
                    oldValueColumnIdentifier, newValueColumnIdentifier );
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
    public void setValueColumnIdentifier(
            DataElementIdentifier newValueColumnIdentifier )
    {
        if( newValueColumnIdentifier != m_valueColumnIdentifier )
        {
            NotificationChain msgs = null;
            if( m_valueColumnIdentifier != null )
                msgs = ((InternalEObject) m_valueColumnIdentifier)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER,
                                null, msgs );
            if( newValueColumnIdentifier != null )
                msgs = ((InternalEObject) newValueColumnIdentifier)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER,
                                null, msgs );
            msgs = basicSetValueColumnIdentifier( newValueColumnIdentifier,
                    msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER,
                    newValueColumnIdentifier, newValueColumnIdentifier ) );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#getValueColumn()
     * @generated NOT
     */
    public String getValueColumn()
    {
        // the name attribute should now be stored in the associated identifier;
        // for backward compatibility of previously persisted object,
        // use the one in deprecated member variable, if exists
        String elementName = getValueColumnGen();
        if( elementName != VALUE_COLUMN_EDEFAULT )
            return elementName;
        return getValueColumnNameInIdentifier();
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getValueColumnGen()
    {
        return m_valueColumn;
    }

    /**
     * Returns the value column name stored in the associated identifier.
     * @generated NOT
     */
    protected String getValueColumnNameInIdentifier()
    {
        DataElementIdentifier identifier = getValueColumnIdentifier();
        if( identifier == null )
            return VALUE_COLUMN_EDEFAULT;

        return identifier.getName();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#setValueColumn(java.lang.String)
     * @generated NOT
     */
    public void setValueColumn( String newValueColumn )
    {
        // the name attribute should now be stored in the associated identifier;
        // clear any existing value in the deprecated member variable
        if( getValueColumnGen() != VALUE_COLUMN_EDEFAULT )
            setValueColumnGen( VALUE_COLUMN_EDEFAULT );
        setValueColumnNameInIdentifier( newValueColumn );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setValueColumnGen( String newValueColumn )
    {
        String oldValueColumn = m_valueColumn;
        m_valueColumn = newValueColumn;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN,
                    oldValueColumn, m_valueColumn ) );
    }
    
    /**
     * Set the value column name in the associated identifier.
     * @generated NOT
     */
    protected void setValueColumnNameInIdentifier( String newValueColumn )
    {
        DataElementIdentifier identifier = getValueColumnIdentifier();
        if( identifier == null )
        {
            identifier = DesignFactory.eINSTANCE.createDataElementIdentifier();
            setValueColumnIdentifier( identifier );
        }

        identifier.setName( newValueColumn );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDisplayNameColumn()
    {
        return m_displayNameColumn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisplayNameColumn( String newDisplayNameColumn )
    {
        String oldDisplayNameColumn = m_displayNameColumn;
        m_displayNameColumn = newDisplayNameColumn;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DYNAMIC_VALUES_QUERY__DISPLAY_NAME_COLUMN,
                    oldDisplayNameColumn, m_displayNameColumn ) );
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
        case DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN:
            return basicSetDataSetDesign( null, msgs );
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER:
            return basicSetValueColumnIdentifier( null, msgs );
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
        case DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN:
            return getDataSetDesign();
        case DesignPackage.DYNAMIC_VALUES_QUERY__ENABLED:
            return isEnabled() ? Boolean.TRUE : Boolean.FALSE;
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER:
            return getValueColumnIdentifier();
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN:
            return getValueColumn();
        case DesignPackage.DYNAMIC_VALUES_QUERY__DISPLAY_NAME_COLUMN:
            return getDisplayNameColumn();
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
        case DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN:
            setDataSetDesign( (DataSetDesign) newValue );
            return;
        case DesignPackage.DYNAMIC_VALUES_QUERY__ENABLED:
            setEnabled( ((Boolean) newValue).booleanValue() );
            return;
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER:
            setValueColumnIdentifier( (DataElementIdentifier) newValue );
            return;
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN:
            setValueColumn( (String) newValue );
            return;
        case DesignPackage.DYNAMIC_VALUES_QUERY__DISPLAY_NAME_COLUMN:
            setDisplayNameColumn( (String) newValue );
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
        case DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN:
            setDataSetDesign( (DataSetDesign) null );
            return;
        case DesignPackage.DYNAMIC_VALUES_QUERY__ENABLED:
            unsetEnabled();
            return;
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER:
            setValueColumnIdentifier( (DataElementIdentifier) null );
            return;
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN:
            setValueColumn( VALUE_COLUMN_EDEFAULT );
            return;
        case DesignPackage.DYNAMIC_VALUES_QUERY__DISPLAY_NAME_COLUMN:
            setDisplayNameColumn( DISPLAY_NAME_COLUMN_EDEFAULT );
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
        case DesignPackage.DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN:
            return m_dataSetDesign != null;
        case DesignPackage.DYNAMIC_VALUES_QUERY__ENABLED:
            return isSetEnabled();
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER:
            return m_valueColumnIdentifier != null;
        case DesignPackage.DYNAMIC_VALUES_QUERY__VALUE_COLUMN:
            return VALUE_COLUMN_EDEFAULT == null ? m_valueColumn != null
                    : !VALUE_COLUMN_EDEFAULT.equals( m_valueColumn );
        case DesignPackage.DYNAMIC_VALUES_QUERY__DISPLAY_NAME_COLUMN:
            return DISPLAY_NAME_COLUMN_EDEFAULT == null ? m_displayNameColumn != null
                    : !DISPLAY_NAME_COLUMN_EDEFAULT
                            .equals( m_displayNameColumn );
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
        result.append( " (enabled: " ); //$NON-NLS-1$
        if( m_enabledESet )
            result.append( m_enabled );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", valueColumn: " ); //$NON-NLS-1$
        result.append( m_valueColumn );
        result.append( ", displayNameColumn: " ); //$NON-NLS-1$
        result.append( m_displayNameColumn );
        result.append( ')' );
        return result.toString();
    }

} //DynamicValuesQueryImpl
