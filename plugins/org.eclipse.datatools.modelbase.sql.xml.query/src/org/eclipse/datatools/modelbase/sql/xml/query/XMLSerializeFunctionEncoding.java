/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLSerializeFunctionEncoding.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Serialize Function Encoding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding#getEncodingName <em>Encoding Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunctionEncoding()
 * @model
 * @generated
 */
public interface XMLSerializeFunctionEncoding extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Encoding Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Encoding Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Encoding Name</em>' attribute.
     * @see #setEncodingName(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLSerializeFunctionEncoding_EncodingName()
     * @model
     * @generated
     */
    String getEncodingName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding#getEncodingName <em>Encoding Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Encoding Name</em>' attribute.
     * @see #getEncodingName()
     * @generated
     */
    void setEncodingName(String value);

} // XMLSerializeFunctionEncoding
