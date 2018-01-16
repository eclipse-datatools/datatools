/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueExpressionCast.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Expression Cast</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specifies a data conversion whose source or target is a value expressoin with an XML datatype.  See ISO SQL/XML sec. 6.5.  The <XML cast operand>is handled by the valueExpr relationship of the ValueExpressionCast.  <XML target operand> (which is a datatype) is handled by the datatype attribute of QueryValueeExpression.  The <null specification> is  handled by the ValueExpressionNullValue subtype of QueryValueExpression.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast#getPassingMechanism <em>Passing Mechanism</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueExpressionCast()
 * @model
 * @generated
 */
public interface XMLValueExpressionCast extends ValueExpressionCast{
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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueExpressionCast_PassingMechanism()
     * @model
     * @generated
     */
    XMLPassingType getPassingMechanism();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast#getPassingMechanism <em>Passing Mechanism</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Passing Mechanism</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #getPassingMechanism()
     * @generated
     */
    void setPassingMechanism(XMLPassingType value);

} // XMLValueExpressionCast
