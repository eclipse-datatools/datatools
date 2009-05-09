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

package org.eclipse.datatools.connectivity.oda.spec.manifest;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.IValidator;
import org.eclipse.datatools.connectivity.oda.spec.result.CustomAggregate;

/**
 * Represents the definition of a contributed aggregate type, as specifed in an extension of the
 * <i>org.eclipse.datatools.connectivity.oda.dynamicResultSet</i> extension point.
 * @since 3.2 (DTP 1.7)
 */
public class AggregateDefinition
{
    // element and attribute tags defined in the extension point schema definition
    public static final String ELEMENT_NAME = "aggregateType"; //$NON-NLS-1$
    public static final String ATTR_ID = "id"; //$NON-NLS-1$
    public static final String ATTR_NAME = "displayName"; //$NON-NLS-1$
    public static final String ATTR_DESC = "description"; //$NON-NLS-1$
    public static final String ATTR_CLASS = "class"; //$NON-NLS-1$
    public static final String ATTR_MIN_VARS = "minInputVariables"; //$NON-NLS-1$
    public static final String ATTR_MAX_VARS = "maxInputVariables"; //$NON-NLS-1$
    public static final String ATTR_CAN_IGNORE_DUPLS = "canIgnoreDuplicate"; //$NON-NLS-1$
    public static final String ATTR_CAN_IGNORE_NULLS = "canIgnoreNull"; //$NON-NLS-1$

    static final Integer ATTR_VARS_DEFAULT_VALUE = Integer.valueOf(1);
    
    private IConfigurationElement m_exprElement;
    private ExtensionContributor m_contributorInfo;
    private String m_id;
    private String m_name;
    private String m_desc;
    private Integer m_minVars;
    private Integer m_maxVars;  // null value means unlimited maximum number of arguments
    private boolean m_canIgnoreDupl;
    private boolean m_canIgnoreNull;
    private VariableRestrictions m_varRestrictions;

    AggregateDefinition( IConfigurationElement exprElement, ExtensionContributor providerInfo ) throws OdaException
    {
        init( exprElement, providerInfo );
    }
    
    private void init( IConfigurationElement exprElement, ExtensionContributor contributorInfo ) throws OdaException
    {
        if( ! exprElement.isValid() )
            throw new OdaException( Messages.bind( Messages.querySpec_INVALID_EXT_POINT_ELEMENT,
                                    ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, exprElement.getContributor().getName() ) );
        
        m_exprElement = exprElement;
        m_contributorInfo = contributorInfo;
        m_id = getIdAttributeValue( exprElement );
        
        m_name = exprElement.getAttribute( ATTR_NAME );
        if( m_name == null )
            m_name = m_id;

        m_desc = exprElement.getAttribute( ATTR_DESC );
        
        // minInputVariables
        String attrValue = exprElement.getAttribute( ATTR_MIN_VARS );
        if( attrValue == null )
            m_minVars = ATTR_VARS_DEFAULT_VALUE;
        else
        {
            try
            {
                m_minVars = Integer.valueOf( attrValue );
            }
            catch( NumberFormatException ex )
            {
            }
            if( m_minVars == null || m_minVars.intValue() < 0 )
                throw new OdaException( Messages.bind( Messages.querySpec_INVALID_EXT_POINT_ATTR_VALUE,
                        new Object[] { ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                                exprElement.getContributor().getName(), attrValue, ATTR_MIN_VARS} ) );
        }
        
        // maxInputVariables
        attrValue = exprElement.getAttribute( ATTR_MAX_VARS );
        if( attrValue == null )
            m_maxVars = ATTR_VARS_DEFAULT_VALUE;
        else if( ! attrValue.equals( FilterExpressionDefinition.ATTR_UNBOUNDED_MAX_ARGS ) )
        {
            try
            {
                m_maxVars = Integer.valueOf( attrValue );
            }
            catch( NumberFormatException ex )
            {
            }
            if( m_maxVars == null || m_maxVars.intValue() < m_minVars.intValue() )
                throw new OdaException( Messages.bind( Messages.querySpec_INVALID_EXT_POINT_ATTR_VALUE,
                        new Object[] { ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                                exprElement.getContributor().getName(), attrValue, ATTR_MAX_VARS} ) );
        }

        m_canIgnoreDupl = false;  // default value
        attrValue = exprElement.getAttribute( ATTR_CAN_IGNORE_DUPLS );
        if( attrValue != null )
            m_canIgnoreDupl = Boolean.parseBoolean( attrValue );

        m_canIgnoreNull = false;  // default value
        attrValue = exprElement.getAttribute( ATTR_CAN_IGNORE_NULLS );
        if( attrValue != null )
            m_canIgnoreNull = Boolean.parseBoolean( attrValue );

        // process children of variable restrictions
        m_varRestrictions = new VariableRestrictions( exprElement );
    }
    
    /**
     * For internal use only.
     */
    public static String getIdAttributeValue( IConfigurationElement exprElement ) throws OdaException
    {
        String id = exprElement.getAttribute( ATTR_ID );
        if( id == null || id.length() == 0 )
            throw new OdaException( Messages.bind( Messages.querySpec_MISSING_EXT_POINT_ATTR_VALUE, 
                    new Object[] { ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                                    exprElement.getContributor().getName(), ATTR_ID, ELEMENT_NAME} ));
        return id;
    }
    
