/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Alias</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Aliases (Chapter 1. Concepts 9)
 * 
 * An alias is an alternate name for a table or view. An alias can be used to reference a table or view in cases where an existing table or view can be referenced. However, the option of referencing a table or view by an alias is not explicitly shown in the syntax diagrams or mentioned in the description of SQL statements.
 * 
 * Like tables and views, an alias may be created, dropped, and have a comment associated with it. No authority is necessary to use an alias. Access to the tables and views that are referred to by the alias, however, still requires the appropriate authorization for the current statement.
 * 
 * An alias is created with the CREATE ALIAS statement. For more information about creating aliases, see " CREATE ALIAS" on page 318.
 * 
 * CREATE ALIAS
 * The CREATE ALIAS statement defines an alias for a table or view.
 * 
 * Invocation: This statement can be embedded in an application program or issued interactively. It is an executable statement that can be dynamically prepared.
 * 
 * Authorization: The privileges held by the authorization ID of the statement must include at least one of the following:
 *  - The privilege to create in the schema
 *  - Administrative authority.
 * Syntax: CREATE ALIAS alias-name FOR table-name view-name
 * 
 * Description alias-name
 * Names the alias. The name, including the implicit or explicit qualifier, must not be the same as an index, table, view or alias that already exists at the current server. If the alias-name is qualified, the schema name must not be a system schema.
 * 
 * FOR table-name or view-name
 * Identifies the table or view at the current server for which alias-name is defined. An alias name must not be specified (an alias cannot refer to another alias). An alias can be defined for an object that does not exist at the time of the definition. If it does not exist when the alias is created, a warning is returned. However, the referenced object must exist when a SQL statement containing the alias is used, otherwise an error is returned.
 * 
 * Example: Create an alias named CURRENT_PROJECTS for the PROJECT table.
 * CREATE ALIAS CURRENT_PROJECTS FOR PROJECT
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias#getAliasedTable <em>Aliased Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Alias()
 * @model
 * @generated
 */
public interface DB2Alias extends Table {
	/**
	 * Returns the value of the '<em><b>Aliased Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aliased Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aliased Table</em>' reference.
	 * @see #setAliasedTable(Table)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Alias_AliasedTable()
	 * @model required="true"
	 * @generated
	 */
	Table getAliasedTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias#getAliasedTable <em>Aliased Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aliased Table</em>' reference.
	 * @see #getAliasedTable()
	 * @generated
	 */
	void setAliasedTable(Table value);

} // DB2Alias
