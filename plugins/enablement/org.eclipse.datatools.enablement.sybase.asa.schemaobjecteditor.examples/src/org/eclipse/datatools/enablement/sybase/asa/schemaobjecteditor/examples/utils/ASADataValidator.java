/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.Messages;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.SQLDataTypes;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.IDatatype;
import org.eclipse.datatools.sqltools.sql.util.HexHelper;
import org.eclipse.osgi.util.NLS;


/**
 * @author Shifeng Yu
 */
public class ASADataValidator extends AbstractSqlDataValidator
{
    private static ILog _log = ExamplePlugin.getDefault().getLog();

    /**
     * ASA data types which have no width and scale in type name.
     * For example, "bigint" is valid, "bigint(4)" is invalid.
     */
    private static final String[] _basetypes1 =
    {
        "BIGINT",     
            "BIT", 
            "DATE", 
            "DEC", 
            "DOUBLE", 
            "FLOAT", 
            "INTEGER", 
            "INT", 
            "LONG VARCHAR",
            "LONG BINARY",
            "REAL", 
            "SMALLINT", 
            "TIME", 
            "TIMESTAMP", 
            "TINYINT", 
            "UNSIGNED BIGINT", 
            "UNSIGNED INT",
            "UNSIGNED SMALLINT"
    }
    ;


    /**
     * ASA data types which have width and scale in type name.
     * For example, "char(5)", "numeric(6,3)"
     */
    private static final String[] _basetypes2 =
    {
        "BINARY", 
            "CHAR", 
            "CHARACTER", 
            "DECIMAL", 
            "NUMERIC", 
            "VARBINARY", 
            "VARCHAR"
    }
    ;

