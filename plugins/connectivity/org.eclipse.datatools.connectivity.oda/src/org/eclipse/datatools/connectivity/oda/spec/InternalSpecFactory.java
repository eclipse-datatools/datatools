/*
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
