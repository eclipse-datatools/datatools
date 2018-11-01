/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestUtil;


/**
 * This singleton explorer is the entry point to explore and access
 * the manifest of all the extensions that implement the 
 * <code>org.eclipse.datatools.connectivity.oda.dynamicResultSet</code> extension point.
 * The <code>ResultExtensionExplorer</code> singleton instance is retrieved 
 * using the <code>getInstance()</code> method.
 * @since 3.3 (DTP 1.8)
 */
public class ResultExtensionExplorer
{
    public static final String DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT = 
        "org.eclipse.datatools.connectivity.oda.dynamicResultSet";  //$NON-NLS-1$
    public static final String FILTER_GROUP_NAME = "filterExpressionTypes"; //$NON-NLS-1$
    public static final String AGGREGATE_GROUP_NAME = "aggregateExpressionTypes"; //$NON-NLS-1$
    public static final String VALUE_EXPR_GROUP_NAME = "valueExpressionTypes"; //$NON-NLS-1$
    private static final String COMBINED_OPERATOR_SUBGROUP_NAME = "combinedOperatorTypes"; //$NON-NLS-1$
    
    private static ResultExtensionExplorer sm_instance = null;
    
    // trace logging variables
    private static Logger sm_logger = null;

    private static final ExtensionContributor[] EMTPY_CONTRIBUTOR_ARRAY = new ExtensionContributor[0];
    private static final FilterExpressionDefinition[] EMPTY_FILTER_EXPRS_ARRAY = new FilterExpressionDefinition[0];
    private static final AggregateDefinition[] EMPTY_AGGR_EXPRS_ARRAY = new AggregateDefinition[0];
    private static final CombinedExpressionOperatorDefinition[] EMPTY_COMBINED_OP_ARRAY = 
        new CombinedExpressionOperatorDefinition[0];
    private static final FunctionExpressionDefinition[] EMPTY_FUNC_TYPES_ARRAY = new FunctionExpressionDefinition[0];
    
    private Map<ExtensionContributor,Map<String,FilterExpressionDefinition>> 
        m_filterTypesByExtn;  // cached copy of filter expression type map by extension contributor
    private Map<ExtensionContributor,Map<String,AggregateDefinition>> 
        m_aggregateTypesByExtn;  // cached copy of aggregate type map by extension contributor
    private Map<String,Map<String,CombinedExpressionOperatorDefinition>>
        m_combinedOpTypesByExtnId;  // cached copy of supported combined operator type map by extension id
    private Map<String,Map<String,FunctionExpressionDefinition>>
        m_functionTypesByExtnId;  // cached copy of value expression function type map by extension id
    
    /**
     * Gets the singleton instance to explore the manifest of the dynamicResultSet extensions.
     * @return  the singleton instance
     */
    public static ResultExtensionExplorer getInstance()
    {
        if( sm_instance == null )
        {
            synchronized( ResultExtensionExplorer.class )
            {
                if( sm_instance == null )
                {
                    sm_instance = new ResultExtensionExplorer();
                }
            }
        }
        
        return sm_instance;
    }
    
    /**
     * Singleton instance release method.
     */
    public static void releaseInstance()
    {
        synchronized( ResultExtensionExplorer.class )
        {
            sm_instance = null;
            sm_logger = null;
        }
    }
    
    static Logger getLogger()
    {
        if( sm_logger == null )
        {
            synchronized( ResultExtensionExplorer.class )
            {
                if( sm_logger == null )
                    sm_logger = Logger.getLogger( ResultExtensionExplorer.class.getPackage().getName() );
            }
        }
        return sm_logger;
    }

    private ResultExtensionExplorer() 
    {
        // look up all dynamicResultSet extensions in registry, create definitions and cache in instance             
        addAllExtensions();
    }
    
    /**
     * Refresh the explorer, and allows it to get the latest extension manifests.
     */
    public void refresh()
    {
        addAllExtensions();     // re-add current extensions to cache
    }

    private void resetCache()
    {
        if( m_filterTypesByExtn != null )
            m_filterTypesByExtn.clear();
        if( m_aggregateTypesByExtn != null )
            m_aggregateTypesByExtn.clear();
        if( m_combinedOpTypesByExtnId != null )
            m_combinedOpTypesByExtnId.clear();      
        if( m_functionTypesByExtnId != null )
            m_functionTypesByExtnId.clear();
    }
    
    private Map<ExtensionContributor,Map<String,FilterExpressionDefinition>> getCachedFilterExtensions()
    {
        if( m_filterTypesByExtn == null )
        {
            synchronized( this )
            {
                if( m_filterTypesByExtn == null )
                {
                    m_filterTypesByExtn = new HashMap<ExtensionContributor,Map<String,FilterExpressionDefinition>>(4);
                }
            }
        }
        return m_filterTypesByExtn;
    }
    
