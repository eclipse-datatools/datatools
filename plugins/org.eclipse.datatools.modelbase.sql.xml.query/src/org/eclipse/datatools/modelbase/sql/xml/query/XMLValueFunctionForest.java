/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionForest.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Forest</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates an XML value containing a sequence of XML elements.  See ISO SQL/XML sec. 6.14.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNullHandlingOption <em>Null Handling Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getForestContentList <em>Forest Content List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNamespacesDecl <em>Namespaces Decl</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionForest()
 * @model
 * @generated
 */
public interface XMLValueFunctionForest extends XMLValueFunction{
	/**
     * Returns the value of the '<em><b>Null Handling Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Null Handling Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Null Handling Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType
     * @see #setNullHandlingOption(XMLNullHandlingType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionForest_NullHandlingOption()
     * @model
     * @generated
     */
    XMLNullHandlingType getNullHandlingOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNullHandlingOption <em>Null Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Null Handling Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType
     * @see #getNullHandlingOption()
     * @generated
     */
    void setNullHandlingOption(XMLNullHandlingType value);

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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionForest_ReturningOption()
     * @model
     * @generated
     */
    XMLReturningType getReturningOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getReturningOption <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Returning Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @see #getReturningOption()
     * @generated
     */
    void setReturningOption(XMLReturningType value);

	/**
     * Returns the value of the '<em><b>Forest Content List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem#getValueFunctionForest <em>Value Function Forest</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Forest Content List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Forest Content List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionForest_ForestContentList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem#getValueFunctionForest
     * @model type="org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem" opposite="valueFunctionForest" containment="true"
     * @generated
     */
    EList getForestContentList();

	/**
     * Returns the value of the '<em><b>Namespaces Decl</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionForest <em>Value Function Forest</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespaces Decl</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespaces Decl</em>' containment reference.
     * @see #setNamespacesDecl(XMLNamespacesDeclaration)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionForest_NamespacesDecl()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionForest
     * @model opposite="valueFunctionForest" containment="true"
     * @generated
     */
    XMLNamespacesDeclaration getNamespacesDecl();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNamespacesDecl <em>Namespaces Decl</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Namespaces Decl</em>' containment reference.
     * @see #getNamespacesDecl()
     * @generated
     */
    void setNamespacesDecl(XMLNamespacesDeclaration value);

} // XMLValueFunctionForest
