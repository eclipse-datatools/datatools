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

import org.apache.tools.ant.Task;

/**
 * @author <a href="konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class AntTaskOperationContext extends OperationContext
{
    private final Task task;
    
    public AntTaskOperationContext( final Task task )
    {
        if( task == null )
        {
            throw new IllegalArgumentException();
        }
        
        this.task = task;
    }
    
    @Override
    public void log( final String message )
    {
        this.task.log( message );
    }
    
}