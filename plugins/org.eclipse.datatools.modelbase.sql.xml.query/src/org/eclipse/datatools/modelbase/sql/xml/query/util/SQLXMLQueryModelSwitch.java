/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLXMLQueryModelSwitch.java,v 1.1 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.util;



import java.util.List;

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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

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
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage
 * @generated
 */
public class SQLXMLQueryModelSwitch {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static SQLXMLQueryModelPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SQLXMLQueryModelSwitch() {
        if (modelPackage == null) {
            modelPackage = SQLXMLQueryModelPackage.eINSTANCE;
        }
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public Object doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch((EClass)eSuperTypes.get(0), theEObject);
        }
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_CONCAT: {
                XMLValueFunctionConcat xmlValueFunctionConcat = (XMLValueFunctionConcat)theEObject;
                Object result = caseXMLValueFunctionConcat(xmlValueFunctionConcat);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionConcat);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionConcat);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionConcat);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionConcat);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionConcat);
                if (result == null) result = caseValueExpression(xmlValueFunctionConcat);
                if (result == null) result = caseSQLObject(xmlValueFunctionConcat);
                if (result == null) result = caseENamedElement(xmlValueFunctionConcat);
                if (result == null) result = caseEModelElement(xmlValueFunctionConcat);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION: {
                XMLValueFunction xmlValueFunction = (XMLValueFunction)theEObject;
                Object result = caseXMLValueFunction(xmlValueFunction);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunction);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunction);
                if (result == null) result = caseQueryValueExpression(xmlValueFunction);
                if (result == null) result = caseSQLQueryObject(xmlValueFunction);
                if (result == null) result = caseValueExpression(xmlValueFunction);
                if (result == null) result = caseSQLObject(xmlValueFunction);
                if (result == null) result = caseENamedElement(xmlValueFunction);
                if (result == null) result = caseEModelElement(xmlValueFunction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_PREFIX: {
                XMLNamespaceDeclarationPrefix xmlNamespaceDeclarationPrefix = (XMLNamespaceDeclarationPrefix)theEObject;
                Object result = caseXMLNamespaceDeclarationPrefix(xmlNamespaceDeclarationPrefix);
                if (result == null) result = caseXMLNamespaceDeclarationItem(xmlNamespaceDeclarationPrefix);
                if (result == null) result = caseSQLQueryObject(xmlNamespaceDeclarationPrefix);
                if (result == null) result = caseSQLObject(xmlNamespaceDeclarationPrefix);
                if (result == null) result = caseENamedElement(xmlNamespaceDeclarationPrefix);
                if (result == null) result = caseEModelElement(xmlNamespaceDeclarationPrefix);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_DEFAULT: {
                XMLNamespaceDeclarationDefault xmlNamespaceDeclarationDefault = (XMLNamespaceDeclarationDefault)theEObject;
                Object result = caseXMLNamespaceDeclarationDefault(xmlNamespaceDeclarationDefault);
                if (result == null) result = caseXMLNamespaceDeclarationItem(xmlNamespaceDeclarationDefault);
                if (result == null) result = caseSQLQueryObject(xmlNamespaceDeclarationDefault);
                if (result == null) result = caseSQLObject(xmlNamespaceDeclarationDefault);
                if (result == null) result = caseENamedElement(xmlNamespaceDeclarationDefault);
                if (result == null) result = caseEModelElement(xmlNamespaceDeclarationDefault);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM: {
                XMLAttributeDeclarationItem xmlAttributeDeclarationItem = (XMLAttributeDeclarationItem)theEObject;
                Object result = caseXMLAttributeDeclarationItem(xmlAttributeDeclarationItem);
                if (result == null) result = caseQueryValueExpression(xmlAttributeDeclarationItem);
                if (result == null) result = caseSQLQueryObject(xmlAttributeDeclarationItem);
                if (result == null) result = caseValueExpression(xmlAttributeDeclarationItem);
                if (result == null) result = caseSQLObject(xmlAttributeDeclarationItem);
                if (result == null) result = caseENamedElement(xmlAttributeDeclarationItem);
                if (result == null) result = caseEModelElement(xmlAttributeDeclarationItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT: {
                XMLValueFunctionElement xmlValueFunctionElement = (XMLValueFunctionElement)theEObject;
                Object result = caseXMLValueFunctionElement(xmlValueFunctionElement);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionElement);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionElement);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionElement);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionElement);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionElement);
                if (result == null) result = caseValueExpression(xmlValueFunctionElement);
                if (result == null) result = caseSQLObject(xmlValueFunctionElement);
                if (result == null) result = caseENamedElement(xmlValueFunctionElement);
                if (result == null) result = caseEModelElement(xmlValueFunctionElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM: {
                XMLNamespaceDeclarationItem xmlNamespaceDeclarationItem = (XMLNamespaceDeclarationItem)theEObject;
                Object result = caseXMLNamespaceDeclarationItem(xmlNamespaceDeclarationItem);
                if (result == null) result = caseSQLQueryObject(xmlNamespaceDeclarationItem);
                if (result == null) result = caseSQLObject(xmlNamespaceDeclarationItem);
                if (result == null) result = caseENamedElement(xmlNamespaceDeclarationItem);
                if (result == null) result = caseEModelElement(xmlNamespaceDeclarationItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM: {
                XMLValueFunctionElementContentItem xmlValueFunctionElementContentItem = (XMLValueFunctionElementContentItem)theEObject;
                Object result = caseXMLValueFunctionElementContentItem(xmlValueFunctionElementContentItem);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionElementContentItem);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionElementContentItem);
                if (result == null) result = caseValueExpression(xmlValueFunctionElementContentItem);
                if (result == null) result = caseSQLObject(xmlValueFunctionElementContentItem);
                if (result == null) result = caseENamedElement(xmlValueFunctionElementContentItem);
                if (result == null) result = caseEModelElement(xmlValueFunctionElementContentItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST: {
                XMLValueFunctionForest xmlValueFunctionForest = (XMLValueFunctionForest)theEObject;
                Object result = caseXMLValueFunctionForest(xmlValueFunctionForest);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionForest);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionForest);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionForest);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionForest);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionForest);
                if (result == null) result = caseValueExpression(xmlValueFunctionForest);
                if (result == null) result = caseSQLObject(xmlValueFunctionForest);
                if (result == null) result = caseENamedElement(xmlValueFunctionForest);
                if (result == null) result = caseEModelElement(xmlValueFunctionForest);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT: {
                XMLValueFunctionComment xmlValueFunctionComment = (XMLValueFunctionComment)theEObject;
                Object result = caseXMLValueFunctionComment(xmlValueFunctionComment);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionComment);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionComment);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionComment);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionComment);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionComment);
                if (result == null) result = caseValueExpression(xmlValueFunctionComment);
                if (result == null) result = caseSQLObject(xmlValueFunctionComment);
                if (result == null) result = caseENamedElement(xmlValueFunctionComment);
                if (result == null) result = caseEModelElement(xmlValueFunctionComment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_DOCUMENT: {
                XMLValueFunctionDocument xmlValueFunctionDocument = (XMLValueFunctionDocument)theEObject;
                Object result = caseXMLValueFunctionDocument(xmlValueFunctionDocument);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionDocument);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionDocument);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionDocument);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionDocument);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionDocument);
                if (result == null) result = caseValueExpression(xmlValueFunctionDocument);
                if (result == null) result = caseSQLObject(xmlValueFunctionDocument);
                if (result == null) result = caseENamedElement(xmlValueFunctionDocument);
                if (result == null) result = caseEModelElement(xmlValueFunctionDocument);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE: {
                XMLValueFunctionParse xmlValueFunctionParse = (XMLValueFunctionParse)theEObject;
                Object result = caseXMLValueFunctionParse(xmlValueFunctionParse);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionParse);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionParse);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionParse);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionParse);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionParse);
                if (result == null) result = caseValueExpression(xmlValueFunctionParse);
                if (result == null) result = caseSQLObject(xmlValueFunctionParse);
                if (result == null) result = caseENamedElement(xmlValueFunctionParse);
                if (result == null) result = caseEModelElement(xmlValueFunctionParse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI: {
                XMLValueFunctionPI xmlValueFunctionPI = (XMLValueFunctionPI)theEObject;
                Object result = caseXMLValueFunctionPI(xmlValueFunctionPI);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionPI);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionPI);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionPI);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionPI);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionPI);
                if (result == null) result = caseValueExpression(xmlValueFunctionPI);
                if (result == null) result = caseSQLObject(xmlValueFunctionPI);
                if (result == null) result = caseENamedElement(xmlValueFunctionPI);
                if (result == null) result = caseEModelElement(xmlValueFunctionPI);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY: {
                XMLValueFunctionQuery xmlValueFunctionQuery = (XMLValueFunctionQuery)theEObject;
                Object result = caseXMLValueFunctionQuery(xmlValueFunctionQuery);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionQuery);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionQuery);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionQuery);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionQuery);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionQuery);
                if (result == null) result = caseValueExpression(xmlValueFunctionQuery);
                if (result == null) result = caseSQLObject(xmlValueFunctionQuery);
                if (result == null) result = caseENamedElement(xmlValueFunctionQuery);
                if (result == null) result = caseEModelElement(xmlValueFunctionQuery);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT: {
                XMLValueFunctionText xmlValueFunctionText = (XMLValueFunctionText)theEObject;
                Object result = caseXMLValueFunctionText(xmlValueFunctionText);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionText);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionText);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionText);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionText);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionText);
                if (result == null) result = caseValueExpression(xmlValueFunctionText);
                if (result == null) result = caseSQLObject(xmlValueFunctionText);
                if (result == null) result = caseENamedElement(xmlValueFunctionText);
                if (result == null) result = caseEModelElement(xmlValueFunctionText);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE: {
                XMLValueFunctionValidate xmlValueFunctionValidate = (XMLValueFunctionValidate)theEObject;
                Object result = caseXMLValueFunctionValidate(xmlValueFunctionValidate);
                if (result == null) result = caseXMLValueFunction(xmlValueFunctionValidate);
                if (result == null) result = caseValueExpressionFunction(xmlValueFunctionValidate);
                if (result == null) result = caseValueExpressionAtomic(xmlValueFunctionValidate);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionValidate);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionValidate);
                if (result == null) result = caseValueExpression(xmlValueFunctionValidate);
                if (result == null) result = caseSQLObject(xmlValueFunctionValidate);
                if (result == null) result = caseENamedElement(xmlValueFunctionValidate);
                if (result == null) result = caseEModelElement(xmlValueFunctionValidate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_EXPRESSION_CAST: {
                XMLValueExpressionCast xmlValueExpressionCast = (XMLValueExpressionCast)theEObject;
                Object result = caseXMLValueExpressionCast(xmlValueExpressionCast);
                if (result == null) result = caseValueExpressionCast(xmlValueExpressionCast);
                if (result == null) result = caseValueExpressionAtomic(xmlValueExpressionCast);
                if (result == null) result = caseQueryValueExpression(xmlValueExpressionCast);
                if (result == null) result = caseSQLQueryObject(xmlValueExpressionCast);
                if (result == null) result = caseValueExpression(xmlValueExpressionCast);
                if (result == null) result = caseSQLObject(xmlValueExpressionCast);
                if (result == null) result = caseENamedElement(xmlValueExpressionCast);
                if (result == null) result = caseEModelElement(xmlValueExpressionCast);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_PREDICATE: {
                XMLPredicate xmlPredicate = (XMLPredicate)theEObject;
                Object result = caseXMLPredicate(xmlPredicate);
                if (result == null) result = casePredicate(xmlPredicate);
                if (result == null) result = caseQuerySearchCondition(xmlPredicate);
                if (result == null) result = caseSQLQueryObject(xmlPredicate);
                if (result == null) result = caseSearchCondition(xmlPredicate);
                if (result == null) result = caseSQLObject(xmlPredicate);
                if (result == null) result = caseENamedElement(xmlPredicate);
                if (result == null) result = caseEModelElement(xmlPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_PREDICATE_CONTENT: {
                XMLPredicateContent xmlPredicateContent = (XMLPredicateContent)theEObject;
                Object result = caseXMLPredicateContent(xmlPredicateContent);
                if (result == null) result = caseXMLPredicate(xmlPredicateContent);
                if (result == null) result = casePredicate(xmlPredicateContent);
                if (result == null) result = caseQuerySearchCondition(xmlPredicateContent);
                if (result == null) result = caseSQLQueryObject(xmlPredicateContent);
                if (result == null) result = caseSearchCondition(xmlPredicateContent);
                if (result == null) result = caseSQLObject(xmlPredicateContent);
                if (result == null) result = caseENamedElement(xmlPredicateContent);
                if (result == null) result = caseEModelElement(xmlPredicateContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_PREDICATE_DOCUMENT: {
                XMLPredicateDocument xmlPredicateDocument = (XMLPredicateDocument)theEObject;
                Object result = caseXMLPredicateDocument(xmlPredicateDocument);
                if (result == null) result = caseXMLPredicate(xmlPredicateDocument);
                if (result == null) result = casePredicate(xmlPredicateDocument);
                if (result == null) result = caseQuerySearchCondition(xmlPredicateDocument);
                if (result == null) result = caseSQLQueryObject(xmlPredicateDocument);
                if (result == null) result = caseSearchCondition(xmlPredicateDocument);
                if (result == null) result = caseSQLObject(xmlPredicateDocument);
                if (result == null) result = caseENamedElement(xmlPredicateDocument);
                if (result == null) result = caseEModelElement(xmlPredicateDocument);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS: {
                XMLPredicateExists xmlPredicateExists = (XMLPredicateExists)theEObject;
                Object result = caseXMLPredicateExists(xmlPredicateExists);
                if (result == null) result = caseXMLPredicate(xmlPredicateExists);
                if (result == null) result = casePredicate(xmlPredicateExists);
                if (result == null) result = caseQuerySearchCondition(xmlPredicateExists);
                if (result == null) result = caseSQLQueryObject(xmlPredicateExists);
                if (result == null) result = caseSearchCondition(xmlPredicateExists);
                if (result == null) result = caseSQLObject(xmlPredicateExists);
                if (result == null) result = caseENamedElement(xmlPredicateExists);
                if (result == null) result = caseEModelElement(xmlPredicateExists);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_PREDICATE_VALID: {
                XMLPredicateValid xmlPredicateValid = (XMLPredicateValid)theEObject;
                Object result = caseXMLPredicateValid(xmlPredicateValid);
                if (result == null) result = caseXMLPredicate(xmlPredicateValid);
                if (result == null) result = casePredicate(xmlPredicateValid);
                if (result == null) result = caseQuerySearchCondition(xmlPredicateValid);
                if (result == null) result = caseSQLQueryObject(xmlPredicateValid);
                if (result == null) result = caseSearchCondition(xmlPredicateValid);
                if (result == null) result = caseSQLObject(xmlPredicateValid);
                if (result == null) result = caseENamedElement(xmlPredicateValid);
                if (result == null) result = caseEModelElement(xmlPredicateValid);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION: {
                XMLQueryExpression xmlQueryExpression = (XMLQueryExpression)theEObject;
                Object result = caseXMLQueryExpression(xmlQueryExpression);
                if (result == null) result = caseSQLQueryObject(xmlQueryExpression);
                if (result == null) result = caseSQLObject(xmlQueryExpression);
                if (result == null) result = caseENamedElement(xmlQueryExpression);
                if (result == null) result = caseEModelElement(xmlQueryExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST: {
                XMLQueryArgumentList xmlQueryArgumentList = (XMLQueryArgumentList)theEObject;
                Object result = caseXMLQueryArgumentList(xmlQueryArgumentList);
                if (result == null) result = caseSQLQueryObject(xmlQueryArgumentList);
                if (result == null) result = caseSQLObject(xmlQueryArgumentList);
                if (result == null) result = caseENamedElement(xmlQueryArgumentList);
                if (result == null) result = caseEModelElement(xmlQueryArgumentList);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_ITEM: {
                XMLQueryArgumentItem xmlQueryArgumentItem = (XMLQueryArgumentItem)theEObject;
                Object result = caseXMLQueryArgumentItem(xmlQueryArgumentItem);
                if (result == null) result = caseQueryValueExpression(xmlQueryArgumentItem);
                if (result == null) result = caseSQLQueryObject(xmlQueryArgumentItem);
                if (result == null) result = caseValueExpression(xmlQueryArgumentItem);
                if (result == null) result = caseSQLObject(xmlQueryArgumentItem);
                if (result == null) result = caseENamedElement(xmlQueryArgumentItem);
                if (result == null) result = caseEModelElement(xmlQueryArgumentItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION: {
                XMLSerializeFunction xmlSerializeFunction = (XMLSerializeFunction)theEObject;
                Object result = caseXMLSerializeFunction(xmlSerializeFunction);
                if (result == null) result = caseValueExpressionFunction(xmlSerializeFunction);
                if (result == null) result = caseValueExpressionAtomic(xmlSerializeFunction);
                if (result == null) result = caseQueryValueExpression(xmlSerializeFunction);
                if (result == null) result = caseSQLQueryObject(xmlSerializeFunction);
                if (result == null) result = caseValueExpression(xmlSerializeFunction);
                if (result == null) result = caseSQLObject(xmlSerializeFunction);
                if (result == null) result = caseENamedElement(xmlSerializeFunction);
                if (result == null) result = caseEModelElement(xmlSerializeFunction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_TARGET: {
                XMLSerializeFunctionTarget xmlSerializeFunctionTarget = (XMLSerializeFunctionTarget)theEObject;
                Object result = caseXMLSerializeFunctionTarget(xmlSerializeFunctionTarget);
                if (result == null) result = caseQueryValueExpression(xmlSerializeFunctionTarget);
                if (result == null) result = caseSQLQueryObject(xmlSerializeFunctionTarget);
                if (result == null) result = caseValueExpression(xmlSerializeFunctionTarget);
                if (result == null) result = caseSQLObject(xmlSerializeFunctionTarget);
                if (result == null) result = caseENamedElement(xmlSerializeFunctionTarget);
                if (result == null) result = caseEModelElement(xmlSerializeFunctionTarget);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_AGGREGATE_FUNCTION: {
                XMLAggregateFunction xmlAggregateFunction = (XMLAggregateFunction)theEObject;
                Object result = caseXMLAggregateFunction(xmlAggregateFunction);
                if (result == null) result = caseValueExpressionFunction(xmlAggregateFunction);
                if (result == null) result = caseValueExpressionAtomic(xmlAggregateFunction);
                if (result == null) result = caseQueryValueExpression(xmlAggregateFunction);
                if (result == null) result = caseSQLQueryObject(xmlAggregateFunction);
                if (result == null) result = caseValueExpression(xmlAggregateFunction);
                if (result == null) result = caseSQLObject(xmlAggregateFunction);
                if (result == null) result = caseENamedElement(xmlAggregateFunction);
                if (result == null) result = caseEModelElement(xmlAggregateFunction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM: {
                XMLValueFunctionConcatContentItem xmlValueFunctionConcatContentItem = (XMLValueFunctionConcatContentItem)theEObject;
                Object result = caseXMLValueFunctionConcatContentItem(xmlValueFunctionConcatContentItem);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionConcatContentItem);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionConcatContentItem);
                if (result == null) result = caseValueExpression(xmlValueFunctionConcatContentItem);
                if (result == null) result = caseSQLObject(xmlValueFunctionConcatContentItem);
                if (result == null) result = caseENamedElement(xmlValueFunctionConcatContentItem);
                if (result == null) result = caseEModelElement(xmlValueFunctionConcatContentItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT_CONTENT: {
                XMLValueFunctionCommentContent xmlValueFunctionCommentContent = (XMLValueFunctionCommentContent)theEObject;
                Object result = caseXMLValueFunctionCommentContent(xmlValueFunctionCommentContent);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionCommentContent);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionCommentContent);
                if (result == null) result = caseValueExpression(xmlValueFunctionCommentContent);
                if (result == null) result = caseSQLObject(xmlValueFunctionCommentContent);
                if (result == null) result = caseENamedElement(xmlValueFunctionCommentContent);
                if (result == null) result = caseEModelElement(xmlValueFunctionCommentContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_DOCUMENT_CONTENT: {
                XMLValueFunctionDocumentContent xmlValueFunctionDocumentContent = (XMLValueFunctionDocumentContent)theEObject;
                Object result = caseXMLValueFunctionDocumentContent(xmlValueFunctionDocumentContent);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionDocumentContent);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionDocumentContent);
                if (result == null) result = caseValueExpression(xmlValueFunctionDocumentContent);
                if (result == null) result = caseSQLObject(xmlValueFunctionDocumentContent);
                if (result == null) result = caseENamedElement(xmlValueFunctionDocumentContent);
                if (result == null) result = caseEModelElement(xmlValueFunctionDocumentContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION: {
                XMLAggregateSortSpecification xmlAggregateSortSpecification = (XMLAggregateSortSpecification)theEObject;
                Object result = caseXMLAggregateSortSpecification(xmlAggregateSortSpecification);
                if (result == null) result = caseSQLQueryObject(xmlAggregateSortSpecification);
                if (result == null) result = caseSQLObject(xmlAggregateSortSpecification);
                if (result == null) result = caseENamedElement(xmlAggregateSortSpecification);
                if (result == null) result = caseEModelElement(xmlAggregateSortSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM: {
                XMLValueFunctionForestContentItem xmlValueFunctionForestContentItem = (XMLValueFunctionForestContentItem)theEObject;
                Object result = caseXMLValueFunctionForestContentItem(xmlValueFunctionForestContentItem);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionForestContentItem);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionForestContentItem);
                if (result == null) result = caseValueExpression(xmlValueFunctionForestContentItem);
                if (result == null) result = caseSQLObject(xmlValueFunctionForestContentItem);
                if (result == null) result = caseENamedElement(xmlValueFunctionForestContentItem);
                if (result == null) result = caseEModelElement(xmlValueFunctionForestContentItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE_CONTENT: {
                XMLValueFunctionParseContent xmlValueFunctionParseContent = (XMLValueFunctionParseContent)theEObject;
                Object result = caseXMLValueFunctionParseContent(xmlValueFunctionParseContent);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionParseContent);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionParseContent);
                if (result == null) result = caseValueExpression(xmlValueFunctionParseContent);
                if (result == null) result = caseSQLObject(xmlValueFunctionParseContent);
                if (result == null) result = caseENamedElement(xmlValueFunctionParseContent);
                if (result == null) result = caseEModelElement(xmlValueFunctionParseContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT: {
                XMLValueFunctionPIContent xmlValueFunctionPIContent = (XMLValueFunctionPIContent)theEObject;
                Object result = caseXMLValueFunctionPIContent(xmlValueFunctionPIContent);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionPIContent);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionPIContent);
                if (result == null) result = caseValueExpression(xmlValueFunctionPIContent);
                if (result == null) result = caseSQLObject(xmlValueFunctionPIContent);
                if (result == null) result = caseENamedElement(xmlValueFunctionPIContent);
                if (result == null) result = caseEModelElement(xmlValueFunctionPIContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION: {
                XMLTableFunction xmlTableFunction = (XMLTableFunction)theEObject;
                Object result = caseXMLTableFunction(xmlTableFunction);
                if (result == null) result = caseTableFunction(xmlTableFunction);
                if (result == null) result = caseTableExpression(xmlTableFunction);
                if (result == null) result = caseTableReference(xmlTableFunction);
                if (result == null) result = caseSQLQueryObject(xmlTableFunction);
                if (result == null) result = caseSQLObject(xmlTableFunction);
                if (result == null) result = caseENamedElement(xmlTableFunction);
                if (result == null) result = caseEModelElement(xmlTableFunction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT_CONTENT: {
                XMLValueFunctionTextContent xmlValueFunctionTextContent = (XMLValueFunctionTextContent)theEObject;
                Object result = caseXMLValueFunctionTextContent(xmlValueFunctionTextContent);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionTextContent);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionTextContent);
                if (result == null) result = caseValueExpression(xmlValueFunctionTextContent);
                if (result == null) result = caseSQLObject(xmlValueFunctionTextContent);
                if (result == null) result = caseENamedElement(xmlValueFunctionTextContent);
                if (result == null) result = caseEModelElement(xmlValueFunctionTextContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_CONTENT: {
                XMLValueFunctionValidateContent xmlValueFunctionValidateContent = (XMLValueFunctionValidateContent)theEObject;
                Object result = caseXMLValueFunctionValidateContent(xmlValueFunctionValidateContent);
                if (result == null) result = caseQueryValueExpression(xmlValueFunctionValidateContent);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionValidateContent);
                if (result == null) result = caseValueExpression(xmlValueFunctionValidateContent);
                if (result == null) result = caseSQLObject(xmlValueFunctionValidateContent);
                if (result == null) result = caseENamedElement(xmlValueFunctionValidateContent);
                if (result == null) result = caseEModelElement(xmlValueFunctionValidateContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM: {
                XMLTableColumnDefinitionItem xmlTableColumnDefinitionItem = (XMLTableColumnDefinitionItem)theEObject;
                Object result = caseXMLTableColumnDefinitionItem(xmlTableColumnDefinitionItem);
                if (result == null) result = caseSQLQueryObject(xmlTableColumnDefinitionItem);
                if (result == null) result = caseSQLObject(xmlTableColumnDefinitionItem);
                if (result == null) result = caseENamedElement(xmlTableColumnDefinitionItem);
                if (result == null) result = caseEModelElement(xmlTableColumnDefinitionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR: {
                XMLTableColumnDefinitionRegular xmlTableColumnDefinitionRegular = (XMLTableColumnDefinitionRegular)theEObject;
                Object result = caseXMLTableColumnDefinitionRegular(xmlTableColumnDefinitionRegular);
                if (result == null) result = caseXMLTableColumnDefinitionItem(xmlTableColumnDefinitionRegular);
                if (result == null) result = caseSQLQueryObject(xmlTableColumnDefinitionRegular);
                if (result == null) result = caseSQLObject(xmlTableColumnDefinitionRegular);
                if (result == null) result = caseENamedElement(xmlTableColumnDefinitionRegular);
                if (result == null) result = caseEModelElement(xmlTableColumnDefinitionRegular);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY: {
                XMLTableColumnDefinitionOrdinality xmlTableColumnDefinitionOrdinality = (XMLTableColumnDefinitionOrdinality)theEObject;
                Object result = caseXMLTableColumnDefinitionOrdinality(xmlTableColumnDefinitionOrdinality);
                if (result == null) result = caseXMLTableColumnDefinitionItem(xmlTableColumnDefinitionOrdinality);
                if (result == null) result = caseSQLQueryObject(xmlTableColumnDefinitionOrdinality);
                if (result == null) result = caseSQLObject(xmlTableColumnDefinitionOrdinality);
                if (result == null) result = caseENamedElement(xmlTableColumnDefinitionOrdinality);
                if (result == null) result = caseEModelElement(xmlTableColumnDefinitionOrdinality);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO: {
                XMLValueFunctionValidateAccordingTo xmlValueFunctionValidateAccordingTo = (XMLValueFunctionValidateAccordingTo)theEObject;
                Object result = caseXMLValueFunctionValidateAccordingTo(xmlValueFunctionValidateAccordingTo);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionValidateAccordingTo);
                if (result == null) result = caseSQLObject(xmlValueFunctionValidateAccordingTo);
                if (result == null) result = caseENamedElement(xmlValueFunctionValidateAccordingTo);
                if (result == null) result = caseEModelElement(xmlValueFunctionValidateAccordingTo);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI: {
                XMLValueFunctionValidateAccordingToURI xmlValueFunctionValidateAccordingToURI = (XMLValueFunctionValidateAccordingToURI)theEObject;
                Object result = caseXMLValueFunctionValidateAccordingToURI(xmlValueFunctionValidateAccordingToURI);
                if (result == null) result = caseXMLValueFunctionValidateAccordingTo(xmlValueFunctionValidateAccordingToURI);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionValidateAccordingToURI);
                if (result == null) result = caseSQLObject(xmlValueFunctionValidateAccordingToURI);
                if (result == null) result = caseENamedElement(xmlValueFunctionValidateAccordingToURI);
                if (result == null) result = caseEModelElement(xmlValueFunctionValidateAccordingToURI);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER: {
                XMLValueFunctionValidateAccordingToIdentifier xmlValueFunctionValidateAccordingToIdentifier = (XMLValueFunctionValidateAccordingToIdentifier)theEObject;
                Object result = caseXMLValueFunctionValidateAccordingToIdentifier(xmlValueFunctionValidateAccordingToIdentifier);
                if (result == null) result = caseXMLValueFunctionValidateAccordingTo(xmlValueFunctionValidateAccordingToIdentifier);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionValidateAccordingToIdentifier);
                if (result == null) result = caseSQLObject(xmlValueFunctionValidateAccordingToIdentifier);
                if (result == null) result = caseENamedElement(xmlValueFunctionValidateAccordingToIdentifier);
                if (result == null) result = caseEModelElement(xmlValueFunctionValidateAccordingToIdentifier);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME: {
                XMLValueFunctionValidateElementName xmlValueFunctionValidateElementName = (XMLValueFunctionValidateElementName)theEObject;
                Object result = caseXMLValueFunctionValidateElementName(xmlValueFunctionValidateElementName);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionValidateElementName);
                if (result == null) result = caseSQLObject(xmlValueFunctionValidateElementName);
                if (result == null) result = caseENamedElement(xmlValueFunctionValidateElementName);
                if (result == null) result = caseEModelElement(xmlValueFunctionValidateElementName);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE: {
                XMLValueFunctionValidateElementNamespace xmlValueFunctionValidateElementNamespace = (XMLValueFunctionValidateElementNamespace)theEObject;
                Object result = caseXMLValueFunctionValidateElementNamespace(xmlValueFunctionValidateElementNamespace);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionValidateElementNamespace);
                if (result == null) result = caseSQLObject(xmlValueFunctionValidateElementNamespace);
                if (result == null) result = caseENamedElement(xmlValueFunctionValidateElementNamespace);
                if (result == null) result = caseEModelElement(xmlValueFunctionValidateElementNamespace);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION: {
                XMLNamespacesDeclaration xmlNamespacesDeclaration = (XMLNamespacesDeclaration)theEObject;
                Object result = caseXMLNamespacesDeclaration(xmlNamespacesDeclaration);
                if (result == null) result = caseSQLQueryObject(xmlNamespacesDeclaration);
                if (result == null) result = caseSQLObject(xmlNamespacesDeclaration);
                if (result == null) result = caseENamedElement(xmlNamespacesDeclaration);
                if (result == null) result = caseEModelElement(xmlNamespacesDeclaration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_ATTRIBUTES_DECLARATION: {
                XMLAttributesDeclaration xmlAttributesDeclaration = (XMLAttributesDeclaration)theEObject;
                Object result = caseXMLAttributesDeclaration(xmlAttributesDeclaration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST: {
                XMLValueFunctionElementContentList xmlValueFunctionElementContentList = (XMLValueFunctionElementContentList)theEObject;
                Object result = caseXMLValueFunctionElementContentList(xmlValueFunctionElementContentList);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionElementContentList);
                if (result == null) result = caseSQLObject(xmlValueFunctionElementContentList);
                if (result == null) result = caseENamedElement(xmlValueFunctionElementContentList);
                if (result == null) result = caseEModelElement(xmlValueFunctionElementContentList);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING: {
                XMLValueFunctionQueryReturning xmlValueFunctionQueryReturning = (XMLValueFunctionQueryReturning)theEObject;
                Object result = caseXMLValueFunctionQueryReturning(xmlValueFunctionQueryReturning);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionQueryReturning);
                if (result == null) result = caseSQLObject(xmlValueFunctionQueryReturning);
                if (result == null) result = caseENamedElement(xmlValueFunctionQueryReturning);
                if (result == null) result = caseEModelElement(xmlValueFunctionQueryReturning);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT: {
                XMLValueFunctionValidateElement xmlValueFunctionValidateElement = (XMLValueFunctionValidateElement)theEObject;
                Object result = caseXMLValueFunctionValidateElement(xmlValueFunctionValidateElement);
                if (result == null) result = caseSQLQueryObject(xmlValueFunctionValidateElement);
                if (result == null) result = caseSQLObject(xmlValueFunctionValidateElement);
                if (result == null) result = caseENamedElement(xmlValueFunctionValidateElement);
                if (result == null) result = caseEModelElement(xmlValueFunctionValidateElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_DEFAULT: {
                XMLTableColumnDefinitionDefault xmlTableColumnDefinitionDefault = (XMLTableColumnDefinitionDefault)theEObject;
                Object result = caseXMLTableColumnDefinitionDefault(xmlTableColumnDefinitionDefault);
                if (result == null) result = caseQueryValueExpression(xmlTableColumnDefinitionDefault);
                if (result == null) result = caseSQLQueryObject(xmlTableColumnDefinitionDefault);
                if (result == null) result = caseValueExpression(xmlTableColumnDefinitionDefault);
                if (result == null) result = caseSQLObject(xmlTableColumnDefinitionDefault);
                if (result == null) result = caseENamedElement(xmlTableColumnDefinitionDefault);
                if (result == null) result = caseEModelElement(xmlTableColumnDefinitionDefault);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_ENCODING: {
                XMLSerializeFunctionEncoding xmlSerializeFunctionEncoding = (XMLSerializeFunctionEncoding)theEObject;
                Object result = caseXMLSerializeFunctionEncoding(xmlSerializeFunctionEncoding);
                if (result == null) result = caseSQLQueryObject(xmlSerializeFunctionEncoding);
                if (result == null) result = caseSQLObject(xmlSerializeFunctionEncoding);
                if (result == null) result = caseENamedElement(xmlSerializeFunctionEncoding);
                if (result == null) result = caseEModelElement(xmlSerializeFunctionEncoding);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Concat</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Concat</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionConcat(XMLValueFunctionConcat object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunction(XMLValueFunction object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Namespace Declaration Prefix</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Namespace Declaration Prefix</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLNamespaceDeclarationPrefix(XMLNamespaceDeclarationPrefix object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Namespace Declaration Default</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Namespace Declaration Default</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLNamespaceDeclarationDefault(XMLNamespaceDeclarationDefault object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Attribute Declaration Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Attribute Declaration Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLAttributeDeclarationItem(XMLAttributeDeclarationItem object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionElement(XMLValueFunctionElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Namespace Declaration Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Namespace Declaration Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLNamespaceDeclarationItem(XMLNamespaceDeclarationItem object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Element Content Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Element Content Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionElementContentItem(XMLValueFunctionElementContentItem object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Forest</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Forest</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionForest(XMLValueFunctionForest object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Comment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Comment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionComment(XMLValueFunctionComment object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Document</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Document</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionDocument(XMLValueFunctionDocument object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Parse</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Parse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionParse(XMLValueFunctionParse object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function PI</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function PI</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionPI(XMLValueFunctionPI object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionQuery(XMLValueFunctionQuery object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Text</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Text</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionText(XMLValueFunctionText object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Validate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Validate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionValidate(XMLValueFunctionValidate object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Expression Cast</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Expression Cast</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueExpressionCast(XMLValueExpressionCast object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLPredicate(XMLPredicate object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Predicate Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Predicate Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLPredicateContent(XMLPredicateContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Predicate Document</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Predicate Document</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLPredicateDocument(XMLPredicateDocument object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Predicate Exists</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Predicate Exists</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLPredicateExists(XMLPredicateExists object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Predicate Valid</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Predicate Valid</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLPredicateValid(XMLPredicateValid object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Query Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Query Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLQueryExpression(XMLQueryExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Query Argument List</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Query Argument List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLQueryArgumentList(XMLQueryArgumentList object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Query Argument Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Query Argument Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLQueryArgumentItem(XMLQueryArgumentItem object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Serialize Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Serialize Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLSerializeFunction(XMLSerializeFunction object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Serialize Function Target</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Serialize Function Target</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLSerializeFunctionTarget(XMLSerializeFunctionTarget object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Aggregate Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Aggregate Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLAggregateFunction(XMLAggregateFunction object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Concat Content Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Concat Content Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionConcatContentItem(XMLValueFunctionConcatContentItem object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Comment Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Comment Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionCommentContent(XMLValueFunctionCommentContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Document Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Document Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionDocumentContent(XMLValueFunctionDocumentContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Aggregate Sort Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Aggregate Sort Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLAggregateSortSpecification(XMLAggregateSortSpecification object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Forest Content Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Forest Content Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionForestContentItem(XMLValueFunctionForestContentItem object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Parse Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Parse Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionParseContent(XMLValueFunctionParseContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function PI Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function PI Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionPIContent(XMLValueFunctionPIContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Table Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Table Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLTableFunction(XMLTableFunction object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Text Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Text Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionTextContent(XMLValueFunctionTextContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Validate Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Validate Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionValidateContent(XMLValueFunctionValidateContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Table Column Definition Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Table Column Definition Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLTableColumnDefinitionItem(XMLTableColumnDefinitionItem object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Table Column Definition Regular</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Table Column Definition Regular</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLTableColumnDefinitionRegular(XMLTableColumnDefinitionRegular object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Table Column Definition Ordinality</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Table Column Definition Ordinality</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLTableColumnDefinitionOrdinality(XMLTableColumnDefinitionOrdinality object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Validate According To</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Validate According To</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionValidateAccordingTo(XMLValueFunctionValidateAccordingTo object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Validate According To URI</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Validate According To URI</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionValidateAccordingToURI(XMLValueFunctionValidateAccordingToURI object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Validate According To Identifier</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Validate According To Identifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionValidateAccordingToIdentifier(XMLValueFunctionValidateAccordingToIdentifier object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Validate Element Name</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Validate Element Name</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionValidateElementName(XMLValueFunctionValidateElementName object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Validate Element Namespace</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Validate Element Namespace</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionValidateElementNamespace(XMLValueFunctionValidateElementNamespace object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Namespaces Declaration</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Namespaces Declaration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLNamespacesDeclaration(XMLNamespacesDeclaration object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Attributes Declaration</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Attributes Declaration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLAttributesDeclaration(XMLAttributesDeclaration object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Element Content List</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Element Content List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionElementContentList(XMLValueFunctionElementContentList object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Query Returning</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Query Returning</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionQueryReturning(XMLValueFunctionQueryReturning object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Value Function Validate Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Value Function Validate Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLValueFunctionValidateElement(XMLValueFunctionValidateElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Table Column Definition Default</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Table Column Definition Default</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLTableColumnDefinitionDefault(XMLTableColumnDefinitionDefault object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Serialize Function Encoding</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Serialize Function Encoding</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXMLSerializeFunctionEncoding(XMLSerializeFunctionEncoding object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseEModelElement(EModelElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseENamedElement(ENamedElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>SQL Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLObject(SQLObject object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>SQL Query Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Query Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLQueryObject(SQLQueryObject object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpression(ValueExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Value Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Value Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryValueExpression(QueryValueExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Atomic</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Atomic</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionAtomic(ValueExpressionAtomic object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionFunction(ValueExpressionFunction object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Cast</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Cast</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCast(ValueExpressionCast object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Search Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Search Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSearchCondition(SearchCondition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Search Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Search Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQuerySearchCondition(QuerySearchCondition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicate(Predicate object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Reference</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableReference(TableReference object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableExpression(TableExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableFunction(TableFunction object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public Object defaultCase(EObject object) {
        return null;
    }

} //SQLXMLQuerySwitch
