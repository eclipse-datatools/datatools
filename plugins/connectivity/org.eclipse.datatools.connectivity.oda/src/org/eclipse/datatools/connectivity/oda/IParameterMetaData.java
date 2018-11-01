/*
 *************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda;

/**
 * An interface that represents the meta-data 
 * of input/output parameters defined in a prepared query.
 * Its implementation is required only if the driver supports 
 * query parameters. 
 * <p>
 * <b>Note:</b> All parameter indices in this interface are 1-based.
 */
public interface IParameterMetaData
{
	/**
	 * The constant indicating that the input/output mode of the 
	 * parameter is unknown.
	 */
	public static final int parameterModeUnknown = 0;

	/**
	 * The constant indicating that the parameter is an input parameter.
	 */
	public static final int parameterModeIn = 1;
	
	/**
	 * The constant indicating that the parameter is both input and output.
	 */
	public static final int parameterModeInOut = 2;
	
	/**
	 * The constant indicating that the parameter is an output parameter.
	 */
	public static final int parameterModeOut = 3;
	
	/**
	 * The constant indicating that the nullability of the parameter is 
	 * unknown.
	 */
	public static final int parameterNullableUnknown = 0;
	
	/**
	 * The constant indicating that the parameter will not allow NULL
	 * values.
	 */
	public static final int parameterNoNulls = 1;
	
	/**
	 * The constant indicating that the parameter will allow NULL values.
	 */
	public static final int parameterNullable = 2;
	
	/**
	 * Returns the number of parameters defined in the 
	 * prepared IQuery object.
	 * @return	the number of parameters.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getParameterCount() throws OdaException;
	
	/**
	 * Returns the input/output mode of the specified parameter.
	 * @param param	1-based index of the parameter.
	 * @return		the input/output mode of the parameter;<br>
	 * 				one of parameterModeUnknown,<br>
	 * 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 				parameterModeIn,<br>
	 * 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 				parameterModeInOut,<br>
	 * 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 				parameterModeOut.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getParameterMode( int param ) throws OdaException;
    
    /**
     * Returns the name of the specific parameter.  
     * Optional method; a parameter may or may not have a name.
     * @param param 1-based index of the parameter.
     * @return      the parameter name, or 
     *              null if the name is not available or this parameter is not named.
     * @throws OdaException     if data source error occurs.
     * @since 3.1
     */
    public String getParameterName( int param ) throws OdaException;
	
	/**
	 * Returns the data provider specific code of the parameter's data type.
	 * The valid values are implementation-specific.
	 * @param param	1-based index of the parameter.
	 * @return		the native data type code of the parameter.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getParameterType( int param ) throws OdaException;
	
	/**
	 * Returns the data provider specific name of the parameter's data type.
	 * @param param	1-based index of the parameter.
	 * @return		the native data type name of the parameter.
	 * @throws OdaException		if data source error occurs.
	 */
	public String getParameterTypeName( int param ) throws OdaException;
	
	/**
	 * Returns the maximum number of decimal digits for the specified parameter.
	 * This method should only apply to numeric data types; however, it is up 
	 * to an ODA data provider to determine those data types that are 
	 * applicable. The maximum precision allowed on a data type may vary depending 
	 * on the data provider.
	 * <br>An optional method.
	 * @param param	1-based index of the parameter.
	 * @return		the precision of the parameter, or -1 if not applicable.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getPrecision( int param ) throws OdaException;
	
	/**
	 * Returns the maximum number of digits to the right of the decimal point 
	 * for the specified parameter. 
	 * This method should only apply to numeric data types;
	 * however, it is up to an ODA data provider to determine 
	 * those data types that are applicable. The maximum scale allowed on 
	 * a data type may vary depending on the data provider.
	 * <br>An optional method.
	 * @param param	1-based index of the parameter.
	 * @return		the scale of the parameter, or -1 if not applicable.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getScale( int param ) throws OdaException;
	
	/**
	 * Returns whether null values are allowed for the specified parameter.
	 * <br>An optional method.
	 * @param param	1-based index of the parameter.
	 * @return		the nullability of the parameter;<br> 
	 * 				one of parameterNullableUnknown,<br>
	 * 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 				parameterNoNulls,<br>
	 * 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 				parameterNullable.
	 * @throws OdaException		if data source error occurs.
	 */
	public int isNullable( int param ) throws OdaException;
}
