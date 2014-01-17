/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Security Label Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getLUWSecurityPolicy <em>LUW Security Policy</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityLabelComponent()
 * @model
 * @generated
 */
public interface LUWSecurityLabelComponent extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType
	 * @see #setType(LUWSecurityLabelComponentType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityLabelComponent_Type()
	 * @model
	 * @generated
	 */
	LUWSecurityLabelComponentType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType
	 * @see #getType()
	 * @generated
	 */
	void setType(LUWSecurityLabelComponentType value);

	/**
	 * Returns the value of the '<em><b>LUW Security Policy</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LUW Security Policy</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LUW Security Policy</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityLabelComponent_LUWSecurityPolicy()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getComponents
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy" opposite="components"
	 * @generated
	 */
	EList getLUWSecurityPolicy();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement#getLUWSecurityLabelComponent <em>LUW Security Label Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityLabelComponent_Elements()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement#getLUWSecurityLabelComponent
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement" opposite="LUWSecurityLabelComponent"
	 * @generated
	 */
	EList getElements();

} // LUWSecurityLabelComponent
