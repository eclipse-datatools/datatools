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
package org.eclipse.datatools.modelbase.sql.statements;

import org.eclipse.emf.ecore.sdo.EDataObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A single statement of the following types (from section 13.5):
 * <SQL executable statement> ::=
 *     <SQL control statement>
 *   | <SQL data statement>
 *   | <SQL schema statement>
 *   | <SQL transaction statement>
 *   | <SQL connection statement>
 *   | <SQL session statement>
 *   | <SQL diagnostics statement>
 *   | <SQL dynamic statement>
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage#getSQLStatement()
 * @model interface="true" abstract="true"
 * @extends EDataObject
 * @generated
 */
public interface SQLStatement extends EDataObject{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model parameters=""
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

} // SQLStatement
