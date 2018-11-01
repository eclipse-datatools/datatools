/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile.ui.wizards;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.i18n.Messages;
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

	private static boolean showFolder = false;

	public RelativeFileSelectionDialog( Shell parent, File rootFolder )
	{
		super( parent, new LabelProvider( ), new ContentProvider( ) );

		assert rootFolder != null;

		this.setValidator( new SelectionValidator( ) );
		this.setInput( rootFolder.getAbsolutePath( ) );
		this.setTitle( Messages.getString( "RelativeFileSelectionDialog.Title.SelectFile" ) ); //$NON-NLS-1$
		this.rootFolder = rootFolder;
	}

	public RelativeFileSelectionDialog( Shell parent, File rootFolder,
			boolean showFolder )
	{
		super( parent, new LabelProvider( ), new ContentProvider( ) );

		assert rootFolder != null;

		RelativeFileSelectionDialog.showFolder = showFolder;

		this.setValidator( new SelectionValidator( ) );
		this.setInput( rootFolder.getAbsolutePath( ) );
		this.setTitle( Messages.getString( "RelativeFileSelectionDialog.Title.SelectFile" ) ); //$NON-NLS-1$
		this.rootFolder = rootFolder;
	}

	public URI getSelectedURI( ) throws URISyntaxException
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

		public Object[] getChildren( Object arg0 )
		{
			if ( arg0 instanceof File )
			{
				File f = (File) arg0;
				return RelativeFileSelectionDialog.getChildren( f );
			}
			return null;
		}

		public Object getParent( Object arg0 )
		{
			return null;
		}

		public boolean hasChildren( Object arg0 )
		{
			if ( arg0 instanceof File )
			{
				File f = (File) arg0;
				return RelativeFileSelectionDialog.getChildren( f ).length > 0;
			}
			return false;
		}

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

		public void dispose( )
		{

		}

		public void inputChanged( Viewer arg0, Object arg1, Object arg2 )
		{

		}
	}

	private static class LabelProvider implements ILabelProvider
	{

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

	private static class SelectionValidator implements
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
						if ( ( showFolder && ( (File) o ).isDirectory( ) )
								|| ( !showFolder && ( (File) o ).isFile( ) ) )
						{
							return new Status( IStatus.OK,
									"org.eclipse.datatools.connectivity.oda.flatfile.ui", //$NON-NLS-1$
									IStatus.OK,
									"", //$NON-NLS-1$
									null );
						}
					}
				}
			}
			return new Status( IStatus.ERROR,
					"org.eclipse.datatools.connectivity.oda.flatfile.ui", //$NON-NLS-1$
					IStatus.ERROR,
					"", //$NON-NLS-1$
					null );
		}

	}

	private static File[] getChildren( File f )
	{
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
				if ( !showFolder )
					return true;
				else
					return false;
			}
		} );
		if ( result != null )
		{
			Arrays.sort( result, new FileComparator( ) );
		}
		return result == null ? new File[0] : result;
	}

	public String[] getSelectedItems( )
	{
		List<String> result = new ArrayList<String>( );
		Object[] selected = this.getResult( ) == null ? new Object[0]
				: this.getResult( );
		for ( Object o : selected )
		{
			File f = (File) o;
			if ( ( showFolder && f.isDirectory( ) )
					|| ( !showFolder && f.isFile( ) ) )
			{
				URI relative = rootFolder.toURI( ).relativize( f.toURI( ) );
				result.add( relative.getPath( ) );
			}
		}

		return result.toArray( new String[0] );
	}

	public static class FileComparator implements
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
