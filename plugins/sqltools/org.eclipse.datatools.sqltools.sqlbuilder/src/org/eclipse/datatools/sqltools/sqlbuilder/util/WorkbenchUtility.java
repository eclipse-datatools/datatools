/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
//TODO add tracing
package org.eclipse.datatools.sqltools.sqlbuilder.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManagerProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

public class WorkbenchUtility {

    public static final String SQL_BUILDER_ID = "org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderEditor"; // $NON-NLS-1$
 //   public static final String SQL_EDITOR_ID = "org.eclipse.wst.rdb.sqleditor.SQLEditor"; // $NON-NLS-1$
    public static final String SQL_EDITOR_ID = "org.eclipse.datatools.sqltools.sqleditor"; // $NON-NLS-1$
    public static final String QUERY_EXTENSION = "sql"; //$NON-NLS-1$
    public static final String QUERY_DDL_EXTENSION = "ddl"; //$NON-NLS-1$ 

    /**
     * Gets the current editor.
     * 
     * @return the current editor
     */
    static public IEditorPart getActiveEditor() {
        IWorkbench workbench = SQLBuilderPlugin.getPlugin().getWorkbench();
        IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
        IEditorPart editorPart = workbenchWindow.getActivePage().getActiveEditor();

        return editorPart;
    }

    /**
     * Gets a SQL file resource with the given name in the given project.  The
     * file will have the extension ".sql".
     *  
     * @param projectName the name of the project containing the statement
     * @param resourceName the name of the file resource to get
     * @return the file resource or <code>null</code> if a file resource with
     * for the given name cannot be opened
     */
    static public IFile getSQLFileResource(String projectName, String resourceName) {
        IFile stmtFile = null;

        Path stmtPath = WorkbenchUtility.getSQLResourcePath(projectName, resourceName);
        stmtFile = ResourcesPlugin.getWorkspace().getRoot().getFile(stmtPath);

        return stmtFile;
    }

    /**
     * Gets a <code>Path</code> object for the given project name and SQL resource name.  The path
     * is formed as /projectName/resourceName.sql
     * 
     * @param projectName the name of the project to use to create the path
     * @param resourceName the resource name to use to create the path
     * @return the new <code>Path</code> object 
     */
    static public Path getSQLResourcePath(String projectName, String resourceName) {
        // [wsdbu00056635] bgp 09May2006
        String stmtPathStr = File.separatorChar + projectName + File.separatorChar + resourceName;
        String stmtPathExt = "";
        
        // See if there is a "." in the path (not at the very beginning 
        // or very end of the path).  If there is, assume the path has a file 
        // extension and get it.
        int dotIndex = stmtPathStr.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < stmtPathStr.length()-1) {
            // Get the current file extension.
            stmtPathExt = stmtPathStr.substring(dotIndex + 1);
        }
        
        // Check whether the file extension, if any, is a "known" extension.
        if (stmtPathExt.equalsIgnoreCase(QUERY_EXTENSION) 
         || stmtPathExt.equalsIgnoreCase(QUERY_DDL_EXTENSION)) {
            // do nothing, since already have an appropriate file extension
        }
        else {
            // Otherwise add the default extension.
            stmtPathStr = stmtPathStr + "." + QUERY_EXTENSION;
        }

