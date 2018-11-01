/*******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Exadel Inc - additional implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookDocumentProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class SqlscrapbookPlugin extends AbstractUIPlugin {
    // The shared instance of this plugin class
    private static SqlscrapbookPlugin plugin;
    private IDocumentProvider documentProvider;
    
    public static final String PLUGIN_ID="org.eclipse.datatools.sqltools.sqlscrapbook";
    /**
     * The constructor.
     */
    public SqlscrapbookPlugin() {
        super();
        plugin = this;
    }

    /**
     * Returns the shared instance.
     */
    public static SqlscrapbookPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns the plugin's resource bundle,
     */
    public ResourceBundle getResourceBundle() {
        return Platform.getResourceBundle(getDefault().getBundle());
    }

    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     */
    public static String getResourceString(String key) {
        ResourceBundle bundle = SqlscrapbookPlugin.getDefault().getResourceBundle();
        try {
            return (bundle != null) ? bundle.getString(key) : key;
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Gets the string resource for a key and does one substitution.
     * 
     * @param key
     *            The key for the String
     * @param s1
     *            the substitution
     * @return the new string after substitution
     */
    public String getString(String key, Object s1) {
        String resourceString = getResourceString( key );
        return MessageFormat.format(resourceString, new Object[] { s1 });
    }

    public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	public static IWorkbenchPage getActivePage() {
		return getDefault().internalGetActivePage();
	}

	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow();
	}

	public static Shell getActiveWorkbenchShell() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window != null) {
			return window.getShell();
		}
		return null;
	}

	private IWorkbenchPage internalGetActivePage() {
		IWorkbenchWindow window = getWorkbench().getActiveWorkbenchWindow();
		if (window == null)
			return null;
		return getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}

	public static String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(Throwable e) {
		log(new Status(IStatus.ERROR, getPluginId(), 1,
				getResourceString("SQLScrapbookPlugin.internal_error"), e)); //$NON-NLS-1$
	}

	public IDocumentProvider getSQLEditorDocumentProvider() {
		if (documentProvider == null)
			documentProvider = new SQLScrapbookDocumentProvider();
		return documentProvider;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

}