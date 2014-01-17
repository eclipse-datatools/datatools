/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Extended Options</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getClasspathCompileJars <em>Classpath Compile Jars</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getPreCompileOpts <em>Pre Compile Opts</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#isForDebug <em>For Debug</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#isBuilt <em>Built</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getCompileOpts <em>Compile Opts</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getLinkOpts <em>Link Opts</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getBindOpts <em>Bind Opts</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getColid <em>Colid</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions()
 * @model
 * @generated
 */
public interface DB2ExtendedOptions extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Classpath Compile Jars</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Java classpath required to compile each of the source files that are part of either the stored procedure or UDF. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classpath Compile Jars</em>' attribute.
	 * @see #setClasspathCompileJars(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions_ClasspathCompileJars()
	 * @model
	 * @generated
	 */
	String getClasspathCompileJars();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getClasspathCompileJars <em>Classpath Compile Jars</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classpath Compile Jars</em>' attribute.
	 * @see #getClasspathCompileJars()
	 * @generated
	 */
	void setClasspathCompileJars(String value);

	/**
	 * Returns the value of the '<em><b>Pre Compile Opts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the precompile options to be used for the stored procedure. For SQLJ
	 * procedure we keep the db2profc options which also includes DB2 package name.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pre Compile Opts</em>' attribute.
	 * @see #setPreCompileOpts(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions_PreCompileOpts()
	 * @model
	 * @generated
	 */
	String getPreCompileOpts();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getPreCompileOpts <em>Pre Compile Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pre Compile Opts</em>' attribute.
	 * @see #getPreCompileOpts()
	 * @generated
	 */
	void setPreCompileOpts(String value);

	/**
	 * Returns the value of the '<em><b>For Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates whether the routine is to be built for debug.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>For Debug</em>' attribute.
	 * @see #setForDebug(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions_ForDebug()
	 * @model
	 * @generated
	 */
	boolean isForDebug();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#isForDebug <em>For Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For Debug</em>' attribute.
	 * @see #isForDebug()
	 * @generated
	 */
	void setForDebug(boolean value);

	/**
	 * Returns the value of the '<em><b>Built</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * False -> has yet to be successfully built by DC/subuilder/other app
	 * True -> has been built at least once successfully within DC/subuilder/other app.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Built</em>' attribute.
	 * @see #setBuilt(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions_Built()
	 * @model
	 * @generated
	 */
	boolean isBuilt();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#isBuilt <em>Built</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Built</em>' attribute.
	 * @see #isBuilt()
	 * @generated
	 */
	void setBuilt(boolean value);

	/**
	 * Returns the value of the '<em><b>Compile Opts</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the compile options to be used for the stored procedure.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Compile Opts</em>' attribute.
	 * @see #setCompileOpts(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions_CompileOpts()
	 * @model
	 * @generated
	 */
   String getCompileOpts();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getCompileOpts <em>Compile Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compile Opts</em>' attribute.
	 * @see #getCompileOpts()
	 * @generated
	 */
   void setCompileOpts(String value);

	/**
	 * Returns the value of the '<em><b>Link Opts</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the link options to be used for the stored procedure.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link Opts</em>' attribute.
	 * @see #setLinkOpts(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions_LinkOpts()
	 * @model
	 * @generated
	 */
   String getLinkOpts();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getLinkOpts <em>Link Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Opts</em>' attribute.
	 * @see #getLinkOpts()
	 * @generated
	 */
   void setLinkOpts(String value);

	/**
	 * Returns the value of the '<em><b>Bind Opts</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the bind options to be used for the stored procedure.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bind Opts</em>' attribute.
	 * @see #setBindOpts(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions_BindOpts()
	 * @model
	 * @generated
	 */
   String getBindOpts();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getBindOpts <em>Bind Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bind Opts</em>' attribute.
	 * @see #getBindOpts()
	 * @generated
	 */
   void setBindOpts(String value);

	/**
	 * Returns the value of the '<em><b>Colid</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Column id previously a zSeries item, now also for LUW.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Colid</em>' attribute.
	 * @see #setColid(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2ExtendedOptions_Colid()
	 * @model
	 * @generated
	 */
   String getColid();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getColid <em>Colid</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Colid</em>' attribute.
	 * @see #getColid()
	 * @generated
	 */
   void setColid(String value);

} // DB2ExtendedOptions
