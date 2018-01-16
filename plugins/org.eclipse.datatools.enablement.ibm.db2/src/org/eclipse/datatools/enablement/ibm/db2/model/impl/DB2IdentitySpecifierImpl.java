/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2IdentitySpecifierImpl.java,v 1.13 2009/07/30 00:21:44 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Identity Specifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IdentitySpecifierImpl#getCache <em>Cache</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IdentitySpecifierImpl#isOrder <em>Order</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IdentitySpecifierImpl#isSystemGenerated <em>System Generated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IdentitySpecifierImpl#getRestartValue <em>Restart Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2IdentitySpecifierImpl extends IdentitySpecifierImpl implements DB2IdentitySpecifier {
	/**
	 * The default value of the '{@link #getCache() <em>Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCache()
	 * @generated
	 * @ordered
	 */
	protected static final int CACHE_EDEFAULT = 20;

	/**
	 * The cached value of the '{@link #getCache() <em>Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCache()
	 * @generated
	 * @ordered
	 */
	protected int cache = CACHE_EDEFAULT;

	/**
	 * The default value of the '{@link #isOrder() <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrder()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOrder() <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrder()
	 * @generated
	 * @ordered
	 */
	protected boolean order = ORDER_EDEFAULT;

	/**
	 * The default value of the '{@link #isSystemGenerated() <em>System Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSystemGenerated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYSTEM_GENERATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSystemGenerated() <em>System Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSystemGenerated()
	 * @generated
	 * @ordered
	 */
	protected boolean systemGenerated = SYSTEM_GENERATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getRestartValue() <em>Restart Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRestartValue()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger RESTART_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRestartValue() <em>Restart Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRestartValue()
	 * @generated
	 * @ordered
	 */
	protected BigInteger restartValue = RESTART_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2IdentitySpecifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_IDENTITY_SPECIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCache() {
		return cache;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCache(int newCache) {
		int oldCache = cache;
		cache = newCache;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_IDENTITY_SPECIFIER__CACHE, oldCache, cache));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOrder() {
		return order;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrder(boolean newOrder) {
		boolean oldOrder = order;
		order = newOrder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_IDENTITY_SPECIFIER__ORDER, oldOrder, order));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSystemGenerated() {
		return systemGenerated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemGenerated(boolean newSystemGenerated) {
		boolean oldSystemGenerated = systemGenerated;
		systemGenerated = newSystemGenerated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_IDENTITY_SPECIFIER__SYSTEM_GENERATED, oldSystemGenerated, systemGenerated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getRestartValue() {
		return restartValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRestartValue(BigInteger newRestartValue) {
		BigInteger oldRestartValue = restartValue;
		restartValue = newRestartValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_IDENTITY_SPECIFIER__RESTART_VALUE, oldRestartValue, restartValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__CACHE:
				return new Integer(getCache());
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__ORDER:
				return isOrder() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__SYSTEM_GENERATED:
				return isSystemGenerated() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__RESTART_VALUE:
				return getRestartValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__CACHE:
				setCache(((Integer)newValue).intValue());
				return;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__ORDER:
				setOrder(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__SYSTEM_GENERATED:
				setSystemGenerated(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__RESTART_VALUE:
				setRestartValue((BigInteger)newValue);
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
		switch (featureID) {
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__CACHE:
				setCache(CACHE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__ORDER:
				setOrder(ORDER_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__SYSTEM_GENERATED:
				setSystemGenerated(SYSTEM_GENERATED_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__RESTART_VALUE:
				setRestartValue(RESTART_VALUE_EDEFAULT);
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
		switch (featureID) {
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__CACHE:
				return cache != CACHE_EDEFAULT;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__ORDER:
				return order != ORDER_EDEFAULT;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__SYSTEM_GENERATED:
				return systemGenerated != SYSTEM_GENERATED_EDEFAULT;
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER__RESTART_VALUE:
				return RESTART_VALUE_EDEFAULT == null ? restartValue != null : !RESTART_VALUE_EDEFAULT.equals(restartValue);
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
		result.append(" (cache: "); //$NON-NLS-1$
		result.append(cache);
		result.append(", order: "); //$NON-NLS-1$
		result.append(order);
		result.append(", systemGenerated: "); //$NON-NLS-1$
		result.append(systemGenerated);
		result.append(", restartValue: "); //$NON-NLS-1$
		result.append(restartValue);
		result.append(')');
		return result.toString();
	}

} //DB2IdentitySpecifierImpl
