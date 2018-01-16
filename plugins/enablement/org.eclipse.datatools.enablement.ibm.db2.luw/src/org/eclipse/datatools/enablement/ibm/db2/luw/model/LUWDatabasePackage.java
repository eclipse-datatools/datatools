/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWDatabasePackage.java,v 1.3 2009/03/16 21:08:37 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getCreator <em>Creator</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getBinder <em>Binder</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getCursorBlock <em>Cursor Block</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getNumberOfSections <em>Number Of Sections</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getOptimizationClass <em>Optimization Class</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getExplainSnapshot <em>Explain Snapshot</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabasePackage()
 * @model
 * @generated
 */
public interface LUWDatabasePackage extends DB2Package {

	/**
	 * Returns the value of the '<em><b>Creator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creator</em>' attribute.
	 * @see #setCreator(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabasePackage_Creator()
	 * @model
	 * @generated
	 */
	String getCreator();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getCreator <em>Creator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creator</em>' attribute.
	 * @see #getCreator()
	 * @generated
	 */
	void setCreator(String value);

	/**
	 * Returns the value of the '<em><b>Binder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binder</em>' attribute.
	 * @see #setBinder(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabasePackage_Binder()
	 * @model
	 * @generated
	 */
	String getBinder();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getBinder <em>Binder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binder</em>' attribute.
	 * @see #getBinder()
	 * @generated
	 */
	void setBinder(String value);

	/**
	 * Returns the value of the '<em><b>Cursor Block</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cursor Block</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cursor Block</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType
	 * @see #setCursorBlock(CursorBlockType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabasePackage_CursorBlock()
	 * @model
	 * @generated
	 */
	CursorBlockType getCursorBlock();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getCursorBlock <em>Cursor Block</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cursor Block</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType
	 * @see #getCursorBlock()
	 * @generated
	 */
	void setCursorBlock(CursorBlockType value);

	/**
	 * Returns the value of the '<em><b>Number Of Sections</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Sections</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Sections</em>' attribute.
	 * @see #setNumberOfSections(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabasePackage_NumberOfSections()
	 * @model
	 * @generated
	 */
	int getNumberOfSections();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getNumberOfSections <em>Number Of Sections</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Sections</em>' attribute.
	 * @see #getNumberOfSections()
	 * @generated
	 */
	void setNumberOfSections(int value);

	/**
	 * Returns the value of the '<em><b>Optimization Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optimization Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optimization Class</em>' attribute.
	 * @see #setOptimizationClass(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabasePackage_OptimizationClass()
	 * @model
	 * @generated
	 */
	int getOptimizationClass();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getOptimizationClass <em>Optimization Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optimization Class</em>' attribute.
	 * @see #getOptimizationClass()
	 * @generated
	 */
	void setOptimizationClass(int value);

	/**
	 * Returns the value of the '<em><b>Explain Snapshot</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explain Snapshot</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explain Snapshot</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType
	 * @see #setExplainSnapshot(ExplainSnaphotType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabasePackage_ExplainSnapshot()
	 * @model
	 * @generated
	 */
	ExplainSnaphotType getExplainSnapshot();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getExplainSnapshot <em>Explain Snapshot</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Explain Snapshot</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType
	 * @see #getExplainSnapshot()
	 * @generated
	 */
	void setExplainSnapshot(ExplainSnaphotType value);
} // LUWDatabasePackage
