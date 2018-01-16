/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionQueryReturning.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Query Returning</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getPassingOption <em>Passing Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getValueFunctionQuery <em>Value Function Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQueryReturning()
 * @model
 * @generated
 */
public interface XMLValueFunctionQueryReturning extends SQLQueryObject{
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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQueryReturning_ReturningOption()
     * @model
     * @generated
     */
    XMLReturningType getReturningOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getReturningOption <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Returning Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @see #getReturningOption()
     * @generated
     */
    void setReturningOption(XMLReturningType value);

	/**
     * Returns the value of the '<em><b>Passing Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Passing Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Passing Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #setPassingOption(XMLPassingType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQueryReturning_PassingOption()
     * @model
     * @generated
     */
    XMLPassingType getPassingOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getPassingOption <em>Passing Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Passing Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #getPassingOption()
     * @generated
     */
    void setPassingOption(XMLPassingType value);

	/**
     * Returns the value of the '<em><b>Value Function Query</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getQueryReturning <em>Query Returning</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Query</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Query</em>' container reference.
     * @see #setValueFunctionQuery(XMLValueFunctionQuery)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionQueryReturning_ValueFunctionQuery()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getQueryReturning
     * @model opposite="queryReturning" required="true"
     * @generated
     */
    XMLValueFunctionQuery getValueFunctionQuery();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getValueFunctionQuery <em>Value Function Query</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Query</em>' container reference.
     * @see #getValueFunctionQuery()
     * @generated
     */
    void setValueFunctionQuery(XMLValueFunctionQuery value);

} // XMLValueFunctionQueryReturning
