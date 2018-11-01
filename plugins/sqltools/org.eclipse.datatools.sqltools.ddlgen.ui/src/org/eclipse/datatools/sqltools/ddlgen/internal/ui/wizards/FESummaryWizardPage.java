/*******************************************************************************
 * Copyright (c) 2001, 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards;

import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.FEUiPlugin;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.IHelpContextsSQMFEUI;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.util.ResourceLoader;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class FESummaryWizardPage 
	extends WizardPage
	implements IContextProvider {

    private Table summaryTable;
    
    private FESummaryProperty[] properties;
    
    private boolean visible = false;
    
    public FESummaryWizardPage(String name) {
        super(name);

        setTitle(ResourceLoader.INSTANCE
                .queryString("FEWizard.SummaryPage.title"));  //$NON-NLS-1$
        setDescription(ResourceLoader.INSTANCE
                .queryString("FEWizard.SummaryPage.description"));  //$NON-NLS-1$
    }

    public void createControl(Composite parent) {

        Composite composite = new Composite(parent, SWT.NONE);
        
//        WorkbenchHelp.setHelp(composite, "org.eclipse.wst.rdb.fe.ui.infopop.genddl_wiz_summary");
    	
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);

        summaryTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION
                | SWT.V_SCROLL | SWT.H_SCROLL);
        GridData gd = new GridData();
        gd.widthHint = 1;
        gd.heightHint = 1;
        gd.horizontalAlignment = GridData.FILL;
        gd.verticalAlignment = GridData.FILL;
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        summaryTable.setLayoutData(gd);
        summaryTable.setLayout(layout);
        summaryTable.setLinesVisible(true);
        summaryTable.setHeaderVisible(true);
        summaryTable.addListener(SWT.Resize, new Listener() {
            public void handleEvent(Event e) {
                int weights[] = new int[] { 2, 4 };
                int weightsum = 0;
                for (int i = 0; i < weights.length; i++) {
                    weightsum += weights[i];
                }
                TableColumn[] cols = summaryTable.getColumns();
                int count = java.lang.Math.min(weights.length, cols.length);
                int tablewidth = summaryTable.getSize().x;
                for (int i = 0; i < count; i++) {
                    cols[i].setWidth(tablewidth * weights[i] / weightsum);
                }
            }
        });
        
        // Initialize Table
        TableLayout tblLayout = new TableLayout();
        tblLayout.addColumnData(new ColumnWeightData(1, true));
        TableColumn colSetting = new TableColumn(summaryTable, SWT.NULL);
        colSetting.setText(ResourceLoader.INSTANCE
                .queryString("FEWizard.SummaryPage.settingColumnHeader"));  //$NON-NLS-1$
        tblLayout.addColumnData(new ColumnWeightData(3, true));
        TableColumn colValue = new TableColumn(summaryTable, SWT.NULL);
        colValue.setText(ResourceLoader.INSTANCE
                .queryString("FEWizard.SummaryPage.valueColumnHeader"));  //$NON-NLS-1$
        

        setControl(composite);

		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), 
				HelpUtil.getContextId(IHelpContextsSQMFEUI.GENERATE_DDL_SUMMARY_WIZARD_PAGE, 
						FEUiPlugin.getDefault().getBundle().getSymbolicName()));
    }
    

    
    public void setProperties(FESummaryProperty[] properties){
        this.properties = properties;
    }
    
	public void setVisible(boolean visible){
	    this.visible = visible;
		if(visible)
			loadProperties();
		super.setVisible(visible);
	}
	
	public boolean isVisible(){
	    return this.visible;
	}
	
	private void loadProperties(){
	    summaryTable.removeAll();
		if (properties != null){
			int propertyCount =  properties.length;
			for( int index = 0; index < propertyCount; index++){
				new TableItem(summaryTable,
						SWT.NONE).setText(new String[] { properties[index].getPropertyName(),
						properties[index].getValue()});
			}
		}	    
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