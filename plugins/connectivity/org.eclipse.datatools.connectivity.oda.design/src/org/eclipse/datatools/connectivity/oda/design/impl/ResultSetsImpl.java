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
 * $Id: ResultSetsImpl.java,v 1.3 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.util.Collection;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ResultSets;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Sets</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSetsImpl#getResultSetDefinitions <em>Result Set Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResultSetsImpl#isDerivedMetaData <em>Derived Meta Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultSetsImpl extends EObjectImpl implements ResultSets
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getResultSetDefinitions() <em>Result Set Definitions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResultSetDefinitions()
     * @generated
     * @ordered
     */
    protected EList<ResultSetDefinition> m_resultSetDefinitions;

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
    protected boolean m_derivedMetaDataESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResultSetsImpl()
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
        return DesignPackage.Literals.RESULT_SETS;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSets#findResultSetDefinition(java.lang.String)
     * @generated NOT
     */
    public ResultSetDefinition findResultSetDefinition( String resultSetName )
    {
        if( m_resultSetDefinitions == null || resultSetName == null
                || resultSetName.length() == 0 )
            return null;

        EList<ResultSetDefinition> defns = getResultSetDefinitions();
        for( int i = 0; i < defns.size(); i++)
        {
            ResultSetDefinition defn = defns.get( i );
            if( resultSetName.equals( defn.getName() ) )
                return defn;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSets#addResultSetDefinition(int, org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition)
     * @generated NOT
     */
    public void addResultSetDefinition( int index,
            ResultSetDefinition resultSetDefn )
    {
        if( resultSetDefn == null )
            return; // nothing to add
        getResultSetDefinitions().add( index, resultSetDefn );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSets#addResultSetDefinition(org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition)
     * @generated NOT
     */
    public void addResultSetDefinition( ResultSetDefinition resultSetDefn )
    {
        if( resultSetDefn == null )
            return; // nothing to add
        getResultSetDefinitions().add( resultSetDefn );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ResultSetDefinition> getResultSetDefinitions()
    {
        if( m_resultSetDefinitions == null )
        {
            m_resultSetDefinitions = new EObjectContainmentEList<ResultSetDefinition>(
                    ResultSetDefinition.class, this,
                    DesignPackage.RESULT_SETS__RESULT_SET_DEFINITIONS );
        }
        return m_resultSetDefinitions;
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
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.RESULT_SETS__DERIVED_META_DATA,
                    oldDerivedMetaData, m_derivedMetaData,
                    !oldDerivedMetaDataESet ) );
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
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.RESULT_SETS__DERIVED_META_DATA,
                    oldDerivedMetaData, DERIVED_META_DATA_EDEFAULT,
                    oldDerivedMetaDataESet ) );
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
    @Override
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
            int featureID, NotificationChain msgs )
    {
        switch( featureID )
        {
        case DesignPackage.RESULT_SETS__RESULT_SET_DEFINITIONS:
            return ((InternalEList<?>) getResultSetDefinitions()).basicRemove(
                    otherEnd, msgs );
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
        case DesignPackage.RESULT_SETS__RESULT_SET_DEFINITIONS:
            return getResultSetDefinitions();
        case DesignPackage.RESULT_SETS__DERIVED_META_DATA:
            return isDerivedMetaData() ? Boolean.TRUE : Boolean.FALSE;
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
        case DesignPackage.RESULT_SETS__RESULT_SET_DEFINITIONS:
            getResultSetDefinitions().clear();
            getResultSetDefinitions().addAll(
                    (Collection<? extends ResultSetDefinition>) newValue );
            return;
        case DesignPackage.RESULT_SETS__DERIVED_META_DATA:
            setDerivedMetaData( ((Boolean) newValue).booleanValue() );
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
        case DesignPackage.RESULT_SETS__RESULT_SET_DEFINITIONS:
            getResultSetDefinitions().clear();
            return;
        case DesignPackage.RESULT_SETS__DERIVED_META_DATA:
            unsetDerivedMetaData();
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
        case DesignPackage.RESULT_SETS__RESULT_SET_DEFINITIONS:
            return m_resultSetDefinitions != null
                    && !m_resultSetDefinitions.isEmpty();
        case DesignPackage.RESULT_SETS__DERIVED_META_DATA:
            return isSetDerivedMetaData();
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
        result.append( " (derivedMetaData: " ); //$NON-NLS-1$
        if( m_derivedMetaDataESet )
            result.append( m_derivedMetaData );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //ResultSetsImpl
