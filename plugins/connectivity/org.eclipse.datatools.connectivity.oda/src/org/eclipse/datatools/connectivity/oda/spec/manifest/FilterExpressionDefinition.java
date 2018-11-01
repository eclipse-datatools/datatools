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

package org.eclipse.datatools.connectivity.oda.spec.manifest;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.IValidator;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.InternalFilterFactory;

/**
 * Represents the definition of a contributed filter expression type, as specifed in an extension of the
 * <i>org.eclipse.datatools.connectivity.oda.dynamicResultSet</i> extension point.
 * @since 3.2 (DTP 1.7)
 */
public class FilterExpressionDefinition
{
    // element and attribute tags defined in the extension point schema definition
    public static final String ELEMENT_NAME = "filterType"; //$NON-NLS-1$
    public static final String ATTR_ID = "id"; //$NON-NLS-1$
    public static final String ATTR_NAME = "displayName"; //$NON-NLS-1$
    public static final String ATTR_DESC = "description"; //$NON-NLS-1$
    public static final String ATTR_CLASS = "class"; //$NON-NLS-1$
    public static final String ATTR_MIN_ARGS = "minArguments"; //$NON-NLS-1$
    public static final String ATTR_MAX_ARGS = "maxArguments"; //$NON-NLS-1$
    public static final String ATTR_NEGATABLE = "isNegatable"; //$NON-NLS-1$
    public static final String ATTR_OPTIONABLE = "isOptionable"; //$NON-NLS-1$

    static final Integer ATTR_MIN_ARGS_DEFAULT_VALUE = Integer.valueOf(1);
    
    private IConfigurationElement m_exprElement;
    private ExtensionContributor m_contributorInfo;
    private String m_id;
    private String m_name;
    private String m_desc;
    private Integer m_minArgs;
    private Integer m_maxArgs;  // null value means unlimited maximum number of arguments
    private boolean m_isNegatable;
    private boolean m_isOptionable;
    private VariableRestrictions m_varRestrictions;
    
    FilterExpressionDefinition( IConfigurationElement exprElement, ExtensionContributor providerInfo ) throws OdaException
    {
        init( exprElement, providerInfo );
    }
    
    private void init( IConfigurationElement exprElement, ExtensionContributor contributorInfo ) throws OdaException
    {
        ResultExtensionUtil.validateConfigurationElement( exprElement );
        
        m_exprElement = exprElement;
        m_contributorInfo = contributorInfo;
        m_id = getIdAttributeValue( exprElement );
        
        m_name = exprElement.getAttribute( ATTR_NAME );
        
        m_desc = exprElement.getAttribute( ATTR_DESC );
        
        // minArguments
        m_minArgs = ResultExtensionUtil.getMinAttributeValue( exprElement, ATTR_MIN_ARGS, ATTR_MIN_ARGS_DEFAULT_VALUE );
        
        // maxArguments; default value is null for unbounded maximum
        m_maxArgs = ResultExtensionUtil.getMaxAttributeValue( exprElement, ATTR_MAX_ARGS, 
                            null, m_minArgs );
        
        // isNegatable
        m_isNegatable = ResultExtensionUtil.getBooleanAttributeValue( exprElement, ATTR_NEGATABLE, false );

        // isOptional
        m_isOptionable = ResultExtensionUtil.getBooleanAttributeValue( exprElement, ATTR_OPTIONABLE, false );
        
        // process children of variable restrictions
        m_varRestrictions = new VariableRestrictions( exprElement );
    }
    
    /**
     * For internal use only.
     */
    public static String getIdAttributeValue( IConfigurationElement exprElement ) throws OdaException
    {
        return ResultExtensionUtil.getRequiredAttributeValue( exprElement, ATTR_ID, ELEMENT_NAME );
    }
    
    /**
     * Creates and returns an instance of CustomExpression for use in an ODA filter specification,
     * based on the class defined in the attribute specified in this definition.
     * @return  an instance of {@link CustomExpression} or its subclass
     * @throws OdaException
     */
    public CustomExpression createExpression() throws OdaException
    {
        return createExpression( null, null );
    }
    
