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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.sapphire.releng.AntTaskOperationContext;
import org.eclipse.sapphire.releng.FileSystemExcludes;

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class GenRepositoryLandingTask extends Task
{
    private File repository;
    private String name;
    private final FileSystemExcludes excludes = new FileSystemExcludes();
    
    public void setRepository( final File repository )
    {
        this.repository = repository;
    }
    
    public void setName( final String name )
    {
        this.name = name;
    }
    
    public FileSystemExcludes createExcludes()
    {
        return this.excludes;
    }
    
    @Override
    public void execute() throws BuildException
    {
        if( this.repository == null )
        {
            throw new BuildException( "The \"repository\" attribute must be specified." );
        }
        
        final GenRepositoryLandingOp op = new GenRepositoryLandingOp();
        op.setRepository( this.repository );
        op.setName( this.name );
        op.getExcludes().addAll( this.excludes.list() );
        op.execute( new AntTaskOperationContext( this ) );
    }
    
}
