/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.constraints.impl.IndexMemberImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase Index Member</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseIndexMemberImpl#getColumnExpression <em>Column Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseIndexMemberImpl extends IndexMemberImpl implements SybaseIndexMember {
	/**
	 * The default value of the '{@link #getColumnExpression() <em>Column Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String COLUMN_EXPRESSION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getColumnExpression() <em>Column Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnExpression()
	 * @generated
	 * @ordered
	 */
	protected String columnExpression = COLUMN_EXPRESSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseIndexMemberImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SybasesqlmodelPackage.Literals.SYBASE_INDEX_MEMBER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColumnExpression() {
		return columnExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnExpression(String newColumnExpression) {
		String oldColumnExpression = columnExpression;
		columnExpression = newColumnExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION, oldColumnExpression, columnExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION:
				return getColumnExpression();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION:
				setColumnExpression((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION:
				setColumnExpression(COLUMN_EXPRESSION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION:
				return COLUMN_EXPRESSION_EDEFAULT == null ? columnExpression != null : !COLUMN_EXPRESSION_EDEFAULT.equals(columnExpression);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (columnExpression: ");
		result.append(columnExpression);
		result.append(')');
		return result.toString();
	}

} //SybaseIndexMemberImpl
