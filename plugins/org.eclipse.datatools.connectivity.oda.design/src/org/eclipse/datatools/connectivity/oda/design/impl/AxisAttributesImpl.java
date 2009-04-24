/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
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
 * $Id: AxisAttributesImpl.java,v 1.2 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AxisAttributes;
import org.eclipse.datatools.connectivity.oda.design.AxisType;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Axis Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AxisAttributesImpl#getAxisType <em>Axis Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AxisAttributesImpl#isOnColumnLayout <em>On Column Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AxisAttributesImpl extends EObjectImpl implements AxisAttributes
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getAxisType() <em>Axis Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAxisType()
     * @generated
     * @ordered
     */
    protected static final AxisType AXIS_TYPE_EDEFAULT = AxisType.MEASURE_LITERAL;

    /**
     * The cached value of the '{@link #getAxisType() <em>Axis Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAxisType()
     * @generated
     * @ordered
     */
    protected AxisType m_axisType = AXIS_TYPE_EDEFAULT;

    /**
     * This is true if the Axis Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_axisTypeESet;

    /**
     * The default value of the '{@link #isOnColumnLayout() <em>On Column Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOnColumnLayout()
     * @generated
     * @ordered
     */
    protected static final boolean ON_COLUMN_LAYOUT_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isOnColumnLayout() <em>On Column Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOnColumnLayout()
     * @generated
     * @ordered
     */
    protected boolean m_onColumnLayout = ON_COLUMN_LAYOUT_EDEFAULT;

    /**
     * This is true if the On Column Layout attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_onColumnLayoutESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AxisAttributesImpl()
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
        return DesignPackage.Literals.AXIS_ATTRIBUTES;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AxisType getAxisType()
    {
        return m_axisType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAxisType( AxisType newAxisType )
    {
        AxisType oldAxisType = m_axisType;
        m_axisType = newAxisType == null ? AXIS_TYPE_EDEFAULT : newAxisType;
        boolean oldAxisTypeESet = m_axisTypeESet;
        m_axisTypeESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE, oldAxisType,
                    m_axisType, !oldAxisTypeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetAxisType()
    {
        AxisType oldAxisType = m_axisType;
        boolean oldAxisTypeESet = m_axisTypeESet;
        m_axisType = AXIS_TYPE_EDEFAULT;
        m_axisTypeESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE, oldAxisType,
                    AXIS_TYPE_EDEFAULT, oldAxisTypeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetAxisType()
    {
        return m_axisTypeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isOnColumnLayout()
    {
        return m_onColumnLayout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOnColumnLayout( boolean newOnColumnLayout )
    {
        boolean oldOnColumnLayout = m_onColumnLayout;
        m_onColumnLayout = newOnColumnLayout;
        boolean oldOnColumnLayoutESet = m_onColumnLayoutESet;
        m_onColumnLayoutESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT,
                    oldOnColumnLayout, m_onColumnLayout, !oldOnColumnLayoutESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetOnColumnLayout()
    {
        boolean oldOnColumnLayout = m_onColumnLayout;
        boolean oldOnColumnLayoutESet = m_onColumnLayoutESet;
        m_onColumnLayout = ON_COLUMN_LAYOUT_EDEFAULT;
        m_onColumnLayoutESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT,
                    oldOnColumnLayout, ON_COLUMN_LAYOUT_EDEFAULT,
                    oldOnColumnLayoutESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetOnColumnLayout()
    {
        return m_onColumnLayoutESet;
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
        case DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE:
            return getAxisType();
        case DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT:
            return isOnColumnLayout() ? Boolean.TRUE : Boolean.FALSE;
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
        case DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE:
            setAxisType( (AxisType) newValue );
            return;
        case DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT:
            setOnColumnLayout( ((Boolean) newValue).booleanValue() );
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
        case DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE:
            unsetAxisType();
            return;
        case DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT:
            unsetOnColumnLayout();
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
        case DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE:
            return isSetAxisType();
        case DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT:
            return isSetOnColumnLayout();
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
        result.append( " (axisType: " ); //$NON-NLS-1$
        if( m_axisTypeESet )
            result.append( m_axisType );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", onColumnLayout: " ); //$NON-NLS-1$
        if( m_onColumnLayoutESet )
            result.append( m_onColumnLayout );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //AxisAttributesImpl
