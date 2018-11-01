/**
 *************************************************************************
 * Copyright (c) 2005, 2011 Actuate Corporation.
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
 * $Id: DesignPackageImpl.java,v 1.22 2011/03/08 22:59:10 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AndExpression;
import org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext;
import org.eclipse.datatools.connectivity.oda.design.AxisAttributes;
import org.eclipse.datatools.connectivity.oda.design.AxisType;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.CompositeFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.CustomData;
import org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.DataAccessDesign;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier;
import org.eclipse.datatools.connectivity.oda.design.DataElementIdentifiers;
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
import org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameters;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariableType;
import org.eclipse.datatools.connectivity.oda.design.FilterExpression;
import org.eclipse.datatools.connectivity.oda.design.FilterExpressionType;
import org.eclipse.datatools.connectivity.oda.design.HorizontalAlignment;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints;
import org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle;
import org.eclipse.datatools.connectivity.oda.design.Locale;
import org.eclipse.datatools.connectivity.oda.design.NameValuePair;
import org.eclipse.datatools.connectivity.oda.design.NotExpression;
import org.eclipse.datatools.connectivity.oda.design.NullOrderingType;
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
import org.eclipse.datatools.connectivity.oda.design.ResultSetCriteria;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ResultSets;
import org.eclipse.datatools.connectivity.oda.design.ResultSubset;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;
import org.eclipse.datatools.connectivity.oda.design.SortDirectionType;
import org.eclipse.datatools.connectivity.oda.design.SortKey;
import org.eclipse.datatools.connectivity.oda.design.SortSpecification;
import org.eclipse.datatools.connectivity.oda.design.StaticValues;
import org.eclipse.datatools.connectivity.oda.design.TextFormatType;
import org.eclipse.datatools.connectivity.oda.design.TextWrapType;
import org.eclipse.datatools.connectivity.oda.design.ValueFormatHints;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DesignPackageImpl extends EPackageImpl implements DesignPackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2011 Actuate Corporation"; //$NON-NLS-1$

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass andExpressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass atomicExpressionContextEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass axisAttributesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass columnDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass compositeFilterExpressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass customDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass customFilterExpressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataAccessDesignEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataElementAttributesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataElementIdentifierEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataElementIdentifiersEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataElementUIHintsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataSetDesignEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataSetParametersEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataSetQueryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataSourceDesignEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass designerStateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass designerStateContentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass designSessionRequestEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass designSessionResponseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dynamicFilterExpressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dynamicValuesQueryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass expressionArgumentsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass expressionParameterDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass expressionParametersEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass expressionVariableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass filterExpressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass filterExpressionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass inputElementAttributesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass inputElementUIHintsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass inputParameterAttributesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass inputParameterUIHintsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass localeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nameValuePairEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass notExpressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass odaDesignSessionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass orExpressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass outputElementAttributesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass parameterDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass parameterFieldDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass parameterFieldsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass propertiesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass propertyEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass propertyAttributesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resourceIdentifiersEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resultSetColumnsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resultSetCriteriaEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resultSetDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resultSetsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resultSubsetEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scalarValueChoicesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scalarValueDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sortKeyEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sortSpecificationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass staticValuesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass valueFormatHintsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum axisTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum elementNullabilityEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum expressionVariableTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum horizontalAlignmentEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum inputPromptControlStyleEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum nullOrderingTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum odaComplexDataTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum odaScalarDataTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum parameterModeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum sessionStatusEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum sortDirectionTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum textFormatTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum textWrapTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType axisTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType elementNullabilityObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType expressionVariableTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType horizontalAlignmentObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType inputPromptControlStyleObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType nullOrderingTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType odaComplexDataTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType odaScalarDataTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType parameterModeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType sessionStatusObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType sortDirectionTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType textFormatTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType textWrapTypeObjectEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DesignPackageImpl()
    {
        super( eNS_URI, DesignFactory.eINSTANCE );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DesignPackage init()
    {
        if( isInited )
            return (DesignPackage) EPackage.Registry.INSTANCE
                    .getEPackage( DesignPackage.eNS_URI );

        // Obtain or create and register package
        DesignPackageImpl theDesignPackage = (DesignPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage( eNS_URI ) instanceof DesignPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage( eNS_URI )
                : new DesignPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theDesignPackage.createPackageContents();

        // Initialize created meta-data
        theDesignPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDesignPackage.freeze();

        return theDesignPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAndExpression()
    {
        return andExpressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAtomicExpressionContext()
    {
        return atomicExpressionContextEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAtomicExpressionContext_Optional()
    {
        return (EAttribute) atomicExpressionContextEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAtomicExpressionContext_Variable()
    {
        return (EReference) atomicExpressionContextEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAtomicExpressionContext_Arguments()
    {
        return (EReference) atomicExpressionContextEClass
                .getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAxisAttributes()
    {
        return axisAttributesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAxisAttributes_AxisType()
    {
        return (EAttribute) axisAttributesEClass.getEStructuralFeatures().get(
                0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAxisAttributes_OnColumnLayout()
    {
        return (EAttribute) axisAttributesEClass.getEStructuralFeatures().get(
                1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAxisAttributes_RelatedColumns()
    {
        return (EReference) axisAttributesEClass.getEStructuralFeatures().get(
                2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getColumnDefinition()
    {
        return columnDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getColumnDefinition_Attributes()
    {
        return (EReference) columnDefinitionEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getColumnDefinition_UsageHints()
    {
        return (EReference) columnDefinitionEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getColumnDefinition_MultiDimensionAttributes()
    {
        return (EReference) columnDefinitionEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCompositeFilterExpression()
    {
        return compositeFilterExpressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCompositeFilterExpression_Children()
    {
        return (EReference) compositeFilterExpressionEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCustomData()
    {
        return customDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCustomData_ProviderId()
    {
        return (EAttribute) customDataEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCustomData_Value()
    {
        return (EAttribute) customDataEClass.getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCustomData_DisplayValue()
    {
        return (EAttribute) customDataEClass.getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCustomFilterExpression()
    {
        return customFilterExpressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCustomFilterExpression_Type()
    {
        return (EReference) customFilterExpressionEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCustomFilterExpression_Context()
    {
        return (EReference) customFilterExpressionEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataAccessDesign()
    {
        return dataAccessDesignEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataAccessDesign_DataSetDesign()
    {
        return (EReference) dataAccessDesignEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataElementAttributes()
    {
        return dataElementAttributesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataElementAttributes_Identifier()
    {
        return (EReference) dataElementAttributesEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementAttributes_Name()
    {
        return (EAttribute) dataElementAttributesEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementAttributes_Position()
    {
        return (EAttribute) dataElementAttributesEClass
                .getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementAttributes_NativeDataTypeCode()
    {
        return (EAttribute) dataElementAttributesEClass
                .getEStructuralFeatures().get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementAttributes_Precision()
    {
        return (EAttribute) dataElementAttributesEClass
                .getEStructuralFeatures().get( 4 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementAttributes_Scale()
    {
        return (EAttribute) dataElementAttributesEClass
                .getEStructuralFeatures().get( 5 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementAttributes_Nullability()
    {
        return (EAttribute) dataElementAttributesEClass
                .getEStructuralFeatures().get( 6 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataElementAttributes_UiHints()
    {
        return (EReference) dataElementAttributesEClass
                .getEStructuralFeatures().get( 7 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataElementIdentifier()
    {
        return dataElementIdentifierEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementIdentifier_Name()
    {
        return (EAttribute) dataElementIdentifierEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementIdentifier_Position()
    {
        return (EAttribute) dataElementIdentifierEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataElementIdentifiers()
    {
        return dataElementIdentifiersEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataElementIdentifiers_Identifiers()
    {
        return (EReference) dataElementIdentifiersEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataElementUIHints()
    {
        return dataElementUIHintsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementUIHints_DisplayName()
    {
        return (EAttribute) dataElementUIHintsEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataElementUIHints_Description()
    {
        return (EAttribute) dataElementUIHintsEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataSetDesign()
    {
        return dataSetDesignEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSetDesign_Name()
    {
        return (EAttribute) dataSetDesignEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSetDesign_OdaExtensionDataSetId()
    {
        return (EAttribute) dataSetDesignEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSetDesign_DataSourceDesign()
    {
        return (EReference) dataSetDesignEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSetDesign_Query()
    {
        return (EReference) dataSetDesignEClass.getEStructuralFeatures()
                .get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSetDesign_DisplayName()
    {
        return (EAttribute) dataSetDesignEClass.getEStructuralFeatures()
                .get( 4 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSetDesign_PublicProperties()
    {
        return (EReference) dataSetDesignEClass.getEStructuralFeatures()
                .get( 5 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSetDesign_PrivateProperties()
    {
        return (EReference) dataSetDesignEClass.getEStructuralFeatures()
                .get( 6 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSetDesign_ResultSets()
    {
        return (EReference) dataSetDesignEClass.getEStructuralFeatures()
                .get( 7 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSetDesign_PrimaryResultSetName()
    {
        return (EAttribute) dataSetDesignEClass.getEStructuralFeatures()
                .get( 8 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSetDesign_Parameters()
    {
        return (EReference) dataSetDesignEClass.getEStructuralFeatures()
                .get( 9 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataSetParameters()
    {
        return dataSetParametersEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSetParameters_ParameterDefinitions()
    {
        return (EReference) dataSetParametersEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSetParameters_DerivedMetaData()
    {
        return (EAttribute) dataSetParametersEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataSetQuery()
    {
        return dataSetQueryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSetQuery_QueryText()
    {
        return (EAttribute) dataSetQueryEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataSourceDesign()
    {
        return dataSourceDesignEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSourceDesign_Name()
    {
        return (EAttribute) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSourceDesign_OdaExtensionId()
    {
        return (EAttribute) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSourceDesign_EffectiveOdaExtensionId()
    {
        return (EAttribute) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSourceDesign_OdaExtensionDataSourceId()
    {
        return (EAttribute) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSourceDesign_DisplayName()
    {
        return (EAttribute) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 4 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSourceDesign_PublicProperties()
    {
        return (EReference) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 5 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSourceDesign_PrivateProperties()
    {
        return (EReference) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 6 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSourceDesign_LinkedProfileName()
    {
        return (EAttribute) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 7 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSourceDesign_LinkedProfileStoreFilePath()
    {
        return (EAttribute) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 8 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDataSourceDesign_HostResourceIdentifiers()
    {
        return (EReference) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 9 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataSourceDesign_ResourceFile()
    {
        return (EAttribute) dataSourceDesignEClass.getEStructuralFeatures()
                .get( 10 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDesignerState()
    {
        return designerStateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDesignerState_Version()
    {
        return (EAttribute) designerStateEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDesignerState_StateContent()
    {
        return (EReference) designerStateEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDesignerStateContent()
    {
        return designerStateContentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDesignerStateContent_StateContentAsString()
    {
        return (EAttribute) designerStateContentEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDesignerStateContent_StateContentAsBlob()
    {
        return (EAttribute) designerStateContentEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDesignSessionRequest()
    {
        return designSessionRequestEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDesignSessionRequest_DataAccessDesign()
    {
        return (EReference) designSessionRequestEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDesignSessionRequest_Editable()
    {
        return (EAttribute) designSessionRequestEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDesignSessionRequest_SessionLocale()
    {
        return (EReference) designSessionRequestEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDesignSessionRequest_DesignerState()
    {
        return (EReference) designSessionRequestEClass.getEStructuralFeatures()
                .get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDesignSessionResponse()
    {
        return designSessionResponseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDesignSessionResponse_SessionStatus()
    {
        return (EAttribute) designSessionResponseEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDesignSessionResponse_DataAccessDesign()
    {
        return (EReference) designSessionResponseEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDesignSessionResponse_DesignerState()
    {
        return (EReference) designSessionResponseEClass
                .getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentRoot()
    {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Mixed()
    {
        return (EAttribute) documentRootEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XMLNSPrefixMap()
    {
        return (EReference) documentRootEClass.getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XSISchemaLocation()
    {
        return (EReference) documentRootEClass.getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_OdaDesignSession()
    {
        return (EReference) documentRootEClass.getEStructuralFeatures().get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDynamicFilterExpression()
    {
        return dynamicFilterExpressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicFilterExpression_Context()
    {
        return (EReference) dynamicFilterExpressionEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicFilterExpression_DefaultType()
    {
        return (EReference) dynamicFilterExpressionEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDynamicValuesQuery()
    {
        return dynamicValuesQueryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicValuesQuery_DataSetDesign()
    {
        return (EReference) dynamicValuesQueryEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicValuesQuery_Enabled()
    {
        return (EAttribute) dynamicValuesQueryEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicValuesQuery_ValueColumnIdentifier()
    {
        return (EReference) dynamicValuesQueryEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicValuesQuery_ValueColumn()
    {
        return (EAttribute) dynamicValuesQueryEClass.getEStructuralFeatures()
                .get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicValuesQuery_DisplayNameColumn()
    {
        return (EAttribute) dynamicValuesQueryEClass.getEStructuralFeatures()
                .get( 4 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpressionArguments()
    {
        return expressionArgumentsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExpressionArguments_ExpressionParameters()
    {
        return (EReference) expressionArgumentsEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpressionParameterDefinition()
    {
        return expressionParameterDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExpressionParameterDefinition_StaticValues()
    {
        return (EReference) expressionParameterDefinitionEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExpressionParameterDefinition_DynamicInputParameter()
    {
        return (EReference) expressionParameterDefinitionEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpressionParameters()
    {
        return expressionParametersEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExpressionParameters_ParameterDefinitions()
    {
        return (EReference) expressionParametersEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpressionVariable()
    {
        return expressionVariableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExpressionVariable_Type()
    {
        return (EAttribute) expressionVariableEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExpressionVariable_Identifier()
    {
        return (EAttribute) expressionVariableEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExpressionVariable_NativeDataTypeCode()
    {
        return (EAttribute) expressionVariableEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFilterExpression()
    {
        return filterExpressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFilterExpression_Negatable()
    {
        return (EAttribute) filterExpressionEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFilterExpressionType()
    {
        return filterExpressionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFilterExpressionType_DeclaringExtensionId()
    {
        return (EAttribute) filterExpressionTypeEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFilterExpressionType_Id()
    {
        return (EAttribute) filterExpressionTypeEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInputElementAttributes()
    {
        return inputElementAttributesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInputElementAttributes_DefaultScalarValue()
    {
        return (EAttribute) inputElementAttributesEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInputElementAttributes_DefaultValues()
    {
        return (EReference) inputElementAttributesEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInputElementAttributes_Editable()
    {
        return (EAttribute) inputElementAttributesEClass
                .getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInputElementAttributes_Optional()
    {
        return (EAttribute) inputElementAttributesEClass
                .getEStructuralFeatures().get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInputElementAttributes_MasksValue()
    {
        return (EAttribute) inputElementAttributesEClass
                .getEStructuralFeatures().get( 4 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInputElementAttributes_StaticValueChoices()
    {
        return (EReference) inputElementAttributesEClass
                .getEStructuralFeatures().get( 5 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInputElementAttributes_DynamicValueChoices()
    {
        return (EReference) inputElementAttributesEClass
                .getEStructuralFeatures().get( 6 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInputElementAttributes_UiHints()
    {
        return (EReference) inputElementAttributesEClass
                .getEStructuralFeatures().get( 7 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInputElementUIHints()
    {
        return inputElementUIHintsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInputElementUIHints_PromptStyle()
    {
        return (EAttribute) inputElementUIHintsEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInputElementUIHints_AutoSuggestThreshold()
    {
        return (EAttribute) inputElementUIHintsEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInputParameterAttributes()
    {
        return inputParameterAttributesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInputParameterAttributes_ElementAttributes()
    {
        return (EReference) inputParameterAttributesEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInputParameterAttributes_UiHints()
    {
        return (EReference) inputParameterAttributesEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInputParameterUIHints()
    {
        return inputParameterUIHintsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInputParameterUIHints_GroupPromptDisplayName()
    {
        return (EAttribute) inputParameterUIHintsEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLocale()
    {
        return localeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLocale_Language()
    {
        return (EAttribute) localeEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLocale_Country()
    {
        return (EAttribute) localeEClass.getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLocale_Variant()
    {
        return (EAttribute) localeEClass.getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNameValuePair()
    {
        return nameValuePairEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNameValuePair_Name()
    {
        return (EAttribute) nameValuePairEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNameValuePair_Value()
    {
        return (EAttribute) nameValuePairEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNotExpression()
    {
        return notExpressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNotExpression_NegatingExpression()
    {
        return (EReference) notExpressionEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOdaDesignSession()
    {
        return odaDesignSessionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOdaDesignSession_Request()
    {
        return (EReference) odaDesignSessionEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOdaDesignSession_Response()
    {
        return (EReference) odaDesignSessionEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOrExpression()
    {
        return orExpressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOutputElementAttributes()
    {
        return outputElementAttributesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOutputElementAttributes_Label()
    {
        return (EAttribute) outputElementAttributesEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOutputElementAttributes_FormattingHints()
    {
        return (EReference) outputElementAttributesEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOutputElementAttributes_HelpText()
    {
        return (EAttribute) outputElementAttributesEClass
                .getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getParameterDefinition()
    {
        return parameterDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getParameterDefinition_InOutMode()
    {
        return (EAttribute) parameterDefinitionEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterDefinition_Attributes()
    {
        return (EReference) parameterDefinitionEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterDefinition_InputAttributes()
    {
        return (EReference) parameterDefinitionEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterDefinition_OutputUsageHints()
    {
        return (EReference) parameterDefinitionEClass.getEStructuralFeatures()
                .get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterDefinition_Fields()
    {
        return (EReference) parameterDefinitionEClass.getEStructuralFeatures()
                .get( 4 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getParameterFieldDefinition()
    {
        return parameterFieldDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterFieldDefinition_Attributes()
    {
        return (EReference) parameterFieldDefinitionEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterFieldDefinition_InputAttributes()
    {
        return (EReference) parameterFieldDefinitionEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterFieldDefinition_OutputUsageHints()
    {
        return (EReference) parameterFieldDefinitionEClass
                .getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getParameterFields()
    {
        return parameterFieldsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterFields_FieldCollection()
    {
        return (EReference) parameterFieldsEClass.getEStructuralFeatures().get(
                0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProperties()
    {
        return propertiesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProperties_Properties()
    {
        return (EReference) propertiesEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProperty()
    {
        return propertyEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProperty_NameValue()
    {
        return (EReference) propertyEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProperty_DesignAttributes()
    {
        return (EReference) propertyEClass.getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPropertyAttributes()
    {
        return propertyAttributesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPropertyAttributes_DisplayName()
    {
        return (EAttribute) propertyAttributesEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPropertyAttributes_ElementAttributes()
    {
        return (EReference) propertyAttributesEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPropertyAttributes_DerivedMetaData()
    {
        return (EAttribute) propertyAttributesEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResourceIdentifiers()
    {
        return resourceIdentifiersEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getResourceIdentifiers_ApplResourceBaseURIString()
    {
        return (EAttribute) resourceIdentifiersEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getResourceIdentifiers_DesignResourceBaseURIString()
    {
        return (EAttribute) resourceIdentifiersEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResultSetColumns()
    {
        return resultSetColumnsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSetColumns_ResultColumnDefinitions()
    {
        return (EReference) resultSetColumnsEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResultSetCriteria()
    {
        return resultSetCriteriaEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSetCriteria_FilterSpecification()
    {
        return (EReference) resultSetCriteriaEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSetCriteria_RowOrdering()
    {
        return (EReference) resultSetCriteriaEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResultSetDefinition()
    {
        return resultSetDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getResultSetDefinition_Name()
    {
        return (EAttribute) resultSetDefinitionEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSetDefinition_ResultSetColumns()
    {
        return (EReference) resultSetDefinitionEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSetDefinition_Criteria()
    {
        return (EReference) resultSetDefinitionEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResultSets()
    {
        return resultSetsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSets_ResultSetDefinitions()
    {
        return (EReference) resultSetsEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getResultSets_DerivedMetaData()
    {
        return (EAttribute) resultSetsEClass.getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResultSubset()
    {
        return resultSubsetEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSubset_DataSetDesign()
    {
        return (EReference) resultSubsetEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getResultSubset_ResultSetName()
    {
        return (EAttribute) resultSubsetEClass.getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSubset_ColumnIdentifiers()
    {
        return (EReference) resultSubsetEClass.getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScalarValueChoices()
    {
        return scalarValueChoicesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScalarValueChoices_ScalarValues()
    {
        return (EReference) scalarValueChoicesEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScalarValueDefinition()
    {
        return scalarValueDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScalarValueDefinition_Value()
    {
        return (EAttribute) scalarValueDefinitionEClass
                .getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScalarValueDefinition_DisplayName()
    {
        return (EAttribute) scalarValueDefinitionEClass
                .getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSortKey()
    {
        return sortKeyEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSortKey_ColumnIdentifier()
    {
        return (EReference) sortKeyEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSortKey_ColumnName()
    {
        return (EAttribute) sortKeyEClass.getEStructuralFeatures().get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSortKey_ColumnPosition()
    {
        return (EAttribute) sortKeyEClass.getEStructuralFeatures().get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSortKey_SortDirection()
    {
        return (EAttribute) sortKeyEClass.getEStructuralFeatures().get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSortKey_NullValueOrdering()
    {
        return (EAttribute) sortKeyEClass.getEStructuralFeatures().get( 4 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSortKey_Optional()
    {
        return (EAttribute) sortKeyEClass.getEStructuralFeatures().get( 5 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSortSpecification()
    {
        return sortSpecificationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSortSpecification_SortKeys()
    {
        return (EReference) sortSpecificationEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStaticValues()
    {
        return staticValuesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStaticValues_Values()
    {
        return (EAttribute) staticValuesEClass.getEStructuralFeatures().get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getValueFormatHints()
    {
        return valueFormatHintsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getValueFormatHints_DisplaySize()
    {
        return (EAttribute) valueFormatHintsEClass.getEStructuralFeatures()
                .get( 0 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getValueFormatHints_DisplayFormat()
    {
        return (EAttribute) valueFormatHintsEClass.getEStructuralFeatures()
                .get( 1 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getValueFormatHints_TextFormatType()
    {
        return (EAttribute) valueFormatHintsEClass.getEStructuralFeatures()
                .get( 2 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getValueFormatHints_HorizontalAlignment()
    {
        return (EAttribute) valueFormatHintsEClass.getEStructuralFeatures()
                .get( 3 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getValueFormatHints_TextWrapType()
    {
        return (EAttribute) valueFormatHintsEClass.getEStructuralFeatures()
                .get( 4 );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getAxisType()
    {
        return axisTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getElementNullability()
    {
        return elementNullabilityEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getExpressionVariableType()
    {
        return expressionVariableTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getHorizontalAlignment()
    {
        return horizontalAlignmentEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getInputPromptControlStyle()
    {
        return inputPromptControlStyleEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getNullOrderingType()
    {
        return nullOrderingTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getOdaComplexDataType()
    {
        return odaComplexDataTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getOdaScalarDataType()
    {
        return odaScalarDataTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getParameterMode()
    {
        return parameterModeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getSessionStatus()
    {
        return sessionStatusEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getSortDirectionType()
    {
        return sortDirectionTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getTextFormatType()
    {
        return textFormatTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getTextWrapType()
    {
        return textWrapTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getAxisTypeObject()
    {
        return axisTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getElementNullabilityObject()
    {
        return elementNullabilityObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getExpressionVariableTypeObject()
    {
        return expressionVariableTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getHorizontalAlignmentObject()
    {
        return horizontalAlignmentObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getInputPromptControlStyleObject()
    {
        return inputPromptControlStyleObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getNullOrderingTypeObject()
    {
        return nullOrderingTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getOdaComplexDataTypeObject()
    {
        return odaComplexDataTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getOdaScalarDataTypeObject()
    {
        return odaScalarDataTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getParameterModeObject()
    {
        return parameterModeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getSessionStatusObject()
    {
        return sessionStatusObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getSortDirectionTypeObject()
    {
        return sortDirectionTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getTextFormatTypeObject()
    {
        return textFormatTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getTextWrapTypeObject()
    {
        return textWrapTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignFactory getDesignFactory()
    {
        return (DesignFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents()
    {
        if( isCreated )
            return;
        isCreated = true;

        // Create classes and their features
        andExpressionEClass = createEClass( AND_EXPRESSION );

        atomicExpressionContextEClass = createEClass( ATOMIC_EXPRESSION_CONTEXT );
        createEAttribute( atomicExpressionContextEClass,
                ATOMIC_EXPRESSION_CONTEXT__OPTIONAL );
        createEReference( atomicExpressionContextEClass,
                ATOMIC_EXPRESSION_CONTEXT__VARIABLE );
        createEReference( atomicExpressionContextEClass,
                ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS );

        axisAttributesEClass = createEClass( AXIS_ATTRIBUTES );
        createEAttribute( axisAttributesEClass, AXIS_ATTRIBUTES__AXIS_TYPE );
        createEAttribute( axisAttributesEClass,
                AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT );
        createEReference( axisAttributesEClass,
                AXIS_ATTRIBUTES__RELATED_COLUMNS );

        columnDefinitionEClass = createEClass( COLUMN_DEFINITION );
        createEReference( columnDefinitionEClass, COLUMN_DEFINITION__ATTRIBUTES );
        createEReference( columnDefinitionEClass,
                COLUMN_DEFINITION__USAGE_HINTS );
        createEReference( columnDefinitionEClass,
                COLUMN_DEFINITION__MULTI_DIMENSION_ATTRIBUTES );

        compositeFilterExpressionEClass = createEClass( COMPOSITE_FILTER_EXPRESSION );
        createEReference( compositeFilterExpressionEClass,
                COMPOSITE_FILTER_EXPRESSION__CHILDREN );

        customDataEClass = createEClass( CUSTOM_DATA );
        createEAttribute( customDataEClass, CUSTOM_DATA__PROVIDER_ID );
        createEAttribute( customDataEClass, CUSTOM_DATA__VALUE );
        createEAttribute( customDataEClass, CUSTOM_DATA__DISPLAY_VALUE );

        customFilterExpressionEClass = createEClass( CUSTOM_FILTER_EXPRESSION );
        createEReference( customFilterExpressionEClass,
                CUSTOM_FILTER_EXPRESSION__TYPE );
        createEReference( customFilterExpressionEClass,
                CUSTOM_FILTER_EXPRESSION__CONTEXT );

        dataAccessDesignEClass = createEClass( DATA_ACCESS_DESIGN );
        createEReference( dataAccessDesignEClass,
                DATA_ACCESS_DESIGN__DATA_SET_DESIGN );

        dataElementAttributesEClass = createEClass( DATA_ELEMENT_ATTRIBUTES );
        createEReference( dataElementAttributesEClass,
                DATA_ELEMENT_ATTRIBUTES__IDENTIFIER );
        createEAttribute( dataElementAttributesEClass,
                DATA_ELEMENT_ATTRIBUTES__NAME );
        createEAttribute( dataElementAttributesEClass,
                DATA_ELEMENT_ATTRIBUTES__POSITION );
        createEAttribute( dataElementAttributesEClass,
                DATA_ELEMENT_ATTRIBUTES__NATIVE_DATA_TYPE_CODE );
        createEAttribute( dataElementAttributesEClass,
                DATA_ELEMENT_ATTRIBUTES__PRECISION );
        createEAttribute( dataElementAttributesEClass,
                DATA_ELEMENT_ATTRIBUTES__SCALE );
        createEAttribute( dataElementAttributesEClass,
                DATA_ELEMENT_ATTRIBUTES__NULLABILITY );
        createEReference( dataElementAttributesEClass,
                DATA_ELEMENT_ATTRIBUTES__UI_HINTS );

        dataElementIdentifierEClass = createEClass( DATA_ELEMENT_IDENTIFIER );
        createEAttribute( dataElementIdentifierEClass,
                DATA_ELEMENT_IDENTIFIER__NAME );
        createEAttribute( dataElementIdentifierEClass,
                DATA_ELEMENT_IDENTIFIER__POSITION );

        dataElementIdentifiersEClass = createEClass( DATA_ELEMENT_IDENTIFIERS );
        createEReference( dataElementIdentifiersEClass,
                DATA_ELEMENT_IDENTIFIERS__IDENTIFIERS );

        dataElementUIHintsEClass = createEClass( DATA_ELEMENT_UI_HINTS );
        createEAttribute( dataElementUIHintsEClass,
                DATA_ELEMENT_UI_HINTS__DISPLAY_NAME );
        createEAttribute( dataElementUIHintsEClass,
                DATA_ELEMENT_UI_HINTS__DESCRIPTION );

        dataSetDesignEClass = createEClass( DATA_SET_DESIGN );
        createEAttribute( dataSetDesignEClass, DATA_SET_DESIGN__NAME );
        createEAttribute( dataSetDesignEClass,
                DATA_SET_DESIGN__ODA_EXTENSION_DATA_SET_ID );
        createEReference( dataSetDesignEClass,
                DATA_SET_DESIGN__DATA_SOURCE_DESIGN );
        createEReference( dataSetDesignEClass, DATA_SET_DESIGN__QUERY );
        createEAttribute( dataSetDesignEClass, DATA_SET_DESIGN__DISPLAY_NAME );
        createEReference( dataSetDesignEClass,
                DATA_SET_DESIGN__PUBLIC_PROPERTIES );
        createEReference( dataSetDesignEClass,
                DATA_SET_DESIGN__PRIVATE_PROPERTIES );
        createEReference( dataSetDesignEClass, DATA_SET_DESIGN__RESULT_SETS );
        createEAttribute( dataSetDesignEClass,
                DATA_SET_DESIGN__PRIMARY_RESULT_SET_NAME );
        createEReference( dataSetDesignEClass, DATA_SET_DESIGN__PARAMETERS );

        dataSetParametersEClass = createEClass( DATA_SET_PARAMETERS );
        createEReference( dataSetParametersEClass,
                DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS );
        createEAttribute( dataSetParametersEClass,
                DATA_SET_PARAMETERS__DERIVED_META_DATA );

        dataSetQueryEClass = createEClass( DATA_SET_QUERY );
        createEAttribute( dataSetQueryEClass, DATA_SET_QUERY__QUERY_TEXT );

        dataSourceDesignEClass = createEClass( DATA_SOURCE_DESIGN );
        createEAttribute( dataSourceDesignEClass, DATA_SOURCE_DESIGN__NAME );
        createEAttribute( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__ODA_EXTENSION_ID );
        createEAttribute( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__EFFECTIVE_ODA_EXTENSION_ID );
        createEAttribute( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__ODA_EXTENSION_DATA_SOURCE_ID );
        createEAttribute( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__DISPLAY_NAME );
        createEReference( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__PUBLIC_PROPERTIES );
        createEReference( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__PRIVATE_PROPERTIES );
        createEAttribute( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__LINKED_PROFILE_NAME );
        createEAttribute( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__LINKED_PROFILE_STORE_FILE_PATH );
        createEReference( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__HOST_RESOURCE_IDENTIFIERS );
        createEAttribute( dataSourceDesignEClass,
                DATA_SOURCE_DESIGN__RESOURCE_FILE );

        designerStateEClass = createEClass( DESIGNER_STATE );
        createEAttribute( designerStateEClass, DESIGNER_STATE__VERSION );
        createEReference( designerStateEClass, DESIGNER_STATE__STATE_CONTENT );

        designerStateContentEClass = createEClass( DESIGNER_STATE_CONTENT );
        createEAttribute( designerStateContentEClass,
                DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_STRING );
        createEAttribute( designerStateContentEClass,
                DESIGNER_STATE_CONTENT__STATE_CONTENT_AS_BLOB );

        designSessionRequestEClass = createEClass( DESIGN_SESSION_REQUEST );
        createEReference( designSessionRequestEClass,
                DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN );
        createEAttribute( designSessionRequestEClass,
                DESIGN_SESSION_REQUEST__EDITABLE );
        createEReference( designSessionRequestEClass,
                DESIGN_SESSION_REQUEST__SESSION_LOCALE );
        createEReference( designSessionRequestEClass,
                DESIGN_SESSION_REQUEST__DESIGNER_STATE );

        designSessionResponseEClass = createEClass( DESIGN_SESSION_RESPONSE );
        createEAttribute( designSessionResponseEClass,
                DESIGN_SESSION_RESPONSE__SESSION_STATUS );
        createEReference( designSessionResponseEClass,
                DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN );
        createEReference( designSessionResponseEClass,
                DESIGN_SESSION_RESPONSE__DESIGNER_STATE );

        documentRootEClass = createEClass( DOCUMENT_ROOT );
        createEAttribute( documentRootEClass, DOCUMENT_ROOT__MIXED );
        createEReference( documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP );
        createEReference( documentRootEClass,
                DOCUMENT_ROOT__XSI_SCHEMA_LOCATION );
        createEReference( documentRootEClass, DOCUMENT_ROOT__ODA_DESIGN_SESSION );

        dynamicFilterExpressionEClass = createEClass( DYNAMIC_FILTER_EXPRESSION );
        createEReference( dynamicFilterExpressionEClass,
                DYNAMIC_FILTER_EXPRESSION__CONTEXT );
        createEReference( dynamicFilterExpressionEClass,
                DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE );

        dynamicValuesQueryEClass = createEClass( DYNAMIC_VALUES_QUERY );
        createEReference( dynamicValuesQueryEClass,
                DYNAMIC_VALUES_QUERY__DATA_SET_DESIGN );
        createEAttribute( dynamicValuesQueryEClass,
                DYNAMIC_VALUES_QUERY__ENABLED );
        createEReference( dynamicValuesQueryEClass,
                DYNAMIC_VALUES_QUERY__VALUE_COLUMN_IDENTIFIER );
        createEAttribute( dynamicValuesQueryEClass,
                DYNAMIC_VALUES_QUERY__VALUE_COLUMN );
        createEAttribute( dynamicValuesQueryEClass,
                DYNAMIC_VALUES_QUERY__DISPLAY_NAME_COLUMN );

        expressionArgumentsEClass = createEClass( EXPRESSION_ARGUMENTS );
        createEReference( expressionArgumentsEClass,
                EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS );

        expressionParameterDefinitionEClass = createEClass( EXPRESSION_PARAMETER_DEFINITION );
        createEReference( expressionParameterDefinitionEClass,
                EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES );
        createEReference( expressionParameterDefinitionEClass,
                EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER );

        expressionParametersEClass = createEClass( EXPRESSION_PARAMETERS );
        createEReference( expressionParametersEClass,
                EXPRESSION_PARAMETERS__PARAMETER_DEFINITIONS );

        expressionVariableEClass = createEClass( EXPRESSION_VARIABLE );
        createEAttribute( expressionVariableEClass, EXPRESSION_VARIABLE__TYPE );
        createEAttribute( expressionVariableEClass,
                EXPRESSION_VARIABLE__IDENTIFIER );
        createEAttribute( expressionVariableEClass,
                EXPRESSION_VARIABLE__NATIVE_DATA_TYPE_CODE );

        filterExpressionEClass = createEClass( FILTER_EXPRESSION );
        createEAttribute( filterExpressionEClass, FILTER_EXPRESSION__NEGATABLE );

        filterExpressionTypeEClass = createEClass( FILTER_EXPRESSION_TYPE );
        createEAttribute( filterExpressionTypeEClass,
                FILTER_EXPRESSION_TYPE__DECLARING_EXTENSION_ID );
        createEAttribute( filterExpressionTypeEClass,
                FILTER_EXPRESSION_TYPE__ID );

        inputElementAttributesEClass = createEClass( INPUT_ELEMENT_ATTRIBUTES );
        createEAttribute( inputElementAttributesEClass,
                INPUT_ELEMENT_ATTRIBUTES__DEFAULT_SCALAR_VALUE );
        createEReference( inputElementAttributesEClass,
                INPUT_ELEMENT_ATTRIBUTES__DEFAULT_VALUES );
        createEAttribute( inputElementAttributesEClass,
                INPUT_ELEMENT_ATTRIBUTES__EDITABLE );
        createEAttribute( inputElementAttributesEClass,
                INPUT_ELEMENT_ATTRIBUTES__OPTIONAL );
        createEAttribute( inputElementAttributesEClass,
                INPUT_ELEMENT_ATTRIBUTES__MASKS_VALUE );
        createEReference( inputElementAttributesEClass,
                INPUT_ELEMENT_ATTRIBUTES__STATIC_VALUE_CHOICES );
        createEReference( inputElementAttributesEClass,
                INPUT_ELEMENT_ATTRIBUTES__DYNAMIC_VALUE_CHOICES );
        createEReference( inputElementAttributesEClass,
                INPUT_ELEMENT_ATTRIBUTES__UI_HINTS );

        inputElementUIHintsEClass = createEClass( INPUT_ELEMENT_UI_HINTS );
        createEAttribute( inputElementUIHintsEClass,
                INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE );
        createEAttribute( inputElementUIHintsEClass,
                INPUT_ELEMENT_UI_HINTS__AUTO_SUGGEST_THRESHOLD );

        inputParameterAttributesEClass = createEClass( INPUT_PARAMETER_ATTRIBUTES );
        createEReference( inputParameterAttributesEClass,
                INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES );
        createEReference( inputParameterAttributesEClass,
                INPUT_PARAMETER_ATTRIBUTES__UI_HINTS );

        inputParameterUIHintsEClass = createEClass( INPUT_PARAMETER_UI_HINTS );
        createEAttribute( inputParameterUIHintsEClass,
                INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME );

        localeEClass = createEClass( LOCALE );
        createEAttribute( localeEClass, LOCALE__LANGUAGE );
        createEAttribute( localeEClass, LOCALE__COUNTRY );
        createEAttribute( localeEClass, LOCALE__VARIANT );

        nameValuePairEClass = createEClass( NAME_VALUE_PAIR );
        createEAttribute( nameValuePairEClass, NAME_VALUE_PAIR__NAME );
        createEAttribute( nameValuePairEClass, NAME_VALUE_PAIR__VALUE );

        notExpressionEClass = createEClass( NOT_EXPRESSION );
        createEReference( notExpressionEClass,
                NOT_EXPRESSION__NEGATING_EXPRESSION );

        odaDesignSessionEClass = createEClass( ODA_DESIGN_SESSION );
        createEReference( odaDesignSessionEClass, ODA_DESIGN_SESSION__REQUEST );
        createEReference( odaDesignSessionEClass, ODA_DESIGN_SESSION__RESPONSE );

        orExpressionEClass = createEClass( OR_EXPRESSION );

        outputElementAttributesEClass = createEClass( OUTPUT_ELEMENT_ATTRIBUTES );
        createEAttribute( outputElementAttributesEClass,
                OUTPUT_ELEMENT_ATTRIBUTES__LABEL );
        createEReference( outputElementAttributesEClass,
                OUTPUT_ELEMENT_ATTRIBUTES__FORMATTING_HINTS );
        createEAttribute( outputElementAttributesEClass,
                OUTPUT_ELEMENT_ATTRIBUTES__HELP_TEXT );

        parameterDefinitionEClass = createEClass( PARAMETER_DEFINITION );
        createEAttribute( parameterDefinitionEClass,
                PARAMETER_DEFINITION__IN_OUT_MODE );
        createEReference( parameterDefinitionEClass,
                PARAMETER_DEFINITION__ATTRIBUTES );
        createEReference( parameterDefinitionEClass,
                PARAMETER_DEFINITION__INPUT_ATTRIBUTES );
        createEReference( parameterDefinitionEClass,
                PARAMETER_DEFINITION__OUTPUT_USAGE_HINTS );
        createEReference( parameterDefinitionEClass,
                PARAMETER_DEFINITION__FIELDS );

        parameterFieldDefinitionEClass = createEClass( PARAMETER_FIELD_DEFINITION );
        createEReference( parameterFieldDefinitionEClass,
                PARAMETER_FIELD_DEFINITION__ATTRIBUTES );
        createEReference( parameterFieldDefinitionEClass,
                PARAMETER_FIELD_DEFINITION__INPUT_ATTRIBUTES );
        createEReference( parameterFieldDefinitionEClass,
                PARAMETER_FIELD_DEFINITION__OUTPUT_USAGE_HINTS );

        parameterFieldsEClass = createEClass( PARAMETER_FIELDS );
        createEReference( parameterFieldsEClass,
                PARAMETER_FIELDS__FIELD_COLLECTION );

        propertiesEClass = createEClass( PROPERTIES );
        createEReference( propertiesEClass, PROPERTIES__PROPERTIES );

        propertyEClass = createEClass( PROPERTY );
        createEReference( propertyEClass, PROPERTY__NAME_VALUE );
        createEReference( propertyEClass, PROPERTY__DESIGN_ATTRIBUTES );

        propertyAttributesEClass = createEClass( PROPERTY_ATTRIBUTES );
        createEAttribute( propertyAttributesEClass,
                PROPERTY_ATTRIBUTES__DISPLAY_NAME );
        createEReference( propertyAttributesEClass,
                PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES );
        createEAttribute( propertyAttributesEClass,
                PROPERTY_ATTRIBUTES__DERIVED_META_DATA );

        resourceIdentifiersEClass = createEClass( RESOURCE_IDENTIFIERS );
        createEAttribute( resourceIdentifiersEClass,
                RESOURCE_IDENTIFIERS__APPL_RESOURCE_BASE_URI_STRING );
        createEAttribute( resourceIdentifiersEClass,
                RESOURCE_IDENTIFIERS__DESIGN_RESOURCE_BASE_URI_STRING );

        resultSetColumnsEClass = createEClass( RESULT_SET_COLUMNS );
        createEReference( resultSetColumnsEClass,
                RESULT_SET_COLUMNS__RESULT_COLUMN_DEFINITIONS );

        resultSetCriteriaEClass = createEClass( RESULT_SET_CRITERIA );
        createEReference( resultSetCriteriaEClass,
                RESULT_SET_CRITERIA__FILTER_SPECIFICATION );
        createEReference( resultSetCriteriaEClass,
                RESULT_SET_CRITERIA__ROW_ORDERING );

        resultSetDefinitionEClass = createEClass( RESULT_SET_DEFINITION );
        createEAttribute( resultSetDefinitionEClass,
                RESULT_SET_DEFINITION__NAME );
        createEReference( resultSetDefinitionEClass,
                RESULT_SET_DEFINITION__RESULT_SET_COLUMNS );
        createEReference( resultSetDefinitionEClass,
                RESULT_SET_DEFINITION__CRITERIA );

        resultSetsEClass = createEClass( RESULT_SETS );
        createEReference( resultSetsEClass, RESULT_SETS__RESULT_SET_DEFINITIONS );
        createEAttribute( resultSetsEClass, RESULT_SETS__DERIVED_META_DATA );

        resultSubsetEClass = createEClass( RESULT_SUBSET );
        createEReference( resultSubsetEClass, RESULT_SUBSET__DATA_SET_DESIGN );
        createEAttribute( resultSubsetEClass, RESULT_SUBSET__RESULT_SET_NAME );
        createEReference( resultSubsetEClass, RESULT_SUBSET__COLUMN_IDENTIFIERS );

        scalarValueChoicesEClass = createEClass( SCALAR_VALUE_CHOICES );
        createEReference( scalarValueChoicesEClass,
                SCALAR_VALUE_CHOICES__SCALAR_VALUES );

        scalarValueDefinitionEClass = createEClass( SCALAR_VALUE_DEFINITION );
        createEAttribute( scalarValueDefinitionEClass,
                SCALAR_VALUE_DEFINITION__VALUE );
        createEAttribute( scalarValueDefinitionEClass,
                SCALAR_VALUE_DEFINITION__DISPLAY_NAME );

        sortKeyEClass = createEClass( SORT_KEY );
        createEReference( sortKeyEClass, SORT_KEY__COLUMN_IDENTIFIER );
        createEAttribute( sortKeyEClass, SORT_KEY__COLUMN_NAME );
        createEAttribute( sortKeyEClass, SORT_KEY__COLUMN_POSITION );
        createEAttribute( sortKeyEClass, SORT_KEY__SORT_DIRECTION );
        createEAttribute( sortKeyEClass, SORT_KEY__NULL_VALUE_ORDERING );
        createEAttribute( sortKeyEClass, SORT_KEY__OPTIONAL );

        sortSpecificationEClass = createEClass( SORT_SPECIFICATION );
        createEReference( sortSpecificationEClass,
                SORT_SPECIFICATION__SORT_KEYS );

        staticValuesEClass = createEClass( STATIC_VALUES );
        createEAttribute( staticValuesEClass, STATIC_VALUES__VALUES );

        valueFormatHintsEClass = createEClass( VALUE_FORMAT_HINTS );
        createEAttribute( valueFormatHintsEClass,
                VALUE_FORMAT_HINTS__DISPLAY_SIZE );
        createEAttribute( valueFormatHintsEClass,
                VALUE_FORMAT_HINTS__DISPLAY_FORMAT );
        createEAttribute( valueFormatHintsEClass,
                VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE );
        createEAttribute( valueFormatHintsEClass,
                VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT );
        createEAttribute( valueFormatHintsEClass,
                VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE );

        // Create enums
        axisTypeEEnum = createEEnum( AXIS_TYPE );
        elementNullabilityEEnum = createEEnum( ELEMENT_NULLABILITY );
        expressionVariableTypeEEnum = createEEnum( EXPRESSION_VARIABLE_TYPE );
        horizontalAlignmentEEnum = createEEnum( HORIZONTAL_ALIGNMENT );
        inputPromptControlStyleEEnum = createEEnum( INPUT_PROMPT_CONTROL_STYLE );
        nullOrderingTypeEEnum = createEEnum( NULL_ORDERING_TYPE );
        odaComplexDataTypeEEnum = createEEnum( ODA_COMPLEX_DATA_TYPE );
        odaScalarDataTypeEEnum = createEEnum( ODA_SCALAR_DATA_TYPE );
        parameterModeEEnum = createEEnum( PARAMETER_MODE );
        sessionStatusEEnum = createEEnum( SESSION_STATUS );
        sortDirectionTypeEEnum = createEEnum( SORT_DIRECTION_TYPE );
        textFormatTypeEEnum = createEEnum( TEXT_FORMAT_TYPE );
        textWrapTypeEEnum = createEEnum( TEXT_WRAP_TYPE );

        // Create data types
        axisTypeObjectEDataType = createEDataType( AXIS_TYPE_OBJECT );
        elementNullabilityObjectEDataType = createEDataType( ELEMENT_NULLABILITY_OBJECT );
        expressionVariableTypeObjectEDataType = createEDataType( EXPRESSION_VARIABLE_TYPE_OBJECT );
        horizontalAlignmentObjectEDataType = createEDataType( HORIZONTAL_ALIGNMENT_OBJECT );
        inputPromptControlStyleObjectEDataType = createEDataType( INPUT_PROMPT_CONTROL_STYLE_OBJECT );
        nullOrderingTypeObjectEDataType = createEDataType( NULL_ORDERING_TYPE_OBJECT );
        odaComplexDataTypeObjectEDataType = createEDataType( ODA_COMPLEX_DATA_TYPE_OBJECT );
        odaScalarDataTypeObjectEDataType = createEDataType( ODA_SCALAR_DATA_TYPE_OBJECT );
        parameterModeObjectEDataType = createEDataType( PARAMETER_MODE_OBJECT );
        sessionStatusObjectEDataType = createEDataType( SESSION_STATUS_OBJECT );
        sortDirectionTypeObjectEDataType = createEDataType( SORT_DIRECTION_TYPE_OBJECT );
        textFormatTypeObjectEDataType = createEDataType( TEXT_FORMAT_TYPE_OBJECT );
        textWrapTypeObjectEDataType = createEDataType( TEXT_WRAP_TYPE_OBJECT );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents()
    {
        if( isInitialized )
            return;
        isInitialized = true;

        // Initialize package
        setName( eNAME );
        setNsPrefix( eNS_PREFIX );
        setNsURI( eNS_URI );

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE
                .getEPackage( XMLTypePackage.eNS_URI );

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        andExpressionEClass.getESuperTypes().add(
                this.getCompositeFilterExpression() );
        compositeFilterExpressionEClass.getESuperTypes().add(
                this.getFilterExpression() );
        customFilterExpressionEClass.getESuperTypes().add(
                this.getFilterExpression() );
        dynamicFilterExpressionEClass.getESuperTypes().add(
                this.getFilterExpression() );
        notExpressionEClass.getESuperTypes().add( this.getFilterExpression() );
        orExpressionEClass.getESuperTypes().add(
                this.getCompositeFilterExpression() );

        // Initialize classes and features; add operations and parameters
        initEClass(
                andExpressionEClass,
                AndExpression.class,
                "AndExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$

        initEClass(
                atomicExpressionContextEClass,
                AtomicExpressionContext.class,
                "AtomicExpressionContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getAtomicExpressionContext_Optional(),
                theXMLTypePackage.getBoolean(),
                "optional", "false", 0, 1, AtomicExpressionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getAtomicExpressionContext_Variable(),
                this.getExpressionVariable(),
                null,
                "variable", null, 0, 1, AtomicExpressionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getAtomicExpressionContext_Arguments(),
                this.getExpressionArguments(),
                null,
                "arguments", null, 0, 1, AtomicExpressionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                axisAttributesEClass,
                AxisAttributes.class,
                "AxisAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getAxisAttributes_AxisType(),
                this.getAxisType(),
                "axisType", "Measure", 0, 1, AxisAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getAxisAttributes_OnColumnLayout(),
                theXMLTypePackage.getBoolean(),
                "onColumnLayout", "true", 0, 1, AxisAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getAxisAttributes_RelatedColumns(),
                this.getResultSubset(),
                null,
                "relatedColumns", null, 0, 1, AxisAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                columnDefinitionEClass,
                ColumnDefinition.class,
                "ColumnDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getColumnDefinition_Attributes(),
                this.getDataElementAttributes(),
                null,
                "attributes", null, 1, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getColumnDefinition_UsageHints(),
                this.getOutputElementAttributes(),
                null,
                "usageHints", null, 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getColumnDefinition_MultiDimensionAttributes(),
                this.getAxisAttributes(),
                null,
                "multiDimensionAttributes", null, 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                compositeFilterExpressionEClass,
                CompositeFilterExpression.class,
                "CompositeFilterExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getCompositeFilterExpression_Children(),
                this.getFilterExpression(),
                null,
                "children", null, 1, -1, CompositeFilterExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                customDataEClass,
                CustomData.class,
                "CustomData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getCustomData_ProviderId(),
                theXMLTypePackage.getString(),
                "providerId", null, 1, 1, CustomData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getCustomData_Value(),
                theXMLTypePackage.getAnySimpleType(),
                "value", null, 1, 1, CustomData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getCustomData_DisplayValue(),
                theXMLTypePackage.getString(),
                "displayValue", null, 0, 1, CustomData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                customFilterExpressionEClass,
                CustomFilterExpression.class,
                "CustomFilterExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getCustomFilterExpression_Type(),
                this.getFilterExpressionType(),
                null,
                "type", null, 1, 1, CustomFilterExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getCustomFilterExpression_Context(),
                this.getAtomicExpressionContext(),
                null,
                "context", null, 1, 1, CustomFilterExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dataAccessDesignEClass,
                DataAccessDesign.class,
                "DataAccessDesign", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getDataAccessDesign_DataSetDesign(),
                this.getDataSetDesign(),
                null,
                "dataSetDesign", null, 0, 1, DataAccessDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dataElementAttributesEClass,
                DataElementAttributes.class,
                "DataElementAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getDataElementAttributes_Identifier(),
                this.getDataElementIdentifier(),
                null,
                "identifier", null, 1, 1, DataElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataElementAttributes_Name(),
                theXMLTypePackage.getString(),
                "name", null, 1, 1, DataElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataElementAttributes_Position(),
                theXMLTypePackage.getUnsignedShort(),
                "position", null, 0, 1, DataElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataElementAttributes_NativeDataTypeCode(),
                theXMLTypePackage.getInt(),
                "nativeDataTypeCode", "0", 0, 1, DataElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getDataElementAttributes_Precision(),
                theXMLTypePackage.getInt(),
                "precision", "-1", 0, 1, DataElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getDataElementAttributes_Scale(),
                theXMLTypePackage.getInt(),
                "scale", "-1", 0, 1, DataElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getDataElementAttributes_Nullability(),
                this.getElementNullability(),
                "nullability", "Unknown", 0, 1, DataElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getDataElementAttributes_UiHints(),
                this.getDataElementUIHints(),
                null,
                "uiHints", null, 0, 1, DataElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dataElementIdentifierEClass,
                DataElementIdentifier.class,
                "DataElementIdentifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDataElementIdentifier_Name(),
                theXMLTypePackage.getString(),
                "name", null, 1, 1, DataElementIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataElementIdentifier_Position(),
                theXMLTypePackage.getUnsignedShort(),
                "position", null, 0, 1, DataElementIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dataElementIdentifiersEClass,
                DataElementIdentifiers.class,
                "DataElementIdentifiers", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getDataElementIdentifiers_Identifiers(),
                this.getDataElementIdentifier(),
                null,
                "identifiers", null, 1, -1, DataElementIdentifiers.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dataElementUIHintsEClass,
                DataElementUIHints.class,
                "DataElementUIHints", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDataElementUIHints_DisplayName(),
                theXMLTypePackage.getString(),
                "displayName", null, 0, 1, DataElementUIHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataElementUIHints_Description(),
                theXMLTypePackage.getString(),
                "description", null, 0, 1, DataElementUIHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dataSetDesignEClass,
                DataSetDesign.class,
                "DataSetDesign", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDataSetDesign_Name(),
                theXMLTypePackage.getString(),
                "name", null, 1, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSetDesign_OdaExtensionDataSetId(),
                theXMLTypePackage.getString(),
                "odaExtensionDataSetId", null, 0, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSetDesign_DataSourceDesign(),
                this.getDataSourceDesign(),
                null,
                "dataSourceDesign", null, 0, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSetDesign_Query(),
                this.getDataSetQuery(),
                null,
                "query", null, 1, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSetDesign_DisplayName(),
                theXMLTypePackage.getString(),
                "displayName", null, 0, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSetDesign_PublicProperties(),
                this.getProperties(),
                null,
                "publicProperties", null, 0, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSetDesign_PrivateProperties(),
                this.getProperties(),
                null,
                "privateProperties", null, 0, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSetDesign_ResultSets(),
                this.getResultSets(),
                null,
                "resultSets", null, 0, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSetDesign_PrimaryResultSetName(),
                theXMLTypePackage.getString(),
                "primaryResultSetName", null, 0, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSetDesign_Parameters(),
                this.getDataSetParameters(),
                null,
                "parameters", null, 0, 1, DataSetDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dataSetParametersEClass,
                DataSetParameters.class,
                "DataSetParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getDataSetParameters_ParameterDefinitions(),
                this.getParameterDefinition(),
                null,
                "parameterDefinitions", null, 1, -1, DataSetParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSetParameters_DerivedMetaData(),
                theXMLTypePackage.getBoolean(),
                "derivedMetaData", "true", 0, 1, DataSetParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(
                dataSetQueryEClass,
                DataSetQuery.class,
                "DataSetQuery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDataSetQuery_QueryText(),
                theXMLTypePackage.getString(),
                "queryText", null, 1, 1, DataSetQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dataSourceDesignEClass,
                DataSourceDesign.class,
                "DataSourceDesign", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDataSourceDesign_Name(),
                theXMLTypePackage.getString(),
                "name", null, 1, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSourceDesign_OdaExtensionId(),
                theXMLTypePackage.getString(),
                "odaExtensionId", null, 1, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSourceDesign_EffectiveOdaExtensionId(),
                theXMLTypePackage.getString(),
                "effectiveOdaExtensionId", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSourceDesign_OdaExtensionDataSourceId(),
                theXMLTypePackage.getString(),
                "odaExtensionDataSourceId", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSourceDesign_DisplayName(),
                theXMLTypePackage.getString(),
                "displayName", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSourceDesign_PublicProperties(),
                this.getProperties(),
                null,
                "publicProperties", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSourceDesign_PrivateProperties(),
                this.getProperties(),
                null,
                "privateProperties", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSourceDesign_LinkedProfileName(),
                theXMLTypePackage.getString(),
                "linkedProfileName", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSourceDesign_LinkedProfileStoreFilePath(),
                theXMLTypePackage.getString(),
                "linkedProfileStoreFilePath", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDataSourceDesign_HostResourceIdentifiers(),
                this.getResourceIdentifiers(),
                null,
                "hostResourceIdentifiers", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDataSourceDesign_ResourceFile(),
                theXMLTypePackage.getString(),
                "resourceFile", null, 0, 1, DataSourceDesign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                designerStateEClass,
                DesignerState.class,
                "DesignerState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDesignerState_Version(),
                theXMLTypePackage.getString(),
                "version", null, 1, 1, DesignerState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDesignerState_StateContent(),
                this.getDesignerStateContent(),
                null,
                "stateContent", null, 1, 1, DesignerState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                designerStateContentEClass,
                DesignerStateContent.class,
                "DesignerStateContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDesignerStateContent_StateContentAsString(),
                theXMLTypePackage.getString(),
                "stateContentAsString", null, 0, 1, DesignerStateContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDesignerStateContent_StateContentAsBlob(),
                theXMLTypePackage.getBase64Binary(),
                "stateContentAsBlob", null, 0, 1, DesignerStateContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                designSessionRequestEClass,
                DesignSessionRequest.class,
                "DesignSessionRequest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getDesignSessionRequest_DataAccessDesign(),
                this.getDataAccessDesign(),
                null,
                "dataAccessDesign", null, 1, 1, DesignSessionRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDesignSessionRequest_Editable(),
                theXMLTypePackage.getBoolean(),
                "editable", "true", 0, 1, DesignSessionRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getDesignSessionRequest_SessionLocale(),
                this.getLocale(),
                null,
                "sessionLocale", null, 0, 1, DesignSessionRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDesignSessionRequest_DesignerState(),
                this.getDesignerState(),
                null,
                "designerState", null, 0, 1, DesignSessionRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                designSessionResponseEClass,
                DesignSessionResponse.class,
                "DesignSessionResponse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDesignSessionResponse_SessionStatus(),
                this.getSessionStatus(),
                "sessionStatus", "Ok", 0, 1, DesignSessionResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getDesignSessionResponse_DataAccessDesign(),
                this.getDataAccessDesign(),
                null,
                "dataAccessDesign", null, 1, 1, DesignSessionResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDesignSessionResponse_DesignerState(),
                this.getDesignerState(),
                null,
                "designerState", null, 0, 1, DesignSessionResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                documentRootEClass,
                DocumentRoot.class,
                "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getDocumentRoot_Mixed(),
                ecorePackage.getEFeatureMapEntry(),
                "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_XMLNSPrefixMap(),
                ecorePackage.getEStringToStringMapEntry(),
                null,
                "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_XSISchemaLocation(),
                ecorePackage.getEStringToStringMapEntry(),
                null,
                "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_OdaDesignSession(),
                this.getOdaDesignSession(),
                null,
                "odaDesignSession", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dynamicFilterExpressionEClass,
                DynamicFilterExpression.class,
                "DynamicFilterExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getDynamicFilterExpression_Context(),
                this.getAtomicExpressionContext(),
                null,
                "context", null, 1, 1, DynamicFilterExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getDynamicFilterExpression_DefaultType(),
                this.getFilterExpressionType(),
                null,
                "defaultType", null, 0, 1, DynamicFilterExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                dynamicValuesQueryEClass,
                DynamicValuesQuery.class,
                "DynamicValuesQuery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getDynamicValuesQuery_DataSetDesign(),
                this.getDataSetDesign(),
                null,
                "dataSetDesign", null, 1, 1, DynamicValuesQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDynamicValuesQuery_Enabled(),
                theXMLTypePackage.getBoolean(),
                "enabled", "true", 0, 1, DynamicValuesQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getDynamicValuesQuery_ValueColumnIdentifier(),
                this.getDataElementIdentifier(),
                null,
                "valueColumnIdentifier", null, 1, 1, DynamicValuesQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDynamicValuesQuery_ValueColumn(),
                theXMLTypePackage.getString(),
                "valueColumn", null, 1, 1, DynamicValuesQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getDynamicValuesQuery_DisplayNameColumn(),
                theXMLTypePackage.getString(),
                "displayNameColumn", null, 0, 1, DynamicValuesQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                expressionArgumentsEClass,
                ExpressionArguments.class,
                "ExpressionArguments", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getExpressionArguments_ExpressionParameters(),
                this.getExpressionParameters(),
                null,
                "expressionParameters", null, 1, 1, ExpressionArguments.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                expressionParameterDefinitionEClass,
                ExpressionParameterDefinition.class,
                "ExpressionParameterDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getExpressionParameterDefinition_StaticValues(),
                this.getStaticValues(),
                null,
                "staticValues", null, 0, 1, ExpressionParameterDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getExpressionParameterDefinition_DynamicInputParameter(),
                this.getParameterDefinition(),
                null,
                "dynamicInputParameter", null, 0, 1, ExpressionParameterDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                expressionParametersEClass,
                ExpressionParameters.class,
                "ExpressionParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getExpressionParameters_ParameterDefinitions(),
                this.getExpressionParameterDefinition(),
                null,
                "parameterDefinitions", null, 1, -1, ExpressionParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                expressionVariableEClass,
                ExpressionVariable.class,
                "ExpressionVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getExpressionVariable_Type(),
                this.getExpressionVariableType(),
                "type", "ResultSetColumn", 0, 1, ExpressionVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getExpressionVariable_Identifier(),
                theXMLTypePackage.getString(),
                "identifier", null, 1, 1, ExpressionVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getExpressionVariable_NativeDataTypeCode(),
                theXMLTypePackage.getInt(),
                "nativeDataTypeCode", null, 0, 1, ExpressionVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                filterExpressionEClass,
                FilterExpression.class,
                "FilterExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getFilterExpression_Negatable(),
                theXMLTypePackage.getBoolean(),
                "negatable", "false", 0, 1, FilterExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(
                filterExpressionTypeEClass,
                FilterExpressionType.class,
                "FilterExpressionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getFilterExpressionType_DeclaringExtensionId(),
                theXMLTypePackage.getString(),
                "declaringExtensionId", null, 1, 1, FilterExpressionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getFilterExpressionType_Id(),
                theXMLTypePackage.getString(),
                "id", null, 1, 1, FilterExpressionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                inputElementAttributesEClass,
                InputElementAttributes.class,
                "InputElementAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getInputElementAttributes_DefaultScalarValue(),
                theXMLTypePackage.getString(),
                "defaultScalarValue", null, 0, 1, InputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getInputElementAttributes_DefaultValues(),
                this.getStaticValues(),
                null,
                "defaultValues", null, 0, 1, InputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getInputElementAttributes_Editable(),
                theXMLTypePackage.getBoolean(),
                "editable", "true", 0, 1, InputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getInputElementAttributes_Optional(),
                theXMLTypePackage.getBoolean(),
                "optional", "false", 0, 1, InputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getInputElementAttributes_MasksValue(),
                theXMLTypePackage.getBoolean(),
                "masksValue", "false", 0, 1, InputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getInputElementAttributes_StaticValueChoices(),
                this.getScalarValueChoices(),
                null,
                "staticValueChoices", null, 0, 1, InputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getInputElementAttributes_DynamicValueChoices(),
                this.getDynamicValuesQuery(),
                null,
                "dynamicValueChoices", null, 0, 1, InputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getInputElementAttributes_UiHints(),
                this.getInputElementUIHints(),
                null,
                "uiHints", null, 0, 1, InputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                inputElementUIHintsEClass,
                InputElementUIHints.class,
                "InputElementUIHints", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getInputElementUIHints_PromptStyle(),
                this.getInputPromptControlStyle(),
                "promptStyle", null, 0, 1, InputElementUIHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getInputElementUIHints_AutoSuggestThreshold(),
                theXMLTypePackage.getInt(),
                "autoSuggestThreshold", "1", 0, 1, InputElementUIHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(
                inputParameterAttributesEClass,
                InputParameterAttributes.class,
                "InputParameterAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getInputParameterAttributes_ElementAttributes(),
                this.getInputElementAttributes(),
                null,
                "elementAttributes", null, 1, 1, InputParameterAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getInputParameterAttributes_UiHints(),
                this.getInputParameterUIHints(),
                null,
                "uiHints", null, 0, 1, InputParameterAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                inputParameterUIHintsEClass,
                InputParameterUIHints.class,
                "InputParameterUIHints", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getInputParameterUIHints_GroupPromptDisplayName(),
                theXMLTypePackage.getString(),
                "groupPromptDisplayName", null, 0, 1, InputParameterUIHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                localeEClass,
                Locale.class,
                "Locale", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getLocale_Language(),
                theXMLTypePackage.getString(),
                "language", "en", 0, 1, Locale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getLocale_Country(),
                theXMLTypePackage.getString(),
                "country", null, 0, 1, Locale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getLocale_Variant(),
                theXMLTypePackage.getString(),
                "variant", null, 0, 1, Locale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                nameValuePairEClass,
                NameValuePair.class,
                "NameValuePair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getNameValuePair_Name(),
                theXMLTypePackage.getString(),
                "name", null, 1, 1, NameValuePair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getNameValuePair_Value(),
                theXMLTypePackage.getString(),
                "value", null, 0, 1, NameValuePair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                notExpressionEClass,
                NotExpression.class,
                "NotExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getNotExpression_NegatingExpression(),
                this.getFilterExpression(),
                null,
                "negatingExpression", null, 1, 1, NotExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                odaDesignSessionEClass,
                OdaDesignSession.class,
                "OdaDesignSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getOdaDesignSession_Request(),
                this.getDesignSessionRequest(),
                null,
                "request", null, 1, 1, OdaDesignSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getOdaDesignSession_Response(),
                this.getDesignSessionResponse(),
                null,
                "response", null, 0, 1, OdaDesignSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                orExpressionEClass,
                OrExpression.class,
                "OrExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$

        initEClass(
                outputElementAttributesEClass,
                OutputElementAttributes.class,
                "OutputElementAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getOutputElementAttributes_Label(),
                theXMLTypePackage.getString(),
                "label", null, 0, 1, OutputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getOutputElementAttributes_FormattingHints(),
                this.getValueFormatHints(),
                null,
                "formattingHints", null, 0, 1, OutputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getOutputElementAttributes_HelpText(),
                theXMLTypePackage.getString(),
                "helpText", null, 0, 1, OutputElementAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                parameterDefinitionEClass,
                ParameterDefinition.class,
                "ParameterDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getParameterDefinition_InOutMode(),
                this.getParameterMode(),
                "inOutMode", "In", 0, 1, ParameterDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getParameterDefinition_Attributes(),
                this.getDataElementAttributes(),
                null,
                "attributes", null, 1, 1, ParameterDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getParameterDefinition_InputAttributes(),
                this.getInputParameterAttributes(),
                null,
                "inputAttributes", null, 0, 1, ParameterDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getParameterDefinition_OutputUsageHints(),
                this.getOutputElementAttributes(),
                null,
                "outputUsageHints", null, 0, 1, ParameterDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getParameterDefinition_Fields(),
                this.getParameterFields(),
                null,
                "fields", null, 0, 1, ParameterDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                parameterFieldDefinitionEClass,
                ParameterFieldDefinition.class,
                "ParameterFieldDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getParameterFieldDefinition_Attributes(),
                this.getDataElementAttributes(),
                null,
                "attributes", null, 1, 1, ParameterFieldDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getParameterFieldDefinition_InputAttributes(),
                this.getInputElementAttributes(),
                null,
                "inputAttributes", null, 0, 1, ParameterFieldDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getParameterFieldDefinition_OutputUsageHints(),
                this.getOutputElementAttributes(),
                null,
                "outputUsageHints", null, 0, 1, ParameterFieldDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                parameterFieldsEClass,
                ParameterFields.class,
                "ParameterFields", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getParameterFields_FieldCollection(),
                this.getParameterFieldDefinition(),
                null,
                "fieldCollection", null, 1, -1, ParameterFields.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                propertiesEClass,
                Properties.class,
                "Properties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getProperties_Properties(),
                this.getProperty(),
                null,
                "properties", null, 1, -1, Properties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                propertyEClass,
                Property.class,
                "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getProperty_NameValue(),
                this.getNameValuePair(),
                null,
                "nameValue", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getProperty_DesignAttributes(),
                this.getPropertyAttributes(),
                null,
                "designAttributes", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                propertyAttributesEClass,
                PropertyAttributes.class,
                "PropertyAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getPropertyAttributes_DisplayName(),
                theXMLTypePackage.getString(),
                "displayName", null, 0, 1, PropertyAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getPropertyAttributes_ElementAttributes(),
                this.getInputElementAttributes(),
                null,
                "elementAttributes", null, 0, 1, PropertyAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getPropertyAttributes_DerivedMetaData(),
                theXMLTypePackage.getBoolean(),
                "derivedMetaData", "true", 0, 1, PropertyAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(
                resourceIdentifiersEClass,
                ResourceIdentifiers.class,
                "ResourceIdentifiers", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getResourceIdentifiers_ApplResourceBaseURIString(),
                theXMLTypePackage.getString(),
                "applResourceBaseURIString", null, 0, 1, ResourceIdentifiers.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getResourceIdentifiers_DesignResourceBaseURIString(),
                theXMLTypePackage.getString(),
                "designResourceBaseURIString", null, 0, 1, ResourceIdentifiers.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                resultSetColumnsEClass,
                ResultSetColumns.class,
                "ResultSetColumns", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getResultSetColumns_ResultColumnDefinitions(),
                this.getColumnDefinition(),
                null,
                "resultColumnDefinitions", null, 1, -1, ResultSetColumns.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                resultSetCriteriaEClass,
                ResultSetCriteria.class,
                "ResultSetCriteria", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getResultSetCriteria_FilterSpecification(),
                this.getFilterExpression(),
                null,
                "filterSpecification", null, 0, 1, ResultSetCriteria.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getResultSetCriteria_RowOrdering(),
                this.getSortSpecification(),
                null,
                "rowOrdering", null, 0, 1, ResultSetCriteria.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                resultSetDefinitionEClass,
                ResultSetDefinition.class,
                "ResultSetDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getResultSetDefinition_Name(),
                theXMLTypePackage.getString(),
                "name", null, 0, 1, ResultSetDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getResultSetDefinition_ResultSetColumns(),
                this.getResultSetColumns(),
                null,
                "resultSetColumns", null, 1, 1, ResultSetDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getResultSetDefinition_Criteria(),
                this.getResultSetCriteria(),
                null,
                "criteria", null, 0, 1, ResultSetDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                resultSetsEClass,
                ResultSets.class,
                "ResultSets", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getResultSets_ResultSetDefinitions(),
                this.getResultSetDefinition(),
                null,
                "resultSetDefinitions", null, 1, -1, ResultSets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getResultSets_DerivedMetaData(),
                theXMLTypePackage.getBoolean(),
                "derivedMetaData", "true", 0, 1, ResultSets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(
                resultSubsetEClass,
                ResultSubset.class,
                "ResultSubset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getResultSubset_DataSetDesign(),
                this.getDataSetDesign(),
                null,
                "dataSetDesign", null, 0, 1, ResultSubset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getResultSubset_ResultSetName(),
                theXMLTypePackage.getString(),
                "resultSetName", null, 0, 1, ResultSubset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEReference(
                getResultSubset_ColumnIdentifiers(),
                this.getDataElementIdentifiers(),
                null,
                "columnIdentifiers", null, 1, 1, ResultSubset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                scalarValueChoicesEClass,
                ScalarValueChoices.class,
                "ScalarValueChoices", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getScalarValueChoices_ScalarValues(),
                this.getScalarValueDefinition(),
                null,
                "scalarValues", null, 1, -1, ScalarValueChoices.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                scalarValueDefinitionEClass,
                ScalarValueDefinition.class,
                "ScalarValueDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getScalarValueDefinition_Value(),
                theXMLTypePackage.getString(),
                "value", null, 1, 1, ScalarValueDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getScalarValueDefinition_DisplayName(),
                theXMLTypePackage.getString(),
                "displayName", null, 0, 1, ScalarValueDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                sortKeyEClass,
                SortKey.class,
                "SortKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getSortKey_ColumnIdentifier(),
                this.getDataElementIdentifier(),
                null,
                "columnIdentifier", null, 1, 1, SortKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getSortKey_ColumnName(),
                theXMLTypePackage.getString(),
                "columnName", null, 1, 1, SortKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getSortKey_ColumnPosition(),
                theXMLTypePackage.getUnsignedShort(),
                "columnPosition", null, 0, 1, SortKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getSortKey_SortDirection(),
                this.getSortDirectionType(),
                "sortDirection", "Ascending", 0, 1, SortKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getSortKey_NullValueOrdering(),
                this.getNullOrderingType(),
                "nullValueOrdering", null, 0, 1, SortKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getSortKey_Optional(),
                theXMLTypePackage.getBoolean(),
                "optional", "false", 0, 1, SortKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(
                sortSpecificationEClass,
                SortSpecification.class,
                "SortSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEReference(
                getSortSpecification_SortKeys(),
                this.getSortKey(),
                null,
                "sortKeys", null, 0, -1, SortSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                staticValuesEClass,
                StaticValues.class,
                "StaticValues", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getStaticValues_Values(),
                theXMLTypePackage.getAnySimpleType(),
                "values", null, 1, -1, StaticValues.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$

        initEClass(
                valueFormatHintsEClass,
                ValueFormatHints.class,
                "ValueFormatHints", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEAttribute(
                getValueFormatHints_DisplaySize(),
                theXMLTypePackage.getInt(),
                "displaySize", "-1", 0, 1, ValueFormatHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getValueFormatHints_DisplayFormat(),
                theXMLTypePackage.getString(),
                "displayFormat", null, 0, 1, ValueFormatHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$
        initEAttribute(
                getValueFormatHints_TextFormatType(),
                this.getTextFormatType(),
                "textFormatType", "Plain", 0, 1, ValueFormatHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getValueFormatHints_HorizontalAlignment(),
                this.getHorizontalAlignment(),
                "horizontalAlignment", "Automatic", 0, 1, ValueFormatHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getValueFormatHints_TextWrapType(),
                this.getTextWrapType(),
                "textWrapType", "None", 0, 1, ValueFormatHints.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED ); //$NON-NLS-1$ //$NON-NLS-2$

        // Initialize enums and add enum literals
        initEEnum( axisTypeEEnum, AxisType.class, "AxisType" ); //$NON-NLS-1$
        addEEnumLiteral( axisTypeEEnum, AxisType.DIMENSION_MEMBER_LITERAL );
        addEEnumLiteral( axisTypeEEnum, AxisType.DIMENSION_ATTRIBUTE_LITERAL );
        addEEnumLiteral( axisTypeEEnum, AxisType.MEASURE_LITERAL );

        initEEnum( elementNullabilityEEnum, ElementNullability.class,
                "ElementNullability" ); //$NON-NLS-1$
        addEEnumLiteral( elementNullabilityEEnum,
                ElementNullability.UNKNOWN_LITERAL );
        addEEnumLiteral( elementNullabilityEEnum,
                ElementNullability.NULLABLE_LITERAL );
        addEEnumLiteral( elementNullabilityEEnum,
                ElementNullability.NOT_NULLABLE_LITERAL );

        initEEnum( expressionVariableTypeEEnum, ExpressionVariableType.class,
                "ExpressionVariableType" ); //$NON-NLS-1$
        addEEnumLiteral( expressionVariableTypeEEnum,
                ExpressionVariableType.RESULT_SET_COLUMN );
        addEEnumLiteral( expressionVariableTypeEEnum,
                ExpressionVariableType.INSTANCE_OF );
        addEEnumLiteral( expressionVariableTypeEEnum,
                ExpressionVariableType.QUERY_EXPRESSION );

        initEEnum( horizontalAlignmentEEnum, HorizontalAlignment.class,
                "HorizontalAlignment" ); //$NON-NLS-1$
        addEEnumLiteral( horizontalAlignmentEEnum,
                HorizontalAlignment.AUTOMATIC_LITERAL );
        addEEnumLiteral( horizontalAlignmentEEnum,
                HorizontalAlignment.LEFT_LITERAL );
        addEEnumLiteral( horizontalAlignmentEEnum,
                HorizontalAlignment.CENTER_LITERAL );
        addEEnumLiteral( horizontalAlignmentEEnum,
                HorizontalAlignment.RIGHT_LITERAL );
        addEEnumLiteral( horizontalAlignmentEEnum,
                HorizontalAlignment.LEFT_AND_RIGHT_LITERAL );

        initEEnum( inputPromptControlStyleEEnum, InputPromptControlStyle.class,
                "InputPromptControlStyle" ); //$NON-NLS-1$
        addEEnumLiteral( inputPromptControlStyleEEnum,
                InputPromptControlStyle.TEXT_FIELD_LITERAL );
        addEEnumLiteral( inputPromptControlStyleEEnum,
                InputPromptControlStyle.SELECTABLE_LIST_LITERAL );
        addEEnumLiteral( inputPromptControlStyleEEnum,
                InputPromptControlStyle.SELECTABLE_LIST_WITH_TEXT_FIELD_LITERAL );
        addEEnumLiteral( inputPromptControlStyleEEnum,
                InputPromptControlStyle.CHECK_BOX_LITERAL );
        addEEnumLiteral( inputPromptControlStyleEEnum,
                InputPromptControlStyle.RADIO_BUTTON_LITERAL );

        initEEnum( nullOrderingTypeEEnum, NullOrderingType.class,
                "NullOrderingType" ); //$NON-NLS-1$
        addEEnumLiteral( nullOrderingTypeEEnum, NullOrderingType.UNKNOWN );
        addEEnumLiteral( nullOrderingTypeEEnum, NullOrderingType.NULLS_FIRST );
        addEEnumLiteral( nullOrderingTypeEEnum, NullOrderingType.NULLS_LAST );

        initEEnum( odaComplexDataTypeEEnum, OdaComplexDataType.class,
                "OdaComplexDataType" ); //$NON-NLS-1$
        addEEnumLiteral( odaComplexDataTypeEEnum,
                OdaComplexDataType.STRUCTURE_LITERAL );
        addEEnumLiteral( odaComplexDataTypeEEnum,
                OdaComplexDataType.TABLE_LITERAL );

        initEEnum( odaScalarDataTypeEEnum, OdaScalarDataType.class,
                "OdaScalarDataType" ); //$NON-NLS-1$
        addEEnumLiteral( odaScalarDataTypeEEnum, OdaScalarDataType.DATE_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum,
                OdaScalarDataType.DOUBLE_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum,
                OdaScalarDataType.INTEGER_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum,
                OdaScalarDataType.STRING_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum, OdaScalarDataType.TIME_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum,
                OdaScalarDataType.TIMESTAMP_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum,
                OdaScalarDataType.DECIMAL_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum, OdaScalarDataType.BLOB_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum, OdaScalarDataType.CLOB_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum,
                OdaScalarDataType.BOOLEAN_LITERAL );
        addEEnumLiteral( odaScalarDataTypeEEnum,
                OdaScalarDataType.JAVA_OBJECT_LITERAL );

        initEEnum( parameterModeEEnum, ParameterMode.class, "ParameterMode" ); //$NON-NLS-1$
        addEEnumLiteral( parameterModeEEnum, ParameterMode.IN_LITERAL );
        addEEnumLiteral( parameterModeEEnum, ParameterMode.OUT_LITERAL );
        addEEnumLiteral( parameterModeEEnum, ParameterMode.IN_OUT_LITERAL );

        initEEnum( sessionStatusEEnum, SessionStatus.class, "SessionStatus" ); //$NON-NLS-1$
        addEEnumLiteral( sessionStatusEEnum, SessionStatus.OK_LITERAL );
        addEEnumLiteral( sessionStatusEEnum,
                SessionStatus.USER_CANCELLED_LITERAL );
        addEEnumLiteral( sessionStatusEEnum, SessionStatus.LOGIN_FAILED_LITERAL );
        addEEnumLiteral( sessionStatusEEnum, SessionStatus.ERROR_LITERAL );

        initEEnum( sortDirectionTypeEEnum, SortDirectionType.class,
                "SortDirectionType" ); //$NON-NLS-1$
        addEEnumLiteral( sortDirectionTypeEEnum, SortDirectionType.ASCENDING );
        addEEnumLiteral( sortDirectionTypeEEnum, SortDirectionType.DESCENDING );

        initEEnum( textFormatTypeEEnum, TextFormatType.class, "TextFormatType" ); //$NON-NLS-1$
        addEEnumLiteral( textFormatTypeEEnum, TextFormatType.PLAIN_LITERAL );
        addEEnumLiteral( textFormatTypeEEnum, TextFormatType.HTML_LITERAL );
        addEEnumLiteral( textFormatTypeEEnum, TextFormatType.RTF_LITERAL );

        initEEnum( textWrapTypeEEnum, TextWrapType.class, "TextWrapType" ); //$NON-NLS-1$
        addEEnumLiteral( textWrapTypeEEnum, TextWrapType.NONE_LITERAL );
        addEEnumLiteral( textWrapTypeEEnum, TextWrapType.WORD_LITERAL );

        // Initialize data types
        initEDataType( axisTypeObjectEDataType, AxisType.class,
                "AxisTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                elementNullabilityObjectEDataType,
                ElementNullability.class,
                "ElementNullabilityObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                expressionVariableTypeObjectEDataType,
                ExpressionVariableType.class,
                "ExpressionVariableTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                horizontalAlignmentObjectEDataType,
                HorizontalAlignment.class,
                "HorizontalAlignmentObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                inputPromptControlStyleObjectEDataType,
                InputPromptControlStyle.class,
                "InputPromptControlStyleObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                nullOrderingTypeObjectEDataType,
                NullOrderingType.class,
                "NullOrderingTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                odaComplexDataTypeObjectEDataType,
                OdaComplexDataType.class,
                "OdaComplexDataTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                odaScalarDataTypeObjectEDataType,
                OdaScalarDataType.class,
                "OdaScalarDataTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                parameterModeObjectEDataType,
                ParameterMode.class,
                "ParameterModeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                sessionStatusObjectEDataType,
                SessionStatus.class,
                "SessionStatusObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                sortDirectionTypeObjectEDataType,
                SortDirectionType.class,
                "SortDirectionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                textFormatTypeObjectEDataType,
                TextFormatType.class,
                "TextFormatTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$
        initEDataType(
                textWrapTypeObjectEDataType,
                TextWrapType.class,
                "TextWrapTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS ); //$NON-NLS-1$

        // Create resource
        createResource( eNS_URI );

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations()
    {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$			
        addAnnotation( andExpressionEClass, source, new String[]
        { "name", "AndExpression", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( atomicExpressionContextEClass, source, new String[]
        { "name", "AtomicExpressionContext", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getAtomicExpressionContext_Optional(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "optional", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getAtomicExpressionContext_Variable(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "variable", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getAtomicExpressionContext_Arguments(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "arguments", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( axisAttributesEClass, source, new String[]
        { "name", "AxisAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getAxisAttributes_AxisType(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "axisType", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getAxisAttributes_OnColumnLayout(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "onColumnLayout", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getAxisAttributes_RelatedColumns(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "relatedColumns", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( axisTypeEEnum, source, new String[]
        { "name", "AxisType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( axisTypeObjectEDataType, source, new String[]
        { "name", "AxisType:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "AxisType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( columnDefinitionEClass, source, new String[]
        { "name", "ColumnDefinition", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getColumnDefinition_Attributes(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "attributes", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getColumnDefinition_UsageHints(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "usageHints", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getColumnDefinition_MultiDimensionAttributes(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "multiDimensionAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( compositeFilterExpressionEClass, source, new String[]
        { "name", "CompositeFilterExpression", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getCompositeFilterExpression_Children(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "children", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( customDataEClass, source, new String[]
        { "name", "CustomData", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getCustomData_ProviderId(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "providerId", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getCustomData_Value(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getCustomData_DisplayValue(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "displayValue", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( customFilterExpressionEClass, source, new String[]
        { "name", "CustomFilterExpression", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getCustomFilterExpression_Type(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "type", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getCustomFilterExpression_Context(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "context", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( dataAccessDesignEClass, source, new String[]
        { "name", "DataAccessDesign", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataAccessDesign_DataSetDesign(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "dataSetDesign", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( dataElementAttributesEClass, source, new String[]
        { "name", "DataElementAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataElementAttributes_Identifier(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "identifier", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataElementAttributes_Name(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataElementAttributes_Position(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "position", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataElementAttributes_NativeDataTypeCode(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "nativeDataTypeCode", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataElementAttributes_Precision(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "precision", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataElementAttributes_Scale(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "scale", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataElementAttributes_Nullability(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "nullability", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataElementAttributes_UiHints(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "uiHints", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( dataElementIdentifierEClass, source, new String[]
        { "name", "DataElementIdentifier", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataElementIdentifier_Name(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataElementIdentifier_Position(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "position", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( dataElementIdentifiersEClass, source, new String[]
        { "name", "DataElementIdentifiers", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataElementIdentifiers_Identifiers(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "identifiers", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( dataElementUIHintsEClass, source, new String[]
        { "name", "DataElementUIHints", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataElementUIHints_DisplayName(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "displayName", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataElementUIHints_Description(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( dataSetDesignEClass, source, new String[]
        { "name", "DataSetDesign", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSetDesign_Name(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSetDesign_OdaExtensionDataSetId(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "odaExtensionDataSetId", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSetDesign_DataSourceDesign(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "dataSourceDesign", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSetDesign_Query(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "query", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSetDesign_DisplayName(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "displayName", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSetDesign_PublicProperties(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "publicProperties", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSetDesign_PrivateProperties(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "privateProperties", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSetDesign_ResultSets(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "resultSets", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSetDesign_PrimaryResultSetName(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "primaryResultSetName", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSetDesign_Parameters(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "parameters", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( dataSetParametersEClass, source, new String[]
        { "name", "DataSetParameters", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSetParameters_ParameterDefinitions(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "parameterDefinitions", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSetParameters_DerivedMetaData(), source,
                new String[]
                { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "derivedMetaData" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( dataSetQueryEClass, source, new String[]
        { "name", "DataSetQuery", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSetQuery_QueryText(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "queryText", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( dataSourceDesignEClass, source, new String[]
        { "name", "DataSourceDesign", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSourceDesign_Name(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSourceDesign_OdaExtensionId(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "odaExtensionId", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSourceDesign_EffectiveOdaExtensionId(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "effectiveOdaExtensionId", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSourceDesign_OdaExtensionDataSourceId(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "odaExtensionDataSourceId", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSourceDesign_DisplayName(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "displayName", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDataSourceDesign_PublicProperties(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "publicProperties", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSourceDesign_PrivateProperties(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "privateProperties", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSourceDesign_LinkedProfileName(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "linkedProfileName", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSourceDesign_LinkedProfileStoreFilePath(),
                source, new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "linkedProfileStoreFilePath", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSourceDesign_HostResourceIdentifiers(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "hostResourceIdentifiers", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDataSourceDesign_ResourceFile(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "resourceFile", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( designerStateEClass, source, new String[]
        { "name", "DesignerState", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDesignerState_Version(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "version", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDesignerState_StateContent(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "stateContent", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( designerStateContentEClass, source, new String[]
        { "name", "DesignerStateContent", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDesignerStateContent_StateContentAsString(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "stateContentAsString", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDesignerStateContent_StateContentAsBlob(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "stateContentAsBlob", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( designSessionRequestEClass, source, new String[]
        { "name", "DesignSessionRequest", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDesignSessionRequest_DataAccessDesign(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "dataAccessDesign", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDesignSessionRequest_Editable(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "editable", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDesignSessionRequest_SessionLocale(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "sessionLocale", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDesignSessionRequest_DesignerState(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "designerState", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( designSessionResponseEClass, source, new String[]
        { "name", "DesignSessionResponse", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDesignSessionResponse_SessionStatus(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "sessionStatus", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDesignSessionResponse_DataAccessDesign(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "dataAccessDesign", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDesignSessionResponse_DesignerState(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "designerState", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( documentRootEClass, source, new String[]
        { "name", "", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDocumentRoot_Mixed(), source, new String[]
        { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
                "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDocumentRoot_XMLNSPrefixMap(), source, new String[]
        { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDocumentRoot_XSISchemaLocation(), source,
                new String[]
                { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDocumentRoot_OdaDesignSession(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "odaDesignSession", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( dynamicFilterExpressionEClass, source, new String[]
        { "name", "DynamicFilterExpression", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDynamicFilterExpression_Context(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "context", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDynamicFilterExpression_DefaultType(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "defaultType", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( dynamicValuesQueryEClass, source, new String[]
        { "name", "DynamicValuesQuery", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDynamicValuesQuery_DataSetDesign(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "dataSetDesign", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDynamicValuesQuery_Enabled(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "enabled", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getDynamicValuesQuery_ValueColumnIdentifier(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "valueColumnIdentifier", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDynamicValuesQuery_ValueColumn(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "valueColumn", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getDynamicValuesQuery_DisplayNameColumn(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "displayNameColumn", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( elementNullabilityEEnum, source, new String[]
        { "name", "ElementNullability" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( elementNullabilityObjectEDataType, source, new String[]
        { "name", "ElementNullability:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "ElementNullability" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( expressionArgumentsEClass, source, new String[]
        { "name", "ExpressionArguments", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getExpressionArguments_ExpressionParameters(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "expressionParameters", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( expressionParameterDefinitionEClass, source,
                new String[]
                { "name", "ExpressionParameterDefinition", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getExpressionParameterDefinition_StaticValues(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "staticValues", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation(
                getExpressionParameterDefinition_DynamicInputParameter(),
                source, new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "dynamicInputParameter", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( expressionParametersEClass, source, new String[]
        { "name", "ExpressionParameters", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getExpressionParameters_ParameterDefinitions(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "parameterDefinitions", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( expressionVariableEClass, source, new String[]
        { "name", "ExpressionVariable", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getExpressionVariable_Type(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "type", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getExpressionVariable_Identifier(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "identifier", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getExpressionVariable_NativeDataTypeCode(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "nativeDataTypeCode", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( expressionVariableTypeEEnum, source, new String[]
        { "name", "ExpressionVariableType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( expressionVariableTypeObjectEDataType, source,
                new String[]
                { "name", "ExpressionVariableType:Object", //$NON-NLS-1$ //$NON-NLS-2$
                        "baseType", "ExpressionVariableType" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( filterExpressionEClass, source, new String[]
        { "name", "FilterExpression", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getFilterExpression_Negatable(), source, new String[]
        { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "negatable" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( filterExpressionTypeEClass, source, new String[]
        { "name", "FilterExpressionType", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getFilterExpressionType_DeclaringExtensionId(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "declaringExtensionId", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getFilterExpressionType_Id(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( horizontalAlignmentEEnum, source, new String[]
        { "name", "HorizontalAlignment" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( horizontalAlignmentObjectEDataType, source, new String[]
        { "name", "HorizontalAlignment:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "HorizontalAlignment" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( inputElementAttributesEClass, source, new String[]
        { "name", "InputElementAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getInputElementAttributes_DefaultScalarValue(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "defaultScalarValue", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputElementAttributes_DefaultValues(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "defaultValues", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputElementAttributes_Editable(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "editable", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputElementAttributes_Optional(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "optional", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputElementAttributes_MasksValue(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "masksValue", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputElementAttributes_StaticValueChoices(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "staticValueChoices", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputElementAttributes_DynamicValueChoices(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "dynamicValueChoices", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputElementAttributes_UiHints(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "uiHints", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( inputElementUIHintsEClass, source, new String[]
        { "name", "InputElementUIHints", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getInputElementUIHints_PromptStyle(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "promptStyle", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputElementUIHints_AutoSuggestThreshold(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "autoSuggestThreshold", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( inputParameterAttributesEClass, source, new String[]
        { "name", "InputParameterAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getInputParameterAttributes_ElementAttributes(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "elementAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getInputParameterAttributes_UiHints(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "uiHints", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( inputParameterUIHintsEClass, source, new String[]
        { "name", "InputParameterUIHints", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getInputParameterUIHints_GroupPromptDisplayName(),
                source, new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "groupPromptDisplayName", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( inputPromptControlStyleEEnum, source, new String[]
        { "name", "InputPromptControlStyle" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( inputPromptControlStyleObjectEDataType, source,
                new String[]
                { "name", "InputPromptControlStyle:Object", //$NON-NLS-1$ //$NON-NLS-2$
                        "baseType", "InputPromptControlStyle" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( localeEClass, source, new String[]
        { "name", "Locale", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getLocale_Language(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "language", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getLocale_Country(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "country", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getLocale_Variant(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "variant", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( nameValuePairEClass, source, new String[]
        { "name", "NameValuePair", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getNameValuePair_Name(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getNameValuePair_Value(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( notExpressionEClass, source, new String[]
        { "name", "NotExpression", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getNotExpression_NegatingExpression(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "negatingExpression", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( nullOrderingTypeEEnum, source, new String[]
        { "name", "NullOrderingType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( nullOrderingTypeObjectEDataType, source, new String[]
        { "name", "NullOrderingType:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "NullOrderingType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( odaComplexDataTypeEEnum, source, new String[]
        { "name", "OdaComplexDataType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( odaComplexDataTypeObjectEDataType, source, new String[]
        { "name", "OdaComplexDataType:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "OdaComplexDataType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( odaDesignSessionEClass, source, new String[]
        { "name", "OdaDesignSession", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getOdaDesignSession_Request(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "request", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getOdaDesignSession_Response(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "response", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( odaScalarDataTypeEEnum, source, new String[]
        { "name", "OdaScalarDataType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( odaScalarDataTypeObjectEDataType, source, new String[]
        { "name", "OdaScalarDataType:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "OdaScalarDataType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( orExpressionEClass, source, new String[]
        { "name", "OrExpression", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( outputElementAttributesEClass, source, new String[]
        { "name", "OutputElementAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getOutputElementAttributes_Label(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "label", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getOutputElementAttributes_FormattingHints(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "formattingHints", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getOutputElementAttributes_HelpText(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "helpText", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( parameterDefinitionEClass, source, new String[]
        { "name", "ParameterDefinition", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getParameterDefinition_InOutMode(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "inOutMode", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getParameterDefinition_Attributes(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "attributes", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getParameterDefinition_InputAttributes(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "inputAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getParameterDefinition_OutputUsageHints(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "outputUsageHints", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getParameterDefinition_Fields(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "fields", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( parameterFieldDefinitionEClass, source, new String[]
        { "name", "ParameterFieldDefinition", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getParameterFieldDefinition_Attributes(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "attributes", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getParameterFieldDefinition_InputAttributes(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "inputAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getParameterFieldDefinition_OutputUsageHints(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "outputUsageHints", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( parameterFieldsEClass, source, new String[]
        { "name", "ParameterFields", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getParameterFields_FieldCollection(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "fieldCollection", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( parameterModeEEnum, source, new String[]
        { "name", "ParameterMode" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( parameterModeObjectEDataType, source, new String[]
        { "name", "ParameterMode:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "ParameterMode" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( propertiesEClass, source, new String[]
        { "name", "Properties", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getProperties_Properties(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "properties", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( propertyEClass, source, new String[]
        { "name", "Property", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getProperty_NameValue(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "nameValue", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getProperty_DesignAttributes(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "designAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( propertyAttributesEClass, source, new String[]
        { "name", "PropertyAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getPropertyAttributes_DisplayName(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "displayName", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getPropertyAttributes_ElementAttributes(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "elementAttributes", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getPropertyAttributes_DerivedMetaData(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "derivedMetaData", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( resourceIdentifiersEClass, source, new String[]
        { "name", "ResourceIdentifiers", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResourceIdentifiers_ApplResourceBaseURIString(),
                source, new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "applResourceBaseURIString", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getResourceIdentifiers_DesignResourceBaseURIString(),
                source, new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "designResourceBaseURIString", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( resultSetColumnsEClass, source, new String[]
        { "name", "ResultSetColumns", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResultSetColumns_ResultColumnDefinitions(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "resultColumnDefinitions", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( resultSetCriteriaEClass, source, new String[]
        { "name", "ResultSetCriteria", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResultSetCriteria_FilterSpecification(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "filterSpecification", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getResultSetCriteria_RowOrdering(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "rowOrdering", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( resultSetDefinitionEClass, source, new String[]
        { "name", "ResultSetDefinition", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResultSetDefinition_Name(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResultSetDefinition_ResultSetColumns(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "resultSetColumns", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getResultSetDefinition_Criteria(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "criteria", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( resultSetsEClass, source, new String[]
        { "name", "ResultSets", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResultSets_ResultSetDefinitions(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "resultSetDefinitions", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getResultSets_DerivedMetaData(), source, new String[]
        { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "derivedMetaData" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( resultSubsetEClass, source, new String[]
        { "name", "ResultSubset", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResultSubset_DataSetDesign(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "dataSetDesign", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResultSubset_ResultSetName(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "resultSetName", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getResultSubset_ColumnIdentifiers(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "columnIdentifiers", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( scalarValueChoicesEClass, source, new String[]
        { "name", "ScalarValueChoices", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getScalarValueChoices_ScalarValues(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "scalarValues", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( scalarValueDefinitionEClass, source, new String[]
        { "name", "ScalarValueDefinition", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getScalarValueDefinition_Value(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getScalarValueDefinition_DisplayName(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "displayName", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( sessionStatusEEnum, source, new String[]
        { "name", "SessionStatus" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( sessionStatusObjectEDataType, source, new String[]
        { "name", "SessionStatus:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "SessionStatus" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( sortDirectionTypeEEnum, source, new String[]
        { "name", "SortDirectionType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( sortDirectionTypeObjectEDataType, source, new String[]
        { "name", "SortDirectionType:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "SortDirectionType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( sortKeyEClass, source, new String[]
        { "name", "SortKey", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getSortKey_ColumnIdentifier(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "columnIdentifier", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getSortKey_ColumnName(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "columnName", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getSortKey_ColumnPosition(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "columnPosition", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getSortKey_SortDirection(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "sortDirection", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getSortKey_NullValueOrdering(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "nullValueOrdering", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getSortKey_Optional(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "optional", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( sortSpecificationEClass, source, new String[]
        { "name", "SortSpecification", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getSortSpecification_SortKeys(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "sortKeys", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( staticValuesEClass, source, new String[]
        { "name", "StaticValues", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getStaticValues_Values(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "values", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( textFormatTypeEEnum, source, new String[]
        { "name", "TextFormatType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( textFormatTypeObjectEDataType, source, new String[]
        { "name", "TextFormatType:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "TextFormatType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( textWrapTypeEEnum, source, new String[]
        { "name", "TextWrapType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( textWrapTypeObjectEDataType, source, new String[]
        { "name", "TextWrapType:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "TextWrapType" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( valueFormatHintsEClass, source, new String[]
        { "name", "ValueFormatHints", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getValueFormatHints_DisplaySize(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "displaySize", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
        addAnnotation( getValueFormatHints_DisplayFormat(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "displayFormat", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getValueFormatHints_TextFormatType(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "textFormatType", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getValueFormatHints_HorizontalAlignment(), source,
                new String[]
                { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "horizontalAlignment", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                } );
        addAnnotation( getValueFormatHints_TextWrapType(), source, new String[]
        { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                "name", "textWrapType", //$NON-NLS-1$ //$NON-NLS-2$
                "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        } );
    }

} //DesignPackageImpl
