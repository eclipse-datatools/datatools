/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.manifest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;

/**
 * Represents the definition of customized data set designer
 * that an ODA data provider extends to allow an user
 * to create or edit an ODA data set design instance.
 * This encapsulates the child elements for the data set wizard page(s) 
 * and editor page(s).  
 */
public class DataSetUIElement
{
    private static final String WIZARD_ELEMENT_NAME = "dataSetWizard"; //$NON-NLS-1$
    private static final String PAGE_ELEMENT_NAME = "dataSetPage"; //$NON-NLS-1$
    private static final String ID_ATRIBUTE_NAME = "id"; //$NON-NLS-1$
    
    private String m_id;
    private String m_initialPageId;
    private boolean m_supportsInParameters;
    private boolean m_supportsOutParameters;
    private DataSetWizardInfo m_wizardInfo;
    private Hashtable m_dataSetPages;
    private ArrayList m_dataSetPageIds;
    private IConfigurationElement m_dataSetElement;

    DataSetUIElement( IConfigurationElement dataSetElement ) 
        throws OdaException
    {
        m_dataSetElement = dataSetElement;
        m_id = dataSetElement.getAttribute( ID_ATRIBUTE_NAME );
        if( m_id == null || m_id.length() == 0 )
            throw new OdaException( 
                    Messages.bind( Messages.manifest_missingAttributeValue,
                                    ID_ATRIBUTE_NAME ));
        
        m_initialPageId = dataSetElement.getAttribute( "initialPageId" ); //$NON-NLS-1$

        m_supportsInParameters = true; // default
        String hasParamDefn = dataSetElement.getAttribute( "supportsInParameters" ); //$NON-NLS-1$
        if( hasParamDefn != null )
        {
            if( hasParamDefn.equalsIgnoreCase( "true" ) ||  //$NON-NLS-1$
                hasParamDefn.equalsIgnoreCase( "false" ) ) //$NON-NLS-1$
                m_supportsInParameters = Boolean.valueOf( hasParamDefn ).booleanValue();
        }
        
        m_supportsOutParameters = false; // default
        hasParamDefn = dataSetElement.getAttribute( "supportsOutParameters" ); //$NON-NLS-1$
        if( hasParamDefn != null )
        {
            if( hasParamDefn.equalsIgnoreCase( "true" ) ||  //$NON-NLS-1$
                hasParamDefn.equalsIgnoreCase( "false" ) ) //$NON-NLS-1$
                m_supportsOutParameters = Boolean.valueOf( hasParamDefn ).booleanValue();
        }

        // dataSetWizard element; must have one and only one
        IConfigurationElement wizardElement = 
                                getWizardElement( dataSetElement );
        if( wizardElement != null )
            m_wizardInfo = new DataSetWizardInfo( wizardElement );

        // dataSetPage elements; must have one or more
        getDataSetPages( dataSetElement );
    }

    /**
     * Collects the dataSetPage elements in the 
     * specified dataSetElement, and keep in 
     * instance variables.
     * Expects to find one or more dataSetPage elements.
     * @return a Hashtable of page id and corresponding 
     *          page info instance
     */
    private Hashtable getDataSetPages( IConfigurationElement dataSetElement )
        throws OdaException
    {
        IConfigurationElement[] pages = 
            dataSetElement.getChildren( PAGE_ELEMENT_NAME );
        if( pages.length == 0 )
            throw new OdaException( 
                Messages.bind( Messages.manifest_dataSetUi_missingElement,
                                PAGE_ELEMENT_NAME ));
        
        m_dataSetPages = new Hashtable( pages.length );
        m_dataSetPageIds = new ArrayList( pages.length );
        for( int i = 0; i < pages.length; i++ )
        {
            IConfigurationElement aPage = pages[i];
            String pageId = aPage.getAttribute( DataSetPageInfo.ID_ATTRIBUTE );
            m_dataSetPages.put( pageId, 
                              new DataSetPageInfo( aPage ) );

            // maintains the sequence of page elements
            m_dataSetPageIds.add( pageId );
        }
        return m_dataSetPages;
    }

    /**
     * Returns the fully qualified ID that uniquely identifies 
     * the ODA data set type within an ODA data source extension.  
     * Its value must match that of the <i>dataSet.id</i> attribute 
     * defined in the  
     * <i>org.eclipse.datatools.connectivity.oda.dataSource</i> 
     * extension of the same ODA driver.
     * @return the attribute value in <i>dataSetUI.id</i>
     */
    public String getId()
    {
        return m_id;
    }

    /**
     * Returns the id of a dataSetPage that should be selected
     * and have initial focus in a preference dialog. 
     * @return the attribute value in <i>dataSetUI.initialPageId</i>;
     *          may be null if none is specified
     */
    public String getInitialPageId()
    {
        return m_initialPageId;
    }

    /**
     * Indicates that this type of data set design supports 
     * input parameter definitions, and that an ODA host designer might need 
     * to collect further metadata on the parameter definitions provided 
     * by the customized page(s).
     * @return the attribute value in <i>dataSetUI.supportsInParameters</i>
     */
    public boolean supportsInParameters()
    {
        return m_supportsInParameters;
    }

    /**
     * Indicates that this type of data set design supports 
     * output parameter definitions, and that an ODA host designer might need 
     * to collect further metadata on the parameter definitions provided 
     * by the customized page(s).
     * @return the attribute value in <i>dataSetUI.supportsOutParameters</i>
     */
    public boolean supportsOutParameters()
    {
        return m_supportsOutParameters;
    }

    /**
     * Returns the configuration element of this instance.
     * @return  a dataSetUI configuration element 
     */
    public IConfigurationElement getElement()
    {
        return m_dataSetElement;
    }
    