    /**
     * Creates and returns an instance of CustomExpression for use in an ODA filter specification,
     * based on the class defined in the attribute specified in this definition.
     * @param variable  the expression variable to set on the created instance; may be null
     * @param args      the expression arguments to set on the created instance; may be null
     * @return  an instance of {@link CustomExpression} or its subclass
     * @throws OdaException
     */
    public CustomExpression createExpression( ExpressionVariable variable, ExpressionArguments args )
        throws OdaException
    {
        String className = m_exprElement.getAttribute( ATTR_CLASS );
        if( className != null && className.length() > 0 )
        {
            try
            {
                Object clazz = m_exprElement.createExecutableExtension( ATTR_CLASS );
                if( clazz instanceof CustomExpression )
                {
                    CustomExpression newExpr = (CustomExpression) clazz;
                    newExpr.setVariable( variable );
                    newExpr.setArguments( args );
                    return newExpr;
                }
                else
                    throw new OdaException( Messages.bind( Messages.querySpec_INVALID_CLASS_TYPE_ATTRIBUTE, 
                            new Object[]{ className, ATTR_CLASS, CustomExpression.class.getName()} ));
            }
            catch( CoreException ex )
            {
                throw new OdaException( ex );
            }
        }
        
        // no class attribute value, use the default class provided by the ODA framework
        return InternalFilterFactory.createCustomFilter( getDeclaringExtensionId(), getId(), variable, args );
    }
    
    /**
     * Indicates whether this type of custom filter expression supports
     * the specified data set type of the specified data source type.
     * @param odaDataSourceId   id of an ODA data source extension
     * @param odaDataSetId      id of an ODA data set defined within the data source extension
     * @return  true if this ODA data set type can be used with this type of custom expression; false otherwise
     */
    public boolean supportsDataSetType( String odaDataSourceId, String odaDataSetId )
    {
        return m_contributorInfo.supportsDataSetType( odaDataSourceId, odaDataSetId );
    }

    /**
     * Gets the unique id of the dynamicResultSets extension that declares this type of custom filter expression.
     * @return  unique id of the declaring dynamicResultSets extension 
     */
    public String getDeclaringExtensionId()
    {
        return m_contributorInfo.getDeclaringExtensionId();
    }

    /**
     * Gets the id that uniquely identifies this type of custom filter expression 
     * within the contributing extension.
     * @return  id of this type of custom filter expression
     */
    public String getId()
    {
        return m_id;
    }

    /**
     * Gets the translateable name that can be used to refer to this particular expression in dialogs presented to the user.
     * Defaults to the expression type id if no name is specified.  The name should be unique within the extension.
     * @return  display name of this type of custom filter expression
     */
    public String getDisplayName()
    {
        if( m_name != null && m_name.trim().length() > 0 )
            return m_name;
        return m_id;    // default to the expression id if no name is specified.
    }
   
    /**
     * Gets the brief translateable description of this type of filter expression.
     * @return  description text, or null if none
     */
    public String getDescription()
    {
        return m_desc;
    }

    /**
     * Returns the contributor of this type of custom expression.
     * @return  an instance of the {@link ExtensionContributor} that defines its scope and capabilities
     */
    public ExtensionContributor getContributor()
    {
        return m_contributorInfo;
    }
    
    /**
     * Gets the concrete class that implements the {@link IValidator} to validate this expression. 
     * @return  an instance of the contributor's validator, or null if none is specified
     * @throws  OdaException if exception occurs in instantiating its defined validator class
     */
    public IValidator getValidator() throws OdaException
    {
        return m_contributorInfo.getValidator();
    }
    
    /**
     * Gets the minimum number of argument values required by this expression type. 
     * The value may be greater than or equal to 0. 
     * @return  an Integer for the minimum number of expected argument values
     */
    public Integer getMinArguments()
    {
        return m_minArgs;
    }
    
    /**
     * Indicates whether this expression type has no upper limit on the number of argument values.
     * @return  true if no upper limit on number of argument values; false otherwise
     */
    public boolean supportsUnboundedMaxArguments()
    {
        return ( getMaxArguments() == null );
    }
    
    /**
     * Gets the maximum number of argument values required by this expression type.
     * @return  an Integer for the maximum number of expected argument values,
     *          or null if no upper limit on the maximum arguments.
     * @see {@link #supportsUnboundedMaxArguments()}
     */
    public Integer getMaxArguments()
    {
        return m_maxArgs;
    }
    
    /**
     * Indicates whether this expression can be negated, i.e. applied with a NotExpression.  
     * Default value is false if none is specified in the extension.
     * @return  true if this expression can be negated; false otherwise
     */
    public boolean isNegatable()
    {
        return m_isNegatable;
    }
    
    /**
     * Indicates whether this expression can be optional and skipped in a filter specification, if 
     * none of its expected argument values are provided.  
     * Default value is false if none is specified in the extension.
     * @return true if this expression can be skipped when no argument values are available; 
     *          false otherwise 
     */
    public boolean isOptionable()
    {
        return m_isOptionable;
    }

    /**
     * Gets the restriction info on the types of expression variable that can be applied
     * with this type of filter expression.
     * @return an instance of VariableRestrictions
     */
    public VariableRestrictions getVariableRestrictions()
    {
        return m_varRestrictions;
    }
    
}
