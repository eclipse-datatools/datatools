/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespaceDeclarationDefault.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Namespace Declaration Default</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault#isNoDefault <em>No Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespaceDeclarationDefault()
 * @model
 * @generated
 */
public interface XMLNamespaceDeclarationDefault extends XMLNamespaceDeclarationItem{
	/**
     * Returns the value of the '<em><b>No Default</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>No Default</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>No Default</em>' attribute.
     * @see #setNoDefault(boolean)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLNamespaceDeclarationDefault_NoDefault()
     * @model default="false"
     * @generated
     */
    boolean isNoDefault();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault#isNoDefault <em>No Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>No Default</em>' attribute.
     * @see #isNoDefault()
     * @generated
     */
    void setNoDefault(boolean value);

} // XMLNamespaceDeclarationDefault
