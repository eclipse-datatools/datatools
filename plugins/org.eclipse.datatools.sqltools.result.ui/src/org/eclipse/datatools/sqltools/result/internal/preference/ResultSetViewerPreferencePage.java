/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.preference;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultSetViewerDescriptor;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultSetViewerRegistryReader;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for the selection of viewer for result sets
 * @author Quy On
 */
public class ResultSetViewerPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
	private Combo viewersCombo;
	private String[] myViewers;
	private String viewerName;	 
	public static final String DEFAULT_VIEWER = Messages.ResultSetViewerPage_defaultViewer;
	
	public void init(IWorkbench workbench)
    {
        setPreferenceStore(ResultsViewUIPlugin.getDefault().getPreferenceStore());
        populateViewerNames();             
    }
	
	/**
	 * Gets the default viewer name from extensions.  If more the one extension result set viewers
	 * are available, then the internal default viewer will be used
	 * @return the default result set viewer name
	 */
	public static String getViewerNameFromExtension()
	{
		 List viewerDescriptors = 
			 ResultSetViewerRegistryReader.getInstance().getResultSetViewers();
		 String viewerName = DEFAULT_VIEWER;
		 if (viewerDescriptors.size() == 1)
		 {
			 viewerName = ((ResultSetViewerDescriptor)viewerDescriptors.get(0)).getDefaultViewer();
			 if (viewerName == null)
			 {
				 viewerName = DEFAULT_VIEWER;
			 }
		 }
		 return viewerName;
	}
	
	/**
	 * Populate the list of viewer names for result sets that are available from extensions	 
	 */
	private void populateViewerNames()
	{
		List viewerDescriptors = 
			 ResultSetViewerRegistryReader.getInstance().getResultSetViewers();
		int viewerCount = viewerDescriptors.size();		
		
		myViewers = new String[viewerCount + 1];
		// add internal default viewer as first one
		myViewers[0] = DEFAULT_VIEWER;
		Iterator iter = viewerDescriptors.iterator();
		int count = 0;
		while(iter.hasNext())
		{
			ResultSetViewerDescriptor desc = (ResultSetViewerDescriptor)iter.next();
			myViewers[++count] = desc.getViewerID();
		}
	}
	
	/**
	 * Sets the viewer name
	 * @param name the name of the viewer for the result set
	 */
	public void setViewerName(String name)
	{
		viewerName = name;
	}
	
	/**
	 * Gets the viewer name
	 * @return the name of the viewer for result sets
	 */
	public String getViewerName()
	{
		return viewerName;
	}
	
	protected Control createContents(Composite parent)
    {
		IPreferenceStore store = getPreferenceStore();
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        comp.setLayout(layout);
        Group viewersGroup = new Group(comp, SWT.NONE);
        viewersGroup.setText(Messages.ResultSetViewerPage_group);
        viewersGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout gLayout = new GridLayout();
        gLayout.numColumns = 2;
        viewersGroup.setLayout(gLayout);
        Label label = new Label(viewersGroup, SWT.NONE);
        label.setText(Messages.ResultSetViewerPage_select_viewer);
        viewersCombo = new Combo(viewersGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        viewersCombo.setToolTipText(Messages.ResultSetViewerPage_viewer_tooltip);
        GridData comboData = new GridData();
        comboData.grabExcessHorizontalSpace = true;
        viewersCombo.setLayoutData(comboData);
        // populate viewers
        for (int i=0;i<myViewers.length;i++)
        {
        	viewersCombo.add(myViewers[i]);
        }
        // select viewer name from store
        viewersCombo.setText(store.getString(PreferenceConstants.RESULT_SET_VIEWER_VIEWERNAME));
        viewersCombo.addSelectionListener(new SelectionAdapter() {
        	public void widgetSelected(SelectionEvent evt)
        	{
        		viewerName = viewersCombo.getText();        		
        	}
        });
        
		return comp;
    }
	
	public boolean performOk()
    {
        IPreferenceStore store = getPreferenceStore();
        store.setValue(PreferenceConstants.RESULT_SET_VIEWER_VIEWERNAME, 
        		viewersCombo.getText());
        return super.performOk();
    }
	
	protected void performDefaults()
    {				
		viewersCombo.setText(getViewerNameFromExtension());
		super.performDefaults();		
    }
}
