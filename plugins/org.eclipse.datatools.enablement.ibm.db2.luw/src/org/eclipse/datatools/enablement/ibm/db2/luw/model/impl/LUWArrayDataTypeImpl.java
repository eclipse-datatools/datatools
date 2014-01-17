/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWArrayDataTypeImpl.java,v 1.1 2009/02/16 19:01:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.ArrayDataTypeImpl;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWArrayDataTypeImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWArrayDataTypeImpl#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWArrayDataTypeImpl#getArrayIndexElementType <em>Array Index Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWArrayDataTypeImpl extends ArrayDataTypeImpl implements LUWArrayDataType {
	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected Schema schema;

	/**
	 * The cached value of the '{@link #getOrdering() <em>Ordering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrdering()
	 * @generated
	 * @ordered
	 */
	protected UserDefinedTypeOrdering ordering;

	/**
	 * The cached value of the '{@link #getArrayIndexElementType() <em>Array Index Element Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayIndexElementType()
	 * @generated
	 * @ordered
	 */
	protected ArrayIndexElementType arrayIndexElementType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWArrayDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_ARRAY_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(Schema newSchema, NotificationChain msgs) {
		Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserDefinedTypeOrdering getOrdering() {
		return ordering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrdering(UserDefinedTypeOrdering newOrdering, NotificationChain msgs) {
		UserDefinedTypeOrdering oldOrdering = ordering;
		ordering = newOrdering;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING, oldOrdering, newOrdering);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrdering(UserDefinedTypeOrdering newOrdering) {
		if (newOrdering != ordering) {
			NotificationChain msgs = null;
			if (ordering != null)
				msgs = ((InternalEObject)ordering).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING, null, msgs);
			if (newOrdering != null)
				msgs = ((InternalEObject)newOrdering).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING, null, msgs);
			msgs = basicSetOrdering(newOrdering, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING, newOrdering, newOrdering));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayIndexElementType getArrayIndexElementType() {
		return arrayIndexElementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArrayIndexElementType(ArrayIndexElementType newArrayIndexElementType, NotificationChain msgs) {
		ArrayIndexElementType oldArrayIndexElementType = arrayIndexElementType;
		arrayIndexElementType = newArrayIndexElementType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE, oldArrayIndexElementType, newArrayIndexElementType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayIndexElementType(ArrayIndexElementType newArrayIndexElementType) {
		if (newArrayIndexElementType != arrayIndexElementType) {
			NotificationChain msgs = null;
			if (arrayIndexElementType != null)
				msgs = ((InternalEObject)arrayIndexElementType).eInverseRemove(this, LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE, ArrayIndexElementType.class, msgs);
			if (newArrayIndexElementType != null)
				msgs = ((InternalEObject)newArrayIndexElementType).eInverseAdd(this, LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE, ArrayIndexElementType.class, msgs);
			msgs = basicSetArrayIndexElementType(newArrayIndexElementType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE, newArrayIndexElementType, newArrayIndexElementType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES, Schema.class, msgs);
				return basicSetSchema((Schema)otherEnd, msgs);
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE:
				if (arrayIndexElementType != null)
					msgs = ((InternalEObject)arrayIndexElementType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE, null, msgs);
				return basicSetArrayIndexElementType((ArrayIndexElementType)otherEnd, msgs);
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
			case LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA:
				return basicSetSchema(null, msgs);
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING:
				return basicSetOrdering(null, msgs);
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE:
				return basicSetArrayIndexElementType(null, msgs);
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
			case LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING:
				return getOrdering();
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE:
				return getArrayIndexElementType();
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
			case LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING:
				setOrdering((UserDefinedTypeOrdering)newValue);
				return;
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE:
				setArrayIndexElementType((ArrayIndexElementType)newValue);
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
			case LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA:
				setSchema((Schema)null);
				return;
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING:
				setOrdering((UserDefinedTypeOrdering)null);
				return;
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE:
				setArrayIndexElementType((ArrayIndexElementType)null);
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
			case LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA:
				return schema != null;
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING:
				return ordering != null;
			case LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE:
				return arrayIndexElementType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == UserDefinedType.class) {
			switch (derivedFeatureID) {
				case LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA: return SQLDataTypesPackage.USER_DEFINED_TYPE__SCHEMA;
				case LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING: return SQLDataTypesPackage.USER_DEFINED_TYPE__ORDERING;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == UserDefinedType.class) {
			switch (baseFeatureID) {
				case SQLDataTypesPackage.USER_DEFINED_TYPE__SCHEMA: return LUWPackage.LUW_ARRAY_DATA_TYPE__SCHEMA;
				case SQLDataTypesPackage.USER_DEFINED_TYPE__ORDERING: return LUWPackage.LUW_ARRAY_DATA_TYPE__ORDERING;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //LUWArrayDataTypeImpl
