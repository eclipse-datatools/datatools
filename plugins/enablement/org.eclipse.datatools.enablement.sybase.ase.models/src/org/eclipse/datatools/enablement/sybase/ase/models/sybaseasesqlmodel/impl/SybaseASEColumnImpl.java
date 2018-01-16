/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEColumnImpl.java,v 1.10 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl#getColumnCheck <em>Column Check</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl#getBoundDefault <em>Bound Default</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl#getBoundRule <em>Bound Rule</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl#isMaterialized <em>Materialized</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl#getEncryptionKey <em>Encryption Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl#isBindDefaultInFutureOnly <em>Bind Default In Future Only</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl#isBindRuleInFutureOnly <em>Bind Rule In Future Only</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl#isHidden <em>Hidden</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEColumnImpl extends ColumnImpl implements SybaseASEColumn 
{
	/**
     * The cached value of the '{@link #getColumnCheck() <em>Column Check</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getColumnCheck()
     * @generated
     * @ordered
     */
	protected SybaseASEColumnCheckConstraint columnCheck;

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
     * The default value of the '{@link #isMaterialized() <em>Materialized</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isMaterialized()
     * @generated
     * @ordered
     */
	protected static final boolean MATERIALIZED_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isMaterialized() <em>Materialized</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isMaterialized()
     * @generated
     * @ordered
     */
	protected boolean materialized = MATERIALIZED_EDEFAULT;

	/**
     * The cached value of the '{@link #getEncryptionKey() <em>Encryption Key</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEncryptionKey()
     * @generated
     * @ordered
     */
	protected SybaseASEEncryptionKey encryptionKey;

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
     * The default value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isHidden()
     * @generated
     * @ordered
     */
    protected static final boolean HIDDEN_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isHidden()
     * @generated
     * @ordered
     */
    protected boolean hidden = HIDDEN_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEColumnImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_COLUMN;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEColumnCheckConstraint getColumnCheck() {
        if (columnCheck != null && columnCheck.eIsProxy())
        {
            InternalEObject oldColumnCheck = (InternalEObject)columnCheck;
            columnCheck = (SybaseASEColumnCheckConstraint)eResolveProxy(oldColumnCheck);
            if (columnCheck != oldColumnCheck)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK, oldColumnCheck, columnCheck));
            }
        }
        return columnCheck;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEColumnCheckConstraint basicGetColumnCheck() {
        return columnCheck;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetColumnCheck(SybaseASEColumnCheckConstraint newColumnCheck, NotificationChain msgs) {
        SybaseASEColumnCheckConstraint oldColumnCheck = columnCheck;
        columnCheck = newColumnCheck;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK, oldColumnCheck, newColumnCheck);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setColumnCheck(SybaseASEColumnCheckConstraint newColumnCheck) {
        if (newColumnCheck != columnCheck)
        {
            NotificationChain msgs = null;
            if (columnCheck != null)
                msgs = ((InternalEObject)columnCheck).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN, SybaseASEColumnCheckConstraint.class, msgs);
            if (newColumnCheck != null)
                msgs = ((InternalEObject)newColumnCheck).eInverseAdd(this, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN, SybaseASEColumnCheckConstraint.class, msgs);
            msgs = basicSetColumnCheck(newColumnCheck, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK, newColumnCheck, newColumnCheck));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_DEFAULT, oldBoundDefault, boundDefault));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_DEFAULT, oldBoundDefault, boundDefault));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_RULE, oldBoundRule, boundRule));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_RULE, oldBoundRule, boundRule));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isMaterialized() {
        return materialized;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMaterialized(boolean newMaterialized) {
        boolean oldMaterialized = materialized;
        materialized = newMaterialized;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__MATERIALIZED, oldMaterialized, materialized));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEEncryptionKey getEncryptionKey() {
        if (encryptionKey != null && encryptionKey.eIsProxy())
        {
            InternalEObject oldEncryptionKey = (InternalEObject)encryptionKey;
            encryptionKey = (SybaseASEEncryptionKey)eResolveProxy(oldEncryptionKey);
            if (encryptionKey != oldEncryptionKey)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__ENCRYPTION_KEY, oldEncryptionKey, encryptionKey));
            }
        }
        return encryptionKey;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEEncryptionKey basicGetEncryptionKey() {
        return encryptionKey;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setEncryptionKey(SybaseASEEncryptionKey newEncryptionKey) {
        SybaseASEEncryptionKey oldEncryptionKey = encryptionKey;
        encryptionKey = newEncryptionKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__ENCRYPTION_KEY, oldEncryptionKey, encryptionKey));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_DEFAULT_IN_FUTURE_ONLY, oldBindDefaultInFutureOnly, bindDefaultInFutureOnly));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_RULE_IN_FUTURE_ONLY, oldBindRuleInFutureOnly, bindRuleInFutureOnly));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isHidden() {
        return hidden;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHidden(boolean newHidden) {
        boolean oldHidden = hidden;
        hidden = newHidden;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__HIDDEN, oldHidden, hidden));
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isComputedColumn() {
		return this.getGenerateExpression() != null;
	}

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK:
                if (columnCheck != null)
                    msgs = ((InternalEObject)columnCheck).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN, SybaseASEColumnCheckConstraint.class, msgs);
                return basicSetColumnCheck((SybaseASEColumnCheckConstraint)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK:
                return basicSetColumnCheck(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK:
                if (resolve) return getColumnCheck();
                return basicGetColumnCheck();
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_DEFAULT:
                if (resolve) return getBoundDefault();
                return basicGetBoundDefault();
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_RULE:
                if (resolve) return getBoundRule();
                return basicGetBoundRule();
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__MATERIALIZED:
                return isMaterialized() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__ENCRYPTION_KEY:
                if (resolve) return getEncryptionKey();
                return basicGetEncryptionKey();
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_DEFAULT_IN_FUTURE_ONLY:
                return isBindDefaultInFutureOnly() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_RULE_IN_FUTURE_ONLY:
                return isBindRuleInFutureOnly() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__HIDDEN:
                return isHidden() ? Boolean.TRUE : Boolean.FALSE;
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK:
                setColumnCheck((SybaseASEColumnCheckConstraint)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_DEFAULT:
                setBoundDefault((SybaseASEDefault)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_RULE:
                setBoundRule((SybaseASERule)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__MATERIALIZED:
                setMaterialized(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__ENCRYPTION_KEY:
                setEncryptionKey((SybaseASEEncryptionKey)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_DEFAULT_IN_FUTURE_ONLY:
                setBindDefaultInFutureOnly(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_RULE_IN_FUTURE_ONLY:
                setBindRuleInFutureOnly(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__HIDDEN:
                setHidden(((Boolean)newValue).booleanValue());
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK:
                setColumnCheck((SybaseASEColumnCheckConstraint)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_DEFAULT:
                setBoundDefault((SybaseASEDefault)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_RULE:
                setBoundRule((SybaseASERule)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__MATERIALIZED:
                setMaterialized(MATERIALIZED_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__ENCRYPTION_KEY:
                setEncryptionKey((SybaseASEEncryptionKey)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_DEFAULT_IN_FUTURE_ONLY:
                setBindDefaultInFutureOnly(BIND_DEFAULT_IN_FUTURE_ONLY_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_RULE_IN_FUTURE_ONLY:
                setBindRuleInFutureOnly(BIND_RULE_IN_FUTURE_ONLY_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__HIDDEN:
                setHidden(HIDDEN_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK:
                return columnCheck != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_DEFAULT:
                return boundDefault != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_RULE:
                return boundRule != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__MATERIALIZED:
                return materialized != MATERIALIZED_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__ENCRYPTION_KEY:
                return encryptionKey != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_DEFAULT_IN_FUTURE_ONLY:
                return bindDefaultInFutureOnly != BIND_DEFAULT_IN_FUTURE_ONLY_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BIND_RULE_IN_FUTURE_ONLY:
                return bindRuleInFutureOnly != BIND_RULE_IN_FUTURE_ONLY_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__HIDDEN:
                return hidden != HIDDEN_EDEFAULT;
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
        result.append(" (materialized: "); //$NON-NLS-1$
        result.append(materialized);
        result.append(", bindDefaultInFutureOnly: "); //$NON-NLS-1$
        result.append(bindDefaultInFutureOnly);
        result.append(", bindRuleInFutureOnly: "); //$NON-NLS-1$
        result.append(bindRuleInFutureOnly);
        result.append(", hidden: "); //$NON-NLS-1$
        result.append(hidden);
        result.append(')');
        return result.toString();
    }

} //SybaseASEColumnImpl
