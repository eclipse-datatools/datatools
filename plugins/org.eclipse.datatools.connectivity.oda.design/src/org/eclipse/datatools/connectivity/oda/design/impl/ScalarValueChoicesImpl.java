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

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scalar Value Choices</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ScalarValueChoicesImpl#getScalarValues <em>Scalar Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScalarValueChoicesImpl extends EObjectImpl implements ScalarValueChoices
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getScalarValues() <em>Scalar Values</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScalarValues()
     * @generated
     * @ordered
     */
    protected EList m_scalarValues = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScalarValueChoicesImpl()
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
        return DesignPackage.eINSTANCE.getScalarValueChoices();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getScalarValues()
    {
        if (m_scalarValues == null)
        {
            m_scalarValues = new EObjectContainmentEList(ScalarValueDefinition.class, this, DesignPackage.SCALAR_VALUE_CHOICES__SCALAR_VALUES);
        }
        return m_scalarValues;
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
                case DesignPackage.SCALAR_VALUE_CHOICES__SCALAR_VALUES:
                    return ((InternalEList)getScalarValues()).basicRemove(otherEnd, msgs);
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
            case DesignPackage.SCALAR_VALUE_CHOICES__SCALAR_VALUES:
                return getScalarValues();
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
            case DesignPackage.SCALAR_VALUE_CHOICES__SCALAR_VALUES:
                getScalarValues().clear();
                getScalarValues().addAll((Collection)newValue);
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
            case DesignPackage.SCALAR_VALUE_CHOICES__SCALAR_VALUES:
                getScalarValues().clear();
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
            case DesignPackage.SCALAR_VALUE_CHOICES__SCALAR_VALUES:
                return m_scalarValues != null && !m_scalarValues.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //ScalarValueChoicesImpl
