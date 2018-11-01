/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.services;

import java.io.File;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.ConnectionUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

/**
 * @author ljulien
 */
public class LabelService implements ILabelService
{
    private static final ResourceLoader resourceLoader = ResourceLoader.getResourceLoader();
    
    private static final String ELEMENT_SELECTED = resourceLoader.queryString("DATATOOLS.CORE.UI.EXPLORER.MULTI_SELECTION"); //$NON-NLS-1$
    private static final String CONNECTION_NAME = resourceLoader.queryString("DATATOOLS.CORE.UI.STATUS.CONNECTION"); //$NON-NLS-1$
    private static final String PROJECT_NAME = resourceLoader.queryString("DATATOOLS.CORE.UI.STATUS.PROJECT"); //$NON-NLS-1$
    private static final String BLANK = ""; //$NON-NLS-1$
    
    private Map typeProvider = new HashMap ();
    
    private Object element;
    private LabelInfo labelInfo;
    
    public LabelService ()
    {
        initializeLabelServiceProvider ();
    }

    private void initializeLabelServiceProvider ()
    {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core.ui", "labelService");//$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) 
		{
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) 
			{
				if(configElements[j].getName().equals("contributor"))//$NON-NLS-1$
				{
					String displayType = configElements[j].getAttribute("displayType");//$NON-NLS-1$
					String iconLocation = configElements[j].getAttribute("iconLocation");//$NON-NLS-1$
					String type = configElements[j].getAttribute("type"); //$NON-NLS-1$
					
				    try
                    {
                        Bundle bundle = Platform.getBundle(configElements[j].getDeclaringExtension().getNamespace());
                        LabelSelector selector = ( configElements[j].getAttribute("selector") != null ) ?  //$NON-NLS-1$
                            ( LabelSelector )configElements[j].createExecutableExtension( "selector" ) : null; //$NON-NLS-1$
                        if( !typeProvider.containsKey( type ) ) typeProvider.put( type, new ArrayList() ); 
                        ( ( List )typeProvider.get( type ) ).add( new LabelInfo (bundle, iconLocation, displayType, selector ) );
                    }
                    catch (CoreException e)
                    {
                        e.printStackTrace();
                    }
				}
			}
		}
    }

    private LabelInfo getLabelInfo(EClass metaclass)
    {
/*        
        if (this.cache.containsKey(metaclass))
        {
            return (LabelInfo) this.cache.get(metaclass);
        }
*/        
        Vector sortedClasses = this.computeClassOrder(metaclass);
        LabelInfo provider = this.getProvider(sortedClasses);
/*        
        if (provider != null)
        {
            this.cache.put(metaclass, provider);
        }
*/        
        return provider;
    }
    
    private LabelInfo getLabelInfo (Class metaclass)
    {
/*        
        if (this.cache.containsKey(metaclass))
        {
            return (LabelInfo) this.cache.get(metaclass);
        }
*/        
        Vector sortedClasses = this.computeClassOrder(metaclass);
        LabelInfo provider = this.getClassProvider(sortedClasses);
/*        
        if (provider != null)
        {
            this.cache.put(metaclass, provider);
        }
*/        
        return provider;
    }
  
    private Vector computeClassOrder(Class metaclass)
    {
        Vector result = new Vector(4);
        result.addElement(metaclass);
        int index = 0;
        for (index = 0; index < result.size(); ++index)
        {
            Class clazz = (Class) result.elementAt(index);
            Class[] it = clazz.getInterfaces();
            for  (int i = 0, n = it.length; i < n; i++)
            {
                result.addElement(it[i]);
            }
        }
        return result;
    }
    
    private Vector computeClassOrder(EClass metaclass)
    {
        Vector result = new Vector(4);
        result.addElement(metaclass);
        int index = 0;
        for (index = 0; index < result.size(); ++index)
        {
            EClass clazz = (EClass) result.elementAt(index);
            Iterator it = clazz.getESuperTypes().iterator();
            while (it.hasNext())
                result.addElement(it.next());
        }
        return result;
    }

    private LabelInfo getClassProvider(Vector classes)
    {
        int count = classes.size();
        for (int i = 0; i < count; ++i)
        {
            Class clazz = (Class) classes.elementAt(i);
            String name = clazz.getName();
            if (this.typeProvider.containsKey(name))
            {
                Iterator labelInfoListIterator = ( ( List )typeProvider.get( name ) ).iterator();
                while( labelInfoListIterator.hasNext() )
                {
                    LabelInfo localLabelInfo = ( LabelInfo )labelInfoListIterator.next();
                    if( localLabelInfo.getSelector() == null ) return localLabelInfo;
                    else if( localLabelInfo.getSelector().select( element ) ) return localLabelInfo;
                }
            }
        }
        return null;
    }

    private LabelInfo getProvider(Vector classes)
    {
        int count = classes.size();
        for (int i = 0; i < count; ++i)
        {
            EClass clazz = (EClass) classes.elementAt(i);
            String name = clazz.getInstanceClassName();
            if (this.typeProvider.containsKey(name))
            {
                Iterator labelInfoListIterator = ( ( List )typeProvider.get( name ) ).iterator();
                while( labelInfoListIterator.hasNext() )
                {
                    LabelInfo localLabelInfo = ( LabelInfo )labelInfoListIterator.next();
                    if( localLabelInfo.getSelector() == null ) return localLabelInfo;
                    else if( localLabelInfo.getSelector().select( element ) ) return localLabelInfo;
                }
            }
        }
        return null;
    }
    
    private LabelInfo getLabelInfo ()
    {
        if( element instanceof EClass ) return getLabelInfo( ( EClass )element );
        else if (element instanceof EObject)
        {
            return getLabelInfo (((EObject)element).eClass());
        }
        else if (element instanceof Class)
        {
            return getLabelInfo ((Class)element);
        }
        else    
        {
            return getLabelInfo ((Class)element.getClass());
        }
    }
    
    private void findService ()
    {
        this.labelInfo = (LabelInfo) getLabelInfo ();
    }
    
    private boolean matchLabelService ()
    {
        if (this.labelInfo == null)
        {
            findService ();
        }
        return this.labelInfo != null;
    }

    public void setElement (Object element)
    {
        this.labelInfo = null;
        this.element = element;
    }
    
    public Image getIcon()
    {
    	if (this.element instanceof ILabelProvider)
    	{
    		return ((ILabelProvider)this.element).getImage(this.element);
    	}
    	else if (matchLabelService ())
        {
            return this.labelInfo.getIcon();
        }
        return null;
    }

    public String getDisplayType()
    {
        if (matchLabelService())
        {
	        String displayType = this.labelInfo.getDisplayType();
	        return displayType != null ? displayType : 
	            element instanceof EObject? ((EObject)element).eClass().getName() : 
	                element instanceof IVirtualNode ? ((IVirtualNode)element).getDisplayName() : null;
        }
        return null;
    }
    
    private String buildConnectionName (Object object, String name)
    {
        String connectionName = ConnectionUtil.getConnectionForEObject(((ICatalogObject)object).getCatalogDatabase()).getName();
        return MessageFormat.format(CONNECTION_NAME, new String [] {name, connectionName});
    }
    
    private String buildResourceName (String resourcePath, String name)
    {
        return MessageFormat.format(PROJECT_NAME, new String [] {name, resourcePath});
    }
    
	public static String getResourcePath (Resource resource)
	{
		String fileName = ""; //$NON-NLS-1$
		if (resource == null || resource.getURI() == null)
		{
		    return null;
		}
		String [] segments = resource.getURI().segments();
		for (int j = 1, n = segments.length; j < n ; j++)
		{
			fileName += (segments[j].indexOf(File.separator) != -1)? segments[j] : File.separator + segments[j];
		}
		return fileName;
	}

	private IFile getIFile (Resource resource)
    {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		String fileString = getResourcePath(resource);
		if (fileString != null && !fileString.equals(BLANK)) {
			return workspaceRoot.getFile(new Path(fileString));
		}
		return null;
    }
    
    private String getIResourcePath (Object object)
    {
        if (object == null || !(object instanceof EObject))
        {
            return null;
        }
        return getIResourcePath (getIFile(((EObject)object).eResource()));
    }
    
    private String getIResourcePath (IFile file)
    {
        return file == null ? null : file.getFullPath().toOSString(); 
    }
    
    private Object getParent (IVirtualNode object)
    {
        Object parent = null;
        while (!((parent = object.getParent()) instanceof SQLObject))
        {
            if (parent instanceof IVirtualNode)
            {
                object = (IVirtualNode)parent;
            }
            else
            {
                break;
            }
        }
        return parent;
    }
    
    private String getFullName (ENamedElement object)
    {
		return "<" + IDataToolsUIServiceManager.INSTANCE.getLabelService(object).getDisplayType() + "> " + object.getName();  //$NON-NLS-1$//$NON-NLS-2$
    }
    
    private String getFullName (IVirtualNode object)
    {
		return "<" + IDataToolsUIServiceManager.INSTANCE.getLabelService(object).getDisplayType() + "> " + object.getDisplayName();  //$NON-NLS-1$//$NON-NLS-2$
    }

    private String getFullName (EObject object, String name)
    {
		return "<" + IDataToolsUIServiceManager.INSTANCE.getLabelService(object).getDisplayType() + "> " + name;  //$NON-NLS-1$//$NON-NLS-2$
    }

    private String getName (ENamedElement object)
    {
        if (object instanceof ICatalogObject)
        {
            return buildConnectionName(object, getFullName(object));
        }
        else
        {
            String projectPath = getIResourcePath (object);
            if (projectPath == null)
            {
                return getFullName(object);
            }
            return buildResourceName (projectPath, getFullName(object));
        }
    }
    
    private String getName (EObject object)
    {
        try
        {
            Method method = object.getClass().getMethod("getName", new Class [] {});
            try
            {
                String name = (String) method.invoke(object, new Object [] {});
                if (getIFile(object.eResource()) != null)
                {
                    return buildResourceName (getResourcePath(object.eResource()), getFullName(object, name));
                }
                else if (object.eContainer() != null && object.eContainer() instanceof ICatalogObject)
                {
                    return buildConnectionName(object.eContainer(), getFullName(object, name));
                }
                else
                {
                    return getFullName(object, name);
                }
            }
            catch (Exception e1)
            {
            }
        }
        catch (Exception e)
        {
        }
        return BLANK;
    }
    
    private String getName (IAdaptable object)
    {
        Object file = ((IAdaptable)object).getAdapter(IFile.class);
        if (file != null)
        {
            return getIResourcePath ((IFile)file);
        }
        return BLANK;
    }
    
    private String getName (IVirtualNode object)
    {
        Object parent = getParent ((IVirtualNode)object);
        if (parent != null && parent instanceof ICatalogObject)
        {
            return buildConnectionName (parent, getFullName (object));
        }
        else if (object instanceof IAdaptable && ((IAdaptable)object).getAdapter(IFile.class) != null)
        {
            return buildResourceName (getIResourcePath ((IFile)((IAdaptable)object).getAdapter(IFile.class)), getFullName (object));
        }
        else
        {
            String project = getIResourcePath (parent);
            if (project == null)
            {
                return getFullName (object);
            }
            return buildResourceName (project, getFullName (object));
        }
    }
    
    private String getName (Object object)
    {
        if (object instanceof ENamedElement)
        {
            return getName((ENamedElement)object);
        }
        else if (object instanceof IVirtualNode)
        {
            return getName((IVirtualNode)object);
        }
        else if (object instanceof IAdaptable)
        {
            return getName((IAdaptable)object);
        }
        else if (object instanceof EObject)
        {
            return getName ((EObject)object);
        }
        else if (object instanceof ILabelProvider)
        {
        	return ((ILabelProvider)object).getText(object);
        }
        return BLANK;
    }
    
    public String getName ()
    {
        if (element instanceof IStructuredSelection)
        {
            IStructuredSelection selection = (IStructuredSelection) element;
	        if (selection.size() == 1)
	        {
	            return getName(selection.getFirstElement());
	        }
	        else if (selection.size() > 1) 
	        {
	            return MessageFormat.format(ELEMENT_SELECTED, new Object [] {BLANK + selection.size()});
	        }
        }
        return getName (element);
    }
    
    private class LabelInfo
    {
        private Bundle iconBundle;
        private String iconLocation;
        private String displayType;
        private LabelSelector selector;
        public LabelInfo (Bundle iconBundle, String iconLocation, String displayType, LabelSelector selector )
        {
            this.iconBundle = iconBundle;
            this.iconLocation = iconLocation;
            this.displayType = displayType;
            this.selector = selector;
        }
        public Image getIcon()
        {
            return resourceLoader.queryAbsolutePathImageFromRegistry(this.iconBundle, this.iconLocation);
        }
        public String getDisplayType ()
        {
            return this.displayType;
        }
        public LabelSelector getSelector() { return selector; }
    }
}
