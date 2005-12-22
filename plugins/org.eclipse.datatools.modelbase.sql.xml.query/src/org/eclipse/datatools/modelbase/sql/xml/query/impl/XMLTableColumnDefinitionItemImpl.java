/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableColumnDefinitionItemImpl.java,v 1.1 2005/12/16 13:16:51 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction;
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
 * An implementation of the model object '<em><b>XML Table Column Definition Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionItemImpl#getTableFunction <em>Table Function</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLTableColumnDefinitionItemImpl extends SQLQueryObjectImpl implements XMLTableColumnDefinitionItem {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLTableColumnDefinitionItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryModelPackage.eINSTANCE.getXMLTableColumnDefinitionItem();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableFunction getTableFunction() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION) return null;
		return (XMLTableFunction)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableFunction(XMLTableFunction newTableFunction) {
		if (newTableFunction != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION && newTableFunction != null)) {
			if (EcoreUtil.isAncestor(this, newTableFunction))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableFunction != null)
				msgs = ((InternalEObject)newTableFunction).eInverseAdd(this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST, XMLTableFunction.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTableFunction, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION, newTableFunction, newTableFunction));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION, msgs);
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
				case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION, msgs);
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
				case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST, XMLTableFunction.class, msgs);
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
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__NAME:
				return getName();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__LABEL:
				return getLabel();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION:
				return getTableFunction();
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
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION:
				setTableFunction((XMLTableFunction)newValue);
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
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION:
				setTableFunction((XMLTableFunction)null);
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
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION:
				return getTableFunction() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //XMLTableColumnDefinitionItemImpl
