/*
 *************************************************************************
 * Copyright (c) 2006, 2010 Actuate Corporation.
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

import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * Represents a data source extension manifest's relationship element.
 * @since 3.0.3
 */
class Relationship
{
    static final String ELEMENT_NAME = "relationship";  //$NON-NLS-1$
    static final String RELATED_ID_ATTRIBUTE_NAME = "relatedId";  //$NON-NLS-1$
    static final String TYPE_ATTRIBUTE_NAME = "type";  //$NON-NLS-1$

    // relationship types
    static final int TYPE_REPLACED_BY_CODE = 1;
    static final int TYPE_WRAPPER_OF_CODE = 2;
    static final String[] sm_typeValues = 
    { 
        "none",             //$NON-NLS-1$
        "replacedBy",       //$NON-NLS-1$
        "wrapperOf"         //$NON-NLS-1$
    };
    
    private int m_type;
    private String m_relatedId;
    
    /**
     * Instantiates a Relationship object that represents the content
     * of the relationship element in the specified parent element.
     * @param relationshipParentElement    configuration element that may contain
     *                                     a relationship element
     * @return  a list of relationship instances, or null if none is defined or 
     *          has an invalid element
     */
    static List<Relationship> createInstances( IConfigurationElement relationshipParentElement )
    {
        if( relationshipParentElement == null )
            return null;       // nothing to create from
        IConfigurationElement[] elements =
            relationshipParentElement.getChildren( ELEMENT_NAME );
        if( elements.length < 1 )           // no relationship element
            return null;
        
        Vector<Relationship> instances = new Vector<Relationship>( elements.length );
        for( int i=0; i < elements.length; i++ )
        {
            Relationship anInstance = new Relationship();
            try
            {
                anInstance.init( elements[i] );
            }
            catch( OdaException e )
            {
                // none or invalid relationship element, ignore and skip element
                anInstance = null;
            }

            if( anInstance != null )
                instances.add( anInstance );
        }
        
        return instances.isEmpty() ? null : instances;
    }
    
    private Relationship()
    {           
    }
        
    private void init( IConfigurationElement relationshipElement )
        throws OdaException
    {    
        m_relatedId = relationshipElement.getAttribute( RELATED_ID_ATTRIBUTE_NAME );
        if( m_relatedId == null || m_relatedId.length() == 0 )
            throw new OdaException( 
                    Messages.bind( Messages.manifest_NO_ATTRIBUTE_ID_DEFINED, 
                            RELATED_ID_ATTRIBUTE_NAME, ELEMENT_NAME ));
        
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
    boolean isDeprecated()
    {
        return ( m_type == TYPE_REPLACED_BY_CODE );
    }

    /**
     * Indicates whether this extension is defined to be a wrapper 
     * with a wrapperOf relationship type.
     */
    boolean isWrapper()
    {
        return ( m_type == TYPE_WRAPPER_OF_CODE );
    }
   
    /**
     * Returns the relationship type constant.
     */
    int getType()
    {
        return m_type;
    }
    
    /**
     * Returns the related id.
     */
    String getRelatedId()
    {
        return m_relatedId;
    }

}
