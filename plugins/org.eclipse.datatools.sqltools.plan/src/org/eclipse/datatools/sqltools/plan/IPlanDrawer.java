/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Canvas;

/**
 * Class that implements this interface can draw a sub-execution plan on a <code>Canvas</code>. Vendor can extend
 * <code>AbstractPlanDrawer</code> intead of implementing this interfact from scratch.
 * 
 * @see AbstractPlanDrawer
 * @author Dafan Yang
 */
public interface IPlanDrawer
{
    /**
     * Draws the given execution plan
     * 
     * @param document the exeuction plan
     */
    public void drawQuery(IExecutionPlanDocument document);

    /**
     * Initializes the drawer, this method will be invoked after the drawer is constructed
     *
     */
    public void init();

    /**
     * Sets the browser on which the detail information of one plan node is displayed
     * 
     * @param browser the browser
     */
    public void setBrowser(Browser browser);

    /**
     * Sets the canvas on which the graphic plan is drawn
     * 
     * @param canvas the canvas
     */
    public void setCanvas(Canvas canvas);
}
