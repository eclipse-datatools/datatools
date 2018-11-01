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

/**
 * Defines the UI state of a SQL Query Builder window's controls.
 */
public interface IWindowStateInfo {
	
	/**
	 * Gets the control state information of the specified control type.
     * @param controlType  An IControlStateInfo.*_CONTROL constant defined for 
     * one of the section controls, for example, {@link IControlStateInfo#SOURCE_CONTROL}.
	 * @return IControlStateInfo instance that represents the state
     *                 of the specified control type; may be null if none is available.
     * @see IControlStateInfo
	 */
	public IControlStateInfo get( int controlType );

    /**
     * Stores the control state information for the specified control type.
     * @param controlType An IControlStateInfo.*_CONTROL constant defined for 
     * one of the section controls, for example, {@link IControlStateInfo#SOURCE_CONTROL}.
     * @param controlStateInfo an IControlStateInfo instance that represents the state
     *                 of the specified control type.
     * @see IControlStateInfo
     */
    public void put( int controlType, IControlStateInfo controlStateInfo );

    /**
     * Revoves the control state information for the specified control type.
     * @param  controlType An IControlStateInfo.*_CONTROL constant defined for 
     * one of the section controls, for example, {@link IControlStateInfo#SOURCE_CONTROL}.
	 * @see IControlStateInfo
     */
	public void remove(int controlType);

	/**
	 * Gets array of all the <code>IControlStateInfo</code>s contained in this
	 * <code>IWindowStateInfo</code>
	 * @return IControlStateInfo[]
	 * @see IControlStateInfo
	 */
	public IControlStateInfo[] getcontrolStateInfos();
	
	/**
     * Returns the version of this window state information.
     * @return  version   version defined by an IWindowStateInfo implementation
     */
    public String getVersion();
    
    /**
     * Sets the version of this window state information.
     * @param version   version defined by an IWindowStateInfo implementation
     */
    public void setVersion( String version );
    
    /**
     * Returns the overall height of the SQL Query Builder main control.
     * @return  the overall height; 
     *          or {@link IControlStateInfo#CONTROL_STATE_UNKNOWN_VALUE} if value is not known.
     */
    public int getHeight();

    /**
     * Sets the overall height of the SQL Query Builder main control.
     * @param height
     */
    public void setHeight( int height );

    /**
     * Returns the overall width of the SQL Query Builder main control.
     * @return  the overall width;
     *          or {@link IControlStateInfo#CONTROL_STATE_UNKNOWN_VALUE} if value is not known.
     */
    public int getWidth();

    /**
     * Sets the overall width of the SQL Query Builder main control.
     * @param width
     */
    public void setWidth( int width );

	/**
	 * Encodes the given <code>IWindowStateInfo</code> object for persistence.
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.model.WindowStateInfo#decode(String)
	 * @return encoded String
	 */
	public String encode();
}
