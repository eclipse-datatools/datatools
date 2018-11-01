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

package org.eclipse.datatools.sqltools.data.internal.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData2;
import org.eclipse.datatools.sqltools.data.internal.ui.editor.IExternalTableDataEditor;
import org.eclipse.datatools.sqltools.data.internal.ui.editor.ITableDataEditor;
import org.eclipse.datatools.sqltools.data.internal.ui.editor.TableDataEditorActionBarContributor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ibm.icu.util.StringTokenizer;

public class DataUIPlugin extends AbstractUIPlugin
{
    //The shared instance.
    private static DataUIPlugin plugin;
    private static final IPath ICONS_PATH= new Path("icons"); //$NON-NLS-1$
    
    public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.data.ui"; //$NON-NLS-1$

    protected TableDataEditorActionBarContributor tableDataEditorContributor;

    /** the list of extensions for the externalTableDataCellEditor extension point */
    protected List externalTableDataEditorExtensions;
   
    private static final String UTF_16_BE = "UTF-16BE"; //$NON-NLS-1$
	private static final String UTF_16_LE = "UTF-16LE"; //$NON-NLS-1$
	private static final String UTF_8 = "UTF-8"; //$NON-NLS-1$
    
    /**
     * The constructor.
     */
    public DataUIPlugin()
    {
        super();
        plugin = this;
        listExternalTableDataEditorExtensions();
    }

    /**
     * Returns the shared instance.
     */
    public static DataUIPlugin getDefault()
    {
        return plugin;
    }
    public TableDataEditorActionBarContributor getTableDataEditorContributor() {
        return tableDataEditorContributor;
    }
    public void setTableDataEditorContributor(
            TableDataEditorActionBarContributor tableDataEditorContributor) {
        this.tableDataEditorContributor = tableDataEditorContributor;
    }
    
	/**
	 * Method writeLog.
	 * @param severity - the severity; one of IStatus.OK, IStatus.ERROR, IStatus.INFO, or IStatus.WARNING
	 * @param code - the plug-in-specific status code, or OK
	 * @param message - a human-readable message, localized to the current locale
	 * @param exception- a low-level exception, or null if not applicable
	 */
	public void writeLog(int severity, int code, String message, Throwable exception) {
		if (message == null)
			message = ""; //$NON-NLS-1$
		getLog().log(
				new Status(severity, getBundle().getSymbolicName(), code, message, exception));
	}
    
    public static boolean isGroupIDOK(EObject o)
    {
        if (o==null)
            return false;
        ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService(); 
        String groupID = containmentService.getGroupId(o);
        return (groupID != null) && (groupID.startsWith(GroupID.CORE_PREFIX));  
    }
    
    /**
     * Gets the encoding string from the Window->Preferences menu
     * @return the encoding string to be used for saving and loading
     */
    public static String getCharacterEncoding( )
    {
 	   return ResourcesPlugin.getEncoding();
    }
    
