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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.34.1 Authorization Identifiers
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Group#getUser <em>User</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getGroup()
 * @model
 * @generated
 */
public interface Group extends AuthorizationIdentifier {
	/**
	 * Returns the value of the '<em><b>User</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.accesscontrol.User}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.User#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getGroup_User()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.User#getGroup
	 * @model type="org.eclipse.datatools.modelbase.sql.accesscontrol.User" opposite="group"
	 * @generated
	 */
	EList getUser();

} // Group
