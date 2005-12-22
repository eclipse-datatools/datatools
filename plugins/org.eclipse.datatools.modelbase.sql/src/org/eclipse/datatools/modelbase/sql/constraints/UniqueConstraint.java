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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Section 4.17.2
 * A unique constraint is satisfied if and only if no two rows in a table have the same non-null values in the unique columns.
 * 
 * Section 11.7
 * 3) If <unique column list> UCL is specified, then
 * a) Each <column name> in the <unique column list> shall identify a column of T, and the same column
 * shall not be identified more than once.
 * b) The set of columns in the <unique column list> shall be distinct from the unique columns of any other unique constraint descriptor that is included in the base table descriptor of T.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint#getForeignKey <em>Foreign Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getUniqueConstraint()
 * @model
 * @generated
 */
public interface UniqueConstraint extends ReferenceConstraint{
	/**
	 * Returns the value of the '<em><b>Foreign Key</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueConstraint <em>Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreign Key</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreign Key</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getUniqueConstraint_ForeignKey()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueConstraint
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.ForeignKey" opposite="uniqueConstraint"
	 * @generated
	 */
	EList getForeignKey();

} // UniqueConstraint
