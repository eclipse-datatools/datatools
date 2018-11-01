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
 * $Id: ParameterFieldDefinitionImpl.java,v 1.2 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterFields;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Field Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ParameterFieldDefinitionImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ParameterFieldDefinitionImpl#getInputAttributes <em>Input Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ParameterFieldDefinitionImpl#getOutputUsageHints <em>Output Usage Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterFieldDefinitionImpl extends EObjectImpl implements
        ParameterFieldDefinition
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

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
    protected InputElementAttributes m_inputAttributes;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ParameterFieldDefinitionImpl()
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
        return DesignPackage.Literals.PARAMETER_FIELD_DEFINITION;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition#isInput()
     */
    public boolean isInput()
    {
        // derived from parent's in/out mode
        return getParent().isInput();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition#isOutput()
     */
    public boolean isOutput()
    {
        // derived from parent's in/out mode
        return getParent().isOutput();
    }

    private ParameterDefinition getParent()
    {
        EObject myContainer = eContainer();
        assert (myContainer instanceof ParameterFields);

        EObject topLevelParam = myContainer.eContainer();
        assert (topLevelParam instanceof ParameterDefinition);
        return (ParameterDefinition) topLevelParam;
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
                    DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES,
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
                                        - DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES,
                                null, msgs );
            if( newAttributes != null )
                msgs = ((InternalEObject) newAttributes)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES,
                                null, msgs );
            msgs = basicSetAttributes( newAttributes, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES,
                    newAttributes, newAttributes ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputElementAttributes getInputAttributes()
    {
        return m_inputAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInputAttributes(
            InputElementAttributes newInputAttributes, NotificationChain msgs )
    {
        InputElementAttributes oldInputAttributes = m_inputAttributes;
        m_inputAttributes = newInputAttributes;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES,
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
    public void setInputAttributes( InputElementAttributes newInputAttributes )
    {
        if( newInputAttributes != m_inputAttributes )
        {
            NotificationChain msgs = null;
            if( m_inputAttributes != null )
                msgs = ((InternalEObject) m_inputAttributes)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES,
                                null, msgs );
            if( newInputAttributes != null )
                msgs = ((InternalEObject) newInputAttributes)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES,
                                null, msgs );
            msgs = basicSetInputAttributes( newInputAttributes, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES,
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
            ENotificationImpl notification = new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS,
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
                                        - DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS,
                                null, msgs );
            if( newOutputUsageHints != null )
                msgs = ((InternalEObject) newOutputUsageHints)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS,
                                null, msgs );
            msgs = basicSetOutputUsageHints( newOutputUsageHints, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS,
                    newOutputUsageHints, newOutputUsageHints ) );
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
        case DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES:
            return basicSetAttributes( null, msgs );
        case DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES:
            return basicSetInputAttributes( null, msgs );
        case DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS:
            return basicSetOutputUsageHints( null, msgs );
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
        case DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES:
            return getAttributes();
        case DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES:
            return getInputAttributes();
        case DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS:
            return getOutputUsageHints();
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
        case DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES:
            setAttributes( (DataElementAttributes) newValue );
            return;
        case DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES:
            setInputAttributes( (InputElementAttributes) newValue );
            return;
        case DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS:
            setOutputUsageHints( (OutputElementAttributes) newValue );
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
        case DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES:
            setAttributes( (DataElementAttributes) null );
            return;
        case DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES:
            setInputAttributes( (InputElementAttributes) null );
            return;
        case DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS:
            setOutputUsageHints( (OutputElementAttributes) null );
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
        case DesignPackage.PARAMETER_FIELD_DEFINITION__ATTRIBUTES:
            return m_attributes != null;
        case DesignPackage.PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES:
            return m_inputAttributes != null;
        case DesignPackage.PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS:
            return m_outputUsageHints != null;
        }
        return super.eIsSet( featureID );
    }

} //ParameterFieldDefinitionImpl
