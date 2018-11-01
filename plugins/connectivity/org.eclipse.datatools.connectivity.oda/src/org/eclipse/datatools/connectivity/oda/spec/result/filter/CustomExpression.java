/*
 *************************************************************************
 * Copyright (c) 2009, 2010 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec.result.filter;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.IValidator;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FilterExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.manifest.SupportedDataSetType;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;

/**
 * Represents an instance of custom filter expression contributed by an extension of
 * the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * @since 3.2 (DTP 1.7)
 */
public class CustomExpression extends AtomicExpression implements IExecutableExtension
{
    private static final String QUALIFIER_SEPARATOR = "."; //$NON-NLS-1$
    private String m_id;
    private String m_extensionId;
    private Map<String,Object> m_customData;
    private FilterExpressionDefinition m_definition;  // expects 1-n-only-1 associated FilterExpressionDefinition

    /*
     * Base class constructor.
     * Use ExpressionFactory#createCustomExpression to create a custom filter expression instance.
     */
    protected CustomExpression( String extensionId, String id )
    {
        this( extensionId, id, null, null );
    }
    
    /*
     * Base class constructor.
     * Use ExpressionFactory#createCustomExpression to create a custom filter expression instance.
     */
    protected CustomExpression( String extensionId, String id, ExpressionVariable variable, ExpressionArguments args )
    {
        super( variable, args );
        m_extensionId = extensionId;
        m_id = id;
    }
    
