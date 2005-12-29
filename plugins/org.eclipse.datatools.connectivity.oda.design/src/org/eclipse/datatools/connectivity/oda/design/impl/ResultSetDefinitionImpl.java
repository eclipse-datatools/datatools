/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
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
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Set Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSetDefinitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSetDefinitionImpl#getResultSetColumns <em>Result Set Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultSetDefinitionImpl extends EObjectImpl implements ResultSetDefinition
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
     * The cached value of the '{@link #getResultSetColumns() <em>Result Set Columns</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResultSetColumns()
     * @generated
     * @ordered
     */
    protected ResultSetColumns m_resultSetColumns = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResultSetDefinitionImpl()
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
        return DesignPackage.eINSTANCE.getResultSetDefinition();
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
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.RESULT_SET_DEFINITION__NAME, oldName, m_name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultSetColumns getResultSetColumns()
    {
        return m_resultSetColumns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResultSetColumns( ResultSetColumns newResultSetColumns, NotificationChain msgs )
    {
        ResultSetColumns oldResultSetColumns = m_resultSetColumns;
        m_resultSetColumns = newResultSetColumns;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS, oldResultSetColumns, newResultSetColumns);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResultSetColumns( ResultSetColumns newResultSetColumns )
    {
        if (newResultSetColumns != m_resultSetColumns)
        {
            NotificationChain msgs = null;
            if (m_resultSetColumns != null)
                msgs = ((InternalEObject)m_resultSetColumns).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS, null, msgs);
            if (newResultSetColumns != null)
                msgs = ((InternalEObject)newResultSetColumns).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS, null, msgs);
            msgs = basicSetResultSetColumns(newResultSetColumns, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS, newResultSetColumns, newResultSetColumns));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove( InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs )
    {
        if (featureID >= 0)
        {
            switch (eDerivedStructuralFeatureID(featureID, baseClass))
            {
                case DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS:
                    return basicSetResultSetColumns(null, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet( EStructuralFeature eFeature, boolean resolve )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.RESULT_SET_DEFINITION__NAME:
                return getName();
            case DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS:
                return getResultSetColumns();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet( EStructuralFeature eFeature, Object newValue )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.RESULT_SET_DEFINITION__NAME:
                setName((String)newValue);
                return;
            case DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS:
                setResultSetColumns((ResultSetColumns)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset( EStructuralFeature eFeature )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.RESULT_SET_DEFINITION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS:
                setResultSetColumns((ResultSetColumns)null);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet( EStructuralFeature eFeature )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.RESULT_SET_DEFINITION__NAME:
                return NAME_EDEFAULT == null ? m_name != null : !NAME_EDEFAULT.equals(m_name);
            case DesignPackage.RESULT_SET_DEFINITION__RESULT_SET_COLUMNS:
                return m_resultSetColumns != null;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString()
    {
        if ( eIsProxy() ) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(m_name);
        result.append(')');
        return result.toString();
    }

} //ResultSetDefinitionImpl
