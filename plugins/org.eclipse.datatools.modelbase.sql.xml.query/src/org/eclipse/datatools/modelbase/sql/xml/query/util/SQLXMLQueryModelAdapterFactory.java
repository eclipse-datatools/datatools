/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLXMLQueryModelAdapterFactory.java,v 1.1 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.util;



import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage
 * @generated
 */
public class SQLXMLQueryModelAdapterFactory extends AdapterFactoryImpl {
	/**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static SQLXMLQueryModelPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SQLXMLQueryModelAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = SQLXMLQueryModelPackage.eINSTANCE;
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
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SQLXMLQueryModelSwitch modelSwitch =
		new SQLXMLQueryModelSwitch() {
            public Object caseXMLValueFunctionConcat(XMLValueFunctionConcat object) {
                return createXMLValueFunctionConcatAdapter();
            }
            public Object caseXMLValueFunction(XMLValueFunction object) {
                return createXMLValueFunctionAdapter();
            }
            public Object caseXMLNamespaceDeclarationPrefix(XMLNamespaceDeclarationPrefix object) {
                return createXMLNamespaceDeclarationPrefixAdapter();
            }
            public Object caseXMLNamespaceDeclarationDefault(XMLNamespaceDeclarationDefault object) {
                return createXMLNamespaceDeclarationDefaultAdapter();
            }
            public Object caseXMLAttributeDeclarationItem(XMLAttributeDeclarationItem object) {
                return createXMLAttributeDeclarationItemAdapter();
            }
            public Object caseXMLValueFunctionElement(XMLValueFunctionElement object) {
                return createXMLValueFunctionElementAdapter();
            }
            public Object caseXMLNamespaceDeclarationItem(XMLNamespaceDeclarationItem object) {
                return createXMLNamespaceDeclarationItemAdapter();
            }
            public Object caseXMLValueFunctionElementContentItem(XMLValueFunctionElementContentItem object) {
                return createXMLValueFunctionElementContentItemAdapter();
            }
            public Object caseXMLValueFunctionForest(XMLValueFunctionForest object) {
                return createXMLValueFunctionForestAdapter();
            }
            public Object caseXMLValueFunctionComment(XMLValueFunctionComment object) {
                return createXMLValueFunctionCommentAdapter();
            }
            public Object caseXMLValueFunctionDocument(XMLValueFunctionDocument object) {
                return createXMLValueFunctionDocumentAdapter();
            }
            public Object caseXMLValueFunctionParse(XMLValueFunctionParse object) {
                return createXMLValueFunctionParseAdapter();
            }
            public Object caseXMLValueFunctionPI(XMLValueFunctionPI object) {
                return createXMLValueFunctionPIAdapter();
            }
            public Object caseXMLValueFunctionQuery(XMLValueFunctionQuery object) {
                return createXMLValueFunctionQueryAdapter();
            }
            public Object caseXMLValueFunctionText(XMLValueFunctionText object) {
                return createXMLValueFunctionTextAdapter();
            }
            public Object caseXMLValueFunctionValidate(XMLValueFunctionValidate object) {
                return createXMLValueFunctionValidateAdapter();
            }
            public Object caseXMLValueExpressionCast(XMLValueExpressionCast object) {
                return createXMLValueExpressionCastAdapter();
            }
            public Object caseXMLPredicate(XMLPredicate object) {
                return createXMLPredicateAdapter();
            }
            public Object caseXMLPredicateContent(XMLPredicateContent object) {
                return createXMLPredicateContentAdapter();
            }
            public Object caseXMLPredicateDocument(XMLPredicateDocument object) {
                return createXMLPredicateDocumentAdapter();
            }
            public Object caseXMLPredicateExists(XMLPredicateExists object) {
                return createXMLPredicateExistsAdapter();
            }
            public Object caseXMLPredicateValid(XMLPredicateValid object) {
                return createXMLPredicateValidAdapter();
            }
            public Object caseXMLQueryExpression(XMLQueryExpression object) {
                return createXMLQueryExpressionAdapter();
            }
            public Object caseXMLQueryArgumentList(XMLQueryArgumentList object) {
                return createXMLQueryArgumentListAdapter();
            }
            public Object caseXMLQueryArgumentItem(XMLQueryArgumentItem object) {
                return createXMLQueryArgumentItemAdapter();
            }
            public Object caseXMLSerializeFunction(XMLSerializeFunction object) {
                return createXMLSerializeFunctionAdapter();
            }
            public Object caseXMLSerializeFunctionTarget(XMLSerializeFunctionTarget object) {
                return createXMLSerializeFunctionTargetAdapter();
            }
            public Object caseXMLAggregateFunction(XMLAggregateFunction object) {
                return createXMLAggregateFunctionAdapter();
            }
            public Object caseXMLValueFunctionConcatContentItem(XMLValueFunctionConcatContentItem object) {
                return createXMLValueFunctionConcatContentItemAdapter();
            }
            public Object caseXMLValueFunctionCommentContent(XMLValueFunctionCommentContent object) {
                return createXMLValueFunctionCommentContentAdapter();
            }
            public Object caseXMLValueFunctionDocumentContent(XMLValueFunctionDocumentContent object) {
                return createXMLValueFunctionDocumentContentAdapter();
            }
            public Object caseXMLAggregateSortSpecification(XMLAggregateSortSpecification object) {
                return createXMLAggregateSortSpecificationAdapter();
            }
            public Object caseXMLValueFunctionForestContentItem(XMLValueFunctionForestContentItem object) {
                return createXMLValueFunctionForestContentItemAdapter();
            }
            public Object caseXMLValueFunctionParseContent(XMLValueFunctionParseContent object) {
                return createXMLValueFunctionParseContentAdapter();
            }
            public Object caseXMLValueFunctionPIContent(XMLValueFunctionPIContent object) {
                return createXMLValueFunctionPIContentAdapter();
            }
            public Object caseXMLTableFunction(XMLTableFunction object) {
                return createXMLTableFunctionAdapter();
            }
            public Object caseXMLValueFunctionTextContent(XMLValueFunctionTextContent object) {
                return createXMLValueFunctionTextContentAdapter();
            }
            public Object caseXMLValueFunctionValidateContent(XMLValueFunctionValidateContent object) {
                return createXMLValueFunctionValidateContentAdapter();
            }
            public Object caseXMLTableColumnDefinitionItem(XMLTableColumnDefinitionItem object) {
                return createXMLTableColumnDefinitionItemAdapter();
            }
            public Object caseXMLTableColumnDefinitionRegular(XMLTableColumnDefinitionRegular object) {
                return createXMLTableColumnDefinitionRegularAdapter();
            }
            public Object caseXMLTableColumnDefinitionOrdinality(XMLTableColumnDefinitionOrdinality object) {
                return createXMLTableColumnDefinitionOrdinalityAdapter();
            }
            public Object caseXMLValueFunctionValidateAccordingTo(XMLValueFunctionValidateAccordingTo object) {
                return createXMLValueFunctionValidateAccordingToAdapter();
            }
            public Object caseXMLValueFunctionValidateAccordingToURI(XMLValueFunctionValidateAccordingToURI object) {
                return createXMLValueFunctionValidateAccordingToURIAdapter();
            }
            public Object caseXMLValueFunctionValidateAccordingToIdentifier(XMLValueFunctionValidateAccordingToIdentifier object) {
                return createXMLValueFunctionValidateAccordingToIdentifierAdapter();
            }
            public Object caseXMLValueFunctionValidateElementName(XMLValueFunctionValidateElementName object) {
                return createXMLValueFunctionValidateElementNameAdapter();
            }
            public Object caseXMLValueFunctionValidateElementNamespace(XMLValueFunctionValidateElementNamespace object) {
                return createXMLValueFunctionValidateElementNamespaceAdapter();
            }
            public Object caseXMLNamespacesDeclaration(XMLNamespacesDeclaration object) {
                return createXMLNamespacesDeclarationAdapter();
            }
            public Object caseXMLAttributesDeclaration(XMLAttributesDeclaration object) {
                return createXMLAttributesDeclarationAdapter();
            }
            public Object caseXMLValueFunctionElementContentList(XMLValueFunctionElementContentList object) {
                return createXMLValueFunctionElementContentListAdapter();
            }
            public Object caseXMLValueFunctionQueryReturning(XMLValueFunctionQueryReturning object) {
                return createXMLValueFunctionQueryReturningAdapter();
            }
            public Object caseXMLValueFunctionValidateElement(XMLValueFunctionValidateElement object) {
                return createXMLValueFunctionValidateElementAdapter();
            }
            public Object caseXMLTableColumnDefinitionDefault(XMLTableColumnDefinitionDefault object) {
                return createXMLTableColumnDefinitionDefaultAdapter();
            }
            public Object caseXMLSerializeFunctionEncoding(XMLSerializeFunctionEncoding object) {
                return createXMLSerializeFunctionEncodingAdapter();
            }
            public Object caseEModelElement(EModelElement object) {
                return createEModelElementAdapter();
            }
            public Object caseENamedElement(ENamedElement object) {
                return createENamedElementAdapter();
            }
            public Object caseSQLObject(SQLObject object) {
                return createSQLObjectAdapter();
            }
            public Object caseSQLQueryObject(SQLQueryObject object) {
                return createSQLQueryObjectAdapter();
            }
            public Object caseValueExpression(ValueExpression object) {
                return createValueExpressionAdapter();
            }
            public Object caseQueryValueExpression(QueryValueExpression object) {
                return createQueryValueExpressionAdapter();
            }
            public Object caseValueExpressionAtomic(ValueExpressionAtomic object) {
                return createValueExpressionAtomicAdapter();
            }
            public Object caseValueExpressionFunction(ValueExpressionFunction object) {
                return createValueExpressionFunctionAdapter();
            }
            public Object caseValueExpressionCast(ValueExpressionCast object) {
                return createValueExpressionCastAdapter();
            }
            public Object caseSearchCondition(SearchCondition object) {
                return createSearchConditionAdapter();
            }
            public Object caseQuerySearchCondition(QuerySearchCondition object) {
                return createQuerySearchConditionAdapter();
            }
            public Object casePredicate(Predicate object) {
                return createPredicateAdapter();
            }
            public Object caseTableReference(TableReference object) {
                return createTableReferenceAdapter();
            }
            public Object caseTableExpression(TableExpression object) {
                return createTableExpressionAdapter();
            }
            public Object caseTableFunction(TableFunction object) {
                return createTableFunctionAdapter();
            }
            public Object defaultCase(EObject object) {
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
    public Adapter createAdapter(Notifier target) {
        return (Adapter)modelSwitch.doSwitch((EObject)target);
    }


	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat <em>XML Value Function Concat</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat
     * @generated
     */
    public Adapter createXMLValueFunctionConcatAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunction <em>XML Value Function</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunction
     * @generated
     */
    public Adapter createXMLValueFunctionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix <em>XML Namespace Declaration Prefix</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix
     * @generated
     */
    public Adapter createXMLNamespaceDeclarationPrefixAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault <em>XML Namespace Declaration Default</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault
     * @generated
     */
    public Adapter createXMLNamespaceDeclarationDefaultAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem <em>XML Attribute Declaration Item</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem
     * @generated
     */
    public Adapter createXMLAttributeDeclarationItemAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement <em>XML Value Function Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement
     * @generated
     */
    public Adapter createXMLValueFunctionElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem <em>XML Namespace Declaration Item</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem
     * @generated
     */
    public Adapter createXMLNamespaceDeclarationItemAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem <em>XML Value Function Element Content Item</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem
     * @generated
     */
    public Adapter createXMLValueFunctionElementContentItemAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest <em>XML Value Function Forest</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest
     * @generated
     */
    public Adapter createXMLValueFunctionForestAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment <em>XML Value Function Comment</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment
     * @generated
     */
    public Adapter createXMLValueFunctionCommentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument <em>XML Value Function Document</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument
     * @generated
     */
    public Adapter createXMLValueFunctionDocumentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse <em>XML Value Function Parse</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse
     * @generated
     */
    public Adapter createXMLValueFunctionParseAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI <em>XML Value Function PI</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI
     * @generated
     */
    public Adapter createXMLValueFunctionPIAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery <em>XML Value Function Query</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery
     * @generated
     */
    public Adapter createXMLValueFunctionQueryAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText <em>XML Value Function Text</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText
     * @generated
     */
    public Adapter createXMLValueFunctionTextAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate <em>XML Value Function Validate</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate
     * @generated
     */
    public Adapter createXMLValueFunctionValidateAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast <em>XML Value Expression Cast</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast
     * @generated
     */
    public Adapter createXMLValueExpressionCastAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicate <em>XML Predicate</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicate
     * @generated
     */
    public Adapter createXMLPredicateAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateContent <em>XML Predicate Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateContent
     * @generated
     */
    public Adapter createXMLPredicateContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateDocument <em>XML Predicate Document</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateDocument
     * @generated
     */
    public Adapter createXMLPredicateDocumentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists <em>XML Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists
     * @generated
     */
    public Adapter createXMLPredicateExistsAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateValid <em>XML Predicate Valid</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateValid
     * @generated
     */
    public Adapter createXMLPredicateValidAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression <em>XML Query Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression
     * @generated
     */
    public Adapter createXMLQueryExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList <em>XML Query Argument List</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList
     * @generated
     */
    public Adapter createXMLQueryArgumentListAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem <em>XML Query Argument Item</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem
     * @generated
     */
    public Adapter createXMLQueryArgumentItemAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction <em>XML Serialize Function</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction
     * @generated
     */
    public Adapter createXMLSerializeFunctionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget <em>XML Serialize Function Target</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget
     * @generated
     */
    public Adapter createXMLSerializeFunctionTargetAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction <em>XML Aggregate Function</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction
     * @generated
     */
    public Adapter createXMLAggregateFunctionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem <em>XML Value Function Concat Content Item</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem
     * @generated
     */
    public Adapter createXMLValueFunctionConcatContentItemAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent <em>XML Value Function Comment Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent
     * @generated
     */
    public Adapter createXMLValueFunctionCommentContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent <em>XML Value Function Document Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent
     * @generated
     */
    public Adapter createXMLValueFunctionDocumentContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification <em>XML Aggregate Sort Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification
     * @generated
     */
    public Adapter createXMLAggregateSortSpecificationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem <em>XML Value Function Forest Content Item</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem
     * @generated
     */
    public Adapter createXMLValueFunctionForestContentItemAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent <em>XML Value Function Parse Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent
     * @generated
     */
    public Adapter createXMLValueFunctionParseContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent <em>XML Value Function PI Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent
     * @generated
     */
    public Adapter createXMLValueFunctionPIContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction <em>XML Table Function</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction
     * @generated
     */
    public Adapter createXMLTableFunctionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent <em>XML Value Function Text Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent
     * @generated
     */
    public Adapter createXMLValueFunctionTextContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent <em>XML Value Function Validate Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent
     * @generated
     */
    public Adapter createXMLValueFunctionValidateContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem <em>XML Table Column Definition Item</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem
     * @generated
     */
    public Adapter createXMLTableColumnDefinitionItemAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular <em>XML Table Column Definition Regular</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular
     * @generated
     */
    public Adapter createXMLTableColumnDefinitionRegularAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionOrdinality <em>XML Table Column Definition Ordinality</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionOrdinality
     * @generated
     */
    public Adapter createXMLTableColumnDefinitionOrdinalityAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo <em>XML Value Function Validate According To</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo
     * @generated
     */
    public Adapter createXMLValueFunctionValidateAccordingToAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI <em>XML Value Function Validate According To URI</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI
     * @generated
     */
    public Adapter createXMLValueFunctionValidateAccordingToURIAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier <em>XML Value Function Validate According To Identifier</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier
     * @generated
     */
    public Adapter createXMLValueFunctionValidateAccordingToIdentifierAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName <em>XML Value Function Validate Element Name</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName
     * @generated
     */
    public Adapter createXMLValueFunctionValidateElementNameAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace <em>XML Value Function Validate Element Namespace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace
     * @generated
     */
    public Adapter createXMLValueFunctionValidateElementNamespaceAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration <em>XML Namespaces Declaration</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration
     * @generated
     */
    public Adapter createXMLNamespacesDeclarationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration <em>XML Attributes Declaration</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration
     * @generated
     */
    public Adapter createXMLAttributesDeclarationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList <em>XML Value Function Element Content List</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList
     * @generated
     */
    public Adapter createXMLValueFunctionElementContentListAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning <em>XML Value Function Query Returning</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning
     * @generated
     */
    public Adapter createXMLValueFunctionQueryReturningAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement <em>XML Value Function Validate Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement
     * @generated
     */
    public Adapter createXMLValueFunctionValidateElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault <em>XML Table Column Definition Default</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault
     * @generated
     */
    public Adapter createXMLTableColumnDefinitionDefaultAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding <em>XML Serialize Function Encoding</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding
     * @generated
     */
    public Adapter createXMLSerializeFunctionEncodingAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.emf.ecore.EModelElement
     * @generated
     */
    public Adapter createEModelElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>ENamed Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.emf.ecore.ENamedElement
     * @generated
     */
    public Adapter createENamedElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject <em>SQL Object</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject
     * @generated
     */
    public Adapter createSQLObjectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.SQLQueryObject <em>SQL Query Object</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryObject
     * @generated
     */
    public Adapter createSQLQueryObjectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.expressions.ValueExpression <em>Value Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.expressions.ValueExpression
     * @generated
     */
    public Adapter createValueExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression <em>Query Value Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression
     * @generated
     */
    public Adapter createQueryValueExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic <em>Value Expression Atomic</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic
     * @generated
     */
    public Adapter createValueExpressionAtomicAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction <em>Value Expression Function</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction
     * @generated
     */
    public Adapter createValueExpressionFunctionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast <em>Value Expression Cast</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast
     * @generated
     */
    public Adapter createValueExpressionCastAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.expressions.SearchCondition <em>Search Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.expressions.SearchCondition
     * @generated
     */
    public Adapter createSearchConditionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition <em>Query Search Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition
     * @generated
     */
    public Adapter createQuerySearchConditionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.Predicate <em>Predicate</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.Predicate
     * @generated
     */
    public Adapter createPredicateAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableReference <em>Table Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference
     * @generated
     */
    public Adapter createTableReferenceAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression <em>Table Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression
     * @generated
     */
    public Adapter createTableExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableFunction
     * @generated
     */
    public Adapter createTableFunctionAdapter() {
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
    public Adapter createEObjectAdapter() {
        return null;
    }

} //SQLXMLQueryAdapterFactory
