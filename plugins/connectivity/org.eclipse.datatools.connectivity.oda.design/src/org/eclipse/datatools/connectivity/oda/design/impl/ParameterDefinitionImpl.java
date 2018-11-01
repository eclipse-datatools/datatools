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
 * $Id: ParameterDefinitionImpl.java,v 1.5 2009/04/14 02:13:18 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes;
import org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterFields;
import org.eclipse.datatools.connectivity.oda.design.ParameterMode;
import org.eclipse.datatools.connectivity.oda.design.StaticValues;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ParameterDefinitionImpl#getInOutMode <em>In Out Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ParameterDefinitionImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ParameterDefinitionImpl#getInputAttributes <em>Input Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ParameterDefinitionImpl#getOutputUsageHints <em>Output Usage Hints</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ParameterDefinitionImpl#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterDefinitionImpl extends EObjectImpl implements
        ParameterDefinition
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getInOutMode() <em>In Out Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInOutMode()
     * @generated
     * @ordered
     */
    protected static final ParameterMode IN_OUT_MODE_EDEFAULT = ParameterMode.IN_LITERAL;

    /**
     * The cached value of the '{@link #getInOutMode() <em>In Out Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInOutMode()
     * @generated
     * @ordered
     */
    protected ParameterMode m_inOutMode = IN_OUT_MODE_EDEFAULT;

    /**
     * This is true if the In Out Mode attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_inOutModeESet;

    /**
     * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributes()
     * @generated
     * @ordered
     */
    protected DataElementAttributes m_attributes;

    /**
     * The cached value of the '{@link #getInputAttributes() <em>Input Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInputAttributes()
     * @generated
     * @ordered
     */
    protected InputParameterAttributes m_inputAttributes;

    /**
     * The cached value of the '{@link #getOutputUsageHints() <em>Output Usage Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutputUsageHints()
     * @generated
     * @ordered
     */
    protected OutputElementAttributes m_outputUsageHints;

    /**
     * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFields()
     * @generated
     * @ordered
     */
    protected ParameterFields m_fields;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ParameterDefinitionImpl()
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
        return DesignPackage.Literals.PARAMETER_DEFINITION;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#isInput()
     * @generated NOT
     */
    public boolean isInput()
    {
        int paramMode = getInOutMode().getValue();
        return (paramMode == ParameterMode.IN || paramMode == ParameterMode.IN_OUT);
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#isOutput()
     * @generated NOT
     */
    public boolean isOutput()
    {
        int paramMode = getInOutMode().getValue();
        return (paramMode == ParameterMode.OUT || paramMode == ParameterMode.IN_OUT);
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#isScalar()
     * @generated NOT
     */
    public boolean isScalar()
    {
        return (getFields() == null || getFields().getFieldCollection()
                .isEmpty());
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getDefaultScalarValue()
     * @generated NOT
     */
    public String getDefaultScalarValue()
    {
        if( !isInput() || !isScalar() )
            return null;
        if( getDefaultValueCount() == 0 )
            return null;

        Object firstValue = getDefaultValues().getValues().get( 0 );
        return (firstValue != null) ? firstValue.toString() : null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#setDefaultScalarValue(java.lang.String)
     * @generated NOT
     */
    public void setDefaultScalarValue( String value )
    {
        if( !isInput() || !isScalar() )
            return; // ignore specified value

        InputElementAttributes inputAttributes = getEditableInputElementAttributes();
        assert (inputAttributes != null);

        // use deprecated method that takes care of migrating value to a collection
        inputAttributes.setDefaultScalarValue( value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getDefaultValues()
     * @generated NOT
     */
    public StaticValues getDefaultValues()
    {
        if( !isInput() )
            return null;
        if( getInputAttributes() == null
                || getInputAttributes().getElementAttributes() == null )
            return null;

        return getInputAttributes().getElementAttributes().getDefaultValues();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getDefaultValueCount()
     * @generated NOT
     */
    public int getDefaultValueCount()
    {
        StaticValues defaultValues = getDefaultValues();
        return (defaultValues == null) ? 0 : defaultValues.count();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#addDefaultValue(java.lang.Object)
     * @generated NOT
     */
    public void addDefaultValue( Object aValue )
    {
        if( !isInput() )
            return; // ignore specified value

        InputElementAttributes inputAttributes = getEditableInputElementAttributes();

        inputAttributes.addDefaultValue( aValue );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getEditableInputElementAttributes()
     * @generated NOT
     */
    public InputElementAttributes getEditableInputElementAttributes()
    {
        InputParameterAttributes paramAttributes = getInputAttributes();
        if( paramAttributes == null )
        {
            paramAttributes = DesignFactory.eINSTANCE
                    .createInputParameterAttributes();
            setInputAttributes( paramAttributes );
        }

        InputElementAttributes inputAttributes = paramAttributes
                .getElementAttributes();
        if( inputAttributes == null )
        {
            inputAttributes = DesignFactory.eINSTANCE
                    .createInputElementAttributes();
            paramAttributes.setElementAttributes( inputAttributes );
        }
        return inputAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterMode getInOutMode()
    {
        return m_inOutMode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInOutMode( ParameterMode newInOutMode )
    {
        ParameterMode oldInOutMode = m_inOutMode;
        m_inOutMode = newInOutMode == null ? IN_OUT_MODE_EDEFAULT
                : newInOutMode;
        boolean oldInOutModeESet = m_inOutModeESet;
        m_inOutModeESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__IN_OUT_MODE,
                    oldInOutMode, m_inOutMode, !oldInOutModeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetInOutMode()
    {
        ParameterMode oldInOutMode = m_inOutMode;
        boolean oldInOutModeESet = m_inOutModeESet;
        m_inOutMode = IN_OUT_MODE_EDEFAULT;
        m_inOutModeESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.PARAMETER_DEFINITION__IN_OUT_MODE,
                    oldInOutMode, IN_OUT_MODE_EDEFAULT, oldInOutModeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetInOutMode()
    {
        return m_inOutModeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataElementAttributes getAttributes()
    {
        return m_attributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAttributes(
            DataElementAttributes newAttributes, NotificationChain msgs )
    {
        DataElementAttributes oldAttributes = m_attributes;
        m_attributes = newAttributes;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES,
                    oldAttributes, newAttributes );
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
    public void setAttributes( DataElementAttributes newAttributes )
    {
        if( newAttributes != m_attributes )
        {
            NotificationChain msgs = null;
            if( m_attributes != null )
                msgs = ((InternalEObject) m_attributes)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES,
                                null, msgs );
            if( newAttributes != null )
                msgs = ((InternalEObject) newAttributes)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES,
                                null, msgs );
            msgs = basicSetAttributes( newAttributes, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES,
                    newAttributes, newAttributes ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputParameterAttributes getInputAttributes()
    {
        return m_inputAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInputAttributes(
            InputParameterAttributes newInputAttributes, NotificationChain msgs )
    {
        InputParameterAttributes oldInputAttributes = m_inputAttributes;
        m_inputAttributes = newInputAttributes;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES,
                    oldInputAttributes, newInputAttributes );
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
    public void setInputAttributes( InputParameterAttributes newInputAttributes )
    {
        if( newInputAttributes != m_inputAttributes )
        {
            NotificationChain msgs = null;
            if( m_inputAttributes != null )
                msgs = ((InternalEObject) m_inputAttributes)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES,
                                null, msgs );
            if( newInputAttributes != null )
                msgs = ((InternalEObject) newInputAttributes)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES,
                                null, msgs );
            msgs = basicSetInputAttributes( newInputAttributes, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES,
                    newInputAttributes, newInputAttributes ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputElementAttributes getOutputUsageHints()
    {
        return m_outputUsageHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOutputUsageHints(
            OutputElementAttributes newOutputUsageHints, NotificationChain msgs )
    {
        OutputElementAttributes oldOutputUsageHints = m_outputUsageHints;
        m_outputUsageHints = newOutputUsageHints;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS,
                    oldOutputUsageHints, newOutputUsageHints );
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
    public void setOutputUsageHints( OutputElementAttributes newOutputUsageHints )
    {
        if( newOutputUsageHints != m_outputUsageHints )
        {
            NotificationChain msgs = null;
            if( m_outputUsageHints != null )
                msgs = ((InternalEObject) m_outputUsageHints)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS,
                                null, msgs );
            if( newOutputUsageHints != null )
                msgs = ((InternalEObject) newOutputUsageHints)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS,
                                null, msgs );
            msgs = basicSetOutputUsageHints( newOutputUsageHints, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS,
                    newOutputUsageHints, newOutputUsageHints ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterFields getFields()
    {
        return m_fields;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFields( ParameterFields newFields,
            NotificationChain msgs )
    {
        ParameterFields oldFields = m_fields;
        m_fields = newFields;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__FIELDS, oldFields,
                    newFields );
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
    public void setFields( ParameterFields newFields )
    {
        if( newFields != m_fields )
        {
            NotificationChain msgs = null;
            if( m_fields != null )
                msgs = ((InternalEObject) m_fields).eInverseRemove( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.PARAMETER_DEFINITION__FIELDS,
                        null, msgs );
            if( newFields != null )
                msgs = ((InternalEObject) newFields).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.PARAMETER_DEFINITION__FIELDS,
                        null, msgs );
            msgs = basicSetFields( newFields, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PARAMETER_DEFINITION__FIELDS, newFields,
                    newFields ) );
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
        case DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES:
            return basicSetAttributes( null, msgs );
        case DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES:
            return basicSetInputAttributes( null, msgs );
        case DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS:
            return basicSetOutputUsageHints( null, msgs );
        case DesignPackage.PARAMETER_DEFINITION__FIELDS:
            return basicSetFields( null, msgs );
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
        case DesignPackage.PARAMETER_DEFINITION__IN_OUT_MODE:
            return getInOutMode();
        case DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES:
            return getAttributes();
        case DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES:
            return getInputAttributes();
        case DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS:
            return getOutputUsageHints();
        case DesignPackage.PARAMETER_DEFINITION__FIELDS:
            return getFields();
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
        case DesignPackage.PARAMETER_DEFINITION__IN_OUT_MODE:
            setInOutMode( (ParameterMode) newValue );
            return;
        case DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES:
            setAttributes( (DataElementAttributes) newValue );
            return;
        case DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES:
            setInputAttributes( (InputParameterAttributes) newValue );
            return;
        case DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS:
            setOutputUsageHints( (OutputElementAttributes) newValue );
            return;
        case DesignPackage.PARAMETER_DEFINITION__FIELDS:
            setFields( (ParameterFields) newValue );
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
        case DesignPackage.PARAMETER_DEFINITION__IN_OUT_MODE:
            unsetInOutMode();
            return;
        case DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES:
            setAttributes( (DataElementAttributes) null );
            return;
        case DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES:
            setInputAttributes( (InputParameterAttributes) null );
            return;
        case DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS:
            setOutputUsageHints( (OutputElementAttributes) null );
            return;
        case DesignPackage.PARAMETER_DEFINITION__FIELDS:
            setFields( (ParameterFields) null );
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
        case DesignPackage.PARAMETER_DEFINITION__IN_OUT_MODE:
            return isSetInOutMode();
        case DesignPackage.PARAMETER_DEFINITION__ATTRIBUTES:
            return m_attributes != null;
        case DesignPackage.PARAMETER_DEFINITION__INPUT_ATTRIBUTES:
            return m_inputAttributes != null;
        case DesignPackage.PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS:
            return m_outputUsageHints != null;
        case DesignPackage.PARAMETER_DEFINITION__FIELDS:
            return m_fields != null;
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
        result.append( " (inOutMode: " ); //$NON-NLS-1$
        if( m_inOutModeESet )
            result.append( m_inOutMode );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //ParameterDefinitionImpl
