/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionQuery.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates an XML value as the result of evaluating a given XQuery expression.  See ISO SQL/XML sec. 6.17.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getEmptyHandlingOption <em>Empty Handling Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryExpr <em>Xquery Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryArgList <em>Xquery Arg List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getQueryReturning <em>Query Returning</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQuery()
 * @model
 * @generated
 */
public interface XMLValueFunctionQuery extends XMLValueFunction{
	/**
     * Returns the value of the '<em><b>Empty Handling Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Empty Handling Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Empty Handling Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType
     * @see #setEmptyHandlingOption(XMLEmptyHandlingType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQuery_EmptyHandlingOption()
     * @model
     * @generated
     */
    XMLEmptyHandlingType getEmptyHandlingOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getEmptyHandlingOption <em>Empty Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Empty Handling Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType
     * @see #getEmptyHandlingOption()
     * @generated
     */
    void setEmptyHandlingOption(XMLEmptyHandlingType value);

	/**
     * Returns the value of the '<em><b>Xquery Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getValueFunctionQuery <em>Value Function Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xquery Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xquery Expr</em>' containment reference.
     * @see #setXqueryExpr(XMLQueryExpression)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQuery_XqueryExpr()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getValueFunctionQuery
     * @model opposite="valueFunctionQuery" containment="true" required="true"
     * @generated
     */
    XMLQueryExpression getXqueryExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryExpr <em>Xquery Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xquery Expr</em>' containment reference.
     * @see #getXqueryExpr()
     * @generated
     */
    void setXqueryExpr(XMLQueryExpression value);

	/**
     * Returns the value of the '<em><b>Xquery Arg List</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getValueFunctionQuery <em>Value Function Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xquery Arg List</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xquery Arg List</em>' containment reference.
     * @see #setXqueryArgList(XMLQueryArgumentList)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQuery_XqueryArgList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getValueFunctionQuery
     * @model opposite="valueFunctionQuery" containment="true"
     * @generated
     */
    XMLQueryArgumentList getXqueryArgList();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryArgList <em>Xquery Arg List</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xquery Arg List</em>' containment reference.
     * @see #getXqueryArgList()
     * @generated
     */
    void setXqueryArgList(XMLQueryArgumentList value);

	/**
     * Returns the value of the '<em><b>Query Returning</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getValueFunctionQuery <em>Value Function Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Returning</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Returning</em>' containment reference.
     * @see #setQueryReturning(XMLValueFunctionQueryReturning)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQuery_QueryReturning()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getValueFunctionQuery
     * @model opposite="valueFunctionQuery" containment="true"
     * @generated
     */
    XMLValueFunctionQueryReturning getQueryReturning();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getQueryReturning <em>Query Returning</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Returning</em>' containment reference.
     * @see #getQueryReturning()
     * @generated
     */
    void setQueryReturning(XMLValueFunctionQueryReturning value);

} // XMLValueFunctionQuery
