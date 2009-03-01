/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.testdriver.spec.impl;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ITester;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;
import org.eclipse.datatools.connectivity.oda.spec.manifest.SupportedDataSetType;
import org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;

/**
 *  Sample custom tester of extension-defined filter expressions.
 */
@SuppressWarnings("restriction")
public class ExpressionTester implements ITester, IExecutableExtension
{
    private List<SupportedDataSetType> m_dataSetTypes;

    public ExpressionTester()
    {}

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    public void setInitializationData( IConfigurationElement contributorElement,
            String propertyName, Object data ) throws CoreException
    {
        // get the data set types specified in the contributor of this tester
        try
        {
            m_dataSetTypes = ExtensionContributor.processDataSetTypeElements( contributorElement );
        }
        catch( OdaException ex )
        {
            // TODO ignore, log warning
        }
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.filter.ITester#validate(org.eclipse.datatools.connectivity.oda.filter.FilterExpression, org.eclipse.datatools.connectivity.oda.filter.ValidationContext)
     */
    public void validate( FilterExpression expr, ValidationContext context )
            throws OdaException
    {
        if( !(expr instanceof CustomExpression) )
        {
            return;     // done
        }
        
        CustomExpression customExpr = (CustomExpression) expr;
        
        // validate if the data set types specified by the contributor of this tester matches 
        // the type declared as supported in the custom expression definition
        if( m_dataSetTypes != null )
        {
            boolean supportsExprDataSetType = false;
            for( int i=0; i < m_dataSetTypes.size(); i++ )
            {
                SupportedDataSetType testerDataSetType = m_dataSetTypes.get( i );
                if( customExpr.supportsDataSetType( testerDataSetType ))
                {
                    supportsExprDataSetType = true;
                    break;
                }
            }
            if( ! supportsExprDataSetType )
                throw new OdaException( "The filter expression (" + customExpr.getQualifiedId() 
                        + ") is not valid for the ODA data set type." );
        }
        
        // TODO 
        // validate a custom expression is supported 
        // vaildate ExpressionVariable is of type RESULT_COLUMN
                
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.filter.ITester#validate(org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression, org.eclipse.datatools.connectivity.oda.filter.ValidationContext)
     */
    public void validate( AggregateExpression expr, ValidationContext context )
            throws OdaException
    {
        // TODO Auto-generated method stub
        
    }

}
