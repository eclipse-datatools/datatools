/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableColumnDefinitionOrdinalityImpl.java,v 1.2 2005/12/17 01:52:31 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionOrdinality;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Table Column Definition Ordinality</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class XMLTableColumnDefinitionOrdinalityImpl extends XMLTableColumnDefinitionItemImpl implements XMLTableColumnDefinitionOrdinality {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLTableColumnDefinitionOrdinalityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLTableColumnDefinitionOrdinality();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION, msgs);
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
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION, msgs);
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
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION:
					return eContainer.eInverseRemove(this, SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST, XMLTableFunction.class, msgs);
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
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION:
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
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION:
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
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION:
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
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION:
				return getTableFunction() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //XMLTableColumnDefinitionOrdinalityImpl
