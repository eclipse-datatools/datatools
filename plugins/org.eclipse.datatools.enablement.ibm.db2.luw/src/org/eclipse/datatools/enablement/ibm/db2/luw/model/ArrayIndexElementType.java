/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.datatypes.ElementType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Index Element Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType#getLUWArrayDataType <em>LUW Array Data Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getArrayIndexElementType()
 * @model
 * @generated
 */
public interface ArrayIndexElementType extends ElementType {
	/**
	 * Returns the value of the '<em><b>LUW Array Data Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType#getArrayIndexElementType <em>Array Index Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LUW Array Data Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LUW Array Data Type</em>' container reference.
	 * @see #setLUWArrayDataType(LUWArrayDataType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getArrayIndexElementType_LUWArrayDataType()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType#getArrayIndexElementType
	 * @model opposite="arrayIndexElementType" required="true"
	 * @generated
	 */
	LUWArrayDataType getLUWArrayDataType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType#getLUWArrayDataType <em>LUW Array Data Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LUW Array Data Type</em>' container reference.
	 * @see #getLUWArrayDataType()
	 * @generated
	 */
	void setLUWArrayDataType(LUWArrayDataType value);

} // ArrayIndexElementType
