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
 * $Id: DesignerStateContentImpl.java,v 1.2 2006/02/08 08:06:17 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DesignerStateContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>er State Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignerStateContentImpl#getStateContentAsString <em>State Content As String</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignerStateContentImpl#getStateContentAsBlob <em>State Content As Blob</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DesignerStateContentImpl extends EObjectImpl implements DesignerStateContent
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getStateContentAsString() <em>State Content As String</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateContentAsString()
     * @generated
     * @ordered
     */
    protected static final String STATE_CONTENT_AS_STRING_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStateContentAsString() <em>State Content As String</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateContentAsString()
     * @generated
     * @ordered
     */
    protected String m_stateContentAsString = STATE_CONTENT_AS_STRING_EDEFAULT;

    /**
     * The default value of the '{@link #getStateContentAsBlob() <em>State Content As Blob</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateContentAsBlob()
     * @generated
     * @ordered
     */
    protected static final byte[] STATE_CONTENT_AS_BLOB_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStateContentAsBlob() <em>State Content As Blob</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateContentAsBlob()
     * @generated
     * @ordered
     */
    protected byte[] m_stateContentAsBlob = STATE_CONTENT_AS_BLOB_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DesignerStateContentImpl()
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
        return DesignPackage.eINSTANCE.getDesignerStateContent();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStateContentAsString()
    {
        return m_stateContentAsString;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignerStateContent#setStateContentAsString(java.lang.String)
     * @generated NOT
     */
    public void setStateContentAsString( String newStateContentAsString )
    {
        setStateContentAsStringGen( newStateContentAsString );
        
        // reset value of alternate content type 
        setStateContentAsBlobGen( null );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private void setStateContentAsStringGen( String newStateContentAsString )
    {
        String oldStateContentAsString = m_stateContentAsString;
        m_stateContentAsString = newStateContentAsString;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_STRING, oldStateContentAsString, m_stateContentAsString));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public byte[] getStateContentAsBlob()
    {
        return m_stateContentAsBlob;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignerStateContent#setStateContentAsBlob(byte[])
     * @generated NOT
     */
    public void setStateContentAsBlob( byte[] newStateContentAsBlob )
    {
        setStateContentAsBlobGen( newStateContentAsBlob );

        // reset value of alternate content type 
        setStateContentAsStringGen( null );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private void setStateContentAsBlobGen( byte[] newStateContentAsBlob )
    {
        byte[] oldStateContentAsBlob = m_stateContentAsBlob;
        m_stateContentAsBlob = newStateContentAsBlob;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_BLOB, oldStateContentAsBlob, m_stateContentAsBlob));
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
            case DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_STRING:
                return getStateContentAsString();
            case DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_BLOB:
                return getStateContentAsBlob();
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
            case DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_STRING:
                setStateContentAsString((String)newValue);
                return;
            case DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_BLOB:
                setStateContentAsBlob((byte[])newValue);
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
            case DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_STRING:
                setStateContentAsString(STATE_CONTENT_AS_STRING_EDEFAULT);
                return;
            case DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_BLOB:
                setStateContentAsBlob(STATE_CONTENT_AS_BLOB_EDEFAULT);
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
            case DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_STRING:
                return STATE_CONTENT_AS_STRING_EDEFAULT == null ? m_stateContentAsString != null : !STATE_CONTENT_AS_STRING_EDEFAULT.equals(m_stateContentAsString);
            case DesignPackage.DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_BLOB:
                return STATE_CONTENT_AS_BLOB_EDEFAULT == null ? m_stateContentAsBlob != null : !STATE_CONTENT_AS_BLOB_EDEFAULT.equals(m_stateContentAsBlob);
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
        result.append(" (stateContentAsString: "); //$NON-NLS-1$
        result.append(m_stateContentAsString);
        result.append(", stateContentAsBlob: "); //$NON-NLS-1$
        result.append(m_stateContentAsBlob);
        result.append(')');
        return result.toString();
    }

} //DesignerStateContentImpl
