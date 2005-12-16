/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElement.java,v 1.5 2005/02/03 22:58:54 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Grouping Sets Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement#getGroupingSets <em>Grouping Sets</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage#getGroupingSetsElement()
 * @model abstract="true"
 * @generated
 */
public interface GroupingSetsElement extends SQLQueryObject{
	/**
	 * Returns the value of the '<em><b>Grouping Sets</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSets#getGroupingSetsElementList <em>Grouping Sets Element List</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grouping Sets</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Grouping Sets</em>' container reference.
	 * @see #setGroupingSets(GroupingSets)
	 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage#getGroupingSetsElement_GroupingSets()
	 * @see org.eclipse.datatools.modelbase.sql.query.GroupingSets#getGroupingSetsElementList
	 * @model opposite="groupingSetsElementList"
	 * @generated
	 */
    GroupingSets getGroupingSets();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement#getGroupingSets <em>Grouping Sets</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grouping Sets</em>' container reference.
	 * @see #getGroupingSets()
	 * @generated
	 */
    void setGroupingSets(GroupingSets value);

} // SQLGroupingSetsElement
