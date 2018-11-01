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
package org.eclipse.datatools.sqltools.core.dbitem;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;

/**
 * Wraps the <code>ParameterDescriptor</code> by adding parameter value,
 * parameter listener and validator support.
 * 
 * @author Yang Liu
 */
public class ParameterWrapper extends RowData implements IParamListenerManager
{
    ParameterDescriptor _pd;

    private boolean     _isnull    = true;

    private String      _value     = null;

    ListenerList        _listeners = new ListenerList();

    private String      _message     = null;

    private int _status = 0;

    public ParameterWrapper(ParameterDescriptor pd)
    {
        this._pd = pd;
    }

    public ParameterDescriptor getParameterDescriptor()
    {
        return _pd;
    }

    /**
     * @return The parameter value
     */
    public String getValue()
    {
        return _value;
    }

    /**
	 * Sets the parameter value. The value will be converted to proper format.
	 * If it's invalid, use getMessage() to get the error message.
	 * 
	 */
    public void setValue(String value)
    {
        _value = validate(value);
        fireAllUpdated(_pd, _value);
    }

    /**
     * Tells whether the parameter value is null. 
     * @return
     */
    public boolean isNull()
    {
        return _isnull;
    }

    /**
     * Sets the parameter value to null. 
     * @return
     */
    public void setNull(boolean b)
    {
        _isnull = b;
        //here, the message should not be set as null,because null value doesn't means it will pass validation.
        //_message = _isnull? null: _message;
    }

    public void addParamListener(IParamListener listener)
    {
        _listeners.add(listener);
    }

    public void removeParamListener(IParamListener listener)
    {
        _listeners.remove(listener);
    }

    protected void fireAllUpdated(ParameterDescriptor pd, String value)
    {
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((IParamListener) listeners[i]).paramValueUpdated(pd, value);
        }
    }


    /**
     * @return Returns the error message.
     */
    public String getMessage()
    {
        return _message;
    }

    /**
     * Sets the parameter value status. @see ISqlDataValidator
     */
    public void setStatus(int status)
    {
        this._status = status;
    }

    /**
     * @param _message The error message to set.
     */
    public void setMessage(String _message)
    {
        this._message = _message;
    }
    /**
     * Here we should use special validator to validate the value.
     * Then we set the errorMessage according to the status.
     * 
     * @param value the original value
     * @return the converted value
     */
    private String validate(String value)
    {
        String result = null;
        try
        {
            ISqlDataValidator validator = SQLToolsFacade.getConfiguration(null, _pd.getDatabaseIdentifier()).getSQLDataService().getSQLDataValidator(_pd.getDatabaseIdentifier());
            if (validator == null)
            {
            	return value;
            }
            _status = validator.validate(_pd.getTypeName(),value);
            switch(_status)
            {
                case ISqlDataValidator.VALIDATE_SUCCESS:
                    {
                        result = validator.getConvertedValue();
                        this.setMessage(null);
                        break;
                    }
                case ISqlDataValidator.VALIDATE_FAIL_CONVERT_SUCCESS:
                    {
                        result = validator.getConvertedValue();
                        this.setMessage(validator.getErrorMessage());
                        break;
                    }
                case ISqlDataValidator.CONVERT_FAIL:
                    {
                        result = value;
                        this.setMessage(validator.getErrorMessage());
                        break;
                    }
                case ISqlDataValidator.SYS_ERROR:
                    {
                        result = value;
                        this.setMessage(validator.getErrorMessage());
                        break;
                    }
            }
        }
        catch (Exception e)
        {
            String errormes = e.getLocalizedMessage();
            errormes = errormes == null?"":errormes ;
            int i = errormes.lastIndexOf(":"); //$NON-NLS-1$
            if (i > 0)
            {
                errormes = errormes.substring(i + 1, errormes.length());
            }
            this.setMessage(this.getMessage()+errormes);
        }
        return result;
    }
    /**
     * Returns the parameter value status. @see ISqlDataValidator
     * @return Returns the _status.
     */
    public int getStatus()
    {
        return _status;
    }
    
    public void setData(Object[] data)
    {
        _oldData = data;
        _newData = (Object[])data.clone();
        _state = STATE_ORIGINAL;
    }

    public void setTableData(ITableData data)
    {
        _tableData = data;
    }

    public void updateValue(int col, Object value)
    {
        if(col == ParametersData.VALUE_COLUMN)
        {
            if (value == null || value.toString().trim().equals(""))
            {
                _isnull = true;
                _value = validate(null);
                super.updateValue(ParametersData.NULL_COLUMN, "true");
                fireAllUpdated(_pd, _value);
                super.updateValue(col, _value);
            }
            else
            {
                _value = validate((String) value);
                if (_value != null)
                {
                    _isnull = false;
                    super.updateValue(ParametersData.NULL_COLUMN, "false");
                }
                fireAllUpdated(_pd, _value);
                super.updateValue(col, _value);
            }
        }
        else if (col == ParametersData.NULL_COLUMN)
        {
            _isnull = value.toString().equals("true") ? true : false;
            if (_isnull)
            {
                _value = validate(null);
                super.updateValue(ParametersData.VALUE_COLUMN, _value);
            }
            fireAllUpdated(_pd, _value);
            super.updateValue(col, value);
        }
        else
        {
            super.updateValue(col, value);
        }
    }

}
