/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateAccordingToURI.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Validate According To URI</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#isNoNamespace <em>No Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getTargetNamespaceURI <em>Target Namespace URI</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getSchemaLocationURI <em>Schema Location URI</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingToURI()
 * @model
 * @generated
 */
public interface XMLValueFunctionValidateAccordingToURI extends XMLValueFunctionValidateAccordingTo{
	/**
     * Returns the value of the '<em><b>No Namespace</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>No Namespace</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>No Namespace</em>' attribute.
     * @see #setNoNamespace(boolean)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingToURI_NoNamespace()
     * @model default="false"
     * @generated
     */
    boolean isNoNamespace();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#isNoNamespace <em>No Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>No Namespace</em>' attribute.
     * @see #isNoNamespace()
     * @generated
     */
    void setNoNamespace(boolean value);

	/**
     * Returns the value of the '<em><b>Target Namespace URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Namespace URI</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Namespace URI</em>' attribute.
     * @see #setTargetNamespaceURI(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingToURI_TargetNamespaceURI()
     * @model
     * @generated
     */
    String getTargetNamespaceURI();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getTargetNamespaceURI <em>Target Namespace URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Namespace URI</em>' attribute.
     * @see #getTargetNamespaceURI()
     * @generated
     */
    void setTargetNamespaceURI(String value);

	/**
     * Returns the value of the '<em><b>Schema Location URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Schema Location URI</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Schema Location URI</em>' attribute.
     * @see #setSchemaLocationURI(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingToURI_SchemaLocationURI()
     * @model
     * @generated
     */
    String getSchemaLocationURI();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getSchemaLocationURI <em>Schema Location URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Schema Location URI</em>' attribute.
     * @see #getSchemaLocationURI()
     * @generated
     */
    void setSchemaLocationURI(String value);

} // XMLValueFunctionValidateAccordingToURI
