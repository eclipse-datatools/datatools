/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.drivers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.internal.DriverUtil;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.ui.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * This dialog allows the user to either type the driver class for
 * a driver definition or populate the list from the available jars
 * and allow the user to select one.
 *
 * @since 1.6
 */
public class DriverClassEditDialog extends SelectionDialog {

	private String[] mJarList = null;
	
	private IStructuredContentProvider fContentProvider;

    private ILabelProvider fLabelProvider;

    private Object fInput;

    private TableViewer fTableViewer;
    
    private Button typeOption;
    
    private Text classNameText;
    
    private Button browseOption;
    
//    private Button browseButton;

    private boolean fAddCancelButton = true;

    private int widthInChars = 55;

    private int heightInChars = 15;

    /**
     * Create a new instance of the receiver with parent shell of parent.
     * @param parent
     */
    public DriverClassEditDialog(Shell parent) {
        super(parent);
        fContentProvider = new ListContentProvider(new ArrayList());
        fLabelProvider = new ListLabelProvider();
    }

    /**
     * @param input The input for the list.
     */
    public void setInput(Object input) {
        fInput = input;
    }

    public void setJarList ( String[] jars ) {
    	this.mJarList = jars;
    }
    
    /**
     *@param addCancelButton if <code>true</code> there will be a cancel
     * button.
     */
    public void setAddCancelButton(boolean addCancelButton) {
        fAddCancelButton = addCancelButton;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        if (!fAddCancelButton) {
			createButton(parent, IDialogConstants.OK_ID,
                    IDialogConstants.OK_LABEL, true);
		} else {
			super.createButtonsForButtonBar(parent);
		}
    }

