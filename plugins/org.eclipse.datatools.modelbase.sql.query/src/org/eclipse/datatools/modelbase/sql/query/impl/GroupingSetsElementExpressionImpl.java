/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementExpressionImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
		return SQLQueryModelPackage.eINSTANCE.getGroupingSetsElementExpression();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GroupingSetsElementSublist getGroupingSetsElementSublist() {
		if (eContainerFeatureID != SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST) return null;
		return (GroupingSetsElementSublist)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGroupingSetsElementSublist(GroupingSetsElementSublist newGroupingSetsElementSublist) {
		if (newGroupingSetsElementSublist != eContainer || (eContainerFeatureID != SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST && newGroupingSetsElementSublist != null)) {
			if (EcoreUtil.isAncestor(this, newGroupingSetsElementSublist))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroupingSetsElementSublist != null)
				msgs = ((InternalEObject)newGroupingSetsElementSublist).eInverseAdd(this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST, GroupingSetsElementSublist.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newGroupingSetsElementSublist, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, newGroupingSetsElementSublist, newGroupingSetsElementSublist));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, oldGrouping, newGrouping);
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
				msgs = ((InternalEObject)grouping).eInverseRemove(this, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, Grouping.class, msgs);
			if (newGrouping != null)
				msgs = ((InternalEObject)newGrouping).eInverseAdd(this, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, Grouping.class, msgs);
			msgs = basicSetGrouping(newGrouping, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, newGrouping, newGrouping));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
					if (grouping != null)
						msgs = ((InternalEObject)grouping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, null, msgs);
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
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
					return eBasicSetContainer(null, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
					return eBasicSetContainer(null, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
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
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST, GroupingSets.class, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST, GroupingSetsElementSublist.class, msgs);
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
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__NAME:
				return getName();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__LABEL:
				return getLabel();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
				return getGroupingSets();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
				return getGroupingSetsElementSublist();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
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
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
				setGroupingSets((GroupingSets)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
				setGroupingSetsElementSublist((GroupingSetsElementSublist)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
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
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
				setGroupingSets((GroupingSets)null);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
				setGroupingSetsElementSublist((GroupingSetsElementSublist)null);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
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
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS:
				return getGroupingSets() != null;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
				return getGroupingSetsElementSublist() != null;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
				return grouping != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLGroupingSetsElementExpressionImpl
