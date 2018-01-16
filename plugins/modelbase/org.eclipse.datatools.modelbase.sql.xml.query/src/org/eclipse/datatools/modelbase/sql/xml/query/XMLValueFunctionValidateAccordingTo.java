/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateAccordingTo.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Validate According To</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValueFunctionValidate <em>Value Function Validate</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValidateElement <em>Validate Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingTo()
 * @model
 * @generated
 */
public interface XMLValueFunctionValidateAccordingTo extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Value Function Validate</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateAccordingTo <em>Validate According To</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Validate</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Validate</em>' container reference.
     * @see #setValueFunctionValidate(XMLValueFunctionValidate)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingTo_ValueFunctionValidate()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateAccordingTo
     * @model opposite="validateAccordingTo" required="true"
     * @generated
     */
    XMLValueFunctionValidate getValueFunctionValidate();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValueFunctionValidate <em>Value Function Validate</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Validate</em>' container reference.
     * @see #getValueFunctionValidate()
     * @generated
     */
    void setValueFunctionValidate(XMLValueFunctionValidate value);

	/**
     * Returns the value of the '<em><b>Validate Element</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateAccordingTo <em>Validate According To</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validate Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validate Element</em>' containment reference.
     * @see #setValidateElement(XMLValueFunctionValidateElement)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidateAccordingTo_ValidateElement()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateAccordingTo
     * @model opposite="validateAccordingTo" containment="true"
     * @generated
     */
    XMLValueFunctionValidateElement getValidateElement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValidateElement <em>Validate Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validate Element</em>' containment reference.
     * @see #getValidateElement()
     * @generated
     */
    void setValidateElement(XMLValueFunctionValidateElement value);

} // XMLValueFunctionValidateAccordingTo
