/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Canvas;

/**
 * Consumers of Execution Plan View can extend this class intead of implementing <code>IPlanDrawer</code> from
 * scratch.
 * 
 * @author Dafan Yang
 */
public abstract class AbstractPlanDrawer implements IPlanDrawer
{
    protected Browser                _browser;
    protected Canvas                 _canvas;
    protected LightweightSystem      _lws;
    protected IExecutionPlanDocument _planDoc;

    /**
     * The invoker should call <code>setCanvas()</code> and <code>setBrowser()</code> after contruct a plan drawer
     * using this contructor.
     * 
     */
    public AbstractPlanDrawer()
    {
        _lws = new LightweightSystem(_canvas);
    }
    /**
     * Constructor
     * 
     * @param canvas the canvas, will be used to display graphic plan
     * @param browser the browser, will be used to display node detail information 
     */
    public AbstractPlanDrawer(Canvas canvas, Browser browser)
    {
        _canvas = canvas;
        _lws = new LightweightSystem(_canvas);
        _browser = browser;
    }

    public abstract void drawPlan(IExecutionPlanDocument planDoc);

    public abstract void init();

    public void setBrowser(Browser browser)
    {
        this._browser = browser;
    }

    public void setCanvas(Canvas canvas)
    {
        this._canvas = canvas;
    }
}
