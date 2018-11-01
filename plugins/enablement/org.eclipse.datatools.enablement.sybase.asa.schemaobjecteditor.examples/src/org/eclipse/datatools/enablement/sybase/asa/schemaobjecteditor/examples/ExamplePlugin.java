/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASABaseColumnValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASABaseTableValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASABaseUserDefinedTypeValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASARoutineValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASATableValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASATriggerValidator;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidatorRegistry;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistryImpl;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ExamplePlugin extends AbstractUIPlugin
{
    public static final String        PLUGIN_ID = "org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples";
    private static ExamplePlugin      _instance = null;
    private SQLModelValidatorRegistry _registry;

    public ExamplePlugin()
    {
        super();
        _instance = this;
    }

    public void start(BundleContext context) throws Exception
    {
        super.start(context);
        _registry = EditorCorePlugin.getDefault().getSQLModelValidatorRegistry();

        // Force to load. Put in another thread to avoid circular load:
        // DMPASAPlugin->SQLDevToolsConfigRegistryImpl->ASAConfig->DMPASAPlugin
        new Thread()
        {
            public void run()
            {
                SQLDevToolsConfigRegistryImpl.INSTANCE.getProducts();
                DatabaseVendorDefinitionId id = ASAConfig.getInstance().getDatabaseVendorDefinitionId();
                DatabaseVendorDefinitionId[] alsoSupports = ASAConfig.getInstance().alsoSupport();

                _registry.registerValidator(SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseUserDefinedType(),
                        new SybaseASABaseUserDefinedTypeValidator());
                _registry.registerValidator(SybaseasasqlmodelPackage.eINSTANCE.getSybaseASATable(),
                        new SybaseASATableValidator());
                _registry.registerValidator(SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseTable(),
                        new SybaseASABaseTableValidator());
                _registry.registerValidator(SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseColumn(),
                        new SybaseASABaseColumnValidator());
                _registry.registerValidator(SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseTrigger(),
                        new SybaseASATriggerValidator());
                _registry.registerValidator(SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseProcedure(),
                        new SybaseASARoutineValidator());
                for (int i = 0; i < alsoSupports.length; i++)
                {
                    _registry.registerValidator(SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseTempTable(),
                            alsoSupports[i], new SybaseASABaseTableValidator());
                }

            }
        }.start();

    }

    public void stop(BundleContext context) throws Exception
    {
        _instance = null;
        super.stop(context);
    }

    public String getPluginId()
    {
        return PLUGIN_ID;
    }

    public static ExamplePlugin getDefault()
    {
        return _instance;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     * 
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path)
    {
        return AbstractUIPlugin.imageDescriptorFromPlugin(getDefault().getPluginId(), path);
    }

    /**
     * Returns the standard display to be used. The method first checks, if the thread calling this method has an
     * associated display. If so, this display is returned. Otherwise the method returns the default display.
     */
    public static Display getStandardDisplay()
    {
        Display display;
        display = Display.getCurrent();
        if (display == null)
        {
            display = Display.getDefault();
        }
        return display;
    }

    /**
     * Gets active workbench page.
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
     * Gets active workbench window.
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
     * Returns the active workbench shell
     * 
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
}
