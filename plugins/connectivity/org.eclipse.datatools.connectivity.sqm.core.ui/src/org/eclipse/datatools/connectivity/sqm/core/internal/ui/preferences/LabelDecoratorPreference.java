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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.preferences;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.IHelpContextsSQMCoreUI;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.RDBCoreUIPlugin;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.osgi.service.prefs.Preferences;

/**
 * @author ljulien
 */
public class LabelDecoratorPreference 
	extends PreferencePage 
	implements IWorkbenchPreferencePage, IContextProvider
{
    private static final String DECORATION_KEY = "org.eclipse.datatools.connectivity.sqm.core.ui.column.decoration"; //$NON-NLS-1$
    
    private static final String DECORATION = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.DECORATION"); //$NON-NLS-1$
    private static final String COLUMN_TAB = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.TAB"); //$NON-NLS-1$
    private static final String FORMAT = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.FORMAT"); //$NON-NLS-1$
    private static final String COLUMN_FORMAT = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.CFORMAT"); //$NON-NLS-1$
    private static final String VARIABLE = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.VARIABLE"); //$NON-NLS-1$
    private static final String EXAMPLE = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.EXAMPLE"); //$NON-NLS-1$
    
    private static final String TITLE = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.TITLE"); //$NON-NLS-1$
    private static final String MESSAGE = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.MESSAGE"); //$NON-NLS-1$
    
    private static final String DATATYPE_DESCRIPTION = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.DATATYPE_DESC"); //$NON-NLS-1$
    private static final String NULLABLE_DESCRIPTION = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.NULLABLE_DESC"); //$NON-NLS-1$
    private static final String PK_DESCRIPTION = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.PK_DESC"); //$NON-NLS-1$
    private static final String FK_DESCRIPTION = ResourceLoader.getResourceLoader().queryString("DATATOOLS.CORE.UI.PREFERENCES.COLUMN.FK_DESC"); //$NON-NLS-1$
   
    private static final String DATATYPE = "{datatype}"; //$NON-NLS-1$
    private static final String NULLABLE = "{nullable}"; //$NON-NLS-1$
    private static final String FK = "{foreignKey}"; //$NON-NLS-1$
    private static final String PK = "{primaryKey}"; //$NON-NLS-1$

    private static final String DATATYPE_REG = "\\{datatype\\}"; //$NON-NLS-1$
    private static final String NULLABLE_REG = "\\{nullable\\}"; //$NON-NLS-1$
    private static final String FK_REG = "\\{foreignKey\\}"; //$NON-NLS-1$
    private static final String PK_REG = "\\{primaryKey\\}"; //$NON-NLS-1$

    private static final String EX_DATATYPE = "VARCHAR(14)";  //$NON-NLS-1$
    private static final String EX_NAME = "NAME"; //$NON-NLS-1$
    private static final String EX_NULLABLE = "Nullable"; //$NON-NLS-1$
    private static final String EX_FK = "FK"; //$NON-NLS-1$
    private static final String EX_PK = "PK"; //$NON-NLS-1$
    
    private static final String BLANK = ""; //$NON-NLS-1$
    private static final String SPACE = " "; //$NON-NLS-1$
    private static final String BEG_BRACKET = "["; //$NON-NLS-1$
    private static final String END_BRACKET = "]"; //$NON-NLS-1$
    
    private static final Preferences preferences = new InstanceScope().getNode("org.eclipse.datatools.connectivity.sqm.core.ui"); //$NON-NLS-1$
    
    private Text exampleText;
    private Text columnText;
    
    private String getReplacement (String columnString, boolean shouldKey, String reg_key, String key, String replacement)
    {
        if (shouldKey)
        {
            return columnString.replaceAll(reg_key, replacement);
        }
        else 
        {
            int index = columnString.indexOf(key);
            if (index != -1)
            {
                int previousIndex = columnString.substring(0, index).lastIndexOf("}"); //$NON-NLS-1$
                int nextIndex = columnString.indexOf("}", columnString.substring(0, index).length()); //$NON-NLS-1$
                nextIndex = columnString.indexOf("{", columnString.substring(0, index).length() + 1) != -1 ? ++nextIndex : -1; //$NON-NLS-1$
                columnString = 
                    previousIndex == -1 && nextIndex == -1?
                            columnString.replaceAll(key, BLANK) : nextIndex == -1 ?
                                columnString.substring(0, previousIndex+1) + columnString.substring(index + key.length(), columnString.length()) :   
                            	previousIndex == -1 ? columnString.substring(0, index) + columnString.substring(nextIndex, columnString.length()) :
                                columnString.substring(0, previousIndex+1) + columnString.substring(nextIndex,columnString.length());
            }
            return columnString;
        }
    }
    
    private String getColumnString (String columnString, String dataType, boolean nullable, boolean pk, boolean fk)
    {
        if (columnString != null && !columnString.equals(BLANK))
        {
	        try
	        {
	            // Two passes so that we remove first what we don't want
	            if (!nullable)
	            {
	                while (columnString.indexOf(NULLABLE) != -1)
	                {
	                    columnString = getReplacement (columnString, nullable, NULLABLE_REG, NULLABLE, EX_NULLABLE);
	                }
                }
	            if (!fk)
	            {
	                while (columnString.indexOf(FK) != -1)
	                {
	                    columnString = getReplacement (columnString, fk, FK_REG, FK, EX_FK);
	                }
                }
	            if (!pk)
	            {
	                while (columnString.indexOf(PK) != -1)
	                {
	                    columnString = getReplacement (columnString, pk, PK_REG, PK, EX_PK);
	                }
	            }
	            if (columnString.indexOf(DATATYPE) == -1)
	            {
                    columnString = getReplacement (columnString, true, DATATYPE_REG, DATATYPE, dataType);
	            }
	            
	            // Second pass -> Substitute
	            if (columnString.indexOf(NULLABLE) != -1 && nullable)
	            {
	                columnString = getReplacement (columnString, nullable, NULLABLE_REG, NULLABLE, EX_NULLABLE);
                }
	            if (columnString.indexOf(FK) != -1 && fk)
	            {
	                columnString = getReplacement (columnString, fk, FK_REG, FK, EX_FK);
                }
	            if (columnString.indexOf(PK) != -1 && pk)
	            {
	                columnString = getReplacement (columnString, pk, PK_REG, PK, EX_PK);
	            }
	            if (columnString.indexOf(DATATYPE) != -1)
	            {
	                columnString = getReplacement (columnString, true, DATATYPE_REG, DATATYPE, dataType);
	            }
	        }
	        catch (PatternSyntaxException e)
	        {
	            return BLANK;
	        }
        }
        return SPACE + columnString.trim();
    }
    
    private String getExampleTextDefault ()
    {
        return EX_NAME + SPACE + getColumnString (columnText.getText(), EX_DATATYPE, true, true, true);
    }
    
    private String getColumnTextDefault ()
    {
        return BEG_BRACKET + DATATYPE + SPACE + NULLABLE + SPACE + PK + SPACE + FK + END_BRACKET;
    }
    
    private void addToTextColumn (List list)
    {
        if (list.contains(NULLABLE))
        {
            columnText.setText(NULLABLE + columnText.getText());
        }
        if (list.contains(FK))
        {
            columnText.setText(FK + columnText.getText());
        }
        if (list.contains(PK))
        {
            columnText.setText(PK + columnText.getText());
        }
        if (list.contains(DATATYPE))
        {
            columnText.setText(DATATYPE + columnText.getText());
        }
    }
    
    private Control getColumnTabDecorationControl(TabFolder folder)
    {
        Composite composite = new Composite(folder, SWT.NONE);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setLayout(new GridLayout(3, false));

        Label label = new Label (composite, SWT.NONE);
        GridData data = new GridData ();
        data.horizontalSpan = 3;
        label.setLayoutData(data);
        label.setText(FORMAT);
        
        label = new Label (composite, SWT.NONE);
        label.setText(COLUMN_FORMAT);
        
        columnText = new Text (composite, SWT.NONE | SWT.BORDER);
        GridData columnLayout = new GridData (GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        columnLayout.grabExcessHorizontalSpace = true;
        columnLayout.widthHint = 240;
        columnText.setLayoutData(columnLayout);
        columnText.setText(preferences.get(DECORATION_KEY, getColumnTextDefault()));
        columnText.addModifyListener(new ModifyListener ()
        {
            public void modifyText(ModifyEvent e)
            {
                exampleText.setText(getExampleTextDefault());
            }
        });
        
        Button variableButton = new Button (composite, SWT.PUSH);
        variableButton.setText(VARIABLE);
        variableButton.addSelectionListener(new SelectionListener ()
        {
            public void widgetSelected(SelectionEvent e)
            {
                ElementTreeSelectionDialog dlg = new ElementTreeSelectionDialog(getShell(), new LabelProvider()
                {
                    public String getText(Object element)
                    {
                        if (element == NULLABLE)
                        {
                            return NULLABLE_DESCRIPTION;
                        }
                        else if (element == FK)
                        {
                            return FK_DESCRIPTION;
                        }
                        else if (element == PK)
                        {
                            return PK_DESCRIPTION;	
                        }
                        else if (element == DATATYPE)
                        {
                            return DATATYPE_DESCRIPTION;
                        }
                        return null;
                    }
                }, new ITreeContentProvider()
                {

                    public Object[] getChildren(Object parentElement)
                    {
                        return new String[] { NULLABLE, FK, PK, DATATYPE };
                    }

                    public Object getParent(Object element)
                    {
                        return null;
                    }

                    public boolean hasChildren(Object element)
                    {
                        return false;
                    }

                    public Object[] getElements(Object inputElement)
                    {
                        return getChildren(inputElement);
                    }

                    public void dispose()
                    {
                    }

                    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
                    {
                    }

                });
                dlg.setTitle(TITLE);
                dlg.setMessage(MESSAGE);
                dlg.setInput(NULLABLE);
                if (dlg.open() == Window.OK)
                {
                    addToTextColumn(Arrays.asList(dlg.getResult()));
                }
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });
        
        label = new Label (composite, SWT.NONE);
        label.setText(EXAMPLE);
        
        exampleText = new Text (composite, SWT.READ_ONLY | SWT.BORDER);
        exampleText.setLayoutData(new GridData (GridData.FILL_HORIZONTAL));
        exampleText.setText(getExampleTextDefault());
        
        return composite;
    }

    private void addColumnTabDecoration(TabFolder folder)
    {
        TabItem columnItem = new TabItem(folder, SWT.NONE);
        columnItem.setText(COLUMN_TAB);
        columnItem.setControl(getColumnTabDecorationControl(folder));
    }

    private void addMultiTabDecorations(Composite parent)
    {
        TabFolder folder = new TabFolder(parent, SWT.TOP);
        createStandardLayout(folder);

        addColumnTabDecoration(folder);
    }

    private void createStandardLayout(Composite composite)
    {
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    }

    private boolean isSelected (String key)
    {
        String columnText = this.columnText.getText();
        return columnText != null && columnText.indexOf(key) != -1 ? true : false;
    }
    
    private boolean isNullable ()
    {
        return isSelected (NULLABLE);
    }
    
    private boolean isFK ()
    {
        return isSelected (FK);
    }
    
    private boolean isPK ()
    {
        return isSelected(PK);
    }
    
    private boolean isDatatype ()
    {
        return isSelected(DATATYPE);
    }
    
    protected void performDefaults()
    {
        this.exampleText.setText(this.getExampleTextDefault());
        this.columnText.setText(this.getColumnTextDefault());
    }

    public boolean performOk()
    {
        preferences.put(DECORATION_KEY, columnText.getText());
        IDataToolsUIServiceManager.INSTANCE.refreshColumnDecorationService();
        return true;
    }

    protected Control createContents(Composite container)
    {
        Composite parent = new Composite(container, SWT.NONE);
        createStandardLayout(parent);

        new Label(parent, SWT.NONE).setText(DECORATION);
        addMultiTabDecorations(parent);

        return parent;
    }

    public void init(IWorkbench workbench)
    {

    }
    
    public String getColumnDecoration (String dataType, boolean isNullable, boolean isPK, boolean isFK)
    {
        return getColumnString(preferences.get(DECORATION_KEY, getColumnTextDefault()), dataType, isNullable, isPK, isFK);
    }


	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(RDBCoreUIPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), 
				HelpUtil.getContextId(IHelpContextsSQMCoreUI.LABEL_DECORATOR_PREFERENCE_PAGE, 
							RDBCoreUIPlugin.getDefault().getBundle().getSymbolicName()));
	}
}