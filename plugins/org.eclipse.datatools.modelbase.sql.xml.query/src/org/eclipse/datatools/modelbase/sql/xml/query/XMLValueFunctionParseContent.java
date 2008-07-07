/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionParseContent.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Parse Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueFunctionParse <em>Value Function Parse</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionParseContent()
 * @model
 * @generated
 */
public interface XMLValueFunctionParseContent extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Value Function Parse</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getParseContent <em>Parse Content</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Parse</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Parse</em>' container reference.
     * @see #setValueFunctionParse(XMLValueFunctionParse)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionParseContent_ValueFunctionParse()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getParseContent
     * @model opposite="parseContent" required="true"
     * @generated
     */
    XMLValueFunctionParse getValueFunctionParse();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueFunctionParse <em>Value Function Parse</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Parse</em>' container reference.
     * @see #getValueFunctionParse()
     * @generated
     */
    void setValueFunctionParse(XMLValueFunctionParse value);

	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionParseContent_ValueExpr()
     * @model containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // XMLValueFunctionParseContent
