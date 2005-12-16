/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespacesDeclarationImpl.java,v 1.2 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Namespaces Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespacesDeclarationImpl#getNamespaceDecltemList <em>Namespace Decltem List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespacesDeclarationImpl#getValueFunctionElement <em>Value Function Element</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespacesDeclarationImpl#getValueFunctionForest <em>Value Function Forest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespacesDeclarationImpl#getTableFunction <em>Table Function</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLNamespacesDeclarationImpl extends SQLQueryObjectImpl implements XMLNamespacesDeclaration {
	/**
	 * The cached value of the '{@link #getNamespaceDecltemList() <em>Namespace Decltem List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNamespaceDecltemList()
	 * @generated
	 * @ordered
	 */
    protected EList namespaceDecltemList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLNamespacesDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLNamespacesDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getNamespaceDecltemList() {
		if (namespaceDecltemList == null) {
			namespaceDecltemList = new EObjectContainmentWithInverseEList(XMLNamespaceDeclarationItem.class, this, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST, SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL);
		}
		return namespaceDecltemList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionElement getValueFunctionElement() {
		if (eContainerFeatureID != SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT) return null;
		return (XMLValueFunctionElement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueFunctionElement(XMLValueFunctionElement newValueFunctionElement) {
		if (newValueFunctionElement != eContainer || (eContainerFeatureID != SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT && newValueFunctionElement != null)) {
			if (EcoreUtil.isAncestor(this, newValueFunctionElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueFunctionElement != null)
				msgs = ((InternalEObject)newValueFunctionElement).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL, XMLValueFunctionElement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueFunctionElement, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT, newValueFunctionElement, newValueFunctionElement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionForest getValueFunctionForest() {
		if (eContainerFeatureID != SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST) return null;
		return (XMLValueFunctionForest)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueFunctionForest(XMLValueFunctionForest newValueFunctionForest) {
		if (newValueFunctionForest != eContainer || (eContainerFeatureID != SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST && newValueFunctionForest != null)) {
			if (EcoreUtil.isAncestor(this, newValueFunctionForest))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueFunctionForest != null)
				msgs = ((InternalEObject)newValueFunctionForest).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL, XMLValueFunctionForest.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueFunctionForest, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST, newValueFunctionForest, newValueFunctionForest));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableFunction getTableFunction() {
		if (eContainerFeatureID != SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION) return null;
		return (XMLTableFunction)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableFunction(XMLTableFunction newTableFunction) {
		if (newTableFunction != eContainer || (eContainerFeatureID != SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION && newTableFunction != null)) {
			if (EcoreUtil.isAncestor(this, newTableFunction))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableFunction != null)
				msgs = ((InternalEObject)newTableFunction).eInverseAdd(this, SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, XMLTableFunction.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTableFunction, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, newTableFunction, newTableFunction));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
					return ((InternalEList)getNamespaceDecltemList()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, msgs);
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
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
					return ((InternalEList)getNamespaceDecltemList()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, msgs);
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
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
					return eContainer.eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL, XMLValueFunctionElement.class, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
					return eContainer.eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL, XMLValueFunctionForest.class, msgs);
				case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
					return eContainer.eInverseRemove(this, SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, XMLTableFunction.class, msgs);
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
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
				return getNamespaceDecltemList();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
				return getValueFunctionElement();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
				return getValueFunctionForest();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
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
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
				getNamespaceDecltemList().clear();
				getNamespaceDecltemList().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
				setValueFunctionElement((XMLValueFunctionElement)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
				setValueFunctionForest((XMLValueFunctionForest)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
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
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
				getNamespaceDecltemList().clear();
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
				setValueFunctionElement((XMLValueFunctionElement)null);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
				setValueFunctionForest((XMLValueFunctionForest)null);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
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
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
				return namespaceDecltemList != null && !namespaceDecltemList.isEmpty();
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
				return getValueFunctionElement() != null;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
				return getValueFunctionForest() != null;
			case SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
				return getTableFunction() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //XMLNamespacesDeclarationImpl
