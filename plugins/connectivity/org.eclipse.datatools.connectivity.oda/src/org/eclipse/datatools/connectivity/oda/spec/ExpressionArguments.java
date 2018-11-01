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

package org.eclipse.datatools.connectivity.oda.spec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.result.ColumnIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.ColumnValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.SimpleValueExpression;

/**
 * Runtime argument(s) of an expression defined in an ODA query specification.
 * <br>Their values are used by an ODA driver to evaluate an expression and its variable
 * specified in an ODA query specification.
 * The argument values are normally provided by an ODA consumer application during runtime,
 * such as by collecting user-input values for an expression's arguments.
 * @since 3.3 (DTP 1.8)
 */
public class ExpressionArguments
{
    protected static final String COMMA_SEPARATOR = ", "; //$NON-NLS-1$

    private Object m_values;
 
    /**
     * Constructor.
     * @param argValues the argument value(s); may be null if no values,
     *          a single value, or
     *          an ordered {@link Collection} that holds multiple values
     */
    public ExpressionArguments( Object argValues )
    {
        setValues( argValues );
    }
    
    public ExpressionArguments()
    {}
    
    /**
     * Indicates whether this has argument values.
     * An empty collection in the values returns false for no values.
     * @return  true if this argument has one or more values; false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean hasValues()
    {
        if( m_values == null )
            return false;
        if( m_values instanceof Collection )
            return ! ((Collection) m_values).isEmpty();
        return true;
    }
    
    /**
     * Returns the number of values in this argument.
     * @return  the number of argument values
     */
    @SuppressWarnings("unchecked")
    public int valueCount()
    {
        if( m_values == null )
            return 0;
        
        if( !( m_values instanceof Collection ))
            return 1;
        
        return ((Collection) m_values).size();
    }
    
    /**
     * Returns all the argument value(s).
     * @return  the argument value(s); may be null if no values,
     *          a single value, or
     *          an ordered {@link Collection} that holds multiple values
     */
    public Object getValues()
    {
        return m_values;
    }
    
    /**
     * Returns the value at the specified position of the ordered collection of argument values.
     * The value at a position is based on the sequence that a value is added using {@link #addValue(Object)}.
     * If the argument's values are not kept in a {@link List}, 
     * all the value(s) are returned at the 0 position.
     * @param index 0-based position of the list of argument values
     * @return  the value at the specified position, 
     *          or null if the index is out of range (index < 0 || index >= size()).
     * @see #getValueExpression(int)
     */
    @SuppressWarnings("unchecked")
    public Object getValue( int index )
    {
        if( index < 0 || index >= valueCount() )
            return null;
        
        if( !( m_values instanceof List ))
            return ( index == 0 ) ? m_values : null;
        
        return ((List) m_values).get( index );
    }
    
    /**
     * Returns the value expression that represents the value at the specified position 
     * of the ordered collection of argument values.
     * @param index 0-based position of the list of argument values
     * @return  an ValueExpression instance representing the value at the specified position, 
     *          or null if the index is out of range (index < 0 || index >= size()).
     * @see #getValue(int)
     */
    public ValueExpression getValueExpression( int index )
    {
        Object value = getValue( index );
        if( value instanceof ValueExpression )
            return (ValueExpression) value;

        if( value instanceof ColumnIdentifier )
            return new ColumnValueExpression( (ColumnIdentifier)value );

        return new SimpleValueExpression( value );
    }
    
    /**
     * Appends the specified value to the end of its list of argument values.
     * It is the responsibility of the caller to ensure compatible type of value object
     * is added to the list.
     * Creates an ordered list, if none already exists, to hold the added value object.
     * @param aValue    a value to add; may be null
     * @return  this, containing the added value
     * @throws UnsupportedOperationException if existing values, if any, are not in a {@link List}
     */
    @SuppressWarnings("unchecked")
    public ExpressionArguments addValue( Object aValue )
    {
        if( m_values == null )
            m_values = new ArrayList(3);
        else
        {
            if( !( m_values instanceof List ))
                throw new UnsupportedOperationException( Messages.querySpec_UNABLE_ADD_TO_NON_LIST_COLLECTION );
        }
        
        ((List) m_values).add( aValue );
        return this;
    }
    
    /**
     * A convenient method to append a value in primitive data type.
     * @param value an int value
     * @return  this, containing the added value
     * @see #addValue(Object)
     */
    public ExpressionArguments addValue( int value )
    {
        return addValue( Integer.valueOf( value ) );
    }
    
    /**
     * A convenient method to append a value in primitive data type.
     * @param value a double value
     * @return  this, containing the added value
     * @see #addValue(Object)
     */
    public ExpressionArguments addValue( double value )
    {
        return addValue( Double.valueOf( value ) );
    }
    
    /**
     * A convenient method to append a value in primitive data type.
     * @param value a boolean value
     * @return  this, containing the added value
     * @see #addValue(Object)
     */
    public ExpressionArguments addValue( boolean value )
    {
        return addValue( Boolean.valueOf( value ) );
    }

    /**
     * Sets all the argument value(s).
     * @param argValues the argument value(s); may be null if no values,
     *          a single value, or
     *          an ordered {@link Collection} that holds multiple values
     */
    public void setValues( Object argValues )
    {
        m_values = argValues;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("unchecked")
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getClass().getSimpleName() + " value(s): (" ); //$NON-NLS-1$
        if( m_values instanceof List )
        {
            List valuesList = (List) m_values;
            for( int i=0; i < valuesList.size(); i++ )
            {
                if( i > 0 )
                    buffer.append( COMMA_SEPARATOR );
                buffer.append( valuesList.get(i) );
            }
        }
        else
            buffer.append( m_values );
        buffer.append( ") " ); //$NON-NLS-1$
        return buffer.toString();
    }
    
}
