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

package org.eclipse.datatools.connectivity.oda.spec.manifest;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.IValidator;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.AndExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.NotExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.OrExpression;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationFactory;

/**
 * Represents the contributor defining its scope and capabilities, as specifed in an extension of the
 * <i>org.eclipse.datatools.connectivity.oda.dynamicResultSet</i> extension point.
 * @since 3.2 (DTP 1.7)
 */
public class ExtensionContributor implements IContributor
{
    public static final String ELEMENT_NAME = "contributor"; //$NON-NLS-1$
    public static final String SUB_ELEMENT_FILTER_EXPRESSION_TYPE = "supportedOdaFilterExpression"; //$NON-NLS-1$
    public static final String ATTR_ODA_FILTER_EXPR_NAME = "name"; //$NON-NLS-1$
    public static final String ATTR_VALIDATOR_CLASS = "validatorClass"; //$NON-NLS-1$
    public static final String ATTR_SPEC_FACTORY_CLASS = "specificationFactoryClass"; //$NON-NLS-1$
    public static final String SUB_ELEMENT_ROW_ORDERING_SUPPORT = "supportsRowOrdering"; //$NON-NLS-1$
    public static final String ATTR_NULL_ORDERING_SUPPORT = "nullValueOrdering"; //$NON-NLS-1$
    public static final String ATTR_NESTED_VALUEEXPR_SUPPORT = "supportsNestedExpressions"; //$NON-NLS-1$
    public static final String SUB_ELEMENT_COMBINE_QUERIES_SUPPORT = "supportsCombiningQueries"; //$NON-NLS-1$
    
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    
    private IConfigurationElement m_contributorElement;
    private List<SupportedDataSetType> m_dataSetTypes;
    private List<String> m_supportedOdaFilterExprNames;
    private IValidator m_validator;
    private QuerySpecificationFactory m_specFactory;
    private boolean m_supportsRowOrdering;
    private boolean m_supportsNullOrdering;
    private boolean m_supportsNestedValueExprs;
    private boolean m_supportsCombineQueries;
    
    public ExtensionContributor( IConfigurationElement contributorElement ) throws OdaException
    {
        init( contributorElement );
    }
    
    private void init( IConfigurationElement contributorElement ) throws OdaException
    {
        m_contributorElement = contributorElement;
        if( ! m_contributorElement.isValid() || getDeclaringExtensionId() == null )
            throw new OdaException( Messages.bind( Messages.querySpec_INVALID_EXT_POINT_ELEMENT,
                                    ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, contributorElement.getContributor().getName() ) );
        
        // supportedDataSetType child elements
        m_dataSetTypes = processDataSetTypeElements( m_contributorElement );
        
        // supportedOdaExpression child elements are in the filterExpressionTypes element,
        // whose processing will be initiated by ResultExtensionExplorer
        
        // supportsRowOrdering child element
        m_supportsRowOrdering = false;  // default value
        m_supportsNullOrdering = false;
        IConfigurationElement[] rowOrderingElements = contributorElement.getChildren( SUB_ELEMENT_ROW_ORDERING_SUPPORT );
        if( rowOrderingElements.length > 0 )
        {
            m_supportsRowOrdering = true;
            String attrValue = rowOrderingElements[0].getAttribute( ATTR_NULL_ORDERING_SUPPORT );
            if( attrValue != null )
                m_supportsNullOrdering = Boolean.parseBoolean( attrValue );
        }

        // supportsCombiningQueries child element
        m_supportsCombineQueries = false;   // default value
        IConfigurationElement[] combineQueriesElements = contributorElement.getChildren( SUB_ELEMENT_COMBINE_QUERIES_SUPPORT );
        if( combineQueriesElements.length > 0 )
        {
            m_supportsCombineQueries = true;
        }
       
        // processing of optional validator and specificationFactory attributes are deferred till it is needed
    }

    void setSupportedOdaFilterExpressions( IConfigurationElement element ) 
        throws OdaException
    {
        m_supportedOdaFilterExprNames = processSupportedOdaFilterExpressions( element );
    }
    
    /**
     * An utility method to process the specified contributor configuration element and 
     * returns a list of its supported data set types.
     * @param contributorElement    contributor configuration element
     * @return  a list of {@link SupportedDataSetType} declared as supported in the contributor element
     * @throws OdaException if specified element has invalid configuration content
     */
    public static List<SupportedDataSetType> processDataSetTypeElements( IConfigurationElement contributorElement ) 
        throws OdaException
    {
        IConfigurationElement[] dataSetTypeElements = contributorElement.getChildren( SupportedDataSetType.ELEMENT_NAME );
        if( dataSetTypeElements.length == 0 )
            throw new OdaException( Messages.bind( Messages.querySpec_MISSING_EXT_POINT_MIN_ELEMENT, 
                    new Object[]{ ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                        contributorElement.getDeclaringExtension().getUniqueIdentifier(), SupportedDataSetType.ELEMENT_NAME} ) );
        
        List<SupportedDataSetType> dataSetTypes = new ArrayList<SupportedDataSetType>( dataSetTypeElements.length );
        for( int i = 0; i < dataSetTypeElements.length; i++ )
        {
            dataSetTypes.add( new SupportedDataSetType( dataSetTypeElements[i] ));
        }
        return dataSetTypes;
    }
    
