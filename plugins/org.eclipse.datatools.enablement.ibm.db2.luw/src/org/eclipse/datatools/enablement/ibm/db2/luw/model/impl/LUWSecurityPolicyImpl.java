/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Security Policy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityPolicyImpl#getNotAuthorizedWrite <em>Not Authorized Write</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityPolicyImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityPolicyImpl#getLabels <em>Labels</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityPolicyImpl#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWSecurityPolicyImpl extends SQLObjectImpl implements LUWSecurityPolicy {
	/**
	 * The default value of the '{@link #getNotAuthorizedWrite() <em>Not Authorized Write</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotAuthorizedWrite()
	 * @generated
	 * @ordered
	 */
	protected static final LUWSecurityLabelNotAuthorizedWriteAction NOT_AUTHORIZED_WRITE_EDEFAULT = LUWSecurityLabelNotAuthorizedWriteAction.OVERRIDE_LITERAL;

	/**
	 * The cached value of the '{@link #getNotAuthorizedWrite() <em>Not Authorized Write</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotAuthorizedWrite()
	 * @generated
	 * @ordered
	 */
	protected LUWSecurityLabelNotAuthorizedWriteAction notAuthorizedWrite = NOT_AUTHORIZED_WRITE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList components;

	/**
	 * The cached value of the '{@link #getLabels() <em>Labels</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabels()
	 * @generated
	 * @ordered
	 */
	protected EList labels;

	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected LUWTable table;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWSecurityPolicyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_SECURITY_POLICY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityLabelNotAuthorizedWriteAction getNotAuthorizedWrite() {
		return notAuthorizedWrite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotAuthorizedWrite(LUWSecurityLabelNotAuthorizedWriteAction newNotAuthorizedWrite) {
		LUWSecurityLabelNotAuthorizedWriteAction oldNotAuthorizedWrite = notAuthorizedWrite;
		notAuthorizedWrite = newNotAuthorizedWrite == null ? NOT_AUTHORIZED_WRITE_EDEFAULT : newNotAuthorizedWrite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_POLICY__NOT_AUTHORIZED_WRITE, oldNotAuthorizedWrite, notAuthorizedWrite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComponents() {
		if (components == null) {
			components = new EObjectWithInverseResolvingEList.ManyInverse(LUWSecurityLabelComponent.class, this, LUWPackage.LUW_SECURITY_POLICY__COMPONENTS, LUWPackage.LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLabels() {
		if (labels == null) {
			labels = new EObjectWithInverseResolvingEList(LUWSecurityLabel.class, this, LUWPackage.LUW_SECURITY_POLICY__LABELS, LUWPackage.LUW_SECURITY_LABEL__POLICY);
		}
		return labels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTable getTable() {
		if (table != null && table.eIsProxy()) {
			InternalEObject oldTable = (InternalEObject)table;
			table = (LUWTable)eResolveProxy(oldTable);
			if (table != oldTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_SECURITY_POLICY__TABLE, oldTable, table));
			}
		}
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTable basicGetTable() {
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(LUWTable newTable, NotificationChain msgs) {
		LUWTable oldTable = table;
		table = newTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_POLICY__TABLE, oldTable, newTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(LUWTable newTable) {
		if (newTable != table) {
			NotificationChain msgs = null;
			if (table != null)
				msgs = ((InternalEObject)table).eInverseRemove(this, LUWPackage.LUW_TABLE__SECURITY_POLICY, LUWTable.class, msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, LUWPackage.LUW_TABLE__SECURITY_POLICY, LUWTable.class, msgs);
			msgs = basicSetTable(newTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_POLICY__TABLE, newTable, newTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_SECURITY_POLICY__COMPONENTS:
				return ((InternalEList)getComponents()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_SECURITY_POLICY__LABELS:
				return ((InternalEList)getLabels()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_SECURITY_POLICY__TABLE:
				if (table != null)
					msgs = ((InternalEObject)table).eInverseRemove(this, LUWPackage.LUW_TABLE__SECURITY_POLICY, LUWTable.class, msgs);
				return basicSetTable((LUWTable)otherEnd, msgs);
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
			case LUWPackage.LUW_SECURITY_POLICY__COMPONENTS:
				return ((InternalEList)getComponents()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_SECURITY_POLICY__LABELS:
				return ((InternalEList)getLabels()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_SECURITY_POLICY__TABLE:
				return basicSetTable(null, msgs);
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
			case LUWPackage.LUW_SECURITY_POLICY__NOT_AUTHORIZED_WRITE:
				return getNotAuthorizedWrite();
			case LUWPackage.LUW_SECURITY_POLICY__COMPONENTS:
				return getComponents();
			case LUWPackage.LUW_SECURITY_POLICY__LABELS:
				return getLabels();
			case LUWPackage.LUW_SECURITY_POLICY__TABLE:
				if (resolve) return getTable();
				return basicGetTable();
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
			case LUWPackage.LUW_SECURITY_POLICY__NOT_AUTHORIZED_WRITE:
				setNotAuthorizedWrite((LUWSecurityLabelNotAuthorizedWriteAction)newValue);
				return;
			case LUWPackage.LUW_SECURITY_POLICY__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_SECURITY_POLICY__LABELS:
				getLabels().clear();
				getLabels().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_SECURITY_POLICY__TABLE:
				setTable((LUWTable)newValue);
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
			case LUWPackage.LUW_SECURITY_POLICY__NOT_AUTHORIZED_WRITE:
				setNotAuthorizedWrite(NOT_AUTHORIZED_WRITE_EDEFAULT);
				return;
			case LUWPackage.LUW_SECURITY_POLICY__COMPONENTS:
				getComponents().clear();
				return;
			case LUWPackage.LUW_SECURITY_POLICY__LABELS:
				getLabels().clear();
				return;
			case LUWPackage.LUW_SECURITY_POLICY__TABLE:
				setTable((LUWTable)null);
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
			case LUWPackage.LUW_SECURITY_POLICY__NOT_AUTHORIZED_WRITE:
				return notAuthorizedWrite != NOT_AUTHORIZED_WRITE_EDEFAULT;
			case LUWPackage.LUW_SECURITY_POLICY__COMPONENTS:
				return components != null && !components.isEmpty();
			case LUWPackage.LUW_SECURITY_POLICY__LABELS:
				return labels != null && !labels.isEmpty();
			case LUWPackage.LUW_SECURITY_POLICY__TABLE:
				return table != null;
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
		result.append(" (notAuthorizedWrite: "); //$NON-NLS-1$
		result.append(notAuthorizedWrite);
		result.append(')');
		return result.toString();
	}

} //LUWSecurityPolicyImpl