    /**
     * Gets the filter definitions cached for the contributor defined with the specified extensionId.
     * @param extensionId   oda.dynamicResultSet extension id
     * @return  may be null if specified extensionId is not found in cache
     */
    private Map<String,FilterExpressionDefinition> getCachedFilterDefinitionsByExtension( String extensionId )
    {
        ExtensionContributor contributor = findCachedContributorOfFilterExtension( extensionId );
        if( contributor != null )   // extensionId is cached
            return getCachedFilterExtensions().get( contributor );
        return null;
    }
    
    private ExtensionContributor findCachedContributorOfFilterExtension( String extensionId )
    {
        if( getCachedFilterExtensions().isEmpty() )
            return null;
        
        // iterate thru each contributor key in cache to find matching contributor of the specified extensionId
        Iterator<ExtensionContributor> iter = getCachedFilterExtensions().keySet().iterator();
        while( iter.hasNext() )
        {
            ExtensionContributor aContributor = iter.next();
            if( aContributor.getDeclaringExtensionId().equals( extensionId ) )
                return aContributor;
        }

        return null;
    }
    
    private Map<ExtensionContributor,Map<String,AggregateDefinition>> getCachedAggregateExtensions()
    {
        if( m_aggregateTypesByExtn == null )
        {
            synchronized( this )
            {
                if( m_aggregateTypesByExtn == null )
                {
                    m_aggregateTypesByExtn = new HashMap<ExtensionContributor,Map<String,AggregateDefinition>>(4);
                }
            }
        }
        return m_aggregateTypesByExtn;
    }
    
    /**
     * Gets the aggregate definitions cached for the contributor defined with the specified extensionId.
     * @param extensionId   oda.dynamicResultSet extension id
     * @return  may be null if specified extensionId is not found in cache
     */
    private Map<String,AggregateDefinition> getCachedAggregateDefinitionsByExtension( String extensionId )
    {
        ExtensionContributor contributor = findCachedContributorOfAggregateExtension( extensionId );
        if( contributor != null )
            return getCachedAggregateExtensions().get( contributor );
        return null;
    }   
    
    private ExtensionContributor findCachedContributorOfAggregateExtension( String extensionId )
    {
        if( getCachedAggregateExtensions().isEmpty() )
            return null;
        
        // iterate thru each contributor key in cache to find matching contributor of the specified extensionId
        Iterator<ExtensionContributor> iter = getCachedAggregateExtensions().keySet().iterator();
        while( iter.hasNext() )
        {
            ExtensionContributor aContributor = iter.next();
            if( aContributor.getDeclaringExtensionId().equals( extensionId ) )
                return aContributor;
        }

        return null;
    }    

    private Map<String,Map<String,CombinedExpressionOperatorDefinition>> 
        getCachedCombinedOperatorExtensions()
    {
        if( m_combinedOpTypesByExtnId == null )
        {
            synchronized( this )
            {
                if( m_combinedOpTypesByExtnId == null )
                {
                    m_combinedOpTypesByExtnId = new HashMap<String,Map<String,CombinedExpressionOperatorDefinition>>(4);
                }
            }
        }
        return m_combinedOpTypesByExtnId;
    }
    
    /**
     * Gets the CombinedExpressionOperatorDefinition instances cached for the contributor defined with the specified extensionId.
     * @param extensionId   oda.dynamicResultSet extension id
     * @return  may be null if specified extensionId is not found in cache
     */
    private Map<String,CombinedExpressionOperatorDefinition> getCachedCombinedOpDefinitionsByExtension( String extensionId )
    {
        return getCachedCombinedOperatorExtensions().get( extensionId );
    }   
    
    /**
     * Gets the FunctionExpressionDefinition instances cached for the contributor defined with the specified extensionId.
     * @param extensionId   oda.dynamicResultSet extension id
     * @return  may be null if specified extensionId is not found in cache
     */
    private Map<String,FunctionExpressionDefinition> getCachedFunctionDefinitionsByExtension( String extensionId )
    {
        return getCachedFunctionExtensions().get( extensionId );
    }   

    private Map<String,Map<String,FunctionExpressionDefinition>> getCachedFunctionExtensions()
    {
        if( m_functionTypesByExtnId == null )
        {
            synchronized( this )
            {
                if( m_functionTypesByExtnId == null )
                {
                    m_functionTypesByExtnId = new HashMap<String,Map<String,FunctionExpressionDefinition>>(4);
                }
            }
        }
        return m_functionTypesByExtnId;
    }
    
