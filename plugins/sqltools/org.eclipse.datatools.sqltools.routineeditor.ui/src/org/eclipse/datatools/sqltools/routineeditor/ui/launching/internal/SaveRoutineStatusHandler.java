/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui.launching.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.Messages;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchPartLabelProvider;

/**
 * @author Hui Cao
 */
public class SaveRoutineStatusHandler implements IStatusHandler {
	
    /**
     * String preference controlling whether editors are saved before launching.
     * Valid values are either "always", "never", or "prompt".
     * If "always" or "never", launching will save editors (or not) automatically.
     * If "prompt", the user will be prompted each time.
     * 
     * @since 1.6
     */
	public static final String PREF_SAVE_DIRTY_EDITORS_BEFORE_LAUNCH = IDebugUIConstants.PLUGIN_ID + ".save_dirty_editors_before_launch"; //$NON-NLS-1$
	/**
	 * Provides a custom class for a resizable selection dialog with a don't ask again button on it
	 * @since 3.2
	 */
	class ScopedResourcesSelectionDialog extends ListSelectionDialog {

		private final String SETTINGS_ID = IDebugUIConstants.PLUGIN_ID + ".SCOPED_SAVE_SELECTION_DIALOG"; //$NON-NLS-1$
		//TODO this works in 3.3, needs to provide context help for this ID in 3.2
		private final String SELECT_RESOURCES_TO_SAVE_DIALOG = IDebugUIConstants.PLUGIN_ID + "." + "select_resources_to_save_dialog";
		Button fSavePref;
		
		public ScopedResourcesSelectionDialog(Shell parentShell, Object input, IStructuredContentProvider contentProvider, ILabelProvider labelProvider, String message) {
			super(parentShell, input, contentProvider, labelProvider, message);
			setShellStyle(getShellStyle() | SWT.RESIZE);
		}
		
		protected Control createDialogArea(Composite parent) {
			Composite ctrl = (Composite) super.createDialogArea(parent);
			fSavePref = new Button(ctrl, SWT.CHECK);
			fSavePref.setText(Messages.SaveScopeResourcesHandler_1);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(ctrl, SELECT_RESOURCES_TO_SAVE_DIALOG);
			return ctrl;
		}
		
		protected void okPressed() {
			String val = (fSavePref.getSelection() ? MessageDialogWithToggle.ALWAYS : MessageDialogWithToggle.PROMPT);
            IEclipsePreferences prefs = ((IScopeContext) new InstanceScope()).getNode(IDebugUIConstants.PLUGIN_ID);
            prefs.put(PREF_SAVE_DIRTY_EDITORS_BEFORE_LAUNCH, val);
			super.okPressed();
		}

		protected IDialogSettings getDialogBoundsSettings() {
			IDialogSettings settings = RoutineEditorUIActivator.getDefault().getDialogSettings();
			IDialogSettings section = settings.getSection(SETTINGS_ID);
			if (section == null) {
				section = settings.addNewSection(SETTINGS_ID);
			} 
			return section;
		}
	}
	
	class SaveProgressMonitor extends NullProgressMonitor {
		private boolean isDone = false;
	
		public void done() {
			super.done();
			isDone = true;
		}
		
		public boolean isDone()
		{
			return isDone;
		}
	}
	
	/**
	 * The objects to save (if any)
	 */
	Object[] fSaves = null;
	
