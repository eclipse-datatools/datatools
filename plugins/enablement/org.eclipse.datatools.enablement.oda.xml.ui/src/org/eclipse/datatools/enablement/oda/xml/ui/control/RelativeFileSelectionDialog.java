/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.xml.ui.control;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

public class RelativeFileSelectionDialog extends ElementTreeSelectionDialog
{

	private final static Image IMG_FOLDER = PlatformUI.getWorkbench( )
			.getSharedImages( )
			.getImage( ISharedImages.IMG_OBJ_FOLDER );

	private final static Image IMG_FILE = PlatformUI.getWorkbench( )
			.getSharedImages( )
			.getImage( ISharedImages.IMG_OBJ_FILE );

	private File rootFolder;

	public RelativeFileSelectionDialog( Shell parent, String rootFolder,
			String[] extensions )
	{
		super( parent, new LabelProvider( ), new ContentProvider( extensions ) );

		assert rootFolder != null;
		this.rootFolder = new File( rootFolder );

		this.setValidator( new SelectionValidator( ) );
		this.setInput( this.rootFolder.getAbsolutePath( ) );
		this.setTitle( Messages.getString( "SelectRelativeFileDialog.Title" ) ); //$NON-NLS-1$
		this.setAllowMultiple( false );
	}

	public RelativeFileSelectionDialog( Shell parent, String rootFolder )
	{
		this( parent, rootFolder, null );
	}

	public URI getSelected( ) throws URISyntaxException
	{
		Object[] selection = getResult( );
		if ( selection != null && selection.length > 0 )
		{
			if ( selection[0] instanceof File )
			{
				URI baseURI = rootFolder.toURI( );
				return baseURI.relativize( ( (File) selection[0] ).toURI( ) );
			}
		}
		return null;
	}

	public String[] getSelectedItems( )
	{
		List<String> result = new ArrayList<String>( );
		Object[] selected = this.getResult( ) == null ? new Object[0]
				: this.getResult( );
		for ( Object o : selected )
		{
			File f = (File) o;
			if ( f.isFile( ) )
			{
				URI relative = rootFolder.toURI( ).relativize( f.toURI( ) );
				result.add( relative.getPath( ) );
			}
		}
		return result.toArray( new String[0] );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.dialogs.ElementTreeSelectionDialog#createDialogArea(org
	 * .eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea( Composite parent )
	{
		Control c = super.createDialogArea( parent );
		this.getTreeViewer( ).expandToLevel( 2 );
		return c;
	}

	private static class ContentProvider implements ITreeContentProvider
	{

		private String[] exts;
		private final String ALL_EXT = "*.*"; //$NON-NLS-1$

		public ContentProvider( String[] extensionFilter )
		{
			if ( extensionFilter != null )
			{
				ArrayList<String> filters = new ArrayList<String>( );
				for ( int i = 0; i < extensionFilter.length; i++ )
				{
					String ext = extensionFilter[i].toLowerCase( Locale.US );
					if ( ALL_EXT.equals( ext ) )
						continue;

					filters.add( ext.substring( 1 ) );
				}
				exts = filters.toArray( new String[]{} );
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang
		 * .Object)
		 */
		public Object[] getChildren( Object arg0 )
		{
			if ( arg0 instanceof File )
			{
				File f = (File) arg0;
				if ( !f.isDirectory( ) )
				{
					return new File[0];
				}
				File[] result = f.listFiles( new FileFilter( ) {

					public boolean accept( File child )
					{
						if ( child.isDirectory( ) )
						{
							return true;
						}
						return filter( child.getAbsolutePath( ) );
					}
				} );
				if ( result != null )
				{
					Arrays.sort( result, new FileComparator( ) );
				}
				return result == null ? new File[0] : result;
			}
			return null;
		}

		private boolean filter( String fileName )
		{
			for ( int k = 0; exts != null && k < exts.length; k++ )
			{
				if ( fileName.toLowerCase( Locale.US ).endsWith( exts[k] ) )
					return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang
		 * .Object)
		 */
		public Object getParent( Object arg0 )
		{
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang
		 * .Object)
		 */
		public boolean hasChildren( Object arg0 )
		{
			if ( arg0 instanceof File )
			{
				File f = (File) arg0;
				return getChildren( f ).length > 0;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang
		 * .Object)
		 */
		public Object[] getElements( Object arg0 )
		{
			if ( arg0 instanceof String )
			{
				return new Object[]{
					new File( (String) arg0 )
				};
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose( )
		{
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
		 * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged( Viewer arg0, Object arg1, Object arg2 )
		{
		}
	}

	private static class LabelProvider implements ILabelProvider
	{

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage( Object arg0 )
		{
			if ( arg0 instanceof File )
			{
				File f = (File) arg0;
				if ( f.isFile( ) )
				{
					return IMG_FILE;
				}
				return IMG_FOLDER;
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText( Object arg0 )
		{
			if ( arg0 instanceof File )
			{
				File f = (File) arg0;
				if ( f.getName( ).trim( ).equals( "" ) ) //$NON-NLS-1$
				{
					// For the case "File("C:\\")"
					return f.getPath( );
				}
				return f.getName( );
			}
			return ""; //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse
		 * .jface.viewers.ILabelProviderListener)
		 */
		public void addListener( ILabelProviderListener arg0 )
		{
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
		 */
		public void dispose( )
		{
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java
		 * .lang.Object, java.lang.String)
		 */
		public boolean isLabelProperty( Object arg0, String arg1 )
		{
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse
		 * .jface.viewers.ILabelProviderListener)
		 */
		public void removeListener( ILabelProviderListener arg0 )
		{
		}

	}

	private static class SelectionValidator
			implements
				ISelectionStatusValidator
	{

		public IStatus validate( Object[] selections )
		{
			if ( selections != null && selections.length > 0 )
			{
				for ( Object o : selections )
				{
					if ( o instanceof File )
					{
						if ( ( (File) o ).isFile( ) )
						{
							return new Status( IStatus.OK,
									"org.eclipse.datatools.connectivity.oda.xml.ui", //$NON-NLS-1$
									IStatus.OK,
									"", //$NON-NLS-1$
									null );
						}
					}
				}
			}
			return new Status( IStatus.ERROR,
					"org.eclipse.datatools.connectivity.oda.xml.ui", //$NON-NLS-1$
					IStatus.ERROR,
					"", //$NON-NLS-1$
					null );
		}

	}

	public static class FileComparator
			implements
				Comparator<File>,
				Serializable
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
			else if ( o1.isDirectory( ) && !o2.isDirectory( ) )
			{
				return -1;
			}
			else
			{
				// o1 is not a directory but o2 is a directory
				return 1;
			}
		}
	}
}
