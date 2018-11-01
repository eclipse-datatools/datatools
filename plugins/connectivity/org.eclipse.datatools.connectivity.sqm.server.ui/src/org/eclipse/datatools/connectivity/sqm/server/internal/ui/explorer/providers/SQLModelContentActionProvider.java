/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.INavigatorContentService;


public class SQLModelContentActionProvider extends CommonActionProvider {
	
	private TitleChangedPropertyDialogAction mPropertiesAction;
	private ICommonViewerSite mViewSite;
	private StructuredViewer mStructViewer;

	public SQLModelContentActionProvider() {
		super();
	}

	public void init(String anExtensionId, IViewPart aViewPart,
			INavigatorContentService aContentService,
			StructuredViewer aStructuredViewer) {
		mPropertiesAction = new TitleChangedPropertyDialogAction(
				mStructViewer.getControl().getShell(), 
				mViewSite.getSelectionProvider());
		mPropertiesAction.setText(ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.PROPERTIES")); //$NON-NLS-1$
	}

	public void dispose() {
	}

	public void setActionContext(ActionContext aContext) {
	}

	public void fillContextMenu(IMenuManager aMenu) {
		aMenu.add(new Separator("additions")); //$NON-NLS-1$
		aMenu.add(mPropertiesAction);
		return;
	}

	public void fillActionBars(IActionBars theActionBars) {
		return;
	}

	public void restoreState(IMemento aMemento) {
	}

	public void saveState(IMemento aMemento) {
	}

	public void init(ICommonActionExtensionSite aConfig) {
		this.mViewSite = aConfig.getViewSite();
		this.mStructViewer = aConfig.getStructuredViewer();

		mPropertiesAction = new TitleChangedPropertyDialogAction(
				mStructViewer.getControl().getShell(), 
				mViewSite.getSelectionProvider());
		mPropertiesAction.setText(ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.PROPERTIES")); //$NON-NLS-1$
		mPropertiesAction.setActionDefinitionId("org.eclipse.ui.file.properties"); //$NON-NLS-1$
	}
	
	class TitleChangedPropertyDialogAction extends PropertyDialogAction {
	
		/**
		 * fix for BZ 319449 to change the Properties dialog label from "Properties for"
		 * @param shell
		 * @param provider
		 * @return
		 */
		public TitleChangedPropertyDialogAction(Shell shell,
				ISelectionProvider provider) {
			super(shell, provider);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.dialogs.PropertyDialogAction#createDialog()
		 */
		public PreferenceDialog createDialog() {
			PreferenceDialog dialog = super.createDialog();
			dialog.getShell().setText(ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.PROPERTIES_DIALOG_TITLE"));//$NON-NLS-1$
			return dialog;
		}
		
	}

}
