/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Routine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Routines (Chapter 1. Concepts 9):
 * A routine is an executable SQL object. There are two types of routines.
 * 
 * Functions:
 * A function is a routine that can be invoked from within other SQL statements and returns a value, or a table. A function is created with the CREATE FUNCTION statement.
 * 
 * Procedures:
 * A procedure (sometimes called a stored procedure) is a routine that can be called to perform operations that can include both host language statements and SQL statements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getFenced <em>Fenced</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getThreadsafe <em>Threadsafe</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isDbInfo <em>Db Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isImplicitSchema <em>Implicit Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isFederated <em>Federated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getParmCcsid <em>Parm Ccsid</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getSpecialRegister <em>Special Register</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getChangeState <em>Change State</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getDebugId <em>Debug Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getProgramType <em>Program Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getOrigSchemaName <em>Orig Schema Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getOrigParmSig <em>Orig Parm Sig</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getExtendedOptions <em>Extended Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getRoutineExtensions <em>Routine Extensions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface DB2Routine extends Routine, DB2AccessPlan {

	/**
	 * Returns the value of the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From the DB2 SQL Reference for Cross-Platform Development v1.1
	 * 
	 * Specifies that the external function or procedure runs in an environment that is isolated from
	 * the database manager environment.
	 * 
	 * 
	 * From the v8.1 UDB documentation for external UDFs and stored procedures:
	 * 
	 * This clause specifies whether the stored procedure is considered "safe" to
	 * run in the database manager operating environment's process or address
	 * space (NOT FENCED), or not (FENCED).
	 * If a stored procedure is registered as FENCED, the database manager
	 * protects its internal resources (for example, data buffers) from access by
	 * the procedure. All procedures have the option of running as FENCED or
	 * NOT FENCED. In general, a procedure running as FENCED will not
	 * perform as well as a similar one running as NOT FENCED.
	 * 
	 * CAUTION:
	 * Use of NOT FENCED for procedures that have not been adequately
	 * checked out can compromise the integrity of DB2. DB2 takes some
	 * precautions against many of the common types of inadvertent failures
	 * that could occur, but cannot guarantee complete integrity when NOT
	 * FENCED stored procedures are used.
	 * 
	 * Either SYSADM authority, DBADM authority, or a special authority
	 * (CREATE_NOT_FENCED) is required to register a stored procedure as
	 * NOT FENCED. Only FENCED can be specified for a stored procedure
	 * with LANGUAGE OLE or NOT THREADSAFE.
	 * 
	 * To create a not-fenced stored procedure, the privileges held by the
	 * authorization ID of the statement must also include at least one of the
	 * following:
	 * - CREATE_NOT_FENCED_ROUTINE authority on the database
	 * - SYSADM or DBADM authority.
	 * 
	 * To create a fenced stored procedure, no additional authorities or privileges are
	 * required. If the authorization ID has insufficient authority to perform the operation, an
	 * error (SQLSTATE 42502) is raised.
	 * 
	 * As of Sept 2003, there isn't a Fenced attribute for 390. There is one 
	 * supported for as400 is done so for compatibility purposes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fenced</em>' attribute.
	 * @see #setFenced(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_Fenced()
	 * @model
	 * @generated
	 */
	String getFenced();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getFenced <em>Fenced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fenced</em>' attribute.
	 * @see #getFenced()
	 * @generated
	 */
	void setFenced(String value);

	/**
	 * Returns the value of the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From the v8.1 UDB documentation for external UDFs and stored procedures:
	 * 
	 * Specifies whether the procedure is considered safe to run in the same
	 * process as other routines (THREADSAFE), or not (NOT THREADSAFE).  
	 * 
	 * If the procedure is defined with LANGUAGE other than OLE:
	 * - If the procedure is defined as THREADSAFE, the database manager can
	 *   invoke the procedure in the same process as other routines. In general,
	 *   to be threadsafe, a procedure should not use any global or static data
	 *   areas. Most programming references include a discussion of writing
	 *   threadsafe routines. Both FENCED and NOT FENCED procedures can
	 *   be THREADSAFE.
	 * - If the procedure is defined as NOT THREADSAFE, the database
	 *   manager will never invoke the procedure in the same process as
	 *   another routine.
	 * 
	 * For FENCED procedures, THREADSAFE is the default if the LANGUAGE
	 * is JAVA. For all other languages, NOT THREADSAFE is the default. If the
	 * procedure is defined with LANGUAGE OLE, THREADSAFE may not be
	 * specified (SQLSTATE 42613). 
	 * 
	 * For NOT FENCED procedures, THREADSAFE is the default. NOT
	 * THREADSAFE cannot be specified (SQLSTATE 42613).
	 * 
	 * As of Sept 2003, only for LUW
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Threadsafe</em>' attribute.
	 * @see #setThreadsafe(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_Threadsafe()
	 * @model
	 * @generated
	 */
	String getThreadsafe();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getThreadsafe <em>Threadsafe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Threadsafe</em>' attribute.
	 * @see #getThreadsafe()
	 * @generated
	 */
	void setThreadsafe(String value);

   /**
    * Pseudo-enumerated value for changeState.  CLEAN means
    * the routine hasn't changed since it was deployed to the
    * server.
    * @see DB2Routine#setChangeState(int);
    */
   int CLEAN = 0;

   /**
    * Pseudo-enumerated value for changeState.  DIRTY_DDL means
    * the routine's DDL has changed since the last time it was
    * deployed to the server.
    * @see DB2Routine#setChangeState(int);
    */
   int DIRTY_DDL = 1;

   /**
    * Pseudo-enumerated value for changeState.  DIRTY means
    * the routine's source has changed since the last time it was
    * deployed to the server.
    * @see DB2Routine#setChangeState(int);
    */
   int DIRTY = 2;

	/**
	 * Returns the value of the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From the DB2 SQL Reference for Cross-Platform Development v1.1
	 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
	 * 
	 * CREATE PROCEDURE (External):
	 * NO DBINFO or DBINFO
	 * Specifies whether additional status information is passed to the procedure
	 * when it is invoked. The default is NO DBINFO.
	 * NO DBINFO
	 * Additional information is not passed.
	 * DBINFO
	 * An additional argument is passed when the procedure is invoked.
	 * The argument is a structure that contains information such as the name of
	 * the current server, the application run-time authorization ID and
	 * identification of the version and release of the database manager that
	 * invoked the procedure. See "Database information in external routines
	 * (DBINFO)" on page 708 for further details.
	 * DBINFO can be specified only if PARAMETER STYLE DB2SQL is
	 * specified.
	 * 
	 * Parameter passing for external functions or procedures written in C or COBOL
	 * This input argument is set by the database manager before invoking the
	 * program. It is only present if the CREATE FUNCTION statement for the
	 * routine specifies the DBINFO keyword. The argument is a structure whose
	 * definition is described in "Database information in external routines
	 * (DBINFO)" on page 708. The dbinfo argument is input only and any changes to
	 * the argument value made by the program are ignored by the database
	 * manager upon return from the program.
	 * 
	 * 
	 * From the v8.1 UDB documentation for external UDFs and stored procedures:
	 * 
	 * Specifies whether specific information known by DB2 is passed to the
	 * stored procedure when it is invoked as an additional invocation-time
	 * argument (DBINFO) or not (NO DBINFO). NO DBINFO is the default. 
	 * 
	 * DBINFO is not supported for LANGUAGE OLE (SQLSTATE 42613). It is
	 * also not supported for PARAMETER STYLE JAVA or DB2GENERAL.
	 * If DBINFO is specified, a structure containing the following information is
	 * passed to the stored procedure:
	 * - Data base name - the name of the currently connected database.
	 * - Application ID - unique application ID which is established for each
	 *   connection to the database.
	 * - Application Authorization ID - the application run-time authorization ID.
	 * - Code page - identifies the database code page.
	 * - Database version/release - identifies the version, release and
	 *   modification level of the database server invoking the stored procedure.
	 * - Platform - contains the server's platform type.
	 * 
	 * The DBINFO structure is common for all external routines and contains
	 * additional fields that are not relevant to procedures.
	 * CREATE PROCEDURE (External)
	 * 
	 * 
	 * From the os390 v7 SQL reference:
	 * Specifies whether specific information known by DB2 is passed to the stored
	 * procedure when it is invoked.
	 * NO DBINFO
	 *   Additional information is not passed. NO DBINFO is the default.
	 * DBINFO
	 *   An additional argument is passed when the stored procedure is invoked.
	 *   The argument is a structure that contains information such as the
	 *   application run-time authorization ID, the schema name, the name of a
	 *   table or column that the procedure might be inserting into or updating, and
	 *   identification of the database server that invoked the procedure. For details
	 *   about the argument and its structure, see DB2 Application Programming
	 *   and SQL Guide.
	 * 
	 *   DBINFO can be specified only if PARAMETER STYLE DB2SQL is
	 *   specified.
	 * 
	 * 
	 * From the as400 SQL v5r1 SQL reference:
	 * DBINFO Indicates that the database manager should pass a structure containing
	 * status information to the function. Table 24 in the SQL reference 
	 * contains a description of the DBINFO structure. Detailed information 
	 * about the DBINFO structure can be found in include file SQLUDF in QSYSINC.H.
	 * DBINFO is only allowed with PARAMETER STYLE DB2SQL.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Db Info</em>' attribute.
	 * @see #setDbInfo(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_DbInfo()
	 * @model
	 * @generated
	 */
	boolean isDbInfo();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isDbInfo <em>Db Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Db Info</em>' attribute.
	 * @see #isDbInfo()
	 * @generated
	 */
	void setDbInfo(boolean value);

	/**
	 * Returns the value of the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From the DB2 SQL Reference for Cross-Platform Development v1.1
	 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
	 * 
	 * The routine name is implicitly or explicitly qualified with a schema name.  If the routine
	 * is created without a schema, then it is created with an implicit schema.
	 * 
	 * The implicit schema name is determined as follows:
	 * - For distinct type names, the database manager searches the SQL path and
	 * selects the first schema in the SQL path such that the data type exists in the
	 * schema.
	 * - For procedure names, the database manager searches the SQL path and
	 * selects the first schema in the SQL path such that the schema contains a
	 * procedure with the same name and the same number of parameters.
	 * - For function names, the database manager uses the SQL path in conjunction
	 * with function resolution, as described under "Function resolution" on page 94.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Implicit Schema</em>' attribute.
	 * @see #setImplicitSchema(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_ImplicitSchema()
	 * @model
	 * @generated
	 */
	boolean isImplicitSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isImplicitSchema <em>Implicit Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implicit Schema</em>' attribute.
	 * @see #isImplicitSchema()
	 * @generated
	 */
	void setImplicitSchema(boolean value);

	/**
	 * Returns the value of the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From UDB v8.1 SQL Reference Volume 2
	 * 
	 * FEDERATED or NOT FEDERATED
	 * This optional clause specifies whether or not federated objects can be
	 * used.
	 * If NOT FEDERATED is specified, federated objects cannot be used in any
	 * SQL statement in the function or procedure . Using a federated object will result in an
	 * error (SQLSTATE 55047).
	 * 
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Federated</em>' attribute.
	 * @see #setFederated(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_Federated()
	 * @model
	 * @generated
	 */
	boolean isFederated();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isFederated <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Federated</em>' attribute.
	 * @see #isFederated()
	 * @generated
	 */
	void setFederated(boolean value);

	/**
	 * Returns the value of the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From the DB2 SQL Reference for Cross-Platform Development v1.1
	 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
	 * 
	 * The meaning of the environment Coded Character Set Identifier (CCSID) depends on 
	 * the application server where the routine is executed.
	 * - On DB2 UDB for z/OS and OS/390, the environment CCSIDs are the CCSIDs
	 * for the table accessed in the containing SQL statement.
	 * - On DB2 UDB for iSeries, the environment CCSIDs are the CCSIDs associated
	 * with the job.
	 * - On DB2 UDB for LUW, the environment CCSIDs are the CCSIDs for the
	 * relational database.
	 * 
	 * 
	 * DB2 Universal Database for OS/390 and z/OS v7 SQL Ref:
	 * Every string used in an SQL operation has a CCSID. The CCSID identifies the
	 * manner in which the characters in the string are encoded. Strings can be encoded
	 * in ASCII, EBCDIC, or Unicode.  Many of the times, the default CCSID is used
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parm Ccsid</em>' attribute.
	 * @see #setParmCcsid(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_ParmCcsid()
	 * @model
	 * @generated
	 */
	String getParmCcsid();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getParmCcsid <em>Parm Ccsid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parm Ccsid</em>' attribute.
	 * @see #getParmCcsid()
	 * @generated
	 */
	void setParmCcsid(String value);

	/**
	 * Returns the value of the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For UDB, in BNF diagrams above the line, INHERIT SPECIAL REGISTERS is the default.
	 * For zSeries, v7+ there is both 
	 * INHERIT SPECIAL REGISTERS 
	 *   Indicates that the values of special registers are inherited according to the rules
	 *   listed in the table for characteristics of special registers in a user-defined
	 *   function or stored procedure in Table 19 on page 92.
	 * DEFAULT SPECIAL REGISTERS
	 *   Indicates that special registers are initialized to the default values, as indicated
	 *   by the rules in the table for characteristics of special registers in a user-defined
	 *   function or stored procedure in Table 19 on page 92.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Special Register</em>' attribute.
	 * @see #setSpecialRegister(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_SpecialRegister()
	 * @model
	 * @generated
	 */
	String getSpecialRegister();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getSpecialRegister <em>Special Register</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Special Register</em>' attribute.
	 * @see #getSpecialRegister()
	 * @generated
	 */
	void setSpecialRegister(String value);

	/**
	 * Returns the value of the '<em><b>Change State</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This replaces the "dirty" and "dirtyDDL" attributes in the WSAD v6 and previous SQL models.
	 * changeState is integer for to indicate DIRTY source, or just dirty DDL (where an external routine doesn't 
	 * need to be recompiled, just dropped and registered).  
	 * 
	 * Rather than use an enumerated type which is not extendable in EMF, this will have a 
	 * pseudo-enumerated type so that the db2 routines can be extended to hold more state 
	 * information than just DIRTY (2) and DIRTY_DDL (1), and CLEAN (0).  CLEAN will be the default
	 * as the initial value is set to 0.  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change State</em>' attribute.
	 * @see #setChangeState(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_ChangeState()
	 * @model default="0"
	 * @generated
	 */
   int getChangeState();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getChangeState <em>Change State</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change State</em>' attribute.
	 * @see #getChangeState()
	 * @generated
	 */
   void setChangeState(int value);

	/**
	 * Returns the value of the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Special ID to uniquely define the registered, built-for-debug routine at the server.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Debug Id</em>' attribute.
	 * @see #setDebugId(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_DebugId()
	 * @model
	 * @generated
	 */
   String getDebugId();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getDebugId <em>Debug Id</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Debug Id</em>' attribute.
	 * @see #getDebugId()
	 * @generated
	 */
   void setDebugId(String value);

	/**
	 * Returns the value of the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For OS/390 v6 and v7, both UDFs and SPs 
	 * <p> 
	 * From DB2 OS390 v7 SQL Reference: 
	 * <DL> 
	 * <DT>SUB 
	 * <DD>The stored procedure runs as a subroutine. 
	 * <DT>MAIN 
	 * <DD>The stored procedure runs as a main routine. 
	 * </DL> 
	 * <p> 
	 * The stored procedure runs as a main routine. 
	 * The default for PROGRAM TYPE depends on the value of special register 
	 * CURRENT RULES. The default is: 
	 * <ul> 
	 * <li>MAIN when the value is DB2 
	 * <li>SUB when the value is STD 
	 * </ul>
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Program Type</em>' attribute.
	 * @see #setProgramType(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_ProgramType()
	 * @model
	 * @generated
	 */
   String getProgramType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getProgramType <em>Program Type</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Program Type</em>' attribute.
	 * @see #getProgramType()
	 * @generated
	 */
   void setProgramType(String value);

	/**
	 * Returns the value of the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For rollback purposes, we need to track the original Schema name used to create 
	 * this routine.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Orig Schema Name</em>' attribute.
	 * @see #setOrigSchemaName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_OrigSchemaName()
	 * @model
	 * @generated
	 */
   String getOrigSchemaName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getOrigSchemaName <em>Orig Schema Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orig Schema Name</em>' attribute.
	 * @see #getOrigSchemaName()
	 * @generated
	 */
   void setOrigSchemaName(String value);

	/**
	 * Returns the value of the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For rollback purposes, we need to track the original parameter signature of this 
	 * routine.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Orig Parm Sig</em>' attribute.
	 * @see #setOrigParmSig(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_OrigParmSig()
	 * @model
	 * @generated
	 */
   String getOrigParmSig();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getOrigParmSig <em>Orig Parm Sig</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orig Parm Sig</em>' attribute.
	 * @see #getOrigParmSig()
	 * @generated
	 */
   void setOrigParmSig(String value);

	/**
	 * Returns the value of the '<em><b>Extended Options</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions}.
	 * <!-- begin-user-doc -->
    * <p>
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Options</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_ExtendedOptions()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions" containment="true" required="true"
	 * @generated
	 */
   EList getExtendedOptions();

	/**
	 * Returns the value of the '<em><b>Routine Extensions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension}.
	 * <!-- begin-user-doc -->
    * <p>
    * Extensions for application specific needs.  The DB2Model has no dependencies
    * on whoever implements DB2RoutineExtension
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Routine Extensions</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Routine_RoutineExtensions()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension"
	 * @generated
	 */
   EList getRoutineExtensions();

} // DB2Routine
