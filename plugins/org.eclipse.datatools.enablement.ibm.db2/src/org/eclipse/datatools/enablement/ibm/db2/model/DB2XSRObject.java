/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XSRObject.java,v 1.6 2007/10/12 23:05:35 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2XSR Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Because the database needs to create its own copy of the XML schemas, DTDs and external entities it is using, a XML Schema repository (XSR) is defined and used to hold that information.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XSRObject()
 * @model
 * @generated
 */
public interface DB2XSRObject extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getXsrObjects <em>Xsr Objects</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Schema</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(DB2Schema)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XSRObject_Schema()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getXsrObjects
	 * @model opposite="xsrObjects" required="true"
	 * @generated
	 */
   DB2Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
   void setSchema(DB2Schema value);

} // DB2XSRObject
