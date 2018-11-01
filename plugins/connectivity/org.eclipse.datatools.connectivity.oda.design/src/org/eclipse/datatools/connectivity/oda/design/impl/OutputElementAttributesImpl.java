/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
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
 * $Id: OutputElementAttributesImpl.java,v 1.5 2010/02/17 02:20:38 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.ValueFormatHints;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output Element Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.OutputElementAttributesImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.OutputElementAttributesImpl#getFormattingHints <em>Formatting Hints</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.OutputElementAttributesImpl#getHelpText <em>Help Text</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputElementAttributesImpl extends EObjectImpl implements
        OutputElementAttributes
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String m_label = LABEL_EDEFAULT;

    /**
     * The cached value of the '{@link #getFormattingHints() <em>Formatting Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFormattingHints()
     * @generated
     * @ordered
     */
    protected ValueFormatHints m_formattingHints;

    /**
     * The default value of the '{@link #getHelpText() <em>Help Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHelpText()
     * @generated
     * @ordered
     */
    protected static final String HELP_TEXT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHelpText() <em>Help Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHelpText()
     * @generated
     * @ordered
     */
    protected String m_helpText = HELP_TEXT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OutputElementAttributesImpl()
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
        return DesignPackage.Literals.OUTPUT_ELEMENT_ATTRIBUTES;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getLabel()
     * @generated NOT
     */
    public String getLabel()
    {
        return DesignUtil.getDefaultResourceString( getLabelGen() );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getLabelGen()
    {
        return m_label;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#setLabel(java.lang.String)
     * @generated NOT
     */
    public void setLabel( String newLabel )
    {
        String newAttrValue = DesignUtil.addDefaultToResourceAttribute( newLabel, getLabelGen() );
        setLabelGen( newAttrValue );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setLabelGen( String newLabel )
    {
        String oldLabel = m_label;
        m_label = newLabel;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__LABEL, oldLabel,
                    m_label ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getLabelKey()
     * @generated NOT
     */
    public String getLabelKey()
    {
        return DesignUtil.getResourceKey( getLabelGen() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#setLabelKey(java.lang.String)
     * @generated NOT
     */
    public void setLabelKey( String newLabelKey )
    {
        String newAttrValue = DesignUtil.addKeyToResourceAttribute( newLabelKey, getLabelGen() );
        setLabelGen( newAttrValue );
    }

    /**
     * Returns a non-null ValueFormatHints.
     * If none is defined, returns one with default values.
     * @generated NOT
     */
    public ValueFormatHints getFormattingHints()
    {
        if( getFormattingHintsGen() != null )
            return getFormattingHintsGen();

        return DesignFactory.eINSTANCE.createValueFormatHints();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueFormatHints getFormattingHintsGen()
    {
        return m_formattingHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFormattingHints(
            ValueFormatHints newFormattingHints, NotificationChain msgs )
    {
        ValueFormatHints oldFormattingHints = m_formattingHints;
        m_formattingHints = newFormattingHints;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS,
                    oldFormattingHints, newFormattingHints );
            if( msgs == null )
                msgs = notification;
            else
                msgs.add( notification );
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFormattingHints( ValueFormatHints newFormattingHints )
    {
        if( newFormattingHints != m_formattingHints )
        {
            NotificationChain msgs = null;
            if( m_formattingHints != null )
                msgs = ((InternalEObject) m_formattingHints)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS,
                                null, msgs );
            if( newFormattingHints != null )
                msgs = ((InternalEObject) newFormattingHints)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS,
                                null, msgs );
            msgs = basicSetFormattingHints( newFormattingHints, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS,
                    newFormattingHints, newFormattingHints ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getHelpText()
     * @generated NOT
     */
    public String getHelpText()
    {
        return DesignUtil.getDefaultResourceString( getHelpTextGen() );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getHelpTextGen()
    {
        return m_helpText;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#setHelpText(java.lang.String)
     * @generated NOT
     */
    public void setHelpText( String newHelpText )
    {
        String newAttrValue = DesignUtil.addDefaultToResourceAttribute( newHelpText, getHelpTextGen() );
        setHelpTextGen( newAttrValue );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setHelpTextGen( String newHelpText )
    {
        String oldHelpText = m_helpText;
        m_helpText = newHelpText;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__HELP_TEXT,
                    oldHelpText, m_helpText ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#getHelpTextKey()
     * @generated NOT
     */
    public String getHelpTextKey()
    {
        return DesignUtil.getResourceKey( getHelpTextGen() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes#setHelpTextKey(java.lang.String)
     * @generated NOT
     */
    public void setHelpTextKey( String newHelpTextKey )
    {
        String newAttrValue = DesignUtil.addKeyToResourceAttribute( newHelpTextKey, getHelpTextGen() );
        setHelpTextGen( newAttrValue );
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
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS:
            return basicSetFormattingHints( null, msgs );
        }
        return super.eInverseRemove( otherEnd, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType )
    {
        switch( featureID )
        {
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__LABEL:
            return getLabelGen();
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS:
            return getFormattingHints();
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__HELP_TEXT:
            return getHelpTextGen();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public void eSet( int featureID, Object newValue )
    {
        switch( featureID )
        {
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__LABEL:
            setLabelGen( (String) newValue );
            return;
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS:
            setFormattingHints( (ValueFormatHints) newValue );
            return;
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__HELP_TEXT:
            setHelpTextGen( (String) newValue );
            return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public void eUnset( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__LABEL:
            setLabelGen( LABEL_EDEFAULT );
            return;
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS:
            setFormattingHints( (ValueFormatHints) null );
            return;
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__HELP_TEXT:
            setHelpTextGen( HELP_TEXT_EDEFAULT );
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
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__LABEL:
            return LABEL_EDEFAULT == null ? m_label != null : !LABEL_EDEFAULT
                    .equals( m_label );
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS:
            return m_formattingHints != null;
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES__HELP_TEXT:
            return HELP_TEXT_EDEFAULT == null ? m_helpText != null
                    : !HELP_TEXT_EDEFAULT.equals( m_helpText );
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
        result.append( " (label: " ); //$NON-NLS-1$
        result.append( m_label );
        result.append( ", helpText: " ); //$NON-NLS-1$
        result.append( m_helpText );
        result.append( ')' );
        return result.toString();
    }

} //OutputElementAttributesImpl
