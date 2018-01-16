/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASESchemaImpl.java,v 1.12 2007/07/06 08:40:20 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETempTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESchemaImpl#getDefaults <em>Defaults</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESchemaImpl#getRules <em>Rules</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESchemaImpl#getEncryptionKeys <em>Encryption Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASESchemaImpl extends SchemaImpl implements SybaseASESchema 
{
	/**
     * The cached value of the '{@link #getDefaults() <em>Defaults</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaults()
     * @generated
     * @ordered
     */
	protected EList defaults;

	/**
     * The cached value of the '{@link #getRules() <em>Rules</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRules()
     * @generated
     * @ordered
     */
	protected EList rules;

	/**
     * The cached value of the '{@link #getEncryptionKeys() <em>Encryption Keys</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEncryptionKeys()
     * @generated
     * @ordered
     */
	protected EList encryptionKeys;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASESchemaImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_SCHEMA;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDefaults() {
        if (defaults == null)
        {
            defaults = new EObjectWithInverseResolvingEList(SybaseASEDefault.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS, SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__SCHEMA);
        }
        return defaults;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getRules() {
        if (rules == null)
        {
            rules = new EObjectWithInverseResolvingEList(SybaseASERule.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES, SybaseasesqlmodelPackage.SYBASE_ASE_RULE__SCHEMA);
        }
        return rules;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getEncryptionKeys() {
        if (encryptionKeys == null)
        {
            encryptionKeys = new EObjectWithInverseResolvingEList(SybaseASEEncryptionKey.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS, SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY__SCHEMA);
        }
        return encryptionKeys;
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getWebServicesAsTables() {
		List result = new java.util.ArrayList();
		List tableList = this.getTables();
		for(int i = 0; i<tableList.size(); i++)
		{
			SQLObject table = (SQLObject)tableList.get(i);
			if(table instanceof SybaseASEWebServiceTable)
			{
//				TableType type = ((SybaseASETable)table).getTableType();
				result.add(table);
			}
		}
		return result;
	}

    public List getSystemProcedures(){
        List result = new ArrayList();
        List procedureList = getProcedures();
        for(int i = 0;i<procedureList.size();i++)
        {
            SQLObject procedure = (SQLObject)procedureList.get(i);
            if((procedure instanceof SybaseASEProcedure) && ((SybaseASEProcedure)procedure).isSystem()){
                result.add(procedure);
            }
        }
        return result;
    }
    
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getNormalTables(){
		List result = new ArrayList();
		List tableList = getTables();
		for(int i = 0;i<tableList.size();i++)
		{
			SQLObject table = (SQLObject)tableList.get(i);
			if((table instanceof SybaseASETable )
					&& !(table instanceof SybaseASEProxyTable) && !((SybaseASETable)table).isSystem()){
				result.add(table);
			}
			else if (table instanceof SybaseASETempTable)
			{
			    result.add(table);
			}
		}
		return result;
	}
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public List getSystemTables(){
        List result = new ArrayList();
        List tableList = getTables();
        for(int i = 0;i<tableList.size();i++)
        {
            SQLObject table = (SQLObject)tableList.get(i);
            if((table instanceof SybaseASETable) && ((SybaseASETable)table).isSystem()){
                result.add(table);
            }
        }
        return result;
    }
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public List getSystemAndNormalTable()
    {
        List result = new ArrayList();
        List tableList = getTables();
        for(int i = 0;i<tableList.size();i++)
        {
            SQLObject table = (SQLObject)tableList.get(i);
            if((table instanceof SybaseASETable || table instanceof SybaseASETempTable)
                    && !(table instanceof SybaseASEProxyTable)){
                result.add(table);
            }
        }
        return result;        
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getProxyTables() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		List result = new ArrayList();
		List tableList = getTables();
		for(int i=0;i<tableList.size();i++)
		{
			SQLObject table = (SQLObject)tableList.get(i);
			if(table instanceof SybaseASEProxyTable &&!(table instanceof SybaseASEWebServiceTable)){
				result.add(table);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getViewTables() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		List result = new ArrayList();
		List tableList = getTables();
		for(int i=0;i<tableList.size();i++)
		{
			SQLObject table = (SQLObject)tableList.get(i);
			if(table instanceof SybaseViewTable){
				result.add(table);
			}
		}
		return result;
	}

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS:
                return ((InternalEList)getDefaults()).basicAdd(otherEnd, msgs);
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES:
                return ((InternalEList)getRules()).basicAdd(otherEnd, msgs);
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS:
                return ((InternalEList)getEncryptionKeys()).basicAdd(otherEnd, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS:
                return ((InternalEList)getDefaults()).basicRemove(otherEnd, msgs);
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES:
                return ((InternalEList)getRules()).basicRemove(otherEnd, msgs);
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS:
                return ((InternalEList)getEncryptionKeys()).basicRemove(otherEnd, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS:
                return getDefaults();
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES:
                return getRules();
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS:
                return getEncryptionKeys();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS:
                getDefaults().clear();
                getDefaults().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES:
                getRules().clear();
                getRules().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS:
                getEncryptionKeys().clear();
                getEncryptionKeys().addAll((Collection)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS:
                getDefaults().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES:
                getRules().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS:
                getEncryptionKeys().clear();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS:
                return defaults != null && !defaults.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES:
                return rules != null && !rules.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS:
                return encryptionKeys != null && !encryptionKeys.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    public Database getDatabase()
    {
        Catalog catalog = this.getCatalog();
        if (catalog == null)
            return null;
        else
            return catalog.getDatabase();
    }

} //SybaseASESchemaImpl
