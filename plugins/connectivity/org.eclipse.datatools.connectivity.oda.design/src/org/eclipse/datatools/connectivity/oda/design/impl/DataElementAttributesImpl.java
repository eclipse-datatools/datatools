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
 * $Id: DataElementAttributesImpl.java,v 1.10 2009/04/24 03:20:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier;
import org.eclipse.datatools.connectivity.oda.design.DataElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.OdaScalarDataType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
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
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementAttributesImpl#getIdentifier <em>Identifier</em>}</li>
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
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdentifier()
     * @generated
     * @ordered
     * @since 3.3.2
     */
    protected DataElementIdentifier m_identifier;

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
     * @deprecated  since 3.3.2; replaced by m_identifier
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
     * @deprecated  since 3.3.2; replaced by m_identifier
     */
    protected int m_position = POSITION_EDEFAULT;

    /**
     * This is true if the Position attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_positionESet;

    /**
     * The default value of the '{@link #getNativeDataTypeCode() <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNativeDataTypeCode()
     * @generated
     * @ordered
     */
    protected static final int NATIVE_DATA_TYPE_CODE_EDEFAULT = 0;

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
    protected boolean m_nativeDataTypeCodeESet;

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
    protected boolean m_precisionESet;

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
    protected boolean m_scaleESet;

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
    protected boolean m_nullabilityESet;

    /**
     * The cached value of the '{@link #getUiHints() <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUiHints()
     * @generated
     * @ordered
     */
    protected DataElementUIHints m_uiHints;

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
    @Override
    protected EClass eStaticClass()
    {
        return DesignPackage.Literals.DATA_ELEMENT_ATTRIBUTES;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataElementIdentifier getIdentifier()
    {
        return m_identifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIdentifier(
            DataElementIdentifier newIdentifier, NotificationChain msgs )
    {
        DataElementIdentifier oldIdentifier = m_identifier;
        m_identifier = newIdentifier;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER,
                    oldIdentifier, newIdentifier );
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
    public void setIdentifier( DataElementIdentifier newIdentifier )
    {
        if( newIdentifier != m_identifier )
        {
            NotificationChain msgs = null;
            if( m_identifier != null )
                msgs = ((InternalEObject) m_identifier)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER,
                                null, msgs );
            if( newIdentifier != null )
                msgs = ((InternalEObject) newIdentifier)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER,
                                null, msgs );
            msgs = basicSetIdentifier( newIdentifier, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER,
                    newIdentifier, newIdentifier ) );
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
        case OdaScalarDataType.BOOLEAN:
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
        case OdaScalarDataType.BOOLEAN:
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

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getName()
     * @generated NOT
     */
    public String getName()
    {
        // the name attribute should now be stored in the associated identifier;
        // for backward compatibility of previously persisted object,
        // use the one in deprecated member variable, if exists
        String elementName = getNameGen();
        if( elementName != NAME_EDEFAULT )
            return elementName;
        return getNameInIdentifier();
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getNameGen()
    {
        return m_name;
    }

    /**
     * Returns the element name stored in the associated identifier.
     * @generated NOT
     */
    protected String getNameInIdentifier()
    {
        DataElementIdentifier identifier = getIdentifier();
        if( identifier == null )
            return NAME_EDEFAULT;

        return identifier.getName();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#setName(java.lang.String)
     * @generated NOT
     */
    public void setName( String newName )
    {
        // the name attribute should now be stored in the associated identifier;
        // clear any existing value in the deprecated member variable
        if( getNameGen() != NAME_EDEFAULT )
            setNameGen( NAME_EDEFAULT );
        setNameInIdentifier( newName );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNameGen( String newName )
    {
        String oldName = m_name;
        m_name = newName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_ATTRIBUTES__NAME, oldName,
                    m_name ) );
    }
    
    /**
     * Set the element name in the associated identifier.
     * @generated NOT
     */
    protected void setNameInIdentifier( String newName )
    {
        DataElementIdentifier identifier = getIdentifier();
        if( identifier == null )
        {
            identifier = DesignFactory.eINSTANCE.createDataElementIdentifier();
            setIdentifier( identifier );
        }

        identifier.setName( newName );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPosition()
     * @generated NOT
     */
    public int getPosition()
    {
        // the position attribute should now be stored in the associated identifier;
        // for backward compatibility of previously persisted object,
        // use the one in deprecated member variable, if exists
        if( isSetPosition() )
            return getPositionGen();
        return getPositionInIdentifier();
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected int getPositionGen()
    {
        return m_position;
    }
    
    /**
     * Returns the element position stored in the associated identifier.
     * @generated NOT
     */
    protected int getPositionInIdentifier()
    {
        DataElementIdentifier identifier = getIdentifier();
        if( identifier == null )
            return POSITION_EDEFAULT;

        return identifier.getPosition();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#setPosition(int)
     * @generated NOT
     */
    public void setPosition( int newPosition )
    {   
        // the position attribute should now be stored in the associated identifier;
        // clear any existing value in the deprecated member variable
        if( isSetPosition() )
            unsetPosition();
        setPositionInIdentifier( newPosition );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPositionGen( int newPosition )
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
     * Sets the element position in the associated identifier.
     * @param newPosition
     * @generated NOT
     */
    protected void setPositionInIdentifier( int newPosition )
    {
        DataElementIdentifier identifier = getIdentifier();
        if( identifier == null )
        {
            identifier = DesignFactory.eINSTANCE.createDataElementIdentifier();
            setIdentifier( identifier );
        }

        identifier.setPosition( newPosition );
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
    @Override
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
            int featureID, NotificationChain msgs )
    {
        switch( featureID )
        {
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER:
            return basicSetIdentifier( null, msgs );
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__UI_HINTS:
            return basicSetUiHints( null, msgs );
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
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER:
            return getIdentifier();
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
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER:
            setIdentifier( (DataElementIdentifier) newValue );
            return;
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
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER:
            setIdentifier( (DataElementIdentifier) null );
            return;
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
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES__IDENTIFIER:
            return m_identifier != null;
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
