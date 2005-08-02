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
package org.eclipse.datatools.modelbase.sql.constraints;

import org.eclipse.datatools.modelbase.sql.tables.BaseTable;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.17.2 Table constraints
 * 
 * A table constraint is either a unique constraint, a referential constraint or a table check constraint. A table constraint is described by a table constraint descriptor which is either a unique constraint descriptor, a referential constraint descriptor or a table check constraint descriptor.
 * 
 * Every table constraint specified for base table T is implicitly a constraint on every subtable of T, by virtue of the fact that every row in a subtable is considered to have a corresponding superrow in every one of its supertables.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.TableConstraint#getBaseTable <em>Base Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getTableConstraint()
 * @model abstract="true"
 * @generated
 */
public interface TableConstraint extends Constraint{
	/**
	 * Returns the value of the '<em><b>Base Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Table</em>' container reference.
	 * @see #setBaseTable(BaseTable)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getTableConstraint_BaseTable()
	 * @see org.eclipse.datatools.modelbase.sql.tables.BaseTable#getConstraints
	 * @model opposite="constraints"
	 * @generated
	 */
	BaseTable getBaseTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.TableConstraint#getBaseTable <em>Base Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Table</em>' container reference.
	 * @see #getBaseTable()
	 * @generated
	 */
	void setBaseTable(BaseTable value);

} // TableConstraint
