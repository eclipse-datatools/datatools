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

package org.eclipse.datatools.connectivity.oda.design.ui.manifest;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

import org.eclipse.birt.core.framework.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Represents the definition of customized data set designer
 * that an ODA data provider extends to allow an user
 * to create or edit an ODA data set design instance.
 * This encapsulates the child elements for the data set wizard page(s) 
 * and editor page(s).  
 */
public class DataSetUIElement
{
    private static final String WIZARD_ELEMENT_NAME = "newDataSetWizard"; //$NON-NLS-1$
    private static final String PAGE_ELEMENT_NAME = "dataSetPage"; //$NON-NLS-1$
    
    private String m_id;
    private String m_initialPageId;
    private boolean m_hasParameterDefinition;
    private DataSetWizardInfo m_wizardInfo;
    private Hashtable m_dataSetPages;

    DataSetUIElement( IConfigurationElement dataSetElement ) 
        throws OdaException
    {
        m_id = dataSetElement.getAttribute( "id" ); //$NON-NLS-1$
        if( m_id == null || m_id.length() == 0 )
            throw new OdaException( "Missing id attribute value" );
        
        m_initialPageId = dataSetElement.getAttribute( "initialPageId" ); //$NON-NLS-1$

        m_hasParameterDefinition = true; // default
        String hasParamDefn = dataSetElement.getAttribute( "hasParameterDefinition" ); //$NON-NLS-1$
        if( hasParamDefn != null )
        {
            if( hasParamDefn.equalsIgnoreCase( "true" ) ||  //$NON-NLS-1$
                hasParamDefn.equalsIgnoreCase( "false" ) ) //$NON-NLS-1$
                m_hasParameterDefinition = Boolean.valueOf( hasParamDefn ).booleanValue();
        }
        
        // newDataSetWizard element; must have one and only one
        IConfigurationElement[] wizardInfo = 
            dataSetElement.getChildren( WIZARD_ELEMENT_NAME );
        // TODO - restore error condition after all data sets are migrated
//        if( wizardInfo.length < 1 )  // expects one element
//            throw new OdaException( "Missing element: " + WIZARD_ELEMENT_NAME );
        if( wizardInfo.length >= 1 ) 
            m_wizardInfo = new DataSetWizardInfo( wizardInfo[0] );

        // dataSetPage elements; must have one or more
        m_dataSetPages = getDataSetPages( dataSetElement );
    }
    
    /**
     * Returns a collection of dataSetPage elements in the given element.
     * Expects to find one or more dataSetPage elements.
     */
    private Hashtable getDataSetPages( IConfigurationElement dataSetElement )
        throws OdaException
    {
        IConfigurationElement[] pages = 
            dataSetElement.getChildren( PAGE_ELEMENT_NAME );
        // TODO - restore error condition after all data sets are migrated
/*        if( pages.length == 0 )
            throw new OdaException( "Missing element: " + PAGE_ELEMENT_NAME );
*/        
        Hashtable dataSetPages = new Hashtable();
        for( int i = 0; i < pages.length; i++ )
        {
            IConfigurationElement aPage = pages[i];
            String pageId = aPage.getAttribute( "id" ); //$NON-NLS-1$
            dataSetPages.put( pageId, 
                              new DataSetPageInfo( aPage ) );
        }
        return dataSetPages;
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
     * Indicates whether this type of data set design tends to have 
     * data set parameters defined, and an ODA host designer might need 
     * to collect further metadata on the parameter definitions provided 
     * by the customized page(s).
     * @return the attribute value in <i>dataSetUI.hasParameterDefinition</i>
     */
    public boolean hasParameterDefinition()
    {
        return m_hasParameterDefinition;
    }

    /**
     * Returns the definition of a data set wizard that allows an user 
     * to create a new ODA data set design instance.
     * @return the wizard definition that represents the
     *          <i>dataSetUI.newDataSetWizard</i> element
     */
    public DataSetWizardInfo getWizardInfo()
    {
        return m_wizardInfo;
    }
    
    /**
     * Returns an array of definitions of
     * customized data set pages that an extension contributes 
     * to an ODA host designer's data set dialog.
     * @return  an array of data set page definitions that
     *          represent the
     *          <i>dataSetUI.dataSetPage</i> elements
     */
    public DataSetPageInfo[] getPageDefinitions()
    {
        if( m_dataSetPages == null )
            return new DataSetPageInfo[0];
        
        Collection pages = m_dataSetPages.values();
        return (DataSetPageInfo[]) pages.toArray( 
                    new DataSetPageInfo[ pages.size() ] );
    }
    
    /**
     * Returns an array of ids of the dataSetUI elements 
     * defined in this data source extension.
     * @return  an array of data set element ids.
     */
    public String[] getDataSetUIElementIDs()
    {
        if( m_dataSetPages == null )
            return new String[0];
        
        Set pageIds = m_dataSetPages.keySet();
        return (String[]) pageIds.toArray( new String[ pageIds.size() ] );
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
     * @throws OdaException if there is no data set page definition associated 
     *                   with the specified data set page id, or 
     *                   if there are more than one data set pages 
     *                   that match the id.
     */
    public DataSetPageInfo getPageDefinition( String pageId ) 
        throws OdaException
    {
        if( pageId == null )
        {
            // find default data set page element and return it if found
            if( m_dataSetPages == null ||
                m_dataSetPages.size() != 1 )
                throw new OdaException( "Missing data set page id" );

            Collection pages = m_dataSetPages.values();
            assert( pages.size() == 1 );
            return (DataSetPageInfo) pages.toArray()[0];
        }
        
        DataSetPageInfo pageInfo = 
            (DataSetPageInfo) m_dataSetPages.get( pageId );

        if( pageInfo == null )
            throw new OdaException( "Invalid data set page id: " + pageId );
        
        return pageInfo;
    }
    
}
