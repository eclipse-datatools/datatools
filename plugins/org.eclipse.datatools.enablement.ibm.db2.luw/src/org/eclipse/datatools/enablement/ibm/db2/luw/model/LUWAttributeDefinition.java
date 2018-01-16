/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWAttributeDefinition.java,v 1.4 2007/10/12 23:05:36 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition#isLOBLogged <em>LOB Logged</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition#isLOBCompacted <em>LOB Compacted</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWAttributeDefinition()
 * @model
 * @generated
 */
public interface LUWAttributeDefinition extends AttributeDefinition {
	/**
	 * Returns the value of the '<em><b>LOB Logged</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LOB Logged</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LOB Logged</em>' attribute.
	 * @see #setLOBLogged(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWAttributeDefinition_LOBLogged()
	 * @model default="true"
	 * @generated
	 */
	boolean isLOBLogged();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition#isLOBLogged <em>LOB Logged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LOB Logged</em>' attribute.
	 * @see #isLOBLogged()
	 * @generated
	 */
	void setLOBLogged(boolean value);

	/**
	 * Returns the value of the '<em><b>LOB Compacted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LOB Compacted</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LOB Compacted</em>' attribute.
	 * @see #setLOBCompacted(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWAttributeDefinition_LOBCompacted()
	 * @model
	 * @generated
	 */
	boolean isLOBCompacted();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition#isLOBCompacted <em>LOB Compacted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LOB Compacted</em>' attribute.
	 * @see #isLOBCompacted()
	 * @generated
	 */
	void setLOBCompacted(boolean value);

} // LUWAttributeDefinition
