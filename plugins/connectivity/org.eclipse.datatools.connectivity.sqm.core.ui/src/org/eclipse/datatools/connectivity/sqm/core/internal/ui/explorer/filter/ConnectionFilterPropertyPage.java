/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers.FilterHelper;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilterImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * @author ledunnel
 */
public abstract class ConnectionFilterPropertyPage extends PropertyPage
		implements IConnectionFilterProvider {

	private static final ResourceLoader resource = ResourceLoader
			.getResourceLoader();

	protected ISelection selection;
	
	private Label descriptionLabel;

	private String DEFAULT_MESSAGE = resource
			.queryString("_UI_DESCRIPTION_FILTER"); //$NON-NLS-1$

	private static final String SELECTION_ONLY_MESSAGE = resource
			.queryString("_UI_DESCRIPTION_SELECTION_ONLY"); //$NON-NLS-1$
	
	private static final String EXPRESSION_ONLY_MESSAGE = resource
		.queryString("_UI_DESCRIPTION_EXPRESSION_ONLY"); //$NON-NLS-1$
	
	protected String defaultTitleText = resource
		.queryString("_UI_TITLE_FILTER_DIALOG"); //$NON-NLS-1$

	private ConnectionFilterComposite filterComposite;
	
	boolean hideSelectionOption = false;
	
	private boolean isMultiplePredicatesMode = false;
	
	private ConnectionFilter connFilter;
	
	public ConnectionFilterPropertyPage() {
		super();
		setTitle(getDefaultPageTitle());
	}

	public void dataChanged() {
		setValid(validatePage());
	}
	
	public Table getSelectionTable(){
	    return filterComposite.selectionTable;
	}
	    
	public void setSelectionListPopulated( boolean populated){
	    filterComposite.isSelectionListPopulated = populated;
	}

	protected Control createContents(Composite parent) {
		return createContents(parent, false);
	}

	protected Control createContents(Composite parent, boolean hideExpressionOption) {
		isMultiplePredicatesMode = FilterHelper.INSTANCE.supportsMultiplePredicatesMode(this.getElement());
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		GridData gd = null;

		descriptionLabel = new Label(composite, SWT.WRAP);
		descriptionLabel.setText(DEFAULT_MESSAGE);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalIndent = -5;
		descriptionLabel.setLayoutData(gd);
		
		Label spacer = new Label ( composite, SWT.NONE);
		spacer.setText(" ");
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		spacer.setLayoutData(gd);

		if(!isMultiplePredicatesMode){
			filterComposite = new ConnectionFilterComposite(composite, SWT.NONE, this, hideExpressionOption, hideSelectionOption);
			filterComposite.initializeValues();
		}
		else {
			connFilter = getConnectionFilter();
			
			if(connFilter == null)
				connFilter = new ConnectionFilterImpl();
			
			filterComposite = new ConnectionFilterComposite(composite, SWT.NONE, this, hideExpressionOption, hideSelectionOption, isMultiplePredicatesMode, connFilter, this);
			filterComposite.initializeValues();
		}
		
		if (filterComposite.isHideExpressionOption()) {
			DEFAULT_MESSAGE = SELECTION_ONLY_MESSAGE;
			this.setDescription(DEFAULT_MESSAGE);
		}
		else if (filterComposite.isHideSelectionOption()) {
			DEFAULT_MESSAGE = EXPRESSION_ONLY_MESSAGE;
			this.setDescription(DEFAULT_MESSAGE);
		}
		else {
			this.setDescription(DEFAULT_MESSAGE);
		}
		initializeDialogUnits(composite);
		setValid(validatePage());
		return composite;
	}
	
	protected void setPageValidity() {
		setValid(validatePage());
	}
	
	protected boolean validatePage() {
		boolean isValid = true;

		if (filterComposite != null){
			isValid = filterComposite.validatePage(this);
		}
		
		if (isValid) {
			this.setMessage(null);
			this.setErrorMessage(null);
			this.setDescription(DEFAULT_MESSAGE);
			this.setTitle(getDefaultPageTitle());
			this.setValid(isValid);
		}
		return isValid;
	}

	public String getPredicate() {
		return filterComposite.getPredicate();
	}
	
	public String getPredicates() {
		return filterComposite.getPredicates();
	}

	public boolean isFilterSpecified() {
		return filterComposite.isFilterSpecified();
	}

	public boolean isIncludeSelected() {
		return filterComposite.isIncludeSelected();
	}

	public void populateSelectionTable(Table selectionTable) {
	    // Clear filter
	 	String predicate = getCurrentPredicate();
	 	setConnectionFilter(null);

		filterComposite.setSelection(selection);
		filterComposite.populateSelectionTable(selectionTable);
		
        // Re-apply filter
        setConnectionFilter(predicate);
	}

	public abstract ConnectionFilter getConnectionFilter();

	protected abstract IConnectionProfile getConnectionProfile();
	
	protected abstract String getConnectionFilterType();
	
	public boolean performOk() {
		
		String filterType = getConnectionFilterType();
		if (filterType != null) {
					
			String predicate = null;
			
			if(!isMultiplePredicatesMode)
			{
				predicate = getPredicate();
			}
			else
			{
				predicate = getPredicates();
			}
			setConnectionFilter(predicate);
		}			
		
		return true;
	}
	
	private String getCurrentPredicate(){
		String predicate = null;
		String filterType = getConnectionFilterType();
		if (filterType != null) {
			IConnectionProfile profile = getConnectionProfile();
			Properties props = profile
					.getProperties(ConnectionFilter.FILTER_SETTINGS_PROFILE_EXTENSION_ID);
			
			predicate = props.getProperty(filterType);	
		}
		return predicate;
	}
	
	private void setConnectionFilter(String predicate) {
		String filterType = getConnectionFilterType();
		if (filterType != null) {
		IConnectionProfile profile = getConnectionProfile();
		Properties props = profile
				.getProperties(ConnectionFilter.FILTER_SETTINGS_PROFILE_EXTENSION_ID);
		if (predicate == null || predicate.length() == 0) {
			props.remove(filterType);
		} else {
			props.setProperty(filterType, predicate);
		}
		profile.setProperties(
				ConnectionFilter.FILTER_SETTINGS_PROFILE_EXTENSION_ID, props);
		}
	}

    protected void performDefaults()
    {
        super.performDefaults();
        
        //Restore default values and settings
        filterComposite.performDefaults();
        setErrorMessage(null);
        setValid(true);
    }    
    
    protected void performApply(){
		String filterType = getConnectionFilterType();
		if (filterType != null) {
					
			String predicate = null;
			
			if(!isMultiplePredicatesMode)
			{
				predicate = getPredicate();
			}
			else
			{
				predicate = getPredicates();
			}
			setConnectionFilter(predicate);
		}		
    }
    
    public void setDefaultPageTitle ( String title ) {
    	defaultTitleText = title;
    }
    
    public String getDefaultPageTitle () {
    	return defaultTitleText;
    }
    
    public boolean getHideSelectionOption() {
    	return hideSelectionOption;
    }

    public void setHideSelectionOption(boolean flag) {
    	hideSelectionOption = flag;
    }

	public void setDescription(String description) {
		if (this.descriptionLabel != null) {
			this.descriptionLabel.setText(description);
		}
	}

}