    /**
	 * Parses the character encoding from an XML file.
	 * <p>
	 * This method assumes that the encoding will appear on the first line of the file
	 * and that it will always be in the form below:
	 * <?xml version="1.0" encoding="UTF-8"?>
	 * @param filePath the path of the file to parse
	 * @return the encoding found, or null if not found
	 */
	public static String parseEncoding(String filePath)
	{
		if (filePath == null || filePath.equals(""))
		{
			return null;
		}
		FileInputStream fileInstream = null;
		BufferedReader br = null;
		FileReader reader = null;
		try
		{
			// Check the BOM for UTF-16 encoding
			fileInstream = new FileInputStream(filePath);
			int byteOne = fileInstream.read();
			int byteTwo = fileInstream.read();
			if (byteOne == 0xFE && byteTwo == 0xFF)
			{
				// Big endian utf-16
				return UTF_16_BE;
			}
			if (byteOne == 0xFF && byteTwo == 0xFE)
			{
				// Little endian utf-16
				return UTF_16_LE;
			}
			int byteThree = fileInstream.read();
			if (byteOne == 0xEF && byteTwo == 0xBB && byteThree == 0xBF)
			{
				// UTF-8
				return UTF_8;
			}
			
			reader = new FileReader(filePath);
			br = new BufferedReader(reader);
			String text = br.readLine();			
			int index = text.indexOf("encoding") + 8; //$NON-NLS-1$
			if (index > 7)
			{
				StringBuffer sub = new StringBuffer(text.substring(index).trim());
				if (sub.charAt(0) == '=')
				{
					sub.deleteCharAt(0); //remove = from working string
					String st =sub.toString().trim(); // remove whitespaces
					if (st.charAt(0) == '"')
					{
						int qouteIndex = st.indexOf('"', 1);
						if (qouteIndex > -1)
						{
							return st.substring(1, qouteIndex);
						}
					}
				}
			}
			return null;
		}
		catch(FileNotFoundException fne)
		{
			return null;
		}
		catch (IOException ioe)
		{
			return null;
		}
		finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}
				if (reader != null)
				{
					reader.close();
				}
				if (fileInstream != null)
				{
					fileInstream.close();
				}
			}
			catch (IOException ex)
			{
				//ignore
			}
		}
	}
	
	/**
	 * Gets the file content of a file with consideration for the character
	 * encoding for that file.  XML files normally declare an encoding in the
	 * first line of the file.  
	 * <p>
	 * The content of the file is examined to determine the encoding.  If found, then
	 * that encoding is used.  If not found, then the encoding found in the workbench
	 * properties is used instead
	 * @param filePath the path to the file
	 * @return the content of the file in form of a string
	 * @exception FileNotFoundException, IOException if file not found, or some other problems.
	 */
	public static String getFileContentWithEncoding(String filePath) throws FileNotFoundException, IOException 
	{
		if (filePath == null)
		{
			return null;
		}
		String encoding = DataUIPlugin.parseEncoding(filePath);
		File aFile = new File(filePath);
		if (aFile.canRead())
		{
			FileInputStream fis = new FileInputStream(aFile);
    		InputStreamReader instream = null;
			if (encoding == null)
			{
				// can not parse encoding, use the one in Window->Properties->General->Workbench
				encoding = ResourcesPlugin.getEncoding();
			}
			if (encoding != null && !encoding.equals(""))
            {
            	instream = new InputStreamReader(fis, encoding);
            }
            else
            {
            	instream = new InputStreamReader(fis);
            }
			BufferedReader br = new BufferedReader(instream);            
            StringBuffer input = new StringBuffer();
            while(true)
            {
                String line = br.readLine();
                if (line==null)
                {
                    break;
                }
                input.append(line).append('\n'); //$NON-NLS-1$
            }
            br.close();
            fis.close();
            return input.toString();
		}
		return null;
	}
    
	/**
	 * This gets a .gif from the icons folder.
	 */
	public ImageDescriptor getImageDescriptor(String key) {
		try {			
			IPath path = ICONS_PATH.append(key + ".gif"); //$NON-NLS-1$
			URL imageURL = FileLocator.find(getBundle(), path, null);
		    return ImageDescriptor.createFromURL(imageURL);
		}
		catch (Exception e) {
			writeLog(IStatus.ERROR, 0, e.getMessage(), e);
		}
		return null;
	}	

	/**
	 * This gets a .png from the icons folder.
	 */
	public ImageDescriptor getPngImageDescriptor(String key) {
		try {	
			IPath path = ICONS_PATH.append(key + ".png"); //$NON-NLS-1$
			URL imageURL = FileLocator.find(getBundle(), path, null);
		    return ImageDescriptor.createFromURL(imageURL);
		}
		catch (Exception e) {
			writeLog(IStatus.ERROR, 0, e.getMessage(), e);
		}
		return null;
	}	
	
    /**
     * Collects all extensions for the externalTableDataEditor extension point 
     * and stores it in a list.
     */
    protected void listExternalTableDataEditorExtensions() {
        externalTableDataEditorExtensions = new ArrayList();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = registry.getExtensionPoint(PLUGIN_ID + ".externalTableDataEditor"); //$NON-NLS-1$
        IExtension[] extensions = extensionPoint.getExtensions();
        for (int i = 0; i < extensions.length; i++) {
            IExtension extension = extensions[i];
            IConfigurationElement[] elements = extension.getConfigurationElements();
            for (int j=0; j<elements.length; ++j)
                externalTableDataEditorExtensions.add(new ExternalTableDataEditorExtension(elements[j]));
        }
    }
    
    /**
     * returns the best matching IExternalTableDataEditor or null.
     * @param editor the TableDataEditor
     * @param columnIndex the index of the sql table column 
     * @return the best fitting IExternalTableDataEditor or null if none matches
     */
    public IExternalTableDataEditor newExternalTableDataCellEditor(ITableDataEditor editor, int columnIndex){
        // input validation
        if ( (editor==null) || (editor.getSqlTable()==null) || 
             (editor.getSqlTable().getColumns() == null)){
            return null;
        }
        if (columnIndex > editor.getSqlTable().getColumns().size()){
            return null;
        }

        if (editor.getTableData() instanceof ITableData2) {
            if (((ITableData2)editor.getTableData()).getResultColumns() == null || (columnIndex > ((ITableData2)editor.getTableData()).getResultColumns().size())) {
        	return null;
            }
        }

        Column sqlCol = null;
        if (editor.getTableData() instanceof ITableData2) {
            sqlCol = (Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex);
        }
        else {
            sqlCol = (Column)editor.getSqlTable().getColumns().get(columnIndex);
        }
        
        IExternalTableDataEditor externalEditor = null;
        
        try {
        	Database db = sqlCol.getTable().getSchema().getCatalog() != null?
        			sqlCol.getTable().getSchema().getCatalog().getDatabase():
        			sqlCol.getTable().getSchema().getDatabase();        	
            DataType type = sqlCol.getDataType();
            
            String vendor = db.getVendor();
            String version = db.getVersion();
            String dataType = sqlCol.getDataType().getName();

            // checks for the sqlmodel objects
            // that support a length attribute
            int length = -1;     
            boolean lengthSupported = false;
            if (type instanceof DataLinkDataType){
                lengthSupported = true;
                length = ((DataLinkDataType)type).getLength();
            }
            if (type instanceof BinaryStringDataType){
                lengthSupported = true;
                length = ((BinaryStringDataType)type).getLength();
            }
            if (type instanceof CharacterStringDataType) {
                lengthSupported = true;
                length = ((CharacterStringDataType)type).getLength();
            }
            
            ExternalTableDataEditorExtension element = null;
            
            Iterator it = externalTableDataEditorExtensions.iterator();
            while (it.hasNext()) {
                ExternalTableDataEditorExtension curElement = (ExternalTableDataEditorExtension)it.next();
                boolean matches = curElement.matches(vendor, version, dataType, lengthSupported, length) && (element==null || curElement.getScore()>element.getScore());
                if (matches)  {
                    element = curElement;
                }
            }

            if (element!=null){
                externalEditor = element.createInstance();
            }
        } catch (CoreException ex) {
            writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
        }
        return externalEditor;
    }
    
}

