/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryResultSpecificationImpl.java,v 1.6 2005/10/22 01:35:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;

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
 * An implementation of the model object '<em><b>SQL Result Column Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryResultSpecificationImpl#getQuerySelect <em>Query Select</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QueryResultSpecificationImpl extends SQLQueryObjectImpl implements QueryResultSpecification {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QueryResultSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQueryResultSpecification();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySelect getQuerySelect() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT) return null;
		return (QuerySelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuerySelect(QuerySelect newQuerySelect) {
		if (newQuerySelect != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT && newQuerySelect != null)) {
			if (EcoreUtil.isAncestor(this, newQuerySelect))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuerySelect != null)
				msgs = ((InternalEObject)newQuerySelect).eInverseAdd(this, SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE, QuerySelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuerySelect, SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT, newQuerySelect, newQuerySelect));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT, msgs);
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
				case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT, msgs);
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
				case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE, QuerySelect.class, msgs);
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
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__NAME:
				return getName();
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__LABEL:
				return getLabel();
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT:
				return getQuerySelect();
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
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
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
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
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
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT:
				return getQuerySelect() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLResultColumnSpecificationImpl
