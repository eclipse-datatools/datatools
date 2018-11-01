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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpressionOperator;

/**
 * Test class implementation of a custom combined operator type.
 */
public class MyCombinedOperator extends CombinedValueExpressionOperator
{
    private static final String CUSTOM_COMBINED_OP_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.combinedOperator.dot"; //$NON-NLS-1$
     
    /**
     * Constructor for use in an IExecutableExtension.
     * @see #setInitializationData
     */
    public MyCombinedOperator()
    {
        super( null, null );
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    public void setInitializationData( IConfigurationElement config,
            String propertyName, Object data ) throws CoreException
    {
        super.setInitializationData( config, propertyName, data );
        
        if( getId().equals( CONCATENATE ) )   // overrides built-in operator's literal representation
            setLiteral( "||" );
    }

    @Override
    public String getLiteral()
    {
        // alternate way to override an operator's literal representation
        if( getId().equals( CUSTOM_COMBINED_OP_ID ) )
            return "."; //$NON-NLS-1$
        return super.getLiteral();
    }

}
