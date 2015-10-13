/******************************************************************************
 * Copyright (c) 2015 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.releng.ant;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class CreateInventoryTask extends AbstractTask
{
    private Path pluginSearchPath = null;
    private File destination = null;
    
    public void setDest( final File destination )
    {
        this.destination = destination;
    }
    
    public Path createPlugins()
    {
        this.pluginSearchPath = new Path( getProject() );
        return this.pluginSearchPath.createPath();
    }
    
    @Override
    public void execute()

        throws BuildException

    {
        final BundleInventory inventory = new BundleInventory();
        
        for( String path : this.pluginSearchPath.list() )
        {
            final File dir = new File( path );
            
            if( ! dir.exists() )
            {
                fail( path + " does not exist!" );
            }
            
            if( dir.exists() )
            {
                for( File location : dir.listFiles() )
                {
                    if( BundleInfo.isValidBundle( location ) )
                    {
                    	try{
                    		inventory.addBundle( new BundleInfo( location ) );
                    	}catch(Exception e){
                    		if( "true".equals( System.getProperty("debug") ))
                   				e.printStackTrace();
                    	}
                    }
                }
            }
        }
        
        try
        {
            inventory.write( this.destination );
        }
        catch( IOException e )
        {
            throw new BuildException( e );
        }
    }
    
}
