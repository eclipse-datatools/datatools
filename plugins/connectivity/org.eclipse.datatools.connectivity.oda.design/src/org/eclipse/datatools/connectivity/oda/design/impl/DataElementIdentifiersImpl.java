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
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.util.Collection;

import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier;
import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Element Identifiers</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementIdentifiersImpl#getIdentifiers <em>Identifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataElementIdentifiersImpl extends EObjectImpl implements
        DataElementIdentifiers
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getIdentifiers() <em>Identifiers</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdentifiers()
     * @generated
     * @ordered
     */
    protected EList<DataElementIdentifier> m_identifiers;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataElementIdentifiersImpl()
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
        return DesignPackage.Literals.DATA_ELEMENT_IDENTIFIERS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataElementIdentifier> getIdentifiers()
    {
        if( m_identifiers == null )
        {
            m_identifiers = new EObjectContainmentEList<DataElementIdentifier>(
                    DataElementIdentifier.class, this,
                    DesignPackage.DATA_ELEMENT_IDENTIFIERS__IDENTIFIERS );
        }
        return m_identifiers;
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
        case DesignPackage.DATA_ELEMENT_IDENTIFIERS__IDENTIFIERS:
            return ((InternalEList<?>) getIdentifiers()).basicRemove( otherEnd,
                    msgs );
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
        case DesignPackage.DATA_ELEMENT_IDENTIFIERS__IDENTIFIERS:
            return getIdentifiers();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet( int featureID, Object newValue )
    {
        switch( featureID )
        {
        case DesignPackage.DATA_ELEMENT_IDENTIFIERS__IDENTIFIERS:
            getIdentifiers().clear();
            getIdentifiers().addAll(
                    (Collection<? extends DataElementIdentifier>) newValue );
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
        case DesignPackage.DATA_ELEMENT_IDENTIFIERS__IDENTIFIERS:
            getIdentifiers().clear();
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
        case DesignPackage.DATA_ELEMENT_IDENTIFIERS__IDENTIFIERS:
            return m_identifiers != null && !m_identifiers.isEmpty();
        }
        return super.eIsSet( featureID );
    }

} //DataElementIdentifiersImpl
