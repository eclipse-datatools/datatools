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
 * 4.14 Tables
 * 
 * A table is a collection of rows having one or more columns. A row is a value of a row type. Every row of the same table has the same row type. The value of the i-th field of every row in a table is the value of the i-th column of that row in the table. The row is the smallest unit of data that can be inserted into a table and deleted from a table.
 * 
 * The degree of a table, and the degree of each of its rows, is the number of columns of that table. The number of rows in a table is its cardinality. A table whose cardinality is 0 (zero) is said to be empty.
 * 
 * A table is either a base table, a derived table, or a transient table. A base table is either a persistent base table, a global temporary table, a created local temporary table, or a declared local temporary table.
 * 
 * A table is described by a table descriptor. A table descriptor is either a base table descriptor, a view descriptor, or a derived table descriptor (for a derived table that is not a view).
 * Every table descriptor includes:
 *  - The column descriptor of each column in the table.
 *  - The name, if any, of the structured type, if any, associated with the table.
 *  - An indication of whether the table is insertable-into or not.
 *  - An indication of whether the table is a referenceable table or not, and an indication of whether the selfreferencing column is a system-generated, a user-generated, or a derived self-referencing column.
 *  - A list, possibly empty, of the names of its direct supertables.
 *  - A list, possibly empty, of the names of its direct subtables.
 * 
 * A base table descriptor describes a base table. In addition to the components of every table descriptor, a base table descriptor includes:
 *  - The name of the base table.
 *  - An indication of whether the table is a persistent base table, a global temporary table, a created local temporary table, or a declared local temporary table.
 *  - If the base table is a global temporary table, a created local temporary table, or a declared local temporary table, then an indication of whether ON COMMIT PRESERVE ROWS was specified or ON COMMIT DELETE ROWS was specified or implied.
 *  - The descriptor of each table constraint specified for the table.
 *  - A non-empty set of functional dependencies, according to the rules given in Subclause 4.18, "Functional dependencies".
 *  - A non-empty set of candidate keys, according to the rules of Subclause 4.19, "Candidate keys".
 *  - A preferred candidate key, which may or may not be additionally designated the primary key, according to the Rules in Subclause 4.18, "Functional dependencies".
 * 
 * 4.14.2 Referenceable tables, subtables, and supertables
 * 
 * A table RT whose row type is derived from a structured type ST is called a typed table. Only a base table or a view can be a typed table. A typed table has columns corresponding, in name and declared type, to every attribute of ST and one other column REFC that is the self-referencing column of RT; let REFCN be the <column name> of REFC. The declared type of REFC is necessarily REF (ST) and the nullability characteristic of REFC is known not nullable. If RT is a base table, then the table constraint "UNIQUE (REFCN)" is implicit in the definition of RT. A typed table is called a referenceable table. A self-referencing column cannot be updated.
 * 
 * Its value is determined during the insertion of a row into the referenceable table. The value of a system-generated self-referencing column and a derived self-referencing column is automatically generated when the row is inserted into the referenceable table. The value of a user-generated self-referencing column is supplied as part of the candidate row to be inserted into the referenceable table.
 * 
 * A table Ta is a direct subtable of another table Tb if and only if the <table name> of Tb is contained in the <subtable clause> contained in the <table definition> or <view definition> of Ta. Both Ta and Tb shall be created on a structured type and the structured type of Ta shall be a direct subtype of the structured type of Tb.
 * 
 * A table Ta is a subtable of a table Tb if and only if any of the following are true:
 * 1) Ta and Tb are the same named table.
 * 2) Ta is a direct subtable of Tb.
 * 3) There is a table Tc such that Ta is a direct subtable of Tc and Tc is a subtable of Tb.
 * A table T is considered to be one of its own subtables. Subtables of T other than T itself are called its proper subtables. A table shall not have itself as a proper subtable.
 * 
 * A table Tb is called a supertable of a table Ta if Ta is a subtable of Tb. If Ta is a direct subtable of Tb, then Tb is called a direct supertable of Ta. A table that is not a subtable of any other table is called a maximal supertable. Let Ta be a maximal supertable and T be a subtable of Ta. The set of all subtables of Ta (which includes Ta itself) is called the subtable family of T or (equivalently) of Ta. Every subtable family has exactly one maximal supertable. A leaf table is a table that does not have any proper subtables.
 * 
 * Those columns of a subtable Ta of a structured type STa that correspond to the inherited attributes of STa are
 * called inherited columns. Those columns of Ta that correspond to the originally-defined attributes of STa are
 * called originally-defined columns.
 * 
 * Let TB be a subtable of TA. Let SLA be the <value expression> sequence implied by the <select list> "*" in the
 * <query specification> "SELECT * FROM TA". For every row RB in the value of TB there exists exactly one
 * row RA in the value of TA such that RA is the result of the <row subquery> "SELECT SLA FROM VALUES
 * RRB", where RRB is some <row value constructor> whose value is RB. RA is said to be the superrow in TA of
 * RB and RB is said to be the subrow in TB of RA. If TA is a base table, then the one-to-one correspondence
 * between superrows and subrows is guaranteed by the requirement for a unique constraint to be specified for
 * some supertable of TA. If TA is a view, then such one-to-one correspondence is guaranteed by the requirement
 * for a unique constraint to be specified on the leaf generally underlying table of TA.
 * Users shall have the UNDER privilege on a table before they can use the table in a subtable definition. A table
 * can have more than one proper subtable. Similarly, a table can have more than one proper supertable.
 * 
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
public interface Table extends SQLObject{
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
