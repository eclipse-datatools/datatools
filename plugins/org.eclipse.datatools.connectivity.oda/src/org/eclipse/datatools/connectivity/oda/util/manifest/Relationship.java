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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Represents a data source extension manifest's relationship element.
 */
class Relationship
{
    static final String ELEMENT_NAME = "relationship";  //$NON-NLS-1$
    static final String RELATED_ATTRIBUTE_NAME = "relatedDataSourceId";  //$NON-NLS-1$
    static final String TYPE_ATTRIBUTE_NAME = "type";  //$NON-NLS-1$

    // relationship types
    static final int TYPE_REPLACED_BY_CODE = 1;
    static final String[] sm_typeValues = 
    { 
        "none",             //$NON-NLS-1$
        "replacedBy"        //$NON-NLS-1$
    };
    
    private int m_type;
    private String m_relatedDataSourceId;
    
    /**
     * Instantiates a Relationship object that represents the content
     * of the relationship element in the specified extension.
     * @param dataSourceExtn    data source extension object
     * @return  the relationship object, or null if none is defined or 
     *          has an invalid element
     */
    static Relationship createInstance( IExtension dataSourceExtn )
    {
        Relationship anInstance = new Relationship();
        try
        {
            anInstance.init( dataSourceExtn );
        }
        catch( OdaException e )
        {
            // none or invalid relationship element, ignore and return null
            return null;
        }
        
        return anInstance;
    }
    
    private Relationship()
    {           
    }
    
    private void init( IExtension dataSourceExtn )
        throws OdaException
    {
        IConfigurationElement[] elements =
                ManifestExplorer.getNamedElements( 
                    dataSourceExtn, ELEMENT_NAME, RELATED_ATTRIBUTE_NAME );
        if( elements.length < 1 )   // none or invalid relationship element
            throw new OdaException();

        // expects one element only, use the first element found
        IConfigurationElement relationshipElement = elements[0];
        m_relatedDataSourceId = relationshipElement.getAttribute( RELATED_ATTRIBUTE_NAME );
        assert( m_relatedDataSourceId != null );    // already validated by getNamedElements
        
        setRelationshipType( relationshipElement.getAttribute( TYPE_ATTRIBUTE_NAME ) );
    }
    
    private void setRelationshipType( String typeAttributeValue )
        throws OdaException
    {
        if( typeAttributeValue == null || typeAttributeValue.length() == 0 )
            throw new OdaException();

        for( int i = 1; i < sm_typeValues.length; i++ )
        {
            if( typeAttributeValue.equalsIgnoreCase( sm_typeValues[i] ) )
            {
                m_type = i;
                return;     // found a match, done with setting type value
            }
        }
                
        // not a recognized type
        throw new OdaException();            
    }
    
    /**
     * Indicates whether this extension is defined to be deprecated with
     * a replacedBy relationship type.
     */
    public boolean isDeprecated()
    {
        return ( m_type == TYPE_REPLACED_BY_CODE );
    }

    /**
     * Returns the related dataSourceId.
     */
    public String getRelatedDataSourceId()
    {
        return m_relatedDataSourceId;
    }

}
