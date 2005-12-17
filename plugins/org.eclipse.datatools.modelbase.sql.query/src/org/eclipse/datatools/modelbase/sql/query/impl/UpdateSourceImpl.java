/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateSourceImpl.java,v 1.1 2005/12/16 13:11:13 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
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
 * An implementation of the model object '<em><b>Update Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceImpl#getUpdateAssignmentExpr <em>Update Assignment Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UpdateSourceImpl extends SQLQueryObjectImpl implements UpdateSource {
	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected UpdateSourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getUpdateSource();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public UpdateAssignmentExpression getUpdateAssignmentExpr() {
		if (eContainerFeatureID != SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR) return null;
		return (UpdateAssignmentExpression)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setUpdateAssignmentExpr(UpdateAssignmentExpression newUpdateAssignmentExpr) {
		if (newUpdateAssignmentExpr != eContainer || (eContainerFeatureID != SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR && newUpdateAssignmentExpr != null)) {
			if (EcoreUtil.isAncestor(this, newUpdateAssignmentExpr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUpdateAssignmentExpr != null)
				msgs = ((InternalEObject)newUpdateAssignmentExpr).eInverseAdd(this, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, UpdateAssignmentExpression.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newUpdateAssignmentExpr, SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, newUpdateAssignmentExpr, newUpdateAssignmentExpr));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.UPDATE_SOURCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, msgs);
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
				case SQLQueryPackage.UPDATE_SOURCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, msgs);
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
				case SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, UpdateAssignmentExpression.class, msgs);
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
			case SQLQueryPackage.UPDATE_SOURCE__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.UPDATE_SOURCE__NAME:
				return getName();
			case SQLQueryPackage.UPDATE_SOURCE__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.UPDATE_SOURCE__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.UPDATE_SOURCE__LABEL:
				return getLabel();
			case SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
				return getUpdateAssignmentExpr();
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
			case SQLQueryPackage.UPDATE_SOURCE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
				setUpdateAssignmentExpr((UpdateAssignmentExpression)newValue);
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
			case SQLQueryPackage.UPDATE_SOURCE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.UPDATE_SOURCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.UPDATE_SOURCE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
				setUpdateAssignmentExpr((UpdateAssignmentExpression)null);
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
			case SQLQueryPackage.UPDATE_SOURCE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.UPDATE_SOURCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.UPDATE_SOURCE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.UPDATE_SOURCE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.UPDATE_SOURCE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
				return getUpdateAssignmentExpr() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //UpdateSourceImpl
