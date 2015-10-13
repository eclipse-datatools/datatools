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

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class DelegatingOperationContext extends OperationContext
{
    private final OperationContext delegate;
    
    public DelegatingOperationContext( final OperationContext delegate )
    {
        this.delegate = delegate;
    }
    
    public void log( final String message )
    {
        this.delegate.log( message );
    }
    
    public File file( final File file )
    {
        return this.delegate.file( file );
    }
    
}