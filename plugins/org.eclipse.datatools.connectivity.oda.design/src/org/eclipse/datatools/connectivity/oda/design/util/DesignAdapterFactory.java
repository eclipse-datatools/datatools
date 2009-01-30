/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
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
 * $Id: DesignAdapterFactory.java,v 1.3 2008/07/23 04:12:28 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.util;

import org.eclipse.datatools.connectivity.oda.design.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage
 * @generated
 */
public class DesignAdapterFactory extends AdapterFactoryImpl
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static DesignPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignAdapterFactory()
    {
        if( modelPackage == null )
        {
            modelPackage = DesignPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType( Object object )
    {
        if( object == modelPackage )
        {
            return true;
        }
        if( object instanceof EObject )
        {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DesignSwitch<Adapter> modelSwitch = new DesignSwitch<Adapter>()
    {
        @Override
        public Adapter caseAndExpression( AndExpression object )
        {
            return createAndExpressionAdapter();
        }

        @Override
        public Adapter caseAtomicExpressionContext(
                AtomicExpressionContext object )
        {
            return createAtomicExpressionContextAdapter();
        }

        @Override
        public Adapter caseAxisAttributes( AxisAttributes object )
        {
            return createAxisAttributesAdapter();
        }

        @Override
        public Adapter caseColumnDefinition( ColumnDefinition object )
        {
            return createColumnDefinitionAdapter();
        }

        @Override
        public Adapter caseCompositeFilterExpression(
                CompositeFilterExpression object )
        {
            return createCompositeFilterExpressionAdapter();
        }

        @Override
        public Adapter caseCustomExpression( CustomExpression object )
        {
            return createCustomExpressionAdapter();
        }

        @Override
        public Adapter caseDataAccessDesign( DataAccessDesign object )
        {
            return createDataAccessDesignAdapter();
        }

        @Override
        public Adapter caseDataElementAttributes( DataElementAttributes object )
        {
            return createDataElementAttributesAdapter();
        }

        @Override
        public Adapter caseDataElementUIHints( DataElementUIHints object )
        {
            return createDataElementUIHintsAdapter();
        }

        @Override
        public Adapter caseDataSetDesign( DataSetDesign object )
        {
            return createDataSetDesignAdapter();
        }

        @Override
        public Adapter caseDataSetParameters( DataSetParameters object )
        {
            return createDataSetParametersAdapter();
        }

        @Override
        public Adapter caseDataSetQuery( DataSetQuery object )
        {
            return createDataSetQueryAdapter();
        }

        @Override
        public Adapter caseDataSourceDesign( DataSourceDesign object )
        {
            return createDataSourceDesignAdapter();
        }

        @Override
        public Adapter caseDesignerState( DesignerState object )
        {
            return createDesignerStateAdapter();
        }

        @Override
        public Adapter caseDesignerStateContent( DesignerStateContent object )
        {
            return createDesignerStateContentAdapter();
        }

        @Override
        public Adapter caseDesignSessionRequest( DesignSessionRequest object )
        {
            return createDesignSessionRequestAdapter();
        }

        @Override
        public Adapter caseDesignSessionResponse( DesignSessionResponse object )
        {
            return createDesignSessionResponseAdapter();
        }

        @Override
        public Adapter caseDocumentRoot( DocumentRoot object )
        {
            return createDocumentRootAdapter();
        }

        @Override
        public Adapter caseDynamicExpression( DynamicExpression object )
        {
            return createDynamicExpressionAdapter();
        }

        @Override
        public Adapter caseDynamicValuesQuery( DynamicValuesQuery object )
        {
            return createDynamicValuesQueryAdapter();
        }

        @Override
        public Adapter caseFilterExpression( FilterExpression object )
        {
            return createFilterExpressionAdapter();
        }

        @Override
        public Adapter caseFilterExpressionArguments(
                FilterExpressionArguments object )
        {
            return createFilterExpressionArgumentsAdapter();
        }

        @Override
        public Adapter caseFilterExpressionVariable(
                FilterExpressionVariable object )
        {
            return createFilterExpressionVariableAdapter();
        }

        @Override
        public Adapter caseFilterParameterDefinition(
                FilterParameterDefinition object )
        {
            return createFilterParameterDefinitionAdapter();
        }

        @Override
        public Adapter caseFilterParameters( FilterParameters object )
        {
            return createFilterParametersAdapter();
        }

        @Override
        public Adapter caseInputElementAttributes( InputElementAttributes object )
        {
            return createInputElementAttributesAdapter();
        }

        @Override
        public Adapter caseInputElementUIHints( InputElementUIHints object )
        {
            return createInputElementUIHintsAdapter();
        }

        @Override
        public Adapter caseInputParameterAttributes(
                InputParameterAttributes object )
        {
            return createInputParameterAttributesAdapter();
        }

        @Override
        public Adapter caseInputParameterUIHints( InputParameterUIHints object )
        {
            return createInputParameterUIHintsAdapter();
        }

        @Override
        public Adapter caseLocale( Locale object )
        {
            return createLocaleAdapter();
        }

        @Override
        public Adapter caseNameValuePair( NameValuePair object )
        {
            return createNameValuePairAdapter();
        }

        @Override
        public Adapter caseNotExpression( NotExpression object )
        {
            return createNotExpressionAdapter();
        }

        @Override
        public Adapter caseOdaDesignSession( OdaDesignSession object )
        {
            return createOdaDesignSessionAdapter();
        }

        @Override
        public Adapter caseOrExpression( OrExpression object )
        {
            return createOrExpressionAdapter();
        }

        @Override
        public Adapter caseOutputElementAttributes(
                OutputElementAttributes object )
        {
            return createOutputElementAttributesAdapter();
        }

        @Override
        public Adapter caseParameterDefinition( ParameterDefinition object )
        {
            return createParameterDefinitionAdapter();
        }

        @Override
        public Adapter caseParameterFieldDefinition(
                ParameterFieldDefinition object )
        {
            return createParameterFieldDefinitionAdapter();
        }

        @Override
        public Adapter caseParameterFields( ParameterFields object )
        {
            return createParameterFieldsAdapter();
        }

        @Override
        public Adapter caseProperties( Properties object )
        {
            return createPropertiesAdapter();
        }

        @Override
        public Adapter caseProperty( Property object )
        {
            return createPropertyAdapter();
        }

        @Override
        public Adapter casePropertyAttributes( PropertyAttributes object )
        {
            return createPropertyAttributesAdapter();
        }

        @Override
        public Adapter caseResourceIdentifiers( ResourceIdentifiers object )
        {
            return createResourceIdentifiersAdapter();
        }

        @Override
        public Adapter caseResultSetColumns( ResultSetColumns object )
        {
            return createResultSetColumnsAdapter();
        }

        @Override
        public Adapter caseResultSetDefinition( ResultSetDefinition object )
        {
            return createResultSetDefinitionAdapter();
        }

        @Override
        public Adapter caseResultSets( ResultSets object )
        {
            return createResultSetsAdapter();
        }

        @Override
        public Adapter caseScalarValueChoices( ScalarValueChoices object )
        {
            return createScalarValueChoicesAdapter();
        }

        @Override
        public Adapter caseScalarValueDefinition( ScalarValueDefinition object )
        {
            return createScalarValueDefinitionAdapter();
        }

        @Override
        public Adapter caseValueFormatHints( ValueFormatHints object )
        {
            return createValueFormatHintsAdapter();
        }

        @Override
        public Adapter defaultCase( EObject object )
        {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter( Notifier target )
    {
        return modelSwitch.doSwitch( (EObject) target );
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.AndExpression <em>And Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.AndExpression
     * @generated
     */
    public Adapter createAndExpressionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext <em>Atomic Expression Context</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext
     * @generated
     */
    public Adapter createAtomicExpressionContextAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes <em>Axis Attributes</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.AxisAttributes
     * @generated
     */
    public Adapter createAxisAttributesAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ColumnDefinition <em>Column Definition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ColumnDefinition
     * @generated
     */
    public Adapter createColumnDefinitionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.CompositeFilterExpression <em>Composite Filter Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.CompositeFilterExpression
     * @generated
     */
    public Adapter createCompositeFilterExpressionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.CustomExpression <em>Custom Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.CustomExpression
     * @generated
     */
    public Adapter createCustomExpressionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DataAccessDesign <em>Data Access Design</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DataAccessDesign
     * @generated
     */
    public Adapter createDataAccessDesignAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes <em>Data Element Attributes</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementAttributes
     * @generated
     */
    public Adapter createDataElementAttributesAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DataElementUIHints <em>Data Element UI Hints</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints
     * @generated
     */
    public Adapter createDataElementUIHintsAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign <em>Data Set Design</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetDesign
     * @generated
     */
    public Adapter createDataSetDesignAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DataSetParameters <em>Data Set Parameters</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetParameters
     * @generated
     */
    public Adapter createDataSetParametersAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DataSetQuery <em>Data Set Query</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DataSetQuery
     * @generated
     */
    public Adapter createDataSetQueryAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign <em>Data Source Design</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DataSourceDesign
     * @generated
     */
    public Adapter createDataSourceDesignAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DesignerState <em>Designer State</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignerState
     * @generated
     */
    public Adapter createDesignerStateAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DesignerStateContent <em>Designer State Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignerStateContent
     * @generated
     */
    public Adapter createDesignerStateContentAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest <em>Session Request</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest
     * @generated
     */
    public Adapter createDesignSessionRequestAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse <em>Session Response</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse
     * @generated
     */
    public Adapter createDesignSessionResponseAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DocumentRoot
     * @generated
     */
    public Adapter createDocumentRootAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DynamicExpression <em>Dynamic Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicExpression
     * @generated
     */
    public Adapter createDynamicExpressionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery <em>Dynamic Values Query</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery
     * @generated
     */
    public Adapter createDynamicValuesQueryAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.FilterExpression <em>Filter Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.FilterExpression
     * @generated
     */
    public Adapter createFilterExpressionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.FilterExpressionArguments <em>Filter Expression Arguments</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.FilterExpressionArguments
     * @generated
     */
    public Adapter createFilterExpressionArgumentsAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.FilterExpressionVariable <em>Filter Expression Variable</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.FilterExpressionVariable
     * @generated
     */
    public Adapter createFilterExpressionVariableAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.FilterParameterDefinition <em>Filter Parameter Definition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.FilterParameterDefinition
     * @generated
     */
    public Adapter createFilterParameterDefinitionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.FilterParameters <em>Filter Parameters</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.FilterParameters
     * @generated
     */
    public Adapter createFilterParametersAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes <em>Input Element Attributes</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementAttributes
     * @generated
     */
    public Adapter createInputElementAttributesAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.InputElementUIHints <em>Input Element UI Hints</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.InputElementUIHints
     * @generated
     */
    public Adapter createInputElementUIHintsAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes <em>Input Parameter Attributes</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes
     * @generated
     */
    public Adapter createInputParameterAttributesAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints <em>Input Parameter UI Hints</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints
     * @generated
     */
    public Adapter createInputParameterUIHintsAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.Locale <em>Locale</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.Locale
     * @generated
     */
    public Adapter createLocaleAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.NameValuePair <em>Name Value Pair</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.NameValuePair
     * @generated
     */
    public Adapter createNameValuePairAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.NotExpression <em>Not Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.NotExpression
     * @generated
     */
    public Adapter createNotExpressionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession <em>Oda Design Session</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.OdaDesignSession
     * @generated
     */
    public Adapter createOdaDesignSessionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.OrExpression <em>Or Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.OrExpression
     * @generated
     */
    public Adapter createOrExpressionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes <em>Output Element Attributes</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes
     * @generated
     */
    public Adapter createOutputElementAttributesAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition <em>Parameter Definition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterDefinition
     * @generated
     */
    public Adapter createParameterDefinitionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition <em>Parameter Field Definition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition
     * @generated
     */
    public Adapter createParameterFieldDefinitionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ParameterFields <em>Parameter Fields</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterFields
     * @generated
     */
    public Adapter createParameterFieldsAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.Properties <em>Properties</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.Properties
     * @generated
     */
    public Adapter createPropertiesAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.Property <em>Property</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.Property
     * @generated
     */
    public Adapter createPropertyAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.PropertyAttributes <em>Property Attributes</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.PropertyAttributes
     * @generated
     */
    public Adapter createPropertyAttributesAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers <em>Resource Identifiers</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers
     * @generated
     */
    public Adapter createResourceIdentifiersAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ResultSetColumns <em>Result Set Columns</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSetColumns
     * @generated
     */
    public Adapter createResultSetColumnsAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition <em>Result Set Definition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition
     * @generated
     */
    public Adapter createResultSetDefinitionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ResultSets <em>Result Sets</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ResultSets
     * @generated
     */
    public Adapter createResultSetsAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices <em>Scalar Value Choices</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices
     * @generated
     */
    public Adapter createScalarValueChoicesAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition <em>Scalar Value Definition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition
     * @generated
     */
    public Adapter createScalarValueDefinitionAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.connectivity.oda.design.ValueFormatHints <em>Value Format Hints</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.connectivity.oda.design.ValueFormatHints
     * @generated
     */
    public Adapter createValueFormatHintsAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter()
    {
        return null;
    }

} //DesignAdapterFactory
