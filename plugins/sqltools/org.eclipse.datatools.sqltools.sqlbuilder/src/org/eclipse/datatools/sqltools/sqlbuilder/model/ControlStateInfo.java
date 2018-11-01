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
 * Defines the UI state of a control in the SQL query builder.
 */
public class ControlStateInfo implements IControlStateInfo
{
	private int _controlType = IControlStateInfo.CONTROL_STATE_UNKNOWN_VALUE;
	private int _height = IControlStateInfo.CONTROL_STATE_UNKNOWN_VALUE;
	private int _width = IControlStateInfo.CONTROL_STATE_UNKNOWN_VALUE;
	private boolean _isVisible = true;
    private boolean _isHideable = true;

    /**
     * No parameter constructor for ControlStateInfo
     */
	public ControlStateInfo(){
	}

	/**
     * Constructor for ControlStateInfo with parameters for
     * all member fields.
	 * 
	 * @param controlType An IControlStateInfo.*_CONTROL constant defined for 
     * 		one of the section controls, for example, {@link IControlStateInfo#SOURCE_CONTROL}.
	 * @param visible
	 * @param hideable
	 * @param width
	 * @param height
	 */
	public ControlStateInfo(int controlType, boolean visible, boolean hideable, int width, int height){
		_controlType = controlType;
		_isVisible = visible;
		_isHideable = hideable;
		_width = width;
		_height = height;
	}
	
    /**
     * Constructor for ControlStateInfo with parameter for
     * controlType member field.
	 * @param controlType An IControlStateInfo.*_CONTROL constant defined for 
     * one of the section controls, for example, {@link IControlStateInfo#SOURCE_CONTROL}.
     */
	public ControlStateInfo(int controlType){
		_controlType = controlType;
	}
    
	/* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#getControlType()
     */
	public int getControlType() {
		return _controlType;
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#setControlType(int)
     */
    public void setControlType( int controlType ) {
    	_controlType = controlType;
    }
	
    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#getHeight()
     */
	public int getHeight() {
		return _height;
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#setHeight(int)
     */
    public void setHeight(int height) {
    	_height = height;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#getWidth()
     */
	public int getWidth() {
		return _width;
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#setWidth(int)
     */
    public void setWidth( int width ) {
    	_width = width;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#isVisible()
     */
	public boolean isVisible() {
		return _isVisible;
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#setIsVisible(boolean)
     */
    public void setIsVisible( boolean isVisible ) {
    	_isVisible = isVisible;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#isHideable()
     */
    public boolean isHideable() {
        return _isHideable;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo#setIsHideable(boolean)
     */
    public void setIsHideable( boolean isHideable ) {
        _isHideable = isHideable;        
    }
    
}
