package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.AuthorizedObjectPrivilegeASALoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseColumnLoader;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseColumn extends SybaseASABaseColumnImpl implements ICatalogObject, IAdaptable
 {
	
	private static final long serialVersionUID = 5633548650842443769L;
	
	protected static final int columnLoadInfoIndex = 0;
	protected static final int constraintLoadInfoIndex = 1;
	protected static final int privilegesLoadInfoIndex = 2;

    private SoftReference authIdLoaderRef;
	private SoftReference columnLoaderRef;
	
	public Database getCatalogDatabase() {
		return this.getTable().getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}
	
	public void refresh() 
	{
	    ISybaseASACatalogBaseColumnOwner table = (ISybaseASACatalogBaseColumnOwner)getTable();
	    if(isNeedRefresh())
	    {
	        synchronized (table.isColumnInfoLoaded()) 
	        {
	            table.setColumnInfoLoaded(Boolean.FALSE);
            }
	        synchronized (table.isColumnConstraintLoaded()) 
            {
                table.setColumnConstraintLoaded(Boolean.FALSE);
            }
	        synchronized (table.isColumnPrivilegeLoaded()) 
            {
                table.setColumnPrivilegeLoaded(Boolean.FALSE);
            }
	        RefreshManager.getInstance().referesh(this);
	    }
	    
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__UNIQUE:
			isUnique();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__CONTAINED_TYPE:
			getContainedType();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__REFERENCED_TYPE:
			getReferencedType();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__NULLABLE:
			isNullable();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__DESCRIPTION:
			getDescription();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__GENERATE_EXPRESSION:
			getGenerateExpression();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__DEFAULT_VALUE:
			getDefaultValue();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT:
			getTypeOfDefault();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT:
			getColumnConstraint();
			break;
        case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__PRIVILEGES:
            getPrivileges();
            break;
		}
		return super.eIsSet(eFeature);
	}
	
	public boolean isUnique() {
	    Boolean columnInfoLoaded = columnInfoLoaded();
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
			{
				getColumnLoader().loadColumnInfo();
				columnInfoLoaded = Boolean.TRUE;
			}
		}
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
		return super.isUnique();
	}
	
	public SQLDataType getContainedType()
	{
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
	    return super.getContainedType();
	}
	
	public UserDefinedType getReferencedType()
	{
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
	    return super.getReferencedType();
	}
	
	public DataType getDataType()
	{
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
	    return super.getDataType();
	}

	public boolean isNullable() {
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
		return super.isNullable();
	}
	
	public String getDescription() {
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
		return super.getDescription();
	}

	public ValueExpression getGenerateExpression() {
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
		return super.getGenerateExpression();
	}

	public String getDefaultValue() {
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
		return super.getDefaultValue();
	}

	public TypeOfDefault getTypeOfDefault() {
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
		return super.getTypeOfDefault();
	}
    
    public IdentitySpecifier getIdentitySpecifier()
    {
        Boolean columnInfoLoaded = columnInfoLoaded();
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(columnLoadInfoIndex, columnInfoLoaded);
        boolean identity = (getTypeOfDefault() == TypeOfDefault.SYSTEM_DEFINED_LITERAL && getDefaultValue()
                .equalsIgnoreCase(SystemDefinedDefaultType.AUTOINCREMENT_LITERAL.getLiteral()));
        setIdentitySpecifier(identity ? SQLSchemaFactory.eINSTANCE.createIdentitySpecifier() : null);
        return super.getIdentitySpecifier();
    }
	
	public EList getColumnConstraint() {
        Boolean constraintInfoLoaded = constraintInfoLoaded();
        synchronized (constraintInfoLoaded) {
            if(!constraintInfoLoaded.booleanValue())
            {
                HashMap existingConstraints = new HashMap();
                for (Iterator it = getTable().getColumns().iterator(); it.hasNext();)
                {
                    SybaseASACatalogBaseColumn column = (SybaseASACatalogBaseColumn) it.next();
                    existingConstraints.put(column.getName(), column.superGetColumnConstraint());
                }
                HashMap constraints = getColumnLoader().loadColumnCheckConstraint(existingConstraints);
                List columns = getTable().getColumns();
                for(int i = 0; i<columns.size(); i++)
                {
                    SybaseASACatalogBaseColumn column = (SybaseASACatalogBaseColumn)columns.get(i);
                    String columnName = column.getName();
                    List constraintList = (List)constraints.get(columnName);
                    
                    if (constraintList == null)
                    {
                        constraintList = new ArrayList();
                    }
                    boolean deliver = column.eDeliver();
                    column.eSetDeliver(false);
                    EList columnConstraints = column.superGetColumnConstraint();
                    columnConstraints.clear();
                    columnConstraints.addAll(constraintList);
                    
                    column.eSetDeliver(deliver);
                }
                constraintInfoLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(constraintLoadInfoIndex, constraintInfoLoaded);
		return super.getColumnConstraint();
	}

    protected EList superGetColumnConstraint()
    {
        return super.getColumnConstraint();
    }
    
	private SybaseASABaseColumnLoader getColumnLoader()
	{
		SybaseASABaseColumnLoader loader = columnLoaderRef == null ? null
				: (SybaseASABaseColumnLoader) columnLoaderRef.get();
		
		if(loader == null)
		{
			loader = createColumnLoader();
			columnLoaderRef = new SoftReference(loader);
		}
		
		return loader;
	}
	
	private SybaseASABaseColumnLoader createColumnLoader()
	{
		return new SybaseASABaseColumnLoader(this);
	}

    public EList getPrivileges()
    {
        Boolean privilegesLoaded = privilegesInfoLoaded();
        synchronized (privilegesLoaded) {
            if(!privilegesLoaded.booleanValue())
            {
                HashMap privileges = getPrivilegeLoader().loadTableColumnPrivilege();

                List columns = getTable().getColumns();
                for(int i = 0; i<columns.size(); i++)
                {
                    SybaseASACatalogBaseColumn column = (SybaseASACatalogBaseColumn)columns.get(i);
                    String columnName = column.getName();
                    List privilegeList = (List)privileges.get(columnName);
                    
                    if (privilegeList == null)
                    {
                    	privilegeList = new ArrayList();
                    }
                    boolean deliver = column.eDeliver();
                    column.eSetDeliver(false);
                    EList columnPrivileges = column.superGetPrivileges();
                    columnPrivileges.clear();
                    columnPrivileges.addAll(privilegeList);
                    
                    column.eSetDeliver(deliver);
                }
                privilegesLoaded = Boolean.TRUE;
            }
        }
        setColumnLoadInfo(privilegesLoadInfoIndex, privilegesLoaded);
        return super.getPrivileges();
    }

    protected EList superGetPrivileges()
    {
        return super.getPrivileges();
    }
    
    private AuthorizedObjectPrivilegeASALoader getPrivilegeLoader()
    {
        AuthorizedObjectPrivilegeASALoader loader = authIdLoaderRef == null ? null
                : (AuthorizedObjectPrivilegeASALoader) authIdLoaderRef.get();
        
        if(loader == null)
        {
            loader = createPrivilegeLoader();
            authIdLoaderRef = new SoftReference(loader);
        }
        
        return loader;
    }
    
    private AuthorizedObjectPrivilegeASALoader createPrivilegeLoader()
    {
        return new AuthorizedObjectPrivilegeASALoader(this);
    }

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
	protected Boolean columnInfoLoaded()
	{
	    return getColumnLoadInfo(columnLoadInfoIndex);
	}
	
	protected Boolean constraintInfoLoaded()
	{
	    return getColumnLoadInfo(constraintLoadInfoIndex);
	}
	
	protected Boolean privilegesInfoLoaded()
	{
	    return getColumnLoadInfo(privilegesLoadInfoIndex);
	}
	
	protected Boolean getColumnLoadInfo(int index)
	{
	    ISybaseASACatalogBaseColumnOwner table = (ISybaseASACatalogBaseColumnOwner)getTable(); 
	    switch(index)
	    {
	        case columnLoadInfoIndex:
	            return table.isColumnInfoLoaded();
	        case constraintLoadInfoIndex:
	            return table.isColumnConstraintLoaded();
	        case privilegesLoadInfoIndex:
	            return table.isColumnPrivilegeLoaded();
	        default:
	            return Boolean.FALSE;
	    }
	}
	
	protected void setColumnLoadInfo(int index, Boolean info)
	{
	    ISybaseASACatalogBaseColumnOwner table = (ISybaseASACatalogBaseColumnOwner)getTable();
	    switch(index)
        {
            case columnLoadInfoIndex:
                table.setColumnInfoLoaded(info);
                break;
            case constraintLoadInfoIndex:
                table.setColumnConstraintLoaded(info);
                break;
            case privilegesLoadInfoIndex:
                table.setColumnPrivilegeLoaded(info);
                break;
        }
	}
	
	private boolean isNeedRefresh()
    {
	    ISybaseASACatalogBaseColumnOwner table = (ISybaseASACatalogBaseColumnOwner)getTable();
        if(table.isColumnConstraintLoaded().booleanValue() || table.isColumnInfoLoaded().booleanValue() || table.isColumnPrivilegeLoaded().booleanValue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	
	static public interface ISybaseASACatalogBaseColumnOwner
	{
	    public Boolean isColumnInfoLoaded();
	    public void setColumnInfoLoaded(Boolean loaded);
	    
	    public Boolean isColumnConstraintLoaded();
	    public void setColumnConstraintLoaded(Boolean loaded);
	    
	    public Boolean isColumnPrivilegeLoaded();
	    public void setColumnPrivilegeLoaded(Boolean loaded);
	}
 }
