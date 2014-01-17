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
 * A representation of the model object '<em><b>Security Policy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getNotAuthorizedWrite <em>Not Authorized Write</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getComponents <em>Components</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getLabels <em>Labels</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityPolicy()
 * @model
 * @generated
 */
public interface LUWSecurityPolicy extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Not Authorized Write</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not Authorized Write</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not Authorized Write</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction
	 * @see #setNotAuthorizedWrite(LUWSecurityLabelNotAuthorizedWriteAction)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityPolicy_NotAuthorizedWrite()
	 * @model
	 * @generated
	 */
	LUWSecurityLabelNotAuthorizedWriteAction getNotAuthorizedWrite();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getNotAuthorizedWrite <em>Not Authorized Write</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Not Authorized Write</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction
	 * @see #getNotAuthorizedWrite()
	 * @generated
	 */
	void setNotAuthorizedWrite(LUWSecurityLabelNotAuthorizedWriteAction value);

	/**
	 * Returns the value of the '<em><b>Components</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getLUWSecurityPolicy <em>LUW Security Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityPolicy_Components()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getLUWSecurityPolicy
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent" opposite="LUWSecurityPolicy"
	 * @generated
	 */
	EList getComponents();

	/**
	 * Returns the value of the '<em><b>Labels</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Labels</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Labels</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityPolicy_Labels()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel#getPolicy
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel" opposite="policy"
	 * @generated
	 */
	EList getLabels();

	/**
	 * Returns the value of the '<em><b>Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getSecurityPolicy <em>Security Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' reference.
	 * @see #setTable(LUWTable)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWSecurityPolicy_Table()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getSecurityPolicy
	 * @model opposite="securityPolicy" required="true"
	 * @generated
	 */
	LUWTable getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getTable <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(LUWTable value);

} // LUWSecurityPolicy
