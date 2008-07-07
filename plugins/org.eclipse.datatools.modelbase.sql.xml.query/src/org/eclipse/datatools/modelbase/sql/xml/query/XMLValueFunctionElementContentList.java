/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionElementContentList.java,v 1.2 2005/12/22 22:21:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Element Content List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getNullHandlingOption <em>Null Handling Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getValueFunctionElement <em>Value Function Element</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getElementContentListChildren <em>Element Content List Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElementContentList()
 * @model
 * @generated
 */
public interface XMLValueFunctionElementContentList extends SQLQueryObject{
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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElementContentList_NullHandlingOption()
     * @model
     * @generated
     */
    XMLNullHandlingType getNullHandlingOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getNullHandlingOption <em>Null Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Null Handling Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType
     * @see #getNullHandlingOption()
     * @generated
     */
    void setNullHandlingOption(XMLNullHandlingType value);

	/**
     * Returns the value of the '<em><b>Value Function Element</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementContentList <em>Element Content List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Element</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Element</em>' container reference.
     * @see #setValueFunctionElement(XMLValueFunctionElement)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElementContentList_ValueFunctionElement()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementContentList
     * @model opposite="elementContentList" required="true"
     * @generated
     */
    XMLValueFunctionElement getValueFunctionElement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getValueFunctionElement <em>Value Function Element</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Element</em>' container reference.
     * @see #getValueFunctionElement()
     * @generated
     */
    void setValueFunctionElement(XMLValueFunctionElement value);

	/**
     * Returns the value of the '<em><b>Element Content List Children</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem#getElementContentList <em>Element Content List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element Content List Children</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Element Content List Children</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionElementContentList_ElementContentListChildren()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem#getElementContentList
     * @model type="org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem" opposite="elementContentList" containment="true"
     * @generated
     */
    EList getElementContentListChildren();

} // XMLValueFunctionElementContentList
