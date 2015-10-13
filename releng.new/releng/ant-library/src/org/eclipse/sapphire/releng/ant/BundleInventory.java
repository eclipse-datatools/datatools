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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class BundleInventory
{
    private static final String NL = System.getProperty( "line.separator" );
    
    private static final Comparator<BundleInfo> BUNDLE_COMPARATOR = new Comparator<BundleInfo>()
    {
        public int compare( final BundleInfo b1,
                            final BundleInfo b2 )
        {
            int result = b1.getId().compareTo( b2.getId() );
            
            if( result == 0 )
            {
                result = b1.getVersion().compareTo( b2.getVersion() );
            }
            
            return result;
        }
    };

    private final SortedSet<BundleInfo> bundles = new TreeSet<BundleInfo>( BUNDLE_COMPARATOR );
    
    public Set<BundleInfo> getBundles()
    {
        return this.bundles;
    }
    
    public BundleInfo getBundle( final String bundleId )
    {
        for( BundleInfo bundle : this.bundles )
        {
            if( bundle.getId().equals( bundleId ) )
            {
                return bundle;
            }
        }
        
        return null;
    }
    
    public void addBundle( final BundleInfo bundle )
    {
        this.bundles.add( bundle );
    }
    
    public void write( final File f ) throws IOException 
    {
        try( BufferedWriter w = new BufferedWriter( new FileWriter( f ) ) )
        {
            for( BundleInfo bundle : this.bundles )
            {
                w.write( bundle.getId() );
                w.write( " : " );
                w.write( bundle.getVersion().toString() );
                w.write( NL );
            }
            
            w.flush();
        }
    }
    
    public void read( final File f ) throws IOException
    {
        this.bundles.clear();
        
        try( BufferedReader r = new BufferedReader( new FileReader( f ) ) )
        {
            for( String line = r.readLine(); line != null; line = r.readLine() )
            {
                final String[] segments = line.split( ":" );
                final String id = segments[ 0 ].trim();
                final BundleVersion version = new BundleVersion( segments[ 1 ] );
                
                this.bundles.add( new BundleInfo( id, version ) );
            }
        }
    }

}
