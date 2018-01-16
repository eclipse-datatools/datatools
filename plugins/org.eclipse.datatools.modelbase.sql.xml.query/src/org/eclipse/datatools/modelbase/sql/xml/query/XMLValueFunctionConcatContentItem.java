/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionConcatContentItem.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Concat Content Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents an XML value expression within an XMLCONCAT function.  See ISO SQL/XML sec. 6.11.  Contains a value expression which must resolve to an XML datatype.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getValueFunctionConcat <em>Value Function Concat</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionConcatContentItem()
 * @model
 * @generated
 */
public interface XMLValueFunctionConcatContentItem extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Value Function Concat</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat#getConcatContentList <em>Concat Content List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Concat</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Concat</em>' container reference.
     * @see #setValueFunctionConcat(XMLValueFunctionConcat)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionConcatContentItem_ValueFunctionConcat()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat#getConcatContentList
     * @model opposite="concatContentList" required="true"
     * @generated
     */
    XMLValueFunctionConcat getValueFunctionConcat();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getValueFunctionConcat <em>Value Function Concat</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Concat</em>' container reference.
     * @see #getValueFunctionConcat()
     * @generated
     */
    void setValueFunctionConcat(XMLValueFunctionConcat value);

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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionConcatContentItem_ValueExpr()
     * @model containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // XMLValueFunctionConcatContentItem
