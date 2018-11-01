/**
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *   Ingres Corporation - initial API and implementation
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl;

import java.math.BigInteger;

import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ingres Identity Specifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl#getSeqLength <em>Seq Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl#getSeqPrecision <em>Seq Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl#getMaximumOption <em>Maximum Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl#getMinimumOption <em>Minimum Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl#getCacheSize <em>Cache Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl#getCacheOption <em>Cache Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl#getOrderOption <em>Order Option</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IngresIdentitySpecifierImpl extends IdentitySpecifierImpl implements IngresIdentitySpecifier {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * The default value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected String dataType = DATA_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeqLength() <em>Seq Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeqLength()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger SEQ_LENGTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSeqLength() <em>Seq Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeqLength()
	 * @generated
	 * @ordered
	 */
	protected BigInteger seqLength = SEQ_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeqPrecision() <em>Seq Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeqPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger SEQ_PRECISION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSeqPrecision() <em>Seq Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeqPrecision()
	 * @generated
	 * @ordered
	 */
	protected BigInteger seqPrecision = SEQ_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumOption() <em>Maximum Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumOption()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean MAXIMUM_OPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaximumOption() <em>Maximum Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumOption()
	 * @generated
	 * @ordered
	 */
	protected Boolean maximumOption = MAXIMUM_OPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinimumOption() <em>Minimum Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumOption()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean MINIMUM_OPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinimumOption() <em>Minimum Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumOption()
	 * @generated
	 * @ordered
	 */
	protected Boolean minimumOption = MINIMUM_OPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getCacheSize() <em>Cache Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheSize()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger CACHE_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCacheSize() <em>Cache Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheSize()
	 * @generated
	 * @ordered
	 */
	protected BigInteger cacheSize = CACHE_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCacheOption() <em>Cache Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheOption()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean CACHE_OPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCacheOption() <em>Cache Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheOption()
	 * @generated
	 * @ordered
	 */
	protected Boolean cacheOption = CACHE_OPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrderOption() <em>Order Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderOption()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ORDER_OPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrderOption() <em>Order Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderOption()
	 * @generated
	 * @ordered
	 */
	protected Boolean orderOption = ORDER_OPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IngresIdentitySpecifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return IngressqlmodelPackage.Literals.INGRES_IDENTITY_SPECIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(String newDataType) {
		String oldDataType = dataType;
		dataType = newDataType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__DATA_TYPE, oldDataType, dataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getSeqLength() {
		return seqLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeqLength(BigInteger newSeqLength) {
		BigInteger oldSeqLength = seqLength;
		seqLength = newSeqLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_LENGTH, oldSeqLength, seqLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getSeqPrecision() {
		return seqPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeqPrecision(BigInteger newSeqPrecision) {
		BigInteger oldSeqPrecision = seqPrecision;
		seqPrecision = newSeqPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_PRECISION, oldSeqPrecision, seqPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getMaximumOption() {
		return maximumOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumOption(Boolean newMaximumOption) {
		Boolean oldMaximumOption = maximumOption;
		maximumOption = newMaximumOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MAXIMUM_OPTION, oldMaximumOption, maximumOption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getMinimumOption() {
		return minimumOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimumOption(Boolean newMinimumOption) {
		Boolean oldMinimumOption = minimumOption;
		minimumOption = newMinimumOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MINIMUM_OPTION, oldMinimumOption, minimumOption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCacheSize() {
		return cacheSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCacheSize(BigInteger newCacheSize) {
		BigInteger oldCacheSize = cacheSize;
		cacheSize = newCacheSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_SIZE, oldCacheSize, cacheSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getCacheOption() {
		return cacheOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCacheOption(Boolean newCacheOption) {
		Boolean oldCacheOption = cacheOption;
		cacheOption = newCacheOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_OPTION, oldCacheOption, cacheOption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getOrderOption() {
		return orderOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderOption(Boolean newOrderOption) {
		Boolean oldOrderOption = orderOption;
		orderOption = newOrderOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__ORDER_OPTION, oldOrderOption, orderOption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__DATA_TYPE:
				return getDataType();
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_LENGTH:
				return getSeqLength();
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_PRECISION:
				return getSeqPrecision();
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MAXIMUM_OPTION:
				return getMaximumOption();
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MINIMUM_OPTION:
				return getMinimumOption();
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_SIZE:
				return getCacheSize();
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_OPTION:
				return getCacheOption();
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__ORDER_OPTION:
				return getOrderOption();
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
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__DATA_TYPE:
				setDataType((String)newValue);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_LENGTH:
				setSeqLength((BigInteger)newValue);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_PRECISION:
				setSeqPrecision((BigInteger)newValue);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MAXIMUM_OPTION:
				setMaximumOption((Boolean)newValue);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MINIMUM_OPTION:
				setMinimumOption((Boolean)newValue);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_SIZE:
				setCacheSize((BigInteger)newValue);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_OPTION:
				setCacheOption((Boolean)newValue);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__ORDER_OPTION:
				setOrderOption((Boolean)newValue);
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
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__DATA_TYPE:
				setDataType(DATA_TYPE_EDEFAULT);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_LENGTH:
				setSeqLength(SEQ_LENGTH_EDEFAULT);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_PRECISION:
				setSeqPrecision(SEQ_PRECISION_EDEFAULT);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MAXIMUM_OPTION:
				setMaximumOption(MAXIMUM_OPTION_EDEFAULT);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MINIMUM_OPTION:
				setMinimumOption(MINIMUM_OPTION_EDEFAULT);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_SIZE:
				setCacheSize(CACHE_SIZE_EDEFAULT);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_OPTION:
				setCacheOption(CACHE_OPTION_EDEFAULT);
				return;
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__ORDER_OPTION:
				setOrderOption(ORDER_OPTION_EDEFAULT);
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
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__DATA_TYPE:
				return DATA_TYPE_EDEFAULT == null ? dataType != null : !DATA_TYPE_EDEFAULT.equals(dataType);
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_LENGTH:
				return SEQ_LENGTH_EDEFAULT == null ? seqLength != null : !SEQ_LENGTH_EDEFAULT.equals(seqLength);
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__SEQ_PRECISION:
				return SEQ_PRECISION_EDEFAULT == null ? seqPrecision != null : !SEQ_PRECISION_EDEFAULT.equals(seqPrecision);
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MAXIMUM_OPTION:
				return MAXIMUM_OPTION_EDEFAULT == null ? maximumOption != null : !MAXIMUM_OPTION_EDEFAULT.equals(maximumOption);
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__MINIMUM_OPTION:
				return MINIMUM_OPTION_EDEFAULT == null ? minimumOption != null : !MINIMUM_OPTION_EDEFAULT.equals(minimumOption);
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_SIZE:
				return CACHE_SIZE_EDEFAULT == null ? cacheSize != null : !CACHE_SIZE_EDEFAULT.equals(cacheSize);
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__CACHE_OPTION:
				return CACHE_OPTION_EDEFAULT == null ? cacheOption != null : !CACHE_OPTION_EDEFAULT.equals(cacheOption);
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER__ORDER_OPTION:
				return ORDER_OPTION_EDEFAULT == null ? orderOption != null : !ORDER_OPTION_EDEFAULT.equals(orderOption);
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
		result.append(" (dataType: ");
		result.append(dataType);
		result.append(", seqLength: ");
		result.append(seqLength);
		result.append(", seqPrecision: ");
		result.append(seqPrecision);
		result.append(", maximumOption: ");
		result.append(maximumOption);
		result.append(", minimumOption: ");
		result.append(minimumOption);
		result.append(", cacheSize: ");
		result.append(cacheSize);
		result.append(", cacheOption: ");
		result.append(cacheOption);
		result.append(", orderOption: ");
		result.append(orderOption);
		result.append(')');
		return result.toString();
	}

} //IngresIdentitySpecifierImpl
