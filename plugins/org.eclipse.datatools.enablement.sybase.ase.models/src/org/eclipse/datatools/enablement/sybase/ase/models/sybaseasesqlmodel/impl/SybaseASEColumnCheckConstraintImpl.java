/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEColumnCheckConstraintImpl.java,v 1.8 2007/07/06 08:40:20 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Column Check Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnCheckConstraintImpl#getColumn <em>Column</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEColumnCheckConstraintImpl extends SybaseASECheckConstraintImpl implements SybaseASEColumnCheckConstraint 
{
	/**
     * The cached value of the '{@link #getColumn() <em>Column</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumn()
     * @generated
     * @ordered
     */
    protected SybaseASEColumn column;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEColumnCheckConstraintImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEColumn getColumn() {
        if (column != null && column.eIsProxy())
        {
            InternalEObject oldColumn = (InternalEObject)column;
            column = (SybaseASEColumn)eResolveProxy(oldColumn);
            if (column != oldColumn)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN, oldColumn, column));
            }
        }
        return column;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEColumn basicGetColumn() {
        return column;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetColumn(SybaseASEColumn newColumn, NotificationChain msgs) {
        SybaseASEColumn oldColumn = column;
        column = newColumn;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN, oldColumn, newColumn);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setColumn(SybaseASEColumn newColumn) {
        if (newColumn != column)
        {
            NotificationChain msgs = null;
            if (column != null)
                msgs = ((InternalEObject)column).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK, SybaseASEColumn.class, msgs);
            if (newColumn != null)
                msgs = ((InternalEObject)newColumn).eInverseAdd(this, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK, SybaseASEColumn.class, msgs);
            msgs = basicSetColumn(newColumn, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN, newColumn, newColumn));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN:
                if (column != null)
                    msgs = ((InternalEObject)column).eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK, SybaseASEColumn.class, msgs);
                return basicSetColumn((SybaseASEColumn)otherEnd, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN:
                return basicSetColumn(null, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN:
                if (resolve) return getColumn();
                return basicGetColumn();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN:
                setColumn((SybaseASEColumn)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN:
                setColumn((SybaseASEColumn)null);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN:
                return column != null;
        }
        return super.eIsSet(featureID);
    }

} //SybaseASEColumnCheckConstraintImpl
