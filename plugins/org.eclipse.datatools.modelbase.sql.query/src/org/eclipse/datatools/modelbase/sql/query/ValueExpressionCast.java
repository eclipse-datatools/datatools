/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCast.java,v 1.6 2005/10/22 01:35:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Cast</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage#getValueExpressionCast()
 * @model
 * @generated
 */
public interface ValueExpressionCast extends ValueExpressionAtomic{
	/**
	 * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCast <em>Value Expr Cast</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Expr</em>' containment reference.
	 * @see #setValueExpr(QueryValueExpression)
	 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage#getValueExpressionCast_ValueExpr()
	 * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCast
	 * @model opposite="valueExprCast" containment="true" required="true"
	 * @generated
	 */
    QueryValueExpression getValueExpr();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast#getValueExpr <em>Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Expr</em>' containment reference.
	 * @see #getValueExpr()
	 * @generated
	 */
    void setValueExpr(QueryValueExpression value);

} // SQLValueExpressionCast
