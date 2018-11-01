/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
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
 * $Id: DesignSwitch.java,v 1.10 2010/06/10 23:40:04 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.util;

import java.util.List;

import org.eclipse.datatools.connectivity.oda.design.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage
 * @generated
 */
public class DesignSwitch<T>
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static DesignPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignSwitch()
    {
        if( modelPackage == null )
        {
            modelPackage = DesignPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch( EObject theEObject )
    {
        return doSwitch( theEObject.eClass(), theEObject );
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch( EClass theEClass, EObject theEObject )
    {
        if( theEClass.eContainer() == modelPackage )
        {
            return doSwitch( theEClass.getClassifierID(), theEObject );
        }
        else
        {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase( theEObject )
                    : doSwitch( eSuperTypes.get( 0 ), theEObject );
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch( int classifierID, EObject theEObject )
    {
        switch( classifierID )
        {
        case DesignPackage.AND_EXPRESSION:
        {
            AndExpression andExpression = (AndExpression) theEObject;
            T result = caseAndExpression( andExpression );
            if( result == null )
                result = caseCompositeFilterExpression( andExpression );
            if( result == null )
                result = caseFilterExpression( andExpression );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT:
        {
            AtomicExpressionContext atomicExpressionContext = (AtomicExpressionContext) theEObject;
            T result = caseAtomicExpressionContext( atomicExpressionContext );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.AXIS_ATTRIBUTES:
        {
            AxisAttributes axisAttributes = (AxisAttributes) theEObject;
            T result = caseAxisAttributes( axisAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.COLUMN_DEFINITION:
        {
            ColumnDefinition columnDefinition = (ColumnDefinition) theEObject;
            T result = caseColumnDefinition( columnDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.COMPOSITE_FILTER_EXPRESSION:
        {
            CompositeFilterExpression compositeFilterExpression = (CompositeFilterExpression) theEObject;
            T result = caseCompositeFilterExpression( compositeFilterExpression );
            if( result == null )
                result = caseFilterExpression( compositeFilterExpression );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.CUSTOM_DATA:
        {
            CustomData customData = (CustomData) theEObject;
            T result = caseCustomData( customData );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.CUSTOM_FILTER_EXPRESSION:
        {
            CustomFilterExpression customFilterExpression = (CustomFilterExpression) theEObject;
            T result = caseCustomFilterExpression( customFilterExpression );
            if( result == null )
                result = caseFilterExpression( customFilterExpression );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_ACCESS_DESIGN:
        {
            DataAccessDesign dataAccessDesign = (DataAccessDesign) theEObject;
            T result = caseDataAccessDesign( dataAccessDesign );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES:
        {
            DataElementAttributes dataElementAttributes = (DataElementAttributes) theEObject;
            T result = caseDataElementAttributes( dataElementAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_ELEMENT_IDENTIFIER:
        {
            DataElementIdentifier dataElementIdentifier = (DataElementIdentifier) theEObject;
            T result = caseDataElementIdentifier( dataElementIdentifier );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_ELEMENT_IDENTIFIERS:
        {
            DataElementIdentifiers dataElementIdentifiers = (DataElementIdentifiers) theEObject;
            T result = caseDataElementIdentifiers( dataElementIdentifiers );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_ELEMENT_UI_HINTS:
        {
            DataElementUIHints dataElementUIHints = (DataElementUIHints) theEObject;
            T result = caseDataElementUIHints( dataElementUIHints );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_SET_DESIGN:
        {
            DataSetDesign dataSetDesign = (DataSetDesign) theEObject;
            T result = caseDataSetDesign( dataSetDesign );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_SET_PARAMETERS:
        {
            DataSetParameters dataSetParameters = (DataSetParameters) theEObject;
            T result = caseDataSetParameters( dataSetParameters );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_SET_QUERY:
        {
            DataSetQuery dataSetQuery = (DataSetQuery) theEObject;
            T result = caseDataSetQuery( dataSetQuery );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_SOURCE_DESIGN:
        {
            DataSourceDesign dataSourceDesign = (DataSourceDesign) theEObject;
            T result = caseDataSourceDesign( dataSourceDesign );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DESIGNER_STATE:
        {
            DesignerState designerState = (DesignerState) theEObject;
            T result = caseDesignerState( designerState );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DESIGNER_STATE_CONTENT:
        {
            DesignerStateContent designerStateContent = (DesignerStateContent) theEObject;
            T result = caseDesignerStateContent( designerStateContent );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DESIGN_SESSION_REQUEST:
        {
            DesignSessionRequest designSessionRequest = (DesignSessionRequest) theEObject;
            T result = caseDesignSessionRequest( designSessionRequest );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DESIGN_SESSION_RESPONSE:
        {
            DesignSessionResponse designSessionResponse = (DesignSessionResponse) theEObject;
            T result = caseDesignSessionResponse( designSessionResponse );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DOCUMENT_ROOT:
        {
            DocumentRoot documentRoot = (DocumentRoot) theEObject;
            T result = caseDocumentRoot( documentRoot );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION:
        {
            DynamicFilterExpression dynamicFilterExpression = (DynamicFilterExpression) theEObject;
            T result = caseDynamicFilterExpression( dynamicFilterExpression );
            if( result == null )
                result = caseFilterExpression( dynamicFilterExpression );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DYNAMIC_VALUES_QUERY:
        {
            DynamicValuesQuery dynamicValuesQuery = (DynamicValuesQuery) theEObject;
            T result = caseDynamicValuesQuery( dynamicValuesQuery );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.EXPRESSION_ARGUMENTS:
        {
            ExpressionArguments expressionArguments = (ExpressionArguments) theEObject;
            T result = caseExpressionArguments( expressionArguments );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION:
        {
            ExpressionParameterDefinition expressionParameterDefinition = (ExpressionParameterDefinition) theEObject;
            T result = caseExpressionParameterDefinition( expressionParameterDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.EXPRESSION_PARAMETERS:
        {
            ExpressionParameters expressionParameters = (ExpressionParameters) theEObject;
            T result = caseExpressionParameters( expressionParameters );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.EXPRESSION_VARIABLE:
        {
            ExpressionVariable expressionVariable = (ExpressionVariable) theEObject;
            T result = caseExpressionVariable( expressionVariable );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.FILTER_EXPRESSION:
        {
            FilterExpression filterExpression = (FilterExpression) theEObject;
            T result = caseFilterExpression( filterExpression );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.FILTER_EXPRESSION_TYPE:
        {
            FilterExpressionType filterExpressionType = (FilterExpressionType) theEObject;
            T result = caseFilterExpressionType( filterExpressionType );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES:
        {
            InputElementAttributes inputElementAttributes = (InputElementAttributes) theEObject;
            T result = caseInputElementAttributes( inputElementAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.INPUT_ELEMENT_UI_HINTS:
        {
            InputElementUIHints inputElementUIHints = (InputElementUIHints) theEObject;
            T result = caseInputElementUIHints( inputElementUIHints );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES:
        {
            InputParameterAttributes inputParameterAttributes = (InputParameterAttributes) theEObject;
            T result = caseInputParameterAttributes( inputParameterAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.INPUT_PARAMETER_UI_HINTS:
        {
            InputParameterUIHints inputParameterUIHints = (InputParameterUIHints) theEObject;
            T result = caseInputParameterUIHints( inputParameterUIHints );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.LOCALE:
        {
            Locale locale = (Locale) theEObject;
            T result = caseLocale( locale );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.NAME_VALUE_PAIR:
        {
            NameValuePair nameValuePair = (NameValuePair) theEObject;
            T result = caseNameValuePair( nameValuePair );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.NOT_EXPRESSION:
        {
            NotExpression notExpression = (NotExpression) theEObject;
            T result = caseNotExpression( notExpression );
            if( result == null )
                result = caseFilterExpression( notExpression );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.ODA_DESIGN_SESSION:
        {
            OdaDesignSession odaDesignSession = (OdaDesignSession) theEObject;
            T result = caseOdaDesignSession( odaDesignSession );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.OR_EXPRESSION:
        {
            OrExpression orExpression = (OrExpression) theEObject;
            T result = caseOrExpression( orExpression );
            if( result == null )
                result = caseCompositeFilterExpression( orExpression );
            if( result == null )
                result = caseFilterExpression( orExpression );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES:
        {
            OutputElementAttributes outputElementAttributes = (OutputElementAttributes) theEObject;
            T result = caseOutputElementAttributes( outputElementAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PARAMETER_DEFINITION:
        {
            ParameterDefinition parameterDefinition = (ParameterDefinition) theEObject;
            T result = caseParameterDefinition( parameterDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PARAMETER_FIELD_DEFINITION:
        {
            ParameterFieldDefinition parameterFieldDefinition = (ParameterFieldDefinition) theEObject;
            T result = caseParameterFieldDefinition( parameterFieldDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PARAMETER_FIELDS:
        {
            ParameterFields parameterFields = (ParameterFields) theEObject;
            T result = caseParameterFields( parameterFields );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PROPERTIES:
        {
            Properties properties = (Properties) theEObject;
            T result = caseProperties( properties );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PROPERTY:
        {
            Property property = (Property) theEObject;
            T result = caseProperty( property );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PROPERTY_ATTRIBUTES:
        {
            PropertyAttributes propertyAttributes = (PropertyAttributes) theEObject;
            T result = casePropertyAttributes( propertyAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESOURCE_IDENTIFIERS:
        {
            ResourceIdentifiers resourceIdentifiers = (ResourceIdentifiers) theEObject;
            T result = caseResourceIdentifiers( resourceIdentifiers );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESULT_SET_COLUMNS:
        {
            ResultSetColumns resultSetColumns = (ResultSetColumns) theEObject;
            T result = caseResultSetColumns( resultSetColumns );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESULT_SET_CRITERIA:
        {
            ResultSetCriteria resultSetCriteria = (ResultSetCriteria) theEObject;
            T result = caseResultSetCriteria( resultSetCriteria );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESULT_SET_DEFINITION:
        {
            ResultSetDefinition resultSetDefinition = (ResultSetDefinition) theEObject;
            T result = caseResultSetDefinition( resultSetDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESULT_SETS:
        {
            ResultSets resultSets = (ResultSets) theEObject;
            T result = caseResultSets( resultSets );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESULT_SUBSET:
        {
            ResultSubset resultSubset = (ResultSubset) theEObject;
            T result = caseResultSubset( resultSubset );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.SCALAR_VALUE_CHOICES:
        {
            ScalarValueChoices scalarValueChoices = (ScalarValueChoices) theEObject;
            T result = caseScalarValueChoices( scalarValueChoices );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.SCALAR_VALUE_DEFINITION:
        {
            ScalarValueDefinition scalarValueDefinition = (ScalarValueDefinition) theEObject;
            T result = caseScalarValueDefinition( scalarValueDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.SORT_KEY:
        {
            SortKey sortKey = (SortKey) theEObject;
            T result = caseSortKey( sortKey );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.SORT_SPECIFICATION:
        {
            SortSpecification sortSpecification = (SortSpecification) theEObject;
            T result = caseSortSpecification( sortSpecification );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.STATIC_VALUES:
        {
            StaticValues staticValues = (StaticValues) theEObject;
            T result = caseStaticValues( staticValues );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.VALUE_FORMAT_HINTS:
        {
            ValueFormatHints valueFormatHints = (ValueFormatHints) theEObject;
            T result = caseValueFormatHints( valueFormatHints );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        default:
            return defaultCase( theEObject );
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>And Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>And Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAndExpression( AndExpression object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Atomic Expression Context</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Atomic Expression Context</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAtomicExpressionContext( AtomicExpressionContext object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Axis Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Axis Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAxisAttributes( AxisAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Column Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Column Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseColumnDefinition( ColumnDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Composite Filter Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Composite Filter Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCompositeFilterExpression( CompositeFilterExpression object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomData( CustomData object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Filter Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Filter Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomFilterExpression( CustomFilterExpression object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Access Design</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Access Design</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataAccessDesign( DataAccessDesign object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Element Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataElementAttributes( DataElementAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Element Identifier</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Element Identifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataElementIdentifier( DataElementIdentifier object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Element Identifiers</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Element Identifiers</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataElementIdentifiers( DataElementIdentifiers object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Element UI Hints</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Element UI Hints</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataElementUIHints( DataElementUIHints object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Set Design</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Set Design</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataSetDesign( DataSetDesign object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Set Parameters</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Set Parameters</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataSetParameters( DataSetParameters object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Set Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Set Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataSetQuery( DataSetQuery object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Source Design</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Source Design</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataSourceDesign( DataSourceDesign object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Designer State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Designer State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDesignerState( DesignerState object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Designer State Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Designer State Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDesignerStateContent( DesignerStateContent object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Session Request</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Session Request</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDesignSessionRequest( DesignSessionRequest object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Session Response</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Session Response</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDesignSessionResponse( DesignSessionResponse object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentRoot( DocumentRoot object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dynamic Filter Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dynamic Filter Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDynamicFilterExpression( DynamicFilterExpression object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dynamic Values Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dynamic Values Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDynamicValuesQuery( DynamicValuesQuery object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Expression Arguments</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Expression Arguments</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExpressionArguments( ExpressionArguments object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Expression Parameter Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Expression Parameter Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExpressionParameterDefinition(
            ExpressionParameterDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Expression Parameters</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Expression Parameters</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExpressionParameters( ExpressionParameters object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Expression Variable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Expression Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExpressionVariable( ExpressionVariable object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Filter Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Filter Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFilterExpression( FilterExpression object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Filter Expression Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Filter Expression Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFilterExpressionType( FilterExpressionType object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Input Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Input Element Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInputElementAttributes( InputElementAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Input Element UI Hints</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Input Element UI Hints</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInputElementUIHints( InputElementUIHints object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Input Parameter Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Input Parameter Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInputParameterAttributes( InputParameterAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Input Parameter UI Hints</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Input Parameter UI Hints</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInputParameterUIHints( InputParameterUIHints object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Locale</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Locale</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLocale( Locale object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Name Value Pair</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Name Value Pair</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNameValuePair( NameValuePair object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Not Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Not Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNotExpression( NotExpression object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Oda Design Session</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Oda Design Session</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOdaDesignSession( OdaDesignSession object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Or Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Or Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOrExpression( OrExpression object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Output Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Output Element Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOutputElementAttributes( OutputElementAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParameterDefinition( ParameterDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter Field Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter Field Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParameterFieldDefinition( ParameterFieldDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter Fields</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter Fields</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParameterFields( ParameterFields object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Properties</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Properties</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseProperties( Properties object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Property</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseProperty( Property object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Property Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Property Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePropertyAttributes( PropertyAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Resource Identifiers</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resource Identifiers</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResourceIdentifiers( ResourceIdentifiers object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Result Set Columns</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Set Columns</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResultSetColumns( ResultSetColumns object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Result Set Criteria</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Set Criteria</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResultSetCriteria( ResultSetCriteria object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Result Set Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Set Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResultSetDefinition( ResultSetDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Result Sets</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Sets</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResultSets( ResultSets object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Result Subset</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Subset</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResultSubset( ResultSubset object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Scalar Value Choices</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Scalar Value Choices</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScalarValueChoices( ScalarValueChoices object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Scalar Value Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Scalar Value Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScalarValueDefinition( ScalarValueDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sort Key</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sort Key</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSortKey( SortKey object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sort Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sort Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSortSpecification( SortSpecification object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Static Values</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Static Values</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStaticValues( StaticValues object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Value Format Hints</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Format Hints</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseValueFormatHints( ValueFormatHints object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase( EObject object )
    {
        return null;
    }

} //DesignSwitch