    /**
     * Returns the definition of a data set wizard that allows an user 
     * to create a new ODA data set design instance.
     * @return the wizard definition that represents the
     *          <i>dataSetUI.dataSetWizard</i> element
     */
    public DataSetWizardInfo getWizardInfo()
    {
        return m_wizardInfo;
    }
    
    /**
     * Returns the dataSetWizard configuration element  
     * that defines the wizard that allows an user 
     * to create a new ODA data set design instance.
     * @param dataSetUIElement  a dataSetUI configuration element 
     * @return  the dataSetWizard configuration element
     * @throws OdaException if no dataSetWizard element is defined
     */
    public static IConfigurationElement getWizardElement( 
                    IConfigurationElement dataSetUIElement )
        throws OdaException
    {
        if( dataSetUIElement == null )
            return null;
        // dataSetWizard element; expects one and only one
        IConfigurationElement[] wizardElements = 
            dataSetUIElement.getChildren( WIZARD_ELEMENT_NAME );

    	// expects one element
        if( wizardElements.length < 1 )  
            throw new OdaException( 
                Messages.bind( Messages.manifest_dataSetUi_missingElement,
                                WIZARD_ELEMENT_NAME ));
        
        // >= 1 wizard element
        return wizardElements[0];   // takes the first one
    }
    
    /**
     * Returns an array of definitions of
     * customized data set pages that an extension contributes 
     * to an ODA host designer's data set dialog.
     * Its order is in the same sequence as the
     * dataSetPage elements defined in the extension manifest.
     * @return  an array of data set page definitions that
     *          represent the
     *          <i>dataSetUI.dataSetPage</i> elements
     */
    public DataSetPageInfo[] getPageDefinitions()
    {
        if( m_dataSetPageIds == null )
            return new DataSetPageInfo[0];
        
        // follow the sequence of the defined pages
        int numPages = m_dataSetPageIds.size();
        ArrayList dataSetPages = new ArrayList( numPages );
        for( int i = 0; i < numPages; i++ )
        {
            String pageId = (String) m_dataSetPageIds.get( i );
            try
            {
                dataSetPages.add( getPageDefinition( pageId ) );
            }
            catch( OdaException e )
            {
                // ignore, skip
                e.printStackTrace();
                continue;
            }
        }

        return (DataSetPageInfo[]) dataSetPages.toArray( 
                    new DataSetPageInfo[ dataSetPages.size() ] );
    }
    
    /**
     * Returns an array of ids of the dataSetPage elements 
     * defined in this data set ui element.
     * Its order is in the same sequence as the
     * dataSetPage elements defined in the extension manifest.
     * @return  an array of data set page ids.
     */
    public String[] getPageIds()
    {
        if( m_dataSetPageIds == null )
            return new String[0];
        
        return (String[]) m_dataSetPageIds.toArray( 
                new String[ m_dataSetPageIds.size() ] );
    }
    
    /**
     * Returns the DataSetPageInfo instance that
     * represents the dataSetPage element with the given id
     * defined in this data set ui element.
     * If the given page id is null and the data set UI
     * extension has only one data set page element, that
     * data set page will be returned by default.
     * @param pageID  the id of a data set page element
     * @return  the data set page definition
     * @throws OdaException if no data set page id is specified, and
     *                   there are more than one data set pages;
     *                   or when no match is found for given page id 
     */
    public DataSetPageInfo getPageDefinition( String pageId ) 
        throws OdaException
    {
        if( pageId == null || pageId.length() == 0 )
        {
            // find default data set page element and return it if found
            if( m_dataSetPages == null ||
                m_dataSetPages.size() != 1 )
                throw new OdaException( Messages.manifest_dataSetUi_missingPageId );

            Collection pages = m_dataSetPages.values();
            assert( pages.size() == 1 );
            return (DataSetPageInfo) pages.toArray()[0];
        }
        
        DataSetPageInfo pageInfo = 
            (DataSetPageInfo) m_dataSetPages.get( pageId );

        if( pageInfo == null )
            throw new OdaException( 
                    Messages.bind( Messages.manifest_dataSetUi_invalidPageId,
                                    pageId ));
        
        return pageInfo;
    }
    
    /**
     * Returns the dataSetPage configuration element  
     * with the given page id
     * defined in this data set ui element.
     * If the given page id is null and the data set UI
     * extension has only one data set page element, that
     * data set page element will be returned by default.
     * @param dataSetUIElement  a dataSetUI configuration element 
     * @param pageID  the id of a data set page element
     * @return the dataSetPage configuration element with given id;
     *          may be null if no matching id is found
     * @throws OdaException if no data set page id is specified, and
     *                   there are more than one data set pages 
     */
    public static IConfigurationElement getPageElement( 
            IConfigurationElement dataSetUIElement, String pageId )
        throws OdaException
    {
        if( dataSetUIElement == null )
            return null;
        
        IConfigurationElement[] pages = 
            dataSetUIElement.getChildren( PAGE_ELEMENT_NAME );
        if( pageId == null || pageId.length() == 0 )
        {
            // find default data set page element and return it if found
            if( pages.length != 1 )
                throw new OdaException( Messages.manifest_dataSetUi_missingPageId );
            return pages[0];
        }
        
        // find the one with the matching pageId
        for( int i = 0; i < pages.length; i++ )
        {
            IConfigurationElement pageElement = pages[i];
            if( pageId.equalsIgnoreCase( pageElement.getAttribute( DataSetPageInfo.ID_ATTRIBUTE ) ))
                return pageElement;
        }
        return null;    // no matching id is found
    }

}
