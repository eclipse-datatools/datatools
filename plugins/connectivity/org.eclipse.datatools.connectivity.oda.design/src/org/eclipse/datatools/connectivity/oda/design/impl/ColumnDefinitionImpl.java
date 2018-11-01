/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
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
 * $Id: ColumnDefinitionImpl.java,v 1.2 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AxisAttributes;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ColumnDefinitionImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ColumnDefinitionImpl#getUsageHints <em>Usage Hints</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ColumnDefinitionImpl#getMultiDimensionAttributes <em>Multi Dimension Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnDefinitionImpl extends EObjectImpl implements
        ColumnDefinition
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributes()
     * @generated
     * @ordered
     */
    protected DataElementAttributes m_attributes;

    /**
     * The cached value of the '{@link #getUsageHints() <em>Usage Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUsageHints()
     * @generated
     * @ordered
     */
    protected OutputElementAttributes m_usageHints;

    /**
     * The cached value of the '{@link #getMultiDimensionAttributes() <em>Multi Dimension Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMultiDimensionAttributes()
     * @generated
     * @ordered
     */
    protected AxisAttributes m_multiDimensionAttributes;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ColumnDefinitionImpl()
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
        return DesignPackage.Literals.COLUMN_DEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataElementAttributes getAttributes()
    {
        return m_attributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAttributes(
            DataElementAttributes newAttributes, NotificationChain msgs )
    {
        DataElementAttributes oldAttributes = m_attributes;
        m_attributes = newAttributes;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.COLUMN_DEFINITION__ATTRIBUTES, oldAttributes,
                    newAttributes );
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
    public void setAttributes( DataElementAttributes newAttributes )
    {
        if( newAttributes != m_attributes )
        {
            NotificationChain msgs = null;
            if( m_attributes != null )
                msgs = ((InternalEObject) m_attributes).eInverseRemove( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.COLUMN_DEFINITION__ATTRIBUTES,
                        null, msgs );
            if( newAttributes != null )
                msgs = ((InternalEObject) newAttributes).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.COLUMN_DEFINITION__ATTRIBUTES,
                        null, msgs );
            msgs = basicSetAttributes( newAttributes, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.COLUMN_DEFINITION__ATTRIBUTES, newAttributes,
                    newAttributes ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputElementAttributes getUsageHints()
    {
        return m_usageHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUsageHints(
            OutputElementAttributes newUsageHints, NotificationChain msgs )
    {
        OutputElementAttributes oldUsageHints = m_usageHints;
        m_usageHints = newUsageHints;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.COLUMN_DEFINITION__USAGE_HINTS,
                    oldUsageHints, newUsageHints );
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
    public void setUsageHints( OutputElementAttributes newUsageHints )
    {
        if( newUsageHints != m_usageHints )
        {
            NotificationChain msgs = null;
            if( m_usageHints != null )
                msgs = ((InternalEObject) m_usageHints).eInverseRemove( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.COLUMN_DEFINITION__USAGE_HINTS,
                        null, msgs );
            if( newUsageHints != null )
                msgs = ((InternalEObject) newUsageHints).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.COLUMN_DEFINITION__USAGE_HINTS,
                        null, msgs );
            msgs = basicSetUsageHints( newUsageHints, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.COLUMN_DEFINITION__USAGE_HINTS,
                    newUsageHints, newUsageHints ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AxisAttributes getMultiDimensionAttributes()
    {
        return m_multiDimensionAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMultiDimensionAttributes(
            AxisAttributes newMultiDimensionAttributes, NotificationChain msgs )
    {
        AxisAttributes oldMultiDimensionAttributes = m_multiDimensionAttributes;
        m_multiDimensionAttributes = newMultiDimensionAttributes;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES,
                    oldMultiDimensionAttributes, newMultiDimensionAttributes );
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
    public void setMultiDimensionAttributes(
            AxisAttributes newMultiDimensionAttributes )
    {
        if( newMultiDimensionAttributes != m_multiDimensionAttributes )
        {
            NotificationChain msgs = null;
            if( m_multiDimensionAttributes != null )
                msgs = ((InternalEObject) m_multiDimensionAttributes)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES,
                                null, msgs );
            if( newMultiDimensionAttributes != null )
                msgs = ((InternalEObject) newMultiDimensionAttributes)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES,
                                null, msgs );
            msgs = basicSetMultiDimensionAttributes(
                    newMultiDimensionAttributes, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES,
                    newMultiDimensionAttributes, newMultiDimensionAttributes ) );
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
        case DesignPackage.COLUMN_DEFINITION__ATTRIBUTES:
            return basicSetAttributes( null, msgs );
        case DesignPackage.COLUMN_DEFINITION__USAGE_HINTS:
            return basicSetUsageHints( null, msgs );
        case DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES:
            return basicSetMultiDimensionAttributes( null, msgs );
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
        case DesignPackage.COLUMN_DEFINITION__ATTRIBUTES:
            return getAttributes();
        case DesignPackage.COLUMN_DEFINITION__USAGE_HINTS:
            return getUsageHints();
        case DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES:
            return getMultiDimensionAttributes();
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
        case DesignPackage.COLUMN_DEFINITION__ATTRIBUTES:
            setAttributes( (DataElementAttributes) newValue );
            return;
        case DesignPackage.COLUMN_DEFINITION__USAGE_HINTS:
            setUsageHints( (OutputElementAttributes) newValue );
            return;
        case DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES:
            setMultiDimensionAttributes( (AxisAttributes) newValue );
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
        case DesignPackage.COLUMN_DEFINITION__ATTRIBUTES:
            setAttributes( (DataElementAttributes) null );
            return;
        case DesignPackage.COLUMN_DEFINITION__USAGE_HINTS:
            setUsageHints( (OutputElementAttributes) null );
            return;
        case DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES:
            setMultiDimensionAttributes( (AxisAttributes) null );
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
        case DesignPackage.COLUMN_DEFINITION__ATTRIBUTES:
            return m_attributes != null;
        case DesignPackage.COLUMN_DEFINITION__USAGE_HINTS:
            return m_usageHints != null;
        case DesignPackage.COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES:
            return m_multiDimensionAttributes != null;
        }
        return super.eIsSet( featureID );
    }

} //ColumnDefinitionImpl
