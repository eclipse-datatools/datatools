/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEProxyTable.java,v 1.7 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Proxy Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getExternalType <em>External Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#isExisting <em>Existing</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getColumnDelimiter <em>Column Delimiter</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getExternalPath <em>External Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProxyTable()
 * @model
 * @generated
 */
public interface SybaseASEProxyTable extends SybaseASETable {
	/**
     * Returns the value of the '<em><b>External Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>External Type</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType
     * @see #setExternalType(ProxyTableExternalType)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProxyTable_ExternalType()
     * @model
     * @generated
     */
	ProxyTableExternalType getExternalType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getExternalType <em>External Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>External Type</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType
     * @see #getExternalType()
     * @generated
     */
	void setExternalType(ProxyTableExternalType value);

	/**
     * Returns the value of the '<em><b>Existing</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Existing</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Existing</em>' attribute.
     * @see #setExisting(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProxyTable_Existing()
     * @model
     * @generated
     */
	boolean isExisting();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#isExisting <em>Existing</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Existing</em>' attribute.
     * @see #isExisting()
     * @generated
     */
	void setExisting(boolean value);

	/**
     * Returns the value of the '<em><b>Column Delimiter</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Delimiter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Column Delimiter</em>' attribute.
     * @see #setColumnDelimiter(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProxyTable_ColumnDelimiter()
     * @model
     * @generated
     */
	String getColumnDelimiter();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getColumnDelimiter <em>Column Delimiter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Delimiter</em>' attribute.
     * @see #getColumnDelimiter()
     * @generated
     */
	void setColumnDelimiter(String value);

	/**
     * Returns the value of the '<em><b>External Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>External Path</em>' attribute.
     * @see #setExternalPath(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProxyTable_ExternalPath()
     * @model
     * @generated
     */
	String getExternalPath();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getExternalPath <em>External Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>External Path</em>' attribute.
     * @see #getExternalPath()
     * @generated
     */
	void setExternalPath(String value);

} // SybaseASEProxyTable
