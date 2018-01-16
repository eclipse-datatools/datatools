/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionComment.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Value Function Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates an XML comment value from a string expression.  See ISO SQL/XML sec. 6.10.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getCommentContent <em>Comment Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionComment()
 * @model
 * @generated
 */
public interface XMLValueFunctionComment extends XMLValueFunction{
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
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionComment_ReturningOption()
     * @model
     * @generated
     */
    XMLReturningType getReturningOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getReturningOption <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Returning Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @see #getReturningOption()
     * @generated
     */
    void setReturningOption(XMLReturningType value);

	/**
     * Returns the value of the '<em><b>Comment Content</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueFunctionComment <em>Value Function Comment</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comment Content</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comment Content</em>' containment reference.
     * @see #setCommentContent(XMLValueFunctionCommentContent)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLValueFunctionComment_CommentContent()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueFunctionComment
     * @model opposite="valueFunctionComment" containment="true" required="true"
     * @generated
     */
    XMLValueFunctionCommentContent getCommentContent();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getCommentContent <em>Comment Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comment Content</em>' containment reference.
     * @see #getCommentContent()
     * @generated
     */
    void setCommentContent(XMLValueFunctionCommentContent value);

} // XMLValueFunctionComment
