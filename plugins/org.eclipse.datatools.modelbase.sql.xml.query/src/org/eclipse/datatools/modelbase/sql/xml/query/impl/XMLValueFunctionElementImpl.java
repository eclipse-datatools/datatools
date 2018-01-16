/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionElementImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
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
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl#getElementName <em>Element Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl#getNamespacesDecl <em>Namespaces Decl</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl#getAttributesDecl <em>Attributes Decl</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl#getElementContentList <em>Element Content List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionElementImpl extends XMLValueFunctionImpl implements XMLValueFunctionElement {
	/**
     * The default value of the '{@link #getElementName() <em>Element Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getElementName()
     * @generated
     * @ordered
     */
    protected static final String ELEMENT_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getElementName() <em>Element Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getElementName()
     * @generated
     * @ordered
     */
    protected String elementName = ELEMENT_NAME_EDEFAULT;

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
     * The cached value of the '{@link #getNamespacesDecl() <em>Namespaces Decl</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamespacesDecl()
     * @generated
     * @ordered
     */
    protected XMLNamespacesDeclaration namespacesDecl;

	/**
     * The cached value of the '{@link #getAttributesDecl() <em>Attributes Decl</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributesDecl()
     * @generated
     * @ordered
     */
    protected XMLAttributesDeclaration attributesDecl;

	/**
     * The cached value of the '{@link #getElementContentList() <em>Element Content List</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getElementContentList()
     * @generated
     * @ordered
     */
    protected XMLValueFunctionElementContentList elementContentList;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionElementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_ELEMENT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getElementName() {
        return elementName;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElementName(String newElementName) {
        String oldElementName = elementName;
        elementName = newElementName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_NAME, oldElementName, elementName));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__RETURNING_OPTION, oldReturningOption, returningOption));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL, oldNamespacesDecl, newNamespacesDecl);
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
                msgs = ((InternalEObject)namespacesDecl).eInverseRemove(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT, XMLNamespacesDeclaration.class, msgs);
            if (newNamespacesDecl != null)
                msgs = ((InternalEObject)newNamespacesDecl).eInverseAdd(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT, XMLNamespacesDeclaration.class, msgs);
            msgs = basicSetNamespacesDecl(newNamespacesDecl, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL, newNamespacesDecl, newNamespacesDecl));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLAttributesDeclaration getAttributesDecl() {
        return attributesDecl;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAttributesDecl(XMLAttributesDeclaration newAttributesDecl, NotificationChain msgs) {
        XMLAttributesDeclaration oldAttributesDecl = attributesDecl;
        attributesDecl = newAttributesDecl;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL, oldAttributesDecl, newAttributesDecl);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAttributesDecl(XMLAttributesDeclaration newAttributesDecl) {
        if (newAttributesDecl != attributesDecl) {
            NotificationChain msgs = null;
            if (attributesDecl != null)
                msgs = ((InternalEObject)attributesDecl).eInverseRemove(this, SQLXMLQueryModelPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT, XMLAttributesDeclaration.class, msgs);
            if (newAttributesDecl != null)
                msgs = ((InternalEObject)newAttributesDecl).eInverseAdd(this, SQLXMLQueryModelPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT, XMLAttributesDeclaration.class, msgs);
            msgs = basicSetAttributesDecl(newAttributesDecl, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL, newAttributesDecl, newAttributesDecl));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionElementContentList getElementContentList() {
        return elementContentList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetElementContentList(XMLValueFunctionElementContentList newElementContentList, NotificationChain msgs) {
        XMLValueFunctionElementContentList oldElementContentList = elementContentList;
        elementContentList = newElementContentList;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST, oldElementContentList, newElementContentList);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElementContentList(XMLValueFunctionElementContentList newElementContentList) {
        if (newElementContentList != elementContentList) {
            NotificationChain msgs = null;
            if (elementContentList != null)
                msgs = ((InternalEObject)elementContentList).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT, XMLValueFunctionElementContentList.class, msgs);
            if (newElementContentList != null)
                msgs = ((InternalEObject)newElementContentList).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT, XMLValueFunctionElementContentList.class, msgs);
            msgs = basicSetElementContentList(newElementContentList, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST, newElementContentList, newElementContentList));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL:
                if (namespacesDecl != null)
                    msgs = ((InternalEObject)namespacesDecl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL, null, msgs);
                return basicSetNamespacesDecl((XMLNamespacesDeclaration)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL:
                if (attributesDecl != null)
                    msgs = ((InternalEObject)attributesDecl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL, null, msgs);
                return basicSetAttributesDecl((XMLAttributesDeclaration)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST:
                if (elementContentList != null)
                    msgs = ((InternalEObject)elementContentList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST, null, msgs);
                return basicSetElementContentList((XMLValueFunctionElementContentList)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL:
                return basicSetNamespacesDecl(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL:
                return basicSetAttributesDecl(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST:
                return basicSetElementContentList(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_NAME:
                return getElementName();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__RETURNING_OPTION:
                return getReturningOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL:
                return getNamespacesDecl();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL:
                return getAttributesDecl();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST:
                return getElementContentList();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_NAME:
                setElementName((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__RETURNING_OPTION:
                setReturningOption((XMLReturningType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL:
                setNamespacesDecl((XMLNamespacesDeclaration)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL:
                setAttributesDecl((XMLAttributesDeclaration)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST:
                setElementContentList((XMLValueFunctionElementContentList)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_NAME:
                setElementName(ELEMENT_NAME_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__RETURNING_OPTION:
                setReturningOption(RETURNING_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL:
                setNamespacesDecl((XMLNamespacesDeclaration)null);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL:
                setAttributesDecl((XMLAttributesDeclaration)null);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST:
                setElementContentList((XMLValueFunctionElementContentList)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_NAME:
                return ELEMENT_NAME_EDEFAULT == null ? elementName != null : !ELEMENT_NAME_EDEFAULT.equals(elementName);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__RETURNING_OPTION:
                return returningOption != RETURNING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL:
                return namespacesDecl != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL:
                return attributesDecl != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST:
                return elementContentList != null;
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
        result.append(" (elementName: ");
        result.append(elementName);
        result.append(", returningOption: ");
        result.append(returningOption);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionElementImpl
