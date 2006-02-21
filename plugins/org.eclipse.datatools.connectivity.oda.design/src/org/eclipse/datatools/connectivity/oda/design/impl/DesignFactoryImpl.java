/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
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
 * $Id: DesignFactoryImpl.java,v 1.2 2006/02/08 08:06:17 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.*;

import org.eclipse.emf.common.util.AbstractEnumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

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
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

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
    public EObject create( EClass eClass )
    {
        switch( eClass.getClassifierID() )
        {
        case DesignPackage.AXIS_ATTRIBUTES:
            return createAxisAttributes();
        case DesignPackage.COLUMN_DEFINITION:
            return createColumnDefinition();
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
        case DesignPackage.DYNAMIC_VALUES_QUERY:
            return createDynamicValuesQuery();
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
        case DesignPackage.ODA_DESIGN_SESSION:
            return createOdaDesignSession();
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
    public Object createFromString( EDataType eDataType, String initialValue )
    {
        switch( eDataType.getClassifierID() )
        {
        case DesignPackage.AXIS_TYPE:
        {
            AxisType result = AxisType.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.ELEMENT_NULLABILITY:
        {
            ElementNullability result = ElementNullability.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.HORIZONTAL_ALIGNMENT:
        {
            HorizontalAlignment result = HorizontalAlignment.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.INPUT_PROMPT_CONTROL_STYLE:
        {
            InputPromptControlStyle result = InputPromptControlStyle
                    .get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.ODA_COMPLEX_DATA_TYPE:
        {
            OdaComplexDataType result = OdaComplexDataType.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.ODA_SCALAR_DATA_TYPE:
        {
            OdaScalarDataType result = OdaScalarDataType.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.PARAMETER_MODE:
        {
            ParameterMode result = ParameterMode.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.SESSION_STATUS:
        {
            SessionStatus result = SessionStatus.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.TEXT_FORMAT_TYPE:
        {
            TextFormatType result = TextFormatType.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.TEXT_WRAP_TYPE:
        {
            TextWrapType result = TextWrapType.get( initialValue );
            if( result == null )
                throw new IllegalArgumentException(
                        "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return result;
        }
        case DesignPackage.AXIS_TYPE_OBJECT:
            return createAxisTypeObjectFromString( eDataType, initialValue );
        case DesignPackage.ELEMENT_NULLABILITY_OBJECT:
            return createElementNullabilityObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.HORIZONTAL_ALIGNMENT_OBJECT:
            return createHorizontalAlignmentObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.INPUT_PROMPT_CONTROL_STYLE_OBJECT:
            return createInputPromptControlStyleObjectFromString( eDataType,
                    initialValue );
        case DesignPackage.NATIVE_DATA_TYPE_CODE:
            return createNativeDataTypeCodeFromString( eDataType, initialValue );
        case DesignPackage.NATIVE_DATA_TYPE_CODE_OBJECT:
            return createNativeDataTypeCodeObjectFromString( eDataType,
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
    public String convertToString( EDataType eDataType, Object instanceValue )
    {
        switch( eDataType.getClassifierID() )
        {
        case DesignPackage.AXIS_TYPE:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.ELEMENT_NULLABILITY:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.HORIZONTAL_ALIGNMENT:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.INPUT_PROMPT_CONTROL_STYLE:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.ODA_COMPLEX_DATA_TYPE:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.ODA_SCALAR_DATA_TYPE:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.PARAMETER_MODE:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.SESSION_STATUS:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.TEXT_FORMAT_TYPE:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.TEXT_WRAP_TYPE:
            return instanceValue == null ? null : instanceValue.toString();
        case DesignPackage.AXIS_TYPE_OBJECT:
            return convertAxisTypeObjectToString( eDataType, instanceValue );
        case DesignPackage.ELEMENT_NULLABILITY_OBJECT:
            return convertElementNullabilityObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.HORIZONTAL_ALIGNMENT_OBJECT:
            return convertHorizontalAlignmentObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.INPUT_PROMPT_CONTROL_STYLE_OBJECT:
            return convertInputPromptControlStyleObjectToString( eDataType,
                    instanceValue );
        case DesignPackage.NATIVE_DATA_TYPE_CODE:
            return convertNativeDataTypeCodeToString( eDataType, instanceValue );
        case DesignPackage.NATIVE_DATA_TYPE_CODE_OBJECT:
            return convertNativeDataTypeCodeObjectToString( eDataType,
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
    public AbstractEnumerator createAxisTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getAxisType(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertAxisTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getAxisType(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createElementNullabilityObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getElementNullability(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertElementNullabilityObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getElementNullability(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createHorizontalAlignmentObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getHorizontalAlignment(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertHorizontalAlignmentObjectToString(
            EDataType eDataType, Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getHorizontalAlignment(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createInputPromptControlStyleObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getInputPromptControlStyle(),
                initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertInputPromptControlStyleObjectToString(
            EDataType eDataType, Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getInputPromptControlStyle(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Short createNativeDataTypeCodeFromString( EDataType eDataType,
            String initialValue )
    {
        return (Short) XMLTypeFactory.eINSTANCE.createFromString(
                XMLTypePackage.eINSTANCE.getShort(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertNativeDataTypeCodeToString( EDataType eDataType,
            Object instanceValue )
    {
        return XMLTypeFactory.eINSTANCE.convertToString(
                XMLTypePackage.eINSTANCE.getShort(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Short createNativeDataTypeCodeObjectFromString( EDataType eDataType,
            String initialValue )
    {
        return (Short) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getNativeDataTypeCode(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertNativeDataTypeCodeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getNativeDataTypeCode(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createOdaComplexDataTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getOdaComplexDataType(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertOdaComplexDataTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getOdaComplexDataType(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createOdaScalarDataTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getOdaScalarDataType(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertOdaScalarDataTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getOdaScalarDataType(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createParameterModeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getParameterMode(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertParameterModeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getParameterMode(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createSessionStatusObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getSessionStatus(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertSessionStatusObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getSessionStatus(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createTextFormatTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getTextFormatType(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTextFormatTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getTextFormatType(), instanceValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractEnumerator createTextWrapTypeObjectFromString(
            EDataType eDataType, String initialValue )
    {
        return (AbstractEnumerator) DesignFactory.eINSTANCE.createFromString(
                DesignPackage.eINSTANCE.getTextWrapType(), initialValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTextWrapTypeObjectToString( EDataType eDataType,
            Object instanceValue )
    {
        return DesignFactory.eINSTANCE.convertToString( DesignPackage.eINSTANCE
                .getTextWrapType(), instanceValue );
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
    public static DesignPackage getPackage()
    {
        return DesignPackage.eINSTANCE;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignFactory#createRequestDesignSession(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     * @generated NOT
     */
    public OdaDesignSession createRequestDesignSession( DataSourceDesign dataSourceDesign )
    {
        OdaDesignSession newSession = createOdaDesignSession();
        newSession.setNewRequest( dataSourceDesign );
        return newSession;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignFactory#createResponseDesignSession(boolean, org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     * @generated NOT
     */
    public OdaDesignSession createResponseDesignSession( boolean isSessionOk, DataSourceDesign dataSourceDesign )
    {
        // create a design session with an empty DataAccessDesign in the request
        OdaDesignSession newSession = createRequestDesignSession( null );

        // sets a new response with the given session status and DataSourceDesign
        newSession.setNewResponse( isSessionOk, dataSourceDesign );
        return newSession;
    }
  
} //DesignFactoryImpl
