/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLAggregateSortSpecification.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Aggregate Sort Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specifies an ordering within the values returned by the aggregation function.  See ISO SQL/XML sec. 11.2.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getAggregateFunction <em>Aggregate Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getOrderBySpec <em>Order By Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAggregateSortSpecification()
 * @model
 * @generated
 */
public interface XMLAggregateSortSpecification extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Aggregate Function</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getSortSpecList <em>Sort Spec List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Aggregate Function</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Aggregate Function</em>' container reference.
     * @see #setAggregateFunction(XMLAggregateFunction)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAggregateSortSpecification_AggregateFunction()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getSortSpecList
     * @model opposite="sortSpecList" required="true"
     * @generated
     */
    XMLAggregateFunction getAggregateFunction();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getAggregateFunction <em>Aggregate Function</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Aggregate Function</em>' container reference.
     * @see #getAggregateFunction()
     * @generated
     */
    void setAggregateFunction(XMLAggregateFunction value);

	/**
     * Returns the value of the '<em><b>Order By Spec</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Order By Spec</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Order By Spec</em>' containment reference.
     * @see #setOrderBySpec(OrderBySpecification)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAggregateSortSpecification_OrderBySpec()
     * @model containment="true" required="true"
     * @generated
     */
    OrderBySpecification getOrderBySpec();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getOrderBySpec <em>Order By Spec</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Order By Spec</em>' containment reference.
     * @see #getOrderBySpec()
     * @generated
     */
    void setOrderBySpec(OrderBySpecification value);

} // XMLAggregateSortSpecification
