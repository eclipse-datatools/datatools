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
package org.eclipse.datatools.modelbase.sql.tables;

import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.14 Tables
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSupertable <em>Supertable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSubtables <em>Subtables</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#getUdt <em>Udt</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#getIndex <em>Index</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSelfRefColumnGeneration <em>Self Ref Column Generation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#isInsertable <em>Insertable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Table#isUpdatable <em>Updatable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable()
 * @model abstract="true"
 * @generated
 */
public interface Table extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Column}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Columns()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#getTable
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.Column" opposite="table" containment="true" required="true"
	 * @generated
	 */
	EList getColumns();

	/**
	 * Returns the value of the '<em><b>Supertable</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSubtables <em>Subtables</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supertable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supertable</em>' reference.
	 * @see #setSupertable(Table)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Supertable()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getSubtables
	 * @model opposite="subtables"
	 * @generated
	 */
	Table getSupertable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSupertable <em>Supertable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supertable</em>' reference.
	 * @see #getSupertable()
	 * @generated
	 */
	void setSupertable(Table value);

	/**
	 * Returns the value of the '<em><b>Subtables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Table}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSupertable <em>Supertable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subtables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subtables</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Subtables()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getSupertable
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.Table" opposite="supertable"
	 * @generated
	 */
	EList getSubtables();

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getTables <em>Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(Schema)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Schema()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getTables
	 * @model opposite="tables" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

	/**
	 * Returns the value of the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Udt</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Udt</em>' reference.
	 * @see #setUdt(StructuredUserDefinedType)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Udt()
	 * @model
	 * @generated
	 */
	StructuredUserDefinedType getUdt();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getUdt <em>Udt</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Udt</em>' reference.
	 * @see #getUdt()
	 * @generated
	 */
	void setUdt(StructuredUserDefinedType value);

	/**
	 * Returns the value of the '<em><b>Triggers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Trigger}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getSubjectTable <em>Subject Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Triggers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Triggers()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getSubjectTable
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.Trigger" opposite="subjectTable"
	 * @generated
	 */
	EList getTriggers();

	/**
	 * Returns the value of the '<em><b>Index</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.Index}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Index()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getTable
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.Index" opposite="table"
	 * @generated
	 */
	EList getIndex();

	/**
	 * Returns the value of the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.tables.ReferenceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Self Ref Column Generation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Self Ref Column Generation</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ReferenceType
	 * @see #setSelfRefColumnGeneration(ReferenceType)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_SelfRefColumnGeneration()
	 * @model
	 * @generated
	 */
	ReferenceType getSelfRefColumnGeneration();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSelfRefColumnGeneration <em>Self Ref Column Generation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Self Ref Column Generation</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ReferenceType
	 * @see #getSelfRefColumnGeneration()
	 * @generated
	 */
	void setSelfRefColumnGeneration(ReferenceType value);

	/**
	 * Returns the value of the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Insertable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Insertable</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Insertable()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isInsertable();

	/**
	 * Returns the value of the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Updatable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Updatable</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTable_Updatable()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isUpdatable();

} // Table
