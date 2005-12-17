/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupElementExpressionImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist;
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
 * An implementation of the model object '<em><b>SQL Super Group Element Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementExpressionImpl#getSuperGroupElementSublist <em>Super Group Element Sublist</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementExpressionImpl#getGroupingExpr <em>Grouping Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperGroupElementExpressionImpl extends SuperGroupElementImpl implements SuperGroupElementExpression {
	/**
	 * The cached value of the '{@link #getGroupingExpr() <em>Grouping Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGroupingExpr()
	 * @generated
	 * @ordered
	 */
    protected GroupingExpression groupingExpr = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SuperGroupElementExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getSuperGroupElementExpression();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SuperGroupElementSublist getSuperGroupElementSublist() {
		if (eContainerFeatureID != SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST) return null;
		return (SuperGroupElementSublist)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSuperGroupElementSublist(SuperGroupElementSublist newSuperGroupElementSublist) {
		if (newSuperGroupElementSublist != eContainer || (eContainerFeatureID != SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST && newSuperGroupElementSublist != null)) {
			if (EcoreUtil.isAncestor(this, newSuperGroupElementSublist))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSuperGroupElementSublist != null)
				msgs = ((InternalEObject)newSuperGroupElementSublist).eInverseAdd(this, SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST, SuperGroupElementSublist.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newSuperGroupElementSublist, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST, newSuperGroupElementSublist, newSuperGroupElementSublist));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GroupingExpression getGroupingExpr() {
		return groupingExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetGroupingExpr(GroupingExpression newGroupingExpr, NotificationChain msgs) {
		GroupingExpression oldGroupingExpr = groupingExpr;
		groupingExpr = newGroupingExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, oldGroupingExpr, newGroupingExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGroupingExpr(GroupingExpression newGroupingExpr) {
		if (newGroupingExpr != groupingExpr) {
			NotificationChain msgs = null;
			if (groupingExpr != null)
				msgs = ((InternalEObject)groupingExpr).eInverseRemove(this, SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, GroupingExpression.class, msgs);
			if (newGroupingExpr != null)
				msgs = ((InternalEObject)newGroupingExpr).eInverseAdd(this, SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, GroupingExpression.class, msgs);
			msgs = basicSetGroupingExpr(newGroupingExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, newGroupingExpr, newGroupingExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
					if (groupingExpr != null)
						msgs = ((InternalEObject)groupingExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, null, msgs);
					return basicSetGroupingExpr((GroupingExpression)otherEnd, msgs);
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
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP:
					return eBasicSetContainer(null, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
					return eBasicSetContainer(null, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
					return basicSetGroupingExpr(null, msgs);
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
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST, SuperGroup.class, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST, SuperGroupElementSublist.class, msgs);
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__NAME:
				return getName();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__LABEL:
				return getLabel();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP:
				return getSuperGroup();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
				return getSuperGroupElementSublist();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
				return getGroupingExpr();
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP:
				setSuperGroup((SuperGroup)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
				setSuperGroupElementSublist((SuperGroupElementSublist)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)newValue);
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP:
				setSuperGroup((SuperGroup)null);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
				setSuperGroupElementSublist((SuperGroupElementSublist)null);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)null);
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP:
				return getSuperGroup() != null;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
				return getSuperGroupElementSublist() != null;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
				return groupingExpr != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLSuperGroupElementExpressionImpl
