/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLSerializeFunction.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Serialize Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getContentOption <em>Content Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeVersion <em>Serialize Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getDeclarationOption <em>Declaration Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeTarget <em>Serialize Target</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeEncoding <em>Serialize Encoding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunction()
 * @model
 * @generated
 */
public interface XMLSerializeFunction extends ValueExpressionFunction{
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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunction_ContentOption()
     * @model
     * @generated
     */
    XMLContentType getContentOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getContentOption <em>Content Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Content Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType
     * @see #getContentOption()
     * @generated
     */
    void setContentOption(XMLContentType value);

	/**
     * Returns the value of the '<em><b>Serialize Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Serialize Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Serialize Version</em>' attribute.
     * @see #setSerializeVersion(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunction_SerializeVersion()
     * @model
     * @generated
     */
    String getSerializeVersion();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeVersion <em>Serialize Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Serialize Version</em>' attribute.
     * @see #getSerializeVersion()
     * @generated
     */
    void setSerializeVersion(String value);

	/**
     * Returns the value of the '<em><b>Declaration Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Declaration Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Declaration Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType
     * @see #setDeclarationOption(XMLDeclarationType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunction_DeclarationOption()
     * @model
     * @generated
     */
    XMLDeclarationType getDeclarationOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getDeclarationOption <em>Declaration Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Declaration Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType
     * @see #getDeclarationOption()
     * @generated
     */
    void setDeclarationOption(XMLDeclarationType value);

	/**
     * Returns the value of the '<em><b>Serialize Target</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getSerializeFunction <em>Serialize Function</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Serialize Target</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Serialize Target</em>' containment reference.
     * @see #setSerializeTarget(XMLSerializeFunctionTarget)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunction_SerializeTarget()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getSerializeFunction
     * @model opposite="serializeFunction" containment="true" required="true"
     * @generated
     */
    XMLSerializeFunctionTarget getSerializeTarget();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeTarget <em>Serialize Target</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Serialize Target</em>' containment reference.
     * @see #getSerializeTarget()
     * @generated
     */
    void setSerializeTarget(XMLSerializeFunctionTarget value);

	/**
     * Returns the value of the '<em><b>Serialize Encoding</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Serialize Encoding</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Serialize Encoding</em>' containment reference.
     * @see #setSerializeEncoding(XMLSerializeFunctionEncoding)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunction_SerializeEncoding()
     * @model containment="true"
     * @generated
     */
    XMLSerializeFunctionEncoding getSerializeEncoding();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeEncoding <em>Serialize Encoding</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Serialize Encoding</em>' containment reference.
     * @see #getSerializeEncoding()
     * @generated
     */
    void setSerializeEncoding(XMLSerializeFunctionEncoding value);

} // XMLSerializeFunction
