/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLAttributeDeclarationItem.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Attribute Declaration Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getAttributesDecl <em>Attributes Decl</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAttributeDeclarationItem()
 * @model
 * @generated
 */
public interface XMLAttributeDeclarationItem extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAttributeDeclarationItem_ValueExpr()
     * @model containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Attributes Decl</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getAttributeDeclItem <em>Attribute Decl Item</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attributes Decl</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes Decl</em>' container reference.
     * @see #setAttributesDecl(XMLAttributesDeclaration)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAttributeDeclarationItem_AttributesDecl()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getAttributeDeclItem
     * @model opposite="attributeDeclItem" required="true"
     * @generated
     */
    XMLAttributesDeclaration getAttributesDecl();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getAttributesDecl <em>Attributes Decl</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Attributes Decl</em>' container reference.
     * @see #getAttributesDecl()
     * @generated
     */
    void setAttributesDecl(XMLAttributesDeclaration value);

} // XMLAttributeDeclarationItem
