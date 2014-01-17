/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGlobalVariableImpl.java,v 1.1 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Global Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGlobalVariableImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGlobalVariableImpl#isIsConstant <em>Is Constant</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGlobalVariableImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWGlobalVariableImpl extends TypedElementImpl implements LUWGlobalVariable {
	/**
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected String defaultValue = DEFAULT_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsConstant() <em>Is Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsConstant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CONSTANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsConstant() <em>Is Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsConstant()
	 * @generated
	 * @ordered
	 */
	protected boolean isConstant = IS_CONSTANT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected DB2Schema schema;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWGlobalVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_GLOBAL_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(String newDefaultValue) {
		String oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_GLOBAL_VARIABLE__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsConstant() {
		return isConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsConstant(boolean newIsConstant) {
		boolean oldIsConstant = isConstant;
		isConstant = newIsConstant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_GLOBAL_VARIABLE__IS_CONSTANT, oldIsConstant, isConstant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (DB2Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(DB2Schema newSchema, NotificationChain msgs) {
		DB2Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(DB2Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES, DB2Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES, DB2Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES, DB2Schema.class, msgs);
				return basicSetSchema((DB2Schema)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA:
				return basicSetSchema(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_GLOBAL_VARIABLE__DEFAULT_VALUE:
				return getDefaultValue();
			case LUWPackage.LUW_GLOBAL_VARIABLE__IS_CONSTANT:
				return isIsConstant() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
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
			case LUWPackage.LUW_GLOBAL_VARIABLE__DEFAULT_VALUE:
				setDefaultValue((String)newValue);
				return;
			case LUWPackage.LUW_GLOBAL_VARIABLE__IS_CONSTANT:
				setIsConstant(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA:
				setSchema((DB2Schema)newValue);
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
			case LUWPackage.LUW_GLOBAL_VARIABLE__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
				return;
			case LUWPackage.LUW_GLOBAL_VARIABLE__IS_CONSTANT:
				setIsConstant(IS_CONSTANT_EDEFAULT);
				return;
			case LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA:
				setSchema((DB2Schema)null);
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
			case LUWPackage.LUW_GLOBAL_VARIABLE__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
			case LUWPackage.LUW_GLOBAL_VARIABLE__IS_CONSTANT:
				return isConstant != IS_CONSTANT_EDEFAULT;
			case LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA:
				return schema != null;
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
		result.append(" (defaultValue: "); //$NON-NLS-1$
		result.append(defaultValue);
		result.append(", isConstant: "); //$NON-NLS-1$
		result.append(isConstant);
		result.append(')');
		return result.toString();
	}

} //LUWGlobalVariableImpl
