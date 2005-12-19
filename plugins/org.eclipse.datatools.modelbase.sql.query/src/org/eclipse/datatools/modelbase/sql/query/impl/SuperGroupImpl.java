/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupImpl.java,v 1.2 2005/12/17 01:46:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElement;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Super Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupImpl#getSuperGroupType <em>Super Group Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupImpl#getSuperGroupElementList <em>Super Group Element List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperGroupImpl extends GroupingImpl implements SuperGroup {
	/**
	 * The default value of the '{@link #getSuperGroupType() <em>Super Group Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSuperGroupType()
	 * @generated
	 * @ordered
	 */
    protected static final SuperGroupType SUPER_GROUP_TYPE_EDEFAULT = SuperGroupType.CUBE_LITERAL;

	/**
	 * The cached value of the '{@link #getSuperGroupType() <em>Super Group Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSuperGroupType()
	 * @generated
	 * @ordered
	 */
    protected SuperGroupType superGroupType = SUPER_GROUP_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSuperGroupElementList() <em>Super Group Element List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSuperGroupElementList()
	 * @generated
	 * @ordered
	 */
    protected EList superGroupElementList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SuperGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getSuperGroup();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SuperGroupType getSuperGroupType() {
		return superGroupType;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSuperGroupType(SuperGroupType newSuperGroupType) {
		SuperGroupType oldSuperGroupType = superGroupType;
		superGroupType = newSuperGroupType == null ? SUPER_GROUP_TYPE_EDEFAULT : newSuperGroupType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_TYPE, oldSuperGroupType, superGroupType));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getSuperGroupElementList() {
		if (superGroupElementList == null) {
			superGroupElementList = new EObjectContainmentWithInverseEList(SuperGroupElement.class, this, SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST, SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP);
		}
		return superGroupElementList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.SUPER_GROUP__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SUPER_GROUP__QUERY_SELECT, msgs);
				case SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR, msgs);
				case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
					return ((InternalEList)getSuperGroupElementList()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.SUPER_GROUP__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.SUPER_GROUP__QUERY_SELECT, msgs);
				case SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR, msgs);
				case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
					return ((InternalEList)getSuperGroupElementList()).basicRemove(otherEnd, msgs);
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
				case SQLQueryPackage.SUPER_GROUP__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, GroupingSetsElementExpression.class, msgs);
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
			case SQLQueryPackage.SUPER_GROUP__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.SUPER_GROUP__NAME:
				return getName();
			case SQLQueryPackage.SUPER_GROUP__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.SUPER_GROUP__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.SUPER_GROUP__LABEL:
				return getLabel();
			case SQLQueryPackage.SUPER_GROUP__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR:
				return getGroupingSetsElementExpr();
			case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_TYPE:
				return getSuperGroupType();
			case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
				return getSuperGroupElementList();
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
			case SQLQueryPackage.SUPER_GROUP__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR:
				setGroupingSetsElementExpr((GroupingSetsElementExpression)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_TYPE:
				setSuperGroupType((SuperGroupType)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
				getSuperGroupElementList().clear();
				getSuperGroupElementList().addAll((Collection)newValue);
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
			case SQLQueryPackage.SUPER_GROUP__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.SUPER_GROUP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.SUPER_GROUP__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR:
				setGroupingSetsElementExpr((GroupingSetsElementExpression)null);
				return;
			case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_TYPE:
				setSuperGroupType(SUPER_GROUP_TYPE_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
				getSuperGroupElementList().clear();
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
			case SQLQueryPackage.SUPER_GROUP__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.SUPER_GROUP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.SUPER_GROUP__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.SUPER_GROUP__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.SUPER_GROUP__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.SUPER_GROUP__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR:
				return getGroupingSetsElementExpr() != null;
			case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_TYPE:
				return superGroupType != SUPER_GROUP_TYPE_EDEFAULT;
			case SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
				return superGroupElementList != null && !superGroupElementList.isEmpty();
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
		result.append(" (superGroupType: ");
		result.append(superGroupType);
		result.append(')');
		return result.toString();
	}

} //SQLSuperGroupImpl
