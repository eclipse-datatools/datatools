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
 * $Id: InputElementAttributesImpl.java,v 1.5 2009/02/12 02:50:20 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices;
import org.eclipse.datatools.connectivity.oda.design.StaticValues;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Element Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getDefaultScalarValue <em>Default Scalar Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getDefaultValues <em>Default Values</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#isOptional <em>Optional</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#isMasksValue <em>Masks Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getStaticValueChoices <em>Static Value Choices</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getDynamicValueChoices <em>Dynamic Value Choices</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementAttributesImpl#getUiHints <em>Ui Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputElementAttributesImpl extends EObjectImpl implements
        InputElementAttributes
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getDefaultScalarValue() <em>Default Scalar Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultScalarValue()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_SCALAR_VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefaultScalarValue() <em>Default Scalar Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultScalarValue()
     * @generated
     * @ordered
     */
    protected String m_defaultScalarValue = DEFAULT_SCALAR_VALUE_EDEFAULT;

    /**
     * The cached value of the '{@link #getDefaultValues() <em>Default Values</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultValues()
     * @generated
     * @ordered
     */
    protected StaticValues m_defaultValues;

    /**
     * The default value of the '{@link #isEditable() <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEditable()
     * @generated
     * @ordered
     */
    protected static final boolean EDITABLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isEditable() <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEditable()
     * @generated
     * @ordered
     */
    protected boolean m_editable = EDITABLE_EDEFAULT;

    /**
     * This is true if the Editable attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_editableESet;

    /**
     * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected static final boolean OPTIONAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected boolean m_optional = OPTIONAL_EDEFAULT;

    /**
     * This is true if the Optional attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_optionalESet;

    /**
     * The default value of the '{@link #isMasksValue() <em>Masks Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMasksValue()
     * @generated
     * @ordered
     */
    protected static final boolean MASKS_VALUE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMasksValue() <em>Masks Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMasksValue()
     * @generated
     * @ordered
     */
    protected boolean m_masksValue = MASKS_VALUE_EDEFAULT;

    /**
     * This is true if the Masks Value attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_masksValueESet;

    /**
     * The cached value of the '{@link #getStaticValueChoices() <em>Static Value Choices</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStaticValueChoices()
     * @generated
     * @ordered
     */
    protected ScalarValueChoices m_staticValueChoices;

    /**
     * The cached value of the '{@link #getDynamicValueChoices() <em>Dynamic Value Choices</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDynamicValueChoices()
     * @generated
     * @ordered
     */
    protected DynamicValuesQuery m_dynamicValueChoices;

    /**
     * The cached value of the '{@link #getUiHints() <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUiHints()
     * @generated
     * @ordered
     */
    protected InputElementUIHints m_uiHints;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InputElementAttributesImpl()
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
        return DesignPackage.Literals.INPUT_ELEMENT_ATTRIBUTES;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#hasValueChoices()
     * @generated NOT
     */
    public boolean hasValueChoices()
    {
        return (getStaticValueChoices() != null || getDynamicValueChoices() != null);
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#setUiPromptStyle(org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle)
     * @generated NOT
     */
    public void setUiPromptStyle( InputPromptControlStyle value )
    {
        // sets attribute in current UIHints, if exists;
        // otherwise, creates a new one
        InputElementUIHints uiHints = getUiHints();
        boolean hasNoUIHints = (uiHints == null);
        if( hasNoUIHints )
            uiHints = DesignFactory.eINSTANCE.createInputElementUIHints();
        uiHints.setPromptStyle( value );

        if( hasNoUIHints )
            setUiHints( uiHints );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getDefaultScalarValueGen()
    {
        return m_defaultScalarValue;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDefaultScalarValue()
     * @generated NOT
     */
    public String getDefaultScalarValue()
    {
        // if already migrated to use a collection, return the first value if available
        if( getDefaultScalarValueGen() == null && getDefaultValuesGen() != null
                && ! getDefaultValuesGen().isEmpty() )
        {
            Object firstValue = getDefaultValuesGen().getValues().get( 0 );
            return (firstValue != null) ? firstValue.toString() : null;
        }
        return getDefaultScalarValueGen();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setDefaultScalarValueGen( String newDefaultScalarValue )
    {
        String oldDefaultScalarValue = m_defaultScalarValue;
        m_defaultScalarValue = newDefaultScalarValue;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE,
                    oldDefaultScalarValue, m_defaultScalarValue ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#setDefaultScalarValue(java.lang.String)
     * @generated NOT
     */
    public void setDefaultScalarValue( String newDefaultScalarValue )
    {
        // migrate data to store in collection instead of the deprecated scalar value variable;
        // adopts original behavior of setting a single default value,
        // overwriting existing default value(s), if any
        StaticValues defaultValues = getDefaultValuesGen();
        if( defaultValues == null )
        {
            defaultValues = DesignFactory.eINSTANCE.createStaticValues();
            setDefaultValuesGen( defaultValues );
        }
        else
            defaultValues.clear(); // overwrites existing values

        defaultValues.add( newDefaultScalarValue );
        setDefaultScalarValueGen( null ); // overwrites existing value, if any
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StaticValues getDefaultValuesGen()
    {
        return m_defaultValues;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDefaultValues()
     * @generated NOT
     */
    public StaticValues getDefaultValues()
    {
        // the collection of default values if exists, overrides the deprecated defaultScalarValue value;
        // if no collection exists, migrates the value in deprecated scalar value variable 
        // into a new collection
        if( getDefaultValuesGen() == null && getDefaultScalarValueGen() != null )
        {
            return migrateDefaultScalarValue();
        }

        return getDefaultValuesGen();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDefaultValues(
            StaticValues newDefaultValues, NotificationChain msgs )
    {
        StaticValues oldDefaultValues = m_defaultValues;
        m_defaultValues = newDefaultValues;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES,
                    oldDefaultValues, newDefaultValues );
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
    protected void setDefaultValuesGen( StaticValues newDefaultValues )
    {
        if( newDefaultValues != m_defaultValues )
        {
            NotificationChain msgs = null;
            if( m_defaultValues != null )
                msgs = ((InternalEObject) m_defaultValues)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES,
                                null, msgs );
            if( newDefaultValues != null )
                msgs = ((InternalEObject) newDefaultValues)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES,
                                null, msgs );
            msgs = basicSetDefaultValues( newDefaultValues, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES,
                    newDefaultValues, newDefaultValues ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#setDefaultValues(org.eclipse.datatools.connectivity.oda.design.StaticValuesType)
     * @generated NOT
     */
    public void setDefaultValues( StaticValues newDefaultValues )
    {
        setDefaultValuesGen( newDefaultValues );
        setDefaultScalarValueGen( null ); // using collection, overwrites existing value, if any
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDefaultValueCount()
     * @generated NOT
     */
    public int getDefaultValueCount()
    {
        StaticValues defaultValues = getDefaultValuesGen();
        if( defaultValues != null )
            return defaultValues.count();

        return (getDefaultScalarValueGen() != null) ? 1 : 0;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#addDefaultValue(java.lang.Object)
     * @generated NOT
     */
    public void addDefaultValue( Object aValue )
    {
        StaticValues defaultValues = getDefaultValuesGen();
        if( defaultValues == null )
        {
            defaultValues = migrateDefaultScalarValue();
        }

        defaultValues.add( aValue );
    }

    private StaticValues migrateDefaultScalarValue()
    {
        StaticValues defaultValues = DesignFactory.eINSTANCE.createStaticValues();
        setDefaultValuesGen( defaultValues );

        // migrate existing default value, if any, to the new collection
        if( getDefaultScalarValueGen() != null )
        {
            defaultValues.add( getDefaultScalarValueGen() );
            setDefaultScalarValueGen( null );
        }
        return defaultValues;
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isEditable()
    {
        return m_editable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEditable( boolean newEditable )
    {
        boolean oldEditable = m_editable;
        m_editable = newEditable;
        boolean oldEditableESet = m_editableESet;
        m_editableESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE,
                    oldEditable, m_editable, !oldEditableESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetEditable()
    {
        boolean oldEditable = m_editable;
        boolean oldEditableESet = m_editableESet;
        m_editable = EDITABLE_EDEFAULT;
        m_editableESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE,
                    oldEditable, EDITABLE_EDEFAULT, oldEditableESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetEditable()
    {
        return m_editableESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isOptional()
    {
        return m_optional;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOptional( boolean newOptional )
    {
        boolean oldOptional = m_optional;
        m_optional = newOptional;
        boolean oldOptionalESet = m_optionalESet;
        m_optionalESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL,
                    oldOptional, m_optional, !oldOptionalESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetOptional()
    {
        boolean oldOptional = m_optional;
        boolean oldOptionalESet = m_optionalESet;
        m_optional = OPTIONAL_EDEFAULT;
        m_optionalESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL,
                    oldOptional, OPTIONAL_EDEFAULT, oldOptionalESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetOptional()
    {
        return m_optionalESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isMasksValue()
    {
        return m_masksValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMasksValue( boolean newMasksValue )
    {
        boolean oldMasksValue = m_masksValue;
        m_masksValue = newMasksValue;
        boolean oldMasksValueESet = m_masksValueESet;
        m_masksValueESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE,
                    oldMasksValue, m_masksValue, !oldMasksValueESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMasksValue()
    {
        boolean oldMasksValue = m_masksValue;
        boolean oldMasksValueESet = m_masksValueESet;
        m_masksValue = MASKS_VALUE_EDEFAULT;
        m_masksValueESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE,
                    oldMasksValue, MASKS_VALUE_EDEFAULT, oldMasksValueESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMasksValue()
    {
        return m_masksValueESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScalarValueChoices getStaticValueChoices()
    {
        return m_staticValueChoices;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStaticValueChoices(
            ScalarValueChoices newStaticValueChoices, NotificationChain msgs )
    {
        ScalarValueChoices oldStaticValueChoices = m_staticValueChoices;
        m_staticValueChoices = newStaticValueChoices;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES,
                    oldStaticValueChoices, newStaticValueChoices );
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
    public void setStaticValueChoices( ScalarValueChoices newStaticValueChoices )
    {
        if( newStaticValueChoices != m_staticValueChoices )
        {
            NotificationChain msgs = null;
            if( m_staticValueChoices != null )
                msgs = ((InternalEObject) m_staticValueChoices)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES,
                                null, msgs );
            if( newStaticValueChoices != null )
                msgs = ((InternalEObject) newStaticValueChoices)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES,
                                null, msgs );
            msgs = basicSetStaticValueChoices( newStaticValueChoices, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES,
                    newStaticValueChoices, newStaticValueChoices ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicValuesQuery getDynamicValueChoices()
    {
        return m_dynamicValueChoices;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDynamicValueChoices(
            DynamicValuesQuery newDynamicValueChoices, NotificationChain msgs )
    {
        DynamicValuesQuery oldDynamicValueChoices = m_dynamicValueChoices;
        m_dynamicValueChoices = newDynamicValueChoices;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES,
                    oldDynamicValueChoices, newDynamicValueChoices );
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
    public void setDynamicValueChoices(
            DynamicValuesQuery newDynamicValueChoices )
    {
        if( newDynamicValueChoices != m_dynamicValueChoices )
        {
            NotificationChain msgs = null;
            if( m_dynamicValueChoices != null )
                msgs = ((InternalEObject) m_dynamicValueChoices)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES,
                                null, msgs );
            if( newDynamicValueChoices != null )
                msgs = ((InternalEObject) newDynamicValueChoices)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES,
                                null, msgs );
            msgs = basicSetDynamicValueChoices( newDynamicValueChoices, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES,
                    newDynamicValueChoices, newDynamicValueChoices ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputElementUIHints getUiHints()
    {
        return m_uiHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUiHints( InputElementUIHints newUiHints,
            NotificationChain msgs )
    {
        InputElementUIHints oldUiHints = m_uiHints;
        m_uiHints = newUiHints;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS,
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
    public void setUiHints( InputElementUIHints newUiHints )
    {
        if( newUiHints != m_uiHints )
        {
            NotificationChain msgs = null;
            if( m_uiHints != null )
                msgs = ((InternalEObject) m_uiHints)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS,
                                null, msgs );
            if( newUiHints != null )
                msgs = ((InternalEObject) newUiHints)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS,
                                null, msgs );
            msgs = basicSetUiHints( newUiHints, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS,
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
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES:
            return basicSetDefaultValues( null, msgs );
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
            return basicSetStaticValueChoices( null, msgs );
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
            return basicSetDynamicValueChoices( null, msgs );
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
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
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE:
            return getDefaultScalarValue();
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES:
            return getDefaultValues();
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE:
            return isEditable() ? Boolean.TRUE : Boolean.FALSE;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL:
            return isOptional() ? Boolean.TRUE : Boolean.FALSE;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE:
            return isMasksValue() ? Boolean.TRUE : Boolean.FALSE;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
            return getStaticValueChoices();
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
            return getDynamicValueChoices();
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
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
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE:
            setDefaultScalarValue( (String) newValue );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES:
            setDefaultValues( (StaticValues) newValue );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE:
            setEditable( ((Boolean) newValue).booleanValue() );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL:
            setOptional( ((Boolean) newValue).booleanValue() );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE:
            setMasksValue( ((Boolean) newValue).booleanValue() );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
            setStaticValueChoices( (ScalarValueChoices) newValue );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
            setDynamicValueChoices( (DynamicValuesQuery) newValue );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
            setUiHints( (InputElementUIHints) newValue );
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
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE:
            setDefaultScalarValue( DEFAULT_SCALAR_VALUE_EDEFAULT );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES:
            setDefaultValues( (StaticValues) null );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE:
            unsetEditable();
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL:
            unsetOptional();
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE:
            unsetMasksValue();
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
            setStaticValueChoices( (ScalarValueChoices) null );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
            setDynamicValueChoices( (DynamicValuesQuery) null );
            return;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
            setUiHints( (InputElementUIHints) null );
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
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE:
            return DEFAULT_SCALAR_VALUE_EDEFAULT == null ? m_defaultScalarValue != null
                    : !DEFAULT_SCALAR_VALUE_EDEFAULT
                            .equals( m_defaultScalarValue );
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES:
            return m_defaultValues != null;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__EDITABLE:
            return isSetEditable();
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__OPTIONAL:
            return isSetOptional();
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE:
            return isSetMasksValue();
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES:
            return m_staticValueChoices != null;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES:
            return m_dynamicValueChoices != null;
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES__UI_HINTS:
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
        result.append( " (defaultScalarValue: " ); //$NON-NLS-1$
        result.append( m_defaultScalarValue );
        result.append( ", editable: " ); //$NON-NLS-1$
        if( m_editableESet )
            result.append( m_editable );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", optional: " ); //$NON-NLS-1$
        if( m_optionalESet )
            result.append( m_optional );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", masksValue: " ); //$NON-NLS-1$
        if( m_masksValueESet )
            result.append( m_masksValue );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //InputElementAttributesImpl
