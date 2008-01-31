/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryMergeStatementImpl.java,v 1.5 2007/02/08 17:00:27 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Merge Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class QueryMergeStatementImpl extends QueryChangeStatementImpl implements QueryMergeStatement {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryMergeStatementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_MERGE_STATEMENT;
    }

} //SQLMergeStatementImpl
