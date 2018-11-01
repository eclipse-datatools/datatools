/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.xml.query;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelFactory;
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
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
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
import org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactory;



public class SQLXMLQueryParserFactory extends SQLQueryParserFactory {

    
    static SQLXMLQueryModelFactory sqlXMLQueryFactory = null;
    
    /**
     * int constant for {@link XXMLConentType#CONTENT}
     */
    public final static int XML_CONTENT_TYPE_CONTENT = XMLContentType.CONTENT;

    /**
     * int constant for {@link XMLConentType#DOCUMENT}
     */
    public final static int XML_CONTENT_TYPE_DOCUMENT = XMLContentType.DOCUMENT;

    /**
     * int constant for {@link XMLConentType#NONE}
     */
    public final static int XML_CONTENT_TYPE_NONE = XMLContentType.NONE;

    /**
     * int constant for {@link XXMLConentType2#CONTENT}
     */
    public final static int XML_CONTENT_TYPE2_CONTENT = XMLContentType2.CONTENT;

    /**
     * int constant for {@link XMLConentType2#DOCUMENT}
     */
    public final static int XML_CONTENT_TYPE2_DOCUMENT = XMLContentType2.DOCUMENT;

    /**
     * int constant for {@link XMLConentType2#NONE}
     */
    public final static int XML_CONTENT_TYPE2_NONE = XMLContentType2.NONE;

    /**
     * int constant for {@link XMLConentType2#SEQUENCE}
     */
    public final static int XML_CONTENT_TYPE2_SEQUENCE = XMLContentType2.SEQUENCE;
    
    /**
     * int constant for {@link XMLNullHandlingType#ABSENT_ON_NULL}
     */
    public final static int XML_CONTENT_OPTION_ABSENT_ON_NULL = XMLNullHandlingType.ABSENT_ON_NULL;
    
    /**
     * int constant for {@link XMLNullHandlingType#EMPTY_ON_NULL}
     */
    public final static int XML_CONTENT_OPTION_EMPTY_ON_NULL = XMLNullHandlingType.EMPTY_ON_NULL;
    
    /**
     * int constant for {@link XMLNullHandlingType#NIL_ON_NO_CONTENT}
     */
    public final static int XML_CONTENT_OPTION_NIL_ON_NO_CONTENT = XMLNullHandlingType.NIL_ON_NO_CONTENT;

    /**
     * int constant for {@link XMLNullHandlingType#NIL_ON_NULL}
     */
    public final static int XML_CONTENT_OPTION_NIL_ON_NULL = XMLNullHandlingType.NIL_ON_NULL;

    /**
     * int constant for {@link XMLNullHandlingType#NULL_ON_NULL}
     */
    public final static int XML_CONTENT_OPTION_NULL_ON_NULL = XMLNullHandlingType.NULL_ON_NULL;
    
    /**
     * int constant for {@link XMLNullHandlingType#NONE}
     */
    public final static int XML_CONTENT_OPTION_NONE = XMLNullHandlingType.NONE;
    
    
    /**
     * int constant for {@link XMLDeclarationType#EXCLUDING_XMLDECLARATION}
     */
    public final static int XML_DECLARATION_TYPE_EXCLUDING = XMLDeclarationType.EXCLUDING_XMLDECLARATION;
    
    /**
     * int constant for {@link XMLDeclarationType#INCLUDING_XMLDECLARATION}
     */
    public final static int XML_DECLARATION_TYPE_INCLUDING = XMLDeclarationType.INCLUDING_XMLDECLARATION;
    
    /**
     * int constant for {@link XMLDeclarationType#NONE}
     */
    public final static int XML_DECLARATION_TYPE_NONE = XMLDeclarationType.NONE;
    
    
    /**
     * int constant for {@link XMLEmptyHandlingType#NULL_ON_EMPTY}
     */
    public final static int XML_EMPTYHANDLINGTYPE_NULL_ON_EMPTY = XMLEmptyHandlingType.NULL_ON_EMPTY;
    
    /**
     * int constant for {@link XMLEmptyHandlingType#EMPTY_ON_EMPTY}
     */
    public final static int XML_EMPTYHANDLINGTYPE_EMPTY_ON_EMPTY = XMLEmptyHandlingType.EMPTY_ON_EMPTY;
    
    /**
     * int constant for {@link XMLEmptyHandlingType#NONE}
     */
    public final static int XML_EMPTYHANDLINGTYPE_NONE = XMLEmptyHandlingType.NONE;

    /**
     * int constant for {@link XMLPassingType#NONE}
     */
    public final static int XML_PASSING_TYPE_NONE = XMLPassingType.NONE;
    
