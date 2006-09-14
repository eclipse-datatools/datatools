/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core.internal.dbitem;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGenerationOptions;
import org.eclipse.datatools.modelbase.sql.datatypes.ExactNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType;
import org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.IDBItem;
import org.eclipse.datatools.sqltools.core.dbitem.IItemWithCode;
import org.eclipse.datatools.sqltools.core.dbitem.ISPUDF;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.common.util.EList;

/**
 * An adapter that makes <code>Routine</code>/<code>Trigger</code> and
 * <code>IControlConnection</code> work together.
 * TODO: event support
 * @author Hui Cao
 * 
 */
public class SQLObjectItem implements IDBItem, IItemWithCode, ISPUDF {

	ProcIdentifier _proc = null;

	SQLObject _routine = null;

	IControlConnection _controlConn = null;
	
	ParameterDescriptor[] _parameterDescriptors = null;

	public SQLObjectItem(ProcIdentifier proc, SQLObject routine,
			IControlConnection controlConn) {
		_proc = proc;
		_routine = routine;
		_controlConn = controlConn;
	}

	public ProcIdentifier getProcIdentifier() {
		return _proc;
	}

	public IControlConnection getControlConnection() {
		return _controlConn;
	}

	public void refresh() {
		dispose();
	}

	public void dispose() {
		if (_routine instanceof ICatalogObject) {
			//Unmark this line when 129092 is fixed
			((ICatalogObject) _routine).refresh();
			if (_routine instanceof Trigger)
			{
				Table table = ((Trigger)_routine).getSubjectTable();
				if (table instanceof ICatalogObject)
				{
					((ICatalogObject) table).refresh();
				}
			}
		}
	}

	public String getCode() throws SQLException {
		String code = "";
		DDLGenerator ddlg = ProfileUtil.getDatabaseDefinition(
				_proc.getProfileName()).getDDLGenerator();
		if (ddlg != null) {
			EngineeringOption[] opts = ddlg.getOptions();
			boolean generateDrop = opts[GenericDdlGenerationOptions.GENERATE_DROP_STATEMENTS]
					.getBoolean();
			boolean fullName = opts[GenericDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME].getBoolean();
			boolean oldQuotedId = opts[GenericDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER].getBoolean();
			boolean quotedId = SQLDevToolsUtil.getQuotedIdentifier(_proc.getDatabaseIdentifier());
			opts[GenericDdlGenerationOptions.GENERATE_DROP_STATEMENTS]
					.setBoolean(false);
			opts[GenericDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME].setBoolean(true);
			opts[GenericDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER].setBoolean(quotedId);
			
			
			String[] ddl = ddlg.generateDDL(new SQLObject[] { _routine }, null);
			if (ddl != null && ddl.length > 0) {
				code = ddl[0];
			}
			// restore
			opts[GenericDdlGenerationOptions.GENERATE_DROP_STATEMENTS]
					.setBoolean(generateDrop);
			opts[GenericDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME].setBoolean(fullName);
			opts[GenericDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER].setBoolean(oldQuotedId);
		}
		return code;
	}

	public void save(String code) throws SQLException {
		DDLGenerator ddlg = ProfileUtil.getDatabaseDefinition(
				_proc.getProfileName()).getDDLGenerator();
		if (ddlg != null) {
			EngineeringOption[] opts = ddlg.getOptions();
			String[] drop = ddlg
					.dropSQLObjects(
							new SQLObject[] { _routine },
							opts[GenericDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER]
									.getBoolean(),
							true, null);
			// we alway use "true" instead of
			// opts[GenericDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME].getBoolean()
			String[] ddl = new String[2];
			ddl[0] = drop[0];
			ddl[1] = code;
			_controlConn.executeDDL(ddl);
		}
	}


	public void drop() throws SQLException {
		DDLGenerator ddlg = ProfileUtil.getDatabaseDefinition(
				_proc.getProfileName()).getDDLGenerator();
		if (ddlg != null) {
			EngineeringOption[] opts = ddlg.getOptions();
			String[] drop = ddlg
					.dropSQLObjects(
							new SQLObject[] { _routine },
							opts[GenericDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER]
									.getBoolean(),
							true, null);
			// we alway use "true" instead of
			// opts[GenericDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME].getBoolean()
			//controlconnection will refresh and dispose this object
			_controlConn.executeDDL(drop);
		}
	}

	public int getValidBreakpointLocation(int number) throws SQLException {
		return number;
	}

	public ParameterDescriptor[] getParameterDescriptor() throws SQLException {
        if (_parameterDescriptors == null)
        {
        	ArrayList paramList = new ArrayList();
            if (_routine instanceof Routine)
            {
            	Routine r = (Routine)_routine;
            	EList ps = r.getParameters();
            	for (Iterator iter = ps.iterator(); iter.hasNext();) {
					Parameter p = (Parameter) iter.next();
					String name = p.getName();
					int type = p.getMode().getValue();
					String dataTypeName = p.getDataType().getName();
					int dataType = DataTypeHelper.getJDBCTypeForNamedType(dataTypeName);
					int precision = 0;
					int scale = 0;
					if (p.getDataType() instanceof NumericalDataType)
					{
						precision = ((NumericalDataType)p.getDataType()).getPrecision();
					}
					if (p.getDataType() instanceof ExactNumericDataType)
					{
						scale = ((ExactNumericDataType)p.getDataType()).getScale();
					}
					//TODO get nullable?
					short nullable = DatabaseMetaData.attributeNullableUnknown;
					ParameterDescriptor pd = new ParameterDescriptor(_proc
							.getDatabaseIdentifier(), name, type, dataType,
							precision, (short) scale, dataTypeName, nullable,
							null);
					paramList.add(pd);
				}
            }
            _parameterDescriptors = (ParameterDescriptor[]) paramList.toArray(new ParameterDescriptor[paramList.size()]);
        }
        return _parameterDescriptors;	
    }

	public Map getParameterDefalutValues(String sql) throws SQLException {
		//TODO define defaultValue for Parameter sql model
		return new HashMap();
	}
	

    /**
     * get the JDBC connection that can be used. Caller should close this connection.
     * @return
     */
    protected Connection getConnection()
    {
        return _controlConn.getReusableConnection();
    }

}