    private static FilterExpressionDefinition[] convertFilterDefnValuesToSortByNameArray( 
            Map<String,FilterExpressionDefinition> exprDefns )
    {   
        if( exprDefns == null )
            return EMPTY_FILTER_EXPRS_ARRAY;

        // sort given expression definitions by their display names
        TreeMap<String,FilterExpressionDefinition> sortedDefnsByName = new TreeMap<String,FilterExpressionDefinition>();
        for( FilterExpressionDefinition exprDefn : exprDefns.values() )
        {
            sortedDefnsByName.put( exprDefn.getDisplayName(), exprDefn );
        }
        
        return sortedDefnsByName.values().toArray( new FilterExpressionDefinition[ sortedDefnsByName.size() ] );
    }

    private static AggregateDefinition[] convertAggregateDefnValuesToSortByNameArray( 
            Map<String,AggregateDefinition> exprDefns )
    {   
        if( exprDefns == null )
            return EMPTY_AGGR_EXPRS_ARRAY;
        
        // sort given expression definitions by their display names
        TreeMap<String,AggregateDefinition> sortedDefnsByName = new TreeMap<String,AggregateDefinition>();
        for( AggregateDefinition exprDefn : exprDefns.values() )
        {
            sortedDefnsByName.put( exprDefn.getDisplayName(), exprDefn );
        }

        return sortedDefnsByName.values().toArray( new AggregateDefinition[ sortedDefnsByName.size() ] );
    }

    private static CombinedExpressionOperatorDefinition[] convertCombinedOpDefnValuesToArray( 
            Map<String,CombinedExpressionOperatorDefinition> combinedOpDefns )
    {   
        if( combinedOpDefns == null )
            return EMPTY_COMBINED_OP_ARRAY;
        
        return combinedOpDefns.values().toArray( new CombinedExpressionOperatorDefinition[ combinedOpDefns.size() ] );
    }

    private static FunctionExpressionDefinition[] convertFunctionDefnValuesToSortByNameArray( 
            Map<String,FunctionExpressionDefinition> functionTypes )
    {   
        if( functionTypes == null )
            return EMPTY_FUNC_TYPES_ARRAY;
        
        // sort given expression definitions by their display names
        TreeMap<String,FunctionExpressionDefinition> sortedDefnsByName = new TreeMap<String,FunctionExpressionDefinition>();
        for( FunctionExpressionDefinition functionType : functionTypes.values() )
        {
            sortedDefnsByName.put( functionType.getDisplayName(), functionType );
        }

        return sortedDefnsByName.values().toArray( new FunctionExpressionDefinition[ sortedDefnsByName.size() ] );
    }
    
    /**
     * Gets the collection of contributors that contribute dynamicResultSet extension to the specified data set type
     * defined within the specified data source type.
     * @param odaDataSourceId   id of an ODA data source extension
     * @param odaDataSetId      id of an ODA data set defined within the data source extension
     * @return  an array of {@link ExtensionContributor};
     *          or an empty array if no supporting contributor is registered 
     * @throws OdaException
     */
    public ExtensionContributor[] getContributorsOfDataSet( String odaDataSourceId, String odaDataSetId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( odaDataSourceId );        
        validateArgumentExists( odaDataSetId );        
        
        // first check if specified data set type is already in cache, and use it
        ExtensionContributor[] contributors = findCachedContributorsByDataSet( odaDataSourceId, odaDataSetId );
        return ( contributors == null ) ? EMTPY_CONTRIBUTOR_ARRAY : contributors;
    }

    private ExtensionContributor[] findCachedContributorsByDataSet( String odaDataSourceId, String odaDataSetId )
    {
        // a data set type may be supported by multiple contributors, 
        // with separate ones for filter vs. aggregate expressions
        Set<ExtensionContributor> contributors = new HashSet<ExtensionContributor>(4);

        List<ExtensionContributor> filterContributors = findCachedContributorsOfFilterExtension( odaDataSourceId, odaDataSetId );
        if( filterContributors != null )
            contributors.addAll( filterContributors );
        
        List<ExtensionContributor> aggrContributors = findCachedContributorsOfAggregateExtension( odaDataSourceId, odaDataSetId );
        if( aggrContributors != null )
            contributors.addAll( aggrContributors );    // only unique contributors get added to the Set

        return contributors.isEmpty() ? null : 
                contributors.toArray( new ExtensionContributor[ contributors.size() ] );
    }

