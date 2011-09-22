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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
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
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	
	private final static Image IMG_FOLDER = PlatformUI.getWorkbench( )
		.getSharedImages( )
		.getImage( ISharedImages.IMG_OBJ_FOLDER );

	private final static Image IMG_FILE = PlatformUI.getWorkbench( )
		.getSharedImages( )
		.getImage( ISharedImages.IMG_OBJ_FILE );
	
	private File topDir;
	private boolean isProfileStoreCreation = false;
	private Text txtFile;
	private String txtFileContent = null;
	String storedPath = null;

	public ProfilePathSelectionDialog( Shell parent, File topDir, boolean isProfileCreation, String storedPath )
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
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.ElementTreeSelectionDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent )
	{
		Control c = super.createDialogArea( parent );

		if( isProfileStoreCreation )
		{
			Composite composite = new Composite( parent, SWT.NONE );
			{
				GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
				gridData.horizontalSpan = 3;
				gridData.grabExcessHorizontalSpace = true;
				gridData.minimumWidth = 360;
				gridData.horizontalIndent = 10;
				composite.setLayoutData( gridData );
			}
			
		    FormLayout layout = new FormLayout( );
			composite.setLayout( layout );
			
	        final Label label = new Label(composite, SWT.NONE);
	        label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
	                "ExportProfilesDialog.label.text")); //$NON-NLS-1$
	        {
				FormData data = new FormData( );
				data.top = new FormAttachment( 0, 5 );
				data.left = new FormAttachment( 0, 5 );
				label.setLayoutData( data );
	        }
	        
	        txtFile = new Text(composite, SWT.BORDER);
	        {
				FormData data = new FormData( );
				data.top = new FormAttachment( 0, 5 );
				data.left = new FormAttachment( label, 5, 5 );
				data.right = new FormAttachment( 100, -10 );
				txtFile.setLayoutData(data);
	        }	        
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
            File parentFile = f.getParentFile();
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
		
		return c;
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
			if ( arg instanceof File )
			{
				return ProfilePathSelectionDialog.getChildren( (File)arg, isProfileCreation ).length > 0;
			}
			return false;
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
						//A profile store does not have an associated extension therefore 
						//show all files and folders.
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
                if( !f.getName().equals(txtFileContent) )
                    path = f.getParentFile().toURI().toString() + txtFileContent;
                else
                    path = f.toURI( ).toString();

                if( !f.isFile( ) )
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
	
	public static class FileComparator implements Comparator<File>, Serializable
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

}
