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
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap m_mixed = null;

    /**
     * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXMLNSPrefixMap()
     * @generated
     * @ordered
     */
    protected EMap m_xMLNSPrefixMap = null;

    /**
     * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXSISchemaLocation()
     * @generated
     * @ordered
     */
    protected EMap m_xSISchemaLocation = null;

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
    protected EClass eStaticClass()
    {
        return DesignPackage.eINSTANCE.getDocumentRoot();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed()
    {
        if (m_mixed == null)
        {
            m_mixed = new BasicFeatureMap(this, DesignPackage.DOCUMENT_ROOT__MIXED);
        }
        return m_mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap getXMLNSPrefixMap()
    {
        if (m_xMLNSPrefixMap == null)
        {
            m_xMLNSPrefixMap = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        }
        return m_xMLNSPrefixMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap getXSISchemaLocation()
    {
        if (m_xSISchemaLocation == null)
        {
            m_xSISchemaLocation = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
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
        return (OdaDesignSession)getMixed().get(DesignPackage.eINSTANCE.getDocumentRoot_OdaDesignSession(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOdaDesignSession( OdaDesignSession newOdaDesignSession, NotificationChain msgs )
    {
        return ((FeatureMap.Internal)getMixed()).basicAdd(DesignPackage.eINSTANCE.getDocumentRoot_OdaDesignSession(), newOdaDesignSession, null);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOdaDesignSession( OdaDesignSession newOdaDesignSession )
    {
        ((FeatureMap.Internal)getMixed()).set(DesignPackage.eINSTANCE.getDocumentRoot_OdaDesignSession(), newOdaDesignSession);
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
                case DesignPackage.DOCUMENT_ROOT__MIXED:
                    return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
                case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                    return ((InternalEList)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
                case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                    return ((InternalEList)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
                case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
                    return basicSetOdaDesignSession(null, msgs);
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
            case DesignPackage.DOCUMENT_ROOT__MIXED:
                return getMixed();
            case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return getXMLNSPrefixMap();
            case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return getXSISchemaLocation();
            case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
                return getOdaDesignSession();
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
            case DesignPackage.DOCUMENT_ROOT__MIXED:
                getMixed().clear();
                getMixed().addAll((Collection)newValue);
                return;
            case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                getXMLNSPrefixMap().clear();
                getXMLNSPrefixMap().addAll((Collection)newValue);
                return;
            case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                getXSISchemaLocation().clear();
                getXSISchemaLocation().addAll((Collection)newValue);
                return;
            case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
                setOdaDesignSession((OdaDesignSession)newValue);
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
                setOdaDesignSession((OdaDesignSession)null);
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
            case DesignPackage.DOCUMENT_ROOT__MIXED:
                return m_mixed != null && !m_mixed.isEmpty();
            case DesignPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return m_xMLNSPrefixMap != null && !m_xMLNSPrefixMap.isEmpty();
            case DesignPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return m_xSISchemaLocation != null && !m_xSISchemaLocation.isEmpty();
            case DesignPackage.DOCUMENT_ROOT__ODA_DESIGN_SESSION:
                return getOdaDesignSession() != null;
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
        result.append(" (mixed: "); //$NON-NLS-1$
        result.append(m_mixed);
        result.append(')');
        return result.toString();
    }

} //DocumentRootImpl
