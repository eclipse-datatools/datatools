/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespacesDeclaration.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Namespaces Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getNamespaceDecltemList <em>Namespace Decltem List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionElement <em>Value Function Element</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionForest <em>Value Function Forest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getTableFunction <em>Table Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespacesDeclaration()
 * @model
 * @generated
 */
public interface XMLNamespacesDeclaration extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Namespace Decltem List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getNamespacesDecl <em>Namespaces Decl</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespace Decltem List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespace Decltem List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespacesDeclaration_NamespaceDecltemList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getNamespacesDecl
     * @model type="org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem" opposite="namespacesDecl" containment="true" required="true"
     * @generated
     */
    EList getNamespaceDecltemList();

	/**
     * Returns the value of the '<em><b>Value Function Element</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getNamespacesDecl <em>Namespaces Decl</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Element</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Element</em>' container reference.
     * @see #setValueFunctionElement(XMLValueFunctionElement)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespacesDeclaration_ValueFunctionElement()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getNamespacesDecl
     * @model opposite="namespacesDecl" required="true"
     * @generated
     */
    XMLValueFunctionElement getValueFunctionElement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionElement <em>Value Function Element</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Element</em>' container reference.
     * @see #getValueFunctionElement()
     * @generated
     */
    void setValueFunctionElement(XMLValueFunctionElement value);

	/**
     * Returns the value of the '<em><b>Value Function Forest</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNamespacesDecl <em>Namespaces Decl</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Forest</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Forest</em>' container reference.
     * @see #setValueFunctionForest(XMLValueFunctionForest)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespacesDeclaration_ValueFunctionForest()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNamespacesDecl
     * @model opposite="namespacesDecl" required="true"
     * @generated
     */
    XMLValueFunctionForest getValueFunctionForest();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionForest <em>Value Function Forest</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Forest</em>' container reference.
     * @see #getValueFunctionForest()
     * @generated
     */
    void setValueFunctionForest(XMLValueFunctionForest value);

	/**
     * Returns the value of the '<em><b>Table Function</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getNamespacesDecl <em>Namespaces Decl</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Function</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Function</em>' container reference.
     * @see #setTableFunction(XMLTableFunction)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespacesDeclaration_TableFunction()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getNamespacesDecl
     * @model opposite="namespacesDecl" required="true"
     * @generated
     */
    XMLTableFunction getTableFunction();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getTableFunction <em>Table Function</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Function</em>' container reference.
     * @see #getTableFunction()
     * @generated
     */
    void setTableFunction(XMLTableFunction value);

} // XMLNamespacesDeclaration
