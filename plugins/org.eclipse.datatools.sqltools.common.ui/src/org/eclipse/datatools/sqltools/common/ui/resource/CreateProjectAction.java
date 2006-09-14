/**
 *Confidential Property of Sybase, Inc.
 *(c) Copyright Sybase, Inc. 2004-2006.
 *All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.resource;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.actions.NewProjectAction;


/**
 * @author Zhi-hong(Bryan) Yang
 */
public class CreateProjectAction extends NewProjectAction
{
    private TreeViewer _viewer;

    /**
     * @param tree
     */
    public CreateProjectAction(TreeViewer tree)
    {
        super();
        this.setText(Messages.ContainerSelectionGroup_createProjectAction_label);
        this._viewer = tree;
        tree.getTree().setFocus();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        //remember the old items for comparasion
        TreeItem[] items = ((Tree) _viewer.getControl()).getItems();
        IProject ops[] = new IProject[items == null? 0: items.length];
        for (int i = 0; i < ops.length; i++)
        {
            ops[i] = (IProject)items[i].getData();
        }
        super.run();

        //views can implement ISetSelectionTarget but dialogs have to update and reveal resource by themselves.
        this._viewer.refresh();
        TreeItem[] nitems = ((Tree) _viewer.getControl()).getItems();
        IProject nps[] = new IProject[nitems == null? 0: nitems.length];
        for (int i = 0; i < nps.length; i++)
        {
            nps[i] = (IProject)nitems[i].getData();
        }
        for (int i = 0; i < nps.length; i++)
        {
            boolean found = false;
            for (int j = 0; j < ops.length; j++)
            {
                if (ops[j].equals(nps[i]))
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                final IProject project = nps[i];
                if (project != null)
                {
                    _viewer.getControl().getShell().getDisplay().asyncExec(new Runnable()
                    {
                        public void run()
                        {
                            StructuredSelection ssel = new StructuredSelection(project);
                            _viewer.getControl().setRedraw(false);
                            _viewer.setSelection(ssel, true);
                            _viewer.getControl().setRedraw(true);
                        }
                    }
                    );
                }
                break;
            }
        }
    }

}