    /**
     * Whether the data type has not scale and precision.
     * For example, NUMERIC has scale and precision and INT has not.
     * @param typeName
     * @return
     */
    public static boolean isTypeWithoutWS(String typeName)
    {
        for(int i =0; i < _basetypes1.length;i++)
        {
            if(_basetypes1[i].equalsIgnoreCase(typeName))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether the data type has scale and precision.
     * For example, NUMERIC has scale and precision and INT has not.
     * @param typeName
     * @return
     */
    public static boolean isTypeWithWS(String typeName)
    {
        for(int i =0; i < _basetypes2.length;i++)
        {
            if(_basetypes2[i].equalsIgnoreCase(typeName))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param profileName
     */
    public ASADataValidator(DatabaseIdentifier databaseIdentifier)
    {
        super(databaseIdentifier);
        this._status = ISqlDataValidator.VALIDATE_SUCCESS;
    }

    /**
     * Here we use the select convert method to validate the inputValue. If the inputValue passes the convert operation
     * (including truncated case), we show the converted value to user. Otherwise, we will get an error and show error 
     * message to user and force user to reedit his input.
     */
    public int validate(String dataType, String inputValue)
    {
        //for the moment, we accept null value for all kinds of datatypes
        if(inputValue == null)
        {
            this._status = ISqlDataValidator.VALIDATE_SUCCESS;
            this._errorMessage = null;
            return _status;
        }

        IControlConnection controlCon = null;
        IDatatype currentType = null;
        try
        {
            controlCon = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(
                _databaseIdentifier);
            currentType = controlCon.getTypeByNameStr(dataType);
        }
        catch (SQLException e)
        {
            this._status = ISqlDataValidator.SYS_ERROR;
            this._errorMessage = e.getMessage();
            this._convertedValue = inputValue;
            return this._status;
        }
        catch (NoSuchProfileException e)
        {
            this._status = ISqlDataValidator.SYS_ERROR;
            this._errorMessage = e.getMessage();
            this._convertedValue = inputValue;
            return this._status;
        }
        catch (Exception e)
        {
            this._status = ISqlDataValidator.SYS_ERROR;
            this._errorMessage = e.getMessage();
            this._convertedValue = inputValue;
            _log.log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, e.getMessage()));
            return this._status;
        }
        if(currentType != null)
        {
            if (SQLUtil.isStringType(dataType))
            {
                String unquote = SQLUtil.unquote(inputValue);
                // if it's quoted, unquote first before validate
                if (!inputValue.equals(unquote))
                {
                    inputValue = unquote;
                }
            }
            return validate(currentType,inputValue);
        }
        else
        {
            this._status = ISqlDataValidator.SYS_ERROR;
            this._errorMessage = NLS.bind(Messages.DataValidation_createDataTypeError, dataType);
            this._convertedValue = inputValue;
            return this._status;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.objectviewer.Utils.ISqlDataValidator#validate(com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype,
     *      java.lang.String, java.lang.String)
     */
    public int validate(IDatatype dataType, String inputValue)
    {
        //for the moment, we accept null value for all kinds of datatypes
        if(inputValue == null)
        {
            this._status = ISqlDataValidator.VALIDATE_SUCCESS;
            this._errorMessage = null;
            return _status;
        }

        Object o = null;
        try
        {
            String conValue = inputValue;

            o = convert(dataType.toString(),inputValue,_databaseIdentifier.getProfileName(), _databaseIdentifier.getDBname());
            if(o == null)
            {
                conValue = null;
            }
            else if (o instanceof byte[])
            {
                byte[] os = (byte[]) o;
                conValue = HexHelper.toHexString(os);
            }
            else if(o instanceof Boolean)
            {
                o = (Boolean) o;
                if(o.toString()=="false")
                {
                    conValue = "0";
                }
                else
                {
                    conValue = "1";
                }
            }
            else    
            {
                conValue = o.toString();
            }
            String typeName =null;
            if(dataType.isUDT())
            {
                typeName = ((DBObject)dataType.getBaseType()).getName();
            }
            else
            {
                typeName = ((DBObject)dataType).getName();
            }
            
            if(typeName.equalsIgnoreCase(SQLDataTypes.DATE) && conValue.indexOf(' ')>0)
            {
                conValue = conValue.substring(0,conValue.indexOf(' '));
            }
            if(typeName.equalsIgnoreCase(SQLDataTypes.TIME) && conValue.indexOf(' ')>0 && conValue.lastIndexOf('.')>0)
            {
                conValue = conValue.substring(conValue.indexOf(' ')+1,conValue.lastIndexOf('.'));
            }
            this._convertedValue = conValue;
            
            if (needSupportExpression)
            {
                if (inputValue.indexOf(conValue) > 0)
                {
                    this._convertedValue = conValue;
                }
                else if (o instanceof byte[] || o instanceof Boolean)
                {
                    this._convertedValue = conValue;
                }
                else
                {
                    this._convertedValue = inputValue;
                }
            }
            

            if(inputValue == null)
            {
                if(_convertedValue == null)
                {
                    this._status = ISqlDataValidator.VALIDATE_SUCCESS;
                    this._errorMessage = null;
                    return _status;
                }
                else
                {
                    this._status = ISqlDataValidator.VALIDATE_FAIL_CONVERT_SUCCESS;
                    this._errorMessage = Messages.DataValidation_warnMessage;
                    return _status;
                }
            }
            else if(inputValue.equalsIgnoreCase(this._convertedValue))
            {
                this._status = ISqlDataValidator.VALIDATE_SUCCESS;
                this._errorMessage = null;
                return _status;
            }
            
            else
            {
                this._status = ISqlDataValidator.VALIDATE_FAIL_CONVERT_SUCCESS;
                this._errorMessage = Messages.DataValidation_warnMessage;
                return _status;
            }
        }
        catch (SQLException e)
        {
            this._status = ISqlDataValidator.CONVERT_FAIL;
            this._errorMessage = e.getMessage();
            this._convertedValue = inputValue;
        }
        catch (NoSuchProfileException e)
        {
            this._status = ISqlDataValidator.SYS_ERROR;
            this._errorMessage = e.getMessage();
            this._convertedValue = inputValue;
        }
        catch (Exception e)
        {
            this._status = ISqlDataValidator.CONVERT_FAIL;
            this._errorMessage = e.getMessage();
            this._convertedValue = inputValue;
        }

        return _status;

    }

    /**
     * This method is used to judge whether user's input need quote before do validation.
     * @param dataType
     * @return
     */
    public static boolean needQuote(String dataType)
    {
        if(dataType == null)
        {
            return false;
        }
        dataType = dataType.toLowerCase();
        if (dataType.equals(SQLDataTypes.CHAR) || dataType.equals(SQLDataTypes.CHARACTER)
        || dataType.equals(SQLDataTypes.LONG_VARCHAR) || dataType.equals(SQLDataTypes.VARCHAR)
            || dataType.equals(SQLDataTypes.CHARACTER_VARYING))
        {
            return true;
        }
        return false;
    }

    public Object convert(String dataType,String inputValue,String profileName, String dbName) throws Exception
    {
        String inputValueUnquote = null;
        DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(profileName, dbName);
        Object o = null;
        String typeName = null;
        int p1 = dataType.indexOf("(");
        if(p1>0)
        {
            typeName = dataType.substring(0,p1);
        }
        else
        {
            typeName = dataType;
        }
        if(inputValue != null)
        {
            inputValueUnquote = SQLUtil.unquote(inputValue);
            if(needQuote(typeName))
            {
                inputValue = SQLUtil.quote(inputValue,"'");
            }
            inputValue = SQLUtil.getQuotedString(dataType,inputValue, databaseIdentifier);
        }
        String strType = "";
        strType = dataType.toString();
        Statement stmt =null;
        ResultSet rs = null;
        Connection cn = null;
        try
        {
            cn = SQLUtil.getConnection(databaseIdentifier);
            stmt = cn.createStatement();
            String sql = createQuerySQL(dataType, inputValue, strType,true);
            rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                o = rs.getObject(1);
            }
        }
        catch(Exception e)
        {
            /*Here we convert a unquoted input value by server, try to use expression
             * or function as input value, such as using getDate() function, which should not be quoted. For CR468495-1
             */
            try
            {
                stmt = cn.createStatement();
                String sql = createQuerySQL(dataType, inputValueUnquote, strType,false);
                rs = stmt.executeQuery(sql);

                while (rs.next())
                {
                    o = rs.getObject(1);
                }
            }
            catch (Exception ex)
            {
                throw e;
            }
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (Exception e)
                {
                    // ignore
                    _log.log(new Status(IStatus.WARNING, ExamplePlugin.PLUGIN_ID, Messages.common_ignoreException));
                }
            }

            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch (Exception e)
                {
                    // ignore
                    _log.log(new Status(IStatus.WARNING, ExamplePlugin.PLUGIN_ID, Messages.common_ignoreException));
                }
            }

        }
        return o;

    }

    private String createQuerySQL(String dataType, String inputValue, String strType, boolean needQuoted)
    {
    	StringBuffer query = new StringBuffer();
        if(inputValue == null || inputValue.startsWith("0x") || SQLUtil.isStringType(dataType))
        {
           query.append("select convert(").append(strType).append(",").append(inputValue).append(")");
        }
        else
        {
        	if(needQuoted)
        	{
               query.append("select convert(").append(strType).append(",'").append(inputValue).append("')");
        	}
        	else
        	{
        	   query.append("select convert(").append(strType).append(",").append(inputValue).append(")");
        	}
        }
        return query.toString();
    }
}
