/*
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.impl;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Default implementation of IParameterMetaData 
 * for a simple ODA runtime driver.
 * @deprecated  As of 3.1.2, see package documentation.
 */
public class SimpleParameterMetaData implements IParameterMetaData 
{

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterCount()
	 */
	public int getParameterCount() throws OdaException 
	{
        // TODO data source dependent
		throw new UnsupportedOperationException( "Please override and implement me." ); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterMode(int)
	 */
	public int getParameterMode( int param ) throws OdaException 
	{
        // TODO data source dependent
		return IParameterMetaData.parameterModeUnknown;
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterName(int)
     */
    public String getParameterName( int param ) throws OdaException
    {
        // TODO data source dependent
        return null;    // name is not available
    }

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterType(int)
	 */
	public int getParameterType( int param ) throws OdaException 
	{
        // TODO data source dependent
		throw new UnsupportedOperationException( "Please override and implement me." ); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterTypeName(int)
	 */
	public String getParameterTypeName( int param ) throws OdaException 
	{
        // TODO data source dependent
		return null; 	// unknown
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getPrecision(int)
	 */
	public int getPrecision( int param ) throws OdaException 
	{
		return -1;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getScale(int)
	 */
	public int getScale( int param ) throws OdaException 
	{
		return -1;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#isNullable(int)
	 */
	public int isNullable( int param ) throws OdaException 
	{
		return IParameterMetaData.parameterNullableUnknown;
	}

}
