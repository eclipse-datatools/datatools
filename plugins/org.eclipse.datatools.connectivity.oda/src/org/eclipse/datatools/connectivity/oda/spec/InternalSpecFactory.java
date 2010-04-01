/*
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.spec;

/**
 *  Internal factory of package classes. 
 */
public class InternalSpecFactory
{
    public static AdvancedQuerySpecification createAdvancedQuerySpecification()
    {
        return new AdvancedQuerySpecification();
    }
    
    public static QuerySpecification createQuerySpecification()
    {
        return new QuerySpecification();
    }
    
}
