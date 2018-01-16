/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * DB2 Schemas (Chapter 1. Concepts 3)
 * 
 * A schema is a collection of named objects. Schemas provide a logical classification of objects in the database. A schema can contain tables, views, nicknames, triggers, functions, packages, and other objects.
 * 
 * A schema is also an object in the database. It is explicitly created using the CREATE SCHEMA statement with the current user recorded as the schema owner. It can also be implicitly created when another object is created, provided that the user has IMPLICIT_SCHEMA authority.
 * 
 * A schema name is used as the high order part of a two-part object name. If the object is specifically qualified with a schema name when created, the object is assigned to that schema. If no schema name is specified when the object is created, the default schema name is used.
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getAccessPlans <em>Access Plans</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getOlapObjects <em>Olap Objects</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getJars <em>Jars</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getXsrObjects <em>Xsr Objects</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getPackages <em>Packages</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getMasks <em>Masks</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getModules <em>Modules</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getGlobalVariables <em>Global Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema()
 * @model
 * @generated
 */
public interface DB2Schema extends Schema {
	/**
	 * Returns the value of the '<em><b>Access Plans</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Plans</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access Plans</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_AccessPlans()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan"
	 * @generated
	 */
	EList getAccessPlans();

	/**
	 * Returns the value of the '<em><b>Olap Objects</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Olap Objects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Olap Objects</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_OlapObjects()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject#getSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject" opposite="schema"
	 * @generated
	 */
	EList getOlapObjects();

	/**
	 * Returns the value of the '<em><b>Jars</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Jars</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Jars</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_Jars()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar" opposite="schema"
	 * @generated
	 */
   EList getJars();

	/**
	 * Returns the value of the '<em><b>Xsr Objects</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Xsr Objects</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Xsr Objects</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_XsrObjects()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject#getSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject" opposite="schema"
	 * @generated
	 */
   EList getXsrObjects();

	/**
	 * Returns the value of the '<em><b>Packages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Packages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Packages</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_Packages()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Package" opposite="schema"
	 * @generated
	 */
	EList getPackages();

	/**
	 * Returns the value of the '<em><b>Masks</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Masks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Masks</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_Masks()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask" opposite="schema"
	 * @generated
	 */
	EList getMasks();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_Permissions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission" opposite="schema"
	 * @generated
	 */
	EList getPermissions();

	/**
	 * Returns the value of the '<em><b>Modules</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWModule}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWModule#getOwningSchema <em>Owning Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modules</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_Modules()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWModule#getOwningSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWModule" opposite="owningSchema"
	 * @generated
	 */
	EList getModules();

	/**
	 * Returns the value of the '<em><b>Global Variables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWGlobalVariable}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWGlobalVariable#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Variables</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Schema_GlobalVariables()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWGlobalVariable#getSchema
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.luw.LUWGlobalVariable" opposite="schema"
	 * @generated
	 */
	EList getGlobalVariables();

} // DB2Schema
