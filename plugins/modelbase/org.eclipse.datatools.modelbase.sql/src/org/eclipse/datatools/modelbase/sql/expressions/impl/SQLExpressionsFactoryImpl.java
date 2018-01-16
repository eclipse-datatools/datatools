/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.expressions.impl;

import org.eclipse.datatools.modelbase.sql.expressions.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLExpressionsFactoryImpl extends EFactoryImpl implements SQLExpressionsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SQLExpressionsFactory init() {
		try {
			SQLExpressionsFactory theSQLExpressionsFactory = (SQLExpressionsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/modelbase/sql/expressions.ecore"); //$NON-NLS-1$ 
			if (theSQLExpressionsFactory != null) {
				return theSQLExpressionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SQLExpressionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLExpressionsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SQLExpressionsPackage.QUERY_EXPRESSION_DEFAULT: return createQueryExpressionDefault();
			case SQLExpressionsPackage.SEARCH_CONDITION_DEFAULT: return createSearchConditionDefault();
			case SQLExpressionsPackage.VALUE_EXPRESSION_DEFAULT: return createValueExpressionDefault();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryExpressionDefault createQueryExpressionDefault() {
		QueryExpressionDefaultImpl queryExpressionDefault = new QueryExpressionDefaultImpl();
		return queryExpressionDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchConditionDefault createSearchConditionDefault() {
		SearchConditionDefaultImpl searchConditionDefault = new SearchConditionDefaultImpl();
		return searchConditionDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueExpressionDefault createValueExpressionDefault() {
		ValueExpressionDefaultImpl valueExpressionDefault = new ValueExpressionDefaultImpl();
		return valueExpressionDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLExpressionsPackage getSQLExpressionsPackage() {
		return (SQLExpressionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static SQLExpressionsPackage getPackage() {
		return SQLExpressionsPackage.eINSTANCE;
	}

} //SQLExpressionsFactoryImpl
