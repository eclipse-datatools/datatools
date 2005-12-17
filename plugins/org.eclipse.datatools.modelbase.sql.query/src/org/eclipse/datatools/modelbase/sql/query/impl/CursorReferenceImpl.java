/**
 * <copyright>
 * </copyright>
 *
 * $Id: CursorReferenceImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.CursorReference;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
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
 * An implementation of the model object '<em><b>SQL Cursor Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.CursorReferenceImpl#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.CursorReferenceImpl#getDeleteStatement <em>Delete Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CursorReferenceImpl extends SQLQueryObjectImpl implements CursorReference {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected CursorReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getCursorReference();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryUpdateStatement getUpdateStatement() {
		if (eContainerFeatureID != SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT) return null;
		return (QueryUpdateStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUpdateStatement(QueryUpdateStatement newUpdateStatement) {
		if (newUpdateStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT && newUpdateStatement != null)) {
			if (EcoreUtil.isAncestor(this, newUpdateStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUpdateStatement != null)
				msgs = ((InternalEObject)newUpdateStatement).eInverseAdd(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, QueryUpdateStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newUpdateStatement, SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT, newUpdateStatement, newUpdateStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryDeleteStatement getDeleteStatement() {
		if (eContainerFeatureID != SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT) return null;
		return (QueryDeleteStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDeleteStatement(QueryDeleteStatement newDeleteStatement) {
		if (newDeleteStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT && newDeleteStatement != null)) {
			if (EcoreUtil.isAncestor(this, newDeleteStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDeleteStatement != null)
				msgs = ((InternalEObject)newDeleteStatement).eInverseAdd(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, QueryDeleteStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newDeleteStatement, SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT, newDeleteStatement, newDeleteStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.CURSOR_REFERENCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT, msgs);
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
				case SQLQueryPackage.CURSOR_REFERENCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.CURSOR_REFERENCE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT, msgs);
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
				case SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, QueryUpdateStatement.class, msgs);
				case SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, QueryDeleteStatement.class, msgs);
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
			case SQLQueryPackage.CURSOR_REFERENCE__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.CURSOR_REFERENCE__NAME:
				return getName();
			case SQLQueryPackage.CURSOR_REFERENCE__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.CURSOR_REFERENCE__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.CURSOR_REFERENCE__LABEL:
				return getLabel();
			case SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
				return getDeleteStatement();
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
			case SQLQueryPackage.CURSOR_REFERENCE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
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
			case SQLQueryPackage.CURSOR_REFERENCE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
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
			case SQLQueryPackage.CURSOR_REFERENCE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.CURSOR_REFERENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.CURSOR_REFERENCE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.CURSOR_REFERENCE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.CURSOR_REFERENCE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
				return getDeleteStatement() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLCursorReferenceImpl
