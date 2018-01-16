/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLQueryArgumentItem.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Query Argument Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a single XML Query Argument within an XML Query Argument list.  The name attribute, if used, indicates that  this argument is an XML Query variable.  The attached value expression must resolve to an XML datatype.  See ISO SQL/XML sec. 6.17.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getPassingMechanism <em>Passing Mechanism</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getXqueryArgList <em>Xquery Arg List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentItem()
 * @model
 * @generated
 */
public interface XMLQueryArgumentItem extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Passing Mechanism</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Passing Mechanism</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Passing Mechanism</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #setPassingMechanism(XMLPassingType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentItem_PassingMechanism()
     * @model
     * @generated
     */
    XMLPassingType getPassingMechanism();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getPassingMechanism <em>Passing Mechanism</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Passing Mechanism</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #getPassingMechanism()
     * @generated
     */
    void setPassingMechanism(XMLPassingType value);

	/**
     * Returns the value of the '<em><b>Xquery Arg List</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getXqueryArgListChildren <em>Xquery Arg List Children</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xquery Arg List</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xquery Arg List</em>' container reference.
     * @see #setXqueryArgList(XMLQueryArgumentList)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentItem_XqueryArgList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getXqueryArgListChildren
     * @model opposite="xqueryArgListChildren" required="true"
     * @generated
     */
    XMLQueryArgumentList getXqueryArgList();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getXqueryArgList <em>Xquery Arg List</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xquery Arg List</em>' container reference.
     * @see #getXqueryArgList()
     * @generated
     */
    void setXqueryArgList(XMLQueryArgumentList value);

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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentItem_ValueExpr()
     * @model containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // XMLQueryArgumentItem