        Path stmtPath = new Path(stmtPathStr);
        return stmtPath;
    }

    /**
     * Gets a list of <code>QuerySelectStatement</code> objects contained within
     * the given project.
     * 
     * @return the list of
     */
    public static List getSelectStatementsFromProject(IProject project,
			SQLDomainModel domainModel) {
		List selectStmtList = new ArrayList();
		String dbProduct = null;
		String dbVersion = null;
		SQLQueryParserManager parserManager = null;
		
		if (project != null) {
			IResource[] resources = getSQLStatements(project);
			String fileContent = "";
			
			// Obtain parserManager for correct product and version
			DatabaseDefinition dbdef = domainModel.getDatabaseDefinition();
			if (dbdef != null) {
				dbProduct = domainModel.getDatabaseDefinition().getProduct();
				dbVersion = domainModel.getDatabaseDefinition().getVersion();
				
			}
			parserManager = SQLQueryParserManagerProvider.getInstance()
					.getParserManager(dbProduct, dbVersion);
			
			for (int i = 0; i < resources.length; i++) {
				boolean contains = false;
				IFile file = (IFile) resources[i];
				//check if the statement is already in the cached list of
				// select statements
				contains = selectStmtList.contains(file);
				// add the statement to the cache if it not already in the cache
				// and is a SELECT statement
				if (!contains) {
					try {
						fileContent = "";
						fileContent = (readFileContentsToString(file, false))
								.trim();
					} catch (Exception e) {}

					QueryStatement parsedStatement = null;
					try {
						parsedStatement = parserManager.checkSyntax(fileContent);
					} 
					catch (SQLParserException e1) {
						// ignore
					}
					catch (SQLParserInternalException e) {
						// ignore
					}

					if (parsedStatement instanceof QuerySelectStatement)
						selectStmtList.add(file);
				}
			}
		}
		return selectStmtList;
	}
  
    /**
     * Get the SQL statements given a project.  Looks for resources
     * in the project containing the "sql" or "ddl" file extension.
     * <p>
     * @param aProj the database development project
     * @return an array of IResources of SQL Statements.
     */
    public static IResource[] getSQLStatements(IProject aProj) {
       List sqlStmts = new ArrayList();
       try {
          IResource[] all = aProj.members();
          for (int i = 0; i < all.length; i++) {
             String ext = all[i].getFileExtension();
             if (QUERY_EXTENSION.equalsIgnoreCase(ext) ||
                 QUERY_DDL_EXTENSION.equalsIgnoreCase(ext)) {  
                sqlStmts.add(all[i]);
             }
          }
       }
       catch (CoreException e) { /* ignore */ }
       
       return (IResource[]) sqlStmts.toArray(new IResource[sqlStmts.size()]);

    }

    /**
     * Opens the given editor input object using the given editor.  The editor
     * is opened in a separate thread.
     * 
     * @param editorInput the editor input for the editor to open
     * @param editorId the editor ID (defined in plugin.xml) of the editor to use
     */
    static public void openEditor(final IEditorInput editorInput, final String editorId) {
        if (editorInput != null) {
            IWorkbench workbench = SQLBuilderPlugin.getPlugin().getWorkbench();
            final IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();

            Display.getDefault().asyncExec(new Runnable() {

                public void run() {
                    try {
                        workbenchWindow.getActivePage().openEditor(editorInput, editorId);
                    }
                    catch (PartInitException ex) {
                        SQLBuilderPlugin.getPlugin().getLogger().writeLog("Exception encountered when attempting to open editor: " 
                                + editorInput + "\n\n" + ex);
                    }
                }
            });
        }
    }

    /**
     * Opens the given file using the SQL Query Builder editor.
     * 
     * @param fileResource the file to open
     */
    static public void openEditor(final IFile fileResource) {
        openEditor(fileResource, SQL_BUILDER_ID);
    }

    /**
     * Opens the given file using the given editor.
     *  
     * @param fileResource the file to open
     * @param editorID the editor ID (defined in plugin.xml) of the editor to use
     */
    static public void openEditor(final IFile fileResource, final String editorID) {
        if (fileResource != null) {
            FileEditorInput editorInput = new FileEditorInput(fileResource);
            openEditor(editorInput, editorID);
        }
    }

    //TODO Trace and Log
    /**
     * Returns the contents of the given file resource as a string, optionally including
     * or suppressing newlines in the text.  Returns an empty string if the file cannot
     * be read.
     * 
     * @param fileResource the file resource to read
     * @param inclueNewlines true when newlines should be included in the generated
     * string, otherwise false
     * @return a string containing the contents of the file
     */
    public static String readFileContentsToString( IFile fileResource, boolean includeNewlines ) {
        StringBuffer strBuf = new StringBuffer();
        InputStream iStream = null;
        try {
            iStream = fileResource.getContents();
            String encoding = fileResource.getCharset();
            if (iStream != null) {
                BufferedReader in = new BufferedReader( new InputStreamReader( iStream, encoding ));
                String tmpStr = "";
                while (tmpStr != null) {
                    tmpStr = in.readLine();
                    if (tmpStr != null) {
                        strBuf.append( tmpStr );
                        if (includeNewlines == true) {
                            strBuf.append( System.getProperty( "line.separator" ));
                        }
                        else {
                            strBuf.append( " " );
                        }
                    }
                }
            }
        }
        catch (CoreException e) {
            SQLBuilderPlugin.getPlugin().getLogger().writeLog( e.getMessage() );
        }
        catch (IOException e) {
            SQLBuilderPlugin.getPlugin().getLogger().writeLog( e.getMessage() );
        }
        finally {
            /* Make sure our inputStream is closed. */
            if (iStream != null) {
                try {
                    iStream.close();
                }
                catch (IOException e) {
                    /* Ignore, not much we can do at this point. */
                }
            }
        }
        
        return strBuf.toString();
    }

    /**
     * Refreshes the given file so it will be in synch with the local file system.
     * 
     * @param fileResource the file to refresh
     * @param progressMonitor a progress monitor to use during the refresh
     * @return <code>true</code> when the file was refreshed OK, otherwise <code>false</code>
     */
    static public boolean refreshLocalWorkspaceFile(IFile fileResource, IProgressMonitor progressMonitor) {
        if (fileResource != null) {
            try {
                fileResource.refreshLocal(1, progressMonitor);
                return true;
            }
            catch (CoreException e) {
                SQLBuilderPlugin.getPlugin().getLogger().writeLog( e.getMessage() );
            }
        }

        return false;
    }

    /**
     * Saves the given SQL statement as a file resource in the given project. 
     * 
     * @param statement the SQL statement to save
     * @param projectName the name of the project into which the query is to be saved
     * @return the file resource in which the statement was saved, or <code>null</code>
     * if it wasn't saved sucessfully
     */
    public static IFile saveStatementAsSQLFileResource(QueryStatement statement, String projectName) {
        String statementName = statement.getName();
        String statementContent = statement.getSQL();
        return saveTextAsSQLFileResource(statementContent, statementName, projectName);
    }

    /**
     * Saves the given string as the content as a SQL file resource with the given name in the
     * given project.  The file will have the extension ".sql" by default.
     * 
     * @param content the string to save
     * @param resourceName the name of the SQL resource (the file name)
     * @param projectName the project in which to save the statement
     * @return the file resource in which the text was saved, or <code>null</code>
     * if it wasn't saved sucessfully
     */
    public static IFile saveTextAsSQLFileResource(String content, String resourceName, String projectName) {
        IFile fileResource = getSQLFileResource(projectName, resourceName);
        saveStringAsFileResource( fileResource, content );

        return fileResource;
    }

    /**
     * Saves the given string as the content of the given file resource.
     *
     * @param fileResource the file resource in which to save the string 
     * @param content the string to save
     */
    public static void saveStringAsFileResource( IFile fileResource, String content ) {
        ByteArrayInputStream iStream = null;
        try {
            // [wsdbu000] bgp 10May2006 - begin
            // Use the character set encoding defined in the Eclipse preferences. 
            String encoding = ResourcesPlugin.getEncoding();
            if (fileResource.exists()) {
                try {
                    iStream = new ByteArrayInputStream( content.getBytes(encoding) );
                }
                catch(UnsupportedEncodingException e) {
                    // Use the default encoding
                    iStream = new ByteArrayInputStream( content.getBytes() );
                }
                fileResource.setContents( iStream, true, false, null );
            }
            else {
                File file = fileResource.getLocation().toFile();
                if (file.exists()) {
                    // TODO: handle problem where the file resource with this
                    //       name doesn't exist but a java.io.File already does
                }
                else {
                    try {
                        iStream = new ByteArrayInputStream( content.getBytes(encoding) );
                    }
                    catch(UnsupportedEncodingException e) {
                        // Use the default encoding
                        iStream = new ByteArrayInputStream( content.getBytes() );
                    }
                    // [wsdbu000] bgp 10May2006 - end
                    fileResource.create( iStream, false, null );
                    fileResource.setCharset(encoding, new NullProgressMonitor());
                }
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            fileResource = null;
        }
        finally {
            /* Make sure we close the inputStream. */
            if (iStream != null) {
                try {
                    iStream.close();
                }
                catch (IOException e) {
                    /* Ignore, not much we can do at this point. */
                }
            }
        }
    }
    
    /**
     * Searches a string and returns the segments containing qoutes '"'
     * @param offset the line offset
     * @param text the string text to search
     * @return an array of segments
     */
    public static int[] getSegments(int offset, String text)
    {
    	if (text == null || text.equals(""))
    	{
    		return null;
    	}
    	boolean found = true;
    	int searchIndex = 0;
    	java.util.List segmentList = new ArrayList();
    	segmentList.add(new Integer(0));
    	while (found)
    	{
    		int index = text.indexOf('"', searchIndex);
    		if (index == -1)
    		{
    			found = false;
    		}
    		if (index > 0)
    		{
    			segmentList.add(new Integer(index));
    			searchIndex = index + 1;
    		}
    	}    	
    	int temp[] = new int[segmentList.size()];
    	for (int i=0;i<segmentList.size();i++)
    	{
    		temp[i] = ((Integer)segmentList.get(i)).intValue();    		
    	}
    	return temp;    	
    }
}