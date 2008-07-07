/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionParse.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Parse</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates an XML value by performing a non-validating parse of a given string expression.  See ISO SQL/XML sec. 6.15.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getContentOption <em>Content Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getWhitespaceHandlingOption <em>Whitespace Handling Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getParseContent <em>Parse Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionParse()
 * @model
 * @generated
 */
public interface XMLValueFunctionParse extends XMLValueFunction{
	/**
     * Returns the value of the '<em><b>Content Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Content Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Content Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType
     * @see #setContentOption(XMLContentType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionParse_ContentOption()
     * @model
     * @generated
     */
    XMLContentType getContentOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getContentOption <em>Content Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Content Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType
     * @see #getContentOption()
     * @generated
     */
    void setContentOption(XMLContentType value);

	/**
     * Returns the value of the '<em><b>Whitespace Handling Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Whitespace Handling Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Whitespace Handling Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType
     * @see #setWhitespaceHandlingOption(XMLWhitespaceHandlingType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionParse_WhitespaceHandlingOption()
     * @model
     * @generated
     */
    XMLWhitespaceHandlingType getWhitespaceHandlingOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getWhitespaceHandlingOption <em>Whitespace Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Whitespace Handling Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType
     * @see #getWhitespaceHandlingOption()
     * @generated
     */
    void setWhitespaceHandlingOption(XMLWhitespaceHandlingType value);

	/**
     * Returns the value of the '<em><b>Parse Content</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueFunctionParse <em>Value Function Parse</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parse Content</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parse Content</em>' containment reference.
     * @see #setParseContent(XMLValueFunctionParseContent)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionParse_ParseContent()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueFunctionParse
     * @model opposite="valueFunctionParse" containment="true" required="true"
     * @generated
     */
    XMLValueFunctionParseContent getParseContent();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getParseContent <em>Parse Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parse Content</em>' containment reference.
     * @see #getParseContent()
     * @generated
     */
    void setParseContent(XMLValueFunctionParseContent value);

} // XMLValueFunctionParse
