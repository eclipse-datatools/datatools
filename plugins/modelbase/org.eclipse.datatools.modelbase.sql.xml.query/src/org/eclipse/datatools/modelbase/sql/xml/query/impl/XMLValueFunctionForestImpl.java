/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionForestImpl.java,v 1.5 2007/02/08 17:04:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Forest</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestImpl#getNullHandlingOption <em>Null Handling Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestImpl#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestImpl#getForestContentList <em>Forest Content List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestImpl#getNamespacesDecl <em>Namespaces Decl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionForestImpl extends XMLValueFunctionImpl implements XMLValueFunctionForest {
	/**
     * The default value of the '{@link #getNullHandlingOption() <em>Null Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullHandlingOption()
     * @generated
     * @ordered
     */
    protected static final XMLNullHandlingType NULL_HANDLING_OPTION_EDEFAULT = XMLNullHandlingType.ABSENT_ON_NULL_LITERAL;

	/**
     * The cached value of the '{@link #getNullHandlingOption() <em>Null Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullHandlingOption()
     * @generated
     * @ordered
     */
    protected XMLNullHandlingType nullHandlingOption = NULL_HANDLING_OPTION_EDEFAULT;

	/**
     * The default value of the '{@link #getReturningOption() <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturningOption()
     * @generated
     * @ordered
     */
    protected static final XMLReturningType RETURNING_OPTION_EDEFAULT = XMLReturningType.RETURNING_CONTENT_LITERAL;

	/**
     * The cached value of the '{@link #getReturningOption() <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturningOption()
     * @generated
     * @ordered
     */
    protected XMLReturningType returningOption = RETURNING_OPTION_EDEFAULT;

	/**
     * The cached value of the '{@link #getForestContentList() <em>Forest Content List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getForestContentList()
     * @generated
     * @ordered
     */
    protected EList forestContentList;

	/**
     * The cached value of the '{@link #getNamespacesDecl() <em>Namespaces Decl</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamespacesDecl()
     * @generated
     * @ordered
     */
    protected XMLNamespacesDeclaration namespacesDecl;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionForestImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_FOREST;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLNullHandlingType getNullHandlingOption() {
        return nullHandlingOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNullHandlingOption(XMLNullHandlingType newNullHandlingOption) {
        XMLNullHandlingType oldNullHandlingOption = nullHandlingOption;
        nullHandlingOption = newNullHandlingOption == null ? NULL_HANDLING_OPTION_EDEFAULT : newNullHandlingOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NULL_HANDLING_OPTION, oldNullHandlingOption, nullHandlingOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLReturningType getReturningOption() {
        return returningOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReturningOption(XMLReturningType newReturningOption) {
        XMLReturningType oldReturningOption = returningOption;
        returningOption = newReturningOption == null ? RETURNING_OPTION_EDEFAULT : newReturningOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__RETURNING_OPTION, oldReturningOption, returningOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getForestContentList() {
        if (forestContentList == null) {
            forestContentList = new EObjectContainmentWithInverseEList(XMLValueFunctionForestContentItem.class, this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_FUNCTION_FOREST);
        }
        return forestContentList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLNamespacesDeclaration getNamespacesDecl() {
        return namespacesDecl;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamespacesDecl(XMLNamespacesDeclaration newNamespacesDecl, NotificationChain msgs) {
        XMLNamespacesDeclaration oldNamespacesDecl = namespacesDecl;
        namespacesDecl = newNamespacesDecl;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL, oldNamespacesDecl, newNamespacesDecl);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamespacesDecl(XMLNamespacesDeclaration newNamespacesDecl) {
        if (newNamespacesDecl != namespacesDecl) {
            NotificationChain msgs = null;
            if (namespacesDecl != null)
                msgs = ((InternalEObject)namespacesDecl).eInverseRemove(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST, XMLNamespacesDeclaration.class, msgs);
            if (newNamespacesDecl != null)
                msgs = ((InternalEObject)newNamespacesDecl).eInverseAdd(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST, XMLNamespacesDeclaration.class, msgs);
            msgs = basicSetNamespacesDecl(newNamespacesDecl, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL, newNamespacesDecl, newNamespacesDecl));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST:
                return ((InternalEList)getForestContentList()).basicAdd(otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL:
                if (namespacesDecl != null)
                    msgs = ((InternalEObject)namespacesDecl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL, null, msgs);
                return basicSetNamespacesDecl((XMLNamespacesDeclaration)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST:
                return ((InternalEList)getForestContentList()).basicRemove(otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL:
                return basicSetNamespacesDecl(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NULL_HANDLING_OPTION:
                return getNullHandlingOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__RETURNING_OPTION:
                return getReturningOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST:
                return getForestContentList();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL:
                return getNamespacesDecl();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NULL_HANDLING_OPTION:
                setNullHandlingOption((XMLNullHandlingType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__RETURNING_OPTION:
                setReturningOption((XMLReturningType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST:
                getForestContentList().clear();
                getForestContentList().addAll((Collection)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL:
                setNamespacesDecl((XMLNamespacesDeclaration)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NULL_HANDLING_OPTION:
                setNullHandlingOption(NULL_HANDLING_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__RETURNING_OPTION:
                setReturningOption(RETURNING_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST:
                getForestContentList().clear();
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL:
                setNamespacesDecl((XMLNamespacesDeclaration)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NULL_HANDLING_OPTION:
                return nullHandlingOption != NULL_HANDLING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__RETURNING_OPTION:
                return returningOption != RETURNING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST:
                return forestContentList != null && !forestContentList.isEmpty();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL:
                return namespacesDecl != null;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (nullHandlingOption: ");
        result.append(nullHandlingOption);
        result.append(", returningOption: ");
        result.append(returningOption);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionForestImpl
