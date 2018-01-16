/**
 * <copyright> </copyright>
 * 
 * $Id: SybaseASABaseSchemaImpl.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Sybase ASA Base Schema</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseSchemaImpl extends SchemaImpl implements SybaseASABaseSchema
{
    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected SybaseASABaseSchemaImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass()
    {
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_SCHEMA;
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public List getNormalTables()
    {
        List result = new ArrayList();
        List tableList = getTables();
        for (int i = 0; i < tableList.size(); i++)
        {
            SQLObject table = (SQLObject) tableList.get(i);
            if ((table instanceof SybaseASABaseTable && !(table instanceof SybaseASABaseProxyTable))
                    && !((SybaseASABaseTable) table).isSystem())
            {
                result.add(table);
            }
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public List getTempTables()
    {
        List result = new ArrayList();
        List tableList = getTables();
        for (int i = 0; i < tableList.size(); i++)
        {
            SQLObject table = (SQLObject) tableList.get(i);
            if (table instanceof SybaseASABaseTempTable)
            {
                result.add(table);
            }
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public List getSystemTables()
    {
        List result = new ArrayList();
        List tableList = getTables();
        for (int i = 0; i < tableList.size(); i++)
        {
            SQLObject table = (SQLObject) tableList.get(i);
            if ((table instanceof SybaseASABaseTable) && (((SybaseASABaseTable) table).isSystem()))
            {
                result.add(table);
            }
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public List getProxyTables()
    {
        List result = new ArrayList();
        List tableList = getTables();
        for (int i = 0; i < tableList.size(); i++)
        {
            SQLObject table = (SQLObject) tableList.get(i);
            if (table instanceof SybaseASABaseProxyTable)
            {
                result.add(table);
            }
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public List getViewTables(boolean systemFlag)
    {
        List result = new ArrayList();
        List tableList = getTables();
        for (int i = 0; i < tableList.size(); i++)
        {
            SQLObject table = (SQLObject) tableList.get(i);
            if (table instanceof SybaseASABaseViewTable)
            {
                if (systemFlag)
                {
                    // get all views if systemFlag is true
                    result.add(table);
                }
                else
                {
                    // get non-system views if systemFlag is false
                    if (!((SybaseASABaseViewTable) table).isSystem())
                    {
                        result.add(table);
                    }
                }
            }
        }
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public List getSystemAndNormalTables()
    {
        List result = new ArrayList();
        List tableList = getTables();
        for (int i = 0; i < tableList.size(); i++)
        {
            SQLObject table = (SQLObject) tableList.get(i);
            if ((table instanceof SybaseASABaseTable && !(table instanceof SybaseASABaseProxyTable)))
            {
                result.add(table);
            }
        }
        return result;
    }

    public EList getSuperIndices()
    {
        return super.getIndices();
    }

    public EList getSuperTriggers()
    {
        return super.getTriggers();
    }

} // SybaseASABaseSchemaImpl
