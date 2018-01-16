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

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#isClustered <em>Clustered</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getFillFactor <em>Fill Factor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#isSystemGenerated <em>System Generated</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getMembers <em>Members</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getForeignKey <em>Foreign Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getIncludedMembers <em>Included Members</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex()
 * @model
 * @generated
 */
public interface Index extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getIndices <em>Indices</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(Schema)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_Schema()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getIndices
	 * @model opposite="indices" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

	/**
	 * Returns the value of the '<em><b>Clustered</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clustered</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clustered</em>' attribute.
	 * @see #setClustered(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_Clustered()
	 * @model default="false"
	 * @generated
	 */
	boolean isClustered();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#isClustered <em>Clustered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clustered</em>' attribute.
	 * @see #isClustered()
	 * @generated
	 */
	void setClustered(boolean value);

	/**
	 * Returns the value of the '<em><b>Fill Factor</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fill Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill Factor</em>' attribute.
	 * @see #setFillFactor(int)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_FillFactor()
	 * @model default="0"
	 * @generated
	 */
	int getFillFactor();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getFillFactor <em>Fill Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill Factor</em>' attribute.
	 * @see #getFillFactor()
	 * @generated
	 */
	void setFillFactor(int value);

	/**
	 * Returns the value of the '<em><b>Unique</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique</em>' attribute.
	 * @see #setUnique(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_Unique()
	 * @model default="false"
	 * @generated
	 */
	boolean isUnique();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#isUnique <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique</em>' attribute.
	 * @see #isUnique()
	 * @generated
	 */
	void setUnique(boolean value);

	/**
	 * Returns the value of the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Generated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Generated</em>' attribute.
	 * @see #setSystemGenerated(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_SystemGenerated()
	 * @model
	 * @generated
	 */
	boolean isSystemGenerated();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#isSystemGenerated <em>System Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Generated</em>' attribute.
	 * @see #isSystemGenerated()
	 * @generated
	 */
	void setSystemGenerated(boolean value);

	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_Members()
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.IndexMember" containment="true" required="true"
	 * @generated
	 */
	EList getMembers();

	/**
	 * Returns the value of the '<em><b>Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' reference.
	 * @see #setTable(Table)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_Table()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getIndex
	 * @model opposite="index" required="true"
	 * @generated
	 */
	Table getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getTable <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(Table value);

	/**
	 * Returns the value of the '<em><b>Foreign Key</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueIndex <em>Unique Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreign Key</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreign Key</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_ForeignKey()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueIndex
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.ForeignKey" opposite="uniqueIndex"
	 * @generated
	 */
	EList getForeignKey();

	/**
	 * Returns the value of the '<em><b>Included Members</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Members</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Members</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndex_IncludedMembers()
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.IndexMember" containment="true"
	 * @generated
	 */
	EList getIncludedMembers();

} // Index
