package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseColumnLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaFactory;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseColumn extends SybaseASABaseColumnImpl implements ICatalogObject
 {
	
	private static final long serialVersionUID = 5633548650842443769L;
	protected Boolean columnInfoLoaded = Boolean.FALSE;
	protected Boolean constraintInfoLoaded = Boolean.FALSE;
	
	private SoftReference columnLoaderRef;
	
	public Database getCatalogDatabase() {
		return this.getTable().getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (columnInfoLoaded) {
			if(columnInfoLoaded.booleanValue())
			{
				columnInfoLoaded = Boolean.FALSE;
			}
		}
		synchronized (constraintInfoLoaded) {
			if(constraintInfoLoaded.booleanValue())
			{
				constraintInfoLoaded = Boolean.FALSE;
			}
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__UNIQUE:
			isUnique();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__CONTAINED_TYPE:
			getDataType();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__REFERENCED_TYPE:
			getDataType();
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
		}
		return super.eIsSet(eFeature);
	}
	
	public boolean isUnique() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
			{
				getColumnLoader().loadColumnInfo();
				columnInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isUnique();
	}

	public DataType getDataType() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
			{
				getColumnLoader().loadColumnInfo();
				columnInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDataType();
	}

	public boolean isNullable() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
			{
				getColumnLoader().loadColumnInfo();
				columnInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isNullable();
	}
	
	public String getDescription() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
			{
				getColumnLoader().loadColumnInfo();
				columnInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDescription();
	}

	public ValueExpression getGenerateExpression() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
			{
				getColumnLoader().loadColumnInfo();
				columnInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getGenerateExpression();
	}

	public String getDefaultValue() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
			{
				getColumnLoader().loadColumnInfo();
				columnInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDefaultValue();
	}

	public TypeOfDefault getTypeOfDefault() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
			{
				getColumnLoader().loadColumnInfo();
				columnInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getTypeOfDefault();
	}
    
    public IdentitySpecifier getIdentitySpecifier()
    {
        synchronized (columnInfoLoaded) {
            if(!columnInfoLoaded.booleanValue())
            {
                getColumnLoader().loadColumnInfo();
                columnInfoLoaded = Boolean.TRUE;
            }
            boolean identity = (getTypeOfDefault() == TypeOfDefault.SYSTEM_DEFINED_LITERAL && getDefaultValue()
                    .equalsIgnoreCase(SystemDefinedDefaultType.AUTOINCREMENT_LITERAL.getLiteral()));
            super.setIdentitySpecifier(identity ? SQLSchemaFactory.eINSTANCE.createIdentitySpecifier() : null);
        }
        return super.getIdentitySpecifier();
    }
	
	public SybaseASABaseColumnCheckConstraint getColumnConstraint() {
		synchronized (constraintInfoLoaded) {
			getColumnLoader().loadColumnCheckConstraint(super.getColumnConstraint());
			constraintInfoLoaded = Boolean.TRUE;
		}
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
}
