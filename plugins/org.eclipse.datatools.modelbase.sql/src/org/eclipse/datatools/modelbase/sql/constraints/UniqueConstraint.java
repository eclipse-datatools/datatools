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
 * Reference: 5WD-02-Foundation-2002-12 4.17.2 Table constraints, 5WD-02-Foundation-2002-12 11.7 <unique contraint definition>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint#isClustered <em>Clustered</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint#getForeignKey <em>Foreign Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getUniqueConstraint()
 * @model
 * @generated
 */
public interface UniqueConstraint extends ReferenceConstraint {
	/**
	 * Returns the value of the '<em><b>Clustered</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Clustered</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Clustered</em>' attribute.
	 * @see #setClustered(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getUniqueConstraint_Clustered()
	 * @model default="true"
	 * @generated
	 */
    boolean isClustered();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint#isClustered <em>Clustered</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clustered</em>' attribute.
	 * @see #isClustered()
	 * @generated
	 */
    void setClustered(boolean value);

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
