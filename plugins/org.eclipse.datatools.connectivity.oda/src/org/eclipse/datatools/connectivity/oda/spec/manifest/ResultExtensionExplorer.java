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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestUtil;


/**
 * <p>
 * <strong>EXPERIMENTAL</strong>. This class has been added as
 * part of a work in progress. There is no guarantee that this API will
 * work or that it will remain the same. Please do not use this API without
 * consulting with the DTP Open Data Access team.
 * </p>
 * This singleton explorer is the entry point to explore and access
 * the manifest of all the extensions that implement the 
 * <code>org.eclipse.datatools.connectivity.oda.dynamicResultSet</code> extension point.
 * The <code>ResultExtensionExplorer</code> singleton instance is retrieved 
 * using the <code>getInstance()</code> method.
 * @since 3.2 (DTP 1.7)
 */
public class ResultExtensionExplorer
{
    public static final String DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT = 
        "org.eclipse.datatools.connectivity.oda.dynamicResultSet";  //$NON-NLS-1$
    public static final String FILTER_GROUP_NAME = "filterExpressionTypes"; //$NON-NLS-1$
    public static final String AGGREGATE_GROUP_NAME = "aggregateExpressionTypes"; //$NON-NLS-1$

    private static ResultExtensionExplorer sm_instance = null;
    
    // trace logging variables
    private static Logger sm_logger = null;

    private static final ExtensionContributor[] EMTPY_CONTRIBUTOR_ARRAY = new ExtensionContributor[0];

    private Map<ExtensionContributor,Map<String,FilterExpressionDefinition>> 
        m_filterExpressionsByExtn;  // cached copy of filter expression map by extension contributor
    private Map<ExtensionContributor,Map<String,AggregateDefinition>> 
        m_aggregateTypesByExtn;  // cached copy of aggregate type map by extension contributor
    
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

    private ResultExtensionExplorer() {}

    /**
     * Refresh the explorer, and allows it to get the latest extension manifests.
     */
    public void refresh()
    {
        if( m_filterExpressionsByExtn != null )
            m_filterExpressionsByExtn.clear();
        if( m_aggregateTypesByExtn != null )
            m_aggregateTypesByExtn.clear();
    }
    
    private Map<ExtensionContributor,Map<String,FilterExpressionDefinition>> getCachedFilterExtensions()
    {
        if( m_filterExpressionsByExtn == null )
        {
            synchronized( this )
            {
                if( m_filterExpressionsByExtn == null )
                {
                    m_filterExpressionsByExtn = Collections.synchronizedMap( 
                                                    new HashMap<ExtensionContributor,Map<String,FilterExpressionDefinition>>() );
                }
            }
        }
        return m_filterExpressionsByExtn;
    }
    
