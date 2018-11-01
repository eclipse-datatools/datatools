/*
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 */
package org.eclipse.datatools.modelbase.sql.xml.query.util;


import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceWriter;
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
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType;



/**
 * SQL source generator for XML extension to SQL Query model.
 * 
 * @author ckadner
 */
public class SQLXMLQuerySourceWriter extends SQLQuerySourceWriter {
    
    /**
     * String constant for {@link XMLContentType#CONTENT} and {@link XMLContentType2#CONTENT}
     */
    protected final  String XML_CONTENT_TYPE_CONTENT            = "CONTENT";

    /**
     * String constant for {@link XMLContentType#DOCUMENT} and {@link XMLContentType2#DOCUMENT}
     */
    protected final  String XML_CONTENT_TYPE_DOCUMENT           = "DOCUMENT";

    /**
     * String constant for {@link XMLContentType2#SEQUENCE}, value: "SEQUENCE"
     */
    protected final  String XML_CONTENT_TYPE_SEQUENCE           = "SEQUENCE";

        
    
 /*   *//**
     * String constant for {@link XMLNullHandlingType#ABSENT_ON_NULL}
     *//*
    protected final  String XML_CONTENT_OPTION_ABSENT_ON_NULL   = "ABSENT ON NULL";
    
    *//**
     * String constant for {@link XMLNullHandlingType#EMPTY_ON_NULL}
     *//*
    protected final  String XML_CONTENT_OPTION_EMPTY_ON_NULL    = "EMPTY ON NULL";
    
    *//**
     * String constant for {@link XMLNullHandlingType#NIL_ON_NO_CONTENT}
     *//*
    protected final  String XML_CONTENT_OPTION_NIL_ON_NO_CONTENT = "NIL ON NO CONTENT";

    *//**
     * String constant for {@link XMLNullHandlingType#NIL_ON_NULL}
     *//*
    protected final  String XML_CONTENT_OPTION_NIL_ON_NULL      = "NIL ON NULL";

    *//**
     * String constant for {@link XMLNullHandlingType#NULL_ON_NULL}
     *//*
    protected final  String XML_CONTENT_OPTION_NULL_ON_NULL     = "NULL ON NULL";
        
*/    /**
     * String constant for {@link XMLDeclarationType#INCLUDING_XMLDECLARATION}
     */
    protected final  String XML_DECLARATION_TYPE_INCLUDING      = "INCLUDING XMLDECLARATION";
    
    /**
     * String constant for {@link XMLDeclarationType#EXCLUDING_XMLDECLARATION}
     */
    protected final  String XML_DECLARATION_TYPE_EXCLUDING      = "EXCLUDING XMLDECLARATION";

    /**
     * String constant for {@link XMLEmptyHandlingType#EMPTY_ON_EMPTY}
     */
    protected final  String XML_EMPTY_HANDLING_TYPE_EMPTY_ON_EMPTY = "EMPTY ON EMPTY";
    
    /**
     * String constant for {@link XMLEmptyHandlingType#NULL_ON_EMPTY}
     */
    protected final  String XML_EMPTY_HANDLING_TYPE_NULL_ON_EMPTY = "NULL ON EMPTY";
    
    /**
     * String constant for {@link XMLNullHandlingType#ABSENT_ON_NULL}
     */
    protected final  String XML_NULL_HANDLING_TYPE_ABSENT_ON_NULL = "ABSENT ON NULL";

    /**
     * String constant for {@link XMLNullHandlingType#EMPTY_ON_NULL}
     */

    protected final  String XML_NULL_HANDLING_TYPE_EMPTY_ON_NULL = "EMPTY ON NULL";
    
    /**
     * String constant for {@link XMLNullHandlingType#NIL_ON_NO_CONTENT}
     */
    protected final  String XML_NULL_HANDLING_TYPE_NIL_ON_NO_CONTENT = "NIL ON NO CONTENT";

    /**
     * String constant for {@link XMLNullHandlingType#NIL_ON_NULL
     */
    protected final  String XML_NULL_HANDLING_TYPE_NIL_ON_NULL = "NIL ON NULL";
    
    /**
     * String constant for {@link XMLNullHandlingType#NULL_ON_NULL
     */
    protected final  String XML_NULL_HANDLING_TYPE_NULL_ON_NULL = "NULL ON NULL";
    
    /**
     * String constant for {@link XMLPassingType#BY_REF}
     */
    protected final  String BY_REF        = "BY REF";

    /**
     * String constant for {@link XMLPassingType#BY_VALUE}
     */
    protected final  String BY_VALUE      = "BY VALUE";
    
    /**
     * String constant for {@link XMLReturningType#RETURNING_CONTENT}
     */
    protected final  String XML_RETURNING_TYPE_CONTENT          = "RETURNING CONTENT";

    /**
     * String constant for {@link XMLReturningType#RETURNING_SEQUENCE}
     */
    protected final  String XML_RETURNING_TYPE_SEQUENCE         = "RETURNING SEQUENCE";

    /**
     * String constant for {@link XMLWhitespaceHandlingType#PRESERE_WHITESPACE}
     */
    protected final  String XML_WHITESPACE_PRESERVE             = "PRESERVE WHITESPACE";
    
