/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResultTableAllColumnsImpl.java,v 1.3 2005/12/19 20:56:37 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Result Column All</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultTableAllColumnsImpl#getTableExpr <em>Table Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultTableAllColumnsImpl extends QueryResultSpecificationImpl implements ResultTableAllColumns {
	/**
	 * The cached value of the '{@link #getTableExpr() <em>Table Expr</em>}' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getTableExpr()
	 * @generated
	 * @ordered
	 */
  protected TableExpression tableExpr = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ResultTableAllColumnsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getResultTableAllColumns();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public TableExpression getTableExpr() {
		if (tableExpr != null && tableExpr.eIsProxy()) {
			TableExpression oldTableExpr = tableExpr;
			tableExpr = (TableExpression)eResolveProxy((InternalEObject)tableExpr);
			if (tableExpr != oldTableExpr) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR, oldTableExpr, tableExpr));
			}
		}
		return tableExpr;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public TableExpression basicGetTableExpr() {
		return tableExpr;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetTableExpr(TableExpression newTableExpr, NotificationChain msgs) {
		TableExpression oldTableExpr = tableExpr;
		tableExpr = newTableExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR, oldTableExpr, newTableExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setTableExpr(TableExpression newTableExpr) {
		if (newTableExpr != tableExpr) {
			NotificationChain msgs = null;
			if (tableExpr != null)
				msgs = ((InternalEObject)tableExpr).eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS, TableExpression.class, msgs);
			if (newTableExpr != null)
				msgs = ((InternalEObject)newTableExpr).eInverseAdd(this, SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS, TableExpression.class, msgs);
			msgs = basicSetTableExpr(newTableExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR, newTableExpr, newTableExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
					if (tableExpr != null)
						msgs = ((InternalEObject)tableExpr).eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS, TableExpression.class, msgs);
					return basicSetTableExpr((TableExpression)otherEnd, msgs);
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
				case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
					return basicSetTableExpr(null, msgs);
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
				case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__SELECT_CLAUSE, QuerySelect.class, msgs);
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
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__NAME:
				return getName();
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__LABEL:
				return getLabel();
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
				if (resolve) return getTableExpr();
				return basicGetTableExpr();
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
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
				setTableExpr((TableExpression)newValue);
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
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
				setTableExpr((TableExpression)null);
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
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
				return tableExpr != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLResultColumnAllImpl
