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

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class SetExportPackageVersionTask extends AbstractTask
{
    private static final String PROP_BUNDLE_VERSION = "Bundle-Version";
    private static final String PROP_EXPORT_PACKAGE = "Export-Package";
    
    private File bundlesLocation = null;
    
    public void setBundles( final File bundlesLocation )
    {
        this.bundlesLocation = bundlesLocation;
    }
    
    @Override
    public void execute() throws BuildException
    {
        try
        {
            if( ! this.bundlesLocation.exists() )
            {
                fail( this.bundlesLocation.toString() + " does not exist!" );
            }
            
            if( this.bundlesLocation.exists() )
            {
                for( File location : this.bundlesLocation.listFiles() )
                {
                    if( BundleInfo.isValidBundle( location ) )
                    {
                        final String originalExportPackage = ManifestUtil.readManifestEntry( location, PROP_EXPORT_PACKAGE );
                        
                        if( originalExportPackage != null )
                        {
                            final String bundleVersion = ManifestUtil.readManifestEntry( location, PROP_BUNDLE_VERSION );
                            
                            if( bundleVersion == null )
                            {
                                fail( "Bundle located at \"" + location.toString() + "\" does not specify Bundle-Version." );
                            }
                            
                            final StringBuilder modifiedExportPackage = new StringBuilder();
                            
                            for( ManifestBundlesListEntry entry : ManifestBundlesListEntry.parse( originalExportPackage ) )
                            {
                                for( String attr : entry.attributes() )
                                {
                                    if( attr.startsWith( "version=" ) )
                                    {
                                        fail( "Bundle located at \"" + location.toString() + "\" manually specifies an exported package version for \"" + entry.bundle() + "\"." );
                                    }
                                }
                                
                                entry.attributes().add( 0, "version=\"" + bundleVersion + "\"" );
                                
                                if( modifiedExportPackage.length() > 0 )
                                {
                                    modifiedExportPackage.append( ',' );
                                }
                                
                                modifiedExportPackage.append( entry );
                            }
                            
                            final File manifestFile = new File( location, ManifestUtil.MANIFEST_PATH );
                            
                            ManifestUtil.setManifestEntry( manifestFile, PROP_EXPORT_PACKAGE, modifiedExportPackage.toString() );
                        }
                    }
                }
            }
        }
        catch( IOException e )
        {
            throw new BuildException( e );
        }
    }
    
}
