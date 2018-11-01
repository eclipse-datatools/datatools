/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;

/**
 * @author bmcnichols
 */
public class FilterListSelectionDialog extends CheckedTreeSelectionDialog
{
    public FilterListSelectionDialog( Shell parentShell, ITreeContentProvider contentProvider, ILabelProvider labelProvider )
    {
        this( parentShell, contentProvider, labelProvider, new ListSelectionFilter() );
    }

    public FilterListSelectionDialog( Shell parentShell, ITreeContentProvider contentProvider, ILabelProvider labelProvider, ListSelectionFilter filter )
    {
        super( parentShell, labelProvider, contentProvider );
        addFilter( filter );
        setMessage( ResourceLoader.getResourceLoader().queryString( "DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.MESSAGE") ); //$NON-NLS-1$
        setTitle( ResourceLoader.getResourceLoader().queryString( "DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.TITLE") ); //$NON-NLS-1$
        setContainerMode( true );
    }
    
    protected void computeResult() { setResult( checkedItems ); }    
    
	protected Control createDialogArea(Composite parent)
	{
		Composite parentComposite = new Composite( parent, SWT.NONE );
        GridLayout gridLayout = new GridLayout( 1, false );
        parentComposite.setLayout( gridLayout );
        GridData data = new GridData( GridData.FILL_BOTH );
        parentComposite.setLayoutData( data );
		
		Composite filterComposite = new Composite( parentComposite, SWT.NONE );
        GridLayout filterCompositeGridLayout = new GridLayout( 1, false );
        filterCompositeGridLayout.marginWidth = convertHorizontalDLUsToPixels( IDialogConstants.HORIZONTAL_MARGIN );
        filterCompositeGridLayout.horizontalSpacing = convertHorizontalDLUsToPixels( IDialogConstants.HORIZONTAL_SPACING );
        filterComposite.setLayout( filterCompositeGridLayout );
        filterComposite.setLayoutData( new GridData( GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL ) );
		
        Label filterLabel = new Label( filterComposite, SWT.NULL );
        filterLabel.setText( ResourceLoader.getResourceLoader().queryString( "DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.FILTERLABEL") ); //$NON-NLS-1$
        filterText = new Text( filterComposite, SWT.SINGLE | SWT.BORDER );
        filterText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL ) );
        filterText.addKeyListener( new KeyAdapter() 
        {
            public void keyReleased( KeyEvent e ) { refreshFilter( filterText.getText() ); }
        });
        
        super.createDialogArea( parentComposite );
		return parentComposite;
	}
	
	protected CheckboxTreeViewer createTreeViewer( Composite parent )
	{
	    CheckboxTreeViewer treeViewer = super.createTreeViewer( parent );
	    treeViewer.addCheckStateListener( new ICheckStateListener() 
        {
            public void checkStateChanged( CheckStateChangedEvent event ) 
            {  
                setCheckedState( getTreeViewer(), event.getElement() ); 
            }
        } );
	    return treeViewer;
	}
	
    private void setCheckedState( CheckboxTreeViewer treeViewer, Object element )
    {
        ITreeContentProvider contentProvider = ( ITreeContentProvider )treeViewer.getContentProvider();
        if( contentProvider.hasChildren( element ) )
        {
            Iterator childrenIterator = getAllChildren( element, contentProvider, treeViewer.getFilters() ).iterator();
            while( childrenIterator.hasNext() )
            {
                Object child = childrenIterator.next();
                if( treeViewer.getChecked( child ) ) checkedItems.add( child );
                else checkedItems.remove( child );
            }
        }
        else if( treeViewer.getChecked( element ) ) checkedItems.add( element );
        else checkedItems.remove( element );
    }
    
    private List getAllChildren( Object element, ITreeContentProvider contentProvider, ViewerFilter[] filters )
    {
        if( contentProvider.hasChildren( element ) )
        {
            Object children[] = filterElements( contentProvider.getChildren( element ), element, filters );
            List childrenList = new ArrayList( children.length );
            for( int i=0; i<children.length; i++ ) childrenList.addAll( getAllChildren( children[i], contentProvider, filters ) );
            return childrenList;
        }
        else return Collections.singletonList( element );
    }
    
    private Object[] filterElements( Object elements[], Object parent, ViewerFilter[] filters )
    {
        Object[] filteredElements = elements;
        for( int i=0; i<filters.length; i++ ) filteredElements = filters[i].filter(getTreeViewer(), parent, filteredElements );
        return filteredElements;
    }
    
	private void refreshFilter( String filterString )
	{
	    ListSelectionFilter filter = ( ListSelectionFilter )getTreeViewer().getFilters()[0];
	    filter.setFilter( filterString );
        getTreeViewer().refresh();
        getTreeViewer().setCheckedElements( checkedItems.toArray() );
	}

	public static class ListSelectionFilter extends ViewerFilter
	{
	    public boolean isElementFilterable( Viewer viewer, Object parentElement, Object element ) { return true; }
        final public boolean select( Viewer viewer, Object parentElement, Object element)
        {
            if( !isElementFilterable( viewer, parentElement, element ) ) return true;
            if( !( element instanceof ENamedElement ) ) return false;
            else return ( ( filter == null ) || filter.matcher( ( ( ENamedElement )element ).getName() ).matches() );
        }
        
        void setFilter( String filterString ) 
        {  
            if( ( filterString == null ) || filterString.equals( "" ) ) filter = null; //$NON-NLS-1$
            else 
            {
                String filterPattern = filterString.replaceAll( "\\\\", "\\\\\\\\" ).replaceAll( "\\.", "\\\\.").replaceAll( "\\?", "." ).replaceAll( "\\*", ".*"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
                filter = Pattern.compile( filterPattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );
            }
        }
        
        private Pattern filter;
	}
	
	private Text filterText;
    private List checkedItems = new ArrayList();
}
