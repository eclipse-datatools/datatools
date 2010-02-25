/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespacesDeclarationImpl.java,v 1.4 2008/07/07 19:55:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
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
    protected EList namespaceDecltemList;

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
        return SQLXMLQueryModelPackage.Literals.XML_NAMESPACES_DECLARATION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getNamespaceDecltemList() {
        if (namespaceDecltemList == null) {
            namespaceDecltemList = new EObjectContainmentWithInverseEList(XMLNamespaceDeclarationItem.class, this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST, SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL);
        }
        return namespaceDecltemList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionElement getValueFunctionElement() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT) return null;
        return (XMLValueFunctionElement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueFunctionElement(XMLValueFunctionElement newValueFunctionElement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueFunctionElement, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueFunctionElement(XMLValueFunctionElement newValueFunctionElement) {
        if (newValueFunctionElement != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT && newValueFunctionElement != null)) {
            if (EcoreUtil.isAncestor(this, newValueFunctionElement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueFunctionElement != null)
                msgs = ((InternalEObject)newValueFunctionElement).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL, XMLValueFunctionElement.class, msgs);
            msgs = basicSetValueFunctionElement(newValueFunctionElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT, newValueFunctionElement, newValueFunctionElement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionForest getValueFunctionForest() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST) return null;
        return (XMLValueFunctionForest)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueFunctionForest(XMLValueFunctionForest newValueFunctionForest, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueFunctionForest, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueFunctionForest(XMLValueFunctionForest newValueFunctionForest) {
        if (newValueFunctionForest != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST && newValueFunctionForest != null)) {
            if (EcoreUtil.isAncestor(this, newValueFunctionForest))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueFunctionForest != null)
                msgs = ((InternalEObject)newValueFunctionForest).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL, XMLValueFunctionForest.class, msgs);
            msgs = basicSetValueFunctionForest(newValueFunctionForest, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST, newValueFunctionForest, newValueFunctionForest));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTableFunction getTableFunction() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION) return null;
        return (XMLTableFunction)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTableFunction(XMLTableFunction newTableFunction, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTableFunction, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableFunction(XMLTableFunction newTableFunction) {
        if (newTableFunction != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION && newTableFunction != null)) {
            if (EcoreUtil.isAncestor(this, newTableFunction))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTableFunction != null)
                msgs = ((InternalEObject)newTableFunction).eInverseAdd(this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, XMLTableFunction.class, msgs);
            msgs = basicSetTableFunction(newTableFunction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, newTableFunction, newTableFunction));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
                return ((InternalEList)getNamespaceDecltemList()).basicAdd(otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueFunctionElement((XMLValueFunctionElement)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueFunctionForest((XMLValueFunctionForest)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTableFunction((XMLTableFunction)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
                return ((InternalEList)getNamespaceDecltemList()).basicRemove(otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
                return basicSetValueFunctionElement(null, msgs);
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
                return basicSetValueFunctionForest(null, msgs);
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
                return basicSetTableFunction(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL, XMLValueFunctionElement.class, msgs);
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL, XMLValueFunctionForest.class, msgs);
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, XMLTableFunction.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
                return getNamespaceDecltemList();
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
                return getValueFunctionElement();
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
                return getValueFunctionForest();
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
                return getTableFunction();
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
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
                getNamespaceDecltemList().clear();
                getNamespaceDecltemList().addAll((Collection)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
                setValueFunctionElement((XMLValueFunctionElement)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
                setValueFunctionForest((XMLValueFunctionForest)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
                setTableFunction((XMLTableFunction)newValue);
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
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
                getNamespaceDecltemList().clear();
                return;
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
                setValueFunctionElement((XMLValueFunctionElement)null);
                return;
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
                setValueFunctionForest((XMLValueFunctionForest)null);
                return;
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
                setTableFunction((XMLTableFunction)null);
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
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST:
                return namespaceDecltemList != null && !namespaceDecltemList.isEmpty();
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT:
                return getValueFunctionElement() != null;
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST:
                return getValueFunctionForest() != null;
            case SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION:
                return getTableFunction() != null;
        }
        return super.eIsSet(featureID);
    }

} //XMLNamespacesDeclarationImpl
