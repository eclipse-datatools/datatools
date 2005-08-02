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
 * A representation of the model object '<em><b>Distinct User Defined Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.7 User-defined types
 * 
 * A user-defined type is a schema object, identified by a <user-defined type name> . The definition of a userdefined type specifies a number of components, including in particular a list of attribute definitions. Although the attribute definitions are said to define the representation of the user-defined type, in fact they implicitly define certain functions (observers and mutators) that are part of the interface of the user-defined type; physical representations of user-defined type values are implementation-dependent.
 * 
 * The representation of a user-defined type is expressed either as a single data type (some predefined data type, called the source type), in which case the user-defined type is said to be a distinct type, or as a list of attribute definitions, in which case it is said to be a structured type.
 * 
 * A user-defined type is described by a user-defined type descriptor. A user-defined type descriptor contains:
 *  - The name of the user-defined type (<user-defined type name> ). This is the type designator of that type, used in type precedence lists (see Subclause 9.5, "Type precedence list determination").
 *  - An indication of whether the user-defined type is a structured type or a distinct type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType#getPredefinedRepresentation <em>Predefined Representation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDistinctUserDefinedType()
 * @model 
 * @generated
 */
public interface DistinctUserDefinedType extends UserDefinedType{
	/**
	 * Returns the value of the '<em><b>Predefined Representation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predefined Representation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predefined Representation</em>' containment reference.
	 * @see #setPredefinedRepresentation(PredefinedDataType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDistinctUserDefinedType_PredefinedRepresentation()
	 * @model containment="true" required="true"
	 * @generated
	 */
	PredefinedDataType getPredefinedRepresentation();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType#getPredefinedRepresentation <em>Predefined Representation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predefined Representation</em>' containment reference.
	 * @see #getPredefinedRepresentation()
	 * @generated
	 */
	void setPredefinedRepresentation(PredefinedDataType value);

} // DistinctUserDefinedType
