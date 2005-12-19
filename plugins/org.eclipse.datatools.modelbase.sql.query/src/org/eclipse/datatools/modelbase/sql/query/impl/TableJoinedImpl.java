/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableJoinedImpl.java,v 1.2 2005/12/17 01:46:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Joined</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl#getJoinOperator <em>Join Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl#getJoinCondition <em>Join Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl#getTableRefRight <em>Table Ref Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl#getTableRefLeft <em>Table Ref Left</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableJoinedImpl extends TableReferenceImpl implements TableJoined {
	/**
	 * The default value of the '{@link #getJoinOperator() <em>Join Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getJoinOperator()
	 * @generated
	 * @ordered
	 */
    protected static final TableJoinedOperator JOIN_OPERATOR_EDEFAULT = TableJoinedOperator.DEFAULT_INNER_LITERAL;

	/**
	 * The cached value of the '{@link #getJoinOperator() <em>Join Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getJoinOperator()
	 * @generated
	 * @ordered
	 */
    protected TableJoinedOperator joinOperator = JOIN_OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getJoinCondition() <em>Join Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getJoinCondition()
	 * @generated
	 * @ordered
	 */
    protected QuerySearchCondition joinCondition = null;

	/**
	 * The cached value of the '{@link #getTableRefRight() <em>Table Ref Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTableRefRight()
	 * @generated
	 * @ordered
	 */
    protected TableReference tableRefRight = null;

	/**
	 * The cached value of the '{@link #getTableRefLeft() <em>Table Ref Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTableRefLeft()
	 * @generated
	 * @ordered
	 */
    protected TableReference tableRefLeft = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected TableJoinedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getTableJoined();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableJoinedOperator getJoinOperator() {
		return joinOperator;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setJoinOperator(TableJoinedOperator newJoinOperator) {
		TableJoinedOperator oldJoinOperator = joinOperator;
		joinOperator = newJoinOperator == null ? JOIN_OPERATOR_EDEFAULT : newJoinOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_JOINED__JOIN_OPERATOR, oldJoinOperator, joinOperator));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySearchCondition getJoinCondition() {
		return joinCondition;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetJoinCondition(QuerySearchCondition newJoinCondition, NotificationChain msgs) {
		QuerySearchCondition oldJoinCondition = joinCondition;
		joinCondition = newJoinCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION, oldJoinCondition, newJoinCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setJoinCondition(QuerySearchCondition newJoinCondition) {
		if (newJoinCondition != joinCondition) {
			NotificationChain msgs = null;
			if (joinCondition != null)
				msgs = ((InternalEObject)joinCondition).eInverseRemove(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, QuerySearchCondition.class, msgs);
			if (newJoinCondition != null)
				msgs = ((InternalEObject)newJoinCondition).eInverseAdd(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, QuerySearchCondition.class, msgs);
			msgs = basicSetJoinCondition(newJoinCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION, newJoinCondition, newJoinCondition));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableReference getTableRefRight() {
		return tableRefRight;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetTableRefRight(TableReference newTableRefRight, NotificationChain msgs) {
		TableReference oldTableRefRight = tableRefRight;
		tableRefRight = newTableRefRight;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, oldTableRefRight, newTableRefRight);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableRefRight(TableReference newTableRefRight) {
		if (newTableRefRight != tableRefRight) {
			NotificationChain msgs = null;
			if (tableRefRight != null)
				msgs = ((InternalEObject)tableRefRight).eInverseRemove(this, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, TableReference.class, msgs);
			if (newTableRefRight != null)
				msgs = ((InternalEObject)newTableRefRight).eInverseAdd(this, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, TableReference.class, msgs);
			msgs = basicSetTableRefRight(newTableRefRight, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, newTableRefRight, newTableRefRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableReference getTableRefLeft() {
		return tableRefLeft;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetTableRefLeft(TableReference newTableRefLeft, NotificationChain msgs) {
		TableReference oldTableRefLeft = tableRefLeft;
		tableRefLeft = newTableRefLeft;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, oldTableRefLeft, newTableRefLeft);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableRefLeft(TableReference newTableRefLeft) {
		if (newTableRefLeft != tableRefLeft) {
			NotificationChain msgs = null;
			if (tableRefLeft != null)
				msgs = ((InternalEObject)tableRefLeft).eInverseRemove(this, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, TableReference.class, msgs);
			if (newTableRefLeft != null)
				msgs = ((InternalEObject)newTableRefLeft).eInverseAdd(this, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, TableReference.class, msgs);
			msgs = basicSetTableRefLeft(newTableRefLeft, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, newTableRefLeft, newTableRefLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.TABLE_JOINED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.TABLE_JOINED__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_JOINED__QUERY_SELECT, msgs);
				case SQLQueryPackage.TABLE_JOINED__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_JOINED__NEST, msgs);
				case SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION:
					if (joinCondition != null)
						msgs = ((InternalEObject)joinCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION, null, msgs);
					return basicSetJoinCondition((QuerySearchCondition)otherEnd, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT:
					if (tableRefRight != null)
						msgs = ((InternalEObject)tableRefRight).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, null, msgs);
					return basicSetTableRefRight((TableReference)otherEnd, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT:
					if (tableRefLeft != null)
						msgs = ((InternalEObject)tableRefLeft).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, null, msgs);
					return basicSetTableRefLeft((TableReference)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.TABLE_JOINED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_JOINED__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.TABLE_JOINED__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_JOINED__QUERY_SELECT, msgs);
				case SQLQueryPackage.TABLE_JOINED__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_JOINED__NEST, msgs);
				case SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION:
					return basicSetJoinCondition(null, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT:
					return basicSetTableRefRight(null, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT:
					return basicSetTableRefLeft(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
				case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
				case SQLQueryPackage.TABLE_JOINED__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.TABLE_JOINED__NEST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.TABLE_JOINED__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.TABLE_JOINED__NAME:
				return getName();
			case SQLQueryPackage.TABLE_JOINED__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.TABLE_JOINED__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.TABLE_JOINED__LABEL:
				return getLabel();
			case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryPackage.TABLE_JOINED__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.TABLE_JOINED__NEST:
				return getNest();
			case SQLQueryPackage.TABLE_JOINED__JOIN_OPERATOR:
				return getJoinOperator();
			case SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION:
				return getJoinCondition();
			case SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT:
				return getTableRefRight();
			case SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT:
				return getTableRefLeft();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.TABLE_JOINED__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__JOIN_OPERATOR:
				setJoinOperator((TableJoinedOperator)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION:
				setJoinCondition((QuerySearchCondition)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT:
				setTableRefRight((TableReference)newValue);
				return;
			case SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT:
				setTableRefLeft((TableReference)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.TABLE_JOINED__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.TABLE_JOINED__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_JOINED__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.TABLE_JOINED__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_JOINED__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryPackage.TABLE_JOINED__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.TABLE_JOINED__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryPackage.TABLE_JOINED__JOIN_OPERATOR:
				setJoinOperator(JOIN_OPERATOR_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION:
				setJoinCondition((QuerySearchCondition)null);
				return;
			case SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT:
				setTableRefRight((TableReference)null);
				return;
			case SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT:
				setTableRefLeft((TableReference)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.TABLE_JOINED__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.TABLE_JOINED__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.TABLE_JOINED__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.TABLE_JOINED__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.TABLE_JOINED__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryPackage.TABLE_JOINED__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryPackage.TABLE_JOINED__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.TABLE_JOINED__NEST:
				return getNest() != null;
			case SQLQueryPackage.TABLE_JOINED__JOIN_OPERATOR:
				return joinOperator != JOIN_OPERATOR_EDEFAULT;
			case SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION:
				return joinCondition != null;
			case SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT:
				return tableRefRight != null;
			case SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT:
				return tableRefLeft != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (joinOperator: ");
		result.append(joinOperator);
		result.append(')');
		return result.toString();
	}

} //SQLTableJoinedImpl