    /**
     * Creates and returns an instance of CustomAggregate for use in an ODA aggregate projection specification,
     * based on the class defined in the attribute specified in this definition.
     * @return  an instance of {@link CustomAggregate} or its subclass
     * @throws OdaException
     */
    public CustomAggregate createExpression() throws OdaException
    {
        return createExpression( null );
    }
    
    /**
     * Creates and returns an instance of CustomAggregate for use in an ODA aggregate projection specification,
     * based on the class defined in the attribute specified in this definition.
     * @param variable  the initial input source variable to set on the created instance; may be null
     * @return  an instance of {@link CustomAggregate} or its subclass
     * @throws OdaException
     */
    public CustomAggregate createExpression( ExpressionVariable variable ) throws OdaException
    {
    
        String className = m_exprElement.getAttribute( ATTR_CLASS );
        if( className != null && className.length() > 0 )
        {
            try
            {
                Object clazz = m_exprElement.createExecutableExtension( ATTR_CLASS );
                if( clazz instanceof CustomAggregate )
                    return (CustomAggregate) clazz;
                else
                    throw new OdaException( Messages.bind( Messages.querySpec_INVALID_CLASS_TYPE_ATTRIBUTE, 
                            new Object[]{ className, ATTR_CLASS, CustomAggregate.class.getName()} ));
            }
            catch( CoreException ex )
            {
                throw new OdaException( ex );
            }
        }
        
        // no class attribute value, use the default class provided by the ODA framework
        return new CustomAggregate( getDeclaringExtensionId(), getId(), variable );
    }
    
    /**
     * Indicates whether this type of custom aggregate type supports
     * the specified data set type of the specified data source type.
     * @param odaDataSourceId   id of an ODA data source extension
     * @param odaDataSetId      id of an ODA data set defined within the data source extension
     * @return  true if this ODA data set type can be used with this type of custom aggregate type; false otherwise
     */
    public boolean supportsDataSetType( String odaDataSourceId, String odaDataSetId )
    {
        return m_contributorInfo.supportsDataSetType( odaDataSourceId, odaDataSetId );
    }

    /**
     * Gets the unique id of the dynamicResultSets extension that declares this type of custom aggregate type.
     * @return  unique id of the declaring dynamicResultSets extension 
     */
    public String getDeclaringExtensionId()
    {
        return m_contributorInfo.getDeclaringExtensionId();
    }

    /**
     * Gets the id that uniquely identifies this type of custom aggregate type 
     * within the contributing extension.
     * @return  id of this type of custom aggregate type
     */
    public String getId()
    {
        return m_id;
    }

    /**
     * Gets the translateable name that can be used to refer to this particular aggregate type in dialogs presented to the user.
     * Defaults to the expression type id if no name is specified.  The name should be unique within the extension.
     * @return  display name of this type of custom aggregate type
     */
    public String getDisplayName()
    {
        if( m_name != null && m_name.trim().length() > 0 )
            return m_name;
        return m_id;    // default to the expression id if no name is specified.
    }
   
    /**
     * Gets the brief translateable description, if any.
     * @return description text, or null if none
     */
    public String getDescription()
    {
        return m_desc;
    }

    /**
     * Returns the contributor of this type of custom aggregate expression.
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
     * Gets the minimum number of input source variables required by this expression type. 
     * The value may be greater than or equal to 0. 
     * @return  an Integer for the minimum number of expected input variables
     */
    public Integer getMinInputVariables()
    {
        return m_minVars;
    }
    
    /**
     * Indicates whether this expression type has no upper limit on the number of input source variables.
     * @return  true if no upper limit on number of input variables; false otherwise
     */
    public boolean supportsUnboundedMaxInputVariables()
    {
        return ( getMaxInputVariables() == null );
    }
    
    /**
     * Gets the maximum number of input source variables required by this expression type.
     * @return  an Integer for the maximum number of expected input variables,
     *          or null if no upper limit on the maximum.
     * @see {@link #supportsUnboundedMaxInputVariables()}
     */
    public Integer getMaxInputVariables()
    {
        return m_maxVars;
    }
    
    /**
     * Indicates whether this aggregate type is capable of ignoring duplicate values of its input variable. 
     * Default value is false if none is specified in the extension.
     * @return true if this aggregate type is capable of ignoring duplicate values of its target variable;
     *          false otherwise 
     */
    public boolean canIgnoreDuplicateValues()
    {
        return m_canIgnoreDupl;
    }
    
    /**
     * Indicates whether this aggregate type is capable of ignoring null values of its input variable. 
     * Default value is false if none is specified in the extension.
     * @return true if this aggregate type is capable of ignoring null values of its input variable;
     *          false otherwise 
     */
    public boolean canIgnoreNullValues()
    {
        return m_canIgnoreNull;
    }

    /**
     * Gets the restriction info on the types of expression variable that can be applied
     * with this type of aggregate expression.
     * @return an instance of VariableRestrictions
     */
    public VariableRestrictions getVariableRestrictions()
    {
        return m_varRestrictions;
    }

}
