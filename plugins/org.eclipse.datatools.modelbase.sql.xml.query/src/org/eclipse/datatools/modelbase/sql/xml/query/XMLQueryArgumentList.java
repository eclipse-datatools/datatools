/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLQueryArgumentList.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Query Argument List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents an XML Query argument list.  See ISO SQL/XML sec. 6.17.  <note>The list is modelled as an explicit object rather than just as a list of XMLQueryArgument objects attached to the parent so that the list as whole can have an optional passing mechanism clause.</note>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPassingMechanism <em>Passing Mechanism</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getXqueryArgListChildren <em>Xquery Arg List Children</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getValueFunctionQuery <em>Value Function Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getTableFunction <em>Table Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentList()
 * @model
 * @generated
 */
public interface XMLQueryArgumentList extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Passing Mechanism</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Passing Mechanism</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Passing Mechanism</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #setPassingMechanism(XMLPassingType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentList_PassingMechanism()
     * @model
     * @generated
     */
    XMLPassingType getPassingMechanism();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPassingMechanism <em>Passing Mechanism</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Passing Mechanism</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #getPassingMechanism()
     * @generated
     */
    void setPassingMechanism(XMLPassingType value);

	/**
     * Returns the value of the '<em><b>Predicate Exists</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryArgList <em>Xquery Arg List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predicate Exists</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Predicate Exists</em>' container reference.
     * @see #setPredicateExists(XMLPredicateExists)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentList_PredicateExists()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryArgList
     * @model opposite="xqueryArgList" required="true"
     * @generated
     */
    XMLPredicateExists getPredicateExists();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPredicateExists <em>Predicate Exists</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Predicate Exists</em>' container reference.
     * @see #getPredicateExists()
     * @generated
     */
    void setPredicateExists(XMLPredicateExists value);

	/**
     * Returns the value of the '<em><b>Xquery Arg List Children</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getXqueryArgList <em>Xquery Arg List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xquery Arg List Children</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xquery Arg List Children</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentList_XqueryArgListChildren()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getXqueryArgList
     * @model type="org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem" opposite="xqueryArgList" containment="true" required="true"
     * @generated
     */
    EList getXqueryArgListChildren();

	/**
     * Returns the value of the '<em><b>Value Function Query</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryArgList <em>Xquery Arg List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Query</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Query</em>' container reference.
     * @see #setValueFunctionQuery(XMLValueFunctionQuery)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentList_ValueFunctionQuery()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryArgList
     * @model opposite="xqueryArgList" required="true"
     * @generated
     */
    XMLValueFunctionQuery getValueFunctionQuery();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getValueFunctionQuery <em>Value Function Query</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Query</em>' container reference.
     * @see #getValueFunctionQuery()
     * @generated
     */
    void setValueFunctionQuery(XMLValueFunctionQuery value);

	/**
     * Returns the value of the '<em><b>Table Function</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getXqueryArgList <em>Xquery Arg List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Function</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Function</em>' container reference.
     * @see #setTableFunction(XMLTableFunction)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLQueryArgumentList_TableFunction()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getXqueryArgList
     * @model opposite="xqueryArgList" required="true"
     * @generated
     */
    XMLTableFunction getTableFunction();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getTableFunction <em>Table Function</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Function</em>' container reference.
     * @see #getTableFunction()
     * @generated
     */
    void setTableFunction(XMLTableFunction value);

} // XMLQueryArgumentList