    /**
     * String constant for {@link XMLWhitespaceHandlingType#STRIP_WHITESPACE}
     */
    protected final  String XML_WHITESPACE_STRIP                = "STRIP WHITESPACE";
    
    
    
    

    /** String constant, value: "ACCORDING TO XMLSCHEMA" */
    protected final  String ACCORDING_TO_XMLSCHEMA = "ACCORDING TO XMLSCHEMA"; 
   
    /** String constant, value: "COLUMNS" */
    protected final  String COLUMNS               = "COLUMNS"; 
    
    /** String constant, value: "CONTENT" */
    protected final  String CONTENT               = "CONTENT"; 

    /** String constant, value: "ELEMENT" */
    protected final  String ELEMENT               = "ELEMENT"; 

    /** String constant, value: "ENCODING" */
    protected final  String ENCODING              = "ENCODING"; 

    /** String constant, value: "FOR ORDINALITY" */
    protected final  String FOR_ORDINALITY        = "FOR ORDINALITY"; 

    /** String constant, value: "ID" */
    protected final  String ID                    = "ID"; 
    
    /** String constant "LOCATION" */
    protected final  String LOCATION              = "LOCATION";
    
    /** String constant "NAME" */
    protected final  String NAME                  = "NAME";

    /** String constant "NAMESPACE" */
    protected final  String NAMESPACE             = "NAMESPACE";

    /** String constant "NO DEFAULT" */
    protected final  String NO_DEFAULT            = "NO DEFAULT";
    
    /** String constant "NO NAMESPACE" */
    protected final  String NO_NAMESPACE          = "NO NAMESPACE";

    /** String constant "OPTION" */
    protected final  String OPTION                = "OPTION";

    /** String constant "PASSING" */
    protected final  String PASSING               = "PASSING";
    
    /** String constant "PATH" */
    protected final  String PATH                  = "PATH";

    /** String constant "RETURNING" */
    protected final  String RETURNING             = "RETURNING";
    
    /** String constant "URI" */
    protected final  String URI                   = "URI";
            
    /** String constant "VERSION" */
    protected final  String VERSION               = "VERSION";
    
    /** String constant, value: "XMLAGG" */
    protected final  String XMLAGG                = "XMLAGG"; 
 
    /** String constant, value: "XMLATTRIBUTES" */
    protected final  String XMLATTRIBUTES         = "XMLATTRIBUTES";

    /** String constant, value: "XMLCAST" */
    protected final  String XMLCAST               = "XMLCAST"; 
 
    /** String constant, value: "XMLCONCAT" */
    protected final  String XMLCONCAT             = "XMLCONCAT"; 

    /** String constant, value: "XMLCOMMENT" */
    protected final  String XMLCOMMENT            = "XMLCOMMENT"; 

    /** String constant, value: "XMLELEMENT" */
    protected final  String XMLELEMENT            = "XMLELEMENT";

    /** String constant, value: "XMLEXISTS" */
    protected final  String XMLEXISTS            = "XMLEXISTS";

    /** String constant, value: "XMLDOCUMENT" */
    protected final  String XMLDOCUMENT           = "XMLDOCUMENT";

    /** String constant, value: "XMLFOREST" */
    protected final  String XMLFOREST             = "XMLFOREST";
 
    /** String constant, value: "XMLNAMESPACES" */
    protected final  String XMLNAMESPACES         = "XMLNAMESPACES"; 
 
    /** String constant, value: "XMLPARSE" */
    protected final  String XMLPARSE              = "XMLPARSE";
    
    /** String constant, value: "XMLPI" */
    protected final  String XMLPI              = "XMLPI";

    /** String constant, value: "XMLQUERY" */
    protected final  String XMLQUERY              = "XMLQUERY";
    
    /** String constant, value: "XMLSERIALIZE" */
    protected final  String XMLSERIALIZE          = "XMLSERIALIZE";
    
    /** String constant, value: "XMLTABLE" */
    protected final  String XMLTABLE              = "XMLTABLE";
    
    /** String constant, value: "XMLTEXT" */
    protected final  String XMLTEXT               = "XMLTEXT";
    
    /** String constant, value: "XMLVALIDATE" */
    protected final  String XMLVALIDATE           = "XMLVALIDATE";

    
    /* ****************** XML Data Type ****************** */
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType#getSQL()
     */
    protected void appendSpecificSQL(XMLDataType dataType, StringBuffer sb)
    {
    	appendSpecificSQL(dataType.getPrimitiveType(), sb);
    }
    
