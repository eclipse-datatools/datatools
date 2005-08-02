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

import java.util.Date;

import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Section 4.38:
 * 
 * A trigger is defined by a <trigger definition> . A <trigger definition> specifies a trigger that is described by a
 * trigger descriptor . A trigger descriptor includes:
 *   - The name of the trigger.
 *   - The name of the base table that is the subject table.
 *   - The trigger action time (BEFORE or AFTER ).
 *   - The trigger event (INSERT , DELETE , or UPDATE ).
 *   - Whether the trigger is a statement-level trigger or a row-level trigger.
 *   - Any old transition variable name, new transition variable name, old transition table name, new transition table name.
 *   - The triggered action.
 *   - The trigger column list (possibly empty) for the trigger event.
 *   - The triggered action column set of the triggered action.
 *   - The timestamp of creation of the trigger.
 * 
 * The order of execution of a set of triggers is ascending by value of their timestamp of creation in their descriptors, such that the oldest trigger executes first. If one or more triggers have the same timestamp value, then their relative order of execution is implementation-defined.
 * 
 * A schema might include one or more trigger descriptors, each of which includes the definition of a triggered action specifying a <triggered SQL statement> that is to be executed (either once for each affected row, in the case of a row-level trigger, or once for the whole triggering INSERT , DELETE , MERGE , or UPDATE statement, in the case of a statement-level trigger) before or after rows are inserted into a table, rows are deleted from a table, or one or more columns are updated in rows of a table. The execution of such a triggered action resulting from the insertion, deletion, or updating of a table may cause the triggering of further triggered actions.
 * 
 * The <triggered SQL statement> of a triggered action is effectively executed either immediately before or
 * immediately after the trigger event, as determined by the specified trigger action time.
 * 
 * Format of trigger definition and syntax rules are defined in section 11.39.
 * 
 * OCL rules are derived from these syntax rules:
 * 4) The schema identified by the explicit or implicit <schema name> of a <trigger name> TRN shall not include a trigger descriptor whose trigger name is TRN.
 * 5) T shall be a base table that is not a declared local temporary table.
 * 6) If a <trigger column list> is specified, then:
 *   a) No <column name> shall appear more than once in the <trigger column list> .
 *   b) The <column name> s of the <trigger column list> shall identify columns of T.
 * 9) If OR or NR is specified, then FOR EACH ROW shall be specified.
 * 12) If BEFORE is specified, then:
 *   b) Neither OLD TABLE nor NEW TABLE shall be specified.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getSubjectTable <em>Subject Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionStatement <em>Action Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getTriggerColumn <em>Trigger Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionGranularity <em>Action Granularity</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getWhen <em>When</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getTimeStamp <em>Time Stamp</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionTime <em>Action Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isUpdateType <em>Update Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isInsertType <em>Insert Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isDeleteType <em>Delete Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getOldRow <em>Old Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getNewRow <em>New Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getOldTable <em>Old Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getNewTable <em>New Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger()
 * @model 
 * @generated
 */
public interface Trigger extends SQLObject{
	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(Schema)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_Schema()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getTriggers
	 * @model opposite="triggers" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

	/**
	 * Returns the value of the '<em><b>Subject Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject Table</em>' reference.
	 * @see #setSubjectTable(Table)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_SubjectTable()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getTriggers
	 * @model opposite="triggers" required="true"
	 * @generated
	 */
	Table getSubjectTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getSubjectTable <em>Subject Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject Table</em>' reference.
	 * @see #getSubjectTable()
	 * @generated
	 */
	void setSubjectTable(Table value);

	/**
	 * Returns the value of the '<em><b>Action Statement</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.statements.SQLStatement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Statement</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Statement</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_ActionStatement()
	 * @model type="org.eclipse.datatools.modelbase.sql.statements.SQLStatement" containment="true" required="true"
	 * @generated
	 */
	EList getActionStatement();

	/**
	 * Returns the value of the '<em><b>Trigger Column</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Column}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger Column</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger Column</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_TriggerColumn()
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.Column"
	 * @generated
	 */
	EList getTriggerColumn();

