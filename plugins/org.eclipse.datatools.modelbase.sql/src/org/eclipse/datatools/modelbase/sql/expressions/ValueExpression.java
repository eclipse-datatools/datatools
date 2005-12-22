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

import org.eclipse.emf.ecore.sdo.EDataObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.14.5 Base columns and generated columns
 * 
 * A column of a base table is either a base column or a generated column. A base column is one that is not a generated column.
 * 
 * A generated column is one whose values are determined by evaluation of a generation expression, a <value expression> whose declared type is by implication that of the column.
 * 
 * A generation expression can reference base columns of the base table to which it belongs but cannot otherwise access SQLdata. Thus, the value of the field corresponding to a generated column in row R is determined by the values of zero or more other fields of R.
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage#getValueExpression()
 * @model interface="true" abstract="true"
 * @extends EDataObject
 * @generated
 */
public interface ValueExpression extends EDataObject{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getSQL();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setSQL(String sqlText);

} // ValueExpression
