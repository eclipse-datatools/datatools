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

package org.eclipse.sapphire.releng.listing;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.sapphire.releng.ClassResourceLoader;
import org.eclipse.sapphire.releng.FileUtil;
import org.eclipse.sapphire.releng.Operation;
import org.eclipse.sapphire.releng.OperationContext;

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class GenFolderListingOp extends Operation
{
    private static final long KB = 1024;
    private static final long MB = KB * 1024;
    private static final long GB = MB * 1024;
    
    private static final String NL = System.getProperty( "line.separator" );
    
    private static final ClassResourceLoader RESOURCE_LOADER = new ClassResourceLoader( GenFolderListingOp.class );
    private static final String LISTING_PAGE_TEMPLATE = RESOURCE_LOADER.resource( "ListingPageTemplate.txt" ).text();
    private static final String LISTING_ENTRY_TEMPLATE = RESOURCE_LOADER.resource( "ListingEntryTemplate.txt" ).text();
    private static final String LISTING_ENTRY_WITHOUT_SPARKLINE_TEMPLATE = RESOURCE_LOADER.resource( "ListingEntryWithoutSparklineTemplate.txt" ).text();
    private static final String SUMMARY_ENTRY_TEMPLATE = RESOURCE_LOADER.resource( "SummaryEntryTemplate.txt" ).text();
    
    private File folder;
    private final Set<File> excludes = new HashSet<File>();
    
    public File getFolder()
    {
        return this.folder;
    }
    
    public void setFolder( final File folder )
    {
        this.folder = folder;
    }
    
    public final Set<File> getExcludes()
    {
        return this.excludes;
    }
    
    @Override
    public void execute( final OperationContext context )
    {
        generate( context, this.folder );
    }
    
    private Entry generate( final OperationContext context, final File target )
    {
        context.log( "Generating listing for " + target.getPath() );
        
        final List<Entry> entries = new ArrayList<Entry>();
        
        Date overallDateModified = new Date( 0 );
        long maxSize = 0;
        long totalSize = 0;
        int folderCount = 0;
        int fileCount = 0;
        
        for( final File f : target.listFiles() )
        {
            final boolean isFile = f.isFile();
            final boolean isDirectory = f.isDirectory();
            final String name = f.getName();
            
            if( ( isFile || isDirectory ) && ! name.equals( "index.html" ) && ! this.excludes.contains( f ) )
            {
                final Entry entry;
                
                if( isFile )
                {
                    entry = new Entry( name, new Date( f.lastModified() ), f.length() );
                    fileCount++;
                }
                else
                {
                    entry = generate( context, f );
                    folderCount++;
                }
                
                entries.add( entry );
                
                final Date dateModified = entry.getDateModified();
                
                if( dateModified.compareTo( overallDateModified ) > 0 )
                {
                    overallDateModified = dateModified;
                }
                
                final long size = entry.getSize();
                
                if( size > maxSize )
                {
                    maxSize = size;
                }
                
                totalSize += size;
            }
        }
        
        Collections.sort
        (
            entries,
            new Comparator<Entry>()
            {
                @Override
                public int compare( final Entry x, Entry y )
                {
                    int result = ( x.isFolder() ? 0 : 1 ) - ( y.isFolder() ? 0 : 1 );
                    
                    if( result == 0 )
                    {
                        result = x.getName().compareTo( y.getName() );
                    }
                    
                    return result;
                }
            }
        );
        
        final int count = fileCount + folderCount;
        final long segmentSize = maxSize / 200;
        final StringBuilder listing = new StringBuilder();
        
        for( final Entry entry : entries )
        {
            String block = ( count > 1 ? LISTING_ENTRY_TEMPLATE : LISTING_ENTRY_WITHOUT_SPARKLINE_TEMPLATE )
                .replace( "${name}", entry.getName() )
                .replace( "${href}", entry.getName() + ( entry.isFolder() ? "/index.html" : "" ) )
                .replace( "${type}", entry.isFolder() ? "folder" : "file" )
                .replace( "${size}", toSizeForDisplay( entry.getSize() ) );
            
            if( count > 1 )
            {
                int segments = (int) Math.round( (double) entry.getSize() / segmentSize );
                segments = segments == 0 ? 1 : segments;
                
                block = block.replace( "${segments}", String.valueOf( segments ) );
            }
            
            append( listing, block, "\n" );
        }
        
        final StringBuilder summary = new StringBuilder();
        
        if( folderCount > 0 )
        {
            appendSummaryLine( summary, "Folders", String.valueOf( folderCount ) );
        }
        
        if( fileCount > 0 )
        {
            appendSummaryLine( summary, "Files", String.valueOf( fileCount ) );
        }
        
        appendSummaryLine( summary, "Size", toSizeForDisplay( totalSize ) );
        appendSummaryLine( summary, "Date Modified", new SimpleDateFormat( "yyyy-MM-dd" ).format( overallDateModified ) );
        
        final String text = LISTING_PAGE_TEMPLATE
            .replace( "${listing}", listing.toString() )
            .replace( "${summary}", summary.toString() )
            .replace( "\n", NL );
        
        try
        {
            FileUtil.write( context.file( new File( target, "index.html" ) ), text );
        }
        catch( final IOException e )
        {
            throw new RuntimeException( e );
        }
        
        return new Entry( target.getName(), overallDateModified, totalSize, true );
    }
    
    private static String toSizeForDisplay( final long size )
    {
        final String text;
        
        if( size < KB )
        {
            text = String.valueOf( size ) + " b";
        }
        else if( size < MB )
        {
            text = String.valueOf( Math.round( (double) size / KB ) ) + " kb";
        }
        else if( size < GB )
        {
            text = String.valueOf( Math.round( (double) size / MB ) ) + " mb";
        }
        else
        {
            text = String.valueOf( Math.round( (double) size / GB ) ) + " gb";
        }
        
        return text;
    }
    
    private static void appendSummaryLine( final StringBuilder summary, final String key, final String value )
    {
        final String block = SUMMARY_ENTRY_TEMPLATE
            .replace( "${key}", key )
            .replace( "${value}", value );
            
         append( summary, block, "\n" );
    }
    
    private static void append( final StringBuilder string, final String segment, final String separator )
    {
        if( string.length() > 0 )
        {
            string.append( separator );
        }
        
        string.append( segment );
    }
    
    private static final class Entry
    {
        private final String name;
        private final long size;
        private final boolean folder;
        private final Date dateModified;
        
        public Entry( String name, final Date dateModified, final long size )
        {
            this( name, dateModified, size, false );
        }
        
        public Entry( String name, final Date dateModified, final long size, final boolean folder )
        {
            if( name == null )
            {
                throw new IllegalArgumentException();
            }
            
            if( dateModified == null )
            {
                throw new IllegalArgumentException();
            }
            
            this.name = name;
            this.dateModified = dateModified;
            this.size = size;
            this.folder = folder;
        }
        
        public String getName()
        {
            return this.name;
        }
        
        public Date getDateModified()
        {
            return this.dateModified;
        }
        
        public long getSize()
        {
            return this.size;
        }
        
        public boolean isFolder()
        {
            return this.folder;
        }
    }
    
    public static void main( final String[] args )
    {
        if( args.length != 1 )
        {
            System.err.println( "USAGE: java -cp sapphire-releng-tools.jar org.eclipse.sapphire.releng.listing.GenFolderListingOp [folder]" );
            System.exit( 1 );
        }
        
        final File target = new File( args[ 0 ] );
        
        if( ! target.isDirectory() )
        {
            System.err.println( "The specified path is not a folder or is not accessible." );
            System.exit( 2 );
        }
        
        final GenFolderListingOp op = new GenFolderListingOp();
        
        op.setFolder( new File( args[ 0 ] ) );
        
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