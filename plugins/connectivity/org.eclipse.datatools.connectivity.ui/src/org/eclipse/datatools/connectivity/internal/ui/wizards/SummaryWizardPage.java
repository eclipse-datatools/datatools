/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: plevin, shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * Common wizard page used to summarize information entered in previous pages.
 * 
 * @author plevin, shongxum
 */
public class SummaryWizardPage extends WizardPage implements ISummary {

	private static final String PAGE = "SummaryWizardPage"; //$NON-NLS-1$

	private static final String TITLE = PAGE + ".title"; //$NON-NLS-1$

	private static final String DESCRIPTION = PAGE + ".description"; //$NON-NLS-1$

	private static final String PROPERTY = PAGE + ".property"; //$NON-NLS-1$

	private static final String VALUE = PAGE + ".value"; //$NON-NLS-1$

	private String mPropertyCol = ""; //$NON-NLS-1$

	private String mValueCol = ""; //$NON-NLS-1$

	private ISummaryDataSource mSource;

	private Table mTable;

	public SummaryWizardPage() {
		super(PAGE);
		setTitle(ConnectivityUIPlugin.getDefault().getResourceString(TITLE));
		setDescription(ConnectivityUIPlugin.getDefault().getResourceString(
				DESCRIPTION));
		mPropertyCol = ConnectivityUIPlugin.getDefault().getResourceString(
				PROPERTY);
		mValueCol = ConnectivityUIPlugin.getDefault().getResourceString(VALUE);
	}

	public SummaryWizardPage(ISummaryDataSource source) {
		this();
		mSource = source;
	}

	/**
	 * @see WizardPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		composite.setLayout(gl);
		mTable = new Table(composite, SWT.BORDER);
		mTable.setLayoutData(new GridData(GridData.FILL_BOTH));
		mTable.setHeaderVisible(true);
		mTable.setLinesVisible(true);
		TableLayout layout = new TableLayout();
		mTable.setLayout(layout);
		layout.addColumnData(new ColumnPixelData(163));
		layout.addColumnData(new ColumnPixelData(245));
		TableColumn keyCol = new TableColumn(mTable, SWT.NONE);
		keyCol.setText(mPropertyCol);
		TableColumn valueCol = new TableColumn(mTable, SWT.NONE);
		valueCol.setText(mValueCol);
		setControl(composite);
		setPageComplete(true);
	}

	/**
	 * @see WizardPage#setVisible(boolean)
	 */
	public void setVisible(boolean value) {
		super.setVisible(value);
		if (value == true) {
			loadSummaryData();
		}
	}

	/**
	 * Sets the title for the 1st (property) column.
	 * 
	 * @param title
	 */
	public void setPropertyColTitle(String title) {
		mPropertyCol = title;

		if (mTable != null && mTable.getColumnCount() >= 2) {
			mTable.getColumn(0).setText(mPropertyCol);
		}
	}

	/**
	 * Sets the title for the 2nd (value) column.
	 * 
	 * @param title
	 */
	public void setValueColTitle(String title) {
		mValueCol = title;

		if (mTable != null && mTable.getColumnCount() >= 2) {
			mTable.getColumn(1).setText(mValueCol);
		}
	}

	/**
	 * Sets summary page data source.
	 * 
	 * @param source Data source.
	 */
	public void setSummaryDataSource(ISummaryDataSource s) {
		mSource = s;
	}

	/**
	 * @see WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		return false;
	}

	/**
	 * Populates the table with summary information.
	 */
	public void loadSummaryData() {
		if (mSource == null) {
			return;
		}
		else {
			Object[] data = mSource.getSummaryData().toArray();
			mTable.removeAll();
			for (int i = 0; i < data.length; i++) {
				TableItem item = new TableItem(mTable, SWT.NONE);
				item.setText((String[]) data[i]);
			}
		}
	}
}