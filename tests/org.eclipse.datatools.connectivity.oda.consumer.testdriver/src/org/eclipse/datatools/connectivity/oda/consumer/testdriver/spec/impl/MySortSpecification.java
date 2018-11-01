/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.testdriver.spec.impl;

import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;

/**
 *
 */
@SuppressWarnings("restriction")
public class MySortSpecification extends SortSpecification
{
    public MySortSpecification()
    {
        super();
    }
    
    public MySortSpecification( int sortMode )
    {
        super( sortMode );
    }
}
