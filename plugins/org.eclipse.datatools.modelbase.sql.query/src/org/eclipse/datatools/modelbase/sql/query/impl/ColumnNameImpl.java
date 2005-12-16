/**
 * <copyright>
 * </copyright>
 *
 * $Id: ColumnNameImpl.java,v 1.7 2005/10/22 01:35:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
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
 * An implementation of the model object '<em><b>SQL Column Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ColumnNameImpl#getTableCorrelation <em>Table Correlation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ColumnNameImpl#getWithTableSpecification <em>With Table Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnNameImpl extends SQLQueryObjectImpl implements ColumnName {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ColumnNameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getColumnName();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableCorrelation getTableCorrelation() {
		if (eContainerFeatureID != SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION) return null;
		return (TableCorrelation)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableCorrelation(TableCorrelation newTableCorrelation) {
		if (newTableCorrelation != eContainer || (eContainerFeatureID != SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION && newTableCorrelation != null)) {
			if (EcoreUtil.isAncestor(this, newTableCorrelation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableCorrelation != null)
				msgs = ((InternalEObject)newTableCorrelation).eInverseAdd(this, SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST, TableCorrelation.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTableCorrelation, SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION, newTableCorrelation, newTableCorrelation));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public WithTableSpecification getWithTableSpecification() {
		if (eContainerFeatureID != SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION) return null;
		return (WithTableSpecification)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setWithTableSpecification(WithTableSpecification newWithTableSpecification) {
		if (newWithTableSpecification != eContainer || (eContainerFeatureID != SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION && newWithTableSpecification != null)) {
			if (EcoreUtil.isAncestor(this, newWithTableSpecification))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWithTableSpecification != null)
				msgs = ((InternalEObject)newWithTableSpecification).eInverseAdd(this, SQLQueryPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST, WithTableSpecification.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newWithTableSpecification, SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION, newWithTableSpecification, newWithTableSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.COLUMN_NAME__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION, msgs);
				case SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION, msgs);
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
				case SQLQueryPackage.COLUMN_NAME__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.COLUMN_NAME__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION:
					return eBasicSetContainer(null, SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION, msgs);
				case SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
					return eBasicSetContainer(null, SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION, msgs);
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
				case SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST, TableCorrelation.class, msgs);
				case SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
					return eContainer.eInverseRemove(this, SQLQueryPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST, WithTableSpecification.class, msgs);
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
			case SQLQueryPackage.COLUMN_NAME__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.COLUMN_NAME__NAME:
				return getName();
			case SQLQueryPackage.COLUMN_NAME__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.COLUMN_NAME__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.COLUMN_NAME__LABEL:
				return getLabel();
			case SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
				return getWithTableSpecification();
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
			case SQLQueryPackage.COLUMN_NAME__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.COLUMN_NAME__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.COLUMN_NAME__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.COLUMN_NAME__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.COLUMN_NAME__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
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
			case SQLQueryPackage.COLUMN_NAME__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.COLUMN_NAME__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.COLUMN_NAME__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.COLUMN_NAME__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.COLUMN_NAME__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
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
			case SQLQueryPackage.COLUMN_NAME__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.COLUMN_NAME__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.COLUMN_NAME__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.COLUMN_NAME__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.COLUMN_NAME__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION:
				return getTableCorrelation() != null;
			case SQLQueryPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
				return getWithTableSpecification() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLColumnNameImpl
