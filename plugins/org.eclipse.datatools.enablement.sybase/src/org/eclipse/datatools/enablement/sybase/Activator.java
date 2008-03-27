package org.eclipse.datatools.enablement.sybase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.ui.SybaseDatabaseProfileSettingManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.sybase";

    private static final int INTERNAL_ERROR = 100000000;
   
    private IPropertyChangeListener propertyChangeListener = null;
	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);	
		IPreferenceStore store = this.getPreferenceStore();
		SybaseDatabaseProfileSettingManager manager = SybaseDatabaseProfileSettingManager.getInstance();
		manager.setShowSchemaGlobal(store.getBoolean(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_SCHEMA));
		manager.setShowOwnerGlobal(store.getBoolean(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_OWNER));
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
	public static Activator getDefault() {
		return plugin;
	}

    /** 
     * Logs runtime status. 
     * 
     * @param status Runtime status. 
     */ 
    public void log(IStatus status) { 
            getLog().log(status); 
    } 

    /** 
     * Logs error message. 
     * 
     * @param message Error message. 
     */ 
    public void log(String message) { 
            log(createErrorStatus(message)); 
    } 

    /** 
     * Logs error message. 
     * 
     * @param message Error message. 
     */ 
    public void debug(String message) { 
        log(createInfoStatus(message)); 
    } 
    
    /** 
     * Logs and exception. 
     * 
     * @param e Exception. 
     */ 
    public void log(Throwable e) { 
            log(createErrorStatus(e)); 
    } 

    public IStatus createErrorStatus(String message) { 
            return new Status(IStatus.ERROR, getBundle().getSymbolicName(), 
                            INTERNAL_ERROR, message, null); 
    } 

    public IStatus createInfoStatus(String message) { 
        return new Status(IStatus.INFO, getBundle().getSymbolicName(), 
                INTERNAL_ERROR, message, null); 
    } 
    
    public IStatus createErrorStatus(Throwable e) { 
            return new Status(IStatus.ERROR, getBundle().getSymbolicName(), 
                            INTERNAL_ERROR, Messages.plugin_internal_error, e); 
    } 

    public IStatus createErrorStatus(String message, Throwable e) { 
       return new Status(IStatus.ERROR, getBundle().getSymbolicName(), 
               INTERNAL_ERROR, message, e); 
    } 
    
    /** 
    * Logs an error message with an exception. 
    * 
    * @param e Exception. 
    */ 
    public void log(String message, Throwable e) { 
       log(createErrorStatus(message, e)); 
    } 

    /**
     * Get active workbench page.
     * <p>
     * This method acts as a convenience for plug-in implementors.
     * </P>
     * 
     * @return IWorkbenchPage the workbench page for this plug-in
     */
    public static IWorkbenchPage getActiveWorkbenchPage()
    {
        IWorkbenchPage workbenchPage = getActiveWorkbenchWindow().getActivePage();
        if (workbenchPage != null)
        {
            return workbenchPage;
        }
        IWorkbenchPage[] workbenchPages = getActiveWorkbenchWindow().getPages();
        if (workbenchPages.length > 0)
        {
            return workbenchPages[0];
        }
        return null;
    }

    /**
     * Get active workbench window.
     * <p>
     * This method exists as a convenience for plug-in implementors.
     * </p>
     * 
     * @return IWorkbenchWindow the workbench for this plug-in
     */
    public static IWorkbenchWindow getActiveWorkbenchWindow()
    {
        IWorkbenchWindow window = getDefault().getWorkbench().getActiveWorkbenchWindow();
        if (window != null)
        {
            return window;
        }
        IWorkbenchWindow[] windows = getDefault().getWorkbench().getWorkbenchWindows();
        if (windows.length > 0)
        {
            return windows[0];
        }
        return null;
    }

}
