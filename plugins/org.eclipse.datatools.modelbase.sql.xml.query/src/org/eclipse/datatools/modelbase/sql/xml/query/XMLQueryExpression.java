/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLQueryExpression.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Query Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents an XQuery expression.  The content of this element is a literal string which specifies an XML Query.  See ISO SQL/XML sec. 6.17.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getXqueryExprContent <em>Xquery Expr Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getValueFunctionQuery <em>Value Function Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryExpression()
 * @model
 * @generated
 */
public interface XMLQueryExpression extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Xquery Expr Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xquery Expr Content</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xquery Expr Content</em>' attribute.
     * @see #setXqueryExprContent(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryExpression_XqueryExprContent()
     * @model
     * @generated
     */
    String getXqueryExprContent();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getXqueryExprContent <em>Xquery Expr Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xquery Expr Content</em>' attribute.
     * @see #getXqueryExprContent()
     * @generated
     */
    void setXqueryExprContent(String value);

	/**
     * Returns the value of the '<em><b>Predicate Exists</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryExpr <em>Xquery Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predicate Exists</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Predicate Exists</em>' container reference.
     * @see #setPredicateExists(XMLPredicateExists)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryExpression_PredicateExists()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryExpr
     * @model opposite="xqueryExpr" required="true"
     * @generated
     */
    XMLPredicateExists getPredicateExists();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getPredicateExists <em>Predicate Exists</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Predicate Exists</em>' container reference.
     * @see #getPredicateExists()
     * @generated
     */
    void setPredicateExists(XMLPredicateExists value);

	/**
     * Returns the value of the '<em><b>Value Function Query</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryExpr <em>Xquery Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Query</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Query</em>' container reference.
     * @see #setValueFunctionQuery(XMLValueFunctionQuery)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryExpression_ValueFunctionQuery()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryExpr
     * @model opposite="xqueryExpr" required="true"
     * @generated
     */
    XMLValueFunctionQuery getValueFunctionQuery();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getValueFunctionQuery <em>Value Function Query</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Query</em>' container reference.
     * @see #getValueFunctionQuery()
     * @generated
     */
    void setValueFunctionQuery(XMLValueFunctionQuery value);

} // XMLQueryExpression
