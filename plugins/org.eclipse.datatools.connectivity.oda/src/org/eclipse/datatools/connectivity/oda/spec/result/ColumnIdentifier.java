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

package org.eclipse.datatools.connectivity.oda.spec.result;

/**
 * The identifier of a result set column, defined by its number (1-based) or native name/expression.
 * <br>A column number, if specified, takes precedence over its specified name/expression.
 * It may be used as an unique key in a {@link java.util.Map}.
 * Comparison by name is case-sensitive.
 */
public class ColumnIdentifier
{
    private Integer m_pos;
    private String m_valueExpr;
    
    /**
     * Creates a column identifier with its ordinal position in the expected result set.
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
     * Creates a column identifier with its native name or expression.
     * @param valueExpr native name or expression of the column
     * @throws IllegalArgumentException if specified argument is null or empty
     */
    public ColumnIdentifier( String valueExpr )
    {
        if( valueExpr == null || valueExpr.length() == 0 )
            throw new IllegalArgumentException( valueExpr );
        
        m_valueExpr = valueExpr;
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
     * @param valueExpr column's native name or expression; may be null
     */
    public void setValueExpression( String valueExpr )
    {
        m_valueExpr = valueExpr;
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
    public String getValueExpression()
    {
        return m_valueExpr;
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
     * Indicates whether this has a value expression specified.
     * The value expression, if exists, is ignored if this is identified by number. 
     * @return  true if this has a value expression; false otherwise
     */
    public boolean hasValueExpression()
    {
        return ( m_valueExpr != null && m_valueExpr.length() > 0 );
    }
    
    /**
     * Indicates whether this has either a valid number or value expression.
     * @return  true if this has a valid number or value expression; false otherwise
     */
    public boolean isValid()
    {
        if( isIdentifiedByNumber() )
            return true;
        if( hasValueExpression() )
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

        // compares by number, if exists
        ColumnIdentifier thatObj = (ColumnIdentifier) obj;
        if( this.isIdentifiedByNumber() )
            return( this.m_pos.equals( thatObj.m_pos ));
        
        if( this.hasValueExpression() )
            return this.m_valueExpr.equals( thatObj.m_valueExpr );

        return false;   // no valid identifier
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        // use its name for hashcode if exists
        if( isIdentifiedByNumber() )
            return m_pos.hashCode();
        
        if( hasValueExpression() )
            return m_valueExpr.hashCode();
        
        return super.hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ColumnIdentifier@" + super.hashCode() + " [number= " + m_pos +   //$NON-NLS-1$//$NON-NLS-2$
                ", value expression= " + m_valueExpr + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }                  

}