    /**
     * int constant for {@link XMLPassingType#REF}
     */
    public final static int XML_PASSING_TYPE_REF = XMLPassingType.BY_REF;

    /**
     * int constant for {@link XMLPassingType#VALUE}
     */
    public final static int XML_PASSING_TYPE_VALUE = XMLPassingType.BY_VALUE;
    
    /**
     * int constant for {@link XMLReturningType#RETURNING_CONTENT}
     */
    public final static int XML_RETURNING_TYPE_CONTENT = XMLReturningType.RETURNING_CONTENT;

    /**
     * int constant for {@link XMLReturningType#RETURNING_SEQUENCE}
     */
    public final static int XML_RETURNING_TYPE_SEQUENCE = XMLReturningType.RETURNING_SEQUENCE;
    
    /**
     * int constant for {@link XMLReturningType#RETURNING_NONE}
     */
    public final static int XML_RETURNING_TYPE_NONE = XMLReturningType.NONE;

    /**
     * int constant for {@link XMLWhitespaceHandlingType#PRESERE_WHITESPACE}
     */
    public final static int XML_WHITESPACE_PRESERVE = XMLWhitespaceHandlingType.PRESERE_WHITESPACE;
    
    /**
     * int constant for {@link XMLWhitespaceHandlingType#STRIP_WHITESPACE}
     */
    public final static int XML_WHITESPACE_STRIP = XMLWhitespaceHandlingType.STRIP_WHITESPACE;

    /**
     * int constant for {@link XMLWhitespaceHandlingType#NONE}
     */
    public final static int XML_WHITESPACE_NONE = XMLWhitespaceHandlingType.NONE;
    
    /**
     * String constant for XML
     */
    public final String XML = "XML";
    
    
    
    /**
     * 
     */
    public SQLXMLQueryParserFactory() {
        super();
        init();
    }

    /**
     * @param aSourceFormat
     */
    public SQLXMLQueryParserFactory(SQLQuerySourceFormat aSourceFormat) {
        super(aSourceFormat);
        init();
    }
    
    /**
     * initializes this <code>SQLXMLQueryParserFactory</code> 
     */
    private void init() {
        if (SQLXMLQueryModelFactory.eINSTANCE == null) {
            SQLXMLQueryModelPackageImpl.init();
        }
        sqlXMLQueryFactory = SQLXMLQueryModelFactory.eINSTANCE;
    }
    
    
    
    /**
     * @return XMLDataType
     * @see SQLDataTypesFactory#createXMLDataType()
     */
    public XMLDataType createDataTypeXML() {
        XMLDataType dataType = SQLDataTypesFactory.eINSTANCE.createXMLDataType();
        dataType.setPrimitiveType(PrimitiveType.XML_TYPE_LITERAL);
        dataType.setName(XML);
        return dataType;
    }


//  ============================================================================
//  *********************************************************  factory methods
//  ============================================================================

    
  /*  public ValueExpressionXMLFunction createXML2CLOBFunction(XMLValueFunction aValueFunction) {
        //if (statementTypeOnly) {return null;}
        ValueExpressionXMLFunction xml2Clob = sqlXMLQueryFactory.createValueExpressionXMLFunction();
        xml2Clob.setValueFunction(aValueFunction);
        //xml2Clob.setDataType(createDataTypeCharacterString(PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null));
        //TODO: check if that is the function name, also check DB2LUWSourceWriter
        xml2Clob.setName("XML2CLOB"); //$NON-NLS-1$
        return xml2Clob;
    }
*/