    /*
     * Constructor for use by org.eclipse.core.runtime.IExecutableExtension#createExecutableExtension
     * to instantiate an extended class.
     * Use ExpressionFactory#createCustomExpression to create a custom filter expression instance.
     */
    protected CustomExpression()
    {
        super( null, null );
        // the actual extension id and expression id will be filled by #setInitializationData
        // when instantiated by ExpressionFactory
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    public void setInitializationData( IConfigurationElement exprElement,
            String propertyName, Object data ) throws CoreException
    {
        m_extensionId = exprElement.getDeclaringExtension().getUniqueIdentifier();
        try
        {
            m_id = FilterExpressionDefinition.getIdAttributeValue( exprElement );
        }
        catch( OdaException ex )
        {
            throw new CoreException( new Status( IStatus.ERROR, exprElement.getContributor().getName(), ex.getLocalizedMessage() ) );
        }
    }

    /**
     * Gets the expression id.  It is unique within the contributing extension.
     * @return the expression id
     */
    public String getId()
    {
        return m_id;
    }
    
    /**
     * Gets the unique id of the ODA dynamicResultSet extension that declares this custom expression type.
     * @return unique id of declaring extension
     */
    public String getDeclaringExtensionId()
    {
        return m_extensionId;
    }
 
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#getQualifiedId()
     */
    public String getQualifiedId()
    {
        return m_extensionId + QUALIFIER_SEPARATOR + m_id;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#getName()
     */
    @Override
    public String getName()
    {
        FilterExpressionDefinition defn = getDefinition();
        if( defn == null )
            return getQualifiedId();
        return m_extensionId + QUALIFIER_SEPARATOR + defn.getDisplayName();
    }

    /**
     * Indicates whether this expression can be applied to the specified data set type within the data source type.
     * @param odaDataSourceId   id of an ODA data source extension
     * @param odaDataSetId      id of an ODA data set defined within the data source extension
     * @return  true if this can be applied to the specified data set type; false otherwise
     */
    public boolean supportsDataSetType( String odaDataSourceId, String odaDataSetId )
    {
        FilterExpressionDefinition defn = getDefinition();
        if( defn == null )
            return false;
        
        return defn.supportsDataSetType( odaDataSourceId, odaDataSetId );
    }
    
    /**
     * Indicates whether this expression can be applied to the specified data set type.
     * @param dataSetType   an instance of {@link SupportedDataSetType} 
     * @return  true if this can be applied to the specified ODA data set type; false otherwise
     */
    public boolean supportsDataSetType( SupportedDataSetType dataSetType )
    {
        if( dataSetType == null )
            return false;
        return supportsDataSetType( dataSetType.getOdaDataSourceId(), dataSetType.getOdaDataSetId() );
    }
    
    /**
     * Gets the value of an extension-defined property of the specified name.
     * An extension contributor may have arbitrary objects associated with an expression. 
     * @param key   the name of property
     * @return      the value of the named property, or null if it has not been set
     */
    public Object getData( String key )
    {
        if( m_customData == null )
            return null;
        return m_customData.get( key );
    }

    /**
     * Sets the value of an extension-defined property of the specified name.
     * An extension contributor may use this to associate arbitrary objects with an expression.
     * @param key   the name of property
     * @param value the new value of the named property
     */
    public void setData( String key, Object value )
    {
        if( m_customData == null )
            m_customData = new HashMap<String,Object>();
        m_customData.put( key, value );
    }
    
    /**
     * Gets the definition of this expression's capabilities and metadata, as registered by the provider 
     * in its extension of the <i>org.eclipse.datatools.connectivity.oda.dynamicResultSet</i> extension point.
     * @return  definition of this custom filter expression
     */
    public FilterExpressionDefinition getDefinition()
    {
        // obtain from extension manifest explorer by extensionId + id
        if( m_definition == null )
        {
            try
            {
                m_definition = ResultExtensionExplorer.getInstance()
                    .getExtensionFilterDefinition( getDeclaringExtensionId(), getId() );
            }
            catch( OdaException ex )
            {
                // TODO log warning
                return null;
            }
            catch( IllegalArgumentException argEx )
            {
                // TODO log warning
                return null;
            }
        }
        return m_definition;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        try
        {
            FilterExpressionDefinition defn = getDefinition();
            if( defn == null )
                throw newFilterExprException( Messages.bind( Messages.querySpec_NON_DEFINED_CUSTOM_FILTER, getName() ) );
            
            validateSyntax( context, defn );

            // pass to custom validator, if exists, for further validation
            IValidator customValidator = getValidator( context, defn );
            if( customValidator != null )
                customValidator.validateSyntax( this, context );
        }
        catch( OdaException ex )
        {
            throw ValidatorUtil.newFilterExprException( this, ex );
        }                
    }

    /**
     * Validates that this expression meets the restriction specified
     * by the expression definition. 
     * @param context
     * @param defn
     * @throws OdaException  if validation fails
     */
    protected void validateSyntax( ValidationContext context, FilterExpressionDefinition defn ) throws OdaException
    {
        assert( defn != null );
        
        // ODA filter spec allows for 0..1 associated ExpressionVariable; up to driver to add validation
        // on the type of variable that it supports

        // validates the associated arguments
        validateExpressionArguments( context, defn );
    }
    
    /**
     * Validates the number of argument values matches the range of arguments defined for the expression
     * @param context
     * @param defn
     * @throws OdaException  if validation fails
     */
    protected void validateExpressionArguments( ValidationContext context, FilterExpressionDefinition defn ) 
        throws OdaException
    {
        int numArgs = getArguments().valueCount();

        int minArgs = defn.getMinArguments().intValue();
        if( numArgs < minArgs )
            throw newFilterExprException( 
                    Messages.bind( Messages.querySpec_CUSTOM_FILTER_LESS_THAN_MIN_ARGS,
                                new Object[]{ getName(), Integer.valueOf(numArgs), Integer.valueOf(minArgs) } ));

        if( ! defn.supportsUnboundedMaxArguments() ) // not unbounded upper limit, validate max arguments
        {
            int maxArgs = defn.getMaxArguments().intValue();
            if( numArgs > maxArgs )
                throw newFilterExprException( 
                    Messages.bind( Messages.querySpec_CUSTOM_FILTER_EXCEED_MAX_ARGS, 
                                new Object[]{ getName(), Integer.valueOf(numArgs), Integer.valueOf(maxArgs) } ) );
        }       
    }
    
    protected IValidator getValidator( ValidationContext context, FilterExpressionDefinition defn )
    {
        // try use the validator in the context, if available
        if( context != null && context.getValidator() != null )
            return context.getValidator();

        // use validator in the definition, if specified
        try
        {
            if( defn != null )
                return defn.getValidator();
        }
        catch( OdaException ex )
        {
            // TODO log warning
        }

        return null;
    }

    protected OdaException newFilterExprException( String message )
    {
        return ValidatorUtil.newFilterExprException( message, this );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#isNegatable()
     */
    @Override
    public boolean isNegatable()
    {
        FilterExpressionDefinition defn = getDefinition();
        return ( defn == null ) ? false : defn.isNegatable();   // default value is false
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.filter.AtomicExpression#isOptionable()
     */
    public boolean isOptionable()
    {
        FilterExpressionDefinition defn = getDefinition();
        return ( defn == null ) ? super.isOptionable() : defn.isOptionable();
    }
    
}
