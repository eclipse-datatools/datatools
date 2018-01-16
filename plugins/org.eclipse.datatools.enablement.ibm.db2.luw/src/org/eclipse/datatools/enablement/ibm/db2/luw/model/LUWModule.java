/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWModule.java,v 1.4 2009/03/06 22:38:09 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;

import org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getDialect <em>Dialect</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getOwningSchema <em>Owning Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getModuleObjects <em>Module Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWModule()
 * @model
 * @generated
 */
public interface LUWModule extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Dialect</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dialect</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dialect</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect
	 * @see #setDialect(SourceDialect)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWModule_Dialect()
	 * @model
	 * @generated
	 */
	SourceDialect getDialect();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getDialect <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dialect</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect
	 * @see #getDialect()
	 * @generated
	 */
	void setDialect(SourceDialect value);

	/**
	 * Returns the value of the '<em><b>Owning Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Schema</em>' reference.
	 * @see #setOwningSchema(DB2Schema)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWModule_OwningSchema()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getModules
	 * @model opposite="modules" required="true"
	 * @generated
	 */
	DB2Schema getOwningSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getOwningSchema <em>Owning Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Schema</em>' reference.
	 * @see #getOwningSchema()
	 * @generated
	 */
	void setOwningSchema(DB2Schema value);

	/**
	 * Returns the value of the '<em><b>Module Objects</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Objects</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWModule_ModuleObjects()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject#getModule
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject" opposite="module" containment="true"
	 * @generated
	 */
	EList getModuleObjects();

} // LUWModule