  /*  public XMLAttributesFunction createXMLAttributesFunction(List aAttributeList) {
        //if (statementTypeOnly) {return null;}
        XMLAttributesFunction attributesFunction = sqlXMLQueryFactory.createXMLAttributesFunction();
        if (aAttributeList != null) {
            attributesFunction.getAttributeValueList().addAll(aAttributeList);
        }
        return attributesFunction;
    }


    public XMLAttributeValue createXMLAttributeValue(QueryValueExpression aValueExpr, String aAliasName) {
        //if (statementTypeOnly) {return null;}
        XMLAttributeValue attributeValue = sqlXMLQueryFactory.createXMLAttributeValue();
        attributeValue.setValueExpr(aValueExpr);
        attributeValue.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aAliasName, getDelimitedIdentifierQuote()));
        return attributeValue;
    }


    public List createXMLAttributeValueList(List aAttributeValueList,
                                            XMLAttributeValue aAttributeValue) {
        //if (statementTypeOnly) {return null;}
        if (aAttributeValueList == null) {
            aAttributeValueList = new Vector();
        }
        aAttributeValueList.add(aAttributeValue);
        return aAttributeValueList;
    }


    public XMLConcatFunction createXMLConcatFunction(List aValueFunctionList) {
        //if (statementTypeOnly) {return null;}
        XMLConcatFunction concatFunction = sqlXMLQueryFactory.createXMLConcatFunction();
        if (aValueFunctionList != null) {
            concatFunction.getValueFunctionList().addAll(aValueFunctionList);
        }
        return concatFunction;
    }


    public XMLElementExpression createXMLElementExpression(QueryValueExpression aValueExpr) {
        //if (statementTypeOnly) {return null;}
        XMLElementExpression elementExpression = sqlXMLQueryFactory.createXMLElementExpression();
        elementExpression.setValueExpr(aValueExpr);
        return elementExpression;
    }


    public XMLElementFunction createXMLElementFunction(String aElementName,
                                                       XMLNamespacesFunction aNamespacesFunc,
                                                       XMLAttributesFunction aAttributesFunc,
                                                       List aElementList) {
        //if (statementTypeOnly) {return null;}
        XMLElementFunction elementFunction = sqlXMLQueryFactory.createXMLElementFunction();
        elementFunction.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aElementName, getDelimitedIdentifierQuote()));
        elementFunction.setNamespacesFunction(aNamespacesFunc);
        elementFunction.setAttributesFunction(aAttributesFunc);
        if (aElementList != null) {
            elementFunction.getElementList().addAll(aElementList);
        }
        return elementFunction;
    }


    public List createXMLElementList(List aElementList,
                                     XMLElement aElement) {
        //if (statementTypeOnly) {return null;}
        if (aElementList == null) {
            aElementList = new Vector();
        }
        aElementList.add(aElement);
        return aElementList;
    }


    public XMLElementValueFunction createXMLElementValueFunction(XMLValueFunction aValueFunction) {
        //if (statementTypeOnly) {return null;}
        XMLElementValueFunction elementValueFunction = sqlXMLQueryFactory.createXMLElementValueFunction();
        elementValueFunction.setValueFunction(aValueFunction);
        return elementValueFunction;
    }




    public List createXMLForestElementList(List aForestElementList,
                                           XMLForestElement aForestElement) {
        //if (statementTypeOnly) {return null;}
        if (aForestElementList == null) {
            aForestElementList = new Vector();
        }
        aForestElementList.add(aForestElement);
        return aForestElementList;
    }


    public XMLForestFunction createXMLForestFunction(XMLNamespacesFunction aNamespacesFunc,
                                                     List aForestElementList) {
        //if (statementTypeOnly) {return null;}
        XMLForestFunction forestFunction = sqlXMLQueryFactory.createXMLForestFunction();
        forestFunction.setNamespacesFunction(aNamespacesFunc);
        if (aForestElementList != null) {
            forestFunction.getForestElementList().addAll(aForestElementList);
        }
        return forestFunction;
    }


    public XMLNamespacesFunction createXMLNamespacesFunction(List aNamespaceList) {
        //if (statementTypeOnly) {return null;}
        XMLNamespacesFunction namespacesFunction = sqlXMLQueryFactory.createXMLNamespacesFunction();
        if (aNamespaceList != null) {
            namespacesFunction.getNamespaceList().addAll(aNamespaceList);
        }
        return namespacesFunction;
    }


    public List createXMLNamespaceList(List aNamespaceList,
                                       XMLNamespace aNamespace) {
        //if (statementTypeOnly) {return null;}
        if (aNamespaceList == null) {
            aNamespaceList = new Vector();
        }
        aNamespaceList.add(aNamespace);
        return aNamespaceList;
    }


    public XMLNamespaceDefault createXMLNamespaceDefault(String aUri) {
        //if (statementTypeOnly) {return null;}
        XMLNamespaceDefault namespaceDefault = sqlXMLQueryFactory.createXMLNamespaceDefault();
        namespaceDefault.setUri(aUri);
        return namespaceDefault;
    }


    public XMLNamespaceDefault createXMLNamespaceNoDefault() {
        //if (statementTypeOnly) {return null;}
        XMLNamespaceDefault namespaceDefault = sqlXMLQueryFactory.createXMLNamespaceDefault();
        namespaceDefault.setNoDefault(true);
        return namespaceDefault;
    }


    public XMLNamespacePrefix createXMLNamespacePrefix(String aUri, String aPrefix) {
        //if (statementTypeOnly) {return null;}
        XMLNamespacePrefix namespacePrefix = sqlXMLQueryFactory.createXMLNamespacePrefix();
        namespacePrefix.setUri(aUri);
        namespacePrefix.setPrefix(aPrefix);
        return namespacePrefix;
    }


    public ValueExpressionXMLFunction createXMLSerializeFunction(QueryValueExpression aValueFunction, CharacterStringDataType aCharacterDataType) {
        //if (statementTypeOnly) {return null;}
        ValueExpressionXMLFunction xmlSerial = sqlXMLQueryFactory.createValueExpressionXMLFunction();
        
        //FIXME: set value as QueryValueExpression
        //xmlSerial.setValueFunction(aValueFunction);
        
        xmlSerial.setDataType(aCharacterDataType);
        //TODO: check if that is the function name, also check DB2LUWSourceWriter
        xmlSerial.setName("XMLSERIALIZE"); //$NON-NLS-1$
        return xmlSerial;
    }


    public List createXMLValueFunctionList(List aValueFunctionList,
                                           XMLValueFunction aValueFunction) {
        //if (statementTypeOnly) {return null;}
        if (aValueFunctionList == null) {
            aValueFunctionList = new Vector();
        }
        aValueFunctionList.add(aValueFunction);
        return aValueFunctionList;
    }*/
    public XMLAggregateFunction createXMLAggregateFunction(String aName,QueryValueExpression aExpr, List aOrderByClause,
                                    int aType) {
        XMLAggregateFunction aggregateFunction = sqlXMLQueryFactory.createXMLAggregateFunction();
        aggregateFunction.setName(aName);
        aggregateFunction.getParameterList().add(aExpr);
        if (aOrderByClause != null) {
            List sortSpecList = aggregateFunction.getSortSpecList();
            if(sortSpecList == null){
                sortSpecList = new ArrayList();
            }
            for(int i=0;i<aOrderByClause.size();i++){
                XMLAggregateSortSpecification sortSpec =  sqlXMLQueryFactory.createXMLAggregateSortSpecification();
                sortSpec.setOrderBySpec((OrderBySpecification)aOrderByClause.get(i));
                sortSpecList.add(sortSpec);
            }
        }
        aggregateFunction.setReturningOption(XMLReturningType.get(aType));
        return aggregateFunction;
    }
    
