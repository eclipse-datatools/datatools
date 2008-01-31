/**************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 **************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.model;


/**
 * The interface defines the UI state of a section control in the SQL Query Builder.
 */
public interface IControlStateInfo {
    
	/**
	 * Constants for the control types
	 */
	public final static int SOURCE_CONTROL = 1;
	public final static int GRAPHICAL_CONTROL = 2;
	public final static int OUTLINE_CONTROL = 3;
	public final static int DESIGN_CONTROL = 4;

    /**
     *  Constant for an unknown value of a control state attribute
     */
    public static final int CONTROL_STATE_UNKNOWN_VALUE = -1;
	
	/**
     * Returns the type of section control for this state information.
     * @return  A SQLBuilderConstants.CONTROL_TYPE_* constant defined for 
     *          one of the section controls
     * @see {@link SQLBuilderConstants}
     */
    public int getControlType();

    /**
     * Sets the type of section control for this state information.
     * @param controlType  A SQLBuilderConstants.CONTROL_TYPE_* constant defined for 
     *                     one of the section controls. 
     * @see {@link SQLBuilderConstants}
     */
    public void setControlType( int controlType );

    /**
     * Returns the height of the control.
     * @return  the control height; 
     *          or SQLBuilderConstants.CONTROL_STATE_UNKNOWN_VALUE if value is not known.
     * @see {@link SQLBuilderConstants#CONTROL_STATE_UNKNOWN_VALUE}
     */
    public int getHeight();

    /**
     * Sets the height of the control.
     * @param height
     */
    public void setHeight( int height );

    /**
     * Returns the width of the control.
     * @return  the control width; 
     *          or SQLBuilderConstants.CONTROL_STATE_UNKNOWN_VALUE if value is not known.
     * @see {@link SQLBuilderConstants#CONTROL_STATE_UNKNOWN_VALUE}
     */
    public int getWidth();

    /**
     * Sets the width of the control.
     * @param width
     */
    public void setWidth( int width );

    /**
     * Indicates the visibility state of the control.
     * @return  true if the control is visible; false otherwise.  Default value is true.
     */
    public boolean isVisible();

    /**
     * Sets the visibility state of the control.
     * @param isVisible true if the control is visible; false otherwise
     */
    public void setIsVisible( boolean isVisible );

    /**
     * Indicates whether the UI option should be present to hide or restore the control.
     * @return  true if the UI option to hide or restore the control should be present; 
     *          false otherwise.  Default value is true.
     */
    public boolean isHideable();

    /**
     * Sets whether the UI option should be present to hide or restore the control.
     * @param isHideable    true if the UI option to hide or restore the control should be present; 
     *          false otherwise.
     */
    public void setIsHideable( boolean isHideable );

}