/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLAttributesDeclaration.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Attributes Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getValueFunctionElement <em>Value Function Element</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getAttributeDeclItem <em>Attribute Decl Item</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAttributesDeclaration()
 * @model
 * @generated
 */
public interface XMLAttributesDeclaration extends EObject{
	/**
     * Returns the value of the '<em><b>Value Function Element</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getAttributesDecl <em>Attributes Decl</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Function Element</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Function Element</em>' container reference.
     * @see #setValueFunctionElement(XMLValueFunctionElement)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAttributesDeclaration_ValueFunctionElement()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getAttributesDecl
     * @model opposite="attributesDecl" required="true"
     * @generated
     */
    XMLValueFunctionElement getValueFunctionElement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getValueFunctionElement <em>Value Function Element</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Function Element</em>' container reference.
     * @see #getValueFunctionElement()
     * @generated
     */
    void setValueFunctionElement(XMLValueFunctionElement value);

	/**
     * Returns the value of the '<em><b>Attribute Decl Item</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getAttributesDecl <em>Attributes Decl</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attribute Decl Item</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attribute Decl Item</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLAttributesDeclaration_AttributeDeclItem()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getAttributesDecl
     * @model type="org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem" opposite="attributesDecl" containment="true" required="true"
     * @generated
     */
    EList getAttributeDeclItem();

} // XMLAttributesDeclaration