    public XMLAttributesDeclaration createXMLAttributesDeclaration(List attributeItemList){
        XMLAttributesDeclaration attribDecl = sqlXMLQueryFactory.createXMLAttributesDeclaration();
        if(attributeItemList != null){
            attribDecl.getAttributeDeclItem().addAll(attributeItemList);
        }
        return attribDecl;
    }
    
    public List createXMLAttributeDeclaraionItemList(List list, XMLAttributeDeclarationItem item){
        if(list == null){
            list = new ArrayList();
        }
        list.add(item);
        return list;
    }

    public XMLAttributeDeclarationItem createXMLAttributeDeclaraionItem(QueryValueExpression expr, String name){
        XMLAttributeDeclarationItem attributeItem = sqlXMLQueryFactory.createXMLAttributeDeclarationItem();
        attributeItem.setValueExpr(expr);
        attributeItem.setName(name);
        return attributeItem;
    }
    public XMLNamespacesDeclaration createXMLNamespaceDeclaration(List nameSpaceDeclList){
        XMLNamespacesDeclaration xmlNameSpace = sqlXMLQueryFactory.createXMLNamespacesDeclaration();
        List declList = xmlNameSpace.getNamespaceDecltemList();
        declList.addAll(nameSpaceDeclList);
        return xmlNameSpace;
    }
    
    public XMLNamespaceDeclarationDefault createXMLNamespaceDeclarationDefault(String aURI, boolean isNoDefault){
        XMLNamespaceDeclarationDefault nameSpace = sqlXMLQueryFactory.createXMLNamespaceDeclarationDefault();
        nameSpace.setUri(aURI);
        nameSpace.setNoDefault(isNoDefault);
        return nameSpace;
    }
    
    public XMLNamespaceDeclarationPrefix createXMLNamespaceDeclarationPrefix (String aURI, String aPrefix){
        XMLNamespaceDeclarationPrefix  nameSpace = sqlXMLQueryFactory.createXMLNamespaceDeclarationPrefix ();
        nameSpace.setUri(aURI);
        nameSpace.setPrefix(aPrefix);
        return nameSpace;
    }
    
    public List createXMLNamespacesDeclarationItemList(List aNamespaceList, XMLNamespaceDeclarationItem item){
        if(aNamespaceList == null){
            aNamespaceList = new ArrayList();
        }
        aNamespaceList.add(item);
        return aNamespaceList;
    }
    
