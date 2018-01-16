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
 * A representation of the model object '<em><b>DB2 Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Functions (Chapter 1. Concepts 9)
 * 
 * A function is a routine that can be invoked from within other SQL statements and returns a value, or a table. For more information, see "Functions" on page 93.
 * 
 * Functions (Chapter 2. Language elements 93)
 * 
 * A function is an operation denoted by a function name followed by zero or more operands that are enclosed in parentheses. It represents a relationship between a set of input values and a set of result values. The input values to a function are called arguments. For example, a function can be passed two input arguments that have date and time data types and return a value with a timestamp data type as the result.
 * 
 * Types of functions:
 * There are several ways to classify functions. One way to classify functions is as built-in, user-defined, or generated user-defined functions for distinct types.
 *  -  Built-in functions are functions that come with the database manager. These functions provide a single-value result. Built-in functions include operator functions such as ??+??, column functions such as AVG, and scalar functions such as SUBSTR. For a list of the built-in column and scalar functions and information on these functions, see Chapter 3, "Built-in functions", on page 131. The built-in functions are in a product-specific schema.
 *  - User-defined functions are functions that are created using the CREATE FUNCTION statement and registered to the database manager in the catalog. For more information, see "CREATE FUNCTION" on page 325. These functions allow users to extend the function of the database manager by adding their own or third party vendor function definitions. A user-defined function is an SQL, external, or sourced function. An SQL function is defined to the database using only an SQL RETURN statement. An external function is defined to the database with a reference to an external program that is executed when the function is invoked. A sourced function is defined to the
 * database with a reference to a built-in function or another user-defined function. Sourced functions can be used to extend built-in column and scalar functions for use on distinct types. A user-defined function resides in the schema in which it was created.
 *  - Generated user-defined functions for distinct types are functions that the database manager automatically generates when a distinct type is created using the CREATE DISTINCT TYPE statement. These functions support casting from the distinct type to the source type and from the source type to the distinct type. The ability to cast between the data types is important because a distinct type is compatible only with itself. The generated user-defined functions for distinct types reside in the same schema as the distinct type for which they were created. For more information about the functions that are generated for a distinct type, see "CREATE DISTINCT TYPE" on page 319.
 * 
 * Another way to classify functions is as column, scalar, or table functions, depending on the input data values and result values.
 *  - A column function receives a set of values for each argument (such as the values of a column) and returns a single-value result for the set of input values. Column functions are sometimes called aggregating functions. Built-in functions and user-defined sourced functions can be column functions.
 *  - A scalar function receives a single value for each argument and returns a single-value result. Built-in functions and user-defined functions can be scalar functions. Generated user-defined functions for distinct types are also scalar functions.
 *  - A table function returns a table for the set of arguments it receives. Each argument is a single value. A table function can be referenced only in the FROM clause of a subselect. A table function can be defined as an external function, but a table function cannot be a sourced function. Table functions can be used to apply SQL language processing power to data that is not data that is not stored in the database or to allow access to such data
 * as if it were stored in a result table. For example, a table function can take a file and convert it to a table, get data from the Web and tabularize it, or access a Lotus Notes database and return information about email messages.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isFinalCall <em>Final Call</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isScratchPad <em>Scratch Pad</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getScratchPadLength <em>Scratch Pad Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getFunctionType <em>Function Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isExternalAction <em>External Action</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isAllowParallel <em>Allow Parallel</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getReturnClause <em>Return Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isInheritLockRequest <em>Inherit Lock Request</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getDialect <em>Dialect</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isInline <em>Inline</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isSecured <em>Secured</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface DB2Function extends DB2Routine {
   /**
    * Pseudo-enumerated value for type.  COLUMN_TYPE means
    * the function returns a column.
    * @see DB2Function#setType(String);
    */
   String COLUMN_TYPE = "C"; //$NON-NLS-1$

   /**
    * Pseudo-enumerated value for type.  SCALAR_TYPE means
    * the function returns a scalar.
    * @see DB2Function#setType(String);
    */
   String SCALAR_TYPE = "S"; //$NON-NLS-1$

   /**
    * Pseudo-enumerated value for type.  TABLE_TYPE means
    * the function returns a table.
    * @see DB2Function#setType(String);
    */
   String TABLE_TYPE = "T"; //$NON-NLS-1$

   /**
    * Pseudo-enumerated value for type.  ROW_TYPE means
    * the function returns a row.
    * @see DB2Function#setType(String);
    */
   String ROW_TYPE = "R"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Final Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This optional clause specifies whether a final call is to be made to an 
	 * external function. The purpose of such a final call is to enable the external 
	 * function to free any system resources it has acquired. It can be useful in 
	 * conjunction with the SCRATCHPAD keyword in situations where the 
	 * external function acquires system resources such as memory and anchors 
	 * them in the scratchpad.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Final Call</em>' attribute.
	 * @see #setFinalCall(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_FinalCall()
	 * @model
	 * @generated
	 */
	boolean isFinalCall();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isFinalCall <em>Final Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final Call</em>' attribute.
	 * @see #isFinalCall()
	 * @generated
	 */
	void setFinalCall(boolean value);

	/**
	 * Returns the value of the '<em><b>Scratch Pad</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <DL> 
	 * <DT>SCRATCHPAD (True) 
	 * <DD>Indicates whether the function requires a static memory area. 
	 * <DT>NO SCRATCHPAD (False) 
	 * <DD>Indicates that the function does not require a persistent memory area. 
	 * </DL> 
	 * <p> 
	 * If True, then there can be an optional length (see scratchPadLength) 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scratch Pad</em>' attribute.
	 * @see #setScratchPad(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_ScratchPad()
	 * @model
	 * @generated
	 */
	boolean isScratchPad();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isScratchPad <em>Scratch Pad</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scratch Pad</em>' attribute.
	 * @see #isScratchPad()
	 * @generated
	 */
	void setScratchPad(boolean value);

	/**
	 * Returns the value of the '<em><b>Scratch Pad Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this is null or empty or a string value other than 1..32767, then 
	 * NO SCRATCHPAD 
	 * otherwise there will be 
	 * SCRATCHPAD <length> 
	 * where the default is 100, the same as if no length was specified. 
	 * 
	 * NO SCRATCHPAD or SCRATCHPAD length 
	 * This optional clause may be used to specify whether a scratchpad is to be 
	 * provided for an external function. (It is strongly recommended that 
	 * user-defined functions be re-entrant, so a scratchpad provides a means for 
	 * the function to "save state" from one call to the next.) 
	 * 
	 * SCRATCHPAD is not supported for PARAMETER STYLE JAVA functions.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scratch Pad Length</em>' attribute.
	 * @see #setScratchPadLength(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_ScratchPadLength()
	 * @model
	 * @generated
	 */
	int getScratchPadLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getScratchPadLength <em>Scratch Pad Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scratch Pad Length</em>' attribute.
	 * @see #getScratchPadLength()
	 * @generated
	 */
	void setScratchPadLength(int value);

	/**
	 * Returns the value of the '<em><b>Function Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <p>
    * Please refer to the defined constants at the beginning of this module for
    * the values to use when passing in the argument.
    * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The valid values to indicate the function type are: 
	 * C (Column) 
	 * S (Scaler) 
	 * T (Table) 
	 * R (Row)
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function Type</em>' attribute.
	 * @see #setFunctionType(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_FunctionType()
	 * @model
	 * @generated
	 */
	String getFunctionType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getFunctionType <em>Function Type</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <p>
    * Please refer to the defined constants at the beginning of this module for
    * the values to use when passing in the argument.
    * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Type</em>' attribute.
	 * @see #getFunctionType()
	 * @generated
	 */
	void setFunctionType(String value);

	/**
	 * Returns the value of the '<em><b>Predicate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the filtering and/or index extension exploitation performed when 
	 * this function is used in a predicate. A predicate-specification allows the 
	 * optional SELECTIVITY clause of a search-condition to be specified. If the 
	 * PREDICATES clause is specified, the function must be defined as 
	 * DETERMINISTIC with NO EXTERNAL ACTION (SQLSTATE 42613).
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicate</em>' attribute.
	 * @see #setPredicate(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_Predicate()
	 * @model
	 * @generated
	 */
	String getPredicate();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getPredicate <em>Predicate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicate</em>' attribute.
	 * @see #getPredicate()
	 * @generated
	 */
	void setPredicate(String value);

	/**
	 * Returns the value of the '<em><b>External Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This optional clause specifies whether or not the function takes some 
	 * action that changes the state of an object not managed by the database 
	 * manager. Optimizations that assume functions have no external impacts 
	 * are prevented by specifying EXTERNAL ACTION. For example: sending a 
	 * message, ringing a bell, or writing a record to a file.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Action</em>' attribute.
	 * @see #setExternalAction(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_ExternalAction()
	 * @model
	 * @generated
	 */
   boolean isExternalAction();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isExternalAction <em>External Action</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Action</em>' attribute.
	 * @see #isExternalAction()
	 * @generated
	 */
   void setExternalAction(boolean value);

	/**
	 * Returns the value of the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From the UDB 7.1 documentation for CREATE FUNCTION (External Table): 
	 * 
	 * This optional clause provides an estimate of the expected number of rows 
	 * to be returned by the function for optimization purposes. Valid values for 
	 * integer range from 0 to 2,147,483,647 inclusive. 
	 * 
	 * If the CARDINALITY clause is not specified for a table function, DB2 will 
	 * assume a finite value as a default- the same value assumed for tables for 
	 * which the RUNSTATS utility has not gathered statistics. 
	 * 
	 * Warning: if a function does in fact have infinite cardinality, i.e. it returns a 
	 * row every time it is called to do so, never returning the .end-of-table. 
	 * condition, then queries which require the .end-of-table. condition to 
	 * correctly function will be infinite, and will have to be interrupted. 
	 * 
	 * Examples of such queries are those involving GROUP BY and ORDER BY. 
	 * The user is advised to not write such UDFs.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cardinality</em>' attribute.
	 * @see #setCardinality(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_Cardinality()
	 * @model
	 * @generated
	 */
   int getCardinality();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getCardinality <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cardinality</em>' attribute.
	 * @see #getCardinality()
	 * @generated
	 */
   void setCardinality(int value);

	/**
	 * Returns the value of the '<em><b>Allow Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This optional clause specifies whether, for a single reference to the 
	 * function, the invocation of the function can be parallelized. In general, the 
	 * invocations of most scalar functions should be parallelizable, but there 
	 * may be functions (such as those depending on a single copy of a 
	 * scratchpad) that cannot. If either ALLOW PARALLEL or DISALLOW 
	 * PARALLEL are specified for a scalar function, then DB2 will accept this 
	 * specification. 
	 * 
	 * The syntax diagram indicates that the default value is ALLOW PARALLEL. 
	 * However, the default is DISALLOW PARALLEL if one or more of the 
	 * following options is specified in the statement: 
	 * NOT DETERMINISTIC 
	 * EXTERNAL ACTION 
	 * SCRATCHPAD 
	 * FINAL CALL 
	 * MODIFIES SQL DATA (version 8) 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Allow Parallel</em>' attribute.
	 * @see #setAllowParallel(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_AllowParallel()
	 * @model
	 * @generated
	 */
   boolean isAllowParallel();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isAllowParallel <em>Allow Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allow Parallel</em>' attribute.
	 * @see #isAllowParallel()
	 * @generated
	 */
   void setAllowParallel(boolean value);

	/**
	 * Returns the value of the '<em><b>Return Clause</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains the text of the SQL statement in the 
	 * RETURN <SQL-statement> 
	 * 
	 * temporary until we hook in the SQLStatement portion of the model.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Return Clause</em>' attribute.
	 * @see #setReturnClause(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_ReturnClause()
	 * @model
	 * @generated
	 */
   String getReturnClause();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getReturnClause <em>Return Clause</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Clause</em>' attribute.
	 * @see #getReturnClause()
	 * @generated
	 */
   void setReturnClause(String value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.OriginType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.OriginType
	 * @see #setOrigin(OriginType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_Origin()
	 * @model
	 * @generated
	 */
	OriginType getOrigin();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getOrigin <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.OriginType
	 * @see #getOrigin()
	 * @generated
	 */
	void setOrigin(OriginType value);

	/**
	 * Returns the value of the '<em><b>Inherit Lock Request</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherit Lock Request</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherit Lock Request</em>' attribute.
	 * @see #setInheritLockRequest(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_InheritLockRequest()
	 * @model
	 * @generated
	 */
	boolean isInheritLockRequest();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isInheritLockRequest <em>Inherit Lock Request</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherit Lock Request</em>' attribute.
	 * @see #isInheritLockRequest()
	 * @generated
	 */
	void setInheritLockRequest(boolean value);

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
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_Dialect()
	 * @model
	 * @generated
	 */
	SourceDialect getDialect();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getDialect <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dialect</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect
	 * @see #getDialect()
	 * @generated
	 */
	void setDialect(SourceDialect value);

	/**
	 * Returns the value of the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inline</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inline</em>' attribute.
	 * @see #setInline(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_Inline()
	 * @model
	 * @generated
	 */
	boolean isInline();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isInline <em>Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inline</em>' attribute.
	 * @see #isInline()
	 * @generated
	 */
	void setInline(boolean value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_Version()
	 * @model
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Secured</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secured</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secured</em>' attribute.
	 * @see #setSecured(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Function_Secured()
	 * @model
	 * @generated
	 */
	boolean isSecured();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isSecured <em>Secured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secured</em>' attribute.
	 * @see #isSecured()
	 * @generated
	 */
	void setSecured(boolean value);

} // DB2Function
