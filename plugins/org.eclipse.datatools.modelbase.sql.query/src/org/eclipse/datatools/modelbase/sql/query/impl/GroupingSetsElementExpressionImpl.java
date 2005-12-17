/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementExpressionImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Grouping Sets Element Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementExpressionImpl#getGroupingSetsElementSublist <em>Grouping Sets Element Sublist</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementExpressionImpl#getGrouping <em>Grouping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingSetsElementExpressionImpl extends GroupingSetsElementImpl implements GroupingSetsElementExpression {
	/**
	 * The cached value of the '{@link #getGrouping() <em>Grouping</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGrouping()
	 * @generated
	 * @ordered
	 */
    protected Grouping grouping = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GroupingSetsElementExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getGroupingSetsElementExpression();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GroupingSetsElementSublist getGroupingSetsElementSublist() {
		if (eContainerFeatureID != SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST) return null;
		return (GroupingSetsElementSublist)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGroupingSetsElementSublist(GroupingSetsElementSublist newGroupingSetsElementSublist) {
		if (newGroupingSetsElementSublist != eContainer || (eContainerFeatureID != SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST && newGroupingSetsElementSublist != null)) {
			if (EcoreUtil.isAncestor(this, newGroupingSetsElementSublist))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroupingSetsElementSublist != null)
				msgs = ((InternalEObject)newGroupingSetsElementSublist).eInverseAdd(this, SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST, GroupingSetsElementSublist.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newGroupingSetsElementSublist, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, newGroupingSetsElementSublist, newGroupingSetsElementSublist));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Grouping getGrouping() {
		return grouping;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetGrouping(Grouping newGrouping, NotificationChain msgs) {
		Grouping oldGrouping = grouping;
		grouping = newGrouping;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, oldGrouping, newGrouping);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrouping(Grouping newGrouping) {
		if (newGrouping != grouping) {
			NotificationChain msgs = null;
			if (grouping != null)
				msgs = ((InternalEObject)grouping).eInverseRemove(this, SQLQueryPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, Grouping.class, msgs);
			if (newGrouping != null)
				msgs = ((InternalEObject)newGrouping).eInverseAdd(this, SQLQueryPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, Grouping.class, msgs);
			msgs = basicSetGrouping(newGrouping, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, newGrouping, newGrouping));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
					if (grouping != null)
						msgs = ((InternalEObject)grouping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, null, msgs);
					return basicSetGrouping((Grouping)otherEnd, msgs);
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
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
					return eBasicSetContainer(null, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
					return eBasicSetContainer(null, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
					return basicSetGrouping(null, msgs);
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
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
					return eContainer.eInverseRemove(this, SQLQueryPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST, GroupingSets.class, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST, GroupingSetsElementSublist.class, msgs);
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
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__NAME:
				return getName();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__LABEL:
				return getLabel();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
				return getGroupingSets();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
				return getGroupingSetsElementSublist();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
				return getGrouping();
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
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
				setGroupingSets((GroupingSets)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
				setGroupingSetsElementSublist((GroupingSetsElementSublist)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
				setGrouping((Grouping)newValue);
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
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
				setGroupingSets((GroupingSets)null);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
				setGroupingSetsElementSublist((GroupingSetsElementSublist)null);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
				setGrouping((Grouping)null);
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
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
				return getGroupingSets() != null;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
				return getGroupingSetsElementSublist() != null;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
				return grouping != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLGroupingSetsElementExpressionImpl
