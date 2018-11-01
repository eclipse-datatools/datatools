/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.ddl.DdlGenerationMessages;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class DependencyImpactAnalyst {
	public static DependencyImpactAnalyst getInstance() {
		if(DependencyImpactAnalyst.INSTANCE == null) {
			DependencyImpactAnalyst.INSTANCE = new DependencyImpactAnalyst();
		}
		return DependencyImpactAnalyst.INSTANCE;
	}
	
	public DependencyImpactDescription[] getDirectDependencies(EObject obj) {
		List dependencies = new ArrayList();
		Set excluded = this.getAllContainedObjects(obj);

		// extensions
		Iterator it = this.dependencyProviders.iterator();
		while(it.hasNext()) {
			DependencyProvider provider = (DependencyProvider) it.next();
			try {
				DependencyImpactDescription[] deps = provider.getDependencies(obj);
				if(deps != null) {
				for(int i=0; i<deps.length; ++i) {
					// minimum check on the desc
					if(deps[i].getSource()[0] != null && deps[i].getTarget() != null) {
						excluded.add(deps[i].getTarget());
						dependencies.add(deps[i]);
					}
				}
				}
			}
			catch(Exception e) {
				//TODO log the error
				it.remove();
			}
		}

		dependencies.addAll(getDirectDependenciesInternal(obj, excluded));
		
		// convert to an array
		return (DependencyImpactDescription[]) dependencies.toArray(new DependencyImpactDescription[dependencies.size()]);
	}

	private List getDirectDependenciesInternal (EObject obj, Set excluded) {
		List dependencies = new ArrayList();
		excluded.addAll(this.getAllContainers(obj));
		excluded.add(obj);

		// modeled dependecies
		if(obj instanceof SQLObject) {
			Iterator it = ((SQLObject) obj).getDependencies().iterator();
			while(it.hasNext()) {
				Dependency dep = ((Dependency) it.next());
				dependencies.add(this.constructDependencyImpactDescription(new EObject[] {obj}, dep.getDependencyType(), dep.getTargetEnd()));
				excluded.add(dep.getTargetEnd());
			}
		}
		
		// references
		if(!(obj instanceof Dependency)) {
			List vec= this.getAllReferenceDependencies(obj, excluded);
			dependencies.addAll(vec);
		}

		return dependencies;
	}

	public DependencyImpactDescription[] getAggregateDependencies(EObject obj) {
		Set contained = this.getAllContainedObjects(obj);
		ArrayList dependencies = new ArrayList();
		DependencyImpactDescription[] desc = this.getDirectDependencies(obj);
		for(int i=0; i<desc.length; ++i) {
			dependencies.add(desc[i]);
		}
		
		Iterator it = contained.iterator();
		while(it.hasNext()) {
			desc = this.getDirectDependencies((EObject) it.next());
			for(int i=0; i<desc.length; ++i) {
				if ( !contained.contains(desc[i].getTarget()) ) 
				{
					dependencies.add(desc[i]);
				}
			}
		}
		
		DependencyImpactDescription[] r = new DependencyImpactDescription[dependencies.size()];
		it = dependencies.iterator();
		int i = 0;
		while(it.hasNext()) {
			r[i++] = (DependencyImpactDescription) it.next();
		}
		return r;
	}

	public DependencyImpactDescription[] getDirectDependencies(EObject obj, int recursionDepth) {
		ArrayList dependencies = new ArrayList();
		Set excluded = this.getAllContainedObjects(obj);
		excluded.addAll(this.getAllContainers(obj));
		DependencyImpactDescription[] d = getDirectDependencies(obj);
		for(int i=0; i<d.length; ++i) {
			dependencies.add(d[i]);
			excluded.add(d[i].getTarget());
		}

		int size = dependencies.size();
		for(int i=0; i<size; ++i) {
			dependencies.addAll(this.getDirectDependencies(((DependencyImpactDescription) dependencies.get(i)).getTarget(), recursionDepth, excluded));
		}
		
		return (DependencyImpactDescription[]) dependencies.toArray(new DependencyImpactDescription[dependencies.size()]);
	}
	
	public DependencyImpactDescription[] getAggregateDependencies(EObject obj, int recursionDepth) {
		ArrayList dependencies = new ArrayList();
		Set excluded = this.getAllContainedObjects(obj);
		excluded.addAll(this.getAllContainers(obj));
		DependencyImpactDescription[] d = getAggregateDependencies(obj);
		for(int i=0; i<d.length; ++i) {
			dependencies.add(d[i]);
			excluded.add(d[i].getTarget());
		}

		int size = dependencies.size();
		for(int i=0; i<size; ++i) {
			dependencies.addAll(this.getAggregateDependencies(((DependencyImpactDescription) dependencies.get(i)).getTarget(), recursionDepth, excluded));
		}
		
		return (DependencyImpactDescription[]) dependencies.toArray(new DependencyImpactDescription[dependencies.size()]);
	}

	public DependencyImpactDescription[] getDirectImpacted(final EObject obj) {
		//buildDependencyMap(obj);
		buildDependencyMapForAll(obj);
		//this.dumpDependencyMap();
		DependencyImpactDescription[] r = getDirectImpactedInternal(obj);
		clearDependencyMap();
		return r;
	}
	
	public DependencyImpactDescription[] getDirectImpacted2(final EObject obj) {
		buildDependencyMapForAll(obj);
		DependencyImpactDescription[] r = getDirectImpactedInternal(obj);
		return r;
	}
	
	private void buildDependencyMapForAll(final EObject selectedObj) {
		if(this.dependencyMap != null) return;
		this.dependencyMap = new HashMap();
		if(selectedObj instanceof ICatalogObject) {
			return;
		}
//<bgp		else {
//			ResourceSet rs = IBMPluginActivator.getInstance().getResourceSet();
//			try {
//				Iterator rsItor = rs.getResources().iterator();
//				while(rsItor.hasNext()) {
//					Resource r = (Resource)rsItor.next();
//					EList contents = r.getContents();
//					if(contents.size() > 0) {	
//						Object obj = contents.get(0);
//						//(JYEH) added this check for compare and sync which creates a resource for
//						//catalog objects and the resource will show up in the list of resources
//						if (obj instanceof ICatalogObject) continue;
//						if(obj instanceof SQLObject) {
//							buildDependencyMap((EObject)obj);
//						}
//					}
//				}
//			}
//			catch (ConcurrentModificationException e) {
//				// When copying changes in the compare editor, it is
//				//   possible to incur this exception, which will result
//				//   in a failure to copy the complete set of changes.
//				//   Catch this and suppress it to prevent copy errors.
//			}
//bgp>		}
	}
	
	private void buildDependencyMap(final EObject obj) {
		
		EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(obj);
		Set elements = this.getAllContainedObjects(root);
		elements.add(root);
		Iterator i = elements.iterator();
		while(i.hasNext()) {
			EObject o = (EObject) i.next();
			//Set excluded = this.getAllContainedObjects(o);
			Set excluded = new HashSet();
			Iterator depIterator = this.getDirectDependenciesInternal(o, excluded).iterator();
			while(depIterator.hasNext()) {
				DependencyImpactDescription desc = (DependencyImpactDescription) depIterator.next();
				EObject target = desc.getTarget();
				ArrayList impacted;
				if(this.dependencyMap.containsKey(target)) {
					impacted = (ArrayList) this.dependencyMap.get(target);
				}
				else {
					impacted = new ArrayList();
					this.dependencyMap.put(target, impacted);
				}
				EObject[] source = desc.getSource();
				String type = desc.getType();
				for(int k=0; k<source.length; ++k) {
					impacted.add(this.constructDependencyImpactDescription(new EObject[] {target}, type, source[k]));
				}

			}
		}
	}
	
	public void clearDependencyMap() {
		this.dependencyMap = null;
	}

	private DependencyImpactDescription[] getDirectImpactedInternal(final EObject obj) {
		ArrayList impactedVector = new ArrayList();
		
		if(obj instanceof IDatabaseObject) {	
			//Database objects
			IDatabaseObject dbObj = (IDatabaseObject)obj;
			ICatalogObject[] objs = dbObj.getImpacted();
			int len = objs.length;
			ArrayList impacted = new ArrayList();
			for(int i=0; i<len; i++) {
				EObject target = (EObject)objs[i];
				// If the impacted object is FK, use its owner table
				if(target instanceof ForeignKey) {
					target = ((ForeignKey)target).getBaseTable();
				}
				impactedVector.add(this.constructDependencyImpactDescription(new EObject[] {obj}, 
						DependencyImpactMessages.DependencyImpactAnalyst_REFERENCE, 
						target));
			}			
		}
		else {		
			//model objects
			// extensions
			Iterator it = this.impactProviders.iterator();
			while(it.hasNext()) {
				ImpactProvider provider = (ImpactProvider) it.next();
				try {
					DependencyImpactDescription[] impacted = provider.getImpacted(obj);
					if(impacted != null) {
					for(int m=0; m<impacted.length; ++m) {
						// minimum check on the desc
						if(impacted[m].getSource()[0] != null && impacted[m].getTarget() != null) {
							impactedVector.add(impacted[m]);
						}
					}
					}
				}
				catch(Exception e) {
					it.remove();
					IBMPluginActivator.getInstance().writeLog(IStatus.ERROR, 0, e.getMessage(), e);
				}
			}
		
			Object c = this.dependencyMap.get(obj);
			if(c != null) { 
				impactedVector.addAll((ArrayList) c);
			}
		}
		
		return (DependencyImpactDescription[]) impactedVector.toArray(new DependencyImpactDescription[impactedVector.size()]);
	}
	
	public DependencyImpactDescription[] getAggregateImpacted(final EObject obj) {
		//buildDependencyMap(obj);
		buildDependencyMapForAll(obj);
		
		Set contained = this.getAllContainedObjects(obj);
		// Use lists to handle duplicate targets
		ArrayList keys = new ArrayList();
		ArrayList values = new ArrayList();
		DependencyImpactDescription[] desc = this.getDirectImpactedInternal(obj);
		for(int i=0; i<desc.length; ++i) {
			keys.add(desc[i].getTarget());
			values.add(desc[i]);
		}
		
		Iterator it = contained.iterator();
		while(it.hasNext()) {
			desc = this.getDirectImpactedInternal((EObject) it.next());
			for(int i=0; i<desc.length; ++i) {
				if(!contained.contains(desc[i].getTarget())) {
					keys.add(desc[i].getTarget());
					values.add(desc[i]);
				}
			}
		}
		
		DependencyImpactDescription[] r = new DependencyImpactDescription[keys.size()];
		it = values.iterator();
		int i = 0;
		while(it.hasNext()) {
			r[i++] = (DependencyImpactDescription) it.next();
		}
		
		clearDependencyMap();
		
		return r;
	}
	
	public DependencyImpactDescription[] getDirectImpacted(final EObject obj, int recursionDepth) {
		//buildDependencyMap(obj);
		buildDependencyMapForAll(obj);
		// test		
		//this.dumpDependencyMap();
		
		ArrayList dependencies = new ArrayList();
		Set excluded = this.getAllContainedObjects(obj);
		excluded.addAll(this.getAllContainers(obj));
		DependencyImpactDescription[] d = getDirectImpactedInternal(obj);
		for(int i=0; i<d.length; ++i) {
			dependencies.add(d[i]);
			excluded.add(d[i].getTarget());
		}

		int size = dependencies.size();
		for(int i=0; i<size; ++i) {
			dependencies.addAll(this.getDirectImpacted(((DependencyImpactDescription) dependencies.get(i)).getTarget(), recursionDepth, excluded));
		}
		
		DependencyImpactDescription[] r= (DependencyImpactDescription[]) dependencies.toArray(new DependencyImpactDescription[dependencies.size()]);
		
		clearDependencyMap();
		
		return r;
	}
	
	public DependencyImpactDescription[] getAggregateImpacted(final EObject obj, int recursionDepth) {
		//buildDependencyMap(obj);
		buildDependencyMapForAll(obj);
		// test
		//this.dumpDependencyMap();

		ArrayList dependencies = new ArrayList();
		Set excluded = this.getAllContainedObjects(obj);
		excluded.addAll(this.getAllContainers(obj));
		DependencyImpactDescription[] d = getAggregateImpacted(obj);
		for(int i=0; i<d.length; ++i) {
			dependencies.add(d[i]);
			excluded.add(d[i].getTarget());
		}

		int size = dependencies.size();
		for(int i=0; i<size; ++i) {
			dependencies.addAll(this.getAggregateImpacted(((DependencyImpactDescription) dependencies.get(i)).getTarget(), recursionDepth, excluded));
		}
		
		
		DependencyImpactDescription[] r= (DependencyImpactDescription[]) dependencies.toArray(new DependencyImpactDescription[dependencies.size()]); 

		clearDependencyMap();

		return r;
	}

	private Set getAllContainedObjects(EObject obj) {
		Set children = new HashSet();
		ContainmentService service = IBMPluginActivator.getInstance().getContainmentService();
		Iterator it = service.getContainedDisplayableElements(obj).iterator();
		while(it.hasNext()) {
			EObject child = (EObject) it.next();
			if(child instanceof SQLObject) {
				children.add(child);
				children.addAll(this.getAllContainedObjects(child));
			}
		}		
		return children;
	}
	
	private Set getAllContainers(EObject obj) {
		Set containers = new HashSet();
		containers.addAll(ContainmentServiceImpl.INSTANCE.getAllContainers(obj));
		return containers;
	}
	
	private ArrayList getAllReferenceDependencies(EObject obj, Set excluded) {
		ArrayList deps = this.getReferenceDependencies(obj, excluded, obj);
		Iterator it = this.getAllInvisibleChildren(obj).iterator();
		while(it.hasNext()) {
			EObject child = (EObject) it.next();
			if(child instanceof SQLObject) {
				deps.addAll(this.getReferenceDependencies(child, excluded, obj));
			}
		}
		return deps;
	}
	
	private ArrayList getReferenceDependencies(EObject obj, Set excluded, EObject source) {
		ContainmentService service = IBMPluginActivator.getInstance().getContainmentService();
		ArrayList deps = new ArrayList(); 
		
		EClass eClass = obj.eClass();
		Iterator references = eClass.getEAllReferences().iterator();
		while(references.hasNext()) {
			EReference reference = (EReference) references.next();
			//if(!reference.isContainment()) {//??
				if(reference.getEOpposite() == null) { // directional
					if(reference.isMany()) {
						Iterator it = ((Collection) obj.eGet(reference)).iterator();
						while(it.hasNext()) {
							Object t = it.next();
							if(t instanceof Dependency) {
								// obj doesn't reference Dependency itself, it reference the target end of Dependency
								t = ((Dependency)t).getTargetEnd();
							}
							if(t instanceof SQLObject && !excluded.contains(t)) {
								EObject target = (EObject) t;
								EObject container = service.getContainer(target);
								if(/*container != null &&*/ service.isDisplayableElement(target)) {
									excluded.add(target);
									deps.add(this.constructDependencyImpactDescription(new EObject[] {source}, DependencyImpactMessages.DependencyImpactAnalyst_REFERENCE, target)); 
								}											
							}						
						}
					}
					else {
						Object t = obj.eGet(reference);
						if(t instanceof SQLObject && !excluded.contains(t)) {
							EObject target = (EObject) t;
							EObject container = service.getContainer(target);
							if(/*container != null &&*/ service.isDisplayableElement(target)) {
								excluded.add(target);
								deps.add(this.constructDependencyImpactDescription(new EObject[] {source}, DependencyImpactMessages.DependencyImpactAnalyst_REFERENCE, target)); 
							}											
						}
					}
				}
				else { // bidirectional
					if(!reference.isMany()) {
						// use impact extension to replace these
//						Object t = obj.eGet(reference);
//						if(t instanceof EObject && !excluded.contains(t)) {
//							if( (obj instanceof ForeignKey) || 
//									(isLogicalGeneralization(obj)) || 
//									(isLogicalRelationshipEnd(obj))) {							
//								// handle relationship, generalization and FK special cases
//								EObject target = (EObject) t;
//								EObject container = service.getContainer(target);
//								EObject objContainer = service.getContainer(obj);
//								if(container != null 
//										&& service.isDisplayableElement(target) 
//										&& (!target.equals(objContainer))
//										&& (!((source instanceof ForeignKey) && (target instanceof BaseTable) && (!objContainer.equals(target))))) {// remove FK to Parent table reference
//									excluded.add(target);
//									EObject displayedSource = service.getContainer(source);
//									EObject displayedTarget;
//									if(target instanceof PrimaryKey) {
//										displayedTarget = service.getContainer(target);
//									}
//									else 
//										displayedTarget = target;
//									deps.add(this.constructDependencyImpactDescription(new EObject[] {displayedSource}, Messages.DependencyImpactAnalyst_REFERENCE, displayedTarget)); 
//									//deps.add(this.constructDependencyImpactDescription(new EObject[] {displayedTarget}, Messages.getString("DependencyImpactAnalyst.REFERENCE"), displayedSource)); //$NON-NLS-1$
//								}
//							}
//						}						
					}
					if(reference.isMany()) {// bidirectional many
						if(!reference.isMany()) {
							Object t = obj.eGet(reference);
							if(t instanceof EObject && !excluded.contains(t)) {
								EObject target = (EObject) t;
								EObject container = service.getContainer(target);
								if(container != null && service.isDisplayableElement(target)) {
									excluded.add(target);
									deps.add(this.constructDependencyImpactDescription(new EObject[] {source}, DependencyImpactMessages.DependencyImpactAnalyst_REFERENCE, target)); 
								}											
							}						
						}
//						Iterator it = ((Collection) obj.eGet(reference)).iterator();
//						while(it.hasNext()) {
//							Object t = it.next();
//							if(t instanceof SQLObject && !excluded.contains(t)) {
//								EObject target = (EObject) t;
//								EObject container = service.getContainer(target);								
//								if(/*container != null &&*/ service.isDisplayableElement(target) &&
//										(!((source instanceof PrimaryKey) && (target instanceof ForeignKey))) && //need to remove PK to FK dependency
//										(!((source instanceof BaseTable) && (target instanceof ForeignKey) && (!container.equals(source)))) ) {//need to remove Parent table to FK reference as dependency
//									excluded.add(target);
//									deps.add(this.constructDependencyImpactDescription(new EObject[] {source}, Messages.getString("DependencyImpactAnalyst.REFERENCE"), target)); //$NON-NLS-1$
//								}
//							}
//						}
					}
				}
			//}
		}
		return deps;
	}
	
	// Since the dependency to Logical model can NOT be introduced, use the getMethod to identify the types
	private boolean isLogicalRelationshipEnd(EObject obj) {
		try {
			Method m = obj.getClass().getMethod("getVerbPhrase", null);
			if(m != null)
				return true;
		}
		catch(NoSuchMethodException exp) {
		}
		return false;
	}
	
	private boolean isLogicalGeneralization(EObject obj) {
		try {
			Method m = obj.getClass().getMethod("getSupertype", null);
			if(m != null)
				return true;
		}
		catch(NoSuchMethodException exp) {
		}
		return false;
	}
	
	private ArrayList getAllInvisibleChildren(EObject obj) {
		ArrayList children = this.getInvisibleChildren(obj);
		int size = children.size();
		for(int i=0; i<size; ++i) {
			children.addAll(this.getAllInvisibleChildren((EObject) children.get(i)));
		}
		return children;
	}
	
	private ArrayList getInvisibleChildren(EObject obj) {
		ArrayList children = new ArrayList();
		ContainmentService service = IBMPluginActivator.getInstance().getContainmentService();
		Set excluded = new HashSet();
		excluded.addAll(service.getContainedDisplayableElements(obj));
		
		Iterator it = service.getContainedElements(obj).iterator();
		while(it.hasNext()) {
			Object child = it.next();
			if(!excluded.contains(child)) {
				children.add(child);
			}
		}		
		return children;		
	}
	
	private DependencyImpactDescription constructDependencyImpactDescription(final EObject[] source, final String type, final EObject target) {
		return new DependencyImpactDescription() {
			public EObject getTarget() {
				return target;
			}

			public EObject[] getSource() {
				return source;
			}

			public String getType() {
				return type;
			}
		};
	}
	
	private ArrayList getDirectDependencies(final EObject obj, int recursionDepth, Set excluded) {
		ArrayList dependencies = new ArrayList();
		ArrayList excludedDependencies = new ArrayList();
		if(recursionDepth-- == 0) return dependencies;

		DependencyImpactDescription[] d = this.getDirectDependencies(obj);
		for(int i=0; i<d.length; ++i) {
			if(!excluded.contains(d[i].getTarget())) {
				dependencies.add(d[i]);
				excluded.add(d[i].getTarget());				
			}
			else {
				excludedDependencies.add(d[i]);
			}
		}
		int size = dependencies.size();
		for(int i=0; i<size; ++i) {
			dependencies.addAll(this.getDirectDependencies(((DependencyImpactDescription) dependencies.get(i)).getTarget(), recursionDepth, excluded));
		}
		dependencies.addAll(excludedDependencies);
		
		return dependencies;		
	}
	
	private ArrayList getAggregateDependencies(final EObject obj, int recursionDepth, Set excluded) {
		ArrayList dependencies = new ArrayList();
		ArrayList excludedDepencencies = new ArrayList();
		if(recursionDepth-- == 0) return dependencies;

		DependencyImpactDescription[] d = this.getAggregateDependencies(obj);
		for(int i=0; i<d.length; ++i) {
			if(!excluded.contains(d[i].getTarget())) {
				dependencies.add(d[i]);
				excluded.add(d[i].getTarget());
			}
			else {
				excludedDepencencies.add(d[i]);
			}
		}
		int size = dependencies.size();
		for(int i=0; i<size; ++i) {
			dependencies.addAll(this.getAggregateDependencies(((DependencyImpactDescription) dependencies.get(i)).getTarget(), recursionDepth, excluded));
		}
		dependencies.addAll(excludedDepencencies);
		
		return dependencies;		
	}
	
	private ArrayList getDirectImpacted(final EObject obj, int recursionDepth, Set excluded) {
		ArrayList dependencies = new ArrayList();
		ArrayList excludedDepencencies = new ArrayList();
		if(recursionDepth-- == 0) return dependencies;

		DependencyImpactDescription[] d = this.getDirectImpactedInternal(obj);
		for(int i=0; i<d.length; ++i) {
			if(!excluded.contains(d[i].getTarget())) {
				dependencies.add(d[i]);
				excluded.add(d[i].getTarget());
			}
			else {
				excludedDepencencies.add(d[i]);
			}
		}
		int size = dependencies.size();
		for(int i=0; i<size; ++i) {
			dependencies.addAll(this.getDirectImpacted(((DependencyImpactDescription) dependencies.get(i)).getTarget(), recursionDepth, excluded));
		}
		dependencies.addAll(excludedDepencencies);
		
		return dependencies;		
	}

	private ArrayList getAggregateImpacted(final EObject obj, int recursionDepth, Set excluded) {
		ArrayList dependencies = new ArrayList();
		ArrayList excludedDependencies = new ArrayList();
		if(recursionDepth-- == 0) return dependencies;

		DependencyImpactDescription[] d = this.getAggregateImpacted(obj);
		for(int i=0; i<d.length; ++i) {
			if(!excluded.contains(d[i].getTarget())) {
				dependencies.add(d[i]);
				excluded.add(d[i].getTarget());
			}
			else {
				excludedDependencies.add(d[i]);
			}
		}
		int size = dependencies.size();
		for(int i=0; i<size; ++i) {
			dependencies.addAll(this.getAggregateImpacted(((DependencyImpactDescription) dependencies.get(i)).getTarget(), recursionDepth, excluded));
		}
		dependencies.addAll(excludedDependencies);
		
		return dependencies;		
	}

	private void initProviders() {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "dependencyExtension"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) {
				if(configElements[j].getName().equals("dependency")) { //$NON-NLS-1$
					DependencyProvider provider = null;
					try {
						provider = (DependencyProvider) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
					}
					catch(CoreException e) {
						continue;
					}
					this.dependencyProviders.add(provider);
				}
			}
		}

		extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "impactExtension"); //$NON-NLS-1$ //$NON-NLS-2$
		extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) {
				if(configElements[j].getName().equals("impact")) { //$NON-NLS-1$
					ImpactProvider provider = null;
					try {
						provider = (ImpactProvider) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
					}
					catch(CoreException e) {
						continue;
					}
					this.impactProviders.add(provider);
				}
			}
		}
	}
	
	private DependencyImpactAnalyst() {
		this.initProviders();
	}
	
	private void dumpDependencyMap() {
		Set keySet = dependencyMap.keySet();
		Iterator keyItor = keySet.iterator();
		Object keyObj;
		ArrayList values;
		while(keyItor.hasNext()) {
			keyObj = keyItor.next();
			System.out.println("key = " + ((SQLObject)keyObj).eClass().getName() + " " + ((SQLObject)keyObj).getName());
			values = (ArrayList)dependencyMap.get(keyObj);
			Iterator valItor = values.iterator();
			while(valItor.hasNext()) {
				DependencyImpactDescription d = (DependencyImpactDescription)valItor.next();
				EObject eVal = d.getTarget();
				if(eVal instanceof SQLObject) {
					SQLObject val = (SQLObject)eVal;
					System.out.println("\t" + "target = " + val.eClass().getName() + " " + val.getName());
				}
				else {
					System.out.println("\t" + "target = " + eVal.getClass().getName());
				}
				Object[] srcs = d.getSource();
				for(int i=0; i<srcs.length; i++) {
					Object src = srcs[i];
					if(src instanceof SQLObject) {
						System.out.println("\t" + "source = " + ((SQLObject)src).eClass().getName() + " " + ((SQLObject)src).getName());
					}
					else {
						System.out.println("\t" + "source = " + ((SQLObject)src).getClass().getName());
					}
				}
			}
		}
	}
	
	private static DependencyImpactAnalyst INSTANCE = null;
	private ArrayList dependencyProviders = new ArrayList();
	private ArrayList impactProviders = new ArrayList();
	private Map dependencyMap = null;
}
