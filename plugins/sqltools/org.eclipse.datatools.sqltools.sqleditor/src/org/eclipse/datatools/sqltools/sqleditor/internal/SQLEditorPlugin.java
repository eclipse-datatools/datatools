/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.profile.SQLToolsProfileListenersManager;
import org.eclipse.datatools.sqltools.editor.core.connection.SQLToolsConnectListenersManager;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.sqleditor.internal.profile.SQLEditorProfileListener;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLCodeScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.templates.SQLContributionTemplateStore;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLColorProvider;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class.
 */
public class SQLEditorPlugin extends AbstractUIPlugin{
    private static final int INTERNAL_ERROR = 0;

	public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.sqleditor";
    
    private static SQLEditorPlugin fgInstance;
    private SQLPartitionScanner    fPartitionScanner;
    private SQLColorProvider       fColorProvider;
    private SQLCodeScanner         fCodeScanner;

    /** The template store. */
    private SQLContributionTemplateStore                   _store;
    /** The context type registry. */
    private ContributionContextTypeRegistry _registry;

	private Properties _properties;
	private URL                        _pluginBase;
    //Properties contains general properties and defaults to preferences.
    private static final String        PROPERTIES  = "default.properties";                      //$NON-NLS-1$
    private static final String             CUSTOM_TEMPLATES_KEY   = "com.sybase.stf.dmp.ui.sqleditor.template.customtemplates"; //$NON-NLS-1$
    private SQLEditorProfileListener _sqlEditorProfileListener = null;

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLEditorPlugin() {
        super();
        fgInstance = this;
    }

    /**
     * Handles plug-in activation.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext) 
     */
    public void start( BundleContext context ) throws Exception {
        super.start( context );
        init();
        _sqlEditorProfileListener = new SQLEditorProfileListener();
        SQLToolsProfileListenersManager pManager = SQLToolsProfileListenersManager.getInstance();
        pManager.addProfileListener(_sqlEditorProfileListener);
        SQLToolsConnectListenersManager cManager = SQLToolsConnectListenersManager.getInstance();
        cManager.addConnectListener(_sqlEditorProfileListener);
        
    }

    /**
     * Handles plug-in deactivation.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext context ) throws Exception {
        SQLToolsProfileListenersManager pManager = SQLToolsProfileListenersManager.getInstance();
        pManager.removeProfileListener(_sqlEditorProfileListener);
        SQLToolsConnectListenersManager cManager = SQLToolsConnectListenersManager.getInstance();
        cManager.removeConnectListener(_sqlEditorProfileListener);

        super.stop( context );
    }

    /**
     * Returns the shared instance of this plug-in.
     * 
     * @return the shared instance of this plug-in
     */
    public static SQLEditorPlugin getDefault() {
        return fgInstance;
    }

    /**
     * Get a scanner for creating SQL partitions.
     * 
     * @return the SQL partition scanner
     */
     public SQLPartitionScanner getSQLPartitionScanner() {
        if (fPartitionScanner == null)
            fPartitionScanner = new SQLPartitionScanner();
        return fPartitionScanner;
    }
    
    /**
     * Gets a SQL code scanner.
     * 
     * @return the SQL code scanner
     */
     public RuleBasedScanner getSQLCodeScanner() {
        if (fCodeScanner == null)
            fCodeScanner = new SQLCodeScanner( getSQLColorProvider() );
        return fCodeScanner;
    }
    
    /**
     * Gets a SQL color provider.
     * 
     * @return the SQL color provider
     */
     public SQLColorProvider getSQLColorProvider() {
        if (fColorProvider == null)
            fColorProvider = new SQLColorProvider();
        return fColorProvider;
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
     * Logs an error message with an exception.
     * 
     * @param e Exception.
     */
    public void log(String message, Throwable e) {
        log(createErrorStatus(message, e));
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

 	public IStatus createErrorStatus(Throwable e) {
 		return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
 				INTERNAL_ERROR, SQLEditorResources.plugin_internal_error, e); 
 	}

    public IStatus createErrorStatus(String message, Throwable e) {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
                INTERNAL_ERROR, message, e);
    }


    /**
     * Returns this plug-in's template store.
     * 
     * @return the template store of this plug-in instance
     */
    public SQLContributionTemplateStore getTemplateStore()
    {
        if (_store == null)
        {
            _store = new SQLContributionTemplateStore(getTemplateContextTypeRegistry(), getDefault()
                .getPreferenceStore(), CUSTOM_TEMPLATES_KEY);
            try
            {
                _store.load();
            }
            catch (IOException e)
            {
                getDefault().getLog().log(
                    new Status(IStatus.ERROR, "com.sybase.stf.dmp.debugger", IStatus.OK, "", e)); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        return _store;
    }

    /**
     * Returns this plug-in's context type registry.
     * 
     * @return the context type registry for this plug-in instance
     */
    public ContextTypeRegistry getTemplateContextTypeRegistry()
    {
        if (_registry == null)
        {
            // create an configure the contexts available in the template editor
            _registry = new ContributionContextTypeRegistry();
            _registry.addContextType(GenericSQLContextType.SQL_CONTEXT_TYPE);
            Collection c = SQLToolsUIFacade.getConfigurations();
            Collection ctxTypes = new ArrayList();
            for (Iterator iter = c.iterator(); iter.hasNext();) {
				SQLDevToolsUIConfiguration config = (SQLDevToolsUIConfiguration) iter.next();
				ctxTypes.add(config.getSQLUIService().getSQLContextType());
			}

            for (Iterator i = ctxTypes.iterator(); i.hasNext();)
            {
                //getId() will return null before calling addContextType
                _registry.addContextType(((GenericSQLContextType) i.next()).getSQLContextId());
            }
        }
        return _registry;
    }
    
    private void init()
    {
        // get properties.
        _properties = new Properties();
        InputStream input = null;
        _pluginBase = getBundle().getEntry("/"); //$NON-NLS-1$
        try
        {
            input = (new URL(_pluginBase, PROPERTIES)).openStream();
            if (input != null)
            {
                _properties.load(input);
            }
            else
            {
                log("Input is null");
            }
        }
        catch (IOException ee)
        {
            log(ee);
        }
        finally
        {
            try
            {
                if (input != null)
                {
                    input.close();
                }
            }
            catch (IOException ee)
            {
                log(ee);
            }
        }

    }

    /**
     * Returns the plugin's properties,
     */
    public Properties getProperties()
    {
        return _properties;
    }

    /**
     * Returns the standard display to be used. The method first checks, if the thread calling this method has an
     * associated dispaly. If so, this display is returned. Otherwise the method returns the default display.
     */
    public static Display getStandardDisplay()
    {
        Display display;
        display = Display.getCurrent();
        if (display == null)
        display = Display.getDefault();
        return display;
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

    /**
     * @return
     */
    public static Shell getActiveWorkbenchShell()
    {
        IWorkbenchWindow window = getActiveWorkbenchWindow();
        if (window != null)
        {
            return window.getShell();
        }
        IWorkbenchWindow[] windows = getDefault().getWorkbench().getWorkbenchWindows();
        if (windows.length > 0)
        return windows[0].getShell();
        return null;
    }

    public static Display getDisplay()
    {
        Shell shell = getActiveWorkbenchShell();
        if (shell != null)
        return shell.getDisplay();
        else
        return Display.getDefault();
    }

    public static IEditorPart getActiveEditor()
    {
    	return SQLEditorPlugin.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    }
    
    public static Collection getSQLEditorActionContributorExtension()
    {
        return SQLEditorContributorExtensionRegistry.getInstance().getActionExtensions();
    }

} // end class
