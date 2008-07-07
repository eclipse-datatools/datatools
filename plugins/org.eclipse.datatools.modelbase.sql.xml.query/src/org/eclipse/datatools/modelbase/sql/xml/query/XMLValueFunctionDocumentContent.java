/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionDocumentContent.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Document Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents an <XML value expression> within an <XML document> function.  See ISO SQL/XML sec. 6.12.  The value expression contained by this element must resolve to an XML datatype.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent#getValueFunctionDocument <em>Value Function Document</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionDocumentContent()
 * @model
 * @generated
 */
public interface XMLValueFunctionDocumentContent extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Value Function Document</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument#getDocumentContent <em>Document Content</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Document</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Document</em>' container reference.
     * @see #setValueFunctionDocument(XMLValueFunctionDocument)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionDocumentContent_ValueFunctionDocument()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument#getDocumentContent
     * @model opposite="documentContent" required="true"
     * @generated
     */
    XMLValueFunctionDocument getValueFunctionDocument();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent#getValueFunctionDocument <em>Value Function Document</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Document</em>' container reference.
     * @see #getValueFunctionDocument()
     * @generated
     */
    void setValueFunctionDocument(XMLValueFunctionDocument value);

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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionDocumentContent_ValueExpr()
     * @model containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // XMLValueFunctionDocumentContent
