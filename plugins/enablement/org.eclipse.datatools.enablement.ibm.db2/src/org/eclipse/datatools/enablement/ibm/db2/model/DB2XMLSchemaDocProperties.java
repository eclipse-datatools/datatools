/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XMLSchemaDocProperties.java,v 1.5 2007/10/12 23:05:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2XML Schema Doc Properties</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Users can specify properties for each XML Schema document and these are stored as a BLOB(5M) in the XSR. Properties come as a name and value pair.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getXmlSchemaDoc <em>Xml Schema Doc</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocProperties()
 * @model
 * @generated
 */
public interface DB2XMLSchemaDocProperties extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * value of the property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocProperties_Value()
	 * @model
	 * @generated
	 */
   String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
   void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Xml Schema Doc</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchemaDocProperties <em>Xml Schema Doc Properties</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Xml Schema Doc</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Schema Doc</em>' container reference.
	 * @see #setXmlSchemaDoc(DB2XMLSchemaDocument)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2XMLSchemaDocProperties_XmlSchemaDoc()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchemaDocProperties
	 * @model opposite="xmlSchemaDocProperties" required="true"
	 * @generated
	 */
   DB2XMLSchemaDocument getXmlSchemaDoc();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getXmlSchemaDoc <em>Xml Schema Doc</em>}' container reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Schema Doc</em>' container reference.
	 * @see #getXmlSchemaDoc()
	 * @generated
	 */
   void setXmlSchemaDoc(DB2XMLSchemaDocument value);

} // DB2XMLSchemaDocProperties