	/* (non-Javadoc)
	 * 
	 * Source object is an array - a launch configuration and an array of projects to save resources for.
	 * 
	 * @see org.eclipse.debug.core.IStatusHandler#handleStatus(org.eclipse.core.runtime.IStatus, java.lang.Object)
	 */
	public Object handleStatus(IStatus status, Object source) throws CoreException {
		IConnectionProfile profile = null; 
		IEclipsePreferences prefs = ((IScopeContext) new InstanceScope()).getNode(IDebugUIConstants.PLUGIN_ID);
		final String save = prefs.get(PREF_SAVE_DIRTY_EDITORS_BEFORE_LAUNCH, MessageDialogWithToggle.PROMPT);
        if (source != null) {
        	if (source instanceof ProcIdentifier)
        	{
        		try {
        			profile = ProfileUtil.getProfile(((ProcIdentifier)source).getProfileName());
        		} catch (NoSuchProfileException e) {
        		}
        	}
        	else if (source instanceof IConnectionProfile)
        	{
        		profile = (IConnectionProfile)source;
        	}
        }
        if (profile != null)
        {
            final int result[] = new int[]{IDialogConstants.OK_ID};
            final IConnectionProfile p = profile;
			SQLEditorPlugin.getStandardDisplay().syncExec(new Runnable(){
				public void run() {
					result[0] = showSaveDialog(p, !save.equals(MessageDialogWithToggle.NEVER), save.equals(MessageDialogWithToggle.PROMPT));
				}
			});            
            if(result[0] == IDialogConstants.OK_ID) {
            	doSave();
            	return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } 
        else {
        	
    		if (save.equals(MessageDialogWithToggle.NEVER)) {
    			return Boolean.TRUE;
    		} 
    		if (RoutineEditorUIActivator.getDefault().getWorkbench().getActiveWorkbenchWindow() == null) {
    			return Boolean.FALSE;
    		}
    		boolean cancel = PlatformUI.getWorkbench().saveAllEditors(MessageDialogWithToggle.PROMPT.equals(save));
            return Boolean.valueOf(cancel);
        }
    }
	
	/**
	 * 
	 * Builds the list of editors that apply to this build that need to be saved
	 * 
	 * @param profile the profile involved in this build, used to scope the searching process
	 * @return the list of dirty editors for this launch to save, never null
	 */
	protected IEditorPart[] getScopedDirtyEditors(IConnectionProfile targetProfile) {
		List dirtyparts = new ArrayList();
		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
		for(int l = 0; l < windows.length; l++) {
			IWorkbenchPage[] pages = windows[l].getPages();
			for(int i = 0; i < pages.length; i++) {
				IEditorPart[] eparts = pages[i].getDirtyEditors();
				for(int j = 0; j < eparts.length; j++) {
					IConnectionProfile profile = (IConnectionProfile)eparts[j].getEditorInput().getAdapter(IConnectionProfile.class);
					if(profile != null) {
						if(targetProfile.equals(profile) & !dirtyparts.contains(eparts[j])) {
							dirtyparts.add(eparts[j]);
						}
					}
				}
			}
		}
		return (IEditorPart[])dirtyparts.toArray(new IEditorPart[dirtyparts.size()]);
	}
	
	/**
	 * Performs the save of the editor parts returned by getScopedResources
	 */
	protected void doSave() {
		if(fSaves != null) {
			for (int i = 0; i < fSaves.length; i++) {
				//we must wait for all editors are saved and refreshed before we can get the correct parameter configuration
				final SaveProgressMonitor monitor = new SaveProgressMonitor();
				final IEditorPart part = ((IEditorPart)fSaves[i]);
				SQLEditorPlugin.getStandardDisplay().syncExec(new Runnable(){
					public void run() {
						part.doSave(monitor);
					}
				});
				
				while (!monitor.isDone() && !monitor.isCanceled())
				{
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
	
	/**
	 * show the save dialog with a list of editors to save (if any)
	 * The dialog is also not shown if the the preference for automatically saving dirty before launch is set to always
	 * @param profile the connection profile to consider for the save
	 * @param save if we should save
	 * @param prompt if we should prompt to save or do it automatically
	 * @return the dialog status, to be propagated back to the <code>handleStatus</code> method
	 */
	protected int showSaveDialog(IConnectionProfile profile, boolean save, boolean prompt) {
		if(save) {
			IEditorPart[] editors = getScopedDirtyEditors(profile);
			if(prompt && (editors.length > 0)) {
				final int result[] = new int[]{IDialogConstants.OK_ID};
				final ScopedResourcesSelectionDialog lsd = new ScopedResourcesSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						new AdaptableList(editors),
						new WorkbenchContentProvider(),
						new WorkbenchPartLabelProvider(),
						Messages.SaveScopeResourcesHandler_2);
				lsd.setInitialSelections(editors);
				lsd.setTitle(Messages.SaveScopeResourcesHandler_3);
				result[0] = lsd.open();
				if( result[0] == IDialogConstants.CANCEL_ID) {
					return IDialogConstants.CANCEL_ID;
				}
				fSaves = lsd.getResult();
			}
			else {
				fSaves = editors;
			}
		}
		return IDialogConstants.OK_ID;
	}

}
