/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWModuleImpl.java,v 1.4 2009/03/06 22:38:09 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;
import org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleImpl#getDialect <em>Dialect</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleImpl#getOwningSchema <em>Owning Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleImpl#getModuleObjects <em>Module Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWModuleImpl extends SQLObjectImpl implements LUWModule {
	/**
	 * The default value of the '{@link #getDialect() <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDialect()
	 * @generated
	 * @ordered
	 */
	protected static final SourceDialect DIALECT_EDEFAULT = SourceDialect.UNKNOWN_LITERAL;
	/**
	 * The cached value of the '{@link #getDialect() <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDialect()
	 * @generated
	 * @ordered
	 */
	protected SourceDialect dialect = DIALECT_EDEFAULT;
	/**
	 * The cached value of the '{@link #getOwningSchema() <em>Owning Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwningSchema()
	 * @generated
	 * @ordered
	 */
	protected DB2Schema owningSchema;
	/**
	 * The cached value of the '{@link #getModuleObjects() <em>Module Objects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleObjects()
	 * @generated
	 * @ordered
	 */
	protected EList moduleObjects;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_MODULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceDialect getDialect() {
		return dialect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDialect(SourceDialect newDialect) {
		SourceDialect oldDialect = dialect;
		dialect = newDialect == null ? DIALECT_EDEFAULT : newDialect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MODULE__DIALECT, oldDialect, dialect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema getOwningSchema() {
		if (owningSchema != null && owningSchema.eIsProxy()) {
			InternalEObject oldOwningSchema = (InternalEObject)owningSchema;
			owningSchema = (DB2Schema)eResolveProxy(oldOwningSchema);
			if (owningSchema != oldOwningSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_MODULE__OWNING_SCHEMA, oldOwningSchema, owningSchema));
			}
		}
		return owningSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema basicGetOwningSchema() {
		return owningSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningSchema(DB2Schema newOwningSchema, NotificationChain msgs) {
		DB2Schema oldOwningSchema = owningSchema;
		owningSchema = newOwningSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MODULE__OWNING_SCHEMA, oldOwningSchema, newOwningSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningSchema(DB2Schema newOwningSchema) {
		if (newOwningSchema != owningSchema) {
			NotificationChain msgs = null;
			if (owningSchema != null)
				msgs = ((InternalEObject)owningSchema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__MODULES, DB2Schema.class, msgs);
			if (newOwningSchema != null)
				msgs = ((InternalEObject)newOwningSchema).eInverseAdd(this, DB2ModelPackage.DB2_SCHEMA__MODULES, DB2Schema.class, msgs);
			msgs = basicSetOwningSchema(newOwningSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MODULE__OWNING_SCHEMA, newOwningSchema, newOwningSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getModuleObjects() {
		if (moduleObjects == null) {
			moduleObjects = new EObjectContainmentWithInverseEList(LUWModuleObject.class, this, LUWPackage.LUW_MODULE__MODULE_OBJECTS, LUWPackage.LUW_MODULE_OBJECT__MODULE);
		}
		return moduleObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_MODULE__OWNING_SCHEMA:
				if (owningSchema != null)
					msgs = ((InternalEObject)owningSchema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__MODULES, DB2Schema.class, msgs);
				return basicSetOwningSchema((DB2Schema)otherEnd, msgs);
			case LUWPackage.LUW_MODULE__MODULE_OBJECTS:
				return ((InternalEList)getModuleObjects()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_MODULE__OWNING_SCHEMA:
				return basicSetOwningSchema(null, msgs);
			case LUWPackage.LUW_MODULE__MODULE_OBJECTS:
				return ((InternalEList)getModuleObjects()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_MODULE__DIALECT:
				return getDialect();
			case LUWPackage.LUW_MODULE__OWNING_SCHEMA:
				if (resolve) return getOwningSchema();
				return basicGetOwningSchema();
			case LUWPackage.LUW_MODULE__MODULE_OBJECTS:
				return getModuleObjects();
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
			case LUWPackage.LUW_MODULE__DIALECT:
				setDialect((SourceDialect)newValue);
				return;
			case LUWPackage.LUW_MODULE__OWNING_SCHEMA:
				setOwningSchema((DB2Schema)newValue);
				return;
			case LUWPackage.LUW_MODULE__MODULE_OBJECTS:
				getModuleObjects().clear();
				getModuleObjects().addAll((Collection)newValue);
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
			case LUWPackage.LUW_MODULE__DIALECT:
				setDialect(DIALECT_EDEFAULT);
				return;
			case LUWPackage.LUW_MODULE__OWNING_SCHEMA:
				setOwningSchema((DB2Schema)null);
				return;
			case LUWPackage.LUW_MODULE__MODULE_OBJECTS:
				getModuleObjects().clear();
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
			case LUWPackage.LUW_MODULE__DIALECT:
				return dialect != DIALECT_EDEFAULT;
			case LUWPackage.LUW_MODULE__OWNING_SCHEMA:
				return owningSchema != null;
			case LUWPackage.LUW_MODULE__MODULE_OBJECTS:
				return moduleObjects != null && !moduleObjects.isEmpty();
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
		result.append(" (dialect: "); //$NON-NLS-1$
		result.append(dialect);
		result.append(')');
		return result.toString();
	}

} //LUWModuleImpl
