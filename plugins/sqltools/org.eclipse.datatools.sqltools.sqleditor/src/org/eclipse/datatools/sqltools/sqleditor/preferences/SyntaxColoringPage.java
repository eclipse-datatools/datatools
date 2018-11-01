/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase
 ******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.preferences;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLSourceViewer;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLPartitions;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLCodeScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLColorProvider;
import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;

/**
 * The preference page for configurating color and style of syntax.
 * 
 * @author renj
 */
public class SyntaxColoringPage extends PreferencePage implements IWorkbenchPreferencePage {

	private List _syntaxList = null;
	private Button _boldFont = null;
	private Button _italicFont = null;
	private Button _strikethroughFont = null;
	private Button _underlineFont = null;
	private Button _systemDefault = null;
	private ColorSelector _colorSelector;
	private SQLSourceViewer _sourceViewer;

	private LinkedHashMap _defaultSyntaxItems = new LinkedHashMap();

	// predefined codes for previewer
	private String PreviewCode = "/*\n" + "* Create/Insert/Select a table.\n" + "*/\n" + "-- create the table\n" + "CREATE TABLE tab(col1 int, col2 varchar);\n\n" + "-- insert data into the table\n"
			+ "INSERT INTO tab values(1, 'text');\n\n" + "-- select the table\n" + "SELECT count(*) AS \"COLUMN\" FROM tab;";

	public SyntaxColoringPage() {
		setDescription(PreferenceMessages.SyntaxColoringPage_description);
		setPreferenceStore(SQLEditorPlugin.getDefault().getPreferenceStore());
		initialPage();
	}

	private void initialPage() {
		_defaultSyntaxItems.put(SQLColorProvider.SQL_COMMENT, new SyntaxItem(false, false, false, false, false, new RGB(64, 128, 128)));
		_defaultSyntaxItems.put(SQLColorProvider.SQL_MULTILINE_COMMENT, new SyntaxItem(false, false, false, false, false, new RGB(64, 128, 128)));
		_defaultSyntaxItems.put(SQLColorProvider.SQL_QUOTED_LITERAL, new SyntaxItem(false, false, false, false, false, new RGB(0, 0, 255)));
		_defaultSyntaxItems.put(SQLColorProvider.SQL_DELIMITED_IDENTIFIER, new SyntaxItem(false, false, false, false, false, new RGB(0, 128, 0)));
		_defaultSyntaxItems.put(SQLColorProvider.SQL_KEYWORD, new SyntaxItem(true, false, false, false, false, new RGB(127, 0, 85)));
		_defaultSyntaxItems.put(SQLColorProvider.SQL_TYPE, new SyntaxItem(true, false, false, false, false, new RGB(64, 0, 200)));
		_defaultSyntaxItems.put(SQLColorProvider.SQL_IDENTIFIER, new SyntaxItem(false, false, false, false, false, new RGB(0, 0, 128)));
		_defaultSyntaxItems.put(SQLColorProvider.SQL_DEFAULT, new SyntaxItem(false, false, false, false, true, getForeGroundColor()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpUtil.getContextId(IHelpContextIds.PREFERENCES_SYNTAX_COLORING, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite fontComposite = new Composite(parent, SWT.NULL);
		GridLayout fontLayout = new GridLayout(2, false);
		fontLayout.marginHeight = 0;
		fontLayout.marginWidth = 0;
		fontComposite.setLayout(fontLayout);

		Label syntaxLabel = new Label(fontComposite, SWT.NULL);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		syntaxLabel.setLayoutData(gd);
		syntaxLabel.setText(PreferenceMessages.SyntaxColoringPage_syntax_items);

		// create syntax list container
		createSyntax(fontComposite);
		// create color and style check boxes
		createStyles(fontComposite);

		Label previewLabel = new Label(fontComposite, SWT.NULL);
		previewLabel.setText(PreferenceMessages.SyntaxColoringPage_preview);

		// create previewer
		createPreview(fontComposite);

		initialData();

		return fontComposite;
	}

	private void createSyntax(Composite parent) {
		Composite comp = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		comp.setLayout(layout);
		comp.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		GridData gridData = new GridData();
		gridData.heightHint = 110;
		gridData.widthHint = 150;
		_syntaxList = new List(comp, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		_syntaxList.setLayoutData(gridData);
		_syntaxList.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				if (_syntaxList.getSelection()[0] != null) {
					if (_syntaxList.getSelection()[0].equals(SQLColorProvider.SQL_DEFAULT)) {
						_systemDefault.setVisible(true);
					} else {
						_systemDefault.setVisible(false);
						_systemDefault.setSelection(false);
						_colorSelector.setEnabled(true);
					}
					refreshStyleComposite(((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])));
				}
			}
		});
	}

