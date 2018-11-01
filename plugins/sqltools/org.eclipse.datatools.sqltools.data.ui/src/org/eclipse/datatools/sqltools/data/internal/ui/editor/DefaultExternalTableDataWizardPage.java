/*******************************************************************************
 * Copyright (c) 2001, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.data.internal.core.editor.IRowData;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData2;
import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TableItem;

/**
 * The default Wizard Page for external editing of the TableDataEditor.
 * @author sschaer
 */
public class DefaultExternalTableDataWizardPage extends WizardPage implements SelectionListener {

    /** the TableDataEditor associated with this wizard */
    protected ITableDataEditor editor;
    
    /** the row in which the currently edited cell is */
	protected IRowData rowData;
    
    /** the row index of the cell being edited */
    protected int columnIndex; 

    /** the default implementation of the edit area, a simple Text area */
    protected Text textWidget; 
    
    /** The Import button */
    protected Button btnImport;
    
    /** The Export button */
    protected Button btnExport;
    
    /** The Set Null button */
    protected Button btnSetNull;  
    
    /** if true, the Import button is displayed */
    protected boolean displayImportBtn;
    
    /** if true, the Export button is displayed */
    protected boolean displayExportBtn;
    
    /** if true, the Set Null button is displayed */
    protected boolean displaySetNullBtn;  
    
    /** true if the current cell has the null value */
    protected boolean hasNullValue;

    /** whether the editor area works on serialized input (in form of a String) or on raw input */
    protected boolean editorAreaIsSerialized;
    
    /** the basic composite used in the first wizard page */
    protected Composite basicComp;
    
    
    protected int buttonAreaSpan;
    
    /**
     * Creates the default page with the given editor
	 * @param pageName the name of the page
     * @param editor the TableDataEditor for this page
     * @throws IllegalArgumentException if the given editor is null or if its 
     *         cursor doesnt provide a valid IRowData 
	 */
	public DefaultExternalTableDataWizardPage(String pageName) {
		super(pageName);
	}
	
