/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableReferenceImpl.java,v 1.7 2005/10/22 01:35:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
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
 * An implementation of the model object '<em><b>SQL Table Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getTableJoinedRight <em>Table Joined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getTableJoinedLeft <em>Table Joined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getQuerySelect <em>Query Select</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getNest <em>Nest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TableReferenceImpl extends SQLQueryObjectImpl implements TableReference {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected TableReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getTableReference();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableJoined getTableJoinedRight() {
		if (eContainerFeatureID != SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT) return null;
		return (TableJoined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableJoinedRight(TableJoined newTableJoinedRight) {
		if (newTableJoinedRight != eContainer || (eContainerFeatureID != SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT && newTableJoinedRight != null)) {
			if (EcoreUtil.isAncestor(this, newTableJoinedRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableJoinedRight != null)
				msgs = ((InternalEObject)newTableJoinedRight).eInverseAdd(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTableJoinedRight, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, newTableJoinedRight, newTableJoinedRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableJoined getTableJoinedLeft() {
		if (eContainerFeatureID != SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT) return null;
		return (TableJoined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableJoinedLeft(TableJoined newTableJoinedLeft) {
		if (newTableJoinedLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT && newTableJoinedLeft != null)) {
			if (EcoreUtil.isAncestor(this, newTableJoinedLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableJoinedLeft != null)
				msgs = ((InternalEObject)newTableJoinedLeft).eInverseAdd(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTableJoinedLeft, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, newTableJoinedLeft, newTableJoinedLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySelect getQuerySelect() {
		if (eContainerFeatureID != SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT) return null;
		return (QuerySelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuerySelect(QuerySelect newQuerySelect) {
		if (newQuerySelect != eContainer || (eContainerFeatureID != SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT && newQuerySelect != null)) {
			if (EcoreUtil.isAncestor(this, newQuerySelect))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuerySelect != null)
				msgs = ((InternalEObject)newQuerySelect).eInverseAdd(this, SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuerySelect, SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT, newQuerySelect, newQuerySelect));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public TableNested getNest() {
		if (eContainerFeatureID != SQLQueryPackage.TABLE_REFERENCE__NEST) return null;
		return (TableNested)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setNest(TableNested newNest) {
		if (newNest != eContainer || (eContainerFeatureID != SQLQueryPackage.TABLE_REFERENCE__NEST && newNest != null)) {
			if (EcoreUtil.isAncestor(this, newNest))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNest != null)
				msgs = ((InternalEObject)newNest).eInverseAdd(this, SQLQueryPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newNest, SQLQueryPackage.TABLE_REFERENCE__NEST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_REFERENCE__NEST, newNest, newNest));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.TABLE_REFERENCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_REFERENCE__NEST, msgs);
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
				case SQLQueryPackage.TABLE_REFERENCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_REFERENCE__NEST, msgs);
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
				case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.TABLE_REFERENCE__NEST:
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
			case SQLQueryPackage.TABLE_REFERENCE__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.TABLE_REFERENCE__NAME:
				return getName();
			case SQLQueryPackage.TABLE_REFERENCE__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.TABLE_REFERENCE__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.TABLE_REFERENCE__LABEL:
				return getLabel();
			case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.TABLE_REFERENCE__NEST:
				return getNest();
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
			case SQLQueryPackage.TABLE_REFERENCE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__NEST:
				setNest((TableNested)newValue);
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
			case SQLQueryPackage.TABLE_REFERENCE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.TABLE_REFERENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.TABLE_REFERENCE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.TABLE_REFERENCE__NEST:
				setNest((TableNested)null);
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
			case SQLQueryPackage.TABLE_REFERENCE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.TABLE_REFERENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.TABLE_REFERENCE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.TABLE_REFERENCE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.TABLE_REFERENCE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.TABLE_REFERENCE__NEST:
				return getNest() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLTableReferenceImpl
