/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespaceDeclarationPrefix.java,v 1.4 2005/10/22 01:40:25 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Namespace Declaration Prefix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix#getPrefix <em>Prefix</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage#getXMLNamespaceDeclarationPrefix()
 * @model
 * @generated
 */
public interface XMLNamespaceDeclarationPrefix extends XMLNamespaceDeclarationItem{
	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Prefix</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage#getXMLNamespaceDeclarationPrefix_Prefix()
	 * @model
	 * @generated
	 */
    String getPrefix();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
    void setPrefix(String value);

} // XMLNamespaceDeclarationPrefix
