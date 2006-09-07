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
package org.eclipse.datatools.modelbase.sql.expressions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage
 * @generated
 */
public interface SQLExpressionsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLExpressionsFactory eINSTANCE = org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Query Expression Default</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Expression Default</em>'.
	 * @generated
	 */
	QueryExpressionDefault createQueryExpressionDefault();

	/**
	 * Returns a new object of class '<em>Search Condition Default</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Search Condition Default</em>'.
	 * @generated
	 */
	SearchConditionDefault createSearchConditionDefault();

	/**
	 * Returns a new object of class '<em>Value Expression Default</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Expression Default</em>'.
	 * @generated
	 */
	ValueExpressionDefault createValueExpressionDefault();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SQLExpressionsPackage getSQLExpressionsPackage();

} //SQLExpressionsFactory
