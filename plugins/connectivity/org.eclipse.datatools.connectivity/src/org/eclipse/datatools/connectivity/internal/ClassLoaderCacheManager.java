/*
 *************************************************************************
 * Copyright (c) 2013 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.internal;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 *  An utility class that manages caching of class loaders for JDBC drivers.
 *  @since 1.2.8 (DTP 1.11)
 */
public class ClassLoaderCacheManager
{    
    private static ClassLoaderCacheManager sm_instance = null;
    private boolean m_isActive = false;
    
    private Map<ClassPathKey,SoftReference<ClassLoader>> m_classLoaders = null;

    public static ClassLoaderCacheManager getInstance()
    {
        if( sm_instance == null )
        {
            synchronized( ClassLoaderCacheManager.class )
            {
                if( sm_instance == null )
                    sm_instance = new ClassLoaderCacheManager();
            }
        }
        return sm_instance;
    }

    /**
     * Singleton instance release method.
     */
    public static void releaseInstance()
    {
        if( sm_instance == null )
            return;
        
        synchronized( ClassLoaderCacheManager.class )
        {
            if( sm_instance != null )
            {
                sm_instance.reset();
                sm_instance = null;
            }
        }
    }
   
    private ClassLoaderCacheManager(){}
    
    private void reset()
    {
        m_classLoaders = null;
    }    

    private Map<ClassPathKey,SoftReference<ClassLoader>> getClassLoaders()
    {
        if( m_classLoaders == null )
        {
            synchronized( this )
            {
                if( m_classLoaders == null )
                    m_classLoaders = Collections.synchronizedMap( 
                            new TreeMap<ClassPathKey, SoftReference<ClassLoader>>() );
            }
        }
        return m_classLoaders;
    }
    
    public ClassLoader getDriverClassLoader( URL[] classLoaderURLs )
    {
        if( classLoaderURLs == null || classLoaderURLs.length == 0 )
            return null;
        return getDriverClassLoader( new ClassPathKey(classLoaderURLs) );
    }
    
    private ClassLoader getDriverClassLoader( ClassPathKey classPaths )
    {
        if( classPaths == null || classPaths.getURLCount() == 0 )
            return null;
        SoftReference<ClassLoader> classLdrRef = getClassLoaders().get( classPaths );
        return classLdrRef != null ? classLdrRef.get() : null;
    }
    
    public void addURLClassLoader( URLClassLoader driverClassLoader )
    {
        if( driverClassLoader == null )
            return;     // nothing to add
        
        URL[] classLoaderURLs = driverClassLoader.getURLs();
        if( classLoaderURLs == null || classLoaderURLs.length == 0 )
            return;     // no class path to cache by
        
        ClassLoader cachedClassLoader = getDriverClassLoader( classLoaderURLs );
        if( cachedClassLoader != null && driverClassLoader.equals( cachedClassLoader ) )
            return;     // same class loader is already cached
        
        getClassLoaders().put( new ClassPathKey( classLoaderURLs ), 
                new SoftReference<ClassLoader>( driverClassLoader ));
    }
    
    public void setIsActive( boolean isActive )
    {
        m_isActive = isActive;
    }
    
    public boolean isActive()
    {
        return m_isActive;
    }

    /* 
     * A private class that wraps an array of class path URLs for use as a key in a TreeMap.
     */
    private class ClassPathKey implements Comparable<ClassPathKey>
    {
        private URL[] m_classPathURLs;
        
        ClassPathKey( URL[] classPathURLs )
        {
            m_classPathURLs = classPathURLs == null ? new URL[0] : classPathURLs;
        }

        private URL[] getclassPathURLs()
        {
            return m_classPathURLs;
        }
        
        private int getURLCount()
        {
            return m_classPathURLs.length;
        }
        
        public int compareTo( ClassPathKey obj )
        {
            if( this == obj )
                return 0;
            if( this.getURLCount() < obj.getURLCount() )
                return -1;
            if( this.getURLCount() > obj.getURLCount() )
                return 1;
            
            // has same number of URLs; check if all their URLs match
            if( this.hasMatchingURLs( obj ) )
                return 0;
            
            int thisHashCode = this.hashCode();
            int objHashCode = obj.hashCode();
            return thisHashCode < objHashCode ? -1 : 1;
        }

        private boolean hasMatchingURLs( ClassPathKey obj )
        {
            if( this.getURLCount() != obj.getURLCount() )
                return false;
            
            URL[] thisURLs = this.getclassPathURLs();
            URL[] objURLs = obj.getclassPathURLs();
            for( int i=0; i < thisURLs.length; ++i )
            {
                URL thisURL = thisURLs[i];
                boolean hasMatchingURL = false;
                for( int j=0; j < objURLs.length; ++j )
                {
                    URL objURL = objURLs[j];
                    if( thisURL.equals( objURL ) )
                    {
                        hasMatchingURL = true;
                        break;
                    }
                }
                
                if( ! hasMatchingURL ) // no matching URL 
                    return false;  
            }
            
            // all URLs have a corresponding match
            return true;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals( Object obj )
        {
            if( ! (obj instanceof ClassPathKey ))
                return false;
    
            return this.compareTo( (ClassPathKey)obj ) == 0;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode()
        {
            int hash = 0;
            URL[] thisURLs = getclassPathURLs();
            for( int i=0; i < thisURLs.length; ++i )
            {
                hash += thisURLs[i].hashCode();
            }
            return hash;
        }       
    }
    
}
