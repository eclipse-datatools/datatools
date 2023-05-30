/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLAttributeDeclarationItemImpl.java,v 1.4 2008/07/07 19:55:14 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;




import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Attribute Declaration Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributeDeclarationItemImpl#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributeDeclarationItemImpl#getAttributesDecl <em>Attributes Decl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLAttributeDeclarationItemImpl extends QueryValueExpressionImpl implements XMLAttributeDeclarationItem {
	/**
     * The cached value of the '{@link #getValueExpr() <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression valueExpr;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLAttributeDeclarationItemImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_ATTRIBUTE_DECLARATION_ITEM;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getValueExpr() {
        return valueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValueExpr(QueryValueExpression newValueExpr, NotificationChain msgs) {
        QueryValueExpression oldValueExpr = valueExpr;
        valueExpr = newValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR, oldValueExpr, newValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExpr(QueryValueExpression newValueExpr) {
        if (newValueExpr != valueExpr) {
            NotificationChain msgs = null;
            if (valueExpr != null)
                msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR, null, msgs);
            if (newValueExpr != null)
                msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR, null, msgs);
            msgs = basicSetValueExpr(newValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR, newValueExpr, newValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLAttributesDeclaration getAttributesDecl() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL) return null;
        return (XMLAttributesDeclaration)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetAttributesDecl(XMLAttributesDeclaration newAttributesDecl, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newAttributesDecl, SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAttributesDecl(XMLAttributesDeclaration newAttributesDecl) {
        if (newAttributesDecl != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL && newAttributesDecl != null)) {
            if (EcoreUtil.isAncestor(this, newAttributesDecl))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newAttributesDecl != null)
                msgs = ((InternalEObject)newAttributesDecl).eInverseAdd(this, SQLXMLQueryModelPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM, XMLAttributesDeclaration.class, msgs);
            msgs = basicSetAttributesDecl(newAttributesDecl, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL, newAttributesDecl, newAttributesDecl));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetAttributesDecl((XMLAttributesDeclaration)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR:
                return basicSetValueExpr(null, msgs);
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL:
                return basicSetAttributesDecl(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM, XMLAttributesDeclaration.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR:
                return getValueExpr();
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL:
                return getAttributesDecl();
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
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR:
                setValueExpr((QueryValueExpression)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL:
                setAttributesDecl((XMLAttributesDeclaration)newValue);
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
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR:
                setValueExpr((QueryValueExpression)null);
                return;
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL:
                setAttributesDecl((XMLAttributesDeclaration)null);
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
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR:
                return valueExpr != null;
            case SQLXMLQueryModelPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL:
                return getAttributesDecl() != null;
        }
        return super.eIsSet(featureID);
    }

} //XMLAttributeDeclarationItemImpl
