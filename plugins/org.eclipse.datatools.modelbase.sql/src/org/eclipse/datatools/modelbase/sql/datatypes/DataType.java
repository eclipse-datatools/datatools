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

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.1 Data types
 * 
 * A data type is a set of representable values. Every representable value belongs to at least one data type and some belong to several data types. Exactly one of the data types of a value V, namely the most specific type of V, is a subtype of every data type of V. A <value expression> E has exactly one declared type, common to every possible result of evaluating E. Items that can be referenced by name, such as SQL parameters, columns, fields, attributes, and variables, also have declared types.
 * 
 * SQL supports three sorts of data types: predefined data types, constructed types, and user-defined types. Predefined data types are sometimes called 'built-in data types', though not in this International Standard. Userdefined types can be defined by a standard, by an implementation, or by an application. A constructed type is specified using one of SQL's data type constructors, ARRAY , MULTISET , REF , and ROW . A constructed type is either an array type, a multiset type, a reference type, or a row type, according to whether it is specified with ARRAY , MULTISET , REF , or ROW , respectively. Array types and multiset types are known generically as collection types.
 * 
 * Every predefined data type is a subtype of itself and of no other data types. It follows that every predefined data type is a supertype of itself and of no other data types. The predefined data types are individually described in each of Subclause 4.2, "Character strings", through Subclause 4.6, "Datetimes and intervals". Row types, reference types and collection types are described in Subclause 4.8, "Row types", Subclause 4.9, "Reference types", Subclause 4.10, "Collection types", respectively.
 * 
 * SQL defines predefined data types named by the following <key word> s: CHARACTER , CHARACTER VARYING , CHARACTER LARGE OBJECT , BINARY LARGE OBJECT , NUMERIC , DECIMAL , SMALLINT , INTEGER , BIGINT , FLOAT , REAL , DOUBLE PRECISION , BOOLEAN , DATE , TIME , TIMESTAMP , and INTERVAL . These names are used in the type designators that constitute the type precedence lists specified in Subclause 9.5, "Type precedence list determination".
 * 
 * For reference purposes:
 *  - The data types CHARACTER , CHARACTER VARYING , and CHARACTER LARGE OBJECT are collectively referred to as character string types.
 *  - The data type BINARY LARGE OBJECT is referred to as the binary string type and the values of binary string types are referred to as binary strings.
 *  - The data types CHARACTER LARGE OBJECT and BINARY LARGE OBJECT are collectively referred to as large object string types and the values of large object string types are referred to as large object strings.
 *  - Character string types and binary string types are collectively referred to as string types and values of string types are referred to as strings.
 *  - The data types NUMERIC , DECIMAL , SMALLINT , INTEGER , and BIGINT are collectively referred to as exact numeric types.
 *  - The data types FLOAT , REAL , and DOUBLE PRECISION are collectively referred to as approximate numeric types.
 *  - Exact numeric types and approximate numeric types are collectively referred to as numeric types. Values of numeric types are referred to as numbers.
 *  - The data types TIME WITHOUT TIME ZONE and TIME WITH TIME ZONE are collectively referred to as time types (or, for emphasis, as time with or without time zone).
 *  - The data types TIMESTAMP WITHOUT TIME ZONE and TIMESTAMP WITH TIME ZONE are collectively referred to as timestamp types (or, for emphasis, as timestamp with or without time zone).
 *  - The data types DATE , TIME , and TIMESTAMP are collectively referred to as datetime types.
 *  - Values of datetime types are referred to as datetimes.
 *  - The data type INTERVAL is referred to as an interval type. Values of interval types are called intervals.
 * 
 * Each data type has an associated data type descriptor; the contents of a data type descriptor are determined by the specific data type that it describes. A data type descriptor includes an identification of the data type and all information needed to characterize a value of that data type. Subclause 6.1, "<data type> ", describes the semantic properties of each data type.
 * 
 * A structured type ST is directly based on a data type DT if any of the following are true:
 *  - DT is the declared type of some attribute of ST.
 *  - DT is a direct supertype of ST.
 *  - DT is a direct subtype of ST.
 *  - DT is compatible with ST.
 * 
 * A collection type CT is directly based on a data type DT if DT is the element type of CT.
 * A row type RT is directly based on a data type DT if DT is the declared type of some field (or the data type of the domain of some field) whose descriptor is included in the descriptor of RT.
 * A data type DT1 is based on a data type DT2 if DT1 is directly based on DT2 or DT1 is directly based on some data type that is based on DT2.
 * 
 * A type TY is usage-dependent on a user-defined type UDT if one of the following conditions is true:
 *  - TY is UDT.
 *  - TY is a reference type whose referenced type is UDT.
 *  - TY is a row type, and the declared type of a field of TY is usage-dependent on UDT.
 *  - TY is a collection type, and the declared element type of TY is usage-dependent on UDT.
 * 
 * Each host language has its own data types, which are separate and distinct from SQL data types, even though similar names may be used to describe the data types. Mappings of SQL data types to data types in host languages are described in Subclause 11.50, "<SQL-invoked routine> ", and Subclause 20.1, "<embedded SQL host program> ". Not every SQL data type has a corresponding data type in every host language. Ordering and comparison of values of the predefined data types requires knowledge only about those predefined data types. However, to be able to compare and order values of constructed types or of user-defined types, additional information is required. We say that some type T is S-ordered, for some set of types S, if, in order to compare and order values of type T, information about ordering at least one of the types in S is first required.
 * 
 * A definition of S-ordered is required for several S (that is, for several sets of types), but not for all possible such sets.
 * The general definition of S-ordered is this:
 * Let T be a type and let S be a set of types. T is S-ordered if one of the following is true:
 * - T is a member of S.
 * - T is a row type and the declared type of some field of T is S-ordered.
 * - T is a collection type and the element type of T is S-ordered.
 * - T is a structured type whose comparison form is STATE and the declared type of some attribute of T is S-ordered.
 * - T is a user-defined type whose comparison form is MAP and the return type of the SQL-invoked function that is identified by the <map function specification> is S-ordered.
 * - T is a reference type with a derived representation and the declared type of some attribute enumerated by the <derived representation> is S-ordered.
 * 
 * The notion of S-ordered is applied in the following definitions:
 * - A type T is LOB-ordered if T is L-ordered, where L is the set of large object types.
 * - A type T is array-ordered if T is ARR-ordered, where ARR is the set of array types.
 * - A type T is multiset-ordered if T is MUL-ordered, where MUL is the set of multiset types.
 * - A type T is reference-ordered if T is REF-ordered, where REF is the set of reference types.
 * - A type T is DT-EC-ordered if T is DTE-ordered, where DTE is the set of distinct types with EQUALS ONLY comparison form (DT-EC stands for "distinct type-equality comparison").
 * - A type T is DT-FC-ordered if T is DTF-ordered, where DTF is the set of distinct types with FULL comparison form.
 * - A type T is DT-NC-ordered if T is DTN-ordered, where DTN is the set of distinct types with no comparison form.
 * - A type T is ST-EC-ordered if T is STE-ordered, where STE is the set of structured types with EQUALS ONLY comparison form.
 * - A type T is ST-FC-ordered if T is STF-ordered, where STF is the set of structured types with FULL comparison form.
 * - A type T is ST-NC-ordered if T is STN-ordered, where STN is the set of structured types with no comparison form.
 * - A type T is ST-ordered if T is ST-EC-ordered, ST-FC-ordered or ST-NC-ordered.
 * - A type T is UDT-EC-ordered if T is either DT-EC-ordered or ST-EC-ordered (UDT stands for "user-defined type").
 * - A type T is UDT-FC-ordered if T is either DT-FC-ordered or ST-FC-ordered
 * - A type T is UDT-NC-ordered if T is either DT-NC-ordered or ST-NC-ordered.
 * 
 * The notion of constituent of a declared type DT is defined recursively as follows:
 * - DT is a constituent of DT.
 * - If DT is a row type, then the declared type of each field of DT is a constituent of DT.
 * - If DT is a collection type, then the element type of DT is a constituent of DT.
 * - Every constituent of a constituent of DT is a constituent of DT.
 * 
 * Two data types, T1 and T2, are said to be compatible if T1 is assignable to T2, T2 is assignable to T1, and their descriptors include the same data type name. If they are row types, it shall further be the case that the declared types of their corresponding fields are pairwise compatible. If they are collection types, it shall further be the case that their element types are compatible. If they are reference types, it shall further be the case that their referenced types are compatible.
 * NOTE 8 - The data types "CHARACTER (n) CHARACTER SET CS1" and "CHARACTER (m) CHARACTER SET CS2", where CS1 1 CS2, have descriptors that include the same data type name (CHARACTER ), but are not mutually assignable; therefore, they are not compatible.
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataType()
 * @model abstract="true"
 * @generated
 */
public interface DataType extends SQLObject{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method is used to set the type on the given Column.  It is implemented on the child classes of DataType.  The concrete children classses will know which type relationship to use on Column to set the data type.
	 * <!-- end-model-doc -->
	 * @model 
	 * @generated
	 */
	void setContainer(TypedElement newContainer);

} // DataType
