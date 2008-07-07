/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateElement.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Validate Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementNamespace <em>Validate Element Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementName <em>Validate Element Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateAccordingTo <em>Validate According To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElement()
 * @model
 * @generated
 */
public interface XMLValueFunctionValidateElement extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Validate Element Namespace</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getValidateElement <em>Validate Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validate Element Namespace</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validate Element Namespace</em>' containment reference.
     * @see #setValidateElementNamespace(XMLValueFunctionValidateElementNamespace)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElement_ValidateElementNamespace()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getValidateElement
     * @model opposite="validateElement" containment="true"
     * @generated
     */
    XMLValueFunctionValidateElementNamespace getValidateElementNamespace();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementNamespace <em>Validate Element Namespace</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validate Element Namespace</em>' containment reference.
     * @see #getValidateElementNamespace()
     * @generated
     */
    void setValidateElementNamespace(XMLValueFunctionValidateElementNamespace value);

	/**
     * Returns the value of the '<em><b>Validate Element Name</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName#getValidateElement <em>Validate Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validate Element Name</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validate Element Name</em>' containment reference.
     * @see #setValidateElementName(XMLValueFunctionValidateElementName)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElement_ValidateElementName()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName#getValidateElement
     * @model opposite="validateElement" containment="true"
     * @generated
     */
    XMLValueFunctionValidateElementName getValidateElementName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementName <em>Validate Element Name</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validate Element Name</em>' containment reference.
     * @see #getValidateElementName()
     * @generated
     */
    void setValidateElementName(XMLValueFunctionValidateElementName value);

	/**
     * Returns the value of the '<em><b>Validate According To</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValidateElement <em>Validate Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validate According To</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validate According To</em>' container reference.
     * @see #setValidateAccordingTo(XMLValueFunctionValidateAccordingTo)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElement_ValidateAccordingTo()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValidateElement
     * @model opposite="validateElement" required="true"
     * @generated
     */
    XMLValueFunctionValidateAccordingTo getValidateAccordingTo();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateAccordingTo <em>Validate According To</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validate According To</em>' container reference.
     * @see #getValidateAccordingTo()
     * @generated
     */
    void setValidateAccordingTo(XMLValueFunctionValidateAccordingTo value);

} // XMLValueFunctionValidateElement
