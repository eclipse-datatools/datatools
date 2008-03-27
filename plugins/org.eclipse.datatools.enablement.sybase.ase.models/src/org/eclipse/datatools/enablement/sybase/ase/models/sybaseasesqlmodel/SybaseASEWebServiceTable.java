/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEWebServiceTable.java,v 1.7 2007/07/06 08:40:17 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Web Service Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable#getWSDLURI <em>WSDLURI</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEWebServiceTable()
 * @model
 * @generated
 */
public interface SybaseASEWebServiceTable extends SybaseASEProxyTable {
	/**
     * Returns the value of the '<em><b>Method</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Method</em>' attribute.
     * @see #setMethod(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEWebServiceTable_Method()
     * @model default=""
     * @generated
     */
	String getMethod();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable#getMethod <em>Method</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Method</em>' attribute.
     * @see #getMethod()
     * @generated
     */
	void setMethod(String value);

    /**
     * Returns the value of the '<em><b>WSDLURI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>WSDLURI</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>WSDLURI</em>' attribute.
     * @see #setWSDLURI(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEWebServiceTable_WSDLURI()
     * @model
     * @generated
     */
    String getWSDLURI();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable#getWSDLURI <em>WSDLURI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>WSDLURI</em>' attribute.
     * @see #getWSDLURI()
     * @generated
     */
    void setWSDLURI(String value);

} // SybaseASEWebServiceTable