    private static List<String> processSupportedOdaFilterExpressions( IConfigurationElement element ) 
        throws OdaException
    {
        IConfigurationElement[] odaExprElements = element.getChildren( SUB_ELEMENT_FILTER_EXPRESSION_TYPE );
        if( odaExprElements.length == 0 )
            return null;
        
        List<String> odaExprNames = new ArrayList<String>( odaExprElements.length );
        for( int i = 0; i < odaExprElements.length; i++ )
        {
            String exprName = odaExprElements[i].getAttribute( ATTR_ODA_FILTER_EXPR_NAME );
            if( exprName != null && 
                ( exprName.equalsIgnoreCase( AndExpression.class.getSimpleName() ) ||
                  exprName.equalsIgnoreCase( OrExpression.class.getSimpleName() ) || 
                  exprName.equalsIgnoreCase( NotExpression.class.getSimpleName() ) ) )
            {
                if( ! odaExprNames.contains( exprName ) )    // adds only if not already exists in collection
                    odaExprNames.add( exprName );
            }
            // else ignore unexpected value
        }
        return odaExprNames;
    }
    
    void processSupportedValueExpressionType( IConfigurationElement valueExprGroupElement )
    {
        m_supportsNestedValueExprs = false;  // default value
        String attrValue = valueExprGroupElement.getAttribute( ATTR_NESTED_VALUEEXPR_SUPPORT );
        if( attrValue != null )
            m_supportsNestedValueExprs = Boolean.parseBoolean( attrValue );
    }
    
