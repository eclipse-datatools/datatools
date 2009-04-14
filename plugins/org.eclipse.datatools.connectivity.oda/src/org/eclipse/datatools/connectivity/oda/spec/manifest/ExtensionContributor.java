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
    public static final String ATTR_ROW_ORDERING_SUPPORT = "supportsRowOrdering"; //$NON-NLS-1$
    
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    
    private IConfigurationElement m_contributorElement;
    private List<SupportedDataSetType> m_dataSetTypes;
    private List<String> m_supportedOdaExprNames;
    private IValidator m_filterValidator;
    private QuerySpecificationFactory m_specFactory;
    private boolean m_supportsRowOrdering;
    
    public ExtensionContributor( IConfigurationElement contributorElement ) throws OdaException
    {
        init( contributorElement );
    }
    
    private void init( IConfigurationElement contributorElement ) throws OdaException
    {
        m_contributorElement = contributorElement;
        if( ! m_contributorElement.isValid() || getDeclaringExtensionId() == null )
            throw new OdaException( Messages.bind( "The {0} extension in ({1}) is invalid.  See the extension point schema definition for required content.",
                                    ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, contributorElement.getContributor().getName() ) );
        
        // supportedDataSetType child elements
        m_dataSetTypes = processDataSetTypeElements( m_contributorElement );
        
        // process supportedOdaExpression child elements
        m_supportedOdaExprNames = processSupportedOdaExpressions( m_contributorElement );
        
        // supportsRowOrdering attribute
        m_supportsRowOrdering = false;  // default value
        String attrValue = contributorElement.getAttribute( ATTR_ROW_ORDERING_SUPPORT );
        if( attrValue != null )
            m_supportsRowOrdering = Boolean.parseBoolean( attrValue );

        // processing of optional validator and specificationFactory attributes are deferred till it is needed
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
            throw new OdaException( Messages.bind( "The {0} extension ({1}) must have at least one {2} element defined .", 
                    new Object[]{ ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                        contributorElement.getDeclaringExtension().getUniqueIdentifier(), SupportedDataSetType.ELEMENT_NAME} ) );
        
        List<SupportedDataSetType> dataSetTypes = new ArrayList<SupportedDataSetType>( dataSetTypeElements.length );
        for( int i = 0; i < dataSetTypeElements.length; i++ )
        {
            dataSetTypes.add( new SupportedDataSetType( dataSetTypeElements[i] ));
        }
        return dataSetTypes;
    }
    
    private static List<String> processSupportedOdaExpressions( IConfigurationElement contributorElement ) 
        throws OdaException
    {
        IConfigurationElement[] odaExprElements = contributorElement.getChildren( SUB_ELEMENT_FILTER_EXPRESSION_TYPE );
        if( odaExprElements.length == 0 )
            return null;
        
        List<String> odaExprNames = new ArrayList<String>( odaExprElements.length );
        for( int i = 0; i < odaExprElements.length; i++ )
        {
            String exprName = odaExprElements[i].getAttribute( ATTR_ODA_FILTER_EXPR_NAME );
            if( exprName != null && 
                ( exprName.equalsIgnoreCase( AndExpression.getName() ) ||
                  exprName.equalsIgnoreCase( OrExpression.getName() ) || 
                  exprName.equalsIgnoreCase( NotExpression.getName() ) ) )
            {
                if( ! odaExprNames.contains( exprName ) )    // adds only if not already exists in collection
                    odaExprNames.add( exprName );
            }
            // else ignore unexpected value
        }
        return odaExprNames;
    }
    
    /**
     * Gets the unique extension id that declares this filterExpressions contributor.
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
     * @return
     */
    public boolean supportsOdaFilterExpression( String odaExprName )
    {
        if( m_supportedOdaExprNames == null )
            return false;
        return m_supportedOdaExprNames.contains( odaExprName );
    }
    
    /**
     * Gets a collection of the ODA defined filter expression names supported by this contributor.
     * @return  an array of the simple names of supported ODA defined filter expressions 
     */
    public String[] getSupportedOdaFilterExpressions()
    {
        if( m_supportedOdaExprNames == null )
            return EMPTY_STRING_ARRAY;
        return m_supportedOdaExprNames.toArray( new String[ m_supportedOdaExprNames.size() ] ); 
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
     * Gets the {@link IValidator} instance of this contributor.
     * @return  validator instance;
     *          or null if none is specified in the dynamicResultSet extension 
     * @throws  OdaException if exception occurs in instantiating the validator class
     */
    public IValidator getValidator() throws OdaException
    {
        if( m_filterValidator == null )
        {
            String validatorClassName = m_contributorElement.getAttribute( ATTR_VALIDATOR_CLASS );
            if( validatorClassName == null || validatorClassName.length() == 0 ) // no validator class specified
                return null;
            
            // create validator instance based on extension provider's validatorClass name
            try
            {
                Object validatorClass = m_contributorElement.createExecutableExtension( ATTR_VALIDATOR_CLASS );
                if( validatorClass instanceof IValidator )
                    m_filterValidator = (IValidator) validatorClass;
                else
                    throw new OdaException( 
                            Messages.bind( "Invalid validator class ({0}) defined; it must implement the {1} interface.",
                                    validatorClassName, IValidator.class.getName() ));
            }
            catch( CoreException ex )
            {
                throw new OdaException( ex );
            }
        }
        
        return m_filterValidator;
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
                    throw new OdaException( 
                            Messages.bind( "Invalid specification factory class ({0}) defined; it must use or extend from {1}.",
                                    factoryClassName, QuerySpecificationFactory.class.getName() ));
            }
            catch( CoreException ex )
            {
                throw new OdaException( ex );
            }
        }
        
        return m_specFactory;
    }
}