	/**
	 * Returns the value of the '<em><b>Action Granularity</b></em>' attribute.
	 * The default value is <code>"STATEMENT"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Granularity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Granularity</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType
	 * @see #setActionGranularity(ActionGranularityType)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_ActionGranularity()
	 * @model default="STATEMENT"
	 * @generated
	 */
	ActionGranularityType getActionGranularity();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionGranularity <em>Action Granularity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Granularity</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType
	 * @see #getActionGranularity()
	 * @generated
	 */
	void setActionGranularity(ActionGranularityType value);

	/**
	 * Returns the value of the '<em><b>When</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>When</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' containment reference.
	 * @see #setWhen(SearchCondition)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_When()
	 * @model containment="true"
	 * @generated
	 */
	SearchCondition getWhen();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getWhen <em>When</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' containment reference.
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(SearchCondition value);

	/**
	 * Returns the value of the '<em><b>Time Stamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Triggers on the same table with the same action time are executed in order of creation, i.e. the time stamp, such that older triggers have priority.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time Stamp</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_TimeStamp()
	 * @model dataType="org.eclipse.datatools.modelbase.sql.schema.Date" changeable="false"
	 * @generated
	 */
	Date getTimeStamp();

	/**
	 * Returns the value of the '<em><b>Action Time</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.tables.ActionTimeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Time</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ActionTimeType
	 * @see #setActionTime(ActionTimeType)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_ActionTime()
	 * @model 
	 * @generated
	 */
	ActionTimeType getActionTime();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionTime <em>Action Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Time</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ActionTimeType
	 * @see #getActionTime()
	 * @generated
	 */
	void setActionTime(ActionTimeType value);

	/**
	 * Returns the value of the '<em><b>Update Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Update Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Update Type</em>' attribute.
	 * @see #setUpdateType(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_UpdateType()
	 * @model 
	 * @generated
	 */
	boolean isUpdateType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isUpdateType <em>Update Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Update Type</em>' attribute.
	 * @see #isUpdateType()
	 * @generated
	 */
	void setUpdateType(boolean value);

	/**
	 * Returns the value of the '<em><b>Insert Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Insert Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Insert Type</em>' attribute.
	 * @see #setInsertType(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_InsertType()
	 * @model 
	 * @generated
	 */
	boolean isInsertType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isInsertType <em>Insert Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Insert Type</em>' attribute.
	 * @see #isInsertType()
	 * @generated
	 */
	void setInsertType(boolean value);

	/**
	 * Returns the value of the '<em><b>Delete Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delete Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delete Type</em>' attribute.
	 * @see #setDeleteType(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_DeleteType()
	 * @model 
	 * @generated
	 */
	boolean isDeleteType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isDeleteType <em>Delete Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delete Type</em>' attribute.
	 * @see #isDeleteType()
	 * @generated
	 */
	void setDeleteType(boolean value);

	/**
	 * Returns the value of the '<em><b>Old Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Old Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Old Row</em>' attribute.
	 * @see #setOldRow(String)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_OldRow()
	 * @model 
	 * @generated
	 */
	String getOldRow();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getOldRow <em>Old Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Old Row</em>' attribute.
	 * @see #getOldRow()
	 * @generated
	 */
	void setOldRow(String value);

	/**
	 * Returns the value of the '<em><b>New Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Row</em>' attribute.
	 * @see #setNewRow(String)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_NewRow()
	 * @model 
	 * @generated
	 */
	String getNewRow();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getNewRow <em>New Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Row</em>' attribute.
	 * @see #getNewRow()
	 * @generated
	 */
	void setNewRow(String value);

	/**
	 * Returns the value of the '<em><b>Old Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Old Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Old Table</em>' attribute.
	 * @see #setOldTable(String)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_OldTable()
	 * @model 
	 * @generated
	 */
	String getOldTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getOldTable <em>Old Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Old Table</em>' attribute.
	 * @see #getOldTable()
	 * @generated
	 */
	void setOldTable(String value);

	/**
	 * Returns the value of the '<em><b>New Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Table</em>' attribute.
	 * @see #setNewTable(String)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTrigger_NewTable()
	 * @model 
	 * @generated
	 */
	String getNewTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getNewTable <em>New Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Table</em>' attribute.
	 * @see #getNewTable()
	 * @generated
	 */
	void setNewTable(String value);

} // Trigger
