/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLPredicateExistsImpl.java,v 1.4 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Predicate Exists</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateExistsImpl#getXqueryExpr <em>Xquery Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateExistsImpl#getXqueryArgList <em>Xquery Arg List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLPredicateExistsImpl extends XMLPredicateImpl implements XMLPredicateExists {
	/**
	 * The cached value of the '{@link #getXqueryExpr() <em>Xquery Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXqueryExpr()
	 * @generated
	 * @ordered
	 */
    protected XMLQueryExpression xqueryExpr = null;

	/**
	 * The cached value of the '{@link #getXqueryArgList() <em>Xquery Arg List</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXqueryArgList()
	 * @generated
	 * @ordered
	 */
    protected XMLQueryArgumentList xqueryArgList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLPredicateExistsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLPredicateExists();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLQueryExpression getXqueryExpr() {
		return xqueryExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetXqueryExpr(XMLQueryExpression newXqueryExpr, NotificationChain msgs) {
		XMLQueryExpression oldXqueryExpr = xqueryExpr;
		xqueryExpr = newXqueryExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, oldXqueryExpr, newXqueryExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXqueryExpr(XMLQueryExpression newXqueryExpr) {
		if (newXqueryExpr != xqueryExpr) {
			NotificationChain msgs = null;
			if (xqueryExpr != null)
				msgs = ((InternalEObject)xqueryExpr).eInverseRemove(this, SQLXMLQueryPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, XMLQueryExpression.class, msgs);
			if (newXqueryExpr != null)
				msgs = ((InternalEObject)newXqueryExpr).eInverseAdd(this, SQLXMLQueryPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, XMLQueryExpression.class, msgs);
			msgs = basicSetXqueryExpr(newXqueryExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, newXqueryExpr, newXqueryExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLQueryArgumentList getXqueryArgList() {
		return xqueryArgList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetXqueryArgList(XMLQueryArgumentList newXqueryArgList, NotificationChain msgs) {
		XMLQueryArgumentList oldXqueryArgList = xqueryArgList;
		xqueryArgList = newXqueryArgList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, oldXqueryArgList, newXqueryArgList);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXqueryArgList(XMLQueryArgumentList newXqueryArgList) {
		if (newXqueryArgList != xqueryArgList) {
			NotificationChain msgs = null;
			if (xqueryArgList != null)
				msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, SQLXMLQueryPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, XMLQueryArgumentList.class, msgs);
			if (newXqueryArgList != null)
				msgs = ((InternalEObject)newXqueryArgList).eInverseAdd(this, SQLXMLQueryPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, XMLQueryArgumentList.class, msgs);
			msgs = basicSetXqueryArgList(newXqueryArgList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, newXqueryArgList, newXqueryArgList));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__UPDATE_STATEMENT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DELETE_STATEMENT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__TABLE_JOINED, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_LEFT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEST, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
					if (xqueryExpr != null)
						msgs = ((InternalEObject)xqueryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, null, msgs);
					return basicSetXqueryExpr((XMLQueryExpression)otherEnd, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
					if (xqueryArgList != null)
						msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, null, msgs);
					return basicSetXqueryArgList((XMLQueryArgumentList)otherEnd, msgs);
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
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__UPDATE_STATEMENT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DELETE_STATEMENT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__TABLE_JOINED:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__TABLE_JOINED, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_LEFT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEST:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEST, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
					return basicSetXqueryExpr(null, msgs);
				case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
					return basicSetXqueryArgList(null, msgs);
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
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__TABLE_JOINED:
				return getTableJoined();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEST:
				return getNest();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEGATED_PREDICATE:
				return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__HAS_SELECTIVITY:
				return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__SELECTIVITY_VALUE:
				return getSelectivityValue();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
				return getXqueryExpr();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
				return getXqueryArgList();
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
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEGATED_PREDICATE:
				setNegatedPredicate(((Boolean)newValue).booleanValue());
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__HAS_SELECTIVITY:
				setHasSelectivity(((Boolean)newValue).booleanValue());
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__SELECTIVITY_VALUE:
				setSelectivityValue((Integer)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
				setXqueryExpr((XMLQueryExpression)newValue);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
				setXqueryArgList((XMLQueryArgumentList)newValue);
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
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEGATED_PREDICATE:
				setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__HAS_SELECTIVITY:
				setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__SELECTIVITY_VALUE:
				setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
				setXqueryExpr((XMLQueryExpression)null);
				return;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
				setXqueryArgList((XMLQueryArgumentList)null);
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
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEST:
				return getNest() != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__NEGATED_PREDICATE:
				return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__HAS_SELECTIVITY:
				return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__SELECTIVITY_VALUE:
				return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
				return xqueryExpr != null;
			case SQLXMLQueryPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
				return xqueryArgList != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //XMLPredicateExistsImpl
