/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLQueryExpressionImpl.java,v 1.1 2005/12/16 13:16:51 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery;
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
 * An implementation of the model object '<em><b>XML Query Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl#getXqueryExprContent <em>Xquery Expr Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl#getValueFunctionQuery <em>Value Function Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLQueryExpressionImpl extends SQLQueryObjectImpl implements XMLQueryExpression {
	/**
	 * The default value of the '{@link #getXqueryExprContent() <em>Xquery Expr Content</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXqueryExprContent()
	 * @generated
	 * @ordered
	 */
    protected static final String XQUERY_EXPR_CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getXqueryExprContent() <em>Xquery Expr Content</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXqueryExprContent()
	 * @generated
	 * @ordered
	 */
    protected String xqueryExprContent = XQUERY_EXPR_CONTENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLQueryExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryModelPackage.eINSTANCE.getXMLQueryExpression();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getXqueryExprContent() {
		return xqueryExprContent;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXqueryExprContent(String newXqueryExprContent) {
		String oldXqueryExprContent = xqueryExprContent;
		xqueryExprContent = newXqueryExprContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT, oldXqueryExprContent, xqueryExprContent));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPredicateExists getPredicateExists() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS) return null;
		return (XMLPredicateExists)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPredicateExists(XMLPredicateExists newPredicateExists) {
		if (newPredicateExists != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS && newPredicateExists != null)) {
			if (EcoreUtil.isAncestor(this, newPredicateExists))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPredicateExists != null)
				msgs = ((InternalEObject)newPredicateExists).eInverseAdd(this, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, XMLPredicateExists.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newPredicateExists, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, newPredicateExists, newPredicateExists));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionQuery getValueFunctionQuery() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY) return null;
		return (XMLValueFunctionQuery)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueFunctionQuery(XMLValueFunctionQuery newValueFunctionQuery) {
		if (newValueFunctionQuery != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY && newValueFunctionQuery != null)) {
			if (EcoreUtil.isAncestor(this, newValueFunctionQuery))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueFunctionQuery != null)
				msgs = ((InternalEObject)newValueFunctionQuery).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR, XMLValueFunctionQuery.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueFunctionQuery, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY, newValueFunctionQuery, newValueFunctionQuery));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY, msgs);
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
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY, msgs);
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
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, XMLPredicateExists.class, msgs);
				case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR, XMLValueFunctionQuery.class, msgs);
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
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__NAME:
				return getName();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__LABEL:
				return getLabel();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT:
				return getXqueryExprContent();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
				return getPredicateExists();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
				return getValueFunctionQuery();
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
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT:
				setXqueryExprContent((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
				setPredicateExists((XMLPredicateExists)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
				setValueFunctionQuery((XMLValueFunctionQuery)newValue);
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
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT:
				setXqueryExprContent(XQUERY_EXPR_CONTENT_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
				setPredicateExists((XMLPredicateExists)null);
				return;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
				setValueFunctionQuery((XMLValueFunctionQuery)null);
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
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT:
				return XQUERY_EXPR_CONTENT_EDEFAULT == null ? xqueryExprContent != null : !XQUERY_EXPR_CONTENT_EDEFAULT.equals(xqueryExprContent);
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
				return getPredicateExists() != null;
			case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
				return getValueFunctionQuery() != null;
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
		result.append(" (xqueryExprContent: ");
		result.append(xqueryExprContent);
		result.append(')');
		return result.toString();
	}

} //XMLQueryExpressionImpl
