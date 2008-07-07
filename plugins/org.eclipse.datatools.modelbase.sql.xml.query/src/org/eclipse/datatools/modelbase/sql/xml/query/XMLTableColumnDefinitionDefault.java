/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableColumnDefinitionDefault.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Table Column Definition Default</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getColumnDefinitionRegular <em>Column Definition Regular</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionDefault()
 * @model
 * @generated
 */
public interface XMLTableColumnDefinitionDefault extends QueryValueExpression{
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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionDefault_ValueExpr()
     * @model containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Column Definition Regular</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getColumnDefinitionDefault <em>Column Definition Default</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column Definition Regular</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Column Definition Regular</em>' container reference.
     * @see #setColumnDefinitionRegular(XMLTableColumnDefinitionRegular)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionDefault_ColumnDefinitionRegular()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getColumnDefinitionDefault
     * @model opposite="columnDefinitionDefault" required="true"
     * @generated
     */
    XMLTableColumnDefinitionRegular getColumnDefinitionRegular();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getColumnDefinitionRegular <em>Column Definition Regular</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Definition Regular</em>' container reference.
     * @see #getColumnDefinitionRegular()
     * @generated
     */
    void setColumnDefinitionRegular(XMLTableColumnDefinitionRegular value);

} // XMLTableColumnDefinitionDefault
