/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLPredicateExists.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Predicate Exists</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Tests whether or not a given XQuery expression represents an non-empty sequence.  See ISO SQL/XML sec. 8.4.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryExpr <em>Xquery Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryArgList <em>Xquery Arg List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLPredicateExists()
 * @model
 * @generated
 */
public interface XMLPredicateExists extends XMLPredicate{
	/**
     * Returns the value of the '<em><b>Xquery Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getPredicateExists <em>Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xquery Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xquery Expr</em>' containment reference.
     * @see #setXqueryExpr(XMLQueryExpression)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLPredicateExists_XqueryExpr()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getPredicateExists
     * @model opposite="predicateExists" containment="true" required="true"
     * @generated
     */
    XMLQueryExpression getXqueryExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryExpr <em>Xquery Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xquery Expr</em>' containment reference.
     * @see #getXqueryExpr()
     * @generated
     */
    void setXqueryExpr(XMLQueryExpression value);

	/**
     * Returns the value of the '<em><b>Xquery Arg List</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPredicateExists <em>Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xquery Arg List</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xquery Arg List</em>' containment reference.
     * @see #setXqueryArgList(XMLQueryArgumentList)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLPredicateExists_XqueryArgList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPredicateExists
     * @model opposite="predicateExists" containment="true"
     * @generated
     */
    XMLQueryArgumentList getXqueryArgList();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryArgList <em>Xquery Arg List</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xquery Arg List</em>' containment reference.
     * @see #getXqueryArgList()
     * @generated
     */
    void setXqueryArgList(XMLQueryArgumentList value);

} // XMLPredicateExists
