/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWPartitionElementImpl.java,v 1.6 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Partition Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl#getStarting <em>Starting</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl#getEnding <em>Ending</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl#getLUWPartitionExpression <em>LUW Partition Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl#getPartition <em>Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl#getEveryClause <em>Every Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWPartitionElementImpl extends SQLObjectImpl implements LUWPartitionElement {
	/**
	 * The default value of the '{@link #getStarting() <em>Starting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStarting()
	 * @generated
	 * @ordered
	 */
	protected static final String STARTING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStarting() <em>Starting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStarting()
	 * @generated
	 * @ordered
	 */
	protected String starting = STARTING_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnding() <em>Ending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnding()
	 * @generated
	 * @ordered
	 */
	protected static final String ENDING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnding() <em>Ending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnding()
	 * @generated
	 * @ordered
	 */
	protected String ending = ENDING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPartition() <em>Partition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartition()
	 * @generated
	 * @ordered
	 */
	protected LUWDataPartition partition;

	/**
	 * The cached value of the '{@link #getEveryClause() <em>Every Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEveryClause()
	 * @generated
	 * @ordered
	 */
	protected LUWPartitionEveryClauseElement everyClause;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWPartitionElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_PARTITION_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStarting() {
		return starting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStarting(String newStarting) {
		String oldStarting = starting;
		starting = newStarting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_ELEMENT__STARTING, oldStarting, starting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEnding() {
		return ending;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnding(String newEnding) {
		String oldEnding = ending;
		ending = newEnding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_ELEMENT__ENDING, oldEnding, ending));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionExpression getLUWPartitionExpression() {
		if (eContainerFeatureID() != LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION) return null;
		return (LUWPartitionExpression)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLUWPartitionExpression(LUWPartitionExpression newLUWPartitionExpression, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLUWPartitionExpression, LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLUWPartitionExpression(LUWPartitionExpression newLUWPartitionExpression) {
		if (newLUWPartitionExpression != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION && newLUWPartitionExpression != null)) {
			if (EcoreUtil.isAncestor(this, newLUWPartitionExpression))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLUWPartitionExpression != null)
				msgs = ((InternalEObject)newLUWPartitionExpression).eInverseAdd(this, LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS, LUWPartitionExpression.class, msgs);
			msgs = basicSetLUWPartitionExpression(newLUWPartitionExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION, newLUWPartitionExpression, newLUWPartitionExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDataPartition getPartition() {
		if (partition != null && partition.eIsProxy()) {
			InternalEObject oldPartition = (InternalEObject)partition;
			partition = (LUWDataPartition)eResolveProxy(oldPartition);
			if (partition != oldPartition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_PARTITION_ELEMENT__PARTITION, oldPartition, partition));
			}
		}
		return partition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDataPartition basicGetPartition() {
		return partition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPartition(LUWDataPartition newPartition, NotificationChain msgs) {
		LUWDataPartition oldPartition = partition;
		partition = newPartition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_ELEMENT__PARTITION, oldPartition, newPartition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartition(LUWDataPartition newPartition) {
		if (newPartition != partition) {
			NotificationChain msgs = null;
			if (partition != null)
				msgs = ((InternalEObject)partition).eInverseRemove(this, LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS, LUWDataPartition.class, msgs);
			if (newPartition != null)
				msgs = ((InternalEObject)newPartition).eInverseAdd(this, LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS, LUWDataPartition.class, msgs);
			msgs = basicSetPartition(newPartition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_ELEMENT__PARTITION, newPartition, newPartition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionEveryClauseElement getEveryClause() {
		return everyClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEveryClause(LUWPartitionEveryClauseElement newEveryClause, NotificationChain msgs) {
		LUWPartitionEveryClauseElement oldEveryClause = everyClause;
		everyClause = newEveryClause;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE, oldEveryClause, newEveryClause);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEveryClause(LUWPartitionEveryClauseElement newEveryClause) {
		if (newEveryClause != everyClause) {
			NotificationChain msgs = null;
			if (everyClause != null)
				msgs = ((InternalEObject)everyClause).eInverseRemove(this, LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT, LUWPartitionEveryClauseElement.class, msgs);
			if (newEveryClause != null)
				msgs = ((InternalEObject)newEveryClause).eInverseAdd(this, LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT, LUWPartitionEveryClauseElement.class, msgs);
			msgs = basicSetEveryClause(newEveryClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE, newEveryClause, newEveryClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public Boolean hasEveryClause() {
		if ( this.everyClause == null )
		{
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLUWPartitionExpression((LUWPartitionExpression)otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_ELEMENT__PARTITION:
				if (partition != null)
					msgs = ((InternalEObject)partition).eInverseRemove(this, LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS, LUWDataPartition.class, msgs);
				return basicSetPartition((LUWDataPartition)otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE:
				if (everyClause != null)
					msgs = ((InternalEObject)everyClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE, null, msgs);
				return basicSetEveryClause((LUWPartitionEveryClauseElement)otherEnd, msgs);
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
			case LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION:
				return basicSetLUWPartitionExpression(null, msgs);
			case LUWPackage.LUW_PARTITION_ELEMENT__PARTITION:
				return basicSetPartition(null, msgs);
			case LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE:
				return basicSetEveryClause(null, msgs);
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
			case LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS, LUWPartitionExpression.class, msgs);
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
			case LUWPackage.LUW_PARTITION_ELEMENT__STARTING:
				return getStarting();
			case LUWPackage.LUW_PARTITION_ELEMENT__ENDING:
				return getEnding();
			case LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION:
				return getLUWPartitionExpression();
			case LUWPackage.LUW_PARTITION_ELEMENT__PARTITION:
				if (resolve) return getPartition();
				return basicGetPartition();
			case LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE:
				return getEveryClause();
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
			case LUWPackage.LUW_PARTITION_ELEMENT__STARTING:
				setStarting((String)newValue);
				return;
			case LUWPackage.LUW_PARTITION_ELEMENT__ENDING:
				setEnding((String)newValue);
				return;
			case LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION:
				setLUWPartitionExpression((LUWPartitionExpression)newValue);
				return;
			case LUWPackage.LUW_PARTITION_ELEMENT__PARTITION:
				setPartition((LUWDataPartition)newValue);
				return;
			case LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE:
				setEveryClause((LUWPartitionEveryClauseElement)newValue);
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
			case LUWPackage.LUW_PARTITION_ELEMENT__STARTING:
				setStarting(STARTING_EDEFAULT);
				return;
			case LUWPackage.LUW_PARTITION_ELEMENT__ENDING:
				setEnding(ENDING_EDEFAULT);
				return;
			case LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION:
				setLUWPartitionExpression((LUWPartitionExpression)null);
				return;
			case LUWPackage.LUW_PARTITION_ELEMENT__PARTITION:
				setPartition((LUWDataPartition)null);
				return;
			case LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE:
				setEveryClause((LUWPartitionEveryClauseElement)null);
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
			case LUWPackage.LUW_PARTITION_ELEMENT__STARTING:
				return STARTING_EDEFAULT == null ? starting != null : !STARTING_EDEFAULT.equals(starting);
			case LUWPackage.LUW_PARTITION_ELEMENT__ENDING:
				return ENDING_EDEFAULT == null ? ending != null : !ENDING_EDEFAULT.equals(ending);
			case LUWPackage.LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION:
				return getLUWPartitionExpression() != null;
			case LUWPackage.LUW_PARTITION_ELEMENT__PARTITION:
				return partition != null;
			case LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE:
				return everyClause != null;
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
		result.append(" (starting: "); //$NON-NLS-1$
		result.append(starting);
		result.append(", ending: "); //$NON-NLS-1$
		result.append(ending);
		result.append(')');
		return result.toString();
	}

} //LUWPartitionElementImpl
