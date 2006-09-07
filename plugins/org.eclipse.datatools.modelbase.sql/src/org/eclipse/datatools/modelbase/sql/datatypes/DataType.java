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
 * Reference: 5WD-02-Foundation-2002-12 4.1 Data types
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataType()
 * @model abstract="true"
 * @generated
 */
public interface DataType extends SQLObject {
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
