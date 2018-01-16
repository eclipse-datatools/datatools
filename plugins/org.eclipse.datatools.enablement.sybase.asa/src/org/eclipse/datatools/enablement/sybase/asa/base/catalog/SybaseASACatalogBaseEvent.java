package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseEventImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseEvent extends SybaseASABaseEventImpl implements ICatalogObject,IAdaptable
{
	private static final long serialVersionUID = 7542251482941333022L;
	
	protected Boolean eventInfoLoaded = Boolean.FALSE;
	protected Boolean schedulesLoaded = Boolean.FALSE;
	
	final public static String REMOTE_LOCATION_TYPE_IN_DATABASE = "R";
	final public static String CONSOLIDATED_LOCATION_TYPE_IN_DATABASE = "C";
	final public static String ALL_LOCATION_TYPE_IN_DATABASE = "A";
	
	final public static String INTERNAL_UNIT_HOURS_IN_DATABASE = "HH";
	final public static String INTERNAL_UNIT_MINUTES_IN_DATABASE = "NN";
	final public static String INTERNAL_UNIT_SECONDS_IN_DATABASE = "SS";
	
	public Database getCatalogDatabase() {
		return this.getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
	    if(isNeedRefresh())
	    {
            synchronized (eventInfoLoaded)
            {
                if (eventInfoLoaded.booleanValue())
                {
                    eventInfoLoaded = Boolean.FALSE;
                }
            }
            synchronized (schedulesLoaded)
            {
                if (schedulesLoaded.booleanValue())
                {
                    schedulesLoaded = Boolean.FALSE;
                }
            }
            RefreshManager.getInstance().referesh(this);
        }
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__ACTION:
			getAction();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__CONDITION:
			getCondition();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__ENABLED:
			isEnabled();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__EVENT_TYPE:
			getEventType();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__LOCATION:
			getLocation();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__SCHEDULES:
			getSchedules();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__DESCRIPTION:
		    getDescription();
		    break;
		}
		return super.eIsSet(eFeature);
	}


	public String getAction() {
		synchronized (eventInfoLoaded) {
			if(!eventInfoLoaded.booleanValue())
			{
				loadEventInfo();
				eventInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getAction();
	}

	public String getCondition() {
		synchronized (eventInfoLoaded) {
			if(!eventInfoLoaded.booleanValue())
			{
				loadEventInfo();
				eventInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getCondition();
	}

	public boolean isEnabled() {
		synchronized (eventInfoLoaded) {
			if(!eventInfoLoaded.booleanValue())
			{
				loadEventInfo();
				eventInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isEnabled();
	}


	public EventType getEventType() {
		synchronized (eventInfoLoaded) {
			if(!eventInfoLoaded.booleanValue())
			{
				loadEventInfo();
				eventInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getEventType();
	}

	public EventLocationType getLocation() {
		synchronized (eventInfoLoaded) {
			if(!eventInfoLoaded.booleanValue())
			{
				loadEventInfo();
				eventInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getLocation();
	}

	public String getDescription() {
	    synchronized (eventInfoLoaded) {
	        if(!eventInfoLoaded.booleanValue())
	        {
	            loadEventInfo();
	            eventInfoLoaded = Boolean.TRUE;
	        }
	    }
	    return super.getDescription();
	}
	
	public EList getSchedules() {
		synchronized (schedulesLoaded) {
			if(!schedulesLoaded.booleanValue())
			{
				loadSchedules();
				schedulesLoaded = Boolean.TRUE;
			}
		}
		return super.getSchedules();
	}

	private void loadEventInfo() {
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(ASASQLs.QUERY_EVENT_INFO);
			stmt.setString(1, this.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
                boolean enabled = rs.getString(1).equalsIgnoreCase("Y");
                String location = rs.getString(2);
                String action = rs.getString(3);
                String condition = rs.getString(4);
                String sEventType = rs.getString(5);
                String remark = rs.getString(6);
                
                EventLocationType elt = null;
                if(location == null)
                {
                }
                else if(location.equals(REMOTE_LOCATION_TYPE_IN_DATABASE))
                {
                	elt = EventLocationType.REMOTE_LITERAL;
                }
                else if(location.equals(CONSOLIDATED_LOCATION_TYPE_IN_DATABASE))
                {
                	elt = EventLocationType.CONSOLIDATED_LITERAL;
                }
                else if(location.equals(ALL_LOCATION_TYPE_IN_DATABASE))
                {
                	elt = EventLocationType.ALL_LITERAL;
                }
                
                EventType eventType = EventType.get(sEventType);
                if(sEventType != null && eventType == null)
                {
                	eventType = EventType.get("\"" + sEventType + "\"");
                }
                               
                super.setEnabled(enabled);
                super.setAction(action);
                super.setDescription(remark);
                super.setLocation(elt);
                super.setEventType(eventType);
                super.setCondition(condition);
                
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
	
	private void loadSchedules()
	{
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		
		EList schedules = super.getSchedules();
		schedules.clear();
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(ASASQLs.QUERY_EVENT_SCHEDULES);
			stmt.setString(1, this.getName());
			rs = stmt.executeQuery();
			while(rs.next())
			{
                String scheduleName = rs.getString(1);
                boolean recurring = rs.getInt(2) == 1;
                Time startTime = rs.getTime(3);
                Time stopTime = rs.getTime(4);
                Date startDate = rs.getDate(5); 
                int daysOfWeek = rs.getInt(6);
                int daysOfMonth = rs.getInt(7);
                String intervalUnit = rs.getString(8);
                int intervalAmount = rs.getInt(9);
                
                IntervalUnitType iut = null;
                if(intervalUnit == null)
                {
                }
                else if(intervalUnit.equals(INTERNAL_UNIT_HOURS_IN_DATABASE))
                {
                	iut = IntervalUnitType.HOURS_LITERAL;
                }
                else if(intervalUnit.equals(INTERNAL_UNIT_MINUTES_IN_DATABASE))
                {
                	iut = IntervalUnitType.MINUTES_LITERAL;
                }
                else if(intervalUnit.equals(INTERNAL_UNIT_SECONDS_IN_DATABASE))
                {
                	iut = IntervalUnitType.SECONDS_LITERAL;
                }
                
                Schedule schedule = SybaseasabasesqlmodelFactory.eINSTANCE.createSchedule();
                schedule.setName(scheduleName);
                schedule.setRecurring(recurring);
                schedule.setStartTime(startTime);
                schedule.setStopTime(stopTime);
                schedule.setStartDate(startDate);
                schedule.setDaysOfWeek(daysOfWeek);
                schedule.setDaysOfMonth(daysOfMonth);
                schedule.setIntervalMount(intervalAmount);
                schedule.setIntervalUnit(iut);
                	
                schedules.add(schedule);
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
	
	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}	
	
	private boolean isNeedRefresh()
    {
        if(eventInfoLoaded.booleanValue()||schedulesLoaded.booleanValue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
