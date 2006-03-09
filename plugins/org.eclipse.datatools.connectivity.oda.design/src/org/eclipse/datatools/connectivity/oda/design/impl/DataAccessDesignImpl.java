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
 * $Id: DataAccessDesignImpl.java,v 1.4 2006/02/28 21:02:29 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataAccessDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Access Design</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataAccessDesignImpl#getDataSetDesign <em>Data Set Design</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataAccessDesignImpl extends EObjectImpl implements DataAccessDesign
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * @generated NOT
     */
    public static final String EMPTY_STR = ""; //$NON-NLS-1$
    
    /**
     * The cached value of the '{@link #getDataSetDesign() <em>Data Set Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataSetDesign()
     * @generated
     * @ordered
     */
    protected DataSetDesign m_dataSetDesign = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataAccessDesignImpl()
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
        return DesignPackage.eINSTANCE.getDataAccessDesign();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataAccessDesign#getOdaExtensionDataSourceId()
     * @generated NOT
     */
    public String getOdaExtensionDataSourceId()
    {
        DataSetDesign dataSet = getDataSetDesign();
        return ( dataSet == null ) ? null :
                dataSet.getOdaExtensionDataSourceId();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataAccessDesign#getDataSourceDesign()
     * @generated NOT
     */
    public DataSourceDesign getDataSourceDesign()
    {
        if( getDataSetDesign() == null )
            return null;
        
        return getDataSetDesign().getDataSourceDesign();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataAccessDesign#setNewDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     * @generated NOT
     */
    public void setNewDataSetDesign( DataSourceDesign dataSourceDesign )
    {
        if( dataSourceDesign == null )
            return;     // no need to create an empty data set; leaves as empty
        
        DataSetDesign newDataSet =
            DesignFactory.eINSTANCE.createDataSetDesign();
        // set empty strings for required attributes in data set design
        newDataSet.setName( EMPTY_STR );
        newDataSet.setQueryText( EMPTY_STR );

        // associate the given data source design to the empty data set
        newDataSet.setDataSourceDesign( dataSourceDesign );

        setDataSetDesign( newDataSet );
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
    public NotificationChain basicSetDataSetDesign( DataSetDesign newDataSetDesign, NotificationChain msgs )
    {
        DataSetDesign oldDataSetDesign = m_dataSetDesign;
        m_dataSetDesign = newDataSetDesign;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN, oldDataSetDesign, newDataSetDesign);
            if (msgs == null) msgs = notification; else msgs.add(notification);
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
        if (newDataSetDesign != m_dataSetDesign)
        {
            NotificationChain msgs = null;
            if (m_dataSetDesign != null)
                msgs = ((InternalEObject)m_dataSetDesign).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN, null, msgs);
            if (newDataSetDesign != null)
                msgs = ((InternalEObject)newDataSetDesign).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN, null, msgs);
            msgs = basicSetDataSetDesign(newDataSetDesign, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN, newDataSetDesign, newDataSetDesign));
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
                case DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN:
                    return basicSetDataSetDesign(null, msgs);
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
            case DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN:
                return getDataSetDesign();
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
            case DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN:
                setDataSetDesign((DataSetDesign)newValue);
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
            case DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN:
                setDataSetDesign((DataSetDesign)null);
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
            case DesignPackage.DATA_ACCESS_DESIGN__DATA_SET_DESIGN:
                return m_dataSetDesign != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //DataAccessDesignImpl
