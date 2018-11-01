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

package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderInputFactory;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderStorageEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderWindowStateFactory;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderWindowStateInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

/**
 * Defines the UI state of a window's controls.
 */
public class WindowStateInfo implements IWindowStateInfo {

    // current version
    public final static String CURRENT_VERSION = "1.0"; //$NON-NLS-1$

    private HashMap _controlStateInfoMap = new HashMap(); 
    private String _version;
    private int _height = IControlStateInfo.CONTROL_STATE_UNKNOWN_VALUE;
    private int _width = IControlStateInfo.CONTROL_STATE_UNKNOWN_VALUE;
	    
    /**
     * Constructor for WindowStateInfo
     */
	public WindowStateInfo(){
		_controlStateInfoMap = new HashMap();
	}
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#get(int)
     */
    public IControlStateInfo get( int controlType ) {
        return (IControlStateInfo)_controlStateInfoMap.get( new Integer( controlType ));
    }

    /* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#put(int, org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo)
	 */
	public void put( int controlType, IControlStateInfo controlStateInfo ) {
		_controlStateInfoMap.put( new Integer( controlType ), controlStateInfo );
	}

    /* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#remove(int)
	 */
	public void remove(int controlType){
		_controlStateInfoMap.remove(new Integer(controlType));
	}

    /* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#getcontrolStateInfos()
	 */
	public IControlStateInfo[] getcontrolStateInfos() {
		Set entries = _controlStateInfoMap.entrySet();
		return (IControlStateInfo[])entries.toArray(new IControlStateInfo[entries.size()]);
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#getVersion()
     */
    public String getVersion() {
        if( _version != null )
            return _version;
        return CURRENT_VERSION;     // return current version if not explicitly set
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#setVersion(java.lang.String)
     */
    public void setVersion( String version ) {
        _version = version;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#getHeight()
     */
    public int getHeight() {
        return _height;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#setHeight(int)
     */
    public void setHeight( int height ) {
        _height = height;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#getWidth()
     */
    public int getWidth() {
        return _width;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#setWidth(int)
     */
    public void setWidth( int width ) {
        _width = width;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo#encode()
     */
	public String encode() {
		/*
		 *  Create an XMLMemento and save the WindowStateInfo to it
		 */
		XMLMemento memento = XMLMemento
				.createWriteRoot(SQLBuilderWindowStateFactory.ID_XML_MEMENTO_ROOT_ELEMENT);
		
		// Create a WindowStateInput
		SQLBuilderWindowStateInput windowStateInput = new SQLBuilderWindowStateInput(this);

		// Save the state of the SQLBuilderWindowStateInput to the XMLMemento
		SQLBuilderWindowStateFactory.saveState(memento,
				windowStateInput);

		// Write out memento to a string
		StringWriter writer = new StringWriter();
		try {
			memento.save(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return writer.toString();

	}

	/**
	 * Decodes a <code>WindowStateInfo</code> from an encoded String. The string should
	 * be a serialized <code>XMLMemento</code> for the <code>WindowStateInfo</code>.
	 * @see encode()
	 * @param code encoded <code>WindowStateInfo</code> object.
	 * @return <code>WindowStateInfo</code> object
	 */
	public static IWindowStateInfo decode(String code)
	{
		/*
		 * Create a new XMLMemento from the string
		 */
		StringReader reader = new StringReader(code);
		XMLMemento memento = null;
		try {
			memento = XMLMemento.createReadRoot(reader);
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}

		/*
		 * Create a new SQLBuilderWindowStateInput from the XMLMemento
		 */
		SQLBuilderWindowStateFactory factory = new SQLBuilderWindowStateFactory();
		IAdaptable element = factory.createElement(memento);
		
		SQLBuilderWindowStateInput windowStateInput = (SQLBuilderWindowStateInput)element;
		
		return windowStateInput.getWindowStateInfo();
	}
	
}
