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

import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.17 Integrity constraints, Reference, 5WD-02-Foundation-2002-12 11.8 <referencial constraint definition>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getMatch <em>Match</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getOnUpdate <em>On Update</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getOnDelete <em>On Delete</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueConstraint <em>Unique Constraint</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getReferencedMembers <em>Referenced Members</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueIndex <em>Unique Index</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getReferencedTable <em>Referenced Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey()
 * @model
 * @generated
 */
public interface ForeignKey extends ReferenceConstraint {
	/**
	 * Returns the value of the '<em><b>Match</b></em>' attribute.
	 * The default value is <code>"MATCH_SIMPLE"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.constraints.MatchType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Match</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.MatchType
	 * @see #setMatch(MatchType)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey_Match()
	 * @model default="MATCH_SIMPLE"
	 * @generated
	 */
	MatchType getMatch();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getMatch <em>Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Match</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.MatchType
	 * @see #getMatch()
	 * @generated
	 */
	void setMatch(MatchType value);

	/**
	 * Returns the value of the '<em><b>On Update</b></em>' attribute.
	 * The default value is <code>"NO_ACTION"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Update</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Update</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see #setOnUpdate(ReferentialActionType)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey_OnUpdate()
	 * @model default="NO_ACTION"
	 * @generated
	 */
	ReferentialActionType getOnUpdate();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getOnUpdate <em>On Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Update</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see #getOnUpdate()
	 * @generated
	 */
	void setOnUpdate(ReferentialActionType value);

	/**
	 * Returns the value of the '<em><b>On Delete</b></em>' attribute.
	 * The default value is <code>"NO_ACTION"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Delete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Delete</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see #setOnDelete(ReferentialActionType)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey_OnDelete()
	 * @model default="NO_ACTION"
	 * @generated
	 */
	ReferentialActionType getOnDelete();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getOnDelete <em>On Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Delete</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see #getOnDelete()
	 * @generated
	 */
	void setOnDelete(ReferentialActionType value);

	/**
	 * Returns the value of the '<em><b>Unique Constraint</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint#getForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique Constraint</em>' reference.
	 * @see #setUniqueConstraint(UniqueConstraint)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey_UniqueConstraint()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint#getForeignKey
	 * @model opposite="ForeignKey"
	 * @generated
	 */
	UniqueConstraint getUniqueConstraint();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueConstraint <em>Unique Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique Constraint</em>' reference.
	 * @see #getUniqueConstraint()
	 * @generated
	 */
	void setUniqueConstraint(UniqueConstraint value);

	/**
	 * Returns the value of the '<em><b>Referenced Members</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Column}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Members</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Members</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey_ReferencedMembers()
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.Column" required="true"
	 * @generated
	 */
	EList getReferencedMembers();

	/**
	 * Returns the value of the '<em><b>Unique Index</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique Index</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique Index</em>' reference.
	 * @see #setUniqueIndex(Index)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey_UniqueIndex()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getForeignKey
	 * @model opposite="ForeignKey"
	 * @generated
	 */
	Index getUniqueIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueIndex <em>Unique Index</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique Index</em>' reference.
	 * @see #getUniqueIndex()
	 * @generated
	 */
	void setUniqueIndex(Index value);

	/**
	 * Returns the value of the '<em><b>Referenced Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable#getReferencingForeignKeys <em>Referencing Foreign Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Table</em>' reference.
	 * @see #setReferencedTable(BaseTable)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey_ReferencedTable()
	 * @see org.eclipse.datatools.modelbase.sql.tables.BaseTable#getReferencingForeignKeys
	 * @model opposite="referencingForeignKeys"
	 * @generated
	 */
	BaseTable getReferencedTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getReferencedTable <em>Referenced Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Table</em>' reference.
	 * @see #getReferencedTable()
	 * @generated
	 */
	void setReferencedTable(BaseTable value);

} // ForeignKey
