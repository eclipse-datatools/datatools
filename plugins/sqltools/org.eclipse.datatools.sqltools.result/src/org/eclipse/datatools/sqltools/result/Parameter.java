/*******************************************************************************
 * Copyright 2005, 2009 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.io.Serializable;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.sqltools.result.internal.utils.Messages;

/**
 * The <code>Parameter</code> represents an in/out parameter for routine objects.
 * <p>
 * In <code>ResultsViewAPI</code> there is a method <code>showParameters(OperationCommand cmd, List params)</code>,
 * which is used to display in/out parameters of rountine objects, the item in the <code>params</code> should be
 * instance of <code>Parameter</code>.
 * 
 * @see org.eclipse.datatools.sqltools.result.ui.ResultsViewAPI
 * @author Dafan Yang
 */
public class Parameter implements Serializable
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long  serialVersionUID = 1L;
    /* Name of this parameter */
    private String             _paramName;
    /* Type of this parameter */
    private String             _paramType;
    /* Value of this parameter, it is IN value by default */
    private String             _paramValue;
    /* OUT value of this parameter */
    private String             _paramOutValue;
    /* Data type of this parameter */
    private String             _paramDataType;
    /* parameter types */
    public static final String INPUT            = "INPUT"; //$NON-NLS-1$
    public static final String OUTPUT           = "OUTPUT"; //$NON-NLS-1$
    public static final String IN_OUT           = "IN/OUT"; //$NON-NLS-1$
    public static final String RETURN           = "RETURN"; //$NON-NLS-1$
    public static final String RESULT           = "RESULT"; //$NON-NLS-1$
    public static final String UNKNOWN           = "UNKNOWN"; //$NON-NLS-1$

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
    	Assert.isLegal(paramName != null && !paramName.trim().equals(""), Messages.Parameter_constructor_error); 
    	Assert.isLegal((paramType != null
                && (paramType.trim().equals(INPUT) || paramType.trim().equals(OUTPUT) || paramType.trim().equals(
                        IN_OUT)|| paramType.trim().equals(RETURN) || paramType.trim().equals(RESULT) || paramType
                .trim().equals(UNKNOWN))), Messages.Parameter_constructor_error);
        _paramName = paramName;
        _paramType = paramType;
        _paramDataType = (paramDataType == null)?"":paramDataType; //$NON-NLS-1$
        _paramValue = (paramValue == null)?"":paramValue; //$NON-NLS-1$
        _paramOutValue = "";
    }
    
    /**
     * Constructor
     * @param paramOutValue out value of this parameter
     */
    public Parameter(String paramName, String paramType, String paramValue, String paramDataType, String paramOutValue)
    {
        this(paramName, paramType, paramValue, paramDataType);
        _paramOutValue = (paramOutValue == null ? "" : paramOutValue);
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
    	// The presence of a null character in the parameter string causes the string to truncate. 
    	// Replace it with an alternate character.  \uFFFD is the Unicode Replacement 
    	// Character, used to replace a character whose value is unknown or unrepresentable.
        return _paramValue.replaceAll("\u0000", "\uFFFD"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Returns the OUT value of this parameter
     * @return the OUT value of this parameter
     */
    public String getParamOutValue()
    {
        return _paramOutValue.replaceAll("\u0000", "\uFFFD"); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
