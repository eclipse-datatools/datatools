/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */
package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.profile.ProfileFileExtension;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

/**
 * Internal class that displays the contents of the host resource folder.
 * It is used when creating a connection profile store and when selecting
 * a connection profile store for use. 
 *
 * @since 3.2.6 (DTP 1.9.2)
 */
public class ProfilePathSelectionDialog extends ElementTreeSelectionDialog
{
	static final String PLUGIN_ID = "org.eclipse.datatools.connectivity.oda.design.ui"; //$NON-NLS-1$
	static final String TITLE = Messages.profilePage_pageTitle;
	static final String TITLE_CREATE = Messages.profileStoreCreationDialog_title;
    static final String FILTER_ALLOW_ALL = ".*"; //$NON-NLS-1$

    private static final String EXT_SEPERATOR = ProfileFileExtension.FILE_EXT_SEPARATOR;
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	
	private final static Image IMG_FOLDER = PlatformUI.getWorkbench( )
		.getSharedImages( )
		.getImage( ISharedImages.IMG_OBJ_FOLDER );

	private final static Image IMG_FILE = PlatformUI.getWorkbench( )
		.getSharedImages( )
		.getImage( ISharedImages.IMG_OBJ_FILE );
	
	private File topDir;
	private boolean isProfileStoreCreation = false;
    private Text txtFilter;
	private Text txtFile;
	private String txtFileContent = null;
    private String storedPath = null;
    private String defaultExtension;
    private TreeViewerFilter treeViewerFilter = new TreeViewerFilter();

    public ProfilePathSelectionDialog( Shell parent, File topDir, boolean isProfileCreation, 
            String storedPath, String defaultExtension )
	{
		super( parent, new LabelProvider( ), new ContentProvider( isProfileCreation ) );
		
		assert topDir != null;
		
		this.setValidator( new SelectionValidator( ) );
		this.setInput( topDir.getAbsolutePath( ) );		
		this.topDir = topDir;
		this.isProfileStoreCreation = isProfileCreation;
		if( isProfileCreation )
			this.setTitle( TITLE_CREATE );
		else
			this.setTitle( TITLE );
		this.storedPath = storedPath;
        this.defaultExtension = defaultExtension;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.ElementTreeSelectionDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent )
	{
        createFilterControl(parent);        
        
        if( ProfileFileExtension.exists( defaultExtension ) )
            txtFilter.setText(EXT_SEPERATOR + defaultExtension); 
        
        txtFilter.addModifyListener( new ModifyListener( ) {

            public void modifyText(ModifyEvent arg0) 
            {
                getTreeViewer().refresh();
            }
        } );
        
		Control c = super.createDialogArea( parent );

		if( isProfileStoreCreation )
		{
            createFileControl(parent);          
		}
		
		if( storedPath != null && storedPath.trim().length() > 0 )
		{	
            File f = new File(storedPath);          
            if( !f.exists() && isProfileStoreCreation )
            {
                String newFileName = f.getName();
                f = f.getParentFile();
                txtFile.setText(newFileName);
            }
            int level = 1;
            File parentFile = (f != null) ? f.getParentFile() : null;
            while( parentFile != null && 
                    !parentFile.getAbsolutePath().equals( topDir.getAbsolutePath() ) )
            {
                parentFile = parentFile.getParentFile();
                level++;
            }
            getTreeViewer( ).expandToLevel(parentFile, level);
            Widget w = getTreeViewer( ).testFindItem(f);
            if( w == null )
            {
                getTreeViewer( ).expandAll(); //Worse case approach
            }
            setInitialSelection(f); //The selection needs to be visible in the TreeViewer for this to work           
		}
		else
            getTreeViewer( ).expandToLevel(2);		
		
        getTreeViewer().addFilter( treeViewerFilter );
		return c;
	}

