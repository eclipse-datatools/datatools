package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsFactory;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseTrigger extends SybaseASABaseTriggerImpl implements ICatalogObject,IAdaptable
{
	private static final long serialVersionUID = 7357619526611063764L;
	protected Boolean triggerInfoLoaded = Boolean.FALSE;
	
	final private static byte INSERT_EVENT = 1;
	final private static byte DELETE_EVENT = 2;
	final private static byte UPDATE_EVENT = 4;
	final private static byte UPDATE_COLUMN_EVENT = 8;
    
    protected final static String OLD                 = "OLD";               //$NON-NLS-1$
    protected final static String NEW                 = "NEW";               //$NON-NLS-1$
    protected final static String REMOTE              = "REMOTE";            //$NON-NLS-1$
    protected final static String AS                  = "AS";                //$NON-NLS-1$
    protected final static String UPDATE              = "UPDATE";            //$NON-NLS-1$
    protected final static String OF                  = "OF";                //$NON-NLS-1$
    protected final static String ORDER               = "ORDER";                //$NON-NLS-1$
    protected final static String ON                  = "ON";                //$NON-NLS-1$

	public Database getCatalogDatabase() {
		return this.getSubjectTable().getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (triggerInfoLoaded) {
			if(triggerInfoLoaded.booleanValue())
			{
				triggerInfoLoaded = Boolean.FALSE;
			}
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__DESCRIPTION:
			getDescription();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ACTION_TIME:
			getSybaseASABaseActionTime();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ORDER:
			getOrder();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ACTION_GRANULARITY:
			getActionGranularity();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__WHEN:
			getWhen();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__TRIGGER_COLUMN:
			getTriggerColumn();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_TYPE:
			isUpdateType();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__DELETE_TYPE:
			isDeleteType();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__INSERT_TYPE:
			isInsertType();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__NEW_ROW:
			getNewRow();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__NEW_TABLE:
			getNewTable();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__OLD_ROW:
			getOldRow();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__OLD_TABLE:
			getOldTable();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME:
			getRemoteName();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ACTION_STATEMENT:
			getActionStatement();
			break;
		}
		return super.eIsSet(eFeature);
	}
	
	public String getDescription() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDescription();
	}
	
	public SybaseASABaseActionTime getSybaseASABaseActionTime() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getSybaseASABaseActionTime();
	}
	
	public int getOrder() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getOrder();
	}
	
	public ActionGranularityType getActionGranularity() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getActionGranularity();
	}

	public SearchCondition getWhen() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getWhen();
	}
	
	public EList getTriggerColumn() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getTriggerColumn();
	}
	
	public boolean isUpdateType() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isUpdateType();
	}

	public boolean isDeleteType() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isDeleteType();
	}

	public boolean isInsertType() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isInsertType();
	}
	
	public String getNewRow() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getNewRow();
	}

	public String getNewTable() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getNewTable();
	}

	public String getOldRow() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getOldRow();
	}

	public String getOldTable() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getOldTable();
	}

	public String getRemoteName() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return null;
	}
	
	public EList getActionStatement() {
		synchronized (triggerInfoLoaded) {
			if(!triggerInfoLoaded.booleanValue())
			{
				loadTriggerInfo();
				triggerInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getActionStatement();
	}
	
	private void loadTriggerInfo() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Table table  = this.getSubjectTable();
			String schemaName = table.getSchema().getName();
			String tableName = table.getName();
			
			stmt = conn.prepareStatement(ASASQLs.QUERY_TRIGGER_INFO);
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			stmt.setString(3, this.getName());
			
			rs = stmt.executeQuery();
			while(rs.next())
			{
                char event = SybaseASACatalogUtils.getCharValue(rs.getString(2));
                char time = SybaseASACatalogUtils.getCharValue(rs.getString(3));
                int order = rs.getInt(4);
                String triggerDefn = rs.getString(5);
                String remark = rs.getString(6);
                String source = rs.getString(7);
                
                boolean isInsertType = false;
                boolean isDeleteType = false;
                boolean isUpdateType = false;
                boolean isUpdateColumnType = false;
                byte eventId = getEventsId(event);
                if((eventId & INSERT_EVENT) != 0)
                {
                	isInsertType = true;
                }
                if((eventId & DELETE_EVENT) != 0)
                {
                	isDeleteType = true;
                }
                if((eventId & UPDATE_EVENT) != 0)
                {
                	isUpdateType = true;
                }
                if((eventId & UPDATE_COLUMN_EVENT) != 0)
                {
                	isUpdateColumnType = true;
                }
                
                String statement = (source != null && source.length() > 0)?source : triggerDefn;
                SybaseASABaseActionTime actionTime = getTimingId(time);
                ActionGranularityType agt = ActionGranularityType.ROW_LITERAL;
                if(time == 'S')
                {
                	agt = ActionGranularityType.STATEMENT_LITERAL;
                }
                
                super.setInsertType(isInsertType);
                super.setDeleteType(isDeleteType);
                super.setUpdateType(isUpdateType);
                super.setUpdateColumnType(isUpdateColumnType);
                //TODO parse triggerColumns from the source
                super.setOrder(order);
                super.setDescription(remark);
                super.setActionGranularity(agt);
                super.setSybaseASABaseActionTime(actionTime);
                SQLStatement sqlStmt = SQLStatementsFactory.eINSTANCE.createSQLStatementDefault();
                sqlStmt.setSQL(statement);
                super.getActionStatement().clear();
                super.getActionStatement().add(sqlStmt);

                //TODO: we need to parse statement to get all other attributes
                //includes: when, triggerColumn, new xxx, old xxx, remote name
			}
		}
		catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		finally
		{
			SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
		}
		this.eSetDeliver(deliver);
	}
	
	private byte getEventsId(char c)
    {
        switch(c)
        {
        case 73: // 'I'
            return 1;

        case 85: // 'U'
            return 4;

        case 68: // 'D'
            return 2;

        case 67: // 'C'
            return 8;

        case 65: // 'A'
            return 3;

        case 66: // 'B'
            return 5;

        case 69: // 'E'
            return 6;

        case 77: // 'M'
            return 7;
        }
        return 0;
    }
	
	private SybaseASABaseActionTime getTimingId(char c)
    {
        switch(c)
        {
        case 66: // 'B'
            return SybaseASABaseActionTime.BEFORE_LITERAL;

        case 65: // 'A'
            return SybaseASABaseActionTime.AFTER_LITERAL;
        case 83: // 'S'
            return SybaseASABaseActionTime.ASE_LITERAL;

        case 82: // 'R'
            return SybaseASABaseActionTime.RESOLVE_LITERAL;
        }
        return SybaseASABaseActionTime.AFTER_LITERAL;
    }

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
}
