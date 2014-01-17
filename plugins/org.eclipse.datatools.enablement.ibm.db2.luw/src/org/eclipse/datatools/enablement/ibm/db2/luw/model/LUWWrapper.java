/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wrapper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLibrary <em>Library</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#isFenced <em>Fenced</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getWrapperType <em>Wrapper Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getDiscoveredLibraries <em>Discovered Libraries</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getServers <em>Servers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLUWDatabase <em>LUW Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getOptions <em>Options</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper()
 * @model abstract="true"
 * @generated
 */
public interface LUWWrapper extends SQLObject {
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
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_Version()
	 * @model
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Library</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Library</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Library</em>' attribute.
	 * @see #setLibrary(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_Library()
	 * @model
	 * @generated
	 */
	String getLibrary();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLibrary <em>Library</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Library</em>' attribute.
	 * @see #getLibrary()
	 * @generated
	 */
	void setLibrary(String value);

	/**
	 * Returns the value of the '<em><b>Fenced</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fenced</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fenced</em>' attribute.
	 * @see #setFenced(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_Fenced()
	 * @model default="true"
	 * @generated
	 */
	boolean isFenced();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#isFenced <em>Fenced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fenced</em>' attribute.
	 * @see #isFenced()
	 * @generated
	 */
	void setFenced(boolean value);

	/**
	 * Returns the value of the '<em><b>Wrapper Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wrapper Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wrapper Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType
	 * @see #setWrapperType(WrapperType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_WrapperType()
	 * @model
	 * @generated
	 */
	WrapperType getWrapperType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getWrapperType <em>Wrapper Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wrapper Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType
	 * @see #getWrapperType()
	 * @generated
	 */
	void setWrapperType(WrapperType value);

	/**
	 * Returns the value of the '<em><b>Data Source</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Source</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource
	 * @see #setDataSource(LUWFederatedDataSource)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_DataSource()
	 * @model
	 * @generated
	 */
	LUWFederatedDataSource getDataSource();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getDataSource <em>Data Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Source</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource
	 * @see #getDataSource()
	 * @generated
	 */
	void setDataSource(LUWFederatedDataSource value);

	/**
	 * Returns the value of the '<em><b>Discovered Libraries</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Discovered Libraries</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Discovered Libraries</em>' attribute list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_DiscoveredLibraries()
	 * @model transient="true"
	 * @generated
	 */
	EList getDiscoveredLibraries();

	/**
	 * Returns the value of the '<em><b>Servers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getWrapper <em>Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servers</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_Servers()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getWrapper
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer" opposite="wrapper"
	 * @generated
	 */
	EList getServers();

	/**
	 * Returns the value of the '<em><b>LUW Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getWrappers <em>Wrappers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LUW Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LUW Database</em>' reference.
	 * @see #setLUWDatabase(LUWDatabase)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_LUWDatabase()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getWrappers
	 * @model opposite="wrappers" required="true"
	 * @generated
	 */
	LUWDatabase getLUWDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLUWDatabase <em>LUW Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LUW Database</em>' reference.
	 * @see #getLUWDatabase()
	 * @generated
	 */
	void setLUWDatabase(LUWDatabase value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWWrapper_Options()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption" containment="true"
	 * @generated
	 */
	EList getOptions();

} // LUWWrapper
