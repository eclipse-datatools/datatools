/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XMLSchemaDocument.java,v 1.5 2007/10/12 23:05:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2XML Schema Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * XML Schema documents are used to validate XML documents that are stored in XML type columns.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getSchemaLocation <em>Schema Location</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#isPrimary <em>Primary</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchema <em>Xml Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchemaDocProperties <em>Xml Schema Doc Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocument()
 * @model
 * @generated
 */
public interface DB2XMLSchemaDocument extends SQLObject {
	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the .xsd file
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocument_FileName()
	 * @model
	 * @generated
	 */
   String getFileName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
   void setFileName(String value);

	/**
	 * Returns the value of the '<em><b>Schema Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the URL of the xml schema document
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Schema Location</em>' attribute.
	 * @see #setSchemaLocation(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocument_SchemaLocation()
	 * @model
	 * @generated
	 */
   String getSchemaLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getSchemaLocation <em>Schema Location</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema Location</em>' attribute.
	 * @see #getSchemaLocation()
	 * @generated
	 */
   void setSchemaLocation(String value);

	/**
	 * Returns the value of the '<em><b>Target Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Namespace of the schema document
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Namespace</em>' attribute.
	 * @see #setTargetNamespace(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocument_TargetNamespace()
	 * @model
	 * @generated
	 */
   String getTargetNamespace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getTargetNamespace <em>Target Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Namespace</em>' attribute.
	 * @see #getTargetNamespace()
	 * @generated
	 */
   void setTargetNamespace(String value);

	/**
	 * Returns the value of the '<em><b>Primary</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Each XML schema has 1 primary xml schema document. The targetNamespace and the schemaLocation of the primary document is assigned to the XML schema
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Primary</em>' attribute.
	 * @see #setPrimary(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocument_Primary()
	 * @model
	 * @generated
	 */
   boolean isPrimary();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#isPrimary <em>Primary</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary</em>' attribute.
	 * @see #isPrimary()
	 * @generated
	 */
   void setPrimary(boolean value);

	/**
	 * Returns the value of the '<em><b>Xml Schema</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getXmlSchemaDocs <em>Xml Schema Docs</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Xml Schema</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Schema</em>' container reference.
	 * @see #setXmlSchema(DB2XMLSchema)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocument_XmlSchema()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getXmlSchemaDocs
	 * @model opposite="xmlSchemaDocs" required="true"
	 * @generated
	 */
   DB2XMLSchema getXmlSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchema <em>Xml Schema</em>}' container reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Schema</em>' container reference.
	 * @see #getXmlSchema()
	 * @generated
	 */
   void setXmlSchema(DB2XMLSchema value);

	/**
	 * Returns the value of the '<em><b>Xml Schema Doc Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getXmlSchemaDoc <em>Xml Schema Doc</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Xml Schema Doc Properties</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Schema Doc Properties</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocument_XmlSchemaDocProperties()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getXmlSchemaDoc
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties" opposite="xmlSchemaDoc" containment="true" required="true"
	 * @generated
	 */
   EList getXmlSchemaDocProperties();

} // DB2XMLSchemaDocument
