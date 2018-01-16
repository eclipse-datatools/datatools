/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2View;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Views
 * 
 * A view provides a different way of looking at the data in one or more tables; it is a named specification of a result table. The specification is a SELECT statement that is run whenever the view is referenced in an SQL statement. A view has columns and rows just like a base table. All views can be used just like base tables for data retrieval. Whether a view can be used in an insert, update, or delete operation depends on its definition. You can use views to control access to sensitive data, because views allow multiple users to see different presentations of the same data.
 * 
 * For example, several users may be accessing a table of data about employees. A manager sees data about his or her employees but not employees in another department. A recruitment officer sees the hire dates of all employees, but not their salaries; a financial officer sees the salaries, but not the hire dates. Each of these users works with a view derived from the base table. Each view appears to be a table and has its own name.
 * 
 * When the column of a view is directly derived from the column of a base table, that view column inherits any constraints that apply to the base table column. For example, if a view includes a foreign key of its base table, insert and update operations using that view are subject to the same referential constraints as is the base table. Also, if the base table of a view is a parent table, delete and update operations using that view are subject to the same rules as are delete and update operations on the base table.
 * 
 * A view can derive the data type of each column from the result table, or base the types on the attributes of a user-defined structured type. This is called a typed view. Similar to a typed table, a typed view can be part of a view hierarchy. A subview inherits columns from its superview. The term subview applies to a typed view and to all typed views that are below it in the view hierarchy. A proper subview of a view V is a view below V in the typed view hierarchy. A view can become inoperative (for example, if the base table is dropped); if this occurs, the view is no longer available for SQL operations.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView#isFederated <em>Federated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView#isOptimizeQuery <em>Optimize Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWView()
 * @model
 * @generated
 */
public interface LUWView extends DB2View {
	/**
	 * Returns the value of the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Federated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Federated</em>' attribute.
	 * @see #setFederated(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWView_Federated()
	 * @model derived="true"
	 * @generated
	 */
	boolean isFederated();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView#isFederated <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Federated</em>' attribute.
	 * @see #isFederated()
	 * @generated
	 */
	void setFederated(boolean value);

	/**
	 * Returns the value of the '<em><b>Optimize Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optimize Query</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optimize Query</em>' attribute.
	 * @see #setOptimizeQuery(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWView_OptimizeQuery()
	 * @model
	 * @generated
	 */
	boolean isOptimizeQuery();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView#isOptimizeQuery <em>Optimize Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optimize Query</em>' attribute.
	 * @see #isOptimizeQuery()
	 * @generated
	 */
	void setOptimizeQuery(boolean value);

} // LUWView
