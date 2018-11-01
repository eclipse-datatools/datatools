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
package org.eclipse.datatools.sqltools.result.internal.ui.export.actions;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.export.TextOutputter;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Print the result set object
 * 
 * @author Dafan Yang
 */
public class PrintResultSetAction extends Action
{
    IResultSetObject            _result;
    IResultInstance             _resultInstance;
    Shell                       _shell;
    Composite                   _parent;
    private static final String FONT_STYLE = "Courier New";
    private static final int    FONT_SIZE  = 10;
    
    public PrintResultSetAction(IResultSetObject result, Composite parent)
    {
        super(Messages.ResultSetAction_Title); 
        this._shell = parent.getShell();
        _result = result;
        _parent = parent;
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_PRINT_RESULTSET, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }
    
    public PrintResultSetAction(IResultInstance resultInstance, Composite parent)
    {
        super(Messages.AllResultSetAction_Title); 
        this._shell = parent.getShell();
        _resultInstance = resultInstance;
        _parent = parent;
    }

    public void run()
    {
        PrintDialog pdlg = new PrintDialog(_shell, SWT.NONE);
        PrinterData data = pdlg.open();
        if(data != null)
        {
            Printer printer = new Printer(data);
            StyledText styledText = new StyledText(_parent, SWT.NONE);
            styledText.setVisible(false);
            TextOutputter outputter = new TextOutputter();
            String printString = "";
            if(_result != null)
            {
                printString = outputter.getPrintString(_result);
            }
            else if(_resultInstance != null)
            {
                printString = outputter.getPrintString(_resultInstance);
            }
            else
            {
                // should not happen
                printer.dispose();
                return;
            }
            FontData fd = new FontData(FONT_STYLE, FONT_SIZE, SWT.NORMAL);
            Font font = new Font(styledText.getDisplay(), fd);
            styledText.setFont(font);
            styledText.setText(printString);
            styledText.print(printer).run();
            printer.dispose();
            font.dispose();
        }
    }
}
