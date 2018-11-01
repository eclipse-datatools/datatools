/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.dialogs;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.datatools.connectivity.ui.dse.DSEPlugin;
import org.eclipse.datatools.connectivity.ui.dse.IHelpContextsConnectivityUIDSE;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog to allow others to select a profile.
 * @author brianf
 */
public class ProfileSelectionDialog extends TitleAreaDialog 
	implements IContextProvider {

    private String _profile;
    private String _category;
	private boolean _limitToProfiles = true;
	private boolean mShowNew = true;
	private boolean mShowConnect = true;
	private boolean mShowSelectButtons = false;
    
	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(DSEPlugin.getDefault().getBundle().getSymbolicName());

	private ProfileSelectionComposite composite = null;

    /**
     * Constructor
     * @param parentShell
     */
    public ProfileSelectionDialog(Shell parentShell) {
        super(parentShell);
        setShellStyle( getShellStyle() | SWT.RESIZE );
        parentShell.setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parentShell, HelpUtil.getContextId(IHelpContextsConnectivityUIDSE.PROFILE_SELECTION_DIALOG, DSEPlugin.getDefault().getBundle().getSymbolicName()));
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell)
    {
        super.configureShell(shell);
        shell.setText(DSEPlugin.getDefault().getResourceString("ProfileSelectionDialog.Dialog.Title")); //$NON-NLS-1$
    }

	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
	protected Control createDialogArea(Composite parent) {
        setTitle(DSEPlugin.getDefault().getResourceString("ProfileSelectionDialog.Title")); //$NON-NLS-1$
        setMessage(DSEPlugin.getDefault().getResourceString("ProfileSelectionDialog.Message")); //$NON-NLS-1$

        Composite container = new Composite(parent, SWT.NULL);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.horizontalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        container.setLayout(gridLayout);
        
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        composite = new ProfileSelectionComposite (container, SWT.NONE, _category, _limitToProfiles, this.mShowNew, this.mShowConnect, this.mShowSelectButtons);
        composite.setCPName(_profile);
        
        fillInDefaultValues();
        
        if (!mShowConnect)
        	composite.getPage().getNavigatorViewer().expandAll();

		return container;
    }
	
	protected ProfileSelectionComposite getComposite() {
		return this.composite;
	}

	public void fillInDefaultValues()
    {
		getComposite().fillInDefaultValues();
   		initListeners();
   		validate();
    }

    private void initListeners() {
    	getComposite().addChangeListener(new MyChangeListener());
    }
	/**
	 * Returns the profile name
	 * @return
	 */
	public String getCPName() {
		return this._profile;
	}
	public void setCPName ( String input ) {
		this._profile = input;
	}
	
	public String getCategoryName() {
		return this._category;
	}
	public void setCategoryName ( String input ) {
		this._category = input;
	}

	public void setLimitToProfiles ( boolean flag ) {
		this._limitToProfiles = flag;
	}
	public boolean getLimitToProfiles() {
		return this._limitToProfiles;
	}

	public void setShowNew ( boolean flag ) {
		this.mShowNew = flag;
	}
	public boolean getShowNew () {
		return this.mShowNew;
	}
	public void setShowConnect ( boolean flag ) {
		this.mShowConnect = flag;
	}
	public boolean getShowConnect() {
		return this.mShowConnect;
	}
	public void setShowSelectButtons ( boolean flag ) {
		this.mShowSelectButtons = flag;
	}
	public boolean getShowSelectButtons() {
		return this.mShowSelectButtons;
	}

	protected void validate() {
	    Runnable torun = new Runnable() {
            public void run() {
        		boolean hasProfile = false;
        		if (_profile != null && _profile.trim().length() > 0) {
        			hasProfile = true;
        		}
        		Button btn = getButton(IDialogConstants.OK_ID);
        		if (btn != null && hasProfile) {
        			btn.setEnabled(true);
        		}
        		else if (btn != null)
        			btn.setEnabled(false);
            }		        
	    };
	    Display.getDefault().asyncExec(torun);
	}

	public boolean close() {
		this.getComposite().dispose();
		return super.close();
	}
	
	private class MyChangeListener implements ChangeListener {

		public void stateChanged(ChangeEvent arg0) {
			ProfileSelectionDialog.this._profile = ProfileSelectionDialog.this.getComposite().getCPName();
		}
		
	}

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

}