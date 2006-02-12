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
 * $Id: DesignerStateImpl.java,v 1.2 2006/02/08 08:06:17 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.DesignerStateContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>er State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignerStateImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignerStateImpl#getStateContent <em>State Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DesignerStateImpl extends EObjectImpl implements DesignerState
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String m_version = VERSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStateContent() <em>State Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateContent()
     * @generated
     * @ordered
     */
    protected DesignerStateContent m_stateContent = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DesignerStateImpl()
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
        return DesignPackage.eINSTANCE.getDesignerState();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignerState#setStateContentAsString(java.lang.String)
     * @generated NOT
     */
    public void setNewStateContentAsString( String value )
    {
        // creates a new state content with the given value,
        // overrides any existing content
        DesignerStateContent content = 
                DesignFactory.eINSTANCE.createDesignerStateContent();
        content.setStateContentAsString( value );

        setStateContent( content );        
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignerState#setStateContentAsBlob(byte[])
     * @generated NOT
     */
    public void setNewStateContentAsBlob( byte[] value )
    {
        // creates a new state content with the given value,
        // overrides any existing content
        DesignerStateContent content = 
                DesignFactory.eINSTANCE.createDesignerStateContent();
        content.setStateContentAsBlob( value );

        setStateContent( content );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVersion()
    {
        return m_version;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVersion( String newVersion )
    {
        String oldVersion = m_version;
        m_version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGNER_STATE__VERSION, oldVersion, m_version));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignerStateContent getStateContent()
    {
        return m_stateContent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStateContent( DesignerStateContent newStateContent, NotificationChain msgs )
    {
        DesignerStateContent oldStateContent = m_stateContent;
        m_stateContent = newStateContent;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGNER_STATE__STATE_CONTENT, oldStateContent, newStateContent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStateContent( DesignerStateContent newStateContent )
    {
        if (newStateContent != m_stateContent)
        {
            NotificationChain msgs = null;
            if (m_stateContent != null)
                msgs = ((InternalEObject)m_stateContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGNER_STATE__STATE_CONTENT, null, msgs);
            if (newStateContent != null)
                msgs = ((InternalEObject)newStateContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGNER_STATE__STATE_CONTENT, null, msgs);
            msgs = basicSetStateContent(newStateContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGNER_STATE__STATE_CONTENT, newStateContent, newStateContent));
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
                case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
                    return basicSetStateContent(null, msgs);
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
            case DesignPackage.DESIGNER_STATE__VERSION:
                return getVersion();
            case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
                return getStateContent();
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
            case DesignPackage.DESIGNER_STATE__VERSION:
                setVersion((String)newValue);
                return;
            case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
                setStateContent((DesignerStateContent)newValue);
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
            case DesignPackage.DESIGNER_STATE__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
                setStateContent((DesignerStateContent)null);
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
            case DesignPackage.DESIGNER_STATE__VERSION:
                return VERSION_EDEFAULT == null ? m_version != null : !VERSION_EDEFAULT.equals(m_version);
            case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
                return m_stateContent != null;
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
        result.append(" (version: "); //$NON-NLS-1$
        result.append(m_version);
        result.append(')');
        return result.toString();
    }

} //DesignerStateImpl
