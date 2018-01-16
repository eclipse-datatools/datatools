/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidate.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Validate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates an XML value by performing a parse of an XML  expression according to a given XML schema.  See ISO SQL/XML sec. 6.19.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getContentOption <em>Content Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateContent <em>Validate Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateAccordingTo <em>Validate According To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidate()
 * @model
 * @generated
 */
public interface XMLValueFunctionValidate extends XMLValueFunction{
	/**
     * Returns the value of the '<em><b>Content Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Content Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Content Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2
     * @see #setContentOption(XMLContentType2)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidate_ContentOption()
     * @model
     * @generated
     */
    XMLContentType2 getContentOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getContentOption <em>Content Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Content Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2
     * @see #getContentOption()
     * @generated
     */
    void setContentOption(XMLContentType2 value);

	/**
     * Returns the value of the '<em><b>Validate Content</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent#getValueFunctionValidate <em>Value Function Validate</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validate Content</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validate Content</em>' containment reference.
     * @see #setValidateContent(XMLValueFunctionValidateContent)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidate_ValidateContent()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent#getValueFunctionValidate
     * @model opposite="valueFunctionValidate" containment="true" required="true"
     * @generated
     */
    XMLValueFunctionValidateContent getValidateContent();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateContent <em>Validate Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validate Content</em>' containment reference.
     * @see #getValidateContent()
     * @generated
     */
    void setValidateContent(XMLValueFunctionValidateContent value);

	/**
     * Returns the value of the '<em><b>Validate According To</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValueFunctionValidate <em>Value Function Validate</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validate According To</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validate According To</em>' containment reference.
     * @see #setValidateAccordingTo(XMLValueFunctionValidateAccordingTo)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionValidate_ValidateAccordingTo()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValueFunctionValidate
     * @model opposite="valueFunctionValidate" containment="true"
     * @generated
     */
    XMLValueFunctionValidateAccordingTo getValidateAccordingTo();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateAccordingTo <em>Validate According To</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validate According To</em>' containment reference.
     * @see #getValidateAccordingTo()
     * @generated
     */
    void setValidateAccordingTo(XMLValueFunctionValidateAccordingTo value);

} // XMLValueFunctionValidate
