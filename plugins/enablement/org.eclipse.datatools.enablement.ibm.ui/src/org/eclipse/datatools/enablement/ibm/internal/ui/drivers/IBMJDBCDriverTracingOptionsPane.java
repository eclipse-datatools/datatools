/*******************************************************************************
 * Copyright (c) 2006 - 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ibm.internal.ui.drivers;

import java.io.File;

import org.eclipse.datatools.enablement.ibm.internal.ui.drivers.Messages;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public class IBMJDBCDriverTracingOptionsPane extends Composite implements
		Listener {

	private Button disableTracingCheckbox;

	private Label traceDirectoryLabel;

	private Text traceDirectoryText;

	private Button browseDirectory;

	private Label traceFileLabel;

	private Text traceFileText;

	private Button appendCheckbox;

	private Group traceLevelGroup;

	private Button connectionCallsCheckbox;

	private Button statementCallsCheckbox;

	private Button resultSetCallsCheckbox;

	private Button driverConfigurationCheckbox;

	private Button connectsCheckbox;

	private Button drdaFlowsCheckbox;

	private Button resultsetMetadataCheckbox;

	private Button parameterMetadataCheckbox;

	private Button diagnosticsCheckbox;

	private Button sqlJCheckbox;

	private Button xaCallsCheckbox;

	private Button allButton;

	private Button noneButton;

	private IIBMJDBCDriverProvider parentDriverProvider;

	private static final String TRACE_DIRECTORY_PROPERTY_NAME = "traceDirectory"; //$NON-NLS-1$

	private static final String TRACE_FILE_PROPERTY_NAME = "traceFile"; //$NON-NLS-1$

	private static final String TRACE_FILE_APPEND_PROPERTY_NAME = "traceFileAppend"; //$NON-NLS-1$

	private static final String TRACE_LEVEL_PROPERTY_NAME = "traceLevel"; //$NON-NLS-1$

	private static final String TRACE_FILE_APPEND_FALSE_PROPERTY_VALUE = "false"; //$NON-NLS-1$

	private static final String TRACE_FILE_APPEND_TRUE_PROPERTY_VALUE = "true"; //$NON-NLS-1$

	private static final String TRACE_LEVEL_TRACE_NONE_PROPERTY_VALUE = "0"; //$NON-NLS-1$

	private static final String CUI_NEWCW_DISABLE_TRACING_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_DISABLE_TRACING_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRACE_DIRECTORY_LABEL_UI_ = Messages
			.getString("CUI_NEWCW_TRACE_DIRECTORY_LABEL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRACE_DIRECTORY_BROWSE_BUTTON_UI_ = Messages
			.getString("CUI_NEWCW_TRACE_DIRECTORY_BROWSE_BUTTON_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRACE_FILE_LABEL_UI_ = Messages
			.getString("CUI_NEWCW_TRACE_FILE_LABEL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DEFAULT_TRACE_FILE_NAME_UI_ = Messages
			.getString("CUI_NEWCW_DEFAULT_TRACE_FILE_NAME_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_APPEND_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_APPEND_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRACE_LEVELS_GROUPBOX_UI_ = Messages
			.getString("CUI_NEWCW_TRACE_LEVELS_GROUPBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_CONNECTION_CALLS_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_CONNECTION_CALLS_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_STATEMENT_CALLS_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_STATEMENT_CALLS_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_RESULT_SET_CALLS_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_RESULT_SET_CALLS_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DRIVER_CONFIGURATION_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_DRIVER_CONFIGURATION_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_CONNECTS_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_CONNECTS_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DRDA_FLOWS_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_DRDA_FLOWS_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_RESULT_SET_METADATA_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_RESULT_SET_METADATA_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PARAMETER_METADATA_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_PARAMETER_METADATA_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DIAGNOSTICS_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_DIAGNOSTICS_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SQLJ_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_SQLJ_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_XA_CALLS_CHECKBOX_UI_ = Messages
			.getString("CUI_NEWCW_XA_CALLS_CHECKBOX_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SELECT_ALL_BUTTON_UI_ = Messages
			.getString("CUI_NEWCW_SELECT_ALL_BUTTON_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DESELECT_ALL_BUTTON_UI_ = Messages
			.getString("CUI_NEWCW_DESELECT_ALL_BUTTON_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_ENTER_DIRECTORY_MESSAGE_UI_ = Messages
			.getString("CUI_NEWCW_ENTER_DIRECTORY_MESSAGE_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_ENTER_VALID_DIRECTORY_MESSAGE_UI_ = Messages
			.getString("CUI_NEWCW_ENTER_VALID_DIRECTORY_MESSAGE_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_ENTER_FILE_MESSAGE_UI_ = Messages
			.getString("CUI_NEWCW_ENTER_FILE_MESSAGE_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SELECT_TRACE_LEVEL_MESSAGE_UI_ = Messages
			.getString("CUI_NEWCW_SELECT_TRACE_LEVEL_MESSAGE_UI_"); //$NON-NLS-1$

	private boolean isReadOnly = false;
	
	public IBMJDBCDriverTracingOptionsPane(Composite parent, int style,
			IIBMJDBCDriverProvider parentDriverProvider, boolean isReadOnly) {
		super(parent, style);
		this.parentDriverProvider = parentDriverProvider;
		Composite parentComposite = this;
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		parentComposite.setLayout(layout);
		
		this.isReadOnly = isReadOnly;
		int additionalStyles = SWT.NONE;
		if (isReadOnly){
			additionalStyles = SWT.READ_ONLY;
		}

		GridData gd;

		disableTracingCheckbox = new Button(parentComposite, SWT.CHECK);
		disableTracingCheckbox.setText(CUI_NEWCW_DISABLE_TRACING_CHECKBOX_UI_);
		disableTracingCheckbox.setSelection(true);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 3;
		gd.grabExcessHorizontalSpace = true;
		disableTracingCheckbox.setLayoutData(gd);

		traceDirectoryLabel = new Label(parentComposite, SWT.NONE);
		traceDirectoryLabel.setText(CUI_NEWCW_TRACE_DIRECTORY_LABEL_UI_);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		traceDirectoryLabel.setLayoutData(gd);

		traceDirectoryText = new Text(parentComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
		gd = new GridData();
		gd.widthHint = 180;
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 1;
		traceDirectoryText.setLayoutData(gd);

		browseDirectory = new Button(parentComposite, SWT.PUSH);
		browseDirectory.setText(CUI_NEWCW_TRACE_DIRECTORY_BROWSE_BUTTON_UI_);
		browseDirectory.setEnabled(!isReadOnly);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 1;
		browseDirectory.setLayoutData(gd);

		traceFileLabel = new Label(parentComposite, SWT.NONE);
		traceFileLabel.setText(CUI_NEWCW_TRACE_FILE_LABEL_UI_);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		traceFileLabel.setLayoutData(gd);

		traceFileText = new Text(parentComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalAlignment = GridData.FILL;
		gd.horizontalSpan = 2;
		traceFileText.setLayoutData(gd);
		traceFileText.setText(CUI_NEWCW_DEFAULT_TRACE_FILE_NAME_UI_);

		appendCheckbox = new Button(parentComposite, SWT.CHECK);
		appendCheckbox.setText(CUI_NEWCW_APPEND_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 3;
		gd.grabExcessHorizontalSpace = true;
		appendCheckbox.setLayoutData(gd);

		traceLevelGroup = new Group(parentComposite, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 2;
		traceLevelGroup.setLayout(layout);
		traceLevelGroup.setText(CUI_NEWCW_TRACE_LEVELS_GROUPBOX_UI_);
		traceLevelGroup.setEnabled(false);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 3;
		traceLevelGroup.setLayoutData(gd);

		connectionCallsCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		connectionCallsCheckbox
				.setText(CUI_NEWCW_CONNECTION_CALLS_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		connectionCallsCheckbox.setLayoutData(gd);

		statementCallsCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		statementCallsCheckbox.setText(CUI_NEWCW_STATEMENT_CALLS_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		statementCallsCheckbox.setLayoutData(gd);

		resultSetCallsCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		resultSetCallsCheckbox.setText(CUI_NEWCW_RESULT_SET_CALLS_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		resultSetCallsCheckbox.setLayoutData(gd);

		driverConfigurationCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		driverConfigurationCheckbox
				.setText(CUI_NEWCW_DRIVER_CONFIGURATION_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		driverConfigurationCheckbox.setLayoutData(gd);

		connectsCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		connectsCheckbox.setText(CUI_NEWCW_CONNECTS_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		connectsCheckbox.setLayoutData(gd);

		drdaFlowsCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		drdaFlowsCheckbox.setText(CUI_NEWCW_DRDA_FLOWS_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		drdaFlowsCheckbox.setLayoutData(gd);

		resultsetMetadataCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		resultsetMetadataCheckbox
				.setText(CUI_NEWCW_RESULT_SET_METADATA_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		resultsetMetadataCheckbox.setLayoutData(gd);

		parameterMetadataCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		parameterMetadataCheckbox
				.setText(CUI_NEWCW_PARAMETER_METADATA_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		parameterMetadataCheckbox.setLayoutData(gd);

		diagnosticsCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		diagnosticsCheckbox.setText(CUI_NEWCW_DIAGNOSTICS_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		diagnosticsCheckbox.setLayoutData(gd);

		sqlJCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		sqlJCheckbox.setText(CUI_NEWCW_SQLJ_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = true;
		sqlJCheckbox.setLayoutData(gd);

		xaCallsCheckbox = new Button(traceLevelGroup, SWT.CHECK);
		xaCallsCheckbox.setText(CUI_NEWCW_XA_CALLS_CHECKBOX_UI_);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;
		xaCallsCheckbox.setLayoutData(gd);

		allButton = new Button(traceLevelGroup, SWT.NONE);
		allButton.setEnabled(!isReadOnly);
		allButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_BEGINNING));
		allButton.setText(CUI_NEWCW_SELECT_ALL_BUTTON_UI_);

		noneButton = new Button(traceLevelGroup, SWT.NONE);
		noneButton.setEnabled(!isReadOnly);
		noneButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_BEGINNING));
		noneButton.setText(CUI_NEWCW_DESELECT_ALL_BUTTON_UI_);

		restoreDefaultSettings();
		enableTracingUI(false);
		addListeners();
	}

	private void enableTracingUI(boolean enable) {
		traceDirectoryLabel.setEnabled(enable);
		traceDirectoryText.setEnabled(enable);	
		traceFileLabel.setEnabled(enable);
		traceFileText.setEnabled(enable);
		appendCheckbox.setEnabled(enable);
		traceLevelGroup.setEnabled(enable);
		connectionCallsCheckbox.setEnabled(enable);
		statementCallsCheckbox.setEnabled(enable);
		resultSetCallsCheckbox.setEnabled(enable);
		driverConfigurationCheckbox.setEnabled(enable);
		connectsCheckbox.setEnabled(enable);
		drdaFlowsCheckbox.setEnabled(enable);
		resultsetMetadataCheckbox.setEnabled(enable);
		parameterMetadataCheckbox.setEnabled(enable);
		diagnosticsCheckbox.setEnabled(enable);
		sqlJCheckbox.setEnabled(enable);
		xaCallsCheckbox.setEnabled(enable);
		if (!isReadOnly) {
			browseDirectory.setEnabled(enable);
			allButton.setEnabled(enable);
			noneButton.setEnabled(enable);
		}
	}

	private void addListeners() {
		disableTracingCheckbox.addListener(SWT.Selection, this);
		traceDirectoryText.addListener(SWT.Modify, this);
		browseDirectory.addListener(SWT.Selection, this);
		traceFileText.addListener(SWT.Modify, this);
		appendCheckbox.addListener(SWT.Selection, this);
		connectionCallsCheckbox.addListener(SWT.Selection, this);
		statementCallsCheckbox.addListener(SWT.Selection, this);
		resultSetCallsCheckbox.addListener(SWT.Selection, this);
		driverConfigurationCheckbox.addListener(SWT.Selection, this);
		connectsCheckbox.addListener(SWT.Selection, this);
		drdaFlowsCheckbox.addListener(SWT.Selection, this);
		resultsetMetadataCheckbox.addListener(SWT.Selection, this);
		parameterMetadataCheckbox.addListener(SWT.Selection, this);
		diagnosticsCheckbox.addListener(SWT.Selection, this);
		sqlJCheckbox.addListener(SWT.Selection, this);
		xaCallsCheckbox.addListener(SWT.Selection, this);
		allButton.addListener(SWT.Selection, this);
		noneButton.addListener(SWT.Selection, this);
	}

	private void removeListeners() {
		disableTracingCheckbox.removeListener(SWT.Selection, this);
		traceDirectoryText.removeListener(SWT.Modify, this);
		browseDirectory.removeListener(SWT.Selection, this);
		traceFileText.removeListener(SWT.Modify, this);
		appendCheckbox.removeListener(SWT.Selection, this);
		connectionCallsCheckbox.removeListener(SWT.Selection, this);
		statementCallsCheckbox.removeListener(SWT.Selection, this);
		resultSetCallsCheckbox.removeListener(SWT.Selection, this);
		driverConfigurationCheckbox.removeListener(SWT.Selection, this);
		connectsCheckbox.removeListener(SWT.Selection, this);
		drdaFlowsCheckbox.removeListener(SWT.Selection, this);
		resultsetMetadataCheckbox.removeListener(SWT.Selection, this);
		parameterMetadataCheckbox.removeListener(SWT.Selection, this);
		diagnosticsCheckbox.removeListener(SWT.Selection, this);
		sqlJCheckbox.removeListener(SWT.Selection, this);
		xaCallsCheckbox.removeListener(SWT.Selection, this);
		allButton.removeListener(SWT.Selection, this);
		noneButton.removeListener(SWT.Selection, this);
	}

	public void handleEvent(Event event) {
		Widget source = event.widget;
		if (isReadOnly){
			if ((source == disableTracingCheckbox)
				|| 	(source == appendCheckbox)
				|| 	(source == connectionCallsCheckbox)
				|| 	(source == statementCallsCheckbox)
				|| 	(source == resultSetCallsCheckbox)
				|| 	(source == driverConfigurationCheckbox)
				|| 	(source == connectsCheckbox)
				|| 	(source == drdaFlowsCheckbox)
				|| 	(source == resultsetMetadataCheckbox)
				|| 	(source == parameterMetadataCheckbox)
				|| 	(source == diagnosticsCheckbox)
				|| 	(source == sqlJCheckbox)
				|| 	(source == xaCallsCheckbox)			
			){
				((Button)source).setSelection(!((Button)source).getSelection());
			}	
		} else {
			if (source == disableTracingCheckbox) {
				enableTracingUI(!disableTracingCheckbox.getSelection());
			} else if (source == allButton) {
				setAllLevelsChecked(true);
			} else if (source == noneButton) {
				setAllLevelsChecked(false);
			} else if (source == browseDirectory) {
				DirectoryDialog dialog = new DirectoryDialog(browseDirectory
						.getShell(), SWT.OPEN);
				dialog.setFilterPath(traceDirectoryText.getText().trim());
				String directory = dialog.open();
				if (directory != null) {
					traceDirectoryText.setText(directory);
				}
			}
			parentDriverProvider.updateURL();
			parentDriverProvider.setConnectionInformation();
		}
	}

	private void setAllLevelsChecked(boolean check) {
		connectionCallsCheckbox.setSelection(check);
		statementCallsCheckbox.setSelection(check);
		resultSetCallsCheckbox.setSelection(check);
		driverConfigurationCheckbox.setSelection(check);
		connectsCheckbox.setSelection(check);
		drdaFlowsCheckbox.setSelection(check);
		resultsetMetadataCheckbox.setSelection(check);
		parameterMetadataCheckbox.setSelection(check);
		diagnosticsCheckbox.setSelection(check);
		sqlJCheckbox.setSelection(check);
		xaCallsCheckbox.setSelection(check);
	}

	public boolean validateControl(DialogPage page) {
		boolean isValid = true;
		if (!disableTracingCheckbox.getSelection()) {
			if ((traceDirectoryText.getText() == null)
					|| (traceDirectoryText.getText().trim().length() < 1)) { //$NON-NLS-1$
				page.setErrorMessage(CUI_NEWCW_ENTER_DIRECTORY_MESSAGE_UI_);
				isValid = false;
			} else if (!isDirectoryValid(traceDirectoryText.getText().trim())) {
				page
						.setErrorMessage(CUI_NEWCW_ENTER_VALID_DIRECTORY_MESSAGE_UI_);
				isValid = false;
			} else if ((traceFileText.getText() == null)
					|| (traceFileText.getText().trim().length() < 1)) { //$NON-NLS-1$
				page.setErrorMessage(CUI_NEWCW_ENTER_FILE_MESSAGE_UI_);
				isValid = false;
			} else if (!isTraceLevelSelected()) {
				page.setErrorMessage(CUI_NEWCW_SELECT_TRACE_LEVEL_MESSAGE_UI_);
				isValid = false;
			}
		}
		return isValid;
	}

	private boolean isDirectoryValid(String directory) {
		boolean isDirectoryValid = false;
		File file = new File(directory);
		if (file.exists() && file.isDirectory()) {
			isDirectoryValid = true;
		}
		return isDirectoryValid;
	}

	public boolean isTraceLevelSelected() {
		boolean isSelected = false;
		if (!getTraceLevel().equals(TRACE_LEVEL_TRACE_NONE_PROPERTY_VALUE)) {
			isSelected = true;
		}
		return isSelected;
	}

	public String getTracingURLProperties() {
		String properties = ""; //$NON-NLS-1$
		if (!disableTracingCheckbox.getSelection()) {
			String traceDirectory = traceDirectoryText.getText().trim();
			String traceFile = traceFileText.getText().trim();
			String append = appendCheckbox.getSelection() ? TRACE_FILE_APPEND_TRUE_PROPERTY_VALUE
					: TRACE_FILE_APPEND_FALSE_PROPERTY_VALUE;
			String traceLevel = getTraceLevel();

			properties = TRACE_FILE_PROPERTY_NAME + "=" + traceFile + ";" //$NON-NLS-1$ //$NON-NLS-2$
					+ TRACE_FILE_APPEND_PROPERTY_NAME + "=" + append + ";" //$NON-NLS-1$ //$NON-NLS-2$
					+ TRACE_LEVEL_PROPERTY_NAME + "=" + traceLevel + ";" //$NON-NLS-1$ //$NON-NLS-2$
					+ TRACE_DIRECTORY_PROPERTY_NAME + "=" + traceDirectory //$NON-NLS-1$
					+ ";"; //$NON-NLS-1$
		}
		return properties;
	}

	private String getTraceLevel() {
		String traceLevelString = TRACE_LEVEL_TRACE_NONE_PROPERTY_VALUE;
		int traceLevel = 0;
		if (connectionCallsCheckbox.getSelection()) {
			traceLevel += 1;
		}
		if (statementCallsCheckbox.getSelection()) {
			traceLevel += 2;
		}
		if (resultSetCallsCheckbox.getSelection()) {
			traceLevel += 4;
		}
		if (driverConfigurationCheckbox.getSelection()) {
			traceLevel += 16;
		}
		if (connectsCheckbox.getSelection()) {
			traceLevel += 32;
		}
		if (drdaFlowsCheckbox.getSelection()) {
			traceLevel += 64;
		}
		if (resultsetMetadataCheckbox.getSelection()) {
			traceLevel += 128;
		}
		if (parameterMetadataCheckbox.getSelection()) {
			traceLevel += 256;
		}
		if (diagnosticsCheckbox.getSelection()) {
			traceLevel += 512;
		}
		if (sqlJCheckbox.getSelection()) {
			traceLevel += 1024;
		}
		if (xaCallsCheckbox.getSelection()) {
			traceLevel += 2048;
		}
		if (traceLevel == 4087) {
			traceLevel = -1; // Trace All
		}
		traceLevelString = Integer.toString(traceLevel);
		return traceLevelString;
	}

	private void setTraceLevel(String traceLevelString) {
		int traceLevel = Integer.parseInt(traceLevelString);
		if (traceLevel == -1) {
			setAllLevelsChecked(true); // Trace All
		} else {
			connectionCallsCheckbox.setSelection(((traceLevel & 1) != 0));
			statementCallsCheckbox.setSelection(((traceLevel & 2) != 0));
			resultSetCallsCheckbox.setSelection(((traceLevel & 4) != 0));
			driverConfigurationCheckbox.setSelection(((traceLevel & 16) != 0));
			connectsCheckbox.setSelection(((traceLevel & 32) != 0));
			drdaFlowsCheckbox.setSelection(((traceLevel & 64) != 0));
			resultsetMetadataCheckbox.setSelection(((traceLevel & 128) != 0));
			parameterMetadataCheckbox.setSelection(((traceLevel & 256) != 0));
			diagnosticsCheckbox.setSelection(((traceLevel & 512) != 0));
			sqlJCheckbox.setSelection(((traceLevel & 1024) != 0));
			xaCallsCheckbox.setSelection(((traceLevel & 2048) != 0));
		}
	}

	public void loadProperties(String properties) {
		removeListeners();
		URLPropertiesParser propertyParser = new URLPropertiesParser(properties);
		if (propertyParser.isTracingEnabled()) {
			disableTracingCheckbox.setSelection(false);
			enableTracingUI(true);
			traceDirectoryText.setText(propertyParser.getTraceDirectory());
			traceFileText.setText(propertyParser.getTraceFile());
			appendCheckbox.setSelection(propertyParser.getTraceFileAppend()
					.equals(TRACE_FILE_APPEND_TRUE_PROPERTY_VALUE));
			setTraceLevel(propertyParser.getTraceLevel());
		} else {
			disableTracingCheckbox.setSelection(true);
			enableTracingUI(false);
			traceDirectoryText.setText(""); //$NON-NLS-1$
			traceFileText.setText(""); //$NON-NLS-1$
			restoreDefaultSettings();
		}
		addListeners();
	}

	private class URLPropertiesParser {
		private String properties = ""; //$NON-NLS-1$

		public URLPropertiesParser(String properties) {
			this.properties = properties;
		}

		public String getTraceDirectory() {
			String traceDirectory = ""; //$NON-NLS-1$
			int beginningIndex = properties
					.lastIndexOf(TRACE_DIRECTORY_PROPERTY_NAME)
					+ TRACE_DIRECTORY_PROPERTY_NAME.length() + 1;
			int endingIndex = properties.indexOf(";", beginningIndex); //$NON-NLS-1$
			traceDirectory = properties.substring(beginningIndex, endingIndex);
			return traceDirectory;
		}

		public String getTraceFile() {
			String traceFile = ""; //$NON-NLS-1$
			int beginningIndex = properties
					.lastIndexOf(TRACE_FILE_PROPERTY_NAME + "=") //$NON-NLS-1$
					+ TRACE_FILE_PROPERTY_NAME.length() + 1;
			int endingIndex = properties.indexOf(";", beginningIndex); //$NON-NLS-1$
			traceFile = properties.substring(beginningIndex, endingIndex);
			return traceFile;
		}

		public String getTraceFileAppend() {
			String traceFile = ""; //$NON-NLS-1$
			int beginningIndex = properties
					.lastIndexOf(TRACE_FILE_APPEND_PROPERTY_NAME)
					+ TRACE_FILE_APPEND_PROPERTY_NAME.length() + 1;
			int endingIndex = properties.indexOf(";", beginningIndex); //$NON-NLS-1$
			traceFile = properties.substring(beginningIndex, endingIndex);
			return traceFile;
		}

		public String getTraceLevel() {
			String traceLevel = ""; //$NON-NLS-1$
			int beginningIndex = properties
					.lastIndexOf(TRACE_LEVEL_PROPERTY_NAME)
					+ TRACE_LEVEL_PROPERTY_NAME.length() + 1;
			int endingIndex = properties.indexOf(";", beginningIndex); //$NON-NLS-1$
			traceLevel = properties.substring(beginningIndex, endingIndex);
			return traceLevel;
		}

		public boolean isTracingEnabled() {
			boolean isTracingEnabled = false;
			if (properties.indexOf(TRACE_LEVEL_PROPERTY_NAME) >= 0) {
				isTracingEnabled = true;
			}
			return isTracingEnabled;
		}
	}

	private void restoreDefaultSettings() {
		setAllLevelsChecked(false);
		connectionCallsCheckbox.setSelection(true);
		traceDirectoryText.setText(System.getProperty("user.home")); //$NON-NLS-1$
		traceFileText.setText(CUI_NEWCW_DEFAULT_TRACE_FILE_NAME_UI_);
	}
}