    /* ****************** XML enumeration types ****************** */

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType#getSQL()
     */
    protected void appendSpecificSQL(XMLContentType contentType, StringBuffer sb) {
        int type = contentType.getValue();
        switch(type){
            case XMLContentType.CONTENT:
                sb.append(XML_CONTENT_TYPE_CONTENT);
                break;
            case XMLContentType.DOCUMENT:
                sb.append(XML_CONTENT_TYPE_DOCUMENT);
                break;
            default:
                break;
        }
    }


    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2#getSQL()
     */
    protected void appendSpecificSQL(XMLContentType2 contentType, StringBuffer sb) {
        int type = contentType.getValue();
        switch(type){
            case XMLContentType2.CONTENT:
                sb.append(XML_CONTENT_TYPE_CONTENT);
                break;
            case XMLContentType2.DOCUMENT:
                sb.append(XML_CONTENT_TYPE_DOCUMENT);
                break;
            case XMLContentType2.SEQUENCE:
                sb.append(XML_CONTENT_TYPE_SEQUENCE);
                break;
            default:
                break;
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType#getSQL()
     */
    protected void appendSpecificSQL(XMLDeclarationType declarationType, StringBuffer sb) {
        int type = declarationType.getValue();
        switch(type){
            case XMLDeclarationType.INCLUDING_XMLDECLARATION:
                sb.append(XML_DECLARATION_TYPE_INCLUDING);
                break;
            case XMLDeclarationType.EXCLUDING_XMLDECLARATION:
                sb.append(XML_DECLARATION_TYPE_EXCLUDING);
                break;
            default:
                break;
        }
    }
 
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XXMLEmptyHandlingType#getSQL()
     */
    protected void appendSpecificSQL(XMLEmptyHandlingType emptyHandlingType, StringBuffer sb) {
        int type = emptyHandlingType.getValue();
        switch(type){
            case XMLEmptyHandlingType.EMPTY_ON_EMPTY:
                sb.append(XML_EMPTY_HANDLING_TYPE_EMPTY_ON_EMPTY);
                break;
            case XMLEmptyHandlingType.NULL_ON_EMPTY:
                sb.append(XML_EMPTY_HANDLING_TYPE_NULL_ON_EMPTY);
                break;
            default:
                break;
        }
    }
 
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType#getSQL()
     */
    protected void appendSpecificSQL(XMLPassingType passingType, StringBuffer sb)
    {
        int type = passingType.getValue();
        switch(type){
            case(XMLPassingType.BY_REF):
                sb.append(BY_REF);
                break;
            case(XMLPassingType.BY_VALUE):
                sb.append(BY_VALUE);
                break;
            case(XMLPassingType.NONE):
                break;
            default:
                break;
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType#getSQL()
     */
    protected void appendSpecificSQL(XMLReturningType returningType, StringBuffer sb) {
        int type = returningType.getValue();
        switch (type) {
            case XMLReturningType.RETURNING_CONTENT:
                sb.append(XML_RETURNING_TYPE_CONTENT);
                break;
            case XMLReturningType.RETURNING_SEQUENCE:
                sb.append(XML_RETURNING_TYPE_SEQUENCE);
                break;
            default:
                break;
        }
        
    }
  
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType#getSQL()
     */
    protected void appendSpecificSQL(XMLNullHandlingType nullHandlingType, StringBuffer sb) {
        int type = nullHandlingType.getValue();
        switch (type) {
            case XMLNullHandlingType.ABSENT_ON_NULL:
                sb.append(XML_NULL_HANDLING_TYPE_ABSENT_ON_NULL);
                break;
            case XMLNullHandlingType.EMPTY_ON_NULL:
                sb.append(XML_NULL_HANDLING_TYPE_EMPTY_ON_NULL);
                break;
            case XMLNullHandlingType.NIL_ON_NO_CONTENT:
                sb.append(XML_NULL_HANDLING_TYPE_NIL_ON_NO_CONTENT);
                break;
            case XMLNullHandlingType.NIL_ON_NULL:
                sb.append(XML_NULL_HANDLING_TYPE_NIL_ON_NULL);
                break;
            case XMLNullHandlingType.NULL_ON_NULL:
                sb.append(XML_NULL_HANDLING_TYPE_NULL_ON_NULL);
                break;
            default:
                break;
        }
        
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType#getSQL()
     */
    protected void appendSpecificSQL(XMLWhitespaceHandlingType whiteSpaceHandlingType, StringBuffer sb) {
        int type = whiteSpaceHandlingType.getValue();
        switch(type){
            case XMLWhitespaceHandlingType.PRESERE_WHITESPACE:
                sb.append(XML_WHITESPACE_PRESERVE);
                break;
            case XMLWhitespaceHandlingType.STRIP_WHITESPACE:
                sb.append(XML_WHITESPACE_STRIP);
                break;
            default:
                break;
        }
    }
  
    
    /* ****************** XML value functions ****************** */
    
    /**
     * @see com#ibm.db.models.sql.xml.query.XMLAggregateFunction#getSQL()
     */
    protected void appendSpecificSQL(XMLAggregateFunction aggFunction, StringBuffer sb) {
        appendKeyword(sb, XMLAGG);
        appendSymbol(sb, PAREN_LEFT);
        appendSQLForSQLObjectList(aggFunction.getParameterList(),sb);
        
        /* Add the optional ORDER BY list. */
        List sortSpecList = aggFunction.getSortSpecList();
        if (sortSpecList != null && !sortSpecList.isEmpty()){
            appendSpace(sb);
            appendKeyword(sb, ORDER_BY);
            appendSpace(sb);
            appendSQLForSQLObjectList(aggFunction.getSortSpecList(),sb);
        }
        
        /* The RETURNING clause is also optional, so only add it when the Returning Type is something 
         * other than NONE. */
        XMLReturningType type = aggFunction.getReturningOption();
        if (type != XMLReturningType.NONE_LITERAL) {
            appendSpace(sb);
            appendSpecificSQL(type,sb);
        }
        appendSymbol(sb, PAREN_RIGHT);
    }
    
    /**
     * @see com#ibm.db.models.sql.xml.query.XMLAggregateSortSpecification#getSQL()
     */
    protected void appendSpecificSQL(XMLAggregateSortSpecification sortSpec, StringBuffer sb) {
        appendSQL(sortSpec.getOrderBySpec(),sb);
    }


    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getSQL()
     */
    protected void appendSpecificSQL(XMLAttributesDeclaration attributesDecl, StringBuffer sb) {
        List list =  attributesDecl.getAttributeDeclItem();
        if(!list.isEmpty()){
            sb.append(XMLATTRIBUTES);
            sb.append(PAREN_LEFT);
            appendSQLForSQLObjectList(list, sb);
            sb.append(PAREN_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getSQL()
     */
    protected void appendSpecificSQL(XMLAttributeDeclarationItem attributesDeclItem, StringBuffer sb) {
        appendSQL(attributesDeclItem.getValueExpr(),sb);
        if(attributesDeclItem.getName()!= null){
            sb.append(SPACE);
            sb.append(AS);
            sb.append(SPACE);
            sb.append(attributesDeclItem.getName());
        }
    }
    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionElement elementFunction, StringBuffer sb) {
        int indent = getLastLineIndent(sb)+2;
        
        // break onto new line if necessary
        if (!isLastLineEmpty(sb)) {
            sb.append(NEW_LINE);
            appendSpace(sb, indent);
        }
        
        sb.append(XMLELEMENT);
        sb.append(SPACE);
        sb.append(PAREN_LEFT);
        sb.append(NAME);
        sb.append(SPACE);
        sb.append(elementFunction.getElementName());
        
        if(elementFunction.getNamespacesDecl() != null){
            sb.append(COMMA);
            appendWithConditionalLineBreaks(elementFunction.getNamespacesDecl(), sb, indent, displayWidth);
        }
        
        if(elementFunction.getAttributesDecl() != null){
            sb.append(COMMA);
            sb.append(SPACE);
            appendSpecificSQL(elementFunction.getAttributesDecl(),sb);
        }
        
        XMLValueFunctionElementContentList elementContentList = elementFunction.getElementContentList();
        if(elementContentList != null){
	        appendSQL(elementContentList,sb);
        }

        XMLReturningType returningOption = elementFunction.getReturningOption();
        if(returningOption != XMLReturningType.NONE_LITERAL){
        	sb.append(SPACE);
        	appendSpecificSQL(returningOption,sb);
        }
        sb.append(PAREN_RIGHT);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLExists#getSQL()
     */
    protected void appendSpecificSQL(XMLPredicateExists existsPredicate, StringBuffer sb) {
    	sb.append(XMLEXISTS);
    	sb.append(PAREN_LEFT);
    	XMLQueryExpression queryExpr = existsPredicate.getXqueryExpr();
    	appendSQL(queryExpr,sb);
    	XMLQueryArgumentList argList = existsPredicate.getXqueryArgList();
    	if(argList != null){
    		sb.append(SPACE);
    		appendSQL(argList,sb);
    	}
    	sb.append(PAREN_RIGHT);
    	
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionForest forestFunction, StringBuffer sb) {
    	sb.append(XMLFOREST);
    	sb.append(PAREN_LEFT);

    	XMLNamespacesDeclaration namespacesDecl = forestFunction.getNamespacesDecl();
    	if(namespacesDecl != null){
    		sb.append(SPACE);
    		appendSQL(namespacesDecl,sb);
            sb.append(COMMA);
            sb.append(SPACE);
    	}
    	List  contentItemList = forestFunction.getForestContentList();
    	appendSQLForSQLObjectList(contentItemList,sb);

    	XMLNullHandlingType nullHandlingType = forestFunction.getNullHandlingOption();
    	if(nullHandlingType != XMLNullHandlingType.NONE_LITERAL){
    		sb.append(SPACE);
    		sb.append(OPTION);
    		sb.append(SPACE);
    		appendSpecificSQL(nullHandlingType,sb);
    	}
    	
    	XMLReturningType returningType = forestFunction.getReturningOption();
    	if(returningType != XMLReturningType.NONE_LITERAL){
            sb.append(SPACE);
            appendSpecificSQL(returningType, sb);
    	}
    	sb.append(PAREN_RIGHT);
    }   

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionForestContentItem forestContentItem, StringBuffer sb) {
    	appendSQL(forestContentItem.getValueExpr(),sb);
    	String name = forestContentItem.getName();
    	if(name != null){
    		sb.append(SPACE);
    		sb.append(AS);
    		sb.append(SPACE);
    		sb.append(name);
    	}
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getSQL()
     */
    protected void appendSpecificSQL(XMLNamespacesDeclaration namespaceDecl, StringBuffer sb) {
            List declItemList = namespaceDecl.getNamespaceDecltemList();
            if(!declItemList.isEmpty()){
                sb.append(XMLNAMESPACES);
                sb.append(PAREN_LEFT);
                appendSQLForSQLObjectList(declItemList, sb);
                sb.append(PAREN_RIGHT);
            }
    }

    //TODO DOC: in SourceWriter never work with instanceof in appendSpecificSQL
    //          methods with general SQLObject types, cause the reflection will
    //          allways look for methods with the specific SQLQueryObject type
    //          e.g. don't:   appendSpecificSQL(XMLNamespaceDeclarationItem item...) {
    //                          ...if (item instanceof XMLNamespaceDeclarationItemDefault) ...},
    //               instead: appendSpecificSQL(XMLNamespaceDeclarationItemDefault...)
    //               and:     appendSpecificSQL(XMLNamespaceDeclarationItemPrefix...)
    /**
     * @deprecated used instead {@link }
     */
    protected void appendSpecificSQL(XMLNamespaceDeclarationItem namespaceDecItem, StringBuffer sb) {
        if(namespaceDecItem instanceof XMLNamespaceDeclarationPrefix){
            sb.append(namespaceDecItem.getUri());
            sb.append(SPACE);
            sb.append(AS);
            sb.append(SPACE);
            sb.append(((XMLNamespaceDeclarationPrefix)namespaceDecItem).getPrefix());
        }
        else if(namespaceDecItem instanceof XMLNamespaceDeclarationDefault){
            if(((XMLNamespaceDeclarationDefault)namespaceDecItem).isNoDefault()){
                sb.append(DEFAULT);
                sb.append(SPACE);
                sb.append(namespaceDecItem.getUri());
            }
            else{
                sb.append(NO_DEFAULT);
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault#getSQL()
     */
    protected void appendSpecificSQL(XMLNamespaceDeclarationDefault namespaceDefault, StringBuffer sb) {
        if (!namespaceDefault.isNoDefault()) {
            sb.append(DEFAULT);
            sb.append(SPACE);
            sb.append(namespaceDefault.getUri());
        }
        else{
            sb.append(NO_DEFAULT);
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix#getSQL()
     */
    protected void appendSpecificSQL(XMLNamespaceDeclarationPrefix namespacePrefix, StringBuffer sb) {
        sb.append(namespacePrefix.getUri());
        sb.append(SPACE);
        sb.append(AS);
        sb.append(SPACE);
        sb.append(namespacePrefix.getPrefix());
    }
    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionElementContentItem elementContent, StringBuffer sb) {
        QueryValueExpression expr = elementContent.getValueExpr();
        appendSQL(expr,sb);
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionElementContentList elementContentList, StringBuffer sb) {
        if(elementContentList != null){
            List contentListChildren = elementContentList.getElementContentListChildren();
            if(! contentListChildren.isEmpty()){
                sb.append(COMMA);
                sb.append(SPACE);
            	appendSQLForSQLObjectList(contentListChildren, sb);
            }
            XMLNullHandlingType nullType = elementContentList.getNullHandlingOption();
            if(nullType != XMLNullHandlingType.NONE_LITERAL){
            	sb.append(SPACE);
            	sb.append(OPTION);
            	sb.append(SPACE);
            	appendSpecificSQL(nullType,sb);
            }
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSQL()
     */
    protected void appendSpecificSQL(XMLSerializeFunction xmlSerializeFunction, StringBuffer sb)
    {
        DataType asDataType = xmlSerializeFunction.getDataType();
        String serializeVersion = xmlSerializeFunction.getSerializeVersion();
        XMLDeclarationType declType = xmlSerializeFunction.getDeclarationOption();
        XMLSerializeFunctionEncoding serializeEncoding = xmlSerializeFunction.getSerializeEncoding();

        int indent = getLastLineIndent(sb) + STANDARD_INDENT;
       
        sb.append(XMLSERIALIZE);
        sb.append(PAREN_LEFT);
        appendSpecificSQL(xmlSerializeFunction.getContentOption(), sb);
        
        indentOnNewLine(sb, indent);
        
        appendSQL(xmlSerializeFunction.getSerializeTarget(), sb);
        sb.append(SPACE);
        sb.append(AS);
        sb.append(SPACE);

        if (asDataType == null) {
            asDataType = xmlSerializeFunction.getSerializeTarget().getDataType();
        }
        appendSQL(asDataType,sb);

        indentOnNewLine(sb, indent);

        if (serializeEncoding != null && serializeEncoding.getEncodingName() != null) {
            sb.append(SPACE);
            sb.append(ENCODING);
            sb.append(SPACE);
            sb.append(serializeEncoding.getEncodingName());
        }
        
        if (serializeVersion != null) {
            sb.append(SPACE);
            sb.append(VERSION);
            sb.append(SPACE);
            sb.append(serializeVersion);
        }
        
        if (declType != null && declType != XMLDeclarationType.NONE_LITERAL) {
            sb.append(SPACE);
            appendSpecificSQL(declType, sb);
        }
        
        sb.append(PAREN_RIGHT);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSQL()
     */
    protected void appendSpecificSQL(XMLSerializeFunctionTarget xmlSerializeFunctionTarget, StringBuffer sb)
    {
        appendSQL(xmlSerializeFunctionTarget.getValueExpr(), sb);

    }

    
    
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getSQL()
     */
    protected void appendSpecificSQL(XMLQueryArgumentItem xQueryArgument, StringBuffer sb)
    {
        appendSQL(xQueryArgument.getValueExpr(), sb);
        String variableName = xQueryArgument.getName();
        if (variableName != null) {
            sb.append(SPACE);
            sb.append(AS);
            sb.append(SPACE);
            sb.append(variableName);
        }
        
        XMLPassingType passingMechanism = xQueryArgument.getPassingMechanism();
        if (passingMechanism != XMLPassingType.NONE_LITERAL) {
            sb.append(SPACE);
            appendSpecificSQL(passingMechanism, sb);
        }
    }    
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getSQL()
     */
    protected void appendSpecificSQL(XMLQueryArgumentList xQueryArgList, StringBuffer sb)
    {
        sb.append(PASSING);
        sb.append(SPACE);
        appendSpecificSQL(xQueryArgList.getPassingMechanism(), sb);
        sb.append(SPACE);
        appendSQLForSQLObjectList(xQueryArgList.getXqueryArgListChildren(), sb);
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getSQL()
     */
    protected void appendSpecificSQL(XMLQueryExpression queryExpr, StringBuffer sb)
    {
        sb.append(queryExpr.getXqueryExprContent());
    }    
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast#getSQL()
     */
    protected void appendSpecificSQL(XMLValueExpressionCast xmlCastExpr, StringBuffer sb)
    {
        StringBuffer sbCast = new StringBuffer();

        sbCast.append(XMLCAST);
        sbCast.append(SPACE);
        sbCast.append(PAREN_LEFT);
        appendSQL(xmlCastExpr.getValueExpr(),sbCast);
        sbCast.append(SPACE);
        sbCast.append(AS);
        sbCast.append(SPACE);
        appendSQL(xmlCastExpr.getDataType(),sbCast);
        XMLPassingType passingMechanism = xmlCastExpr.getPassingMechanism();
        if (passingMechanism != XMLPassingType.NONE_LITERAL) {
	        sbCast.append(SPACE);
	        appendSpecificSQL(xmlCastExpr.getPassingMechanism(),sbCast);
        }
        sbCast.append(PAREN_RIGHT);
        wrapSQL(xmlCastExpr, sbCast);
        sb.append(sbCast);


    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionComment commentFunction, StringBuffer sb)
    {
        sb.append(XMLCOMMENT);
        sb.append(PAREN_LEFT);
        XMLValueFunctionCommentContent content = commentFunction.getCommentContent();
        if(content != null){
            appendSQL(content.getValueExpr(),sb);
        }
        sb.append(SPACE);
        appendSpecificSQL(commentFunction.getReturningOption(),sb);
        sb.append(PAREN_RIGHT);
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionConcat concatFunction, StringBuffer sb)
    {
        sb.append(XMLCONCAT);
        sb.append(PAREN_LEFT);
        appendSQLForSQLObjectList(concatFunction.getConcatContentList(), sb);
        sb.append(SPACE);
        appendSpecificSQL(concatFunction.getReturningOption(),sb);
        sb.append(PAREN_RIGHT);
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionConcatContentItem concatItem, StringBuffer sb){
        appendSQL(concatItem.getValueExpr(),sb);
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionDocument documentFunction, StringBuffer sb){
    		sb.append(XMLDOCUMENT);
    		sb.append(PAREN_LEFT);
    		XMLValueFunctionDocumentContent docContent = documentFunction.getDocumentContent();
    		if(docContent != null){
    			appendSQL(docContent.getValueExpr(),sb);
    		}
            XMLReturningType returningType = documentFunction.getReturningOption();
            if (returningType != XMLReturningType.NONE_LITERAL) {
                sb.append(SPACE);
                appendSpecificSQL(returningType, sb);
            }
    		sb.append(PAREN_RIGHT);            
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionParse parseFunction, StringBuffer sb)
    {
        int indent = getLastLineIndent(sb) + STANDARD_INDENT;
        
        sb.append(XMLPARSE);
        sb.append(PAREN_LEFT);
        appendSpecificSQL(parseFunction.getContentOption(), sb);
        sb.append(SPACE);
        
        StringBuffer contentSB = new StringBuffer();
        appendSQL(parseFunction.getParseContent(), contentSB);
        indentSQL(contentSB, indent);
        sb.append(contentSB);
        
        XMLWhitespaceHandlingType whitespaceHandlingOption = parseFunction.getWhitespaceHandlingOption();
        if (whitespaceHandlingOption != XMLWhitespaceHandlingType.NONE_LITERAL) {
            indentOnNewLine(sb, indent);
            appendSpecificSQL(whitespaceHandlingOption,sb);
        }
        
        sb.append(PAREN_RIGHT);
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionPI piFunction, StringBuffer sb)
    {
    	sb.append(XMLPI);
    	sb.append(PAREN_LEFT);
    	sb.append(NAME);
    	sb.append(SPACE);
    	sb.append(piFunction.getTargetName());
    	
    	XMLValueFunctionPIContent piContent = piFunction.getPIContent();
    	if(piContent != null){
    		sb.append(COMMA);
    		sb.append(SPACE);
    		appendSQL(piContent.getValueExpr(),sb);
    	}
    	
    	XMLReturningType returningType = piFunction.getReturningOption();
    	if(returningType != XMLReturningType.NONE_LITERAL){
    		sb.append(SPACE);
    		appendSpecificSQL(returningType,sb);
    	}
    	sb.append(PAREN_RIGHT);
    	
    }
    
    /**
     * @see XMLValueFunctionParseContent#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionParseContent parseContent, StringBuffer sb)
    {
        appendSQL(parseContent.getValueExpr(), sb);
    }

    
    /**
     * @see XMLValueFunctionQuery#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionQuery queryFunction, StringBuffer sb)
    {
        sb.append(XMLQUERY);
        sb.append(PAREN_LEFT);
        appendSQL(queryFunction.getXqueryExpr(), sb);
        
//        XMLQUERY 
//        _LPAREN 
//        <xQuery_expression>
//        <opt_xml_query_argument_list> 
        
//        <opt_xml_returning_clause_opt_mechanism>
//        <xml_query_empty_handling_option>
//        _RPAREN

        XMLQueryArgumentList xqueryArgList = queryFunction.getXqueryArgList();
        if (xqueryArgList != null) {
            sb.append(SPACE);
            appendSQL(xqueryArgList, sb);
        }
        
        XMLValueFunctionQueryReturning returningClause = queryFunction.getQueryReturning();
        if (returningClause != null) {
            sb.append(SPACE);
            appendSQL(returningClause, sb);
        }
        
        XMLEmptyHandlingType emptyHandlingOption = queryFunction.getEmptyHandlingOption();
        if (emptyHandlingOption != XMLEmptyHandlingType.NONE_LITERAL) {
            sb.append(SPACE);
            appendSpecificSQL(emptyHandlingOption, sb);
        }
        
        sb.append(PAREN_RIGHT);
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionQueryReturning queryReturning, StringBuffer sb)
    {
        // RETURNING <content_or_sequence>
        appendSpecificSQL(queryReturning.getReturningOption(), sb);

        // PASSING BY <value_or_ref>
        XMLPassingType passingOption = queryReturning.getPassingOption();
        if (passingOption != XMLPassingType.NONE_LITERAL) {
            sb.append(SPACE);
            appendSpecificSQL(passingOption, sb);
        }
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getSQL()
     */
    protected void appendSpecificSQL(XMLTableColumnDefinitionDefault columnDefinitionDefault, StringBuffer sb)
    {
        sb.append(DEFAULT);
        sb.append(SPACE);
        appendSQL(columnDefinitionDefault.getValueExpr(), sb);
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionOrdinality#getSQL()
     */
    protected void appendSpecificSQL(XMLTableColumnDefinitionOrdinality columnDefinitionOrdinality, StringBuffer sb)
    {
        //<column_name> FOR ORDINALITY
        String columnName = columnDefinitionOrdinality.getName();
        columnName = StatementHelper.convertCatalogIdentifierToSQLFormat(columnName, getDelimitedIdentifierQuote());
        sb.append(columnName);
        sb.append(SPACE);
        sb.append(FOR_ORDINALITY);
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getSQL()
     */
    protected void appendSpecificSQL(XMLTableColumnDefinitionRegular columnDefinitionRegular, StringBuffer sb)
    {
        //<column_name> <datatype> <opt_value_or_ref> <opt_default_clause> <opt_xml_table_column_pattern>
        String columnName = columnDefinitionRegular.getName();
        columnName = StatementHelper.convertCatalogIdentifierToSQLFormat(columnName, getDelimitedIdentifierQuote());
        sb.append(columnName);
        sb.append(SPACE);
        appendSQL(columnDefinitionRegular.getDataType(), sb);
        
        XMLPassingType passingOption = columnDefinitionRegular.getPassingOption();
        if (passingOption != XMLPassingType.NONE_LITERAL) {
            sb.append(SPACE);
            appendSpecificSQL(passingOption, sb);
        }

        XMLTableColumnDefinitionDefault defaultClause = columnDefinitionRegular.getColumnDefinitionDefault();
        if (defaultClause != null) {
            sb.append(SPACE);
            appendSQL(defaultClause, sb);
        }
        
        String tableColumnPattern = columnDefinitionRegular.getTableColumnPattern();
        if (tableColumnPattern != null) {
            sb.append(SPACE);
            sb.append(PATH);
            sb.append(SPACE);
            sb.append(tableColumnPattern);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getSQL()
     */
    protected void appendSpecificSQL(XMLTableFunction tableFunction, StringBuffer sb)
    {
        // XMLTABLE _LPAREN  <opt_xml_namespace_declaration_commalast>
        //     <xml_table_row_pattern>
        //     <opt_xml_table_argument_list>
        //     COLUMNS <xml_table_column_definition_list>
        //     _RPAREN
        sb.append(XMLTABLE);
        sb.append(PAREN_LEFT);
        XMLNamespacesDeclaration namespacesDecl = tableFunction.getNamespacesDecl();
        if (namespacesDecl != null) {
            appendSQL(namespacesDecl, sb);
            sb.append(COMMA);
            sb.append(SPACE);
        }
        
        sb.append(tableFunction.getTableRowPattern());
        
        XMLQueryArgumentList xQueryArgList = tableFunction.getXqueryArgList();
        if (xQueryArgList != null) {
            sb.append(SPACE);
            appendSQL(xQueryArgList, sb);
        }
        
        sb.append(SPACE);
        sb.append(COLUMNS);
        sb.append(SPACE);
        appendSQLForSQLObjectList(tableFunction.getColumnDefList(), sb);
        
        sb.append(PAREN_RIGHT);
        
        if (tableFunction.getTableCorrelation() != null)
        {
            sb.append(SPACE);
            sb.append(AS);
            sb.append(SPACE);
            appendSQL(tableFunction.getTableCorrelation(),sb);
        }

    }    
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionText textFunction, StringBuffer sb)
    {
        // XMLTEXT _LPAREN <string_value_expression> <opt_xml_returning_clause> _RPAREN
        sb.append(XMLTEXT);
        sb.append(PAREN_LEFT);
        appendSQL(textFunction.getTextContent(), sb);

        XMLReturningType returningType = textFunction.getReturningOption();
        if (returningType != XMLReturningType.NONE_LITERAL) {
            sb.append(SPACE);
            appendSpecificSQL(returningType, sb);
        }
        sb.append(PAREN_RIGHT);
    }    
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionTextContent textContent, StringBuffer sb)
    {
        appendSQL(textContent.getValueExpr(), sb);
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionValidate validateFunction, StringBuffer sb)
    {
        int indent = getLastLineIndent(sb) + STANDARD_INDENT;
        
        sb.append(XMLVALIDATE);
        sb.append(PAREN_LEFT);
        appendSpecificSQL(validateFunction.getContentOption(), sb);
        
        sb.append(NEW_LINE);
        appendSpace(sb, indent);
        appendSQL(validateFunction.getValidateContent(), sb);
        
        if (validateFunction.getValidateAccordingTo() != null) {
            indentOnNewLine(sb, indent);
            appendSQL(validateFunction.getValidateAccordingTo(), sb);
        }
        sb.append(PAREN_RIGHT);
        
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionValidateAccordingToIdentifier accordingToIdentifier, StringBuffer sb)
    {
        sb.append(ACCORDING_TO_XMLSCHEMA);
        sb.append(SPACE);
        sb.append(ID);
        sb.append(SPACE);
        
        String schemaName = accordingToIdentifier.getSchemaName();
        String registeredXMLSchemaName = accordingToIdentifier.getRegisteredXMLSchemaName();        
        
        if (schemaName != null) {
            schemaName = StatementHelper.convertCatalogIdentifierToSQLFormat(schemaName, getDelimitedIdentifierQuote());
            sb.append(schemaName);
            sb.append(DOT);
        }
        registeredXMLSchemaName = StatementHelper.convertCatalogIdentifierToSQLFormat(registeredXMLSchemaName, getDelimitedIdentifierQuote());
        sb.append(registeredXMLSchemaName);
        
        XMLValueFunctionValidateElement optValidateElement = accordingToIdentifier.getValidateElement();
        if (optValidateElement != null) {
            sb.append(SPACE);
            appendSQL(optValidateElement, sb);
        }
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionValidateAccordingToURI accordingToURI, StringBuffer sb)
    {
        sb.append(ACCORDING_TO_XMLSCHEMA);
        sb.append(SPACE);
        if (accordingToURI.isNoNamespace()) {
            sb.append(NO_NAMESPACE);
        }
        else {
            sb.append(URI);
            sb.append(SPACE);
            sb.append(accordingToURI.getTargetNamespaceURI());
        }
        
        String schemaLocationURI = accordingToURI.getSchemaLocationURI();
        if (schemaLocationURI != null) {
            sb.append(SPACE);
            sb.append(LOCATION);
            sb.append(SPACE);
            sb.append(schemaLocationURI);
        }
        
        // ... <opt_xml_valid_element_clause>
        XMLValueFunctionValidateElement optValidateElement = accordingToURI.getValidateElement();
        if (optValidateElement != null) {
            sb.append(SPACE);
            appendSQL(optValidateElement, sb);
        }
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionValidateContent validateFunctionContent, StringBuffer sb)
    {
        appendSQL(validateFunctionContent.getValueExpr(), sb);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionValidateElement validateElement, StringBuffer sb)
    {
        XMLValueFunctionValidateElementNamespace validateElementNamespace = validateElement.getValidateElementNamespace();
        XMLValueFunctionValidateElementName validateElementName = validateElement.getValidateElementName();
        
        if (validateElementNamespace != null) {
            appendSpecificSQL(validateElementNamespace, sb);
            if (validateElementName != null) {
                sb.append(SPACE);
            }
        }
        
        if (validateElementName != null) {
            appendSQL(validateElementName, sb);
        }
        
    }

    /**
     * @see XMLValueFunctionValidateElementName#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionValidateElementName validateElementName, StringBuffer sb) {
        sb.append(ELEMENT);
        sb.append(SPACE);
        sb.append(validateElementName.getName());
    }    

    /**
     * @see XMLValueFunctionValidateElementNamespace#getSQL()
     */
    protected void appendSpecificSQL(XMLValueFunctionValidateElementNamespace validateElementNS, StringBuffer sb) {
        if (validateElementNS.isNoNamespace()) {
            sb.append(NO_NAMESPACE);
        }
        else {
            sb.append(NAMESPACE);
            sb.append(SPACE);
            sb.append(validateElementNS.getNamespaceURI());
        }
    }


}
