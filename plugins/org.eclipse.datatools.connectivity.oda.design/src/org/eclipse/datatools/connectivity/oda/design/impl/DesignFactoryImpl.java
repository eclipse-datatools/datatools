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
 * $Id: DesignFactoryImpl.java,v 1.8 2008/07/23 04:12:27 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AndExpression;
import org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext;
import org.eclipse.datatools.connectivity.oda.design.AxisAttributes;
import org.eclipse.datatools.connectivity.oda.design.AxisType;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.CompositeFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.CustomExpression;
import org.eclipse.datatools.connectivity.oda.design.DataAccessDesign;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DataSetQuery;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.DesignerStateContent;
import org.eclipse.datatools.connectivity.oda.design.DocumentRoot;
import org.eclipse.datatools.connectivity.oda.design.DynamicExpression;
import org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.FilterExpressionArguments;
import org.eclipse.datatools.connectivity.oda.design.FilterExpressionVariable;
import org.eclipse.datatools.connectivity.oda.design.FilterParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.FilterParameters;
import org.eclipse.datatools.connectivity.oda.design.FilterVariableType;
import org.eclipse.datatools.connectivity.oda.design.HorizontalAlignment;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints;
import org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle;
import org.eclipse.datatools.connectivity.oda.design.Locale;
import org.eclipse.datatools.connectivity.oda.design.NameValuePair;
import org.eclipse.datatools.connectivity.oda.design.NotExpression;
import org.eclipse.datatools.connectivity.oda.design.OdaComplexDataType;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.OdaScalarDataType;
import org.eclipse.datatools.connectivity.oda.design.OrExpression;
import org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterFields;
import org.eclipse.datatools.connectivity.oda.design.ParameterMode;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.Property;
import org.eclipse.datatools.connectivity.oda.design.PropertyAttributes;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ResultSets;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;
import org.eclipse.datatools.connectivity.oda.design.TextFormatType;
import org.eclipse.datatools.connectivity.oda.design.TextWrapType;
import org.eclipse.datatools.connectivity.oda.design.ValueFormatHints;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DesignFactoryImpl extends EFactoryImpl implements DesignFactory
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DesignFactory init()
    {
        try
        {
            DesignFactory theDesignFactory = (DesignFactory) EPackage.Registry.INSTANCE
                    .getEFactory( "http://www.eclipse.org/datatools/connectivity/oda/design" ); //$NON-NLS-1$ 
            if( theDesignFactory != null )
            {
                return theDesignFactory;
            }
        }
        catch( Exception exception )
        {
            EcorePlugin.INSTANCE.log( exception );
        }
        return new DesignFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignFactoryImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create( EClass eClass )
    {
        switch( eClass.getClassifierID() )
        {
        case DesignPackage.AND_EXPRESSION:
            return createAndExpression();
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT:
            return createAtomicExpressionContext();
        case DesignPackage.AXIS_ATTRIBUTES:
            return createAxisAttributes();
        case DesignPackage.COLUMN_DEFINITION:
            return createColumnDefinition();
        case DesignPackage.COMPOSITE_FILTER_EXPRESSION:
            return createCompositeFilterExpression();
        case DesignPackage.CUSTOM_EXPRESSION:
            return createCustomExpression();
        case DesignPackage.DATA_ACCESS_DESIGN:
            return createDataAccessDesign();
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES:
            return createDataElementAttributes();
        case DesignPackage.DATA_ELEMENT_UI_HINTS:
            return createDataElementUIHints();
        case DesignPackage.DATA_SET_DESIGN:
            return createDataSetDesign();
        case DesignPackage.DATA_SET_PARAMETERS:
            return createDataSetParameters();
        case DesignPackage.DATA_SET_QUERY:
            return createDataSetQuery();
        case DesignPackage.DATA_SOURCE_DESIGN:
            return createDataSourceDesign();
        case DesignPackage.DESIGNER_STATE:
            return createDesignerState();
        case DesignPackage.DESIGNER_STATE_CONTENT:
            return createDesignerStateContent();
        case DesignPackage.DESIGN_SESSION_REQUEST:
            return createDesignSessionRequest();
        case DesignPackage.DESIGN_SESSION_RESPONSE:
            return createDesignSessionResponse();
        case DesignPackage.DOCUMENT_ROOT:
            return createDocumentRoot();
        case DesignPackage.DYNAMIC_EXPRESSION:
            return createDynamicExpression();
        case DesignPackage.DYNAMIC_VALUES_QUERY:
            return createDynamicValuesQuery();
        case DesignPackage.FILTER_EXPRESSION_ARGUMENTS:
            return createFilterExpressionArguments();
        case DesignPackage.FILTER_EXPRESSION_VARIABLE:
            return createFilterExpressionVariable();
        case DesignPackage.FILTER_PARAMETER_DEFINITION:
            return createFilterParameterDefinition();
        case DesignPackage.FILTER_PARAMETERS:
            return createFilterParameters();
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES:
            return createInputElementAttributes();
        case DesignPackage.INPUT_ELEMENT_UI_HINTS:
            return createInputElementUIHints();
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES:
            return createInputParameterAttributes();
        case DesignPackage.INPUT_PARAMETER_UI_HINTS:
            return createInputParameterUIHints();
        case DesignPackage.LOCALE:
            return createLocale();
        case DesignPackage.NAME_VALUE_PAIR:
            return createNameValuePair();
        case DesignPackage.NOT_EXPRESSION:
            return createNotExpression();
        case DesignPackage.ODA_DESIGN_SESSION:
            return createOdaDesignSession();
        case DesignPackage.OR_EXPRESSION:
            return createOrExpression();
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES:
            return createOutputElementAttributes();
        case DesignPackage.PARAMETER_DEFINITION:
            return createParameterDefinition();
        case DesignPackage.PARAMETER_FIELD_DEFINITION:
            return createParameterFieldDefinition();
        case DesignPackage.PARAMETER_FIELDS:
            return createParameterFields();
        case DesignPackage.PROPERTIES:
            return createProperties();
        case DesignPackage.PROPERTY:
            return createProperty();
        case DesignPackage.PROPERTY_ATTRIBUTES:
            return createPropertyAttributes();
        case DesignPackage.RESOURCE_IDENTIFIERS:
            return createResourceIdentifiers();
        case DesignPackage.RESULT_SET_COLUMNS:
            return createResultSetColumns();
        case DesignPackage.RESULT_SET_DEFINITION:
            return createResultSetDefinition();
        case DesignPackage.RESULT_SETS:
            return createResultSets();
        case DesignPackage.SCALAR_VALUE_CHOICES:
            return createScalarValueChoices();
        case DesignPackage.SCALAR_VALUE_DEFINITION:
            return createScalarValueDefinition();
        case DesignPackage.VALUE_FORMAT_HINTS:
            return createValueFormatHints();
        default:
            throw new IllegalArgumentException(
                    "The class '" + eClass.getName() + "' is not a valid classifier" ); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString( EDataType eDataType, String initialValue )
    {
        switch( eDataType.getClassifierID() )
        {
        case DesignPackage.AXIS_TYPE:
            return createAxisTypeFromString( eDataType, initialValue );
        case DesignPackage.ELEMENT_NULLABILITY:
            return createElementNullabilityFromString( eDataType, initialValue );
        case DesignPackage.FILTER_VARIABLE_TYPE:
            return createFilterVariableTypeFromString( eDataType, initialValue );
        case DesignPackage.HORIZONTAL_ALIGNMENT:
            return createHorizontalAlignmentFromString( eDataType, initialValue );
        case DesignPackage.INPUT_PROMPT_CONTROL_STYLE:
            return createInputPromptControlStyleFromString( eDataType,
                    initialValue );
        case DesignPackage.ODA_COMPLEX_DATA_TYPE:
            return createOdaComplexDataTypeFromString( eDataType, initialValue );
        case DesignPackage.ODA_SCALAR_DATA_TYPE:
            return createOdaScalarDataTypeFromString( eDataType, initialValue );
        case DesignPackage.PARAMETER_MODE:
            return createParameterModeFromString( eDataType, initialValue );
        case DesignPackage.SESSION_STATUS:
            return createSessionStatusFromString( eDataType, initialValue );
        case DesignPackage.TEXT_FORMAT_TYPE:
            return createTextFormatTypeFromString( eDataType, initialValue );
        case DesignPackage.TEXT_WRAP_TYPE:
            return createTextWrapTypeFromString( eDataType, initialValue );
        case DesignPackage.AXIS_TYPE_OBJECT:
            return createAxisTypeObjectFromString( eDataType, initialValue );
        case DesignPackage.ELEMENT_NULLABILITY_OBJECT:
            return createElementNullabilityObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.FILTER_VARIABLE_TYPE_OBJECT:
            return createFilterVariableTypeObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.HORIZONTAL_ALIGNMENT_OBJECT:
            return createHorizontalAlignmentObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.INPUT_PROMPT_CONTROL_STYLE_OBJECT:
            return createInputPromptControlStyleObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.ODA_COMPLEX_DATA_TYPE_OBJECT:
            return createOdaComplexDataTypeObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.ODA_SCALAR_DATA_TYPE_OBJECT:
            return createOdaScalarDataTypeObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.PARAMETER_MODE_OBJECT:
            return createParameterModeObjectFromString( eDataType, initialValue );
        case DesignPackage.SESSION_STATUS_OBJECT:
            return createSessionStatusObjectFromString( eDataType, initialValue );
        case DesignPackage.TEXT_FORMAT_TYPE_OBJECT:
            return createTextFormatTypeObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.TEXT_WRAP_TYPE_OBJECT:
            return createTextWrapTypeObjectFromString( eDataType, initialValue );
        default:
            throw new IllegalArgumentException(
                    "The datatype '" + eDataType.getName() + "' is not a valid classifier" ); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString( EDataType eDataType, Object instanceValue )
    {
        switch( eDataType.getClassifierID() )
        {
        case DesignPackage.AXIS_TYPE:
            return convertAxisTypeToString( eDataType, instanceValue );
        case DesignPackage.ELEMENT_NULLABILITY:
            return convertElementNullabilityToString( eDataType, instanceValue );
        case DesignPackage.FILTER_VARIABLE_TYPE:
            return convertFilterVariableTypeToString( eDataType, instanceValue );
        case DesignPackage.HORIZONTAL_ALIGNMENT:
            return convertHorizontalAlignmentToString( eDataType, instanceValue );
        case DesignPackage.INPUT_PROMPT_CONTROL_STYLE:
            return convertInputPromptControlStyleToString( eDataType,
                    instanceValue );
        case DesignPackage.ODA_COMPLEX_DATA_TYPE:
            return convertOdaComplexDataTypeToString( eDataType, instanceValue );
        case DesignPackage.ODA_SCALAR_DATA_TYPE:
            return convertOdaScalarDataTypeToString( eDataType, instanceValue );
        case DesignPackage.PARAMETER_MODE:
            return convertParameterModeToString( eDataType, instanceValue );
        case DesignPackage.SESSION_STATUS:
            return convertSessionStatusToString( eDataType, instanceValue );
        case DesignPackage.TEXT_FORMAT_TYPE:
            return convertTextFormatTypeToString( eDataType, instanceValue );
        case DesignPackage.TEXT_WRAP_TYPE:
            return convertTextWrapTypeToString( eDataType, instanceValue );
        case DesignPackage.AXIS_TYPE_OBJECT:
            return convertAxisTypeObjectToString( eDataType, instanceValue );
        case DesignPackage.ELEMENT_NULLABILITY_OBJECT:
            return convertElementNullabilityObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.FILTER_VARIABLE_TYPE_OBJECT:
            return convertFilterVariableTypeObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.HORIZONTAL_ALIGNMENT_OBJECT:
            return convertHorizontalAlignmentObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.INPUT_PROMPT_CONTROL_STYLE_OBJECT:
            return convertInputPromptControlStyleObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.ODA_COMPLEX_DATA_TYPE_OBJECT:
            return convertOdaComplexDataTypeObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.ODA_SCALAR_DATA_TYPE_OBJECT:
            return convertOdaScalarDataTypeObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.PARAMETER_MODE_OBJECT:
            return convertParameterModeObjectToString( eDataType, instanceValue );
        case DesignPackage.SESSION_STATUS_OBJECT:
            return convertSessionStatusObjectToString( eDataType, instanceValue );
        case DesignPackage.TEXT_FORMAT_TYPE_OBJECT:
            return convertTextFormatTypeObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.TEXT_WRAP_TYPE_OBJECT:
            return convertTextWrapTypeObjectToString( eDataType, instanceValue );
        default:
            throw new IllegalArgumentException(
                    "The datatype '" + eDataType.getName() + "' is not a valid classifier" ); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AndExpression createAndExpression()
    {
        AndExpressionImpl andExpression = new AndExpressionImpl();
        return andExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AtomicExpressionContext createAtomicExpressionContext()
    {
        AtomicExpressionContextImpl atomicExpressionContext = new AtomicExpressionContextImpl();
        return atomicExpressionContext;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AxisAttributes createAxisAttributes()
    {
        AxisAttributesImpl axisAttributes = new AxisAttributesImpl();
        return axisAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ColumnDefinition createColumnDefinition()
    {
        ColumnDefinitionImpl columnDefinition = new ColumnDefinitionImpl();
        return columnDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CompositeFilterExpression createCompositeFilterExpression()
    {
        CompositeFilterExpressionImpl compositeFilterExpression = new CompositeFilterExpressionImpl();
        return compositeFilterExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CustomExpression createCustomExpression()
    {
        CustomExpressionImpl customExpression = new CustomExpressionImpl();
        return customExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataAccessDesign createDataAccessDesign()
    {
        DataAccessDesignImpl dataAccessDesign = new DataAccessDesignImpl();
        return dataAccessDesign;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataElementAttributes createDataElementAttributes()
    {
        DataElementAttributesImpl dataElementAttributes = new DataElementAttributesImpl();
        return dataElementAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataElementUIHints createDataElementUIHints()
    {
        DataElementUIHintsImpl dataElementUIHints = new DataElementUIHintsImpl();
        return dataElementUIHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataSetDesign createDataSetDesign()
    {
        DataSetDesignImpl dataSetDesign = new DataSetDesignImpl();
        return dataSetDesign;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataSetParameters createDataSetParameters()
    {
        DataSetParametersImpl dataSetParameters = new DataSetParametersImpl();
        return dataSetParameters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataSetQuery createDataSetQuery()
    {
        DataSetQueryImpl dataSetQuery = new DataSetQueryImpl();
        return dataSetQuery;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataSourceDesign createDataSourceDesign()
    {
        DataSourceDesignImpl dataSourceDesign = new DataSourceDesignImpl();
        return dataSourceDesign;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignerState createDesignerState()
    {
        DesignerStateImpl designerState = new DesignerStateImpl();
        return designerState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignerStateContent createDesignerStateContent()
    {
        DesignerStateContentImpl designerStateContent = new DesignerStateContentImpl();
        return designerStateContent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignSessionRequest createDesignSessionRequest()
    {
        DesignSessionRequestImpl designSessionRequest = new DesignSessionRequestImpl();
        return designSessionRequest;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignSessionResponse createDesignSessionResponse()
    {
        DesignSessionResponseImpl designSessionResponse = new DesignSessionResponseImpl();
        return designSessionResponse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DocumentRoot createDocumentRoot()
    {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicExpression createDynamicExpression()
    {
        DynamicExpressionImpl dynamicExpression = new DynamicExpressionImpl();
        return dynamicExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicValuesQuery createDynamicValuesQuery()
    {
        DynamicValuesQueryImpl dynamicValuesQuery = new DynamicValuesQueryImpl();
        return dynamicValuesQuery;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterExpressionArguments createFilterExpressionArguments()
    {
        FilterExpressionArgumentsImpl filterExpressionArguments = new FilterExpressionArgumentsImpl();
        return filterExpressionArguments;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterExpressionVariable createFilterExpressionVariable()
    {
        FilterExpressionVariableImpl filterExpressionVariable = new FilterExpressionVariableImpl();
        return filterExpressionVariable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterParameterDefinition createFilterParameterDefinition()
    {
        FilterParameterDefinitionImpl filterParameterDefinition = new FilterParameterDefinitionImpl();
        return filterParameterDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterParameters createFilterParameters()
    {
        FilterParametersImpl filterParameters = new FilterParametersImpl();
        return filterParameters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputElementAttributes createInputElementAttributes()
    {
        InputElementAttributesImpl inputElementAttributes = new InputElementAttributesImpl();
        return inputElementAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputElementUIHints createInputElementUIHints()
    {
        InputElementUIHintsImpl inputElementUIHints = new InputElementUIHintsImpl();
        return inputElementUIHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputParameterAttributes createInputParameterAttributes()
    {
        InputParameterAttributesImpl inputParameterAttributes = new InputParameterAttributesImpl();
        return inputParameterAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputParameterUIHints createInputParameterUIHints()
    {
        InputParameterUIHintsImpl inputParameterUIHints = new InputParameterUIHintsImpl();
        return inputParameterUIHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Locale createLocale()
    {
        LocaleImpl locale = new LocaleImpl();
        return locale;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NameValuePair createNameValuePair()
    {
        NameValuePairImpl nameValuePair = new NameValuePairImpl();
        return nameValuePair;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotExpression createNotExpression()
    {
        NotExpressionImpl notExpression = new NotExpressionImpl();
        return notExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OdaDesignSession createOdaDesignSession()
    {
        OdaDesignSessionImpl odaDesignSession = new OdaDesignSessionImpl();
        return odaDesignSession;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrExpression createOrExpression()
    {
        OrExpressionImpl orExpression = new OrExpressionImpl();
        return orExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputElementAttributes createOutputElementAttributes()
    {
        OutputElementAttributesImpl outputElementAttributes = new OutputElementAttributesImpl();
        return outputElementAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterDefinition createParameterDefinition()
    {
        ParameterDefinitionImpl parameterDefinition = new ParameterDefinitionImpl();
        return parameterDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterFieldDefinition createParameterFieldDefinition()
    {
        ParameterFieldDefinitionImpl parameterFieldDefinition = new ParameterFieldDefinitionImpl();
        return parameterFieldDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterFields createParameterFields()
    {
        ParameterFieldsImpl parameterFields = new ParameterFieldsImpl();
        return parameterFields;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Properties createProperties()
    {
        PropertiesImpl properties = new PropertiesImpl();
        return properties;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Property createProperty()
    {
        PropertyImpl property = new PropertyImpl();
        return property;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PropertyAttributes createPropertyAttributes()
    {
        PropertyAttributesImpl propertyAttributes = new PropertyAttributesImpl();
        return propertyAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceIdentifiers createResourceIdentifiers()
    {
        ResourceIdentifiersImpl resourceIdentifiers = new ResourceIdentifiersImpl();
        return resourceIdentifiers;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultSetColumns createResultSetColumns()
    {
        ResultSetColumnsImpl resultSetColumns = new ResultSetColumnsImpl();
        return resultSetColumns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultSetDefinition createResultSetDefinition()
    {
        ResultSetDefinitionImpl resultSetDefinition = new ResultSetDefinitionImpl();
        return resultSetDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultSets createResultSets()
    {
        ResultSetsImpl resultSets = new ResultSetsImpl();
        return resultSets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScalarValueChoices createScalarValueChoices()
    {
        ScalarValueChoicesImpl scalarValueChoices = new ScalarValueChoicesImpl();
        return scalarValueChoices;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScalarValueDefinition createScalarValueDefinition()
    {
        ScalarValueDefinitionImpl scalarValueDefinition = new ScalarValueDefinitionImpl();
        return scalarValueDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueFormatHints createValueFormatHints()
    {
        ValueFormatHintsImpl valueFormatHints = new ValueFormatHintsImpl();
        return valueFormatHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AxisType createAxisTypeFromString( EDataType eDataType,
            String initialValue )
    {
        AxisType result = AxisType.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertAxisTypeToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementNullability createElementNullabilityFromString(
            EDataType eDataType, String initialValue )
    {
        ElementNullability result = ElementNullability.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertElementNullabilityToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterVariableType createFilterVariableTypeFromString(
            EDataType eDataType, String initialValue )
    {
        FilterVariableType result = FilterVariableType.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFilterVariableTypeToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HorizontalAlignment createHorizontalAlignmentFromString(
            EDataType eDataType, String initialValue )
    {
        HorizontalAlignment result = HorizontalAlignment.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertHorizontalAlignmentToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputPromptControlStyle createInputPromptControlStyleFromString(
            EDataType eDataType, String initialValue )
    {
        InputPromptControlStyle result = InputPromptControlStyle
                .get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertInputPromptControlStyleToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OdaComplexDataType createOdaComplexDataTypeFromString(
            EDataType eDataType, String initialValue )
    {
        OdaComplexDataType result = OdaComplexDataType.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertOdaComplexDataTypeToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OdaScalarDataType createOdaScalarDataTypeFromString(
            EDataType eDataType, String initialValue )
    {
        OdaScalarDataType result = OdaScalarDataType.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertOdaScalarDataTypeToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterMode createParameterModeFromString( EDataType eDataType,
            String initialValue )
    {
        ParameterMode result = ParameterMode.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertParameterModeToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SessionStatus createSessionStatusFromString( EDataType eDataType,
            String initialValue )
    {
        SessionStatus result = SessionStatus.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertSessionStatusToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextFormatType createTextFormatTypeFromString( EDataType eDataType,
            String initialValue )
    {
        TextFormatType result = TextFormatType.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTextFormatTypeToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextWrapType createTextWrapTypeFromString( EDataType eDataType,
            String initialValue )
    {
        TextWrapType result = TextWrapType.get( initialValue );
        if( result == null )
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTextWrapTypeToString( EDataType eDataType,
            Object instanceValue )
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AxisType createAxisTypeObjectFromString( EDataType eDataType,
            String initialValue )
    {
        return createAxisTypeFromString( DesignPackage.Literals.AXIS_TYPE,
                initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertAxisTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertAxisTypeToString( DesignPackage.Literals.AXIS_TYPE,
                instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementNullability createElementNullabilityObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createElementNullabilityFromString(
                DesignPackage.Literals.ELEMENT_NULLABILITY, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertElementNullabilityObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertElementNullabilityToString(
                DesignPackage.Literals.ELEMENT_NULLABILITY, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterVariableType createFilterVariableTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createFilterVariableTypeFromString(
                DesignPackage.Literals.FILTER_VARIABLE_TYPE, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFilterVariableTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertFilterVariableTypeToString(
                DesignPackage.Literals.FILTER_VARIABLE_TYPE, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HorizontalAlignment createHorizontalAlignmentObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createHorizontalAlignmentFromString(
                DesignPackage.Literals.HORIZONTAL_ALIGNMENT, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertHorizontalAlignmentObjectToString(
            EDataType eDataType, Object instanceValue )
    {
        return convertHorizontalAlignmentToString(
                DesignPackage.Literals.HORIZONTAL_ALIGNMENT, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputPromptControlStyle createInputPromptControlStyleObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createInputPromptControlStyleFromString(
                DesignPackage.Literals.INPUT_PROMPT_CONTROL_STYLE, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertInputPromptControlStyleObjectToString(
            EDataType eDataType, Object instanceValue )
    {
        return convertInputPromptControlStyleToString(
                DesignPackage.Literals.INPUT_PROMPT_CONTROL_STYLE,
                instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OdaComplexDataType createOdaComplexDataTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createOdaComplexDataTypeFromString(
                DesignPackage.Literals.ODA_COMPLEX_DATA_TYPE, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertOdaComplexDataTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertOdaComplexDataTypeToString(
                DesignPackage.Literals.ODA_COMPLEX_DATA_TYPE, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OdaScalarDataType createOdaScalarDataTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createOdaScalarDataTypeFromString(
                DesignPackage.Literals.ODA_SCALAR_DATA_TYPE, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertOdaScalarDataTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertOdaScalarDataTypeToString(
                DesignPackage.Literals.ODA_SCALAR_DATA_TYPE, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterMode createParameterModeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createParameterModeFromString(
                DesignPackage.Literals.PARAMETER_MODE, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertParameterModeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertParameterModeToString(
                DesignPackage.Literals.PARAMETER_MODE, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SessionStatus createSessionStatusObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createSessionStatusFromString(
                DesignPackage.Literals.SESSION_STATUS, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertSessionStatusObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertSessionStatusToString(
                DesignPackage.Literals.SESSION_STATUS, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextFormatType createTextFormatTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createTextFormatTypeFromString(
                DesignPackage.Literals.TEXT_FORMAT_TYPE, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTextFormatTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertTextFormatTypeToString(
                DesignPackage.Literals.TEXT_FORMAT_TYPE, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextWrapType createTextWrapTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return createTextWrapTypeFromString(
                DesignPackage.Literals.TEXT_WRAP_TYPE, initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTextWrapTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return convertTextWrapTypeToString(
                DesignPackage.Literals.TEXT_WRAP_TYPE, instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignPackage getDesignPackage()
    {
        return (DesignPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DesignPackage getPackage()
    {
        return DesignPackage.eINSTANCE;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignFactory#createDesignSessionRequest(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     * @generated NOT
     */
    public DesignSessionRequest createDesignSessionRequest(
            DataSourceDesign dataSourceDesign )
    {
        DesignSessionRequest newRequest = createDesignSessionRequest();
        newRequest.setNewDataAccessDesign( dataSourceDesign );
        return newRequest;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignFactory#createDesignSessionRequest(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
     * @generated NOT
     */
    public DesignSessionRequest createDesignSessionRequest(
            DataSetDesign dataSetDesign )
    {
        DesignSessionRequest newRequest = createDesignSessionRequest();
        newRequest.setNewDataAccessDesign( dataSetDesign );
        return newRequest;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignFactory#createRequestDesignSession(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     * @generated NOT
     */
    public OdaDesignSession createRequestDesignSession(
            DataSourceDesign dataSourceDesign )
    {
        OdaDesignSession newSession = createOdaDesignSession();
        newSession.setNewRequest( dataSourceDesign );
        return newSession;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignFactory#createResponseDesignSession(boolean, org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     * @generated NOT
     */
    public OdaDesignSession createResponseDesignSession( boolean isSessionOk,
            DataSourceDesign dataSourceDesign )
    {
        // create a design session with an empty DataAccessDesign in the request
        OdaDesignSession newSession = createRequestDesignSession( null );

        // sets a new response with the given session status and DataSourceDesign
        newSession.setNewResponse( isSessionOk, dataSourceDesign );
        return newSession;
    }

} //DesignFactoryImpl
