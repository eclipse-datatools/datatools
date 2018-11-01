/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 *				brianf - updated for command handler
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ImportProfilesDialog;
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
public class ImportProfileViewAction extends Action 
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
	public ImportProfileViewAction() {
		super();
		setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ServersView.action.importCPs")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		this.shell = view.getSite().getShell();
	}

	/**
	 * Initialize the shell for use as the parent shell of the action's dialog. 
	 * Use this method when the action is extended to run without being associated 
	 * with a view.
	 * @param parentShell
	 */
	public void init( Shell parentShell )
	{
	    shell = parentShell;
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

		final ImportProfilesDialog dlg = new ImportProfilesDialog(shell);
		int ret = dlg.open();
		if (ret == Window.OK) {
			final IConnectionProfile[] profiles = dlg.getProfiles();
			if (profiles == null) {
				ExceptionHandler.showException(shell,
						ConnectivityUIPlugin.getDefault().getResourceString(
								"dialog.title.error"), dlg.getException() //$NON-NLS-1$
								.getMessage(), dlg.getException());
				return null;
			}
			// Check to see if we need to import these into another repo
			if (dlg.getUseLocalRepository()) {
				BusyIndicator.showWhile(shell.getDisplay(), new Runnable() {

					public void run() {
						ProfileManager manager = ProfileManager.getInstance();
						try {
							for (int i = 0; i < profiles.length; i++) {
								String path = ProfileManager.getInstance().getProfilePath(profiles[i]);
								IConnectionProfile foundProfile = ProfileManager.getInstance().getProfileByFullPath(path);
								if (foundProfile != null) {
									if (dlg.isOverwritten()) {
										((ConnectionProfile) profiles[i]).migrate();
										manager.modifyProfile(profiles[i]);
									}								}
								else {
									((ConnectionProfile) profiles[i]).migrate();
									manager.addProfile(profiles[i]);
								}
							}
						}
						catch (ConnectionProfileException e) {
							ExceptionHandler.showException(shell,
									ConnectivityUIPlugin.getDefault()
											.getResourceString(
													"dialog.title.error"), e //$NON-NLS-1$
											.getMessage(), e);
						}
					}
				});
			}
			else {
				BusyIndicator.showWhile(shell.getDisplay(), new Runnable() {

					public void run() {
						try {
							IConnectionProfile repoProfile = dlg
									.getSelectedRepository();
							IConnectionProfileRepository repo = (IConnectionProfileRepository) repoProfile
									.getManagedConnection(
											IConnectionProfileRepository.class
													.getName()).getConnection()
									.getRawConnection();
							for (int i = 0; i < profiles.length; i++) {
								if (!repo.supportsProfileType(profiles[i]
										.getProviderId())
										|| !((ConnectionProfileProvider) profiles[i]
												.getProvider())
												.compatibleWithRepository(repoProfile)) {
									continue;
								}
								String path = ProfileManager.getInstance().getProfilePath(profiles[i]);
								path = repo.getRepositoryProfile().getName() + InternalProfileManager.PROFILE_PATH_SEPARATOR + path;
								IConnectionProfile foundProfile = ProfileManager.getInstance().getProfileByFullPath(path);
								if (foundProfile == null) {
									((ConnectionProfile) profiles[i]).migrate();
									repo.addProfile(profiles[i]);
								}
								else if (dlg.isOverwritten()) {
									((ConnectionProfile) profiles[i]).migrate();
									repo.modifyProfile(profiles[i]);
								}
							}
						}
						catch (ConnectionProfileException e) {
							ExceptionHandler.showException(shell,
									ConnectivityUIPlugin.getDefault()
											.getResourceString(
													"dialog.title.error"), e //$NON-NLS-1$
											.getMessage(), e);
						}
					}
				});
			}
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
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		try {
			execute(new ExecutionEvent());
		} catch (final ExecutionException e) {
			// TODO Do something meaningful and poignant.
		}
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
     * @return
     */
    public boolean isCompleted()
    {
        return isCompleted;
    }

	/**
	 * @param handlerEvent
	 */
	protected void fireHandlerChanged(final HandlerEvent handlerEvent) {
		if (handlerEvent == null) {
			throw new NullPointerException();
		}
		if (listenerList == null)
			return;

		final Object[] listeners = listenerList.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final IHandlerListener listener = (IHandlerListener) listeners[i];
			listener.handlerChanged(handlerEvent);
		}
	}
}