/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionPIContentImpl.java,v 1.4 2008/07/07 19:55:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;




import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function PI Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIContentImpl#getValueFunctionPI <em>Value Function PI</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIContentImpl#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionPIContentImpl extends QueryValueExpressionImpl implements XMLValueFunctionPIContent {
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
    protected XMLValueFunctionPIContentImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_PI_CONTENT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionPI getValueFunctionPI() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI) return null;
        return (XMLValueFunctionPI)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueFunctionPI(XMLValueFunctionPI newValueFunctionPI, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueFunctionPI, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueFunctionPI(XMLValueFunctionPI newValueFunctionPI) {
        if (newValueFunctionPI != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI && newValueFunctionPI != null)) {
            if (EcoreUtil.isAncestor(this, newValueFunctionPI))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueFunctionPI != null)
                msgs = ((InternalEObject)newValueFunctionPI).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI__PI_CONTENT, XMLValueFunctionPI.class, msgs);
            msgs = basicSetValueFunctionPI(newValueFunctionPI, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI, newValueFunctionPI, newValueFunctionPI));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR, oldValueExpr, newValueExpr);
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
                msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR, null, msgs);
            if (newValueExpr != null)
                msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR, null, msgs);
            msgs = basicSetValueExpr(newValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR, newValueExpr, newValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueFunctionPI((XMLValueFunctionPI)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI:
                return basicSetValueFunctionPI(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR:
                return basicSetValueExpr(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI__PI_CONTENT, XMLValueFunctionPI.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI:
                return getValueFunctionPI();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR:
                return getValueExpr();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI:
                setValueFunctionPI((XMLValueFunctionPI)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR:
                setValueExpr((QueryValueExpression)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI:
                setValueFunctionPI((XMLValueFunctionPI)null);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR:
                setValueExpr((QueryValueExpression)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI:
                return getValueFunctionPI() != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR:
                return valueExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //XMLValueFunctionPIContentImpl
