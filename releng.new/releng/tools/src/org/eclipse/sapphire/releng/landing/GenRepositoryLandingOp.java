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

package org.eclipse.sapphire.releng.landing;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.sapphire.releng.ClassResourceLoader;
import org.eclipse.sapphire.releng.DelegatingOperationContext;
import org.eclipse.sapphire.releng.FileUtil;
import org.eclipse.sapphire.releng.Operation;
import org.eclipse.sapphire.releng.OperationContext;
import org.eclipse.sapphire.releng.listing.GenFolderListingOp;

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class GenRepositoryLandingOp extends Operation
{
    private static final String NL = System.getProperty( "line.separator" );
    
    private static final ClassResourceLoader RESOURCE_LOADER = new ClassResourceLoader( GenRepositoryLandingOp.class );
    private static final String LANDING_PAGE_TEMPLATE = RESOURCE_LOADER.resource( "LandingPageTemplate.txt" ).text();

    private File repository;
    private String name = "Repository";
    private final Set<File> excludes = new HashSet<File>();
    
    public File getRepository()
    {
        return this.repository;
    }
    
    public void setRepository( final File repository )
    {
        this.repository = repository;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName( final String name )
    {
        this.name = ( name == null ? "Repository" : name );
    }
    
    public final Set<File> getExcludes()
    {
        return this.excludes;
    }
    
    @Override
    public void execute( final OperationContext context )
    {
        final File listingPage = context.file( new File( this.repository, "content.html" ) );
        
        if( listingPage.exists() && ! listingPage.delete() )
        {
            throw new RuntimeException();
        }
        
        final File imagesFolder = context.file( new File( this.repository, "images" ) );
        
        if( ! imagesFolder.exists() && ! imagesFolder.mkdirs() )
        {
            throw new RuntimeException();
        }
        
        final GenFolderListingOp genFolderListingOp = new GenFolderListingOp();
        genFolderListingOp.setFolder( this.repository );
        genFolderListingOp.getExcludes().addAll( this.excludes );
        genFolderListingOp.getExcludes().add( imagesFolder );
        
        genFolderListingOp.execute
        (
            new DelegatingOperationContext( context )
            {
                @Override
                public File file( final File file )
                {
                    if( file.getParentFile().equals( GenRepositoryLandingOp.this.repository ) && file.getName().equals( "index.html" ) )
                    {
                        return listingPage;
                    }
                    
                    return super.file( file );
                }
            }
        );
        
        context.log( "Generating repository landing page for " + this.repository.getPath() );
        
        RESOURCE_LOADER.resource( "InstallDialog.png" ).copy( imagesFolder );
        
        final String text = LANDING_PAGE_TEMPLATE
            .replace( "${repository-name}", this.name )
            .replace( "\n", NL );
        
        try
        {
            FileUtil.write( context.file( new File( this.repository, "index.html" ) ), text );
        }
        catch( final IOException e )
        {
            throw new RuntimeException( e );
        }
    }
    
    public static void main( final String[] args )
    {
        if( args.length != 1 && args.length != 2 )
        {
            System.err.println( "USAGE: java -cp sapphire-releng-tools.jar org.eclipse.sapphire.releng.landing.GenRepositoryLandingOp [repository] [name]" );
            System.exit( 1 );
        }
        
        final File target = new File( args[ 0 ] );
        
        if( ! target.isDirectory() )
        {
            System.err.println( "The specified path is not a folder or is not accessible." );
            System.exit( 2 );
        }
        
        final GenRepositoryLandingOp op = new GenRepositoryLandingOp();
        op.setRepository( new File( args[ 0 ] ) );
        
        if( args.length == 2 )
        {
            op.setName( args[ 1 ] );
        }
        
        final String excludes = System.getProperty( "excludes" );
        
        if( excludes != null )
        {
            for( final String exclude : excludes.split( ";" ) )
            {
                op.getExcludes().add( new File( exclude ) );
            }
        }
        
        op.execute();
    }
    
}