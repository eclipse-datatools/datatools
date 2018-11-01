/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core.internal.dbitem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.EngineeringOptionID;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.IDBItem;
import org.eclipse.datatools.sqltools.core.dbitem.IItemWithCode;
import org.eclipse.datatools.sqltools.core.dbitem.ISPUDF;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sql.util.ParameterUtil;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;



/**
 * An adapter that makes <code>Routine</code>/<code>Trigger</code> and
 * <code>IControlConnection</code> work together.
 * TODO: event support
 * @author Hui Cao
 * 
 */
public class SQLObjectItem implements IDBItem, IItemWithCode, ISPUDF {

	protected ProcIdentifier _proc = null;

    protected SQLObject _routine = null;

    protected IControlConnection _controlConn = null;
	
    protected ParameterDescriptor[] _parameterDescriptors = null;

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
		_parameterDescriptors = null; 
	}

	public String getCode() throws SQLException {
		String code = "";
		Database database = ModelUtil.getDatabase(ModelUtil.getSchema(_routine));
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(
						database.getVendor(), database.getVersion());
		
		DDLGenerator ddlg = databaseDefinition.getDDLGenerator();
		if (ddlg != null) {
			SQLObject[] sqlElements = new SQLObject[] { _routine };
			EngineeringOption[] opts = ddlg.getOptions(sqlElements);
			boolean quotedId = SQLDevToolsUtil.getQuotedIdentifier(_proc.getDatabaseIdentifier());
			EngineeringOptionID.setOptionValueByID(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER,opts,quotedId);

			String[] ddl = ddlg.generateDDL(sqlElements, null);
			if (ddl != null && ddl.length > 0) {
				code = ddl[0];
			}
		}
		return code;
	}

	public void save(String code) throws SQLException {
		DDLGenerator ddlg = ProfileUtil.getDatabaseDefinition(
				_proc.getProfileName()).getDDLGenerator();
		if (ddlg != null) {
			SQLObject[] sqlElements = new SQLObject[] { _routine };
			EngineeringOption[] opts = ddlg.getOptions(sqlElements);
			String[] drop = ddlg
					.dropSQLObjects(
							sqlElements,
							EngineeringOptionID.getOptionValueByID(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER,opts),
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
			SQLObject[] sqlElements = new SQLObject[] { _routine };
			EngineeringOption[] opts = ddlg.getOptions(sqlElements);
			String[] drop = ddlg
					.dropSQLObjects(
							sqlElements,
							SQLDevToolsUtil.isQuotedIdentifierOn(_proc.getDatabaseIdentifier()),
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
//        if (_parameterDescriptors == null)
//        {
//        	ArrayList paramList = new ArrayList();
//            if (_routine instanceof Routine)
//            {
//            	Routine r = (Routine)_routine;
//            	EList ps = r.getParameters();
//            	for (Iterator iter = ps.iterator(); iter.hasNext();) {
//					Parameter p = (Parameter) iter.next();
//					String name = p.getName();
//					int type = p.getMode().getValue();
//					String dataTypeName = p.getDataType().getName();
//					int dataType = DataTypeHelper.getJDBCTypeForNamedType(dataTypeName);
//					int precision = 0;
//					int scale = 0;
//					if (p.getDataType() instanceof NumericalDataType)
//					{
//						precision = ((NumericalDataType)p.getDataType()).getPrecision();
//					}
//					if (p.getDataType() instanceof ExactNumericDataType)
//					{
//						scale = ((ExactNumericDataType)p.getDataType()).getScale();
//					}
//					//TODO get nullable?
//					short nullable = DatabaseMetaData.attributeNullableUnknown;
//					ParameterDescriptor pd = new ParameterDescriptor(_proc
//							.getDatabaseIdentifier(), name, type, dataType,
//							precision, (short) scale, dataTypeName, nullable,
//							null);
//					paramList.add(pd);
//				}
//            }
//            _parameterDescriptors = (ParameterDescriptor[]) paramList.toArray(new ParameterDescriptor[paramList.size()]);
//        }
//        return _parameterDescriptors;	
        if (_parameterDescriptors == null && _routine instanceof Routine)
        {
            Connection conn = null;
            conn = getConnection();
            _parameterDescriptors = ParameterUtil.getParameterDescriptors(getControlConnection().getDatabaseIdentifier(),
            		conn, getProcIdentifier().getType(), getProcIdentifier());

            String sql = this.getCode();
            Map defaultparamvalue = getParameterDefalutValues(sql);
            Map typeNames = getSPParamTypeNameMapFromParser(sql);
            if (_parameterDescriptors!=null) 
            {
                for (int i=0;i<_parameterDescriptors.length;i++) 
                {
                    ParameterDescriptor param = _parameterDescriptors[i];
                    // get sql type name from editor parser
                    String typeName = (String)typeNames.get(param.getName());
                    if (typeName!=null)
                    {
                        param.setSqlTypeNameFromParser(typeName);
                    }
                    if (defaultparamvalue!=null) 
                    {
                        String value = (String)defaultparamvalue.get(param.getName());
                        if (value!=null)
                        {
                            if (SQLUtil.isStringType(param.getSqlDataType()))
                            {
                                param.setDefaultValue(SQLUtil.unquote(value));
                            }
                            else
                            {
                                param.setDefaultValue(value);
                            }
                        }
                        else
                        {
                        	//BZ 131539: Java routine don't allow passing null to numeric parameters
                        	if (_routine instanceof Routine) {
    							Routine r = (Routine) _routine;
    							if (SQLUtil.isNumericType(param.getSqlDataType())
    									&& "JAVA".equalsIgnoreCase(r.getLanguage())) {
    								param.setDefaultValue("0");
    							}
    						}
                        }
                    }
                    
                }
            }
        }
        if (_parameterDescriptors == null)
        {
        	_parameterDescriptors = new ParameterDescriptor[0];
        }
        return _parameterDescriptors;		
    }

	public Map getParameterDefalutValues(String sql) throws SQLException {
		if (sql!=null && !"".equals(sql.trim())) 
        {
            //call parser to get parameter default value
            Map prameterValueMap = ParameterUtil.getSPParamDefaultValues(getControlConnection().getDatabaseIdentifier(),sql);
            return prameterValueMap;
        }
		return new HashMap();
	}
	
	public Map getSPParamTypeNameMapFromParser(String sql) throws SQLException
    {
        if (sql!=null && !"".equals(sql.trim())) 
        {
            //call parser to get parameter default value
            Map typeNames = ParameterUtil.getSPParamTypeNameMapFromParser(getControlConnection().getDatabaseIdentifier(),sql);
            return typeNames;
        }
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
