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
 * $Id: ValueFormatHintsImpl.java,v 1.3 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.HorizontalAlignment;
import org.eclipse.datatools.connectivity.oda.design.TextFormatType;
import org.eclipse.datatools.connectivity.oda.design.TextWrapType;
import org.eclipse.datatools.connectivity.oda.design.ValueFormatHints;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Format Hints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getDisplaySize <em>Display Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getDisplayFormat <em>Display Format</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getTextFormatType <em>Text Format Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getTextWrapType <em>Text Wrap Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueFormatHintsImpl extends EObjectImpl implements
        ValueFormatHints
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getDisplaySize() <em>Display Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplaySize()
     * @generated
     * @ordered
     */
    protected static final int DISPLAY_SIZE_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getDisplaySize() <em>Display Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplaySize()
     * @generated
     * @ordered
     */
    protected int m_displaySize = DISPLAY_SIZE_EDEFAULT;

    /**
     * This is true if the Display Size attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_displaySizeESet;

    /**
     * The default value of the '{@link #getDisplayFormat() <em>Display Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayFormat()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_FORMAT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayFormat() <em>Display Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayFormat()
     * @generated
     * @ordered
     */
    protected String m_displayFormat = DISPLAY_FORMAT_EDEFAULT;

    /**
     * The default value of the '{@link #getTextFormatType() <em>Text Format Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextFormatType()
     * @generated
     * @ordered
     */
    protected static final TextFormatType TEXT_FORMAT_TYPE_EDEFAULT = TextFormatType.PLAIN_LITERAL;

    /**
     * The cached value of the '{@link #getTextFormatType() <em>Text Format Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextFormatType()
     * @generated
     * @ordered
     */
    protected TextFormatType m_textFormatType = TEXT_FORMAT_TYPE_EDEFAULT;

    /**
     * This is true if the Text Format Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_textFormatTypeESet;

    /**
     * The default value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalAlignment()
     * @generated
     * @ordered
     */
    protected static final HorizontalAlignment HORIZONTAL_ALIGNMENT_EDEFAULT = HorizontalAlignment.AUTOMATIC_LITERAL;

    /**
     * The cached value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalAlignment()
     * @generated
     * @ordered
     */
    protected HorizontalAlignment m_horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;

    /**
     * This is true if the Horizontal Alignment attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_horizontalAlignmentESet;

    /**
     * The default value of the '{@link #getTextWrapType() <em>Text Wrap Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextWrapType()
     * @generated
     * @ordered
     */
    protected static final TextWrapType TEXT_WRAP_TYPE_EDEFAULT = TextWrapType.NONE_LITERAL;

    /**
     * The cached value of the '{@link #getTextWrapType() <em>Text Wrap Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextWrapType()
     * @generated
     * @ordered
     */
    protected TextWrapType m_textWrapType = TEXT_WRAP_TYPE_EDEFAULT;

    /**
     * This is true if the Text Wrap Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_textWrapTypeESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValueFormatHintsImpl()
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
        return DesignPackage.Literals.VALUE_FORMAT_HINTS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDisplayFormat()
    {
        return m_displayFormat;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisplayFormat( String newDisplayFormat )
    {
        String oldDisplayFormat = m_displayFormat;
        m_displayFormat = newDisplayFormat;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT,
                    oldDisplayFormat, m_displayFormat ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getDisplaySize()
    {
        return m_displaySize;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisplaySize( int newDisplaySize )
    {
        int oldDisplaySize = m_displaySize;
        m_displaySize = newDisplaySize;
        boolean oldDisplaySizeESet = m_displaySizeESet;
        m_displaySizeESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE,
                    oldDisplaySize, m_displaySize, !oldDisplaySizeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetDisplaySize()
    {
        int oldDisplaySize = m_displaySize;
        boolean oldDisplaySizeESet = m_displaySizeESet;
        m_displaySize = DISPLAY_SIZE_EDEFAULT;
        m_displaySizeESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE,
                    oldDisplaySize, DISPLAY_SIZE_EDEFAULT, oldDisplaySizeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetDisplaySize()
    {
        return m_displaySizeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextFormatType getTextFormatType()
    {
        return m_textFormatType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTextFormatType( TextFormatType newTextFormatType )
    {
        TextFormatType oldTextFormatType = m_textFormatType;
        m_textFormatType = newTextFormatType == null ? TEXT_FORMAT_TYPE_EDEFAULT
                : newTextFormatType;
        boolean oldTextFormatTypeESet = m_textFormatTypeESet;
        m_textFormatTypeESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE,
                    oldTextFormatType, m_textFormatType, !oldTextFormatTypeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetTextFormatType()
    {
        TextFormatType oldTextFormatType = m_textFormatType;
        boolean oldTextFormatTypeESet = m_textFormatTypeESet;
        m_textFormatType = TEXT_FORMAT_TYPE_EDEFAULT;
        m_textFormatTypeESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE,
                    oldTextFormatType, TEXT_FORMAT_TYPE_EDEFAULT,
                    oldTextFormatTypeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetTextFormatType()
    {
        return m_textFormatTypeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HorizontalAlignment getHorizontalAlignment()
    {
        return m_horizontalAlignment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHorizontalAlignment(
            HorizontalAlignment newHorizontalAlignment )
    {
        HorizontalAlignment oldHorizontalAlignment = m_horizontalAlignment;
        m_horizontalAlignment = newHorizontalAlignment == null ? HORIZONTAL_ALIGNMENT_EDEFAULT
                : newHorizontalAlignment;
        boolean oldHorizontalAlignmentESet = m_horizontalAlignmentESet;
        m_horizontalAlignmentESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT,
                    oldHorizontalAlignment, m_horizontalAlignment,
                    !oldHorizontalAlignmentESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetHorizontalAlignment()
    {
        HorizontalAlignment oldHorizontalAlignment = m_horizontalAlignment;
        boolean oldHorizontalAlignmentESet = m_horizontalAlignmentESet;
        m_horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;
        m_horizontalAlignmentESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT,
                    oldHorizontalAlignment, HORIZONTAL_ALIGNMENT_EDEFAULT,
                    oldHorizontalAlignmentESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetHorizontalAlignment()
    {
        return m_horizontalAlignmentESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextWrapType getTextWrapType()
    {
        return m_textWrapType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTextWrapType( TextWrapType newTextWrapType )
    {
        TextWrapType oldTextWrapType = m_textWrapType;
        m_textWrapType = newTextWrapType == null ? TEXT_WRAP_TYPE_EDEFAULT
                : newTextWrapType;
        boolean oldTextWrapTypeESet = m_textWrapTypeESet;
        m_textWrapTypeESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE,
                    oldTextWrapType, m_textWrapType, !oldTextWrapTypeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetTextWrapType()
    {
        TextWrapType oldTextWrapType = m_textWrapType;
        boolean oldTextWrapTypeESet = m_textWrapTypeESet;
        m_textWrapType = TEXT_WRAP_TYPE_EDEFAULT;
        m_textWrapTypeESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE,
                    oldTextWrapType, TEXT_WRAP_TYPE_EDEFAULT,
                    oldTextWrapTypeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetTextWrapType()
    {
        return m_textWrapTypeESet;
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
        case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE:
            return new Integer( getDisplaySize() );
        case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT:
            return getDisplayFormat();
        case DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE:
            return getTextFormatType();
        case DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT:
            return getHorizontalAlignment();
        case DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE:
            return getTextWrapType();
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
        case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE:
            setDisplaySize( ((Integer) newValue).intValue() );
            return;
        case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT:
            setDisplayFormat( (String) newValue );
            return;
        case DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE:
            setTextFormatType( (TextFormatType) newValue );
            return;
        case DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT:
            setHorizontalAlignment( (HorizontalAlignment) newValue );
            return;
        case DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE:
            setTextWrapType( (TextWrapType) newValue );
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
        case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE:
            unsetDisplaySize();
            return;
        case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT:
            setDisplayFormat( DISPLAY_FORMAT_EDEFAULT );
            return;
        case DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE:
            unsetTextFormatType();
            return;
        case DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT:
            unsetHorizontalAlignment();
            return;
        case DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE:
            unsetTextWrapType();
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
        case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE:
            return isSetDisplaySize();
        case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT:
            return DISPLAY_FORMAT_EDEFAULT == null ? m_displayFormat != null
                    : !DISPLAY_FORMAT_EDEFAULT.equals( m_displayFormat );
        case DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE:
            return isSetTextFormatType();
        case DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT:
            return isSetHorizontalAlignment();
        case DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE:
            return isSetTextWrapType();
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
        result.append( " (displaySize: " ); //$NON-NLS-1$
        if( m_displaySizeESet )
            result.append( m_displaySize );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", displayFormat: " ); //$NON-NLS-1$
        result.append( m_displayFormat );
        result.append( ", textFormatType: " ); //$NON-NLS-1$
        if( m_textFormatTypeESet )
            result.append( m_textFormatType );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", horizontalAlignment: " ); //$NON-NLS-1$
        if( m_horizontalAlignmentESet )
            result.append( m_horizontalAlignment );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", textWrapType: " ); //$NON-NLS-1$
        if( m_textWrapTypeESet )
            result.append( m_textWrapType );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //ValueFormatHintsImpl
