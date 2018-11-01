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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.actions.ConnectAction;
import org.eclipse.datatools.sqltools.common.ui.util.TableLayoutComposite;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
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
    private class FileConnectionInfoSorter extends ViewerSorter
    {
        public static final int NAME = 1;
        public static final int VENDOR = 2;
        public static final int PROFILE_NAME = 3;
        public static final int DATABASE_NAME = 4;
        public static final String EMPTY_STRING = "";
        
        private int sortType;
        
        public FileConnectionInfoSorter(int sortOrder)
        {
            this.sortType = sortOrder;
        }

        public int compare(Viewer viewer, Object e1, Object e2)
        {
            IFile f1 = (IFile)e1;
            IFile f2 = (IFile)e2;
            ISQLEditorConnectionInfo connInfo1 = SQLFileUtil.getConnectionInfo(f1);
            ISQLEditorConnectionInfo connInfo2 = SQLFileUtil.getConnectionInfo(f2);
            if(connInfo1==null || connInfo2==null)
            {
                return 0;
            }
            switch(sortType)
            {
                case NAME:
                {
                    String fName1 = f1.getName();
                    if(fName1==null)
                    {
                        fName1 = EMPTY_STRING;
                    }
                    String fName2 = f2.getName();
                    if(fName2==null)
                    {
                        fName2 = EMPTY_STRING;
                    }
                    return fName1.compareTo(fName2);
                }
                case -NAME:
                {
                    String fName1 = f1.getName();
                    if(fName1==null)
                    {
                        fName1 = EMPTY_STRING;
                    }
                    String fName2 = f2.getName();
                    if(fName2==null)
                    {
                        fName2 = EMPTY_STRING;
                    }
                    return fName2.compareTo(fName1);
                }
                case VENDOR:
                {
                    String vender1 = EMPTY_STRING;
                    if(connInfo1.getDatabaseVendorDefinitionId()!=null)
                    {
                        vender1 = connInfo1.getDatabaseVendorDefinitionId().toString();
                    }
                    String vender2 = EMPTY_STRING;
                    if(connInfo2.getDatabaseVendorDefinitionId()!=null)
                    {
                        vender2 = connInfo2.getDatabaseVendorDefinitionId().toString();
                    }
                    return vender1.compareTo(vender2);
                }
                case -VENDOR:
                {
                    String vender1 = EMPTY_STRING;
                    if(connInfo1.getDatabaseVendorDefinitionId()!=null)
                    {
                        vender1 = connInfo1.getDatabaseVendorDefinitionId().toString();
                    }
                    String vender2 = EMPTY_STRING;
                    if(connInfo2.getDatabaseVendorDefinitionId()!=null)
                    {
                        vender2 = connInfo2.getDatabaseVendorDefinitionId().toString();
                    }
                    return vender2.compareTo(vender1);
                }
                case PROFILE_NAME:
                {
                    String profile1 = connInfo1.getConnectionProfileName();
                    if(profile1==null)
                    {
                        profile1 = EMPTY_STRING;
                    }
                    String profile2 = connInfo2.getConnectionProfileName();
                    if(profile2==null)
                    {
                        profile2 = EMPTY_STRING;
                    }
                    return profile1.compareTo(profile2);
                }
                case -PROFILE_NAME:
                {
                    String profile1 = connInfo1.getConnectionProfileName();
                    if(profile1==null)
                    {
                        profile1 = EMPTY_STRING;
                    }
                    String profile2 = connInfo2.getConnectionProfileName();
                    if(profile2==null)
                    {
                        profile2 = EMPTY_STRING;
                    }
                    return profile2.compareTo(profile1);
                }
                case DATABASE_NAME:
                {
                    String dbName1 = connInfo1.getDatabaseName();
                    if(dbName1==null)
                    {
                        dbName1 = EMPTY_STRING;
                    }
                    String dbName2 = connInfo2.getDatabaseName();
                    if(dbName2==null)
                    {
                        dbName2 = EMPTY_STRING;
                    }
                    return dbName1.compareTo(dbName2);
                }
                case -DATABASE_NAME:
                {
                    String dbName1 = connInfo1.getDatabaseName();
                    if(dbName1==null)
                    {
                        dbName1 = EMPTY_STRING;
                    }
                    String dbName2 = connInfo2.getDatabaseName();
                    if(dbName2==null)
                    {
                        dbName2 = EMPTY_STRING;
                    }
                    return dbName2.compareTo(dbName1);
                }
            }
            return 0;
        }
    }
    /**
     * Label provider for templates.
     */
    private class FileConnectionInfoLabelProvider extends LabelProvider implements ITableLabelProvider {

        /*
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
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
    
    private ViewerSorter nameSorter = new FileConnectionInfoSorter(FileConnectionInfoSorter.NAME);
    private ViewerSorter nameSorterReverse = new FileConnectionInfoSorter(-FileConnectionInfoSorter.NAME);
    private ViewerSorter vendorSorter = new FileConnectionInfoSorter(FileConnectionInfoSorter.VENDOR);
    private ViewerSorter vendorSorterReverse = new FileConnectionInfoSorter(-FileConnectionInfoSorter.VENDOR);
    private ViewerSorter profileSorter = new FileConnectionInfoSorter(FileConnectionInfoSorter.PROFILE_NAME);
    private ViewerSorter profileSorterReverse = new FileConnectionInfoSorter(-FileConnectionInfoSorter.PROFILE_NAME);
    private ViewerSorter dbSorter = new FileConnectionInfoSorter(FileConnectionInfoSorter.DATABASE_NAME);
    private ViewerSorter dbSorterReverse = new FileConnectionInfoSorter(-FileConnectionInfoSorter.DATABASE_NAME);
    private Button _upButton;
    private Button _downButton;
    
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
    
    private void setSortColumn(SelectionEvent e, boolean sortType)
    {
        TableColumn sortColumn = (TableColumn)e.getSource();
        fTableViewer.getTable().setSortColumn(sortColumn);
        fTableViewer.getTable().setSortDirection(sortType?SWT.UP:SWT.DOWN);
        fTableViewer.getTable().redraw();
        setOrderedFiles();
        checkUpDownStatus();
    }
    
    private void setOrderedFiles()
    {
        IFile[] newFiles = new IFile[_files.length];
        Table table = fTableViewer.getTable();
        TableItem[] allItems = table.getItems();
        int count = 0;
        if (allItems != null && allItems.length > 0)
        {
            for (int i = 0; i < allItems.length; i++)
            {
                String fileName = ((IFile) allItems[i].getData()).getFullPath().toString();
                for (int j = 0; j < _files.length; j++)
                {
                    String tmpFileName = _files[j].getFullPath().toString();
                    if (fileName.equals(tmpFileName))
                    {
                        newFiles[count] = _files[j];
                        count++;
                        break;
                    }
                }
            }
        }
        _files=newFiles;
        fTableViewer.setInput(_files);
        fTableViewer.refresh();
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
        column1.addSelectionListener(new SelectionAdapter(){
            boolean sortType = true;
            public void widgetSelected(SelectionEvent e)
            {
                sortType = !sortType;
                fTableViewer.setSorter(sortType?nameSorter:nameSorterReverse);
                setSortColumn(e,sortType);
            }
            
        });

        TableColumn column2= new TableColumn(table, SWT.NONE);
        column2.setText(Messages.FilesConnectionInfoDialog_column_type);
        minWidth= computeMinimumColumnWidth(gc, Messages.FilesConnectionInfoDialog_column_type);
        tableComposite.addColumnData(new ColumnWeightData(2, minWidth, true));
        column2.addSelectionListener(new SelectionAdapter(){
            boolean sortType = true;
            public void widgetSelected(SelectionEvent e)
            {
                sortType = !sortType;
                fTableViewer.setSorter(sortType?vendorSorter:vendorSorterReverse);
                setSortColumn(e,sortType);
            }
            
        });
        TableColumn column3= new TableColumn(table, SWT.NONE);
        column3.setText(Messages.FilesConnectionInfoDialog_column_profile);
        minWidth= computeMinimumColumnWidth(gc, Messages.FilesConnectionInfoDialog_column_profile);
        tableComposite.addColumnData(new ColumnWeightData(1, minWidth, true));
        column3.addSelectionListener(new SelectionAdapter(){
            boolean sortType = true;
            public void widgetSelected(SelectionEvent e)
            {
                sortType = !sortType;
                fTableViewer.setSorter(sortType?profileSorter:profileSorterReverse);
                setSortColumn(e,sortType);
            }
            
        });
        
        TableColumn column4= new TableColumn(table, SWT.NONE);
        column4.setAlignment(SWT.CENTER);
        column4.setText(Messages.FilesConnectionInfoDialog_column_database);
        minWidth= computeMinimumColumnWidth(gc, Messages.FilesConnectionInfoDialog_column_database);
        tableComposite.addColumnData(new ColumnWeightData(1, minWidth, true));
        column4.addSelectionListener(new SelectionAdapter(){
            boolean sortType = true;
            public void widgetSelected(SelectionEvent e)
            {
                sortType = !sortType;
                fTableViewer.setSorter(sortType?dbSorter:dbSorterReverse);
                setSortColumn(e,sortType);
            }
        });
        
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
        
        _upButton = createButton(buttonComposite,IDialogConstants.CLIENT_ID,Messages.UP_TITLE,false);
        _downButton = createButton(buttonComposite,IDialogConstants.CLIENT_ID,Messages.DOWN_TITLE,false);
        _upButton.setEnabled(false);
        _downButton.setEnabled(false);
        listener = new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                moveButtonAction(true);
                checkOK();
                checkUpDownStatus();
            }
        };
        _upButton.addSelectionListener(listener);
        listener = new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                moveButtonAction(false);
                checkOK();
                checkUpDownStatus();
            }
        };
        _downButton.addSelectionListener(listener);
        
        fTableViewer.getTable().addSelectionListener(new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e)
            {
                checkUpDownStatus();
            }
        });
    }
    
    protected void checkUpDownStatus()
    {
        int selectionCount = fTableViewer.getTable().getSelectionCount();
        if(selectionCount>0)
        {
            if(getSelectionStartIndex()>0)
            {
                _upButton.setEnabled(true);
            }
            else
            {
                _upButton.setEnabled(false);
            }
            if(getSelectionEndIndex()<_files.length-1)
            {
                _downButton.setEnabled(true);
            }
            else
            {
                _downButton.setEnabled(false);
            }
        }
        else
        {
            _upButton.setEnabled(false);
            _downButton.setEnabled(false);
        }
    }
    
    private int getSelectionStartIndex()
    {
        Table table = fTableViewer.getTable();
        int start = 0;
        TableItem[] selectionItems = table.getSelection();
        if (selectionItems != null && selectionItems.length > 0)
        {
            for (int i = 0; i < _files.length; i++)
            {
                String fileName = _files[i].getFullPath().toString();
                String firstSelectedFileName = ((IFile)selectionItems[0].getData()).getFullPath().toString();
                if (fileName.equals(firstSelectedFileName))
                {
                    start = i;
                    break;
                }
            }
        }
        return start;
    }
    
    private int getSelectionEndIndex()
    {
        Table table = fTableViewer.getTable();
        int end = _files.length-1;
        TableItem[] selectionItems = table.getSelection();
        if (selectionItems != null && selectionItems.length > 0)
        {
            for (int i = 0; i < _files.length; i++)
            {
                String fileName = _files[i].getFullPath().toString();
                String lastSelectedFileName = ((IFile)selectionItems[selectionItems.length - 1].getData()).getFullPath().toString();
                if (fileName.equals(lastSelectedFileName))
                {
                    end = i;
                    break;
                }
            }
        }
        return end;
    }
    
    private void moveButtonAction(boolean isOrderUp)
    {
        fTableViewer.getTable().setSortColumn(null);
        fTableViewer.getTable().setSortDirection(SWT.NONE);
        fTableViewer.setSorter(null);
        
        IFile[] newFiles = new IFile[_files.length];
        int start = getSelectionStartIndex();
        int end = getSelectionEndIndex();
        int count = 0;
        if (isOrderUp)
        {
            if(start == 0)
            {
                return;
            }
            for (int i = 0; i < start - 1; i++)
            {
                newFiles[count] = _files[i];
                count++;
            }
            for (int i = start; i <= end; i++)
            {
                newFiles[count] = _files[i];
                count++;
            }
            newFiles[count] = _files[start - 1];
            count++;
            for (int i = end + 1; i < _files.length; i++)
            {
                newFiles[count] = _files[i];
                count++;
            }
        }
        else
        {
            if(end==_files.length-1)
            {
                return;
            }
            for (int i = 0; i < start; i++)
            {
                newFiles[count] = _files[i];
                count++;
            }
            newFiles[count] = _files[end+1];
            count++;
            for (int i = start; i <= end; i++)
            {
                newFiles[count] = _files[i];
                count++;
            }

            for (int i = end + 2; i < _files.length; i++)
            {
                newFiles[count] = _files[i];
                count++;
            }
        }
        _files=newFiles;
        fTableViewer.setInput(_files);
        fTableViewer.refresh();
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
