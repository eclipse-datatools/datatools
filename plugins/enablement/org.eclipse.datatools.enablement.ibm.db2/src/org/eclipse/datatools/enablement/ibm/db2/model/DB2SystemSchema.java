/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 System Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Schemas
 * 
 * The objects in a relational database are organized into sets called schemas. A schema provides a logical classification of objects in the database. The schema-name is used as the qualifier of SQL object names such as tables, views, indexes, and triggers. Each database manager supports a set of schemas that are reserved for use by the database manager. Such schemas are called system schemas. User objects must not be created in system schemas.
 * 
 * The schema SESSION and all schemas that start with 'SYS' and 'Q' are system schemas. SESSION is always used as the schema name for declared temporary tables.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2SystemSchema()
 * @model
 * @generated
 */
public interface DB2SystemSchema extends DB2Schema {
} // DB2SystemSchema
