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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * Internal utility class for processing the ODA dynamicResultSet extension manifest.
 */
class ResultExtensionUtil
{
    static final String ATTR_UNBOUNDED_MAX_ARGS = "*"; //$NON-NLS-1$
    
    static String getRequiredAttributeValue( IConfigurationElement element, 
            String attributeName, String elementName ) throws OdaException
    {
        String value = element.getAttribute( attributeName );
        if( value == null || value.length() == 0 )
            throw new OdaException( Messages.bind( Messages.querySpec_MISSING_EXT_POINT_ATTR_VALUE, 
                    new Object[] { ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                                    element.getContributor().getName(), attributeName, elementName} ));
        return value;
    }
    
    static Integer getMinAttributeValue( IConfigurationElement element, String attributeName, 
            Integer defaultValue )    
        throws OdaException
    {
        String attrValueText = element.getAttribute( attributeName );
        if( attrValueText == null )     // no value is specified, uses default value
            return defaultValue;
        
        // a value is specified; convert value to Integer value
        Integer minValue = null;        
        try
        {
            minValue = Integer.valueOf( attrValueText );
        }
        catch( NumberFormatException ex )
        {
        }
        if( minValue == null || minValue.intValue() < 0 )
            throw newInvalidAttributeValueException( element.getContributor().getName(), 
                    attrValueText, attributeName );

        return minValue;
    }
    
    /**
     * Returns the value of the specified attribute; 
     * may be a null value to represent unlimited maximum number of arguments
     */
    static Integer getMaxAttributeValue( IConfigurationElement element, String attributeName, 
                                        Integer defaultValue, Integer minValue )
        throws OdaException
    {
        String attrValueText = element.getAttribute( attributeName );
        if( attrValueText == null )     // no value is specified, uses default value
            return defaultValue;
        
        // a value is specified
        
        // if unbounded, returns NULL to represent unlimited maximum number of arguments
        if( attrValueText.equals( ATTR_UNBOUNDED_MAX_ARGS ) )
            return null;

        // convert value to Integer value
        Integer maxValue = null;        
        try
        {
            maxValue = Integer.valueOf( attrValueText );
        }
        catch( NumberFormatException ex )
        {
        }
        if( maxValue == null || maxValue.intValue() < minValue.intValue() )
            throw newInvalidAttributeValueException( element.getContributor().getName(), 
                    attrValueText, attributeName );

        return maxValue;
    }
    
    static boolean getBooleanAttributeValue( IConfigurationElement element, String attributeName, 
                                            boolean defaultValue )
    {
        boolean booleanValue = defaultValue;  
        String attrValueText = element.getAttribute( attributeName );
        if( attrValueText != null )
            booleanValue = Boolean.parseBoolean( attrValueText );
        return booleanValue;
    }
    
    static void validateArgumentExists( String arg ) throws IllegalArgumentException
    {
        if( arg == null || arg.length() == 0 )
            throw new IllegalArgumentException( arg );        
    }
    
    static void validateArgumentExists( ExtensionContributor contributor ) throws IllegalArgumentException
    {
        if( contributor == null )
            throw new IllegalArgumentException( new NullPointerException() );  
    }

    static void validateConfigurationElement( IConfigurationElement element ) throws OdaException
    {
        if( element == null || ! element.isValid() )
            throw new OdaException( Messages.bind( Messages.querySpec_INVALID_EXT_POINT_ELEMENT,
                                        ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                                        element.getContributor().getName() ) );
    }

    static OdaException newInvalidAttributeValueException( String extensionId, String attrValue, String attrName )
    {
        return new OdaException( Messages.bind( Messages.querySpec_INVALID_EXT_POINT_ATTR_VALUE,
                new Object[] { ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                               extensionId, attrValue, attrName} ) );
    }
}
