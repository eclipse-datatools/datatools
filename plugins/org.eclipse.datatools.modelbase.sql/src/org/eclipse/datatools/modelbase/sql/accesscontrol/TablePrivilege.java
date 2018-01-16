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
package org.eclipse.datatools.modelbase.sql.accesscontrol;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Privilege</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.34.2 Privileges
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.TablePrivilege#isWithHierarchy <em>With Hierarchy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getTablePrivilege()
 * @model
 * @generated
 */
public interface TablePrivilege extends Privilege {
	/**
	 * Returns the value of the '<em><b>With Hierarchy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>With Hierarchy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>With Hierarchy</em>' attribute.
	 * @see #setWithHierarchy(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getTablePrivilege_WithHierarchy()
	 * @model
	 * @generated
	 */
	boolean isWithHierarchy();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.TablePrivilege#isWithHierarchy <em>With Hierarchy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>With Hierarchy</em>' attribute.
	 * @see #isWithHierarchy()
	 * @generated
	 */
	void setWithHierarchy(boolean value);

} // TablePrivilege
