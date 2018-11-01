/**************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 **************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.input;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ControlStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.WindowStateInfo;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

/**
 * The class implements a factory which is capable of saving and recreating a
 * SQLBuilderWindowStateInput stored in a memento. 
 */
public class SQLBuilderWindowStateFactory implements IElementFactory
{

    /** The Factory ID. */
    public final static String ID_SQL_BUILDER_STORAGE_EDITOR_INPUT_FACTORY =
        "org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderWindowStateFactory"; //$NON-NLS-1$
    
    /**
     * The root element name used in <code>XMLMemento</code> objects representing
     * <code>SQLBuilderStorageEditorInput</code> objects. 
     */
    public final static String ID_XML_MEMENTO_ROOT_ELEMENT = "SQLQueryBuilder";

    public final static String KEY_WINDOW_STATE_TYPE = "windowState"; //$NON-NLS-1$

	public final static String KEY_WINDOW_STATE_VERSION = "version"; //$NON-NLS-1$

	public final static String KEY_WINDOW_STATE_HEIGHT = "height"; //$NON-NLS-1$

	public final static String KEY_WINDOW_STATE_WIDTH = "width"; //$NON-NLS-1$

	public final static String KEY_WINDOW_CONTROL_TYPE = "control"; //$NON-NLS-1$

	public final static String KEY_SECTION_NAME = "name"; //$NON-NLS-1$

	public final static String KEY_SECTION_VISIBLE_STATE = "isVisible"; //$NON-NLS-1$

	public final static String KEY_SECTION_HIDEABLE_STATE = "isHideable"; //$NON-NLS-1$

	public final static String KEY_SECTION_HEIGHT = "height"; //$NON-NLS-1$

	public final static String KEY_SECTION_WIDTH = "width"; //$NON-NLS-1$

	public final static String NAME_SQL_SOURCE_VIEWER = "SQLSourceViewer"; //$NON-NLS-1$
	
	public final static String NAME_DESIGN_VIEWER = "DesignViewer"; //$NON-NLS-1$
	
	public final static String NAME_GRAPH_CONTROL = "GraphControl"; //$NON-NLS-1$
	
	public final static String NAME_OUTLINE_VIEWER = "OutlineViewer"; //$NON-NLS-1$
	
	/**
     * Re-creates and returns an object from the state captured within the given 
     * memento. Returns a SQLBuilderWindowStateInput.
     *  
     * @see org.eclipse.ui.IElementFactory#createElement(org.eclipse.ui.IMemento)
     */
    public IAdaptable createElement( IMemento memento )
	{
		if ( memento == null )
			return null;
		
        IMemento windowStateElement = memento.getChild( KEY_WINDOW_STATE_TYPE );
        if ( windowStateElement == null )
            return null;
        
        // memento element exists
        
        IWindowStateInfo windowStateInfo = new WindowStateInfo();       
        
        windowStateInfo.setVersion( windowStateElement.getString( KEY_WINDOW_STATE_VERSION ) );
        Integer heightValue = getAttributeValueAsInteger( windowStateElement,
                                KEY_WINDOW_STATE_HEIGHT );          
        if ( heightValue != null )
            windowStateInfo.setHeight( heightValue.intValue() );
        
        Integer widthValue = getAttributeValueAsInteger( windowStateElement,
                                KEY_WINDOW_STATE_WIDTH );          
        if ( widthValue != null )
            windowStateInfo.setWidth( widthValue.intValue() );
        
        // re-create each control found in the window state element
        IMemento[] winStateControls = windowStateElement.getChildren( KEY_WINDOW_CONTROL_TYPE );
		for ( int i = 0; i < winStateControls.length; i++ )
		{
		    IMemento controlElement = winStateControls[i];
			
            String controlName = controlElement.getString( KEY_SECTION_NAME );
            if ( controlName == null || controlName.length() == 0 )
                continue;   // skip unnamed control
            
            IControlStateInfo ctrlStateInfo = new ControlStateInfo();
			if ( controlName.equals( NAME_SQL_SOURCE_VIEWER ) )
			{				
				ctrlStateInfo.setControlType( IControlStateInfo.SOURCE_CONTROL );
			}
			else if (controlName.equals( NAME_DESIGN_VIEWER ))
			{
				ctrlStateInfo.setControlType( IControlStateInfo.DESIGN_CONTROL );	
			}
			else if (controlName.equals( NAME_GRAPH_CONTROL ))
			{
				ctrlStateInfo.setControlType( IControlStateInfo.GRAPHICAL_CONTROL );	
			}
			else if (controlName.equals( NAME_OUTLINE_VIEWER ))
			{
				ctrlStateInfo.setControlType( IControlStateInfo.OUTLINE_CONTROL );	
			}
			else // invalid control name, skip control
			{
			    continue;
			}
		
			heightValue = getAttributeValueAsInteger( controlElement, KEY_SECTION_HEIGHT );          
            if ( heightValue != null )
                ctrlStateInfo.setHeight( heightValue.intValue() );

            widthValue = getAttributeValueAsInteger( controlElement, KEY_SECTION_WIDTH );          
            if ( widthValue != null )
                ctrlStateInfo.setWidth( widthValue.intValue() );
       
            String isVisibleString = getAttributeValue( controlElement,
                                    KEY_SECTION_VISIBLE_STATE );
            boolean isVisible = ( isVisibleString != null && isVisibleString.trim().length() > 0 ) ?
                                    Boolean.valueOf( isVisibleString ).booleanValue() :
                                    true;			
			ctrlStateInfo.setIsVisible( isVisible );

            String isHideableString = getAttributeValue( controlElement,
                                    KEY_SECTION_HIDEABLE_STATE );
            boolean isHideable = ( isHideableString != null && isHideableString.trim().length() > 0 ) ?
                                    Boolean.valueOf( isHideableString ).booleanValue() :
                                    true;           
            ctrlStateInfo.setIsHideable( isHideable );
			
			windowStateInfo.put( ctrlStateInfo.getControlType(), ctrlStateInfo );
		}
		
		return new SQLBuilderWindowStateInput( windowStateInfo );		
	}

