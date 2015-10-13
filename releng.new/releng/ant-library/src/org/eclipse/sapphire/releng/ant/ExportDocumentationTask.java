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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.BuildException;

public final class ExportDocumentationTask extends AbstractTask
{
    private String server;
    private File destination;
    
    public void setServer( final String server )
    {
        this.server = server;
    }

    public void setDest( final File destination )
    {
        this.destination = destination;
    }
    
    @Override
    public void execute() throws BuildException
    {
        final String base = this.server + "/help/topic/org.eclipse.sapphire.doc/html/";
        final String url = base + "index.html";
        
        try
        {
            export( url, base, this.destination );
        }
        catch( final Exception e )
        {
            throw new BuildException( e );
        }
    }
    
    private void export( final String url, final String base, final File destination ) throws Exception
    {
        export( url, base, destination, new HashSet<String>() );
    }
    
    private void export( final String url, final String base, final File destination, final Set<String> captured ) throws Exception
    {
        if( captured.contains( url ) )
        {
            return;
        }
        
        info( "Exporting " + url );
        
        captured.add( url );
        
        final File f = new File( destination, url.substring( base.length() ) );
        final File fp = f.getParentFile();
        
        if( ! fp.exists() && ! fp.mkdirs() )
        {
            throw new RuntimeException();
        }
        
        final URLConnection connection = ( new URL( url ) ).openConnection();
        
        try( InputStream stream = connection.getInputStream() )
        {
            if( connection.getContentType().equals( "text/html" ) )
            {
                final Reader r = new InputStreamReader( stream, "UTF-8" );
                final StringBuilder content = new StringBuilder();
                
                char[] buffer = new char[ 1024 ];
                
                for( int count; ( count = r.read( buffer ) ) != -1; )
                {
                    content.append( buffer, 0, count );
                }
                
                String html = content.toString();
                
                html = html.replaceAll( "<script[^>]*>[^<]*</script>", "" );
                html = html.replaceFirst( "<link[^>]+breadcrumbs\\.css[^>]+></link>", "" );
                
                final Matcher hrefMatcher = Pattern.compile( "href=\"((\\.\\./)*topic/org\\.eclipse\\.sapphire\\.doc/html/([^\"]+))\"" ).matcher( html );
                
                while( hrefMatcher.find() )
                {
                    final String href = hrefMatcher.group( 3 );
                    final File hrefFile = new File( destination, href );
                    final File hrefFolder = hrefFile.getParentFile();
                    final int level = fp.getAbsolutePath().split( "\\\\" ).length - hrefFolder.getAbsolutePath().split( "\\\\" ).length;
                    final StringBuilder relative = new StringBuilder();
                    
                    for( int i = 0; i < level; i++ )
                    {
                        relative.append( "../" );
                    }
                    
                    relative.append( hrefFile.getName() );
                    
                    html = html.replace( hrefMatcher.group( 1 ), relative );
                }
                
                final Set<String> references = new HashSet<String>();
                
                references.addAll( references( html, "a", "href" ) );
                references.addAll( references( html, "img", "src" ) );
                references.addAll( references( html, "link", "href" ) );
                
                final int urlLastSlash = url.lastIndexOf( "/" );
                final String urlParent = url.substring( 0, urlLastSlash + 1 );
                
                for( final String ref : references )
                {
                    export( url( urlParent, ref ), base, destination, captured );
                }
                
                try( OutputStreamWriter fw = new OutputStreamWriter( new FileOutputStream( f ), "UTF-8" ) )
                {
                    fw.write( html );
                }
            }
            else
            {
                try( FileOutputStream fout = new FileOutputStream( f ) )
                {
                    byte[] buffer = new byte[ 1024 ];
                    
                    for( int count; ( count = stream.read( buffer ) ) != -1; )
                    {
                        fout.write( buffer, 0, count );
                    }
                }
            }
        }
    }
    
    private static final Set<String> references( final String html, final String tag, final String attribute )
    {
        final Set<String> urls = new HashSet<String>();
        final Matcher tagMatcher = Pattern.compile( "(<" + tag + "\\s+[^>]+>)" ).matcher( html );
        
        while( tagMatcher.find() )
        {
            final String link = tagMatcher.group( 1 );
            final Matcher hrefMatcher = Pattern.compile( attribute + "\\s*=\\s*\"([^\"]+)\"" ).matcher( link );
            
            if( hrefMatcher.find() )
            {
                final String href = hrefMatcher.group( 1 );
            
                if( ! href.startsWith( "#" ) && ! href.startsWith( "http://" ) && ! href.startsWith( "https://" ) )
                {
                    urls.add( href );
                }
            }
        }
        
        return urls;
    }
    
    private static final String url( final String base, final String relative )
    {
        String b = base;
        String r = relative;
        
        if( b.endsWith( "/" ) )
        {
            b = b.substring( 0, b.length() - 1 );
        }
        
        while( r.startsWith( "../" ) )
        {
            final int bLastSlash = b.lastIndexOf( '/' );
            b = b.substring( 0, bLastSlash );
            r = r.substring( 3 );
        }
        
        final int rLastHash = r.lastIndexOf( '#' );
        
        if( rLastHash != -1 )
        {
            r = r.substring( 0, rLastHash );
        }
        
        return b + "/" + r;
    }
    
}