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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.MatchType;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SybaseASECatalogForeignKey extends ForeignKeyImpl implements ICatalogObject,IAdaptable {
	private static final long serialVersionUID = 3257285837955086384L;

	public void refresh() {
//		synchronized (eAnnotationLoaded) {
//			if(eAnnotationLoaded.booleanValue())
//				eAnnotationLoaded = Boolean.FALSE;
//		}
		synchronized (membersLoaded) {
			if(membersLoaded.booleanValue())
				membersLoaded = Boolean.FALSE;
		}
		synchronized (referMemberLoaded) {
			if(referMemberLoaded.booleanValue())
				referMemberLoaded = Boolean.FALSE;
		}
		synchronized (FKPrimaryUniqueLoaded) {
			if(FKPrimaryUniqueLoaded.booleanValue())
				FKPrimaryUniqueLoaded = Boolean.FALSE;
		}
        synchronized (referTableLoaded) {
            if(referTableLoaded.booleanValue())
                referTableLoaded = Boolean.FALSE;
        }
        synchronized (matchFullLoaded) {
            if(matchFullLoaded.booleanValue())
                matchFullLoaded = Boolean.FALSE;
        }
        
		RefreshManager.getInstance().referesh(this);
	}

//	public EList getEAnnotations() {
//		synchronized (eAnnotationLoaded) {
//			if(!eAnnotationLoaded.booleanValue())
//				loadEAnnotations();
//		}
//		return this.eAnnotations;
//	}
	
	public Connection getConnection() {
		SybaseASECatalogSchema schema = (SybaseASECatalogSchema)this.getBaseTable().getSchema();
		return schema.getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
//		case SQLConstraintsPackage.FOREIGN_KEY__EANNOTATIONS:
//			this.getEAnnotations();
//			break;
		case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT:
			this.getUniqueConstraint();
			break;
		case SQLConstraintsPackage.FOREIGN_KEY__MEMBERS:
			this.getMembers();
			break;
		case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS:
			this.getReferencedMembers();
			break;
		case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX:
			this.getUniqueIndex();
			break;
        case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE:
            this.getReferencedTable();
            break;
        case SQLConstraintsPackage.FOREIGN_KEY__MATCH:
            this.getMatch();
            break;
		}
		
		return super.eIsSet(eFeature);
	}

//	private synchronized void loadEAnnotations() {
//		if(this.eAnnotationLoaded.booleanValue()) return;
////		EList memberList = super.getEAnnotations();
//		
//		boolean deliver = this.eDeliver();
//		this.eSetDeliver(false);	
//		
//		SybaseASECatalogForeignKey.setAsIdentifyingRelatinship(this,this.isIdentifyingRelationship(super.getMembers()));
//		
//		this.eAnnotationLoaded = Boolean.TRUE;
//		this.eSetDeliver(deliver);		
//	}
	

