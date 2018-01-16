/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionElement.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates an XML element with a given element name, list of XML attributes, and list of content values.  See ISO SQL/XML sec. 6.13.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementName <em>Element Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getNamespacesDecl <em>Namespaces Decl</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getAttributesDecl <em>Attributes Decl</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementContentList <em>Element Content List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElement()
 * @model
 * @generated
 */
public interface XMLValueFunctionElement extends XMLValueFunction{
	/**
     * Returns the value of the '<em><b>Element Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Element Name</em>' attribute.
     * @see #setElementName(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElement_ElementName()
     * @model
     * @generated
     */
    String getElementName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementName <em>Element Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Element Name</em>' attribute.
     * @see #getElementName()
     * @generated
     */
    void setElementName(String value);

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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElement_ReturningOption()
     * @model
     * @generated
     */
    XMLReturningType getReturningOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getReturningOption <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Returning Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @see #getReturningOption()
     * @generated
     */
    void setReturningOption(XMLReturningType value);

	/**
     * Returns the value of the '<em><b>Namespaces Decl</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionElement <em>Value Function Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespaces Decl</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespaces Decl</em>' containment reference.
     * @see #setNamespacesDecl(XMLNamespacesDeclaration)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElement_NamespacesDecl()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionElement
     * @model opposite="valueFunctionElement" containment="true"
     * @generated
     */
    XMLNamespacesDeclaration getNamespacesDecl();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getNamespacesDecl <em>Namespaces Decl</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Namespaces Decl</em>' containment reference.
     * @see #getNamespacesDecl()
     * @generated
     */
    void setNamespacesDecl(XMLNamespacesDeclaration value);

	/**
     * Returns the value of the '<em><b>Attributes Decl</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getValueFunctionElement <em>Value Function Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attributes Decl</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes Decl</em>' containment reference.
     * @see #setAttributesDecl(XMLAttributesDeclaration)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElement_AttributesDecl()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getValueFunctionElement
     * @model opposite="valueFunctionElement" containment="true"
     * @generated
     */
    XMLAttributesDeclaration getAttributesDecl();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getAttributesDecl <em>Attributes Decl</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Attributes Decl</em>' containment reference.
     * @see #getAttributesDecl()
     * @generated
     */
    void setAttributesDecl(XMLAttributesDeclaration value);

	/**
     * Returns the value of the '<em><b>Element Content List</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getValueFunctionElement <em>Value Function Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element Content List</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Element Content List</em>' containment reference.
     * @see #setElementContentList(XMLValueFunctionElementContentList)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElement_ElementContentList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getValueFunctionElement
     * @model opposite="valueFunctionElement" containment="true"
     * @generated
     */
    XMLValueFunctionElementContentList getElementContentList();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementContentList <em>Element Content List</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Element Content List</em>' containment reference.
     * @see #getElementContentList()
     * @generated
     */
    void setElementContentList(XMLValueFunctionElementContentList value);

} // XMLValueFunctionElement
