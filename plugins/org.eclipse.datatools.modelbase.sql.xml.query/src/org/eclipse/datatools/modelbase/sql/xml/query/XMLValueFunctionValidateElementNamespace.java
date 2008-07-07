/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateElementNamespace.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Validate Element Namespace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#isNoNamespace <em>No Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getNamespaceURI <em>Namespace URI</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getValidateElement <em>Validate Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElementNamespace()
 * @model
 * @generated
 */
public interface XMLValueFunctionValidateElementNamespace extends SQLQueryObject{
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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElementNamespace_NoNamespace()
     * @model default="false"
     * @generated
     */
    boolean isNoNamespace();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#isNoNamespace <em>No Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>No Namespace</em>' attribute.
     * @see #isNoNamespace()
     * @generated
     */
    void setNoNamespace(boolean value);

	/**
     * Returns the value of the '<em><b>Namespace URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespace URI</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespace URI</em>' attribute.
     * @see #setNamespaceURI(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElementNamespace_NamespaceURI()
     * @model
     * @generated
     */
    String getNamespaceURI();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getNamespaceURI <em>Namespace URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Namespace URI</em>' attribute.
     * @see #getNamespaceURI()
     * @generated
     */
    void setNamespaceURI(String value);

	/**
     * Returns the value of the '<em><b>Validate Element</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementNamespace <em>Validate Element Namespace</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validate Element</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validate Element</em>' container reference.
     * @see #setValidateElement(XMLValueFunctionValidateElement)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElementNamespace_ValidateElement()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementNamespace
     * @model opposite="validateElementNamespace" required="true"
     * @generated
     */
    XMLValueFunctionValidateElement getValidateElement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getValidateElement <em>Validate Element</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validate Element</em>' container reference.
     * @see #getValidateElement()
     * @generated
     */
    void setValidateElement(XMLValueFunctionValidateElement value);

} // XMLValueFunctionValidateElementNamespace