//	public static void setAsIdentifyingRelatinship(ForeignKey fk,boolean identifying){
//		EAnnotation eAnnotation = fk.addEAnnotation(RDBCorePlugin.FK_MODELING_RELATIONSHIP);
//		fk.addEAnnotationDetail(eAnnotation,RDBCorePlugin.FK_IS_IDENTIFYING_RELATIONSHIP,new Boolean(identifying).toString());
//
//		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_CHILD_MULTIPLICITY, RDBCorePlugin.MANY);
//		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_CHILD_ROLE_NAME, new String ());
//		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_PARENT_MULTIPLICITY, (fk.getMembers().size() > 0) ? RDBCorePlugin.ZERO_TO_ONE : RDBCorePlugin.ONE);
//		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_PARENT_ROLE_NAME, new String ());
//	}

	
//	private boolean isIdentifyingRelationship(EList columns){
//		boolean isIdentifying = true;
//		Iterator it = columns.iterator();
//		while(it.hasNext()) {
//			Column column = (Column) it.next();
//			if(!column.isPartOfPrimaryKey()){
//				isIdentifying = false;
//				break;
//			}
//		}
//		return isIdentifying;
//	}
	
	
	
    public BaseTable getReferencedTable()
    {
        synchronized (referTableLoaded)
        {
            if(!referTableLoaded.booleanValue())
            {
                Index uniqueIndex = null;
                if(this.getUniqueConstraint() != null)
                {
//                    uniqueIndex = ((SybaseASEUniqueConstraint)this.getUniqueConstraint()).getSystemGenedIndex();
                    SybaseASEUniqueConstraint aseUnqiue = null;
                    if(this.getUniqueConstraint() instanceof SybaseASEUniqueConstraint)
                    {
                        aseUnqiue = (SybaseASEUniqueConstraint)this.getUniqueConstraint();
                    }
                    else 
                    {
                        aseUnqiue = (SybaseASEUniqueConstraint)((SybaseASEPrimaryKey)this.getUniqueConstraint()).getAseUniqueConstraint();
                    }
                    uniqueIndex = aseUnqiue.getSystemGenedIndex();
                }
                else
                {
                    uniqueIndex = this.getUniqueIndex();
                }
                setReferencedTable((BaseTable)uniqueIndex.getTable());
            }
        }
        
        return super.getReferencedTable();
    }
    
    public EList getMembers()
    {
    	synchronized (membersLoaded) {
			if(!membersLoaded.booleanValue())
				loadMembers(); 
		}
            
        return super.getMembers();
    }
	
	private void loadMembers()
    {
        if(membersLoaded.booleanValue())
            return;
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        EList members = super.getMembers();
        members.clear();
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try{
        	oldCatalog = conn.getCatalog();
        	conn.setCatalog(this.getBaseTable().getSchema().getCatalog().getName());
        	stmt = conn.prepareStatement(ASESQLs.QUERY_FOREIGNKEY_MEMBERS);
        	stmt.setString(1, this.getName());
        	stmt.setString(2, this.getBaseTable().getSchema().getName());
        	rs = stmt.executeQuery();
        	while(rs.next())
        	{
        		for (int i = 1; i < 17; i++)
                {
                      String colName = rs.getString(i);
                      if (colName != null && !colName.equals(""))
                      {
                          Column col = (Column)ASEUtil.getSQLObject(this.getBaseTable().getColumns(), colName);
                          super.getMembers().add(col);
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
        membersLoaded = Boolean.TRUE;
    }
	
	public EList getReferencedMembers() {
		synchronized (referMemberLoaded) {
			if(!referMemberLoaded.booleanValue())
				loadReferencedMemebers();
		}
		return super.getReferencedMembers();
	}

    private void loadReferencedMemebers() {
    	boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        super.getReferencedMembers().clear();
        
        Index uniqueIndex = null;
        if(this.getUniqueConstraint() != null)
        {
//            uniqueIndex = ((SybaseASEUniqueConstraint)this.getUniqueConstraint()).getSystemGenedIndex();
            SybaseASEUniqueConstraint aseUnqiue = null;
            if(this.getUniqueConstraint() instanceof SybaseASEUniqueConstraint)
            {
                aseUnqiue = (SybaseASEUniqueConstraint)this.getUniqueConstraint();
            }
            else 
            {
                aseUnqiue = (SybaseASEUniqueConstraint)((SybaseASEPrimaryKey)this.getUniqueConstraint()).getAseUniqueConstraint();
            }
            uniqueIndex = aseUnqiue.getSystemGenedIndex();
        }
        else
        {
            uniqueIndex = this.getUniqueIndex();
        }
        EList indexMems = uniqueIndex.getMembers();
        for(int i = 0; i<indexMems.size(); i++)
        {
            IndexMember mem = (IndexMember)indexMems.get(i);
            super.getReferencedMembers().add(mem.getColumn());
        }
		
        this.eSetDeliver(deliver);
        referMemberLoaded = Boolean.TRUE;
	}
    
    public UniqueConstraint getUniqueConstraint() {
    	synchronized (FKPrimaryUniqueLoaded) {
			if(!FKPrimaryUniqueLoaded.booleanValue())
				loadFKPrimaryUnique();
		}
    	return super.getUniqueConstraint();
    }
    
    public Index getUniqueIndex() {
    	synchronized (FKPrimaryUniqueLoaded) {
			if(!FKPrimaryUniqueLoaded.booleanValue())
				loadFKPrimaryUnique();
		}
    	return super.getUniqueIndex();
    }
    
    private void loadFKPrimaryUnique()
    {
    	boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
        	oldCatalog = conn.getCatalog();
        	conn.setCatalog(this.getBaseTable().getSchema().getCatalog().getName());
        	stmt = conn.prepareStatement(ASESQLs.QUERY_FOREIGNKEY_UNIQUEINDEX);
        	stmt.setString(1, this.getName());
        	stmt.setString(2, this.getBaseTable().getSchema().getName());
        	rs = stmt.executeQuery();
        	while(rs.next())
        	{
        		int indexId = rs.getInt(1);
              String pmryDBName = rs.getString(2);
              int refTableId = rs.getInt(3);
              
              setFKPmryUnique(indexId, pmryDBName, refTableId, this, conn);
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
        FKPrimaryUniqueLoaded = Boolean.TRUE;
    }

    private void setFKPmryUnique(int indexId, String pmryDBName, int refTableId, ForeignKey fk, Connection conn)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            
            boolean isSameDatabase = true;
            if (pmryDBName != null && !pmryDBName.equals(""))
            {
                isSameDatabase = false;
                conn.setCatalog(pmryDBName);
            }
            else
            {
                conn.setCatalog(this.getBaseTable().getSchema().getCatalog().getName());
            }
            stmt = conn.prepareStatement(ASESQLs.QUERY_REF_INDEX);
            stmt.setInt(1, refTableId);
            stmt.setInt(2, indexId);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String tableName = rs.getString(1);
                String schemaName = rs.getString(2);
                String indexName = rs.getString(3);

                SybaseASECatalog catalog = (SybaseASECatalog) this.getBaseTable().getSchema().getCatalog();

                if (!isSameDatabase)
                {
                    SybaseASEDatabase db = (SybaseASEDatabase) this.getCatalogDatabase();
                    catalog = (SybaseASECatalog) ASEUtil.getSQLObject(db.getCatalogs(), pmryDBName);
                }

                Schema schema = (SybaseASECatalogSchema) ASEUtil.getSQLObject(catalog.getSchemas(),
                        schemaName);
                BaseTable table = (BaseTable) ASEUtil.getSQLObject(schema.getTables(), tableName);
                fk.setReferencedTable(table);
                Index index = (Index) ASEUtil.getSQLObject(table.getIndex(), indexName);

                if (index.isSystemGenerated())
                {
                    EList constrList = table.getConstraints();
                    for (int i = 0; i < constrList.size(); i++)
                    {
                        TableConstraint constr = (TableConstraint) constrList.get(i);
                        EClass clazz = constr.eClass();
                        if (SQLConstraintsPackage.eINSTANCE.getUniqueConstraint().isSuperTypeOf(clazz))
                        {
                            if (constr.getName().equals(indexName))
                            {
                                fk.setUniqueConstraint((UniqueConstraint) constr);
                                break;
                            }
                        }
                    }
                }
                else
                {
                    fk.setUniqueIndex(index);
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
    }

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
//	private Boolean eAnnotationLoaded= Boolean.FALSE;
	private Boolean membersLoaded = Boolean.FALSE;
	private Boolean referMemberLoaded = Boolean.FALSE;
	private Boolean FKPrimaryUniqueLoaded = Boolean.FALSE;
    private Boolean referTableLoaded = Boolean.FALSE;
    private Boolean matchFullLoaded = Boolean.FALSE;

    public MatchType getMatch()
    {
        synchronized (membersLoaded)
        {
            if(!matchFullLoaded.booleanValue())
            {
                loadMatchFull();
            }
        }
        return super.getMatch();
    }

    private void loadMatchFull()
    {
        if(matchFullLoaded.booleanValue())
        {
            return;
        }
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getBaseTable().getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_FOREIGNKEY_MATCH_FULL);
            stmt.setString(1, this.getName());
            rs = stmt.executeQuery();
            while(rs.next())
            {
                int status = rs.getInt(1);
                if((status & 2)==2)
                {
                    this.setMatch(MatchType.MATCH_FULL_LITERAL);
                }
                else
                {
                    this.setMatch(MatchType.MATCH_SIMPLE_LITERAL);
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
        matchFullLoaded = Boolean.TRUE;
    }
}
