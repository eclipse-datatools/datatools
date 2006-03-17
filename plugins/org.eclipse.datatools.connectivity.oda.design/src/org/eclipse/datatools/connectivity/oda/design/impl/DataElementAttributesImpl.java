/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DataElementAttributesImpl.java,v 1.4 2006/03/09 05:09:18 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.OdaScalarDataType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Element Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementAttributesImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementAttributesImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementAttributesImpl#getNativeDataTypeCode <em>Native Data Type Code</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementAttributesImpl#getPrecision <em>Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementAttributesImpl#getScale <em>Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementAttributesImpl#getNullability <em>Nullability</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementAttributesImpl#getUiHints <em>Ui Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataElementAttributesImpl extends EObjectImpl implements
        DataElementAttributes
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
    protected static final String EMPTY_STR = ""; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String m_name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPosition() <em>Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected static final int POSITION_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected int m_position = POSITION_EDEFAULT;

    /**
     * This is true if the Position attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_positionESet = false;

    /**
     * The default value of the '{@link #getNativeDataTypeCode() <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNativeDataTypeCode()
     * @generated
     * @ordered
     */
    protected static final int NATIVE_DATA_TYPE_CODE_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getNativeDataTypeCode() <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNativeDataTypeCode()
     * @generated
     * @ordered
     */
    protected int m_nativeDataTypeCode = NATIVE_DATA_TYPE_CODE_EDEFAULT;

    /**
     * This is true if the Native Data Type Code attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_nativeDataTypeCodeESet = false;

    /**
     * The default value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrecision()
     * @generated
     * @ordered
     */
    protected static final int PRECISION_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrecision()
     * @generated
     * @ordered
     */
    protected int m_precision = PRECISION_EDEFAULT;

    /**
     * This is true if the Precision attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_precisionESet = false;

    /**
     * The default value of the '{@link #getScale() <em>Scale</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScale()
     * @generated
     * @ordered
     */
    protected static final int SCALE_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getScale() <em>Scale</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScale()
     * @generated
     * @ordered
     */
    protected int m_scale = SCALE_EDEFAULT;

    /**
     * This is true if the Scale attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_scaleESet = false;

    /**
     * The default value of the '{@link #getNullability() <em>Nullability</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullability()
     * @generated
     * @ordered
     */
    protected static final ElementNullability NULLABILITY_EDEFAULT = ElementNullability.UNKNOWN_LITERAL;

    /**
     * The cached value of the '{@link #getNullability() <em>Nullability</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullability()
     * @generated
     * @ordered
     */
    protected ElementNullability m_nullability = NULLABILITY_EDEFAULT;

    /**
     * This is true if the Nullability attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_nullabilityESet = false;

    /**
     * The cached value of the '{@link #getUiHints() <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUiHints()
     * @generated
     * @ordered
     */
    protected DataElementUIHints m_uiHints = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataElementAttributesImpl()
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
        return DesignPackage.eINSTANCE.getDataElementAttributes();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#setApplicablePrecision(int, org.eclipse.datatools.connectivity.oda.design.OdaScalarDataType)
     * @generated NOT
     */
    public void setApplicablePrecision( int value, OdaScalarDataType odaDataType )
    {
        switch( odaDataType.getValue() )
        {
        // precision is not applicable for these data types
        case OdaScalarDataType.DATE:
        case OdaScalarDataType.TIME:
        case OdaScalarDataType.TIMESTAMP:
            setPrecision( PRECISION_EDEFAULT );
            break;
        case OdaScalarDataType.DOUBLE:
        case OdaScalarDataType.INTEGER:
        case OdaScalarDataType.STRING:
        case OdaScalarDataType.DECIMAL:
        case OdaScalarDataType.BLOB:
        case OdaScalarDataType.CLOB:
        default:
            setPrecision( value );
            break;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#setApplicableScale(int, org.eclipse.datatools.connectivity.oda.design.OdaScalarDataType)
     * @generated NOT
     */
    public void setApplicableScale( int value, OdaScalarDataType odaDataType )
    {
        switch( odaDataType.getValue() )
        {
        // scale is not applicable for these data types
        case OdaScalarDataType.DATE:
        case OdaScalarDataType.TIME:
        case OdaScalarDataType.TIMESTAMP:
        case OdaScalarDataType.STRING:
        case OdaScalarDataType.BLOB:
        case OdaScalarDataType.CLOB:
            setScale( SCALE_EDEFAULT );
            break;
        case OdaScalarDataType.INTEGER:
            setScale( (short) 0 );
            break;
        case OdaScalarDataType.DOUBLE:
        case OdaScalarDataType.DECIMAL:
        default:
            setScale( value );
            break;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#allowsNull()
     * @generated NOT
     */
    public boolean allowsNull()
    {
        return (getNullability().getValue() == ElementNullability.NULLABLE);
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#setUiDisplayName(java.lang.String)
     * @generated NOT
     */
    public void setUiDisplayName( String value )
    {
        // sets attribute in current UIHints, if exists;
        // otherwise, creates a new one
        DataElementUIHints uiHints = getUiHints();
        boolean hasNoUIHints = (uiHints == null);
        if( hasNoUIHints )
            uiHints = DesignFactory.eINSTANCE.createDataElementUIHints();
        uiHints.setDisplayName( value );

        if( hasNoUIHints )
            setUiHints( uiHints );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#setUiDescription(java.lang.String)
     * @generated NOT
     */
    public void setUiDescription( String value )
    {
        // sets attribute in current UIHints, if exists;
        // otherwise, creates a new one
        DataElementUIHints uiHints = getUiHints();
        boolean hasNoUIHints = (uiHints == null);
        if( hasNoUIHints )
            uiHints = DesignFactory.eINSTANCE.createDataElementUIHints();
        uiHints.setDescription( value );

        if( hasNoUIHints )
            setUiHints( uiHints );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName( String newName )
    {
        String oldName = m_name;
        m_name = newName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__NAME, oldName,
                    m_name ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPosition()
    {
        return m_position;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#setPosition(int)
     * @generated NOT
     */
    public void setPosition( int newPosition )
    {
        setPositionImpl( newPosition );
        
        /* If a data element can only be identified by position, 
         * its name may be empty.
         * Set required name field to empty by default.
         */
        if( getName() == null ) // not yet set
            setName( EMPTY_STR );  
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPositionImpl( int newPosition )
    {
        int oldPosition = m_position;
        m_position = newPosition;
        boolean oldPositionESet = m_positionESet;
        m_positionESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__POSITION,
                    oldPosition, m_position, !oldPositionESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetPosition()
    {
        int oldPosition = m_position;
        boolean oldPositionESet = m_positionESet;
        m_position = POSITION_EDEFAULT;
        m_positionESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__POSITION,
                    oldPosition, POSITION_EDEFAULT, oldPositionESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetPosition()
    {
        return m_positionESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getNativeDataTypeCode()
    {
        return m_nativeDataTypeCode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNativeDataTypeCode( int newNativeDataTypeCode )
    {
        int oldNativeDataTypeCode = m_nativeDataTypeCode;
        m_nativeDataTypeCode = newNativeDataTypeCode;
        boolean oldNativeDataTypeCodeESet = m_nativeDataTypeCodeESet;
        m_nativeDataTypeCodeESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__NATIVE_DATA_TYPE_CODE,
                    oldNativeDataTypeCode, m_nativeDataTypeCode,
                    !oldNativeDataTypeCodeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetNativeDataTypeCode()
    {
        int oldNativeDataTypeCode = m_nativeDataTypeCode;
        boolean oldNativeDataTypeCodeESet = m_nativeDataTypeCodeESet;
        m_nativeDataTypeCode = NATIVE_DATA_TYPE_CODE_EDEFAULT;
        m_nativeDataTypeCodeESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.UNSET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__NATIVE_DATA_TYPE_CODE,
                    oldNativeDataTypeCode, NATIVE_DATA_TYPE_CODE_EDEFAULT,
                    oldNativeDataTypeCodeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetNativeDataTypeCode()
    {
        return m_nativeDataTypeCodeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPrecision()
    {
        return m_precision;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrecision( int newPrecision )
    {
        int oldPrecision = m_precision;
        m_precision = newPrecision;
        boolean oldPrecisionESet = m_precisionESet;
        m_precisionESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__PRECISION,
                    oldPrecision, m_precision, !oldPrecisionESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetPrecision()
    {
        int oldPrecision = m_precision;
        boolean oldPrecisionESet = m_precisionESet;
        m_precision = PRECISION_EDEFAULT;
        m_precisionESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__PRECISION,
                    oldPrecision, PRECISION_EDEFAULT, oldPrecisionESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetPrecision()
    {
        return m_precisionESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getScale()
    {
        return m_scale;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScale( int newScale )
    {
        int oldScale = m_scale;
        m_scale = newScale;
        boolean oldScaleESet = m_scaleESet;
        m_scaleESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__SCALE, oldScale,
                    m_scale, !oldScaleESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetScale()
    {
        int oldScale = m_scale;
        boolean oldScaleESet = m_scaleESet;
        m_scale = SCALE_EDEFAULT;
        m_scaleESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__SCALE, oldScale,
                    SCALE_EDEFAULT, oldScaleESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetScale()
    {
        return m_scaleESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementNullability getNullability()
    {
        return m_nullability;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNullability( ElementNullability newNullability )
    {
        ElementNullability oldNullability = m_nullability;
        m_nullability = newNullability == null ? NULLABILITY_EDEFAULT
                : newNullability;
        boolean oldNullabilityESet = m_nullabilityESet;
        m_nullabilityESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__NULLABILITY,
                    oldNullability, m_nullability, !oldNullabilityESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetNullability()
    {
        ElementNullability oldNullability = m_nullability;
        boolean oldNullabilityESet = m_nullabilityESet;
        m_nullability = NULLABILITY_EDEFAULT;
        m_nullabilityESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__NULLABILITY,
                    oldNullability, NULLABILITY_EDEFAULT, oldNullabilityESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetNullability()
    {
        return m_nullabilityESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataElementUIHints getUiHints()
    {
        return m_uiHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUiHints( DataElementUIHints newUiHints,
            NotificationChain msgs )
    {
        DataElementUIHints oldUiHints = m_uiHints;
        m_uiHints = newUiHints;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS,
                    oldUiHints, newUiHints );
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
    public void setUiHints( DataElementUIHints newUiHints )
    {
        if( newUiHints != m_uiHints )
        {
            NotificationChain msgs = null;
            if( m_uiHints != null )
                msgs = ((InternalEObject) m_uiHints)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS,
                                null, msgs );
            if( newUiHints != null )
                msgs = ((InternalEObject) newUiHints)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS,
                                null, msgs );
            msgs = basicSetUiHints( newUiHints, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS,
                    newUiHints, newUiHints ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
            int featureID, Class baseClass, NotificationChain msgs )
    {
        if( featureID >= 0 )
        {
            switch( eDerivedStructuralFeatureID( featureID, baseClass ) )
            {
            case DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS:
                return basicSetUiHints( null, msgs );
            default:
                return eDynamicInverseRemove( otherEnd, featureID, baseClass,
                        msgs );
            }
        }
        return eBasicSetContainer( null, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet( EStructuralFeature eFeature, boolean resolve )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NAME:
            return getName();
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__POSITION:
            return new Integer( getPosition() );
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NATIVE_DATA_TYPE_CODE:
            return new Integer( getNativeDataTypeCode() );
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__PRECISION:
            return new Integer( getPrecision() );
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__SCALE:
            return new Integer( getScale() );
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NULLABILITY:
            return getNullability();
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS:
            return getUiHints();
        }
        return eDynamicGet( eFeature, resolve );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet( EStructuralFeature eFeature, Object newValue )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NAME:
            setName( (String) newValue );
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__POSITION:
            setPosition( ((Integer) newValue).intValue() );
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NATIVE_DATA_TYPE_CODE:
            setNativeDataTypeCode( ((Integer) newValue).intValue() );
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__PRECISION:
            setPrecision( ((Integer) newValue).intValue() );
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__SCALE:
            setScale( ((Integer) newValue).intValue() );
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NULLABILITY:
            setNullability( (ElementNullability) newValue );
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS:
            setUiHints( (DataElementUIHints) newValue );
            return;
        }
        eDynamicSet( eFeature, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset( EStructuralFeature eFeature )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NAME:
            setName( NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__POSITION:
            unsetPosition();
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NATIVE_DATA_TYPE_CODE:
            unsetNativeDataTypeCode();
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__PRECISION:
            unsetPrecision();
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__SCALE:
            unsetScale();
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NULLABILITY:
            unsetNullability();
            return;
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS:
            setUiHints( (DataElementUIHints) null );
            return;
        }
        eDynamicUnset( eFeature );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet( EStructuralFeature eFeature )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NAME:
            return NAME_EDEFAULT == null ? m_name != null : !NAME_EDEFAULT
                    .equals( m_name );
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__POSITION:
            return isSetPosition();
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NATIVE_DATA_TYPE_CODE:
            return isSetNativeDataTypeCode();
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__PRECISION:
            return isSetPrecision();
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__SCALE:
            return isSetScale();
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__NULLABILITY:
            return isSetNullability();
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS:
            return m_uiHints != null;
        }
        return eDynamicIsSet( eFeature );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString()
    {
        if( eIsProxy() )
            return super.toString();

        StringBuffer result = new StringBuffer( super.toString() );
        result.append( " (name: " ); //$NON-NLS-1$
        result.append( m_name );
        result.append( ", position: " ); //$NON-NLS-1$
        if( m_positionESet )
            result.append( m_position );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", nativeDataTypeCode: " ); //$NON-NLS-1$
        if( m_nativeDataTypeCodeESet )
            result.append( m_nativeDataTypeCode );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", precision: " ); //$NON-NLS-1$
        if( m_precisionESet )
            result.append( m_precision );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", scale: " ); //$NON-NLS-1$
        if( m_scaleESet )
            result.append( m_scale );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", nullability: " ); //$NON-NLS-1$
        if( m_nullabilityESet )
            result.append( m_nullability );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //DataElementAttributesImpl
