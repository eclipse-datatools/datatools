/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.manifest;

import org.eclipse.birt.core.framework.IConfigurationElement;
import java.util.ArrayList;

/**
 * The definition of a property defined by an ODA data source extension or 
 * its supported data set definitions.
 * <br>
 * No validation is done on the attribute values; 
 * it is up to the consumer to process as appropriate.
 */
public class Property
{
    private String m_name;
    private String m_displayName;
    private String m_groupName;
    private String m_groupDisplayName;
    private String m_type;
    private boolean m_canInherit = true;	// default value
    private String m_defaultValue;
    private boolean m_isEncryptable = false;	// default value
    private PropertyChoice[] m_choices = null;

    Property( IConfigurationElement propertyElement )
    {
        setAttributes( propertyElement, null, null );
    }
    
    Property( IConfigurationElement propertyElement,
              String groupName, String groupDisplayName )
    {
        setAttributes( propertyElement, groupName, groupDisplayName );
    }
    
    private void setAttributes( IConfigurationElement propertyElement,
            String groupName, String groupDisplayName )
    {
        // no validation is done; up to the consumer to process
        m_name = propertyElement.getAttribute( "name" );
        m_displayName = ManifestExplorer.getElementDisplayName( propertyElement );  
        m_groupName = groupName;
        m_groupDisplayName = groupDisplayName;
        m_type = propertyElement.getAttribute( "type" );
        m_defaultValue = propertyElement.getAttribute( "defaultValue" );

        m_isEncryptable = false;
        String encryptableValue = propertyElement.getAttribute( "isEncryptable" );
 		if( encryptableValue != null )
		{
		    if ( encryptableValue.equalsIgnoreCase( "true" ) || 
		         encryptableValue.equalsIgnoreCase( "false" ) )
		        m_isEncryptable = Boolean.valueOf( encryptableValue ).booleanValue();
		}

 		m_canInherit = true;
        String canInherit = propertyElement.getAttribute( "canInherit" );
		if( canInherit != null )
		{
		    if ( canInherit.equalsIgnoreCase( "true" ) || 
			     canInherit.equalsIgnoreCase( "false" ) )
		        m_canInherit = Boolean.valueOf( canInherit ).booleanValue();
		}
		
		// choice elements
		IConfigurationElement[] choiceElements = 
		    propertyElement.getChildren( "choice" );
		int numChoices = choiceElements.length;
		if ( numChoices <= 0 )
		    return;		// done
		
		ArrayList choices = new ArrayList( numChoices );
		for( int i = 0; i < numChoices; i++ )
		{
			IConfigurationElement choiceElement = choiceElements[i];
			choices.add( new PropertyChoice( choiceElement ) );
		}
		m_choices = (PropertyChoice[]) choices.toArray( new PropertyChoice[ numChoices ] );

   }

    /**
     * Returns the property name.
     * @return	property name
     */
    public String getName()
    {
        return m_name;
    }
    
    /**
	 * Returns the display name of the extension-defined property.  
	 * Defaults to property name if no display name is specified.
	 * @return	The display name of the property
	 */
    public String getDisplayName()
    {
        return m_displayName;
    }
    
    /**
     * If the property is defined in a group, returns
     * the group's name.  Returns null for top-level property.
     * @return	the group name of the property, if applicable.
     */
    public String getGroupName()
    {
        return m_groupName;
    }
    
    /**
     * If the property is defined in a group, returns
     * the group's display name.  
	 * Defaults to group name if no display name is specified.
     * Returns null for top-level property.
     * @return	the group display name of the property, if applicable.
     */
     public String getGroupDisplayName()
    {
        return m_groupDisplayName;
    }
    
    /**
     * Returns the type of property.  See the extension point
     * schema for a list of valid type values.
     * @return	property type.
     */
    public String getType()
    {
        return m_type;
    }
    
    /**
     * Returns whether the property can inherit from parent.
     * Defaults to true if none is specified.
     * @return	whether the property can inherit.
     */
    public boolean canInherit()
    {
        return m_canInherit;
    }
    
    /**
     * Returns the default value of the property.  
     * Could be null value.
     * @return	property default value.
     */
    public String getDefaultValue()
    {
        return m_defaultValue;
    }
    
    /**
     * Returns a flag indicating whether this property value should be encrypted
     * in the persistent report design file.
     * @return	'true' or 'false' value indicating wehther
     * 			the property value should be encrypted.
     */
    public boolean isEncryptable()
    {
        return m_isEncryptable;
    }

    /**
     * Returns the selection list of choices for the property value.
     * An empty array is returned if no choices are specified.
     * @return	an array of PropertyChoice instances that
     * 			represent the choice elements defined for the property.
     */
    public PropertyChoice[] getChoices()
    {
        if ( m_choices == null )
        {
    		m_choices = new PropertyChoice[ 0 ];
        }
        return m_choices;
    }

}
