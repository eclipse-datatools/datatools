/*
 *************************************************************************
 * Copyright (c) 2013, 2014 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec.basequery;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.BaseQuery;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;

/**
 * A concrete base query that specifies the query text to be prepared by 
 * {@link org.eclipse.datatools.connectivity.oda.IQuery#prepare(String)}.
 * When a non-empty query text is passed in as argument to the #prepare method, 
 * it would override and take precedence over this specification.
 * @since 3.4 (DTP 1.11)
 */
public class AtomicQuery extends BaseQuery
{
    private String m_queryText;
    
    public AtomicQuery( String queryText )
    {
        setQueryText( queryText );
    }
    
    public void setQueryText( String queryText )
    {
        m_queryText = queryText;
    }

    public String getQueryText()
    {
        return m_queryText;
    }
    
    public boolean hasQueryText()
    {
        return getQueryText() != null && ! getQueryText().trim().isEmpty();
    }

    @Override
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.BaseQuery#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        if( ! hasQueryText() )
            throw ValidatorUtil.newBaseQueryException( Messages.querySpec_MISSING_ATOMIC_QUERY_TEXT, this );
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( AtomicQuery.class.getSimpleName() );
        buffer.append( "  { query text: " ); //$NON-NLS-1$
        buffer.append( m_queryText );
        buffer.append( " }" ); //$NON-NLS-1$
        return buffer.toString();
    }
    
}
