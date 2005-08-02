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
 * A representation of the model object '<em><b>Multiset Data Type</b></em>'.
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
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getMultisetDataType()
 * @model abstract="true"
 * @generated
 */
public interface MultisetDataType extends CollectionDataType{
} // MultisetDataType