    /* (non-Javadoc)
     * @see org.eclipse.ui.dialogs.SelectionDialog#createMessageArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Label createMessageArea( Composite composite )
    {
        // A message area is not required, therefore do not create the empty message label
        return null;
    }

    private void createFilterControl(Composite parent) 
    {
        Composite compositeFilter = new Composite( parent, SWT.NONE );
        {
            GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
            gridData.horizontalSpan = 3;
            gridData.grabExcessHorizontalSpace = true;
            gridData.minimumWidth = 360;
            gridData.horizontalIndent = 10;
            compositeFilter.setLayoutData( gridData );
            FormLayout layout = new FormLayout( );
            compositeFilter.setLayout( layout );
        }
        
        final Label filterLabel = new Label(compositeFilter, SWT.NONE);
        filterLabel.setText(Messages.profilePage_button_browse_filter);
        {
            FormData data = new FormData( );
            data.top = new FormAttachment( 0, 18 );
            data.left = new FormAttachment( 0, 5 );
            filterLabel.setLayoutData( data );
        }
        filterLabel.setToolTipText(Messages.profilePage_button_browse_filter_tooltiptext);
        
        txtFilter = new Text(compositeFilter, SWT.BORDER);
        {
            FormData data = new FormData( );
            data.top = new FormAttachment( 0, 15 );
            data.left = new FormAttachment( filterLabel, 5, 5 );
            data.right = new FormAttachment( 100, -10 );
            txtFilter.setLayoutData(data);
        }
        txtFilter.setToolTipText(Messages.profilePage_button_browse_filter_tooltiptext);
    }
    
    private void createFileControl(Composite parent) 
    {
        Composite composite = new Composite( parent, SWT.NONE );
        {
            GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
            gridData.horizontalSpan = 3;
            gridData.grabExcessHorizontalSpace = true;
            gridData.minimumWidth = 360;
            gridData.horizontalIndent = 10;
            composite.setLayoutData( gridData );
            FormLayout layout = new FormLayout( );
            composite.setLayout( layout );
        }
        
        final Label fileLabel = new Label(composite, SWT.NONE);
        fileLabel.setText(ConnectivityUIPlugin.getDefault().getResourceString(
                "ExportProfilesDialog.label.text")); //$NON-NLS-1$
        {
            FormData data = new FormData( );
            data.top = new FormAttachment( 0, 8 );
            data.left = new FormAttachment( 0, 5 );
            fileLabel.setLayoutData( data );
        }
        
        txtFile = new Text(composite, SWT.BORDER);
        {
            FormData data = new FormData( );
            data.top = new FormAttachment( 0, 5 );
            data.left = new FormAttachment( fileLabel, 5, 5 );
            data.right = new FormAttachment( 100, -10 );
            txtFile.setLayoutData(data);
        }
    }
	
	protected void okPressed() 
	{
		if(isProfileStoreCreation)
        {
            txtFileContent = txtFile.getText();
            if( txtFileContent.trim().length() == 0  )
            {
                MessageDialog.openError(getShell(), Messages.ui_errorLabel,
                        Messages.profilePage_browse_noselection_error);
                return;             
            }
        }
		super.okPressed();
	}
	
	private static class ContentProvider implements ITreeContentProvider
	{
		private boolean isProfileCreation = false;

		public ContentProvider(boolean isProfileCreation) 
		{
			this.isProfileCreation = isProfileCreation;
		}

		public Object[] getChildren( Object arg )
		{
			if ( arg instanceof File )
			{
				return ProfilePathSelectionDialog.getChildren( (File)arg, isProfileCreation );
			}
			return null;
		}

		public Object getParent( Object arg0 )
		{
			return null;
		}

		public boolean hasChildren( Object arg )
		{
            Object[] children = getChildren( arg );
            return children != null ? children.length > 0 : false;
		}

		public Object[] getElements( Object arg )
		{
			if ( arg instanceof String )
			{
				return new Object[]{ new File((String)arg) };
			}
			return null;
		}

		public void dispose( )
		{
			
		}

		public void inputChanged( Viewer arg0, Object arg1, Object arg2 )
		{
			
		}
	}
	
	private static class LabelProvider implements ILabelProvider
	{

		public Image getImage( Object arg )
		{
			if ( arg instanceof File )
			{
				if ( ((File)arg).isFile( ) )
				{
					return IMG_FILE;
				}
				return IMG_FOLDER;
			}
			return null;
		}

		public String getText( Object arg0 )
		{
			if ( arg0 instanceof File )
			{
			    File f = (File)arg0;
                if ( f.getName( ).trim( ).length() == 0 )
			    {
			    	//For the case "File("C:\\")"
			    	return f.getPath( );
			    }
				return f.getName( );
			}
			return EMPTY_STRING;
		}

		public void addListener( ILabelProviderListener arg0 )
		{
			
		}

		public void dispose( )
		{
			
		}

		public boolean isLabelProperty( Object arg0, String arg1 )
		{
			return false;
		}

		public void removeListener( ILabelProviderListener arg0 )
		{
			
		}
		
	}
	
	private class SelectionValidator implements ISelectionStatusValidator
	{
		
		public IStatus validate( Object[] selections )
		{
			if ( selections != null && selections.length > 0 )
			{
				for ( Object o : selections )
				{
					if ( o instanceof File )
					{
						File selFile = ((File)o);
						if ( isProfileStoreCreation || 
								(!isProfileStoreCreation && selFile.isFile( )) )
						{
							if( isProfileStoreCreation && selFile.isFile( ) )
							{
								txtFile.setText( selFile.getName() );								
							}
							return new Status( IStatus.OK,
									PLUGIN_ID,
									IStatus.OK,
									EMPTY_STRING,
									null );
						}
					}
				}
			}
			return new Status( IStatus.ERROR,
					PLUGIN_ID,
					IStatus.ERROR,
					EMPTY_STRING,
					null );
		}
		
		
	}
	
	private static File[] getChildren( File f, final boolean isProfileCreation )
	{
		if ( !f.isDirectory( ) )
		{
			return new File[0];
		}
		File[] result 
			= f.listFiles( new FileFilter( )
				{
					public boolean accept( File child )
					{
                        //Return true so that the content provider provides all files
                        //Files are filtered out based on the ViewerFilter
						return true; 
					}	
				});
		if ( result != null )
		{
			Arrays.sort( result, new FileComparator( ) );
		}
		return result == null ? new File[0] : result;
	}
	
	public String[] getSelectedItems( )
	{
		List<String> result = new ArrayList<String>( );
		Object[] selected = this.getResult( ) == null ? new Object[0] : this.getResult( );
		for ( Object o : selected )
		{
			File f = (File)o;
			if ( isProfileStoreCreation )
			{
                String path = EMPTY_STRING; 
                
                //If a file does not have an extension provided by the user
                //apply the default extension.
                if( txtFileContent != null && txtFileContent.length() > 0 &&
                        ! txtFileContent.contains(EXT_SEPERATOR) &&
                        ProfileFileExtension.exists( defaultExtension ) )
                    txtFileContent += EXT_SEPERATOR + defaultExtension; 
                
                if( f.isFile( ) )
                    path = f.getParentFile().toURI().toString() + txtFileContent;
                else
                    path = f.toURI( ).toString() + txtFileContent;              
				URI filePath = URI.create(path);
				URI relative = topDir.toURI( ).relativize( filePath );
				result.add( relative.getPath( ) );
			}
			else if ( !isProfileStoreCreation && f.isFile( ) )
			{
				URI relative = topDir.toURI( ).relativize( f.toURI( ) );
				result.add( relative.getPath( ) );
			}
		}
		return result.toArray( new String[0] );
	}
	
    /**
     * Helps compare two files so that directories are all displayed before
     * the files. In addition the files and directories are sorted
     * in their groups.
     */
    private static class FileComparator implements Comparator<File>, Serializable
	{
		private static final long serialVersionUID = 1L;

