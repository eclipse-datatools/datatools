/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseDatabase.java,v 1.3 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDataTypes <em>Data Types</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getWebServices <em>Web Services</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDbSpaces <em>Db Spaces</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDatabaseFileName <em>Database File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getLogFileName <em>Log File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getMirrorFileName <em>Mirror File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isCaseSensitive <em>Case Sensitive</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getCollation <em>Collation</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isBlankPaddingOn <em>Blank Padding On</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isCheckSumOn <em>Check Sum On</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isJConnectOn <em>JConnect On</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getPageSize <em>Page Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getEncryptionInfo <em>Encryption Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getJavaSupport <em>Java Support</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getPasswordCaseSensitive <em>Password Case Sensitive</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase()
 * @model
 * @generated
 */
public interface SybaseASABaseDatabase extends Database, SQLObject
{
    /**
	 * Returns the value of the '<em><b>Data Types</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Types</em>' reference list.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_DataTypes()
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType#getDatabase
	 * @model type="org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType" opposite="database"
	 * @generated
	 */
	EList getDataTypes();

    /**
	 * Returns the value of the '<em><b>Web Services</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Web Services</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Web Services</em>' reference list.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_WebServices()
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getDatabase
	 * @model type="org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService" opposite="database"
	 * @generated
	 */
	EList getWebServices();

    /**
	 * Returns the value of the '<em><b>Db Spaces</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Db Spaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Db Spaces</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_DbSpaces()
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace#getDatabase
	 * @model type="org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace" opposite="database" containment="true" required="true"
	 * @generated
	 */
	EList getDbSpaces();

    /**
	 * Returns the value of the '<em><b>Database File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database File Name</em>' attribute.
	 * @see #setDatabaseFileName(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_DatabaseFileName()
	 * @model required="true"
	 * @generated
	 */
	String getDatabaseFileName();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDatabaseFileName <em>Database File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database File Name</em>' attribute.
	 * @see #getDatabaseFileName()
	 * @generated
	 */
	void setDatabaseFileName(String value);

    /**
	 * Returns the value of the '<em><b>Log File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Log File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Log File Name</em>' attribute.
	 * @see #setLogFileName(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_LogFileName()
	 * @model
	 * @generated
	 */
	String getLogFileName();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getLogFileName <em>Log File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Log File Name</em>' attribute.
	 * @see #getLogFileName()
	 * @generated
	 */
	void setLogFileName(String value);

    /**
	 * Returns the value of the '<em><b>Mirror File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mirror File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mirror File Name</em>' attribute.
	 * @see #setMirrorFileName(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_MirrorFileName()
	 * @model
	 * @generated
	 */
	String getMirrorFileName();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getMirrorFileName <em>Mirror File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mirror File Name</em>' attribute.
	 * @see #getMirrorFileName()
	 * @generated
	 */
	void setMirrorFileName(String value);

    /**
	 * Returns the value of the '<em><b>Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Case Sensitive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Case Sensitive</em>' attribute.
	 * @see #setCaseSensitive(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_CaseSensitive()
	 * @model
	 * @generated
	 */
	boolean isCaseSensitive();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isCaseSensitive <em>Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Case Sensitive</em>' attribute.
	 * @see #isCaseSensitive()
	 * @generated
	 */
	void setCaseSensitive(boolean value);

    /**
	 * Returns the value of the '<em><b>Collation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collation</em>' attribute.
	 * @see #setCollation(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_Collation()
	 * @model
	 * @generated
	 */
	String getCollation();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getCollation <em>Collation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collation</em>' attribute.
	 * @see #getCollation()
	 * @generated
	 */
	void setCollation(String value);

    /**
	 * Returns the value of the '<em><b>Blank Padding On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Blank Padding On</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Blank Padding On</em>' attribute.
	 * @see #setBlankPaddingOn(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_BlankPaddingOn()
	 * @model
	 * @generated
	 */
	boolean isBlankPaddingOn();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isBlankPaddingOn <em>Blank Padding On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Blank Padding On</em>' attribute.
	 * @see #isBlankPaddingOn()
	 * @generated
	 */
	void setBlankPaddingOn(boolean value);

    /**
	 * Returns the value of the '<em><b>Check Sum On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check Sum On</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check Sum On</em>' attribute.
	 * @see #setCheckSumOn(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_CheckSumOn()
	 * @model
	 * @generated
	 */
	boolean isCheckSumOn();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isCheckSumOn <em>Check Sum On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check Sum On</em>' attribute.
	 * @see #isCheckSumOn()
	 * @generated
	 */
	void setCheckSumOn(boolean value);

    /**
	 * Returns the value of the '<em><b>JConnect On</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>JConnect On</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>JConnect On</em>' attribute.
	 * @see #setJConnectOn(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_JConnectOn()
	 * @model default="true"
	 * @generated
	 */
	boolean isJConnectOn();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isJConnectOn <em>JConnect On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>JConnect On</em>' attribute.
	 * @see #isJConnectOn()
	 * @generated
	 */
	void setJConnectOn(boolean value);

    /**
	 * Returns the value of the '<em><b>Page Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Only 2048 | 4096 | 8192 | 16384 | 32768 are valid values
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Page Size</em>' attribute.
	 * @see #setPageSize(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_PageSize()
	 * @model
	 * @generated
	 */
	int getPageSize();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getPageSize <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Size</em>' attribute.
	 * @see #getPageSize()
	 * @generated
	 */
	void setPageSize(int value);

    /**
	 * Returns the value of the '<em><b>Encryption Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encryption Info</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encryption Info</em>' reference.
	 * @see #setEncryptionInfo(EncryptionInfo)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_EncryptionInfo()
	 * @model
	 * @generated
	 */
	EncryptionInfo getEncryptionInfo();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getEncryptionInfo <em>Encryption Info</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encryption Info</em>' reference.
	 * @see #getEncryptionInfo()
	 * @generated
	 */
	void setEncryptionInfo(EncryptionInfo value);

    /**
	 * Returns the value of the '<em><b>Java Support</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Support</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Support</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType
	 * @see #setJavaSupport(JavaSupportType)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_JavaSupport()
	 * @model
	 * @generated
	 */
	JavaSupportType getJavaSupport();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getJavaSupport <em>Java Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Support</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType
	 * @see #getJavaSupport()
	 * @generated
	 */
	void setJavaSupport(JavaSupportType value);

    /**
	 * Returns the value of the '<em><b>Password Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * if passwordCaseSensitive attribute is null, it follow the caseSensitive
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Password Case Sensitive</em>' attribute.
	 * @see #setPasswordCaseSensitive(Boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseDatabase_PasswordCaseSensitive()
	 * @model
	 * @generated
	 */
	Boolean getPasswordCaseSensitive();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getPasswordCaseSensitive <em>Password Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password Case Sensitive</em>' attribute.
	 * @see #getPasswordCaseSensitive()
	 * @generated
	 */
	void setPasswordCaseSensitive(Boolean value);

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isBaseOnASA10();
	
	/**
	 * 
	 * @NOT generated
	 */
	EList getDatabaseSchemas();

} // SybaseASABaseDatabase