	private static String getAttributeValue( IMemento memento, String attrName )
	{
		if( memento == null )
			return null;
		
		return memento.getString( attrName );
	}

	private static Integer getAttributeValueAsInteger( IMemento memento, String attrName )
	{
	    return convertToInteger( getAttributeValue( memento, attrName ) );
	}

	private static Integer convertToInteger( String intString )
	{
	    if( intString == null || intString.trim().length() == 0 )
	        return null;
	    
        int intValue = IControlStateInfo.CONTROL_STATE_UNKNOWN_VALUE;
        try
        {
            intValue = Integer.parseInt( intString );
        }
        catch( NumberFormatException ex )
        {
            // ignore; use the default unknown value
        }
        return new Integer( intValue );
	}
	
    /**
     * Saves the state of the given window state input object in the given memento.
     * @param memento the storage area for object's state
     * @param windowStateInput the window state input object that needs to be saved;
     *                  must contain an IWindowStateInfo instance
     */
    public static void saveState( IMemento memento, SQLBuilderWindowStateInput windowStateInput )
    {
        if( memento == null || windowStateInput == null )
            return;
        
        IWindowStateInfo windowStateInfo = windowStateInput.getWindowStateInfo();
        
        // a SQLBuilderWindowStateInput must contain an IWindowStateInfo instance
        if( windowStateInfo == null )
            throw new IllegalArgumentException( "SQLBuilderWindowStateInput" ); //$NON-NLS-1$

        IMemento winStateElement = memento.createChild( KEY_WINDOW_STATE_TYPE );

        winStateElement.putString( KEY_WINDOW_STATE_VERSION, windowStateInfo.getVersion() );
        saveSizeAttribute( winStateElement, KEY_WINDOW_STATE_HEIGHT, 
                windowStateInfo.getHeight() );
        saveSizeAttribute( winStateElement, KEY_WINDOW_STATE_WIDTH, 
                windowStateInfo.getWidth() );
        
        // saves each control type in the WindowStateInfo, if exists, in the memento

        // <control name="SQLSourceViewer" isVisible="true" isHideable="true"
        //          height="300" width="400"/>
        IControlStateInfo controlStateInfo = 
            windowStateInfo.get( IControlStateInfo.SOURCE_CONTROL );
        saveControlState( winStateElement, controlStateInfo, NAME_SQL_SOURCE_VIEWER );
        
        // <control name="DesignViewer" isVisible="true" isHideable="false"
        //          height="300" width="400"/>
        controlStateInfo = windowStateInfo.get( IControlStateInfo.DESIGN_CONTROL );
        saveControlState( winStateElement, controlStateInfo, NAME_DESIGN_VIEWER );
        
        // <control name="GraphControl" isVisible="false" isHideable="false"
        //          height="300" width="400"/>
        controlStateInfo = windowStateInfo.get( IControlStateInfo.GRAPHICAL_CONTROL );
        saveControlState( winStateElement, controlStateInfo, NAME_GRAPH_CONTROL );
        
        // <control name="OutlineViewer" isVisible="true" isHideable="true"
        //          height="320" width="100"/>
        controlStateInfo = windowStateInfo.get( IControlStateInfo.OUTLINE_CONTROL );
        saveControlState( winStateElement, controlStateInfo, NAME_OUTLINE_VIEWER );
    }
    
    private static void saveSizeAttribute( IMemento memento, String key, int length )
    {
        if( length != IControlStateInfo.CONTROL_STATE_UNKNOWN_VALUE )
            memento.putString( key, Integer.toString( length ) );
    }

    private static void saveControlState( IMemento memento, 
                                        final IControlStateInfo controlStateInfo,
                                        final String controlName )
    {
        if( controlStateInfo == null )
            return;     // done; nothing to save
        
        IMemento controlMemento = memento.createChild( KEY_WINDOW_CONTROL_TYPE );
        controlMemento.putString( KEY_SECTION_NAME, controlName );
        
        controlMemento.putString( KEY_SECTION_VISIBLE_STATE, 
                            Boolean.toString( controlStateInfo.isVisible() ));
        controlMemento.putString( KEY_SECTION_HIDEABLE_STATE, 
                            Boolean.toString( controlStateInfo.isHideable() ));
        
        saveSizeAttribute( controlMemento, KEY_SECTION_HEIGHT, controlStateInfo.getHeight() );
        saveSizeAttribute( controlMemento, KEY_SECTION_WIDTH, controlStateInfo.getWidth() );
    }
	
}
