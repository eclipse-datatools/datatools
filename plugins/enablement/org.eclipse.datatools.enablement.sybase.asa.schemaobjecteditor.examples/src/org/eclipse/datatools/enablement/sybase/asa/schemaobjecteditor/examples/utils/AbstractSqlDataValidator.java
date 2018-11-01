/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;



/**
 * @author Shifeng Yu
 *
 */
public abstract class AbstractSqlDataValidator implements ISqlDataValidator,ISqlDataValidatorAssistant
{

    protected int _status;
    protected String _errorMessage;
    protected String _convertedValue;
    protected DatabaseIdentifier _databaseIdentifier;

    protected boolean needSupportExpression = true;
    /**
     * @param status
     * @param errorMessage
     * @param convertedValue
     * @param profileName
     */
    public AbstractSqlDataValidator(DatabaseIdentifier databaseIdentifier)
    {
        this._status = 0;
        this._errorMessage = null;
        this._convertedValue = null;
        this._databaseIdentifier = databaseIdentifier;
    }
    /**
     * @return Returns the errorMessage.
     */
    public String getErrorMessage()
    {
        return _errorMessage;
    }
    /**
     * @return Returns the status.
     */
    public int getStatus()
    {
        return _status;
    }
    /**
     * @return Returns the convertedValue.
     */
    public String getConvertedValue()
    {
        return _convertedValue;
    }
    
    public boolean isNeedSupportExpression()
    {
        return needSupportExpression;
    }
    
    public void setNeedSupportExpression(boolean needSupportExpression)
    {
        this.needSupportExpression = needSupportExpression;
    }
}