    public XMLValueFunctionPI createXMLValueFunctionPI(String name,String piTarget,QueryValueExpression expr,int returningOption){
        XMLValueFunctionPI xmlPIFunction =  sqlXMLQueryFactory.createXMLValueFunctionPI();
        xmlPIFunction.setName(name);
        xmlPIFunction.setDataType(createDataTypeXML());
        xmlPIFunction.setTargetName(piTarget);
        if(expr != null){
            XMLValueFunctionPIContent piContent = sqlXMLQueryFactory.createXMLValueFunctionPIContent();
            piContent.setValueExpr(expr);
            xmlPIFunction.setPIContent(piContent);
        }
        xmlPIFunction.setReturningOption(XMLReturningType.get(returningOption));
        return xmlPIFunction;
    }
    
    public XMLValueFunctionText createXMLValueFunctionText(String name,QueryValueExpression expr,int returningOption){
        XMLValueFunctionText xmlTextFunction =  sqlXMLQueryFactory.createXMLValueFunctionText();
        xmlTextFunction.setName(name);
        xmlTextFunction.setDataType(createDataTypeXML());
        if(expr != null){
            XMLValueFunctionTextContent textContent = sqlXMLQueryFactory.createXMLValueFunctionTextContent();
            textContent.setValueExpr(expr);
            xmlTextFunction.setTextContent(textContent);
        }
        xmlTextFunction.setReturningOption(XMLReturningType.get(returningOption));
        return xmlTextFunction;
    }

    public XMLPredicateExists createXMLPredicateExists(String name,XMLQueryExpression queryExpr,XMLQueryArgumentList argList){
        XMLPredicateExists predicate =  sqlXMLQueryFactory.createXMLPredicateExists();
        predicate.setName(name);
        predicate.setXqueryExpr(queryExpr);
        predicate.setXqueryArgList(argList);
        return predicate;
    }
    
    public XMLQueryArgumentList createXMLQueryArgumentList(int passingMechanism, List argListChildren){
        XMLQueryArgumentList argList = sqlXMLQueryFactory.createXMLQueryArgumentList();
        argList.setPassingMechanism(XMLPassingType.get(passingMechanism));
        if(argListChildren != null){
            argList.getXqueryArgListChildren().addAll(argListChildren);
        }
        return argList;
    }
    
    public XMLQueryArgumentItem createXMLQueryArgumentItem(QueryValueExpression expr,String variableName,
            int passingMechanism){
        XMLQueryArgumentItem item = sqlXMLQueryFactory.createXMLQueryArgumentItem();
        item.setValueExpr(expr);
        item.setPassingMechanism(XMLPassingType.get(passingMechanism));
        item.setName(variableName);
       return item;
    }
    
    
    public List createXMLQueryArgumentItemList(List itemList,XMLQueryArgumentItem item){
        if(itemList == null){
            itemList =  new ArrayList();
        }
        itemList.add(item);
        return itemList;
    }
    
    public XMLQueryExpression createXMLQueryExpression(String exprContent){
        XMLQueryExpression queryExpression = sqlXMLQueryFactory.createXMLQueryExpression();
        queryExpression.setXqueryExprContent(exprContent);
        return queryExpression;
    }
    
    public List createXMLTableColumnDefinitionList(List columnDefList,XMLTableColumnDefinitionItem columnDef){
        if(columnDefList == null){
            columnDefList = new ArrayList();
        }
        columnDefList.add(columnDef);
        return columnDefList;
    }
    
    public XMLTableColumnDefinitionOrdinality createXMLTableColumnDefinitionOrdinality(String columnName){
        XMLTableColumnDefinitionOrdinality columnDef = sqlXMLQueryFactory.createXMLTableColumnDefinitionOrdinality();
        columnName = StatementHelper.convertSQLIdentifierToCatalogFormat(columnName, getDelimitedIdentifierQuote());
        columnDef.setName(columnName);
        return columnDef;
    }
    
    public XMLTableColumnDefinitionRegular createXMLTableColumnDefinitionRegular(String columnName,DataType dataType,
            int passingOption, QueryValueExpression defaultOption,String columnPattern){
        XMLTableColumnDefinitionRegular columnDef = sqlXMLQueryFactory.createXMLTableColumnDefinitionRegular();
        columnName = StatementHelper.convertSQLIdentifierToCatalogFormat(columnName, getDelimitedIdentifierQuote());
        columnDef.setName(columnName);
        columnDef.setDataType(dataType);
        columnDef.setPassingOption(XMLPassingType.get(passingOption));
        if (defaultOption != null) {
            XMLTableColumnDefinitionDefault columnDefinitionDefault = sqlXMLQueryFactory.createXMLTableColumnDefinitionDefault();
            columnDefinitionDefault.setValueExpr(defaultOption);
            columnDef.setColumnDefinitionDefault(columnDefinitionDefault);
        }
// TODO: fix for new XMLTableColumnDefinitionDefault class, which replaces the default attribute
//        columnDef.setDefault(defaultOption);
        columnDef.setTableColumnPattern(columnPattern);
        return columnDef;
    }

