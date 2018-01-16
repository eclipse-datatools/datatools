/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLAggregateFunction.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Aggregate Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specifies a value computed from a collection of rows.  See ISO SQL/XML sec. 11.2.  The <XML value expression> clause is handled by the parameterList relationship in ValueExpressionFunction, but is restricted to a single element in this case.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getSortSpecList <em>Sort Spec List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAggregateFunction()
 * @model
 * @generated
 */
public interface XMLAggregateFunction extends ValueExpressionFunction{
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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAggregateFunction_ReturningOption()
     * @model
     * @generated
     */
    XMLReturningType getReturningOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getReturningOption <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Returning Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @see #getReturningOption()
     * @generated
     */
    void setReturningOption(XMLReturningType value);

	/**
     * Returns the value of the '<em><b>Sort Spec List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getAggregateFunction <em>Aggregate Function</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sort Spec List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sort Spec List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAggregateFunction_SortSpecList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getAggregateFunction
     * @model type="org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification" opposite="aggregateFunction" containment="true"
     * @generated
     */
    EList getSortSpecList();

} // XMLAggregateFunction
