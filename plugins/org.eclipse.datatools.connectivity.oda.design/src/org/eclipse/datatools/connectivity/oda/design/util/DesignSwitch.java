/**
 *************************************************************************
 * Copyright (c) 2005, 2008 Actuate Corporation.
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
 * $Id: DesignSwitch.java,v 1.3 2007/04/11 02:59:53 lchan Exp $
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
public class DesignSwitch
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2008 Actuate Corporation"; //$NON-NLS-1$

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
    public Object doSwitch( EObject theEObject )
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
    protected Object doSwitch( EClass theEClass, EObject theEObject )
    {
        if( theEClass.eContainer() == modelPackage )
        {
            return doSwitch( theEClass.getClassifierID(), theEObject );
        }
        else
        {
            List eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase( theEObject )
                    : doSwitch( (EClass) eSuperTypes.get( 0 ), theEObject );
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch( int classifierID, EObject theEObject )
    {
        switch( classifierID )
        {
        case DesignPackage.AXIS_ATTRIBUTES:
        {
            AxisAttributes axisAttributes = (AxisAttributes) theEObject;
            Object result = caseAxisAttributes( axisAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.COLUMN_DEFINITION:
        {
            ColumnDefinition columnDefinition = (ColumnDefinition) theEObject;
            Object result = caseColumnDefinition( columnDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_ACCESS_DESIGN:
        {
            DataAccessDesign dataAccessDesign = (DataAccessDesign) theEObject;
            Object result = caseDataAccessDesign( dataAccessDesign );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_ELEMENT_ATTRIBUTES:
        {
            DataElementAttributes dataElementAttributes = (DataElementAttributes) theEObject;
            Object result = caseDataElementAttributes( dataElementAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_ELEMENT_UI_HINTS:
        {
            DataElementUIHints dataElementUIHints = (DataElementUIHints) theEObject;
            Object result = caseDataElementUIHints( dataElementUIHints );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_SET_DESIGN:
        {
            DataSetDesign dataSetDesign = (DataSetDesign) theEObject;
            Object result = caseDataSetDesign( dataSetDesign );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_SET_PARAMETERS:
        {
            DataSetParameters dataSetParameters = (DataSetParameters) theEObject;
            Object result = caseDataSetParameters( dataSetParameters );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_SET_QUERY:
        {
            DataSetQuery dataSetQuery = (DataSetQuery) theEObject;
            Object result = caseDataSetQuery( dataSetQuery );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DATA_SOURCE_DESIGN:
        {
            DataSourceDesign dataSourceDesign = (DataSourceDesign) theEObject;
            Object result = caseDataSourceDesign( dataSourceDesign );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DESIGNER_STATE:
        {
            DesignerState designerState = (DesignerState) theEObject;
            Object result = caseDesignerState( designerState );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DESIGNER_STATE_CONTENT:
        {
            DesignerStateContent designerStateContent = (DesignerStateContent) theEObject;
            Object result = caseDesignerStateContent( designerStateContent );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DESIGN_SESSION_REQUEST:
        {
            DesignSessionRequest designSessionRequest = (DesignSessionRequest) theEObject;
            Object result = caseDesignSessionRequest( designSessionRequest );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DESIGN_SESSION_RESPONSE:
        {
            DesignSessionResponse designSessionResponse = (DesignSessionResponse) theEObject;
            Object result = caseDesignSessionResponse( designSessionResponse );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DOCUMENT_ROOT:
        {
            DocumentRoot documentRoot = (DocumentRoot) theEObject;
            Object result = caseDocumentRoot( documentRoot );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.DYNAMIC_VALUES_QUERY:
        {
            DynamicValuesQuery dynamicValuesQuery = (DynamicValuesQuery) theEObject;
            Object result = caseDynamicValuesQuery( dynamicValuesQuery );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.INPUT_ELEMENT_ATTRIBUTES:
        {
            InputElementAttributes inputElementAttributes = (InputElementAttributes) theEObject;
            Object result = caseInputElementAttributes( inputElementAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.INPUT_ELEMENT_UI_HINTS:
        {
            InputElementUIHints inputElementUIHints = (InputElementUIHints) theEObject;
            Object result = caseInputElementUIHints( inputElementUIHints );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES:
        {
            InputParameterAttributes inputParameterAttributes = (InputParameterAttributes) theEObject;
            Object result = caseInputParameterAttributes( inputParameterAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.INPUT_PARAMETER_UI_HINTS:
        {
            InputParameterUIHints inputParameterUIHints = (InputParameterUIHints) theEObject;
            Object result = caseInputParameterUIHints( inputParameterUIHints );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.LOCALE:
        {
            Locale locale = (Locale) theEObject;
            Object result = caseLocale( locale );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.NAME_VALUE_PAIR:
        {
            NameValuePair nameValuePair = (NameValuePair) theEObject;
            Object result = caseNameValuePair( nameValuePair );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.ODA_DESIGN_SESSION:
        {
            OdaDesignSession odaDesignSession = (OdaDesignSession) theEObject;
            Object result = caseOdaDesignSession( odaDesignSession );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.OUTPUT_ELEMENT_ATTRIBUTES:
        {
            OutputElementAttributes outputElementAttributes = (OutputElementAttributes) theEObject;
            Object result = caseOutputElementAttributes( outputElementAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PARAMETER_DEFINITION:
        {
            ParameterDefinition parameterDefinition = (ParameterDefinition) theEObject;
            Object result = caseParameterDefinition( parameterDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PARAMETER_FIELD_DEFINITION:
        {
            ParameterFieldDefinition parameterFieldDefinition = (ParameterFieldDefinition) theEObject;
            Object result = caseParameterFieldDefinition( parameterFieldDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PARAMETER_FIELDS:
        {
            ParameterFields parameterFields = (ParameterFields) theEObject;
            Object result = caseParameterFields( parameterFields );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PROPERTIES:
        {
            Properties properties = (Properties) theEObject;
            Object result = caseProperties( properties );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PROPERTY:
        {
            Property property = (Property) theEObject;
            Object result = caseProperty( property );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.PROPERTY_ATTRIBUTES:
        {
            PropertyAttributes propertyAttributes = (PropertyAttributes) theEObject;
            Object result = casePropertyAttributes( propertyAttributes );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESOURCE_IDENTIFIERS:
        {
            ResourceIdentifiers resourceIdentifiers = (ResourceIdentifiers) theEObject;
            Object result = caseResourceIdentifiers( resourceIdentifiers );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESULT_SET_COLUMNS:
        {
            ResultSetColumns resultSetColumns = (ResultSetColumns) theEObject;
            Object result = caseResultSetColumns( resultSetColumns );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESULT_SET_DEFINITION:
        {
            ResultSetDefinition resultSetDefinition = (ResultSetDefinition) theEObject;
            Object result = caseResultSetDefinition( resultSetDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.RESULT_SETS:
        {
            ResultSets resultSets = (ResultSets) theEObject;
            Object result = caseResultSets( resultSets );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.SCALAR_VALUE_CHOICES:
        {
            ScalarValueChoices scalarValueChoices = (ScalarValueChoices) theEObject;
            Object result = caseScalarValueChoices( scalarValueChoices );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.SCALAR_VALUE_DEFINITION:
        {
            ScalarValueDefinition scalarValueDefinition = (ScalarValueDefinition) theEObject;
            Object result = caseScalarValueDefinition( scalarValueDefinition );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        case DesignPackage.VALUE_FORMAT_HINTS:
        {
            ValueFormatHints valueFormatHints = (ValueFormatHints) theEObject;
            Object result = caseValueFormatHints( valueFormatHints );
            if( result == null )
                result = defaultCase( theEObject );
            return result;
        }
        default:
            return defaultCase( theEObject );
        }
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Axis Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Axis Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseAxisAttributes( AxisAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Column Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Column Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseColumnDefinition( ColumnDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Data Access Design</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Data Access Design</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDataAccessDesign( DataAccessDesign object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Data Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Data Element Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDataElementAttributes( DataElementAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Data Element UI Hints</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Data Element UI Hints</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDataElementUIHints( DataElementUIHints object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Data Set Design</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Data Set Design</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDataSetDesign( DataSetDesign object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Data Set Parameters</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Data Set Parameters</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDataSetParameters( DataSetParameters object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Data Set Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Data Set Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDataSetQuery( DataSetQuery object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Data Source Design</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Data Source Design</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDataSourceDesign( DataSourceDesign object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Designer State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Designer State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDesignerState( DesignerState object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Designer State Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Designer State Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDesignerStateContent( DesignerStateContent object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Session Request</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Session Request</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDesignSessionRequest( DesignSessionRequest object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Session Response</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Session Response</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDesignSessionResponse( DesignSessionResponse object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDocumentRoot( DocumentRoot object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Dynamic Values Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Dynamic Values Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDynamicValuesQuery( DynamicValuesQuery object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Input Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Input Element Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseInputElementAttributes( InputElementAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Input Element UI Hints</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Input Element UI Hints</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseInputElementUIHints( InputElementUIHints object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Input Parameter Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Input Parameter Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseInputParameterAttributes( InputParameterAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Input Parameter UI Hints</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Input Parameter UI Hints</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseInputParameterUIHints( InputParameterUIHints object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Locale</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Locale</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLocale( Locale object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Name Value Pair</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Name Value Pair</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseNameValuePair( NameValuePair object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Oda Design Session</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Oda Design Session</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseOdaDesignSession( OdaDesignSession object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Output Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Output Element Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseOutputElementAttributes( OutputElementAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Parameter Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Parameter Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseParameterDefinition( ParameterDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Parameter Field Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Parameter Field Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseParameterFieldDefinition( ParameterFieldDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Parameter Fields</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Parameter Fields</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseParameterFields( ParameterFields object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Properties</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Properties</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProperties( Properties object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Property</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProperty( Property object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Property Attributes</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Property Attributes</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePropertyAttributes( PropertyAttributes object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Resource Identifiers</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Resource Identifiers</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseResourceIdentifiers( ResourceIdentifiers object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Result Set Columns</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Result Set Columns</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseResultSetColumns( ResultSetColumns object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Result Set Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Result Set Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseResultSetDefinition( ResultSetDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Result Sets</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Result Sets</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseResultSets( ResultSets object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Scalar Value Choices</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Scalar Value Choices</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseScalarValueChoices( ScalarValueChoices object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Scalar Value Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Scalar Value Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseScalarValueDefinition( ScalarValueDefinition object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Value Format Hints</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Value Format Hints</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueFormatHints( ValueFormatHints object )
    {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public Object defaultCase( EObject object )
    {
        return null;
    }

} //DesignSwitch
