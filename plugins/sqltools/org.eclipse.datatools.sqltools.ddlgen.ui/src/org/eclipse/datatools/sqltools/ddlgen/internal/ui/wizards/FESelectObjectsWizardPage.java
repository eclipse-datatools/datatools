/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategoryID;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.FEUiPlugin;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.IHelpContextsSQMFEUI;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.util.ResourceLoader;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;


public class FESelectObjectsWizardPage 
	extends WizardPage 
	implements IContextProvider {
	
    private static final String SELECT_ALL = ResourceLoader.INSTANCE.queryString("SELECT_ALL_BUTTON_TEXT"); //$NON-NLS-1$
	private static final String DESELECT_ALL = ResourceLoader.INSTANCE.queryString("DESELECT_ALL_BUTTON_TEXT"); //$NON-NLS-1$
	private static final String RESTORE_DEFAULTS = ResourceLoader.INSTANCE.queryString("RESTORE_DEFAULTS_BUTTON_TEXT"); //$NON-NLS-1$
	private static final String GENERATE_NO_MNEMONIC_LABEL_TEXT = ResourceLoader.INSTANCE
            .queryString("GENERATE_NO_MNEMONIC_LABEL_TEXT"); //$NON-NLS-1$

    private static final String FE_SELECT_OBJECTS_PAGE_HEADER_SUBTITLE = ResourceLoader.INSTANCE
            .queryString("FE_SELECT_OBJECTS_PAGE_HEADER_SUBTITLE"); //$NON-NLS-1$
    private static final String FE_SELECT_OBJECTS_PAGE_HEADER_TITLE = ResourceLoader.INSTANCE
    .queryString("FE_SELECT_OBJECTS_PAGE_HEADER_TITLE"); //$NON-NLS-1$

    private Label m_label;
    private Vector m_Buttons = new Vector();
    private Vector m_Default = new Vector();
    private Button m_selectAll;
    private Button m_deselectAll;
    private Button m_restoreDefaults;
    private FEConfigurationData configurationData;
    private FEConfigurationData filteredConfigurationData = new FEConfigurationData(new EngineeringOption[0]);

    private Listener eventListener = null;
    
    public FESelectObjectsWizardPage(String pageName, FEConfigurationData configurationData, Listener selectionChangedListener) {
        this(pageName, configurationData);
        eventListener = selectionChangedListener;   
    }

    public FESelectObjectsWizardPage(String pageName, FEConfigurationData configurationData) {
        super(pageName); 
        this.configurationData = configurationData;
        setTitle(FE_SELECT_OBJECTS_PAGE_HEADER_TITLE);
        setDescription(FE_SELECT_OBJECTS_PAGE_HEADER_SUBTITLE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {

        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));

//        WorkbenchHelp.setHelp(composite, "org.eclipse.datatools.connectivity.sqm.fe.ui.infopop.genddl_wiz_objects");
    	
        m_label = new Label(composite, SWT.NONE);
        GridData generateData = new GridData();
        generateData.horizontalSpan = 2;
        m_label.setLayoutData(generateData);
        m_label.setText(GENERATE_NO_MNEMONIC_LABEL_TEXT);

        Composite composite1 = new Composite(composite, SWT.NONE);
        composite1.setLayout(new GridLayout());
        composite1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Composite composite2 = new Composite(composite, SWT.NONE);
        composite2.setLayout(new GridLayout());
        composite2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		EngineeringOption [] options = configurationData.getOptions();
        EngineeringOption option;
		Vector filteredOptions = new Vector();
		for (int index = 0; index < options.length; index++){
            option = options[index]; //@d00058820gs
            if (option != null && option.getCategory().getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)) {
   				filteredOptions.add(option);
            }
		}
        if(filteredOptions.size() == 1) //force all checkboxes to be enabled
            ((EngineeringOption)filteredOptions.get(0)).setBoolean(true);
      
		EngineeringOption[] filteredOptionsArray = new EngineeringOption[filteredOptions.size()];
		filteredOptions.copyInto(filteredOptionsArray);
		filteredConfigurationData =  new FEConfigurationData(filteredOptionsArray);

		for (int i = 0; i < Math.ceil((double)filteredOptionsArray.length / (double)2.0) ; i++)
		{	
			addButton (composite1, filteredOptionsArray[i]);
		}
		for (int i = (int)Math.ceil((double)filteredOptionsArray.length / (double)2.0); i < filteredOptionsArray.length ; i++)
		{
			addButton (composite2, filteredOptionsArray[i]);
		}
        
        //Buttons
        Composite composite3 = new Composite(composite, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 2;//3;
        layout.marginHeight = 0;
        layout.marginWidth = 2;

        composite3.setLayout(layout);
        generateData = new GridData(GridData.FILL_HORIZONTAL);
        generateData.horizontalSpan = 2;//3;
        generateData.verticalSpan = 17;
        generateData.horizontalAlignment = GridData.BEGINNING;
        composite3.setLayoutData(generateData);
        
        Composite composite4 = new Composite(composite3, SWT.NONE);
        RowLayout layout2 = new RowLayout();
        layout2.pack = false;
        layout2.spacing = 5;
        composite4.setLayout(layout2);
        

        m_selectAll = new Button(composite4, SWT.NONE);
        m_selectAll.setText(SELECT_ALL);
        
        m_deselectAll = new Button(composite4, SWT.NONE);
        m_deselectAll.setText(DESELECT_ALL);
        
        m_restoreDefaults = new Button(composite4, SWT.NONE);
        m_restoreDefaults.setText(RESTORE_DEFAULTS); 
        
        m_selectAll.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                for (Iterator it = m_Buttons.iterator(); it.hasNext(); )
                {
                    Button button = (Button)it.next();
                    ((EngineeringOption) button.getData()).setBoolean(true);
                    button.setSelection(true);                    
                }
                if (FESelectObjectsWizardPage.this.eventListener != null){
                    FESelectObjectsWizardPage.this.eventListener.handleEvent(null);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        
        m_deselectAll.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                for (Iterator it = m_Buttons.iterator(); it.hasNext(); )
                {
                    Button button = (Button)it.next();
                    ((EngineeringOption) button.getData()).setBoolean(false);
                    button.setSelection(false);
                }
                if (FESelectObjectsWizardPage.this.eventListener != null){
                    FESelectObjectsWizardPage.this.eventListener.handleEvent(null);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        m_restoreDefaults.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                int i = 0;
                for (Iterator it = m_Buttons.iterator(); it.hasNext(); i++)
                {
                    Button button = (Button)it.next();
                    ((EngineeringOption) button.getData()).setBoolean(((Boolean)m_Default.get(i)).booleanValue());
                    button.setSelection(((Boolean)m_Default.get(i)).booleanValue());
                }
                if (FESelectObjectsWizardPage.this.eventListener != null){
                    FESelectObjectsWizardPage.this.eventListener.handleEvent(null);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        
        this.setControl(composite);
        setPageComplete(true);

		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), 
				HelpUtil.getContextId(IHelpContextsSQMFEUI.GENERATE_DDL_SELECT_OBJECTS_WIZARD_PAGE, 
						FEUiPlugin.getDefault().getBundle().getSymbolicName()));
    }

    /**
     * @param composite
     * @param option
     */
    private void addButton(Composite composite, EngineeringOption option) {
        m_Default.add(new Boolean(option.getBoolean())); 
        final Button button = new Button(composite, SWT.CHECK);
        m_Buttons.add(button);
        button.setText(option.getOptionName());
        button.setData(option);
        button.setSelection(option.getBoolean());
        button.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                ((EngineeringOption) button.getData()).setBoolean(button
                        .getSelection());
                if (FESelectObjectsWizardPage.this.eventListener != null){
                    FESelectObjectsWizardPage.this.eventListener.handleEvent(null);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
    }
    
    public FEConfigurationData getOptions(){
        return this.filteredConfigurationData;
    }


	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(FEUiPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}
}