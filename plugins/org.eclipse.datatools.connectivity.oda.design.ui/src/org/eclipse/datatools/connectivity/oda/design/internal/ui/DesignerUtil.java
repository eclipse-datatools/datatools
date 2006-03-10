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

import java.lang.reflect.Constructor;

import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osgi.framework.Bundle;

/**
 *  Internal utility class to provide services
 *  to the ODA Designer packages.
 */
public class DesignerUtil
{
    private DesignerUtil()
    {}
    
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
            throw new OdaException( Messages.common_nullArgument );
        
        DataSourceDesign dataSourceDesign =
            designSession.getRequestDataSourceDesign();
        if( dataSourceDesign == null )
            throw new OdaException( Messages.common_missingDataSourceDesign );

        DataSourceDesign editDataSourceDesign = 
            (DataSourceDesign) EcoreUtil.copy( dataSourceDesign );
        
        return new AdaptableDataSourceProfile( editDataSourceDesign );
    }
    
    /**
     * Instantiate an object of the specified plugin class through
     * its constructor with a single String argument.
     * @param pluginId
     * @param className
     * @param argument
     * @return
     * @throws RuntimeException
     */
    static Object createInstanceWithStringArg( String pluginId,
                                               String className, 
                                               String argument ) 
        throws RuntimeException
    {
        Object newInstance = null;
        try
        {
            Bundle bundle = Platform.getBundle( pluginId );
            Class loadedClass = bundle.loadClass( className );

            // looks for custom constructor
            // with a single String argument
            Class argTypes[] = new Class[1];
            argTypes[0] = String.class;
            Constructor ct = loadedClass.getConstructor( argTypes );

            // instantiate with given argument
            Object argList[] = new Object[1];
            argList[0] = argument;
            newInstance = ct.newInstance( argList );
        }
        catch( Exception ex )
        {
            throw new RuntimeException( 
                    Messages.bind( Messages.common_createClassFailed, 
                                    className ), ex );
        }
        return newInstance;
    }

}
