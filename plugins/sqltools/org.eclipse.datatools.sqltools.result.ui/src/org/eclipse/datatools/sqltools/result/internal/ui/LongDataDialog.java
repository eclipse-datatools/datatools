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
package org.eclipse.datatools.sqltools.result.internal.ui;

import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * To display the super long data in result set.
 * 
 * @see org.eclipse.datatools.sqltools.result.internal.ui.viewer.ResultSetViewer#LONG_BOUNDARY
 * @author Dafan Yang
 */
public class LongDataDialog extends TrayDialog implements IContextProvider
{
    private String           _columnName;
    private String           _columnType;
    private Label            _columnNameNameLabel;
    private Label            _columnTypeNameLabel;
    private Label            _columnNameLabel;
    private Label            _columnTypeLabel;
    private Text             _contentText;
    private String           _content;
    private static final int CONTENT_WIDTH  = 400;
    private static final int CONTENT_HEIGHT = 250;
    
    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName());
    
    public IContext getContext(Object target)
    {
        return contextProviderDelegate.getContext(target);
    }

    public int getContextChangeMask()
    {
        return contextProviderDelegate.getContextChangeMask();
    }

    public String getSearchExpression(Object target)
    {
        return contextProviderDelegate.getSearchExpression(target);
    }

    public LongDataDialog(Shell parentShell, String columName, String columnType, String content)
    {
        super(parentShell);
        _columnName = columName == null ? "" : columName; //$NON-NLS-1$
        _columnType = columnType == null ? "" : columnType; //$NON-NLS-1$
        _content = content == null ? "" : content; //$NON-NLS-1$
    }

    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText(Messages.LongDataDialog_title); 
    }

    protected Control createDialogArea(Composite parent)
    {
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
//        HelpUtil.setHelp(parent, IHelpConstants.DIALOG_LONG_DATA);
        HelpUtil.setHelp(parent, "com.sybase.stf.dmp.asa.wizard_asa_udd");
        
        Composite parentComp = (Composite) super.createDialogArea(parent);

        Composite comp = new Composite(parentComp, SWT.NONE);
        comp.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        comp.setLayout(layout);

        Composite topComp = new Composite(comp, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        topComp.setLayout(layout);

        _columnNameNameLabel = new Label(topComp, SWT.NONE);
        _columnNameNameLabel.setText(Messages.LongDataDialog_columnName); 
        _columnNameLabel = new Label(topComp, SWT.NONE);
        _columnNameLabel.setText(_columnName);

        _columnTypeNameLabel = new Label(topComp, SWT.NONE);
        _columnTypeNameLabel.setText(Messages.LongDataDialog_datatype); 
        _columnTypeLabel = new Label(topComp, SWT.NONE);
        _columnTypeLabel.setText(_columnType.toUpperCase());

        Composite bottomComp = new Composite(comp, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        bottomComp.setLayout(layout);
        
        _contentText = new Text(bottomComp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData gd = new GridData();
        gd.widthHint = CONTENT_WIDTH;
        gd.heightHint = CONTENT_HEIGHT;
        _contentText.setLayoutData(gd);
        _contentText.setText(_content);
        return comp;
    }
}
