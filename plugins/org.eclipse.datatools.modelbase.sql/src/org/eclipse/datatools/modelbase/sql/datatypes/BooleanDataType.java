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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.5 Boolean types
 * 
 * The data type boolean comprises the distinct truth values True and False. Unless prohibited by a NOT NULL constraint, the boolean data type also supports the Unknown truth value as the null value. This specification does not make a distinction between the null value of the boolean data type and the Unknown truth value that is the result of an SQL <predicate> , <search condition> , or <boolean value expression> ; they may be used interchangeably to mean exactly the same thing.
 * 
 * The boolean data type is described by the boolean data type descriptor. The boolean data type descriptor contains:
 *  - The name of the boolean data type (BOOLEAN ).
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getBooleanDataType()
 * @model 
 * @generated
 */
public interface BooleanDataType extends PredefinedDataType{
} // BooleanDataType
