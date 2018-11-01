/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.preference;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ParameterViewerDescriptor;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ParameterViewerRegistryReader;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultSetViewerDescriptor;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultSetViewerRegistryReader;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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
    private Combo paramViewersCombo;
    private Text  maxParamCount;
    private String[] paramViewers;
    private String viewerName;  
    private IPreferenceStore store;
    public static final String DEFAULT_VIEWER = Messages.ResultSetViewerPage_defaultViewer;
    
    public void init(IWorkbench workbench)
    {
        setPreferenceStore(ResultsViewUIPlugin.getDefault().getPreferenceStore());
        store = getPreferenceStore();
        populateViewerNames();
        populateParamViewerNames(); 
    }
    public static final String PARAM_DEFAULT_VIEWER= Messages.ParameterViewerPage_defaultViewer;
    private ModifyListener modifyListener = new ModifyListener() {
        public void modifyText(ModifyEvent event) {
            try {
                    int maxparameterLength = Integer.parseInt(maxParamCount.getText().trim());
                if (maxparameterLength < 1) {
                        setMessage(Messages.ParameterViewerPreferencePage_parameterResultOptions_LessthanOne, //$NON-NLS-1$
                                DialogPage.ERROR);
                        setValid(false);
                        updateApplyButton();
                    } else {
                        setMessage(null);
                        setValid(true);
                        updateApplyButton();
                    }
                } catch (Exception e) {
                    setMessage(Messages.SQLResultsViewPage_resultsetoptions_invalidnumberformat, //$NON-NLS-1$
                            DialogPage.ERROR);
                    setValid(false);
                    updateApplyButton();
                }
            }
        };
        /**
         * Gets the default viewer name from extensions.  If more the one extension parameter table viewers
         * are available, then the internal default viewer will be used
         * @return the default result parameter table viewer name
         */
        public static String getParameterViewerNameFromExtension() {
                    List viewerDescriptors = ParameterViewerRegistryReader.getInstance()
                            .getParameterViewers();
                    String viewerName = PARAM_DEFAULT_VIEWER;
                    if (viewerDescriptors.size() == 1) {
                        viewerName = ((ParameterViewerDescriptor) viewerDescriptors.get(0))
                                .getDefaultViewer();
                        if (viewerName == null) {
                            viewerName = PARAM_DEFAULT_VIEWER;
                        }
                    }
                    return viewerName;
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
     * Populate the list of viewer names for parameter table viewer that are available from extensions   
     */
    private void populateParamViewerNames() {
                List viewerDescriptors = ParameterViewerRegistryReader.getInstance()
                        .getParameterViewers();
                int viewerCount = viewerDescriptors.size();
        
                paramViewers = new String[viewerCount + 1];
                // add internal default viewer as first one
                paramViewers[0] = PARAM_DEFAULT_VIEWER;
                Iterator iter = viewerDescriptors.iterator();
                int count = 0;
                while (iter.hasNext()) {
                    ParameterViewerDescriptor desc = (ParameterViewerDescriptor) iter
                            .next();
                    paramViewers[++count] = desc.getViewerID();
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
        Group resultViewersGroup = new Group(comp, SWT.NONE);
        
        resultViewersGroup.setText(Messages.ResultSetViewerPage_group);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        resultViewersGroup.setLayoutData(gridData); 
        GridLayout gLayout = new GridLayout();
        gLayout.numColumns = 2;
        resultViewersGroup.setLayout(gLayout);
        Label label = new Label(resultViewersGroup, SWT.NONE);
        label.setText(Messages.ResultSetViewerPage_select_viewer);
        viewersCombo = new Combo(resultViewersGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        viewersCombo.setToolTipText(Messages.ResultSetViewerPage_viewer_tooltip);
        GridData comboData = new GridData(GridData.FILL_HORIZONTAL);
        viewersCombo.setLayoutData(comboData);
        Group paramViewersGroup = new Group(comp, SWT.NONE);
        paramViewersGroup.setText(Messages.ParameterViewerPage_group);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        paramViewersGroup.setLayoutData(gridData);
        paramViewersGroup.setLayout(gLayout);
        Label paramLabel = new Label(paramViewersGroup, SWT.NONE);
        paramLabel.setText(Messages.ParameterViewerPage_select_viewer);
        paramViewersCombo = new Combo(paramViewersGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        paramViewersCombo.setToolTipText(Messages.ParameterViewerPage_viewer_tooltip);
        paramViewersCombo.setLayoutData(comboData);
        // populate viewers
        for (int i = 0; i < paramViewers.length; i++) {
            paramViewersCombo.add(paramViewers[i]);
        }
        // select viewer name from store
        paramViewersCombo.setText(store.getString(PreferenceConstants.PARAMETER_VIEWER_VIEWERNAME));
        paramViewersCombo.addSelectionListener(new SelectionAdapter() {
                    public void widgetSelected(SelectionEvent evt) {
                        viewerName = paramViewersCombo.getText();
                    }
                });
        Composite compMaxParameter = new Composite(comp, SWT.NONE);
        compMaxParameter.setLayoutData(gridData); 
        GridLayout layoutMaxParameter = new GridLayout();
        layoutMaxParameter.numColumns = 2;
        compMaxParameter.setLayout(layoutMaxParameter);
        Label maxParameterLabel = new Label(compMaxParameter, SWT.NONE);
        maxParameterLabel.setText(Messages.ParameterViewer_ellipsisEnabledValue);
        maxParameterLabel.setToolTipText(Messages.ParameterViewerPreferencePage_parameterResultOptions_maxParmeterCount_tooltip);// SQLResultsViewPage_resultsetoptions_maxrowcount_tooltip
        maxParamCount = new Text(compMaxParameter, SWT.SINGLE | SWT.BORDER);
        maxParamCount.setToolTipText(Messages.ParameterViewerPreferencePage_parameterResultOptions_maxParmeterCount_tooltip);
        maxParamCount.setLayoutData(gridData);
        // select max parameter count value from Store
        maxParamCount.setText(store.getString(PreferenceConstants.ELLIPSIS_ENABLED_VALUE_LENGTH));
        maxParamCount.addModifyListener(modifyListener);
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
                store.setValue(PreferenceConstants.RESULT_SET_VIEWER_VIEWERNAME, 
                viewersCombo.getText());
                store.setValue(PreferenceConstants.PARAMETER_VIEWER_VIEWERNAME,
                                        paramViewersCombo.getText());
                store.setValue(PreferenceConstants.ELLIPSIS_ENABLED_VALUE_LENGTH,
                                        maxParamCount.getText().trim());
        return super.performOk();
    }
    
    protected void performDefaults()
    {               
        viewersCombo.setText(getViewerNameFromExtension());
        paramViewersCombo.setText(getParameterViewerNameFromExtension());
                maxParamCount.setText(store
                        .getDefaultString(PreferenceConstants.ELLIPSIS_ENABLED_VALUE_LENGTH));
        super.performDefaults();        
    }
}
