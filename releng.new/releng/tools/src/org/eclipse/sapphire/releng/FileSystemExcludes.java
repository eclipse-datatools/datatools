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

package org.eclipse.sapphire.releng;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileResource;

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class FileSystemExcludes
{
    private final List<ResourceCollection> rcs = new ArrayList<ResourceCollection>();
    
    public void add( final ResourceCollection rc )
    {
        this.rcs.add( rc );
    }
    
    public List<File> list()
    {
        final List<File> list = new ArrayList<File>();
        
        for( final ResourceCollection rc : this.rcs )
        {
            for( final Iterator<Resource> itr = rc.iterator(); itr.hasNext(); )
            {
                final Resource resource = itr.next();
                
                if( resource instanceof FileResource )
                {
                    list.add( ( (FileResource) resource ).getFile() );
                }
            }
        }
        
        return list;
    }
    
}
