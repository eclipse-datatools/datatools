/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEDefaultImpl.java,v 1.6 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Default</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDefaultImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDefaultImpl#getStatement <em>Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEDefaultImpl extends SQLObjectImpl implements SybaseASEDefault 
{
	/**
     * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSchema()
     * @generated
     * @ordered
     */
	protected SybaseASESchema schema;

	/**
     * The default value of the '{@link #getStatement() <em>Statement</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStatement()
     * @generated
     * @ordered
     */
	protected static final String STATEMENT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getStatement() <em>Statement</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStatement()
     * @generated
     * @ordered
     */
	protected String statement = STATEMENT_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEDefaultImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_DEFAULT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASESchema getSchema() {
        if (schema != null && schema.eIsProxy())
        {
            InternalEObject oldSchema = (InternalEObject)schema;
            schema = (SybaseASESchema)eResolveProxy(oldSchema);
            if (schema != oldSchema)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA, oldSchema, schema));
            }
        }
        return schema;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASESchema basicGetSchema() {
        return schema;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSchema(SybaseASESchema newSchema, NotificationChain msgs) {
        SybaseASESchema oldSchema = schema;
        schema = newSchema;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA, oldSchema, newSchema);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSchema(SybaseASESchema newSchema) {
        if (newSchema != schema)
        {
            NotificationChain msgs = null;
            if (schema != null)
                msgs = ((InternalEObject)schema).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS, SybaseASESchema.class, msgs);
            if (newSchema != null)
                msgs = ((InternalEObject)newSchema).eInverseAdd(this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS, SybaseASESchema.class, msgs);
            msgs = basicSetSchema(newSchema, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA, newSchema, newSchema));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getStatement() {
        return statement;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setStatement(String newStatement) {
        String oldStatement = statement;
        statement = newStatement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__STATEMENT, oldStatement, statement));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA:
                if (schema != null)
                    msgs = ((InternalEObject)schema).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS, SybaseASESchema.class, msgs);
                return basicSetSchema((SybaseASESchema)otherEnd, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA:
                return basicSetSchema(null, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA:
                if (resolve) return getSchema();
                return basicGetSchema();
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__STATEMENT:
                return getStatement();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA:
                setSchema((SybaseASESchema)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__STATEMENT:
                setStatement((String)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA:
                setSchema((SybaseASESchema)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__STATEMENT:
                setStatement(STATEMENT_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA:
                return schema != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__STATEMENT:
                return STATEMENT_EDEFAULT == null ? statement != null : !STATEMENT_EDEFAULT.equals(statement);
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
        result.append(" (statement: "); //$NON-NLS-1$
        result.append(statement);
        result.append(')');
        return result.toString();
    }

} //SybaseASEDefaultImpl
