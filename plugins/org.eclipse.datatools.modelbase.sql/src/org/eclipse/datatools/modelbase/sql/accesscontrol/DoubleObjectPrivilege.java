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

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Double Object Privilege</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.34.2 Privileges
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.DoubleObjectPrivilege#getObject2 <em>Object2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getDoubleObjectPrivilege()
 * @model
 * @generated
 */
public interface DoubleObjectPrivilege extends Privilege {
	/**
	 * Returns the value of the '<em><b>Object2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object2</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object2</em>' reference.
	 * @see #setObject2(SQLObject)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getDoubleObjectPrivilege_Object2()
	 * @model required="true"
	 * @generated
	 */
	SQLObject getObject2();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.DoubleObjectPrivilege#getObject2 <em>Object2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object2</em>' reference.
	 * @see #getObject2()
	 * @generated
	 */
	void setObject2(SQLObject value);

} // DoubleObjectPrivilege
