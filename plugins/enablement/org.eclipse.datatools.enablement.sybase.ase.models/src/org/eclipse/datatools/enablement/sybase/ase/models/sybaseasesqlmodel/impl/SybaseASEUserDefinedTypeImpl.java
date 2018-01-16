/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEUserDefinedTypeImpl.java,v 1.7 2007/07/06 08:40:20 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.DistinctUserDefinedTypeImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE User Defined Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl#getBoundDefault <em>Bound Default</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl#getBoundRule <em>Bound Rule</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl#isBindDefaultInFutureOnly <em>Bind Default In Future Only</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl#isBindRuleInFutureOnly <em>Bind Rule In Future Only</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl#isAllowNulls <em>Allow Nulls</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl#isIdentity <em>Identity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEUserDefinedTypeImpl extends DistinctUserDefinedTypeImpl implements SybaseASEUserDefinedType 
{
	/**
     * The cached value of the '{@link #getBoundDefault() <em>Bound Default</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBoundDefault()
     * @generated
     * @ordered
     */
    protected SybaseASEDefault boundDefault;

	/**
     * The cached value of the '{@link #getBoundRule() <em>Bound Rule</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBoundRule()
     * @generated
     * @ordered
     */
    protected SybaseASERule boundRule;

	/**
     * The default value of the '{@link #isBindDefaultInFutureOnly() <em>Bind Default In Future Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isBindDefaultInFutureOnly()
     * @generated
     * @ordered
     */
	protected static final boolean BIND_DEFAULT_IN_FUTURE_ONLY_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isBindDefaultInFutureOnly() <em>Bind Default In Future Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isBindDefaultInFutureOnly()
     * @generated
     * @ordered
     */
	protected boolean bindDefaultInFutureOnly = BIND_DEFAULT_IN_FUTURE_ONLY_EDEFAULT;

	/**
     * The default value of the '{@link #isBindRuleInFutureOnly() <em>Bind Rule In Future Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isBindRuleInFutureOnly()
     * @generated
     * @ordered
     */
	protected static final boolean BIND_RULE_IN_FUTURE_ONLY_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isBindRuleInFutureOnly() <em>Bind Rule In Future Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isBindRuleInFutureOnly()
     * @generated
     * @ordered
     */
	protected boolean bindRuleInFutureOnly = BIND_RULE_IN_FUTURE_ONLY_EDEFAULT;

	/**
     * The default value of the '{@link #isAllowNulls() <em>Allow Nulls</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowNulls()
     * @generated
     * @ordered
     */
	protected static final boolean ALLOW_NULLS_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isAllowNulls() <em>Allow Nulls</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowNulls()
     * @generated
     * @ordered
     */
	protected boolean allowNulls = ALLOW_NULLS_EDEFAULT;

	/**
     * The default value of the '{@link #isIdentity() <em>Identity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIdentity()
     * @generated
     * @ordered
     */
	protected static final boolean IDENTITY_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isIdentity() <em>Identity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIdentity()
     * @generated
     * @ordered
     */
	protected boolean identity = IDENTITY_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEUserDefinedTypeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_USER_DEFINED_TYPE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEDefault getBoundDefault() {
        if (boundDefault != null && boundDefault.eIsProxy())
        {
            InternalEObject oldBoundDefault = (InternalEObject)boundDefault;
            boundDefault = (SybaseASEDefault)eResolveProxy(oldBoundDefault);
            if (boundDefault != oldBoundDefault)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT, oldBoundDefault, boundDefault));
            }
        }
        return boundDefault;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEDefault basicGetBoundDefault() {
        return boundDefault;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBoundDefault(SybaseASEDefault newBoundDefault) {
        SybaseASEDefault oldBoundDefault = boundDefault;
        boundDefault = newBoundDefault;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT, oldBoundDefault, boundDefault));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASERule getBoundRule() {
        if (boundRule != null && boundRule.eIsProxy())
        {
            InternalEObject oldBoundRule = (InternalEObject)boundRule;
            boundRule = (SybaseASERule)eResolveProxy(oldBoundRule);
            if (boundRule != oldBoundRule)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE, oldBoundRule, boundRule));
            }
        }
        return boundRule;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASERule basicGetBoundRule() {
        return boundRule;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBoundRule(SybaseASERule newBoundRule) {
        SybaseASERule oldBoundRule = boundRule;
        boundRule = newBoundRule;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE, oldBoundRule, boundRule));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isBindDefaultInFutureOnly() {
        return bindDefaultInFutureOnly;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setBindDefaultInFutureOnly(boolean newBindDefaultInFutureOnly) {
        boolean oldBindDefaultInFutureOnly = bindDefaultInFutureOnly;
        bindDefaultInFutureOnly = newBindDefaultInFutureOnly;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY, oldBindDefaultInFutureOnly, bindDefaultInFutureOnly));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isBindRuleInFutureOnly() {
        return bindRuleInFutureOnly;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setBindRuleInFutureOnly(boolean newBindRuleInFutureOnly) {
        boolean oldBindRuleInFutureOnly = bindRuleInFutureOnly;
        bindRuleInFutureOnly = newBindRuleInFutureOnly;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY, oldBindRuleInFutureOnly, bindRuleInFutureOnly));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isAllowNulls() {
        return allowNulls;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAllowNulls(boolean newAllowNulls) {
        boolean oldAllowNulls = allowNulls;
        allowNulls = newAllowNulls;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__ALLOW_NULLS, oldAllowNulls, allowNulls));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isIdentity() {
        return identity;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIdentity(boolean newIdentity) {
        boolean oldIdentity = identity;
        identity = newIdentity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__IDENTITY, oldIdentity, identity));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT:
                if (resolve) return getBoundDefault();
                return basicGetBoundDefault();
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE:
                if (resolve) return getBoundRule();
                return basicGetBoundRule();
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY:
                return isBindDefaultInFutureOnly() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY:
                return isBindRuleInFutureOnly() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__ALLOW_NULLS:
                return isAllowNulls() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__IDENTITY:
                return isIdentity() ? Boolean.TRUE : Boolean.FALSE;
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT:
                setBoundDefault((SybaseASEDefault)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE:
                setBoundRule((SybaseASERule)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY:
                setBindDefaultInFutureOnly(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY:
                setBindRuleInFutureOnly(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__ALLOW_NULLS:
                setAllowNulls(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__IDENTITY:
                setIdentity(((Boolean)newValue).booleanValue());
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT:
                setBoundDefault((SybaseASEDefault)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE:
                setBoundRule((SybaseASERule)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY:
                setBindDefaultInFutureOnly(BIND_DEFAULT_IN_FUTURE_ONLY_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY:
                setBindRuleInFutureOnly(BIND_RULE_IN_FUTURE_ONLY_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__ALLOW_NULLS:
                setAllowNulls(ALLOW_NULLS_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__IDENTITY:
                setIdentity(IDENTITY_EDEFAULT);
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT:
                return boundDefault != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE:
                return boundRule != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY:
                return bindDefaultInFutureOnly != BIND_DEFAULT_IN_FUTURE_ONLY_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY:
                return bindRuleInFutureOnly != BIND_RULE_IN_FUTURE_ONLY_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__ALLOW_NULLS:
                return allowNulls != ALLOW_NULLS_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE__IDENTITY:
                return identity != IDENTITY_EDEFAULT;
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
        result.append(" (bindDefaultInFutureOnly: "); //$NON-NLS-1$
        result.append(bindDefaultInFutureOnly);
        result.append(", bindRuleInFutureOnly: "); //$NON-NLS-1$
        result.append(bindRuleInFutureOnly);
        result.append(", allowNulls: "); //$NON-NLS-1$
        result.append(allowNulls);
        result.append(", identity: "); //$NON-NLS-1$
        result.append(identity);
        result.append(')');
        return result.toString();
    }

} //SybaseASEUserDefinedTypeImpl
