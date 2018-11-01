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
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.Parameter;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.UIUtil;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Parent class of <code>SingleWindowTextSection</code> and <code>SingleWindowGridSection</code>
 * 
 * @author Dafan Yang
 */
abstract public class SingleWindowModeSection extends ResultSection
{
    protected Composite _composite;
    protected boolean   _isResultHid;
    /* In multiple threads environment, onInstanceFinished may be invoked twice. We use this flag to prevent */
    protected boolean   _isLabelCreated = false;
    
    public SingleWindowModeSection(Composite composite, IResultInstance instance, ResultsViewControl resultsViewControl)
    {
        super(instance, resultsViewControl);
        _parent = composite;
        createInitialControl(composite);
    }

    public SingleWindowModeSection(Composite composite, ResultsViewControl resultsViewControl)
    {
        super( null, resultsViewControl);
        _parent = composite;
        createInitialControl(composite);
    }

    /**
     * Creates the initial static tabs
     * @param composite the parent composite
     */
    protected void createInitialControl(Composite composite)
    {
        _composite = new Composite(composite, SWT.NONE | SWT.BORDER);

        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;

        _composite.setLayout(layout);
    }

    public void showDetail(IResultInstance instance)
    {
        super.showDetail(instance);
        if (instance == _resultInstance)
        {
            return;
        }
        
        // Clear the status line
        if (_resultsViewControl.getView() != null){
        	_resultsViewControl.getView().clearStatusLine();
        }
    	// Generate new status message 
    	_resultsViewControl.refreshResults();
        
        _resultInstance = instance;
        createViewerForResultInstance(instance);
    }

    public Composite getControl()
    {
        return _composite;
    }

    abstract protected void createViewerForResultInstance(IResultInstance instance);

    public void onInstanceFinished()
    {
        //create a label to display warning information if some result's rows are hidden
        if (_isResultHid && !_isLabelCreated)
        {
            _isLabelCreated = true;
            Composite warnComp = new Composite(_composite, SWT.NONE);
            GridLayout layout = new GridLayout();
            layout.marginHeight = 0;
            layout.marginWidth = 0;
            layout.numColumns = 2;
            warnComp.setLayout(layout);
            GridData gd = new GridData();
            gd.grabExcessHorizontalSpace = true;
            gd.grabExcessVerticalSpace = false;
            gd.heightHint =  UIUtil.convertHeightInCharsToPixels(1, _parent);
            warnComp.setLayoutData(gd);

            Label warnIcon = new Label(warnComp, SWT.NONE);
            warnIcon.setImage(Images.get(Images.IMG_RESULT_VIEW_WARN));

            Label label = new Label(warnComp, SWT.NONE);
            label.setText(Messages.ResultSection_warnning_rowshidden); 
            
            _composite.layout(true);
        }
    }

    public void onParametersShown(List params)
    {
        // will display the parameters at the end
        return;        
    }
    
    /**
     * Returns a valid parameter list (May contain invalid item in the list)
     * @param params the parameter list
     * @return a valid parameter list
     */
    protected List getValidParamList(List params)
    {
        if (params == null)
        {
            return new ArrayList();
        }
        Iterator iter = params.iterator();
        ArrayList newList = new ArrayList();
        while (iter.hasNext())
        {
            // only Parameter type is accepted
            Object obj = iter.next();
            if (obj != null && obj instanceof Parameter)
            {
                newList.add(obj);
            }
        }
        return newList;
    }
}
