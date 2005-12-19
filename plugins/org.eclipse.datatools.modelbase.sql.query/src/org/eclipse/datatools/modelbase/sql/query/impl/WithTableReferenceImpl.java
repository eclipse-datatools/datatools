/**
 * <copyright>
 * </copyright>
 *
 * $Id: WithTableReferenceImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>With Table Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableReferenceImpl#getWithTableSpecification <em>With Table Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WithTableReferenceImpl extends TableExpressionImpl implements WithTableReference {
	/**
	 * The cached value of the '{@link #getWithTableSpecification() <em>With Table Specification</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getWithTableSpecification()
	 * @generated
	 * @ordered
	 */
    protected WithTableSpecification withTableSpecification = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected WithTableReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getWithTableReference();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public WithTableSpecification getWithTableSpecification() {
		if (withTableSpecification != null && withTableSpecification.eIsProxy()) {
			WithTableSpecification oldWithTableSpecification = withTableSpecification;
			withTableSpecification = (WithTableSpecification)eResolveProxy((InternalEObject)withTableSpecification);
			if (withTableSpecification != oldWithTableSpecification) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION, oldWithTableSpecification, withTableSpecification));
			}
		}
		return withTableSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public WithTableSpecification basicGetWithTableSpecification() {
		return withTableSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetWithTableSpecification(WithTableSpecification newWithTableSpecification, NotificationChain msgs) {
		WithTableSpecification oldWithTableSpecification = withTableSpecification;
		withTableSpecification = newWithTableSpecification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION, oldWithTableSpecification, newWithTableSpecification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setWithTableSpecification(WithTableSpecification newWithTableSpecification) {
		if (newWithTableSpecification != withTableSpecification) {
			NotificationChain msgs = null;
			if (withTableSpecification != null)
				msgs = ((InternalEObject)withTableSpecification).eInverseRemove(this, SQLQueryPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES, WithTableSpecification.class, msgs);
			if (newWithTableSpecification != null)
				msgs = ((InternalEObject)newWithTableSpecification).eInverseAdd(this, SQLQueryPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES, WithTableSpecification.class, msgs);
			msgs = basicSetWithTableSpecification(newWithTableSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION, newWithTableSpecification, newWithTableSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.WITH_TABLE_REFERENCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.WITH_TABLE_REFERENCE__NEST, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_CORRELATION:
					if (tableCorrelation != null)
						msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_CORRELATION, null, msgs);
					return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
					if (withTableSpecification != null)
						msgs = ((InternalEObject)withTableSpecification).eInverseRemove(this, SQLQueryPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES, WithTableSpecification.class, msgs);
					return basicSetWithTableSpecification((WithTableSpecification)otherEnd, msgs);
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
				case SQLQueryPackage.WITH_TABLE_REFERENCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.WITH_TABLE_REFERENCE__NEST, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_CORRELATION:
					return basicSetTableCorrelation(null, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
					return basicSetWithTableSpecification(null, msgs);
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
				case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.WITH_TABLE_REFERENCE__NEST:
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
			case SQLQueryPackage.WITH_TABLE_REFERENCE__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__NAME:
				return getName();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__LABEL:
				return getLabel();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__NEST:
				return getNest();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__COLUMN_LIST:
				return getColumnList();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__RESULT_TABLE_ALL_COLUMNS:
				return getResultTableAllColumns();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__VALUE_EXPR_COLUMNS:
				return getValueExprColumns();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
				if (resolve) return getWithTableSpecification();
				return basicGetWithTableSpecification();
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
			case SQLQueryPackage.WITH_TABLE_REFERENCE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__COLUMN_LIST:
				getColumnList().clear();
				getColumnList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				getResultTableAllColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				getValueExprColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
				setWithTableSpecification((WithTableSpecification)newValue);
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
			case SQLQueryPackage.WITH_TABLE_REFERENCE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__COLUMN_LIST:
				getColumnList().clear();
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				return;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
				setWithTableSpecification((WithTableSpecification)null);
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
			case SQLQueryPackage.WITH_TABLE_REFERENCE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.WITH_TABLE_REFERENCE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.WITH_TABLE_REFERENCE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__NEST:
				return getNest() != null;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__COLUMN_LIST:
				return columnList != null && !columnList.isEmpty();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__TABLE_CORRELATION:
				return tableCorrelation != null;
			case SQLQueryPackage.WITH_TABLE_REFERENCE__RESULT_TABLE_ALL_COLUMNS:
				return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__VALUE_EXPR_COLUMNS:
				return valueExprColumns != null && !valueExprColumns.isEmpty();
			case SQLQueryPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
				return withTableSpecification != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //WithTableReferenceImpl