    public XMLTableFunction createXMLTableFunction(String name,XMLNamespacesDeclaration namespaceDecl,String tableRowPattern,
            XMLQueryArgumentList queryArgList, List tableColumnList){
        XMLTableFunction tablefunction =  sqlXMLQueryFactory.createXMLTableFunction();
        tablefunction.setName(name);
        tablefunction.setNamespacesDecl(namespaceDecl);
        tablefunction.setTableRowPattern(tableRowPattern);
        tablefunction.setXqueryArgList(queryArgList);
        tablefunction.getColumnDefList().addAll(tableColumnList);
        return tablefunction;
    }
        
    public XMLValueExpressionCast createXMLValueExpressionCast(QueryValueExpression operandExpression,DataType targetType,
            int passingtype){
        XMLValueExpressionCast castExpression = sqlXMLQueryFactory.createXMLValueExpressionCast();
        castExpression.setValueExpr(operandExpression);
        castExpression.setDataType(targetType);
        castExpression.setPassingMechanism(XMLPassingType.get(passingtype));
        return castExpression;
    }
    
    public XMLValueFunctionComment createXMLValueFunctionComment(String name,QueryValueExpression expr,int returningOption){
        XMLValueFunctionComment commentFunction = sqlXMLQueryFactory.createXMLValueFunctionComment();
        commentFunction.setName(name);
        commentFunction.setDataType(createDataTypeXML());
        XMLValueFunctionCommentContent content = sqlXMLQueryFactory.createXMLValueFunctionCommentContent();
        content.setValueExpr(expr);
        commentFunction.setCommentContent(content);
        commentFunction.setReturningOption(XMLReturningType.get(returningOption));
        return commentFunction;
    }
    public List createXMLValueFunctionElementContentItemList(List aContentList, QueryValueExpression aContentExpr){
        if(aContentList == null){
            aContentList =  new ArrayList();
        }
        XMLValueFunctionElementContentItem elementContentItem = sqlXMLQueryFactory.createXMLValueFunctionElementContentItem();
        aContentExpr.setDataType(createDataTypeXML());
        elementContentItem.setValueExpr(aContentExpr);
        aContentList.add(elementContentItem);
        return aContentList;
    }
    public XMLValueFunctionConcat  createXMLValueFunctionConcat(String name,List concatContentList, int returningOption){
       XMLValueFunctionConcat concatFunction = sqlXMLQueryFactory.createXMLValueFunctionConcat();
       concatFunction.setName(name);
       concatFunction.setDataType(createDataTypeXML());
       if(concatContentList != null){
        List contentList = concatFunction.getConcatContentList();
        contentList.addAll(concatContentList);
       }
       concatFunction.setReturningOption(XMLReturningType.get(returningOption));
       return concatFunction;
    }
    public List createXMLValueFunctionConcatItemList(List concatItemList,QueryValueExpression expr){
        if(concatItemList == null){
            concatItemList = new ArrayList();
        }
        XMLValueFunctionConcatContentItem item = sqlXMLQueryFactory.createXMLValueFunctionConcatContentItem();
        expr.setDataType(createDataTypeXML());
        item.setValueExpr(expr);
        concatItemList.add(item);
        return concatItemList;
    }
    
    public XMLValueFunctionDocument createXMLValueFunctionDocument(String name,QueryValueExpression expr, int returningOption ){
        XMLValueFunctionDocument documentFunction =  sqlXMLQueryFactory.createXMLValueFunctionDocument();
        documentFunction.setName(name);
        documentFunction.setDataType(createDataTypeXML());
        XMLValueFunctionDocumentContent docContent =  sqlXMLQueryFactory.createXMLValueFunctionDocumentContent();
        expr.setDataType(createDataTypeXML());
        docContent.setValueExpr(expr);
        documentFunction.setDocumentContent(docContent);
        documentFunction.setReturningOption(XMLReturningType.get(returningOption));
        return documentFunction;
    }
    
    public XMLValueFunctionElementContentList createXMLValueFucnctionElementContentList(List contentList,int nullOption){
        XMLValueFunctionElementContentList elementContent = sqlXMLQueryFactory.createXMLValueFunctionElementContentList();
        elementContent.setNullHandlingOption(XMLNullHandlingType.get(nullOption));
        if(contentList != null){
            elementContent.getElementContentListChildren().addAll(contentList);
        }
        return elementContent;
    }
    
