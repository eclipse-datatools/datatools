/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableNestedImpl.java,v 1.3 2005/12/19 20:56:36 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Nested</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableNestedImpl#getNestedTableRef <em>Nested Table Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableNestedImpl extends TableReferenceImpl implements TableNested {
	/**
	 * The cached value of the '{@link #getNestedTableRef() <em>Nested Table Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getNestedTableRef()
	 * @generated
	 * @ordered
	 */
  protected TableReference nestedTableRef = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected TableNestedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getTableNested();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public TableReference getNestedTableRef() {
		return nestedTableRef;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetNestedTableRef(TableReference newNestedTableRef, NotificationChain msgs) {
		TableReference oldNestedTableRef = nestedTableRef;
		nestedTableRef = newNestedTableRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, oldNestedTableRef, newNestedTableRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setNestedTableRef(TableReference newNestedTableRef) {
		if (newNestedTableRef != nestedTableRef) {
			NotificationChain msgs = null;
			if (nestedTableRef != null)
				msgs = ((InternalEObject)nestedTableRef).eInverseRemove(this, SQLQueryModelPackage.TABLE_REFERENCE__NEST, TableReference.class, msgs);
			if (newNestedTableRef != null)
				msgs = ((InternalEObject)newNestedTableRef).eInverseAdd(this, SQLQueryModelPackage.TABLE_REFERENCE__NEST, TableReference.class, msgs);
			msgs = basicSetNestedTableRef(newNestedTableRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, newNestedTableRef, newNestedTableRef));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.TABLE_NESTED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.TABLE_NESTED__NEST, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
					if (nestedTableRef != null)
						msgs = ((InternalEObject)nestedTableRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, null, msgs);
					return basicSetNestedTableRef((TableReference)otherEnd, msgs);
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
				case SQLQueryModelPackage.TABLE_NESTED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__NEST:
					return eBasicSetContainer(null, SQLQueryModelPackage.TABLE_NESTED__NEST, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
					return basicSetNestedTableRef(null, msgs);
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
				case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.TABLE_NESTED__NEST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
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
			case SQLQueryModelPackage.TABLE_NESTED__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.TABLE_NESTED__NAME:
				return getName();
			case SQLQueryModelPackage.TABLE_NESTED__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.TABLE_NESTED__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.TABLE_NESTED__LABEL:
				return getLabel();
			case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryModelPackage.TABLE_NESTED__NEST:
				return getNest();
			case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
				return getNestedTableRef();
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
			case SQLQueryModelPackage.TABLE_NESTED__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
				setNestedTableRef((TableReference)newValue);
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
			case SQLQueryModelPackage.TABLE_NESTED__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.TABLE_NESTED__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.TABLE_NESTED__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
				setNestedTableRef((TableReference)null);
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
			case SQLQueryModelPackage.TABLE_NESTED__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.TABLE_NESTED__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.TABLE_NESTED__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.TABLE_NESTED__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.TABLE_NESTED__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryModelPackage.TABLE_NESTED__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryModelPackage.TABLE_NESTED__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryModelPackage.TABLE_NESTED__NEST:
				return getNest() != null;
			case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
				return nestedTableRef != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLTableNestedImpl
