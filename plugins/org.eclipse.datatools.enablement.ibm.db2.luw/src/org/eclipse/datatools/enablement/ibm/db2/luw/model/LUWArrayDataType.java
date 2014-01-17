/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWArrayDataType.java,v 1.1 2009/02/16 19:01:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType#getArrayIndexElementType <em>Array Index Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWArrayDataType()
 * @model
 * @generated
 */
public interface LUWArrayDataType extends ArrayDataType, UserDefinedType {

	/**
	 * Returns the value of the '<em><b>Array Index Element Type</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType#getLUWArrayDataType <em>LUW Array Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Index Element Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Index Element Type</em>' containment reference.
	 * @see #setArrayIndexElementType(ArrayIndexElementType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWArrayDataType_ArrayIndexElementType()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType#getLUWArrayDataType
	 * @model opposite="LUWArrayDataType" containment="true"
	 * @generated
	 */
	ArrayIndexElementType getArrayIndexElementType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType#getArrayIndexElementType <em>Array Index Element Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Index Element Type</em>' containment reference.
	 * @see #getArrayIndexElementType()
	 * @generated
	 */
	void setArrayIndexElementType(ArrayIndexElementType value);
} // LUWArrayDataType
