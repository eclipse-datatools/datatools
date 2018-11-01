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
 * $Id: DesignFactory.java,v 1.13 2010/06/10 23:40:04 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage
 * @generated
 */
public interface DesignFactory extends EFactory
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    DesignFactory eINSTANCE = org.eclipse.datatools.connectivity.oda.design.impl.DesignFactoryImpl
            .init();

    /**
     * Returns a new object of class '<em>And Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>And Expression</em>'.
     * @generated
     */
    AndExpression createAndExpression();

    /**
     * Returns a new object of class '<em>Atomic Expression Context</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Atomic Expression Context</em>'.
     * @generated
     */
    AtomicExpressionContext createAtomicExpressionContext();

    /**
     * Returns a new object of class '<em>Axis Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Axis Attributes</em>'.
     * @generated
     */
    AxisAttributes createAxisAttributes();

    /**
     * Returns a new object of class '<em>Column Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Column Definition</em>'.
     * @generated
     */
    ColumnDefinition createColumnDefinition();

    /**
     * Returns a new object of class '<em>Composite Filter Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Composite Filter Expression</em>'.
     * @generated
     */
    CompositeFilterExpression createCompositeFilterExpression();

    /**
     * Returns a new object of class '<em>Custom Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Custom Data</em>'.
     * @generated
     */
    CustomData createCustomData();

    /**
     * Returns a new object of class '<em>Custom Filter Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Custom Filter Expression</em>'.
     * @generated
     */
    CustomFilterExpression createCustomFilterExpression();

    /**
     * Returns a new object of class '<em>Data Access Design</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Access Design</em>'.
     * @generated
     */
    DataAccessDesign createDataAccessDesign();

    /**
     * Returns a new object of class '<em>Data Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Element Attributes</em>'.
     * @generated
     */
    DataElementAttributes createDataElementAttributes();

    /**
     * Returns a new object of class '<em>Data Element Identifier</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Element Identifier</em>'.
     * @generated
     */
    DataElementIdentifier createDataElementIdentifier();

    /**
     * Returns a new object of class '<em>Data Element Identifiers</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Element Identifiers</em>'.
     * @generated
     */
    DataElementIdentifiers createDataElementIdentifiers();

    /**
     * Returns a new object of class '<em>Data Element UI Hints</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Element UI Hints</em>'.
     * @generated
     */
    DataElementUIHints createDataElementUIHints();

    /**
     * Returns a new object of class '<em>Data Set Design</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Set Design</em>'.
     * @generated
     */
    DataSetDesign createDataSetDesign();

    /**
     * Returns a new object of class '<em>Data Set Parameters</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Set Parameters</em>'.
     * @generated
     */
    DataSetParameters createDataSetParameters();

    /**
     * Returns a new object of class '<em>Data Set Query</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Set Query</em>'.
     * @generated
     */
    DataSetQuery createDataSetQuery();

    /**
     * Returns a new object of class '<em>Data Source Design</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Source Design</em>'.
     * @generated
     */
    DataSourceDesign createDataSourceDesign();

    /**
     * Returns a new object of class '<em>Designer State</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Designer State</em>'.
     * @generated
     */
    DesignerState createDesignerState();

    /**
     * Returns a new object of class '<em>Designer State Content</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Designer State Content</em>'.
     * @generated
     */
    DesignerStateContent createDesignerStateContent();

    /**
     * Returns a new object of class '<em>Session Request</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Session Request</em>'.
     * @generated
     */
    DesignSessionRequest createDesignSessionRequest();

    /**
     * Returns a new object of class '<em>Session Response</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Session Response</em>'.
     * @generated
     */
    DesignSessionResponse createDesignSessionResponse();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Dynamic Filter Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Dynamic Filter Expression</em>'.
     * @generated
     */
    DynamicFilterExpression createDynamicFilterExpression();

    /**
     * Returns a new object of class '<em>Dynamic Values Query</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Dynamic Values Query</em>'.
     * @generated
     */
    DynamicValuesQuery createDynamicValuesQuery();

    /**
     * Returns a new object of class '<em>Expression Arguments</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Expression Arguments</em>'.
     * @generated
     */
    ExpressionArguments createExpressionArguments();

    /**
     * Returns a new object of class '<em>Expression Parameter Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Expression Parameter Definition</em>'.
     * @generated
     */
    ExpressionParameterDefinition createExpressionParameterDefinition();

    /**
     * Returns a new object of class '<em>Expression Parameters</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Expression Parameters</em>'.
     * @generated
     */
    ExpressionParameters createExpressionParameters();

    /**
     * Returns a new object of class '<em>Expression Variable</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Expression Variable</em>'.
     * @generated
     */
    ExpressionVariable createExpressionVariable();

    /**
     * Returns a new object of class '<em>Filter Expression Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Filter Expression Type</em>'.
     * @generated
     */
    FilterExpressionType createFilterExpressionType();

    /**
     * Returns a new object of class '<em>Input Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Element Attributes</em>'.
     * @generated
     */
    InputElementAttributes createInputElementAttributes();

    /**
     * Returns a new object of class '<em>Input Element UI Hints</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Element UI Hints</em>'.
     * @generated
     */
    InputElementUIHints createInputElementUIHints();

    /**
     * Returns a new object of class '<em>Input Parameter Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Parameter Attributes</em>'.
     * @generated
     */
    InputParameterAttributes createInputParameterAttributes();

    /**
     * Returns a new object of class '<em>Input Parameter UI Hints</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Parameter UI Hints</em>'.
     * @generated
     */
    InputParameterUIHints createInputParameterUIHints();

    /**
     * Returns a new object of class '<em>Locale</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Locale</em>'.
     * @generated
     */
    Locale createLocale();

    /**
     * Returns a new object of class '<em>Name Value Pair</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Name Value Pair</em>'.
     * @generated
     */
    NameValuePair createNameValuePair();

    /**
     * Returns a new object of class '<em>Not Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Not Expression</em>'.
     * @generated
     */
    NotExpression createNotExpression();

    /**
     * Returns a new object of class '<em>Oda Design Session</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Oda Design Session</em>'.
     * @generated
     */
    OdaDesignSession createOdaDesignSession();

    /**
     * Returns a new object of class '<em>Or Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Or Expression</em>'.
     * @generated
     */
    OrExpression createOrExpression();

    /**
     * Returns a new object of class '<em>Output Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Output Element Attributes</em>'.
     * @generated
     */
    OutputElementAttributes createOutputElementAttributes();

    /**
     * Returns a new object of class '<em>Parameter Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Definition</em>'.
     * @generated
     */
    ParameterDefinition createParameterDefinition();

    /**
     * Returns a new object of class '<em>Parameter Field Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Field Definition</em>'.
     * @generated
     */
    ParameterFieldDefinition createParameterFieldDefinition();

    /**
     * Returns a new object of class '<em>Parameter Fields</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Fields</em>'.
     * @generated
     */
    ParameterFields createParameterFields();

    /**
     * Returns a new object of class '<em>Properties</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Properties</em>'.
     * @generated
     */
    Properties createProperties();

    /**
     * Returns a new object of class '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Property</em>'.
     * @generated
     */
    Property createProperty();

    /**
     * Returns a new object of class '<em>Property Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Property Attributes</em>'.
     * @generated
     */
    PropertyAttributes createPropertyAttributes();

    /**
     * Returns a new object of class '<em>Resource Identifiers</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource Identifiers</em>'.
     * @generated
     */
    ResourceIdentifiers createResourceIdentifiers();

    /**
     * Returns a new object of class '<em>Result Set Columns</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Set Columns</em>'.
     * @generated
     */
    ResultSetColumns createResultSetColumns();

    /**
     * Returns a new object of class '<em>Result Set Criteria</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Set Criteria</em>'.
     * @generated
     */
    ResultSetCriteria createResultSetCriteria();

    /**
     * Returns a new object of class '<em>Result Set Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Set Definition</em>'.
     * @generated
     */
    ResultSetDefinition createResultSetDefinition();

    /**
     * Returns a new object of class '<em>Result Sets</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Sets</em>'.
     * @generated
     */
    ResultSets createResultSets();

    /**
     * Returns a new object of class '<em>Result Subset</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Subset</em>'.
     * @generated
     */
    ResultSubset createResultSubset();

    /**
     * Returns a new object of class '<em>Scalar Value Choices</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Scalar Value Choices</em>'.
     * @generated
     */
    ScalarValueChoices createScalarValueChoices();

    /**
     * Returns a new object of class '<em>Scalar Value Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Scalar Value Definition</em>'.
     * @generated
     */
    ScalarValueDefinition createScalarValueDefinition();

    /**
     * Returns a new object of class '<em>Sort Key</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sort Key</em>'.
     * @generated
     */
    SortKey createSortKey();

    /**
     * Returns a new object of class '<em>Sort Specification</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sort Specification</em>'.
     * @generated
     */
    SortSpecification createSortSpecification();

    /**
     * Returns a new object of class '<em>Static Values</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Static Values</em>'.
     * @generated
     */
    StaticValues createStaticValues();

    /**
     * Returns a new object of class '<em>Value Format Hints</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Format Hints</em>'.
     * @generated
     */
    ValueFormatHints createValueFormatHints();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    DesignPackage getDesignPackage();

    /**
     * Creates and returns a new object of class '<em>Session Request</em>'
     * that contains the given DataSourceDesign.
     * <br>This may be used by an ODA host designer to create 
     * a design session request to create/edit a 
     * data source design definition.
     * @param   dataSourceDesign    the data source design to edit;
     *                              may be null for a request to
     *                              create a new data source design
     * @return a new object of class '<em>Session Request</em>'.
     * @see #createDesignSessionRequest()
     * @generated NOT
     */
    DesignSessionRequest createDesignSessionRequest(
            DataSourceDesign dataSourceDesign );

    /**
     * Creates and returns a new object of class '<em>Session Request</em>'
     * that contains the given DataSetDesign.
     * <br>This may be used by an ODA host designer to create 
     * a design session request to edit a 
     * data set design definition.
     * @param   dataSetDesign    the data set design to edit
     * @return a new object of class '<em>Session Request</em>'.
     * @see #createDesignSessionRequest()
     * @generated NOT
     */
    DesignSessionRequest createDesignSessionRequest( DataSetDesign dataSetDesign );

    /**
     * Creates and returns an OdaDesignSession instance with a 
     * DesignSessionRequest that contains the given DataSourceDesign.
     * <br>This may be used by an ODA host designer to create 
     * a design session instance, for use to create/edit a 
     * data source design definition,
     * or to create a new data set design with a given data source design.
     * @param   dataSourceDesign    the data source design to edit;
     *                              may be null for a session to
     *                              create a new data source design
     * @see #createDesignSessionRequest(DataSourceDesign)
     * @generated NOT
     */
    OdaDesignSession createRequestDesignSession(
            DataSourceDesign dataSourceDesign );

    /**
     * Creates and returns a new OdaDesignSession instance with a
     * DesignSessionResponse that contains the given session status
     * and DataSourceDesign.
     * <br>This method may be used by an ODA driver's design UI extension
     * to create a new design session instance with a response that contains
     * a new or edited data source design definition.
     * <br>The returned session instance also includes an appropriate
     * DesignSessionRequest to meet the contract for API objects content.
     * @param isSessionOk
     * @param dataSourceDesign
     * @return
     * @generated NOT
     */
    OdaDesignSession createResponseDesignSession( boolean isSessionOk,
            DataSourceDesign dataSourceDesign );

} //DesignFactory
