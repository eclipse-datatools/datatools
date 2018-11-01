/*******************************************************************************
 * Copyright (c) 2001, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.containment;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.ContainmentProvider;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public final class ContainmentServiceImpl implements ContainmentService {
    public static final ContainmentService INSTANCE = new ContainmentServiceImpl();
    
	public Collection getContainedElements(EObject obj) {
		EClass metaclass = obj.eClass();
		ContainmentProvider provider = this.getProvider(metaclass);

		if(provider == null) {
			Collection children = new LinkedList();
			children.addAll(obj.eContents());
			return children;
		}
		else {
			return provider.getContainedElements(obj);
		}
	}

	public Collection getAllContainedElements(EObject obj) {
		Collection allChildren = new LinkedList();

		Collection children = this.getContainedElements(obj);
		allChildren.addAll(children);
		
		Iterator it = children.iterator();
		while(it.hasNext()) {
			EObject child = (EObject) it.next();
			allChildren.addAll(this.getAllContainedElements(child));
		}		
		return allChildren;
	}
	
	public Collection getContainedDisplayableElements(EObject obj) {
		Collection children = getContainedElements(obj);
		Iterator it = children.iterator();
		while(it.hasNext()) {
			EObject child = (EObject) it.next();
			if(!this.isDisplayableElement(child)) {
				it.remove();
			}
		}		
		return children;
	}

	public Collection getAllContainedDisplayableElements(EObject obj) {
		Collection allChildren = new LinkedList();

		Collection children = this.getContainedDisplayableElements(obj);
		allChildren.addAll(children);
		
		Iterator it = children.iterator();
		while(it.hasNext()) {
			EObject child = (EObject) it.next();
			allChildren.addAll(this.getAllContainedDisplayableElements(child));
		}		
		return allChildren;
	}

	public Collection getContainedDisplayableElements(EObject obj, String group) {
		Collection children = getContainedElements(obj);
		Iterator it = children.iterator();
		while(it.hasNext()) {
			EObject child = (EObject) it.next();
			if(this.isDisplayableElement(child)) {
				String id = this.getGroupId(child);
				if(id != group && (id == null || !id.startsWith(group))) {
					it.remove();
				}
			}
			else {
				it.remove();
			}
		}		
		return children;
	}

	public boolean isDisplayableElement(EObject obj)
	{
		EClass metaclass = obj.eClass();
		ContainmentProvider provider = this.getProvider(metaclass);

		if(provider == null) {
			return false;
		}
		else {
			return provider.isDisplayableElement(obj);
		}
	}

	public EObject getContainer(EObject obj) {
		EClass metaclass = obj.eClass();
		ContainmentProvider provider = this.getProvider(metaclass);

		if(provider == null) {
			return obj.eContainer();
		}
		else {
			return provider.getContainer(obj);
		}
	}
	
	public List getAllContainers(EObject obj) {
	    List containers = new LinkedList();
	    EObject container = this.getContainer(obj); 
	    while(container != null) {
	        containers.add(0, container);
	        container = this.getContainer(container);
	    }
	    return containers;
	}

	public EObject getRootElement(EObject obj) {
	    EObject container = getContainer(obj);
	    while(container != null) {
	        obj = container;
		    container = getContainer(obj);	        
	    }
	    
	    return obj;
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		EClass metaclass = obj.eClass();
		ContainmentProvider provider = this.getProvider(metaclass);

		if(provider == null) {
			return obj.eContainmentFeature();
		}
		else {
			return provider.getContainmentFeature(obj);
		}
	}
	
	public String getGroupId(EObject obj) {
		EClass metaclass = obj.eClass();
		ContainmentProvider provider = this.getProvider(metaclass);

		if(provider == null) {
			return null;
		}
		else {
			return provider.getGroupId(obj);
		}
	}

	private ContainmentServiceImpl() {
		Hashtable providers = new Hashtable();
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "logicalContainment"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) {
				if(configElements[j].getName().equals("containment")) { //$NON-NLS-1$
					String packageURI = configElements[j].getAttribute("package"); //$NON-NLS-1$
					String className = configElements[j].getAttribute("class"); //$NON-NLS-1$
					ContainmentProvider provider = null;
					try {
						provider = (ContainmentProvider) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
					}
					catch(CoreException e) {
					    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
					            "The error was detected when creating the containment provider for " + className + " in "+ packageURI, //$NON-NLS-1$ //$NON-NLS-2$
					            e);
						RDBCorePlugin.getDefault().getLog().log(status);
						continue;
					}

					String priority = configElements[j].getAttribute("priority"); //$NON-NLS-1$
					int currentPriority = 0;
					if (priority != null) {
						try {
							currentPriority = Integer.parseInt(priority);
						}catch (NumberFormatException formatEx){
						}
					} 
					
					if(this.packages.containsKey(packageURI)) {
						Hashtable contentProviders = ((Hashtable) this.packages.get(packageURI));
						if (contentProviders.containsKey(className)) {
							Provider existProvider = (Provider)  contentProviders.get(className);
							if (currentPriority > existProvider.getPriority()) {
								existProvider.setPriority(currentPriority);
								existProvider.setContainmentProvider(provider);
							}
						} else {
							((Hashtable) this.packages.get(packageURI)).put(className, new Provider(provider,currentPriority));
						}
					}
					else {
						Hashtable classNames = new Hashtable();
						classNames.put(className, new Provider(provider,currentPriority));
						this.packages.put(packageURI, classNames);
					}
				}
			}
		}
	}

	private ContainmentProvider getProvider(EClass metaclass) {
		if(this.cache.containsKey(metaclass)) {
			return (ContainmentProvider) this.cache.get(metaclass);
		}
		
		Vector sortedClasses = this.computeClassOrder(metaclass);
		ContainmentProvider provider = this.getProvider(sortedClasses);
		if(provider != null) this.cache.put(metaclass, provider);
		return provider;
	}

	private Vector computeClassOrder(EClass metaclass) {
		Vector result = new Vector(4);
		result.addElement(metaclass);			
		int index = 0;
		for(index=0; index<result.size(); ++index) {
			EClass clazz = (EClass) result.elementAt(index);
			Iterator it = clazz.getESuperTypes().iterator();
			while(it.hasNext()) result.addElement(it.next());			
		}
		return result;
	}
	
	private ContainmentProvider getProvider(Vector classes) {
		int count = classes.size();
		for(int i=0; i<count; ++i) {
			EClass clazz = (EClass) classes.elementAt(i);
			String uri = clazz.getEPackage().getNsURI();
			if(this.packages.containsKey(uri)) {
				Hashtable classNames = (Hashtable) this.packages.get(uri);
				if(classNames.containsKey(clazz.getName())) {
					return (ContainmentProvider) ((Provider)classNames.get(clazz.getName())).getContainmentProvider();
				}
			}
		}
		return null;
	}
	
	private class Provider{
		private ContainmentProvider provider;
		private int currentPriority;
		
		public Provider(ContainmentProvider provider,int priority) {
			this.provider  = provider;
			this.currentPriority = priority;
		}
		
		public int getPriority(){
			return this.currentPriority;
		}
		
		public void setPriority(int priority) {
			this.currentPriority = priority;
		}
		
		public ContainmentProvider getContainmentProvider(){
			return this.provider;
		}
		
		public void setContainmentProvider(ContainmentProvider provider){
			this.provider = provider;
		}
		
	}
	private Hashtable packages = new Hashtable();
	private Hashtable cache = new Hashtable();
}
