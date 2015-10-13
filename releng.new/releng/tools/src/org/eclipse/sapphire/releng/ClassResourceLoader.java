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

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class ClassResourceLoader
{
    private Class<?> context;
    
    public ClassResourceLoader( final Class<?> context )
    {
        if( context == null )
        {
            throw new IllegalArgumentException();
        }
        
        this.context = context;
    }
    
    public Resource resource( final String name )
    {
        if( name == null )
        {
            throw new IllegalArgumentException();
        }
        
        return new Resource( this.context.getResource( name ), name );
    }
    
}