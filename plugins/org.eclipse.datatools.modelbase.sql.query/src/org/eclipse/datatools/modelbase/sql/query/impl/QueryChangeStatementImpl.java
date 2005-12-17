/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryChangeStatementImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import org.eclipse.datatools.modelbase.sql.query.QueryChangeStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Change Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class QueryChangeStatementImpl extends QueryStatementImpl implements QueryChangeStatement {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QueryChangeStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQueryChangeStatement();
	}

} //SQLQueryChangeStatementImpl
