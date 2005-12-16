/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateAssignmentExpressionImpl.java,v 1.6 2005/10/22 01:35:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Update Assignment Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl#getTargetColumnList <em>Target Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl#getUpdateSource <em>Update Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UpdateAssignmentExpressionImpl extends SQLQueryObjectImpl implements UpdateAssignmentExpression {
	/**
	 * The cached value of the '{@link #getTargetColumnList() <em>Target Column List</em>}' reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getTargetColumnList()
	 * @generated
	 * @ordered
	 */
  protected EList targetColumnList = null;

	/**
	 * The cached value of the '{@link #getUpdateSource() <em>Update Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getUpdateSource()
	 * @generated
	 * @ordered
	 */
  protected UpdateSource updateSource = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected UpdateAssignmentExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getUpdateAssignmentExpression();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public QueryUpdateStatement getUpdateStatement() {
		if (eContainerFeatureID != SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT) return null;
		return (QueryUpdateStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setUpdateStatement(QueryUpdateStatement newUpdateStatement) {
		if (newUpdateStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT && newUpdateStatement != null)) {
			if (EcoreUtil.isAncestor(this, newUpdateStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUpdateStatement != null)
				msgs = ((InternalEObject)newUpdateStatement).eInverseAdd(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__ASSIGNMENT_CLAUSE, QueryUpdateStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newUpdateStatement, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT, newUpdateStatement, newUpdateStatement));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList getTargetColumnList() {
		if (targetColumnList == null) {
			targetColumnList = new EObjectWithInverseResolvingEList.ManyInverse(ValueExpressionColumn.class, this, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST, SQLQueryPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET);
		}
		return targetColumnList;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public UpdateSource getUpdateSource() {
		return updateSource;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetUpdateSource(UpdateSource newUpdateSource, NotificationChain msgs) {
		UpdateSource oldUpdateSource = updateSource;
		updateSource = newUpdateSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, oldUpdateSource, newUpdateSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setUpdateSource(UpdateSource newUpdateSource) {
		if (newUpdateSource != updateSource) {
			NotificationChain msgs = null;
			if (updateSource != null)
				msgs = ((InternalEObject)updateSource).eInverseRemove(this, SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, UpdateSource.class, msgs);
			if (newUpdateSource != null)
				msgs = ((InternalEObject)newUpdateSource).eInverseAdd(this, SQLQueryPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, UpdateSource.class, msgs);
			msgs = basicSetUpdateSource(newUpdateSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, newUpdateSource, newUpdateSource));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
					return ((InternalEList)getTargetColumnList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
					if (updateSource != null)
						msgs = ((InternalEObject)updateSource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, null, msgs);
					return basicSetUpdateSource((UpdateSource)otherEnd, msgs);
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
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
					return ((InternalEList)getTargetColumnList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
					return basicSetUpdateSource(null, msgs);
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
				case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__ASSIGNMENT_CLAUSE, QueryUpdateStatement.class, msgs);
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
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__NAME:
				return getName();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__LABEL:
				return getLabel();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
				return getTargetColumnList();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
				return getUpdateSource();
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
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
				getTargetColumnList().clear();
				getTargetColumnList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
				setUpdateSource((UpdateSource)newValue);
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
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
				getTargetColumnList().clear();
				return;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
				setUpdateSource((UpdateSource)null);
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
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
				return targetColumnList != null && !targetColumnList.isEmpty();
			case SQLQueryPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
				return updateSource != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //UpdateAssignmentExpressionImpl
