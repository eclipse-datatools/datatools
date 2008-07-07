/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableColumnDefinitionItem.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Table Column Definition Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem#getTableFunction <em>Table Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionItem()
 * @model
 * @generated
 */
public interface XMLTableColumnDefinitionItem extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Table Function</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getColumnDefList <em>Column Def List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Function</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Function</em>' container reference.
     * @see #setTableFunction(XMLTableFunction)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionItem_TableFunction()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getColumnDefList
     * @model opposite="columnDefList" required="true"
     * @generated
     */
    XMLTableFunction getTableFunction();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem#getTableFunction <em>Table Function</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Function</em>' container reference.
     * @see #getTableFunction()
     * @generated
     */
    void setTableFunction(XMLTableFunction value);

} // XMLTableColumnDefinitionItem
