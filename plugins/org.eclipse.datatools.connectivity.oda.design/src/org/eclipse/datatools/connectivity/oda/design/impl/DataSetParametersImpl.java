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

import java.util.Collection;

import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Set Parameters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetParametersImpl#getParameterDefinitions <em>Parameter Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetParametersImpl#isDerivedMetaData <em>Derived Meta Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataSetParametersImpl extends EObjectImpl implements DataSetParameters
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getParameterDefinitions() <em>Parameter Definitions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterDefinitions()
     * @generated
     * @ordered
     */
    protected EList m_parameterDefinitions = null;

    /**
     * The default value of the '{@link #isDerivedMetaData() <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDerivedMetaData()
     * @generated
     * @ordered
     */
    protected static final boolean DERIVED_META_DATA_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isDerivedMetaData() <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDerivedMetaData()
     * @generated
     * @ordered
     */
    protected boolean m_derivedMetaData = DERIVED_META_DATA_EDEFAULT;

    /**
     * This is true if the Derived Meta Data attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_derivedMetaDataESet = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataSetParametersImpl()
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
        return DesignPackage.eINSTANCE.getDataSetParameters();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getParameterDefinitions()
    {
        if (m_parameterDefinitions == null)
        {
            m_parameterDefinitions = new EObjectContainmentEList(ParameterDefinition.class, this, DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS);
        }
        return m_parameterDefinitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDerivedMetaData()
    {
        return m_derivedMetaData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDerivedMetaData( boolean newDerivedMetaData )
    {
        boolean oldDerivedMetaData = m_derivedMetaData;
        m_derivedMetaData = newDerivedMetaData;
        boolean oldDerivedMetaDataESet = m_derivedMetaDataESet;
        m_derivedMetaDataESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA, oldDerivedMetaData, m_derivedMetaData, !oldDerivedMetaDataESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetDerivedMetaData()
    {
        boolean oldDerivedMetaData = m_derivedMetaData;
        boolean oldDerivedMetaDataESet = m_derivedMetaDataESet;
        m_derivedMetaData = DERIVED_META_DATA_EDEFAULT;
        m_derivedMetaDataESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA, oldDerivedMetaData, DERIVED_META_DATA_EDEFAULT, oldDerivedMetaDataESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetDerivedMetaData()
    {
        return m_derivedMetaDataESet;
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
                case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
                    return ((InternalEList)getParameterDefinitions()).basicRemove(otherEnd, msgs);
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
            case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
                return getParameterDefinitions();
            case DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA:
                return isDerivedMetaData() ? Boolean.TRUE : Boolean.FALSE;
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
            case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
                getParameterDefinitions().clear();
                getParameterDefinitions().addAll((Collection)newValue);
                return;
            case DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA:
                setDerivedMetaData(((Boolean)newValue).booleanValue());
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
            case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
                getParameterDefinitions().clear();
                return;
            case DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA:
                unsetDerivedMetaData();
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
            case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
                return m_parameterDefinitions != null && !m_parameterDefinitions.isEmpty();
            case DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA:
                return isSetDerivedMetaData();
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
        result.append(" (derivedMetaData: "); //$NON-NLS-1$
        if (m_derivedMetaDataESet) result.append(m_derivedMetaData); else result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //DataSetParametersImpl
