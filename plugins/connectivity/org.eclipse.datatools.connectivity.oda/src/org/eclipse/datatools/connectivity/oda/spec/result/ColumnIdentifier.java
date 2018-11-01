/*
 *************************************************************************
 * Copyright (c) 2009, 2013 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec.result;

import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;

/**
 * The identifier of a result set column, defined by its number and/or native name/expression.
 * <br>A column number, if specified, takes precedence over its specified name/expression.
 * A column number is 1-based, as defined in an {@link org.eclipse.datatools.connectivity.oda.IResultSet}
 * before any dynamic projection is applied.
 * <br>A ColumnIdentifier instance may be used as an unique key in a {@link java.util.Map}.
 * Comparison by name is case-sensitive.
 */
public class ColumnIdentifier
{
    private Integer m_pos;
    private String m_nameExpr;
    private QuerySpecification m_querySpecRef;
 
    private static final String LOG_CLASSNAME_PREFIX = "ColumnIdentifier@"; //$NON-NLS-1$
    private static final String LOG_ORDINAL_LABEL = " [ordinal= "; //$NON-NLS-1$
    private static final String LOG_NAME_LABEL = ", name= "; //$NON-NLS-1$
    private static final String LOG_QUERYSPEC_LABEL = ", querySpec= "; //$NON-NLS-1$
    private static final String LOG_END_BRACKET = "]"; //$NON-NLS-1$

    /**
     * Constructor to create an instance that identifies a result set column by both its ordinal
     * position and native name/expression.  This would uniquely identify a column when multiple columns
     * in a result set have the same name.
     * @param pos   column number (1-based)
     * @param nameExpr native name or expression of the column
     */
    public ColumnIdentifier( int pos, String nameExpr )
    {
        this( pos );
        setNameExpression( nameExpr );
    }
    
    /**
     * Constructor to create an instance that identifies a result set column by its ordinal
     * position.
     * @param pos   column number (1-based)
     * @throws IllegalArgumentException if specified argument is not greater or equal to 1
     */
    public ColumnIdentifier( int pos )
    {
        if( pos < 1 )
            throw new IllegalArgumentException( Integer.valueOf( pos ).toString() );
        
        m_pos = Integer.valueOf( pos );
    }
    
    /**
     * Constructor to create an instance that identifies a result set column by its 
     * native name or expression.
     * @param nameExpr native name or expression of the column
     * @throws IllegalArgumentException if specified argument is null or empty
     */
    public ColumnIdentifier( String nameExpr )
    {
        if( nameExpr == null || nameExpr.length() == 0 )
            throw new IllegalArgumentException( nameExpr );
        
        m_nameExpr = nameExpr;
    }

    /**
     * Constructor to create an instance that identifies a column by its
     * native name or expression in the result set defined in a query specification.
     * @param nameExpr   native name or expression of the column
     * @param queryQualifier    the optional {@link QuerySpecification} qualifier of this column identifier
     * @since 3.4.1 (DTP 1.11.1)
     */
    public ColumnIdentifier( String nameExpr, QuerySpecification queryQualifier )
    {
        this( nameExpr );
        setQueryQualifier( queryQualifier );
    }

    /**
     * Constructor that copies the content of the specified identifier.
     * @param thatIdentifier    the ColumnIdentifier to copy from
     * @since 3.4.1 (DTP 1.11.1)
     */
    public ColumnIdentifier( ColumnIdentifier thatIdentifier )
    {
        this.m_nameExpr = thatIdentifier.m_nameExpr;
        this.m_pos = thatIdentifier.m_pos;
        this.m_querySpecRef =  thatIdentifier.m_querySpecRef;
    }

    /**
     * Sets the column number.
     * @param pos column number; may be null
     */
    public void setNumber( Integer pos )
    {
        m_pos = pos;
    }

    /**
     * Sets the column's native name or expression.
     * @param nameExpr a column's native name or expression; may be null
     */
    public void setNameExpression( String nameExpr )
    {
        m_nameExpr = nameExpr;
    }
    
    /**
     * Sets the column's native name or expression.
     * @param valueExpr a column's native name or expression; may be null
     * @deprecated  replaced by {@link #setNameExpression(String)}
     */
    public void setValueExpression( String valueExpr )
    {
        setNameExpression( valueExpr );
    }

