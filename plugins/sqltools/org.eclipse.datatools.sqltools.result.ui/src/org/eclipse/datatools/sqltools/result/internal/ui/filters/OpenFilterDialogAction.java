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
package org.eclipse.datatools.sqltools.result.internal.ui.filters;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Action to open the filters dialog
 * @author Dafan Yang
 */
public class OpenFilterDialogAction extends Action
{
    Shell         _shell;

    /**
     * Constructor
     * @param shell a shell
     */
    public OpenFilterDialogAction(Shell shell)
    {
        super();
        _shell = shell;
        setText(Messages.ResultView_filter); 
        setToolTipText(Messages.ResultView_filtertip); 
        setImageDescriptor(Images.DESC_RESULT_VIEW_FILTER);
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_OPEN_FILTER_DIALOG, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        ResultsFilterDialog dialog = new ResultsFilterDialog(_shell);
        dialog.open();
    }
}
