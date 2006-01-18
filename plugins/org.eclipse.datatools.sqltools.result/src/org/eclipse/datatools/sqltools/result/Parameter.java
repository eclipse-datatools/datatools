/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.io.Serializable;

import org.eclipse.datatools.sqltools.result.internal.utils.Messages;
import org.eclipse.jface.util.Assert;

/**
 * The <code>Parameter</code> represents an in/out parameter for routine objects.
 * <p>
 * In <code>ResultsViewAPI</code> there is a method <code>showParameters(OperationCommand cmd, List params)</code>,
 * which is used to display in/out parameters of rountine objects, the item in the <code>params</code> should be
 * instance of <code>Parameter</code>.
 * 
 * @see org.eclipse.datatools.sqltools.result.ResultsViewAPI
 * @author Dafan Yang
 */
public class Parameter implements Serializable
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long  serialVersionUID = 1L;
    private String             _paramName;
    private String             _paramType;
    private String             _paramValue;
    private String             _paramDataType;
    /* parameter types */
    public static final String INPUT            = "INPUT"; //$NON-NLS-1$
    public static final String OUTPUT           = "OUTPUT"; //$NON-NLS-1$
    public static final String IN_OUT           = "IN/OUT"; //$NON-NLS-1$

    /**
     * Constructs a parameter. A simple validation will be performed during the construction
     * 
     * @param paramName name of parameter, can not be null or empty
     * @param paramType type of parameter, should be one of the three
     * @see #INPUT
     * @see #OUTPUT
     * @see #IN_OUT
     * @param paramValue value of this parameter
     * @param paramDataType data type of this parameter
     */
    public Parameter(String paramName, String paramType, String paramValue, String paramDataType)
    {
    	Assert.isLegal(paramName != null && !paramName.trim().equals(""), Messages.getString("Parameter.constructor.error")); //$NON-NLS-1$ //$NON-NLS-2$
    	Assert.isLegal(!(paramType == null
                || (!paramType.trim().equals(INPUT) && paramType.trim().equals(OUTPUT) && paramType.trim().equals(
                        IN_OUT))), Messages.getString("Parameter.constructor.error")); //$NON-NLS-1$
        _paramName = paramName;
        _paramType = paramType;
        _paramDataType = (paramDataType == null)?"":paramDataType; //$NON-NLS-1$
        _paramValue = (paramValue == null)?"":paramValue; //$NON-NLS-1$
    }

    /**
     * Returns the parameter's data type
     * @return the parameter's data type
     */
    public String getParamDataType()
    {
        return _paramDataType;
    }

    /**
     * Returns the parameter's name
     * @return the parameter's name
     */
    public String getParamName()
    {
        return _paramName;
    }

    /**
     * Returns the parameter's type
     * @see #INPUT
     * @see #OUTPUT
     * @see #IN_OUT
     * @return the parameter's type
     */
    public String getParamType()
    {
        return _paramType;
    }

    /**
     * Returns the parameter's value
     * @return the parameter's value
     */
    public String getParamValue()
    {
        return _paramValue;
    }
}
