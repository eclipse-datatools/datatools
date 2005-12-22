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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Primary Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Section 4.17
 * A PrimaryKey is a specialization of a UniqueConstraint.  A PrimaryKey additionally requires that none of the values in the specified column or columns be a null value.
 * 
 * Section 11.7
 * 5) If the <unique specification> specifies PRIMARY KEY , then for each <column name> in the explicit or
 * implicit <unique column list> for which NOT NULL is not specified, NOT NULL is implicit in the
 * <column definition> .
 * 6) A <table definition> shall specify at most one implicit or explicit <unique constraint definition> that
 * specifies PRIMARY KEY .
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getPrimaryKey()
 * @model
 * @generated
 */
public interface PrimaryKey extends UniqueConstraint{
} // PrimaryKey