    /**
     * 
     * @param extensionId
     * @return  may be null if specified extensionId is not found in cache
     */
    private Map<String,FilterExpressionDefinition> getCachedFilterDefinitionsByExtension( String extensionId )
    {
        if( getCachedFilterExtensions().isEmpty() )
            return null;
        
        synchronized( getCachedFilterExtensions() )
        {
            Iterator<Entry<ExtensionContributor, Map<String,FilterExpressionDefinition>>> iter = 
                getCachedFilterExtensions().entrySet().iterator();
            while( iter.hasNext() )
            {
                Entry<ExtensionContributor, Map<String,FilterExpressionDefinition>> entry = iter.next();
                if( entry.getKey().getDeclaringExtensionId().equals( extensionId ) )
                    return entry.getValue();
            }
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
                    m_aggregateTypesByExtn = Collections.synchronizedMap( 
                                                    new HashMap<ExtensionContributor,Map<String,AggregateDefinition>>() );
                }
            }
        }
        return m_aggregateTypesByExtn;
    }
    
    private Map<String,AggregateDefinition> getCachedAggregateDefinitionsByExtension( String extensionId )
    {
        if( getCachedAggregateExtensions().isEmpty() )
            return null;
        
        synchronized( getCachedAggregateExtensions() )
        {
            Iterator<Entry<ExtensionContributor, Map<String,AggregateDefinition>>> iter = 
                getCachedAggregateExtensions().entrySet().iterator();
            while( iter.hasNext() )
            {
                Entry<ExtensionContributor, Map<String,AggregateDefinition>> entry = iter.next();
                if( entry.getKey().getDeclaringExtensionId().equals( extensionId ) )
                    return entry.getValue();
            }
        }
        return null;
    }
    
    private Map<String,AggregateDefinition> getContributedAggregateDefinitionMap( ExtensionContributor extensionContributor )
        throws OdaException
    {
        Map<String,AggregateDefinition> aggregateDefns = 
            getCachedAggregateExtensions().get( extensionContributor );
        if( aggregateDefns == null )
        {
            // look up extension from registry, create definitions and add to cache             
            ExtensionContributor contributor = addExtension( extensionContributor.getDeclaringExtensionId() );
            aggregateDefns = getContributedAggregateDefinitionMap( contributor );

        }
        return aggregateDefns;
    }

    private FilterExpressionDefinition[] convertFilterDefnValuesToSortByNameArray( Map<String,FilterExpressionDefinition> exprDefns )
    {   
        // sort given expression definitions by their display names
        TreeMap<String,FilterExpressionDefinition> sortedDefnsByName = new TreeMap<String,FilterExpressionDefinition>();
        Iterator<FilterExpressionDefinition> iter = exprDefns.values().iterator();
        while( iter.hasNext() )
        {
            FilterExpressionDefinition exprDefn = iter.next();
            sortedDefnsByName.put( exprDefn.getDisplayName(), exprDefn );
        }
        
        return sortedDefnsByName.values().toArray( new FilterExpressionDefinition[ sortedDefnsByName.size() ] );
    }

    private ExtensionContributor[] getCachedContributorsByDataSet( String odaDataSourceId, String odaDataSetId )
    {
        if( getCachedFilterExtensions().isEmpty() )
            return null;
        
        List<ExtensionContributor> contributors = new ArrayList<ExtensionContributor>( getCachedFilterExtensions().size() );
        synchronized( getCachedFilterExtensions() )
        {
            Iterator<ExtensionContributor> iter = getCachedFilterExtensions().keySet().iterator();               
            while( iter.hasNext() )
            {
                ExtensionContributor aContributor = iter.next();
                if( aContributor.supportsDataSetType( odaDataSourceId, odaDataSetId ) )
                {
                    contributors.add( aContributor );
                }
            }
        }
        return contributors.isEmpty() ? null : 
                contributors.toArray( new ExtensionContributor[ contributors.size() ] );
    }
    
    /**
     * Gets the collection of contributors that contributes dynamicResultSet extension to the specified data set type
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
        if ( odaDataSourceId == null || odaDataSourceId.length() == 0 )
            throw new IllegalArgumentException( odaDataSourceId );        
        if ( odaDataSetId == null || odaDataSetId.length() == 0 )
            throw new IllegalArgumentException( odaDataSetId );        
        
        // first check if specified data set type is already in cache, and use it
        ExtensionContributor[] contributors = getCachedContributorsByDataSet( odaDataSourceId, odaDataSetId );
        if( contributors == null )
        {
            // look up all extensions from registry, create definitions and add to cache             
            addAllExtensions();
            contributors = getCachedContributorsByDataSet( odaDataSourceId, odaDataSetId );
        }
        return ( contributors == null ) ? EMTPY_CONTRIBUTOR_ARRAY : contributors;
    }
    
    private Map<String,FilterExpressionDefinition> getContributedFilterDefinitionMap( ExtensionContributor extensionContributor )
        throws OdaException
    {
        Map<String,FilterExpressionDefinition> filterExprDefns = 
                        getCachedFilterExtensions().get( extensionContributor );
        if( filterExprDefns == null )
        {
            // look up extension from registry, create definitions and add to cache             
            ExtensionContributor contributor = addExtension( extensionContributor.getDeclaringExtensionId() );
            filterExprDefns = getContributedFilterDefinitionMap( contributor );

        }
        return filterExprDefns;
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
        if( extensionContributor == null )
            throw new IllegalArgumentException( new NullPointerException() );  
        
        Map<String,FilterExpressionDefinition> exprDefns = getContributedFilterDefinitionMap( extensionContributor );
        return convertFilterDefnValuesToSortByNameArray( exprDefns );
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
        if( extensionId == null || extensionId.length() == 0 )
            throw new IllegalArgumentException( extensionId );        
        
        // first check if specified extension is already in cache, and use it
        Map<String,FilterExpressionDefinition> filterExprDefns = getCachedFilterDefinitionsByExtension( extensionId );
        if( filterExprDefns == null )
        {
            // look up extension from registry, create definitions and add to cache             
            ExtensionContributor contributor = addExtension( extensionId );
            filterExprDefns = getContributedFilterDefinitionMap( contributor );
        }

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
        if ( extensionContributor == null )
            throw new IllegalArgumentException( new NullPointerException() );  
        if ( exprId == null || exprId.length() == 0 )
            throw new IllegalArgumentException( exprId );

        Map<String,FilterExpressionDefinition> exprDefns = getContributedFilterDefinitionMap( extensionContributor );
        return ( exprDefns == null ) ? null : exprDefns.get( exprId );
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
        if ( extensionId == null || extensionId.length() == 0 )
            throw new IllegalArgumentException( extensionId );        
        if ( exprId == null || exprId.length() == 0 )
            throw new IllegalArgumentException( exprId );
        
        // first check if specified extension is already in cache, and use it
        Map<String,FilterExpressionDefinition> filterExprDefns = getCachedFilterDefinitionsByExtension( extensionId );
        if( filterExprDefns == null )
        {
            // look up extension from registry, create definitions and add to cache             
            ExtensionContributor contributor = addExtension( extensionId );
            filterExprDefns = getContributedFilterDefinitionMap( contributor );
        }
        
        // get the definition of the specified expression 
        return ( filterExprDefns == null ) ? null : filterExprDefns.get( exprId );
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
        FilterExpressionDefinition[] defns = getExtensionFilterDefinitions( extensionId );
        if( defns.length == 0 )
            return null;
        
        return defns[0].getContributor();
    }
    
    /* 
     * Look up all extensions from registry, and add each corresponding definitions to cache 
     * if not already in cache.
     */
    private void addAllExtensions() throws OdaException
    {
        IExtension[] extensions = ManifestExplorer.getExtensions( DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT );
        for( int i=0; i < extensions.length; i++ )
        {
            addExtension( extensions[i], false );
        }
    }
    
    private ExtensionContributor addExtension( String extensionId )
        throws OdaException
    {
        // look up extension from registry
        IExtension[] extensions = ManifestExplorer.getExtensions( DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT );        
        IExtension dynamicResultExtn = findExtension( extensionId, extensions );       
        if ( dynamicResultExtn == null )
            throw new OdaException( Messages.bind( "No {0} extension found with the id ({1}).", 
                    new Object[]{ ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                                    extensionId } ));
               
        // found extension, create definitions and add to cache, replacing existing ones if any             
        ExtensionContributor contributor = addExtension( dynamicResultExtn, true );
        return contributor;
    }
    
    private static IExtension findExtension( String extensionId, IExtension[] extensions )
        throws OdaException
    {
        int length = ( extensions == null ) ? 0 : extensions.length;
    
        for( int i = 0; i < length; i++ )
        {
            IExtension extension = extensions[i];
            if( extensionId.equals( extension.getUniqueIdentifier() ))
                return extension;
        }
        return null;
    }
    
    /**
     * 
     * @param dynamicResultExtn
     * @param ifNotExists   indicates to add only if the specified extension is not already in cache
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
            throw new OdaException( Messages.bind( "The {0} extension ({1}) is missing the {2} element.", 
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
        
        return contributor;
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
        if( extensionContributor == null )
            throw new IllegalArgumentException( new NullPointerException() );  
        
        Map<String,AggregateDefinition> exprDefns = getContributedAggregateDefinitionMap( extensionContributor );
        return convertAggregateDefnValuesToSortByNameArray( exprDefns );
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
        if( extensionId == null || extensionId.length() == 0 )
            throw new IllegalArgumentException( extensionId );        
        
        // first check if specified extension is already in cache, and use it
        Map<String,AggregateDefinition> exprDefns = getCachedAggregateDefinitionsByExtension( extensionId );
        if( exprDefns == null )
        {
            // look up extension from registry, create definitions and add to cache             
            ExtensionContributor contributor = addExtension( extensionId );
            exprDefns = getContributedAggregateDefinitionMap( contributor );
        }

        return convertAggregateDefnValuesToSortByNameArray( exprDefns );
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
        if ( extensionContributor == null )
            throw new IllegalArgumentException( new NullPointerException() );  
        if ( exprId == null || exprId.length() == 0 )
            throw new IllegalArgumentException( exprId );

        Map<String,AggregateDefinition> exprDefns = getContributedAggregateDefinitionMap( extensionContributor );
        return ( exprDefns == null ) ? null : exprDefns.get( exprId );
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
        if ( extensionId == null || extensionId.length() == 0 )
            throw new IllegalArgumentException( extensionId );        
        if ( exprId == null || exprId.length() == 0 )
            throw new IllegalArgumentException( exprId );
        
        // first check if specified extension is already in cache, and use it
        Map<String,AggregateDefinition> exprDefns = getCachedAggregateDefinitionsByExtension( extensionId );
        if( exprDefns == null )
        {
            // look up extension from registry, create definitions and add to cache             
            ExtensionContributor contributor = addExtension( extensionId );
            exprDefns = getContributedAggregateDefinitionMap( contributor );
        }
        
        // get the definition of the specified expression 
        return ( exprDefns == null ) ? null : exprDefns.get( exprId );
    }

    private AggregateDefinition[] convertAggregateDefnValuesToSortByNameArray( Map<String,AggregateDefinition> exprDefns )
    {   
        // sort given expression definitions by their display names
        TreeMap<String,AggregateDefinition> sortedDefnsByName = new TreeMap<String,AggregateDefinition>();
        Iterator<AggregateDefinition> iter = exprDefns.values().iterator();
        while( iter.hasNext() )
        {
            AggregateDefinition exprDefn = iter.next();
            sortedDefnsByName.put( exprDefn.getDisplayName(), exprDefn );
        }

        return sortedDefnsByName.values().toArray( new AggregateDefinition[ sortedDefnsByName.size() ] );
    }

}
