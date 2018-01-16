/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLXMLQueryModelPackageImpl.java,v 1.3 2008/07/07 19:55:14 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;

import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl;
import org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl;
import org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;

import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl;
import org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl;
import org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl;
import org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicate;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateDocument;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateValid;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionOrdinality;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLXMLQueryModelPackageImpl extends EPackageImpl implements SQLXMLQueryModelPackage {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionConcatEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlNamespaceDeclarationPrefixEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlNamespaceDeclarationDefaultEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlAttributeDeclarationItemEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionElementEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlNamespaceDeclarationItemEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionElementContentItemEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionForestEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionCommentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionDocumentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionParseEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionPIEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionQueryEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionTextEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionValidateEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueExpressionCastEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlPredicateEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlPredicateContentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlPredicateDocumentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlPredicateExistsEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlPredicateValidEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlQueryExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlQueryArgumentListEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlQueryArgumentItemEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlSerializeFunctionEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlSerializeFunctionTargetEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlAggregateFunctionEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionConcatContentItemEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionCommentContentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionDocumentContentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlAggregateSortSpecificationEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionForestContentItemEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionParseContentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionPIContentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlTableFunctionEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionTextContentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionValidateContentEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlTableColumnDefinitionItemEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlTableColumnDefinitionRegularEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlTableColumnDefinitionOrdinalityEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionValidateAccordingToEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionValidateAccordingToURIEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionValidateAccordingToIdentifierEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionValidateElementNameEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionValidateElementNamespaceEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlNamespacesDeclarationEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlAttributesDeclarationEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionElementContentListEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionQueryReturningEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlValueFunctionValidateElementEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlTableColumnDefinitionDefaultEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlSerializeFunctionEncodingEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum xmlPassingTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum xmlContentTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum xmlDeclarationTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum xmlReturningTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum xmlNullHandlingTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum xmlWhitespaceHandlingTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum xmlEmptyHandlingTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum xmlContentType2EEnum = null;

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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private SQLXMLQueryModelPackageImpl() {
        super(eNS_URI, SQLXMLQueryModelFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link SQLXMLQueryModelPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SQLXMLQueryModelPackage init() {
        if (isInited) return (SQLXMLQueryModelPackage)EPackage.Registry.INSTANCE.getEPackage(SQLXMLQueryModelPackage.eNS_URI);

        // Obtain or create and register package
        SQLXMLQueryModelPackageImpl theSQLXMLQueryModelPackage = (SQLXMLQueryModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SQLXMLQueryModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SQLXMLQueryModelPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        SQLQueryModelPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theSQLXMLQueryModelPackage.createPackageContents();

        // Initialize created meta-data
        theSQLXMLQueryModelPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSQLXMLQueryModelPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SQLXMLQueryModelPackage.eNS_URI, theSQLXMLQueryModelPackage);
        return theSQLXMLQueryModelPackage;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionConcat() {
        return xmlValueFunctionConcatEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionConcat_ReturningOption() {
        return (EAttribute)xmlValueFunctionConcatEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionConcat_ConcatContentList() {
        return (EReference)xmlValueFunctionConcatEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunction() {
        return xmlValueFunctionEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLNamespaceDeclarationPrefix() {
        return xmlNamespaceDeclarationPrefixEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLNamespaceDeclarationPrefix_Prefix() {
        return (EAttribute)xmlNamespaceDeclarationPrefixEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLNamespaceDeclarationDefault() {
        return xmlNamespaceDeclarationDefaultEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLNamespaceDeclarationDefault_NoDefault() {
        return (EAttribute)xmlNamespaceDeclarationDefaultEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLAttributeDeclarationItem() {
        return xmlAttributeDeclarationItemEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLAttributeDeclarationItem_ValueExpr() {
        return (EReference)xmlAttributeDeclarationItemEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLAttributeDeclarationItem_AttributesDecl() {
        return (EReference)xmlAttributeDeclarationItemEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionElement() {
        return xmlValueFunctionElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionElement_ElementName() {
        return (EAttribute)xmlValueFunctionElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionElement_ReturningOption() {
        return (EAttribute)xmlValueFunctionElementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionElement_NamespacesDecl() {
        return (EReference)xmlValueFunctionElementEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionElement_AttributesDecl() {
        return (EReference)xmlValueFunctionElementEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionElement_ElementContentList() {
        return (EReference)xmlValueFunctionElementEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLNamespaceDeclarationItem() {
        return xmlNamespaceDeclarationItemEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLNamespaceDeclarationItem_Uri() {
        return (EAttribute)xmlNamespaceDeclarationItemEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLNamespaceDeclarationItem_NamespacesDecl() {
        return (EReference)xmlNamespaceDeclarationItemEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionElementContentItem() {
        return xmlValueFunctionElementContentItemEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionElementContentItem_ValueExpr() {
        return (EReference)xmlValueFunctionElementContentItemEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionElementContentItem_ElementContentList() {
        return (EReference)xmlValueFunctionElementContentItemEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionForest() {
        return xmlValueFunctionForestEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionForest_NullHandlingOption() {
        return (EAttribute)xmlValueFunctionForestEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionForest_ReturningOption() {
        return (EAttribute)xmlValueFunctionForestEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionForest_ForestContentList() {
        return (EReference)xmlValueFunctionForestEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionForest_NamespacesDecl() {
        return (EReference)xmlValueFunctionForestEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionComment() {
        return xmlValueFunctionCommentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionComment_ReturningOption() {
        return (EAttribute)xmlValueFunctionCommentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionComment_CommentContent() {
        return (EReference)xmlValueFunctionCommentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionDocument() {
        return xmlValueFunctionDocumentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionDocument_ReturningOption() {
        return (EAttribute)xmlValueFunctionDocumentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionDocument_DocumentContent() {
        return (EReference)xmlValueFunctionDocumentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionParse() {
        return xmlValueFunctionParseEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionParse_ContentOption() {
        return (EAttribute)xmlValueFunctionParseEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionParse_WhitespaceHandlingOption() {
        return (EAttribute)xmlValueFunctionParseEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionParse_ParseContent() {
        return (EReference)xmlValueFunctionParseEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionPI() {
        return xmlValueFunctionPIEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionPI_TargetName() {
        return (EAttribute)xmlValueFunctionPIEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionPI_ReturningOption() {
        return (EAttribute)xmlValueFunctionPIEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionPI_PIContent() {
        return (EReference)xmlValueFunctionPIEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionQuery() {
        return xmlValueFunctionQueryEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionQuery_EmptyHandlingOption() {
        return (EAttribute)xmlValueFunctionQueryEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionQuery_XqueryExpr() {
        return (EReference)xmlValueFunctionQueryEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionQuery_XqueryArgList() {
        return (EReference)xmlValueFunctionQueryEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionQuery_QueryReturning() {
        return (EReference)xmlValueFunctionQueryEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionText() {
        return xmlValueFunctionTextEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionText_ReturningOption() {
        return (EAttribute)xmlValueFunctionTextEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionText_TextContent() {
        return (EReference)xmlValueFunctionTextEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionValidate() {
        return xmlValueFunctionValidateEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionValidate_ContentOption() {
        return (EAttribute)xmlValueFunctionValidateEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidate_ValidateContent() {
        return (EReference)xmlValueFunctionValidateEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidate_ValidateAccordingTo() {
        return (EReference)xmlValueFunctionValidateEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueExpressionCast() {
        return xmlValueExpressionCastEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueExpressionCast_PassingMechanism() {
        return (EAttribute)xmlValueExpressionCastEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLPredicate() {
        return xmlPredicateEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLPredicateContent() {
        return xmlPredicateContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLPredicateDocument() {
        return xmlPredicateDocumentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLPredicateExists() {
        return xmlPredicateExistsEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLPredicateExists_XqueryExpr() {
        return (EReference)xmlPredicateExistsEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLPredicateExists_XqueryArgList() {
        return (EReference)xmlPredicateExistsEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLPredicateValid() {
        return xmlPredicateValidEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLQueryExpression() {
        return xmlQueryExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLQueryExpression_XqueryExprContent() {
        return (EAttribute)xmlQueryExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLQueryExpression_PredicateExists() {
        return (EReference)xmlQueryExpressionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLQueryExpression_ValueFunctionQuery() {
        return (EReference)xmlQueryExpressionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLQueryArgumentList() {
        return xmlQueryArgumentListEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLQueryArgumentList_PassingMechanism() {
        return (EAttribute)xmlQueryArgumentListEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLQueryArgumentList_PredicateExists() {
        return (EReference)xmlQueryArgumentListEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLQueryArgumentList_XqueryArgListChildren() {
        return (EReference)xmlQueryArgumentListEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLQueryArgumentList_ValueFunctionQuery() {
        return (EReference)xmlQueryArgumentListEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLQueryArgumentList_TableFunction() {
        return (EReference)xmlQueryArgumentListEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLQueryArgumentItem() {
        return xmlQueryArgumentItemEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLQueryArgumentItem_PassingMechanism() {
        return (EAttribute)xmlQueryArgumentItemEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLQueryArgumentItem_XqueryArgList() {
        return (EReference)xmlQueryArgumentItemEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLQueryArgumentItem_ValueExpr() {
        return (EReference)xmlQueryArgumentItemEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLSerializeFunction() {
        return xmlSerializeFunctionEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLSerializeFunction_ContentOption() {
        return (EAttribute)xmlSerializeFunctionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLSerializeFunction_SerializeVersion() {
        return (EAttribute)xmlSerializeFunctionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLSerializeFunction_DeclarationOption() {
        return (EAttribute)xmlSerializeFunctionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLSerializeFunction_SerializeTarget() {
        return (EReference)xmlSerializeFunctionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLSerializeFunction_SerializeEncoding() {
        return (EReference)xmlSerializeFunctionEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLSerializeFunctionTarget() {
        return xmlSerializeFunctionTargetEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLSerializeFunctionTarget_SerializeFunction() {
        return (EReference)xmlSerializeFunctionTargetEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLSerializeFunctionTarget_ValueExpr() {
        return (EReference)xmlSerializeFunctionTargetEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLAggregateFunction() {
        return xmlAggregateFunctionEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLAggregateFunction_ReturningOption() {
        return (EAttribute)xmlAggregateFunctionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLAggregateFunction_SortSpecList() {
        return (EReference)xmlAggregateFunctionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionConcatContentItem() {
        return xmlValueFunctionConcatContentItemEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionConcatContentItem_ValueFunctionConcat() {
        return (EReference)xmlValueFunctionConcatContentItemEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionConcatContentItem_ValueExpr() {
        return (EReference)xmlValueFunctionConcatContentItemEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionCommentContent() {
        return xmlValueFunctionCommentContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionCommentContent_ValueFunctionComment() {
        return (EReference)xmlValueFunctionCommentContentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionCommentContent_ValueExpr() {
        return (EReference)xmlValueFunctionCommentContentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionDocumentContent() {
        return xmlValueFunctionDocumentContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionDocumentContent_ValueFunctionDocument() {
        return (EReference)xmlValueFunctionDocumentContentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionDocumentContent_ValueExpr() {
        return (EReference)xmlValueFunctionDocumentContentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLAggregateSortSpecification() {
        return xmlAggregateSortSpecificationEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLAggregateSortSpecification_AggregateFunction() {
        return (EReference)xmlAggregateSortSpecificationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLAggregateSortSpecification_OrderBySpec() {
        return (EReference)xmlAggregateSortSpecificationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionForestContentItem() {
        return xmlValueFunctionForestContentItemEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionForestContentItem_ValueFunctionForest() {
        return (EReference)xmlValueFunctionForestContentItemEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionForestContentItem_ValueExpr() {
        return (EReference)xmlValueFunctionForestContentItemEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionParseContent() {
        return xmlValueFunctionParseContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionParseContent_ValueFunctionParse() {
        return (EReference)xmlValueFunctionParseContentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionParseContent_ValueExpr() {
        return (EReference)xmlValueFunctionParseContentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionPIContent() {
        return xmlValueFunctionPIContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionPIContent_ValueFunctionPI() {
        return (EReference)xmlValueFunctionPIContentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionPIContent_ValueExpr() {
        return (EReference)xmlValueFunctionPIContentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLTableFunction() {
        return xmlTableFunctionEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLTableFunction_TableRowPattern() {
        return (EAttribute)xmlTableFunctionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLTableFunction_XqueryArgList() {
        return (EReference)xmlTableFunctionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLTableFunction_ColumnDefList() {
        return (EReference)xmlTableFunctionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLTableFunction_NamespacesDecl() {
        return (EReference)xmlTableFunctionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionTextContent() {
        return xmlValueFunctionTextContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionTextContent_ValueFunctionText() {
        return (EReference)xmlValueFunctionTextContentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionTextContent_ValueExpr() {
        return (EReference)xmlValueFunctionTextContentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionValidateContent() {
        return xmlValueFunctionValidateContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateContent_ValueFunctionValidate() {
        return (EReference)xmlValueFunctionValidateContentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateContent_ValueExpr() {
        return (EReference)xmlValueFunctionValidateContentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLTableColumnDefinitionItem() {
        return xmlTableColumnDefinitionItemEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLTableColumnDefinitionItem_TableFunction() {
        return (EReference)xmlTableColumnDefinitionItemEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLTableColumnDefinitionRegular() {
        return xmlTableColumnDefinitionRegularEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLTableColumnDefinitionRegular_DataType() {
        return (EReference)xmlTableColumnDefinitionRegularEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLTableColumnDefinitionRegular_PassingOption() {
        return (EAttribute)xmlTableColumnDefinitionRegularEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLTableColumnDefinitionRegular_TableColumnPattern() {
        return (EAttribute)xmlTableColumnDefinitionRegularEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLTableColumnDefinitionRegular_ColumnDefinitionDefault() {
        return (EReference)xmlTableColumnDefinitionRegularEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLTableColumnDefinitionOrdinality() {
        return xmlTableColumnDefinitionOrdinalityEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionValidateAccordingTo() {
        return xmlValueFunctionValidateAccordingToEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateAccordingTo_ValueFunctionValidate() {
        return (EReference)xmlValueFunctionValidateAccordingToEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateAccordingTo_ValidateElement() {
        return (EReference)xmlValueFunctionValidateAccordingToEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionValidateAccordingToURI() {
        return xmlValueFunctionValidateAccordingToURIEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionValidateAccordingToURI_NoNamespace() {
        return (EAttribute)xmlValueFunctionValidateAccordingToURIEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionValidateAccordingToURI_TargetNamespaceURI() {
        return (EAttribute)xmlValueFunctionValidateAccordingToURIEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionValidateAccordingToURI_SchemaLocationURI() {
        return (EAttribute)xmlValueFunctionValidateAccordingToURIEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionValidateAccordingToIdentifier() {
        return xmlValueFunctionValidateAccordingToIdentifierEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionValidateAccordingToIdentifier_SchemaName() {
        return (EAttribute)xmlValueFunctionValidateAccordingToIdentifierEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionValidateAccordingToIdentifier_RegisteredXMLSchemaName() {
        return (EAttribute)xmlValueFunctionValidateAccordingToIdentifierEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionValidateElementName() {
        return xmlValueFunctionValidateElementNameEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateElementName_ValidateElement() {
        return (EReference)xmlValueFunctionValidateElementNameEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionValidateElementNamespace() {
        return xmlValueFunctionValidateElementNamespaceEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionValidateElementNamespace_NoNamespace() {
        return (EAttribute)xmlValueFunctionValidateElementNamespaceEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionValidateElementNamespace_NamespaceURI() {
        return (EAttribute)xmlValueFunctionValidateElementNamespaceEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateElementNamespace_ValidateElement() {
        return (EReference)xmlValueFunctionValidateElementNamespaceEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLNamespacesDeclaration() {
        return xmlNamespacesDeclarationEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLNamespacesDeclaration_NamespaceDecltemList() {
        return (EReference)xmlNamespacesDeclarationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLNamespacesDeclaration_ValueFunctionElement() {
        return (EReference)xmlNamespacesDeclarationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLNamespacesDeclaration_ValueFunctionForest() {
        return (EReference)xmlNamespacesDeclarationEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLNamespacesDeclaration_TableFunction() {
        return (EReference)xmlNamespacesDeclarationEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLAttributesDeclaration() {
        return xmlAttributesDeclarationEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLAttributesDeclaration_ValueFunctionElement() {
        return (EReference)xmlAttributesDeclarationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLAttributesDeclaration_AttributeDeclItem() {
        return (EReference)xmlAttributesDeclarationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionElementContentList() {
        return xmlValueFunctionElementContentListEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionElementContentList_NullHandlingOption() {
        return (EAttribute)xmlValueFunctionElementContentListEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionElementContentList_ValueFunctionElement() {
        return (EReference)xmlValueFunctionElementContentListEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionElementContentList_ElementContentListChildren() {
        return (EReference)xmlValueFunctionElementContentListEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionQueryReturning() {
        return xmlValueFunctionQueryReturningEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionQueryReturning_ReturningOption() {
        return (EAttribute)xmlValueFunctionQueryReturningEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLValueFunctionQueryReturning_PassingOption() {
        return (EAttribute)xmlValueFunctionQueryReturningEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionQueryReturning_ValueFunctionQuery() {
        return (EReference)xmlValueFunctionQueryReturningEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLValueFunctionValidateElement() {
        return xmlValueFunctionValidateElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateElement_ValidateElementNamespace() {
        return (EReference)xmlValueFunctionValidateElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateElement_ValidateElementName() {
        return (EReference)xmlValueFunctionValidateElementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLValueFunctionValidateElement_ValidateAccordingTo() {
        return (EReference)xmlValueFunctionValidateElementEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLTableColumnDefinitionDefault() {
        return xmlTableColumnDefinitionDefaultEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLTableColumnDefinitionDefault_ValueExpr() {
        return (EReference)xmlTableColumnDefinitionDefaultEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getXMLTableColumnDefinitionDefault_ColumnDefinitionRegular() {
        return (EReference)xmlTableColumnDefinitionDefaultEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getXMLSerializeFunctionEncoding() {
        return xmlSerializeFunctionEncodingEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXMLSerializeFunctionEncoding_EncodingName() {
        return (EAttribute)xmlSerializeFunctionEncodingEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getXMLPassingType() {
        return xmlPassingTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getXMLContentType() {
        return xmlContentTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getXMLDeclarationType() {
        return xmlDeclarationTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getXMLReturningType() {
        return xmlReturningTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getXMLNullHandlingType() {
        return xmlNullHandlingTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getXMLWhitespaceHandlingType() {
        return xmlWhitespaceHandlingTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getXMLEmptyHandlingType() {
        return xmlEmptyHandlingTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getXMLContentType2() {
        return xmlContentType2EEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SQLXMLQueryModelFactory getSQLXMLQueryModelFactory() {
        return (SQLXMLQueryModelFactory)getEFactoryInstance();
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
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        xmlValueFunctionConcatEClass = createEClass(XML_VALUE_FUNCTION_CONCAT);
        createEAttribute(xmlValueFunctionConcatEClass, XML_VALUE_FUNCTION_CONCAT__RETURNING_OPTION);
        createEReference(xmlValueFunctionConcatEClass, XML_VALUE_FUNCTION_CONCAT__CONCAT_CONTENT_LIST);

        xmlValueFunctionEClass = createEClass(XML_VALUE_FUNCTION);

        xmlNamespaceDeclarationPrefixEClass = createEClass(XML_NAMESPACE_DECLARATION_PREFIX);
        createEAttribute(xmlNamespaceDeclarationPrefixEClass, XML_NAMESPACE_DECLARATION_PREFIX__PREFIX);

        xmlNamespaceDeclarationDefaultEClass = createEClass(XML_NAMESPACE_DECLARATION_DEFAULT);
        createEAttribute(xmlNamespaceDeclarationDefaultEClass, XML_NAMESPACE_DECLARATION_DEFAULT__NO_DEFAULT);

        xmlAttributeDeclarationItemEClass = createEClass(XML_ATTRIBUTE_DECLARATION_ITEM);
        createEReference(xmlAttributeDeclarationItemEClass, XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR);
        createEReference(xmlAttributeDeclarationItemEClass, XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL);

        xmlValueFunctionElementEClass = createEClass(XML_VALUE_FUNCTION_ELEMENT);
        createEAttribute(xmlValueFunctionElementEClass, XML_VALUE_FUNCTION_ELEMENT__ELEMENT_NAME);
        createEAttribute(xmlValueFunctionElementEClass, XML_VALUE_FUNCTION_ELEMENT__RETURNING_OPTION);
        createEReference(xmlValueFunctionElementEClass, XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL);
        createEReference(xmlValueFunctionElementEClass, XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL);
        createEReference(xmlValueFunctionElementEClass, XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST);

        xmlNamespaceDeclarationItemEClass = createEClass(XML_NAMESPACE_DECLARATION_ITEM);
        createEAttribute(xmlNamespaceDeclarationItemEClass, XML_NAMESPACE_DECLARATION_ITEM__URI);
        createEReference(xmlNamespaceDeclarationItemEClass, XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL);

        xmlValueFunctionElementContentItemEClass = createEClass(XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM);
        createEReference(xmlValueFunctionElementContentItemEClass, XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR);
        createEReference(xmlValueFunctionElementContentItemEClass, XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__ELEMENT_CONTENT_LIST);

        xmlValueFunctionForestEClass = createEClass(XML_VALUE_FUNCTION_FOREST);
        createEAttribute(xmlValueFunctionForestEClass, XML_VALUE_FUNCTION_FOREST__NULL_HANDLING_OPTION);
        createEAttribute(xmlValueFunctionForestEClass, XML_VALUE_FUNCTION_FOREST__RETURNING_OPTION);
        createEReference(xmlValueFunctionForestEClass, XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST);
        createEReference(xmlValueFunctionForestEClass, XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL);

        xmlValueFunctionCommentEClass = createEClass(XML_VALUE_FUNCTION_COMMENT);
        createEAttribute(xmlValueFunctionCommentEClass, XML_VALUE_FUNCTION_COMMENT__RETURNING_OPTION);
        createEReference(xmlValueFunctionCommentEClass, XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT);

        xmlValueFunctionDocumentEClass = createEClass(XML_VALUE_FUNCTION_DOCUMENT);
        createEAttribute(xmlValueFunctionDocumentEClass, XML_VALUE_FUNCTION_DOCUMENT__RETURNING_OPTION);
        createEReference(xmlValueFunctionDocumentEClass, XML_VALUE_FUNCTION_DOCUMENT__DOCUMENT_CONTENT);

        xmlValueFunctionParseEClass = createEClass(XML_VALUE_FUNCTION_PARSE);
        createEAttribute(xmlValueFunctionParseEClass, XML_VALUE_FUNCTION_PARSE__CONTENT_OPTION);
        createEAttribute(xmlValueFunctionParseEClass, XML_VALUE_FUNCTION_PARSE__WHITESPACE_HANDLING_OPTION);
        createEReference(xmlValueFunctionParseEClass, XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT);

        xmlValueFunctionPIEClass = createEClass(XML_VALUE_FUNCTION_PI);
        createEAttribute(xmlValueFunctionPIEClass, XML_VALUE_FUNCTION_PI__TARGET_NAME);
        createEAttribute(xmlValueFunctionPIEClass, XML_VALUE_FUNCTION_PI__RETURNING_OPTION);
        createEReference(xmlValueFunctionPIEClass, XML_VALUE_FUNCTION_PI__PI_CONTENT);

        xmlValueFunctionQueryEClass = createEClass(XML_VALUE_FUNCTION_QUERY);
        createEAttribute(xmlValueFunctionQueryEClass, XML_VALUE_FUNCTION_QUERY__EMPTY_HANDLING_OPTION);
        createEReference(xmlValueFunctionQueryEClass, XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR);
        createEReference(xmlValueFunctionQueryEClass, XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST);
        createEReference(xmlValueFunctionQueryEClass, XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING);

        xmlValueFunctionTextEClass = createEClass(XML_VALUE_FUNCTION_TEXT);
        createEAttribute(xmlValueFunctionTextEClass, XML_VALUE_FUNCTION_TEXT__RETURNING_OPTION);
        createEReference(xmlValueFunctionTextEClass, XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT);

        xmlValueFunctionValidateEClass = createEClass(XML_VALUE_FUNCTION_VALIDATE);
        createEAttribute(xmlValueFunctionValidateEClass, XML_VALUE_FUNCTION_VALIDATE__CONTENT_OPTION);
        createEReference(xmlValueFunctionValidateEClass, XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT);
        createEReference(xmlValueFunctionValidateEClass, XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO);

        xmlValueExpressionCastEClass = createEClass(XML_VALUE_EXPRESSION_CAST);
        createEAttribute(xmlValueExpressionCastEClass, XML_VALUE_EXPRESSION_CAST__PASSING_MECHANISM);

        xmlPredicateEClass = createEClass(XML_PREDICATE);

        xmlPredicateContentEClass = createEClass(XML_PREDICATE_CONTENT);

        xmlPredicateDocumentEClass = createEClass(XML_PREDICATE_DOCUMENT);

        xmlPredicateExistsEClass = createEClass(XML_PREDICATE_EXISTS);
        createEReference(xmlPredicateExistsEClass, XML_PREDICATE_EXISTS__XQUERY_EXPR);
        createEReference(xmlPredicateExistsEClass, XML_PREDICATE_EXISTS__XQUERY_ARG_LIST);

        xmlPredicateValidEClass = createEClass(XML_PREDICATE_VALID);

        xmlQueryExpressionEClass = createEClass(XML_QUERY_EXPRESSION);
        createEAttribute(xmlQueryExpressionEClass, XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT);
        createEReference(xmlQueryExpressionEClass, XML_QUERY_EXPRESSION__PREDICATE_EXISTS);
        createEReference(xmlQueryExpressionEClass, XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY);

        xmlQueryArgumentListEClass = createEClass(XML_QUERY_ARGUMENT_LIST);
        createEAttribute(xmlQueryArgumentListEClass, XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM);
        createEReference(xmlQueryArgumentListEClass, XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS);
        createEReference(xmlQueryArgumentListEClass, XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN);
        createEReference(xmlQueryArgumentListEClass, XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY);
        createEReference(xmlQueryArgumentListEClass, XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION);

        xmlQueryArgumentItemEClass = createEClass(XML_QUERY_ARGUMENT_ITEM);
        createEAttribute(xmlQueryArgumentItemEClass, XML_QUERY_ARGUMENT_ITEM__PASSING_MECHANISM);
        createEReference(xmlQueryArgumentItemEClass, XML_QUERY_ARGUMENT_ITEM__XQUERY_ARG_LIST);
        createEReference(xmlQueryArgumentItemEClass, XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR);

        xmlSerializeFunctionEClass = createEClass(XML_SERIALIZE_FUNCTION);
        createEAttribute(xmlSerializeFunctionEClass, XML_SERIALIZE_FUNCTION__CONTENT_OPTION);
        createEAttribute(xmlSerializeFunctionEClass, XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION);
        createEAttribute(xmlSerializeFunctionEClass, XML_SERIALIZE_FUNCTION__DECLARATION_OPTION);
        createEReference(xmlSerializeFunctionEClass, XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET);
        createEReference(xmlSerializeFunctionEClass, XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING);

        xmlSerializeFunctionTargetEClass = createEClass(XML_SERIALIZE_FUNCTION_TARGET);
        createEReference(xmlSerializeFunctionTargetEClass, XML_SERIALIZE_FUNCTION_TARGET__SERIALIZE_FUNCTION);
        createEReference(xmlSerializeFunctionTargetEClass, XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR);

        xmlAggregateFunctionEClass = createEClass(XML_AGGREGATE_FUNCTION);
        createEAttribute(xmlAggregateFunctionEClass, XML_AGGREGATE_FUNCTION__RETURNING_OPTION);
        createEReference(xmlAggregateFunctionEClass, XML_AGGREGATE_FUNCTION__SORT_SPEC_LIST);

        xmlValueFunctionConcatContentItemEClass = createEClass(XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM);
        createEReference(xmlValueFunctionConcatContentItemEClass, XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_FUNCTION_CONCAT);
        createEReference(xmlValueFunctionConcatContentItemEClass, XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR);

        xmlValueFunctionCommentContentEClass = createEClass(XML_VALUE_FUNCTION_COMMENT_CONTENT);
        createEReference(xmlValueFunctionCommentContentEClass, XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_FUNCTION_COMMENT);
        createEReference(xmlValueFunctionCommentContentEClass, XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR);

        xmlValueFunctionDocumentContentEClass = createEClass(XML_VALUE_FUNCTION_DOCUMENT_CONTENT);
        createEReference(xmlValueFunctionDocumentContentEClass, XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_FUNCTION_DOCUMENT);
        createEReference(xmlValueFunctionDocumentContentEClass, XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR);

        xmlAggregateSortSpecificationEClass = createEClass(XML_AGGREGATE_SORT_SPECIFICATION);
        createEReference(xmlAggregateSortSpecificationEClass, XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION);
        createEReference(xmlAggregateSortSpecificationEClass, XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC);

        xmlValueFunctionForestContentItemEClass = createEClass(XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM);
        createEReference(xmlValueFunctionForestContentItemEClass, XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_FUNCTION_FOREST);
        createEReference(xmlValueFunctionForestContentItemEClass, XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR);

        xmlValueFunctionParseContentEClass = createEClass(XML_VALUE_FUNCTION_PARSE_CONTENT);
        createEReference(xmlValueFunctionParseContentEClass, XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_FUNCTION_PARSE);
        createEReference(xmlValueFunctionParseContentEClass, XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR);

        xmlValueFunctionPIContentEClass = createEClass(XML_VALUE_FUNCTION_PI_CONTENT);
        createEReference(xmlValueFunctionPIContentEClass, XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI);
        createEReference(xmlValueFunctionPIContentEClass, XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR);

        xmlTableFunctionEClass = createEClass(XML_TABLE_FUNCTION);
        createEAttribute(xmlTableFunctionEClass, XML_TABLE_FUNCTION__TABLE_ROW_PATTERN);
        createEReference(xmlTableFunctionEClass, XML_TABLE_FUNCTION__XQUERY_ARG_LIST);
        createEReference(xmlTableFunctionEClass, XML_TABLE_FUNCTION__COLUMN_DEF_LIST);
        createEReference(xmlTableFunctionEClass, XML_TABLE_FUNCTION__NAMESPACES_DECL);

        xmlValueFunctionTextContentEClass = createEClass(XML_VALUE_FUNCTION_TEXT_CONTENT);
        createEReference(xmlValueFunctionTextContentEClass, XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_FUNCTION_TEXT);
        createEReference(xmlValueFunctionTextContentEClass, XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR);

        xmlValueFunctionValidateContentEClass = createEClass(XML_VALUE_FUNCTION_VALIDATE_CONTENT);
        createEReference(xmlValueFunctionValidateContentEClass, XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_FUNCTION_VALIDATE);
        createEReference(xmlValueFunctionValidateContentEClass, XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR);

        xmlTableColumnDefinitionItemEClass = createEClass(XML_TABLE_COLUMN_DEFINITION_ITEM);
        createEReference(xmlTableColumnDefinitionItemEClass, XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION);

        xmlTableColumnDefinitionRegularEClass = createEClass(XML_TABLE_COLUMN_DEFINITION_REGULAR);
        createEReference(xmlTableColumnDefinitionRegularEClass, XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE);
        createEAttribute(xmlTableColumnDefinitionRegularEClass, XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION);
        createEAttribute(xmlTableColumnDefinitionRegularEClass, XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN);
        createEReference(xmlTableColumnDefinitionRegularEClass, XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT);

        xmlTableColumnDefinitionOrdinalityEClass = createEClass(XML_TABLE_COLUMN_DEFINITION_ORDINALITY);

        xmlValueFunctionValidateAccordingToEClass = createEClass(XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO);
        createEReference(xmlValueFunctionValidateAccordingToEClass, XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE);
        createEReference(xmlValueFunctionValidateAccordingToEClass, XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT);

        xmlValueFunctionValidateAccordingToURIEClass = createEClass(XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI);
        createEAttribute(xmlValueFunctionValidateAccordingToURIEClass, XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE);
        createEAttribute(xmlValueFunctionValidateAccordingToURIEClass, XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI);
        createEAttribute(xmlValueFunctionValidateAccordingToURIEClass, XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI);

        xmlValueFunctionValidateAccordingToIdentifierEClass = createEClass(XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER);
        createEAttribute(xmlValueFunctionValidateAccordingToIdentifierEClass, XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME);
        createEAttribute(xmlValueFunctionValidateAccordingToIdentifierEClass, XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME);

        xmlValueFunctionValidateElementNameEClass = createEClass(XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME);
        createEReference(xmlValueFunctionValidateElementNameEClass, XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT);

        xmlValueFunctionValidateElementNamespaceEClass = createEClass(XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE);
        createEAttribute(xmlValueFunctionValidateElementNamespaceEClass, XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE);
        createEAttribute(xmlValueFunctionValidateElementNamespaceEClass, XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI);
        createEReference(xmlValueFunctionValidateElementNamespaceEClass, XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT);

        xmlNamespacesDeclarationEClass = createEClass(XML_NAMESPACES_DECLARATION);
        createEReference(xmlNamespacesDeclarationEClass, XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST);
        createEReference(xmlNamespacesDeclarationEClass, XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT);
        createEReference(xmlNamespacesDeclarationEClass, XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST);
        createEReference(xmlNamespacesDeclarationEClass, XML_NAMESPACES_DECLARATION__TABLE_FUNCTION);

        xmlAttributesDeclarationEClass = createEClass(XML_ATTRIBUTES_DECLARATION);
        createEReference(xmlAttributesDeclarationEClass, XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT);
        createEReference(xmlAttributesDeclarationEClass, XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM);

        xmlValueFunctionElementContentListEClass = createEClass(XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST);
        createEAttribute(xmlValueFunctionElementContentListEClass, XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION);
        createEReference(xmlValueFunctionElementContentListEClass, XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT);
        createEReference(xmlValueFunctionElementContentListEClass, XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN);

        xmlValueFunctionQueryReturningEClass = createEClass(XML_VALUE_FUNCTION_QUERY_RETURNING);
        createEAttribute(xmlValueFunctionQueryReturningEClass, XML_VALUE_FUNCTION_QUERY_RETURNING__RETURNING_OPTION);
        createEAttribute(xmlValueFunctionQueryReturningEClass, XML_VALUE_FUNCTION_QUERY_RETURNING__PASSING_OPTION);
        createEReference(xmlValueFunctionQueryReturningEClass, XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY);

        xmlValueFunctionValidateElementEClass = createEClass(XML_VALUE_FUNCTION_VALIDATE_ELEMENT);
        createEReference(xmlValueFunctionValidateElementEClass, XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE);
        createEReference(xmlValueFunctionValidateElementEClass, XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME);
        createEReference(xmlValueFunctionValidateElementEClass, XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO);

        xmlTableColumnDefinitionDefaultEClass = createEClass(XML_TABLE_COLUMN_DEFINITION_DEFAULT);
        createEReference(xmlTableColumnDefinitionDefaultEClass, XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR);
        createEReference(xmlTableColumnDefinitionDefaultEClass, XML_TABLE_COLUMN_DEFINITION_DEFAULT__COLUMN_DEFINITION_REGULAR);

        xmlSerializeFunctionEncodingEClass = createEClass(XML_SERIALIZE_FUNCTION_ENCODING);
        createEAttribute(xmlSerializeFunctionEncodingEClass, XML_SERIALIZE_FUNCTION_ENCODING__ENCODING_NAME);

        // Create enums
        xmlPassingTypeEEnum = createEEnum(XML_PASSING_TYPE);
        xmlContentTypeEEnum = createEEnum(XML_CONTENT_TYPE);
        xmlDeclarationTypeEEnum = createEEnum(XML_DECLARATION_TYPE);
        xmlReturningTypeEEnum = createEEnum(XML_RETURNING_TYPE);
        xmlNullHandlingTypeEEnum = createEEnum(XML_NULL_HANDLING_TYPE);
        xmlWhitespaceHandlingTypeEEnum = createEEnum(XML_WHITESPACE_HANDLING_TYPE);
        xmlEmptyHandlingTypeEEnum = createEEnum(XML_EMPTY_HANDLING_TYPE);
        xmlContentType2EEnum = createEEnum(XML_CONTENT_TYPE2);
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
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        SQLQueryModelPackage theSQLQueryModelPackage = (SQLQueryModelPackage)EPackage.Registry.INSTANCE.getEPackage(SQLQueryModelPackage.eNS_URI);
        SQLDataTypesPackage theSQLDataTypesPackage = (SQLDataTypesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLDataTypesPackage.eNS_URI);

        // Add supertypes to classes
        xmlValueFunctionConcatEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueFunctionEClass.getESuperTypes().add(theSQLQueryModelPackage.getValueExpressionFunction());
        xmlNamespaceDeclarationPrefixEClass.getESuperTypes().add(this.getXMLNamespaceDeclarationItem());
        xmlNamespaceDeclarationDefaultEClass.getESuperTypes().add(this.getXMLNamespaceDeclarationItem());
        xmlAttributeDeclarationItemEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlValueFunctionElementEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlNamespaceDeclarationItemEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlValueFunctionElementContentItemEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlValueFunctionForestEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueFunctionCommentEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueFunctionDocumentEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueFunctionParseEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueFunctionPIEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueFunctionQueryEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueFunctionTextEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueFunctionValidateEClass.getESuperTypes().add(this.getXMLValueFunction());
        xmlValueExpressionCastEClass.getESuperTypes().add(theSQLQueryModelPackage.getValueExpressionCast());
        xmlPredicateEClass.getESuperTypes().add(theSQLQueryModelPackage.getPredicate());
        xmlPredicateContentEClass.getESuperTypes().add(this.getXMLPredicate());
        xmlPredicateDocumentEClass.getESuperTypes().add(this.getXMLPredicate());
        xmlPredicateExistsEClass.getESuperTypes().add(this.getXMLPredicate());
        xmlPredicateValidEClass.getESuperTypes().add(this.getXMLPredicate());
        xmlQueryExpressionEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlQueryArgumentListEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlQueryArgumentItemEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlSerializeFunctionEClass.getESuperTypes().add(theSQLQueryModelPackage.getValueExpressionFunction());
        xmlSerializeFunctionTargetEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlAggregateFunctionEClass.getESuperTypes().add(theSQLQueryModelPackage.getValueExpressionFunction());
        xmlValueFunctionConcatContentItemEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlValueFunctionCommentContentEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlValueFunctionDocumentContentEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlAggregateSortSpecificationEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlValueFunctionForestContentItemEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlValueFunctionParseContentEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlValueFunctionPIContentEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlTableFunctionEClass.getESuperTypes().add(theSQLQueryModelPackage.getTableFunction());
        xmlValueFunctionTextContentEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlValueFunctionValidateContentEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlTableColumnDefinitionItemEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlTableColumnDefinitionRegularEClass.getESuperTypes().add(this.getXMLTableColumnDefinitionItem());
        xmlTableColumnDefinitionOrdinalityEClass.getESuperTypes().add(this.getXMLTableColumnDefinitionItem());
        xmlValueFunctionValidateAccordingToEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlValueFunctionValidateAccordingToURIEClass.getESuperTypes().add(this.getXMLValueFunctionValidateAccordingTo());
        xmlValueFunctionValidateAccordingToIdentifierEClass.getESuperTypes().add(this.getXMLValueFunctionValidateAccordingTo());
        xmlValueFunctionValidateElementNameEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlValueFunctionValidateElementNamespaceEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlNamespacesDeclarationEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlValueFunctionElementContentListEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlValueFunctionQueryReturningEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlValueFunctionValidateElementEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());
        xmlTableColumnDefinitionDefaultEClass.getESuperTypes().add(theSQLQueryModelPackage.getQueryValueExpression());
        xmlSerializeFunctionEncodingEClass.getESuperTypes().add(theSQLQueryModelPackage.getSQLQueryObject());

        // Initialize classes and features; add operations and parameters
        initEClass(xmlValueFunctionConcatEClass, XMLValueFunctionConcat.class, "XMLValueFunctionConcat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionConcat_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLValueFunctionConcat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionConcat_ConcatContentList(), this.getXMLValueFunctionConcatContentItem(), this.getXMLValueFunctionConcatContentItem_ValueFunctionConcat(), "concatContentList", null, 1, -1, XMLValueFunctionConcat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionEClass, XMLValueFunction.class, "XMLValueFunction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(xmlNamespaceDeclarationPrefixEClass, XMLNamespaceDeclarationPrefix.class, "XMLNamespaceDeclarationPrefix", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLNamespaceDeclarationPrefix_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, XMLNamespaceDeclarationPrefix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlNamespaceDeclarationDefaultEClass, XMLNamespaceDeclarationDefault.class, "XMLNamespaceDeclarationDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLNamespaceDeclarationDefault_NoDefault(), ecorePackage.getEBoolean(), "noDefault", "false", 0, 1, XMLNamespaceDeclarationDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlAttributeDeclarationItemEClass, XMLAttributeDeclarationItem.class, "XMLAttributeDeclarationItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLAttributeDeclarationItem_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLAttributeDeclarationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLAttributeDeclarationItem_AttributesDecl(), this.getXMLAttributesDeclaration(), this.getXMLAttributesDeclaration_AttributeDeclItem(), "attributesDecl", null, 1, 1, XMLAttributeDeclarationItem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionElementEClass, XMLValueFunctionElement.class, "XMLValueFunctionElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionElement_ElementName(), ecorePackage.getEString(), "elementName", null, 0, 1, XMLValueFunctionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionElement_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLValueFunctionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionElement_NamespacesDecl(), this.getXMLNamespacesDeclaration(), this.getXMLNamespacesDeclaration_ValueFunctionElement(), "namespacesDecl", null, 0, 1, XMLValueFunctionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionElement_AttributesDecl(), this.getXMLAttributesDeclaration(), this.getXMLAttributesDeclaration_ValueFunctionElement(), "attributesDecl", null, 0, 1, XMLValueFunctionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionElement_ElementContentList(), this.getXMLValueFunctionElementContentList(), this.getXMLValueFunctionElementContentList_ValueFunctionElement(), "elementContentList", null, 0, 1, XMLValueFunctionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlNamespaceDeclarationItemEClass, XMLNamespaceDeclarationItem.class, "XMLNamespaceDeclarationItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLNamespaceDeclarationItem_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, XMLNamespaceDeclarationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLNamespaceDeclarationItem_NamespacesDecl(), this.getXMLNamespacesDeclaration(), this.getXMLNamespacesDeclaration_NamespaceDecltemList(), "namespacesDecl", null, 1, 1, XMLNamespaceDeclarationItem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionElementContentItemEClass, XMLValueFunctionElementContentItem.class, "XMLValueFunctionElementContentItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionElementContentItem_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionElementContentItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionElementContentItem_ElementContentList(), this.getXMLValueFunctionElementContentList(), this.getXMLValueFunctionElementContentList_ElementContentListChildren(), "elementContentList", null, 1, 1, XMLValueFunctionElementContentItem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionForestEClass, XMLValueFunctionForest.class, "XMLValueFunctionForest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionForest_NullHandlingOption(), this.getXMLNullHandlingType(), "nullHandlingOption", null, 0, 1, XMLValueFunctionForest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionForest_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLValueFunctionForest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionForest_ForestContentList(), this.getXMLValueFunctionForestContentItem(), this.getXMLValueFunctionForestContentItem_ValueFunctionForest(), "forestContentList", null, 0, -1, XMLValueFunctionForest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionForest_NamespacesDecl(), this.getXMLNamespacesDeclaration(), this.getXMLNamespacesDeclaration_ValueFunctionForest(), "namespacesDecl", null, 0, 1, XMLValueFunctionForest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionCommentEClass, XMLValueFunctionComment.class, "XMLValueFunctionComment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionComment_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLValueFunctionComment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionComment_CommentContent(), this.getXMLValueFunctionCommentContent(), this.getXMLValueFunctionCommentContent_ValueFunctionComment(), "commentContent", null, 1, 1, XMLValueFunctionComment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionDocumentEClass, XMLValueFunctionDocument.class, "XMLValueFunctionDocument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionDocument_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLValueFunctionDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionDocument_DocumentContent(), this.getXMLValueFunctionDocumentContent(), this.getXMLValueFunctionDocumentContent_ValueFunctionDocument(), "documentContent", null, 1, 1, XMLValueFunctionDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionParseEClass, XMLValueFunctionParse.class, "XMLValueFunctionParse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionParse_ContentOption(), this.getXMLContentType(), "contentOption", null, 0, 1, XMLValueFunctionParse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionParse_WhitespaceHandlingOption(), this.getXMLWhitespaceHandlingType(), "whitespaceHandlingOption", null, 0, 1, XMLValueFunctionParse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionParse_ParseContent(), this.getXMLValueFunctionParseContent(), this.getXMLValueFunctionParseContent_ValueFunctionParse(), "parseContent", null, 1, 1, XMLValueFunctionParse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionPIEClass, XMLValueFunctionPI.class, "XMLValueFunctionPI", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionPI_TargetName(), ecorePackage.getEString(), "targetName", null, 0, 1, XMLValueFunctionPI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionPI_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLValueFunctionPI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionPI_PIContent(), this.getXMLValueFunctionPIContent(), this.getXMLValueFunctionPIContent_ValueFunctionPI(), "PIContent", null, 0, 1, XMLValueFunctionPI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionQueryEClass, XMLValueFunctionQuery.class, "XMLValueFunctionQuery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionQuery_EmptyHandlingOption(), this.getXMLEmptyHandlingType(), "emptyHandlingOption", null, 0, 1, XMLValueFunctionQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionQuery_XqueryExpr(), this.getXMLQueryExpression(), this.getXMLQueryExpression_ValueFunctionQuery(), "xqueryExpr", null, 1, 1, XMLValueFunctionQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionQuery_XqueryArgList(), this.getXMLQueryArgumentList(), this.getXMLQueryArgumentList_ValueFunctionQuery(), "xqueryArgList", null, 0, 1, XMLValueFunctionQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionQuery_QueryReturning(), this.getXMLValueFunctionQueryReturning(), this.getXMLValueFunctionQueryReturning_ValueFunctionQuery(), "queryReturning", null, 0, 1, XMLValueFunctionQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionTextEClass, XMLValueFunctionText.class, "XMLValueFunctionText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionText_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLValueFunctionText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionText_TextContent(), this.getXMLValueFunctionTextContent(), this.getXMLValueFunctionTextContent_ValueFunctionText(), "textContent", null, 1, 1, XMLValueFunctionText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionValidateEClass, XMLValueFunctionValidate.class, "XMLValueFunctionValidate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionValidate_ContentOption(), this.getXMLContentType2(), "contentOption", null, 0, 1, XMLValueFunctionValidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionValidate_ValidateContent(), this.getXMLValueFunctionValidateContent(), this.getXMLValueFunctionValidateContent_ValueFunctionValidate(), "validateContent", null, 1, 1, XMLValueFunctionValidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionValidate_ValidateAccordingTo(), this.getXMLValueFunctionValidateAccordingTo(), this.getXMLValueFunctionValidateAccordingTo_ValueFunctionValidate(), "validateAccordingTo", null, 0, 1, XMLValueFunctionValidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueExpressionCastEClass, XMLValueExpressionCast.class, "XMLValueExpressionCast", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueExpressionCast_PassingMechanism(), this.getXMLPassingType(), "passingMechanism", null, 0, 1, XMLValueExpressionCast.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlPredicateEClass, XMLPredicate.class, "XMLPredicate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(xmlPredicateContentEClass, XMLPredicateContent.class, "XMLPredicateContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(xmlPredicateDocumentEClass, XMLPredicateDocument.class, "XMLPredicateDocument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(xmlPredicateExistsEClass, XMLPredicateExists.class, "XMLPredicateExists", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLPredicateExists_XqueryExpr(), this.getXMLQueryExpression(), this.getXMLQueryExpression_PredicateExists(), "xqueryExpr", null, 1, 1, XMLPredicateExists.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLPredicateExists_XqueryArgList(), this.getXMLQueryArgumentList(), this.getXMLQueryArgumentList_PredicateExists(), "xqueryArgList", null, 0, 1, XMLPredicateExists.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlPredicateValidEClass, XMLPredicateValid.class, "XMLPredicateValid", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(xmlQueryExpressionEClass, XMLQueryExpression.class, "XMLQueryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLQueryExpression_XqueryExprContent(), ecorePackage.getEString(), "xqueryExprContent", null, 0, 1, XMLQueryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLQueryExpression_PredicateExists(), this.getXMLPredicateExists(), this.getXMLPredicateExists_XqueryExpr(), "predicateExists", null, 1, 1, XMLQueryExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLQueryExpression_ValueFunctionQuery(), this.getXMLValueFunctionQuery(), this.getXMLValueFunctionQuery_XqueryExpr(), "valueFunctionQuery", null, 1, 1, XMLQueryExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlQueryArgumentListEClass, XMLQueryArgumentList.class, "XMLQueryArgumentList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLQueryArgumentList_PassingMechanism(), this.getXMLPassingType(), "passingMechanism", null, 0, 1, XMLQueryArgumentList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLQueryArgumentList_PredicateExists(), this.getXMLPredicateExists(), this.getXMLPredicateExists_XqueryArgList(), "predicateExists", null, 1, 1, XMLQueryArgumentList.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLQueryArgumentList_XqueryArgListChildren(), this.getXMLQueryArgumentItem(), this.getXMLQueryArgumentItem_XqueryArgList(), "xqueryArgListChildren", null, 1, -1, XMLQueryArgumentList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLQueryArgumentList_ValueFunctionQuery(), this.getXMLValueFunctionQuery(), this.getXMLValueFunctionQuery_XqueryArgList(), "valueFunctionQuery", null, 1, 1, XMLQueryArgumentList.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLQueryArgumentList_TableFunction(), this.getXMLTableFunction(), this.getXMLTableFunction_XqueryArgList(), "tableFunction", null, 1, 1, XMLQueryArgumentList.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlQueryArgumentItemEClass, XMLQueryArgumentItem.class, "XMLQueryArgumentItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLQueryArgumentItem_PassingMechanism(), this.getXMLPassingType(), "passingMechanism", null, 0, 1, XMLQueryArgumentItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLQueryArgumentItem_XqueryArgList(), this.getXMLQueryArgumentList(), this.getXMLQueryArgumentList_XqueryArgListChildren(), "xqueryArgList", null, 1, 1, XMLQueryArgumentItem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLQueryArgumentItem_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLQueryArgumentItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlSerializeFunctionEClass, XMLSerializeFunction.class, "XMLSerializeFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLSerializeFunction_ContentOption(), this.getXMLContentType(), "contentOption", null, 0, 1, XMLSerializeFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLSerializeFunction_SerializeVersion(), ecorePackage.getEString(), "serializeVersion", null, 0, 1, XMLSerializeFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLSerializeFunction_DeclarationOption(), this.getXMLDeclarationType(), "declarationOption", null, 0, 1, XMLSerializeFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLSerializeFunction_SerializeTarget(), this.getXMLSerializeFunctionTarget(), this.getXMLSerializeFunctionTarget_SerializeFunction(), "serializeTarget", null, 1, 1, XMLSerializeFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLSerializeFunction_SerializeEncoding(), this.getXMLSerializeFunctionEncoding(), null, "serializeEncoding", null, 0, 1, XMLSerializeFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlSerializeFunctionTargetEClass, XMLSerializeFunctionTarget.class, "XMLSerializeFunctionTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLSerializeFunctionTarget_SerializeFunction(), this.getXMLSerializeFunction(), this.getXMLSerializeFunction_SerializeTarget(), "serializeFunction", null, 1, 1, XMLSerializeFunctionTarget.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLSerializeFunctionTarget_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLSerializeFunctionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlAggregateFunctionEClass, XMLAggregateFunction.class, "XMLAggregateFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLAggregateFunction_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLAggregateFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLAggregateFunction_SortSpecList(), this.getXMLAggregateSortSpecification(), this.getXMLAggregateSortSpecification_AggregateFunction(), "sortSpecList", null, 0, -1, XMLAggregateFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionConcatContentItemEClass, XMLValueFunctionConcatContentItem.class, "XMLValueFunctionConcatContentItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionConcatContentItem_ValueFunctionConcat(), this.getXMLValueFunctionConcat(), this.getXMLValueFunctionConcat_ConcatContentList(), "valueFunctionConcat", null, 1, 1, XMLValueFunctionConcatContentItem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionConcatContentItem_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionConcatContentItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionCommentContentEClass, XMLValueFunctionCommentContent.class, "XMLValueFunctionCommentContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionCommentContent_ValueFunctionComment(), this.getXMLValueFunctionComment(), this.getXMLValueFunctionComment_CommentContent(), "valueFunctionComment", null, 1, 1, XMLValueFunctionCommentContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionCommentContent_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionCommentContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionDocumentContentEClass, XMLValueFunctionDocumentContent.class, "XMLValueFunctionDocumentContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionDocumentContent_ValueFunctionDocument(), this.getXMLValueFunctionDocument(), this.getXMLValueFunctionDocument_DocumentContent(), "valueFunctionDocument", null, 1, 1, XMLValueFunctionDocumentContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionDocumentContent_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionDocumentContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlAggregateSortSpecificationEClass, XMLAggregateSortSpecification.class, "XMLAggregateSortSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLAggregateSortSpecification_AggregateFunction(), this.getXMLAggregateFunction(), this.getXMLAggregateFunction_SortSpecList(), "aggregateFunction", null, 1, 1, XMLAggregateSortSpecification.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLAggregateSortSpecification_OrderBySpec(), theSQLQueryModelPackage.getOrderBySpecification(), null, "orderBySpec", null, 1, 1, XMLAggregateSortSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionForestContentItemEClass, XMLValueFunctionForestContentItem.class, "XMLValueFunctionForestContentItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionForestContentItem_ValueFunctionForest(), this.getXMLValueFunctionForest(), this.getXMLValueFunctionForest_ForestContentList(), "valueFunctionForest", null, 1, 1, XMLValueFunctionForestContentItem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionForestContentItem_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionForestContentItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionParseContentEClass, XMLValueFunctionParseContent.class, "XMLValueFunctionParseContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionParseContent_ValueFunctionParse(), this.getXMLValueFunctionParse(), this.getXMLValueFunctionParse_ParseContent(), "valueFunctionParse", null, 1, 1, XMLValueFunctionParseContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionParseContent_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionParseContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionPIContentEClass, XMLValueFunctionPIContent.class, "XMLValueFunctionPIContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionPIContent_ValueFunctionPI(), this.getXMLValueFunctionPI(), this.getXMLValueFunctionPI_PIContent(), "valueFunctionPI", null, 1, 1, XMLValueFunctionPIContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionPIContent_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionPIContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlTableFunctionEClass, XMLTableFunction.class, "XMLTableFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLTableFunction_TableRowPattern(), ecorePackage.getEString(), "tableRowPattern", null, 0, 1, XMLTableFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLTableFunction_XqueryArgList(), this.getXMLQueryArgumentList(), this.getXMLQueryArgumentList_TableFunction(), "xqueryArgList", null, 0, 1, XMLTableFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLTableFunction_ColumnDefList(), this.getXMLTableColumnDefinitionItem(), this.getXMLTableColumnDefinitionItem_TableFunction(), "columnDefList", null, 1, -1, XMLTableFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLTableFunction_NamespacesDecl(), this.getXMLNamespacesDeclaration(), this.getXMLNamespacesDeclaration_TableFunction(), "namespacesDecl", null, 0, 1, XMLTableFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionTextContentEClass, XMLValueFunctionTextContent.class, "XMLValueFunctionTextContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionTextContent_ValueFunctionText(), this.getXMLValueFunctionText(), this.getXMLValueFunctionText_TextContent(), "valueFunctionText", null, 1, 1, XMLValueFunctionTextContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionTextContent_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionTextContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionValidateContentEClass, XMLValueFunctionValidateContent.class, "XMLValueFunctionValidateContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionValidateContent_ValueFunctionValidate(), this.getXMLValueFunctionValidate(), this.getXMLValueFunctionValidate_ValidateContent(), "valueFunctionValidate", null, 1, 1, XMLValueFunctionValidateContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionValidateContent_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLValueFunctionValidateContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlTableColumnDefinitionItemEClass, XMLTableColumnDefinitionItem.class, "XMLTableColumnDefinitionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLTableColumnDefinitionItem_TableFunction(), this.getXMLTableFunction(), this.getXMLTableFunction_ColumnDefList(), "tableFunction", null, 1, 1, XMLTableColumnDefinitionItem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlTableColumnDefinitionRegularEClass, XMLTableColumnDefinitionRegular.class, "XMLTableColumnDefinitionRegular", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLTableColumnDefinitionRegular_DataType(), theSQLDataTypesPackage.getDataType(), null, "dataType", null, 0, 1, XMLTableColumnDefinitionRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLTableColumnDefinitionRegular_PassingOption(), this.getXMLPassingType(), "passingOption", null, 0, 1, XMLTableColumnDefinitionRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLTableColumnDefinitionRegular_TableColumnPattern(), ecorePackage.getEString(), "tableColumnPattern", null, 0, 1, XMLTableColumnDefinitionRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLTableColumnDefinitionRegular_ColumnDefinitionDefault(), this.getXMLTableColumnDefinitionDefault(), this.getXMLTableColumnDefinitionDefault_ColumnDefinitionRegular(), "columnDefinitionDefault", null, 0, 1, XMLTableColumnDefinitionRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlTableColumnDefinitionOrdinalityEClass, XMLTableColumnDefinitionOrdinality.class, "XMLTableColumnDefinitionOrdinality", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(xmlValueFunctionValidateAccordingToEClass, XMLValueFunctionValidateAccordingTo.class, "XMLValueFunctionValidateAccordingTo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionValidateAccordingTo_ValueFunctionValidate(), this.getXMLValueFunctionValidate(), this.getXMLValueFunctionValidate_ValidateAccordingTo(), "valueFunctionValidate", null, 1, 1, XMLValueFunctionValidateAccordingTo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionValidateAccordingTo_ValidateElement(), this.getXMLValueFunctionValidateElement(), this.getXMLValueFunctionValidateElement_ValidateAccordingTo(), "validateElement", null, 0, 1, XMLValueFunctionValidateAccordingTo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionValidateAccordingToURIEClass, XMLValueFunctionValidateAccordingToURI.class, "XMLValueFunctionValidateAccordingToURI", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionValidateAccordingToURI_NoNamespace(), ecorePackage.getEBoolean(), "noNamespace", "false", 0, 1, XMLValueFunctionValidateAccordingToURI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionValidateAccordingToURI_TargetNamespaceURI(), ecorePackage.getEString(), "targetNamespaceURI", null, 0, 1, XMLValueFunctionValidateAccordingToURI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionValidateAccordingToURI_SchemaLocationURI(), ecorePackage.getEString(), "schemaLocationURI", null, 0, 1, XMLValueFunctionValidateAccordingToURI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionValidateAccordingToIdentifierEClass, XMLValueFunctionValidateAccordingToIdentifier.class, "XMLValueFunctionValidateAccordingToIdentifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionValidateAccordingToIdentifier_SchemaName(), ecorePackage.getEString(), "schemaName", null, 0, 1, XMLValueFunctionValidateAccordingToIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionValidateAccordingToIdentifier_RegisteredXMLSchemaName(), ecorePackage.getEString(), "registeredXMLSchemaName", null, 0, 1, XMLValueFunctionValidateAccordingToIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionValidateElementNameEClass, XMLValueFunctionValidateElementName.class, "XMLValueFunctionValidateElementName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionValidateElementName_ValidateElement(), this.getXMLValueFunctionValidateElement(), this.getXMLValueFunctionValidateElement_ValidateElementName(), "validateElement", null, 1, 1, XMLValueFunctionValidateElementName.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionValidateElementNamespaceEClass, XMLValueFunctionValidateElementNamespace.class, "XMLValueFunctionValidateElementNamespace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionValidateElementNamespace_NoNamespace(), ecorePackage.getEBoolean(), "noNamespace", "false", 0, 1, XMLValueFunctionValidateElementNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionValidateElementNamespace_NamespaceURI(), ecorePackage.getEString(), "namespaceURI", null, 0, 1, XMLValueFunctionValidateElementNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionValidateElementNamespace_ValidateElement(), this.getXMLValueFunctionValidateElement(), this.getXMLValueFunctionValidateElement_ValidateElementNamespace(), "validateElement", null, 1, 1, XMLValueFunctionValidateElementNamespace.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlNamespacesDeclarationEClass, XMLNamespacesDeclaration.class, "XMLNamespacesDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLNamespacesDeclaration_NamespaceDecltemList(), this.getXMLNamespaceDeclarationItem(), this.getXMLNamespaceDeclarationItem_NamespacesDecl(), "namespaceDecltemList", null, 1, -1, XMLNamespacesDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLNamespacesDeclaration_ValueFunctionElement(), this.getXMLValueFunctionElement(), this.getXMLValueFunctionElement_NamespacesDecl(), "valueFunctionElement", null, 1, 1, XMLNamespacesDeclaration.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLNamespacesDeclaration_ValueFunctionForest(), this.getXMLValueFunctionForest(), this.getXMLValueFunctionForest_NamespacesDecl(), "valueFunctionForest", null, 1, 1, XMLNamespacesDeclaration.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLNamespacesDeclaration_TableFunction(), this.getXMLTableFunction(), this.getXMLTableFunction_NamespacesDecl(), "tableFunction", null, 1, 1, XMLNamespacesDeclaration.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlAttributesDeclarationEClass, XMLAttributesDeclaration.class, "XMLAttributesDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLAttributesDeclaration_ValueFunctionElement(), this.getXMLValueFunctionElement(), this.getXMLValueFunctionElement_AttributesDecl(), "valueFunctionElement", null, 1, 1, XMLAttributesDeclaration.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLAttributesDeclaration_AttributeDeclItem(), this.getXMLAttributeDeclarationItem(), this.getXMLAttributeDeclarationItem_AttributesDecl(), "attributeDeclItem", null, 1, -1, XMLAttributesDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionElementContentListEClass, XMLValueFunctionElementContentList.class, "XMLValueFunctionElementContentList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionElementContentList_NullHandlingOption(), this.getXMLNullHandlingType(), "nullHandlingOption", null, 0, 1, XMLValueFunctionElementContentList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionElementContentList_ValueFunctionElement(), this.getXMLValueFunctionElement(), this.getXMLValueFunctionElement_ElementContentList(), "valueFunctionElement", null, 1, 1, XMLValueFunctionElementContentList.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionElementContentList_ElementContentListChildren(), this.getXMLValueFunctionElementContentItem(), this.getXMLValueFunctionElementContentItem_ElementContentList(), "elementContentListChildren", null, 0, -1, XMLValueFunctionElementContentList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionQueryReturningEClass, XMLValueFunctionQueryReturning.class, "XMLValueFunctionQueryReturning", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLValueFunctionQueryReturning_ReturningOption(), this.getXMLReturningType(), "returningOption", null, 0, 1, XMLValueFunctionQueryReturning.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXMLValueFunctionQueryReturning_PassingOption(), this.getXMLPassingType(), "passingOption", null, 0, 1, XMLValueFunctionQueryReturning.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionQueryReturning_ValueFunctionQuery(), this.getXMLValueFunctionQuery(), this.getXMLValueFunctionQuery_QueryReturning(), "valueFunctionQuery", null, 1, 1, XMLValueFunctionQueryReturning.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlValueFunctionValidateElementEClass, XMLValueFunctionValidateElement.class, "XMLValueFunctionValidateElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLValueFunctionValidateElement_ValidateElementNamespace(), this.getXMLValueFunctionValidateElementNamespace(), this.getXMLValueFunctionValidateElementNamespace_ValidateElement(), "validateElementNamespace", null, 0, 1, XMLValueFunctionValidateElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionValidateElement_ValidateElementName(), this.getXMLValueFunctionValidateElementName(), this.getXMLValueFunctionValidateElementName_ValidateElement(), "validateElementName", null, 0, 1, XMLValueFunctionValidateElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLValueFunctionValidateElement_ValidateAccordingTo(), this.getXMLValueFunctionValidateAccordingTo(), this.getXMLValueFunctionValidateAccordingTo_ValidateElement(), "validateAccordingTo", null, 1, 1, XMLValueFunctionValidateElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlTableColumnDefinitionDefaultEClass, XMLTableColumnDefinitionDefault.class, "XMLTableColumnDefinitionDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getXMLTableColumnDefinitionDefault_ValueExpr(), theSQLQueryModelPackage.getQueryValueExpression(), null, "valueExpr", null, 1, 1, XMLTableColumnDefinitionDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXMLTableColumnDefinitionDefault_ColumnDefinitionRegular(), this.getXMLTableColumnDefinitionRegular(), this.getXMLTableColumnDefinitionRegular_ColumnDefinitionDefault(), "columnDefinitionRegular", null, 1, 1, XMLTableColumnDefinitionDefault.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlSerializeFunctionEncodingEClass, XMLSerializeFunctionEncoding.class, "XMLSerializeFunctionEncoding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXMLSerializeFunctionEncoding_EncodingName(), ecorePackage.getEString(), "encodingName", null, 0, 1, XMLSerializeFunctionEncoding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(xmlPassingTypeEEnum, XMLPassingType.class, "XMLPassingType");
        addEEnumLiteral(xmlPassingTypeEEnum, XMLPassingType.BY_REF_LITERAL);
        addEEnumLiteral(xmlPassingTypeEEnum, XMLPassingType.BY_VALUE_LITERAL);
        addEEnumLiteral(xmlPassingTypeEEnum, XMLPassingType.NONE_LITERAL);

        initEEnum(xmlContentTypeEEnum, XMLContentType.class, "XMLContentType");
        addEEnumLiteral(xmlContentTypeEEnum, XMLContentType.CONTENT_LITERAL);
        addEEnumLiteral(xmlContentTypeEEnum, XMLContentType.DOCUMENT_LITERAL);
        addEEnumLiteral(xmlContentTypeEEnum, XMLContentType.NONE_LITERAL);

        initEEnum(xmlDeclarationTypeEEnum, XMLDeclarationType.class, "XMLDeclarationType");
        addEEnumLiteral(xmlDeclarationTypeEEnum, XMLDeclarationType.EXCLUDING_XMLDECLARATION_LITERAL);
        addEEnumLiteral(xmlDeclarationTypeEEnum, XMLDeclarationType.INCLUDING_XMLDECLARATION_LITERAL);
        addEEnumLiteral(xmlDeclarationTypeEEnum, XMLDeclarationType.NONE_LITERAL);

        initEEnum(xmlReturningTypeEEnum, XMLReturningType.class, "XMLReturningType");
        addEEnumLiteral(xmlReturningTypeEEnum, XMLReturningType.RETURNING_CONTENT_LITERAL);
        addEEnumLiteral(xmlReturningTypeEEnum, XMLReturningType.RETURNING_SEQUENCE_LITERAL);
        addEEnumLiteral(xmlReturningTypeEEnum, XMLReturningType.NONE_LITERAL);

        initEEnum(xmlNullHandlingTypeEEnum, XMLNullHandlingType.class, "XMLNullHandlingType");
        addEEnumLiteral(xmlNullHandlingTypeEEnum, XMLNullHandlingType.ABSENT_ON_NULL_LITERAL);
        addEEnumLiteral(xmlNullHandlingTypeEEnum, XMLNullHandlingType.EMPTY_ON_NULL_LITERAL);
        addEEnumLiteral(xmlNullHandlingTypeEEnum, XMLNullHandlingType.NIL_ON_NO_CONTENT_LITERAL);
        addEEnumLiteral(xmlNullHandlingTypeEEnum, XMLNullHandlingType.NIL_ON_NULL_LITERAL);
        addEEnumLiteral(xmlNullHandlingTypeEEnum, XMLNullHandlingType.NULL_ON_NULL_LITERAL);
        addEEnumLiteral(xmlNullHandlingTypeEEnum, XMLNullHandlingType.NONE_LITERAL);

        initEEnum(xmlWhitespaceHandlingTypeEEnum, XMLWhitespaceHandlingType.class, "XMLWhitespaceHandlingType");
        addEEnumLiteral(xmlWhitespaceHandlingTypeEEnum, XMLWhitespaceHandlingType.PRESERE_WHITESPACE_LITERAL);
        addEEnumLiteral(xmlWhitespaceHandlingTypeEEnum, XMLWhitespaceHandlingType.STRIP_WHITESPACE_LITERAL);
        addEEnumLiteral(xmlWhitespaceHandlingTypeEEnum, XMLWhitespaceHandlingType.NONE_LITERAL);

        initEEnum(xmlEmptyHandlingTypeEEnum, XMLEmptyHandlingType.class, "XMLEmptyHandlingType");
        addEEnumLiteral(xmlEmptyHandlingTypeEEnum, XMLEmptyHandlingType.EMPTY_ON_EMPTY_LITERAL);
        addEEnumLiteral(xmlEmptyHandlingTypeEEnum, XMLEmptyHandlingType.NULL_ON_EMPTY_LITERAL);
        addEEnumLiteral(xmlEmptyHandlingTypeEEnum, XMLEmptyHandlingType.NONE_LITERAL);

        initEEnum(xmlContentType2EEnum, XMLContentType2.class, "XMLContentType2");
        addEEnumLiteral(xmlContentType2EEnum, XMLContentType2.CONTENT_LITERAL);
        addEEnumLiteral(xmlContentType2EEnum, XMLContentType2.DOCUMENT_LITERAL);
        addEEnumLiteral(xmlContentType2EEnum, XMLContentType2.SEQUENCE_LITERAL);
        addEEnumLiteral(xmlContentType2EEnum, XMLContentType2.NONE_LITERAL);

        // Create resource
        createResource(eNS_URI);
    }

} //SQLXMLQueryPackageImpl
