package org.eclipse.datatools.connectivity.repository.file;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class FileRepositoryPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.connectivity.repository.file";
	public static final String FILE_NAME_PROP_ID = PLUGIN_ID + ".fileName";
	public static final String ENCRYPT_PROP_ID = PLUGIN_ID + ".encrypt";
	public static final String PASSWORD_PROP_ID = PLUGIN_ID + ".password";

	// The shared instance
	private static FileRepositoryPlugin plugin;
	
	/**
	 * The constructor
	 */
	public FileRepositoryPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static FileRepositoryPlugin getDefault() {
		return plugin;
	}

}