    /**
     * Gets the column number, if specified.
     * @return  column number, or null if not specified
     */
    public Integer getNumber()
    {
        return m_pos;
    }

    /**
     * Gets the column's native name or expression, if specified.
     * @return  column's native name or expression, or null if not specified
     */
    public String getNameExpression()
    {
        return m_nameExpr;
    }
    
    /**
     * Gets the column's native name or expression, if specified.
     * @return  column's native name or expression, or null if not specified
     * @deprecated {@link #getNameExpression()}
     */
    public String getValueExpression()
    {
        return getNameExpression();
    }

    /**
     * Indicates whether this has a valid number that is used as the identifier.
     * @return  true if this is identified by the column number; false otherwise
     */
    public boolean isIdentifiedByNumber()
    {
        return ( m_pos != null && m_pos.intValue() > 0 );
    }
    
    /**
     * Indicates whether this has a name expression specified.
     * The name expression is superceded by the ordinal position identifier, if exists. 
     * @return  true if this has a name expression; false otherwise
     */
    public boolean hasNameExpression()
    {
        return ( m_nameExpr != null && m_nameExpr.length() > 0 );
    }

    /**
     * Indicates whether this has a name expression specified.
     * The name expression, if exists, is ignored if this is identified by number. 
     * @return  true if this has a name expression; false otherwise
     * @deprecated replaced by {@link #hasNameExpression()}
     */
    public boolean hasValueExpression()
    {
        return hasNameExpression();
    }

    /**
     * Specifies the query that projects this identified column in its result set.  
     * This may be used to optionally qualify a column, when its name may be ambiguous. 
     * @param querySpec     the optional {@link QuerySpecification} qualifier of this column identifier
     * @since 3.4.1 (DTP 1.11.1)
     */
    public void setQueryQualifier( QuerySpecification querySpec )
    {
        m_querySpecRef = querySpec;
    }

    /**
     * Gets the column's query qualifier, if specified.
     * @return  the QuerySpecification that projects this column in its result set; may be null if not specified
     * @since 3.4.1 (DTP 1.11.1)
     */
    public QuerySpecification getQueryQualifier()
    {
        return m_querySpecRef;
    }

    /**
     * Indicates whether this column has a query qualifier.
     * @return  true if this is qualified by the query that projects it in its result set; false otherwise
     * @since 3.4.1 (DTP 1.11.1)
     */
    public boolean hasQueryQualifier()
    {
        return m_querySpecRef != null;
    }

    /**
     * Indicates whether this has either a valid number or name expression.
     * @return  true if this has a valid number or name expression; false otherwise
     */
    public boolean isValid()
    {
        if( isIdentifiedByNumber() )
            return true;
        if( hasNameExpression() )
            return true;
        return false;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if( ! (obj instanceof ColumnIdentifier) )
            return false;

        ColumnIdentifier thatObj = (ColumnIdentifier) obj;
        if( this == thatObj )
            return true;

        // compares by position first, if exists
        if( this.isIdentifiedByNumber() )
        {
            if( ! this.m_pos.equals( thatObj.m_pos ) )
                return false;
        }
        
        // compares by name, if exists
        if( this.hasNameExpression() )
        {
            if( ! this.m_nameExpr.equals( thatObj.m_nameExpr ) )
                return false;
        }
        
        // compares the query qualifier
        return this.getQueryQualifier() == thatObj.getQueryQualifier();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        int hashCode = 0;
        // use its position for hashcode if exists
        if( isIdentifiedByNumber() )
            hashCode = m_pos.hashCode();
        
        if( hasNameExpression() )
            hashCode = hashCode ^ m_nameExpr.hashCode();
        
        if( m_querySpecRef != null )
            return hashCode ^ m_querySpecRef.hashCode();

        return (hashCode == 0) ? super.hashCode() : hashCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( LOG_CLASSNAME_PREFIX );
        buffer.append( super.hashCode() );
        buffer.append( LOG_ORDINAL_LABEL );
        buffer.append( m_pos ); 
        buffer.append( LOG_NAME_LABEL );
        buffer.append( m_nameExpr );
        buffer.append( LOG_QUERYSPEC_LABEL );
        buffer.append( m_querySpecRef );
        buffer.append( LOG_END_BRACKET ); 
        return buffer.toString();
    }                  

}
