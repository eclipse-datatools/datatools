/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlParser;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelFactoryImpl;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpressionDefault;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaFactoryImpl;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SybaseASECatalogColumn extends SybaseASEColumnImpl implements ICatalogObject,IAdaptable{
	private static final long serialVersionUID = 3257570602843189304L;
	
    private static final int    COL_STATUS2_COMPUTED_COL              = 0x0010;
    private static final int    COL_STATUS2_COMPUTED_MATERIALIZED_COL = 0x0020;
    private static final int    COL_STATUS2_COMPUTED_VIEW_COL         = 0x0030;
    private static final int    COL_STATUS3_HIDDEN_COMPUTED_COL       = 0x0001;
	private static final String ATTR_COL_TYPE                         = "type";
    private static final String ATTR_COL_SCALE                        = "scale";
    private static final String ATTR_COL_LENGTH                       = "col_length";
    private static final String ATTR_COL_STATUS                       = "status";
    private static final String ATTR_CHAR_LENGTH                      = "char_length";    // char length for UTF
    private static final String ATTR_COL_PRECISION                    = "prec";
    private static final String ATTR_COL_USERTYPE_ID                  = "type_id";
    private static final String ATTR_COL_OFFLINE_RULE                 = "offline_rule";   // External Bindings
    private static final String ATTR_COL_OBJECT_STORAGE               = "object_storage"; // in row, off row, etc
    private static final String ATTR_COL_OFFLINE_DEFAULT              = "offline_default"; // External Bindings
    private static final String ATTR_COL_INLINE_DEFAULT_ID            = "inline_default";
    private static final String ATTR_COL_INLINE_RULE_ID               = "inline_rule";
    private static final String ATTR_COL_STATUS2                      = "status2";        // status 2 for computed
    // columns
    private static final String ATTR_COL_ENCRYPT_KEY                  = "encrkey_id";
    private static final String ATTR_COL_ENCRYPT_KEY_DB               = "encrdb";
	
	public void refresh() {
		synchronized (inlineDefaultLoaded) {
			if(inlineDefaultLoaded.booleanValue())
			{
				inlineDefaultLoaded = Boolean.FALSE;
			}
		}
		synchronized (boundDefaultLoaded) {
			if(boundDefaultLoaded.booleanValue())
			{
				boundDefaultLoaded = Boolean.FALSE;
			}
		}
		synchronized (valueExpressionLoaded) {
			if(valueExpressionLoaded.booleanValue())
			{
				valueExpressionLoaded = Boolean.FALSE;
			}
		}
		synchronized (columnCheckLoaded) {
			if(columnCheckLoaded.booleanValue())
			{
				columnCheckLoaded = Boolean.FALSE;
			}
		}
		synchronized (bindedRuleLoaded) {
			if(bindedRuleLoaded.booleanValue())
			{
				bindedRuleLoaded = Boolean.FALSE;
			}
		}
		synchronized (columnTypeLoaded) {
			if(columnTypeLoaded.booleanValue())
				columnTypeLoaded = Boolean.FALSE;
		}
		synchronized (columnInfoLoaded) {
			if(columnInfoLoaded.booleanValue())
				columnInfoLoaded = Boolean.FALSE;
		}
		synchronized (encryptionKeyLoaded) {
			if(encryptionKeyLoaded.booleanValue())
				encryptionKeyLoaded = Boolean.FALSE;
		}
		RefreshManager.getInstance().referesh(this);
	}

	public Connection getConnection() {
		SybaseASECatalogSchema schema = (SybaseASECatalogSchema)this.getTable().getSchema();
		return schema.getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getTable().getSchema().getCatalog().getDatabase();		
	}	
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
			case SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER:
				this.getIdentitySpecifier();
				break;
			case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__DEFAULT_VALUE:
				this.getDefaultValue();
				break;
			case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_DEFAULT:
				this.getBoundDefault();
				break;
			case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_RULE:
				this.getBoundRule();
				break;
			case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__GENERATE_EXPRESSION:
				this.getGenerateExpression();
				break;
			case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__COLUMN_CHECK:
				this.getColumnCheck();
				break;
			case SQLTablesPackage.COLUMN__NULLABLE:
				this.isNullable();
				break;
			case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__ENCRYPTION_KEY:
				this.getEncryptionKey();
				break;
			case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__MATERIALIZED:
				this.isMaterialized();
				break;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__CONTAINED_TYPE:
                this.getContainedType();
                break;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__REFERENCED_TYPE:
                this.getReferencedType();
                break;
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__PRIVILEGES:
                getPrivileges();
                break;
		}
		
		return super.eIsSet(eFeature);
	}
	
    public SQLDataType getContainedType(){
        synchronized (columnTypeLoaded) {
            if(!columnTypeLoaded.booleanValue())
                loadColumnType();
        }
        return super.getContainedType();
    }
    
    public UserDefinedType getReferencedType()
    {
        synchronized (columnTypeLoaded) {
            if(!columnTypeLoaded.booleanValue())
                loadColumnType();
        }
        return super.getReferencedType();
    }
    
    public DataType getDataType()
    {
        synchronized (columnTypeLoaded) {
            if(!columnTypeLoaded.booleanValue())
            {
                loadColumnType();
            }
        }
        return super.getDataType();
    }
	

    public boolean isHidden()
    {
        synchronized (columnInfoLoaded)
        {
            if(!columnInfoLoaded.booleanValue())
                loadColumnInfo();
        }
        return super.isHidden();
    }

    private void loadColumnType() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try
		{
            conn = this.getConnection();
			Table table  = getTable();
			Schema schema = table.getSchema();
			
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(ASESQLs.QUERY_COLUMN_DATATYPE);
			stmt.setString(1, schema.getName());
			stmt.setString(2, table.getName());
            stmt.setString(3, this.getName());
            rs = stmt.executeQuery();
            while(rs.next())
            {
            	String typeName = rs.getString(ATTR_COL_TYPE);
                int typeID = rs.getInt(ATTR_COL_USERTYPE_ID);
                boolean isUDT = typeID <= 100 ? false : true;

                if (!isUDT)
                {
                    int iCharSize = rs.getInt(ATTR_CHAR_LENGTH);
                    int length = rs.getInt(ATTR_COL_LENGTH);
                    int precision = rs.getInt(ATTR_COL_PRECISION);
                    int scale = rs.getInt(ATTR_COL_SCALE);
                    DatabaseDefinition dbDef = RDBCorePlugin.getDefault()
							.getDatabaseDefinitionRegistry().getDefinition(
									getCatalogDatabase());
                    PredefinedDataType type = SybaseASECatalogUtils
							.getASEPredefinedType(length, precision, scale,
									iCharSize, typeName, dbDef);
                    super.setDataType(type);
                }
                else // handle UDT
                {
                    try
                    {
                        DistinctUserDefinedType udt = null;
                        String ownerName = SybaseASECatalogUtils.getUDTOwner(typeName, this.getTable().getSchema().getCatalog().getName(),
                                conn);
                        Schema udtSchema = (Schema)ASEUtil.getSQLObject(this.getTable().getSchema().getCatalog().getSchemas(), ownerName);
                        udt = (DistinctUserDefinedType)ASEUtil.getSQLObject(udtSchema.getUserDefinedTypes(), typeName);
                        super.setDataType(udt);
                    }
                    catch (Exception e)
                    {
                        // ingnore
                    }
                }
            }
		}
		catch(SQLException e)
		{
			JDBCASEPlugin.getDefault().log(e);
		}
        catch(Exception ex)
        {
            
        }
		finally
		{
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}
		
		this.eSetDeliver(deliver);
		this.columnTypeLoaded = Boolean.TRUE;
	}

	//ASE column's defaultValue attribute will hold either inline default 
	//or bound default value. Judge boundDefault is null or not to determine
	//the defaultValue's holder(inline or bound defualt).
	public String getDefaultValue() {
		synchronized (inlineDefaultLoaded) {
			if(!inlineDefaultLoaded.booleanValue())
			{
				loadInlineDefault();
				loadBoundDefault();
			}
		}
		return super.getDefaultValue();
	}

	private void loadInlineDefault() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		super.setDefaultValue(null);
		
        String inlineDefaultString = getInlineDefault(this.getConnection());
        if(inlineDefaultString != null)
        {
//            String inlineDefault = getDefaultValue(inlineDefaultString);
            super.setDefaultValue(new SybaseASEDdlParser().parseInlineDefault(inlineDefaultString));
        }
	    
		this.eSetDeliver(deliver);
		this.inlineDefaultLoaded = Boolean.TRUE;
	}

	private String getInlineDefault(Connection conn)
    {
        String inlineDefault = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_INLINE_DEFAULT);
            stmt.setInt(1, ((ICatalogTable)this.getTable()).getTableId());
            stmt.setString(2, this.getName());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                inlineDefault = rs.getString(1).trim();
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }

        return inlineDefault;
    }
	
	public SybaseASEDefault getBoundDefault() {
		synchronized (boundDefaultLoaded) {
			if(!boundDefaultLoaded.booleanValue())
				loadBoundDefault();
		}
		return super.getBoundDefault();
	}
	
	private void loadBoundDefault() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try
		{
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(ASESQLs.QUERY_COLUMN_BINDED_DEFAULT);
			stmt.setInt(1, ((ICatalogTable)this.getTable()).getTableId());
            stmt.setString(2, this.getName());
            rs = stmt.executeQuery();
            SybaseASEDefault offlineDefault = null;
            while(rs.next())
            {
            	String ownerName = rs.getString(1);
                String defaultName = rs.getString(2);
                SybaseASESchema schema = (SybaseASESchema) ASEUtil.getSQLObject(this.getTable().getSchema().getCatalog()
                        .getSchemas(), ownerName);
                offlineDefault = (SybaseASEDefault) ASEUtil.getSQLObject(schema.getDefaults(),
                        defaultName);
            }
            super.setBoundDefault(offlineDefault);
            if(offlineDefault != null)
            {
            	String statement = offlineDefault.getStatement();
            	super.setDefaultValue(new SybaseASEDdlParser().parseDefaultRuleStatement(statement));
            }
		}
		catch(SQLException e)
		{
			JDBCASEPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}
		
        
        this.eSetDeliver(deliver);
        boundDefaultLoaded = Boolean.TRUE;
	}

	public ValueExpression getGenerateExpression() {
		synchronized (valueExpressionLoaded) {
			if(!valueExpressionLoaded.booleanValue())
			{
                try
                {
                    loadValueExpression();   
                }
                catch (Exception e)
                {
                }
			}
		}
		return super.getGenerateExpression();
	}
	
	private void loadValueExpression() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		SybaseASEDatabase database = (SybaseASEDatabase)this.getCatalogDatabase();
		ValueExpressionDefault expression = null;
		if(database.isComputedColApplicable())
		{
			String computedColExpr = getComputedColExpression(this.getName(), this.getConnection());
			if(computedColExpr != null && !computedColExpr.equals(""))
			{
				DatabaseDefinition dbDef = RDBCorePlugin.getDefault()
						.getDatabaseDefinitionRegistry().getDefinition(
								this.getCatalogDatabase());
				DataModelElementFactory factory = dbDef.getDataModelElementFactory();
				
		        expression = (ValueExpressionDefault) factory
		                .create(SQLExpressionsPackage.eINSTANCE.getValueExpressionDefault());
		        expression.setSQL(computedColExpr);
			}
		}
		super.setGenerateExpression(expression);
        
        this.eSetDeliver(deliver);
        valueExpressionLoaded = Boolean.TRUE;
	}

	public SybaseASEColumnCheckConstraint getColumnCheck() {
		synchronized (columnCheckLoaded) {
			if(!columnCheckLoaded.booleanValue())
				loadColumncheckConstraint();
		} 
		return super.getColumnCheck();
	}
	
	private void loadColumncheckConstraint() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		SybaseASEColumnCheckConstraint inlineRule = null;
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.INLINE_CHECKCONSTRAINT_OF_COLUMN);
            stmt.setInt(1, ((ICatalogTable)this.getTable()).getTableId());
            stmt.setString(2, this.getName());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String checkName = rs.getString(1);
                String inlineRuleExpr = rs.getString(2);

                DatabaseDefinition dbDef = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(
                        this.getCatalogDatabase());
                DataModelElementFactory factory = dbDef.getDataModelElementFactory();

                SearchConditionDefault sc = (SearchConditionDefault) factory.create(SQLExpressionsPackage.eINSTANCE
                        .getSearchConditionDefault());
                sc.setSQL(SQLUtil.parseSearchStatement(inlineRuleExpr));
                inlineRule = (SybaseASEColumnCheckConstraint) factory.create(SybaseasesqlmodelPackage.eINSTANCE
                        .getSybaseASEColumnCheckConstraint());
                inlineRule.setSearchCondition(sc);
                inlineRule.setName(checkName);
                
    			stmt = conn.prepareStatement(ASESQLs.QUERY_COLUMNCHECK_CONSTRAINTS_CREATOR);
    			stmt.setString(1, checkName);
    			stmt.setInt(2, ((ICatalogTable)this.getTable()).getTableId());
    			stmt.setInt(3, ((ICatalogTable)this.getTable()).getTableId());
    			rs = stmt.executeQuery();
    			Schema creator = null;
    			while(rs.next())
    			{
    				String creatorName = rs.getString(1);
                    SybaseASECatalog catalog = (SybaseASECatalog) this.getTable().getSchema().getCatalog();
                    creator = (Schema) ASEUtil.getSQLObject(catalog.getSchemas(), creatorName);
    			}
    			inlineRule.setCreator(creator);

            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        
        super.setColumnCheck(inlineRule);

        this.eSetDeliver(deliver);
        columnCheckLoaded = Boolean.TRUE;
	}

	public SybaseASERule getBoundRule() {
		synchronized (bindedRuleLoaded) {
			if(!bindedRuleLoaded.booleanValue())
				loadBindedRule();
		}
		return super.getBoundRule();
	}
	
	private void loadBindedRule() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try
		{
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(ASESQLs.QUERY_COLUMN_BINDED_RULE);
			stmt.setInt(1, ((ICatalogTable)this.getTable()).getTableId());
            stmt.setString(2, this.getName());
			stmt.setInt(3, ((ICatalogTable)this.getTable()).getTableId());
            stmt.setString(4, this.getName());
            rs = stmt.executeQuery();
            SybaseASERule offlineRule = null;
            while(rs.next())
            {
            	String ownerName = rs.getString(1);
                String ruleName = rs.getString(2);
                SybaseASESchema schema = (SybaseASESchema) ASEUtil.getSQLObject(this.getTable().getSchema().getCatalog()
                        .getSchemas(), ownerName);
                offlineRule = (SybaseASERule) ASEUtil.getSQLObject(schema.getRules(),
                        ruleName);
            }
            super.setBoundRule(offlineRule);
		}
		catch(SQLException e)
		{
			JDBCASEPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}
        this.eSetDeliver(deliver);
        bindedRuleLoaded = Boolean.TRUE;
	}

	private String getComputedColExpression(String columnName, Connection conn)
    {
        StringBuffer sb = new StringBuffer(128);
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(getTable().getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.COMPUTED_COL_EXPRESSION);
            stmt.setInt(1, ((ICatalogTable)getTable()).getTableId());
            stmt.setString(2, columnName);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                
                Pattern p1 = Pattern.compile("AS\\s+(.*)\\s+NOT\\s+MATERIALIZED");
                Pattern p2 = Pattern.compile("AS\\s+(.*)\\s+MATERIALIZED");
                Pattern p3 = Pattern.compile("AS\\s+(.*)");
                Pattern[] p=new Pattern[]{p1,p2,p3};
                for(int i=0;i<p.length;i++)
                {
                   
                    Matcher matcher = p[i].matcher(rs.getString(1)==null?"":rs.getString(1).trim());
                    if(matcher.matches())
                    {
                        sb.append(matcher.group(1).trim());
                        break;
                    }
                }

            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        return sb.toString();
    }
	
	public boolean isNullable() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
				loadColumnInfo();
		}
		return super.isNullable();
	}
	
	public IdentitySpecifier getIdentitySpecifier() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
				loadColumnInfo();
		}
		return super.getIdentitySpecifier();
	}
	
	public boolean isMaterialized() {
		synchronized (columnInfoLoaded) {
			if(!columnInfoLoaded.booleanValue())
				loadColumnInfo();
		}
		return super.isMaterialized();
	}
	
	private void loadColumnInfo() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try
		{
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(ASESQLs.QUERY_COLUMN_STATUS);
			stmt.setInt(1, ((ICatalogTable)this.getTable()).getTableId());
            stmt.setString(2, this.getName());
            rs = stmt.executeQuery();
            boolean nullable = false;
            boolean isMaterialized = false;
            IdentitySpecifier is = null;
            while(rs.next())
            {
            	int status = rs.getInt(1);
            	int status2 = rs.getInt(2);
            	nullable = (status & 8) == 8;
                boolean isIdentity = (status & 128) == 128;
                if (isIdentity)
                {
                    is = (IdentitySpecifier) SQLSchemaFactoryImpl.init().create(SQLSchemaPackage.eINSTANCE.getIdentitySpecifier());
                }
                
                if (((SybaseASEDatabase) this.getCatalogDatabase())
						.isComputedColApplicable()
						&& (status2 & COL_STATUS2_COMPUTED_COL) == COL_STATUS2_COMPUTED_COL
						&& (status2 & COL_STATUS2_COMPUTED_MATERIALIZED_COL) == COL_STATUS2_COMPUTED_MATERIALIZED_COL)
                {
                	isMaterialized = true;
                }
            }
            super.setNullable(nullable);
            super.setIdentitySpecifier(is);
            super.setMaterialized(isMaterialized);
            SybaseASEDatabase db = (SybaseASEDatabase)getTable().getSchema().getCatalog().getDatabase();
            if(db.isFunctionalBasedIndexMemApplicable())
            {
            	stmt = conn.prepareStatement(ASESQLs.INDEX_COL_STATUS3_QUERY);
				stmt.setString(1, getName());
				stmt.setString(2, ASEUtil
						.getFullQuatifiedName(getTable()));
				rs = stmt.executeQuery();
				while(rs.next())
				{
					int status3 = rs.getInt(1);
					if ((status3 & COL_STATUS3_HIDDEN_COMPUTED_COL) > 0)
					{
						super.setHidden(true);
					}
					else
					{
						super.setHidden(false);
					}
				}
            }
		}
		catch(SQLException e)
		{
			JDBCASEPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}
        this.eSetDeliver(deliver);
        columnInfoLoaded = Boolean.TRUE;
	}
	
	public SybaseASEEncryptionKey getEncryptionKey() {
		synchronized (encryptionKeyLoaded) {
			if(!encryptionKeyLoaded.booleanValue())
				loadEncryptionKey();
		}
		return super.getEncryptionKey();
	}

	private void loadEncryptionKey() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		SybaseASEEncryptionKey key = null;
		SybaseASEDatabase database = (SybaseASEDatabase)this.getCatalogDatabase();
		if(database.isEncryptionKeyApplicable())
		{
			Connection conn = this.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String oldCatalog = null;
			try
			{
				oldCatalog = conn.getCatalog();
				conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
				stmt = conn.prepareStatement(ASESQLs.QUERY_COLUMN_ENCRYPTIONKEY);
				stmt.setInt(1, ((ICatalogTable)this.getTable()).getTableId());
	            stmt.setString(2, this.getName());
	            rs = stmt.executeQuery();
	            while(rs.next())
	            {
	            	int encrypKeyId = rs.getInt(1);
	            	if(encrypKeyId != 0)
	            	{
	            		String db = rs.getString(2);
                        // if encrypted key is in the same database as the table,
                        // then db will be null
                        if (db == null || db.equals(""))
                        {
                            db = this.getTable().getSchema().getCatalog().getName();
                        }
                        key = getEncryptionKey(db, encrypKeyId, conn);
	            	}
	            }
			}
			catch(SQLException e)
			{
				JDBCASEPlugin.getDefault().log(e);
			}
			finally
			{
				SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
			}
		}
		super.setEncryptionKey(key);
        this.eSetDeliver(deliver);
        columnInfoLoaded = Boolean.TRUE;
	}
	
	private SybaseASEEncryptionKey getEncryptionKey(String dbName, int keyId, Connection conn)
    {
        SybaseASEEncryptionKey key = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(dbName);
            stmt = conn.prepareStatement(ASESQLs.ENCRYPT_COL_ATTR);
            stmt.setInt(1, keyId);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String keyName = rs.getString(1);
                String ownerName = rs.getString(2);

                Catalog catalog = (Catalog) ASEUtil.getSQLObject(getTable()
						.getSchema().getDatabase().getCatalogs(), dbName);
                Schema schema = (Schema) ASEUtil.getSQLObject(catalog.getSchemas(), ownerName);
                key = (SybaseASEEncryptionKey)ASEUtil.getSQLObject(((SybaseASESchema)schema).getEncryptionKeys(), keyName);
                if( key == null )
                {
                	//TODO: load encryptionkeys in SybaseASESchema in future
    	            key = (SybaseASEEncryptionKey) SybaseasesqlmodelFactoryImpl.init().create(SybaseasesqlmodelPackage.eINSTANCE
    	                    .getSybaseASEEncryptionKey());
    	            key.setName(keyName);
	                key.setSchema((SybaseASESchema) schema);
                }
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }

        return key;
    }

	/**
	 * Delegates to table.
	 */
	public EList getPrivileges()
    {
        EList privileges = super.getPrivileges();
        ArrayList tempList = new ArrayList();
        if (getTable() instanceof SybaseAuthorizedObject)
        {
            EList allPrivs = ((SybaseAuthorizedObject)getTable()).getPrivileges();
            for (Iterator it = allPrivs.iterator(); it.hasNext();)
            {
                SybasePrivilege p = (SybasePrivilege) it.next();
                if (p.getActionObjects().contains(this))
                {
                	tempList.add(p);
                }
            }
        }
        privileges.addAll(tempList);
        return privileges;
    }

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private Boolean inlineDefaultLoaded = Boolean.FALSE;
	private Boolean boundDefaultLoaded = Boolean.FALSE;
	private Boolean valueExpressionLoaded = Boolean.FALSE;
	private Boolean columnCheckLoaded = Boolean.FALSE;
	private Boolean bindedRuleLoaded = Boolean.FALSE;
	private Boolean columnTypeLoaded = Boolean.FALSE;
	private Boolean columnInfoLoaded = Boolean.FALSE; //load nullable ,identitySpecifier, isMaterialized
	private Boolean encryptionKeyLoaded = Boolean.FALSE;
}
