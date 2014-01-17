/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWPartitionExpressionImpl.java,v 1.7 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Partition Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionExpressionImpl#isNullsLast <em>Nulls Last</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionExpressionImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionExpressionImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionExpressionImpl#getPartitionElements <em>Partition Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWPartitionExpressionImpl extends SQLObjectImpl implements LUWPartitionExpression {
	/**
	 * The default value of the '{@link #isNullsLast() <em>Nulls Last</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullsLast()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NULLS_LAST_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isNullsLast() <em>Nulls Last</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullsLast()
	 * @generated
	 * @ordered
	 */
	protected boolean nullsLast = NULLS_LAST_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColumn() <em>Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected Column column;

	/**
	 * The cached value of the '{@link #getPartitionElements() <em>Partition Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionElements()
	 * @generated
	 * @ordered
	 */
	protected EList partitionElements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWPartitionExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_PARTITION_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNullsLast() {
		return nullsLast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullsLast(boolean newNullsLast) {
		boolean oldNullsLast = nullsLast;
		nullsLast = newNullsLast;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_EXPRESSION__NULLS_LAST, oldNullsLast, nullsLast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDataPartitionKey getKey() {
		if (eContainerFeatureID() != LUWPackage.LUW_PARTITION_EXPRESSION__KEY) return null;
		return (LUWDataPartitionKey)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKey(LUWDataPartitionKey newKey, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newKey, LUWPackage.LUW_PARTITION_EXPRESSION__KEY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKey(LUWDataPartitionKey newKey) {
		if (newKey != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_PARTITION_EXPRESSION__KEY && newKey != null)) {
			if (EcoreUtil.isAncestor(this, newKey))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newKey != null)
				msgs = ((InternalEObject)newKey).eInverseAdd(this, LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS, LUWDataPartitionKey.class, msgs);
			msgs = basicSetKey(newKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_EXPRESSION__KEY, newKey, newKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column getColumn() {
		if (column != null && column.eIsProxy()) {
			InternalEObject oldColumn = (InternalEObject)column;
			column = (Column)eResolveProxy(oldColumn);
			if (column != oldColumn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_PARTITION_EXPRESSION__COLUMN, oldColumn, column));
			}
		}
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column basicGetColumn() {
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumn(Column newColumn) {
		Column oldColumn = column;
		column = newColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_EXPRESSION__COLUMN, oldColumn, column));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitionElements() {
		if (partitionElements == null) {
			partitionElements = new EObjectContainmentWithInverseEList(LUWPartitionElement.class, this, LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS, LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION);
		}
		return partitionElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_PARTITION_EXPRESSION__KEY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetKey((LUWDataPartitionKey)otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS:
				return ((InternalEList)getPartitionElements()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_PARTITION_EXPRESSION__KEY:
				return basicSetKey(null, msgs);
			case LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS:
				return ((InternalEList)getPartitionElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case LUWPackage.LUW_PARTITION_EXPRESSION__KEY:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS, LUWDataPartitionKey.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_PARTITION_EXPRESSION__NULLS_LAST:
				return isNullsLast() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_PARTITION_EXPRESSION__KEY:
				return getKey();
			case LUWPackage.LUW_PARTITION_EXPRESSION__COLUMN:
				if (resolve) return getColumn();
				return basicGetColumn();
			case LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS:
				return getPartitionElements();
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
			case LUWPackage.LUW_PARTITION_EXPRESSION__NULLS_LAST:
				setNullsLast(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_PARTITION_EXPRESSION__KEY:
				setKey((LUWDataPartitionKey)newValue);
				return;
			case LUWPackage.LUW_PARTITION_EXPRESSION__COLUMN:
				setColumn((Column)newValue);
				return;
			case LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS:
				getPartitionElements().clear();
				getPartitionElements().addAll((Collection)newValue);
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
			case LUWPackage.LUW_PARTITION_EXPRESSION__NULLS_LAST:
				setNullsLast(NULLS_LAST_EDEFAULT);
				return;
			case LUWPackage.LUW_PARTITION_EXPRESSION__KEY:
				setKey((LUWDataPartitionKey)null);
				return;
			case LUWPackage.LUW_PARTITION_EXPRESSION__COLUMN:
				setColumn((Column)null);
				return;
			case LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS:
				getPartitionElements().clear();
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
			case LUWPackage.LUW_PARTITION_EXPRESSION__NULLS_LAST:
				return nullsLast != NULLS_LAST_EDEFAULT;
			case LUWPackage.LUW_PARTITION_EXPRESSION__KEY:
				return getKey() != null;
			case LUWPackage.LUW_PARTITION_EXPRESSION__COLUMN:
				return column != null;
			case LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS:
				return partitionElements != null && !partitionElements.isEmpty();
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
		result.append(" (nullsLast: "); //$NON-NLS-1$
		result.append(nullsLast);
		result.append(')');
		return result.toString();
	}

} //LUWPartitionExpressionImpl