    public XMLValueFunctionElement createXMLValueFucntionElement(String afunctionName,String aElementName,
            XMLNamespacesDeclaration namespaceDecl,XMLAttributesDeclaration aAttributesDeclaration,
            XMLValueFunctionElementContentList aContent, int aReturningOption){
        XMLValueFunctionElement elementFunction =  sqlXMLQueryFactory.createXMLValueFunctionElement();
        elementFunction.setName(afunctionName);
        elementFunction.setDataType(createDataTypeXML());
        elementFunction.setElementName(aElementName);
        elementFunction.setNamespacesDecl(namespaceDecl);
        elementFunction.setAttributesDecl(aAttributesDeclaration);
        elementFunction.setElementContentList(aContent);
        elementFunction.setReturningOption(XMLReturningType.get(aReturningOption));
        return elementFunction;
    }
    
/*    Object createXXX(XMLValueFunctionValidateElementNamespace namespace,XMLValueFunctionValidateElementName name){
        
        return new Object();
    }*/
    
    public XMLValueFunctionForest createXMLValueFunctionForest(String functionName,
            XMLNamespacesDeclaration namespaceDecl,List forestContentList,int nullOption, int returningOption) {
        
        XMLValueFunctionForest valueFunctionForest = sqlXMLQueryFactory.createXMLValueFunctionForest();
        valueFunctionForest.setName(functionName);
        valueFunctionForest.setDataType(createDataTypeXML());
        valueFunctionForest.setNamespacesDecl(namespaceDecl);
        if(forestContentList != null){
            List contentList = valueFunctionForest.getForestContentList();
            contentList.addAll(forestContentList);
        }
        valueFunctionForest.setNullHandlingOption(XMLNullHandlingType.get(nullOption));
        valueFunctionForest.setReturningOption(XMLReturningType.get(returningOption));
        return valueFunctionForest;
    }
    
    public XMLValueFunctionForestContentItem createXMLValueFunctionForestContentItem(QueryValueExpression valueExpr,
            String name){
            XMLValueFunctionForestContentItem item =  sqlXMLQueryFactory.createXMLValueFunctionForestContentItem();
            item.setValueExpr(valueExpr);
            item.setName(name); 
            return item;
    }
    
    public List createXMLValueFunctionForestContentItemList(List contentList, XMLValueFunctionForestContentItem content){
        if(contentList == null){
            contentList = new ArrayList();
        }
        contentList.add(content);
        return contentList;
    }
    
    
    public XMLValueFunctionParse createXMLValueFunctionParse(String aName, int aContentOption,
                                           QueryValueExpression aValueExpr, int aWhitespaceOption) {
        XMLValueFunctionParse xmlParseFunction = sqlXMLQueryFactory.createXMLValueFunctionParse();
        xmlParseFunction.setName(aName);
        xmlParseFunction.setDataType(createDataTypeXML());
        xmlParseFunction.setContentOption(XMLContentType.get(aContentOption));
        XMLValueFunctionParseContent parseContent = sqlXMLQueryFactory.createXMLValueFunctionParseContent();
        parseContent.setValueExpr(aValueExpr);
        xmlParseFunction.setParseContent(parseContent);
        xmlParseFunction.setWhitespaceHandlingOption(XMLWhitespaceHandlingType.get(aWhitespaceOption));
        return xmlParseFunction;
    }

    public XMLValueFunctionQuery createXMLValueFunctionQuery(String name, XMLQueryExpression queryExpression,
            XMLQueryArgumentList queryArgument, XMLValueFunctionQueryReturning returningOptions, int emptyOption){
        XMLValueFunctionQuery queryFunction = sqlXMLQueryFactory.createXMLValueFunctionQuery();
        queryFunction.setName(name);
        queryFunction.setDataType(createDataTypeXML());
        queryFunction.setXqueryExpr(queryExpression);
        queryFunction.setXqueryArgList(queryArgument);
        queryFunction.setQueryReturning(returningOptions);
        queryFunction.setEmptyHandlingOption(XMLEmptyHandlingType.get(emptyOption));
        return queryFunction;
    }
    
    public XMLValueFunctionQueryReturning createXMLValueFunctionQueryReturning(int returningOption,int passingOption){
        XMLValueFunctionQueryReturning queryReturning = sqlXMLQueryFactory.createXMLValueFunctionQueryReturning();
        queryReturning.setReturningOption(XMLReturningType.get(returningOption));
        queryReturning.setPassingOption(XMLPassingType.get(passingOption));
        return queryReturning;
    }
    
