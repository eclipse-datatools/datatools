/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionVariableImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Value Expression Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionVariableImpl#getQuerySelect <em>Query Select</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionVariableImpl extends ValueExpressionAtomicImpl implements ValueExpressionVariable {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValueExpressionVariableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.VALUE_EXPRESSION_VARIABLE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySelect getQuerySelect() {
        if (eContainerFeatureID() != SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT) return null;
        return (QuerySelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQuerySelect(QuerySelect newQuerySelect, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuerySelect, SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuerySelect(QuerySelect newQuerySelect) {
        if (newQuerySelect != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT && newQuerySelect != null)) {
            if (EcoreUtil.isAncestor(this, newQuerySelect))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuerySelect != null)
                msgs = ((InternalEObject)newQuerySelect).eInverseAdd(this, SQLQueryModelPackage.QUERY_SELECT__INTO_CLAUSE, QuerySelect.class, msgs);
            msgs = basicSetQuerySelect(newQuerySelect, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT, newQuerySelect, newQuerySelect));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuerySelect((QuerySelect)otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT:
                return basicSetQuerySelect(null, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__INTO_CLAUSE, QuerySelect.class, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT:
                return getQuerySelect();
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT:
                setQuerySelect((QuerySelect)newValue);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT:
                setQuerySelect((QuerySelect)null);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT:
                return getQuerySelect() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLValueExpressionVariableImpl
