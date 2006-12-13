/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * Utility class for the ODA extension manifest.
 */
public class ManifestUtil
{
    
    /**
     * Returns the configuration element of the given extension
     * and element name.
     * <br>For internal use only.
     */
    public static IConfigurationElement getNamedElement( IExtension extension,
            String elementName ) 
        throws OdaException
    {
        IConfigurationElement[] configElements =
                        getNamedElements( extension, elementName );
        if( configElements.length == 0 )
            throw new OdaException( Messages.manifest_NO_DRIVER_RUNTIME_CONFIGURATION_DEFINED );

        return configElements[0];   // returns the first matching element
    }
    
    /**
     * Returns a collection of configuration elements with the given name
     * in the given extension.  
     * Validates that each element has an id attribute defined.
     * @return a collection of matching configuration elements
     * <br>For internal use only.
     */
    public static IConfigurationElement[] getNamedElements( 
                                            IExtension extension,
                                            String elementName ) 
        throws OdaException
    {
        return getNamedElements( extension, elementName, "id" );    //$NON-NLS-1$
    }
    
    /**
     * Returns a collection of configuration elements with the given name
     * in the given extension.  
     * Validates that each element has the specified attribute defined.
     * @return a collection of matching configuration elements
     * <br>For internal use only.
     */
    public static IConfigurationElement[] getNamedElements( 
                                            IExtension extension,
                                            String elementName, 
                                            String requiredAttributeName ) 
        throws OdaException
    {
        IConfigurationElement[] configElements = extension.getConfigurationElements();
        ArrayList matchedElements = new ArrayList();
        for( int i = 0, n = configElements.length; i < n; i++ )
        {
            IConfigurationElement configElement = configElements[i];
            if( ! configElement.getName().equalsIgnoreCase( elementName ) )
                continue;

            // validate that the element has the required attribute with non-empty value
            String attrValue = configElement.getAttribute( requiredAttributeName );
            if( attrValue == null || attrValue.length() == 0 )
                throw new OdaException( 
                        Messages.bind( Messages.manifest_NO_ATTRIBUTE_ID_DEFINED, 
                                        requiredAttributeName, elementName ));

            matchedElements.add( configElement );
        }
        
        return (IConfigurationElement[]) matchedElements.toArray( 
                    new IConfigurationElement[ matchedElements.size() ] );
    }

    /**
     * Returns a collection of property definition from the 
     * specified driverDefinedProps that are defined to be visible.
     * <br>For internal use only.
     */
    public static Property[] getVisiblePropertiesDefn( 
            Property[] driverDefinedProps, Properties propertiesVisibility )
    {
        if( driverDefinedProps.length == 0 )
            return driverDefinedProps;
        
        ArrayList visibleProps = new ArrayList();
        for( int i = 0, size = driverDefinedProps.length; i < size; i++ )
        {
            Property aProp = driverDefinedProps[i];
            if( aProp.isVisible( propertiesVisibility ) )
                visibleProps.add( aProp );
        }
        
        return (Property[]) visibleProps.toArray( new Property[ visibleProps.size() ] );        
    }

    /**
     * Returns a collection of property definition from the 
     * specified driverDefinedProps that are defined to be hidden.
     * <br>For internal use only.
     */
    public static Property[] getHiddenPropertiesDefn( 
            Property[] driverDefinedProps, Properties propertiesVisibility )
    {
        if( driverDefinedProps.length == 0 )
            return driverDefinedProps;
        
        ArrayList hiddenProps = new ArrayList();
        for( int i = 0, size = driverDefinedProps.length; i < size; i++ )
        {
            Property aProp = driverDefinedProps[i];
            if( ! aProp.isVisible( propertiesVisibility ) )
                hiddenProps.add( aProp );
        }
        
        return (Property[]) hiddenProps.toArray( new Property[ hiddenProps.size() ] );        
    }

}
