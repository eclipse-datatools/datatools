/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEProxyTableImpl.java,v 1.7 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Proxy Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProxyTableImpl#getExternalType <em>External Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProxyTableImpl#isExisting <em>Existing</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProxyTableImpl#getColumnDelimiter <em>Column Delimiter</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProxyTableImpl#getExternalPath <em>External Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEProxyTableImpl extends SybaseASETableImpl implements SybaseASEProxyTable 
{
	/**
     * The default value of the '{@link #getExternalType() <em>External Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExternalType()
     * @generated
     * @ordered
     */
	protected static final ProxyTableExternalType EXTERNAL_TYPE_EDEFAULT = ProxyTableExternalType.TABLE_LITERAL;

	/**
     * The cached value of the '{@link #getExternalType() <em>External Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExternalType()
     * @generated
     * @ordered
     */
	protected ProxyTableExternalType externalType = EXTERNAL_TYPE_EDEFAULT;

	/**
     * The default value of the '{@link #isExisting() <em>Existing</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isExisting()
     * @generated
     * @ordered
     */
	protected static final boolean EXISTING_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isExisting() <em>Existing</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isExisting()
     * @generated
     * @ordered
     */
	protected boolean existing = EXISTING_EDEFAULT;

	/**
     * The default value of the '{@link #getColumnDelimiter() <em>Column Delimiter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getColumnDelimiter()
     * @generated
     * @ordered
     */
	protected static final String COLUMN_DELIMITER_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getColumnDelimiter() <em>Column Delimiter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getColumnDelimiter()
     * @generated
     * @ordered
     */
	protected String columnDelimiter = COLUMN_DELIMITER_EDEFAULT;

	/**
     * The default value of the '{@link #getExternalPath() <em>External Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExternalPath()
     * @generated
     * @ordered
     */
	protected static final String EXTERNAL_PATH_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getExternalPath() <em>External Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExternalPath()
     * @generated
     * @ordered
     */
	protected String externalPath = EXTERNAL_PATH_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEProxyTableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_PROXY_TABLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ProxyTableExternalType getExternalType() {
        return externalType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setExternalType(ProxyTableExternalType newExternalType) {
        ProxyTableExternalType oldExternalType = externalType;
        externalType = newExternalType == null ? EXTERNAL_TYPE_EDEFAULT : newExternalType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE, oldExternalType, externalType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isExisting() {
        return existing;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setExisting(boolean newExisting) {
        boolean oldExisting = existing;
        existing = newExisting;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXISTING, oldExisting, existing));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getColumnDelimiter() {
        return columnDelimiter;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setColumnDelimiter(String newColumnDelimiter) {
        String oldColumnDelimiter = columnDelimiter;
        columnDelimiter = newColumnDelimiter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER, oldColumnDelimiter, columnDelimiter));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getExternalPath() {
        return externalPath;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setExternalPath(String newExternalPath) {
        String oldExternalPath = externalPath;
        externalPath = newExternalPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH, oldExternalPath, externalPath));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE:
                return getExternalType();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXISTING:
                return isExisting() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER:
                return getColumnDelimiter();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH:
                return getExternalPath();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE:
                setExternalType((ProxyTableExternalType)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXISTING:
                setExisting(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER:
                setColumnDelimiter((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH:
                setExternalPath((String)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE:
                setExternalType(EXTERNAL_TYPE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXISTING:
                setExisting(EXISTING_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER:
                setColumnDelimiter(COLUMN_DELIMITER_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH:
                setExternalPath(EXTERNAL_PATH_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE:
                return externalType != EXTERNAL_TYPE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXISTING:
                return existing != EXISTING_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER:
                return COLUMN_DELIMITER_EDEFAULT == null ? columnDelimiter != null : !COLUMN_DELIMITER_EDEFAULT.equals(columnDelimiter);
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH:
                return EXTERNAL_PATH_EDEFAULT == null ? externalPath != null : !EXTERNAL_PATH_EDEFAULT.equals(externalPath);
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
        result.append(" (externalType: "); //$NON-NLS-1$
        result.append(externalType);
        result.append(", existing: "); //$NON-NLS-1$
        result.append(existing);
        result.append(", columnDelimiter: "); //$NON-NLS-1$
        result.append(columnDelimiter);
        result.append(", externalPath: "); //$NON-NLS-1$
        result.append(externalPath);
        result.append(')');
        return result.toString();
    }

} //SybaseASEProxyTableImpl
