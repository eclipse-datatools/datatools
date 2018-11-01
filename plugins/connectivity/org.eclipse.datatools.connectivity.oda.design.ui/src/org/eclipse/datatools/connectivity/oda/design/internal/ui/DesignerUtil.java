/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import java.lang.reflect.Constructor;

import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

    /**
     * An utility method to enable/disable the specified parent control and 
     * all its nested children, except the preservedButton if specified,
     * according to the specified <code>enabled</code> state.
     * The preservedButton's state is excluded from the state changes.
     * @param aControl  a control
     * @param enabled   the new enabled state
     * @param preservedButton   the button whose enabled state is to be preserved;
     *                          may be null
     * @since 3.0.4
     */
    public static void enableAllControls( Control parent, boolean enabled,
                                            Button preservedButton )
    {
        if( parent == null )
            return;     // nothing to enable

        if( preservedButton != null && preservedButton.isDisposed() )
            preservedButton = null;     // can't access the button
        
        boolean isButtonEnabled = ( preservedButton != null ) ? 
                                    preservedButton.isEnabled() : true;

        enableAllControls( parent, enabled );
        
        // restore the previous state of the preserved button
        if( preservedButton != null )
        {
            preservedButton.setEnabled( isButtonEnabled );
            preservedButton.getParent().setEnabled( isButtonEnabled );
            parent.setEnabled( isButtonEnabled );        
        }
        else
            // in case the preservedButton is created later in enabled state 
            parent.setEnabled( isButtonEnabled );        
    }

    /**
     * Recursively goes through all the children of the specified control
     * and enable/disable them according to the specified <code>enabled</code> state.
     * @param aControl  a control
     * @param enabled   the new enabled state
     * @since 3.0.4
     */
    public static void enableAllControls( Control aControl, boolean enabled )
    {
        if( aControl == null )
            return;     // nothing to enable
        
        if( aControl instanceof Composite )
        {
            Control[] childControls = ((Composite) aControl).getChildren();
            for( int i = 0; i < childControls.length; i++ )
            {
                enableAllControls( childControls[i], enabled );
            }
        }
        
        if( ! aControl.isDisposed() )
            aControl.setEnabled( enabled );        
    }
    
}
