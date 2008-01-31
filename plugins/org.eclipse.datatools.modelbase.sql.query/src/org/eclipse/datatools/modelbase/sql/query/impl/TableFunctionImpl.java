/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableFunctionImpl.java,v 1.5 2007/02/08 17:00:30 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class TableFunctionImpl extends TableExpressionImpl implements TableFunction {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TableFunctionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.TABLE_FUNCTION;
    }

} //SQLTableFunctionImpl
