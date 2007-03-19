/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseTableImpl.java,v 1.1 2007/03/05 15:52:15 jgraham Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTableImpl#getDbSpace <em>Db Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseTableImpl extends PersistentTableImpl implements SybaseASABaseTable 
{
    /**
     * The cached value of the '{@link #getDbSpace() <em>Db Space</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDbSpace()
     * @generated
     * @ordered
     */
	protected SybaseASABaseDBSpace dbSpace = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASABaseTableImpl()
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
        return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_TABLE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASABaseDBSpace getDbSpace()
    {
        if (dbSpace != null && dbSpace.eIsProxy())
        {
            InternalEObject oldDbSpace = (InternalEObject)dbSpace;
            dbSpace = (SybaseASABaseDBSpace)eResolveProxy(oldDbSpace);
            if (dbSpace != oldDbSpace)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DB_SPACE, oldDbSpace, dbSpace));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DB_SPACE, oldDbSpace, dbSpace));
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isSystem() {
		String owner = this.getSchema().getName();
		return owner.equals("SYS") || owner.equals("dbo") || owner.equals("rs_systabgroup");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getCheckConstraints() {
		List result = new ArrayList();
		EList tableConstraints = getConstraints();
		for(int i = 0; i<tableConstraints.size(); i++)
		{
			Object constr = tableConstraints.get(i);
			if(constr instanceof CheckConstraint)
			{
				result.add(constr);
			}
		}
		return result;
	}

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DB_SPACE:
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
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DB_SPACE:
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
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DB_SPACE:
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
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DB_SPACE:
                return dbSpace != null;
        }
        return super.eIsSet(featureID);
    }

} //SybaseASABaseTableImpl