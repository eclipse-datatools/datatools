/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEWebServiceTableImpl.java,v 1.7 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Web Service Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceTableImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceTableImpl#getWSDLURI <em>WSDLURI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEWebServiceTableImpl extends SybaseASEProxyTableImpl implements SybaseASEWebServiceTable 
{
	/**
     * The default value of the '{@link #getMethod() <em>Method</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMethod()
     * @generated
     * @ordered
     */
	protected static final String METHOD_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getMethod() <em>Method</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMethod()
     * @generated
     * @ordered
     */
	protected String method = METHOD_EDEFAULT;

	/**
     * The default value of the '{@link #getWSDLURI() <em>WSDLURI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWSDLURI()
     * @generated
     * @ordered
     */
    protected static final String WSDLURI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWSDLURI() <em>WSDLURI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWSDLURI()
     * @generated
     * @ordered
     */
    protected String wsdluri = WSDLURI_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEWebServiceTableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_WEB_SERVICE_TABLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getMethod() {
        return method;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMethod(String newMethod) {
        String oldMethod = method;
        method = newMethod;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__METHOD, oldMethod, method));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getWSDLURI()
    {
        return wsdluri;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWSDLURI(String newWSDLURI)
    {
        String oldWSDLURI = wsdluri;
        wsdluri = newWSDLURI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__WSDLURI, oldWSDLURI, wsdluri));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__METHOD:
                return getMethod();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__WSDLURI:
                return getWSDLURI();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__METHOD:
                setMethod((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__WSDLURI:
                setWSDLURI((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__METHOD:
                setMethod(METHOD_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__WSDLURI:
                setWSDLURI(WSDLURI_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__METHOD:
                return METHOD_EDEFAULT == null ? method != null : !METHOD_EDEFAULT.equals(method);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE__WSDLURI:
                return WSDLURI_EDEFAULT == null ? wsdluri != null : !WSDLURI_EDEFAULT.equals(wsdluri);
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (method: "); //$NON-NLS-1$
        result.append(method);
        result.append(", WSDLURI: "); //$NON-NLS-1$
        result.append(wsdluri);
        result.append(')');
        return result.toString();
    }

} //SybaseASEWebServiceTableImpl
