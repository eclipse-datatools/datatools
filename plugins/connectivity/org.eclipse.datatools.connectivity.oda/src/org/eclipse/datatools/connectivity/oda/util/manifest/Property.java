/*
 *************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * The definition of a property defined by an ODA data source extension or 
 * its supported data set definitions.
 * <br>
 * No validation is done on the attribute values; 
 * it is up to the consumer to process as appropriate.
 */
public class Property
{
    private static final String VISIBILITY_LOCK = "lock"; //$NON-NLS-1$
    private static final String VISIBILITY_CHANGE = "change"; //$NON-NLS-1$
    private static final String VISIBILITY_HIDE = "hide"; //$NON-NLS-1$
    private static final String LITERAL_TRUE = "true"; //$NON-NLS-1$
    private static final String LITERAL_FALSE = "false"; //$NON-NLS-1$
    
    private static final String NAME_ATTR = "name";  //$NON-NLS-1$
    private static final String TYPE_ATTR = "type";  //$NON-NLS-1$
    private static final String DEFAULT_VALUE_ATTR = "defaultValue";  //$NON-NLS-1$
    private static final String ENCRYPTABLE_ATTR = "isEncryptable";  //$NON-NLS-1$
    private static final String CAN_INHERIT_ATTR = "canInherit";  //$NON-NLS-1$
    private static final String EMPTY_VALUE_TYPE_ATTR = "allowsEmptyValueAsNull";  //$NON-NLS-1$
    private static final String CHOICE_ELEMENT = "choice";   //$NON-NLS-1$
    
    private String m_name;
    private String m_displayName;
    private String m_groupName;
    private String m_groupDisplayName;
    private String m_type = "string"; //$NON-NLS-1$;
    private boolean m_canInherit = true;
    private String m_defaultValue;
    private boolean m_isEncryptable = false;
    private PropertyChoice[] m_choices = null;
    private boolean m_allowsEmptyValueAsNull = true;

    Property( IConfigurationElement propertyElement )
    {
        setAttributes( propertyElement, null, null );
    }
    
    Property( IConfigurationElement propertyElement,
              String groupName, String groupDisplayName )
    {
        setAttributes( propertyElement, groupName, groupDisplayName );
    }
    
    Property( String name, String displayName, 
              String groupName, String groupDisplayName )
    {
        // no validation is done; up to the consumer to process
        assert( name != null && name.length() > 0 );
        m_name = name;
        m_displayName = displayName;  
        m_groupName = groupName;
        m_groupDisplayName = groupDisplayName;
     }
    
    private void setAttributes( IConfigurationElement propertyElement,
            String groupName, String groupDisplayName )
    {
        // no validation is done; up to the consumer to process
        m_name = propertyElement.getAttribute( NAME_ATTR );
        m_displayName = ManifestExplorer.getElementDisplayName( propertyElement );  
        m_groupName = groupName;
        m_groupDisplayName = groupDisplayName;
        String propType = propertyElement.getAttribute( TYPE_ATTR );
        if( propType != null && propType.length() >= 0 )
        	m_type = propType;     // use this instead of default type
        m_defaultValue = propertyElement.getAttribute( DEFAULT_VALUE_ATTR );

        Boolean boolValue = convertBooleanValue( 
                propertyElement.getAttribute( ENCRYPTABLE_ATTR ) );
        if( boolValue != null )
            m_isEncryptable = boolValue.booleanValue();

        boolValue = convertBooleanValue( 
                propertyElement.getAttribute( CAN_INHERIT_ATTR ) );
        if( boolValue != null )
            m_canInherit = boolValue.booleanValue();
		
        boolValue = convertBooleanValue( 
                propertyElement.getAttribute( EMPTY_VALUE_TYPE_ATTR ) );
        if( boolValue != null )
            m_allowsEmptyValueAsNull = boolValue.booleanValue();
        
		// choice elements
		IConfigurationElement[] choiceElements = 
		    propertyElement.getChildren( CHOICE_ELEMENT );
		int numChoices = choiceElements.length;
		if ( numChoices <= 0 )
		    return;		// done
		
		ArrayList<PropertyChoice> choices = new ArrayList<PropertyChoice>( numChoices );
		for( int i = 0; i < numChoices; i++ )
		{
			IConfigurationElement choiceElement = choiceElements[i];
			choices.add( new PropertyChoice( choiceElement ) );
		}
		m_choices = (PropertyChoice[]) choices.toArray( new PropertyChoice[ numChoices ] );

    }

    private Boolean convertBooleanValue( String value )
    {
        if ( value == null || value.length() == 0 )
            return null;
        
        if ( value.equalsIgnoreCase( LITERAL_TRUE ) || 
             value.equalsIgnoreCase( LITERAL_FALSE ) )
            return Boolean.valueOf( value );
        return null;
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
     * @return	'true' or 'false' value that indicates whether
     * 			the property value should be encrypted.
     */
    public boolean isEncryptable()
    {
        return m_isEncryptable;
    }

    /**
     * Returns a flag that indicates whether an empty value of this property 
     * can be treated as a null value.
     * @return  true if this property's empty value can be handled by the client 
     *              as a null value
     */
    public boolean allowsEmptyValueAsNull()
    {
        return m_allowsEmptyValueAsNull;
    }

    /**
     * Overrides the default setting of whether an empty value of this property 
     * can be treated as a null value.
     * @param allowsEmptyValueAsNull    a boolean flag to indicate
     *              whether an empty value of this property 
     *              can be treated as a null value.
     */
    void setAllowsEmptyValueAsNull( boolean allowsEmptyValueAsNull )
    {
        m_allowsEmptyValueAsNull = allowsEmptyValueAsNull;
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

    /**
     * Indicates whether this property should be visible
     * per the definition specified in the properties element.
     * @param propertiesVisibility  the collection of property visibility
     *          defined for the element associated with
     *          this property
     * @return  true if property is defined to be visible;
     *          false otherwise
     */
    public boolean isVisible( Properties propertiesVisibility )
    {
        String visibility = 
            getVisibility( getName(), propertiesVisibility );
        if( visibility.equalsIgnoreCase( VISIBILITY_HIDE ) )
            return false;
        return true;
    }

    /**
     * Indicates whether this property value should be editable,
     * per the definition specified in the properties element.
     * @param propertiesVisibility  the collection of property visibility
     *          defined for the element associated with
     *          this property
     * @return  true if property is defined to be editable;
     *          false if the property value should be read only.
     */
    public boolean isEditable( Properties propertiesVisibility )
    {
        String visibility = 
            getVisibility( getName(), propertiesVisibility );
        if( visibility.equalsIgnoreCase( VISIBILITY_HIDE ) ||
            visibility.equalsIgnoreCase( VISIBILITY_LOCK ) )
            return false;
        return true;
    }
    
    /**
     * Finds the property visibility value.
     */
    private String getVisibility( String propName, 
                            Properties propertiesVisibility )
    {
        if( propertiesVisibility == null || 
            propertiesVisibility.size() == 0 )
            return VISIBILITY_CHANGE;   // default
        
        assert( propName != null );
        return propertiesVisibility.getProperty( 
                            propName, VISIBILITY_CHANGE );
    }
}
