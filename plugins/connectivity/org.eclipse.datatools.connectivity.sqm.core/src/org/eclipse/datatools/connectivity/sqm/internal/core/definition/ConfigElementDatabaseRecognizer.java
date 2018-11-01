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

package org.eclipse.datatools.connectivity.sqm.internal.core.definition;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.SQMServices;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.IDatabaseRecognizer;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.GenericCatalogMessages;

/**
 * A DatabaseRecognizer that handles the dbDefinitionMapping configuration elements declared
 * in an extension of the <code>org.eclipse.datatools.connectivity.sqm.core.databaseRecognition</code> 
 * extension point.
 */
public class ConfigElementDatabaseRecognizer implements IDatabaseRecognizer, IExecutableExtension
{
    public static final String DB_DEFN_MAPPING = "dbDefinitionMapping"; //$NON-NLS-1$
    
    private List<DbDefinitionMapping> m_dbDefnMappings;
    
    public ConfigElementDatabaseRecognizer( IExtension dbRecognitionExtension ) throws CoreException
    {
        init( dbRecognitionExtension );
    }
    
    public ConfigElementDatabaseRecognizer() {}
    
    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    public void setInitializationData( IConfigurationElement config,
            String propertyName, Object data ) throws CoreException
    {
        init( config.getDeclaringExtension() );
    }

    private void init( IExtension dbRecognitionExtension ) throws CoreException
    {
        IConfigurationElement[] configElements = dbRecognitionExtension.getConfigurationElements();
        if( configElements.length == 0 )
            return;     // nothing to initialize
        
        m_dbDefnMappings = new ArrayList<DbDefinitionMapping>( configElements.length );        
        for( int i=0; i < configElements.length; i++ )
        {
            if( ! configElements[i].getName().equals( DB_DEFN_MAPPING ) )
                continue;

            IConfigurationElement dbDefnMapElement = configElements[i];
            m_dbDefnMappings.add( new DbDefinitionMapping( dbDefnMapElement ));
        }
    }
    
    public boolean hasJdbcMappings()
    {
        return ( m_dbDefnMappings != null && ! m_dbDefnMappings.isEmpty() );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.sqm.core.definition.IDatabaseRecognizer#recognize(java.sql.Connection)
     */
    public DatabaseDefinition recognize( Connection connection )
    {
        if( connection == null || ! hasJdbcMappings() )
            return null;

        String jdbcProduct = null;
        String jdbcVersion = null;
        try
        {
            jdbcProduct = connection.getMetaData().getDatabaseProductName();
            jdbcVersion = connection.getMetaData().getDatabaseProductVersion();
        }
        catch( SQLException ex )
        {
            return null;
        }
        if( jdbcVersion == null )
            return null;    // required metadata is not available, cannot recognize db
        
        // find mapping of dbdefinition product and version
        Iterator<DbDefinitionMapping> iter = m_dbDefnMappings.iterator();
        while( iter.hasNext() )
        {
            DbDefinitionMapping dbDefnMap = iter.next();
            if( dbDefnMap.matchesIgnoreCase( jdbcProduct, jdbcVersion ) || 
                dbDefnMap.matchesPattern( jdbcProduct, jdbcVersion ) )
            {
                return SQMServices.getDatabaseDefinitionRegistry().getDefinition( 
                            dbDefnMap.m_dbProduct, dbDefnMap.m_dbVersion );
            }
        }
        return null;
    }

    /**
     * Internal class to represent the dbDefinitionMapping configuration element 
     * and provide JDBC product name and version matching services.
     */
    private class DbDefinitionMapping
    {
        private static final String JDBC_PRODUCT_NAME=  "jdbcDatabaseProductName"; //$NON-NLS-1$
        private static final String JDBC_PRODUCT_VERSION = "jdbcDatabaseProductVersion"; //$NON-NLS-1$
        private static final String DB_DEFINITION_PRODUCT = "dbdefinitionProduct"; //$NON-NLS-1$
        private static final String DB_DEFINITION_VERSION = "dbdefinitionVersion"; //$NON-NLS-1$
        
        private String m_jdbcProductName;
        private String m_jdbcProductVersion;
        private String m_dbProduct;
        private String m_dbVersion;
        
        private DbDefinitionMapping( IConfigurationElement dbDefnMapElement ) throws CoreException
        {
            m_jdbcProductName = dbDefnMapElement.getAttribute( JDBC_PRODUCT_NAME ); // optional attribute
            m_jdbcProductVersion = dbDefnMapElement.getAttribute( JDBC_PRODUCT_VERSION );
            m_dbProduct = dbDefnMapElement.getAttribute( DB_DEFINITION_PRODUCT );
            m_dbVersion = dbDefnMapElement.getAttribute( DB_DEFINITION_VERSION );
            
            if( m_jdbcProductVersion == null || m_dbProduct == null || m_dbVersion == null )
                throw new CoreException( new Status( IStatus.ERROR, dbDefnMapElement.getNamespaceIdentifier(),
                        GenericCatalogMessages.bind( GenericCatalogMessages.DEFN_MISSING_ATTRIBUTE_VALUES, DB_DEFN_MAPPING)) );
        }
        
        boolean matchesIgnoreCase( String jdbcDbProductName, String jdbcDbProductVersion )
        {
            if( ! m_jdbcProductVersion.equalsIgnoreCase( jdbcDbProductVersion ) )
                return false;
            if( m_jdbcProductName == null )
                return true;    // matches any product name
            return m_jdbcProductName.equalsIgnoreCase( jdbcDbProductName );
        }

        boolean matchesPattern( String jdbcDbProductName, String jdbcDbProductVersion )
        {
            try
            {
                Pattern p = Pattern.compile( m_jdbcProductVersion );
                Matcher m = p.matcher( jdbcDbProductVersion );
                if( ! m.matches() )
                    return false;
                
                if( m_jdbcProductName == null )
                    return true;    // matches any pattern
                
                p = Pattern.compile( m_jdbcProductName );
                m = p.matcher( jdbcDbProductName );
                return m.matches();
            }
            catch( Exception ex )
            {
            }

            return false;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals( Object obj )
        {
            if( ! (obj instanceof DbDefinitionMapping) )
                return false;
            
            DbDefinitionMapping thatObj = (DbDefinitionMapping) obj;
            if( ! matchesIgnoreCase( thatObj.m_jdbcProductName, thatObj.m_jdbcProductVersion ) )
                return false;
            if( ! this.m_dbProduct.equalsIgnoreCase( thatObj.m_dbProduct ) )
                return false;
            return this.m_dbVersion.equalsIgnoreCase( thatObj.m_dbVersion );
        }                
    }
    
}
