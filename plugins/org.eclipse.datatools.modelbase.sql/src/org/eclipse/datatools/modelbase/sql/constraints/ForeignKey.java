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
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Section 4.17
 * In the case that a table constraint is a referential constraint, the table is referred to as the referencing table. The referenced columns of a referential constraint shall be the unique columns of some unique constraint of the
 * referenced table.
 * 
 * Section 11.8
 * 5) The referenced table shall be a base table.
 *  Case:
 *   a) If the referencing table is a persistent base table, then the referenced table shall be a persistent base table.
 *   b) If the referencing table is a global temporary table, then the referenced table shall be a global temporary table. 
 *   c) If the referencing table is a created local temporary table, then the referenced table shall be either a global temporary table or a created local temporary table.
 *   d) If the referencing table is a declared local temporary table, then the referenced table shall be either a global temporary table, a created local temporary table or a declared local temporary table.
 * 
 * 6) If the referenced table is a temporary table with ON COMMIT DELETE ROWS specified, then the ref-erencing table shall specify ON COMMIT DELETE ROWS .
 * 7) Each referencing column shall identify a column of the referencing table, and the same column shall not be identified more than once.
 * 9) The <referencing columns> shall contain the same number of <column name> s as the <referenced table and columns> . The i-th column identified in the <referencing columns> corresponds to the i-th column identified in the <referenced table and columns> . The declared type of each referencing column shall be comparable to the declared type of the corresponding referenced column. There shall not be corresponding constituents of the declared type of a referencing column and the declared type of the corresponding referenced column such that one constituent is datetime with time zone and the other is datetime without time zone.
 * 10) If a <referential constraint definition> does not specify any <update rule> , then an <update rule> with a <referential action> of NO ACTION is implicit.
 * 11) If a <referential constraint definition> does not specify any <delete rule> , then a <delete rule> with a <referential action> of NO ACTION is implicit.
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
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getForeignKey()
 * @model 
 * @generated
 */
public interface ForeignKey extends ReferenceConstraint{
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

} // ForeignKey