	private void createStyles(Composite parent) {
		Composite comp = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		comp.setLayout(layout);
		comp.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		Composite compColorControl = new Composite(comp, SWT.NULL);
		GridLayout colorLayout = new GridLayout(3, false);
		colorLayout.marginHeight = 0;
		colorLayout.marginWidth = 0;
		compColorControl.setLayout(colorLayout);

		Label label = new Label(compColorControl, SWT.NULL);
		label.setText(PreferenceMessages.SyntaxColoringPage_color);
		_colorSelector = new ColorSelector(compColorControl);
		_colorSelector.addListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).setColor(_colorSelector.getColorValue());
				updatePreviewer();
			}
		});

		_systemDefault = new Button(compColorControl, SWT.CHECK);
		_systemDefault.setText(PreferenceMessages.SyntaxColoringPage_default_foreground_color);
		_systemDefault.addSelectionListener(new stylesListener());
		_systemDefault.setVisible(false);

		_boldFont = new Button(comp, SWT.CHECK);
		_boldFont.setText(PreferenceMessages.SyntaxColoringPage_bold);
		_boldFont.addSelectionListener(new stylesListener());

		_italicFont = new Button(comp, SWT.CHECK);
		_italicFont.setText(PreferenceMessages.SyntaxColoringPage_italic);
		_italicFont.addSelectionListener(new stylesListener());

		_strikethroughFont = new Button(comp, SWT.CHECK);
		_strikethroughFont.setText(PreferenceMessages.SyntaxColoringPage_strikethrough);
		_strikethroughFont.addSelectionListener(new stylesListener());

		_underlineFont = new Button(comp, SWT.CHECK);
		_underlineFont.setText(PreferenceMessages.SyntaxColoringPage_underline);
		_underlineFont.addSelectionListener(new stylesListener());
	}

	private void createPreview(Composite parent) {
		_sourceViewer = new SQLSourceViewer(parent, null, null, false, SWT.MULTI | SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 300;
		gridData.horizontalSpan = 2;
		gridData.heightHint = convertHeightInCharsToPixels(10);
		_sourceViewer.getTextWidget().setLayoutData(gridData);
		_sourceViewer.setEditable(false);

		Document document = new Document(PreviewCode);
		SimpleSQLSourceViewerConfiguration config = new SimpleSQLSourceViewerConfiguration();
		_sourceViewer.configure(config);
		// use a generic database vendor to get the configuration
		SQLDevToolsConfiguration factory = SQLToolsFacade.getConfigurationByVendorIdentifier(new DatabaseVendorDefinitionId("UNKOWN", "1.0"));
		SQLPartitionScanner _sqlPartitionSanner = new SQLPartitionScanner(factory.getSQLService().getSQLSyntax());
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension3 = (IDocumentExtension3) document;

			IDocumentPartitioner partitioner = new FastPartitioner(_sqlPartitionSanner, SQLPartitionScanner.SQL_PARTITION_TYPES);

			partitioner.connect(document);
			extension3.setDocumentPartitioner(SQLPartitionScanner.SQL_PARTITIONING, partitioner);
		}
		_sourceViewer.setDocument(document);
		_sourceViewer.getTextWidget().setBackground(new Color(Display.getCurrent(), getBackGroundColor()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	private void initialData() {
		for (Iterator it = _defaultSyntaxItems.keySet().iterator(); it.hasNext();) {
			String syntaxItemName = (String) it.next();
			_syntaxList.add(syntaxItemName);

			if (getPreferenceStore().getString(syntaxItemName) == null || getPreferenceStore().getString(syntaxItemName).equals("")) {
				// use default values
				_syntaxList.setData(syntaxItemName, new SyntaxItem(_defaultSyntaxItems.get(syntaxItemName).toString()));
			} else {
				// use values saved before
				_syntaxList.setData(syntaxItemName, new SyntaxItem(getPreferenceStore().getString(syntaxItemName)));
			}
			getPreferenceStore().setDefault(syntaxItemName, ((SyntaxItem) _syntaxList.getData(syntaxItemName)).toString());
		}

		_colorSelector.setColorValue(new RGB(0, 0, 0));

		_syntaxList.select(0);
		_syntaxList.notifyListeners(SWT.Selection, new Event());
	}

	/**
	 * Reset original defined values to selected syntax item.
	 */
	private void getInitialValue() {
		if (_syntaxList.getSelection()[0] != null) {
			_syntaxList.setData(_syntaxList.getSelection()[0], new SyntaxItem(_defaultSyntaxItems.get(_syntaxList.getSelection()[0]).toString()));
			refreshStyleComposite((SyntaxItem) _defaultSyntaxItems.get(_syntaxList.getSelection()[0]));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	public void performDefaults() {
		super.performDefaults();

		getInitialValue();
		updatePreviewer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {
		for (int i = 0; i < _syntaxList.getItemCount(); i++) {
			String key = _syntaxList.getItem(i);
			String value = ((SyntaxItem) _syntaxList.getData(_syntaxList.getItem(i))).toString();

			getPreferenceStore().putValue(key, value);
			getPreferenceStore().setDefault(key, value);
		}

		SQLEditorPlugin.getDefault().savePluginPreferences();

		return super.performOk();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performCancel()
	 */
	public boolean performCancel() {
		for (int i = 0; i < _syntaxList.getItemCount(); i++) {
			getPreferenceStore().putValue(_syntaxList.getItem(i), getPreferenceStore().getDefaultString(_syntaxList.getItem(i)));
		}

		return super.performCancel();
	}

	/**
	 * Set all check boxes' status.
	 * 
	 * @param syntax
	 *            the item of syntax
	 */
	private void refreshStyleComposite(SyntaxItem syntax) {
		_colorSelector.setColorValue(syntax.getColor());
		_boldFont.setSelection(syntax.isBold());
		_italicFont.setSelection(syntax.isItalic());
		_strikethroughFont.setSelection(syntax.isStrikethrough());
		_underlineFont.setSelection(syntax.isUnderline());
		_systemDefault.setSelection(syntax.isSystemDefault());
		_systemDefault.notifyListeners(SWT.Selection, new Event());
	}

	/**
	 * Refesh previewer by updated selection.
	 */
	private void updatePreviewer() {
		getPreferenceStore().setValue(_syntaxList.getSelection()[0], ((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).toString());

		SimpleSQLSourceViewerConfiguration config = new SimpleSQLSourceViewerConfiguration();
		_sourceViewer.unconfigure();
		_sourceViewer.configure(config);
	}

	/**
	 * Get foreground color
	 * @return foreground color
	 */
	private RGB getForeGroundColor()
	{
		String rgb[] = new String[]{"0","0","0"};
		try {
			if (!org.eclipse.ui.internal.editors.text.EditorsPlugin.getDefault().getPreferenceStore().getString(AbstractTextEditor.PREFERENCE_COLOR_FOREGROUND_SYSTEM_DEFAULT).equalsIgnoreCase("true")) {
				String foreGroundColor = org.eclipse.ui.internal.editors.text.EditorsPlugin.getDefault().getPreferenceStore().getString(AbstractTextEditor.PREFERENCE_COLOR_FOREGROUND);
				if (foreGroundColor != null && !foreGroundColor.equals("")) {
					rgb = foreGroundColor.split(",");
				}
			}
		} catch (Throwable ex) {
			// do nothing, keep using default value
		}
		return new RGB(Integer.parseInt(rgb[0]),Integer.parseInt(rgb[1]),Integer.parseInt(rgb[2]));
	}
	
	/**
	 * Get background color
	 * @return background color
	 */
	private RGB getBackGroundColor()
	{
		String rgb[] = new String[] { "255", "255", "255" };
		try {
			if (!org.eclipse.ui.internal.editors.text.EditorsPlugin.getDefault().getPreferenceStore().getString(AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND_SYSTEM_DEFAULT).equalsIgnoreCase("true")) {
				String backGroundColor = org.eclipse.ui.internal.editors.text.EditorsPlugin.getDefault().getPreferenceStore().getString(AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND);
				if (backGroundColor != null && !backGroundColor.equals("")) {
					rgb = backGroundColor.split(",");
				}
			}
		} catch (Throwable ex) {
			// do nothing, keep using default value
		}
		return new RGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
	}

	/**
	 * Handle selection event of all check boxes
	 */
	class stylesListener implements SelectionListener {
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).setBoldKey(_boldFont.getSelection());
			((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).setItalicKey(_italicFont.getSelection());
			((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).setStrikethroughKey(_strikethroughFont.getSelection());
			((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).setUnderlineKey(_underlineFont.getSelection());
			((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).setSystemDefault(_systemDefault.getSelection());
			if (_systemDefault.isVisible() && _systemDefault.getSelection()) {
				((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).setColor(((SyntaxItem)_defaultSyntaxItems.get(SQLColorProvider.SQL_DEFAULT)).getColor());
			} else {
				((SyntaxItem) _syntaxList.getData(_syntaxList.getSelection()[0])).setColor(_colorSelector.getColorValue());
			}
			_colorSelector.setEnabled(!_systemDefault.getSelection());
			updatePreviewer();
		}
	}

	/**
	 * The class is used only in this preference page.
	 */
	class SimpleSQLSourceViewerConfiguration extends SourceViewerConfiguration {

		public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
			SQLCodeScanner scanner = new SQLCodeScanner(SQLEditorPlugin.getDefault().getSQLColorProvider());
			// use a generic database vendor to get the configuration
			SQLDevToolsConfiguration factory = SQLToolsFacade.getConfigurationByVendorIdentifier(new DatabaseVendorDefinitionId("UNKOWN", "1.0"));
			if (factory != null) {
				scanner.setSQLSyntax(factory.getSQLService().getSQLSyntax());
			}

			PresentationReconciler reconciler = new PresentationReconciler();
			reconciler.setDocumentPartitioning(ISQLPartitions.SQL_PARTITIONING);

			// rule for default text
			DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
			reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
			reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

			// rule for multiline comments
			dr = new DefaultDamagerRepairer(scanner);
			reconciler.setDamager(dr, ISQLPartitions.SQL_MULTILINE_COMMENT);
			reconciler.setRepairer(dr, ISQLPartitions.SQL_MULTILINE_COMMENT);

			// rule for singleline comments
			dr = new DefaultDamagerRepairer(scanner);
			reconciler.setDamager(dr, ISQLPartitions.SQL_COMMENT);
			reconciler.setRepairer(dr, ISQLPartitions.SQL_COMMENT);

			// rule for SQL Strings
			dr = new DefaultDamagerRepairer(scanner);
			reconciler.setDamager(dr, ISQLPartitions.SQL_STRING);
			reconciler.setRepairer(dr, ISQLPartitions.SQL_STRING);

			// rule for double quoted identifiers
			dr = new DefaultDamagerRepairer(scanner);
			reconciler.setDamager(dr, ISQLPartitions.SQL_DOUBLE_QUOTES_IDENTIFIER);
			reconciler.setRepairer(dr, ISQLPartitions.SQL_DOUBLE_QUOTES_IDENTIFIER);

			dr = new DefaultDamagerRepairer(scanner);
			reconciler.setDamager(dr, SQLPartitionScanner.SQL_CODE);
			reconciler.setRepairer(dr, SQLPartitionScanner.SQL_CODE);

			return reconciler;
		}
	}
}
