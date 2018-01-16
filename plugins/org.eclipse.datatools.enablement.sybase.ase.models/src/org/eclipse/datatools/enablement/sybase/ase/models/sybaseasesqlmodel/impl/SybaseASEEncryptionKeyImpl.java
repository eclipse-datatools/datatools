/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEEncryptionKeyImpl.java,v 1.6 2007/07/06 08:40:18 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.Schema;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Encryption Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEEncryptionKeyImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEEncryptionKeyImpl extends SQLObjectImpl implements SybaseASEEncryptionKey 
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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEEncryptionKeyImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_ENCRYPTION_KEY;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA, oldSchema, schema));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA, oldSchema, newSchema);
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
                msgs = ((InternalEObject)schema).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS, SybaseASESchema.class, msgs);
            if (newSchema != null)
                msgs = ((InternalEObject)newSchema).eInverseAdd(this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS, SybaseASESchema.class, msgs);
            msgs = basicSetSchema(newSchema, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA, newSchema, newSchema));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA:
                if (schema != null)
                    msgs = ((InternalEObject)schema).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS, SybaseASESchema.class, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA:
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA:
                if (resolve) return getSchema();
                return basicGetSchema();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA:
                setSchema((SybaseASESchema)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA:
                setSchema((SybaseASESchema)null);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA:
                return schema != null;
        }
        return super.eIsSet(featureID);
    }

} //SybaseASEEncryptionKeyImpl
