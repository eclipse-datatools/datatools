/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XMLSchema.java,v 1.5 2007/01/15 18:40:06 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2XML Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * XML Schema comprises of one or more XMLSchema documents.  Users register XML schemas and not just schema documents (from LI2934)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getDecomposition <em>Decomposition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getStatus <em>Status</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getXmlSchemaDocs <em>Xml Schema Docs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchema()
 * @model
 * @generated
 */
public interface DB2XMLSchema extends DB2XSRObject {
	/**
	 * Returns the value of the '<em><b>Decomposition</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition}.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The states can either be Yes, No or Inoperative and thus need to be represented by an Enumeration instead of a boolean. Represented in the database:
	 * Y=enabled
	 * N=not enabled
	 * X=inoperative
	 * 
	 * Use enumerated types for the three states.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Decomposition</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition
	 * @see #setDecomposition(DB2XMLSchemaDecomposition)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchema_Decomposition()
	 * @model
	 * @generated
	 */
   DB2XMLSchemaDecomposition getDecomposition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getDecomposition <em>Decomposition</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decomposition</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition
	 * @see #getDecomposition()
	 * @generated
	 */
   void setDecomposition(DB2XMLSchemaDecomposition value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus}.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Status</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus
	 * @see #setStatus(DB2XMLSchemaStatus)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchema_Status()
	 * @model
	 * @generated
	 */
   DB2XMLSchemaStatus getStatus();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus
	 * @see #getStatus()
	 * @generated
	 */
   void setStatus(DB2XMLSchemaStatus value);

	/**
	 * Returns the value of the '<em><b>Xml Schema Docs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchema <em>Xml Schema</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Xml Schema Docs</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Schema Docs</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchema_XmlSchemaDocs()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument" opposite="xmlSchema" containment="true" required="true"
	 * @generated
	 */
   EList getXmlSchemaDocs();

} // DB2XMLSchema
