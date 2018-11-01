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

package org.eclipse.datatools.connectivity.oda.spec.valueexpr;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.IValidator;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FunctionExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.manifest.SupportedDataSetType;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;

/**
 * Represents a custom function value expression contributed by an extension of
 * the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * <br>An ODA dynamicResultSet extension declares its contribution of 
 * each custom function value expression in a functionExpressionType element 
 * in its extension manifest.  
 * @since 3.2.2 (DTP 1.7.2)
 */
public class CustomFunction extends FunctionValueExpression implements IExecutableExtension
{
    private static final String QUALIFIER_SEPARATOR = "."; //$NON-NLS-1$

    private String m_extensionId;
    private String m_id;
    private FunctionExpressionDefinition m_definition;  // expects 1-n-only-1 associated definition
    private Map<String,Object> m_customData;
    
    /*
     * Base class constructor.
     * Use ExpressionFactory#createCustomFunction to create a custom function instance.
     */
    protected CustomFunction( String extensionId, String id )
    {
        super( null );  // initialize with no function arguments
        m_extensionId = extensionId;
        m_id = id;
    }
    
    /*
     * Constructor for use by org.eclipse.core.runtime.IExecutableExtension#createExecutableExtension
     * to instantiate an extended class.
     * Use ExpressionFactory#createCustomFunction to create a custom function instance.
     */
    protected CustomFunction()
    {
        super( null );  // no function arguments
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
            m_id = FunctionExpressionDefinition.getIdAttributeValue( exprElement );
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
     * Gets the unique id of the dynamicResultSet extension that declares this custom expression type.
     * @return unique id of declaring extension
     */
    public String getDeclaringExtensionId()
    {
        return m_extensionId;
    }
 
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.valueexpr.FunctionValueExpression#getQualifiedId()
     */
    public String getQualifiedId()
    {
        return m_extensionId + QUALIFIER_SEPARATOR + m_id;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#getName()
     */
    @Override
    public String getName()
    {
        FunctionExpressionDefinition defn = getDefinition();
        if( defn == null )
            return getId();
        return defn.getName();
    }

    /**
     * Indicates whether this expression can be applied to the specified data set type within the data source type.
     * @param odaDataSourceId   id of an ODA data source extension
     * @param odaDataSetId      id of an ODA data set defined within the data source extension
     * @return  true if this can be applied to the specified data set type; false otherwise
     */
    public boolean supportsDataSetType( String odaDataSourceId, String odaDataSetId )
    {
        FunctionExpressionDefinition defn = getDefinition();
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
     * Indicates whether this function type is capable of ignoring duplicate values of its input argument. 
     * Default value is false if none is specified in the extension.
     * @return true if this function type is capable of ignoring duplicate values of its input argument;
     *          false otherwise 
     */
    public boolean canIgnoreDuplicateValues()
    {
        FunctionExpressionDefinition defn = getDefinition();
        return ( defn == null ) ? false : defn.canIgnoreDuplicateValues();
    }

    /**
     * Gets the definition of this expression's capabilities and metadata, as registered by the provider 
     * in its extension of the <i>org.eclipse.datatools.connectivity.oda.dynamicResultSet</i> extension point.
     * @return  definition of this custom function value expression
     */
    public FunctionExpressionDefinition getDefinition()
    {
        // obtain from extension manifest explorer by extensionId + id
        if( m_definition == null )
        {
            try
            {
                m_definition = ResultExtensionExplorer.getInstance()
                    .getExtensionFunctionDefinition( getDeclaringExtensionId(), getId() );
            }
            catch( OdaException ex )
            {
                // TODO log warning
                return null;
            }
        }
        return m_definition;
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

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        try
        {
            FunctionExpressionDefinition defn = getDefinition();
            if( defn == null )
                throw new OdaException( 
                        Messages.bind( Messages.querySpec_NON_DEFINED_CUSTOM_FUNC, getName() ));
            
            validateSyntax( context, defn );     

            // pass to custom validator, if exists, for further validation
            IValidator customValidator = getValidator( context, defn );
            if( customValidator != null )
                customValidator.validateSyntax( this, context );
        }
        catch( OdaException ex )
        {
            throw ValidatorUtil.newValueExprException( this, ex );
        }                
    }

    /**
     * Validates that this expression meets the restriction specified
     * by the expression definition. 
     * @param defn
     * @throws OdaException  if validation fails
     */
    protected void validateSyntax( ValidationContext context, FunctionExpressionDefinition defn ) throws OdaException
    {
        assert( defn != null );
        
        // validate number of associated arguments
        int numArgs = getArguments().valueCount();

        int minArgs = defn.getMinArguments().intValue();
        if( numArgs < minArgs )
            throw newValueExprException( 
                    Messages.bind( Messages.querySpec_CUSTOM_FUNC_LESS_THAN_MIN_ARGS,
                                new Object[]{ getName(), Integer.valueOf(numArgs), Integer.valueOf(minArgs) } ));

        if( ! defn.supportsUnboundedMaxArguments() ) // no unbounded upper limit, validate max arguments
        {
            int maxArgs = defn.getMaxArguments().intValue();
            if( numArgs > maxArgs )
                throw newValueExprException( 
                    Messages.bind( Messages.querySpec_CUSTOM_FUNC_EXCEED_MAX_ARGS, 
                                new Object[]{ getName(), Integer.valueOf(numArgs), Integer.valueOf(maxArgs) } ) );
        }       
        
        // validates the capabilities
        if( ignoresDuplicateValues() && ! defn.canIgnoreDuplicateValues() )
            throw newValueExprException( 
                    Messages.bind( Messages.querySpec_CUSTOM_FUNC_INCOMPATIBLE_DUPL_CHECK, getName() ) ); 
        
        // up to custom validator to validate the data type of function arguments
    }

    protected OdaException newValueExprException( String message )
    {
        return ValidatorUtil.newValueExprException( message, this );
    }
   
    protected IValidator getValidator( ValidationContext context, FunctionExpressionDefinition defn )
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

}
