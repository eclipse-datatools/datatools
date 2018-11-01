/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.ui.explorer.filter;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.ConnectionFilterComposite;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public abstract class ConnectionFilterWizardPage extends WizardPage implements IConnectionFilterProvider {

	private static final ResourceLoader resource = ResourceLoader
			.getResourceLoader();

	protected ISelection selection;

	private static final String SELECTION_ONLY_MESSAGE = resource
			.queryString("_UI_DESCRIPTION_SELECTION_ONLY"); //$NON-NLS-1$
	
	private static final String EXPRESSION_ONLY_MESSAGE = resource
		.queryString("_UI_DESCRIPTION_EXPRESSION_ONLY"); //$NON-NLS-1$
	
	protected String defaultTitleText = resource
       .queryString("_UI_TITLE_FILTER_DIALOG");

	private ConnectionFilterComposite filterComposite;
	
	public ConnectionFilterWizardPage(String pageName) {
		super(pageName);
	}

    /**
     * @param pageName
     * @param title
     * @param titleImage
     */
    protected ConnectionFilterWizardPage(String pageName, String title,
            ImageDescriptor titleImage, ISelection sel) {
        super(pageName, title, titleImage);
        selection = sel;
    }
    
	public void createControl(Composite parent) {
		createControl(parent, false, false);
	}

	public void createControl(Composite parent, boolean hideExpressionOption, boolean hideSelectionOption) {
		filterComposite = new ConnectionFilterComposite(parent, SWT.NONE, this, hideExpressionOption, hideSelectionOption);
		filterComposite.initializeValues();
	    setDescription(filterComposite.DEFAULT_MESSAGE);

        if (filterComposite.isHideExpressionOption()) {
            filterComposite.DEFAULT_MESSAGE = SELECTION_ONLY_MESSAGE;
            this.setDescription(filterComposite.DEFAULT_MESSAGE);
        }
        else if (filterComposite.isHideSelectionOption()) {
            filterComposite.DEFAULT_MESSAGE = EXPRESSION_ONLY_MESSAGE;
            this.setDescription(filterComposite.DEFAULT_MESSAGE);
        }
        else {
            this.setDescription(filterComposite.DEFAULT_MESSAGE);
        }

		setControl(filterComposite);	
		validatePage();
	}

	protected void initializeValues() {
		filterComposite.initializeValues();
	}

	protected boolean validatePage() {
		return filterComposite.validatePage(this);
	}
	
	/**
	 * This method is called when ever the user changes the filter settings.
	 */
	public void dataChanged(){
		validatePage();
	}

	/**
	 * This method returns the filter predicate that should be used to set the connection filter
	 */
	public String getPredicate() {
		return filterComposite.getPredicate();
	}

	/**
	 * This method indicates whether the filter is disabled or not.
	 */
	public boolean isFilterSpecified() {
		return filterComposite.isFilterSpecified();
	}
	
	/**
	 * This method indicates whether the include or exclude option is selected for a selection filter.
	 */
	public boolean isIncludeSelected() {
		return filterComposite.isFilterSpecified();
	}

	/**
	 * Override this method to override the default table population implementation.
	 */
	public void populateSelectionTable(Table selectionTable) {
		filterComposite.populateSelectionTable(selectionTable);
	}
	
	/**
	 * This method will refresh the contents of the table
	 */
	public void populateSelectionTable() {
		filterComposite.populateSelectionTable();
	}

	public void setSelectionListPopulated(boolean isPopulated){
	    filterComposite.setSelectionListPopulated(isPopulated);
	}

	/**
	 * This method provides the filter to use to initialize the UI.
	 */
	public abstract ConnectionFilter getConnectionFilter();  
}
