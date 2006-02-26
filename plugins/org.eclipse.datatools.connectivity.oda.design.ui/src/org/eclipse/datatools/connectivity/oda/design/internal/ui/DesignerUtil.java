/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 *  Internal utility class to provide services
 *  to the ODA Designer packages.
 */
public class DesignerUtil
{
    /**
     * Validates and adapts the given data source design
	 * for editing.
     * <br>The adaptable element returned is a copy of the  
     * DataSourceDesign instance found in given session's request.
     * @param designSession
     * @return	an adaptable data source design as an element 
     * that can be edited in a data source property page
     * @throws OdaException
     */
    public static AdaptableDataSourceProfile 
        getAdaptableDataSourceDesign( OdaDesignSession designSession )
    throws OdaException
    {
        if( designSession == null )
            throw new OdaException( "Illegal argument; must not be null." );
        
        DataSourceDesign dataSourceDesign =
            designSession.getRequestDataSourceDesign();
        if( dataSourceDesign == null )
            throw new OdaException( "Missing data source design in OdaDesignSession argument." );

        DataSourceDesign editDataSourceDesign = 
            (DataSourceDesign) EcoreUtil.copy( dataSourceDesign );
        
        return new AdaptableDataSourceProfile( editDataSourceDesign );
    }

}
