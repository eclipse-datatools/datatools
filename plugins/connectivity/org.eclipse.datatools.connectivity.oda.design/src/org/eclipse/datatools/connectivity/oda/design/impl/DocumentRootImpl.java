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
 * $Id: DocumentRootImpl.java,v 1.2 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DocumentRoot;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DocumentRootImpl#getOdaDesignSession <em>Oda Design Session</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap m_mixed;

    /**
     * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXMLNSPrefixMap()
     * @generated
     * @ordered
     */
    protected EMap<String, String> m_xMLNSPrefixMap;

    /**
     * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXSISchemaLocation()
     * @generated
     * @ordered
     */
    protected EMap<String, String> m_xSISchemaLocation;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DocumentRootImpl()
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
        return DesignPackage.Literals.DOCUMENT_ROOT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed()
    {
        if( m_mixed == null )
        {
            m_mixed = new BasicFeatureMap( this,
                    DesignPackage.DOCUMENT_ROOT__MIXED );
        }
        return m_mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXMLNSPrefixMap()
    {
        if( m_xMLNSPrefixMap == null )
        {
            m_xMLNSPrefixMap = new EcoreEMap<String, String>(
                    EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
                    EStringToStringMapEntryImpl.class, this,
                    DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP );
        }
        return m_xMLNSPrefixMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXSISchemaLocation()
    {
        if( m_xSISchemaLocation == null )
        {
            m_xSISchemaLocation = new EcoreEMap<String, String>(
                    EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
                    EStringToStringMapEntryImpl.class, this,
                    DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION );
        }
        return m_xSISchemaLocation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OdaDesignSession getOdaDesignSession()
    {
        return (OdaDesignSession) getMixed().get(
                DesignPackage.Literals.DOCUMENT_ROOT__ODA_DESIGN_SESSION, true );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOdaDesignSession(
            OdaDesignSession newOdaDesignSession, NotificationChain msgs )
    {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                DesignPackage.Literals.DOCUMENT_ROOT__ODA_DESIGN_SESSION,
                newOdaDesignSession, msgs );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOdaDesignSession( OdaDesignSession newOdaDesignSession )
    {
        ((FeatureMap.Internal) getMixed()).set(
                DesignPackage.Literals.DOCUMENT_ROOT__ODA_DESIGN_SESSION,
                newOdaDesignSession );
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
        case DesignPackage.DOCUMENT_ROOT__MIXED:
            return ((InternalEList<?>) getMixed()).basicRemove( otherEnd, msgs );
        case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            return ((InternalEList<?>) getXMLNSPrefixMap()).basicRemove(
                    otherEnd, msgs );
        case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            return ((InternalEList<?>) getXSISchemaLocation()).basicRemove(
                    otherEnd, msgs );
        case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
            return basicSetOdaDesignSession( null, msgs );
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
        case DesignPackage.DOCUMENT_ROOT__MIXED:
            if( coreType )
                return getMixed();
            return ((FeatureMap.Internal) getMixed()).getWrapper();
        case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            if( coreType )
                return getXMLNSPrefixMap();
            else
                return getXMLNSPrefixMap().map();
        case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            if( coreType )
                return getXSISchemaLocation();
            else
                return getXSISchemaLocation().map();
        case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
            return getOdaDesignSession();
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
        case DesignPackage.DOCUMENT_ROOT__MIXED:
            ((FeatureMap.Internal) getMixed()).set( newValue );
            return;
        case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            ((EStructuralFeature.Setting) getXMLNSPrefixMap()).set( newValue );
            return;
        case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            ((EStructuralFeature.Setting) getXSISchemaLocation())
                    .set( newValue );
            return;
        case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
            setOdaDesignSession( (OdaDesignSession) newValue );
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
        case DesignPackage.DOCUMENT_ROOT__MIXED:
            getMixed().clear();
            return;
        case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            getXMLNSPrefixMap().clear();
            return;
        case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            getXSISchemaLocation().clear();
            return;
        case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
            setOdaDesignSession( (OdaDesignSession) null );
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
        case DesignPackage.DOCUMENT_ROOT__MIXED:
            return m_mixed != null && !m_mixed.isEmpty();
        case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            return m_xMLNSPrefixMap != null && !m_xMLNSPrefixMap.isEmpty();
        case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            return m_xSISchemaLocation != null
                    && !m_xSISchemaLocation.isEmpty();
        case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
            return getOdaDesignSession() != null;
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
        result.append( " (mixed: " ); //$NON-NLS-1$
        result.append( m_mixed );
        result.append( ')' );
        return result.toString();
    }

} //DocumentRootImpl
