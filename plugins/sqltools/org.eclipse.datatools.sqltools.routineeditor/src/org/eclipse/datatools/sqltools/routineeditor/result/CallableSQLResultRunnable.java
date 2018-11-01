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
package org.eclipse.datatools.sqltools.routineeditor.result;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.datatools.sqltools.editor.core.result.ResultSupportRunnable;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.Parameter;
import org.eclipse.datatools.sqltools.routineeditor.internal.RoutineEditorActivator;
import org.eclipse.datatools.sqltools.routineeditor.internal.result.Messages;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.parameter.ParameterInOutWrapper;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * This is a CallableSupportRunnalbe, used to run a callable SQL statement. It supports things like showing SP return
 * value, output parameter, etc.
 * 
 * @author Zhihong(Bryan) Yang
 */
public class CallableSQLResultRunnable extends ResultSupportRunnable
{

    protected Connection              _connection;
    protected String                  _sql;
    protected String                  _detailSql;
    protected boolean                 _closeCon;
    protected IConnectionTracker      _tracker;
    protected ILaunchConfiguration    _configuration;
    protected ParameterInOutWrapper[] _pws;
    protected String                  _procName;
    /**
     * 
     * @param con the connection
     * @param configuration the lauch configuration
     * @param closeCon whether should close connection
     * @param tracker if closeCon is true and tracker is not null, will notify it when close the connection
     */
    public CallableSQLResultRunnable(Connection con, ILaunchConfiguration configuration, boolean closeCon,
        IConnectionTracker tracker, DatabaseIdentifier databaseIdentifier) throws CoreException, SQLException, NoSuchProfileException
    {
        super(null, null, databaseIdentifier); //$NON-NLS-1$
        _connection = con;
        _configuration = configuration;
        _sql = LaunchHelper.constructCallableStatementSQLString(configuration);
        _detailSql = LaunchHelper.constructCallableStatementDisplayString(configuration);
        _closeCon = closeCon;
        _tracker = tracker;
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.launching.ResultSupportRunnable#getConnection()
     */
    protected Connection getConnection()
    {
        return _connection;
    }

    protected OperationCommand createDefaultOperationCommand() {
		return new OperationCommand(getActionType(), _detailSql, getConsumerName(), _databaseIdentifier.getProfileName(), _databaseIdentifier.getDBname());
	}

	public String getConsumerName() {
		if (_consumerName == null)
		{
			_consumerName = ""; //$NON-NLS-1$
		}
		return _consumerName;
	}

    /**
     * 
     * @param stmt callablestatement instance
     * @param pws parameter with in/out value arrays
     */
    protected void getStatementOutParam(Statement stmt, ParameterInOutWrapper[] pws)
    {
        if (!(stmt instanceof CallableStatement))
        return;
        CallableStatement cstmt = (CallableStatement) stmt;
        if (cstmt == null || pws == null)
        return;
        int j = 0;

        for (int i = 0; i < pws.length; i++)
        {
            int sqlType = pws[i].getParameterDescriptor().getSqlDataType();
            int paramType = pws[i].getParameterDescriptor().getParmType();
            String name = pws[i].getParameterDescriptor().getName();
            //TODO MO
//            if (paramType == ParameterMode.IN)
//            {
//                j++;
//            }
//            else if (paramType == ParameterMode.OUT || paramType == ParameterMode.INOUT)
            if (paramType == DatabaseMetaData.procedureColumnIn)
            {
                j++;
            }
            else if (//paramType == DatabaseMetaData.procedureColumnUnknown //FIXME treat UNKNOWN type as OUT type
             paramType == DatabaseMetaData.procedureColumnReturn
                || paramType == DatabaseMetaData.procedureColumnOut
                || paramType == DatabaseMetaData.procedureColumnInOut)
            {

                if (name.equals(_procName))
                {
                    pws[i].setOutValue(pws[0].getOutValue());
                    continue;
                }
                j++;
                try
                {

                    String outValue = "0x"; //$NON-NLS-1$
                    Object o = cstmt.getObject(j);
                    if (o != null)
                    {
                        if (o instanceof byte[])
                        {
                            byte[] os = (byte[])o;
                            outValue = SQLUtil.toHexString(os);
                        }
                        else
                        {
                            outValue = o.toString();
                        }
                    }
                    pws[i].setOutValue(outValue);
                }

                catch (SQLException e1)
                {
                    RoutineEditorActivator.getDefault().log("", e1);  //$NON-NLS-1$
                }
                catch (NumberFormatException e1)
                {
                	RoutineEditorActivator.getDefault().log("", e1);  //$NON-NLS-1$
                }
            }
        }

    }

    /**
     * 
     * @param stmt callablestatement instance
     * @param pws parameter with in/out value arrays
     */
    protected void registerOutParameter(Statement stmt, ParameterInOutWrapper[] pws) throws SQLException
    {
        if (!(stmt instanceof CallableStatement))
        return;

        CallableStatement cstmt = (CallableStatement) stmt;
        if (cstmt == null || pws == null)
        {
        	return;
        }
        int j = 0;
        
        int returnParamIndex = _sql.indexOf("{?");
        if (returnParamIndex >= 0)
        {
        	j++;//return parameter should be the first
        }
        for (int i = 0; i < pws.length; i++)
        {
            int sqlType = pws[i].getParameterDescriptor().getSqlDataType();
            String sqlTypeName = pws[i].getParameterDescriptor().getTypeName();
            int paramType = pws[i].getParameterDescriptor().getParmType();
            String paramTypeName = pws[i].getParameterDescriptor().getParamTypeAsString();
            String name = pws[i].getParameterDescriptor().getName();
            //TODO MO
//            if (paramType == ParameterMode.IN)
//            {
//                j++;
//            }
//            else if (paramType == ParameterMode.OUT || paramType == ParameterMode.INOUT)
            if (paramType == DatabaseMetaData.procedureColumnIn)
            {
                j++;
            }
            else if (paramType == DatabaseMetaData.procedureColumnReturn && returnParamIndex >= 0)
            {
            	cstmt.registerOutParameter(1, sqlType);
            }
            else if (paramType == DatabaseMetaData.procedureColumnUnknown //FIXME treat UNKNOWN type as OUT type
                || paramType == DatabaseMetaData.procedureColumnOut
                || paramType == DatabaseMetaData.procedureColumnInOut)
            {
                if (name.equals(_procName))
                {
                    continue;
                }
                j++;
                cstmt.registerOutParameter(j, sqlType);
            }
        }
    }

    /**
     * 
     * @param stmt callablestatement instance
     * @param pws parameter with in/out value arrays
     */
    protected void setInParameter(Statement stmt, ParameterInOutWrapper[] pws) throws SQLException
    {
        if (!(stmt instanceof CallableStatement))
        return;

        CallableStatement cstmt = (CallableStatement) stmt;
        if (cstmt == null || pws == null)
        return;

        int j = 0;
        int returnParamIndex = _sql.indexOf("{?");
        if (returnParamIndex >= 0)
        {
        	j++;//return parameter should be the first
        }
        
        int k = 0;
        List values = null;
        try
        {
            values = LaunchHelper.readParameterList(_configuration);
        }
        catch (CoreException e)
        {
        	RoutineEditorActivator.getDefault().log(e);
        }
        DBHelper helper = SQLToolsFacade.getConfiguration(null, getDatabaseIdentifier()).getDBHelper();
        for (int i = 0; i < pws.length; i++)
        {
            int sqlType = pws[i].getParameterDescriptor().getSqlDataType();
            int paramType = pws[i].getParameterDescriptor().getParmType();
            if (helper != null)
            {
                paramType = helper.getCorrectParamType(paramType);
            }
            String paramTypeName = pws[i].getParameterDescriptor().getParamTypeAsString();
            String name = pws[i].getParameterDescriptor().getName();

            //TODO MO
//            if (paramType == ParameterMode.OUT)
//            {
//                j++;
//            }
//            else if (paramType == ParameterMode.IN
//            || paramType == ParameterMode.INOUT)
            if (paramType == DatabaseMetaData.procedureColumnReturn)
            {
            	//we have take return parameter into account
                continue;
            }
            if (paramType == DatabaseMetaData.procedureColumnOut || paramType == DatabaseMetaData.procedureColumnReturn)
            {
            	j++;
            }
            else if (paramType == DatabaseMetaData.procedureColumnIn
            || paramType == DatabaseMetaData.procedureColumnInOut)
            {
                j++;
                String value = null;
                if (values != null && k < values.size())
                {
                    value = (String) values.get(k++);
                }
                pws[i].setInValue(value);

                if (value==null) 
                {
                    cstmt.setNull(j,sqlType);
                }
                else 
                {
                    Object v = null;
                    try 
                    {
                        ISqlDataValidator validator = SQLToolsFacade.getConfiguration(null, getDatabaseIdentifier()).getSQLDataService().getSQLDataValidator(getDatabaseIdentifier());
                        v = validator.convert(pws[i].getParameterDescriptor().getTypeName(), value, getDatabaseIdentifier().getProfileName(), getDatabaseIdentifier().getDBname());
                    }
                    catch (Exception e1) 
                    {
                        v = value;
                    }
                    try 
                    {
                        cstmt.setObject(j, v, sqlType);
                    }
                    catch (Exception e) 
                    {
                        cstmt.setObject(j, v);
                    }
                }
            }
        }
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.launching.ResultSupportRunnable#prepareStatement(java.sql.Connection)
	 */
    protected Statement prepareStatement(Connection connection) throws SQLException
    {
        CallableStatement cstmt = connection.prepareCall(_sql);
        if (_configuration != null)
        {
            ProcIdentifier proc;
            try
            {
                proc = LaunchHelper.readProcIdentifier(_configuration);
                if (proc != null)
                {
                    _procName = proc.getProcName();
                    _pws = LaunchHelper.getAllParameterWrappersByOrder(proc);
                    setInParameter(cstmt, _pws);
                    registerOutParameter(cstmt, _pws);

                }
            }
            catch (NoSuchProfileException e1)
            {
            	RoutineEditorActivator.getDefault().log("", e1);  //$NON-NLS-1$
            }
            catch (CoreException e)
            {
            	RoutineEditorActivator.getDefault().log("", e);  //$NON-NLS-1$
            }
        }
        return cstmt;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.launching.ResultSupportRunnable#runStatement(java.sql.Statement)
     */
    protected boolean runStatement(Statement stmt) throws SQLException
    {
        if (stmt != null && stmt instanceof CallableStatement)
        {
            CallableStatement cstmt = (CallableStatement) stmt;
            return cstmt.execute();
        }
        return false;
    }

    protected boolean handleSuccess(boolean moreResult)
    {
        boolean success = super.handleSuccess(moreResult);
        if (_pws != null)
        {
        	getStatementOutParam(_stmt, _pws);
        	synchronized (getOperationCommand())
        	{
        		
                resultsViewAPI.appendStatusMessage(getOperationCommand(), getReturnStatusString());
        		resultsViewAPI.showParameters(getOperationCommand(), convert(_pws));
        	}
        }
		return success;
    }
    
    protected String getReturnStatusString()
    {
        StringBuffer buffer = new StringBuffer(""); //$NON-NLS-1$
        for (int i = 0; i < _pws.length; i++)
        {
            int paramType = _pws[i].getParameterDescriptor().getParmType();
            String paramName = _pws[i].getParameterDescriptor().getName();
            String outValue = _pws[i].getOutValue();
            if (paramType == DatabaseMetaData.procedureColumnReturn)
            {
                buffer.append(Messages.bind(Messages.CallableSQLResultRunnable_return_status, outValue));
            }
        }
        return buffer.toString();
    }
    /**
     * Converts ParameterInOutWrapper to list of Parameter required by results view
     * @param pws
     * @return
     */
    private List convert(ParameterInOutWrapper[] pws)
    {
    	ArrayList params = new ArrayList();
    	for (int i = 0; i < pws.length; i++) {
    		ParameterDescriptor pd = pws[i].getParameterDescriptor();
            //filter out result and return parameters
            if (pd.getParmType() == DatabaseMetaData.procedureColumnResult || pd.getParmType() == DatabaseMetaData.procedureColumnReturn)
            {
                continue;
            }
			Parameter param = new Parameter(pd.getName(), pd.getParamTypeAsString(), pws[i].getInValue(), pd.getTypeName(), pws[i].getOutValue());
			params.add(param);
		}
    	return params;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.launching.ResultSupportRunnable#handleEnd(java.sql.Connection, java.sql.Statement)
     */
    protected void handleEnd(Connection connection, Statement stmt)
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();    
            }
            //Hui Cao: since terminating is a separate thread, even though connection is not closed during the evaluation, it will be later.
            //so as long as user gestures to terminate, we won't do the cleaning job again here. 
            if (connection != null && !isTerminated() && !isCanceled())
            {
                if (_closeCon)
                {
                    ProfileUtil.closeConnection(this._databaseIdentifier.getProfileName(), this._databaseIdentifier.getDBname(), connection);
                }
            }
        }
        catch (Exception e)
        {
            // ignore
        }

        if (_closeCon)
        {
            if (_tracker != null)
            {
                _tracker.connectionClosed();
            }
        }
    }

    public static char byteToChar(byte[] b)
    {
        int s=0;
        if(b[0]>0)
        s+=b[0];
        else
        s+=256+b[0];
        s*=256;
        if(b[1]>0)
        s+=b[1];
        else
        s+=256+b[1];
        char ch=(char)s;
        return ch;
    }

    public ILaunchConfiguration getConfiguration()
    {
        return _configuration;
    }
}
