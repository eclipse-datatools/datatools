/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateAccordingToIdentifier.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Validate According To Identifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getSchemaName <em>Schema Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getRegisteredXMLSchemaName <em>Registered XML Schema Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingToIdentifier()
 * @model
 * @generated
 */
public interface XMLValueFunctionValidateAccordingToIdentifier extends XMLValueFunctionValidateAccordingTo{
	/**
     * Returns the value of the '<em><b>Schema Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Schema Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Schema Name</em>' attribute.
     * @see #setSchemaName(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingToIdentifier_SchemaName()
     * @model
     * @generated
     */
    String getSchemaName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getSchemaName <em>Schema Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Schema Name</em>' attribute.
     * @see #getSchemaName()
     * @generated
     */
    void setSchemaName(String value);

	/**
     * Returns the value of the '<em><b>Registered XML Schema Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Registered XML Schema Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Registered XML Schema Name</em>' attribute.
     * @see #setRegisteredXMLSchemaName(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingToIdentifier_RegisteredXMLSchemaName()
     * @model
     * @generated
     */
    String getRegisteredXMLSchemaName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getRegisteredXMLSchemaName <em>Registered XML Schema Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Registered XML Schema Name</em>' attribute.
     * @see #getRegisteredXMLSchemaName()
     * @generated
     */
    void setRegisteredXMLSchemaName(String value);

} // XMLValueFunctionValidateAccordingToIdentifier