		public int compare( File o1, File o2 )
		{
			if ( o1.isDirectory( ) && o2.isDirectory( ) )
			{
				return o1.getName( ).compareTo( o2.getName( ) );
			}
			else if ( o1.isFile( ) && o2.isFile( ) )
			{
				return o1.getName( ).compareTo( o2.getName( ) );
			}
			else if ( o1.isDirectory( ) && !o2.isDirectory( ))
			{
				return -1;
			}
			else
			{
				//o1 is not a directory but o2 is a directory
				return 1;
			}
		}	
	}	
    
    /**
     * A TreeViewer Filter class that decides which files should be visible in the
     * tree viewer.
     * It gets the filter to use from the ProfilePathSelectionDialog's txtFilter 
     * Text control.
     */
    private class TreeViewerFilter extends ViewerFilter
    {
        private static final String FILTER_SEPARATOR = ","; //$NON-NLS-1$       
        
        /*
         * (non-Javadoc)
         * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
         */
        public boolean select(Viewer arg0, Object arg1, Object arg2) 
        {
            if( arg2 instanceof File )
            {
                File file = ((File)arg2);
                if( file.isDirectory() ) //Don't filter out any directories
                    return true;
                else
                {
                    String txtFilterContent = txtFilter.getText();
                    if( txtFilterContent.contains(FILTER_SEPARATOR)) //list of filters separated 
                    {
                        String[] filters = txtFilterContent.split(FILTER_SEPARATOR);
                        for( String filter : filters )
                        {
                            if( showFile(file, filter) )
                                return true;
                        }                       
                    }
                    else //single filter
                    {       
                        return showFile(file, txtFilterContent);
                    }
                }
            }
            return false;
        }

        /**
         * Decides whether the file should be displayed in the TreeViewer or not based on the filter
         * @param file  
         * @param filter
         * @return true if file should be shown false if it should be hidden
         */
        private boolean showFile( File file, String filter ) 
        {
            if( !filter.contains( EXT_SEPERATOR ) ) //Not valid filter format, so don't filter anything
                return true;

            filter = filter.trim();
            if( filter.endsWith( FILTER_ALLOW_ALL ) ) //Allow everything
                return true;                                            
            
            String filterExt = filter.substring( filter.lastIndexOf(EXT_SEPERATOR)+1 );

            String fileName = file.getName();
            if( fileName.contains( EXT_SEPERATOR ) )
            {
                String fileExt = fileName.substring( fileName.lastIndexOf(EXT_SEPERATOR)+1 );
                if( fileExt.equals( filterExt ) )
                    return true;
            }
            return false;
        }       
    }

}
