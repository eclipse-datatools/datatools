/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseProxyTable.java,v 1.1 2007/03/05 15:52:14 jgraham Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Proxy Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable#getRemoteObjectLocation <em>Remote Object Location</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseProxyTable()
 * @model
 * @generated
 */
public interface SybaseASABaseProxyTable extends SybaseASABaseTable {
	/**
	 * Returns the value of the '<em><b>Remote Object Location</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Object Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Object Location</em>' attribute.
	 * @see #setRemoteObjectLocation(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseProxyTable_RemoteObjectLocation()
	 * @model default=""
	 * @generated
	 */
	String getRemoteObjectLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable#getRemoteObjectLocation <em>Remote Object Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Object Location</em>' attribute.
	 * @see #getRemoteObjectLocation()
	 * @generated
	 */
	void setRemoteObjectLocation(String value);

} // SybaseASABaseProxyTable