    protected Control createDialogArea(Composite container) {
        Composite parent = (Composite) super.createDialogArea(container);
        createMessageArea(parent);
        
        typeOption = new Button (parent, SWT.RADIO);
        typeOption.setText(Messages.DriverClassEditDialog_Type_option_button);
        typeOption.setLayoutData(new GridData());
        typeOption.setSelection(true);
        typeOption.addSelectionListener( new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				handleOptionSelection();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
        
        
        classNameText = new Text (parent, SWT.BORDER);
        classNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
        classNameText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (classNameText.getText() != null) {
					ArrayList selected = new ArrayList();
					selected.add(classNameText.getText().trim());
					setResult(selected);
				}
			}
		});
        
        browseOption = new Button (parent, SWT.RADIO);
        browseOption.setText(Messages.DriverClassEditDialog_Browse_option_button);
        browseOption.setLayoutData(new GridData());
        browseOption.setSelection(false);
        browseOption.addSelectionListener( new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				handleOptionSelection();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
        
/*        browseButton = new Button (parent, SWT.PUSH);
        browseButton.setText(Messages.DriverClassEditDialog_populate_classes_button);
        browseButton.setLayoutData(new GridData());
        browseButton.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent e) {
				DriverClassEditDialog.this.populateClassList();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
*/
        fTableViewer = new TableViewer(parent, getTableStyle());
        fTableViewer.setContentProvider(fContentProvider);
        fTableViewer.setLabelProvider(fLabelProvider);
        if (fInput instanceof List) 
        	fTableViewer.setInput(fInput);
        else if (fInput instanceof String)
        	classNameText.setText( (String) fInput);
        
        fTableViewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(DoubleClickEvent event) {
                if (fAddCancelButton) {
					okPressed();
				}
            }
        });
        List initialSelection = getInitialElementSelections();
        if (initialSelection != null) {
			fTableViewer
                    .setSelection(new StructuredSelection(initialSelection));
		}
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.heightHint = convertHeightInCharsToPixels(heightInChars);
        gd.widthHint = convertWidthInCharsToPixels(widthInChars);
        Table table = fTableViewer.getTable();
        table.setLayoutData(gd);
        table.setFont(container.getFont());
        handleOptionSelection();
        return parent;
    }
    
    private void handleOptionSelection() {
    	if (typeOption.getSelection()) {
    		classNameText.setEnabled(true);
//    		browseButton.setEnabled(false);
    		fTableViewer.getTable().setEnabled(false);
    	}
    	else {
    		classNameText.setEnabled(false);
//    		browseButton.setEnabled(true);
    		fTableViewer.getTable().setEnabled(true);
    		populateClassList();
    	}
    }

    /**
     * Return the style flags for the table viewer.
     * @return int
     */
    private int getTableStyle() {
        return SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
    }

    /*
     * Overrides method from Dialog
     */
    protected void okPressed() {
    	if (fTableViewer.getTable().getEnabled()) {
	        // Build a list of selected children.
	        IStructuredSelection selection = (IStructuredSelection) fTableViewer
	                .getSelection();
	        setResult(selection.toList());
    	}
    	else if (classNameText.getEnabled()) {
			if (classNameText.getText() != null) {
				ArrayList selected = new ArrayList();
				selected.add(classNameText.getText().trim());
				setResult(selected);
			}
    	}
        super.okPressed();
    }
   
    private ArrayList getClassList() {
		final ArrayList classes = new ArrayList();
		for (int i = 0; i < mJarList.length; i++) {
			String filepath = mJarList[i];
			final File file = new File(filepath);
			try {
				new JarFile(file);
			} catch (ZipException e) {
				// must not be a zip file - skip it
				continue;
			} catch (IOException e) {
				String msg = e.getLocalizedMessage();
				if (e.getLocalizedMessage() == null || e.getLocalizedMessage().trim().length() == 0) {
					msg = ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.msg");//$NON-NLS-1$
				}
				ExceptionHandler.showException(getShell(), 
						ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.title"), //$NON-NLS-1$
						msg, e);
				return null;
			}
			ProgressMonitorDialog pmd = new ProgressMonitorDialog(getShell());
			try {
				pmd.run(true, false, new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						try {
							String[] classStr = DriverUtil.getDriverClassesFromJar(file, monitor);
							classes.addAll(Arrays.asList(classStr));
						} catch (Exception e1) {
							InvocationTargetException ce = new InvocationTargetException(e1);
							throw ce;
						}
					}
				});
			} catch (InvocationTargetException e) {
				String msg = e.getLocalizedMessage();
				if (e.getLocalizedMessage() == null || e.getLocalizedMessage().trim().length() == 0) {
					msg = ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.msg");//$NON-NLS-1$
				}
				ExceptionHandler.showException(getShell(), 
						ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.title"), //$NON-NLS-1$
						msg, e);
				return null;
			} catch (InterruptedException e) {
				String msg = e.getLocalizedMessage();
				if (e.getLocalizedMessage() == null || e.getLocalizedMessage().trim().length() == 0) {
					msg = ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.msg");//$NON-NLS-1$
				}
				ExceptionHandler.showException(getShell(), 
						ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.title"), //$NON-NLS-1$
						msg, e);
				return null;
			}
		}
		return classes;
    }

	private class ListContentProvider implements IStructuredContentProvider {
		
		private ArrayList mList = null;
		
		public ListContentProvider(ArrayList list) {
			mList = list;
		}
		
		public void setList( ArrayList list ) {
			mList = list;
		}
		
		public Object[] getElements(Object inputElement) {
			return mList.toArray();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private class ListLabelProvider extends LabelProvider {
		public String getText(Object obj) {
			String name = (String)obj;
			if (name!=null)
				return name;
			return (name != null) ? name : "";
		}
		public Image getImage(Object obj) {
			return null;
		}
	}
	
	private void populateClassList() {
		ArrayList list = getClassList();
		if (list != null) {
			fContentProvider = new ListContentProvider(list);
			fTableViewer.setContentProvider(fContentProvider);
        	fTableViewer.setInput(list);
        	fTableViewer.getTable().setFocus();
            List initialSelection = getInitialElementSelections();
            if (initialSelection == null || initialSelection.size() == 0) {
            	if (classNameText != null && classNameText.getText().trim().length() > 0) {
            		initialSelection = new ArrayList();
            		initialSelection.add(classNameText.getText().trim());
            	}
            }
            if (initialSelection != null && initialSelection.size() > 0) {
    			fTableViewer
                        .setSelection(new StructuredSelection(initialSelection));
    			fTableViewer.reveal(initialSelection);
    		}
		}
		else {
			String title = 
				ConnectivityUIPlugin.getDefault().getResourceString("DriverClassBrowsePropertyDescriptor.noclasses.title");//$NON-NLS-1$
			String message = 
				ConnectivityUIPlugin.getDefault().getResourceString("DriverClassBrowsePropertyDescriptor.noclasses.msg");//$NON-NLS-1$
			MessageDialog.openInformation(getShell(),
					title, 
					message);
		}
	}
}
