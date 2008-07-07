/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionPI.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function PI</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates an XML value consisting of an XML processing instruction based on the given string expression.  See ISO SQL/XML sec. 6.16.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getTargetName <em>Target Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getPIContent <em>PI Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionPI()
 * @model
 * @generated
 */
public interface XMLValueFunctionPI extends XMLValueFunction{
	/**
     * Returns the value of the '<em><b>Target Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Name</em>' attribute.
     * @see #setTargetName(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionPI_TargetName()
     * @model
     * @generated
     */
    String getTargetName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getTargetName <em>Target Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Name</em>' attribute.
     * @see #getTargetName()
     * @generated
     */
    void setTargetName(String value);

	/**
     * Returns the value of the '<em><b>Returning Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Returning Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Returning Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @see #setReturningOption(XMLReturningType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionPI_ReturningOption()
     * @model
     * @generated
     */
    XMLReturningType getReturningOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getReturningOption <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Returning Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @see #getReturningOption()
     * @generated
     */
    void setReturningOption(XMLReturningType value);

	/**
     * Returns the value of the '<em><b>PI Content</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent#getValueFunctionPI <em>Value Function PI</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>PI Content</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>PI Content</em>' containment reference.
     * @see #setPIContent(XMLValueFunctionPIContent)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionPI_PIContent()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent#getValueFunctionPI
     * @model opposite="valueFunctionPI" containment="true"
     * @generated
     */
    XMLValueFunctionPIContent getPIContent();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getPIContent <em>PI Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>PI Content</em>' containment reference.
     * @see #getPIContent()
     * @generated
     */
    void setPIContent(XMLValueFunctionPIContent value);

} // XMLValueFunctionPI
