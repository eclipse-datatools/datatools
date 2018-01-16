/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getDb2PackageName <em>Db2 Package Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getLastModified <em>Last Modified</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getSupporting <em>Supporting</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPrimary <em>Primary</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Source()
 * @model
 * @generated
 */
public interface DB2Source extends Source {
	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fully qualified file name where the file is stored on the local file system.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Source_FileName()
	 * @model
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);

	/**
	 * Returns the value of the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Java package name
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Name</em>' attribute.
	 * @see #setPackageName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Source_PackageName()
	 * @model
	 * @generated
	 */
	String getPackageName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPackageName <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Name</em>' attribute.
	 * @see #getPackageName()
	 * @generated
	 */
	void setPackageName(String value);

	/**
	 * Returns the value of the '<em><b>Db2 Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A package is an object that contains all the sections from a single source file. 
	 * A section is the compiled form of an SQL statement.  The name of the DB2 package name.  
	 * 
	 * In the case of SQLJ stored procedures for LUW and 390, this field contains the 
	 * location of the serialized file stored on the sever (aka root package name)
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Db2 Package Name</em>' attribute.
	 * @see #setDb2PackageName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Source_Db2PackageName()
	 * @model
	 * @generated
	 */
	String getDb2PackageName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getDb2PackageName <em>Db2 Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Db2 Package Name</em>' attribute.
	 * @see #getDb2PackageName()
	 * @generated
	 */
	void setDb2PackageName(String value);

	/**
	 * Returns the value of the '<em><b>Last Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When this source was last modified. The string timestamp should be in the JDBC timestamp 
	 * escape format. yyyy-mm-dd hh:mm:ss.fffffffff, where ffffffffff indicates nanoseconds.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Last Modified</em>' attribute.
	 * @see #setLastModified(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Source_LastModified()
	 * @model
	 * @generated
	 */
	String getLastModified();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getLastModified <em>Last Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Modified</em>' attribute.
	 * @see #getLastModified()
	 * @generated
	 */
	void setLastModified(String value);

	/**
	 * Returns the value of the '<em><b>Supporting</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPrimary <em>Primary</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Supporting</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Supporting</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Source_Supporting()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPrimary
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Source" opposite="primary" containment="true"
	 * @generated
	 */
   EList getSupporting();

	/**
	 * Returns the value of the '<em><b>Primary</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getSupporting <em>Supporting</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Primary</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary</em>' container reference.
	 * @see #setPrimary(DB2Source)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Source_Primary()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getSupporting
	 * @model opposite="supporting" required="true"
	 * @generated
	 */
   DB2Source getPrimary();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPrimary <em>Primary</em>}' container reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary</em>' container reference.
	 * @see #getPrimary()
	 * @generated
	 */
   void setPrimary(DB2Source value);

} // DB2Source
