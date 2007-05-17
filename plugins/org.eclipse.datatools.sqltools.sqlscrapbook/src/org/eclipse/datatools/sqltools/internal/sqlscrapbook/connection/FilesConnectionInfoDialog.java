/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.sqltools.common.ui.util.TableLayoutComposite;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


/**
 * Extends the base ConnectionInfoDialog by providing a list of files for user
 * to review the current associated connection infos and decide the files to apply
 * the new connection info.
 * @author Hui Cao
 */
public class FilesConnectionInfoDialog extends ConnectionInfoDialog
{
    /**
     * Label provider for templates.
     */
    private class FileConnectionInfoLabelProvider extends LabelProvider implements ITableLabelProvider {

        /*
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            //TODO return profile type image
            return null;
        }

        /*
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            IFile file = (IFile) element;
            ISQLEditorConnectionInfo connInfo = SQLFileUtil.getConnectionInfo(file);
            
            switch (columnIndex) {
                case 0:
                    return file.getName();
                case 1:
                    return connInfo.getDatabaseVendorDefinitionId().toString();
                case 2:
                    return connInfo.getConnectionProfileName();
                case 3:
                    return connInfo.getDatabaseName();
                default:
                    return ""; //$NON-NLS-1$
            }
        }
    }


    private class FileConnectionInfoContentProvider implements IStructuredContentProvider {

        /*
         * @see IStructuredContentProvider#getElements(Object)
         */
        public Object[] getElements(Object input) {
            return (Object[])input;
        }

        /*
         * @see IContentProvider#inputChanged(Viewer, Object, Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }

        /*
         * @see IContentProvider#dispose()
         */
        public void dispose() {
        }

    }

    private class TooltipPresenter extends MouseTrackAdapter
    {
        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.swt.events.MouseTrackAdapter#mouseHover(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseHover(org.eclipse.swt.events.MouseEvent e)
        {
            if (fTableViewer != null && !fTableViewer.getControl().isDisposed())
            {
                Table table = ((Table) e.widget);
                TableItem item = table.getItem(new Point(e.x, e.y));
                if (item != null && item.getData() != null)
                {
                    String tooltip = ((IFile) item.getData()).getFullPath().makeRelative().toString();
                    table.setToolTipText(tooltip);
                }
                else
                {
                    table.setToolTipText(null);
                }
            }
        }

    }
    private IFile[] _files = null;
    private Object[] _results = null;
    private CheckboxTableViewer fTableViewer = null;
    public FilesConnectionInfoDialog(Shell parentShell, ISQLEditorConnectionInfo connInfo, boolean overridePrompt, IFile[] files, String title )
    {
        super(parentShell, connInfo, overridePrompt, title);
        _files = files;
    }

    protected Control createDialogArea(Composite parent) {
        Composite composite = createOuterArea(parent);
        createFilesArea(composite);
        createConnectionArea(composite);
        return composite;
    }
    
    protected void createFilesArea(Composite composite)
    {
        Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.ConnectionInfoDialog_file_conn_info);
        TableLayoutComposite tableComposite= new TableLayoutComposite(composite, SWT.NONE);
        GridData data= new GridData(GridData.FILL_BOTH);
        data.widthHint= 500;
        data.heightHint= convertHeightInCharsToPixels(15);
        tableComposite.setLayoutData(data);
        
        Table table= new Table(tableComposite, SWT.CHECK | SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        GC gc= new GC(getShell());
        gc.setFont(JFaceResources.getDialogFont());
        
        TableColumn column1= new TableColumn(table, SWT.NONE);
        column1.setText(Messages.FilesConnectionInfoDialog_column_name);
        int minWidth= computeMinimumColumnWidth(gc, Messages.FilesConnectionInfoDialog_column_name);
        tableComposite.addColumnData(new ColumnWeightData(2, minWidth, true));

        TableColumn column2= new TableColumn(table, SWT.NONE);
        column2.setText(Messages.FilesConnectionInfoDialog_column_type);
        minWidth= computeMinimumColumnWidth(gc, Messages.FilesConnectionInfoDialog_column_type);
        tableComposite.addColumnData(new ColumnWeightData(2, minWidth, true));

        TableColumn column3= new TableColumn(table, SWT.NONE);
        column3.setText(Messages.FilesConnectionInfoDialog_column_profile);
        minWidth= computeMinimumColumnWidth(gc, Messages.FilesConnectionInfoDialog_column_profile);
        tableComposite.addColumnData(new ColumnWeightData(1, minWidth, true));

        TableColumn column4= new TableColumn(table, SWT.NONE);
        column4.setAlignment(SWT.CENTER);
        column4.setText(Messages.FilesConnectionInfoDialog_column_database);
        minWidth= computeMinimumColumnWidth(gc, Messages.FilesConnectionInfoDialog_column_database);
        tableComposite.addColumnData(new ColumnWeightData(1, minWidth, true));
        
        gc.dispose();

        fTableViewer= new CheckboxTableViewer(table);
        fTableViewer.setLabelProvider(new FileConnectionInfoLabelProvider());
        fTableViewer.setContentProvider(new FileConnectionInfoContentProvider());

        fTableViewer.setComparator(null);

        fTableViewer.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                checkOK();
            }
        });
        fTableViewer.setInput(_files);
        fTableViewer.setAllChecked(true);
        fTableViewer.getControl().addMouseTrackListener(new TooltipPresenter());
        
        addSelectionButtons(composite);
    }
    
    /**
     * Add the selection and deselection buttons to the dialog.
     * @param composite org.eclipse.swt.widgets.Composite
     */
    private void addSelectionButtons(Composite composite) {
        Composite buttonComposite = new Composite(composite, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 0;
        layout.marginWidth = 0;
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        buttonComposite.setLayout(layout);
        buttonComposite.setLayoutData(new GridData(SWT.END, SWT.TOP, true, false));

        Button selectButton = createButton(buttonComposite,
                IDialogConstants.SELECT_ALL_ID, Messages.SELECT_ALL_TITLE, false);

        SelectionListener listener = new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                fTableViewer.setAllChecked(true);
                checkOK();
            }
        };
        selectButton.addSelectionListener(listener);

        Button deselectButton = createButton(buttonComposite,
                IDialogConstants.DESELECT_ALL_ID, Messages.DESELECT_ALL_TITLE, false);

        listener = new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                fTableViewer.setAllChecked(false);
                checkOK();
            }
        };
        deselectButton.addSelectionListener(listener);
    }

    protected boolean checkOK() {
        boolean enabled = _group.canFinish() && fTableViewer.getCheckedElements() != null && fTableViewer.getCheckedElements().length > 0;
        if (getButton(IDialogConstants.OK_ID) != null) {
            getButton(IDialogConstants.OK_ID).setEnabled(enabled);
        }
        return enabled;
    }

    private int computeMinimumColumnWidth(GC gc, String string) {
        return gc.stringExtent(string).x + 10; // pad 10 to accommodate table header trimmings
    }
    
    protected void okPressed()
    {
        _results = fTableViewer.getCheckedElements();
        super.okPressed();
    }
    
    public Object[] getCheckedFiles()
    {
        return _results;
    }

}
