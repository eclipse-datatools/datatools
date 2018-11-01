/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.ui;

import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.result.ResultConfiguration;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.core.IResultManagerListener;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultsView;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.internal.utils.StatusLogger;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ResultsViewUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.result.ui";
	
	public static final String BUNDLE_NAME = PLUGIN_ID + ".PluginResources";

	private ResourceBundle _bundle = ResourceBundle.getBundle(BUNDLE_NAME);

	private Color _disabledBakColor;
	
	// The shared instance
	private static ResultsViewUIPlugin plugin;

	/**
	 * The constructor
	 */
	public ResultsViewUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		// Set the ResultConfiguration values to what is stored in the preferences.
		ResultConfiguration resultConfig = ResultConfiguration.getInstance();
		IPreferenceStore prefStore = getPreferenceStore();
		resultConfig.setMaxRowCount(prefStore.getInt(PreferenceConstants.SQL_RESULTS_VIEW_MAX_ROW_COUNT));
		resultConfig.setMaxDisplayRowCount(prefStore.getInt(PreferenceConstants.SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT));
		resultConfig.setAutoSave(prefStore.getBoolean(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY));
		resultConfig.setAutoClean(prefStore.getBoolean(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY));
		resultConfig.setShowLabel(prefStore.getBoolean(PreferenceConstants.SQL_RESULT_VIEW_SHOW_LABELS));
		
        getPreferenceStore().addPropertyChangeListener(
        		new IPropertyChangeListener()
        		{
					public void propertyChange(PropertyChangeEvent event) 
					{
						String name = event.getProperty();
						Object newValue = event.getNewValue();
						if(name.equals(ResultsConstants.SQL_RESULTS_VIEW_MAX_ROW_COUNT))
						{
						    ResultConfiguration.getInstance().setMaxRowCount(((Integer)newValue).intValue());
						}
						if(name.equals(ResultsConstants.SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT))
						{
						    ResultConfiguration.getInstance().setMaxDisplayRowCount(((Integer)newValue).intValue());
						}
						if(name.equals(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY))
						{
						    ResultConfiguration.getInstance().setAutoSave(((Boolean)newValue).booleanValue());
						}
						if(name.equals(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY))
                        {
						    ResultConfiguration.getInstance().setAutoClean(((Boolean)newValue).booleanValue());
                        }
						if(name.equals(PreferenceConstants.SQL_RESULT_VIEW_SHOW_LABELS))
                        {
						    ResultConfiguration.getInstance().setShowLabel(((Boolean)newValue).booleanValue());
                        }
					}
        			
        		});
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
        if(_disabledBakColor != null)
        {
            _disabledBakColor.dispose();
        }
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static ResultsViewUIPlugin getDefault() {
		return plugin;
	}

	public synchronized Color getDisabledBakColor() {
		if (_disabledBakColor == null) {
			_disabledBakColor = new Color(getDefault().getWorkbench()
					.getDisplay(), 238, 237, 224);
		}
		return _disabledBakColor;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * Returns this plugin's unique identifier
	 * 
	 * @retun this plugin's unique identifier
	 */
	public static String getPluginId() {
		return PLUGIN_ID;
	}

	public static IResultManager getResultManager() {
		return ResultsViewPlugin
				.getDefault().getResultManager();
	}
	
	/**
	 * Returns a logger.
	 * 
	 * @param bundle
	 *            the bundle used in logger
	 * @return a logger
	 */
	public static ILogger getLogger(ResourceBundle bundle) {
		return new StatusLogger(getDefault().getLog(), PLUGIN_ID,
				bundle == null ? getDefault()._bundle : bundle);
	}

	public ResourceBundle getResourceBundle()
	{
		return _bundle;
	}
	
	/**
	 * check result view first, then fire listener and delegate work to ResultViewConstrol
	 * @author linsong
	 *
	 */
	public static class ResultViewControlListener implements IResultManagerListener
	{

		public void allResultInstancesRemoved() {
			getResultsViewConstrol().allResultInstancesRemoved();			
		}

		public void parametersShow(IResultInstance instance, List params) {
			getResultsViewConstrol().parametersShow(instance, params);			
		}

		public void resultInstanceAppended(IResultInstance instance,
				ResultItem result, int index) {
			getResultsViewConstrol().resultInstanceAppended(instance, result, index);
		}

		public void resultInstanceCreated(IResultInstance instance) {
			getResultsViewConstrol().resultInstanceCreated(instance);
		}

		public void resultInstanceRemoved(IResultInstance instance) {
			getResultsViewConstrol().resultInstanceRemoved(instance);			
		}

		public void resultInstanceReset(IResultInstance instance) {
			getResultsViewConstrol().resultInstanceReset(instance);
		}

		public void resultInstanceStatusUpdated(IResultInstance instance) {
			getResultsViewConstrol().resultInstanceStatusUpdated(instance);
		}

		public void resultInstancesRemoved(IResultInstance[] instances) {
			getResultsViewConstrol().resultInstancesRemoved(instances);
		}
		
		private ResultsViewControl getResultsViewConstrol()
		{
			ResultsView resultsView = ResultViewUIUtil.checkResultView();
			return resultsView.getResultsViewControl();
		}
		
		public boolean equals(Object obj) 
		{
			if(obj instanceof ResultViewControlListener)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}