/**
 * an internal class holding the information of one contribution to 
 * the externalTableDataCellEditor extension point  
 */
class ExternalTableDataEditorExtension {
    protected IConfigurationElement element;
    protected String vendor;
    protected String version;
    protected String dataType;
    protected int length = -10;
    protected int score;
    protected boolean lengthProvided = false;
    
    public ExternalTableDataEditorExtension(IConfigurationElement element){
        this.element = element;
        vendor = element.getAttribute("vendor"); //$NON-NLS-1$
        version = element.getAttribute("version"); //$NON-NLS-1$
        dataType = element.getAttribute("dataType"); //$NON-NLS-1$
        String sLength = element.getAttribute("length"); //$NON-NLS-1$
        try{
            length = Integer.parseInt(sLength);
            lengthProvided = true;
        }catch (NumberFormatException nfe){
        }catch (NullPointerException npe){}
        
        score = ((vendor!=null)?1:0) + ((version!=null)?1:0) + ((dataType!=null)?1:0) + ((length>0)?1:0);
    }
    
    /**
     * returns true if this ExternalTableDataEditorExtension matches the given
     * arguments
     */
    public boolean matches(String vendor, String version, String dataType, boolean lengthSupported, int length){
        
        // no match if we have a length restriction but no datatype with length support
        if (lengthProvided && !lengthSupported) {
            return false;
        }
        // no match if length restriction doesnt apply
        if (lengthProvided && !(this.length <= length)){
            return false;
        }
        // no match if vendor,version,datatype are given but not the same
		if (this.vendor!=null && !contains(this.vendor, vendor))
			return false;
		else if (this.version!=null && !contains(this.version,version))
			return false;
		else if (this.dataType!=null && !contains(this.dataType,dataType))
			return false;
        
        return true;
    }
    
	protected boolean contains(String values, String value)
	{
		StringTokenizer st = new StringTokenizer(values, ",", false); //$NON-NLS-1$
		while (st.hasMoreElements()) {
			String s = st.nextToken();
			if (value.equals(s.trim()))
				return true;
		}
		return false;
	}
    
    public IExternalTableDataEditor createInstance() throws CoreException{
        return (IExternalTableDataEditor)element.createExecutableExtension("class"); //$NON-NLS-1$
    }
    
    public int getScore(){
        return score;
    }
}
