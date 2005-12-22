/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLXMLQueryFactoryImpl.java,v 1.2 2005/12/19 20:57:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;


import org.eclipse.datatools.modelbase.sql.xml.query.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLXMLQueryModelFactoryImpl extends EFactoryImpl implements SQLXMLQueryModelFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SQLXMLQueryModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_CONCAT: return createXMLValueFunctionConcat();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_PREFIX: return createXMLNamespaceDeclarationPrefix();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_DEFAULT: return createXMLNamespaceDeclarationDefault();
			case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM: return createXMLAttributeDeclarationItem();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT: return createXMLValueFunctionElement();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM: return createXMLNamespaceDeclarationItem();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM: return createXMLValueFunctionElementContentItem();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST: return createXMLValueFunctionForest();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT: return createXMLValueFunctionComment();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_DOCUMENT: return createXMLValueFunctionDocument();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE: return createXMLValueFunctionParse();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI: return createXMLValueFunctionPI();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY: return createXMLValueFunctionQuery();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT: return createXMLValueFunctionText();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE: return createXMLValueFunctionValidate();
			case SQLXMLQueryModelPackage.XML_VALUE_EXPRESSION_CAST: return createXMLValueExpressionCast();
			case SQLXMLQueryModelPackage.XML_PREDICATE_CONTENT: return createXMLPredicateContent();
			case SQLXMLQueryModelPackage.XML_PREDICATE_DOCUMENT: return createXMLPredicateDocument();
			case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS: return createXMLPredicateExists();
			case SQLXMLQueryModelPackage.XML_PREDICATE_VALID: return createXMLPredicateValid();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION: return createXMLQueryExpression();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST: return createXMLQueryArgumentList();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_ITEM: return createXMLQueryArgumentItem();
			case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION: return createXMLSerializeFunction();
			case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_TARGET: return createXMLSerializeFunctionTarget();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_FUNCTION: return createXMLAggregateFunction();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM: return createXMLValueFunctionConcatContentItem();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT_CONTENT: return createXMLValueFunctionCommentContent();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_DOCUMENT_CONTENT: return createXMLValueFunctionDocumentContent();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION: return createXMLAggregateSortSpecification();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM: return createXMLValueFunctionForestContentItem();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE_CONTENT: return createXMLValueFunctionParseContent();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT: return createXMLValueFunctionPIContent();
			case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION: return createXMLTableFunction();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT_CONTENT: return createXMLValueFunctionTextContent();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_CONTENT: return createXMLValueFunctionValidateContent();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM: return createXMLTableColumnDefinitionItem();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR: return createXMLTableColumnDefinitionRegular();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY: return createXMLTableColumnDefinitionOrdinality();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO: return createXMLValueFunctionValidateAccordingTo();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI: return createXMLValueFunctionValidateAccordingToURI();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER: return createXMLValueFunctionValidateAccordingToIdentifier();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME: return createXMLValueFunctionValidateElementName();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE: return createXMLValueFunctionValidateElementNamespace();
			case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION: return createXMLNamespacesDeclaration();
			case SQLXMLQueryModelPackage.XML_ATTRIBUTES_DECLARATION: return createXMLAttributesDeclaration();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST: return createXMLValueFunctionElementContentList();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING: return createXMLValueFunctionQueryReturning();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT: return createXMLValueFunctionValidateElement();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_DEFAULT: return createXMLTableColumnDefinitionDefault();
			case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_ENCODING: return createXMLSerializeFunctionEncoding();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SQLXMLQueryModelPackage.XML_PASSING_TYPE: {
				XMLPassingType result = XMLPassingType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case SQLXMLQueryModelPackage.XML_CONTENT_TYPE: {
				XMLContentType result = XMLContentType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case SQLXMLQueryModelPackage.XML_DECLARATION_TYPE: {
				XMLDeclarationType result = XMLDeclarationType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case SQLXMLQueryModelPackage.XML_RETURNING_TYPE: {
				XMLReturningType result = XMLReturningType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case SQLXMLQueryModelPackage.XML_NULL_HANDLING_TYPE: {
				XMLNullHandlingType result = XMLNullHandlingType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case SQLXMLQueryModelPackage.XML_WHITESPACE_HANDLING_TYPE: {
				XMLWhitespaceHandlingType result = XMLWhitespaceHandlingType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case SQLXMLQueryModelPackage.XML_EMPTY_HANDLING_TYPE: {
				XMLEmptyHandlingType result = XMLEmptyHandlingType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case SQLXMLQueryModelPackage.XML_CONTENT_TYPE2: {
				XMLContentType2 result = XMLContentType2.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SQLXMLQueryModelPackage.XML_PASSING_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLXMLQueryModelPackage.XML_CONTENT_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLXMLQueryModelPackage.XML_DECLARATION_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLXMLQueryModelPackage.XML_RETURNING_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLXMLQueryModelPackage.XML_NULL_HANDLING_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLXMLQueryModelPackage.XML_WHITESPACE_HANDLING_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLXMLQueryModelPackage.XML_EMPTY_HANDLING_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLXMLQueryModelPackage.XML_CONTENT_TYPE2:
				return instanceValue == null ? null : instanceValue.toString();
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionConcat createXMLValueFunctionConcat() {
		XMLValueFunctionConcatImpl xmlValueFunctionConcat = new XMLValueFunctionConcatImpl();
		return xmlValueFunctionConcat;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLNamespaceDeclarationPrefix createXMLNamespaceDeclarationPrefix() {
		XMLNamespaceDeclarationPrefixImpl xmlNamespaceDeclarationPrefix = new XMLNamespaceDeclarationPrefixImpl();
		return xmlNamespaceDeclarationPrefix;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLNamespaceDeclarationDefault createXMLNamespaceDeclarationDefault() {
		XMLNamespaceDeclarationDefaultImpl xmlNamespaceDeclarationDefault = new XMLNamespaceDeclarationDefaultImpl();
		return xmlNamespaceDeclarationDefault;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLAttributeDeclarationItem createXMLAttributeDeclarationItem() {
		XMLAttributeDeclarationItemImpl xmlAttributeDeclarationItem = new XMLAttributeDeclarationItemImpl();
		return xmlAttributeDeclarationItem;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionElement createXMLValueFunctionElement() {
		XMLValueFunctionElementImpl xmlValueFunctionElement = new XMLValueFunctionElementImpl();
		return xmlValueFunctionElement;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLNamespaceDeclarationItem createXMLNamespaceDeclarationItem() {
		XMLNamespaceDeclarationItemImpl xmlNamespaceDeclarationItem = new XMLNamespaceDeclarationItemImpl();
		return xmlNamespaceDeclarationItem;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionElementContentItem createXMLValueFunctionElementContentItem() {
		XMLValueFunctionElementContentItemImpl xmlValueFunctionElementContentItem = new XMLValueFunctionElementContentItemImpl();
		return xmlValueFunctionElementContentItem;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionForest createXMLValueFunctionForest() {
		XMLValueFunctionForestImpl xmlValueFunctionForest = new XMLValueFunctionForestImpl();
		return xmlValueFunctionForest;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionComment createXMLValueFunctionComment() {
		XMLValueFunctionCommentImpl xmlValueFunctionComment = new XMLValueFunctionCommentImpl();
		return xmlValueFunctionComment;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionDocument createXMLValueFunctionDocument() {
		XMLValueFunctionDocumentImpl xmlValueFunctionDocument = new XMLValueFunctionDocumentImpl();
		return xmlValueFunctionDocument;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionParse createXMLValueFunctionParse() {
		XMLValueFunctionParseImpl xmlValueFunctionParse = new XMLValueFunctionParseImpl();
		return xmlValueFunctionParse;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionPI createXMLValueFunctionPI() {
		XMLValueFunctionPIImpl xmlValueFunctionPI = new XMLValueFunctionPIImpl();
		return xmlValueFunctionPI;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionQuery createXMLValueFunctionQuery() {
		XMLValueFunctionQueryImpl xmlValueFunctionQuery = new XMLValueFunctionQueryImpl();
		return xmlValueFunctionQuery;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionText createXMLValueFunctionText() {
		XMLValueFunctionTextImpl xmlValueFunctionText = new XMLValueFunctionTextImpl();
		return xmlValueFunctionText;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidate createXMLValueFunctionValidate() {
		XMLValueFunctionValidateImpl xmlValueFunctionValidate = new XMLValueFunctionValidateImpl();
		return xmlValueFunctionValidate;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueExpressionCast createXMLValueExpressionCast() {
		XMLValueExpressionCastImpl xmlValueExpressionCast = new XMLValueExpressionCastImpl();
		return xmlValueExpressionCast;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPredicateContent createXMLPredicateContent() {
		XMLPredicateContentImpl xmlPredicateContent = new XMLPredicateContentImpl();
		return xmlPredicateContent;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPredicateDocument createXMLPredicateDocument() {
		XMLPredicateDocumentImpl xmlPredicateDocument = new XMLPredicateDocumentImpl();
		return xmlPredicateDocument;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPredicateExists createXMLPredicateExists() {
		XMLPredicateExistsImpl xmlPredicateExists = new XMLPredicateExistsImpl();
		return xmlPredicateExists;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPredicateValid createXMLPredicateValid() {
		XMLPredicateValidImpl xmlPredicateValid = new XMLPredicateValidImpl();
		return xmlPredicateValid;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLQueryExpression createXMLQueryExpression() {
		XMLQueryExpressionImpl xmlQueryExpression = new XMLQueryExpressionImpl();
		return xmlQueryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLQueryArgumentList createXMLQueryArgumentList() {
		XMLQueryArgumentListImpl xmlQueryArgumentList = new XMLQueryArgumentListImpl();
		return xmlQueryArgumentList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLQueryArgumentItem createXMLQueryArgumentItem() {
		XMLQueryArgumentItemImpl xmlQueryArgumentItem = new XMLQueryArgumentItemImpl();
		return xmlQueryArgumentItem;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLSerializeFunction createXMLSerializeFunction() {
		XMLSerializeFunctionImpl xmlSerializeFunction = new XMLSerializeFunctionImpl();
		return xmlSerializeFunction;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLSerializeFunctionTarget createXMLSerializeFunctionTarget() {
		XMLSerializeFunctionTargetImpl xmlSerializeFunctionTarget = new XMLSerializeFunctionTargetImpl();
		return xmlSerializeFunctionTarget;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLAggregateFunction createXMLAggregateFunction() {
		XMLAggregateFunctionImpl xmlAggregateFunction = new XMLAggregateFunctionImpl();
		return xmlAggregateFunction;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionConcatContentItem createXMLValueFunctionConcatContentItem() {
		XMLValueFunctionConcatContentItemImpl xmlValueFunctionConcatContentItem = new XMLValueFunctionConcatContentItemImpl();
		return xmlValueFunctionConcatContentItem;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionCommentContent createXMLValueFunctionCommentContent() {
		XMLValueFunctionCommentContentImpl xmlValueFunctionCommentContent = new XMLValueFunctionCommentContentImpl();
		return xmlValueFunctionCommentContent;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionDocumentContent createXMLValueFunctionDocumentContent() {
		XMLValueFunctionDocumentContentImpl xmlValueFunctionDocumentContent = new XMLValueFunctionDocumentContentImpl();
		return xmlValueFunctionDocumentContent;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLAggregateSortSpecification createXMLAggregateSortSpecification() {
		XMLAggregateSortSpecificationImpl xmlAggregateSortSpecification = new XMLAggregateSortSpecificationImpl();
		return xmlAggregateSortSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionForestContentItem createXMLValueFunctionForestContentItem() {
		XMLValueFunctionForestContentItemImpl xmlValueFunctionForestContentItem = new XMLValueFunctionForestContentItemImpl();
		return xmlValueFunctionForestContentItem;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionParseContent createXMLValueFunctionParseContent() {
		XMLValueFunctionParseContentImpl xmlValueFunctionParseContent = new XMLValueFunctionParseContentImpl();
		return xmlValueFunctionParseContent;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionPIContent createXMLValueFunctionPIContent() {
		XMLValueFunctionPIContentImpl xmlValueFunctionPIContent = new XMLValueFunctionPIContentImpl();
		return xmlValueFunctionPIContent;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableFunction createXMLTableFunction() {
		XMLTableFunctionImpl xmlTableFunction = new XMLTableFunctionImpl();
		return xmlTableFunction;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionTextContent createXMLValueFunctionTextContent() {
		XMLValueFunctionTextContentImpl xmlValueFunctionTextContent = new XMLValueFunctionTextContentImpl();
		return xmlValueFunctionTextContent;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateContent createXMLValueFunctionValidateContent() {
		XMLValueFunctionValidateContentImpl xmlValueFunctionValidateContent = new XMLValueFunctionValidateContentImpl();
		return xmlValueFunctionValidateContent;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableColumnDefinitionItem createXMLTableColumnDefinitionItem() {
		XMLTableColumnDefinitionItemImpl xmlTableColumnDefinitionItem = new XMLTableColumnDefinitionItemImpl();
		return xmlTableColumnDefinitionItem;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableColumnDefinitionRegular createXMLTableColumnDefinitionRegular() {
		XMLTableColumnDefinitionRegularImpl xmlTableColumnDefinitionRegular = new XMLTableColumnDefinitionRegularImpl();
		return xmlTableColumnDefinitionRegular;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableColumnDefinitionOrdinality createXMLTableColumnDefinitionOrdinality() {
		XMLTableColumnDefinitionOrdinalityImpl xmlTableColumnDefinitionOrdinality = new XMLTableColumnDefinitionOrdinalityImpl();
		return xmlTableColumnDefinitionOrdinality;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateAccordingTo createXMLValueFunctionValidateAccordingTo() {
		XMLValueFunctionValidateAccordingToImpl xmlValueFunctionValidateAccordingTo = new XMLValueFunctionValidateAccordingToImpl();
		return xmlValueFunctionValidateAccordingTo;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateAccordingToURI createXMLValueFunctionValidateAccordingToURI() {
		XMLValueFunctionValidateAccordingToURIImpl xmlValueFunctionValidateAccordingToURI = new XMLValueFunctionValidateAccordingToURIImpl();
		return xmlValueFunctionValidateAccordingToURI;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateAccordingToIdentifier createXMLValueFunctionValidateAccordingToIdentifier() {
		XMLValueFunctionValidateAccordingToIdentifierImpl xmlValueFunctionValidateAccordingToIdentifier = new XMLValueFunctionValidateAccordingToIdentifierImpl();
		return xmlValueFunctionValidateAccordingToIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateElementName createXMLValueFunctionValidateElementName() {
		XMLValueFunctionValidateElementNameImpl xmlValueFunctionValidateElementName = new XMLValueFunctionValidateElementNameImpl();
		return xmlValueFunctionValidateElementName;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateElementNamespace createXMLValueFunctionValidateElementNamespace() {
		XMLValueFunctionValidateElementNamespaceImpl xmlValueFunctionValidateElementNamespace = new XMLValueFunctionValidateElementNamespaceImpl();
		return xmlValueFunctionValidateElementNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLNamespacesDeclaration createXMLNamespacesDeclaration() {
		XMLNamespacesDeclarationImpl xmlNamespacesDeclaration = new XMLNamespacesDeclarationImpl();
		return xmlNamespacesDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLAttributesDeclaration createXMLAttributesDeclaration() {
		XMLAttributesDeclarationImpl xmlAttributesDeclaration = new XMLAttributesDeclarationImpl();
		return xmlAttributesDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionElementContentList createXMLValueFunctionElementContentList() {
		XMLValueFunctionElementContentListImpl xmlValueFunctionElementContentList = new XMLValueFunctionElementContentListImpl();
		return xmlValueFunctionElementContentList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionQueryReturning createXMLValueFunctionQueryReturning() {
		XMLValueFunctionQueryReturningImpl xmlValueFunctionQueryReturning = new XMLValueFunctionQueryReturningImpl();
		return xmlValueFunctionQueryReturning;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateElement createXMLValueFunctionValidateElement() {
		XMLValueFunctionValidateElementImpl xmlValueFunctionValidateElement = new XMLValueFunctionValidateElementImpl();
		return xmlValueFunctionValidateElement;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableColumnDefinitionDefault createXMLTableColumnDefinitionDefault() {
		XMLTableColumnDefinitionDefaultImpl xmlTableColumnDefinitionDefault = new XMLTableColumnDefinitionDefaultImpl();
		return xmlTableColumnDefinitionDefault;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLSerializeFunctionEncoding createXMLSerializeFunctionEncoding() {
		XMLSerializeFunctionEncodingImpl xmlSerializeFunctionEncoding = new XMLSerializeFunctionEncodingImpl();
		return xmlSerializeFunctionEncoding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLXMLQueryModelPackage getSQLXMLQueryModelPackage() {
		return (SQLXMLQueryModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static SQLXMLQueryModelPackage getPackage() {
		return SQLXMLQueryModelPackage.eINSTANCE;
	}

} //SQLXMLQueryFactoryImpl
