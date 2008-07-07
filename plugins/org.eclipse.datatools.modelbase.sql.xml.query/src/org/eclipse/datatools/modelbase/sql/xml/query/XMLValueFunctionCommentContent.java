/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionCommentContent.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Comment Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents the <string value expression> within an <XML comment> function.  See ISO SQL/XML sec. 6.10.  Contains a value expression which must resolve to an string datatype.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueFunctionComment <em>Value Function Comment</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionCommentContent()
 * @model
 * @generated
 */
public interface XMLValueFunctionCommentContent extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Value Function Comment</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getCommentContent <em>Comment Content</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Comment</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Comment</em>' container reference.
     * @see #setValueFunctionComment(XMLValueFunctionComment)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionCommentContent_ValueFunctionComment()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getCommentContent
     * @model opposite="commentContent" required="true"
     * @generated
     */
    XMLValueFunctionComment getValueFunctionComment();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueFunctionComment <em>Value Function Comment</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Comment</em>' container reference.
     * @see #getValueFunctionComment()
     * @generated
     */
    void setValueFunctionComment(XMLValueFunctionComment value);

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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionCommentContent_ValueExpr()
     * @model containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // XMLValueFunctionCommentContent
