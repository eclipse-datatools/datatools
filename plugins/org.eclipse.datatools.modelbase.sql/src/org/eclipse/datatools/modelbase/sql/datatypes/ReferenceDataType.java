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
package org.eclipse.datatools.modelbase.sql.datatypes;

import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.1 Data types
 * 
 * A data type is a set of representable values. Every representable value belongs to at least one data type and some belong to several data types. [...]
 * 
 * A constructed type is specified using one of SQL's data type constructors, ARRAY , MULTISET , REF , and ROW . A constructed type is either an array type, a multiset type, a reference type, or a row type, according to whether it is specified with ARRAY , MULTISET , REF , or ROW , respectively. Array types and multiset types are known generically as collection types.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType#getScopeTable <em>Scope Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType#getReferencedType <em>Referenced Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getReferenceDataType()
 * @model abstract="true"
 * @generated
 */
public interface ReferenceDataType extends ConstructedDataType{
	/**
	 * Returns the value of the '<em><b>Scope Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope Table</em>' reference.
	 * @see #setScopeTable(Table)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getReferenceDataType_ScopeTable()
	 * @model required="true"
	 * @generated
	 */
	Table getScopeTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType#getScopeTable <em>Scope Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Table</em>' reference.
	 * @see #getScopeTable()
	 * @generated
	 */
	void setScopeTable(Table value);

	/**
	 * Returns the value of the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Type</em>' reference.
	 * @see #setReferencedType(StructuredUserDefinedType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getReferenceDataType_ReferencedType()
	 * @model required="true"
	 * @generated
	 */
	StructuredUserDefinedType getReferencedType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType#getReferencedType <em>Referenced Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Type</em>' reference.
	 * @see #getReferencedType()
	 * @generated
	 */
	void setReferencedType(StructuredUserDefinedType value);

} // ReferenceDataType