    /**
     * Gets the unique extension id that declares this dynamicResultSet contributor.
     * @return unique extension id of this contributor
     */
    public String getDeclaringExtensionId()
    {
        return m_contributorElement.getDeclaringExtension().getUniqueIdentifier();
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IContributor#getName()
     */
    public String getName()
    {
        return m_contributorElement.getContributor().getName();
    }
    
    /**
     * Indicates whether this supports the specified data set type defined within the specified data source type.
     * @param odaDataSourceId   id of an ODA data source extension
     * @param odaDataSetId      id of an ODA data set defined within the data source extension
     * @return  true if this contributes support of the specified ODA data set type; false otherwise
     */
    public boolean supportsDataSetType( String odaDataSourceId, String odaDataSetId )
    {
        for( int i=0; i < m_dataSetTypes.size(); i++ )
        {
            SupportedDataSetType dataSetType = m_dataSetTypes.get( i );
            if( dataSetType.matches( odaDataSourceId, odaDataSetId ) )
                return true;
        }
        return false;
    }
    
    /**
     * Indicates whether this supports the specified data set type.
     * @param dataSetType   an instance of {@link SupportedDataSetType} 
     * @return  true if this contributes support of the specified ODA data set type; false otherwise
     */
    public boolean supportsDataSetType( SupportedDataSetType dataSetType )
    {
        if( dataSetType == null )
            return false;
        return supportsDataSetType( dataSetType.getOdaDataSourceId(), dataSetType.getOdaDataSetId() );
    }

    /**
     * Gets a collection of data set types supported by this contributor.
     * @return  an array of {@link SupportedDataSetType} 
     */
    public SupportedDataSetType[] getSupportedDataSetTypes()
    {
        return m_dataSetTypes.toArray( new SupportedDataSetType[ m_dataSetTypes.size() ] ); 
    }

    /**
     * Indicates whether this supports the specified ODA defined filter expression.
     * @param odaExprName   simple name of an ODA defined filter expression 
     * @return  true if the specified filter expression is supported; false otherwise
     */
    public boolean supportsOdaFilterExpression( String odaExprName )
    {
        if( m_supportedOdaFilterExprNames == null )
            return false;
        return m_supportedOdaFilterExprNames.contains( odaExprName );
    }
    
    /**
     * Gets a collection of the ODA defined filter expression names supported by this contributor.
     * @return  an array of the simple names of supported ODA defined filter expressions 
     */
    public String[] getSupportedOdaFilterExpressions()
    {
        if( m_supportedOdaFilterExprNames == null )
            return EMPTY_STRING_ARRAY;
        return m_supportedOdaFilterExprNames.toArray( new String[ m_supportedOdaFilterExprNames.size() ] ); 
    }

    /**
     * Indicates whether this contributor supports dynamic row ordering of its result sets 
     * for all its supported data set types.
     * @return  true if dynamic row ordering is supported; false otherwise
     */
    public boolean supportsDynamicRowOrdering()
    {
        return m_supportsRowOrdering;
    }

    /**
     * Indicates whether this contributor supports dynamic operation that combines
     * two or more queries for all its supported data set types.
     * @return  true if dynamic combining queries is supported; false otherwise
     * @since 3.4 (DTP 1.11)
     */
    public boolean supportsDynamicCombiningQueries()
    {
        return m_supportsCombineQueries;
    }

    /**
     * Indicates whether this contributor's support of dynamic row ordering includes
     * control over the ordering of null vs. non-null values in the row order.
     * @return  true if dynamic null value ordering is supported; false otherwise
     */
    public boolean supportsNullValueOrdering()
    {
        return supportsDynamicRowOrdering() && m_supportsNullOrdering;
    }
    
    /**
     * Indicates whether this supports the specified ODA built-in combined operator type.
     * @param builtInOperatorId the id of a built-in value expression combined operator type; 
     *          the constants are defined in 
     *          {@link org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpressionOperator}
     * @return  true if the specified built-in combined operator type is supported;
     *          false otherwise
     * @see {@link org.eclipse.datatools.connectivity.oda.spec.util.ExpressionFactory#getCombinedOperator(String, String)}
     * @since 3.2.2 (DTP 1.7.2)
     */
    public boolean supportsOdaCombinedOperator( String builtInOperatorId )
    {
        return ResultExtensionExplorer.getInstance()
                    .supportsOdaCombinedOperator( getDeclaringExtensionId(), builtInOperatorId );
    }

    /**
     * Indicates whether this extension supports handling of combined value expression type.
     * @return true if supported; false otherwise
     * @since 3.2.2 (DTP 1.7.2)
     */
    public boolean supportsCombinedValueExpressionType()
    {
        return ResultExtensionExplorer.getInstance()
                    .supportsCombinedValueExpressionType( getDeclaringExtensionId() );
    }

    /**
     * Indicates whether this extension supports handling of nested value expression type.
     * @return true if supported; false otherwise
     * @since 3.2.2 (DTP 1.7.2)
     */
    public boolean supportsNestedValueExpressionType()
    {
        return m_supportsNestedValueExprs;
    }
    
    /**
     * Indicates whether this extension supports handling of function value expression type.
     * @return true if supported; false otherwise
     * @since 3.2.2 (DTP 1.7.2)
     */
    public boolean supportsFunctionValueExpressionType()
    {
        return ResultExtensionExplorer.getInstance()
                    .supportsFunctionValueExpressionType( getDeclaringExtensionId() );
    }

    /**
     * Gets the {@link IValidator} instance of this contributor.
     * @return  validator instance;
     *          or null if none is specified in the dynamicResultSet extension 
     * @throws  OdaException if exception occurs in instantiating the validator class
     */
    public IValidator getValidator() throws OdaException
    {
        if( m_validator == null )
        {
            String validatorClassName = m_contributorElement.getAttribute( ATTR_VALIDATOR_CLASS );
            if( validatorClassName == null || validatorClassName.length() == 0 ) // no validator class specified
                return null;
            
            // create validator instance based on extension provider's validatorClass name
            try
            {
                Object validatorClass = m_contributorElement.createExecutableExtension( ATTR_VALIDATOR_CLASS );
                if( validatorClass instanceof IValidator )
                    m_validator = (IValidator) validatorClass;
                else
                    throw new OdaException( Messages.bind( Messages.querySpec_INVALID_CLASS_TYPE_ATTRIBUTE, 
                        new Object[]{ validatorClassName, ATTR_VALIDATOR_CLASS, IValidator.class.getName()} ));
            }
            catch( CoreException ex )
            {
                throw new OdaException( ex );
            }
        }
        
        return m_validator;
    }

    /**
     * 
     * @return
     * @throws OdaException
     */
    public QuerySpecificationFactory getSpecificationFactory() throws OdaException
    {
        if( m_specFactory == null )
        {
            String factoryClassName = m_contributorElement.getAttribute( ATTR_SPEC_FACTORY_CLASS );
            if( factoryClassName == null || factoryClassName.length() == 0 ) // no factory class specified
                return null;
            
            // create factory instance based on extension provider's factoryClassName
            try
            {
                Object factoryClass = m_contributorElement.createExecutableExtension( ATTR_SPEC_FACTORY_CLASS );
                if( factoryClass instanceof QuerySpecificationFactory )
                    m_specFactory = (QuerySpecificationFactory) factoryClass;
                else
                    throw new OdaException( Messages.bind( Messages.querySpec_INVALID_CLASS_TYPE_ATTRIBUTE, 
                        new Object[]{ factoryClassName, ATTR_SPEC_FACTORY_CLASS, QuerySpecificationFactory.class.getName()} ));
            }
            catch( CoreException ex )
            {
                throw new OdaException( ex );
            }
        }
        
        return m_specFactory;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return m_contributorElement.hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if( ! (obj instanceof ExtensionContributor) )
            return false;
        return m_contributorElement.equals( ((ExtensionContributor)obj).m_contributorElement );
    }
    
}
