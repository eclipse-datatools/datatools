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

import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.13 Columns, fields, and attributes
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Column#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Column#getIdentitySpecifier <em>Identity Specifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Column#getGenerateExpression <em>Generate Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Column#isImplementationDependent <em>Implementation Dependent</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Column#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Column#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Column#getScopeCheck <em>Scope Check</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.Column#isScopeChecked <em>Scope Checked</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn()
 * @model
 * @generated
 */
public interface Column extends TypedElement {
	/**
	 * Returns the value of the '<em><b>Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' container reference.
	 * @see #setTable(Table)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn_Table()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getColumns
	 * @model opposite="columns" required="true"
	 * @generated
	 */
	Table getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getTable <em>Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' container reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(Table value);

	/**
	 * Returns the value of the '<em><b>Identity Specifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Specifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Specifier</em>' containment reference.
	 * @see #setIdentitySpecifier(IdentitySpecifier)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn_IdentitySpecifier()
	 * @model containment="true"
	 * @generated
	 */
	IdentitySpecifier getIdentitySpecifier();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getIdentitySpecifier <em>Identity Specifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Specifier</em>' containment reference.
	 * @see #getIdentitySpecifier()
	 * @generated
	 */
	void setIdentitySpecifier(IdentitySpecifier value);

	/**
	 * Returns the value of the '<em><b>Generate Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generate Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generate Expression</em>' containment reference.
	 * @see #setGenerateExpression(ValueExpression)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn_GenerateExpression()
	 * @model containment="true"
	 * @generated
	 */
	ValueExpression getGenerateExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getGenerateExpression <em>Generate Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generate Expression</em>' containment reference.
	 * @see #getGenerateExpression()
	 * @generated
	 */
	void setGenerateExpression(ValueExpression value);

	/**
	 * Returns the value of the '<em><b>Implementation Dependent</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation Dependent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation Dependent</em>' attribute.
	 * @see #setImplementationDependent(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn_ImplementationDependent()
	 * @model default="False"
	 * @generated
	 */
	boolean isImplementationDependent();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Column#isImplementationDependent <em>Implementation Dependent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation Dependent</em>' attribute.
	 * @see #isImplementationDependent()
	 * @generated
	 */
	void setImplementationDependent(boolean value);

	/**
	 * Returns the value of the '<em><b>Nullable</b></em>' attribute.
	 * The default value is <code>"True"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nullable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nullable</em>' attribute.
	 * @see #setNullable(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn_Nullable()
	 * @model default="True"
	 * @generated
	 */
	boolean isNullable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Column#isNullable <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nullable</em>' attribute.
	 * @see #isNullable()
	 * @generated
	 */
	void setNullable(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(String)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn_DefaultValue()
	 * @model
	 * @generated
	 */
	String getDefaultValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(String value);

	/**
	 * Returns the value of the '<em><b>Scope Check</b></em>' attribute.
	 * The default value is <code>"NO_ACTION"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Check</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope Check</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see #setScopeCheck(ReferentialActionType)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn_ScopeCheck()
	 * @model default="NO_ACTION"
	 * @generated
	 */
	ReferentialActionType getScopeCheck();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getScopeCheck <em>Scope Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Check</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see #getScopeCheck()
	 * @generated
	 */
	void setScopeCheck(ReferentialActionType value);

	/**
	 * Returns the value of the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Checked</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope Checked</em>' attribute.
	 * @see #setScopeChecked(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getColumn_ScopeChecked()
	 * @model
	 * @generated
	 */
	boolean isScopeChecked();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.Column#isScopeChecked <em>Scope Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Checked</em>' attribute.
	 * @see #isScopeChecked()
	 * @generated
	 */
	void setScopeChecked(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isPartOfForeignKey();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isPartOfUniqueConstraint();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isPartOfPrimaryKey();

} // Column
