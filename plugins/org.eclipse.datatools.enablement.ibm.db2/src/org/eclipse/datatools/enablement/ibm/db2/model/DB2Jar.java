/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2Jar.java,v 1.6 2007/10/12 23:05:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Jar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getOwner <em>Owner</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getCreatedTS <em>Created TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getAlteredTS <em>Altered TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getProcedures <em>Procedures</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Jar()
 * @model
 * @generated
 */
public interface DB2Jar extends SQLObject {
	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * File name for the jar.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Jar_FileName()
	 * @model
	 * @generated
	 */
   String getFileName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
   void setFileName(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Path</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Jar_Path()
	 * @model
	 * @generated
	 */
   String getPath();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
   void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Owner</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' attribute.
	 * @see #setOwner(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Jar_Owner()
	 * @model
	 * @generated
	 */
   String getOwner();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getOwner <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' attribute.
	 * @see #getOwner()
	 * @generated
	 */
   void setOwner(String value);

	/**
	 * Returns the value of the '<em><b>Created TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Created TS</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Created TS</em>' attribute.
	 * @see #setCreatedTS(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Jar_CreatedTS()
	 * @model
	 * @generated
	 */
   String getCreatedTS();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getCreatedTS <em>Created TS</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created TS</em>' attribute.
	 * @see #getCreatedTS()
	 * @generated
	 */
   void setCreatedTS(String value);

	/**
	 * Returns the value of the '<em><b>Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Altered TS</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Altered TS</em>' attribute.
	 * @see #setAlteredTS(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Jar_AlteredTS()
	 * @model
	 * @generated
	 */
   String getAlteredTS();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getAlteredTS <em>Altered TS</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Altered TS</em>' attribute.
	 * @see #getAlteredTS()
	 * @generated
	 */
   void setAlteredTS(String value);

	/**
	 * Returns the value of the '<em><b>Procedures</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getJar <em>Jar</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Procedures</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Procedures</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Jar_Procedures()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getJar
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions" opposite="jar"
	 * @generated
	 */
   EList getProcedures();

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getJars <em>Jars</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Schema</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(DB2Schema)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Jar_Schema()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getJars
	 * @model opposite="jars" required="true"
	 * @generated
	 */
   DB2Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
   void setSchema(DB2Schema value);

} // DB2Jar