	public void init(ITableDataEditor editor)
	{
        // input validation
        if ((editor == null) || (editor.getCursor() == null) || 
            (editor.getCursor().getRow() == null) ) {
            throw new IllegalArgumentException(Messages.getString("DefaultExternalTableDataEditorWizard.InitError"));  //$NON-NLS-1$
        }
        this.editor = editor;
        this.columnIndex = editor.getCursor().getColumn();
        this.rowData = editor.getOrCreateRow();
        hasNullValue = isNullValue();
        try{
            String type = null;
            if (editor.getTableData() instanceof ITableData2) {
        	type = ((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).getDataType().getName();
            }
            else {
        	type = ((Column)editor.getSqlTable().getColumns().get(columnIndex)).getDataType().getName();
            }
            setTitle(Messages.getString("DefaultExternalTableDataWizardPage.Title", new Object[]{type}));  //$NON-NLS-1$
        } catch (Exception e){
            setTitle(Messages.getString("DefaultExternalTableDataWizardPage.DefaultTitle"));  //$NON-NLS-1$
        }
	    setDescription(Messages.getString("DefaultExternalTableDataWizardPage.Description"));  //$NON-NLS-1$

        // by default import and export buttons are enabled
        displayImportBtn  = true;
        displayExportBtn  = true;
        displaySetNullBtn = true;
        
        // how many buttons we create in the buttonArea
        buttonAreaSpan = 1;
	}

	/**
	 * whether the cell is null or not
	 * @return whether the value of the currently selected cell is null
	 */
	protected boolean isNullValue(){
		return (this.rowData.getValue(columnIndex) == null) ? true : false;
	}
	
    /**
     * Instead of overwriting this method, subclasses should overwrite
     * one of the following methods that are called from within this method:
     *  createBasicComposite(Composite comp) 
     *  createEditorArea(Composite comp)  
     *  createButtonArea(Composite comp)   
     */
	public void createControl(Composite parent) {
        basicComp = createBasicComposite(parent);
	    createEditorArea(basicComp);
        createButtonArea(basicComp);
		setControl(basicComp);
        if(editor.isReadonly()){
            setPageComplete(false);
        }else{
            setPageComplete(true);
        }
        
	}
	
    /**
     * Creates the basic Composite for this wizard page
     * @param parent the parent composite
     * @return the basic Composite in which the page will reside
     */
    protected Composite createBasicComposite(Composite parent){
        Composite comp = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout();
        gl.verticalSpacing = 10;
        comp.setLayout(gl);
        GridData gd = new GridData(GridData.FILL_BOTH);
        comp.setLayoutData(gd);
        return comp;
    }

    /**
     * Creates the area for the actual editing of the cell data.
     * Subclasses should overwrite this method in order to provide 
     * other widgets for editing the cell data.
     * If this method is being overwritten, 
     * getEditorAreaInput(), setEditorAreaInput() and getEditWidget() also need
     * to be overwritten.
     * @param comp the parent composite
     * @see #getEditorAreaContent()
     * @see #setEditorAreaContent(Object)
     * @see #getEditWidget()
     */
	protected void createEditorArea(Composite comp){
		
		Composite editorComp = new Composite(comp,SWT.NONE);
        GridLayout glEditor = new GridLayout(2, false);
        // if either button is enabled we have two columns
		if (displayExportBtn || displayImportBtn){ 
			glEditor.numColumns = 2; 
		}
		glEditor.marginHeight = 0;
		glEditor.marginWidth = 0;
        editorComp.setLayout(glEditor);
        editorComp.setLayoutData( new GridData(GridData.FILL_BOTH) );
        
        textWidget = new Text(editorComp, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		// always enable it so that at least a manual copy and paste from a read-only table can be copied..
        textWidget.setEnabled(true);
        boolean editable = (!hasNullValue && !editor.isReadonly()) ;
        
        // if it is new (yet null) value of a non-nullable column (i.e. non-nullable and set to null) 
		// we force the editorarea, import, export to be enabled and the null button is set to false
		// see further down as well
        boolean nonNullableCheck = false;
        if (editor.getTableData() instanceof ITableData2) {
            nonNullableCheck =  (!((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).isNullable() && hasNullValue);
        }
        else {
            nonNullableCheck =  (!((Column)editor.getSqlTable().getColumns().get(columnIndex)).isNullable() && hasNullValue);
        }
        textWidget.setEditable(editable || nonNullableCheck);
	
        editorAreaIsSerialized = true;  // as we deal with a text field, we want it serialized
		GridData gdtxtTextValue = new GridData(GridData.FILL_BOTH);
		gdtxtTextValue.grabExcessHorizontalSpace = true;
		gdtxtTextValue.grabExcessVerticalSpace = true;
		textWidget.setLayoutData(gdtxtTextValue);   
        gdtxtTextValue.horizontalSpan = 1;
        //limit width/height of text area when string content is very wide or long
        gdtxtTextValue.widthHint = 400;
        gdtxtTextValue.heightHint = 230;
        setEditorAreaContent(rowData.getValue(columnIndex));
        
        // if import or export is enabled 
		if (displayExportBtn || displayImportBtn){
	        GridLayout glButtons = new GridLayout();
	        glButtons.marginWidth = 0;
	        glButtons.marginHeight = 0;
	        GridData gdCButtons = new GridData(GridData.FILL_VERTICAL);
	        Composite cmpButtons = new Composite(editorComp, SWT.NONE);
	        cmpButtons.setLayout(glButtons);
	        cmpButtons.setLayoutData(gdCButtons);

	        // Import button
	        if(displayImportBtn){
	            GridData gdBtnImport = new GridData();
	            gdBtnImport.horizontalAlignment = GridData.FILL;
	            btnImport = new Button(cmpButtons, SWT.PUSH);	    		
	            btnImport.setText(Messages.getString("DefaultExternalTableDataWizardPage.Button.Import")); //$NON-NLS-1$
	            btnImport.setLayoutData(gdBtnImport);
	            btnImport.addSelectionListener(this);
	            // if current value is null or editor is readonly button has to be disabled
	            if ((hasNullValue || editor.isReadonly())){
	                btnImport.setEnabled(false);
	            }
	            // if it is new (yet null) value of a non-nullable column (i.e. non-nullable and value set to null) 
	    		// we force the editorarea, import, export to be enabled and the null button is set to false
	    		// see further up and down as well	            
	    		if ( editor.getTableData() instanceof ITableData2 && !((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).isNullable() && hasNullValue) {
	    			btnImport.setEnabled(true);
	    		}
	    		else if (!((Column)editor.getSqlTable().getColumns().get(columnIndex)).isNullable() && hasNullValue) {
	    			btnImport.setEnabled(true);
	    		}
	            
	        }
	        // Export button
	        if(displayExportBtn){
	            GridData gdBtnExport = new GridData();
	            gdBtnExport.horizontalAlignment = GridData.FILL;
	            gdBtnExport.verticalAlignment =GridData.VERTICAL_ALIGN_BEGINNING;
	            btnExport = new Button(cmpButtons, SWT.PUSH);  
	            btnExport.setText(Messages.getString("DefaultExternalTableDataWizardPage.Button.Export")); //$NON-NLS-1$
	            btnExport.setLayoutData(gdBtnExport);
	            btnExport.addSelectionListener(this);
	            if (hasNullValue){
	                btnExport.setEnabled(false);
	            }
	            // if it is new (yet null) value of a non-nullable column (i.e. non-nullable and value set to null) 
	    		// we force the editorarea, import, export to be enabled and the null button is set to false
	    		// see further up as well	            
	    		if ( editor.getTableData() instanceof ITableData2 && !((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).isNullable() && hasNullValue) {
	    			btnExport.setEnabled(true);
	    		}
	    		else if (!((Column)editor.getSqlTable().getColumns().get(columnIndex)).isNullable() && hasNullValue) {
	    			btnExport.setEnabled(true);
	    		}
	            
	        }
		}
	}

    /**
     * Creates the area for the buttons.
     * Subclasses should overwrite this method in order to provide 
     * other or additional buttons. 
     * If this method is being overwritten, the SelectionListener methods
     * widgetSelected() and widgedDefaultSelected should also be overwritten.
     * @param comp the parent composite
     * @see #widgetSelected(SelectionEvent)
     * @see #widgetDefaultSelected(SelectionEvent) 
     */
    protected void createButtonArea(Composite comp){
        // new Composite for the buttons
        GridLayout glButtons = new GridLayout();
        glButtons.numColumns = buttonAreaSpan;
        glButtons.marginWidth = 0;
        glButtons.marginHeight = 0;
        GridData gdCButtons = new GridData();
        gdCButtons.horizontalAlignment= GridData.BEGINNING;
        Composite cmpButtons = new Composite(comp, SWT.NONE);
        cmpButtons.setLayout(glButtons);
        cmpButtons.setLayoutData(gdCButtons);

        // SetNull button
        if(displaySetNullBtn){
            GridData gdBtnSetNull = new GridData();
            gdBtnSetNull.horizontalAlignment = GridData.FILL;
            btnSetNull = new Button(cmpButtons, SWT.CHECK);  
            btnSetNull.setText(Messages.getString("DefaultExternalTableDataWizardPage.Button.SetNull")); //$NON-NLS-1$
            btnSetNull.setLayoutData(gdBtnSetNull);
            btnSetNull.addSelectionListener(this);
            if (hasNullValue){
                btnSetNull.setSelection(true);
            }
            // if it is new (yet null) value of a non-nullable column (i.e. non-nullable and value set to null) 
    		// we force the editorarea, import, export to be enabled and the null button is set to false
		if ( editor.getTableData() instanceof ITableData2 &&  !((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).isNullable() && hasNullValue) {
			btnSetNull.setSelection(false);
			hasNullValue = false;
		}
		else if ( !((Column)editor.getSqlTable().getColumns().get(columnIndex)).isNullable() && hasNullValue ) {
			btnSetNull.setSelection(false);
			hasNullValue = false;
		}
            
            // set Null is enabled only for nullable columns of a writeable editor
		if (editor.getTableData() instanceof ITableData2) {
	            if ( ((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).isNullable() && !editor.isReadonly()){
	                btnSetNull.setEnabled(true);
	            } else{
	                btnSetNull.setEnabled(false);
	            }
		}
		else {
			if ( ((Column)editor.getSqlTable().getColumns().get(columnIndex)).isNullable() && !editor.isReadonly()){
	                btnSetNull.setEnabled(true);
	            } else{
	                btnSetNull.setEnabled(false);
	            }
		}
        }
    }
    
    /**
     * Serializes the given Object to a String using the 
     * ColumDataAccessor of the selected cell.
     * @param o the object to be serialized
     * @return the String representation 
     */
    protected String serializeInput(Object o) {
        return rowData.getTable().getColumnDataAccessor(columnIndex).serialize(
                o, rowData.getTable().getColumnType(columnIndex));
    }

    /**
     * Deserializes the given String to an Object using the 
     * ColumnDataAccessor of the selected cell.
     * @param value the String to be deserialized
     * @return the Object as returned by the ColumnDataAccessor
     */
    protected Object deserializeInput(String value) {
        return rowData.getTable().getColumnDataAccessor(columnIndex)
                    .deserialize( value,
                            rowData.getTable().getColumnType(columnIndex));
    }

    /**
     * Updates the cell data with the current content of the 
     * edit widget. 
     */
    public boolean updateRowData(){
        try{
            if(hasNullValue){
                editor.doSetNull();
            } else{
                if (editorAreaIsSerialized){
                    rowData.updateValue(columnIndex, deserializeInput((String)getEditorAreaContent()));
                }else{
                    rowData.updateValue(columnIndex, getEditorAreaContent());
                }
                editor.getTableViewer().refresh(rowData);
                editor.getCursor().redraw();
                editor.setDirty(true);
            }
            // Mark background dirty
            TableItem item = editor.getCursor().getRow();
            editor.setDirtyBackground(columnIndex, item);
            
            setErrorMessage(null);
            return true;
        }catch (IllegalArgumentException iae){
            // IllegalArgument is thrown if deserialization fails 
            // (e.g. if for the entered binary data string length%2!=0 
            setErrorMessage(Messages.getString("TableDataCellModifier.dataFormatError")); //$NON-NLS-1$
            return false;
        }
    }
    

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
    }
    
    /**
     * Handles the button events and delegates to the event handle routines.
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        if (e.getSource().equals(btnSetNull)){
            handleSetNull();
        }
        if (e.getSource().equals(btnImport)){
            handleImport();
        }
        if (e.getSource().equals(btnExport)){
            handleExport();
        }
    }
    
    /**
     * handles the action when the Set Null button is pressed.
     * I.e. disables the editWidget and the export and import buttons
     */
    protected void handleSetNull(){
        // for text widgets: disable edit so that mark and copy is still possible
        // for others disable widget itself
        if (getEditWidget() instanceof Text){
            ((Text)getEditWidget()).setEditable(!btnSetNull.getSelection());
        } else{
            getEditWidget().setEnabled(!btnSetNull.getSelection());
        }
        if(displayImportBtn){
            btnImport.setEnabled(!btnSetNull.getSelection());
        }
        if(displayExportBtn){
            btnExport.setEnabled(!btnSetNull.getSelection());
        }
        hasNullValue = btnSetNull.getSelection();
    }
    
    /**
     * opens a standard FileDialog and loads the file if one is selected.
     * To set the fileExtensions used for the Filter of the FileDialog overwrite the getFileExtensions() method
     * @see #getFileExtensions() 
     */
    protected void handleImport(){
        String title = null;
        try{
            String type = null;
            if (editor.getTableData() instanceof ITableData2) {
        	type = ((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).getDataType().getName();
            }
            else {
        	type = ((Column)editor.getSqlTable().getColumns().get(columnIndex)).getDataType().getName();
            }
            title = Messages.getString("DefaultExternalTableDataWizardPage.Import.Title", new Object[]{type}); //$NON-NLS-1$
        } catch (Exception e){
            title = Messages.getString("DefaultExternalTableDataWizardPage.Import.DefaultTitle"); //$NON-NLS-1$
        }
        FileDialog fileDialog = new FileDialog(this.getShell(), SWT.OPEN);
        fileDialog.setText(title); //$NON-NLS-1$
        fileDialog.setFilterExtensions(getFileExtensions());
        String fname = fileDialog.open();
        if (fname != null){
            File aFile = new File(fname);
            if(aFile.canRead()){
                try{
                	if (isBinaryFile()){
                        FileInputStream fis = new FileInputStream(aFile);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        byte b[] = new byte[bis.available()];
                        bis.read(b);
                        bis.close();
                        setEditorAreaContent(b);
                	} else{                	
                		StringBuffer input = new StringBuffer();
                		input.append(DataUIPlugin.getFileContentWithEncoding(fname));
                        setEditorAreaContent(input.toString());
                    } 
                }catch(FileNotFoundException fnfe){
                    DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, fnfe.getMessage(), fnfe);
                    setErrorMessage(Messages.getString("DefaultExternalTableDataWizardPage.Import.FileNotFound")); //$NON-NLS-1$
                }catch(IOException ioe){
                    DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ioe.getMessage(), ioe);
                    setErrorMessage(Messages.getString("DefaultExternalTableDataWizardPage.Import.IOError")); //$NON-NLS-1$
                }
            }
        }
    }

    /**
     * opens a standard Save FileDialog and saves 
     * the content of the editorWidget.
     * To set the fileExtensions used for the Filter of the FileDialog overwrite the getFileExtensions() method
     * @see #getFileExtensions()
     *
     */
    protected void handleExport(){
        String title = null;
        try{
            String type = null;
            if (editor.getTableData() instanceof ITableData2) {
        	type = ((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).getDataType().getName();
            }
            else {
        	type = ((Column)editor.getSqlTable().getColumns().get(columnIndex)).getDataType().getName();
            }
            title = Messages.getString("DefaultExternalTableDataWizardPage.Export.Title", new Object[]{type}); //$NON-NLS-1$
        } catch (Exception e){
            title = Messages.getString("DefaultExternalTableDataWizardPage.Export.DefaultTitle"); //$NON-NLS-1$
        }
        
        FileDialog fileDialog = new FileDialog(this.getShell(), SWT.SAVE);
        fileDialog.setText(title); //$NON-NLS-1$
        fileDialog.setFilterExtensions(getFileExtensions());
        String fname = fileDialog.open();
        if (fname != null){
            File aFile = new File(fname);
            boolean writeFile = true;
            if(aFile.exists()){
                MessageDialog dialog =
                    new MessageDialog(
                       this.getShell(),
                       Messages.getString("DefaultExternalTableDataWizardPage.Export.FileOverwriteDialog.Title"), //$NON-NLS-1$
                       null,
                       Messages.getString("DefaultExternalTableDataWizardPage.Export.FileOverwriteDialog.Message", new Object[]{fname}), //$NON-NLS-1$
                       MessageDialog.QUESTION,
                       new String[] {
                          IDialogConstants.YES_LABEL,  // return value dependent on this order.
                          IDialogConstants.NO_LABEL},
                       1);  // YES button is pre-selected
                dialog.open();
                int returnValue = dialog.getReturnCode();
                if (returnValue !=0){
                    writeFile = false;
                }
            }
            if(writeFile){
                Object obj = null;
                if (editorAreaIsSerialized){
                    obj = deserializeInput((String)getEditorAreaContent());
                } else{
                    obj = getEditorAreaContent();
                }
                byte[] bytes = null;
                if (obj instanceof String){
                	String encoding = DataUIPlugin.getCharacterEncoding();
                	try {
                		// use the user specified encoding if set by user
                		if (encoding != null && !encoding.equals("")) {
                			bytes = ((String)obj).getBytes(encoding);
                		}
                		else {
                			bytes = ((String)obj).getBytes();
                		}
                	}
                	catch (java.io.UnsupportedEncodingException ex) {
                		// if specified encoding is not supported use default
                		bytes = ((String)obj).getBytes();
                	}                	
                    
                } else{
            	   	bytes = getByteContentForExport(obj);
                }
                if (bytes!=null){
                    try{
                        // save file to system path (buffered)
                        FileOutputStream fos = new FileOutputStream(aFile);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        bos.write(bytes);
                        bos.close();
                    }catch(FileNotFoundException fnfe){
                        DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, fnfe.getMessage(), fnfe);
                        setErrorMessage(Messages.getString("DefaultExternalTableDataWizardPage.Export.FileNotFound")); //$NON-NLS-1$
                    }catch(IOException ioe){
                        DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ioe.getMessage(), ioe);
                        setErrorMessage(Messages.getString("DefaultExternalTableDataWizardPage.Export.IOError")); //$NON-NLS-1$
                    }
                }
            }
        }
    }
    
    /**
     * returns a String array of file extensions used in the Filter 
     * of the FileDialog for both Import and Export.
     * @see #handleExport()
     * @see #handleImport()
     * @return a String array of file extensions for the FileDialogs filter.
     */
    protected String[] getFileExtensions(){
        String[] extension = new String[2];
        extension[0] ="*.*"; //$NON-NLS-1$
        extension[1] ="*.txt"; //$NON-NLS-1$
        return extension;
    }
    
    
    /**
     * if true is returned the import/export functionality uses a FileInputStream to read/write the file.
     * If false is returned  the import/export functionality uses a FileReader to read/write the file.
     */
    protected boolean isBinaryFile(){
    	// work around to determine the input type: 
        //   deserialize an empty string
        //   return type (byte[] or String) determines read method of input file:
        //   byte[]  -> read as binary data
        //   String  -> read as character/text data
        Object workAround = deserializeInput(""); //$NON-NLS-1$
        if (workAround instanceof byte[]){
        	return true;
        }
        return false;
    }
    
    /**
     * returns a byte array of the content to be exported to the file system or null.
     * Expects a deserialized Object as input. This method is called from within the handleExport method
     * and needs to be overwritten if the deserialize returns something different than a String or byte array.
     * 
     * @param obj the deserialized 
     * @return
     */
    protected byte[] getByteContentForExport(Object exportableEditorContent){
        if (exportableEditorContent instanceof byte[]){
            return (byte[])exportableEditorContent;
        }
        return null;
    }
    
    /**
     * Returns the current content of the edit widget. 
     * @return the content of the edit widget
     */
    protected Object getEditorAreaContent(){
        // we dont need to deserialize
        // as we deal with Strings
        return textWidget.getText();
    }
    
    /**
     * Sets the content of the edit widget to the given input.
     * @param content the content to be set 
     */
    protected void setEditorAreaContent(Object content){
    	String serializedContent = null;
    	if (content instanceof String){
    		serializedContent = (String)content;
    	} else{
    		serializedContent = serializeInput(content);
    	}
        if (serializedContent == null){
            hasNullValue = true;  // input is still null
            textWidget.setText(""); //$NON-NLS-1$
        } else{
            textWidget.setText(serializedContent);
            hasNullValue = false;
        }
    }
    
    protected void setEditorAreaContentFromFile (String fname) {
    	File aFile = new File(fname);
    	if(aFile.canRead()){
    		try{
    			if (isBinaryFile()){
    				FileInputStream fis = new FileInputStream(aFile);
    				BufferedInputStream bis = new BufferedInputStream(fis);
    				byte b[] = new byte[bis.available()];
    				bis.read(b);
    				bis.close();
    				setEditorAreaContent(b);
    			} else{                	
    				StringBuffer input = new StringBuffer();
    				input.append(DataUIPlugin.getFileContentWithEncoding(fname));
    				setEditorAreaContent(input.toString());
    			} 
    		}catch(FileNotFoundException fnfe){
    			DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, fnfe.getMessage(), fnfe);
    			setErrorMessage(Messages.getString("DefaultExternalTableDataWizardPage.Import.FileNotFound")); //$NON-NLS-1$
    		}catch(IOException ioe){
    			DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ioe.getMessage(), ioe);
    			setErrorMessage(Messages.getString("DefaultExternalTableDataWizardPage.Import.IOError")); //$NON-NLS-1$
    		}
    	}    	
    }

    /**
     * Returns the edit widget 
     * @return the edit widget
     */
    protected Control getEditWidget(){
        return textWidget;
    }
}    
