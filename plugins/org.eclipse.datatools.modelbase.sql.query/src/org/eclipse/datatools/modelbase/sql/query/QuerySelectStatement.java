/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuerySelectStatement.java,v 1.6 2005/10/22 01:35:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Select Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getQueryExpr <em>Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getOrderByClause <em>Order By Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage#getQuerySelectStatement()
 * @model
 * @generated
 */
public interface QuerySelectStatement extends QueryStatement{
	/**
	 * Returns the value of the '<em><b>Query Expr</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getSelectStatement <em>Select Statement</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Query Expr</em>' containment reference.
	 * @see #setQueryExpr(QueryExpressionRoot)
	 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage#getQuerySelectStatement_QueryExpr()
	 * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getSelectStatement
	 * @model opposite="selectStatement" containment="true" required="true"
	 * @generated
	 */
    QueryExpressionRoot getQueryExpr();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getQueryExpr <em>Query Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query Expr</em>' containment reference.
	 * @see #getQueryExpr()
	 * @generated
	 */
    void setQueryExpr(QueryExpressionRoot value);

	/**
	 * Returns the value of the '<em><b>Order By Clause</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getSelectStatement <em>Select Statement</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Order By Clause</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Order By Clause</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage#getQuerySelectStatement_OrderByClause()
	 * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getSelectStatement
	 * @model type="org.eclipse.datatools.modelbase.sql.query.OrderBySpecification" opposite="selectStatement" containment="true"
	 * @generated
	 */
    EList getOrderByClause();

} // SQLSelectStatement
