/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespaceDeclarationItem.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Namespace Declaration Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getNamespacesDecl <em>Namespaces Decl</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespaceDeclarationItem()
 * @model
 * @generated
 */
public interface XMLNamespaceDeclarationItem extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Uri</em>' attribute.
     * @see #setUri(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespaceDeclarationItem_Uri()
     * @model
     * @generated
     */
    String getUri();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getUri <em>Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Uri</em>' attribute.
     * @see #getUri()
     * @generated
     */
    void setUri(String value);

	/**
     * Returns the value of the '<em><b>Namespaces Decl</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getNamespaceDecltemList <em>Namespace Decltem List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespaces Decl</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespaces Decl</em>' container reference.
     * @see #setNamespacesDecl(XMLNamespacesDeclaration)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespaceDeclarationItem_NamespacesDecl()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getNamespaceDecltemList
     * @model opposite="namespaceDecltemList" required="true"
     * @generated
     */
    XMLNamespacesDeclaration getNamespacesDecl();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getNamespacesDecl <em>Namespaces Decl</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Namespaces Decl</em>' container reference.
     * @see #getNamespacesDecl()
     * @generated
     */
    void setNamespacesDecl(XMLNamespacesDeclaration value);

} // XMLNamespaceDeclarationItem
