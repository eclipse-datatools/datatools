/*******************************************************************************
 * Copyright (c) 2005, 2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: 
 *  shongxum - initial API and implementation
 *  Actuate Corporation - refactored to improve extendability
 * 	brianf - added command handler code
 *  Actuate Corporation - added the cipherProvider extension point [BZ 358686]
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.security.SecurityManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog;
import org.eclipse.datatools.connectivity.security.ICipherProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Ideally, this class should be split into two, one is for Action, the other
 * is for View Action.
 * 
 * @author shongxum & brianf
 */
public class ExportProfileViewAction extends Action 
	implements IHandler, IViewActionDelegate {

	/**
	 * A collection of objects listening to changes to this manager. This
	 * collection is <code>null</code> if there are no listeners.
	 */
	private transient ListenerList listenerList = null;

	protected boolean isCompleted = false;
	private Shell shell;

	/**
	 * Constructor
	 */
	public ExportProfileViewAction() {
		super();
		setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ServersView.action.exportCPs")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#addHandlerListener(org.eclipse.core.commands.IHandlerListener)
	 */
	public final void addHandlerListener(final IHandlerListener listener) {
		if (listenerList == null) {
			listenerList = new ListenerList(ListenerList.IDENTITY);
		}

		listenerList.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#dispose()
	 */
	public final void dispose() {
		listenerList = null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = HandlerUtil.getActivePart(event);
		if (part == null && shell == null) 
			return null;
		else if (part instanceof IViewPart)
			init((IViewPart)part);

		isCompleted = false;    // reset state
		final ExportProfilesDialog dlg = createExportProfilesDialog( shell );
		int ret = dlg.open();
		if (ret == Window.OK) {
			BusyIndicator.showWhile(shell.getDisplay(), 
			        createSaveExportedProfilesRunnable( shell, dlg ));
		}
		fireHandlerChanged(new HandlerEvent(this, false, false));
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#removeHandlerListener(org.eclipse.core.commands.IHandlerListener)
	 */
	public void removeHandlerListener(IHandlerListener handlerListener) {
		if (listenerList != null) {
			listenerList.remove(handlerListener);

			if (listenerList.isEmpty()) {
				listenerList = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		shell = view.getSite().getShell();
	}

	/**
	 * Initialize the shell for use as the parent shell of the action's dialog. 
	 * Use this method when the action is extended to run without being associated 
	 * with a view.
	 * @param parentShell
	 */
	protected void init( Shell parentShell )
	{
	    shell = parentShell;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		try {
			execute(new ExecutionEvent());
		} catch (final ExecutionException e) {
			// TODO Do something meaningful and poignant.
		}
	}

	/**
	 * @param parentShell
	 * @return
	 */
	protected ExportProfilesDialog createExportProfilesDialog( Shell parentShell )
	{
	    return new ExportProfilesDialog( parentShell );
	}
	
	/**
	 * Returns a new runnable that saves the profiles selected in the
	 * specified ExportProfilesDialog.
	 * @param parentShell  the parent shell for display of exception message
	 * @param dlg  an instance of ExportProfilesDialog or its subclass.
	 * @return a new runnable for saving exported profiles
	 */
    protected Runnable createSaveExportedProfilesRunnable( 
            final Shell parentShell, 
            final ExportProfilesDialog dlg )
    {
        return new Runnable() {
        	public void run() {
                isCompleted = false;    // reset state
        		try {
        			ICipherProvider isp = null;
        			if (dlg.needEncryption()) {
        				isp = SecurityManager.getInstance()
                                .getCipherProvider( dlg.getFile() );
        			}
        			ConnectionProfileMgmt.saveCPs(
        					dlg.getSelectedProfiles(), dlg.getFile(), isp);
        			isCompleted = true;
        		}
        		catch (Exception e) {
        			ExceptionHandler.showException( parentShell, ConnectivityUIPlugin
        					.getDefault().getResourceString(
        							"dialog.title.error"), e //$NON-NLS-1$
        					.getMessage(), e);
        		}
        	}
        };
    }

    /**
     * @return
     */
    public boolean isCompleted()
    {
        return isCompleted;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * @param handlerEvent
	 */
	protected void fireHandlerChanged(final HandlerEvent handlerEvent) {
		if (handlerEvent == null) {
			throw new NullPointerException();
		}
		if( listenerList == null )
		    return;

		final Object[] listeners = listenerList.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final IHandlerListener listener = (IHandlerListener) listeners[i];
			listener.handlerChanged(handlerEvent);
		}
	}
}