    public XMLValueFunctionValidate createXMLValueFunctionValidate(String name,int contentOption,QueryValueExpression expr,
            XMLValueFunctionValidateAccordingTo validAccordingTo){
        XMLValueFunctionValidate validateFunction = sqlXMLQueryFactory.createXMLValueFunctionValidate();
        validateFunction.setName(name);
        if(expr != null){
        	// the data type of the expression is XML
        	expr.setDataType(createDataTypeXML());
            XMLValueFunctionValidateContent validateContent = sqlXMLQueryFactory.createXMLValueFunctionValidateContent();
            validateContent.setValueExpr(expr);
            validateFunction.setValidateContent(validateContent);
        }
        validateFunction.setContentOption(XMLContentType2.get(contentOption));
        validateFunction.setValidateAccordingTo(validAccordingTo);
        return validateFunction;
    }
    
    public XMLValueFunctionValidateAccordingToIdentifier createXMLValueFunctionValidateAccordingToIdentifier(String schemaName, String registeredXMLSchemaName){
        XMLValueFunctionValidateAccordingToIdentifier validAccordingTo =
            sqlXMLQueryFactory.createXMLValueFunctionValidateAccordingToIdentifier();
        
        if (schemaName != null) {
            schemaName = StatementHelper.convertSQLIdentifierToCatalogFormat(schemaName, getDelimitedIdentifierQuote());
        }
        registeredXMLSchemaName = StatementHelper.convertSQLIdentifierToCatalogFormat(registeredXMLSchemaName, getDelimitedIdentifierQuote());
        
        validAccordingTo.setSchemaName(schemaName);
        validAccordingTo.setRegisteredXMLSchemaName(registeredXMLSchemaName);
        
        return validAccordingTo; 
    }
    
    public XMLValueFunctionValidateAccordingTo setElementContent(XMLValueFunctionValidateAccordingTo accordingTo,
            XMLValueFunctionValidateElement validateElement){
        accordingTo.setValidateElement(validateElement);
        return accordingTo;
    }
    
    public XMLValueFunctionValidateAccordingToURI createXMLValueFunctionValidateAccordingToURI(
            boolean isNamespace,String targetNamespace,String schemaLocation){
        XMLValueFunctionValidateAccordingToURI validAccordingTo = 
            sqlXMLQueryFactory.createXMLValueFunctionValidateAccordingToURI();
        validAccordingTo.setNoNamespace(isNamespace);
        validAccordingTo.setTargetNamespaceURI(targetNamespace);
        validAccordingTo.setSchemaLocationURI(schemaLocation);
        return validAccordingTo;
    }
    
    public XMLValueFunctionValidateElement createXMLValueFunctionValidateElement(XMLValueFunctionValidateElementNamespace 
            namespace, XMLValueFunctionValidateElementName name){
        XMLValueFunctionValidateElement validateElement =  sqlXMLQueryFactory.createXMLValueFunctionValidateElement();
        validateElement.setValidateElementNamespace(namespace);
        validateElement.setValidateElementName(name);
        return validateElement;
    }
    
    public XMLValueFunctionValidateElementName createXMLValueFunctionValidateElementName(String name){
        XMLValueFunctionValidateElementName elementName = 
            sqlXMLQueryFactory.createXMLValueFunctionValidateElementName();
        elementName.setName(name);
        return elementName;
    }

    public XMLValueFunctionValidateElementNamespace createXMLValueFunctionValidateElementNamespace(boolean isNoNamespace,
            String nameSpaceURI){
        XMLValueFunctionValidateElementNamespace elementNamespace = 
            sqlXMLQueryFactory.createXMLValueFunctionValidateElementNamespace();
        elementNamespace.setNoNamespace(isNoNamespace);
        elementNamespace.setNamespaceURI(nameSpaceURI);
        return elementNamespace;
    }
    
    public XMLSerializeFunction createXMLSerializeFunction(String aName,
            int aContentOption, QueryValueExpression aValueExpr, 
            DataType aDataType, String aEncodingSpec, String aVersion, int aDeclOption) {
        XMLSerializeFunction serializeFunction = sqlXMLQueryFactory.createXMLSerializeFunction();
        serializeFunction.setName(aName);
        serializeFunction.setContentOption(XMLContentType.get(aContentOption));
        XMLSerializeFunctionTarget target = sqlXMLQueryFactory.createXMLSerializeFunctionTarget();
        target.setValueExpr(aValueExpr);
        serializeFunction.setSerializeTarget(target);
 //TODO - data type is to be set for the valueexpr in the target ?
        serializeFunction.setDataType(aDataType);
        if (aEncodingSpec != null) {
            XMLSerializeFunctionEncoding encodingSpec = sqlXMLQueryFactory.createXMLSerializeFunctionEncoding();
            encodingSpec.setEncodingName(aEncodingSpec);
            serializeFunction.setSerializeEncoding(encodingSpec);
        }
        serializeFunction.setSerializeVersion(aVersion);
        serializeFunction.setDeclarationOption(XMLDeclarationType.get(aDeclOption));
        return serializeFunction;
    }
}
