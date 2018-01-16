/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLSerializeFunctionTarget.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Serialize Function Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getSerializeFunction <em>Serialize Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunctionTarget()
 * @model
 * @generated
 */
public interface XMLSerializeFunctionTarget extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Serialize Function</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeTarget <em>Serialize Target</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Serialize Function</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Serialize Function</em>' container reference.
     * @see #setSerializeFunction(XMLSerializeFunction)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunctionTarget_SerializeFunction()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeTarget
     * @model opposite="serializeTarget" required="true"
     * @generated
     */
    XMLSerializeFunction getSerializeFunction();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getSerializeFunction <em>Serialize Function</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Serialize Function</em>' container reference.
     * @see #getSerializeFunction()
     * @generated
     */
    void setSerializeFunction(XMLSerializeFunction value);

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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunctionTarget_ValueExpr()
     * @model containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // XMLSerializeFunctionTarget
