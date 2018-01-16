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
package org.eclipse.datatools.modelbase.sql.schema;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Dependency#getTargetEnd <em>Target End</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Dependency#getDependencyType <em>Dependency Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDependency()
 * @model
 * @generated
 */
public interface Dependency extends SQLObject{
	/**
	 * Returns the value of the '<em><b>Target End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target End</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target End</em>' reference.
	 * @see #setTargetEnd(EObject)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDependency_TargetEnd()
	 * @model required="true"
	 * @generated
	 */
	EObject getTargetEnd();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Dependency#getTargetEnd <em>Target End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target End</em>' reference.
	 * @see #getTargetEnd()
	 * @generated
	 */
	void setTargetEnd(EObject value);

	/**
	 * Returns the value of the '<em><b>Dependency Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependency Type</em>' attribute.
	 * @see #setDependencyType(String)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDependency_DependencyType()
	 * @model
	 * @generated
	 */
	String getDependencyType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Dependency#getDependencyType <em>Dependency Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dependency Type</em>' attribute.
	 * @see #getDependencyType()
	 * @generated
	 */
	void setDependencyType(String value);

} // Dependency