    private List<ExtensionContributor> findCachedContributorsOfFilterExtension( String odaDataSourceId, String odaDataSetId )
    {
        if( getCachedFilterExtensions().isEmpty() )
            return null;

        // iterate thru each contributor key in cache to find matching contributor of the specified extensionId
        return findSupportingContributors( getCachedFilterExtensions().keySet().iterator(), 
                    odaDataSourceId, odaDataSetId );
    }
  
    private List<ExtensionContributor> findCachedContributorsOfAggregateExtension( String odaDataSourceId, String odaDataSetId )
    {
        if( getCachedAggregateExtensions().isEmpty() )
            return null;

        return findSupportingContributors( getCachedAggregateExtensions().keySet().iterator(), 
                    odaDataSourceId, odaDataSetId );
    }
    
    private List<ExtensionContributor> findSupportingContributors( Iterator<ExtensionContributor> iter,
            String odaDataSourceId, String odaDataSetId )
    {
        List<ExtensionContributor> contributors = null;
        
        // iterate thru each contributor to find all contributors that support the specified data set type
        while( iter.hasNext() )
        {
            ExtensionContributor aContributor = iter.next();
            if( aContributor.supportsDataSetType( odaDataSourceId, odaDataSetId ) )
            {
                if( contributors == null )
                    contributors = new ArrayList<ExtensionContributor>(4);
                contributors.add( aContributor );
            }
        }

        return ( contributors == null || contributors.isEmpty() ) ? null : contributors;
    }    
    
    /**
     * Gets the collection of custom filter expression definitions declared by the specified contributor.
     * @param extensionContributor  contributor of a dynamicResultSet extension
     * @return  an array of {@link FilterExpressionDefinition} declared by the specified contributor; 
     *          or an empty array if none
     * @throws IllegalArgumentException
     * @throws OdaException
     */
    public FilterExpressionDefinition[] getContributedFilterDefinitions( ExtensionContributor extensionContributor )
        throws IllegalArgumentException, OdaException
    {
        Map<String,FilterExpressionDefinition> filterExprDefns = getFilterDefinitionsByContributor( extensionContributor );
        return convertFilterDefnValuesToSortByNameArray( filterExprDefns );
    }

    /**
     * Gets the definition of the specified custom filter expression declared by the
     * specified expression contributor.
     * @param extensionContributor  contributor of a dynamicResultSet extension
     * @param exprId    id of a custom filter expression 
     * @return  an instance of {@link FilterExpressionDefinition}, or null if no matching definition is found
     * @throws IllegalArgumentException
     * @throws OdaException
     */
    public FilterExpressionDefinition getContributedFilterDefinition( ExtensionContributor extensionContributor, String exprId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( exprId );

        Map<String,FilterExpressionDefinition> filterExprDefns = getFilterDefinitionsByContributor( extensionContributor );
        return ( filterExprDefns == null ) ? null : filterExprDefns.get( exprId );
    }
    
    /**
     * Gets the collection of custom filter expression definitions declared by the specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @return  an array of {@link FilterExpressionDefinition} defined by the specified extension; 
     *          or an empty array if none
     * @throws OdaException
     */
    public FilterExpressionDefinition[] getExtensionFilterDefinitions( String extensionId ) 
        throws IllegalArgumentException, OdaException
    {
        Map<String,FilterExpressionDefinition> filterExprDefns = getFilterDefinitionsByExtension( extensionId );
        return convertFilterDefnValuesToSortByNameArray( filterExprDefns );
    }

    /**
     * Gets the definition of the specified custom filter expression type declared by the
     * specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param exprId    id of a custom filter expression 
     * @return  an instance of {@link FilterExpressionDefinition}, or null if no matching definition is found
     * @throws IllegalArgumentException
     * @throws OdaException
     */
    public FilterExpressionDefinition getExtensionFilterDefinition( String extensionId, String exprId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( exprId );
        
        Map<String,FilterExpressionDefinition> filterExprDefns = getFilterDefinitionsByExtension( extensionId );
        
        // get the definition of the specified expression 
        return ( filterExprDefns == null ) ? null : filterExprDefns.get( exprId );
    }
    
    private Map<String,FilterExpressionDefinition> getFilterDefinitionsByExtension( String extensionId )
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( extensionId );        
        
