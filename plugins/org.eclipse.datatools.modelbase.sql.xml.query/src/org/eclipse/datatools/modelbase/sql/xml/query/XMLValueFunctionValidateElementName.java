/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateElementName.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Validate Element Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName#getValidateElement <em>Validate Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElementName()
 * @model
 * @generated
 */
public interface XMLValueFunctionValidateElementName extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Validate Element</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementName <em>Validate Element Name</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validate Element</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validate Element</em>' container reference.
     * @see #setValidateElement(XMLValueFunctionValidateElement)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateElementName_ValidateElement()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementName
     * @model opposite="validateElementName" required="true"
     * @generated
     */
    XMLValueFunctionValidateElement getValidateElement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName#getValidateElement <em>Validate Element</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validate Element</em>' container reference.
     * @see #getValidateElement()
     * @generated
     */
    void setValidateElement(XMLValueFunctionValidateElement value);

} // XMLValueFunctionValidateElementName
