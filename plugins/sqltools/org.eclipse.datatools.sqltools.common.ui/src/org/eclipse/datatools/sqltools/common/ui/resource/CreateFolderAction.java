/*******************************************************************************
 * Copyright (c) 2005 -- 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.sqltools.common.ui.resource;

import java.util.Iterator;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.datatools.sqltools.common.ui.internal.IHelpContextIds;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.ui.dialogs.WizardNewFolderMainPage;
import org.eclipse.ui.wizards.newresource.BasicNewFolderResourceWizard;


/**
 * Standard action for creating a folder resource within the currently selected folder or project.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 * @author Hui Cao
 */
public class CreateFolderAction extends SelectionListenerAction
{

    /**
     * The id of this action.
     */
    public static final String ID = PlatformUI.PLUGIN_ID + ".CreateFolderAction"; //$NON-NLS-1$

    /**
     * The shell in which to show any dialogs.
     */
    private Shell              _shell;

    /**
     * The tree viewer to update
     */
    private TreeViewer         _viewer;

    /**
     * Creates a new action for creating a folder resource.
     * 
     * @param shell the shell for any dialogs
     *  
     */
    public CreateFolderAction(TreeViewer viewer)
    {
        super(Messages.CreateFolderAction_text); //$NON-NLS-1$
        if (viewer == null)
        {
            throw new IllegalArgumentException();
        }
        this._viewer = viewer;
        this._shell = viewer.getControl().getShell();
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER));
        setToolTipText(Messages.CreateFolderAction_toolTip); //$NON-NLS-1$
        setId(ID);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IHelpContextIds.CREATE_FOLDER_ACTION);
    }

    /**
     * The <code>CreateFolderAction</code> implementation of this <code>IAction</code> method opens a
     * <code>BasicNewFolderResourceWizard</code> in a wizard dialog under the shell passed to the constructor.
     */
    public void run()
    {
        final BasicNewFolderResourceWizard wizard = new BasicNewFolderResourceWizard();
        wizard.init(PlatformUI.getWorkbench(), getStructuredSelection());
        wizard.setNeedsProgressMonitor(true);
        WizardDialog dialog = new WizardDialog(_shell, wizard);
        dialog.create();
        dialog.getShell().setText(Messages.CreateFolderAction_title); //$NON-NLS-1$
        PlatformUI.getWorkbench().getHelpSystem().setHelp(dialog.getShell(), IHelpContextIds.NEW_FOLDER_WIZARD);
        if (dialog.open() != Window.CANCEL)
        {
            //views can implement ISetSelectionTarget but dialogs have to update and reveal resource by themselves.
            _shell.getDisplay().asyncExec(new Runnable()
            {
                public void run()
                {
                    //BasicNewFolderResourceWizard has only one page. createNewFolder() will return the created one or
                    // null.
                    IFolder folder = ((WizardNewFolderMainPage) wizard.getStartingPage()).createNewFolder();
                    if (folder != null)
                    {
                        StructuredSelection ssel = new StructuredSelection(folder);
                        _viewer.getControl().setRedraw(false);
                        _viewer.add(folder.getParent(), folder);
                        _viewer.setSelection(ssel, true);
                        _viewer.getControl().setRedraw(true);
                    }
                }
            }
            );
        }

    }

    /**
     * The <code>CreateFolderAction</code> implementation of this <code>SelectionListenerAction</code> method
     * enables the action only if the selection contains folders and open projects.
     */
    protected boolean updateSelection(IStructuredSelection s)
    {
        if (!super.updateSelection(s))
        {
            return false;
        }
        Iterator resources = getSelectedResources().iterator();
        while (resources.hasNext())
        {
            IResource resource = (IResource) resources.next();
            if (!resourceIsType(resource, IResource.PROJECT | IResource.FOLDER) || !resource.isAccessible())
            {
                return false;
            }
        }
        return true;
    }
}
