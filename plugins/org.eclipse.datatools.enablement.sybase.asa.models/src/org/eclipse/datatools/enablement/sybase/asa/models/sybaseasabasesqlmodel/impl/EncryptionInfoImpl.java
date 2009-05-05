/**
 * <copyright>
 * </copyright>
 *
 * $Id: EncryptionInfoImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Encryption Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EncryptionInfoImpl#isEncryptedTable <em>Encrypted Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EncryptionInfoImpl#getEncryptionKey <em>Encryption Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EncryptionInfoImpl#getAlgorithm <em>Algorithm</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EncryptionInfoImpl extends EObjectImpl implements EncryptionInfo 
{
    /**
	 * The default value of the '{@link #isEncryptedTable() <em>Encrypted Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEncryptedTable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENCRYPTED_TABLE_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isEncryptedTable() <em>Encrypted Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEncryptedTable()
	 * @generated
	 * @ordered
	 */
	protected boolean encryptedTable = ENCRYPTED_TABLE_EDEFAULT;

    /**
	 * The default value of the '{@link #getEncryptionKey() <em>Encryption Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncryptionKey()
	 * @generated
	 * @ordered
	 */
	protected static final String ENCRYPTION_KEY_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getEncryptionKey() <em>Encryption Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncryptionKey()
	 * @generated
	 * @ordered
	 */
	protected String encryptionKey = ENCRYPTION_KEY_EDEFAULT;

    /**
	 * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected static final String ALGORITHM_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected String algorithm = ALGORITHM_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EncryptionInfoImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass()
    {
		return SybaseasabasesqlmodelPackage.Literals.ENCRYPTION_INFO;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEncryptedTable()
    {
		return encryptedTable;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncryptedTable(boolean newEncryptedTable)
    {
		boolean oldEncryptedTable = encryptedTable;
		encryptedTable = newEncryptedTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTED_TABLE, oldEncryptedTable, encryptedTable));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEncryptionKey()
    {
		return encryptionKey;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncryptionKey(String newEncryptionKey)
    {
		String oldEncryptionKey = encryptionKey;
		encryptionKey = newEncryptionKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTION_KEY, oldEncryptionKey, encryptionKey));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAlgorithm()
    {
		return algorithm;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlgorithm(String newAlgorithm)
    {
		String oldAlgorithm = algorithm;
		algorithm = newAlgorithm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ALGORITHM, oldAlgorithm, algorithm));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTED_TABLE:
				return isEncryptedTable() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTION_KEY:
				return getEncryptionKey();
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ALGORITHM:
				return getAlgorithm();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTED_TABLE:
				setEncryptedTable(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTION_KEY:
				setEncryptionKey((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ALGORITHM:
				setAlgorithm((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTED_TABLE:
				setEncryptedTable(ENCRYPTED_TABLE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTION_KEY:
				setEncryptionKey(ENCRYPTION_KEY_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ALGORITHM:
				setAlgorithm(ALGORITHM_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTED_TABLE:
				return encryptedTable != ENCRYPTED_TABLE_EDEFAULT;
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ENCRYPTION_KEY:
				return ENCRYPTION_KEY_EDEFAULT == null ? encryptionKey != null : !ENCRYPTION_KEY_EDEFAULT.equals(encryptionKey);
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO__ALGORITHM:
				return ALGORITHM_EDEFAULT == null ? algorithm != null : !ALGORITHM_EDEFAULT.equals(algorithm);
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString()
    {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (encryptedTable: ");
		result.append(encryptedTable);
		result.append(", encryptionKey: ");
		result.append(encryptionKey);
		result.append(", algorithm: ");
		result.append(algorithm);
		result.append(')');
		return result.toString();
	}

} //EncryptionInfoImpl