        // since all extensions should already be loaded in instance,
        // just check if specified extension is already in cache, and use it
        Map<String,FilterExpressionDefinition> filterExprDefns = getCachedFilterDefinitionsByExtension( extensionId );
        return filterExprDefns;
    }
    
    private Map<String,FilterExpressionDefinition> getFilterDefinitionsByContributor( ExtensionContributor extensionContributor )
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( extensionContributor );  
        
        // since all extensions should already be loaded in instance,
        // just check if specified extension is already in cache, and use it
        Map<String,FilterExpressionDefinition> filterExprDefns = 
                        getCachedFilterExtensions().get( extensionContributor );
        return filterExprDefns;
    }
    
    /**
     * Returns the contributor of the specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @return  an instance of the {@link ExtensionContributor} that defines its scope and capabilities
     * @throws OdaException
     */
    public ExtensionContributor getExtensionContributor( String extensionId ) 
        throws OdaException
    {
        // first try find from the filter definition map
        ExtensionContributor contributor = findCachedContributorOfFilterExtension( extensionId );
        if( contributor != null )   // found contributor
            return contributor;
        
        // not found; next try find from the aggregate definition map
        contributor = findCachedContributorOfAggregateExtension( extensionId );
        return contributor;       
    }
    
    /* 
     * Look up all current extensions in registry, and add each corresponding definitions to cached copy
     * if not already in cache.  Existing cached extensions, if any, are left as is.
     */
    private void addAllExtensions()
    {
        IExtension[] extensions = ManifestExplorer.getExtensions( DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT );
        synchronized( this )
        {
            // first clear cached collections, if exist
            resetCache();
            
            for( int i=0; i < extensions.length; i++ )
            {
                try
                {
                    addExtension( extensions[i], false );
                }
                catch( OdaException ex )
                {
                    // log warning on invalid extension manifest
                    getLogger().log( Level.WARNING, 
                            "Ignoring invalid " + DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT + " extension.",  //$NON-NLS-1$ //$NON-NLS-2$
                            ex );
                }
            }
        }
    }
        
    /**
     * Adds the specified extension definition to cached copy.  An extension is added 
     * if not already in cache.  Existing cached copy may be replaced as controlled by the
     * replaceExisting argument.
     * @param dynamicResultExtn
     * @param replaceExisting   indicates whether to replace the cached extension if already exists
     * @return  may return an empty map
     * @throws OdaException
     */
    private ExtensionContributor addExtension( IExtension dynamicResultExtn, boolean replaceExisting )
        throws OdaException
    {
        // contributor element
        IConfigurationElement contributorElement =
            ManifestUtil.getNamedElement( dynamicResultExtn, ExtensionContributor.ELEMENT_NAME );
        if( contributorElement == null )
            throw new OdaException( Messages.bind( Messages.querySpec_MISSING_EXT_POINT_ELEMENT, 
                    new Object[]{ ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                                    dynamicResultExtn.getUniqueIdentifier(), ExtensionContributor.ELEMENT_NAME} ));
        
        ExtensionContributor contributor = new ExtensionContributor( contributorElement );

        // if replace existing filter expressions in cache, or nothing in cache for this contributor
        if( replaceExisting || getCachedFilterExtensions().get( contributor ) == null ) 
        {       
            // process the filterExpressionTypes group element, if any, in extension; expects 0 or 1 group element
            IConfigurationElement[] filterExprGroup =
                ManifestUtil.getNamedElements( dynamicResultExtn, FILTER_GROUP_NAME );
            if( filterExprGroup.length > 0 )
            {
                // process supportedOdaFilterExpression child elements
                contributor.setSupportedOdaFilterExpressions( filterExprGroup[0] );
                
                // process custom filterType child elements
                IConfigurationElement[] filterExprElements = filterExprGroup[0].getChildren( FilterExpressionDefinition.ELEMENT_NAME ); 
    
                Map<String,FilterExpressionDefinition> filterExprs = new HashMap<String,FilterExpressionDefinition>(filterExprElements.length);
                for( int i=0; i < filterExprElements.length; i++ )
                {
                    FilterExpressionDefinition filerExprDefn =
                        new FilterExpressionDefinition( filterExprElements[i], contributor );
                    filterExprs.put( filerExprDefn.getId(), filerExprDefn );   // replace existing entry, if exists
                }
                
                // cache contributed filter expression map by contributor
                getCachedFilterExtensions().put( contributor, filterExprs );   
            }
        }
 
        // next process the aggregateExpressionTypes group element in extension; expects 0 or 1 group element
        if( replaceExisting || getCachedAggregateExtensions().get( contributor ) == null ) 
        {
            IConfigurationElement[] aggregateExprGroup =
                ManifestUtil.getNamedElements( dynamicResultExtn, AGGREGATE_GROUP_NAME );
            if( aggregateExprGroup.length > 0 )
            {
                IConfigurationElement[] aggregateElements = aggregateExprGroup[0].getChildren( AggregateDefinition.ELEMENT_NAME ); 
        
                Map<String,AggregateDefinition> aggregateExprs = new HashMap<String,AggregateDefinition>(aggregateElements.length);
                for( int i=0; i < aggregateElements.length; i++ )
                {
                    AggregateDefinition aggregateDefn =
                        new AggregateDefinition( aggregateElements[i], contributor );
                    aggregateExprs.put( aggregateDefn.getId(), aggregateDefn );   // replace existing entry, if exists
                }
                
                // cache contributed aggregate type map by contributor
                getCachedAggregateExtensions().put( contributor, aggregateExprs );
            }
        }
        
        // next process the valueExpressionTypes group element in extension; expects 0 or 1 group element
        if( replaceExisting || getCachedCombinedOperatorExtensions().get( contributor.getDeclaringExtensionId() ) == null ) 
        {           
            IConfigurationElement[] valueExprGroup =
                ManifestUtil.getNamedElements( dynamicResultExtn, VALUE_EXPR_GROUP_NAME );
            if( valueExprGroup.length > 0 )
                addExtensionValueExprGroupElement( contributor, valueExprGroup[0] );            
        }
        
        return contributor;
    }
    
    private void addExtensionValueExprGroupElement( ExtensionContributor contributor, IConfigurationElement valueExprGroupElement )
        throws OdaException
    {
        // process the combinedOperatorTypes subgroup element in extension; expects 0 or 1 subgroup element
        IConfigurationElement[] combinedOperatorGroup =
            valueExprGroupElement.getChildren( COMBINED_OPERATOR_SUBGROUP_NAME ); 
        if( combinedOperatorGroup.length > 0 )
            addExtensionCombinedOperatorGroupElement( contributor.getDeclaringExtensionId(), combinedOperatorGroup[0] );

        // process the functionExpressionType sub elements in extension
        IConfigurationElement[] functionTypeElements = valueExprGroupElement.getChildren( FunctionExpressionDefinition.ELEMENT_NAME ); 
        addExtensionFunctionTypeElements( contributor, functionTypeElements );
        
        // delegates to the contributor to process the types of value expression declared as supported
        contributor.processSupportedValueExpressionType( valueExprGroupElement );
    }
    
    private void addExtensionCombinedOperatorGroupElement( String extensionId, IConfigurationElement combinedOperatorGroup )
        throws OdaException
    {
        IConfigurationElement[] supportedOdaCombinedOpElements = 
            combinedOperatorGroup.getChildren( CombinedExpressionOperatorDefinition.SUPPORTED_ELEMENT_NAME ); 
        IConfigurationElement[] customCombinedOpElements = 
            combinedOperatorGroup.getChildren( CombinedExpressionOperatorDefinition.CUSTOM_ELEMENT_NAME ); 

        Map<String,CombinedExpressionOperatorDefinition> combinedOperatorTypes = 
            new HashMap<String,CombinedExpressionOperatorDefinition>(
                supportedOdaCombinedOpElements.length + customCombinedOpElements.length );
        for( int i=0; i < supportedOdaCombinedOpElements.length; i++ )
        {
            CombinedExpressionOperatorDefinition supportedOdaCombinedOpDefn =
                new CombinedExpressionOperatorDefinition( supportedOdaCombinedOpElements[i] );
            combinedOperatorTypes.put( supportedOdaCombinedOpDefn.getId(), supportedOdaCombinedOpDefn );   // replace existing entry, if exists
        }
        for( int i=0; i < customCombinedOpElements.length; i++ )
        {
            CombinedExpressionOperatorDefinition.CustomCombinedOperatorDefinition customCombinedOpDefn =
                CombinedExpressionOperatorDefinition.newCustomDefinition( customCombinedOpElements[i] );
            combinedOperatorTypes.put( customCombinedOpDefn.getId(), customCombinedOpDefn );   // replace existing entry, if exists
        }
        
        // cache supported and custom combined operator type map by extension id
        getCachedCombinedOperatorExtensions().put( extensionId, combinedOperatorTypes );
    }
 
    private void addExtensionFunctionTypeElements( ExtensionContributor contributor, IConfigurationElement[] functionTypeElements )
        throws OdaException
    {
        if( functionTypeElements.length == 0 )
            return;     // nothing to add
        
        Map<String,FunctionExpressionDefinition> functionTypes = 
            new HashMap<String,FunctionExpressionDefinition>( functionTypeElements.length );
        for( int i=0; i < functionTypeElements.length; i++ )
        {
            FunctionExpressionDefinition funcDefn = new FunctionExpressionDefinition( functionTypeElements[i], contributor );
            functionTypes.put( funcDefn.getId(), funcDefn );  // replace existing entry, if exists
        }

        // cache supported and custom combined operator type map by extension id
        getCachedFunctionExtensions().put( contributor.getDeclaringExtensionId(), functionTypes );        
    }
    
    /**
     * Gets the collection of custom aggregate definitions declared by the specified contributor.
     * @param extensionContributor  contributor of a dynamicResultSet extension
     * @return  an array of {@link AggregateDefinition} declared by the specified contributor; 
     *          or an empty array if none
     * @throws IllegalArgumentException if specified argument is invalid or null
     * @throws OdaException
     */
    public AggregateDefinition[] getContributedAggregateDefinitions( ExtensionContributor extensionContributor )
        throws IllegalArgumentException, OdaException
    {
        Map<String,AggregateDefinition> aggrExprDefns = getAggregateDefinitionsByContributor( extensionContributor );
        return convertAggregateDefnValuesToSortByNameArray( aggrExprDefns );
    }

    /**
     * Gets the definition of the specified custom aggregate declared by the
     * specified expression contributor.
     * @param extensionContributor  contributor of a dynamicResultSet extension
     * @param exprId    id of an aggregate type
     * @return  an instance of {@link AggregateDefinition}, or null if no matching definition is found
     * @throws IllegalArgumentException if any specified argument is invalid or null
     * @throws OdaException
     */
    public AggregateDefinition getContributedAggregateDefinition( ExtensionContributor extensionContributor, String exprId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( exprId );

        Map<String,AggregateDefinition> aggrExprDefns = getAggregateDefinitionsByContributor( extensionContributor );
        return ( aggrExprDefns == null ) ? null : aggrExprDefns.get( exprId );
    }
    
    /**
     * Gets the collection of custom aggregate definitions declared by the specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @return  an array of {@link AggregateDefinition} defined by the specified extension; 
     *          or an empty array if none
     * @throws IllegalArgumentException if specified argument is invalid or null
     * @throws OdaException
     */
    public AggregateDefinition[] getExtensionAggregateDefinitions( String extensionId ) 
        throws IllegalArgumentException, OdaException
    {
        Map<String,AggregateDefinition> aggrExprDefns = getAggregateDefinitionsByExtension( extensionId );
        return convertAggregateDefnValuesToSortByNameArray( aggrExprDefns );
    }
    
    /**
     * Gets the definition of the specified custom aggregate type declared by the
     * specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param exprId    id of an aggregate type
     * @return  an instance of {@link AggregateDefinition}, or null if no matching definition is found
     * @throws IllegalArgumentException if any specified argument is null or empty
     * @throws OdaException
     */
    public AggregateDefinition getExtensionAggregateDefinition( String extensionId, String exprId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( exprId );
        
        Map<String,AggregateDefinition> aggrExprDefns = getAggregateDefinitionsByExtension( extensionId );
        
        // get the definition of the specified expression 
        return ( aggrExprDefns == null ) ? null : aggrExprDefns.get( exprId );
    }
    
    private Map<String,AggregateDefinition> getAggregateDefinitionsByExtension( String extensionId )
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( extensionId );        
        
        // since all extensions should already be loaded in instance,
        // just check if specified extension is already in cache, and use it
        Map<String,AggregateDefinition> exprDefns = getCachedAggregateDefinitionsByExtension( extensionId );
        return exprDefns;
    }
    
    private Map<String,AggregateDefinition> getAggregateDefinitionsByContributor( ExtensionContributor extensionContributor )
        throws OdaException
    {
        validateArgumentExists( extensionContributor );  
        
        // since all extensions should already be loaded in instance,
        // just check if specified extension is already in cache, and use it
        Map<String,AggregateDefinition> aggregateDefns = 
                        getCachedAggregateExtensions().get( extensionContributor );
        return aggregateDefns;
    }
    
    /**
     * Gets the collection of supported and custom value expression combined operator definitions 
     * declared by the specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @return  an array of {@link CombinedExpressionOperatorDefinition} defined by the specified extension; 
     *          or an empty array if none
     * @throws IllegalArgumentException if specified argument is invalid or null
     * @throws OdaException
     */
    public CombinedExpressionOperatorDefinition[] getExtensionCombinedOperatorDefinitions( String extensionId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( extensionId );        

        // since all extensions should already be loaded in instance,
        // just check if specified extension is already in cache, and use it
        Map<String,CombinedExpressionOperatorDefinition> combinedOpDefns = 
            getCachedCombinedOpDefinitionsByExtension( extensionId );
        return convertCombinedOpDefnValuesToArray( combinedOpDefns );
    }
    
    /**
     * Gets the definition of the specified supported or custom value expression combined operator 
     * declared by the specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param operatorId    the id of a value expression combined operator type; 
     *              may be an ODA built-in operator or a custom type contributed by the extension
     * @return  an instance of {@link CombinedExpressionOperatorDefinition}, or null if no matching definition is found
     * @throws IllegalArgumentException if any specified argument is null or empty
     * @throws OdaException
     */
    public CombinedExpressionOperatorDefinition getExtensionCombinedOperatorDefinition( String extensionId, String operatorId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( extensionId );        
        validateArgumentExists( operatorId );
        
        Map<String,CombinedExpressionOperatorDefinition> combinedOpDefns = 
            getCachedCombinedOpDefinitionsByExtension( extensionId );
        
        // get the definition of the specified operator 
        return ( combinedOpDefns == null ) ? null : combinedOpDefns.get( operatorId );
    }
    
    /**
     * Indicates whether the specified extension supports the specified built-in combined operator type.
     * @param extensionId      unique id of an extension that implements the dynamicResultSet extension point
     * @param builtInOperatorId the id of a built-in value expression combined operator type; 
     *          the constants are defined in 
     *          {@link org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpressionOperator}
     * @return  true if the specified built-in combined operator type is supported by the specified extension;
     *          false otherwise
     * @see {@link org.eclipse.datatools.connectivity.oda.spec.util.ExpressionFactory#getCombinedOperator(String, String)}
     */
    boolean supportsOdaCombinedOperator( String extensionId, String builtInOperatorId )
    {
        CombinedExpressionOperatorDefinition opDefn = null;
        try
        {
            opDefn = getExtensionCombinedOperatorDefinition( extensionId, builtInOperatorId );
        }
        catch( IllegalArgumentException ex )
        {
            // ignore
            return false;
        }
        catch( OdaException ex )
        {
            // ignore
            return false;
        }
        
        return opDefn != null && opDefn.isBuiltInOperator();
    }
    
    boolean supportsCombinedValueExpressionType( String extensionId )
    {
        // if extension has at least 1 supported combined operator, 
        // it supports the complex expression type
        Map<String,CombinedExpressionOperatorDefinition> combinedOpDefns =
                getCachedCombinedOpDefinitionsByExtension( extensionId );
        return combinedOpDefns != null && ! combinedOpDefns.isEmpty();
    }
   
    /**
     * Gets the collection of supported and custom value expression function definitions 
     * declared by the specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @return  an array of {@link FunctionExpressionDefinition} defined by the specified extension; 
     *          or an empty array if none
     * @throws IllegalArgumentException if specified argument is invalid or null
     * @throws OdaException
     */
    public FunctionExpressionDefinition[] getExtensionFunctionDefinitions( String extensionId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( extensionId );        

        // since all extensions should already be loaded in instance,
        // just check if specified extension is already in cache, and use it
        Map<String,FunctionExpressionDefinition> functionDefns = getCachedFunctionDefinitionsByExtension( extensionId );
        return convertFunctionDefnValuesToSortByNameArray( functionDefns );
    }
    
    /**
     * Gets the definition of the specified supported or custom value expression function
     * declared by the specified extension.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param functionId    the id of a value expression function type contributed by the extension
     * @return  an instance of {@link FunctionExpressionDefinition}, or null if no matching definition is found
     * @throws IllegalArgumentException if any specified argument is null or empty
     * @throws OdaException
     */
    public FunctionExpressionDefinition getExtensionFunctionDefinition( String extensionId, String functionId ) 
        throws IllegalArgumentException, OdaException
    {
        validateArgumentExists( extensionId );        
        validateArgumentExists( functionId );
        
        Map<String,FunctionExpressionDefinition> functionDefns = getCachedFunctionDefinitionsByExtension( extensionId );
        
        // get the definition of the specified function 
        return ( functionDefns == null ) ? null : functionDefns.get( functionId );
    }
    
    boolean supportsFunctionValueExpressionType( String extensionId )
    {
        // if extension has at least 1 supported custom function, 
        // it supports the function expression type
        Map<String,FunctionExpressionDefinition> functionDefns =
            getCachedFunctionDefinitionsByExtension( extensionId );
        return functionDefns != null && ! functionDefns.isEmpty();
    }

    private static void validateArgumentExists( String arg ) throws IllegalArgumentException
    {
        ResultExtensionUtil.validateArgumentExists( arg );        
    }
    
    private static void validateArgumentExists( ExtensionContributor contributor ) throws IllegalArgumentException
    {
        ResultExtensionUtil.validateArgumentExists( contributor ) ;
    }

}
