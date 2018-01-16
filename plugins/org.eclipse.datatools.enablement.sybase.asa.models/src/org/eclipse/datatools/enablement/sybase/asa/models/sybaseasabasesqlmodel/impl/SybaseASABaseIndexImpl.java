/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseIndexImpl.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseIndexImpl#getDbSpace <em>Db Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseIndexImpl extends IndexImpl implements SybaseASABaseIndex 
{
    /**
	 * The cached value of the '{@link #getDbSpace() <em>Db Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbSpace()
	 * @generated
	 * @ordered
	 */
	protected SybaseASABaseDBSpace dbSpace;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseIndexImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass()
    {
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_INDEX;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASABaseDBSpace getDbSpace()
    {
		if (dbSpace != null && dbSpace.eIsProxy()) {
			InternalEObject oldDbSpace = (InternalEObject)dbSpace;
			dbSpace = (SybaseASABaseDBSpace)eResolveProxy(oldDbSpace);
			if (dbSpace != oldDbSpace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DB_SPACE, oldDbSpace, dbSpace));
			}
		}
		return dbSpace;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASABaseDBSpace basicGetDbSpace()
    {
		return dbSpace;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDbSpace(SybaseASABaseDBSpace newDbSpace)
    {
		SybaseASABaseDBSpace oldDbSpace = dbSpace;
		dbSpace = newDbSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DB_SPACE, oldDbSpace, dbSpace));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DB_SPACE:
				if (resolve) return getDbSpace();
				return basicGetDbSpace();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DB_SPACE:
				setDbSpace((SybaseASABaseDBSpace)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DB_SPACE:
				setDbSpace((SybaseASABaseDBSpace)null);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DB_SPACE:
				return dbSpace != null;
		}
		return super.eIsSet(featureID);
	}

} //SybaseASABaseIndexImpl