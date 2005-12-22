/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLQueryArgumentListImpl.java,v 1.1 2005/12/16 13:16:52 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery;
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
 * An implementation of the model object '<em><b>XML Query Argument List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getPassingMechanism <em>Passing Mechanism</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getXqueryArgListChildren <em>Xquery Arg List Children</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getValueFunctionQuery <em>Value Function Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getTableFunction <em>Table Function</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLQueryArgumentListImpl extends SQLQueryObjectImpl implements XMLQueryArgumentList {
	/**
	 * The default value of the '{@link #getPassingMechanism() <em>Passing Mechanism</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPassingMechanism()
	 * @generated
	 * @ordered
	 */
    protected static final XMLPassingType PASSING_MECHANISM_EDEFAULT = XMLPassingType.BY_REF_LITERAL;

	/**
	 * The cached value of the '{@link #getPassingMechanism() <em>Passing Mechanism</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPassingMechanism()
	 * @generated
	 * @ordered
	 */
    protected XMLPassingType passingMechanism = PASSING_MECHANISM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getXqueryArgListChildren() <em>Xquery Arg List Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXqueryArgListChildren()
	 * @generated
	 * @ordered
	 */
    protected EList xqueryArgListChildren = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLQueryArgumentListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryModelPackage.eINSTANCE.getXMLQueryArgumentList();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPassingType getPassingMechanism() {
		return passingMechanism;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPassingMechanism(XMLPassingType newPassingMechanism) {
		XMLPassingType oldPassingMechanism = passingMechanism;
		passingMechanism = newPassingMechanism == null ? PASSING_MECHANISM_EDEFAULT : newPassingMechanism;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM, oldPassingMechanism, passingMechanism));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPredicateExists getPredicateExists() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS) return null;
		return (XMLPredicateExists)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPredicateExists(XMLPredicateExists newPredicateExists) {
		if (newPredicateExists != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS && newPredicateExists != null)) {
			if (EcoreUtil.isAncestor(this, newPredicateExists))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPredicateExists != null)
				msgs = ((InternalEObject)newPredicateExists).eInverseAdd(this, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, XMLPredicateExists.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newPredicateExists, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, newPredicateExists, newPredicateExists));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getXqueryArgListChildren() {
		if (xqueryArgListChildren == null) {
			xqueryArgListChildren = new EObjectContainmentWithInverseEList(XMLQueryArgumentItem.class, this, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_ITEM__XQUERY_ARG_LIST);
		}
		return xqueryArgListChildren;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionQuery getValueFunctionQuery() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY) return null;
		return (XMLValueFunctionQuery)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueFunctionQuery(XMLValueFunctionQuery newValueFunctionQuery) {
		if (newValueFunctionQuery != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY && newValueFunctionQuery != null)) {
			if (EcoreUtil.isAncestor(this, newValueFunctionQuery))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueFunctionQuery != null)
				msgs = ((InternalEObject)newValueFunctionQuery).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST, XMLValueFunctionQuery.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueFunctionQuery, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY, newValueFunctionQuery, newValueFunctionQuery));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableFunction getTableFunction() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION) return null;
		return (XMLTableFunction)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableFunction(XMLTableFunction newTableFunction) {
		if (newTableFunction != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION && newTableFunction != null)) {
			if (EcoreUtil.isAncestor(this, newTableFunction))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableFunction != null)
				msgs = ((InternalEObject)newTableFunction).eInverseAdd(this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, XMLTableFunction.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTableFunction, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, newTableFunction, newTableFunction));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
					return ((InternalEList)getXqueryArgListChildren()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, msgs);
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
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
					return ((InternalEList)getXqueryArgListChildren()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, msgs);
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
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, XMLPredicateExists.class, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST, XMLValueFunctionQuery.class, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, XMLTableFunction.class, msgs);
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
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__NAME:
				return getName();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__LABEL:
				return getLabel();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM:
				return getPassingMechanism();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
				return getPredicateExists();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
				return getXqueryArgListChildren();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
				return getValueFunctionQuery();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
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
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM:
				setPassingMechanism((XMLPassingType)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
				setPredicateExists((XMLPredicateExists)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
				getXqueryArgListChildren().clear();
				getXqueryArgListChildren().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
				setValueFunctionQuery((XMLValueFunctionQuery)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
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
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM:
				setPassingMechanism(PASSING_MECHANISM_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
				setPredicateExists((XMLPredicateExists)null);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
				getXqueryArgListChildren().clear();
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
				setValueFunctionQuery((XMLValueFunctionQuery)null);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
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
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM:
				return passingMechanism != PASSING_MECHANISM_EDEFAULT;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
				return getPredicateExists() != null;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
				return xqueryArgListChildren != null && !xqueryArgListChildren.isEmpty();
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
				return getValueFunctionQuery() != null;
			case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
				return getTableFunction() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (passingMechanism: ");
		result.append(passingMechanism);
		result.append(')');
		return result.toString();
	}

} //XMLQueryArgumentListImpl
