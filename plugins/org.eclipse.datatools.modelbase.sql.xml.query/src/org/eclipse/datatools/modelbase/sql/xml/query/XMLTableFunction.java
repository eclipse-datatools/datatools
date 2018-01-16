/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableFunction.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Table Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getTableRowPattern <em>Table Row Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getXqueryArgList <em>Xquery Arg List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getColumnDefList <em>Column Def List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getNamespacesDecl <em>Namespaces Decl</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableFunction()
 * @model
 * @generated
 */
public interface XMLTableFunction extends TableFunction{
	/**
     * Returns the value of the '<em><b>Table Row Pattern</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Row Pattern</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Row Pattern</em>' attribute.
     * @see #setTableRowPattern(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableFunction_TableRowPattern()
     * @model
     * @generated
     */
    String getTableRowPattern();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getTableRowPattern <em>Table Row Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Row Pattern</em>' attribute.
     * @see #getTableRowPattern()
     * @generated
     */
    void setTableRowPattern(String value);

	/**
     * Returns the value of the '<em><b>Xquery Arg List</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getTableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xquery Arg List</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xquery Arg List</em>' containment reference.
     * @see #setXqueryArgList(XMLQueryArgumentList)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableFunction_XqueryArgList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getTableFunction
     * @model opposite="tableFunction" containment="true"
     * @generated
     */
    XMLQueryArgumentList getXqueryArgList();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getXqueryArgList <em>Xquery Arg List</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xquery Arg List</em>' containment reference.
     * @see #getXqueryArgList()
     * @generated
     */
    void setXqueryArgList(XMLQueryArgumentList value);

	/**
     * Returns the value of the '<em><b>Column Def List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem#getTableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column Def List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Column Def List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableFunction_ColumnDefList()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem#getTableFunction
     * @model type="org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem" opposite="tableFunction" containment="true" required="true"
     * @generated
     */
    EList getColumnDefList();

	/**
     * Returns the value of the '<em><b>Namespaces Decl</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getTableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespaces Decl</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespaces Decl</em>' containment reference.
     * @see #setNamespacesDecl(XMLNamespacesDeclaration)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableFunction_NamespacesDecl()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getTableFunction
     * @model opposite="tableFunction" containment="true"
     * @generated
     */
    XMLNamespacesDeclaration getNamespacesDecl();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getNamespacesDecl <em>Namespaces Decl</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Namespaces Decl</em>' containment reference.
     * @see #getNamespacesDecl()
     * @generated
     */
    void setNamespacesDecl(XMLNamespacesDeclaration value);

} // XMLTableFunction
