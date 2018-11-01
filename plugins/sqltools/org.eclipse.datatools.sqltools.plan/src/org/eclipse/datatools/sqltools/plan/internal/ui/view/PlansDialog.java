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
package org.eclipse.datatools.sqltools.plan.internal.ui.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * Dialog that shows a list of items with icon and label.
 * @author Hui Cao
 */
public class PlansDialog extends SelectionDialog
{

    private static final int _REMOVE_ID           = IDialogConstants.CLIENT_ID + 1;
    private static final int _WIDTH_IN_CHARACTERS = 55;

    private List             _fInput;
    private TableViewer      _fViewer;

    private static final class PlansLabelProvider extends LabelProvider
    {

        private ArrayList _fImages = new ArrayList();

        public String getText(Object element)
        {
            String label = ((IPlanInstance) element).getPlanRequest().getDatabaseDefinitionId() + ": "
                    + ((IPlanInstance) element).getPlanRequest().getSql();
            if (label.length() > _WIDTH_IN_CHARACTERS)
            {
                label = label.substring(0, _WIDTH_IN_CHARACTERS - 3) + " ...";
            }

            return label;
        }

        public Image getImage(Object element)
        {

            ImageDescriptor imageDescriptor = Images.DESC_SHOWPLAN;
            if (imageDescriptor == null)
            {
                return null;
            }

            Image image = imageDescriptor.createImage();
            _fImages.add(image);

            return image;
        }

        public void dispose()
        {
            Iterator iter = _fImages.iterator();
            while (iter.hasNext())
                ((Image) iter.next()).dispose();

            _fImages = null;
        }
    }

    public PlansDialog(Shell parent, List input)
    {
        super(parent);
        setTitle(Messages.getString("PlansDialog.title")); //$NON-NLS-1$
        setMessage(Messages.getString("PlansDialog.message")); //$NON-NLS-1$
        _fInput = input;
    }

    /*
     * Overrides method from Dialog
     */
    protected Label createMessageArea(Composite composite)
    {
        Label label = new Label(composite, SWT.WRAP);
        label.setText(getMessage());
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = convertWidthInCharsToPixels(_WIDTH_IN_CHARACTERS);
        label.setLayoutData(gd);
        applyDialogFont(label);
        return label;
    }

    /*
     * Overrides method from Dialog
     */
    protected Control createDialogArea(Composite container)
    {
        Composite ancestor = (Composite) super.createDialogArea(container);

        createMessageArea(ancestor);

        Composite parent = new Composite(ancestor, SWT.NONE);

        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        parent.setLayout(layout);

        _fViewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        _fViewer.setContentProvider(new ArrayContentProvider());

        _fViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                getButton(_REMOVE_ID).setEnabled(!event.getSelection().isEmpty());
            }
        });

        final Table table = _fViewer.getTable();
        table.addMouseListener(new MouseAdapter()
        {
            public void mouseDoubleClick(MouseEvent e)
            {
                okPressed();
            }
        });
        _fViewer.setLabelProvider(new PlansLabelProvider());
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.heightHint = convertHeightInCharsToPixels(15);
        gd.widthHint = convertWidthInCharsToPixels(_WIDTH_IN_CHARACTERS);
        table.setLayoutData(gd);

        Button button = createButton(parent, _REMOVE_ID, Messages.getString("PlansDialog.remove"), false); //$NON-NLS-1$
        ((GridData) button.getLayoutData()).verticalAlignment = GridData.BEGINNING;

        applyDialogFont(ancestor);

        // set input & selections last, so all the widgets are created.
        _fViewer.setInput(_fInput);
        List initialSelection = getInitialElementSelections();
        if (initialSelection != null)
            _fViewer.setSelection(new StructuredSelection(initialSelection));

        return table;
    }

    protected void buttonPressed(int buttonId)
    {
        if (buttonId == _REMOVE_ID)
        {
            IStructuredSelection selection = (IStructuredSelection) _fViewer.getSelection();
            Iterator instances = selection.iterator();
            while (instances.hasNext())
            {
                IPlanInstance inst = (IPlanInstance) instances.next();
                PlanViewPlugin.getPlanManager().removePlanInstance(inst);
                _fInput.remove(inst);
            }
            _fViewer.refresh();
            return;
        }
        super.buttonPressed(buttonId);
    }

    /*
     * Overrides method from Dialog
     */
    protected void okPressed()
    {
        // Build a list of selected children.
        ISelection selection = _fViewer.getSelection();
        if (selection instanceof IStructuredSelection)
            setResult(((IStructuredSelection) _fViewer.getSelection()).toList());
        super.okPressed();
    }
}
